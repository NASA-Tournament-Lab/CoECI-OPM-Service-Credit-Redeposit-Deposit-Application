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

package gov.opm.scrd.entities.application;

import gov.opm.scrd.entities.common.IdentifiableEntity;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.FormType;
import gov.opm.scrd.entities.lookup.PayCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * This is the class representing the account used in the application. There will be always a dedicated person who is
 * holding the account. Once the account is created, it is status is set to PENDING(@see AccountStatus). Account can be
 * approved/disapproved by manager which changes the status to APPROVED/DISAPPROVED respectively.
 * </p>
 *
 * <p>
 * The account can be assigned for the investigation to the claim officer. In such case
 * claimOfficer/claimOfficerAssignmentDate fields are set. Similarly, account can be unassigned.
 * </p>
 *
 * <p>
 * The account is associated with number of the payments stored in the payment history. There can be different types of
 * payments(@see Payment). Based on it the billing data contained in @see BillingSummary is calculated.
 * </p>
 *
 * <p>
 * Additionally, the FERS deposit/redeposit calculation data is present in the entity. This data is populated by the
 * business rules.
 * </p>
 *
 * <p>
 * Each account before approval should be valid. The validation information is present in @see
 * AccountConfirmationValidation.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Services - Account and Payment Services Assembly 1.0):</em>
 * <ol>
 * <li>Changed claimNumber to String type.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added fields: totalDeposit, totalRedeposit, totalVarRedeposit, totalNonDeposit, totalFersW, accIntDep,
 * accIntRdep, accIntNonDep, accIntVarRdep, accIntFers, totPayd, totPayr, totPayn, totPayvr, totPayfers,
 * computationDate, varIntComputationDate, lastAction, lastPay, payCode, timePeriod, additionalService, noInterest,
 * code20Date, flagPreredeposit, flagPostredeposit, priorClaimNumber, paymentOrder, newClaimNumber, stopAchPayment,
 * dbtsAccount</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.3 (OPM - SCRD - Frontend Account Module Assembly 1.0):</em>
 * <ul>
 * <li>change lastAction to String</li>
 * <li>add field lastActionDate</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.4 (OPM - Release I Assembly 1.0):</em>
 * <ul>
 * <li>remove fields fersDepositCalculationVersions, fersRedepositCalculationVersions</li>
 * <li>add field calculationVersions</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax, bannie2492, TCSASSEMBLER
 * @version 1.4
 */
public class Account extends IdentifiableEntity {
    /**
     * <p>
     * Represents the claim number of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String claimNumber;
    /**
     * <p>
     * Represents the plan type of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String planType;
    /**
     * <p>
     * Represents the form type of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private FormType formType;
    /**
     * <p>
     * Represents the holder of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private AccountHolder holder;
    /**
     * <p>
     * Represents the status of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private AccountStatus status;
    /**
     * <p>
     * Represents the flag specifying whether account is grace. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private boolean grace;
    /**
     * <p>
     * Represents the flag specifying whether account is frozen. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private boolean frozen;
    /**
     * <p>
     * Represents the notes associated with account. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private List<AccountNote> notes;
    /**
     * <p>
     * Represents the claim officer assigned to the account. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String claimOfficer;
    /**
     * <p>
     * Represents the assigned date of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date claimOfficerAssignmentDate;
    /**
     * <p>
     * Represents the claimant birthdate of the assigned account. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private Date claimantBirthdate;
    /**
     * <p>
     * Represents the balance of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal balance;
    /**
     * <p>
     * Represents the timestamp when account was returned from records. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Date returnedFromRecordsDate;
    /**
     * <p>
     * Represents the billing summary of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BillingSummary billingSummary;

     /**
     * <p>
     * Represents the calculation data of account. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     *
     * @since 1.4(OPM - Release I Assembly 1.0)
     */
    private List<CalculationVersion> calculationVersions;


    /**
     * <p>
     * Represents the payments of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private List<Payment> paymentHistory;
    /**
     * <p>
     * Represents the account validity data of account. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private AccountConfirmationValidation validity;
    /**
     * <p>
     * Represents the total deposit of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totalDeposit;
    /**
     * <p>
     * Represents the total redeposit of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totalRedeposit;
    /**
     * <p>
     * Represents the total var redeposit of account. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totalVarRedeposit;
    /**
     * <p>
     * Represents the total non deposit of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totalNonDeposit;
    /**
     * <p>
     * Represents the total fers w of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totalFersW;
    /**
     * <p>
     * Represents the acc int dep of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal accIntDep;
    /**
     * <p>
     * Represents the acc int rdep of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal accIntRdep;
    /**
     * <p>
     * Represents the acc int non dep of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal accIntNonDep;
    /**
     * <p>
     * Represents the acc int var rdep of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal accIntVarRdep;
    /**
     * <p>
     * Represents the acc int fers of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal accIntFers;
    /**
     * <p>
     * Represents the tot payd of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totPayd;
    /**
     * <p>
     * Represents the tot payr of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totPayr;
    /**
     * <p>
     * Represents the tot payn of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totPayn;
    /**
     * <p>
     * Represents the tot payvr of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totPayvr;
    /**
     * <p>
     * Represents the tot payfers of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal totPayfers;
    /**
     * <p>
     * Represents the computation date of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Date computationDate;
    /**
     * <p>
     * Represents the var int computation date of account. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Date varIntComputationDate;
    /**
     * <p>
     * Represents the last action of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String lastAction;
    /**
     * <p>
     * Represents the last action date of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.3 (OPM - SCRD - Frontend Account Module Assembly 1.0)
     */
    private Date lastActionDate;
    /**
     * <p>
     * Represents the last pay date of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Date lastPay;
    /**
     * <p>
     * Represents the pay code of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private PayCode payCode;

    /**
     * <p>
     * Represents the time period of account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String timePeriod;

    /**
     * <p>
     * Represents the additional service of account. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String additionalService;
    /**
     * <p>
     * Represents the flag specifying whether account has no interest. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean noInterest;
    /**
     * <p>
     * Represents the code 20 date of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Date code20Date;
    /**
     * <p>
     * Represents the preredeposit flag of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean flagPreredeposit;
    /**
     * <p>
     * Represents the postredeposit flag of account. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean flagPostredeposit;
    /**
     * <p>
     * Represents the prior claim number of account. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String priorClaimNumber;
    /**
     * <p>
     * Represents the payment order of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String paymentOrder;
    /**
     * <p>
     * Represents the new claim number of account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String newClaimNumber;
    /**
     * <p>
     * Represents the flag specifying whether account should stop ACH payments. It is managed with a getter and setter.
     * It may have any value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean stopAchPayment;
    /**
     * <p>
     * Represents the flag specifying whether account is DBTS. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean dbtsAccount;

    /**
     * Creates an instance of Account.
     */
    public Account() {
        // Empty
    }

    /**
     * Gets the claim number of account.
     *
     * @return the claim number of account.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number of account.
     *
     * @param claimNumber
     *            the claim number of account.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the plan type of account.
     *
     * @return the plan type of account.
     */
    public String getPlanType() {
        return planType;
    }

    /**
     * Sets the plan type of account.
     *
     * @param planType
     *            the plan type of account.
     */
    public void setPlanType(String planType) {
        this.planType = planType;
    }

    /**
     * Gets the form type of account.
     *
     * @return the form type of account.
     */
    public FormType getFormType() {
        return formType;
    }

    /**
     * Sets the form type of account.
     *
     * @param formType
     *            the form type of account.
     */
    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    /**
     * Gets the holder of account.
     *
     * @return the holder of account.
     */
    public AccountHolder getHolder() {
        return holder;
    }

    /**
     * Sets the holder of account.
     *
     * @param holder
     *            the holder of account.
     */
    public void setHolder(AccountHolder holder) {
        this.holder = holder;
    }

    /**
     * Gets the status of account.
     *
     * @return the status of account.
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of account.
     *
     * @param status
     *            the status of account.
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    /**
     * Gets the flag specifying whether account is grace.
     *
     * @return the flag specifying whether account is grace.
     */
    public boolean isGrace() {
        return grace;
    }

    /**
     * Sets the flag specifying whether account is grace.
     *
     * @param grace
     *            the flag specifying whether account is grace.
     */
    public void setGrace(boolean grace) {
        this.grace = grace;
    }

    /**
     * Gets the flag specifying whether account is frozen.
     *
     * @return the flag specifying whether account is frozen.
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Sets the flag specifying whether account is frozen.
     *
     * @param frozen
     *            the flag specifying whether account is frozen.
     */
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * Gets the notes associated with account.
     *
     * @return the notes associated with account.
     */
    public List<AccountNote> getNotes() {
        return notes;
    }

    /**
     * Sets the notes associated with account.
     *
     * @param notes
     *            the notes associated with account.
     */
    public void setNotes(List<AccountNote> notes) {
        this.notes = notes;
    }

    /**
     * Gets the claim officer assigned to the account.
     *
     * @return the claim officer assigned to the account.
     */
    public String getClaimOfficer() {
        return claimOfficer;
    }

    /**
     * Sets the claim officer assigned to the account.
     *
     * @param claimOfficer
     *            the claim officer assigned to the account.
     */
    public void setClaimOfficer(String claimOfficer) {
        this.claimOfficer = claimOfficer;
    }

    /**
     * Gets the assigned date of account.
     *
     * @return the assigned date of account.
     */
    public Date getClaimOfficerAssignmentDate() {
        return claimOfficerAssignmentDate;
    }

    /**
     * Sets the assigned date of account.
     *
     * @param claimOfficerAssignmentDate
     *            the assigned date of account.
     */
    public void setClaimOfficerAssignmentDate(Date claimOfficerAssignmentDate) {
        this.claimOfficerAssignmentDate = claimOfficerAssignmentDate;
    }

    /**
     * Gets the claimant birthdate of the assigned account.
     *
     * @return the claimant birthdate of the assigned account.
     */
    public Date getClaimantBirthdate() {
        return claimantBirthdate;
    }

    /**
     * Sets the claimant birthdate of the assigned account.
     *
     * @param claimantBirthdate
     *            the claimant birthdate of the assigned account.
     */
    public void setClaimantBirthdate(Date claimantBirthdate) {
        this.claimantBirthdate = claimantBirthdate;
    }

    /**
     * Gets the balance of account.
     *
     * @return the balance of account.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the balance of account.
     *
     * @param balance
     *            the balance of account.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets the timestamp when account was returned from records.
     *
     * @return the timestamp when account was returned from records.
     */
    public Date getReturnedFromRecordsDate() {
        return returnedFromRecordsDate;
    }

    /**
     * Sets the timestamp when account was returned from records.
     *
     * @param returnedFromRecordsDate
     *            the timestamp when account was returned from records.
     */
    public void setReturnedFromRecordsDate(Date returnedFromRecordsDate) {
        this.returnedFromRecordsDate = returnedFromRecordsDate;
    }

    /**
     * Gets the billing summary of account.
     *
     * @return the billing summary of account.
     */
    public BillingSummary getBillingSummary() {
        return billingSummary;
    }

    /**
     * Sets the billing summary of account.
     *
     * @param billingSummary
     *            the billing summary of account.
     */
    public void setBillingSummary(BillingSummary billingSummary) {
        this.billingSummary = billingSummary;
    }

    /**
     * Gets the calculation versions of account.
     *
     * @return the calculation versions of account.
     *
     * @since 1.4 (OPM - Release I Assembly 1.0)
     */
    public List<CalculationVersion> getCalculationVersions() {
        return calculationVersions;
    }

    /**
     * Sets the calculation versions of account.
     *
     * @param calculationVersions
     *            the calculation versions of account.
     *
     * @since 1.4 (OPM - Release I Assembly 1.0)
     */
    public void setCalculationVersions(List<CalculationVersion> calculationVersions) {
        this.calculationVersions = calculationVersions;
    }

    /**
     * Gets the payments of account.
     *
     * @return the payments of account.
     */
    public List<Payment> getPaymentHistory() {
        return paymentHistory;
    }

    /**
     * Sets the payments of account.
     *
     * @param paymentHistory
     *            the payments of account.
     */
    public void setPaymentHistory(List<Payment> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    /**
     * Gets the account validity data of account.
     *
     * @return the account validity data of account.
     */
    public AccountConfirmationValidation getValidity() {
        return validity;
    }

    /**
     * Sets the account validity data of account.
     *
     * @param validity
     *            the account validity data of account.
     */
    public void setValidity(AccountConfirmationValidation validity) {
        this.validity = validity;
    }

    /**
     * Gets the total deposit of account.
     *
     * @return the total deposit of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotalDeposit() {
        return totalDeposit;
    }

    /**
     * Sets the total deposit of account.
     *
     * @param totalDeposit
     *            the total deposit of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotalDeposit(BigDecimal totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    /**
     * Gets the total redeposit of account.
     *
     * @return the total redeposit of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotalRedeposit() {
        return totalRedeposit;
    }

    /**
     * Sets the total redeposit of account.
     *
     * @param totalRedeposit
     *            the total redeposit of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotalRedeposit(BigDecimal totalRedeposit) {
        this.totalRedeposit = totalRedeposit;
    }

    /**
     * Gets the total var redeposit of account.
     *
     * @return the total var redeposit of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotalVarRedeposit() {
        return totalVarRedeposit;
    }

    /**
     * Sets the total var redeposit of account.
     *
     * @param totalVarRedeposit
     *            the total var redeposit of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotalVarRedeposit(BigDecimal totalVarRedeposit) {
        this.totalVarRedeposit = totalVarRedeposit;
    }

    /**
     * Gets the total non deposit of account.
     *
     * @return the total non deposit of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotalNonDeposit() {
        return totalNonDeposit;
    }

    /**
     * Sets the total non deposit of account.
     *
     * @param totalNonDeposit
     *            the total non deposit of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotalNonDeposit(BigDecimal totalNonDeposit) {
        this.totalNonDeposit = totalNonDeposit;
    }

    /**
     * Gets the total fers w of account.
     *
     * @return the total fers w of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotalFersW() {
        return totalFersW;
    }

    /**
     * Sets the total fers w of account.
     *
     * @param totalFersW
     *            the total fers w of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotalFersW(BigDecimal totalFersW) {
        this.totalFersW = totalFersW;
    }

    /**
     * Gets the acc int dep of account.
     *
     * @return the acc int dep of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getAccIntDep() {
        return accIntDep;
    }

    /**
     * Sets the acc int dep of account.
     *
     * @param accIntDep
     *            the acc int dep of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setAccIntDep(BigDecimal accIntDep) {
        this.accIntDep = accIntDep;
    }

    /**
     * Gets the acc int rdep of account.
     *
     * @return the acc int rdep of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getAccIntRdep() {
        return accIntRdep;
    }

    /**
     * Sets the acc int rdep of account.
     *
     * @param accIntRdep
     *            the acc int rdep of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setAccIntRdep(BigDecimal accIntRdep) {
        this.accIntRdep = accIntRdep;
    }

    /**
     * Gets the acc int non dep of account.
     *
     * @return the acc int non dep of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getAccIntNonDep() {
        return accIntNonDep;
    }

    /**
     * Sets the acc int non dep of account.
     *
     * @param accIntNonDep
     *            the acc int non dep of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setAccIntNonDep(BigDecimal accIntNonDep) {
        this.accIntNonDep = accIntNonDep;
    }

    /**
     * Gets the acc int var rdep of account.
     *
     * @return the acc int var rdep of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getAccIntVarRdep() {
        return accIntVarRdep;
    }

    /**
     * Sets the acc int var rdep of account.
     *
     * @param accIntVarRdep
     *            the acc int var rdep of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setAccIntVarRdep(BigDecimal accIntVarRdep) {
        this.accIntVarRdep = accIntVarRdep;
    }

    /**
     * Gets the acc int fers of account.
     *
     * @return the acc int fers of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getAccIntFers() {
        return accIntFers;
    }

    /**
     * Sets the acc int fers of account.
     *
     * @param accIntFers
     *            the acc int fers of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setAccIntFers(BigDecimal accIntFers) {
        this.accIntFers = accIntFers;
    }

    /**
     * Gets the tot payd of account.
     *
     * @return the tot payd of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotPayd() {
        return totPayd;
    }

    /**
     * Sets the tot payd of account.
     *
     * @param totPayd
     *            the tot payd of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotPayd(BigDecimal totPayd) {
        this.totPayd = totPayd;
    }

    /**
     * Gets the tot payr of account.
     *
     * @return the tot payr of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotPayr() {
        return totPayr;
    }

    /**
     * Sets the tot payr of account.
     *
     * @param totPayr
     *            the tot payr of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotPayr(BigDecimal totPayr) {
        this.totPayr = totPayr;
    }

    /**
     * Gets the tot payn of account.
     *
     * @return the tot payn of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotPayn() {
        return totPayn;
    }

    /**
     * Sets the tot payn of account.
     *
     * @param totPayn
     *            the tot payn of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotPayn(BigDecimal totPayn) {
        this.totPayn = totPayn;
    }

    /**
     * Gets the tot payvr of account.
     *
     * @return the tot payvr of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotPayvr() {
        return totPayvr;
    }

    /**
     * Sets the tot payvr of account.
     *
     * @param totPayvr
     *            the tot payvr of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotPayvr(BigDecimal totPayvr) {
        this.totPayvr = totPayvr;
    }

    /**
     * Gets the tot payfers of account.
     *
     * @return the tot payfers of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getTotPayfers() {
        return totPayfers;
    }

    /**
     * Sets the tot payfers of account.
     *
     * @param totPayfers
     *            the tot payfers of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setTotPayfers(BigDecimal totPayfers) {
        this.totPayfers = totPayfers;
    }

    /**
     * Gets the computation date of account.
     *
     * @return the computation date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Date getComputationDate() {
        return computationDate;
    }

    /**
     * Sets the computation date of account.
     *
     * @param computationDate
     *            the computation date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setComputationDate(Date computationDate) {
        this.computationDate = computationDate;
    }

    /**
     * Gets the var int computation date of account.
     *
     * @return the var int computation date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Date getVarIntComputationDate() {
        return varIntComputationDate;
    }

    /**
     * Sets the var int computation date of account.
     *
     * @param varIntComputationDate
     *            the var int computation date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setVarIntComputationDate(Date varIntComputationDate) {
        this.varIntComputationDate = varIntComputationDate;
    }

    /**
     * Gets the last action of account.
     *
     * @return the last action date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public String getLastAction() {
        return lastAction;
    }

    /**
     * Sets the last action of account.
     *
     * @param lastAction
     *            the last action date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    /**
     * Gets the last action date of account.
     *
     * @return the last action date of account.
     *
     * @since 1.3 (OPM - SCRD - Frontend Account Module Assembly 1.0)
     */
    public Date getLastActionDate() {
        return lastActionDate;
    }

    /**
     * Sets the last action date of account.
     *
     * @param lastActionDate
     *            the last action date of account.
     *
     * @since 1.3 (OPM - SCRD - Frontend Account Module Assembly 1.0)
     */
    public void setLastActionDate(Date lastActionDate) {
        this.lastActionDate = lastActionDate;
    }

    /**
     * Gets the last pay date of account.
     *
     * @return the last pay date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Date getLastPay() {
        return lastPay;
    }

    /**
     * Sets the last pay date of account.
     *
     * @param lastPay
     *            the last pay date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setLastPay(Date lastPay) {
        this.lastPay = lastPay;
    }

    /**
     * Gets the pay code of account.
     *
     * @return the pay code of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public PayCode getPayCode() {
        return payCode;
    }

    /**
     * Sets the pay code of account.
     *
     * @param payCode
     *            the pay code of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setPayCode(PayCode payCode) {
        this.payCode = payCode;
    }


    /**
     * Gets the time period of account.
     *
     * @return the timePeriod the time period of account.
     */
    public String getTimePeriod() {
        return timePeriod;
    }

    /**
     * Sets the time period of account.
     *
     * @param timePeriod
     *            the time period of account.
     */
    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    /**
     * Gets the additional service of account.
     *
     * @return the additional service of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public String getAdditionalService() {
        return additionalService;
    }

    /**
     * Sets the additional service of account.
     *
     * @param additionalService
     *            the additional service of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setAdditionalService(String additionalService) {
        this.additionalService = additionalService;
    }

    /**
     * Gets the flag specifying whether account has no interest.
     *
     * @return the flag specifying whether account has no interest.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getNoInterest() {
        return noInterest;
    }

    /**
     * Sets the flag specifying whether account has no interest.
     *
     * @param noInterest
     *            the flag specifying whether account has no interest.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setNoInterest(Boolean noInterest) {
        this.noInterest = noInterest;
    }

    /**
     * Gets the code 20 date of account.
     *
     * @return the code 20 date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Date getCode20Date() {
        return code20Date;
    }

    /**
     * Sets the code 20 date of account.
     *
     * @param code20Date
     *            the code 20 date of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setCode20Date(Date code20Date) {
        this.code20Date = code20Date;
    }

    /**
     * Gets the preredeposit flag of account.
     *
     * @return the preredeposit flag of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getFlagPreredeposit() {
        return flagPreredeposit;
    }

    /**
     * Sets the preredeposit flag of account.
     *
     * @param flagPreredeposit
     *            the preredeposit flag of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setFlagPreredeposit(Boolean flagPreredeposit) {
        this.flagPreredeposit = flagPreredeposit;
    }

    /**
     * Gets the postredeposit flag of account.
     *
     * @return the postredeposit flag of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getFlagPostredeposit() {
        return flagPostredeposit;
    }

    /**
     * Sets the postredeposit flag of account.
     *
     * @param flagPostredeposit
     *            the postredeposit flag of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setFlagPostredeposit(Boolean flagPostredeposit) {
        this.flagPostredeposit = flagPostredeposit;
    }

    /**
     * Gets the prior claim number of account.
     *
     * @return the prior claim number of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public String getPriorClaimNumber() {
        return priorClaimNumber;
    }

    /**
     * Sets the prior claim number of account.
     *
     * @param priorClaimNumber
     *            the prior claim number of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setPriorClaimNumber(String priorClaimNumber) {
        this.priorClaimNumber = priorClaimNumber;
    }

    /**
     * Gets the payment order of account.
     *
     * @return the payment order of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public String getPaymentOrder() {
        return paymentOrder;
    }

    /**
     * Sets the payment order of account.
     *
     * @param paymentOrder
     *            the payment order of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setPaymentOrder(String paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    /**
     * Gets the new claim number of account.
     *
     * @return the new claim number of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public String getNewClaimNumber() {
        return newClaimNumber;
    }

    /**
     * Sets the new claim number of account.
     *
     * @param newClaimNumber
     *            the new claim number of account.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setNewClaimNumber(String newClaimNumber) {
        this.newClaimNumber = newClaimNumber;
    }

    /**
     * Gets the flag specifying whether account should stop ACH payments.
     *
     * @return the flag specifying whether account should stop ACH payments.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getStopAchPayment() {
        return stopAchPayment;
    }

    /**
     * Sets the flag specifying whether account should stop ACH payments.
     *
     * @param stopAchPayment
     *            the flag specifying whether account should stop ACH payments.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setStopAchPayment(Boolean stopAchPayment) {
        this.stopAchPayment = stopAchPayment;
    }

    /**
     * Gets the flag specifying whether account is DBTS.
     *
     * @return the flag specifying whether account is DBTS.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getDbtsAccount() {
        return dbtsAccount;
    }

    /**
     * Sets the flag specifying whether account is DBTS.
     *
     * @param dbtsAccount
     *            the flag specifying whether account is DBTS.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setDbtsAccount(Boolean dbtsAccount) {
        this.dbtsAccount = dbtsAccount;
    }
}
