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
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.Role;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This is the class representing the view of the Payment entity when the type corresponds to the pending payment. This
 * entity is not persisted.
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
 * <em>Changes in 1.2 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Add userName and userRole fields</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax, liuliquan
 * @version 1.2
 */
public class PendingPayment {
    /**
     * <p>
     * Represents the status of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private PaymentStatus paymentStatus;
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
     * Represents the amount of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal amount;
    /**
     * <p>
     * Represents the flag specifying whether the payment should be applied to GL file. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean applyToGL;
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
     * Represents the account status of the payment. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private AccountStatus accountStatus;
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
     * Represents the id of account of the payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private long paymentId;
    /**
     * <p>
     * Represents the user name of the payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private String userName;
    /**
     * <p>
     * Represents the user role of the user. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private Role userRole;

    /**
     * Creates an instance of PendingPayment.
     */
    public PendingPayment() {
        // Empty
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
     * Gets the id of account of the payment.
     *
     * @return the id of account of the payment.
     */
    public long getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the id of account of the payment.
     *
     * @param paymentId
     *            the id of account of the payment.
     */
    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Getter method for property <tt>userName</tt>.
     * @return property value of userName
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property <tt>userName</tt>.
     * @param userName value to be assigned to property userName
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method for property <tt>userRole</tt>.
     * @return property value of userRole
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    public Role getUserRole() {
        return userRole;
    }

    /**
     * Setter method for property <tt>userRole</tt>.
     * @param userRole value to be assigned to property userRole
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    /**
     * Converts the entity to a string.
     *
     * @return the string with entity data.
     */
    @Override
    public String toString() {
        return Helper.toString(this);
    }
}
