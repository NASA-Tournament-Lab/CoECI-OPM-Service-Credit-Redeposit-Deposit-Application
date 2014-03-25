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
import gov.opm.scrd.entities.common.IdentifiableEntity;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.ActionTab;
import gov.opm.scrd.entities.lookup.AgencyCode;
import gov.opm.scrd.entities.lookup.ApplicationDesignation;
import gov.opm.scrd.entities.lookup.AppointmentType;
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.entities.lookup.CalculationStatus;
import gov.opm.scrd.entities.lookup.ChangeTransFieldNumberCode;
import gov.opm.scrd.entities.lookup.ClaimOfficer;
import gov.opm.scrd.entities.lookup.Country;
import gov.opm.scrd.entities.lookup.DepositType;
import gov.opm.scrd.entities.lookup.FormType;
import gov.opm.scrd.entities.lookup.PayCode;
import gov.opm.scrd.entities.lookup.PayType;
import gov.opm.scrd.entities.lookup.PaymentAppliedOrder;
import gov.opm.scrd.entities.lookup.PaymentReversalReason;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PaymentType;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.Role;
import gov.opm.scrd.entities.lookup.ServiceType;
import gov.opm.scrd.entities.lookup.State;
import gov.opm.scrd.entities.lookup.Suffix;
import gov.opm.scrd.entities.lookup.TransferType;
import gov.opm.scrd.entities.lookup.UserStatus;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * <p>
 * Base persistence tests.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Updated methods: getPeriodType, getState, getAccountStatus, getRetirementType, getAppointmentType,
 * getServiceType, getRole, getPayType, getCalculation, getCalculationResult, getCalculationVersion, getAccountNote,
 * getAccountHolder, getAccount, getPayment</li>
 * <li>Added methods: getAgencyCode, getPayCode, getPaymentAppliedOrder, getChangeTransFieldNumberCode,
 * getPayTransStatusCode, getPaymentsAppliedOrderCode, getPaymentRefundLink, getUserAccountAssignment,
 * getA01PrintSuppressionCases, getInvoice, getAdjustmentTransaction, getPaymentInterestDetail, getInterestRate,
 * getPaymentMoveTransaction, getPaymentTransactionNote, getClaimWithoutService, getGLCode, getInterestGracePeriod,
 * getDeductionRate, getHoliday, getSCMFirstInsert, getGLPaymentType, getInterestSuppression, getContactInfo,
 * getTimeFactor, getAnnuitantList, getNewClaimNumber, getAuditBatch, getBatchDailyPayments</li>
 * </ul>
 * </p>
 *
 * @author sparemax, TCSASSEMBLER
 * @version 1.1
 */
public class BasePersistenceTests {
    /**
     * <p>
     * Represents the path of SQL files.
     * </p>
     */
    private static final String SQL_FILES = "src" + File.separator + "sql" + File.separator;

    /**
     * <p>
     * Represents the <code>EntityManagerFactory </code> for tests.
     * </p>
     */
    private static EntityManagerFactory factory;

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
        return new JUnit4TestAdapter(BasePersistenceTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        factory = Persistence.createEntityManagerFactory("opmUnitName");
        entityManager = factory.createEntityManager();
    }

    /**
     * <p>
     * Cleans up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        entityManager.close();
        entityManager = null;
        factory.close();
        factory = null;
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Before
    public void setUp() throws Exception {
        clearDB();
    }

    /**
     * <p>
     * Cleans up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @After
    public void tearDown() throws Exception {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }

        clearDB();
    }

    /**
     * Gets the entity manager.
     *
     * @return the entity manager.
     */
    public static EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * <p>
     * Deletes the entity.
     * </p>
     *
     * @param entity
     *            the entity.
     */
    protected void delete(Object entity) {
        entityManager.getTransaction().begin();

        entityManager.remove(entity);

        entityManager.getTransaction().commit();
    }

    /**
     * <p>
     * Updates the entity.
     * </p>
     *
     * @param entity
     *            the entity.
     */
    protected void update(Object entity) {
        entityManager.getTransaction().begin();

        entityManager.merge(entity);

        entityManager.getTransaction().commit();
    }

    /**
     * <p>
     * Creates the entity.
     * </p>
     *
     * @param <T>
     *            the entity type.
     * @param entity
     *            the entity.
     *
     * @return the id
     */
    protected <T extends IdentifiableEntity> long create(T entity) {
        entityManager.getTransaction().begin();

        entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.clear();

        return entity.getId();
    }

    /**
     * <p>
     * Clears the database.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    protected static void clearDB() throws Exception {
        executeSQL(SQL_FILES + "clear-data.sql");
    }

    /**
     * <p>
     * Initializes the database.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    protected static void initDB() throws Exception {
        executeSQL(SQL_FILES + "initial-data.sql");
    }

    /**
     * Creates an instance of Account.
     *
     * @return the Account instance.
     */
    protected Account getAccount() {
        Account entity = new Account();

        entity.setTotalDeposit(BigDecimal.ONE);
        entity.setTotalRedeposit(BigDecimal.ONE);
        entity.setTotalVarRedeposit(BigDecimal.ONE);
        entity.setTotalNonDeposit(BigDecimal.ONE);
        entity.setTotalFersW(BigDecimal.ONE);
        entity.setAccIntDep(BigDecimal.ONE);
        entity.setAccIntRdep(BigDecimal.ONE);
        entity.setAccIntNonDep(BigDecimal.ONE);
        entity.setAccIntVarRdep(BigDecimal.ONE);
        entity.setAccIntFers(BigDecimal.ONE);
        entity.setTotPayd(BigDecimal.ONE);
        entity.setTotPayr(BigDecimal.ONE);
        entity.setTotPayn(BigDecimal.ONE);
        entity.setTotPayvr(BigDecimal.ONE);
        entity.setTotPayfers(BigDecimal.ONE);
        entity.setComputationDate(new Date());
        entity.setVarIntComputationDate(new Date());
        entity.setLastAction("action");
        entity.setLastActionDate(new Date());
        entity.setLastPay(new Date());

        entity.setPayCode(getPayCode());
        create(entity.getPayCode());

        entity.setAdditionalService("additionalService1");
        entity.setNoInterest(true);
        entity.setCode20Date(new Date());
        entity.setFlagPreredeposit(true);
        entity.setFlagPostredeposit(true);
        entity.setPriorClaimNumber("priorClaimNumber1");
        entity.setPaymentOrder("paymentOrder1");
        entity.setNewClaimNumber("newClaimNumber1");
        entity.setStopAchPayment(true);
        entity.setDbtsAccount(true);

        entity.setClaimNumber("claimNumber1");
        entity.setPlanType("planType1");

        entity.setFormType(getFormType());
        create(entity.getFormType());

        entity.setHolder(getAccountHolder());
        create(entity.getHolder());

        entity.setStatus(getAccountStatus());
        create(entity.getStatus());

        entity.setGrace(true);
        entity.setFrozen(true);
        entity.setClaimOfficer("claimOfficer1");
        entity.setClaimOfficerAssignmentDate(new Date());
        entity.setClaimantBirthdate(new Date());
        entity.setBalance(BigDecimal.ONE);
        entity.setReturnedFromRecordsDate(new Date());
        entity.setBillingSummary(getBillingSummary());

        entity.setCalculationVersions(new ArrayList<CalculationVersion>());

        return entity;
    }

    /**
     * Creates an instance of Payment.
     *
     * @return the Payment instance.
     */
    protected Payment getPayment() {
        Payment entity = new Payment();

        entity.setGovRefund(true);
        entity.setDisapprove(true);
        entity.setHistoryPayment(true);
        entity.setResolvedSuspense(true);
        entity.setUserInserted(true);
        entity.setPostFlag(true);

        entity.setOrderCode(getPaymentsAppliedOrderCode());
        create(entity.getOrderCode());

        entity.setStatusCode(getPayTransStatusCode());
        create(entity.getStatusCode());

        entity.setBatchNumber("batchNumber1");
        entity.setBlockNumber("blockNumber1");
        entity.setSequenceNumber("sequenceNumber1");

        entity.setPaymentStatus(getPaymentStatus());
        create(entity.getPaymentStatus());

        entity.setClaimNumber("claimNumber1");
        entity.setAccountHolderBirthdate(new Date());
        entity.setDepositDate(new Date());
        entity.setAmount(BigDecimal.ONE);
        entity.setSsn("ssn1");
        entity.setClaimant("claimant1");
        entity.setClaimantBirthdate(new Date());
        entity.setImportId("importId1");
        entity.setSequence(1);
        entity.setTransactionDate(new Date());
        entity.setStatusDate(new Date());

        entity.setApplyTo(getApplicationDesignation());
        create(entity.getApplyTo());

        entity.setApplyToGL(true);
        entity.setNote("note1");
        entity.setTransactionKey("KEY");
        entity.setAch(true);
        entity.setAccountBalance(BigDecimal.ONE);

        entity.setAccountStatus(getAccountStatus());
        create(entity.getAccountStatus());

        entity.setMasterClaimNumber("masterClaimNumber1");
        entity.setMasterClaimantBirthdate(new Date());

        entity.setMasterAccountStatus(getAccountStatus());
        create(entity.getMasterAccountStatus());

        entity.setMasterAccountBalance(BigDecimal.ONE);
        entity.setMasterAccountId(1L);
        entity.setPreDepositAmount(BigDecimal.ONE);
        entity.setPreRedepositAmount(BigDecimal.ONE);
        entity.setPostDepositAmount(BigDecimal.ONE);
        entity.setPostRedepositAmount(BigDecimal.ONE);
        entity.setApprovalUser("approvalUser1");

        entity.setAccountStatus(getAccountStatus());
        create(entity.getAccountStatus());

        entity.setApprovalStatus(ApprovalStatus.PENDING);
        entity.setApprovalReason("approvalReason1");
        entity.setPaymentType(PaymentType.INTEREST_ADJUSTMENT);

        return entity;
    }

    /**
     * Creates an instance of AccountConfirmationValidation.
     *
     * @return the AccountConfirmationValidation instance.
     */
    protected static AccountConfirmationValidation getAccountConfirmationValidation() {
        AccountConfirmationValidation entity = new AccountConfirmationValidation();

        entity.setDataCheckStatus(ApprovalStatus.APPROVED);
        entity.setDataCheckStatusValidator("dataCheckStatusValidator1");
        entity.setDataCheckStatusReason("dataCheckStatusReason1");
        entity.setEntries(Arrays.asList(getAccountConfirmationValidationEntry()));

        return entity;
    }

    /**
     * Creates an instance of AccountConfirmationValidationEntry.
     *
     * @return the AccountConfirmationValidationEntry instance.
     */
    protected static AccountConfirmationValidationEntry getAccountConfirmationValidationEntry() {
        AccountConfirmationValidationEntry entity = new AccountConfirmationValidationEntry();

        entity.setFieldName("fieldName1");
        entity.setValid(true);

        return entity;
    }

    /**
     * Creates an instance of CalculationVersion.
     *
     * @return the CalculationVersion instance.
     */
    protected CalculationVersion getCalculationVersion() {
        CalculationVersion entity = new CalculationVersion();

        entity.setName("name1");
        entity.setCalculations(new ArrayList<Calculation>(Arrays.asList(getCalculation())));
        entity.setCalculationResult(getCalculationResult());
        entity.setCalculationDate(new Date());

        entity.setVersion(1);
        entity.setLineNumber(1);

        return entity;
    }

    /**
     * Creates an instance of CalculationResult.
     *
     * @return the CalculationResult instance.
     */
    protected CalculationResult getCalculationResult() {
        CalculationResult entity = new CalculationResult();

        entity.setItems(new ArrayList<CalculationResultItem>(Arrays.asList(getCalculationResultItem())));
        entity.setRedeposits(new ArrayList<Redeposit>(Arrays.asList(getRedeposit())));
        entity.setDedeposits(new ArrayList<Dedeposit>(Arrays.asList(getDedeposit())));
        entity.setSummary(getSummaryData());

        entity.setCalculationStatus(getCalculationStatus());
        create(entity.getCalculationStatus());

        entity.setOfficial(true);
        entity.setApplyToRealPayments(true);

        entity.setPaymentOrder(1);
        entity.setInterestAccrualDate(new Date());

        return entity;
    }

    /**
     * Creates an instance of CalculationResultItem.
     *
     * @return the CalculationResultItem instance.
     */
    protected CalculationResultItem getCalculationResultItem() {
        CalculationResultItem entity = new CalculationResultItem();

        entity.setStartDate(new Date());
        entity.setEndDate(new Date());
        entity.setMidDate(new Date());
        entity.setEffectiveDate(new Date());

        entity.setPeriodType(getPeriodType());
        create(entity.getPeriodType());

        entity.setDeductionAmount(BigDecimal.ONE);
        entity.setTotalInterest(BigDecimal.ONE);
        entity.setPaymentsApplied(BigDecimal.ONE);
        entity.setBalance(BigDecimal.ONE);
        RetirementType rt = new RetirementType();
        rt.setName("rt1");
        create(rt);
        entity.setRetirementType(rt);
        entity.setServiceCategory(DepositType.CSRS_POST_10_82_DEPOSIT);
        entity.setEffectiveDate(new Date());

        return entity;
    }

    /**
     * Creates an instance of SummaryData.
     *
     * @return the SummaryData instance.
     */
    protected static SummaryData getSummaryData() {
        SummaryData entity = new SummaryData();

        entity.setTotalPaymentsRequired(BigDecimal.ONE);
        entity.setTotalInitialInterest(BigDecimal.ONE);
        entity.setTotalPaymentsApplied(BigDecimal.ONE);
        entity.setTotalBalance(BigDecimal.ONE);

        return entity;
    }

    /**
     * Creates an instance of Dedeposit.
     *
     * @return the Dedeposit instance.
     */
    protected static Dedeposit getDedeposit() {
        Dedeposit entity = new Dedeposit();

        entity.setDepositType(DepositType.CSRS_POST_10_82_DEPOSIT);
        entity.setDeposit(BigDecimal.ONE);
        entity.setInterest(BigDecimal.ONE);
        entity.setTotal(BigDecimal.ONE);

        return entity;
    }

    /**
     * Creates an instance of Redeposit.
     *
     * @return the Redeposit instance.
     */
    protected static Redeposit getRedeposit() {
        Redeposit entity = new Redeposit();

        entity.setDepositType(DepositType.CSRS_POST_10_82_DEPOSIT);
        entity.setDeposit(BigDecimal.ONE);
        entity.setInterest(BigDecimal.ONE);
        entity.setTotal(BigDecimal.ONE);

        return entity;
    }

    /**
     * Creates an instance of Calculation.
     *
     * @return the Calculation instance.
     */
    protected Calculation getCalculation() {
        Calculation entity = new Calculation();
        
        entity.setBeginDate(new Date());
        entity.setEndDate(new Date());

        entity.setRetirementType(getRetirementType());
        create(entity.getRetirementType());

        entity.setPeriodType(getPeriodType());
        create(entity.getPeriodType());

        entity.setAppointmentType(getAppointmentType());
        create(entity.getAppointmentType());

        entity.setServiceType(getServiceType());
        create(entity.getServiceType());

        entity.setAmount(BigDecimal.ONE);

        entity.setPayType(getPayType());
        create(entity.getPayType());

        entity.setAgencyCode(getAgencyCode());
        create(entity.getAgencyCode());

        entity.setHoursInYear(1);
        entity.setAmount(BigDecimal.ONE);

        entity.setAnnualizedAmount(BigDecimal.ONE);
        
        entity.setDateEntered(new Date());
        entity.setEnteredBy(1L);
        entity.setInterestAccrualDate(new Date());

        return entity;
    }

    /**
     * Creates an instance of BillingSummary.
     *
     * @return the BillingSummary instance.
     */
    protected static BillingSummary getBillingSummary() {
        BillingSummary entity = new BillingSummary();

        entity.setComputedDate(new Date());
        entity.setLastDepositDate(new Date());
        entity.setFirstBillingDate(new Date());
        entity.setLastInterestCalculation(new Date());
        entity.setTransactionType("transactionType1");
        entity.setLastTransactionDate(new Date());
        entity.setBillings(Arrays.asList(getBilling()));
        entity.setStopACHPayments(true);

        return entity;
    }

    /**
     * Creates an instance of Billing.
     *
     * @return the Billing instance.
     */
    protected static Billing getBilling() {
        Billing entity = new Billing();

        entity.setName("name1");
        entity.setInitialBilling(BigDecimal.ONE);
        entity.setAdditionalInterest(BigDecimal.ONE);
        entity.setTotalPayments(BigDecimal.ONE);
        entity.setBalance(BigDecimal.ONE);
        entity.setPaymentOrder(1);

        return entity;
    }

    /**
     * Creates an instance of AccountHolder.
     *
     * @return the AccountHolder instance.
     */
    protected AccountHolder getAccountHolder() {
        AccountHolder entity = new AccountHolder();

        entity.setPosition("position1");
        entity.setAgencyCode("agencyCode1");

        entity.setLastName("lastName1");
        entity.setFirstName("firstName1");
        entity.setMiddleInitial("Y");

        entity.setSuffix(getSuffix());
        create(entity.getSuffix());

        entity.setBirthDate(new Date());
        entity.setSsn("ssn1");
        entity.setTelephone("telephone1");
        entity.setEmail("email1");
        entity.setTitle("title1");
        entity.setDepartmentCode("departmentCode1");

        entity.setAddress(getAddress());

        entity.setGeoCode("geoCode1");
        entity.setCityOfEmployment("cityOfEmployment1");

        entity.setStateOfEmployment(getState());
        create(entity.getStateOfEmployment());

        return entity;
    }

    /**
     * Creates an instance of Address.
     *
     * @return the Address instance.
     */
    protected Address getAddress() {
        Address entity = new Address();

        entity.setStreet1("street11");
        entity.setStreet2("street21");
        entity.setStreet3("street31");
        entity.setStreet4("street41");
        entity.setStreet5("street51");
        entity.setCity("city1");

        entity.setState(getState());
        create(entity.getState());

        entity.setZipCode("zipCode1");

        entity.setCountry(getCountry());
        create(entity.getCountry());

        return entity;
    }

    /**
     * Creates an instance of AccountNote.
     *
     * @return the AccountNote instance.
     */
    protected static AccountNote getAccountNote() {
        AccountNote entity = new AccountNote();

        entity.setPriority(1);
        entity.setDate(new Date());
        entity.setText("text1");
        entity.setWriter("writer1");

        return entity;
    }

    /**
     * Creates an instance of RefundTransaction.
     *
     * @return the RefundTransaction instance.
     */
    protected RefundTransaction getRefundTransaction() {
        RefundTransaction entity = new RefundTransaction();

        entity.setTransactionKey("KEY");
        entity.setAmount(BigDecimal.ONE);
        entity.setClaimNumber("claimNumber1");
        entity.setRefundDate(new Date());
        entity.setRefundUsername("refundUsername1");

        entity.setTransferType(getTransferType());
        create(entity.getTransferType());

        return entity;
    }

    /**
     * Creates an instance of PaymentReverse.
     *
     * @return the PaymentReverse instance.
     */
    protected PaymentReverse getPaymentReverse() {
        PaymentReverse entity = new PaymentReverse();

        entity.setPaymentId(1);

        entity.setReason(getPaymentReversalReason());
        create(entity.getReason());

        entity.setApplyToGL(true);
        entity.setReverser("reverser1");

        return entity;
    }

    /**
     * Creates an instance of AuditParameterRecord.
     *
     * @return the AuditParameterRecord instance.
     */
    protected static AuditParameterRecord getAuditParameterRecord() {
        AuditParameterRecord entity = new AuditParameterRecord();

        entity.setItemId(1L);
        entity.setItemType("itemType1");
        entity.setPropertyName("propertyName1");
        entity.setPreviousValue("previousValue1");
        entity.setNewValue("newValue1");

        return entity;
    }

    /**
     * Creates an instance of AuditRecord.
     *
     * @return the AuditRecord instance.
     */
    protected static AuditRecord getAuditRecord() {
        AuditRecord entity = new AuditRecord();

        entity.setUsername("username1");
        entity.setIpAddress("ipAddress1");
        entity.setActionName("actionName1");
        entity.setDate(new Date());

        entity.setParameters(Arrays.asList(getAuditParameterRecord()));

        return entity;
    }

    /**
     * Creates an instance of HelpItem.
     *
     * @return the HelpItem instance.
     */
    protected static HelpItem getHelpItem() {
        HelpItem entity = new HelpItem();

        entity.setTitle("title1");
        entity.setSummary("summary1");
        entity.setContent("content1");

        return entity;
    }

    /**
     * Creates an instance of Printout.
     *
     * @return the Printout instance.
     */
    protected static Printout getPrintout() {
        Printout entity = new Printout();

        entity.setName("name1");
        entity.setPrintDate(new Date());

        return entity;
    }

    /**
     * Creates an instance of ServiceCreditPreference.
     *
     * @return the ServiceCreditPreference instance.
     */
    protected static ServiceCreditPreference getServiceCreditPreference() {
        ServiceCreditPreference entity = new ServiceCreditPreference();

        entity.setUseAgents(true);
        entity.setUseStatusBar(true);
        entity.setUseMessageBox(true);
        entity.setOtherSetting("otherSetting1");

        return entity;
    }

    /**
     * Creates an instance of Notification.
     *
     * @return the Notification instance.
     */
    protected Notification getNotification() {
        Notification entity = new Notification();

        entity.setDate(new Date());
        entity.setDetails("details1");
        entity.setSender("sender1");
        entity.setRead(true);
        entity.setRecipient("recipient1");

        entity.setRole(getRole());
        create(entity.getRole());

        entity.setReadBy(new ArrayList<String>(Arrays.asList("readBy1")));

        return entity;
    }

    /**
     * Creates an instance of Info.
     *
     * @return the Info instance.
     */
    protected static Info getInfo() {
        Info entity = new Info();

        entity.setDate(new Date());
        entity.setDetails("details1");

        return entity;
    }

    /**
     * Creates an instance of Error.
     *
     * @return the Error instance.
     */
    protected static Error getError() {
        Error entity = new Error();

        entity.setDate(new Date());
        entity.setDetails("details1");

        return entity;
    }

    /**
     * Creates an instance of RolePermission.
     *
     * @return the RolePermission instance.
     */
    protected static RolePermission getRolePermission() {
        RolePermission entity = new RolePermission();

        entity.setRole("role1");
        entity.setAction("action1");

        return entity;
    }

    /**
     * Creates an instance of UserPermission.
     *
     * @return the UserPermission instance.
     */
    protected static UserPermission getUserPermission() {
        UserPermission entity = new UserPermission();

        entity.setUsername("username1");
        entity.setAction("action1");

        return entity;
    }

    /**
     * Creates an instance of User.
     *
     * @return the User instance.
     */
    protected User getUser() {
        User entity = new User();

        entity.setUsername("username1");
        entity.setDefaultTab(ActionTab.ADMIN);
        entity.setNetworkId("networkId1");

        entity.setRole(getRole());
        create(entity.getRole());

        entity.setFirstName("firstName1");
        entity.setLastName("lastName1");
        entity.setEmail("email1");
        entity.setTelephone("telephone1");


        entity.setStatus(getUserStatus());
        create(entity.getStatus());

        return entity;
    }

    /**
     * Creates an instance of BatchDailyPayments.
     *
     * @return the BatchDailyPayments instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected BatchDailyPayments getBatchDailyPayments() {
        BatchDailyPayments entity = new BatchDailyPayments();

        entity.setAuditBatchId(1L);
        entity.setPayTransactionKey(1);
        entity.setNumberPaymentToday(1);
        entity.setBatchTime(new Date());

        entity.setAccountStatus(getAccountStatus());
        create(entity.getAccountStatus());

        entity.setPayTransStatusCode(1);
        entity.setClaimNumber("claimNumber1");
        entity.setAccountBalance(BigDecimal.ONE);
        entity.setOverPaymentAmount(BigDecimal.ONE);

        entity.setAchPayment(true);
        entity.setAchStopLetter(true);
        entity.setPrintInvoice(true);
        entity.setRefundRequired(true);
        entity.setReversedPayment(true);
        entity.setUpdateToCompleted(true);
        entity.setPrintInitialBill(true);
        entity.setLatestBatch(true);
        entity.setErrorProcessing(true);

        return entity;
    }

    /**
     * Creates an instance of AuditBatch.
     *
     * @return the AuditBatch instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static AuditBatch getAuditBatch() {
        AuditBatch entity = new AuditBatch();

        entity.setEventYear(1);
        entity.setEventMonth(1);
        entity.setEventDay(1);
        entity.setFileReceived(true);
        entity.setDailyAction(true);
        entity.setManualBatch(true);
        entity.setErrorImporting(true);
        entity.setErrorProcessing(true);
        entity.setLatestBatch(true);
        entity.setAmountImported(BigDecimal.ONE);
        entity.setAmountProcessed(BigDecimal.ONE);
        entity.setNumberAccepted(1);
        entity.setNumberUnresolved(1);
        entity.setNumberSuspended(1);
        entity.setNumberAchAccepted(1);
        entity.setNumberAchUnresolved(1);
        entity.setNumberAchSuspended(1);
        entity.setNumberChangeRequests(1);
        entity.setPaymentsProcessed(1);
        entity.setInitialBillsProcessed(1);
        entity.setReversedProcessed(1);
        entity.setAchStopLetters(1);
        entity.setRefundMemos(1);
        entity.setErrorCountProcessing(1);
        entity.setUserKey(1L);
        entity.setBatchTime(new Date());

        return entity;
    }

    /**
     * Creates an instance of NewClaimNumber.
     *
     * @return the NewClaimNumber instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static NewClaimNumber getNewClaimNumber() {
        NewClaimNumber entity = new NewClaimNumber();

        entity.setClaimNumber("claimNumber1");

        return entity;
    }

    /**
     * Creates an instance of AnnuitantList.
     *
     * @return the AnnuitantList instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static AnnuitantList getAnnuitantList() {
        AnnuitantList entity = new AnnuitantList();

        entity.setClaimNumber("claimNumber1");

        return entity;
    }

    /**
     * Creates an instance of TimeFactor.
     *
     * @return the TimeFactor instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static TimeFactor getTimeFactor() {
        TimeFactor entity = new TimeFactor();

        entity.setNumDays(1);
        entity.setNumMonths(1);
        entity.setTimeFactor(BigDecimal.ONE);

        return entity;
    }

    /**
     * Creates an instance of ContactInfo.
     *
     * @return the ContactInfo instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static ContactInfo getContactInfo() {
        ContactInfo entity = new ContactInfo();

        entity.setName("name1");
        entity.setText("text1");

        return entity;
    }

    /**
     * Creates an instance of InterestSuppression.
     *
     * @return the InterestSuppression instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static InterestSuppression getInterestSuppression() {
        InterestSuppression entity = new InterestSuppression();

        entity.setClaimNumber("claimNumber1");
        entity.setPost982Redeposit(true);
        entity.setPre1082Redeposit(true);
        entity.setPost982Deposit(true);
        entity.setPre1082Deposit(true);
        entity.setFersDeposit(true);

        return entity;
    }

    /**
     * Creates an instance of GLPaymentType.
     *
     * @return the GLPaymentType instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static GLPaymentType getGLPaymentType() {
        GLPaymentType entity = new GLPaymentType();

        entity.setPaymentCode("paymentCode1");
        entity.setCodeDescription("codeDescription1");

        return entity;
    }

    /**
     * Creates an instance of SCMFirstInsert.
     *
     * @return the SCMFirstInsert instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static SCMFirstInsert getSCMFirstInsert() {
        SCMFirstInsert entity = new SCMFirstInsert();

        entity.setClaimNumber("claimNumber1");
        entity.setLastAction(new Date());

        return entity;
    }

    /**
     * Creates an instance of Holiday.
     *
     * @return the Holiday instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static Holiday getHoliday() {
        Holiday entity = new Holiday();

        entity.setHoliday("holiday1");
        entity.setExactDate(true);
        entity.setWeekDay(1);
        entity.setMonthNumber(1);
        entity.setDayOfMonth(1);
        entity.setWeekOfMonth(1);
        entity.setHolidayId(1);

        return entity;
    }

    /**
     * Creates an instance of DeductionRate.
     *
     * @return the DeductionRate instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected DeductionRate getDeductionRate() {
        DeductionRate entity = new DeductionRate();

        entity.setServiceType("serviceType1");

        entity.setRetirementType(getRetirementType());
        create(entity.getRetirementType());

        entity.setStartDate(new Date());
        entity.setEndDate(new Date());
        entity.setDaysInPeriod(1);
        entity.setRate(BigDecimal.ONE);
        entity.setServiceTypeDescription("serviceTypeDescription1");
        entity.setDeductionConversionFactor(BigDecimal.ONE);

        return entity;
    }

    /**
     * Creates an instance of InterestGracePeriod.
     *
     * @return the InterestGracePeriod instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static InterestGracePeriod getInterestGracePeriod() {
        InterestGracePeriod entity = new InterestGracePeriod();

        entity.setClaimNumber("claimNumber1");
        entity.setPost982Redeposit(true);
        entity.setPre1082Redeposit(true);
        entity.setPost982Deposit(true);
        entity.setPre1082Deposit(true);
        entity.setFersDeposit(true);

        return entity;
    }

    /**
     * Creates an instance of GLCode.
     *
     * @return the GLCode instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected GLCode getGLCode() {
        GLCode entity = new GLCode();

        entity.setName("name1");
        entity.setCode("code1");
        entity.setPaymentType("paymentType1");

        entity.setRetirementType(getRetirementType());
        create(entity.getRetirementType());

        entity.setPostOffice(true);

        return entity;
    }

    /**
     * Creates an instance of ClaimWithoutService.
     *
     * @return the ClaimWithoutService instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static ClaimWithoutService getClaimWithoutService() {
        ClaimWithoutService entity = new ClaimWithoutService();

        entity.setClaimNumber("claimNumber1");
        entity.setDateOfBirth(new Date());

        return entity;
    }

    /**
     * Creates an instance of PaymentTransactionNote.
     *
     * @return the PaymentTransactionNote instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static PaymentTransactionNote getPaymentTransactionNote() {
        PaymentTransactionNote entity = new PaymentTransactionNote();

        entity.setPayTransactionKey(1L);
        entity.setNote("note1");

        return entity;
    }

    /**
     * Creates an instance of PaymentMoveTransaction.
     *
     * @return the PaymentMoveTransaction instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static PaymentMoveTransaction getPaymentMoveTransaction() {
        PaymentMoveTransaction entity = new PaymentMoveTransaction();

        entity.setPayTransactionKey(1L);
        entity.setClaimNumber("claimNumber1");
        entity.setTotPayd(BigDecimal.ONE);
        entity.setTotPayr(BigDecimal.ONE);
        entity.setTotPayn(BigDecimal.ONE);
        entity.setTotPayvr(BigDecimal.ONE);
        entity.setTotPayfers(BigDecimal.ONE);
        entity.setModificationDate(new Date());
        entity.setApprovalDate(new Date());
        entity.setProcessedDate(new Date());
        entity.setTechnicianUserKey(1L);
        entity.setManagerUserKey(1L);
        entity.setApproved(true);
        entity.setDisapproved(true);
        entity.setModified(true);
        entity.setNote("note1");

        return entity;
    }

    /**
     * Creates an instance of InterestRate.
     *
     * @return the InterestRate instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static InterestRate getInterestRate() {
        InterestRate entity = new InterestRate();

        entity.setInterestYear(1);
        entity.setInterestRate(BigDecimal.ONE);

        return entity;
    }

    /**
     * Creates an instance of PaymentInterestDetail.
     *
     * @return the PaymentInterestDetail instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static PaymentInterestDetail getPaymentInterestDetail() {
        PaymentInterestDetail entity = new PaymentInterestDetail();

        entity.setPayTransactionKey(1L);
        entity.setAccountType(1L);
        entity.setNumWholeYears(1);
        entity.setCalculatedInterest(BigDecimal.ONE);
        entity.setLastPayToEOYFactor(BigDecimal.ONE);
        entity.setPartialToThisPayFactor(BigDecimal.ONE);
        entity.setThisInterestRate(BigDecimal.ONE);
        entity.setLastPaymentDate(new Date());
        entity.setTransactionDate(new Date());
        entity.setComputedDate(new Date());
        entity.setPost(true);
        entity.setGui(true);
        entity.setLastPaymentWasThisYear(true);

        return entity;
    }

    /**
     * Creates an instance of AdjustmentTransaction.
     *
     * @return the AdjustmentTransaction instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static AdjustmentTransaction getAdjustmentTransaction() {
        AdjustmentTransaction entity = new AdjustmentTransaction();

        entity.setPayTransactionKey(1L);
        entity.setClaimNumber("claimNumber1");
        entity.setAccIntDep(BigDecimal.ONE);
        entity.setAccIntRdep(BigDecimal.ONE);
        entity.setAccIntNonDep(BigDecimal.ONE);
        entity.setAccIntVarRdep(BigDecimal.ONE);
        entity.setAccIntDepFers(BigDecimal.ONE);
        entity.setModificationDate(new Date());
        entity.setApprovalDate(new Date());
        entity.setProcessedDate(new Date());
        entity.setTechnicianUserKey(1L);
        entity.setManagerUserKey(1L);
        entity.setApproved(true);
        entity.setDisapproved(true);
        entity.setModified(true);
        entity.setNote("note1");

        return entity;
    }

    /**
     * Creates an instance of Invoice.
     *
     * @return the Invoice instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static Invoice getInvoice() {
        Invoice entity = new Invoice();

        entity.setPayTransactionKey(1L);
        entity.setDeposit(BigDecimal.ONE);
        entity.setRedeposit(BigDecimal.ONE);
        entity.setTotVarRedeposit(BigDecimal.ONE);
        entity.setNonDed(BigDecimal.ONE);
        entity.setFersW(BigDecimal.ONE);
        entity.setAccIntDep(BigDecimal.ONE);
        entity.setAccIntRdep(BigDecimal.ONE);
        entity.setAccIntNonDep(BigDecimal.ONE);
        entity.setAccIntVarRdep(BigDecimal.ONE);
        entity.setAccIntFers(BigDecimal.ONE);
        entity.setTotPayd(BigDecimal.ONE);
        entity.setTotPayr(BigDecimal.ONE);
        entity.setTotPayn(BigDecimal.ONE);
        entity.setTotPayvr(BigDecimal.ONE);
        entity.setTotPayfers(BigDecimal.ONE);
        entity.setLastPay(new Date());
        entity.setCalcDate(new Date());
        entity.setLastInvoiceId(1L);

        return entity;
    }

    /**
     * Creates an instance of A01PrintSuppressionCases.
     *
     * @return the A01PrintSuppressionCases instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static A01PrintSuppressionCases getA01PrintSuppressionCases() {
        A01PrintSuppressionCases entity = new A01PrintSuppressionCases();

        entity.setClaimNumber("claimNumber1");
        entity.setReasonForPrintSuppression(1);

        return entity;
    }

    /**
     * Creates an instance of UserAccountAssignment.
     *
     * @return the UserAccountAssignment instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static UserAccountAssignment getUserAccountAssignment() {
        UserAccountAssignment entity = new UserAccountAssignment();

        entity.setAssignmentDate(new Date());

        return entity;
    }

    /**
     * Creates an instance of PaymentRefundLink.
     *
     * @return the PaymentRefundLink instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static PaymentRefundLink getPaymentRefundLink() {
        PaymentRefundLink entity = new PaymentRefundLink();

        entity.setPaymentNeedingRefund(1L);
        entity.setRefundForPayment(1L);

        return entity;
    }

    /**
     * Creates an instance of PaymentsAppliedOrderCode.
     *
     * @return the PaymentsAppliedOrderCode instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static PaymentsAppliedOrderCode getPaymentsAppliedOrderCode() {
        PaymentsAppliedOrderCode entity = new PaymentsAppliedOrderCode();

        entity.setPaymentAccount("paymentAccount1");
        entity.setDisplayOrder(1);

        return entity;
    }

    /**
     * Creates an instance of PayTransStatusCode.
     *
     * @return the PayTransStatusCode instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static PayTransStatusCode getPayTransStatusCode() {
        PayTransStatusCode entity = new PayTransStatusCode();

        entity.setDescription("description1");
        entity.setCategory("category1");
        entity.setDisplayOrder(1);
        entity.setNextStateLink(1);
        entity.setBatchProcessingOrder(1);
        entity.setFinalState(true);
        entity.setNeedsApproval(true);
        entity.setShowOnSuspense(true);
        entity.setIncludeInBalance(true);
        entity.setNightlyBatch(true);
        entity.setDeletable(true);
        entity.setReversable(true);
        entity.setManualEntered(true);
        entity.setSuspenseAction(true);
        entity.setCanHitGl(true);
        entity.setReversingType(true);
        entity.setBalancedScorecard(true);
        entity.setSendToDbts(true);

        return entity;
    }

    /**
     * Creates an instance of ChangeTransFieldNumberCode.
     *
     * @return the ChangeTransFieldNumberCode instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static ChangeTransFieldNumberCode getChangeTransFieldNumberCode() {
        ChangeTransFieldNumberCode entity = new ChangeTransFieldNumberCode();

        entity.setDescription("description1");
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of PaymentAppliedOrder.
     *
     * @return the PaymentAppliedOrder instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static PaymentAppliedOrder getPaymentAppliedOrder() {
        PaymentAppliedOrder entity = new PaymentAppliedOrder();

        entity.setDisplayOrder(1);
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of PayCode.
     *
     * @return the PayCode instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static PayCode getPayCode() {
        PayCode entity = new PayCode();

        entity.setDescription("description1");
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of AgencyCode.
     *
     * @return the AgencyCode instance.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    protected static AgencyCode getAgencyCode() {
        AgencyCode entity = new AgencyCode();

        entity.setDisplayOrder(1);
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of FormType.
     *
     * @return the FormType instance.
     */
    protected static FormType getFormType() {
        FormType entity = new FormType();

        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of Suffix.
     *
     * @return the Suffix instance.
     */
    protected static Suffix getSuffix() {
        Suffix entity = new Suffix();

        entity.setName("Sr");

        return entity;
    }

    /**
     * Creates an instance of PeriodType.
     *
     * @return the PeriodType instance.
     */
    protected static PeriodType getPeriodType() {
        PeriodType entity = new PeriodType();

        entity.setDescription("description1");
        entity.setDisplayOrder(1);
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of State.
     *
     * @return the State instance.
     */
    protected static State getState() {
        State entity = new State();

        entity.setAbbreviation("ab");
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of Country.
     *
     * @return the Country instance.
     */
    protected static Country getCountry() {
        Country entity = new Country();

        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of AccountStatus.
     *
     * @return the AccountStatus instance.
     */
    protected static AccountStatus getAccountStatus() {
        AccountStatus entity = new AccountStatus();

        entity.setCategory("category1");
        entity.setDisplayOrder(1);
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of RetirementType.
     *
     * @return the RetirementType instance.
     */
    protected static RetirementType getRetirementType() {
        RetirementType entity = new RetirementType();

        entity.setDescription("description1");
        entity.setDisplayOrder(1);
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of ServiceType.
     *
     * @return the ServiceType instance.
     */
    protected static ServiceType getServiceType() {
        ServiceType entity = new ServiceType();

        entity.setDisplayOrder(1);
        entity.setFersDepositAllowedAfter88(2);
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of Role.
     *
     * @return the Role instance.
     */
    protected static Role getRole() {
        Role entity = new Role();

        entity.setName("name1");
        entity.setDescription("description1");

        return entity;
    }

    /**
     * Creates an instance of PayType.
     *
     * @return the PayType instance.
     */
    protected static PayType getPayType() {
        PayType entity = new PayType();

        entity.setDescription("description1");
        entity.setDisplayOrder(1);
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of AppointmentType.
     *
     * @return the AppointmentType instance.
     */
    protected static AppointmentType getAppointmentType() {
        AppointmentType entity = new AppointmentType();

        entity.setDescription("description1");
        entity.setDisplayOrder(1);
        entity.setCategory("category1");
        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of PaymentReversalReason.
     *
     * @return the PaymentReversalReason instance.
     */
    protected static PaymentReversalReason getPaymentReversalReason() {
        PaymentReversalReason entity = new PaymentReversalReason();

        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of ApplicationDesignation.
     *
     * @return the ApplicationDesignation instance.
     */
    protected static ApplicationDesignation getApplicationDesignation() {
        ApplicationDesignation entity = new ApplicationDesignation();

        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of PaymentStatus.
     *
     * @return the PaymentStatus instance.
     */
    protected static PaymentStatus getPaymentStatus() {
        PaymentStatus entity = new PaymentStatus();

        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of ClaimOfficer.
     *
     * @return the ClaimOfficer instance.
     */
    protected static ClaimOfficer getClaimOfficer() {
        ClaimOfficer entity = new ClaimOfficer();

        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of UserStatus.
     *
     * @return the UserStatus instance.
     */
    protected static UserStatus getUserStatus() {
        UserStatus entity = new UserStatus();

        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of TransferType.
     *
     * @return the TransferType instance.
     */
    protected static TransferType getTransferType() {
        TransferType entity = new TransferType();

        entity.setName("name1");

        return entity;
    }

    /**
     * Creates an instance of CalculationStatus.
     *
     * @return the CalculationStatus instance.
     */
    protected static CalculationStatus getCalculationStatus() {
        CalculationStatus entity = new CalculationStatus();

        entity.setName("name1");

        return entity;
    }

    /**
     * <p>
     * Executes the SQL statements in the file. Lines that are empty or starts with '#' will be ignore.
     * </p>
     *
     * @param file
     *            the file.
     *
     * @throws Exception
     *             to JUnit.
     */
    private static void executeSQL(String file) throws Exception {
        entityManager.getTransaction().begin();

        String[] values = readFile(file).split(";");

        for (int i = 0; i < values.length; i++) {
            String sql = values[i].trim();
            if ((sql.length() != 0) && (!sql.startsWith("#"))) {
                entityManager.createNativeQuery(sql).executeUpdate();
            }
        }

        entityManager.getTransaction().commit();
    }

    /**
     * <p>
     * Reads the content of a given file.
     * </p>
     *
     * @param fileName
     *            the name of the file to read.
     *
     * @return a string represents the content.
     *
     * @throws IOException
     *             if any error occurs during reading.
     */
    private static String readFile(String fileName) throws IOException {
        Reader reader = new FileReader(fileName);

        try {
            // Create a StringBuilder instance
            StringBuilder sb = new StringBuilder();

            // Buffer for reading
            char[] buffer = new char[1024];

            // Number of read chars
            int k = 0;

            // Read characters and append to string builder
            while ((k = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, k);
            }

            // Return read content
            return sb.toString();
        } finally {
            try {
                reader.close();
            } catch (IOException ioe) {
                // Ignore
            }
        }
    }
}
