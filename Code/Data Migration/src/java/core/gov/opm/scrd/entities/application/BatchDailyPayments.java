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

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This is the class representing the batch daily payments.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class BatchDailyPayments extends IdentifiableEntity {
    /**
     * <p>
     * Represents the audit batch log id. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Long auditBatchLogId;
    /**
     * <p>
     * Represents the pay transaction key. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer payTransactionKey;
    /**
     * <p>
     * Represents the number payment today. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer numberPaymentToday;
    /**
     * <p>
     * Represents the batch time. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Date batchTime;
    /**
     * <p>
     * Represents the account status. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private AccountStatus accountStatus;
    /**
     * <p>
     * Represents the pay trans status code. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer payTransStatusCode;
    /**
     * <p>
     * Represents the claim number. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String claimNumber;
    /**
     * <p>
     * Represents the account balance. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal accountBalance;
    /**
     * <p>
     * Represents the over payment amount. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal overPaymentAmount;
    /**
     * <p>
     * Represents the ach payment flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean achPayment;
    /**
     * <p>
     * Represents the ach stop letter flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean achStopLetter;
    /**
     * <p>
     * Represents the print invoice flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean printInvoice;
    /**
     * <p>
     * Represents the refund required flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean refundRequired;
    /**
     * <p>
     * Represents the reversed payment flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean reversedPayment;
    /**
     * <p>
     * Represents the update to completed flag. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Boolean updateToCompleted;
    /**
     * <p>
     * Represents the print initial bill flag. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Boolean printInitialBill;
    /**
     * <p>
     * Represents the latest batch flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean latestBatch;
    /**
     * <p>
     * Represents the error processing flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean errorProcessing;

    /**
     * Creates an instance of BatchDailyPayments.
     */
    public BatchDailyPayments() {
        // Empty
    }

    /**
     * Gets the audit batch log id.
     *
     * @return the audit batch log id.
     */
    public Long getAuditBatchLogId() {
        return auditBatchLogId;
    }

    /**
     * Sets the audit batch log id.
     *
     * @param auditBatchLogId
     *            the audit batch log id.
     */
    public void setAuditBatchLogId(Long auditBatchLogId) {
        this.auditBatchLogId = auditBatchLogId;
    }

    /**
     * Gets the pay transaction key.
     *
     * @return the pay transaction key.
     */
    public Integer getPayTransactionKey() {
        return payTransactionKey;
    }

    /**
     * Sets the pay transaction key.
     *
     * @param payTransactionKey
     *            the pay transaction key.
     */
    public void setPayTransactionKey(Integer payTransactionKey) {
        this.payTransactionKey = payTransactionKey;
    }

    /**
     * Gets the number payment today.
     *
     * @return the number payment today.
     */
    public Integer getNumberPaymentToday() {
        return numberPaymentToday;
    }

    /**
     * Sets the number payment today.
     *
     * @param numberPaymentToday
     *            the number payment today.
     */
    public void setNumberPaymentToday(Integer numberPaymentToday) {
        this.numberPaymentToday = numberPaymentToday;
    }

    /**
     * Gets the batch time.
     *
     * @return the batch time.
     */
    public Date getBatchTime() {
        return batchTime;
    }

    /**
     * Sets the batch time.
     *
     * @param batchTime
     *            the batch time.
     */
    public void setBatchTime(Date batchTime) {
        this.batchTime = batchTime;
    }

    /**
     * Gets the account status.
     *
     * @return the account status.
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the account status.
     *
     * @param accountStatus
     *            the account status.
     */
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Gets the pay trans status code.
     *
     * @return the pay trans status code.
     */
    public Integer getPayTransStatusCode() {
        return payTransStatusCode;
    }

    /**
     * Sets the pay trans status code.
     *
     * @param payTransStatusCode
     *            the pay trans status code.
     */
    public void setPayTransStatusCode(Integer payTransStatusCode) {
        this.payTransStatusCode = payTransStatusCode;
    }

    /**
     * Gets the claim number.
     *
     * @return the claim number.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number.
     *
     * @param claimNumber
     *            the claim number.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the account balance.
     *
     * @return the account balance.
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the account balance.
     *
     * @param accountBalance
     *            the account balance.
     */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * Gets the over payment amount.
     *
     * @return the over payment amount.
     */
    public BigDecimal getOverPaymentAmount() {
        return overPaymentAmount;
    }

    /**
     * Sets the over payment amount.
     *
     * @param overPaymentAmount
     *            the over payment amount.
     */
    public void setOverPaymentAmount(BigDecimal overPaymentAmount) {
        this.overPaymentAmount = overPaymentAmount;
    }

    /**
     * Gets the ach payment flag.
     *
     * @return the ach payment flag.
     */
    public Boolean getAchPayment() {
        return achPayment;
    }

    /**
     * Sets the ach payment flag.
     *
     * @param achPayment
     *            the ach payment flag.
     */
    public void setAchPayment(Boolean achPayment) {
        this.achPayment = achPayment;
    }

    /**
     * Gets the ach stop letter flag.
     *
     * @return the ach stop letter flag.
     */
    public Boolean getAchStopLetter() {
        return achStopLetter;
    }

    /**
     * Sets the ach stop letter flag.
     *
     * @param achStopLetter
     *            the ach stop letter flag.
     */
    public void setAchStopLetter(Boolean achStopLetter) {
        this.achStopLetter = achStopLetter;
    }

    /**
     * Gets the print invoice flag.
     *
     * @return the print invoice flag.
     */
    public Boolean getPrintInvoice() {
        return printInvoice;
    }

    /**
     * Sets the print invoice flag.
     *
     * @param printInvoice
     *            the print invoice flag.
     */
    public void setPrintInvoice(Boolean printInvoice) {
        this.printInvoice = printInvoice;
    }

    /**
     * Gets the refund required flag.
     *
     * @return the refund required flag.
     */
    public Boolean getRefundRequired() {
        return refundRequired;
    }

    /**
     * Sets the refund required flag.
     *
     * @param refundRequired
     *            the refund required flag.
     */
    public void setRefundRequired(Boolean refundRequired) {
        this.refundRequired = refundRequired;
    }

    /**
     * Gets the reversed payment flag.
     *
     * @return the reversed payment flag.
     */
    public Boolean getReversedPayment() {
        return reversedPayment;
    }

    /**
     * Sets the reversed payment flag.
     *
     * @param reversedPayment
     *            the reversed payment flag.
     */
    public void setReversedPayment(Boolean reversedPayment) {
        this.reversedPayment = reversedPayment;
    }

    /**
     * Gets the update to completed flag.
     *
     * @return the update to completed flag.
     */
    public Boolean getUpdateToCompleted() {
        return updateToCompleted;
    }

    /**
     * Sets the update to completed flag.
     *
     * @param updateToCompleted
     *            the update to completed flag.
     */
    public void setUpdateToCompleted(Boolean updateToCompleted) {
        this.updateToCompleted = updateToCompleted;
    }

    /**
     * Gets the print initial bill flag.
     *
     * @return the print initial bill flag.
     */
    public Boolean getPrintInitialBill() {
        return printInitialBill;
    }

    /**
     * Sets the print initial bill flag.
     *
     * @param printInitialBill
     *            the print initial bill flag.
     */
    public void setPrintInitialBill(Boolean printInitialBill) {
        this.printInitialBill = printInitialBill;
    }

    /**
     * Gets the latest batch flag.
     *
     * @return the latest batch flag.
     */
    public Boolean getLatestBatch() {
        return latestBatch;
    }

    /**
     * Sets the latest batch flag.
     *
     * @param latestBatch
     *            the latest batch flag.
     */
    public void setLatestBatch(Boolean latestBatch) {
        this.latestBatch = latestBatch;
    }

    /**
     * Gets the error processing flag.
     *
     * @return the error processing flag.
     */
    public Boolean getErrorProcessing() {
        return errorProcessing;
    }

    /**
     * Sets the error processing flag.
     *
     * @param errorProcessing
     *            the error processing flag.
     */
    public void setErrorProcessing(Boolean errorProcessing) {
        this.errorProcessing = errorProcessing;
    }
}
