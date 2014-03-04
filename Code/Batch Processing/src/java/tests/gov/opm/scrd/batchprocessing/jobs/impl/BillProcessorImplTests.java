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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountNote;
import gov.opm.scrd.entities.application.AuditBatch;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.application.Redeposit;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.batchprocessing.PaymentTransactionCodes;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.FormType;
import gov.opm.scrd.entities.lookup.PayCode;
import gov.opm.scrd.entities.lookup.Suffix;
import gov.opm.scrd.mock.MockReversePaymentRuleService;
import gov.opm.scrd.mock.MockWaltRuleService;

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
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * <p>Unit tests for {@link BillProcessorImpl} class.</p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class BillProcessorImplTests extends BasePersistenceTests {

    /** Represents the date format. */
    private static final DateFormat DF = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * <p>Represents the entity manager used in tests.</p>
     */
    private static EntityManager entityManager;

    /**
     * <p>Represents the audit batch id for tests.</p>
     */
    private long auditBatchId;

    /**
     * <p>Represents the account id for tests.</p>
     */
    private long accountId;

    /**
     * <p>Represents the BillProcessorImpl instance for tests.</p>
     */
    private BillProcessorImpl service;

    /**
     * <p>Represents the Spring context.</p>
     */
    private ConfigurableApplicationContext context;

    /**
     * <p>Adapter for earlier versions of JUnit.</p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BillProcessorImplTests.class);
    }

    /**
     * Create audit batch.
     */
    private void createAuditBatch() {

        AuditBatch ab = new AuditBatch();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        ab.setEventYear(year);
        ab.setEventMonth(month);
        ab.setEventDay(day);
        ab.setUserKey(1L);
        ab.setDeleted(false);
        ab.setBatchTime(new Date());
        ab.setAmountImported(new BigDecimal(0));
        ab.setAmountProcessed(new BigDecimal(0));
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
        auditBatchId = create(ab);
    }

    /**
     * Create account.
     *
     * @throws Exception to JUnit.
     */
    private void createAccount() throws Exception {

        Account account = getAccount();
        account.setClaimNumber("9000010");
        account.setStatus(entityManager.find(AccountStatus.class, 1L));
        account.setPayCode(entityManager.find(PayCode.class, 1L));
        account.setFormType(entityManager.find(FormType.class, 1L));

        

        account.getHolder().setBirthDate(DF.parse("05/10/1983"));
        account.getHolder().setMiddleInitial("I");
        account.getHolder().setSuffix(entityManager.find(Suffix.class, 1L));
        update(account.getHolder());

        accountId = create(account);
        account.setCalculationVersions(Arrays.asList(
                new CalculationVersion[] {getCalculationVersion(), getCalculationVersion()}));
        account.getCalculationVersions().get(0).setAccountId(accountId);
        account.getCalculationVersions().get(1).setAccountId(accountId);
        update(account);
    }

    /**
     * <p>Sets up the unit tests.</p>
     *
     * @throws Exception to JUnit.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        initDB();

        entityManager = getEntityManager();

        createAuditBatch();

        createAccount();

        context = new ClassPathXmlApplicationContext("billProcessor_test.xml");
        service = (BillProcessorImpl) context.getBean("billProcessor");

        service.setAccountTotal(new BigDecimal(1000));
    }

    /**
     * <p>Cleans up the unit tests.</p>
     *
     * @throws Exception to JUnit.
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();

        if (context != null) {
            context.close();
        }
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 90: Verify calculation logic is correct for ProcessBills when PayTransStatusCode is
     * PostedPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case90() throws Exception {
        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        assertEquals("bill should be processed", 1, service.getBillCounter().intValue());
        assertTrue("bill should be processed", BigDecimal.ZERO.compareTo(service.getClaimantOverPaymentAmount()) == 0);
        assertEquals("bill should be processed", 0, service.getErrorCount().intValue());
        assertFalse("bill should be processed", service.getErrorFound());
        assertEquals("bill should be processed", "9000010", service.getLastPaymentsCSD());
        assertEquals("bill should be processed", 1, service.getMultiRecordAccountStatus().intValue());

        AuditBatch audit = entityManager.find(AuditBatch.class, auditBatchId);
        assertTrue("audit batch should be updated", audit.getAmountProcessed().compareTo(new BigDecimal("100")) == 0);
        assertEquals("audit batch should be updated", 0, audit.getErrorCountProcessing().intValue());
        assertFalse("audit batch should be updated", audit.getErrorProcessing());
        assertEquals("audit batch should be updated", 1, audit.getPaymentsProcessed().intValue());

        InvoiceData processed = service.getThisInvoice();
        assertTrue("bill should be processed", processed.getAccountBalance().compareTo(new BigDecimal("800")) == 0);
        assertTrue("bill should be processed", processed.getTodaysPaymentTotal().compareTo(new BigDecimal("100")) == 0);
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 91: Verify ProcessBills method  will not allow to process the bill if the  bill has
     * been already processed today.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case91() throws Exception {
        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        createData(data);

        InvoiceData data1 = getInvoiceData();
        data1.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        createData(data1);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);
        assertFalse("bill should not be processed", service.getProcessThisPayment());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 92: Verify ProcessBills method  will not calculate bill if the account is not active
     * and not owes money.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case92() throws Exception {
        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        data.setAccountStatus(2L); // Account not active
        data.setAccountBalance(new BigDecimal(-100)); // Account paid off
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);
        assertFalse("bill should not be processed", service.getProcessThisPayment());

        InvoiceData processed = service.getThisInvoice();
        assertNull("There should be no bill calculated", processed);
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 92: Verify ProcessBills method  will not calculate bill if the account is not active
     * and not owes money.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case922() throws Exception {
        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        data.setPayTransPaymentAmount(new BigDecimal(10));
        data.setAccountBalance(BigDecimal.ZERO);
        createData(data);

        Redeposit redeposit = entityManager.find(Account.class, accountId).getCalculationVersions()
            .get(0).getCalculationResult().getRedeposits().get(0);
        redeposit.setTotal(new BigDecimal(10));
        super.update(redeposit);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);
        assertFalse("bill should not be processed", service.getProcessThisPayment());

        InvoiceData processed = service.getThisInvoice();
        assertTrue("Overpay amount should be set", processed.getOverPaymentAmount().compareTo(new BigDecimal(-7)) == 0);
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 93: Verify the process when Processes ReturnCode is  PaymentSuccess.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case93() throws Exception {
        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        data.setPayTransPaymentAmount(new BigDecimal(1.5));
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        TypedQuery<AccountNote> query =
                entityManager.createQuery("select e from AccountNote e where e.accountId = :accountId",
                AccountNote.class);
        query.setParameter("accountId", accountId);

        List<AccountNote> result = query.getResultList();
        assertFalse("account note must be created", result.isEmpty());
        assertEquals("account note is wrong", "stop ach letter", result.get(0).getText());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 94: Verify the process when Processes ReturnCode is PaymentRequiresRefund.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case94() throws Exception {
        // mock setting the response code to 1.
        service.setWaltRuleService(new MockWaltRuleService(1));

        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        TypedQuery<AccountNote> query =
                entityManager.createQuery("select e from AccountNote e where e.accountId = :accountId",
                AccountNote.class);
        query.setParameter("accountId", accountId);

        List<AccountNote> result = query.getResultList();
        assertFalse("account note must be created", result.isEmpty());
        assertEquals("account note is wrong", "credit balance", result.get(0).getText());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 95: Verify the process when Processes ReturnCode is PaymentFailure.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case95() throws Exception {
        // mock setting the response code to 3.
        service.setWaltRuleService(new MockWaltRuleService(3));

        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        assertTrue("Failure occurs, error should be found", service.getErrorFound());
        assertEquals("Failure occurs, error should be found", 1, service.getErrorCount().intValue());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 96: Verify the process when Processes ReturnCode is PaymentBeforeInitialBill.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case96() throws Exception {
        // mock setting the response code to 4.
        service.setWaltRuleService(new MockWaltRuleService(4));

        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        assertTrue("Failure occurs, error should be found", service.getErrorFound());
        assertEquals("Failure occurs, error should be found", 1, service.getErrorCount().intValue());

        TypedQuery<AccountNote> query =
                entityManager.createQuery("select e from AccountNote e where e.accountId = :accountId",
                AccountNote.class);
        query.setParameter("accountId", accountId);

        List<AccountNote> result = query.getResultList();
        assertFalse("account note must be created", result.isEmpty());
        assertEquals("account note is wrong", "payment before initial bill", result.get(0).getText());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 97: Verify ProcessBills method can set information about completed multi records to
     * the current payment record when PayTransStatusCode is PostedPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case97() throws Exception {
        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.POSTED_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertTrue("bill should be processed", processed.getPrintInvoice());
        assertEquals("bill should be processed", 1, processed.getAccountStatus().intValue());
        assertFalse("bill should be processed", processed.getRefundRequired());
        assertFalse("bill should be processed", processed.getAchStopLetter());
        assertTrue("bill should be processed", processed.getUpdateToCompleted());
        assertTrue("bill should be processed", processed.getOverPaymentAmount().compareTo(BigDecimal.ZERO) == 0);
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 98: Verify ProcessBills method can process when reverse the payment is successes (
     * PayTransStatusCode is ReversedPending).</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case98() throws Exception {
        service.setReversePaymentRuleService(new MockReversePaymentRuleService(true));

        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.REVERSE_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertTrue("reversal bill should be processed", processed.getReversedPayment());
        assertTrue("reversal bill should be processed", processed.getUpdateToCompleted());
        assertTrue("reversal bill should be processed", service.getReversalTotal().compareTo(new BigDecimal(100)) == 0);
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 99: Verify ProcessBills method can process when reverse the payment is failed (
     * PayTransStatusCode is ReversedPending).</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case99() throws Exception {
        service.setReversePaymentRuleService(new MockReversePaymentRuleService(false));

        InvoiceData data = getInvoiceData();
        data.setPayTransStatusCode(PaymentTransactionCodes.REVERSE_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertFalse("reversal bill should be failed", processed.getPrintInvoice());
        assertFalse("reversal bill should be failed", processed.getRefundRequired());
        assertTrue("reversal bill should be failed", service.getErrorFound());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 100: Verify ProcessBills method can process when PayTransStatusCode is
     * SuspenseRefundPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case100() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to SuspenseRefundPending
        data.setPayTransStatusCode(PaymentTransactionCodes.SUSPENSE_REFUND_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertTrue("bill should be processed", processed.getRefundRequired());
        assertTrue("bill should be processed", processed.getUpdateToCompleted());
        assertTrue("bill should be processed", processed.getOverPaymentAmount().compareTo(new BigDecimal(100)) == 0);
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 101: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: CreditBalanceRefundPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case101a() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to CreditBalanceRefundPending
        data.setPayTransStatusCode(PaymentTransactionCodes.CREDIT_BALANCE_REFUND_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertFalse("bill should be processed", processed.getPrintInvoice());
        assertTrue("bill should be processed", processed.getOverPaymentAmount().compareTo(new BigDecimal(0)) == 0);
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 101: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: WriteOffPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case101b() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to WriteOffPending
        data.setPayTransStatusCode(PaymentTransactionCodes.WRITE_OFF_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertFalse("bill should be processed", processed.getPrintInvoice());
        assertTrue("bill should be processed", processed.getOverPaymentAmount().compareTo(new BigDecimal(0)) == 0);
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 101: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: AdjustmentPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case101c() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to AdjustmentPending
        data.setPayTransStatusCode(PaymentTransactionCodes.ADJUSTMENT_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertFalse("bill should be processed", processed.getPrintInvoice());
        assertTrue("bill should be processed", processed.getOverPaymentAmount().compareTo(new BigDecimal(0)) == 0);
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 101: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: BatchAutoRefundPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case101d() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to BatchAutoRefundPending
        data.setPayTransStatusCode(PaymentTransactionCodes.BATCH_AUTO_REFUND_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);
        assertTrue("bill should be processed", service.getPaymentTotal().compareTo(new BigDecimal(8)) == 0);

        InvoiceData processed = service.getThisInvoice();
        assertFalse("bill should be processed", processed.getPrintInitialBill());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 101: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: ManualAdjustmentCancelledPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case101e() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to ManualAdjustmentCancelledPending
        data.setPayTransStatusCode(PaymentTransactionCodes.MANUAL_ADJUSTMENT_CANCELLED_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);
        assertTrue("bill should be processed", service.getPaymentTotal().compareTo(new BigDecimal(8)) == 0);

        InvoiceData processed = service.getThisInvoice();
        assertFalse("bill should be processed", processed.getPrintInitialBill());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 101: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: BatchAutoRefundCanceledPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case101f() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to BatchAutoRefundCanceledPending
        data.setPayTransStatusCode(PaymentTransactionCodes.BATCH_AUTO_REFUND_CANCELLED_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);
        assertTrue("bill should be processed", service.getPaymentTotal().compareTo(new BigDecimal(8)) == 0);

        InvoiceData processed = service.getThisInvoice();
        assertFalse("bill should be processed", processed.getPrintInitialBill());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 101: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: SuspenseDebitVoucherPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case101g() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to SuspenseDebitVoucherPending
        data.setPayTransStatusCode(PaymentTransactionCodes.SUSPENSE_DEBIT_VOUCHER_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);
        assertTrue("bill should be processed", service.getPaymentTotal().compareTo(new BigDecimal(8)) == 0);

        InvoiceData processed = service.getThisInvoice();
        assertFalse("bill should be processed", processed.getPrintInitialBill());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 102: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: AnnuityPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case102a() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to AnnuityPending
        data.setPayTransStatusCode(PaymentTransactionCodes.ANNUITY_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertTrue("bill should be processed", processed.getUpdateToCompleted());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 102: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: HealthBenefitsPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case102b() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to HealthBenefitsPending
        data.setPayTransStatusCode(PaymentTransactionCodes.HEALTH_BENEFITS_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertTrue("bill should be processed", processed.getUpdateToCompleted());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 102: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: VoluntaryContributionsPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case102c() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to VoluntaryContributionsPending
        data.setPayTransStatusCode(PaymentTransactionCodes.VOLUNTARY_CONTRIBUTIONS_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertTrue("bill should be processed", processed.getUpdateToCompleted());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 102: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: DirectPayLifePending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case102d() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to DirectPayLifePending
        data.setPayTransStatusCode(PaymentTransactionCodes.DIRECT_PAY_LIFE_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertTrue("bill should be processed", processed.getUpdateToCompleted());
    }

    /**
     * <p>Accuracy test for the method <code>processBills()</code>.<br>
     * Corresponding to scenario 102: Verify ProcessBills method can process when PayTransStatusCode is following
     * status: DebitVoucherPending.</p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_processBills_case102e() throws Exception {
        InvoiceData data = getInvoiceData();
        // set transaction status code to DebitVoucherPending
        data.setPayTransStatusCode(PaymentTransactionCodes.DEBIT_VOUCHER_PENDING.getCode());
        createData(data);

        StringBuilder procMessage = new StringBuilder("test message");
        service.processBills(auditBatchId, procMessage, 2, 3, 3, 2, 2);

        InvoiceData processed = service.getThisInvoice();
        assertTrue("bill should be processed", processed.getUpdateToCompleted());
    }

    /**
     * Get a sample Invoice data for testing purpose.
     *
     * @return the invoice data.
     *
     * @throws Exception if any error
     */
    private InvoiceData getInvoiceData() throws Exception {
        InvoiceData entity = new InvoiceData();
        entity.setAccountBalance(new BigDecimal(800));
        entity.setAccountStatus(1L);
        entity.setAchPayment(true);
        entity.setAchStopLetter(false);
        entity.setPayTransPaymentAmount(new BigDecimal(100));
        entity.setPayTransTransactionDate(DF.parse("05/10/2013"));
        entity.setPrintInitialBill(false);
        entity.setRefundRequired(true);
        entity.setReversedPayment(true);
        entity.setScmClaimNumber("9000010");
        entity.setScmDateOfBirth(DF.parse("05/10/1983"));
        entity.setScmName("scm name");
        entity.setUpdateToCompleted(true);

        return entity;
    }

    private void createData(InvoiceData data) {
        PaymentTransaction pt = new PaymentTransaction();
        pt.setAchPayment(data.getAchPayment());
        pt.setPayTransStatusCode(data.getPayTransStatusCode());
        pt.setScmClaimNumber(data.getScmClaimNumber());
        pt.setScmDateOfBirth(data.getScmDateOfBirth());
        pt.setPayTransPaymentAmount(data.getPayTransPaymentAmount());
        pt.setPayTransTransactionDate(data.getPayTransTransactionDate());
        pt.setPayTransStatusDate(new Date());

        super.create(pt);

        Account account = entityManager.find(Account.class, accountId);
        account.setStatus(entityManager.find(AccountStatus.class, data.getAccountStatus()));
        account.setTotalDeposit(BigDecimal.ZERO);
        account.setTotalRedeposit(BigDecimal.ZERO);
        account.setTotalNonDeposit(BigDecimal.ZERO);
        account.setTotalVarRedeposit(BigDecimal.ZERO);
        account.setTotalFersW(data.getAccountBalance());
        super.update(account);
    }
}
