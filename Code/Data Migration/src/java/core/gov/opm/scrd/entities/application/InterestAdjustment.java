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

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This is the class representing the view of the Payment entity when the type corresponds to the interest adjustment.
 * This entity is not persisted.
 * </p>
 *
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Changed claimNumber to String type.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class InterestAdjustment {
    /**
     * <p>
     * Represents the claim number of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String claimNumber;
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
     * Represents the post deposit amount of the payment. It is managed with a getter and setterRepresents the post
     * redeposit amount of the payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal postRedepositAmount;
    /**
     * <p>
     * Represents the amount of payment. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal paymentTransactionAmount;
    /**
     * <p>
     * Represents the deposit date of payment. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date adjustmentDate;
    /**
     * <p>
     * Represents the account status of the payment. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private AccountStatus accountStatus;
    /**
     * <p>
     * Represents the birth date of account holder with this payment. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Date accountHolderBirthdate;
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
     * Creates an instance of InterestAdjustment.
     */
    public InterestAdjustment() {
        // Empty
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
     * Gets the post deposit amount of the payment.
     *
     * @return the post deposit amount of the payment.
     */
    public BigDecimal getPostRedepositAmount() {
        return postRedepositAmount;
    }

    /**
     * Sets the post deposit amount of the payment.
     *
     * @param postRedepositAmount
     *            the post deposit amount of the payment.
     */
    public void setPostRedepositAmount(BigDecimal postRedepositAmount) {
        this.postRedepositAmount = postRedepositAmount;
    }

    /**
     * Gets the amount of payment.
     *
     * @return the amount of payment.
     */
    public BigDecimal getPaymentTransactionAmount() {
        return paymentTransactionAmount;
    }

    /**
     * Sets the amount of payment.
     *
     * @param paymentTransactionAmount
     *            the amount of payment.
     */
    public void setPaymentTransactionAmount(BigDecimal paymentTransactionAmount) {
        this.paymentTransactionAmount = paymentTransactionAmount;
    }

    /**
     * Gets the deposit date of payment.
     *
     * @return the deposit date of payment.
     */
    public Date getAdjustmentDate() {
        return adjustmentDate;
    }

    /**
     * Sets the deposit date of payment.
     *
     * @param adjustmentDate
     *            the deposit date of payment.
     */
    public void setAdjustmentDate(Date adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
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
     * Converts the entity to a string.
     *
     * @return the string with entity data.
     */
    @Override
    public String toString() {
        return Helper.toString(this);
    }
}
