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
import gov.opm.scrd.entities.lookup.ApplicationDesignation;
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PaymentType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This is the class representing the performed payment of the account. This is a consolidated persistence entity for
 * all the payments used in the application. There can be ordinary payment or specific payment view. The payment view
 * information is set in @see PaymentType. Additional payment views are @see SuspendedPayment, @see PaymentMove, @see
 * InterestAdjustment, @see PendingPayment.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Services - Account and Payment Services Assembly 1.0):</em>
 * <ol>
 * <li>Added field approvalReason.</li>
 * <li>Changed claimNumber to String type.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added fields: govRefund, disapprove, historyPayment, resolvedSuspense, userInserted, postFlag, orderCode,
 * statusCode</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.2
 */
public class Payment extends IdentifiableEntity {
    /**
     * <p>
     * Represents the batch number of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String batchNumber;
    /**
     * <p>
     * Represents the block number of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String blockNumber;
    /**
     * <p>
     * Represents the sequence number of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String sequenceNumber;
    /**
     * <p>
     * Represents the status of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private PaymentStatus paymentStatus;
    /**
     * <p>
     * Represents the claim number of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String claimNumber;
    /**
     * <p>
     * Represents the birth date of account holder with this payment. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Date accountHolderBirthdate;
    /**
     * <p>
     * Represents the deposit date of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date depositDate;
    /**
     * <p>
     * Represents the amount of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal amount;
    /**
     * <p>
     * Represents the social security number of account holder with this payment. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String ssn;
    /**
     * <p>
     * Represents the claimant of the payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String claimant;
    /**
     * <p>
     * Represents the birth date of claimant of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Date claimantBirthdate;
    /**
     * <p>
     * Represents the id of import operation of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String importId;
    /**
     * <p>
     * Represents the sequence index of the payment. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private int sequence;
    /**
     * <p>
     * Represents the transaction date of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date transactionDate;
    /**
     * <p>
     * Represents the status date of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Date statusDate;
    /**
     * <p>
     * Represents the application designation of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private ApplicationDesignation applyTo;
    /**
     * <p>
     * Represents the flag specifying whether the payment should be applied to GL file. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean applyToGL;
    /**
     * <p>
     * Represents the note of the payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String note;
    /**
     * <p>
     * Represents the transaction key of the payment. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private String transactionKey;
    /**
     * <p>
     * Represents the flag specifying whether this payment is ACH. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private boolean ach;
    /**
     * <p>
     * Represents the balance of the payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal accountBalance;
    /**
     * <p>
     * Represents the account status of the payment. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private AccountStatus accountStatus;
    /**
     * <p>
     * Represents the claim number of master account of the payment. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private String masterClaimNumber;
    /**
     * <p>
     * Represents the birth date of claimant of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Date masterClaimantBirthdate;
    /**
     * <p>
     * Represents the status of master account of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private AccountStatus masterAccountStatus;
    /**
     * <p>
     * Represents the balance of master account of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal masterAccountBalance;
    /**
     * <p>
     * Represents the balance of master account of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Long masterAccountId;
    /**
     * <p>
     * Represents the predeposit amount of the payment. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private BigDecimal preDepositAmount;
    /**
     * <p>
     * Represents the preredeposit amount of the payment. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private BigDecimal preRedepositAmount;
    /**
     * <p>
     * Represents the post deposit amount of the payment. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private BigDecimal postDepositAmount;
    /**
     * <p>
     * Represents the post redeposit amount of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal postRedepositAmount;
    /**
     * <p>
     * Represents the user who approved the payment. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private String approvalUser;
    /**
     * <p>
     * Represents the approval status of the payment. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private ApprovalStatus approvalStatus;
    /**
     * <p>
     * Represents the approval reason of the payment. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private String approvalReason;
    /**
     * <p>
     * Represents the type of the payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private PaymentType paymentType;
    /**
     * <p>
     * Represents the id of account of the payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Long accountId;
    /**
     * <p>
     * Represents the flag specifying whether payment is government refund. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean govRefund;
    /**
     * <p>
     * Represents the flag specifying whether payment is disapproved. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean disapprove;
    /**
     * <p>
     * Represents the flag specifying whether payment is history. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean historyPayment;
    /**
     * <p>
     * Represents the flag specifying whether payment has resolved suspense. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean resolvedSuspense;
    /**
     * <p>
     * Represents the flag specifying whether payment is user inserted. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean userInserted;
    /**
     * <p>
     * Represents the flag specifying whether payment is post. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Boolean postFlag;
    /**
     * <p>
     * Represents the order code of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private PaymentsAppliedOrderCode orderCode;
    /**
     * <p>
     * Represents the status code of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private PayTransStatusCode statusCode;

    /**
     * Creates an instance of Payment.
     */
    public Payment() {
        // Empty
    }

    /**
     * Gets the batch number of payment.
     *
     * @return the batch number of payment.
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the batch number of payment.
     *
     * @param batchNumber
     *            the batch number of payment.
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the block number of payment.
     *
     * @return the block number of payment.
     */
    public String getBlockNumber() {
        return blockNumber;
    }

    /**
     * Sets the block number of payment.
     *
     * @param blockNumber
     *            the block number of payment.
     */
    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    /**
     * Gets the sequence number of payment.
     *
     * @return the sequence number of payment.
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequence number of payment.
     *
     * @param sequenceNumber
     *            the sequence number of payment.
     */
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the status of payment.
     *
     * @return the status of payment.
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the status of payment.
     *
     * @param paymentStatus
     *            the status of payment.
     */
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Gets the claim number of payment.
     *
     * @return the claim number of payment.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number of payment.
     *
     * @param claimNumber
     *            the claim number of payment.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the birth date of account holder with this payment.
     *
     * @return the birth date of account holder with this payment.
     */
    public Date getAccountHolderBirthdate() {
        return accountHolderBirthdate;
    }

    /**
     * Sets the birth date of account holder with this payment.
     *
     * @param accountHolderBirthdate
     *            the birth date of account holder with this payment.
     */
    public void setAccountHolderBirthdate(Date accountHolderBirthdate) {
        this.accountHolderBirthdate = accountHolderBirthdate;
    }

    /**
     * Gets the deposit date of payment.
     *
     * @return the deposit date of payment.
     */
    public Date getDepositDate() {
        return depositDate;
    }

    /**
     * Sets the deposit date of payment.
     *
     * @param depositDate
     *            the deposit date of payment.
     */
    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    /**
     * Gets the amount of payment.
     *
     * @return the amount of payment.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of payment.
     *
     * @param amount
     *            the amount of payment.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the social security number of account holder with this payment.
     *
     * @return the social security number of account holder with this payment.
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the social security number of account holder with this payment.
     *
     * @param ssn
     *            the social security number of account holder with this payment.
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * Gets the claimant of the payment.
     *
     * @return the claimant of the payment.
     */
    public String getClaimant() {
        return claimant;
    }

    /**
     * Sets the claimant of the payment.
     *
     * @param claimant
     *            the claimant of the payment.
     */
    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    /**
     * Gets the birth date of claimant of the payment.
     *
     * @return the birth date of claimant of the payment.
     */
    public Date getClaimantBirthdate() {
        return claimantBirthdate;
    }

    /**
     * Sets the birth date of claimant of the payment.
     *
     * @param claimantBirthdate
     *            the birth date of claimant of the payment.
     */
    public void setClaimantBirthdate(Date claimantBirthdate) {
        this.claimantBirthdate = claimantBirthdate;
    }

    /**
     * Gets the id of import operation of the payment.
     *
     * @return the id of import operation of the payment.
     */
    public String getImportId() {
        return importId;
    }

    /**
     * Sets the id of import operation of the payment.
     *
     * @param importId
     *            the id of import operation of the payment.
     */
    public void setImportId(String importId) {
        this.importId = importId;
    }

    /**
     * Gets the sequence index of the payment.
     *
     * @return the sequence index of the payment.
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * Sets the sequence index of the payment.
     *
     * @param sequence
     *            the sequence index of the payment.
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * Gets the transaction date of payment.
     *
     * @return the transaction date of payment.
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the transaction date of payment.
     *
     * @param transactionDate
     *            the transaction date of payment.
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the status date of payment.
     *
     * @return the status date of payment.
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * Sets the status date of payment.
     *
     * @param statusDate
     *            the status date of payment.
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * Gets the application designation of the payment.
     *
     * @return the application designation of the payment.
     */
    public ApplicationDesignation getApplyTo() {
        return applyTo;
    }

    /**
     * Sets the application designation of the payment.
     *
     * @param applyTo
     *            the application designation of the payment.
     */
    public void setApplyTo(ApplicationDesignation applyTo) {
        this.applyTo = applyTo;
    }

    /**
     * Gets the flag specifying whether the payment should be applied to GL file.
     *
     * @return the flag specifying whether the payment should be applied to GL file.
     */
    public boolean isApplyToGL() {
        return applyToGL;
    }

    /**
     * Sets the flag specifying whether the payment should be applied to GL file.
     *
     * @param applyToGL
     *            the flag specifying whether the payment should be applied to GL file.
     */
    public void setApplyToGL(boolean applyToGL) {
        this.applyToGL = applyToGL;
    }

    /**
     * Gets the note of the payment.
     *
     * @return the note of the payment.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note of the payment.
     *
     * @param note
     *            the note of the payment.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets the transaction key of the payment.
     *
     * @return the transaction key of the payment.
     */
    public String getTransactionKey() {
        return transactionKey;
    }

    /**
     * Sets the transaction key of the payment.
     *
     * @param transactionKey
     *            the transaction key of the payment.
     */
    public void setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
    }

    /**
     * Gets the flag specifying whether this payment is ACH.
     *
     * @return the flag specifying whether this payment is ACH.
     */
    public boolean isAch() {
        return ach;
    }

    /**
     * Sets the flag specifying whether this payment is ACH.
     *
     * @param ach
     *            the flag specifying whether this payment is ACH.
     */
    public void setAch(boolean ach) {
        this.ach = ach;
    }

    /**
     * Gets the balance of the payment.
     *
     * @return the balance of the payment.
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the balance of the payment.
     *
     * @param accountBalance
     *            the balance of the payment.
     */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * Gets the account status of the payment.
     *
     * @return the account status of the payment.
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the account status of the payment.
     *
     * @param accountStatus
     *            the account status of the payment.
     */
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Gets the claim number of master account of the payment.
     *
     * @return the claim number of master account of the payment.
     */
    public String getMasterClaimNumber() {
        return masterClaimNumber;
    }

    /**
     * Sets the claim number of master account of the payment.
     *
     * @param masterClaimNumber
     *            the claim number of master account of the payment.
     */
    public void setMasterClaimNumber(String masterClaimNumber) {
        this.masterClaimNumber = masterClaimNumber;
    }

    /**
     * Gets the birth date of claimant of the payment.
     *
     * @return the birth date of claimant of the payment.
     */
    public Date getMasterClaimantBirthdate() {
        return masterClaimantBirthdate;
    }

    /**
     * Sets the birth date of claimant of the payment.
     *
     * @param masterClaimantBirthdate
     *            the birth date of claimant of the payment.
     */
    public void setMasterClaimantBirthdate(Date masterClaimantBirthdate) {
        this.masterClaimantBirthdate = masterClaimantBirthdate;
    }

    /**
     * Gets the status of master account of the payment.
     *
     * @return the status of master account of the payment.
     */
    public AccountStatus getMasterAccountStatus() {
        return masterAccountStatus;
    }

    /**
     * Sets the status of master account of the payment.
     *
     * @param masterAccountStatus
     *            the status of master account of the payment.
     */
    public void setMasterAccountStatus(AccountStatus masterAccountStatus) {
        this.masterAccountStatus = masterAccountStatus;
    }

    /**
     * Gets the balance of master account of the payment.
     *
     * @return the balance of master account of the payment.
     */
    public BigDecimal getMasterAccountBalance() {
        return masterAccountBalance;
    }

    /**
     * Sets the balance of master account of the payment.
     *
     * @param masterAccountBalance
     *            the balance of master account of the payment.
     */
    public void setMasterAccountBalance(BigDecimal masterAccountBalance) {
        this.masterAccountBalance = masterAccountBalance;
    }

    /**
     * Gets the balance of master account of the payment.
     *
     * @return the balance of master account of the payment.
     */
    public Long getMasterAccountId() {
        return masterAccountId;
    }

    /**
     * Sets the balance of master account of the payment.
     *
     * @param masterAccountId
     *            the balance of master account of the payment.
     */
    public void setMasterAccountId(Long masterAccountId) {
        this.masterAccountId = masterAccountId;
    }

    /**
     * Gets the predeposit amount of the payment.
     *
     * @return the predeposit amount of the payment.
     */
    public BigDecimal getPreDepositAmount() {
        return preDepositAmount;
    }

    /**
     * Sets the predeposit amount of the payment.
     *
     * @param preDepositAmount
     *            the predeposit amount of the payment.
     */
    public void setPreDepositAmount(BigDecimal preDepositAmount) {
        this.preDepositAmount = preDepositAmount;
    }

    /**
     * Gets the preredeposit amount of the payment.
     *
     * @return the preredeposit amount of the payment.
     */
    public BigDecimal getPreRedepositAmount() {
        return preRedepositAmount;
    }

    /**
     * Sets the preredeposit amount of the payment.
     *
     * @param preRedepositAmount
     *            the preredeposit amount of the payment.
     */
    public void setPreRedepositAmount(BigDecimal preRedepositAmount) {
        this.preRedepositAmount = preRedepositAmount;
    }

    /**
     * Gets the post deposit amount of the payment.
     *
     * @return the post deposit amount of the payment.
     */
    public BigDecimal getPostDepositAmount() {
        return postDepositAmount;
    }

    /**
     * Sets the post deposit amount of the payment.
     *
     * @param postDepositAmount
     *            the post deposit amount of the payment.
     */
    public void setPostDepositAmount(BigDecimal postDepositAmount) {
        this.postDepositAmount = postDepositAmount;
    }

    /**
     * Gets the post redeposit amount of the payment.
     *
     * @return the post redeposit amount of the payment.
     */
    public BigDecimal getPostRedepositAmount() {
        return postRedepositAmount;
    }

    /**
     * Sets the post redeposit amount of the payment.
     *
     * @param postRedepositAmount
     *            the post redeposit amount of the payment.
     */
    public void setPostRedepositAmount(BigDecimal postRedepositAmount) {
        this.postRedepositAmount = postRedepositAmount;
    }

    /**
     * Gets the user who approved the payment.
     *
     * @return the user who approved the payment.
     */
    public String getApprovalUser() {
        return approvalUser;
    }

    /**
     * Sets the user who approved the payment.
     *
     * @param approvalUser
     *            the user who approved the payment.
     */
    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    /**
     * Gets the approval status of the payment.
     *
     * @return the approval status of the payment.
     */
    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * Sets the approval status of the payment.
     *
     * @param approvalStatus
     *            the approval status of the payment.
     */
    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * Gets the approval reason of the payment.
     *
     * @return the approval reason of the payment.
     */
    public String getApprovalReason() {
        return approvalReason;
    }

    /**
     * Sets the approval reason of the payment.
     *
     * @param approvalReason
     *            the approval reason of the payment.
     */
    public void setApprovalReason(String approvalReason) {
        this.approvalReason = approvalReason;
    }

    /**
     * Gets the type of the payment.
     *
     * @return the type of the payment.
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the type of the payment.
     *
     * @param paymentType
     *            the type of the payment.
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the id of account of the payment.
     *
     * @return the id of account of the payment.
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Sets the id of account of the payment.
     *
     * @param accountId
     *            the id of account of the payment.
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the flag specifying whether payment is government refund.
     *
     * @return the flag specifying whether payment is government refund.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getGovRefund() {
        return govRefund;
    }

    /**
     * Sets the flag specifying whether payment is government refund.
     *
     * @param govRefund
     *            the flag specifying whether payment is government refund.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setGovRefund(Boolean govRefund) {
        this.govRefund = govRefund;
    }

    /**
     * Gets the flag specifying whether payment is disapproved.
     *
     * @return the flag specifying whether payment is disapproved.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getDisapprove() {
        return disapprove;
    }

    /**
     * Sets the flag specifying whether payment is disapproved.
     *
     * @param disapprove
     *            the flag specifying whether payment is disapproved.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setDisapprove(Boolean disapprove) {
        this.disapprove = disapprove;
    }

    /**
     * Gets the flag specifying whether payment is history.
     *
     * @return the flag specifying whether payment is history.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getHistoryPayment() {
        return historyPayment;
    }

    /**
     * Sets the flag specifying whether payment is history.
     *
     * @param historyPayment
     *            the flag specifying whether payment is history.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setHistoryPayment(Boolean historyPayment) {
        this.historyPayment = historyPayment;
    }

    /**
     * Gets the flag specifying whether payment has resolved suspense.
     *
     * @return the flag specifying whether payment has resolved suspense.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getResolvedSuspense() {
        return resolvedSuspense;
    }

    /**
     * Sets the flag specifying whether payment has resolved suspense.
     *
     * @param resolvedSuspense
     *            the flag specifying whether payment has resolved suspense.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setResolvedSuspense(Boolean resolvedSuspense) {
        this.resolvedSuspense = resolvedSuspense;
    }

    /**
     * Gets the flag specifying whether payment is user inserted.
     *
     * @return the flag specifying whether payment is user inserted.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getUserInserted() {
        return userInserted;
    }

    /**
     * Sets the flag specifying whether payment is user inserted.
     *
     * @param userInserted
     *            the flag specifying whether payment is user inserted.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setUserInserted(Boolean userInserted) {
        this.userInserted = userInserted;
    }

    /**
     * Gets the flag specifying whether payment is post.
     *
     * @return the flag specifying whether payment is post.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Boolean getPostFlag() {
        return postFlag;
    }

    /**
     * Sets the flag specifying whether payment is post.
     *
     * @param postFlag
     *            the flag specifying whether payment is post.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setPostFlag(Boolean postFlag) {
        this.postFlag = postFlag;
    }

    /**
     * Gets the order code of payment.
     *
     * @return the order code of payment.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public PaymentsAppliedOrderCode getOrderCode() {
        return orderCode;
    }

    /**
     * Sets the order code of payment.
     *
     * @param orderCode
     *            the order code of payment.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setOrderCode(PaymentsAppliedOrderCode orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * Gets the status code of payment.
     *
     * @return the status code of payment.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public PayTransStatusCode getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code of payment.
     *
     * @param statusCode
     *            the status code of payment.
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setStatusCode(PayTransStatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
