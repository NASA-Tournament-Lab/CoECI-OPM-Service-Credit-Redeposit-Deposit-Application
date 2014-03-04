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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.batchprocessing.jobs.BatchProcessingJob.SysTime;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AuditBatch;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.application.Holiday;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.batchprocessing.MainframeImport;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.batchprocessing.PaymentTransactionCodes;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.FormType;
import gov.opm.scrd.entities.lookup.PayCode;
import gov.opm.scrd.entities.lookup.Suffix;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.subethamail.wiser.Wiser;


/**
 * Unit tests for BatchProcessingJob.
 *
 * @author liuliquan
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:batchProcessJob_test.xml")
public class BatchProcessingJobTests extends BasePersistenceTests {

    /**
     * Represents the SMTP port used for unit test.
     */
    public static final int SMTP_PORT = 2983;

    /**
     * Represents the date format.
     */
    private static final DateFormat DF = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Represents the BatchProcessingJob instance for tests.
     * <p/>
     * Injected by Spring.
     */
    @Autowired
    private BatchProcessingJob batchProcessingJob;

    /**
     * Represents the BatchProcessingJob instance for tests.
     * <p/>
     * Injected by Spring.
     */
    @Autowired
    private BatchProcessingJob alwaysWorkDayBatchProcessingJob;

    /**
     * Represents the BatchProcessingJob instance for tests.
     * <p/>
     * Injected by Spring.
     */
    @Autowired
    private BatchProcessingJob alwaysHolidayBatchProcessingJob;

    /**
     * Represents the path to the directory where input lockbox files are stored.
     * <p/>
     * Injected by Spring.
     */
    @Autowired
    private String inputDirectoryPath;

    /**
     * Represents the directory which is expected to be cleaned after the processing is done.
     * <p/>
     * Injected by Spring.
     */
    @Autowired
    private String directoryToClean;

    /**
     * Represents the path to the directory where General Ledger files will be created.
     * <p/>
     * Injected by Spring.
     */
    @Autowired
    private String glDirectory;

    /**
     * Represents the name of the user performing the batch job.
     * <p/>
     * Injected by Spring.
     */
    @Autowired
    private String currentUserName;

    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private EntityManager entityManager;

    /**
     * <p>
     * Represents the STMP server used in tests.
     * </p>
     */
    private Wiser smtpServer;

    /**
     * <p>Represents the account id for tests.</p>
     */
    private long accountId;

    /**
     * <p>Adapter for earlier versions of JUnit.</p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BatchProcessingJobTests.class);
    }

    /**
     * <p>Sets up the unit tests.</p>
     *
     * @throws Exception to JUnit.
     */
    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        initDB();

        entityManager = getEntityManager();

        startSMTPServer();
    }

    /**
     * <p>Cleans up the unit tests.</p>
     *
     * @throws Exception to JUnit.
     */
    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        clearDirectory(inputDirectoryPath, false);
        clearDirectory(directoryToClean, false);
        clearDirectory(glDirectory, false);

        if (smtpServer != null) {
            smtpServer.stop();
        }
    }

    /**
     * Creates and starts the SMTP server.
     */
    private void startSMTPServer() {
        smtpServer = new Wiser();
        smtpServer.setPort(SMTP_PORT);
        smtpServer.start();
    }

    /**
     * Get today's audit batch.
     *
     * @return today's audit batch.
     * @throws Exception to JUnit.
     */
    private AuditBatch getTodayAuditBatch() throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(SysTime.getInstance().now());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        TypedQuery<AuditBatch> query = entityManager.createQuery(
                "select e from AuditBatch e where e.eventYear=:eventYear "
                        + "AND e.eventMonth=:eventMonth AND e.eventDay=:eventDay "
                        + "order by e.id desc", AuditBatch.class);
        query.setParameter("eventYear", year);
        query.setParameter("eventMonth", month);
        query.setParameter("eventDay", day);
        return query.getSingleResult();
    }

    /**
     * Deletes the file.
     *
     * @param file
     *            the file.
     */
    private static void deleteFile(File file) {
        if (file.isDirectory()) {
            for (File sub : file.listFiles()) {
                deleteFile(sub);
            }
        }
        file.delete();
    }

    /**
     * Clears the directory.
     *
     * @param directory
     *            the directory.
     * @param deleteDirectory
     *            whether delete the directory.
     */
    private static void clearDirectory(String directory, boolean deleteDirectory) {
        File dir = new File(directory);

        deleteFile(dir);

        if (!deleteDirectory) {
            dir.mkdirs();
        }
    }

    /**
     * Create lockbox file.
     *
     * @param file to create
     * @param content file content
     * @throws Exception to JUnit.
     */
    private void createFile(File file, String content) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(content.getBytes("utf-8"));
        fos.flush();
        fos.close();
    }

    /**
     * Create account.
     *
     * @throws Exception to JUnit.
     */
    private void createAccount() throws Exception {
        Account account = getAccount();
        account.setClaimNumber("1112233");
        account.setStatus(entityManager.find(AccountStatus.class, 1L));
        account.setPayCode(entityManager.find(PayCode.class, 1L));
        account.setFormType(entityManager.find(FormType.class, 1L));

        account.getHolder().setAgencyCode("18");
        account.getHolder().setBirthDate(DF.parse("03/12/1977"));
        account.getHolder().setMiddleInitial("A");
        account.getHolder().setSuffix(entityManager.find(Suffix.class, 1L));
        update(account.getHolder());

        account.setTotalDeposit(BigDecimal.ZERO);
        account.setTotalRedeposit(BigDecimal.ZERO);
        account.setTotalNonDeposit(BigDecimal.ZERO);
        account.setTotalVarRedeposit(BigDecimal.ZERO);
        account.setTotalFersW(new BigDecimal(200));
        accountId = create(account);
        account.setCalculationVersions(Arrays.asList(
                new CalculationVersion[] {getCalculationVersion()}));
        account.getCalculationVersions().get(0).setAccountId(accountId);        
        update(account);
    }

    /**
     * Test holiday.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_Holiday() throws Exception {
        Calendar cal = Calendar.getInstance();

        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        assertTrue(batchProcessingJob.isNowHoliday(cal.getTime())); // weekends

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        assertFalse(batchProcessingJob.isNowHoliday(cal.getTime())); // not holiday

        Holiday holiday = new Holiday();
        holiday.setExactDate(true);
        holiday.setDeleted(false);
        holiday.setMonthNumber(cal.get(Calendar.MONTH) + 1);
        holiday.setDayOfMonth(cal.get(Calendar.DAY_OF_MONTH));
        super.create(holiday);

        assertTrue(batchProcessingJob.isNowHoliday(cal.getTime())); // exact holiday date
    }

    /**
     * Verify batch service can exit when permission denied.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_BatchUserViolation() throws Exception {
        TestsHelper.setField(batchProcessingJob, "currentUserName", "NotBatchUser");

        try {
            batchProcessingJob.execute();

            assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
            assertEquals("Mail should be sent", "Service Credit User Violation",
                    smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim());
        } finally {
            TestsHelper.setField(batchProcessingJob, "currentUserName", currentUserName);
        }
    }

    /**
     * Verify batch service can exit when input files network folder is down.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_NetworkDown() throws Exception {

        clearDirectory(inputDirectoryPath, true);

        try {

            batchProcessingJob.execute();

            assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
            assertEquals("Mail should be sent", "SERVICE CREDIT BATCH CANNOT ACCESS NETWORK",
                    smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim());
            List<AuditRecord> auditRecords = entityManager.createQuery("from AuditRecord", AuditRecord.class).getResultList();
            assertEquals("Audit should be done.", 1,  auditRecords.size());
            assertEquals("Audit should be done.", "Lock Box Import Error",  auditRecords.get(0).getActionName());
            assertEquals("Audit should be done.", 2,  auditRecords.get(0).getParameters().size());
            AuditParameterRecord param1 = auditRecords.get(0).getParameters().get(0);
            AuditParameterRecord param2 = auditRecords.get(0).getParameters().get(1);
            assertEquals("Audit should be done.", "subject",  param1.getPropertyName());
            assertEquals("Audit should be done.", "SERVICE CREDIT BATCH CANNOT ACCESS NETWORK",  param1.getNewValue());
            assertEquals("Audit should be done.", "error description",  param2.getPropertyName());
            assertTrue("Audit should be done.", param2.getNewValue().contains("THE NETWORK IS DOWN!"));
        } finally {
            new File(inputDirectoryPath).mkdirs();
        }
    }

    /**
     * Verify batch service can exit when Mainframe is not available on the network.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_NoInputFiels() throws Exception {

        clearDirectory(inputDirectoryPath, false);
        createFile(new File(inputDirectoryPath, "IAmNotLockboxFile.txt"), ""); // This is not lock box file

        alwaysWorkDayBatchProcessingJob.execute();

        assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
        assertEquals("Mail should be sent", "SERVICE CREDIT LOCKBOX FILE IS LATE!",
                smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim());
        
        List<AuditRecord> auditRecords = entityManager.createQuery("from AuditRecord", AuditRecord.class).getResultList();
        assertEquals("Audit should be done.", 1, auditRecords.size());
        assertEquals("Audit should be done.", "Lock Box Import Error", auditRecords.get(0).getActionName());
        assertEquals("Audit should be done.", 2, auditRecords.get(0).getParameters().size());
    }

    /**
     * Verify can clean old files.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_CleanOldFile() throws Exception {
        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"), "");

        File oldFile1 = new File(directoryToClean, "old1.txt");
        createFile(oldFile1, "......");
        oldFile1.setLastModified(DF.parse("01/01/2010").getTime());

        File oldFile2 = new File(directoryToClean, "old2.txt"); // Just created
        createFile(oldFile2, "......");

        File oldFile3 = new File(directoryToClean, "IAmNotOld.txt"); // File name not match
        createFile(oldFile3, "......");
        oldFile3.setLastModified(DF.parse("01/01/2010").getTime());

        alwaysHolidayBatchProcessingJob.execute();

        assertFalse("old1.txt should be deleted", oldFile1.exists());
        assertTrue("old2.txt should not be deleted", oldFile2.exists());
        assertTrue("IAmNotOld.txt should not be deleted", oldFile3.exists());
    }

    /**
     * Verify can handle case of empty file.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_ImportFile_EmptyFile() throws Exception {
        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"), "");
        alwaysHolidayBatchProcessingJob.execute();

        List<MainframeImport> mainframeImports =
                entityManager.createQuery("from MainframeImport", MainframeImport.class).getResultList();
        assertEquals("None MainframeImport should be created", 0, mainframeImports.size());

        // File import mail should be sent
        assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
        assertEquals("Mail should be sent", "Service Credit Import: OK",
                smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim());
        List<AuditRecord> auditRecords = entityManager.createQuery("from AuditRecord", AuditRecord.class).getResultList();
        assertEquals("Audit should be done.", 1, auditRecords.size());
        assertEquals("Audit should be done.", "Lock Box Import Error", auditRecords.get(0).getActionName());
        assertEquals("Audit should be done.", 2, auditRecords.get(0).getParameters().size());
    }

    /**
     * Verify can handle case of bad line.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_ImportFile_BadLine() throws Exception {
        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"),
                "Bad line");

        alwaysHolidayBatchProcessingJob.execute();

        AuditBatch auditBatch = getTodayAuditBatch();
        assertTrue("File receieved flag should be true", auditBatch.getFileReceived());
        assertTrue("Error importing flag should be true", auditBatch.getErrorImporting());
        assertEquals("There should be 1 error", 1, auditBatch.getErrorCountImporting().intValue());

        List<MainframeImport> mainframeImports =
                entityManager.createQuery("from MainframeImport", MainframeImport.class).getResultList();
        assertEquals("MainframeImport should be created", 1, mainframeImports.size());
        assertTrue("Error flag should be true", mainframeImports.get(0).getErrorFlag());

        // File import mail should be sent
        assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
        assertTrue("Mail should be sent",
                smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim().contains("BATCH IMPORT ERROR"));
        List<AuditRecord> auditRecords = entityManager.createQuery("from AuditRecord", AuditRecord.class).getResultList();
        assertEquals("Audit should be done.", 1, auditRecords.size());
        assertEquals("Audit should be done.", "Lock Box Import Error", auditRecords.get(0).getActionName());
        assertEquals("Audit should be done.", 2, auditRecords.get(0).getParameters().size());
    }

    /**
     * Verify can handle case of bad R line.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_ImportFile_BadRLine() throws Exception {
        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"),
                "R64445566 bad R line");

        alwaysHolidayBatchProcessingJob.execute();

        AuditBatch auditBatch = getTodayAuditBatch();
        assertTrue("File receieved flag should be true", auditBatch.getFileReceived());
        assertTrue("Error importing flag should be true", auditBatch.getErrorImporting());
        assertEquals("There should be 1 error", 1, auditBatch.getErrorCountImporting().intValue());

        List<MainframeImport> mainframeImports =
                entityManager.createQuery("from MainframeImport", MainframeImport.class).getResultList();
        assertEquals("MainframeImport should be created", 1, mainframeImports.size());
        assertTrue("Error flag should be true", mainframeImports.get(0).getErrorFlag());

        // File import mail should be sent
        assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
        assertTrue("Mail should be sent",
                smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim().contains("PAYMENT ERROR"));
        List<AuditRecord> auditRecords = entityManager.createQuery("from AuditRecord", AuditRecord.class).getResultList();
        assertEquals("Audit should be done.", 1, auditRecords.size());
        assertEquals("Audit should be done.", "Lock Box Import Error", auditRecords.get(0).getActionName());
        assertEquals("Audit should be done.", 2, auditRecords.get(0).getParameters().size());
    }

    /**
     * Verify can handle case of bad R line.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_ImportFile_BadCLine() throws Exception {
        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"),
                "C64445566 bad C line");

        alwaysHolidayBatchProcessingJob.execute();

        AuditBatch auditBatch = getTodayAuditBatch();
        assertTrue("File receieved flag should be true", auditBatch.getFileReceived());
        assertTrue("Error importing flag should be true", auditBatch.getErrorImporting());
        assertEquals("There should be 1 error", 1, auditBatch.getErrorCountImporting().intValue());

        List<MainframeImport> mainframeImports =
                entityManager.createQuery("from MainframeImport", MainframeImport.class).getResultList();
        assertEquals("MainframeImport should be created", 1, mainframeImports.size());
        assertTrue("Error flag should be true", mainframeImports.get(0).getErrorFlag());

        // File import mail should be sent
        assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
        assertTrue("Mail should be sent", smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim()
                .contains("Change record rejected"));
        List<AuditRecord> auditRecords = entityManager.createQuery("from AuditRecord", AuditRecord.class).getResultList();
        assertEquals("Audit should be done.", 1, auditRecords.size());
        assertEquals("Audit should be done.", "Lock Box Import Error", auditRecords.get(0).getActionName());
        assertEquals("Audit should be done.", 2, auditRecords.get(0).getParameters().size());
    }

    /**
     * Verify can handle case of bad summary line.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_ImportFile_BadSummaryLine() throws Exception {
        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"),
                "R ZZZ bad Summary line");

        alwaysHolidayBatchProcessingJob.execute();

        AuditBatch auditBatch = getTodayAuditBatch();
        assertTrue("File receieved flag should be true", auditBatch.getFileReceived());
        assertTrue("Error importing flag should be true", auditBatch.getErrorImporting());
        assertEquals("There should be 1 error", 1, auditBatch.getErrorCountImporting().intValue());

        List<MainframeImport> mainframeImports =
                entityManager.createQuery("from MainframeImport", MainframeImport.class).getResultList();
        assertEquals("MainframeImport should be created", 1, mainframeImports.size());
        assertTrue("Error flag should be true", mainframeImports.get(0).getErrorFlag());

        // File import mail should be sent
        assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
        assertTrue("Mail should be sent", smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim()
                .contains("PAYMENT ERROR"));
        List<AuditRecord> auditRecords = entityManager.createQuery("from AuditRecord", AuditRecord.class).getResultList();
        assertEquals("Audit should be done.", 1, auditRecords.size());
        assertEquals("Audit should be done.", "Lock Box Import Error", auditRecords.get(0).getActionName());
        assertEquals("Audit should be done.", 2, auditRecords.get(0).getParameters().size());
    }


    /**
     * Verify can handle case of summary amount is less than imported amount.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_ImportFile_SummaryAmountNotMatch1() throws Exception {
        createAccount();

        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"),
                "C 02      1112233031277  Chizago, IL 12345-1234" + CRLF
                + "C 01      1112233031277  Mark I. Jason, III" + CRLF
                + "C 10      1112233031277  new_ssn" + CRLF
                + "R67778899 11122330312770003412020214 1    " + CRLF
                + "R64445566 11122330312770008900020314 0    " + CRLF
                + "R ZZZ     ZZZZZZZ0000012311    " + CRLF
                + "FileName lockbox_file_1.txt" + CRLF
                + " ");

        alwaysHolidayBatchProcessingJob.execute();

        // File import mail should be sent
        assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
        assertTrue("Mail should be sent", smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim()
                .contains("OUT OF BALANCE"));
        List<AuditRecord> auditRecords = entityManager.createQuery("from AuditRecord", AuditRecord.class).getResultList();
        assertEquals("Audit should be done.", 1, auditRecords.size());
        assertEquals("Audit should be done.", "Lock Box Import Error", auditRecords.get(0).getActionName());
        assertEquals("Audit should be done.", 2, auditRecords.get(0).getParameters().size());
    }

    /**
     * Verify can handle case of summary amount is more than imported amount.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_ImportFile_SummaryAmountNotMatch2() throws Exception {
        createAccount();

        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"),
                "C 02      1112233031277  Chizago, IL 12345-1234" + CRLF
                + "C 01      1112233031277  Mark I. Jason, III" + CRLF
                + "C 10      1112233031277  new_ssn" + CRLF
                + "R67778899 11122330312770003412020214 1    " + CRLF
                + "R64445566 11122330312770008900020314 0    " + CRLF
                + "R ZZZ     ZZZZZZZ0000088888    " + CRLF
                + "FileName lockbox_file_1.txt" + CRLF
                + " ");

        alwaysHolidayBatchProcessingJob.execute();

        // File import mail should be sent
        assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
        assertTrue("Mail should be sent", smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim()
                .contains("OUT OF BALANCE"));
        List<AuditRecord> auditRecords = entityManager.createQuery("from AuditRecord", AuditRecord.class).getResultList();
        assertEquals("Audit should be done.", 1, auditRecords.size());
        assertEquals("Audit should be done.", "Lock Box Import Error", auditRecords.get(0).getActionName());
        assertEquals("Audit should be done.", 2, auditRecords.get(0).getParameters().size());
    }

    /**
     * Verify main frame import are inserted into database.
     * The C line changes are applied. R line payments are created.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_ImportFile() throws Exception {
        createAccount();

        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"),
                "C 02      1112233031277  Chizago, IL 12345-1234" + CRLF
                + "C 01      1112233031277  Mark I. Jason, III" + CRLF
                + "C 10      1112233031277  new_ssn" + CRLF
                + "R67778899 11122330312770003412020214 1    " + CRLF
                + "R64445566 11122330312770008900020314 0    " + CRLF
                + "R ZZZ     ZZZZZZZ0000012312    " + CRLF
                + "FileName lockbox_file_1.txt" + CRLF
                + " ");

        alwaysHolidayBatchProcessingJob.execute();
        entityManager.clear();

        User batchProcessUser = entityManager.createQuery("SELECT e FROM User e"
                + " WHERE e.deleted = false AND e.username = :username", User.class)
                .setParameter("username", currentUserName).getSingleResult();

        AuditBatch auditBatch = getTodayAuditBatch();
        assertTrue("File receieved flag should be true", auditBatch.getFileReceived());
        assertFalse("Error importing flag should be false", auditBatch.getErrorImporting());
        assertEquals("There should be no error", 0, auditBatch.getErrorCountImporting().intValue());
        assertEquals("There should be 3 change requests", 3, auditBatch.getNumberChangeRequests().intValue());
        assertEquals("There should be 1 accepted check payment", 1, auditBatch.getNumberAccepted().intValue());
        assertEquals("There should be 1 accepted ach payment", 1, auditBatch.getNumberAchAccepted().intValue());
        assertEquals("123.12 should be imported", 0,
                Double.compare(123.12, auditBatch.getAmountImported().doubleValue()));

        List<MainframeImport> mainframeImports =
                entityManager.createQuery("from MainframeImport", MainframeImport.class).getResultList();
        assertEquals("MainframeImport should be created", 6, mainframeImports.size());

        // Assert payments are created
        int paymentsCount = 0;
        for (MainframeImport mainframeImport : mainframeImports) {
            assertFalse("Proccessing flag should be false", mainframeImport.getProcessingFlag());
            assertFalse("Error flag should be false", mainframeImport.getErrorFlag());
            assertEquals(auditBatch.getId(), mainframeImport.getAuditBatchId().longValue());

            if (mainframeImport.getPaymentTransactionId() != null) {
                paymentsCount++;
                PaymentTransaction pt = entityManager.find(
                        PaymentTransaction.class, mainframeImport.getPaymentTransactionId());
                if (pt.getAchPayment() == Boolean.TRUE) {
                    assertTrue(mainframeImport.getAchFlag());
                    assertEquals("777", pt.getPayTransBatchNumber());
                    assertEquals("88", pt.getPayTransBlockNumber());
                    assertEquals("99", pt.getPayTransSequenceNumber());
                    assertEquals(0, Double.compare(34.12, pt.getPayTransPaymentAmount().doubleValue()));
                    assertEquals("02/02/2014", DF.format(pt.getPayTransTransactionDate()));
                } else {
                    assertFalse(mainframeImport.getAchFlag());
                    assertEquals("444", pt.getPayTransBatchNumber());
                    assertEquals("55", pt.getPayTransBlockNumber());
                    assertEquals("66", pt.getPayTransSequenceNumber());
                    assertEquals(0, Double.compare(89, pt.getPayTransPaymentAmount().doubleValue()));
                    assertEquals("02/03/2014", DF.format(pt.getPayTransTransactionDate()));
                }
                assertEquals("03/12/1977", DF.format(pt.getScmDateOfBirth()));
                assertEquals("1112233", pt.getScmClaimNumber());
                assertEquals(batchProcessUser.getId(), pt.getTechnicianUserKey().longValue());

                // The transaction status code should be POSTED_PENDING
                assertEquals(PaymentTransactionCodes.POSTED_PENDING.getCode(), pt.getPayTransStatusCode().longValue());
            }
        }
        assertEquals(2, paymentsCount);

        // Check the C line changes are applied
        Account account = entityManager.find(Account.class, accountId);

        assertEquals("Chizago", account.getHolder().getAddress().getCity());
        assertEquals("IL", account.getHolder().getAddress().getState().getName());
        assertEquals("12345-1234", account.getHolder().getAddress().getZipCode());
        assertEquals("Chizago, IL 12345-1234", account.getHolder().getAddress().getStreet1());

        assertEquals("Mark", account.getHolder().getFirstName());
        assertEquals("I", account.getHolder().getMiddleInitial());
        assertEquals("Jason", account.getHolder().getLastName());
        assertEquals("III", account.getHolder().getSuffix().getName());
        assertEquals("new_ssn", account.getHolder().getSsn());

        // File import mail should be sent
        assertEquals("Mail should be sent", 1, smtpServer.getMessages().size());
        assertEquals("Mail should be sent", "Service Credit Import: OK",
                smtpServer.getMessages().get(0).getMimeMessage().getSubject().trim());
    }

    /**
     * Verify bills are processed to POSTED_COMPLETE status, and GL file created.
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void testCase_ProcessBills() throws Exception {
        createAccount();

        createFile(new File(inputDirectoryPath, "lockbox_file_1.txt"),
                "C 02      1112233031277  Chizago, IL 12345-1234" + CRLF
                + "C 01      1112233031277  Mark I. Jason, III" + CRLF
                + "C 10      1112233031277  new_ssn" + CRLF
                + "R67778899 11122330312770003412020214 1    " + CRLF
                + "R64445566 11122330312770008900020314 0    " + CRLF
                + "R ZZZ     ZZZZZZZ0000012312    " + CRLF
                + "FileName lockbox_file_1.txt");

        alwaysWorkDayBatchProcessingJob.execute();

        List<PaymentTransaction> pts = entityManager.createQuery("from PaymentTransaction",
                PaymentTransaction.class).getResultList();
        assertEquals("There should be 2 payment transactions", 2, pts.size());
        for (PaymentTransaction pt : pts) {
            // The transaction status code should be POSTED_COMPLETE
            assertEquals("Trans status code should be POSTED_COMPLETE",
                    PaymentTransactionCodes.POSTED_COMPLETE.getCode(), pt.getPayTransStatusCode().longValue());
        }

        assertTrue("GL file should be created", new File(glDirectory, "SCGL"
                + new SimpleDateFormat("yyMMdd").format(new Date(SysTime.getInstance().now())) + ".txt").exists());
    }
}
