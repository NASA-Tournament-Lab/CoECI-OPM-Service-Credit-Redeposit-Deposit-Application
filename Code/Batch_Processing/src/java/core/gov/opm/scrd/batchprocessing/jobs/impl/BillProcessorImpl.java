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

package gov.opm.scrd.batchprocessing.jobs.impl;

import static gov.opm.scrd.batchprocessing.jobs.BatchProcessHelper.checkState;
import static gov.opm.scrd.batchprocessing.jobs.BatchProcessHelper.nullToZero;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.batchprocessing.jobs.BatchProcessingConfigurationException;
import gov.opm.scrd.batchprocessing.jobs.BatchProcessingException;
import gov.opm.scrd.batchprocessing.jobs.BillProcessingException;
import gov.opm.scrd.batchprocessing.jobs.BillProcessor;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountNote;
import gov.opm.scrd.entities.application.AuditBatch;
import gov.opm.scrd.entities.application.CalculationResult;
import gov.opm.scrd.entities.application.CalculationResultItem;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.application.Dedeposit;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.Redeposit;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.batchprocessing.AccountNoteType;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
import gov.opm.scrd.entities.batchprocessing.PaymentTransactionCodes;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.DepositType;
import gov.opm.scrd.mock.MockReversePaymentRuleRequest;
import gov.opm.scrd.mock.MockReversePaymentRuleResponse;
import gov.opm.scrd.mock.MockWaltRuleRequest;
import gov.opm.scrd.mock.MockWaltRuleResponse;
import gov.opm.scrd.services.AccountService;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.RuleService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>This class is the default implementation of BillProcessor. It process the bills, updates supplement
 * persistence and changes master records.</p>
 *  <p>Thread - safety. The class is effectively thread - safe. The class state is changed during processing, but
 * only a single instance of class will be created at one moment of time. All the critical resources (database, local
 * file) are not expected to be shared, as only one instance of the job is expected to be executed on a machine in a
 * given day time frame.</p>
 *
 * @author faeton, liu
 * @version 1.1
 */
public class BillProcessorImpl implements BillProcessor {

    /**
     * Represents the sql for finding the payments by a certain transaction day.
     */
    private static final String GET_PAYMENT_BY_TRANSACTION_DAY =
            "select * from Payment p where p.claimNumber = :claimNumber and p.transactionDate >= :startDate"
            + " and p.transactionDate < :endDate";

    /**
     * <p>
     * Represents the JPQL to query User by username.
     * </p>
     */
    private static final String JPQL_QUERY_USER_BY_USERNAME = "SELECT e FROM User e"
        + " WHERE e.deleted = false AND e.username = :username";

    /**
     * Represents the account service. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null. It is injected by Spring.
     */
    @Autowired
    private AccountService accountService;

    /**
     * Represents the walt rule service. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null. It is injected by Spring.
     */
    @SuppressWarnings("rawtypes")
    @Autowired
    private RuleService waltRuleService;

    /**
     * Represents the reverse payment rule service. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null. It is injected by Spring.
     */
    @SuppressWarnings("rawtypes")
    @Autowired
    private RuleService reversePaymentRuleService;

    /**
     * Represents the account note texts. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null. It is injected by Spring.
     */
    @Resource
    private Map<AccountNoteType, String> accountNoteTexts;

    /**
     * Represents the current user name. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null. It is injected by Spring.
     */
    @Autowired
    private String currentUserName;

    /**
     * Represents the entity manager. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null. It is injected by Spring.
     */
    @Autowired
    private EntityManager entityManager;

    /**
     * Represents the logger. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null. It is injected by Spring.
     */
    @Autowired
    private Logger logger;

    /**
     * Represents the refund trigger amount. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null. It is injected by Spring.
     */
    @Autowired
    private BigDecimal refundTriggerAmount;

    /**
     * Represents the bill counter. It is accessible by getter and modified by setter. It can be any value. The
     * default value is 0.
     */
    private Integer billCounter = 0;

    /**
     * Represents the totals payments counter. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private Integer totalsPaymentsCounter = 0;

    /**
     * Represents the totals payments sum. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private BigDecimal totalsPaymentsSum = BigDecimal.ZERO;

    /**
     * Represents the last payments csd. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String lastPaymentsCSD;

    /**
     * Represents the process this payment. It is accessible by getter and modified by setter. It can be any
     * value. The default value is false.
     */
    private Boolean processThisPayment = false;

    /**
     * Represents the multi record print invoice. It is accessible by getter and modified by setter. It can be
     * any value. The default value is false.
     */
    private Boolean multiRecordPrintInvoice = false;

    /**
     * Represents the multi record account status. It is accessible by getter and modified by setter. It can be
     * any value. The default value is 0.
     */
    private Long multiRecordAccountStatus = 0L;

    /**
     * Represents the multi record refund required. It is accessible by getter and modified by setter. It can
     * be any value. The default value is false.
     */
    private Boolean multiRecordRefundRequired = false;

    /**
     * Represents the multi record ach stop letter. It is accessible by getter and modified by setter. It can
     * be any value. The default value is false.
     */
    private Boolean multiRecordACHStopLetter = false;

    /**
     * Represents the multi record update to completed. It is accessible by getter and modified by setter. It
     * can be any value. The default value is false.
     */
    private Boolean multiRecordUpdateToCompleted = false;

    /**
     * Represents the multi record over payment amount. It is accessible by getter and modified by setter. It
     * can be any value. The default value is 0.
     */
    private BigDecimal multiRecordOverPaymentAmount = BigDecimal.ZERO;

    /**
     * Represents the sb. It is accessible by getter and modified by setter. It can be any value. It is default
     * to an empty string builder.
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * Represents the reversal total. It is accessible by getter and modified by setter. It can be any value.
     * The default value is 0.
     */
    private BigDecimal reversalTotal = BigDecimal.ZERO;

    /**
     * Represents the payment count. It is accessible by getter and modified by setter. It can be any value.
     * The default value is 0.
     */
    private Integer paymentCount = 0;

    /**
     * Represents the payment total. It is accessible by getter and modified by setter. It can be any value.
     * The default value is 0.
     */
    private BigDecimal paymentTotal = BigDecimal.ZERO;

    /**
     * Represents the account total. It is accessible by getter and modified by setter. It can be any value.
     * The default value is 0.
     */
    private BigDecimal accountTotal = BigDecimal.ZERO;

    /**
     * Represents the this account. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Account thisAccount;

    /**
     * Represents the error count. It is accessible by getter and modified by setter. It can be any value. The
     * default value is 0.
     */
    private Integer errorCount = 0;

    /**
     * Represents the error found. It is accessible by getter and modified by setter. It can be any value. The
     * default value is false.
     */
    private Boolean errorFound = false;

    /**
     * Represents the claimant over payment amount. It is accessible by getter and modified by setter. It can
     * be any value. The default value is 0.
     */
    private BigDecimal claimantOverPaymentAmount = BigDecimal.ZERO;

    /**
     * Represents the invoice being processed. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private InvoiceData thisInvoice = null;

    /**
     * Represents the batch process user. It is initialized in at the beginning of processing.
     */
    private User batchProcessUser;

    /**
     * Creates instance of the class.
     */
    public BillProcessorImpl() {
    }

    /**
     * Processes the bills data.
     *
     * @param auditBatchId the id of audit batch log.
     * @param procMessage logging message used to collect information about process.
     * @param invoiceCount general information counter for invoices.
     * @param achStopCount general information counter for ACH stop markers.
     * @param refundMemoCount general information counter for refund memos.
     * @param reversalCount general information counter for reversal payments.
     * @param initialBillCount general information counter for initial bills.
     * @throws IllegalArgumentException if any argument is null, or procMessage is null/empty
     * @throws BillProcessingException if there is any problem executing the method.
     */
    @SuppressWarnings("unchecked")
    public void processBills(Long auditBatchId, StringBuilder procMessage, Integer invoiceCount,
            Integer achStopCount, Integer refundMemoCount, Integer reversalCount, Integer initialBillCount)
        throws BillProcessingException {
        String signature = "BillProcessorImpl.processBills";
        LoggingHelper.logEntrance(logger, signature,
            new String[] {
                "auditBatchId", "procMessage", "invoiceCount", "achStopCount", "refundMemoCount", "reversalCount",
                "initialBillCount"
            },
            new Object[] {
                auditBatchId, procMessage, invoiceCount, achStopCount, refundMemoCount, reversalCount,
                initialBillCount
            });
        checkIAE(auditBatchId == null, "auditBatchId is not allowed to be null");
        checkIAE(procMessage == null
                || procMessage.toString().trim().isEmpty(), "procMessage is not allowed to be null/empty");
        checkIAE(invoiceCount == null, "invoiceCount is not allowed to be null");
        checkIAE(achStopCount == null, "achStopCount is not allowed to be null");
        checkIAE(refundMemoCount == null, "refundMemoCount is not allowed to be null");
        checkIAE(reversalCount == null, "reversalCount is not allowed to be null");
        checkIAE(reversalCount == null, "reversalCount is not allowed to be null");

        // Reset
        reset();

        List<InvoiceData> invoiceData;

        try {
            entityManager.getTransaction().begin();

            // Initialize the batchProcessUser
            List<User> list = entityManager.createQuery(JPQL_QUERY_USER_BY_USERNAME, User.class)
                    .setParameter("username", currentUserName)
                    .getResultList();

            if (list.isEmpty()) {
                throw new BillProcessingException(
                        "Batch process user [" + currentUserName + "] is not found");
            }

            batchProcessUser = list.get(0);

            StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("BatchDailyPaymentsSelect");
            sp.setParameter("pNetworkId", batchProcessUser.getNetworkId());

            sp.execute();

            invoiceData = sp.getResultList();
            entityManager.getTransaction().commit();

        } catch (PersistenceException e) {
            LoggingHelper.logException(logger, signature, e);
            throw new BillProcessingException("Error finding the Invoice Data", e);
        }

        Integer[] invoiceCountArray = new Integer[] {invoiceCount};
        Integer[] refundMemoCountArray = new Integer[] {refundMemoCount};
        Integer[] reversalCountArray = new Integer[] {reversalCount};
        Integer[] achStopCountArray = new Integer[] {achStopCount};
        Exception ex = null;

        for (InvoiceData invoice : invoiceData) {
            entityManager.clear();
            thisInvoice = invoice;
            thisInvoice.setOverThePaymentAmount(BigDecimal.ZERO);
            thisInvoice.setAchStopLetter(false);

            if (thisInvoice.getPrintInitialBill()) {
                thisInvoice.setUpdateToCompleted(true);
                thisInvoice.setAccountStatus(1L);
                initialBillCount++;
            } else {
                try {

                    Long statusCode = thisInvoice.getPayTransStatusCode();
                    if (PaymentTransactionCodes.POSTED_PENDING.getCode() == statusCode) {
                        processPostedPayments(auditBatchId, procMessage, thisInvoice, achStopCountArray,
                            refundMemoCountArray, invoiceCountArray);
                    } else if (PaymentTransactionCodes.REVERSE_PENDING.getCode() == statusCode) {
                        processReversedPending(auditBatchId, procMessage, thisInvoice, reversalCountArray);
                    } else if (PaymentTransactionCodes.SUSPENSE_REFUND_PENDING.getCode() == statusCode) {
                        processSuspenseRefundPending(auditBatchId, procMessage, thisInvoice);
                    } else if (PaymentTransactionCodes.CREDIT_BALANCE_REFUND_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.WRITE_OFF_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.ADJUSTMENT_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.BATCH_AUTO_REFUND_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.MANUAL_ADJUSTMENT_CANCELLED_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.BATCH_AUTO_REFUND_CANCELLED_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.SUSPENSE_DEBIT_VOUCHER_PENDING.getCode() == statusCode) {
                        if (applyManualAdjustment(thisInvoice) != 0) {
                            processError(auditBatchId, procMessage, thisInvoice);
                        }
                    } else if (PaymentTransactionCodes.ANNUITY_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.HEALTH_BENEFITS_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.VOLUNTARY_CONTRIBUTIONS_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.DIRECT_PAY_LIFE_PENDING.getCode() == statusCode
                            || PaymentTransactionCodes.DEBIT_VOUCHER_PENDING.getCode() == statusCode) {
                        thisInvoice.setUpdateToCompleted(true);
                    } else {
                        logMessage(Level.DEBUG, "The transaction status code is not supported");
                    }
                    thisInvoice.setErrorProcessing(errorFound);

                    entityManager.getTransaction().begin();

                    StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("BatchDailyPaymentsUpdate");
                    sp.setParameter("pPayTransactionKey", thisInvoice.getPayTransactionKey());
                    sp.setParameter("pOverPaymentAmount", thisInvoice.getOverPaymentAmount());
                    sp.setParameter("pPrintInvoice", thisInvoice.getPrintInvoice());
                    sp.setParameter("pRefundRequired", thisInvoice.getRefundRequired());
                    sp.setParameter("pReversedPayment", thisInvoice.getReversedPayment());
                    sp.setParameter("pUpdateToCompleted", thisInvoice.getUpdateToCompleted());
                    sp.setParameter("pACHStopLetter", thisInvoice.getAchStopLetter());
                    sp.setParameter("pACHPayment", thisInvoice.getAchPayment());
                    sp.setParameter("pPrintInitialBill", thisInvoice.getPrintInitialBill());
                    sp.setParameter("pErrorProcessing", thisInvoice.getErrorProcessing());
                    sp.setParameter("pAccountStatus", entityManager.find(
                        AccountStatus.class, thisInvoice.getAccountStatus()).getName());
                    sp.setParameter("pSCMClaimNumber", thisInvoice.getScmClaimNumber());
                    sp.setParameter("pAuditBatchIDLog", thisInvoice.getAuditBatchId());
                    sp.setParameter("pNetworkId", batchProcessUser.getNetworkId());
                    sp.execute();
                    entityManager.getTransaction().commit();

                } catch (BillProcessingException e) {
                    ex = e;
                    entityManager.getTransaction().rollback();
                } catch (Exception e) {
                    ex = e;
                    entityManager.getTransaction().rollback();
                    LoggingHelper.logException(logger, signature, e);
                    multiRecordUpdateToCompleted = false;
                    errorFound = true;
                    errorCount += 1;
                    logMessage(Level.ERROR, auditBatchId + " Processing Bill persisting Invoice failed");
                    procMessage.append("\r\n");
                    procMessage.append("----------> ERROR! Persisting FAILED! <----------");
                    procMessage.append("\r\n");
                    procMessage.append("CSD #");
                    procMessage.append(thisInvoice.getScmClaimNumber());
                    procMessage.append(" Payment ID#");
                    procMessage.append(thisInvoice.getPayTransactionKey());
                    procMessage.append(" had an error. ");
                    procMessage.append("\r\n");
                    procMessage.append(thisInvoice.getPayTransPaymentAmount());
                    procMessage.append(thisInvoice.getPayTransTransactionDate());
                    procMessage.append("\r\n");
                    procMessage.append("Database Error: ProcessBills - ");
                    procMessage.append(e.getMessage());
                }
            }
        }

        logMessage(Level.INFO, "Total Payment Records: " + invoiceData.size());

        if ((paymentCount + initialBillCount + reversalCount + achStopCount + refundMemoCount) > 0) {
            sb.append("Boyers' printout count:\r");
            sb.append(invoiceCount + " RI36-18 Receipt Invoices Printed.");
            sb.append(initialBillCount + " RI36-23 Initial Invoices Printed.\r\n");
            sb.append(reversalCount + " RI36-18 Reversed Invoices Printed.\r\n");
            sb.append(achStopCount + " Stop ACH Letters Printed.\r\n");
            sb.append("\r\n");
            sb.append("DC's printout count: \r\n");
            sb.append(refundMemoCount + " Refund Memos Printed.\r\n");
            sb.append("\r\n");

            if (invoiceCount > 0) {
                sb.append("Posted " + invoiceCount + " payments totaling " + paymentTotal + "\r\n");
            }

            if (reversalCount > 0) {
                sb.append("Reversed " + reversalCount + " payments totaling " + reversalTotal + "\r\n");
            }

            sb.append("\r\n");

            if (errorCount == 0) {
                sb.append("Processed Successfully. ");
            } else {
                sb.append("# ERRORS FOUND ");
                sb.append(errorCount);
                sb.append("\r\n\r\n");
            }

            procMessage.append("\r\n\r\n");
            procMessage.append(sb);
        }

        try {
            AuditBatch audit = entityManager.find(AuditBatch.class, auditBatchId);

            if (audit == null) {
                logMessage(Level.ERROR, "Error updating log: the audit batch doesn't exist.");
                throw new BillProcessingException("audit batch doesn't exist");
            }

            audit.setDailyAction(true);
            audit.setAmountProcessed(nullToZero(audit.getAmountProcessed()).add(paymentTotal));
            audit.setPaymentsProcessed(nullToZero(audit.getPaymentsProcessed()) + paymentCount);
            audit.setInitialBillsProcessed(nullToZero(audit.getInitialBillsProcessed()) + initialBillCount);
            audit.setReversedProcessed(nullToZero(audit.getReversedProcessed()) + reversalCount);
            audit.setAchStopLetters(nullToZero(audit.getAchStopLetters()) + achStopCount);
            audit.setRefundMemos(nullToZero(audit.getRefundMemos()) + refundMemoCount);
            audit.setErrorCountProcessing(nullToZero(audit.getErrorCountProcessing()) + errorCount);
            audit.setErrorProcessing(errorFound || audit.getErrorProcessing() == Boolean.TRUE);
            audit.setBatchTime(new Date());
            entityManager.getTransaction().begin();
            entityManager.merge(audit);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            LoggingHelper.logException(logger, signature, e);
            procMessage.append("\r\n");
            procMessage.append("----------> ERROR! NIGHTLY PROCESSING FAILED! <----------");
            procMessage.append("\r\n");
            procMessage.append("Database Error: ProcessBills - ");
            procMessage.append(e.getMessage());
            throw new BillProcessingException(e.getMessage(), e);
        }

        if (ex != null) {
            if (ex instanceof BillProcessingException) {
                throw (BillProcessingException) ex;
            }
            throw new BillProcessingException(ex.getMessage(), ex);
        }

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Reset the internal fields.
     */
    private void reset() {
        this.billCounter = 0;
        this.totalsPaymentsCounter = 0;
        this.totalsPaymentsSum = BigDecimal.ZERO;
        this.lastPaymentsCSD = null;
        this.processThisPayment = false;
        this.multiRecordPrintInvoice = false;
        this.multiRecordAccountStatus = 0L;
        this.multiRecordRefundRequired = false;
        this.multiRecordACHStopLetter = false;
        this.multiRecordUpdateToCompleted = false;
        this.multiRecordOverPaymentAmount = BigDecimal.ZERO;
        this.sb = new StringBuilder();
        this.reversalTotal = BigDecimal.ZERO;
        this.paymentCount = 0;
        this.paymentTotal = BigDecimal.ZERO;
        this.thisAccount = null;
        this.errorCount = 0;
        this.errorFound = false;
        this.claimantOverPaymentAmount = BigDecimal.ZERO;
        this.thisInvoice = null;
        this.batchProcessUser = null;
        this.entityManager.clear();
    }

    /**
     * Log the message at the given level.
     *
     * @param level the logging level
     * @param message the message to be logged
     */
    private void logMessage(Level level, String message) {
        if (logger != null) {
            logger.log(level, message);
        }
    }

    /**
     * Process posted payments.
     *
     * @param auditBatchId the audit batch id
     * @param procMessage the proc message
     * @param thisInvoice the this invoice
     * @param achStopCountArray the ach stop count array
     * @param refundMemoCountArray the refund memo count array
     * @param invoiceCountArray the invoice count array
     *
     * @throws BillProcessingException the bill processing exception
     */
    private void processPostedPayments(Long auditBatchId, StringBuilder procMessage, InvoiceData thisInvoice,
        Integer[] achStopCountArray, Integer[] refundMemoCountArray, Integer[] invoiceCountArray)
        throws BillProcessingException {
        String signature = "BillProcessorImpl.processPostedPayments";
        LoggingHelper.logEntrance(logger, signature,
            new String[] {
                "auditBatchId", "procMessage", "thisInvoice", "achStopCountArray", "refundMemoCountArray",
                "invoiceCountArray"
            },
            new Object[] {
                auditBatchId, procMessage, thisInvoice, achStopCountArray, refundMemoCountArray, invoiceCountArray
            });

        try {
            thisInvoice.setReversedPayment(false);

            thisAccount = accountService.getByClaimNumber(thisInvoice.getScmClaimNumber());

            if (thisAccount == null) {
                throw LoggingHelper.logException(
                        logger, signature, new BillProcessingException("the account can't be found"));
            }

            List<CalculationVersion> versions =
                    new ArrayList<CalculationVersion>(thisAccount.getCalculationVersions());

            if (thisInvoice.getAccountPaymentTotal() == null) {
                thisInvoice.setAccountPaymentTotal(BigDecimal.ZERO);
            }

            accountTotal = BigDecimal.ZERO;

            for (CalculationVersion version : versions) {
                if (version.getCalculationResult().isOfficial()) {
                    for (Dedeposit dedeposit : version.getCalculationResult().getDedeposits()) {
                        accountTotal = accountTotal.add(dedeposit.getDeposit());
                        accountTotal = accountTotal.add(dedeposit.getInterest());
                        accountTotal = accountTotal.subtract(dedeposit.getTotal());

                        thisInvoice.setAccountPaymentTotal(
                                thisInvoice.getAccountPaymentTotal().add(dedeposit.getTotal()));
                    }

                    for (Redeposit redeposit : version.getCalculationResult().getRedeposits()) {
                        accountTotal = accountTotal.add(redeposit.getDeposit());
                        accountTotal = accountTotal.add(redeposit.getInterest());
                        accountTotal = accountTotal.subtract(redeposit.getTotal());

                        thisInvoice.setAccountPaymentTotal(
                                thisInvoice.getAccountPaymentTotal().add(redeposit.getTotal()));
                    }

                    for (CalculationResultItem resultItem : version.getCalculationResult().getItems()) {
                        accountTotal = accountTotal.subtract(resultItem.getPaymentsApplied());
                        thisInvoice.setAccountPaymentTotal(thisInvoice.getAccountPaymentTotal()
                                                                      .add(resultItem.getPaymentsApplied()));
                    }
                }
            }

            thisInvoice.setAccountBalanceNew(accountTotal.subtract(thisInvoice.getPayTransPaymentAmount()));

            if ((thisInvoice.getAccountStatus() == 1)
                    && (thisInvoice.getAccountBalance().compareTo(BigDecimal.ZERO) > 0)
                    && (thisInvoice.getPayTransPaymentAmount().compareTo(BigDecimal.ZERO) > 0)) {
                if (thisInvoice.getScmClaimNumber().equals(lastPaymentsCSD)
                        && (thisInvoice.getNumberPaymentsToday() > 1)) {
                    billCounter++;
                } else {
                    lastPaymentsCSD = thisInvoice.getScmClaimNumber();
                    billCounter = 1;
                }

                if (thisInvoice.getNumberPaymentsToday() == 1) {
                    processThisPayment = true;
                    totalsPaymentsSum = thisInvoice.getPayTransPaymentAmount();
                    totalsPaymentsCounter = 1;
                } else {
                    if (billCounter == 1) {
                        processThisPayment = true;
                        totalsPaymentsSum = thisInvoice.getTodaysPaymentTotal();
                        totalsPaymentsCounter = thisInvoice.getNumberPaymentsToday();
                        thisInvoice.setPayTransPaymentAmount(totalsPaymentsSum);
                    } else {
                        processThisPayment = false;
                        thisInvoice.setPrintInvoice(multiRecordPrintInvoice);
                        thisInvoice.setAccountStatus(multiRecordAccountStatus);
                        thisInvoice.setRefundRequired(false);
                        thisInvoice.setAchStopLetter(false);
                        thisInvoice.setUpdateToCompleted(multiRecordUpdateToCompleted);
                        thisInvoice.setOverPaymentAmount(BigDecimal.ZERO);
                    }
                }

                if (processThisPayment) {
                    thisInvoice.setAccountBalanceNew(accountTotal.subtract(totalsPaymentsSum));
                    thisInvoice.setTodaysPaymentTotal(totalsPaymentsSum);
                    multiRecordAccountStatus = thisInvoice.getAccountStatus();
                    multiRecordACHStopLetter = false;
                    multiRecordOverPaymentAmount = BigDecimal.ZERO;
                    multiRecordPrintInvoice = false;
                    multiRecordRefundRequired = false;
                    multiRecordUpdateToCompleted = false;

                    // We will be assuming there exists some rule/request/response for accepting
                    // the payment for no prompt class
                    // NOTE, FF issue
                    MockWaltRuleRequest request = new MockWaltRuleRequest();

                    // ruleRequest.setNoPrompt(true);
                    @SuppressWarnings("unchecked")
                    MockWaltRuleResponse response = (MockWaltRuleResponse) waltRuleService.execute(request);

                    int returnCode = response.getReturnCode();

                    switch (returnCode) {
                    case 0:
                        multiRecordPrintInvoice = true;
                        invoiceCountArray[0]++;

                        if (accountTotal.compareTo(BigDecimal.ZERO) <= 0) {
                            multiRecordAccountStatus = 12L;
                        }

                        multiRecordRefundRequired = false;

                        if (thisInvoice.getAchPayment()) {
                            if ((accountTotal.compareTo(new BigDecimal("2").multiply(
                                            thisInvoice.getPayTransPaymentAmount())) < 0)
                                    && (accountTotal.compareTo(thisInvoice.getPayTransPaymentAmount()) >= 0)) {
                                multiRecordACHStopLetter = true;
                                achStopCountArray[0]++;
                                insertAccountNote(AccountNoteType.STOP_ACH_LETTER);
                            }
                        }

                        multiRecordUpdateToCompleted = true;
                        paymentCount += totalsPaymentsCounter;
                        paymentTotal = paymentTotal.add(totalsPaymentsSum);

                        break;

                    case 1:
                        claimantOverPaymentAmount = thisInvoice.getPayTransPaymentAmount();
                        thisInvoice.setOverThePaymentAmount(claimantOverPaymentAmount);

                        if (claimantOverPaymentAmount.compareTo(refundTriggerAmount) > 0) {
                            multiRecordRefundRequired = true;
                            multiRecordOverPaymentAmount = claimantOverPaymentAmount;
                            refundMemoCountArray[0]++;

                            if (thisInvoice.getAchPayment()) {
                                insertAccountNote(AccountNoteType.ACH_OVER_PAYMENT);
                            } else {
                                insertAccountNote(AccountNoteType.REFUND_MEMO);
                            }
                        } else {
                            multiRecordRefundRequired = false;

                            if (claimantOverPaymentAmount.compareTo(BigDecimal.ZERO) > 0) {
                                insertAccountNote(AccountNoteType.CREDIT_BALANCE);
                            } else {
                                insertAccountNote(AccountNoteType.ACCOUNT_SET_TO_HISTORY);
                            }
                        }

                        multiRecordUpdateToCompleted = true;
                        multiRecordPrintInvoice = true;
                        invoiceCountArray[0]++;
                        multiRecordAccountStatus = 12L;
                        paymentCount += totalsPaymentsCounter;
                        paymentTotal = paymentTotal.add(totalsPaymentsSum);

                        break;

                    case 3:
                        multiRecordPrintInvoice = false;
                        multiRecordRefundRequired = false;
                        errorCount++;
                        errorFound = true;
                        logMessage(Level.ERROR, auditBatchId + "Calculation Failure: "
                                + waltRuleService.getClass().getCanonicalName()
                                + " calculating the new amount in the calculation engine.");
                        multiRecordUpdateToCompleted = false;
                        procMessage.append("\r\n\r\n");
                        procMessage.append("ATTENTION! Could not process payment record #");
                        procMessage.append(thisInvoice.getPayTransactionKey());
                        procMessage.append(" dated ");
                        procMessage.append(thisInvoice.getPayTransTransactionDate());
                        procMessage.append(" for ");
                        procMessage.append(thisInvoice.getPayTransPaymentAmount());
                        procMessage.append(". The CSD Claim Number is ");
                        procMessage.append(thisInvoice.getScmClaimNumber());
                        procMessage.append(". Error: ");
                        procMessage.append(response.getErrorDescription());
                        procMessage.append("\r\n");
                        procMessage.append(" Please notify the responsible party. ");
                        procMessage.append("\r\n\r\n");
                        thisInvoice.setNote(response.getDescription());

                        break;

                    case 4:
                        multiRecordPrintInvoice = false;
                        multiRecordRefundRequired = false;
                        errorCount++;
                        errorFound = true;
                        multiRecordUpdateToCompleted = false;
                        procMessage.append(
                            "ATTENTION! We cannot accept payments dated before the initial billing."
                            + " Can not process payment record #");
                        procMessage.append(thisInvoice.getPayTransactionKey());
                        procMessage.append(" for ");
                        procMessage.append(thisInvoice.getPayTransPaymentAmount());
                        procMessage.append(". The CSD Claim Number is ");
                        procMessage.append(thisInvoice.getScmClaimNumber());
                        procMessage.append(". This payment is dated ");
                        procMessage.append(thisInvoice.getPayTransTransactionDate());
                        procMessage.append(" which is before the initial billing calculation date of ");
                        procMessage.append(thisAccount.getComputationDate());
                        procMessage.append("\r\n");
                        procMessage.append(". Please notify the responsible party. ");
                        procMessage.append("\r\n\r\n");
                        thisInvoice.setNote(response.getDescription());
                        insertAccountNote(AccountNoteType.PAYMENT_BEFORE_INITIAL_BILL);

                        break;
                    default:
                        // do nothing
                        break;
                    }

                    thisInvoice.setPrintInvoice(multiRecordPrintInvoice);
                    thisInvoice.setAccountStatus(multiRecordAccountStatus);
                    thisInvoice.setRefundRequired(multiRecordRefundRequired);
                    thisInvoice.setAchStopLetter(multiRecordACHStopLetter);
                    thisInvoice.setUpdateToCompleted(multiRecordUpdateToCompleted);
                    thisInvoice.setOverPaymentAmount(multiRecordOverPaymentAmount);
                }
            } else {
                thisInvoice.setRefundRequired(false);
                thisInvoice.setOverPaymentAmount(BigDecimal.ZERO);

                if (thisInvoice.getPayTransPaymentAmount().equals(BigDecimal.ZERO)) {
                    thisInvoice.setUpdateToCompleted(true);
                    thisInvoice.setPrintInvoice(true);
                    invoiceCountArray[0]++;
                } else {
                    claimantOverPaymentAmount = BigDecimal.ZERO;

                    if (updateMasterRecord(thisInvoice) == 0) {
                        thisInvoice.setOverPaymentAmount(claimantOverPaymentAmount);
                        thisInvoice.setUpdateToCompleted(true);
                        thisInvoice.setPrintInvoice(true);
                        invoiceCountArray[0]++;
                        paymentCount++;
                        paymentTotal = paymentTotal.add(thisInvoice.getPayTransPaymentAmount());
                    } else {
                        thisInvoice.setOverPaymentAmount(BigDecimal.ZERO);
                        thisInvoice.setUpdateToCompleted(false);
                        thisInvoice.setPrintInvoice(false);
                    }
                }

                if ((thisInvoice.getAccountBalance().compareTo(BigDecimal.ZERO) < 0)
                        && (thisInvoice.getAccountStatus() == 1)) {
                    thisInvoice.setAccountStatus(12L);
                }
            }
        } catch (Exception e) {
            // logging of BatchProcessingException is already done
            if (!(e instanceof BatchProcessingException)) {
                LoggingHelper.logException(logger, signature, e);
            }
            multiRecordUpdateToCompleted = false;
            errorFound = true;
            errorCount += 1;
            logMessage(Level.ERROR, auditBatchId + "Payment Failure: "
                    + waltRuleService.getClass().getCanonicalName() + " processing a payment.");
            procMessage.append("\r\n");
            procMessage.append("----------> ERROR! PAYMENT FAILED! <----------");
            procMessage.append("\r\n");
            procMessage.append("CSD #");
            procMessage.append(thisInvoice.getScmClaimNumber());
            procMessage.append(" Payment ID#");
            procMessage.append(thisInvoice.getPayTransactionKey());
            procMessage.append(" had an error. ");
            procMessage.append("\r\n");
            procMessage.append(thisInvoice.getPayTransPaymentAmount());
            procMessage.append(thisInvoice.getPayTransTransactionDate());
            procMessage.append("\r\n");
            procMessage.append("Database Error: ProcessBills - ");
            procMessage.append(e.getMessage());
            throw new BillProcessingException("ERROR! PAYMENT FAILED!", e);
        }

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Process reversed pending.
     *
     * @param auditBatchId the audit batch id
     * @param procMessage the proc message
     * @param thisInvoice the this invoice
     * @param reversalCountArray the reversal count array
     *
     * @throws BillProcessingException the bill processing exception
     */
    private void processReversedPending(Long auditBatchId, StringBuilder procMessage, InvoiceData thisInvoice,
        Integer[] reversalCountArray) throws BillProcessingException {
        String signature = "BillProcessorImpl.processReversedPending";
        LoggingHelper.logEntrance(logger, signature,
            new String[] {"auditBatchId", "procMessage", "thisInvoice", "reversalCountArray"},
            new Object[] {auditBatchId, procMessage, thisInvoice, reversalCountArray});

        try {
            // We will be assuming there exists some rule/request/response
            // for checking reverse payment the payment for no prompt class
            // NOTE, this is a FF issue
            MockReversePaymentRuleRequest request = new MockReversePaymentRuleRequest();

            // ruleRequest.setNoPrompt(true);
            // ruleRequest.setPayTransactionKey(thisInvoice.getPayTransactionKey());
            @SuppressWarnings("unchecked")
            MockReversePaymentRuleResponse response =
                    (MockReversePaymentRuleResponse) reversePaymentRuleService.execute(request);

            if (response.isAllowed()) {
                thisInvoice.setReversedPayment(true);
                thisInvoice.setUpdateToCompleted(true);
                reversalCountArray[0]++;
                reversalTotal = reversalTotal.add(thisInvoice.getPayTransPaymentAmount());
            } else {
                thisInvoice.setPrintInvoice(false);
                thisInvoice.setRefundRequired(false);
                errorCount += 1;
                errorFound = true;
                procMessage.append("\r\n\r\n");
                procMessage.append("ATTENTION! Could not process payment record #");
                procMessage.append(thisInvoice.getPayTransactionKey());
                procMessage.append(" dated ");
                procMessage.append(thisInvoice.getPayTransTransactionDate());
                procMessage.append(" for ");
                procMessage.append(thisInvoice.getPayTransPaymentAmount());
                procMessage.append(". The CSD Claim Number is ");
                procMessage.append(thisInvoice.getScmClaimNumber());
                procMessage.append(". Error: ");
                procMessage.append(response.getDescription());
                procMessage.append("\r\n");
                procMessage.append(" Please notify the responsible party. ");
                procMessage.append("\r\n\r\n");
                logMessage(Level.INFO, auditBatchId + " Calculation Failure." + " calculating the new amount");
                thisInvoice.setUpdateToCompleted(false);
            }
        } catch (OPMException e) {
            LoggingHelper.logException(logger, signature, e);
            thisInvoice.setUpdateToCompleted(false);
            errorFound = true;
            errorCount += 1;
            logMessage(Level.ERROR, auditBatchId + " Reversing Failure: "
                    + reversePaymentRuleService.getClass().getCanonicalName() + " reversing a payment.");
            procMessage.append("\r\n");
            procMessage.append("----------> ERROR! REVERSAL FAILED! <----------");
            procMessage.append("\r\n");
            procMessage.append("CSD #");
            procMessage.append(thisInvoice.getScmClaimNumber());
            procMessage.append(" Payment ID#");
            procMessage.append(thisInvoice.getPayTransactionKey());
            procMessage.append(" would not reverse. ");
            procMessage.append("\r\n");
            procMessage.append(thisInvoice.getPayTransPaymentAmount());
            procMessage.append(thisInvoice.getPayTransTransactionDate());
            procMessage.append("\r\n");
            procMessage.append("Database Error: ProcessBills - ");
            procMessage.append(e.getMessage());
            throw new BillProcessingException("ERROR! REVERSAL FAILED!", e);
        }

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Process suspense refund pending.
     *
     * @param auditBatchId the audit batch id
     * @param procMessage the proc message
     * @param thisInvoice the this invoice
     */
    private void processSuspenseRefundPending(Long auditBatchId, StringBuilder procMessage,
            InvoiceData thisInvoice) {
        String signature = "BillProcessorImpl.processSuspenseRefundPending";
        LoggingHelper.logEntrance(logger, signature, new String[] {"auditBatchId", "procMessage", "thisInvoice"},
            new Object[] {auditBatchId, procMessage, thisInvoice});
        thisInvoice.setRefundRequired(true);
        thisInvoice.setOverPaymentAmount(thisInvoice.getPayTransPaymentAmount());
        thisInvoice.setOverThePaymentAmount(thisInvoice.getPayTransPaymentAmount());
        thisInvoice.setUpdateToCompleted(true);
        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Process error.
     *
     * @param auditBatchId the audit batch id
     * @param procMessage the proc message
     * @param thisInvoice the this invoice
     */
    private void processError(Long auditBatchId, StringBuilder procMessage, InvoiceData thisInvoice) {
        String signature = "BillProcessorImpl.processError";
        LoggingHelper.logEntrance(logger, signature, new String[] {"auditBatchId", "procMessage", "thisInvoice"},
            new Object[] {auditBatchId, procMessage, thisInvoice});
        procMessage.append("ApplyManualAdjustment: ERROR with Pay ID# " + thisInvoice.getPayTransactionKey()
            + " for CSD #");
        procMessage.append(thisInvoice.getScmClaimNumber() + "");
        logMessage(Level.INFO, auditBatchId + " processing an adjustment payment.");

        thisInvoice.setOverPaymentAmount(BigDecimal.ZERO);
        thisInvoice.setPrintInvoice(false);
        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Apply manual adjustment.
     *
     * @param thisInvoice the this invoice
     *
     * @return the status code for the manual adjustment
     */
    private Integer applyManualAdjustment(InvoiceData thisInvoice) {
        String signature = "BillProcessorImpl.applyManualAdjustment";
        LoggingHelper.logEntrance(logger, signature, new String[] {"thisInvoice"}, new Object[] {thisInvoice});

        Integer returnCode = 0;

        BigDecimal workingFERSTotalPayment = BigDecimal.ZERO;
        BigDecimal workingPost982DepositTotalPayment = BigDecimal.ZERO;
        BigDecimal workingPost982RedepositTotalPayment = BigDecimal.ZERO;
        BigDecimal workingPre1082RedepositTotalPayment = BigDecimal.ZERO;
        BigDecimal workingPre1082DepositTotalPayment = BigDecimal.ZERO;
        BigDecimal totalOwed = BigDecimal.ZERO;

        try {
            thisAccount = accountService.getByClaimNumber(thisInvoice.getScmClaimNumber());
            List<CalculationVersion> versions =
                    new ArrayList<CalculationVersion>(thisAccount.getCalculationVersions());

            for (CalculationVersion calculationVersion : versions) {
                if (calculationVersion.getCalculationResult().isOfficial()) {
                    CalculationResult result = calculationVersion.getCalculationResult();
                    CalculationResultItem item = result.getItems().get(0);
                    totalOwed.add(item.getDeductionAmount());
                    totalOwed.add(item.getTotalInterest());

                    workingFERSTotalPayment.add(item.getPaymentsApplied());

                    for (Dedeposit dedeposit : result.getDedeposits()) {
                        if (DepositType.CSRS_POST_10_82_DEPOSIT.equals(dedeposit.getDepositType())) {
                            totalOwed = totalOwed.add(dedeposit.getTotal());
                            totalOwed = totalOwed.add(dedeposit.getInterest());
                            workingPost982DepositTotalPayment =
                                    workingPost982DepositTotalPayment.add(dedeposit.getTotal());
                            workingPost982DepositTotalPayment =
                                    workingPost982DepositTotalPayment.add(dedeposit.getInterest());
                        }
                    }

                    for (Redeposit redeposit : result.getRedeposits()) {
                        if (DepositType.CSRS_POST_10_82_DEPOSIT.equals(redeposit.getDepositType())) {
                            totalOwed = totalOwed.add(redeposit.getTotal());
                            totalOwed = totalOwed.add(redeposit.getInterest());
                            workingPost982RedepositTotalPayment =
                                    workingPost982RedepositTotalPayment.add(redeposit.getTotal());
                            workingPost982RedepositTotalPayment =
                                    workingPost982RedepositTotalPayment.add(redeposit.getInterest());
                        }
                    }

                    for (Redeposit redeposit : result.getRedeposits()) {
                        if (DepositType.CSRS_PRE_10_82_DEPOSIT.equals(redeposit.getDepositType())) {
                            totalOwed = totalOwed.add(redeposit.getTotal());
                            totalOwed = totalOwed.add(redeposit.getInterest());
                            workingPre1082RedepositTotalPayment =
                                    workingPre1082RedepositTotalPayment.add(redeposit.getTotal());
                            workingPre1082RedepositTotalPayment =
                                    workingPre1082RedepositTotalPayment.add(redeposit.getInterest());
                        }
                    }

                    for (Dedeposit dedeposit : result.getDedeposits()) {
                        if (DepositType.CSRS_PRE_10_82_DEPOSIT.equals(dedeposit.getDepositType())) {
                            totalOwed = totalOwed.add(dedeposit.getTotal());
                            totalOwed = totalOwed.add(dedeposit.getInterest());
                            workingPre1082DepositTotalPayment =
                                    workingPre1082DepositTotalPayment.add(dedeposit.getTotal());
                            workingPre1082DepositTotalPayment =
                                    workingPre1082DepositTotalPayment.add(dedeposit.getInterest());
                        }
                    }
                }
            }

            paymentTotal = workingFERSTotalPayment.add(workingPost982DepositTotalPayment)
                                                  .add(workingPost982RedepositTotalPayment)
                                                  .add(workingPre1082RedepositTotalPayment)
                                                  .add(workingPre1082DepositTotalPayment);
            accountTotal = totalOwed.subtract(paymentTotal);
        } catch (Exception exp) {
            LoggingHelper.logException(logger, signature, exp);
            LoggingHelper.logExit(logger, signature, new Object[] {1001});

            return 1001;
        }

        BigDecimal paymentLeft;

        try {
            Long statusCode = thisInvoice.getPayTransStatusCode();
            if (PaymentTransactionCodes.WRITE_OFF_PENDING.getCode() == statusCode) {
                paymentLeft = thisInvoice.getPayTransPaymentAmount().abs();

                if (paymentLeft.compareTo(refundTriggerAmount) > 0) {
                    LoggingHelper.logExit(logger, signature, new Object[] {1003});

                    return 1003;
                }

                if (accountTotal.compareTo(BigDecimal.ZERO) <= 0) {
                    LoggingHelper.logExit(logger, signature, new Object[] {1004});

                    return 1004;
                }

                if (paymentLeft.abs().compareTo(accountTotal.abs()) != 0) {
                    LoggingHelper.logExit(logger, signature, new Object[] {1005});

                    return 1005;
                }
            } else if (PaymentTransactionCodes.CREDIT_BALANCE_REFUND_PENDING.getCode() == statusCode) {
                paymentLeft = thisInvoice.getPayTransPaymentAmount().abs().negate();

                if (accountTotal.compareTo(BigDecimal.ZERO) >= 0) {
                    LoggingHelper.logExit(logger, signature, new Object[] {1006});

                    return 1006;
                }

                if (paymentLeft.abs().compareTo(accountTotal.abs()) != 0) {
                    LoggingHelper.logExit(logger, signature, new Object[] {1007});

                    return 1007;
                }
            } else if (PaymentTransactionCodes.ADJUSTMENT_PENDING.getCode() == statusCode) {
                try {
                    thisInvoice.setPayTransPaymentAmount(paymentTotalOnSpecifiedDate(thisInvoice.getScmClaimNumber(),
                            thisInvoice.getPayTransTransactionDate()));
                    workingFERSTotalPayment = BigDecimal.ZERO;
                    workingPost982DepositTotalPayment = BigDecimal.ZERO;
                    workingPost982RedepositTotalPayment = BigDecimal.ZERO;
                    workingPre1082RedepositTotalPayment = BigDecimal.ZERO;
                    workingPre1082DepositTotalPayment = BigDecimal.ZERO;
                } catch (Exception e) {
                    LoggingHelper.logException(logger, signature, e);
                    LoggingHelper.logExit(logger, signature, new Object[] {1010});

                    return 1010;
                }
            } else if (PaymentTransactionCodes.MANUAL_ADJUSTMENT_CANCELLED_PENDING.getCode() == statusCode) {
                paymentLeft = thisInvoice.getPayTransPaymentAmount().negate();
            } else if (PaymentTransactionCodes.SUSPENSE_DEBIT_VOUCHER_PENDING.getCode() == statusCode
                    || PaymentTransactionCodes.BATCH_AUTO_REFUND_PENDING.getCode() == statusCode) {
                paymentLeft = thisInvoice.getPayTransPaymentAmount().abs().negate();
            } else if (PaymentTransactionCodes.BATCH_AUTO_REFUND_CANCELLED_PENDING.getCode() == statusCode) {
                paymentLeft = thisInvoice.getPayTransPaymentAmount().abs();
            } else {
                LoggingHelper.logExit(logger, signature, new Object[] {1009});

                return 1009;
            }
        } catch (Exception exp) {
            LoggingHelper.logException(logger, signature, exp);
            LoggingHelper.logExit(logger, signature, new Object[] {1002});

            return 1002;
        }

        LoggingHelper.logExit(logger, signature, new Object[] {returnCode});

        return returnCode;
    }

    /**
     * Payment total on specified date.
     *
     * @param claimNumber the claim number
     * @param payTransTransactionDate the pay trans transaction date
     *
     * @return the total payments on the specified date.
     */
    private BigDecimal paymentTotalOnSpecifiedDate(String claimNumber, Date payTransTransactionDate) {
        String signature = "BillProcessorImpl.paymentTotalOnSpecifiedDate";
        LoggingHelper.logEntrance(logger, signature, new String[] {"claimNumber", "payTransTransactionDate"},
            new Object[] {claimNumber, payTransTransactionDate});

        BigDecimal result = BigDecimal.ZERO;
        Calendar cal = Calendar.getInstance();
        cal.setTime(payTransTransactionDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date startDate = cal.getTime();
        cal.add(Calendar.DATE, 1);

        Date endDate = cal.getTime();
        TypedQuery<Payment> query = entityManager.createQuery(GET_PAYMENT_BY_TRANSACTION_DAY, Payment.class);
        query.setParameter("claimNumber", claimNumber);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Payment> payments = query.getResultList();

        for (Payment payment : payments) {
            result = result.add(payment.getAmount());
        }

        LoggingHelper.logExit(logger, signature, new Object[] {result});

        return result;
    }

    /**
     * Insert account note.
     *
     * @param type the type
     * @throws BatchProcessingException if any error
     */
    private void insertAccountNote(AccountNoteType type) throws BatchProcessingException {
        String signature = "BillProcessorImpl.insertAccountNote";
        LoggingHelper.logEntrance(logger, signature, new String[] {"type"}, new Object[] {type});

        String text = accountNoteTexts.get(type);

        AccountNote accountNote = new AccountNote();
        accountNote.setDate(new Date());
        accountNote.setWriter(currentUserName);
        accountNote.setAccountId(thisAccount.getId());
        accountNote.setText(text);
        accountNote.setDeleted(false);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(accountNote);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(
                    logger, signature, new BatchProcessingException("Persistence error occurs", e));
        }
        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Update master record.
     *
     * @param thisInvoice this invoice
     * @return result code, 0 means success
     * @throws BatchProcessingException if any persistence error
     */
    private int updateMasterRecord(InvoiceData thisInvoice) throws BatchProcessingException {
        String signature = "BillProcessorImpl.updateMasterRecord";
        LoggingHelper.logEntrance(logger, signature, new String[] {"thisInvoice"}, new Object[] {thisInvoice});

        int returnCode = -99;

        List<CalculationVersion> versions =
                new ArrayList<CalculationVersion>(thisAccount.getCalculationVersions());

        BigDecimal owedMoney = BigDecimal.ZERO;

        BigDecimal preDeposit = BigDecimal.ZERO;
        BigDecimal postDeposit = BigDecimal.ZERO;
        BigDecimal preRedeposit = BigDecimal.ZERO;
        BigDecimal postRedeposit = BigDecimal.ZERO;

        for (CalculationVersion version : versions) {
            if (version.getCalculationResult().isOfficial()) {
                for (Dedeposit dedeposit : version.getCalculationResult().getDedeposits()) {
                    owedMoney = owedMoney.add(dedeposit.getDeposit());
                    owedMoney = owedMoney.add(dedeposit.getInterest());
                    owedMoney = owedMoney.subtract(dedeposit.getTotal());

                    if (dedeposit.getDepositType().equals(DepositType.CSRS_PRE_10_82_DEPOSIT)) {
                        preDeposit = preDeposit.add(dedeposit.getTotal());
                    }

                    if (dedeposit.getDepositType().equals(DepositType.CSRS_POST_10_82_DEPOSIT)) {
                        postDeposit = postDeposit.add(dedeposit.getTotal());
                    }
                }

                for (Redeposit redeposit : version.getCalculationResult().getRedeposits()) {
                    owedMoney = owedMoney.add(redeposit.getDeposit());
                    owedMoney = owedMoney.add(redeposit.getInterest());
                    owedMoney = owedMoney.subtract(redeposit.getTotal());

                    if (redeposit.getDepositType().equals(DepositType.CSRS_PRE_10_82_DEPOSIT)) {
                        preRedeposit = preRedeposit.add(redeposit.getTotal());
                    }

                    if (redeposit.getDepositType().equals(DepositType.CSRS_POST_10_82_DEPOSIT)) {
                        postRedeposit = postRedeposit.add(redeposit.getTotal());
                    }
                }

                for (CalculationResultItem resultItem : version.getCalculationResult().getItems()) {
                    owedMoney = owedMoney.add(resultItem.getPaymentsApplied());
                }
            }
        }

        if (owedMoney.compareTo(BigDecimal.ZERO) > 0) {
            LoggingHelper.logExit(logger, signature, new Object[]{returnCode});
            return returnCode;
        }

        // It is expected that account entity will be updated properly for the changes mentioned here
        if (preRedeposit.compareTo(BigDecimal.ZERO) > 0) {
            thisAccount.setTotPayr(nullToZero(thisAccount.getTotPayr()).add(thisInvoice.getPayTransPaymentAmount()));
        } else if (postRedeposit.compareTo(BigDecimal.ZERO) > 0) {
            thisAccount.setTotPayvr(nullToZero(thisAccount.getTotPayvr()).add(thisInvoice.getPayTransPaymentAmount()));
        } else if (preDeposit.compareTo(BigDecimal.ZERO) > 0) {
            thisAccount.setTotPayd(nullToZero(thisAccount.getTotPayd()).add(thisInvoice.getPayTransPaymentAmount()));
        } else if (postDeposit.compareTo(BigDecimal.ZERO) > 0) {
            thisAccount.setTotPayn(nullToZero(thisAccount.getTotPayn()).add(thisInvoice.getPayTransPaymentAmount()));
        } else {
            thisAccount.setTotPayfers(nullToZero(thisAccount.getTotPayfers())
                    .add(thisInvoice.getPayTransPaymentAmount()));
        }

        try {
            entityManager.getTransaction().begin();
            StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("BatchApplyOverpayment");
            sp.setParameter("pClaimNumber", thisAccount.getClaimNumber());
            sp.setParameter("pTotPayfers", thisAccount.getTotPayfers());
            sp.setParameter("pTotPayvr", thisAccount.getTotPayvr());
            sp.setParameter("pTotPayn", thisAccount.getTotPayn());
            sp.setParameter("pTotPayr", thisAccount.getTotPayr());
            sp.setParameter("pTotPayd", thisAccount.getTotPayd());
            sp.setParameter("pRefundTriggerAmount", refundTriggerAmount);

            sp.execute();

            returnCode = (Integer) sp.getOutputParameterValue("pReturn");

            claimantOverPaymentAmount = (BigDecimal) sp.getOutputParameterValue("pOverpaymentAmount");

            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            returnCode = 1000;
            LoggingHelper.logException(
                    logger, signature, new BatchProcessingException("Persistence error occurs", e));
        }

        LoggingHelper.logExit(logger, signature, new Object[]{returnCode});
        return returnCode;
    }

    /**
     * Check if the configurable values are injected correctly.
     * @throws BatchProcessingConfigurationException if entityManager is null, accountService is null,
     *  waltRuleService is null, reversePaymentRuleService is null, accountNoteTexts is null or empty or
     *  contains null key or contains null/empty value, refundTriggerAmount is null, or currentUserName is null/empty
     */
    @PostConstruct
    protected void checkInit() {
        checkState(entityManager == null, "entityManager is not set");

        checkState(accountService == null, "accountService is not set");
        checkState(waltRuleService == null, "waltRuleService is not set");
        checkState(reversePaymentRuleService == null, "reversePaymentRuleService is not set");
        checkState((accountNoteTexts == null) || accountNoteTexts.isEmpty(), "accountNoteTexts is not set");
        for (Map.Entry<AccountNoteType, String> entry : accountNoteTexts.entrySet()) {
            checkState(entry.getKey() == null, "element key of accountNoteTexts can not be null");
            checkState(entry.getValue() == null || entry.getValue().trim().isEmpty(),
                    "element value of accountNoteTexts can not be null/empty");
        }
        checkState(refundTriggerAmount == null, "refundTriggerAmount is not set");
        checkState((currentUserName == null) || (currentUserName.trim().length() == 0), "currentUserName is not set");
    }

    /**
     * Check if IAE should be thrown.
     *
     * @param isInvalid the is invalid
     * @param message the message
     *
     * @throws IllegalArgumentException if invalid
     */
    private void checkIAE(boolean isInvalid, String message) {
        if (isInvalid) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Gets the account service.
     *
     * @return the account service
     */
    public AccountService getAccountService() {
        return accountService;
    }

    /**
     * Sets the account service.
     *
     * @param accountService the new account service
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Gets the walt rule service.
     *
     * @return the walt rule service
     */
    @SuppressWarnings("rawtypes")
    public RuleService getWaltRuleService() {
        return waltRuleService;
    }

    /**
     * Sets the walt rule service.
     *
     * @param waltRuleService the new walt rule service
     */
    @SuppressWarnings("rawtypes")
    public void setWaltRuleService(RuleService waltRuleService) {
        this.waltRuleService = waltRuleService;
    }

    /**
     * Gets the reverse payment rule service.
     *
     * @return the reverse payment rule service
     */
    @SuppressWarnings("rawtypes")
    public RuleService getReversePaymentRuleService() {
        return reversePaymentRuleService;
    }

    /**
     * Sets the reverse payment rule service.
     *
     * @param reversePaymentRuleService the new reverse payment rule service
     */
    @SuppressWarnings("rawtypes")
    public void setReversePaymentRuleService(RuleService reversePaymentRuleService) {
        this.reversePaymentRuleService = reversePaymentRuleService;
    }

    /**
     * Gets the account note texts.
     *
     * @return the account note texts
     */
    public Map<AccountNoteType, String> getAccountNoteTexts() {
        return accountNoteTexts;
    }

    /**
     * Sets the account note texts.
     *
     * @param accountNoteTexts the account note texts
     */
    public void setAccountNoteTexts(Map<AccountNoteType, String> accountNoteTexts) {
        this.accountNoteTexts = accountNoteTexts;
    }

    /**
     * Gets the current user name.
     *
     * @return the current user name
     */
    public String getCurrentUserName() {
        return currentUserName;
    }

    /**
     * Sets the current user name.
     *
     * @param currentUserName the new current user name
     */
    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    /**
     * Gets the entity manager.
     *
     * @return the entity manager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Sets the entity manager.
     *
     * @param entityManager the new entity manager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Sets the logger.
     *
     * @param logger the new logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Gets the refund trigger amount.
     *
     * @return the refund trigger amount
     */
    public BigDecimal getRefundTriggerAmount() {
        return refundTriggerAmount;
    }

    /**
     * Sets the refund trigger amount.
     *
     * @param refundTriggerAmount the new refund trigger amount
     */
    public void setRefundTriggerAmount(BigDecimal refundTriggerAmount) {
        this.refundTriggerAmount = refundTriggerAmount;
    }

    /**
     * Gets the bill counter.
     *
     * @return the bill counter
     */
    public Integer getBillCounter() {
        return billCounter;
    }

    /**
     * Sets the bill counter.
     *
     * @param billCounter the new bill counter
     */
    public void setBillCounter(Integer billCounter) {
        this.billCounter = billCounter;
    }

    /**
     * Gets the totals payments counter.
     *
     * @return the totals payments counter
     */
    public Integer getTotalsPaymentsCounter() {
        return totalsPaymentsCounter;
    }

    /**
     * Sets the totals payments counter.
     *
     * @param totalsPaymentsCounter the new totals payments counter
     */
    public void setTotalsPaymentsCounter(Integer totalsPaymentsCounter) {
        this.totalsPaymentsCounter = totalsPaymentsCounter;
    }

    /**
     * Gets the totals payments sum.
     *
     * @return the totals payments sum
     */
    public BigDecimal getTotalsPaymentsSum() {
        return totalsPaymentsSum;
    }

    /**
     * Sets the totals payments sum.
     *
     * @param totalsPaymentsSum the new totals payments sum
     */
    public void setTotalsPaymentsSum(BigDecimal totalsPaymentsSum) {
        this.totalsPaymentsSum = totalsPaymentsSum;
    }

    /**
     * Gets the last payments csd.
     *
     * @return the last payments csd
     */
    public String getLastPaymentsCSD() {
        return lastPaymentsCSD;
    }

    /**
     * Sets the last payments csd.
     *
     * @param lastPaymentsCSD the new last payments csd
     */
    public void setLastPaymentsCSD(String lastPaymentsCSD) {
        this.lastPaymentsCSD = lastPaymentsCSD;
    }

    /**
     * Gets the process this payment.
     *
     * @return the process this payment
     */
    public Boolean getProcessThisPayment() {
        return processThisPayment;
    }

    /**
     * Sets the process this payment.
     *
     * @param processThisPayment the new process this payment
     */
    public void setProcessThisPayment(Boolean processThisPayment) {
        this.processThisPayment = processThisPayment;
    }

    /**
     * Gets the multi record print invoice.
     *
     * @return the multi record print invoice
     */
    public Boolean getMultiRecordPrintInvoice() {
        return multiRecordPrintInvoice;
    }

    /**
     * Sets the multi record print invoice.
     *
     * @param multiRecordPrintInvoice the new multi record print invoice
     */
    public void setMultiRecordPrintInvoice(Boolean multiRecordPrintInvoice) {
        this.multiRecordPrintInvoice = multiRecordPrintInvoice;
    }

    /**
     * Gets the multi record account status.
     *
     * @return the multi record account status
     */
    public Long getMultiRecordAccountStatus() {
        return multiRecordAccountStatus;
    }

    /**
     * Sets the multi record account status.
     *
     * @param multiRecordAccountStatus the new multi record account status
     */
    public void setMultiRecordAccountStatus(Long multiRecordAccountStatus) {
        this.multiRecordAccountStatus = multiRecordAccountStatus;
    }

    /**
     * Gets the multi record refund required.
     *
     * @return the multi record refund required
     */
    public Boolean getMultiRecordRefundRequired() {
        return multiRecordRefundRequired;
    }

    /**
     * Sets the multi record refund required.
     *
     * @param multiRecordRefundRequired the new multi record refund required
     */
    public void setMultiRecordRefundRequired(Boolean multiRecordRefundRequired) {
        this.multiRecordRefundRequired = multiRecordRefundRequired;
    }

    /**
     * Gets the multi record ach stop letter.
     *
     * @return the multi record ach stop letter
     */
    public Boolean getMultiRecordACHStopLetter() {
        return multiRecordACHStopLetter;
    }

    /**
     * Sets the multi record ach stop letter.
     *
     * @param multiRecordACHStopLetter the new multi record ach stop letter
     */
    public void setMultiRecordACHStopLetter(Boolean multiRecordACHStopLetter) {
        this.multiRecordACHStopLetter = multiRecordACHStopLetter;
    }

    /**
     * Gets the multi record update to completed.
     *
     * @return the multi record update to completed
     */
    public Boolean getMultiRecordUpdateToCompleted() {
        return multiRecordUpdateToCompleted;
    }

    /**
     * Sets the multi record update to completed.
     *
     * @param multiRecordUpdateToCompleted the new multi record update to completed
     */
    public void setMultiRecordUpdateToCompleted(Boolean multiRecordUpdateToCompleted) {
        this.multiRecordUpdateToCompleted = multiRecordUpdateToCompleted;
    }

    /**
     * Gets the multi record over payment amount.
     *
     * @return the multi record over payment amount
     */
    public BigDecimal getMultiRecordOverPaymentAmount() {
        return multiRecordOverPaymentAmount;
    }

    /**
     * Sets the multi record over payment amount.
     *
     * @param multiRecordOverPaymentAmount the new multi record over payment amount
     */
    public void setMultiRecordOverPaymentAmount(BigDecimal multiRecordOverPaymentAmount) {
        this.multiRecordOverPaymentAmount = multiRecordOverPaymentAmount;
    }

    /**
     * Gets the sb.
     *
     * @return the sb
     */
    public StringBuilder getSb() {
        return sb;
    }

    /**
     * Sets the sb.
     *
     * @param sb the new sb
     */
    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    /**
     * Gets the reversal total.
     *
     * @return the reversal total
     */
    public BigDecimal getReversalTotal() {
        return reversalTotal;
    }

    /**
     * Sets the reversal total.
     *
     * @param reversalTotal the new reversal total
     */
    public void setReversalTotal(BigDecimal reversalTotal) {
        this.reversalTotal = reversalTotal;
    }

    /**
     * Gets the payment count.
     *
     * @return the payment count
     */
    public Integer getPaymentCount() {
        return paymentCount;
    }

    /**
     * Sets the payment count.
     *
     * @param paymentCount the new payment count
     */
    public void setPaymentCount(Integer paymentCount) {
        this.paymentCount = paymentCount;
    }

    /**
     * Gets the payment total.
     *
     * @return the payment total
     */
    public BigDecimal getPaymentTotal() {
        return paymentTotal;
    }

    /**
     * Sets the payment total.
     *
     * @param paymentTotal the new payment total
     */
    public void setPaymentTotal(BigDecimal paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    /**
     * Gets the account total.
     *
     * @return the account total
     */
    public BigDecimal getAccountTotal() {
        return accountTotal;
    }

    /**
     * Sets the account total.
     *
     * @param accountTotal the new account total
     */
    public void setAccountTotal(BigDecimal accountTotal) {
        this.accountTotal = accountTotal;
    }

    /**
     * Gets the this account.
     *
     * @return the this account
     */
    public Account getThisAccount() {
        return thisAccount;
    }

    /**
     * Sets the this account.
     *
     * @param thisAccount the new this account
     */
    public void setThisAccount(Account thisAccount) {
        this.thisAccount = thisAccount;
    }

    /**
     * Gets the error count.
     *
     * @return the error count
     */
    public Integer getErrorCount() {
        return errorCount;
    }

    /**
     * Sets the error count.
     *
     * @param errorCount the new error count
     */
    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * Gets the error found.
     *
     * @return the error found
     */
    public Boolean getErrorFound() {
        return errorFound;
    }

    /**
     * Sets the error found.
     *
     * @param errorFound the new error found
     */
    public void setErrorFound(Boolean errorFound) {
        this.errorFound = errorFound;
    }

    /**
     * Gets the claimant over payment amount.
     *
     * @return the claimant over payment amount
     */
    public BigDecimal getClaimantOverPaymentAmount() {
        return claimantOverPaymentAmount;
    }

    /**
     * Sets the claimant over payment amount.
     *
     * @param claimantOverPaymentAmount the new claimant over payment amount
     */
    public void setClaimantOverPaymentAmount(BigDecimal claimantOverPaymentAmount) {
        this.claimantOverPaymentAmount = claimantOverPaymentAmount;
    }

    /**
     * Getter method for property <tt>thisInvoice</tt>.
     * @return property value of thisInvoice
     */
    public InvoiceData getThisInvoice() {
        return thisInvoice;
    }

    /**
     * Setter method for property <tt>thisInvoice</tt>.
     * @param thisInvoice value to be assigned to property thisInvoice
     */
    public void setThisInvoice(InvoiceData thisInvoice) {
        this.thisInvoice = thisInvoice;
    }
}
