/*
    Copyright 2014 OPM.gov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package gov.opm.scrd.batchprocessing.jobs;

import static gov.opm.scrd.batchprocessing.jobs.BatchProcessHelper.CRLF;
import static gov.opm.scrd.batchprocessing.jobs.BatchProcessHelper.checkInteger;
import static gov.opm.scrd.batchprocessing.jobs.BatchProcessHelper.checkState;
import static gov.opm.scrd.batchprocessing.jobs.BatchProcessHelper.nullToZero;
import gov.opm.scrd.entities.application.AuditBatch;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.application.PaymentStatementPrint;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.batchprocessing.GLFileRecord;
import gov.opm.scrd.entities.batchprocessing.ImportStatus;
import gov.opm.scrd.entities.batchprocessing.LockboxPaymentType;
import gov.opm.scrd.entities.batchprocessing.MainframeImport;
import gov.opm.scrd.entities.batchprocessing.MainframeRecordType;
import gov.opm.scrd.entities.common.IdentifiableEntity;
import gov.opm.scrd.services.AuthorizationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.SecurityService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * <p>
 * This class representing the batch payment processing job. It is expected to be executed daily.
 * In a nutshell, this job receives a set of files for the processing, where the payment data is contained.
 * It perform necessary file processing, importing and calculation and updates the master records for them.
 * Finally, it produces the output GL file. The job can also send notification about its progress and write
 * locally for logging purposes.
 * </p>
 *
 * <p>
 * Thread - safety. The class is effectively thread - safe. The class state is not changed after configuration,
 * the configuration is done in a thread - safe manner. All the critical resources (database, local file) are
 * not expected to be shared, as only one instance of the job is expected to be executed on a machine in a given
 * day time frame.
 * </p>
 * <p>
 * <em>Changes in 1.1 (Bug fix OPM-156 Batch Processing AudtRecord creation for lockbox errors):</em>
 * <ul>
 * <li>#add auditing for lock box import errors.</li>
 * </ul>
 * </p>
 * <p>
 * <em>Changes in 1.2 (OPM - FACES Assembly 1.0):</em>
 * <ul>
 * <li>persist payment statement print at the end of job execution.</li>
 * </ul>
 * </p>
 *
 * @author faeton, liuliquan, bannie2492
 * @version 1.2
 */
public class BatchProcessingJob {

    /**
     * Represents the pattern used parse address in C line.
     */
    private static final Pattern ADDRESS_PATTERN = Pattern.compile(
            "^([a-zA-Z\\s]+)[,]?\\s+([a-zA-Z][a-zA-Z])\\s+(\\d{5}|(\\d{5}-\\d{4})|(\\d{5}\\s\\d{4}))\\s*$");

    /**
     * Represents the pattern used parse name in C line.
     */
    private static final Pattern NAME_PATTERN = Pattern.compile(
        "^([a-zA-Z]+)\\s*([a-zA-Z])?.?\\s+([a-zA-Z]+),?\\s*([a-zA-Z]{2,3})?\\s*$");

    /**
     * Represents the main frame comparator.
     */
    private static final MainFrameImportComparator MAIN_FRAME_COMPARATOR = new MainFrameImportComparator();

    /**
     * Represents the JPQL to query User by username.
     */
    private static final String JPQL_QUERY_USER_BY_USERNAME = "SELECT e FROM User e"
        + " WHERE e.deleted = false AND e.username = :username";

    /**
     * Represents the EntityManager instance used to manage data in the persistence.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private EntityManager entityManager;

    /**
     * Represents the Logger instance used for logging.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private Logger logger;

    /**
     * Represents the SecurityService instance used for checking user security.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Represents the BillProcessor instance used to calculate bills.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private BillProcessor billProcessor;

    /**
     * Represents the JavaMailSender instance used to send email.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Represents the RFile instance used to parse a line of lockbox file.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private RFile rFile;

    /**
     * Represents the recipient of the email.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
    */
    @Autowired
    private String generalMailRecipient;

    /**
     * Represents the recipient of the bill email.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
    */
    @Autowired
    private String billMailRecipient;

    /**
     * Represents the path to the template of the file import email to generate.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
    */
    @Autowired
    private String fileImportMailTemplate;

    /**
     * Represents the velocity engine used to generate file import email.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private VelocityEngine velocityEngine;

    /**
     * Represents the retry interval for batch processing to restart when
     * no lockbox file has arrived or error occurred.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private Integer retryInterval;

    /**
     * Represents the maximum number of retries for the single single run of batch processing.
     * Must not be negative.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private Integer retriesNumber;

    /**
     * Represents the name of the user performing the batch job.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private String currentUserName;

    /**
     * Represents the path to the directory where input lockbox files are stored.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private String inputDirectoryPath;

    /**
     * Represents the wild card pattern of input lockbox files to process.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private String wildCardInput;

    /**
     * Represents the directory which is expected to be cleaned after the processing is done.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private String directoryToClean;

    /**
     * Represents the wild card pattern of files in directory to clean.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private String wildCardToClean;

    /**
     * Represents the maximum age of files to clean.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private Integer ageToCleanInDays;

    /**
     * Represents the path to the directory where General Ledger files will be created.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private String glDirectory;

    /**
     * Represents the name of database used in the application. It is set purely for logging reasons.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private String databaseString;

    /**
     * Represents the name of datasource used in the application. It is set purely for logging reasons.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private String datasourceString;

    /**
     * Represents the id of workstation used in the application. It is set purely for logging reasons.
     * <p/>
     * Injected by Spring. It has a setter. The default value is null.
     */
    @Autowired
    private String workstationId;
    /**
     * Represents the batch process user. It is initialized in at the beginning of processing.
     */
    private User batchProcessUser;

    /**
     * Represents today's audit batch. It is retrieved from database at the beginning of each execution.
     */
    private AuditBatch todayAuditBatch;

    /**
     * Represents batch phase: FILE_IMPORT or BILL_PROCESS. Used to determine the recipient of error email.
     */
    private BatchPhase batchPhase;

    /**
     * Empty constructor.
     */
    public BatchProcessingJob() {
        // Empty
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws BatchProcessingConfigurationException if the instance was not initialized properly.
     */
    @PostConstruct
    protected void checkInit() {

        checkState(logger == null, "logger is not set"); // Logging is required for batch process
        checkState(entityManager == null, "entityManager is not set");
        checkState(securityService == null, "securityService is not set");
        checkState(billProcessor == null, "billProcessor is not set");
        checkState(mailSender == null, "mailSender is not set");
        checkState(velocityEngine == null, "velocityEngine is not set");
        checkState(rFile == null, "rFile is not set");

        checkState(StringUtils.isEmpty(generalMailRecipient), "generalMailRecipient is not set");
        checkState(StringUtils.isEmpty(billMailRecipient), "billMailRecipient is not set");
        checkState(StringUtils.isEmpty(fileImportMailTemplate), "fileImportMailTemplate is not set");
        checkState(StringUtils.isEmpty(inputDirectoryPath), "inputDirectoryPath is not set");
        checkState(StringUtils.isEmpty(wildCardInput), "wildCardInput is not set");
        checkState(StringUtils.isEmpty(directoryToClean), "directoryToClean is not set");
        checkState(StringUtils.isEmpty(wildCardToClean), "wildCardToClean is not set");
        checkState(StringUtils.isEmpty(glDirectory), "glDirectory is not set");
        checkState(StringUtils.isEmpty(databaseString), "databaseString is not set");
        checkState(StringUtils.isEmpty(datasourceString), "datasourceString is not set");
        checkState(StringUtils.isEmpty(workstationId), "workstationId is not set");
        checkState(StringUtils.isEmpty(currentUserName), "currentUserName is not set");

        checkState(retriesNumber == null, "retriesNumber is not set");
        if (retriesNumber < 0) {
            throw new BatchProcessingConfigurationException("retriesNumber must be >= 0.");
        }
        checkInteger(retryInterval, "retryInterval is required to be positive");
        checkInteger(ageToCleanInDays, "ageToCleanInDays is required to be positive");
    }

    /**
     * Reset before each execution.
     */
    private void reset() {
        batchProcessUser = null;
        batchPhase = null;
        todayAuditBatch = null;
        rFile.clear();
        entityManager.clear();
    }

    /**
     * Executes the job. The method checks necessary permissions, import files,
     * process bills and create General Ledger file.
     * <p/>
     * No exception is thrown from this method. We'll retry max <code>retriesNumber</code>
     * times if error occurred.
     */
    public void execute() {

        for (int i = 0; i <= retriesNumber; i++) {

            StringBuilder procMessage = new StringBuilder();

            try {
                if (i == 0) {
                    logger.info("Service Credit Batch started.");
                } else {
                    logger.info("Service Credit Batch retry " + i + ".");
                }
                if (doExecute(procMessage)) {
                    logger.info("Service Credit Batch finished successfully.");
                    return;
                }
            } catch (Exception e) {
                // Catch major error, send notify email, we'll retry
                logger.error(e.getMessage(), e);

                String subject = "Service Credit Nightly Batch: ERROR!!";
                String mailType = "ERROR";
                if (batchPhase == BatchPhase.FILE_IMPORT) {
                    subject = "Service Credit Import: BATCH IMPORT ERROR";
                    mailType = "FileImport";
                } else if (batchPhase == BatchPhase.BILL_PROCESS) {
                    mailType = "BillProcessing";
                }
                savePaymentStatusPrint(procMessage.toString() + CRLF + e.getMessage());
                this.auditError(subject, procMessage.toString() + CRLF + e.getMessage());
                notifyByEmail(procMessage.toString() + CRLF + e.getMessage(), subject, mailType);
            }

            // Sleep interval time then retry
            if (i < retriesNumber) {
                logger.info("Service Credit Batch will retry in " + retryInterval / 60000 + " minutes...");
                try {
                    Thread.sleep(retryInterval);
                } catch (InterruptedException e) {
                    logger.error("Thread interrupted when sleeping for retring", e);
                    return;
                }
            } else {
                logger.warn("Service Credit Batch has retried max " + retriesNumber
                    + " times today. The batch process will exit unsuccessfully.");
            }
        }
    }

    /**
     * Do execution.
     *
     * @param procMessage The process message. Used to build the mail message.
     * @return true if execution is successful; false to retry.
     * @throws BatchProcessingException If major error occurred.
     * @throes OPMException if there is any error during auditing
     */
    private boolean doExecute(StringBuilder procMessage) throws BatchProcessingException, OPMException {
        // Reset
        reset();

        Date now = new Date(SysTime.instance.now());

        procMessage.append("Service Credit Batch started at ");
        procMessage.append(DateFormat.getTimeInstance(DateFormat.LONG, Locale.US).format(now));
        procMessage.append(" on ");
        procMessage.append(DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(now));
        procMessage.append(". Batch done at @#$%EndingTime%$#@.");
        procMessage.append(CRLF).append(CRLF);

        try {
            // Initialize the batchProcessUser
            List<User> list = entityManager.createQuery(JPQL_QUERY_USER_BY_USERNAME, User.class)
                    .setParameter("username", currentUserName)
                    .getResultList();

            if (list.isEmpty()) {
                throw new AuthorizationException(
                        "Batch process user [" + currentUserName + "] is not found");
            }

            batchProcessUser = list.get(0);

            // Authorize
            securityService.authorize(currentUserName,
                    Arrays.asList(new String[] {batchProcessUser.getRole().getName()}),
                    "batchProcessingJob");
        } catch (AuthorizationException e) {
            logger.error("User " + currentUserName + " is not allowed to run the batch service.", e);
            procMessage.append("User ");
            procMessage.append(currentUserName);
            procMessage.append(" is not allowed to run the batch service. ");
            if (batchProcessUser == null) {
                procMessage.append("He does not exist. ");
            } else {
                procMessage.append("He is in the ").append(batchProcessUser.getRole().getName())
                    .append(" group instead of the System role. ");
            }
            procMessage.append("This is a serious configuration error. Call the Program Manager or DBA. ");
            notifyByEmail(procMessage.toString(), "Service Credit User Violation", "ERROR");
            savePaymentStatusPrint(procMessage.toString());
            return false;
        } catch (OPMException e) {
            throw new BatchProcessingException("Database error authorizing batch user.", e);
        } catch (PersistenceException e) {
            throw new BatchProcessingException("Database error authorizing batch user.", e);
        }

        // Initialize today's audit batch
        initTodayAuditBatch(now);

        // Is now holiday
        boolean isNowHoliday = isNowHoliday(now);
        logger.info("Is today holiday : " + isNowHoliday);

        batchPhase = BatchPhase.FILE_IMPORT;

        // Import files
        if (!importFiles(now, isNowHoliday)) {
            savePaymentStatusPrint(procMessage.toString());
            return false;
        }

        // Clean old files
        File toCleanDirectory = new File(directoryToClean);
        logger.info("Start cleaning old files in: " + toCleanDirectory);
        cleanOutOldFiles(toCleanDirectory);
        logger.info("End cleaning old files in: " + toCleanDirectory);

        if (!isNowHoliday) {
            batchPhase = BatchPhase.BILL_PROCESS;
            logger.info("Start Bill Processing.");

            boolean noMinorError = true;

            // Process bills
            try {
                entityManager.clear();
                billProcessor.processBills(todayAuditBatch.getId(), procMessage, 0, 0, 0, 0, 0);
            } catch (BillProcessingException e) {
                throw new BatchProcessingException(e.getMessage(), e);
            }
            logger.info("End Bill Processing.");

            // BatchDailyAccountUpdate
            logger.info("Start daily account updating.");
            noMinorError = batchDailyAccountUpdate(procMessage) && noMinorError;
            logger.info("End daily account updating.");

            // Make GL files
            File glFileDirectory = new File(glDirectory);
            logger.info("Start making GL file: " + glFileDirectory);
            noMinorError = makeGLFile(glFileDirectory, procMessage, now) && noMinorError;
            logger.info("End making GL file: " + glFileDirectory);

            // Notify process email
            String subject = noMinorError ? "Service Credit Nightly Batch: Good"
                : "Service Credit Nightly Batch: Warning!";
            savePaymentStatusPrint(procMessage.toString());
            notifyByEmail(procMessage.toString(), subject, "BillProcessing");
        }

        return true;
    }

    /**
     * Initialize today's audit batch.
     *
     * @param today The today.
     * @throws BatchProcessingException If major error occurred.
     */
    private void initTodayAuditBatch(Date today) throws BatchProcessingException {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(today.getTime());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        try {
            // Query today's AuditBatch with max id
            TypedQuery<AuditBatch> query = entityManager.createQuery(
                    "select e from AuditBatch e where e.eventYear=:eventYear "
                            + "AND e.eventMonth=:eventMonth AND e.eventDay=:eventDay "
                            + "AND e.userKey=:userKey order by e.id desc", AuditBatch.class);
            query.setParameter("eventYear", year);
            query.setParameter("eventMonth", month);
            query.setParameter("eventDay", day);
            query.setParameter("userKey", batchProcessUser.getId());

            query.setMaxResults(1);

            List<AuditBatch> result = query.getResultList();

            if (result == null || result.isEmpty()) {
                // Today's AuditBatch not exist yet, create it
                AuditBatch ab = new AuditBatch();
                ab.setEventYear(year);
                ab.setEventMonth(month);
                ab.setEventDay(day);
                ab.setUserKey(batchProcessUser.getId());
                ab.setBatchTime(today);
                ab.setAmountImported(BigDecimal.ZERO);
                ab.setAmountProcessed(BigDecimal.ZERO);
                ab.setErrorImporting(false);
                ab.setErrorProcessing(false);
                ab.setErrorCountProcessing(0);
                ab.setErrorCountImporting(0);
                ab.setNumberChangeRequests(0);
                ab.setAchStopLetters(0);
                ab.setInitialBillsProcessed(0);
                ab.setPaymentsProcessed(0);
                ab.setRefundMemos(0);
                ab.setReversedProcessed(0);
                ab.setNumberAccepted(0);
                ab.setNumberSuspended(0);
                ab.setNumberUnresolved(0);
                ab.setNumberAchAccepted(0);
                ab.setNumberAchSuspended(0);
                ab.setNumberAchUnresolved(0);
                todayAuditBatch = persistEntity(ab);
            } else {
                todayAuditBatch = result.get(0);
            }

        } catch (PersistenceException pe) {
            throw new BatchProcessingException("Database error while getting audit batch log" , pe);
        }
    }

    /**
     * Checks whether the current day is a holiday.
     *
     * @param now The current day.
     * @return True if the current date is a holiday, false otherwise.
     * @throws BatchProcessingException If major error occurred.
     */
    protected boolean isNowHoliday(Date now) throws BatchProcessingException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
            return true; // Sunday for 0 and Saturday for 6 are holidays
        }

        try {
            startTransaction();

            StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("IsThisHoliday");
            sp.setParameter("pDate2Test", now, TemporalType.DATE);

            Boolean result = (Boolean) sp.getSingleResult();

            commitTransaction();

            return result;
        } catch (PersistenceException pe) {
            throw new BatchProcessingException("Database error checking holiday.", pe);
        }
    }

    /**
     * Import lock box files.
     *
     * @param now The current date.
     * @param isNowHoliday Indicate whether current is holiday
     * @return true if execution is successful; false to retry.
     * @throws BatchProcessingException If major error occurred.
     */
    private boolean importFiles(Date now, boolean isNowHoliday) throws BatchProcessingException {
        StringBuilder procMessage = new StringBuilder();
        procMessage.append("Service Credit Batch started at ");
        procMessage.append(DateFormat.getTimeInstance(DateFormat.LONG, Locale.US).format(now));
        procMessage.append(" on ");
        procMessage.append(DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(now));
        procMessage.append(". Batch done at @#$%EndingTime%$#@.");
        procMessage.append(CRLF).append(CRLF);

        File inputDirectory = new File(inputDirectoryPath);
        if (!inputDirectory.exists() || !inputDirectory.isDirectory() || !inputDirectory.canRead()) {
            logger.error("Can not read folder: " + inputDirectory);

            procMessage.append("THE NETWORK IS DOWN! ");
            procMessage.append(CRLF);
            procMessage.append("Network Services not Available for Service Credit. Cannot access the ");
            procMessage.append(inputDirectoryPath);
            procMessage.append(" network folder. Please ask the Help Desk to investigate. ");
            procMessage.append("SERVICE CREDIT IS SHUT DOWN UNTIL THIS ERROR IS FIXED! ");
            notifyByEmail(procMessage.toString(), "SERVICE CREDIT BATCH CANNOT ACCESS NETWORK", "Testing Network");
            auditError("SERVICE CREDIT BATCH CANNOT ACCESS NETWORK", procMessage.toString());
            return false;
        }

        // Filter the input lockbox files
        final String regex = wildCardInput.replace("?", ".?").replace("*", ".*?");
        File[] inputFiles = inputDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File inputFile) {
                if (inputFile.getName().matches(regex)) {
                    if (inputFile.isFile() && inputFile.canRead() && inputFile.canWrite()) {
                        return true;
                    }
                    logger.warn("Does not have read/write permission to file: " + inputFile);
                }
                return false;
            }
        });

        if (inputFiles.length == 0) {
            if (!isNowHoliday && Boolean.TRUE != todayAuditBatch.getFileReceived()) {
                logger.error("No files arrived today in: " + inputDirectory);

                procMessage.append("Today's Lockbox Bank File has not arrived. Please notify Production Control"
                        + " and BSG that the data file is not in the ");
                procMessage.append(inputDirectoryPath);
                procMessage.append(" network share. The nightly batch process is scheduled to run at ");
                procMessage.append(DateFormat.getTimeInstance(DateFormat.LONG, Locale.US).format(now));
                procMessage.append(" and today's import should run before that time. ");
                notifyByEmail(procMessage.toString(), "SERVICE CREDIT LOCKBOX FILE IS LATE!", "ERROR");
                auditError("SERVICE CREDIT LOCKBOX FILE IS LATE!", procMessage.toString());
                return false;
            }
            return true;
        }

        // Mark file arrived
        if (Boolean.TRUE != todayAuditBatch.getFileReceived()) {
            todayAuditBatch.setFileReceived(true);
            try {
                todayAuditBatch = mergeEntity(todayAuditBatch);
            } catch (PersistenceException pe) {
                throw new BatchProcessingException(
                    "Database error while updating audit batch log when importing files" , pe);
            }
        }

        // Import files
        logger.info("Start importing files in: " + inputDirectory);
        for (File inputFile : inputFiles) {
            if (!inputFile.exists() || !inputFile.isFile() || !inputFile.canRead()) {
                // Just make sure
                logger.warn("Lockbox file is not a readable file: " + inputFile);
                continue;
            }

            importFile(procMessage, inputFile);
        }
        logger.info("End importing files in: " + inputDirectory);

        return true;
    }

    /**
     * Import a single lock box file.
     *
     * @param procMessage The process message. Used to build the mail message.
     * @param inputFile The lock box file to import
     * @return The import status
     * @throws BatchProcessingException If major error occurred.
     */
    private ImportStatus importFile(StringBuilder procMessage, File inputFile) throws BatchProcessingException {
        // Create the import status
        ImportStatus importStatus = new ImportStatus();
        importStatus.setAuditBatchId(todayAuditBatch.getId());

        // Load file text content
        logger.info("Start loading file content from: " + inputFile);
        List<MainframeImport> mainFrameImports = loadFileContent(inputFile, importStatus);
        logger.info("End loading file content from: " + inputFile);

        try {
            if (!mainFrameImports.isEmpty()) {
                // Process the MainframeImport data
                logger.info("Start processing MainframeImport data from: " + inputFile);
                processMainframeData(mainFrameImports, importStatus);
                logger.info("End processing MainframeImport data from: " + inputFile);

                // Collate new mainframe payments
                if (importStatus.getNumberDiscreteRecords() > 0) {
                    logger.info("Start collating new mainframe payments from: " + inputFile);
                    collateNewMainframePayments(importStatus);
                    logger.info("End collating new mainframe payments from: " + inputFile);
                }
            }
        } finally {
            if (Double.compare(importStatus.getTransactionsTotal().doubleValue(),
                    importStatus.getFileSummaryTotal().doubleValue()) != 0) {
                importStatus.setSuccessful(false);
            }
            // Log import status
            logger.info(logImportStatus(importStatus));
            sendFileImportEmail(procMessage, importStatus);
        }

        return importStatus;
    }

    /**
     * Send file import email.
     *
     * @param procMessage The process message. Used to build the mail message.
     * @param importStatus The file import status
     */
    private void sendFileImportEmail(StringBuilder procMessage, ImportStatus importStatus) {

        // Determine the file import mail subject
        String subjectLine = "Service Credit Import: ";
        if (importStatus.getSuccessful() && importStatus.getPaymentsGood()
                && importStatus.getChangeRecordsGood()) {
            subjectLine += "OK ";
        } else {
            if (!importStatus.getSuccessful()) {
                subjectLine += "BATCH IMPORT ERROR ";
            }
            if (importStatus.getNumberGoodSummaryRecords() > 0) {
                if (Double.compare(importStatus.getFileSummaryTotal().doubleValue(),
                        importStatus.getTotalCheckPayments()
                        .add(importStatus.getTotalACHPayments())
                        .add(importStatus.getTotalDupePayments())
                        .add(importStatus.getTotalSkippedPayments()).doubleValue()) != 0) {
                    subjectLine += "OUT OF BALANCE! ";
                }
            }
            if (importStatus.getNumberDupeCheckPayments() + importStatus.getNumberDupeAchPayments() > 0) {
                subjectLine += "DUPLICATE PAYMENTS ";
            }
            if (!importStatus.getPaymentsGood()) {
                subjectLine += "PAYMENT ERROR ";
            }
            if (!importStatus.getChangeRecordsGood()) {
                if (importStatus.getSuccessful() && importStatus.getPaymentsGood()) {
                    subjectLine += "OK except for ";
                }
                subjectLine += "Change record rejected. ";
            }
        }

        // Notify file import email
        notifyByEmail(procMessage.append(makeImportMailMessage(importStatus)).toString(), subjectLine, "FileImport");
        // Audit error
        auditError(subjectLine, makeImportMailMessage(importStatus));
    }

    /**
     * Load the lock box file content, and insert the MainframeImport records into database.
     *
     * @param inputFile The lock box file to load
     * @param importStatus The import status
     * @return The MainframeImport records inserted into database
     * @throws BatchProcessingException If major error occurred.
     */
    private List<MainframeImport> loadFileContent(File inputFile, ImportStatus importStatus)
        throws BatchProcessingException {

        importStatus.setInputName(inputFile.getName());

        Date fileArrivalDate = new Date(inputFile.lastModified());

        SimpleDateFormat df = new SimpleDateFormat("MMddyy");
        String achDate = df.format(fileArrivalDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fileArrivalDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        String checkDate = df.format(calendar.getTime());

        String uniqueFileName = "SC" + checkDate + "_" + todayAuditBatch.getId();
        Date importDate = new Date();

        boolean goodDataInFile = false;
        BufferedReader reader = null;
        String line;
        List<MainframeImport> mainFrames = new ArrayList<MainframeImport>();

        // Read input file, insert text line into MainframeImport
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\0", "\040"); // octal 040 = 32, the space character

                if (line.length() < 2) {
                    logger.warn("Weird character is: [" + line + "]");
                } else {
                    importStatus.setNumberLinesInFile(importStatus.getNumberLinesInFile() + 1);
                    boolean achFlag = false;
                    if (line.startsWith("R6")) {
                        achFlag = line.length() > 37 && line.charAt(37) == '1';
                        achFlag = achFlag || (line.length() >= 36 && achDate.equals(line.substring(30, 36)));
                    }

                    try {
                        startTransaction();

                        StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery(
                                "BatchMainframeImportInsert");
                        sp.setParameter("pRecordString", line);
                        sp.setParameter("pImportDate", importDate);
                        sp.setParameter("pACHFlag", achFlag);
                        sp.setParameter("pFileName", uniqueFileName);
                        sp.setParameter("pAuditBatchId", todayAuditBatch.getId());

                        sp.execute();

                        @SuppressWarnings("unchecked")
                        List<MainframeImport> mainframeImports = sp.getResultList();
                        if (mainframeImports != null && !mainframeImports.isEmpty()) {
                            mainFrames.add(mainframeImports.get(0));
                            goodDataInFile = true;
                        }

                        commitTransaction();
                    } catch (PersistenceException pe) {
                        // Here need raise exception
                        throw new BatchProcessingException("Text Line import error: " + line, pe);
                    }
                }
            }

        } catch (IOException e) {
            // Here need raise exception
            throw new BatchProcessingException("IO Error reading line from file: " + inputFile, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // Log a warn message and ignore
                    logger.warn("Error closing input stream of " + inputFile, e);
                }
            }
        }

        // Import successful, backup the input file
        String newFileNamePrefix = goodDataInFile ? "Backup_" : "Dupe_";
        int fileCounter = 1;
        File backFile = new File(inputDirectoryPath, newFileNamePrefix + uniqueFileName + ".txt");
        while (backFile.exists()) {
            backFile = new File(inputDirectoryPath, newFileNamePrefix + uniqueFileName + "_" + fileCounter + ".txt");
        }
        inputFile.renameTo(backFile);
        importStatus.setOutputName(backFile.getName());

        logger.info(importStatus.getNumberLinesInFile() + " lines loaded from "
            + importStatus.getInputName() + " which is backup to " + importStatus.getOutputName());

        return mainFrames;
    }

    /**
     * Process MainframeImport records just inserted.
     *
     * @param mainFrameImports The MainframeImport records to be processed.
     * @param importStatus The import status.
     * @throws BatchProcessingException If major error occurred.
     */
    private void processMainframeData(List<MainframeImport> mainFrameImports, ImportStatus importStatus)
        throws BatchProcessingException {

        String lastRecordText = "Breidfjord: Nothing will match this initial string.";

        // Sort by record text
        Collections.sort(mainFrameImports, MAIN_FRAME_COMPARATOR);

        for (MainframeImport mainFrame : mainFrameImports) {
            String recordString = mainFrame.getRecordString();

            try {
                if (lastRecordText.equals(recordString)) {
                    // Duplicate
                    importStatus.setNumberDuplicateRecords(importStatus.getNumberDuplicateRecords() + 1);
                    deleteEntity(mainFrame);
                } else {
                    if (recordString.startsWith("FileName") || recordString.length() < 2) {
                        importStatus.setNumberEOFMarkers(importStatus.getNumberEOFMarkers() + 1);
                        importStatus.setNumberDiscreteRecords(importStatus.getNumberDiscreteRecords() + 1);
                        deleteEntity(mainFrame);
                    } else {
                        MainframeRecordType recordType = null;

                        startTransaction();

                        if (recordString.startsWith("R ZZZ")) {
                            // Summary record
                            recordType = extractSummaryRecord(mainFrame, importStatus);
                        } else {
                            // R or C record
                            recordType = processImportRecord(mainFrame, importStatus);
                        }

                        if (recordType == MainframeRecordType.DUPLICATE_RECORD) {
                            // Duplicate record remote it
                            entityManager.remove(entityManager.find(MainframeImport.class, mainFrame.getId()));
                        } else {
                            // Set error flag
                            if (recordType == MainframeRecordType.SUM_R_VALID
                                    || recordType == MainframeRecordType.VALID_C_TRANSACTION
                                    || recordType == MainframeRecordType.VALID_R_TRANSACTION) {
                                mainFrame.setErrorFlag(false);
                            } else {
                                mainFrame.setErrorFlag(true);
                            }

                            // Clear the processing flag
                            mainFrame.setProcessingFlag(false);
                            entityManager.merge(mainFrame);
                        }

                        commitTransaction();

                        // Fill import status for logging
                        fillImportStatus(importStatus, recordType);

                        if (recordType == MainframeRecordType.SUM_R_VALID
                                || recordType == MainframeRecordType.VALID_C_TRANSACTION
                                || recordType == MainframeRecordType.VALID_R_TRANSACTION) {
                            logger.info(recordType + ": \t" + recordString);
                        }
                    }
                }
            } catch (PersistenceException pe) {
                throw new BatchProcessingException(
                    "Database error while importing records from the MainframeImport table for this record: "
                    + recordString, pe);
            }
        }

        // Update AuditBatch
        todayAuditBatch.setAmountImported(nullToZero(todayAuditBatch.getAmountImported()).add(
                importStatus.getTransactionsTotal()));
        todayAuditBatch.setNumberChangeRequests(nullToZero(todayAuditBatch.getNumberChangeRequests())
                + importStatus.getNumberGoodChangeRecords() + importStatus.getNumberBadChangeRecords()
                + importStatus.getNumberNoMatchChangeRecords());
        todayAuditBatch.setBatchTime(new Date());
        int errorCount = importStatus.getNumberBadDataRecords() + importStatus.getNumberDuplicateRecords()
                + importStatus.getNumberBadSummaryRecords() + importStatus.getNumberBadChangeRecords()
                + importStatus.getNumberBadCheckPayments() + importStatus.getNumberBadAchPayments();
        if (errorCount > 0) {
            todayAuditBatch.setErrorImporting(true);
            todayAuditBatch.setErrorCountImporting(nullToZero(todayAuditBatch.getErrorCountImporting()) + errorCount);
        }
        try {
            todayAuditBatch = mergeEntity(todayAuditBatch);
        } catch (PersistenceException pe) {
            throw new BatchProcessingException(
                "Database error while updating audit batch log in processMainframeData" , pe);
        }
    }

    /**
     * Extract summary record.
     * <p/>
     * This method does not throw any exception.
     *
     * @param mainFrame The MainframeImport record.
     * @param importStatus The import status.
     * @return The record type result.
     */
    private MainframeRecordType extractSummaryRecord(MainframeImport mainFrame, ImportStatus importStatus) {
        MainframeRecordType returnType = MainframeRecordType.SUM_R_CORRUPT;
        String recordString = mainFrame.getRecordString();
        if (recordString.length() > 17) {
            String sumString = recordString.substring(17).trim();
            try {
                importStatus.setFileSummaryTotal(
                    importStatus.getFileSummaryTotal().add(
                            new BigDecimal(sumString).divide(BatchProcessHelper.HUNDRED)));
                returnType = MainframeRecordType.SUM_R_VALID;
            } catch (NumberFormatException exp) {
                logger.error("Error extracting the R Transaction check sum from the bank input file."
                    + " The transaction record did not have a number in the the expected position: "
                    + recordString, exp);
            }
        } else {
            logger.error("Error extracting the R Transaction check sum from the bank input file."
                + " The transaction record did not have a number in the the expected position: "
                + recordString);
        }

        return returnType;
    }

    /**
     * Parse the change text of C line.
     *
     * @param forAddress Should parse for address or name?
     * @param changeText The change text of C line.
     * @return parsed string array with length 4.
     */
    private static String[] captureChanges(boolean forAddress, String changeText) {
        String[] result = {"", "", "", ""};

        Matcher matcher = (forAddress ? ADDRESS_PATTERN : NAME_PATTERN).matcher(changeText);

        if (matcher.matches()) {
            int groupCount = matcher.groupCount();
            for (int i = 0; i < result.length; i++) {
                if (i + 1 <= groupCount) {
                    String captured = matcher.group(i + 1);
                    result[i] = captured == null ? result[i] : captured;
                }
            }
        }

        return result;
    }

    /**
     * Process import record: R or C line.
     *
     * @param mainFrame The MainframeImport record.
     * @param importStatus The import status.
     * @return The record type result.
     * @throws BatchProcessingException If major error occurred.
     */
    private MainframeRecordType processImportRecord(MainframeImport mainFrame, ImportStatus importStatus)
        throws BatchProcessingException {

        String recordString = mainFrame.getRecordString();

        // Parse the record text
        rFile.parseFileLine(recordString);
        rFile.setAchPaymentFlag(rFile.getPaymentType() == LockboxPaymentType.ACH
                || mainFrame.getAchFlag() == Boolean.TRUE);

        String errorText = null;
        MainframeRecordType returnType = MainframeRecordType.NOT_A_RECORD;

        if (rFile.getValidFileRecord() == Boolean.TRUE) {

            if (rFile.getTransactionCode().equals("R")) {

                importStatus.setTransactionsTotal(importStatus.getTransactionsTotal().add(rFile.getAmount()));

                try {
                    StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("BatchInputBankPayments");
                    sp.setParameter("pPayTransBatchNumber", rFile.getCdNumber());
                    sp.setParameter("pPayTransBlockNumber", rFile.getBlockNumber());
                    sp.setParameter("pPayTransSequenceNumber", rFile.getSequenceNumber());
                    sp.setParameter("pSCMClaimNumber", rFile.getClaimNumber());
                    sp.setParameter("pSCMDateOfBirth", rFile.getDateOfBirth(), TemporalType.DATE);
                    sp.setParameter("pPayTransPaymentAmount", rFile.getAmount());
                    sp.setParameter("pPayTransTransactionDate", rFile.getCdDate(), TemporalType.DATE);
                    sp.setParameter("pACHPaymentFlag", rFile.getAchPaymentFlag());
                    sp.setParameter("pNetworkId", batchProcessUser.getNetworkId());

                    sp.execute();

                    String returnCode = (String) sp.getOutputParameterValue("pReturn");
                    Long paymentTransactionKey = (Long) sp.getOutputParameterValue("pPayTransactionKey");

                    if ("0".equals(returnCode)) {
                        mainFrame.setPaymentTransactionId(paymentTransactionKey);
                        returnType = MainframeRecordType.VALID_R_TRANSACTION;
                    } else if ("17".equals(returnCode)) {
                        returnType = MainframeRecordType.DUPLICATE_RECORD;
                        errorText = "Duplicate payment record based on year, batch, block, sequence, amount and date: "
                            + recordString;
                    } else {
                        returnType = MainframeRecordType.BAD_R_TRANSACTION;
                        errorText = "Unknown R Transaction error (" + returnCode + "): " + recordString;
                    }
                } catch (PersistenceException pe) {
                    // Fill import status for logging
                    fillImportStatus(importStatus, MainframeRecordType.BAD_R_TRANSACTION);
                    errorText = "Database Error while inserting payment transaction record: " + recordString;
                    throw new BatchProcessingException(errorText, pe);
                }
            } else {
                try {

                    StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("BatchPerformBankChanges");

                    sp.setParameter("pSCMClaimnumber", rFile.getClaimNumber());
                    sp.setParameter("pSCMDateOfBirth", rFile.getDateOfBirth(), TemporalType.DATE);
                    sp.setParameter("pFieldNumber", rFile.getIndexOfAccountFieldToCorrect());
                    sp.setParameter("pDataElement", rFile.getCorrectedData());

                    String[] address = captureChanges(true, rFile.getCorrectedData());
                    String[] name = captureChanges(false, rFile.getCorrectedData());

                    sp.setParameter("pSCMCity", address[0]);
                    sp.setParameter("pSCMState", address[1]);
                    sp.setParameter("pSCMZipcode", address[2]);
                    sp.setParameter("pSCMFName", name[0]);
                    sp.setParameter("pSCMMInitial", name[1]);
                    sp.setParameter("pSCMLastname", name[2]);
                    sp.setParameter("pSCMSuffix", name[3]);

                    sp.execute();

                    Integer updatedCount = (Integer) sp.getOutputParameterValue("pUpdateCount");

                    String returnCode = (String) sp.getOutputParameterValue("pErrorCode");

                    if ("0".equals(returnCode)) {
                        if (updatedCount <= 0) {
                            returnType = MainframeRecordType.NOT_MATCH_C_TRANSACTION;
                            logger.warn("Change command did not update database for CSD #"
                                + rFile.getClaimNumber() + " Birthdate " + rFile.getDateOfBirth()
                                + ": " + recordString);
                        } else {
                            returnType = MainframeRecordType.VALID_C_TRANSACTION;
                        }
                    } else if ("-97".equals(returnCode)) {
                        returnType = MainframeRecordType.BAD_C_TRANSACTION;
                        errorText = "Invalid field number. The program does not accept changes to field # "
                            + rFile.getIndexOfAccountFieldToCorrect() + ": " + recordString;
                    } else {
                        returnType = MainframeRecordType.BAD_C_TRANSACTION;
                        errorText = "Unknown C Transaction error (" + returnCode + "): " + recordString;
                    }
                } catch (PersistenceException pe) {
                    // Fill import status for logging
                    fillImportStatus(importStatus, MainframeRecordType.BAD_C_TRANSACTION);
                    errorText = "Database Error while changing the applicant record: " + recordString;
                    throw new BatchProcessingException(errorText, pe);
                }
            }
        } else {
            if ("C".equals(rFile.getTransactionCode())) {
                returnType = MainframeRecordType.BAD_C_TRANSACTION;
            } else if ("R".equals(rFile.getTransactionCode())) {
                returnType = MainframeRecordType.BAD_R_TRANSACTION;
            } else {
                returnType = MainframeRecordType.NOT_A_RECORD;
            }
            errorText = "Text Line is not a valid Transaction Format ["
                + rFile.getRecordImportError() + "]: " + recordString;
        }

        if (errorText != null) {
            logger.error("Error importing the record from the MainframeImport table in"
                    + " processImportRecord module: " + returnType + ". " + errorText);
        }

        return returnType;
    }

    /**
     * Fill the ImportStatus with the main frame import result type.
     *
     * @param importStatus The import status.
     * @param recordType The MainframeImport record type result.
     */
    private void fillImportStatus(ImportStatus importStatus, MainframeRecordType recordType) {

        if (recordType != MainframeRecordType.DUPLICATE_RECORD) {
            importStatus.setNumberDiscreteRecords(importStatus.getNumberDiscreteRecords() + 1);
        }

        switch (recordType) {
        case SUM_R_VALID:
            importStatus.setNumberGoodSummaryRecords(importStatus.getNumberGoodSummaryRecords() + 1);
            break;
        case SUM_R_CORRUPT:
            importStatus.setPaymentsGood(false);
            importStatus.setNumberBadSummaryRecords(importStatus.getNumberBadSummaryRecords() + 1);
            break;
        case NOT_A_RECORD:
            importStatus.setSuccessful(false);
            importStatus.setNumberBadDataRecords(importStatus.getNumberBadDataRecords() + 1);
            break;
        case VALID_C_TRANSACTION:
            importStatus.setNumberGoodChangeRecords(importStatus.getNumberGoodChangeRecords() + 1);
            break;
        case NOT_MATCH_C_TRANSACTION:
            importStatus.setChangeRecordsGood(false);
            importStatus.setNumberNoMatchChangeRecords(importStatus.getNumberNoMatchChangeRecords() + 1);
            break;
        case BAD_C_TRANSACTION:
            importStatus.setChangeRecordsGood(false);
            importStatus.setNumberBadChangeRecords(importStatus.getNumberBadChangeRecords() + 1);
            break;
        case VALID_R_TRANSACTION:
            if (rFile.getAchPaymentFlag() == Boolean.TRUE) {
                importStatus.setNumberGoodAchPayments(importStatus.getNumberGoodAchPayments() + 1);
                importStatus.setTotalACHPayments(importStatus.getTotalACHPayments().add(rFile.getAmount()));
            } else {
                importStatus.setNumberGoodCheckPayments(importStatus.getNumberGoodCheckPayments() + 1);
                importStatus.setTotalCheckPayments(importStatus.getTotalCheckPayments().add(rFile.getAmount()));
            }
            break;
        case BAD_R_TRANSACTION:
            importStatus.setPaymentsGood(false);
            if (rFile.getAchPaymentFlag() == Boolean.TRUE) {
                importStatus.setNumberBadAchPayments(importStatus.getNumberBadAchPayments() + 1);
            } else {
                importStatus.setNumberBadCheckPayments(importStatus.getNumberBadCheckPayments() + 1);
            }
            if (rFile.getAmount() != null) {
                importStatus.setTotalSkippedPayments(importStatus.getTotalSkippedPayments().add(rFile.getAmount()));
            }
            break;
        case DUPLICATE_RECORD:
            importStatus.setNumberDuplicateRecords(importStatus.getNumberDuplicateRecords() + 1);
            if (rFile.getAchPaymentFlag() == Boolean.TRUE) {
                importStatus.setNumberDupeAchPayments(importStatus.getNumberDupeAchPayments() + 1);
            } else {
                importStatus.setNumberDupeCheckPayments(importStatus.getNumberDupeCheckPayments() + 1);
            }
            if (rFile.getAmount() != null) {
                importStatus.setTotalDupePayments(importStatus.getTotalDupePayments().add(rFile.getAmount()));
            }
            break;
        default:
            break;
        }
    }

    /**
     * Get log text of import status.
     *
     * @param importStatus The import status.
     * @return The log text of import status.
     */
    private String logImportStatus(ImportStatus importStatus) {
        Date now = new Date();
        StringBuilder sb = new StringBuilder(CRLF);
        sb.append("Service Credit imported a data file from the bank lockbox on ");
        sb.append(DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(now));
        sb.append(" at ");
        sb.append(DateFormat.getTimeInstance(DateFormat.LONG, Locale.US).format(now)).append(".").append(CRLF);
        sb.append("Lockbox data imported into the ").append(databaseString);
        sb.append(" database on server ").append(datasourceString);
        sb.append(" from computer ").append(workstationId).append(".");

        sb.append(CRLF).append(CRLF);

        sb.append("# Lines in file = ")
                .append(importStatus.getNumberDuplicateRecords() + importStatus.getNumberDiscreteRecords())
                .append(CRLF);

        if (importStatus.getNumberDiscreteRecords() > 0) {
            sb.append("# New Lines = ").append(importStatus.getNumberDiscreteRecords()).append(CRLF);
        }

        sb.append("# Duplicate Lines = ").append(importStatus.getNumberDuplicateRecords()).append(CRLF);
        sb.append(CRLF);

        int failedInserts = importStatus.getNumberBadChangeRecords() + importStatus.getNumberNoMatchChangeRecords()
                + importStatus.getNumberBadAchPayments() + importStatus.getNumberBadCheckPayments();

        int successfulInserts = importStatus.getNumberGoodChangeRecords() + importStatus.getNumberGoodAchPayments()
                + importStatus.getNumberGoodCheckPayments();

        sb.append("# Records Processed = ")
                .append(importStatus.getNumberBadDataRecords() + importStatus.getNumberGoodSummaryRecords()
                        + importStatus.getNumberBadSummaryRecords()
                        + importStatus.getNumberDupeAchPayments() + importStatus.getNumberDupeCheckPayments()
                        + failedInserts + successfulInserts).append(CRLF);

        sb.append("# Check Payments = ")
                .append(importStatus.getNumberGoodCheckPayments() + importStatus.getNumberBadCheckPayments()
                        + importStatus.getNumberDupeCheckPayments()).append(CRLF);
        sb.append("# Good Check Payments = ").append(importStatus.getNumberGoodCheckPayments()).append(CRLF);
        sb.append("# Bad Check Payments = ").append(importStatus.getNumberBadCheckPayments()).append(CRLF);
        sb.append("# Dupe Check Payments = ").append(importStatus.getNumberDupeCheckPayments()).append(CRLF);

        sb.append("# ACH Payments = ")
                .append(importStatus.getNumberGoodAchPayments() + importStatus.getNumberBadAchPayments()
                        + importStatus.getNumberDupeAchPayments()).append(CRLF);
        sb.append("# Good ACH Payments = ").append(importStatus.getNumberGoodAchPayments()).append(CRLF);
        sb.append("# Bad ACH Payments = ").append(importStatus.getNumberBadAchPayments()).append(CRLF);
        sb.append("# Dupe ACH Payments = ").append(importStatus.getNumberDupeAchPayments()).append(CRLF);

        sb.append("# Bad Change Records = ").append(importStatus.getNumberBadChangeRecords()).append(CRLF);
        sb.append("# Good Change Records = ").append(importStatus.getNumberGoodChangeRecords()).append(CRLF);
        sb.append("# No Match Change Records = ").append(importStatus.getNumberNoMatchChangeRecords()).append(CRLF);

        sb.append("# Invalid Summary Records = ").append(importStatus.getNumberBadSummaryRecords()).append(CRLF);
        sb.append("# Valid Summary Records = ").append(importStatus.getNumberGoodSummaryRecords()).append(CRLF);

        sb.append("# Successful Transactions = ").append(successfulInserts).append(CRLF);
        sb.append("# Failed Transactions = ").append(failedInserts).append(CRLF);
        sb.append("# Invalid Lines = ").append(importStatus.getNumberBadDataRecords()).append(CRLF);

        sb.append(CRLF);
        sb.append("Total Check Payments = ").append(importStatus.getTotalCheckPayments()).append(CRLF);
        sb.append("Total ACH Payments = ").append(importStatus.getTotalACHPayments()).append(CRLF);
        sb.append("Total of Accepted Payments = ")
                .append(importStatus.getTotalCheckPayments().add(importStatus.getTotalACHPayments())).append(CRLF);

        if (Double.compare(importStatus.getTransactionsTotal().doubleValue(),
                importStatus.getFileSummaryTotal().doubleValue()) == 0) {
            sb.append("Total in File = ").append(importStatus.getFileSummaryTotal()).append(CRLF);
        } else {
            sb.append("Total in File Summary (checksum) = ").append(importStatus.getFileSummaryTotal()).append(CRLF);
            sb.append("Total of Processed Transactions = ").append(importStatus.getTransactionsTotal()).append(CRLF);

            sb.append(CRLF).append(CRLF);

            sb.append("ERROR: THE BANK FILE CONTAINED ").append(importStatus.getFileSummaryTotal()).append(" BUT ")
                    .append(importStatus.getTransactionsTotal())
                    .append(" WAS IMPORTED INTO THE SERVICE CREDIT DATABASE. ");

            if (importStatus.getNumberGoodSummaryRecords() > 0) {
                if (Double.compare(importStatus.getTransactionsTotal().doubleValue(),
                        importStatus.getFileSummaryTotal().doubleValue()) > 0) {
                    sb.append("THE PROGRAM IMPORTED ")
                            .append(importStatus.getTransactionsTotal().subtract(importStatus.getFileSummaryTotal()))
                            .append(" MORE THAN THE BANK TOTAL.").append(CRLF);
                } else if (Double.compare(importStatus.getTransactionsTotal().doubleValue(),
                        importStatus.getFileSummaryTotal().doubleValue()) < 0) {
                    sb.append("THE PROGRAM IMPORTED ")
                            .append(importStatus.getFileSummaryTotal().subtract(importStatus.getTransactionsTotal()))
                            .append(" LESS THAN THE BANK TOTAL.").append(CRLF);
                }
            }
        }

        sb.append(CRLF);

        int failedCount = importStatus.getNumberBadDataRecords() + importStatus.getNumberBadSummaryRecords()
                + failedInserts;

        if (failedCount > 0) {
            sb.append("ERROR: ").append(failedCount).append(" RECORDS FAILED PROCESSING.").append(CRLF);
        } else {
            sb.append("No bad records in this batch.").append(CRLF);
        }

        if (failedInserts > 0) {
            sb.append("ERROR: ").append(failedInserts).append(" TRANSACTIONS COULD NOT BE READ INTO THE DATABASE!")
                    .append(CRLF);
        }

        if (importStatus.getNumberBadSummaryRecords() > 0) {
            sb.append("ERROR: ").append(importStatus.getNumberBadSummaryRecords())
                    .append(" CHECKSUMS COULD NOT BE PROCESSED!").append(CRLF);
        }

        if (importStatus.getNumberDuplicateRecords() > 0) {
            sb.append(importStatus.getNumberDuplicateRecords()).append(" DUPLICATE RECORDS FROM THE BANK").append(CRLF);
        }

        sb.append(successfulInserts).append(" = number of successful calls to the ProcessImportRecord function.")
                .append(CRLF);

        if (failedInserts > 0) {
            sb.append(failedInserts).append(" NUMBER OF FAILED CALLS TO THE PROCESSIMPORTRECORD FUNCTION!")
            .append(CRLF);
        }

        sb.append(CRLF);

        // Log pending payment transactions collated
        sb.append("         Accepted Payments: ").append(
                importStatus.getNumberAcceptedCheckPayments() + importStatus.getNumberAcceptedAchPayments());
        sb.append(CRLF);
        sb.append("       Unresolved Payments: ").append(
                importStatus.getNumberUnresolvedCheckPayments() + importStatus.getNumberUnresolvedAchPayments());
        sb.append(CRLF);
        sb.append("        Suspended Payments: ").append(
                importStatus.getNumberSuspendedCheckPayments() + importStatus.getNumberSuspendedAchPayments());
        sb.append(CRLF);
        sb.append("-----------------------------------");
        sb.append(CRLF);
        sb.append("Pending payments processed: ").append(importStatus.getNumberAcceptedCheckPayments()
                + importStatus.getNumberAcceptedAchPayments() + importStatus.getNumberUnresolvedCheckPayments()
                + importStatus.getNumberUnresolvedAchPayments() + importStatus.getNumberSuspendedCheckPayments()
                + importStatus.getNumberSuspendedAchPayments());
        sb.append(CRLF);

        return sb.toString();
    }

    /**
     * Collates new main frame payments.
     *
     * @param importStatus The import status.
     * @throws BatchProcessingException If major error occurred.
     */
    private void collateNewMainframePayments(ImportStatus importStatus) throws BatchProcessingException {

        try {
            startTransaction();

            StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("BatchCollateNewPayments");

            sp.execute();

            Integer acceptedCount = (Integer) sp.getOutputParameterValue("pAcceptedCount");
            Integer unresolvedCount = (Integer) sp.getOutputParameterValue("pUnresolvedCount");
            Integer suspendedCount = (Integer) sp.getOutputParameterValue("pSuspendedCount");
            Integer acceptedACHCount = (Integer) sp.getOutputParameterValue("pAcceptedACHCount");
            Integer unresolvedACHCount = (Integer) sp.getOutputParameterValue("pUnresolvedACHCount");
            Integer suspendedACHCount = (Integer) sp.getOutputParameterValue("pSuspendedACHCount");

            commitTransaction();

            importStatus.setNumberAcceptedCheckPayments(acceptedCount);
            importStatus.setNumberUnresolvedCheckPayments(unresolvedCount);
            importStatus.setNumberSuspendedCheckPayments(suspendedCount);
            importStatus.setNumberAcceptedAchPayments(acceptedACHCount);
            importStatus.setNumberUnresolvedAchPayments(unresolvedACHCount);
            importStatus.setNumberSuspendedAchPayments(suspendedACHCount);
        } catch (PersistenceException pe) {
            throw new BatchProcessingException("Database Error: CollateNewMainframePayments", pe);
        }


        // Update audit batch log
        todayAuditBatch.setNumberAccepted(
                nullToZero(todayAuditBatch.getNumberAccepted()) + importStatus.getNumberAcceptedCheckPayments());
        todayAuditBatch.setNumberSuspended(
                nullToZero(todayAuditBatch.getNumberSuspended()) + importStatus.getNumberSuspendedCheckPayments());
        todayAuditBatch.setNumberUnresolved(
                nullToZero(todayAuditBatch.getNumberUnresolved()) + importStatus.getNumberUnresolvedCheckPayments());
        todayAuditBatch.setNumberAchAccepted(
                nullToZero(todayAuditBatch.getNumberAchAccepted()) + importStatus.getNumberAcceptedAchPayments());
        todayAuditBatch.setNumberAchSuspended(
                nullToZero(todayAuditBatch.getNumberAchSuspended()) + importStatus.getNumberSuspendedAchPayments());
        todayAuditBatch.setNumberAchUnresolved(
                nullToZero(todayAuditBatch.getNumberAchUnresolved()) + importStatus.getNumberUnresolvedAchPayments());

        try {
            todayAuditBatch = mergeEntity(todayAuditBatch);
        } catch (PersistenceException pe) {
            throw new BatchProcessingException(
                "Database error while updating audit batch log in collateNewMainframePayments" , pe);
        }
    }

    /**
     * Generates the mail message based on the file import status.
     *
     * @param importStatus The import status to generate file import mail message.
     * @return The file import mail message for the status.
    */
    private String makeImportMailMessage(ImportStatus importStatus) {
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("importStatus", importStatus);
        model.put("dateTool", new DateTool());
        model.put("mathTool", new MathTool());
        model.put("numberTool", new NumberTool());
        model.put("StringUtils", StringUtils.class);

        try {
            return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, fileImportMailTemplate, "utf-8", model);
        } catch (VelocityException ve) {
            logger.error("Error while making file import mail message", ve);
            // Use the log text instead...
            return logImportStatus(importStatus);
        }
    }

    /**
     * Cleans the directory containing old files.
     * <p/>
     * This method does not throw any exception.
     *
     * @param toCleanDirectory The directory containing old files to clean.
     */
    private void cleanOutOldFiles(File toCleanDirectory) {
        if (!toCleanDirectory.exists() || !toCleanDirectory.isDirectory()
                || !toCleanDirectory.canRead() || !toCleanDirectory.canWrite()) {
            logger.warn("Can not clean directory:" + toCleanDirectory);
            return;
        }

        int numberDeletedFiles = 0;

        final String regex = wildCardToClean.replace("?", ".?").replace("*", ".*?");

        final Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());

        try {

            File[] candidateFiles = toCleanDirectory.listFiles(new FileFilter() {
                @Override
                public boolean accept(File inputFile) {
                    Calendar lastModifiedCalendar = Calendar.getInstance();
                    lastModifiedCalendar.setTime(new Date(inputFile.lastModified()));
                    lastModifiedCalendar.add(Calendar.DAY_OF_MONTH, ageToCleanInDays);

                    return lastModifiedCalendar.compareTo(currentCalendar) < 0
                            && inputFile.getName().matches(regex) && inputFile.isFile() && inputFile.canWrite();
                }
            });

            for (File candidateFile : candidateFiles) {
                candidateFile.delete();
                numberDeletedFiles++;
            }

            logger.info("Old files cleaned: " + numberDeletedFiles);
        } catch (Exception e) {
            // Ignore any error, just log it
            logger.error("Error while cleaning old files", e);
        }
    }

    /**
     * Batch daily account update.
     * <p/>
     * This method does not throw any exception.
     *
     * @param procMessage The process message. Used to build the mail message.
     * @return true if execution is successful; false otherwise.
     */
    private boolean batchDailyAccountUpdate(StringBuilder procMessage) {
        try {
            startTransaction();

            StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("BatchDailyAccountUpdate");
            sp.setParameter("pAuditBatchIDLog", todayAuditBatch.getId());

            Integer count = (Integer) sp.getSingleResult();

            commitTransaction();

            String msg = "Updated " + count + " master records to history or ACH Stop status.";
            logger.info(msg);
            procMessage.append(CRLF).append(CRLF).append(msg);

            return true;
        } catch (PersistenceException pe) {
            logger.error("ERROR: Updating master records to history or ACH Stop status failed.", pe);
            procMessage.append(CRLF).append(CRLF)
                .append("ERROR: Updating master records to history or ACH Stop status failed. ");
            return false;
        }
    }

    /**
     * Creates the General Ledger file given the database data.
     * <p/>
     * This method does not throw any exception.
     *
     * @param glFileDirectory The directory to create GL file.
     * @param procMessage The process message. Used to build the mail message.
     * @param now The current date.
     * @return true if execution is successful; false otherwise.
     */
    private boolean makeGLFile(File glFileDirectory, StringBuilder procMessage, Date now) {
        if (!glFileDirectory.exists() || !glFileDirectory.isDirectory()
                || !glFileDirectory.canRead() || !glFileDirectory.canWrite()) {
            logger.warn("Can not make GL file in directory:" + glFileDirectory);
            procMessage.append(CRLF).append(CRLF)
                .append("Can not make GL file in directory:" + glFileDirectory).append(CRLF);
            return false;
        }

        File outputGLFile = new File(glFileDirectory, "SCGL"
            + new SimpleDateFormat("yyMMdd").format(now) + ".txt");

        PrintWriter output = null;

        try {
            startTransaction();

            StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("BatchDailyGLFile");
            sp.setParameter("pDayToProcess", now, TemporalType.DATE);
            sp.execute();

            @SuppressWarnings("unchecked")
            List<GLFileRecord> records = sp.getResultList();

            commitTransaction();

            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            String dayOfYear = String.format("%03d", cal.get(Calendar.DAY_OF_YEAR));

            for (GLFileRecord record : records) {
                StringBuilder line = new StringBuilder("");
                line.append(record.getFeederSystemId());
                line.append(record.getJulianDate());
                line.append(dayOfYear);
                line.append(record.getGlFiller());
                line.append(record.getGlCode());

                int fiscalYear = record.getFiscalYear() == null ? 0 : record.getFiscalYear();
                if (fiscalYear < 1000) {
                    line.append(StringUtils.rightPad(record.getGlAccountingCode(), 20));
                } else {
                    line.append(fiscalYear % 100);
                    line.append("  ");
                    line.append(StringUtils.rightPad(record.getGlAccountingCode(), 16));
                }

                line.append(String.format("%015d",
                        record.getRecipientAmount().multiply(BatchProcessHelper.HUNDRED).longValue()));

                line.append(record.getRevenueSourceCode());

                // Pad 28 spaces
                for (int i = 0; i < 28; i++) {
                    line.append(" ");
                }

                if (output == null) {
                    // Lazily create output file only when there is line to write
                    output = new PrintWriter(outputGLFile);
                }
                output.println(line.toString());
            }

            if (output != null) {
                output.flush();
                logger.info("General Ledger file created.");
                procMessage.append(CRLF).append(CRLF).append("General Ledger file created.").append(CRLF);
            } else {
                String info = "There are no GL entries for "
                        + DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(now)
                        + " so no GL file was created. ";
                logger.info(info);
                procMessage.append(CRLF).append(CRLF).append(info).append(CRLF);
            }

            return true;
        } catch (PersistenceException pe) {
            logger.error("Database error creating the GL file.", pe);
            procMessage.append(CRLF).append(CRLF).append("Database error creating the GL file.").append(CRLF);
            return false;
        } catch (IOException e) {
            logger.error("IO error creating the GL file.", e);
            procMessage.append(CRLF).append(CRLF).append("IO error creating the GL file.").append(CRLF);
            return false;
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /**
     * Sends the notification by email.
     * <p/>
     * This method does not throw any exception.
     *
     * @param mailMessage The message of the mail.
     * @param mailSubject The subject of the email.
     * @param processType The name of the current processor. It is used to distinguish the recipient of the email.
     */
    private void notifyByEmail(String mailMessage, String mailSubject, String processType) {
        mailMessage = mailMessage.replace("@#$%EndingTime%$#@",
                DateFormat.getTimeInstance(DateFormat.LONG, Locale.US).format(new Date()));

        String recipient = processType.equals("BillProcessing") ? billMailRecipient : generalMailRecipient;

        logger.info("Send email to " + recipient + ", subject = [" + mailSubject + "]. Message:"
            + CRLF + mailMessage);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(recipient);

            helper.setSubject(mailSubject);

            helper.setText(mailMessage);

            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error sending email to " + recipient + ", subject = [" + mailSubject + "].", e);
        } catch (MailSendException e) {
            logger.error("Error sending email to " + recipient + ", subject = [" + mailSubject + "].", e);
        }
    }

    /**
     * Start transaction.
     * @throws PersistenceException If database error occurred.
     */
    private void startTransaction() {

        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

    /**
     * Commit transaction.
     * @throws PersistenceException If database error occurred.
     */
    private void commitTransaction() {

        try {
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
    }

    /**
     * Persist entity.
     *
     * @param <T> The generic entity type.
     * @param entity to persist.
     * @return The entity persisted.
     * @throws PersistenceException If database error occurred.
     */
    private <T extends IdentifiableEntity> T persistEntity(T entity) {
        startTransaction();

        entityManager.persist(entity);

        commitTransaction();

        return entity;
    }

    /**
     * Merge entity.
     *
     * @param <T> The generic entity type.
     * @param entity to merge.
     * @return The entity merged.
     * @throws PersistenceException If database error occurred.
     */
    private <T extends IdentifiableEntity> T mergeEntity(T entity) {

        startTransaction();

        T merged = entityManager.merge(entity);

        commitTransaction();

        return merged;
    }

    /**
     * Delete entity.
     *
     * @param <T> The generic entity type.
     * @param entity to delete.
     * @throws PersistenceException If database error occurred.
     */
    private <T extends IdentifiableEntity> void deleteEntity(T entity) {

        startTransaction();

        entityManager.remove(entityManager.find(entity.getClass(), entity.getId()));

        commitTransaction();
    }

    /**
     * Setter method for property <tt>entityManager</tt>.
     * @param entityManager value to be assigned to property entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Setter method for property <tt>logger</tt>.
     * @param logger value to be assigned to property logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Setter method for property <tt>securityService</tt>.
     * @param securityService value to be assigned to property securityService
     */
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * Setter method for property <tt>billProcessor</tt>.
     * @param billProcessor value to be assigned to property billProcessor
     */
    public void setBillProcessor(BillProcessor billProcessor) {
        this.billProcessor = billProcessor;
    }

    /**
     * Setter method for property <tt>mailSender</tt>.
     * @param mailSender value to be assigned to property mailSender
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Setter method for property <tt>generalMailRecipient</tt>.
     * @param generalMailRecipient value to be assigned to property generalMailRecipient
     */
    public void setGeneralMailRecipient(String generalMailRecipient) {
        this.generalMailRecipient = generalMailRecipient;
    }

    /**
     * Setter method for property <tt>billMailRecipient</tt>.
     * @param billMailRecipient value to be assigned to property billMailRecipient
     */
    public void setBillMailRecipient(String billMailRecipient) {
        this.billMailRecipient = billMailRecipient;
    }

    /**
     * Setter method for property <tt>fileImportMailTemplate</tt>.
     * @param fileImportMailTemplate value to be assigned to property fileImportMailTemplate
     */
    public void setFileImportMailTemplate(String fileImportMailTemplate) {
        this.fileImportMailTemplate = fileImportMailTemplate;
    }

    /**
     * Setter method for property <tt>velocityEngine</tt>.
     * @param velocityEngine value to be assigned to property velocityEngine
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    /**
     * Setter method for property <tt>rFile</tt>.
     * @param rFile value to be assigned to property rFile
     */
    public void setrFile(RFile rFile) {
        this.rFile = rFile;
    }

    /**
     * Setter method for property <tt>retryInterval</tt>.
     * @param retryInterval value to be assigned to property retryInterval
     */
    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    /**
     * Setter method for property <tt>retriesNumber</tt>.
     * @param retriesNumber value to be assigned to property retriesNumber
     */
    public void setRetriesNumber(Integer retriesNumber) {
        this.retriesNumber = retriesNumber;
    }

    /**
     * Setter method for property <tt>currentUserName</tt>.
     * @param currentUserName value to be assigned to property currentUserName
     */
    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    /**
     * Setter method for property <tt>inputDirectoryPath</tt>.
     * @param inputDirectoryPath value to be assigned to property inputDirectoryPath
     */
    public void setInputDirectoryPath(String inputDirectoryPath) {
        this.inputDirectoryPath = inputDirectoryPath;
    }

    /**
     * Setter method for property <tt>wildCardInput</tt>.
     * @param wildCardInput value to be assigned to property wildCardInput
     */
    public void setWildCardInput(String wildCardInput) {
        this.wildCardInput = wildCardInput;
    }

    /**
     * Setter method for property <tt>directoryToClean</tt>.
     * @param directoryToClean value to be assigned to property directoryToClean
     */
    public void setDirectoryToClean(String directoryToClean) {
        this.directoryToClean = directoryToClean;
    }

    /**
     * Setter method for property <tt>wildCardToClean</tt>.
     * @param wildCardToClean value to be assigned to property wildCardToClean
     */
    public void setWildCardToClean(String wildCardToClean) {
        this.wildCardToClean = wildCardToClean;
    }

    /**
     * Setter method for property <tt>ageToCleanInDays</tt>.
     * @param ageToCleanInDays value to be assigned to property ageToCleanInDays
     */
    public void setAgeToCleanInDays(Integer ageToCleanInDays) {
        this.ageToCleanInDays = ageToCleanInDays;
    }

    /**
     * Setter method for property <tt>glDirectory</tt>.
     * @param glDirectory value to be assigned to property glDirectory
     */
    public void setGlDirectory(String glDirectory) {
        this.glDirectory = glDirectory;
    }

    /**
     * Setter method for property <tt>databaseString</tt>.
     * @param databaseString value to be assigned to property databaseString
     */
    public void setDatabaseString(String databaseString) {
        this.databaseString = databaseString;
    }

    /**
     * Setter method for property <tt>datasourceString</tt>.
     * @param datasourceString value to be assigned to property datasourceString
     */
    public void setDatasourceString(String datasourceString) {
        this.datasourceString = datasourceString;
    }

    /**
     * Setter method for property <tt>workstationId</tt>.
     * @param workstationId value to be assigned to property workstationId
     */
    public void setWorkstationId(String workstationId) {
        this.workstationId = workstationId;
    }

    /**
     * Setter method for property <tt>batchProcessUser</tt>.
     * @param batchProcessUser value to be assigned to property batchProcessUser
     */
    public void setBatchProcessUser(User batchProcessUser) {
        this.batchProcessUser = batchProcessUser;
    }

    /**
     * Setter method for property <tt>todayAuditBatch</tt>.
     * @param todayAuditBatch value to be assigned to property todayAuditBatch
     */
    public void setTodayAuditBatch(AuditBatch todayAuditBatch) {
        this.todayAuditBatch = todayAuditBatch;
    }

    /**
     * Setter method for property <tt>batchPhase</tt>.
     * @param batchPhase value to be assigned to property batchPhase
     */
    public void setBatchPhase(BatchPhase batchPhase) {
        this.batchPhase = batchPhase;
    }

    /**
     * Audit the lock box import file error.
     * @param subject the error subject
     * @param description the error description
     */
    private void auditError(String subject, String description) {
        AuditRecord record = new AuditRecord();
        record.setUsername("Batch Process Module");
        record.setDate(new Date());
        record.setIpAddress("N/A");
        record.setActionName("Lock Box Import Error");
        List<AuditParameterRecord> list = new ArrayList<AuditParameterRecord>();
        AuditParameterRecord param = new AuditParameterRecord();
        param.setItemId(0L);
        param.setItemType("Lock Box Import");
        param.setPropertyName("subject");
        param.setPreviousValue("N/A");
        param.setNewValue(subject);
        list.add(param);
        AuditParameterRecord param2 = new AuditParameterRecord();
        param2.setItemId(0L);
        param2.setItemType("Lock Box Import");
        param2.setPropertyName("error description");
        param2.setPreviousValue("N/A");
        param2.setNewValue(description.replace("@#$%EndingTime%$#@",
                DateFormat.getTimeInstance(DateFormat.LONG, Locale.US).format(new Date())));
        list.add(param2);
        record.setParameters(list);
        persistEntity(record);
    }

    /**
     * Saves the process messages.
     * @param procMessage the process message
     */
    private void savePaymentStatusPrint(String procMessage) {
        PaymentStatementPrint psp = new PaymentStatementPrint();
        psp.setDate(new Date());
        psp.setMessage(procMessage.replace("@#$%EndingTime%$#@",
                DateFormat.getTimeInstance(DateFormat.LONG, Locale.US).format(new Date())));
        persistEntity(psp);
    }

    /**
     * Represents batch phase: FILE_IMPORT or BILL_PROCESS. Used to determine the recipient of error email.
     *
     * @author liuliquan
     * @version 1.0
     */
    private static enum BatchPhase {
        /**
         * Represents the file import phase.
         */
        FILE_IMPORT,

        /**
         * Represents the bill process phase.
         */
        BILL_PROCESS
    }

    /**
     * <p>
     * This is the comparator used to compare MainframeImport by record string.
     * </p>
     * <p>
     * <strong>Thread Safety: </strong> This class is stateless and thread safe.
     * </p>
     * @author liuliquan
     * @version 1.0
     */
    private static class MainFrameImportComparator implements Comparator<MainframeImport> {

        /**
         * Compare two entities.
         * @param o1
         *            the first object to be compared.
         * @param o2
         *            the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or
         *         greater than the second.
         */
        @Override
        public int compare(MainframeImport o1, MainframeImport o2) {
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 == null) {
                return -1;
            } else if (o2 == null) {
                return 1;
            } else {
                return o1.getRecordString().compareTo(o2.getRecordString());
            }
        }
    }

    /**
     * System time.
     *
     * @author liuliquan
     * @version 1.0
     */
    public static class SysTime {
        /**
         * The instance.
         */
        private static SysTime instance = new SysTime();

        /**
         * Getter method for property <tt>instance</tt>.
         * @return property value of instance
         */
        public static SysTime getInstance() {
            return instance;
        }

        /**
         * Setter method for property <tt>instance</tt>.
         * @param instance value to be assigned to property instance
         */
        public static void setInstance(SysTime instance) {
            SysTime.instance = instance;
        }

        /**
         * Get current time.
         *
         * @return current time.
         */
        public long now() {
            return System.currentTimeMillis();
        }
    }
}
