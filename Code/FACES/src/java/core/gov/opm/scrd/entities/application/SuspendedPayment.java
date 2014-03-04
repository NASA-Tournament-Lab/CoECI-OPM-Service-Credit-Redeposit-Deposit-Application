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

import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This is the class representing the view of the Payment entity when the type corresponds to the suspended payment.
 * This entity is not persisted.
 * </p>
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Changed claimNumber and masterClaimNumber to String type.</li>
 * </ol>
 * </p>
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 * Changed log in the OPM - Frontend - Payments Module Assembly:
 * Add the paymentNote field.
 * @author faeton, sparemax
 * @version 1.0
 */
public class SuspendedPayment {
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
     * Represents the flag specifying whether this payment is ACH. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private boolean ach;

    /**
     * <p>
     * Represents the amount of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal amount;

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
     * Represents the claim number of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String claimNumber;

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
     * Represents the deposit date of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date depositDate;

    /**
     * <p>
     * Represents the balance of master account of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal masterAccountBalance;

    /**
     * <p>
     * Represents the status of master account of the payment. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private AccountStatus masterAccountStatus;

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
     * Represents the id of account of the payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private long paymentId;

    /**
     * The payment note. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * Changed log in the OPM - Frontend - Payments Module Assembly:
     * It's newly added.
     */
    private String paymentNote;

    /**
     * <p>
     * Represents the status of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private PaymentStatus paymentStatus;

    /**
     * <p>
     * Represents the sequence number of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String sequenceNumber;

    /**
     * transactionDate is used to represents the transaction date.
     */
    private Date transactionDate;

    /**
     * Creates an instance of SuspendedPayment.
     */
    public SuspendedPayment() {
        // Empty
    }

    /**
     * Set the payment note.
     * Changed log in the OPM - Frontend - Payments Module Assembly:
     * It's newly added.
     * @param paymentNote to set
     */
    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }

    /**
     * Get the payment note.
     * Changed log in the OPM - Frontend - Payments Module Assembly:
     * It's newly added.
     * @return the payment note
     */
    public String getPaymentNote() {
        return this.paymentNote;
    }

    /**
     * Gets the status of payment.
     * @return the status of payment.
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the status of payment.
     * @param paymentStatus
     *            the status of payment.
     */
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Gets the deposit date of payment.
     * @return the deposit date of payment.
     */
    public Date getDepositDate() {
        return depositDate;
    }

    /**
     * Sets the deposit date of payment.
     * @param depositDate
     *            the deposit date of payment.
     */
    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    /**
     * Gets the batch number of payment.
     * @return the batch number of payment.
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the batch number of payment.
     * @param batchNumber
     *            the batch number of payment.
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the block number of payment.
     * @return the block number of payment.
     */
    public String getBlockNumber() {
        return blockNumber;
    }

    /**
     * Sets the block number of payment.
     * @param blockNumber
     *            the block number of payment.
     */
    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    /**
     * Gets the sequence number of payment.
     * @return the sequence number of payment.
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequence number of payment.
     * @param sequenceNumber
     *            the sequence number of payment.
     */
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the claim number of payment.
     * @return the claim number of payment.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number of payment.
     * @param claimNumber
     *            the claim number of payment.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the flag specifying whether this payment is ACH.
     * @return the flag specifying whether this payment is ACH.
     */
    public boolean isAch() {
        return ach;
    }

    /**
     * Sets the flag specifying whether this payment is ACH.
     * @param ach
     *            the flag specifying whether this payment is ACH.
     */
    public void setAch(boolean ach) {
        this.ach = ach;
    }

    /**
     * Gets the amount of payment.
     * @return the amount of payment.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of payment.
     * @param amount
     *            the amount of payment.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the birth date of claimant of the payment.
     * @return the birth date of claimant of the payment.
     */
    public Date getClaimantBirthdate() {
        return claimantBirthdate;
    }

    /**
     * Sets the birth date of claimant of the payment.
     * @param claimantBirthdate
     *            the birth date of claimant of the payment.
     */
    public void setClaimantBirthdate(Date claimantBirthdate) {
        this.claimantBirthdate = claimantBirthdate;
    }

    /**
     * Gets the claimant of the payment.
     * @return the claimant of the payment.
     */
    public String getClaimant() {
        return claimant;
    }

    /**
     * Sets the claimant of the payment.
     * @param claimant
     *            the claimant of the payment.
     */
    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    /**
     * Gets the balance of the payment.
     * @return the balance of the payment.
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the balance of the payment.
     * @param accountBalance
     *            the balance of the payment.
     */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * Gets the account status of the payment.
     * @return the account status of the payment.
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the account status of the payment.
     * @param accountStatus
     *            the account status of the payment.
     */
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Gets the claim number of master account of the payment.
     * @return the claim number of master account of the payment.
     */
    public String getMasterClaimNumber() {
        return masterClaimNumber;
    }

    /**
     * Sets the claim number of master account of the payment.
     * @param masterClaimNumber
     *            the claim number of master account of the payment.
     */
    public void setMasterClaimNumber(String masterClaimNumber) {
        this.masterClaimNumber = masterClaimNumber;
    }

    /**
     * Gets the birth date of claimant of the payment.
     * @return the birth date of claimant of the payment.
     */
    public Date getMasterClaimantBirthdate() {
        return masterClaimantBirthdate;
    }

    /**
     * Sets the birth date of claimant of the payment.
     * @param masterClaimantBirthdate
     *            the birth date of claimant of the payment.
     */
    public void setMasterClaimantBirthdate(Date masterClaimantBirthdate) {
        this.masterClaimantBirthdate = masterClaimantBirthdate;
    }

    /**
     * Gets the status of master account of the payment.
     * @return the status of master account of the payment.
     */
    public AccountStatus getMasterAccountStatus() {
        return masterAccountStatus;
    }

    /**
     * Sets the status of master account of the payment.
     * @param masterAccountStatus
     *            the status of master account of the payment.
     */
    public void setMasterAccountStatus(AccountStatus masterAccountStatus) {
        this.masterAccountStatus = masterAccountStatus;
    }

    /**
     * Gets the balance of master account of the payment.
     * @return the balance of master account of the payment.
     */
    public BigDecimal getMasterAccountBalance() {
        return masterAccountBalance;
    }

    /**
     * Sets the balance of master account of the payment.
     * @param masterAccountBalance
     *            the balance of master account of the payment.
     */
    public void setMasterAccountBalance(BigDecimal masterAccountBalance) {
        this.masterAccountBalance = masterAccountBalance;
    }

    /**
     * Gets the id of account of the payment.
     * @return the id of account of the payment.
     */
    public long getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the id of account of the payment.
     * @param paymentId
     *            the id of account of the payment.
     */
    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Converts the entity to a string.
     * @return the string with entity data.
     */
    @Override
    public String toString() {
        return Helper.toString(this);
    }

    /**
     * Get transaction date
     * 
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return this.transactionDate;
    }

    /**
     * Set transaction date
     * 
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

}

