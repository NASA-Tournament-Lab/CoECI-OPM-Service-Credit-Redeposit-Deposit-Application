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

package gov.opm.scrd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.entities.application.A01PrintSuppressionCases;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountConfirmationValidation;
import gov.opm.scrd.entities.application.AccountConfirmationValidationEntry;
import gov.opm.scrd.entities.application.AccountHolder;
import gov.opm.scrd.entities.application.AccountNote;
import gov.opm.scrd.entities.application.Address;
import gov.opm.scrd.entities.application.AdjustmentTransaction;
import gov.opm.scrd.entities.application.AnnuitantList;
import gov.opm.scrd.entities.application.AuditBatch;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.application.BatchDailyPayments;
import gov.opm.scrd.entities.application.Billing;
import gov.opm.scrd.entities.application.BillingSummary;
import gov.opm.scrd.entities.application.Calculation;
import gov.opm.scrd.entities.application.CalculationResult;
import gov.opm.scrd.entities.application.CalculationResultItem;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.application.ClaimWithoutService;
import gov.opm.scrd.entities.application.ContactInfo;
import gov.opm.scrd.entities.application.Dedeposit;
import gov.opm.scrd.entities.application.DeductionRate;
import gov.opm.scrd.entities.application.Error;
import gov.opm.scrd.entities.application.GLCode;
import gov.opm.scrd.entities.application.GLPaymentType;
import gov.opm.scrd.entities.application.HelpItem;
import gov.opm.scrd.entities.application.Holiday;
import gov.opm.scrd.entities.application.Info;
import gov.opm.scrd.entities.application.InterestGracePeriod;
import gov.opm.scrd.entities.application.InterestRate;
import gov.opm.scrd.entities.application.InterestSuppression;
import gov.opm.scrd.entities.application.Invoice;
import gov.opm.scrd.entities.application.NewClaimNumber;
import gov.opm.scrd.entities.application.Notification;
import gov.opm.scrd.entities.application.PayTransStatusCode;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.PaymentInterestDetail;
import gov.opm.scrd.entities.application.PaymentMoveTransaction;
import gov.opm.scrd.entities.application.PaymentRefundLink;
import gov.opm.scrd.entities.application.PaymentReverse;
import gov.opm.scrd.entities.application.PaymentTransactionNote;
import gov.opm.scrd.entities.application.PaymentsAppliedOrderCode;
import gov.opm.scrd.entities.application.Printout;
import gov.opm.scrd.entities.application.Redeposit;
import gov.opm.scrd.entities.application.RefundTransaction;
import gov.opm.scrd.entities.application.RolePermission;
import gov.opm.scrd.entities.application.SCMFirstInsert;
import gov.opm.scrd.entities.application.ServiceCreditPreference;
import gov.opm.scrd.entities.application.SummaryData;
import gov.opm.scrd.entities.application.TimeFactor;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.application.UserAccountAssignment;
import gov.opm.scrd.entities.application.UserPermission;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.AgencyCode;
import gov.opm.scrd.entities.lookup.ApplicationDesignation;
import gov.opm.scrd.entities.lookup.AppointmentType;
import gov.opm.scrd.entities.lookup.CalculationStatus;
import gov.opm.scrd.entities.lookup.ChangeTransFieldNumberCode;
import gov.opm.scrd.entities.lookup.ClaimOfficer;
import gov.opm.scrd.entities.lookup.Country;
import gov.opm.scrd.entities.lookup.FormType;
import gov.opm.scrd.entities.lookup.PayCode;
import gov.opm.scrd.entities.lookup.PayType;
import gov.opm.scrd.entities.lookup.PaymentAppliedOrder;
import gov.opm.scrd.entities.lookup.PaymentReversalReason;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.Role;
import gov.opm.scrd.entities.lookup.ServiceType;
import gov.opm.scrd.entities.lookup.State;
import gov.opm.scrd.entities.lookup.Suffix;
import gov.opm.scrd.entities.lookup.TransferType;
import gov.opm.scrd.entities.lookup.UserStatus;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Persistence tests for entities.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Services - Account and Payment Services Assembly 1.0):</em>
 * <ol>
 * <li>Changed to extend BasePersistenceTests.</li>
 * <li>Updated test cases for Account and Payment.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Updated test cases: test_PeriodType, test_State, test_AccountStatus, test_RetirementType, test_AppointmentType,
 * test_ServiceType, test_Role, test_PayType, test_RefundTransaction, test_CalculationVersion, test_AccountNote,
 * test_AccountHolder, test_Account, test_Payment</li>
 * <li>Added test cases: test_AgencyCode, test_PayCode, test_PaymentAppliedOrder, test_ChangeTransFieldNumberCode,
 * test_PayTransStatusCode, test_PaymentsAppliedOrderCode, test_PaymentRefundLink, test_UserAccountAssignment,
 * test_A01PrintSuppressionCases, test_Invoice, test_AdjustmentTransaction, test_PaymentInterestDetail,
 * test_InterestRate, test_PaymentMoveTransaction, test_PaymentTransactionNote, test_ClaimWithoutService, test_GLCode,
 * test_InterestGracePeriod, test_DeductionRate, test_Holiday, test_SCMFirstInsert, test_GLPaymentType,
 * test_InterestSuppression, test_ContactInfo, test_TimeFactor, test_AnnuitantList, test_NewClaimNumber,
 * test_AuditBatch, test_BatchDailyPayments</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.3 (OPM - SCRD - Frontend Account Module Assembly 1.0):</em>
 * <ul>
 * <li>Updated test cases: test_CalculationVersion, when test data entry of history items, the table name are not
 * correct.</li>
 * </ul>
 * </p>
 *
 * @author sparemax, TCSASSEMBLER
 * @version 1.3
 */
public class PersistenceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the schema.
     * </p>
     */
    private static final String SCHEMA = "opm.";

    /**
     * <p>
     * Represents the 'insert' action.
     * </p>
     */
    private static final String ACTION_I = "I";

    /**
     * <p>
     * Represents the 'update' action.
     * </p>
     */
    private static final String ACTION_U = "U";

    /**
     * <p>
     * Represents the 'delete' action.
     * </p>
     */
    private static final String ACTION_D = "D";

    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PersistenceTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        entityManager = getEntityManager();
    }

    /**
     * <p>
     * Accuracy test for the class <code>Payment</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_Payment() {
        Account account = getAccount();
        create(account);

        Payment entity = getPayment();
        entity.setAccountId(account.getId());

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("payment_history", "id", id, ACTION_I);

        // Retrieve
        Payment res =
            (Payment) entityManager.find(Payment.class, id);

        assertEquals("The result should be correct.",
            entity.getGovRefund(), res.getGovRefund());
        assertEquals("The result should be correct.",
            entity.getDisapprove(), res.getDisapprove());
        assertEquals("The result should be correct.",
            entity.getHistoryPayment(), res.getHistoryPayment());
        assertEquals("The result should be correct.",
            entity.getResolvedSuspense(), res.getResolvedSuspense());
        assertEquals("The result should be correct.",
            entity.getUserInserted(), res.getUserInserted());
        assertEquals("The result should be correct.",
            entity.getPostFlag(), res.getPostFlag());
        assertEquals("The result should be correct.",
            entity.getOrderCode().getId(), res.getOrderCode().getId());
        assertEquals("The result should be correct.",
            entity.getStatusCode().getId(), res.getStatusCode().getId());

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getBatchNumber(), res.getBatchNumber());
        assertEquals("The result should be correct.", entity.getBlockNumber(), res.getBlockNumber());
        assertEquals("The result should be correct.", entity.getSequenceNumber(), res.getSequenceNumber());
        assertEquals("The result should be correct.",
            entity.getPaymentStatus().getId(), res.getPaymentStatus().getId());
        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertNotNull("The result should be correct.", res.getAccountHolderBirthdate());
        assertNotNull("The result should be correct.", res.getDepositDate());
        assertEquals("The result should be correct.", entity.getAmount().intValue(), res.getAmount().intValue());
        assertEquals("The result should be correct.", entity.getSsn(), res.getSsn());
        assertEquals("The result should be correct.", entity.getClaimant(), res.getClaimant());
        assertNotNull("The result should be correct.", res.getClaimantBirthdate());
        assertEquals("The result should be correct.", entity.getImportId(), res.getImportId());
        assertEquals("The result should be correct.", entity.getSequence(), res.getSequence());
        assertNotNull("The result should be correct.", res.getTransactionDate());
        assertNotNull("The result should be correct.", res.getStatusDate());
        assertEquals("The result should be correct.", entity.getApplyTo().getId(), res.getApplyTo().getId());
        assertEquals("The result should be correct.", entity.isApplyToGL(), res.isApplyToGL());
        assertEquals("The result should be correct.", entity.getNote(), res.getNote());
        assertEquals("The result should be correct.", entity.getTransactionKey(), res.getTransactionKey());
        assertEquals("The result should be correct.", entity.isAch(), res.isAch());
        assertEquals("The result should be correct.",
            entity.getAccountBalance().intValue(), res.getAccountBalance().intValue());
        assertEquals("The result should be correct.",
            entity.getAccountStatus().getId(), res.getAccountStatus().getId());
        assertEquals("The result should be correct.", entity.getMasterClaimNumber(), res.getMasterClaimNumber());
        assertNotNull("The result should be correct.", res.getMasterClaimantBirthdate());
        assertEquals("The result should be correct.",
            entity.getMasterAccountStatus().getId(), res.getMasterAccountStatus().getId());
        assertEquals("The result should be correct.",
            entity.getMasterAccountBalance().intValue(), res.getMasterAccountBalance().intValue());
        assertEquals("The result should be correct.", entity.getMasterAccountId(), res.getMasterAccountId());
        assertEquals("The result should be correct.",
            entity.getPreDepositAmount().intValue(), res.getPreDepositAmount().intValue());
        assertEquals("The result should be correct.",
            entity.getPreRedepositAmount().intValue(), res.getPreRedepositAmount().intValue());
        assertEquals("The result should be correct.",
            entity.getPostDepositAmount().intValue(), res.getPostDepositAmount().intValue());
        assertEquals("The result should be correct.",
            entity.getPostRedepositAmount().intValue(), res.getPostRedepositAmount().intValue());
        assertEquals("The result should be correct.", entity.getApprovalUser(), res.getApprovalUser());
        assertEquals("The result should be correct.",
            entity.getApprovalStatus(), res.getApprovalStatus());
        assertEquals("The result should be correct.",
            entity.getApprovalReason(), res.getApprovalReason());
        assertEquals("The result should be correct.", entity.getPaymentType(), res.getPaymentType());
        assertEquals("The result should be correct.", entity.getAccountId(), res.getAccountId());

        entity.setBatchNumber("new");

        // Update
        update(entity);

        res = (Payment) entityManager.find(Payment.class, id);

        assertEquals("The result should be correct.",
            entity.getBatchNumber(), res.getBatchNumber());

        checkHistory("payment_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (Payment) entityManager.find(Payment.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("payment_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AccountConfirmationValidation</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_AccountConfirmationValidation() {
        Account account = getAccount();
        create(account);

        AccountConfirmationValidation entity = getAccountConfirmationValidation();
        entity.setAccountId(account.getId());

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("account_confirmation_validation_history", "id", id, ACTION_I);

        checkHistory("account_confirmation_validation_entry_history", "id", entity.getEntries().get(0).getId(),
            ACTION_I);

        // Retrieve
        AccountConfirmationValidation res =
            (AccountConfirmationValidation) entityManager.find(AccountConfirmationValidation.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getAccountId(), res.getAccountId());
        assertEquals("The result should be correct.", entity.getDataCheckStatus(), res.getDataCheckStatus());
        assertEquals("The result should be correct.",
            entity.getDataCheckStatusValidator(), res.getDataCheckStatusValidator());
        assertEquals("The result should be correct.",
            entity.getDataCheckStatusReason(), res.getDataCheckStatusReason());
        assertEquals("The result should be correct.", entity.getEntries().size(), res.getEntries().size());

        AccountConfirmationValidationEntry entry = entity.getEntries().get(0);
        AccountConfirmationValidationEntry retrievedEntry = res.getEntries().get(0);
        assertTrue("The result should be correct.", retrievedEntry.getId() > 0);
        assertEquals("The result should be correct.", entry.getFieldName(), retrievedEntry.getFieldName());
        assertEquals("The result should be correct.", entry.getValid(), retrievedEntry.getValid());

        entity.setDataCheckStatusValidator("new");

        // Update
        update(entity);

        res = (AccountConfirmationValidation) entityManager.find(AccountConfirmationValidation.class, id);

        assertEquals("The result should be correct.",
            entity.getDataCheckStatusValidator(), res.getDataCheckStatusValidator());

        checkHistory("account_confirmation_validation_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (AccountConfirmationValidation) entityManager.find(AccountConfirmationValidation.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("account_confirmation_validation_history", "id", id, ACTION_D);
        checkHistory("account_confirmation_validation_entry_history", "id", entity.getEntries().get(0).getId(),
            ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AccountNote</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_AccountNote() {
        Account account = getAccount();
        create(account);

        AccountNote entity = getAccountNote();
        entity.setAccountId(account.getId());

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("account_note_history", "id", id, ACTION_I);

        // Retrieve
        AccountNote res = (AccountNote) entityManager.find(AccountNote.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertNotNull("The result should be correct.", res.getDate());
        assertEquals("The result should be correct.", entity.getWriter(), res.getWriter());
        assertEquals("The result should be correct.", entity.getText(), res.getText());
        assertEquals("The result should be correct.", entity.getAccountId(), res.getAccountId());
        assertEquals("The result should be correct.", entity.getPriority(), res.getPriority());

        entity.setWriter("new");

        // Update
        update(entity);

        res = (AccountNote) entityManager.find(AccountNote.class, id);

        assertEquals("The result should be correct.", entity.getWriter(), res.getWriter());

        checkHistory("account_note_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (AccountNote) entityManager.find(AccountNote.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("account_note_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Account</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_Account() {
        Account entity = getAccount();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("account_history", "id", id, ACTION_I);

        AccountNote accountNote = getAccountNote();
        accountNote.setAccountId(id);
        create(accountNote);
        Payment payment = getPayment();
        payment.setAccountId(id);
        create(payment);
        AccountConfirmationValidation accountConfirmationValidation = getAccountConfirmationValidation();
        accountConfirmationValidation.setAccountId(id);
        create(accountConfirmationValidation);

        // Retrieve
        Account res = (Account) entityManager.find(Account.class, id);

        assertEquals("The result should be correct.",
            entity.getTotalDeposit().intValue(), res.getTotalDeposit().intValue());
        assertEquals("The result should be correct.",
            entity.getTotalRedeposit().intValue(), res.getTotalRedeposit().intValue());
        assertEquals("The result should be correct.",
            entity.getTotalVarRedeposit().intValue(), res.getTotalVarRedeposit().intValue());
        assertEquals("The result should be correct.",
            entity.getTotalNonDeposit().intValue(), res.getTotalNonDeposit().intValue());
        assertEquals("The result should be correct.",
            entity.getTotalFersW().intValue(), res.getTotalFersW().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntDep().intValue(), res.getAccIntDep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntRdep().intValue(), res.getAccIntRdep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntNonDep().intValue(), res.getAccIntNonDep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntVarRdep().intValue(), res.getAccIntVarRdep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntFers().intValue(), res.getAccIntFers().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayd().intValue(), res.getTotPayd().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayr().intValue(), res.getTotPayr().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayn().intValue(), res.getTotPayn().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayvr().intValue(), res.getTotPayvr().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayfers().intValue(), res.getTotPayfers().intValue());
        assertNotNull("The result should be correct.", res.getComputationDate());
        assertNotNull("The result should be correct.", res.getVarIntComputationDate());
        assertNotNull("The result should be correct.", res.getLastAction());
        assertNotNull("The result should be correct.", res.getLastPay());
        assertEquals("The result should be correct.", entity.getTimePeriod(), res.getTimePeriod());
        assertEquals("The result should be correct.",
            entity.getPayCode().getId(), res.getPayCode().getId());
        assertEquals("The result should be correct.",
            entity.getAdditionalService(), res.getAdditionalService());
        assertEquals("The result should be correct.", entity.getNoInterest(), res.getNoInterest());
        assertNotNull("The result should be correct.", res.getCode20Date());
        assertEquals("The result should be correct.", entity.getFlagPreredeposit(), res.getFlagPreredeposit());
        assertEquals("The result should be correct.", entity.getFlagPostredeposit(), res.getFlagPostredeposit());
        assertEquals("The result should be correct.", entity.getPriorClaimNumber(), res.getPriorClaimNumber());
        assertEquals("The result should be correct.", entity.getPaymentOrder(), res.getPaymentOrder());
        assertEquals("The result should be correct.", entity.getNewClaimNumber(), res.getNewClaimNumber());
        assertEquals("The result should be correct.", entity.getStopAchPayment(), res.getStopAchPayment());
        assertEquals("The result should be correct.", entity.getDbtsAccount(), res.getDbtsAccount());

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertEquals("The result should be correct.", entity.getPlanType(), res.getPlanType());
        assertEquals("The result should be correct.", entity.getFormType().getId(), res.getFormType().getId());
        assertEquals("The result should be correct.", entity.getHolder().getId(), res.getHolder().getId());
        assertEquals("The result should be correct.", entity.getStatus().getId(), res.getStatus().getId());
        assertEquals("The result should be correct.", entity.isGrace(), res.isGrace());
        assertEquals("The result should be correct.", entity.isFrozen(), res.isFrozen());
        assertEquals("The result should be correct.", entity.getClaimOfficer(), res.getClaimOfficer());
        assertNotNull("The result should be correct.", res.getClaimOfficerAssignmentDate());
        assertNotNull("The result should be correct.", res.getClaimantBirthdate());
        assertEquals("The result should be correct.", entity.getBalance().intValue(), res.getBalance().intValue());
        assertNotNull("The result should be correct.", res.getReturnedFromRecordsDate());
        assertEquals("The result should be correct.",
            entity.getCalculationVersions().size(), res.getCalculationVersions().size());

        assertEquals("The result should be correct.", 1, res.getNotes().size());
        assertEquals("The result should be correct.", accountNote.getId(), res.getNotes().get(0).getId());

        BillingSummary billingSummary = entity.getBillingSummary();
        BillingSummary retrievedBillingSummary = res.getBillingSummary();
        assertTrue("The result should be correct.", retrievedBillingSummary.getId() > 0);
        assertNotNull("The result should be correct.",
            retrievedBillingSummary.getComputedDate());
        assertNotNull("The result should be correct.",
            retrievedBillingSummary.getLastDepositDate());
        assertNotNull("The result should be correct.",
            retrievedBillingSummary.getFirstBillingDate());
        assertNotNull("The result should be correct.",
            retrievedBillingSummary.getLastInterestCalculation());
        assertEquals("The result should be correct.",
            billingSummary.getTransactionType(), retrievedBillingSummary.getTransactionType());
        assertNotNull("The result should be correct.",
            retrievedBillingSummary.getLastTransactionDate());
        assertEquals("The result should be correct.",
            billingSummary.getStopACHPayments(), retrievedBillingSummary.getStopACHPayments());
        assertEquals("The result should be correct.",
            billingSummary.getBillings().size(), retrievedBillingSummary.getBillings().size());

        Billing billing = billingSummary.getBillings().get(0);
        Billing retrievedBilling = retrievedBillingSummary.getBillings().get(0);
        assertEquals("The result should be correct.",
            billing.getName(), retrievedBilling.getName());
        assertEquals("The result should be correct.",
            billing.getInitialBilling().intValue(), retrievedBilling.getInitialBilling().intValue());
        assertEquals("The result should be correct.",
            billing.getAdditionalInterest().intValue(), retrievedBilling.getAdditionalInterest().intValue());
        assertEquals("The result should be correct.",
            billing.getTotalPayments().intValue(), retrievedBilling.getTotalPayments().intValue());
        assertEquals("The result should be correct.",
            billing.getBalance().intValue(), retrievedBilling.getBalance().intValue());
        assertEquals("The result should be correct.",
            billing.getPaymentOrder(), retrievedBilling.getPaymentOrder());


        entity.setPlanType("new");

        // Update
        update(entity);

        res = (Account) entityManager.find(Account.class, id);

        assertEquals("The result should be correct.", entity.getPlanType(), res.getPlanType());

        checkHistory("account_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (Account) entityManager.find(Account.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("account_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>CalculationVersion</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_CalculationVersion() {
        CalculationVersion entity = getCalculationVersion();

        // Create
        long accountId = create(getAccount());
        entity.setAccountId(accountId);
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("calculation_version_history", "id", id, ACTION_I);
        checkHistory("calculation_history", "id", entity.getCalculations().get(0).getId(), ACTION_I);
        checkHistory("calculation_result_history", "id", entity.getCalculationResult().getId(), ACTION_I);
        checkHistory("calculation_result_item_history", "id", entity.getCalculationResult().getItems().get(0).getId(),
            ACTION_I);
        checkHistory("summary_data_history", "id", entity.getCalculationResult().getSummary().getId(), ACTION_I);
        checkHistory("redeposit_history", "id", entity.getCalculationResult().getRedeposits().get(0).getId(),
            ACTION_I);
        checkHistory("dedeposit_history", "id", entity.getCalculationResult().getDedeposits().get(0).getId(),
            ACTION_I);

        // Retrieve
        CalculationVersion res = (CalculationVersion) entityManager.find(CalculationVersion.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertNotNull("The result should be correct.", res.getCalculationDate());
        assertEquals("The result should be correct.", entity.getVersion(), res.getVersion());
        assertEquals("The result should be correct.", entity.getLineNumber(), res.getLineNumber());
        assertEquals("The result should be correct.", entity.getCalculations().size(), res.getCalculations().size());

        Calculation calculation = entity.getCalculations().get(0);
        Calculation retrievedCalculation = res.getCalculations().get(0);
        assertNotNull("The result should be correct.", retrievedCalculation.getBeginDate());
        assertNotNull("The result should be correct.", retrievedCalculation.getEndDate());
        assertEquals("The result should be correct.", calculation.getRetirementType().getId(), retrievedCalculation
            .getRetirementType().getId());
        assertEquals("The result should be correct.", calculation.getPeriodType().getId(), retrievedCalculation
            .getPeriodType().getId());
        assertEquals("The result should be correct.", calculation.getAppointmentType().getId(), retrievedCalculation
            .getAppointmentType().getId());
        assertEquals("The result should be correct.", calculation.getServiceType().getId(), retrievedCalculation
            .getServiceType().getId());
        assertEquals("The result should be correct.",
            calculation.getAmount().intValue(), retrievedCalculation.getAmount().intValue());
        assertEquals("The result should be correct.",
            calculation.getPayType().getId(), retrievedCalculation.getPayType().getId());
        assertEquals("The result should be correct.",
            calculation.getAgencyCode().getId(), retrievedCalculation.getAgencyCode().getId());
        assertEquals("The result should be correct.",
            calculation.getHoursInYear(), retrievedCalculation.getHoursInYear());
        assertEquals("The result should be correct.",
            calculation.getAnnualizedAmount().intValue(), retrievedCalculation.getAnnualizedAmount().intValue());
        assertNotNull("The result should be correct.", retrievedCalculation.getDateEntered());

        assertNotNull("The result should be correct.", res.getCalculationDate());

        CalculationResult calculationResult = entity.getCalculationResult();
        CalculationResult retrievedCalculationResult = res.getCalculationResult();
        assertTrue("The result should be correct.", retrievedCalculationResult.getId() > 0);
        assertEquals("The result should be correct.",
            calculationResult.isOfficial(), retrievedCalculationResult.isOfficial());
        assertEquals("The result should be correct.",
            calculationResult.isApplyToRealPayments(), retrievedCalculationResult.isApplyToRealPayments());
        assertEquals("The result should be correct.",
            calculationResult.getPaymentOrder(), retrievedCalculationResult.getPaymentOrder());
        assertNotNull("The result should be correct.",
            retrievedCalculationResult.getInterestAccrualDate());

        CalculationResultItem calculationResultItem = calculationResult.getItems().get(0);
        CalculationResultItem retrievedCalculationResultItem = retrievedCalculationResult.getItems().get(0);
        assertTrue("The result should be correct.", retrievedCalculationResultItem.getId() > 0);
        assertNotNull("The result should be correct.", retrievedCalculationResultItem.getStartDate());
        assertNotNull("The result should be correct.", retrievedCalculationResultItem.getEndDate());
        assertNotNull("The result should be correct.", retrievedCalculationResultItem.getMidDate());
        assertNotNull("The result should be correct.", retrievedCalculationResultItem.getEffectiveDate());
        assertEquals("The result should be correct.",
            calculationResultItem.getPeriodType().getId(), retrievedCalculationResultItem.getPeriodType().getId());
        assertEquals("The result should be correct.",
            calculationResultItem.getDeductionAmount().intValue(),
            retrievedCalculationResultItem.getDeductionAmount().intValue());
        assertEquals("The result should be correct.",
            calculationResultItem.getTotalInterest().intValue(),
            retrievedCalculationResultItem.getTotalInterest().intValue());
        assertEquals("The result should be correct.",
            calculationResultItem.getPaymentsApplied().intValue(),
            retrievedCalculationResultItem.getPaymentsApplied().intValue());
        assertEquals("The result should be correct.",
            calculationResultItem.getBalance().intValue(),
            retrievedCalculationResultItem.getBalance().intValue());

        Redeposit redeposit = calculationResult.getRedeposits().get(0);
        Redeposit retrievedRedeposit = retrievedCalculationResult.getRedeposits().get(0);
        assertTrue("The result should be correct.", retrievedRedeposit.getId() > 0);
        assertEquals("The result should be correct.",
            redeposit.getDepositType(), retrievedRedeposit.getDepositType());
        assertEquals("The result should be correct.",
            redeposit.getDeposit().intValue(),
            retrievedRedeposit.getDeposit().intValue());
        assertEquals("The result should be correct.",
            redeposit.getDeposit().intValue(),
            retrievedRedeposit.getDeposit().intValue());
        assertEquals("The result should be correct.",
            redeposit.getInterest().intValue(),
            retrievedRedeposit.getInterest().intValue());
        assertEquals("The result should be correct.",
            redeposit.getTotal().intValue(),
            retrievedRedeposit.getTotal().intValue());

        Dedeposit dedeposit = calculationResult.getDedeposits().get(0);
        Dedeposit retrievedDedeposit = retrievedCalculationResult.getDedeposits().get(0);
        assertTrue("The result should be correct.", retrievedDedeposit.getId() > 0);
        assertEquals("The result should be correct.",
            dedeposit.getDepositType(), retrievedDedeposit.getDepositType());
        assertEquals("The result should be correct.",
            dedeposit.getDeposit().intValue(),
            retrievedDedeposit.getDeposit().intValue());
        assertEquals("The result should be correct.",
            dedeposit.getDeposit().intValue(),
            retrievedDedeposit.getDeposit().intValue());
        assertEquals("The result should be correct.",
            dedeposit.getInterest().intValue(),
            retrievedDedeposit.getInterest().intValue());
        assertEquals("The result should be correct.",
            dedeposit.getTotal().intValue(),
            retrievedDedeposit.getTotal().intValue());

        SummaryData summaryData = calculationResult.getSummary();
        SummaryData retrievedSummaryData = retrievedCalculationResult.getSummary();
        assertTrue("The result should be correct.", retrievedSummaryData.getId() > 0);
        assertEquals("The result should be correct.",
            summaryData.getTotalPaymentsRequired().intValue(),
            retrievedSummaryData.getTotalPaymentsRequired().intValue());
        assertEquals("The result should be correct.",
            summaryData.getTotalInitialInterest().intValue(),
            retrievedSummaryData.getTotalInitialInterest().intValue());
        assertEquals("The result should be correct.",
            summaryData.getTotalPaymentsApplied().intValue(),
            retrievedSummaryData.getTotalPaymentsApplied().intValue());
        assertEquals("The result should be correct.",
            summaryData.getTotalBalance().intValue(),
            retrievedSummaryData.getTotalBalance().intValue());


        entity.setName("new");

        // Update
        update(entity);

        res = (CalculationVersion) entityManager.find(CalculationVersion.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        checkHistory("calculation_version_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (CalculationVersion) entityManager.find(CalculationVersion.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("calculation_version_history", "id", id, ACTION_D);
        checkHistory("calculation_history", "id", entity.getCalculations().get(0).getId(), ACTION_D);
        checkHistory("calculation_result_history", "id", entity.getCalculationResult().getId(), ACTION_D);
        checkHistory("calculation_result_item_history", "id", entity.getCalculationResult().getItems().get(0).getId(),
            ACTION_D);
        checkHistory("summary_data_history", "id", entity.getCalculationResult().getSummary().getId(), ACTION_D);
        checkHistory("redeposit_history", "id", entity.getCalculationResult().getRedeposits().get(0).getId(),
            ACTION_D);
        checkHistory("dedeposit_history", "id", entity.getCalculationResult().getDedeposits().get(0).getId(),
            ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AccountHolder</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_AccountHolder() {
        AccountHolder entity = getAccountHolder();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("account_holder_history", "id", id, ACTION_I);
        checkHistory("address_history", "id", entity.getAddress().getId(), ACTION_I);

        // Retrieve
        AccountHolder res = (AccountHolder) entityManager.find(AccountHolder.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getLastName(), res.getLastName());
        assertEquals("The result should be correct.", entity.getFirstName(), res.getFirstName());
        assertEquals("The result should be correct.", entity.getMiddleInitial(), res.getMiddleInitial());
        assertEquals("The result should be correct.", entity.getSuffix().getId(), res.getSuffix().getId());
        assertNotNull("The result should be correct.", res.getBirthDate());
        assertEquals("The result should be correct.", entity.getSsn(), res.getSsn());
        assertEquals("The result should be correct.", entity.getTelephone(), res.getTelephone());
        assertEquals("The result should be correct.", entity.getEmail(), res.getEmail());
        assertEquals("The result should be correct.", entity.getTitle(), res.getTitle());
        assertEquals("The result should be correct.", entity.getDepartmentCode(), res.getDepartmentCode());
        assertEquals("The result should be correct.", entity.getGeoCode(), res.getGeoCode());
        assertEquals("The result should be correct.", entity.getCityOfEmployment(), res.getCityOfEmployment());
        assertEquals("The result should be correct.",
            entity.getStateOfEmployment().getId(), res.getStateOfEmployment().getId());
        assertEquals("The result should be correct.", entity.getPosition(), res.getPosition());
        assertEquals("The result should be correct.", entity.getAgencyCode(), res.getAgencyCode());

        Address address = entity.getAddress();
        Address retrievedAddress = res.getAddress();
        assertTrue("The result should be correct.", retrievedAddress.getId() > 0);
        assertEquals("The result should be correct.", address.getStreet1(), retrievedAddress.getStreet1());
        assertEquals("The result should be correct.", address.getStreet2(), retrievedAddress.getStreet2());
        assertEquals("The result should be correct.", address.getStreet3(), retrievedAddress.getStreet3());
        assertEquals("The result should be correct.", address.getStreet4(), retrievedAddress.getStreet4());
        assertEquals("The result should be correct.", address.getStreet5(), retrievedAddress.getStreet5());
        assertEquals("The result should be correct.", address.getCity(), retrievedAddress.getCity());
        assertEquals("The result should be correct.",
            address.getState().getId(), retrievedAddress.getState().getId());
        assertEquals("The result should be correct.", address.getZipCode(), retrievedAddress.getZipCode());
        assertEquals("The result should be correct.",
            address.getCountry().getId(), retrievedAddress.getCountry().getId());

        entity.setLastName("new");

        // Update
        update(entity);

        res = (AccountHolder) entityManager.find(AccountHolder.class, id);

        assertEquals("The result should be correct.", entity.getLastName(), res.getLastName());

        checkHistory("account_holder_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (AccountHolder) entityManager.find(AccountHolder.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("account_holder_history", "id", id, ACTION_D);
        checkHistory("address_history", "id", entity.getAddress().getId(), ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PaymentReverse</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_PaymentReverse() {
        Payment payment = getPayment();
        create(payment);

        PaymentReverse entity = getPaymentReverse();
        entity.setPaymentId(payment.getId());

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("payment_reverse_history", "id", id, ACTION_I);

        // Retrieve
        PaymentReverse res = (PaymentReverse) entityManager.find(PaymentReverse.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getPaymentId(), res.getPaymentId());
        assertEquals("The result should be correct.", entity.getReason().getId(), res.getReason().getId());
        assertEquals("The result should be correct.", entity.isApplyToGL(), res.isApplyToGL());
        assertEquals("The result should be correct.", entity.getReverser(), res.getReverser());

        entity.setReverser("new");

        // Update
        update(entity);

        res = (PaymentReverse) entityManager.find(PaymentReverse.class, id);

        assertEquals("The result should be correct.", entity.getReverser(), res.getReverser());

        checkHistory("payment_reverse_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (PaymentReverse) entityManager.find(PaymentReverse.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("payment_reverse_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>RefundTransaction</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_RefundTransaction() {
        RefundTransaction entity = getRefundTransaction();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("refund_transaction_history", "id", id, ACTION_I);

        // Retrieve
        RefundTransaction res = (RefundTransaction) entityManager.find(RefundTransaction.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getTransactionKey(), res.getTransactionKey());
        assertEquals("The result should be correct.", entity.getAmount().intValue(), res.getAmount().intValue());
        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertNotNull("The result should be correct.", res.getRefundDate());
        assertEquals("The result should be correct.", entity.getRefundUsername(), res.getRefundUsername());
        assertEquals("The result should be correct.", entity.getTransferType().getId(), res.getTransferType().getId());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (RefundTransaction) entityManager.find(RefundTransaction.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("refund_transaction_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (RefundTransaction) entityManager.find(RefundTransaction.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("refund_transaction_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AuditRecord</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_AuditRecord() {
        AuditRecord entity = getAuditRecord();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        AuditRecord res = (AuditRecord) entityManager.find(AuditRecord.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getUsername(), res.getUsername());
        assertEquals("The result should be correct.", entity.getIpAddress(), res.getIpAddress());
        assertEquals("The result should be correct.", entity.getActionName(), res.getActionName());
        assertNotNull("The result should be correct.", res.getDate());
        assertEquals("The result should be correct.", entity.getParameters().size(), res.getParameters().size());

        AuditParameterRecord parameter = entity.getParameters().get(0);
        AuditParameterRecord retrievedParameter = res.getParameters().get(0);

        assertFalse("The result should be correct.", retrievedParameter.isDeleted());
        assertEquals("The result should be correct.", parameter.getItemId(), retrievedParameter.getItemId());
        assertEquals("The result should be correct.", parameter.getItemType(), retrievedParameter.getItemType());
        assertEquals("The result should be correct.",
            parameter.getPropertyName(), retrievedParameter.getPropertyName());
        assertEquals("The result should be correct.",
            parameter.getPreviousValue(), retrievedParameter.getPreviousValue());
        assertEquals("The result should be correct.", parameter.getNewValue(), retrievedParameter.getNewValue());

        entity.setUsername("new");

        // Update
        update(entity);

        res = (AuditRecord) entityManager.find(AuditRecord.class, id);

        assertEquals("The result should be correct.", entity.getUsername(), res.getUsername());

        // Delete
        delete(res);

        res = (AuditRecord) entityManager.find(AuditRecord.class, id);
        assertNull("The result should be correct.", res);

        assertNull("The result should be correct.",
            entityManager.find(AuditParameterRecord.class, parameter.getId()));
    }

    /**
     * <p>
     * Accuracy test for the class <code>HelpItem</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_HelpItem() {
        HelpItem entity = getHelpItem();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        HelpItem res = (HelpItem) entityManager.find(HelpItem.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getTitle(), res.getTitle());
        assertEquals("The result should be correct.", entity.getSummary(), res.getSummary());
        assertEquals("The result should be correct.", entity.getContent(), res.getContent());

        entity.setTitle("new");

        // Update
        update(entity);

        res = (HelpItem) entityManager.find(HelpItem.class, id);

        assertEquals("The result should be correct.", entity.getTitle(), res.getTitle());

        // Delete
        delete(res);

        res = (HelpItem) entityManager.find(HelpItem.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Printout</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_Printout() {
        Printout entity = getPrintout();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        Printout res = (Printout) entityManager.find(Printout.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertNotNull("The result should be correct.", res.getPrintDate());

        entity.setName("new");

        // Update
        update(entity);

        res = (Printout) entityManager.find(Printout.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (Printout) entityManager.find(Printout.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>ServiceCreditPreference</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_ServiceCreditPreference() {
        ServiceCreditPreference entity = getServiceCreditPreference();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("service_credit_preference_history", "id", id, ACTION_I);

        // Retrieve
        ServiceCreditPreference res = (ServiceCreditPreference) entityManager.find(ServiceCreditPreference.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.isUseAgents(), res.isUseAgents());
        assertEquals("The result should be correct.", entity.isUseStatusBar(), res.isUseStatusBar());
        assertEquals("The result should be correct.", entity.isUseMessageBox(), res.isUseMessageBox());
        assertEquals("The result should be correct.", entity.getOtherSetting(), res.getOtherSetting());

        entity.setOtherSetting("new");

        // Update
        update(entity);

        res = (ServiceCreditPreference) entityManager.find(ServiceCreditPreference.class, id);

        assertEquals("The result should be correct.", entity.getOtherSetting(), res.getOtherSetting());

        checkHistory("service_credit_preference_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (ServiceCreditPreference) entityManager.find(ServiceCreditPreference.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("service_credit_preference_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Notification</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_Notification() {
        Notification entity = getNotification();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("notification_history", "id", id, ACTION_I);
        checkHistory("notification_READBY_history", "notification_id", id, ACTION_I);

        // Retrieve
        Notification res = (Notification) entityManager.find(Notification.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertNotNull("The result should be correct.", res.getDate());
        assertEquals("The result should be correct.", entity.getDetails(), res.getDetails());
        assertEquals("The result should be correct.", entity.getSender(), res.getSender());
        assertEquals("The result should be correct.", entity.isRead(), res.isRead());
        assertEquals("The result should be correct.", entity.getRecipient(), res.getRecipient());
        assertEquals("The result should be correct.", entity.getRole().getId(), res.getRole().getId());
        assertEquals("The result should be correct.", 1, res.getReadBy().size());

        entity.setDetails("new");

        // Update
        update(entity);

        res = (Notification) entityManager.find(Notification.class, id);

        assertEquals("The result should be correct.", entity.getDetails(), res.getDetails());

        checkHistory("notification_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (Notification) entityManager.find(Notification.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("notification_history", "id", id, ACTION_D);
        checkHistory("notification_READBY_history", "notification_id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Info</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_Info() {
        Info entity = getInfo();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("info_history", "id", id, ACTION_I);

        // Retrieve
        Info res = (Info) entityManager.find(Info.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertNotNull("The result should be correct.", res.getDate());
        assertEquals("The result should be correct.", entity.getDetails(), res.getDetails());

        entity.setDetails("new");

        // Update
        update(entity);

        res = (Info) entityManager.find(Info.class, id);

        assertEquals("The result should be correct.", entity.getDetails(), res.getDetails());

        checkHistory("info_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (Info) entityManager.find(Info.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("info_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Error</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_Error() {
        Error entity = getError();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("error_history", "id", id, ACTION_I);

        // Retrieve
        Error res = (Error) entityManager.find(Error.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertNotNull("The result should be correct.", res.getDate());
        assertEquals("The result should be correct.", entity.getDetails(), res.getDetails());

        entity.setDetails("new");

        // Update
        update(entity);

        res = (Error) entityManager.find(Error.class, id);

        assertEquals("The result should be correct.", entity.getDetails(), res.getDetails());

        checkHistory("error_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (Error) entityManager.find(Error.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("error_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>RolePermission</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_RolePermission() {
        RolePermission entity = getRolePermission();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        RolePermission res = (RolePermission) entityManager.find(RolePermission.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getRole(), res.getRole());
        assertEquals("The result should be correct.", entity.getAction(), res.getAction());

        entity.setRole("new");

        // Update
        update(entity);

        res = (RolePermission) entityManager.find(RolePermission.class, id);

        assertEquals("The result should be correct.", entity.getRole(), res.getRole());

        // Delete
        delete(res);

        res = (RolePermission) entityManager.find(RolePermission.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>UserPermission</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_UserPermission() {
        UserPermission entity = getUserPermission();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        UserPermission res = (UserPermission) entityManager.find(UserPermission.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getUsername(), res.getUsername());
        assertEquals("The result should be correct.", entity.getAction(), res.getAction());

        entity.setUsername("new");

        // Update
        update(entity);

        res = (UserPermission) entityManager.find(UserPermission.class, id);

        assertEquals("The result should be correct.", entity.getUsername(), res.getUsername());

        // Delete
        delete(res);

        res = (UserPermission) entityManager.find(UserPermission.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>User</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_User() {
        User entity = getUser();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        User res = (User) entityManager.find(User.class, id);

        assertFalse("The result should be correct.", res.isDeleted());
        assertEquals("The result should be correct.", entity.getUsername(), res.getUsername());
        assertEquals("The result should be correct.", entity.getDefaultTab(), res.getDefaultTab());
        assertEquals("The result should be correct.", entity.getNetworkId(), res.getNetworkId());
        assertEquals("The result should be correct.", entity.getRole().getId(), res.getRole().getId());
        assertEquals("The result should be correct.", entity.getFirstName(), res.getFirstName());
        assertEquals("The result should be correct.", entity.getLastName(), res.getLastName());
        assertEquals("The result should be correct.", entity.getEmail(), res.getEmail());
        assertEquals("The result should be correct.", entity.getTelephone(), res.getTelephone());
        assertEquals("The result should be correct.", entity.getStatus().getId(), res.getStatus().getId());

        entity.setUsername("new");

        // Update
        update(entity);

        res = (User) entityManager.find(User.class, id);

        assertEquals("The result should be correct.", entity.getUsername(), res.getUsername());

        // Delete
        delete(res);

        res = (User) entityManager.find(User.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>BatchDailyPayments</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_BatchDailyPayments() {
        BatchDailyPayments entity = getBatchDailyPayments();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("batch_daily_payments_history", "id", id, ACTION_I);

        // Retrieve
        BatchDailyPayments res = (BatchDailyPayments) entityManager.find(BatchDailyPayments.class, id);

        assertEquals("The result should be correct.", entity.getAuditBatchLogId(), res.getAuditBatchLogId());
        assertEquals("The result should be correct.", entity.getPayTransactionKey(), res.getPayTransactionKey());
        assertEquals("The result should be correct.", entity.getNumberPaymentToday(), res.getNumberPaymentToday());
        assertNotNull("The result should be correct.", res.getBatchTime());
        assertEquals("The result should be correct.",
            entity.getAccountStatus().getId(), res.getAccountStatus().getId());
        assertEquals("The result should be correct.", entity.getPayTransStatusCode(), res.getPayTransStatusCode());
        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertEquals("The result should be correct.",
            entity.getAccountBalance().intValue(), res.getAccountBalance().intValue());
        assertEquals("The result should be correct.",
            entity.getOverPaymentAmount().intValue(), res.getOverPaymentAmount().intValue());
        assertEquals("The result should be correct.", entity.getAchPayment(), res.getAchPayment());
        assertEquals("The result should be correct.", entity.getAchStopLetter(), res.getAchStopLetter());
        assertEquals("The result should be correct.", entity.getPrintInvoice(), res.getPrintInvoice());
        assertEquals("The result should be correct.", entity.getRefundRequired(), res.getRefundRequired());
        assertEquals("The result should be correct.", entity.getReversedPayment(), res.getReversedPayment());
        assertEquals("The result should be correct.", entity.getUpdateToCompleted(), res.getUpdateToCompleted());
        assertEquals("The result should be correct.", entity.getPrintInitialBill(), res.getPrintInitialBill());
        assertEquals("The result should be correct.", entity.getLatestBatch(), res.getLatestBatch());
        assertEquals("The result should be correct.", entity.getErrorProcessing(), res.getErrorProcessing());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (BatchDailyPayments) entityManager.find(BatchDailyPayments.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("batch_daily_payments_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (BatchDailyPayments) entityManager.find(BatchDailyPayments.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("batch_daily_payments_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AuditBatch</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_AuditBatch() {
        AuditBatch entity = getAuditBatch();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("audit_batch_history", "id", id, ACTION_I);

        // Retrieve
        AuditBatch res = (AuditBatch) entityManager.find(AuditBatch.class, id);

        assertEquals("The result should be correct.", entity.getEventYear(), res.getEventYear());
        assertEquals("The result should be correct.", entity.getEventMonth(), res.getEventMonth());
        assertEquals("The result should be correct.", entity.getEventDay(), res.getEventDay());
        assertEquals("The result should be correct.", entity.getFileReceived(), res.getFileReceived());
        assertEquals("The result should be correct.", entity.getDailyAction(), res.getDailyAction());
        assertEquals("The result should be correct.", entity.getManualBatch(), res.getManualBatch());
        assertEquals("The result should be correct.", entity.getErrorImporting(), res.getErrorImporting());
        assertEquals("The result should be correct.", entity.getErrorProcessing(), res.getErrorProcessing());
        assertEquals("The result should be correct.", entity.getLatestBatch(), res.getLatestBatch());
        assertEquals("The result should be correct.",
            entity.getAmountImported().intValue(), res.getAmountImported().intValue());
        assertEquals("The result should be correct.",
            entity.getAmountProcessed().intValue(), res.getAmountProcessed().intValue());
        assertEquals("The result should be correct.", entity.getNumberAccepted(), res.getNumberAccepted());
        assertEquals("The result should be correct.", entity.getNumberUnresolved(), res.getNumberUnresolved());
        assertEquals("The result should be correct.", entity.getNumberSuspended(), res.getNumberSuspended());
        assertEquals("The result should be correct.", entity.getNumberAchAccepted(), res.getNumberAchAccepted());
        assertEquals("The result should be correct.", entity.getNumberAchUnresolved(), res.getNumberAchUnresolved());
        assertEquals("The result should be correct.", entity.getNumberAchSuspended(), res.getNumberAchSuspended());
        assertEquals("The result should be correct.", entity.getNumberChangeRequests(), res.getNumberChangeRequests());
        assertEquals("The result should be correct.", entity.getPaymentsProcessed(), res.getPaymentsProcessed());
        assertEquals("The result should be correct.",
            entity.getInitialBillsProcessed(), res.getInitialBillsProcessed());
        assertEquals("The result should be correct.", entity.getReversedProcessed(), res.getReversedProcessed());
        assertEquals("The result should be correct.", entity.getAchStopLetters(), res.getAchStopLetters());
        assertEquals("The result should be correct.", entity.getRefundMemos(), res.getRefundMemos());
        assertEquals("The result should be correct.", entity.getErrorCountProcessing(), res.getErrorCountProcessing());
        assertEquals("The result should be correct.", entity.getUserKey(), res.getUserKey());
        assertNotNull("The result should be correct.", res.getBatchTime());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setEventYear(2);

        // Update
        update(entity);

        res = (AuditBatch) entityManager.find(AuditBatch.class, id);

        assertEquals("The result should be correct.", entity.getEventYear(), res.getEventYear());

        checkHistory("audit_batch_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (AuditBatch) entityManager.find(AuditBatch.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("audit_batch_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>NewClaimNumber</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_NewClaimNumber() {
        NewClaimNumber entity = getNewClaimNumber();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("new_claim_number_history", "id", id, ACTION_I);

        // Retrieve
        NewClaimNumber res = (NewClaimNumber) entityManager.find(NewClaimNumber.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (NewClaimNumber) entityManager.find(NewClaimNumber.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("new_claim_number_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (NewClaimNumber) entityManager.find(NewClaimNumber.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("new_claim_number_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AnnuitantList</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_AnnuitantList() {
        AnnuitantList entity = getAnnuitantList();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("annuitant_list_history", "id", id, ACTION_I);

        // Retrieve
        AnnuitantList res = (AnnuitantList) entityManager.find(AnnuitantList.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (AnnuitantList) entityManager.find(AnnuitantList.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("annuitant_list_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (AnnuitantList) entityManager.find(AnnuitantList.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("annuitant_list_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>TimeFactor</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_TimeFactor() {
        TimeFactor entity = getTimeFactor();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("time_factor_history", "id", id, ACTION_I);

        // Retrieve
        TimeFactor res = (TimeFactor) entityManager.find(TimeFactor.class, id);

        assertEquals("The result should be correct.", entity.getNumDays(), res.getNumDays());
        assertEquals("The result should be correct.", entity.getNumMonths(), res.getNumMonths());
        assertEquals("The result should be correct.",
            entity.getTimeFactor().intValue(), res.getTimeFactor().intValue());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setNumDays(2);

        // Update
        update(entity);

        res = (TimeFactor) entityManager.find(TimeFactor.class, id);

        assertEquals("The result should be correct.", entity.getNumDays(), res.getNumDays());

        checkHistory("time_factor_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (TimeFactor) entityManager.find(TimeFactor.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("time_factor_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>ContactInfo</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_ContactInfo() {
        ContactInfo entity = getContactInfo();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("contact_info_history", "id", id, ACTION_I);

        // Retrieve
        ContactInfo res = (ContactInfo) entityManager.find(ContactInfo.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertEquals("The result should be correct.", entity.getText(), res.getText());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (ContactInfo) entityManager.find(ContactInfo.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        checkHistory("contact_info_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (ContactInfo) entityManager.find(ContactInfo.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("contact_info_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>InterestSuppression</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_InterestSuppression() {
        InterestSuppression entity = getInterestSuppression();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("interest_suppression_history", "id", id, ACTION_I);

        // Retrieve
        InterestSuppression res = (InterestSuppression) entityManager.find(InterestSuppression.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertEquals("The result should be correct.", entity.getPost982Redeposit(), res.getPost982Redeposit());
        assertEquals("The result should be correct.", entity.getPre1082Redeposit(), res.getPre1082Redeposit());
        assertEquals("The result should be correct.", entity.getPost982Deposit(), res.getPost982Deposit());
        assertEquals("The result should be correct.", entity.getPre1082Deposit(), res.getPre1082Deposit());
        assertEquals("The result should be correct.", entity.getFersDeposit(), res.getFersDeposit());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (InterestSuppression) entityManager.find(InterestSuppression.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("interest_suppression_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (InterestSuppression) entityManager.find(InterestSuppression.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("interest_suppression_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>GLPaymentType</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_GLPaymentType() {
        GLPaymentType entity = getGLPaymentType();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("gl_payment_type_history", "id", id, ACTION_I);

        // Retrieve
        GLPaymentType res = (GLPaymentType) entityManager.find(GLPaymentType.class, id);

        assertEquals("The result should be correct.", entity.getPaymentCode(), res.getPaymentCode());
        assertEquals("The result should be correct.", entity.getCodeDescription(), res.getCodeDescription());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setPaymentCode("new");

        // Update
        update(entity);

        res = (GLPaymentType) entityManager.find(GLPaymentType.class, id);

        assertEquals("The result should be correct.", entity.getPaymentCode(), res.getPaymentCode());

        checkHistory("gl_payment_type_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (GLPaymentType) entityManager.find(GLPaymentType.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("gl_payment_type_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>SCMFirstInsert</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_SCMFirstInsert() {
        SCMFirstInsert entity = getSCMFirstInsert();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("scm_first_insert_history", "id", id, ACTION_I);

        // Retrieve
        SCMFirstInsert res = (SCMFirstInsert) entityManager.find(SCMFirstInsert.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertNotNull("The result should be correct.", res.getLastAction());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (SCMFirstInsert) entityManager.find(SCMFirstInsert.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("scm_first_insert_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (SCMFirstInsert) entityManager.find(SCMFirstInsert.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("scm_first_insert_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Holiday</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_Holiday() {
        Holiday entity = getHoliday();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("holiday_history", "id", id, ACTION_I);

        // Retrieve
        Holiday res = (Holiday) entityManager.find(Holiday.class, id);

        assertEquals("The result should be correct.", entity.getHoliday(), res.getHoliday());
        assertEquals("The result should be correct.", entity.getExactDate(), res.getExactDate());
        assertEquals("The result should be correct.", entity.getWeekDay(), res.getWeekDay());
        assertEquals("The result should be correct.", entity.getMonthNumber(), res.getMonthNumber());
        assertEquals("The result should be correct.", entity.getDayOfMonth(), res.getDayOfMonth());
        assertEquals("The result should be correct.", entity.getWeekOfMonth(), res.getWeekOfMonth());
        assertEquals("The result should be correct.", entity.getHolidayId(), res.getHolidayId());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setHoliday("new");

        // Update
        update(entity);

        res = (Holiday) entityManager.find(Holiday.class, id);

        assertEquals("The result should be correct.", entity.getHoliday(), res.getHoliday());

        checkHistory("holiday_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (Holiday) entityManager.find(Holiday.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("holiday_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>DeductionRate</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_DeductionRate() {
        DeductionRate entity = getDeductionRate();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("deduction_rate_history", "id", id, ACTION_I);

        // Retrieve
        DeductionRate res = (DeductionRate) entityManager.find(DeductionRate.class, id);

        assertEquals("The result should be correct.", entity.getServiceType(), res.getServiceType());
        assertEquals("The result should be correct.",
            entity.getRetirementType().getId(), res.getRetirementType().getId());
        assertNotNull("The result should be correct.", res.getStartDate());
        assertNotNull("The result should be correct.", res.getEndDate());
        assertEquals("The result should be correct.", entity.getDaysInPeriod(), res.getDaysInPeriod());
        assertEquals("The result should be correct.", entity.getRate().intValue(), res.getRate().intValue());
        assertEquals("The result should be correct.",
            entity.getServiceTypeDescription(), res.getServiceTypeDescription());
        assertEquals("The result should be correct.",
            entity.getDeductionConversionFactor().intValue(), res.getDeductionConversionFactor().intValue());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setServiceType("new");

        // Update
        update(entity);

        res = (DeductionRate) entityManager.find(DeductionRate.class, id);

        assertEquals("The result should be correct.", entity.getServiceType(), res.getServiceType());

        checkHistory("deduction_rate_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (DeductionRate) entityManager.find(DeductionRate.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("deduction_rate_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>InterestGracePeriod</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_InterestGracePeriod() {
        InterestGracePeriod entity = getInterestGracePeriod();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("interest_grace_period_history", "id", id, ACTION_I);

        // Retrieve
        InterestGracePeriod res = (InterestGracePeriod) entityManager.find(InterestGracePeriod.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertEquals("The result should be correct.", entity.getPost982Redeposit(), res.getPost982Redeposit());
        assertEquals("The result should be correct.", entity.getPre1082Redeposit(), res.getPre1082Redeposit());
        assertEquals("The result should be correct.", entity.getPost982Deposit(), res.getPost982Deposit());
        assertEquals("The result should be correct.", entity.getPre1082Deposit(), res.getPre1082Deposit());
        assertEquals("The result should be correct.", entity.getFersDeposit(), res.getFersDeposit());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (InterestGracePeriod) entityManager.find(InterestGracePeriod.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("interest_grace_period_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (InterestGracePeriod) entityManager.find(InterestGracePeriod.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("interest_grace_period_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>GLCode</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_GLCode() {
        GLCode entity = getGLCode();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("gl_code_history", "id", id, ACTION_I);

        // Retrieve
        GLCode res = (GLCode) entityManager.find(GLCode.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertEquals("The result should be correct.", entity.getCode(), res.getCode());
        assertEquals("The result should be correct.", entity.getPaymentType(), res.getPaymentType());
        assertEquals("The result should be correct.",
            entity.getRetirementType().getId(), res.getRetirementType().getId());
        assertEquals("The result should be correct.", entity.getPostOffice(), res.getPostOffice());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (GLCode) entityManager.find(GLCode.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        checkHistory("gl_code_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (GLCode) entityManager.find(GLCode.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("gl_code_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>ClaimWithoutService</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_ClaimWithoutService() {
        ClaimWithoutService entity = getClaimWithoutService();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("claim_without_service_history", "id", id, ACTION_I);

        // Retrieve
        ClaimWithoutService res = (ClaimWithoutService) entityManager.find(ClaimWithoutService.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertNotNull("The result should be correct.", res.getDateOfBirth());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (ClaimWithoutService) entityManager.find(ClaimWithoutService.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("claim_without_service_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (ClaimWithoutService) entityManager.find(ClaimWithoutService.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("claim_without_service_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PaymentTransactionNote</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_PaymentTransactionNote() {
        PaymentTransactionNote entity = getPaymentTransactionNote();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("payment_transaction_note_history", "id", id, ACTION_I);

        // Retrieve
        PaymentTransactionNote res = (PaymentTransactionNote) entityManager.find(PaymentTransactionNote.class, id);

        assertEquals("The result should be correct.", entity.getPayTransactionKey(), res.getPayTransactionKey());
        assertEquals("The result should be correct.", entity.getNote(), res.getNote());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setNote("new");

        // Update
        update(entity);

        res = (PaymentTransactionNote) entityManager.find(PaymentTransactionNote.class, id);

        assertEquals("The result should be correct.", entity.getNote(), res.getNote());

        checkHistory("payment_transaction_note_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (PaymentTransactionNote) entityManager.find(PaymentTransactionNote.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("payment_transaction_note_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PaymentMoveTransaction</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_PaymentMoveTransaction() {
        PaymentMoveTransaction entity = getPaymentMoveTransaction();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("payment_move_transaction_history", "id", id, ACTION_I);

        // Retrieve
        PaymentMoveTransaction res = (PaymentMoveTransaction) entityManager.find(PaymentMoveTransaction.class, id);

        assertEquals("The result should be correct.", entity.getPayTransactionKey(), res.getPayTransactionKey());
        assertEquals("The result should be correct.",
            entity.getTotPayd().intValue(), res.getTotPayd().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayr().intValue(), res.getTotPayr().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayn().intValue(), res.getTotPayn().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayvr().intValue(), res.getTotPayvr().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayfers().intValue(), res.getTotPayfers().intValue());
        assertNotNull("The result should be correct.", res.getModificationDate());
        assertNotNull("The result should be correct.", res.getApprovalDate());
        assertNotNull("The result should be correct.", res.getProcessedDate());
        assertEquals("The result should be correct.",
            entity.getTechnicianUserKey(), res.getTechnicianUserKey());
        assertEquals("The result should be correct.",
            entity.getManagerUserKey(), res.getManagerUserKey());
        assertEquals("The result should be correct.",
            entity.getApproved(), res.getApproved());
        assertEquals("The result should be correct.",
            entity.getDisapproved(), res.getDisapproved());
        assertEquals("The result should be correct.",
            entity.getModified(), res.getModified());
        assertEquals("The result should be correct.",
            entity.getNote(), res.getNote());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (PaymentMoveTransaction) entityManager.find(PaymentMoveTransaction.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("payment_move_transaction_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (PaymentMoveTransaction) entityManager.find(PaymentMoveTransaction.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("payment_move_transaction_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>InterestRate</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_InterestRate() {
        InterestRate entity = getInterestRate();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("interest_rate_history", "id", id, ACTION_I);

        // Retrieve
        InterestRate res = (InterestRate) entityManager.find(InterestRate.class, id);

        assertEquals("The result should be correct.", entity.getInterestYear(), res.getInterestYear());
        assertEquals("The result should be correct.",
            entity.getInterestRate().intValue(), res.getInterestRate().intValue());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setInterestYear(2);

        // Update
        update(entity);

        res = (InterestRate) entityManager.find(InterestRate.class, id);

        assertEquals("The result should be correct.", entity.getInterestYear(), res.getInterestYear());

        checkHistory("interest_rate_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (InterestRate) entityManager.find(InterestRate.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("interest_rate_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PaymentInterestDetail</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_PaymentInterestDetail() {
        PaymentInterestDetail entity = getPaymentInterestDetail();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("payment_interest_detail_history", "id", id, ACTION_I);

        // Retrieve
        PaymentInterestDetail res = (PaymentInterestDetail) entityManager.find(PaymentInterestDetail.class, id);

        assertEquals("The result should be correct.", entity.getPayTransactionKey(), res.getPayTransactionKey());
        assertEquals("The result should be correct.", entity.getAccountType(), res.getAccountType());
        assertEquals("The result should be correct.", entity.getNumWholeYears(), res.getNumWholeYears());
        assertEquals("The result should be correct.",
            entity.getCalculatedInterest().intValue(), res.getCalculatedInterest().intValue());
        assertEquals("The result should be correct.",
            entity.getLastPayToEOYFactor().intValue(), res.getLastPayToEOYFactor().intValue());
        assertEquals("The result should be correct.",
            entity.getPartialToThisPayFactor().intValue(), res.getPartialToThisPayFactor().intValue());
        assertEquals("The result should be correct.",
            entity.getThisInterestRate().intValue(), res.getThisInterestRate().intValue());
        assertNotNull("The result should be correct.", res.getLastPaymentDate());
        assertNotNull("The result should be correct.", res.getTransactionDate());
        assertNotNull("The result should be correct.", res.getComputedDate());
        assertEquals("The result should be correct.", entity.getPost(), res.getPost());
        assertEquals("The result should be correct.", entity.getGui(), res.getGui());
        assertEquals("The result should be correct.",
            entity.getLastPaymentWasThisYear(), res.getLastPaymentWasThisYear());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setPayTransactionKey(2L);

        // Update
        update(entity);

        res = (PaymentInterestDetail) entityManager.find(PaymentInterestDetail.class, id);

        assertEquals("The result should be correct.", entity.getPayTransactionKey(), res.getPayTransactionKey());

        checkHistory("payment_interest_detail_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (PaymentInterestDetail) entityManager.find(PaymentInterestDetail.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("payment_interest_detail_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AdjustmentTransaction</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_AdjustmentTransaction() {
        AdjustmentTransaction entity = getAdjustmentTransaction();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("adjustment_transaction_history", "id", id, ACTION_I);

        // Retrieve
        AdjustmentTransaction res = (AdjustmentTransaction) entityManager.find(AdjustmentTransaction.class, id);

        assertEquals("The result should be correct.",
            entity.getPayTransactionKey(), res.getPayTransactionKey());
        assertEquals("The result should be correct.",
            entity.getClaimNumber(), res.getClaimNumber());
        assertEquals("The result should be correct.",
            entity.getAccIntDep().intValue(), res.getAccIntDep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntRdep().intValue(), res.getAccIntRdep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntNonDep().intValue(), res.getAccIntNonDep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntVarRdep().intValue(), res.getAccIntVarRdep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntDepFers().intValue(), res.getAccIntDepFers().intValue());
        assertNotNull("The result should be correct.", res.getModificationDate());
        assertNotNull("The result should be correct.", res.getApprovalDate());
        assertNotNull("The result should be correct.", res.getProcessedDate());
        assertEquals("The result should be correct.",
            entity.getTechnicianUserKey(), res.getTechnicianUserKey());
        assertEquals("The result should be correct.",
            entity.getManagerUserKey(), res.getManagerUserKey());
        assertEquals("The result should be correct.",
            entity.getApproved(), res.getApproved());
        assertEquals("The result should be correct.",
            entity.getDisapproved(), res.getDisapproved());
        assertEquals("The result should be correct.",
            entity.getModified(), res.getModified());
        assertEquals("The result should be correct.",
            entity.getNote(), res.getNote());

        entity.setNote("new");

        // Update
        update(entity);

        res = (AdjustmentTransaction) entityManager.find(AdjustmentTransaction.class, id);

        assertEquals("The result should be correct.", entity.getNote(), res.getNote());

        checkHistory("adjustment_transaction_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (AdjustmentTransaction) entityManager.find(AdjustmentTransaction.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("adjustment_transaction_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Invoice</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_Invoice() {
        Invoice entity = getInvoice();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("invoice_history", "id", id, ACTION_I);

        // Retrieve
        Invoice res = (Invoice) entityManager.find(Invoice.class, id);

        assertEquals("The result should be correct.", entity.getPayTransactionKey(), res.getPayTransactionKey());
        assertEquals("The result should be correct.",
            entity.getDeposit().intValue(), res.getDeposit().intValue());
        assertEquals("The result should be correct.",
            entity.getRedeposit().intValue(), res.getRedeposit().intValue());
        assertEquals("The result should be correct.",
            entity.getTotVarRedeposit().intValue(), res.getTotVarRedeposit().intValue());
        assertEquals("The result should be correct.",
            entity.getNonDed().intValue(), res.getNonDed().intValue());
        assertEquals("The result should be correct.",
            entity.getFersW().intValue(), res.getFersW().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntDep().intValue(), res.getAccIntDep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntRdep().intValue(), res.getAccIntRdep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntNonDep().intValue(), res.getAccIntNonDep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntVarRdep().intValue(), res.getAccIntVarRdep().intValue());
        assertEquals("The result should be correct.",
            entity.getAccIntFers().intValue(), res.getAccIntFers().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayd().intValue(), res.getTotPayd().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayr().intValue(), res.getTotPayr().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayn().intValue(), res.getTotPayn().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayvr().intValue(), res.getTotPayvr().intValue());
        assertEquals("The result should be correct.",
            entity.getTotPayfers().intValue(), res.getTotPayfers().intValue());
        assertNotNull("The result should be correct.",
            res.getLastPay());
        assertNotNull("The result should be correct.",
            res.getCalcDate());
        assertEquals("The result should be correct.",
            entity.getLastInvoiceId(), res.getLastInvoiceId());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setPayTransactionKey(2L);

        // Update
        update(entity);

        res = (Invoice) entityManager.find(Invoice.class, id);

        assertEquals("The result should be correct.", entity.getPayTransactionKey(), res.getPayTransactionKey());

        checkHistory("invoice_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (Invoice) entityManager.find(Invoice.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("invoice_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>A01PrintSuppressionCases</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_A01PrintSuppressionCases() {
        A01PrintSuppressionCases entity = getA01PrintSuppressionCases();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("a01_print_suppression_case_history", "id", id, ACTION_I);

        // Retrieve
        A01PrintSuppressionCases res =
            (A01PrintSuppressionCases) entityManager.find(A01PrintSuppressionCases.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());
        assertEquals("The result should be correct.",
            entity.getReasonForPrintSuppression(), res.getReasonForPrintSuppression());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setClaimNumber("new");

        // Update
        update(entity);

        res = (A01PrintSuppressionCases) entityManager.find(A01PrintSuppressionCases.class, id);

        assertEquals("The result should be correct.", entity.getClaimNumber(), res.getClaimNumber());

        checkHistory("a01_print_suppression_case_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (A01PrintSuppressionCases) entityManager.find(A01PrintSuppressionCases.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("a01_print_suppression_case_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>UserAccountAssignment</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_UserAccountAssignment() {
        User user = getUser();
        create(user);
        Account account = getAccount();
        create(account);

        UserAccountAssignment entity = getUserAccountAssignment();
        entity.setUser(user);
        entity.setAccount(account);

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("user_account_assignment_history", "id", id, ACTION_I);

        // Retrieve
        UserAccountAssignment res = (UserAccountAssignment) entityManager.find(UserAccountAssignment.class, id);

        assertEquals("The result should be correct.", entity.getUser().getId(), res.getUser().getId());
        assertEquals("The result should be correct.", entity.getAccount().getId(), res.getAccount().getId());
        assertNotNull("The result should be correct.", res.getAssignmentDate());
        assertFalse("The result should be correct.", res.isDeleted());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        entity.setAssignmentDate(cal.getTime());

        // Update
        update(entity);

        res = (UserAccountAssignment) entityManager.find(UserAccountAssignment.class, id);

        assertNotNull("The result should be correct.", res.getAssignmentDate());

        checkHistory("user_account_assignment_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (UserAccountAssignment) entityManager.find(UserAccountAssignment.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("user_account_assignment_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PaymentRefundLink</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_PaymentRefundLink() {
        PaymentRefundLink entity = getPaymentRefundLink();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("payment_refund_link_history", "id", id, ACTION_I);

        // Retrieve
        PaymentRefundLink res = (PaymentRefundLink) entityManager.find(PaymentRefundLink.class, id);

        assertEquals("The result should be correct.",
            entity.getPaymentNeedingRefund(), res.getPaymentNeedingRefund());
        assertEquals("The result should be correct.", entity.getRefundForPayment(), res.getRefundForPayment());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setPaymentNeedingRefund(2L);

        // Update
        update(entity);

        res = (PaymentRefundLink) entityManager.find(PaymentRefundLink.class, id);

        assertEquals("The result should be correct.", entity.getPaymentNeedingRefund(), res.getPaymentNeedingRefund());

        checkHistory("payment_refund_link_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (PaymentRefundLink) entityManager.find(PaymentRefundLink.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("payment_refund_link_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PaymentsAppliedOrderCode</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_PaymentsAppliedOrderCode() {
        PaymentsAppliedOrderCode entity = getPaymentsAppliedOrderCode();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("payments_applied_order_code_history", "id", id, ACTION_I);

        // Retrieve
        PaymentsAppliedOrderCode res =
            (PaymentsAppliedOrderCode) entityManager.find(PaymentsAppliedOrderCode.class, id);

        assertEquals("The result should be correct.",
            entity.getPaymentAccount(), res.getPaymentAccount());
        assertEquals("The result should be correct.", entity.getDisplayOrder(), res.getDisplayOrder());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setPaymentAccount("new");

        // Update
        update(entity);

        res = (PaymentsAppliedOrderCode) entityManager.find(PaymentsAppliedOrderCode.class, id);

        assertEquals("The result should be correct.", entity.getPaymentAccount(), res.getPaymentAccount());

        checkHistory("payments_applied_order_code_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (PaymentsAppliedOrderCode) entityManager.find(PaymentsAppliedOrderCode.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("payments_applied_order_code_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PayTransStatusCode</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_PayTransStatusCode() {
        PayTransStatusCode entity = getPayTransStatusCode();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        checkHistory("pay_trans_status_code_history", "id", id, ACTION_I);

        // Retrieve
        PayTransStatusCode res = (PayTransStatusCode) entityManager.find(PayTransStatusCode.class, id);

        assertEquals("The result should be correct.", entity.getDescription(), res.getDescription());
        assertEquals("The result should be correct.", entity.getCategory(), res.getCategory());
        assertEquals("The result should be correct.", entity.getDisplayOrder(), res.getDisplayOrder());
        assertEquals("The result should be correct.", entity.getNextStateLink(), res.getNextStateLink());
        assertEquals("The result should be correct.", entity.getBatchProcessingOrder(), res.getBatchProcessingOrder());
        assertEquals("The result should be correct.", entity.getFinalState(), res.getFinalState());
        assertEquals("The result should be correct.", entity.getNeedsApproval(), res.getNeedsApproval());
        assertEquals("The result should be correct.", entity.getShowOnSuspense(), res.getShowOnSuspense());
        assertEquals("The result should be correct.", entity.getIncludeInBalance(), res.getIncludeInBalance());
        assertEquals("The result should be correct.", entity.getNightlyBatch(), res.getNightlyBatch());
        assertEquals("The result should be correct.", entity.getDeletable(), res.getDeletable());
        assertEquals("The result should be correct.", entity.getReversable(), res.getReversable());
        assertEquals("The result should be correct.", entity.getManualEntered(), res.getManualEntered());
        assertEquals("The result should be correct.", entity.getSuspenseAction(), res.getSuspenseAction());
        assertEquals("The result should be correct.", entity.getCanHitGl(), res.getCanHitGl());
        assertEquals("The result should be correct.", entity.getReversingType(), res.getReversingType());
        assertEquals("The result should be correct.", entity.getBalancedScorecard(), res.getBalancedScorecard());
        assertEquals("The result should be correct.", entity.getSendToDbts(), res.getSendToDbts());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setDescription("new");

        // Update
        update(entity);

        res = (PayTransStatusCode) entityManager.find(PayTransStatusCode.class, id);

        assertEquals("The result should be correct.", entity.getDescription(), res.getDescription());

        checkHistory("pay_trans_status_code_history", "id", id, ACTION_U);

        // Delete
        delete(res);

        res = (PayTransStatusCode) entityManager.find(PayTransStatusCode.class, id);
        assertNull("The result should be correct.", res);

        checkHistory("pay_trans_status_code_history", "id", id, ACTION_D);
    }

    /**
     * <p>
     * Accuracy test for the class <code>ChangeTransFieldNumberCode</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_ChangeTransFieldNumberCode() {
        ChangeTransFieldNumberCode entity = getChangeTransFieldNumberCode();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        ChangeTransFieldNumberCode res =
            (ChangeTransFieldNumberCode) entityManager.find(ChangeTransFieldNumberCode.class, id);

        assertEquals("The result should be correct.", entity.getDescription(), res.getDescription());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (ChangeTransFieldNumberCode) entityManager.find(ChangeTransFieldNumberCode.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (ChangeTransFieldNumberCode) entityManager.find(ChangeTransFieldNumberCode.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PaymentAppliedOrder</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_PaymentAppliedOrder() {
        PaymentAppliedOrder entity = getPaymentAppliedOrder();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        PaymentAppliedOrder res = (PaymentAppliedOrder) entityManager.find(PaymentAppliedOrder.class, id);

        assertEquals("The result should be correct.", entity.getDisplayOrder(), res.getDisplayOrder());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (PaymentAppliedOrder) entityManager.find(PaymentAppliedOrder.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (PaymentAppliedOrder) entityManager.find(PaymentAppliedOrder.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PayCode</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_PayCode() {
        PayCode entity = getPayCode();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        PayCode res = (PayCode) entityManager.find(PayCode.class, id);

        assertEquals("The result should be correct.", entity.getDescription(), res.getDescription());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (PayCode) entityManager.find(PayCode.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (PayCode) entityManager.find(PayCode.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AgencyCode</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_AgencyCode() {
        AgencyCode entity = getAgencyCode();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        AgencyCode res = (AgencyCode) entityManager.find(AgencyCode.class, id);

        assertEquals("The result should be correct.", entity.getDisplayOrder(), res.getDisplayOrder());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (AgencyCode) entityManager.find(AgencyCode.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (AgencyCode) entityManager.find(AgencyCode.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>FormType</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_FormType() {
        FormType entity = getFormType();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        FormType res = (FormType) entityManager.find(FormType.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (FormType) entityManager.find(FormType.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (FormType) entityManager.find(FormType.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Suffix</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_Suffix() {
        Suffix entity = getSuffix();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        Suffix res = (Suffix) entityManager.find(Suffix.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (Suffix) entityManager.find(Suffix.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (Suffix) entityManager.find(Suffix.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PeriodType</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_PeriodType() {
        PeriodType entity = getPeriodType();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        PeriodType res = (PeriodType) entityManager.find(PeriodType.class, id);

        assertEquals("The result should be correct.", entity.getDisplayOrder(), res.getDisplayOrder());
        assertEquals("The result should be correct.", entity.getDescription(), res.getDescription());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (PeriodType) entityManager.find(PeriodType.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (PeriodType) entityManager.find(PeriodType.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>State</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_State() {
        State entity = getState();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        State res = (State) entityManager.find(State.class, id);

        assertEquals("The result should be correct.", entity.getAbbreviation(), res.getAbbreviation());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (State) entityManager.find(State.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (State) entityManager.find(State.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Country</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_Country() {
        Country entity = getCountry();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        Country res = (Country) entityManager.find(Country.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (Country) entityManager.find(Country.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (Country) entityManager.find(Country.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AccountStatus</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_AccountStatus() {
        AccountStatus entity = getAccountStatus();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        AccountStatus res = (AccountStatus) entityManager.find(AccountStatus.class, id);

        assertEquals("The result should be correct.", entity.getCategory(), res.getCategory());
        assertEquals("The result should be correct.", entity.getDisplayOrder(), res.getDisplayOrder());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (AccountStatus) entityManager.find(AccountStatus.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (AccountStatus) entityManager.find(AccountStatus.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>RetirementType</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_RetirementType() {
        RetirementType entity = getRetirementType();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        RetirementType res = (RetirementType) entityManager.find(RetirementType.class, id);

        assertEquals("The result should be correct.", entity.getDescription(), res.getDescription());
        assertEquals("The result should be correct.", entity.getDisplayOrder(), res.getDisplayOrder());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (RetirementType) entityManager.find(RetirementType.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (RetirementType) entityManager.find(RetirementType.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>ServiceType</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_ServiceType() {
        ServiceType entity = getServiceType();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        ServiceType res = (ServiceType) entityManager.find(ServiceType.class, id);

        assertEquals("The result should be correct.",
            entity.getDisplayOrder(), res.getDisplayOrder());
        assertEquals("The result should be correct.",
            entity.getFersDepositAllowedAfter88(), res.getFersDepositAllowedAfter88());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (ServiceType) entityManager.find(ServiceType.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (ServiceType) entityManager.find(ServiceType.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>Role</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_Role() {
        Role entity = getRole();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        Role res = (Role) entityManager.find(Role.class, id);

        assertEquals("The result should be correct.", entity.getDescription(), res.getDescription());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (Role) entityManager.find(Role.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (Role) entityManager.find(Role.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>AppointmentType</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_AppointmentType() {
        AppointmentType entity = getAppointmentType();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        AppointmentType res = (AppointmentType) entityManager.find(AppointmentType.class, id);

        assertEquals("The result should be correct.", entity.getCategory(), res.getCategory());
        assertEquals("The result should be correct.", entity.getDescription(), res.getDescription());
        assertEquals("The result should be correct.", entity.getDisplayOrder(), res.getDisplayOrder());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (AppointmentType) entityManager.find(AppointmentType.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (AppointmentType) entityManager.find(AppointmentType.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PayType</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_PayType() {
        PayType entity = getPayType();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        PayType res = (PayType) entityManager.find(PayType.class, id);

        assertEquals("The result should be correct.", entity.getDescription(), res.getDescription());
        assertEquals("The result should be correct.", entity.getDisplayOrder(), res.getDisplayOrder());
        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (PayType) entityManager.find(PayType.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (PayType) entityManager.find(PayType.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PaymentReversalReason</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_PaymentReversalReason() {
        PaymentReversalReason entity = getPaymentReversalReason();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        PaymentReversalReason res = (PaymentReversalReason) entityManager.find(PaymentReversalReason.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (PaymentReversalReason) entityManager.find(PaymentReversalReason.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (PaymentReversalReason) entityManager.find(PaymentReversalReason.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>ApplicationDesignation</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_ApplicationDesignation() {
        ApplicationDesignation entity = getApplicationDesignation();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        ApplicationDesignation res = (ApplicationDesignation) entityManager.find(ApplicationDesignation.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (ApplicationDesignation) entityManager.find(ApplicationDesignation.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (ApplicationDesignation) entityManager.find(ApplicationDesignation.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>PaymentStatus</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_PaymentStatus() {
        PaymentStatus entity = getPaymentStatus();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        PaymentStatus res = (PaymentStatus) entityManager.find(PaymentStatus.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (PaymentStatus) entityManager.find(PaymentStatus.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (PaymentStatus) entityManager.find(PaymentStatus.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>ClaimOfficer</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_ClaimOfficer() {
        ClaimOfficer entity = getClaimOfficer();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        ClaimOfficer res = (ClaimOfficer) entityManager.find(ClaimOfficer.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (ClaimOfficer) entityManager.find(ClaimOfficer.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (ClaimOfficer) entityManager.find(ClaimOfficer.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>UserStatus</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_UserStatus() {
        UserStatus entity = getUserStatus();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        UserStatus res = (UserStatus) entityManager.find(UserStatus.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (UserStatus) entityManager.find(UserStatus.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (UserStatus) entityManager.find(UserStatus.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>TransferType</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_TransferType() {
        TransferType entity = getTransferType();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        TransferType res = (TransferType) entityManager.find(TransferType.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (TransferType) entityManager.find(TransferType.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (TransferType) entityManager.find(TransferType.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the class <code>CalculationStatus</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_CalculationStatus() {
        CalculationStatus entity = getCalculationStatus();

        // Create
        long id = create(entity);
        entityManager.clear();

        assertTrue("The result should be correct.", id > 0);

        // Retrieve
        CalculationStatus res = (CalculationStatus) entityManager.find(CalculationStatus.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());
        assertFalse("The result should be correct.", res.isDeleted());

        entity.setName("new");

        // Update
        update(entity);

        res = (CalculationStatus) entityManager.find(CalculationStatus.class, id);

        assertEquals("The result should be correct.", entity.getName(), res.getName());

        // Delete
        delete(res);

        res = (CalculationStatus) entityManager.find(CalculationStatus.class, id);
        assertNull("The result should be correct.", res);
    }

    /**
     * <p>
     * Checks if the record exists in history table.
     * </p>
     *
     * @param table
     *            the table.
     * @param name
     *            the name.
     * @param value
     *            the value.
     * @param action
     *            the action.
     */
    private static void checkHistory(String table, String name, long value, String action) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM " + SCHEMA + table + " WHERE " + name
            + "=? AND action=?");
        int count = ((Number) query.setParameter(1, value).setParameter(2, action).getSingleResult()).intValue();

        entityManager.getTransaction().commit();

        assertTrue("The result should be correct.", count > 0);

    }
}
