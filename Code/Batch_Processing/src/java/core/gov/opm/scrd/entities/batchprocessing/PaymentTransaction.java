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

package gov.opm.scrd.entities.batchprocessing;

import gov.opm.scrd.entities.common.IdentifiableEntity;

import java.math.BigDecimal;

import java.util.Date;

/**
 * <p>This class is simply a POJO containing payment transaction information.</p>
 * <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Reporting Payment Module Assembly):</em>
 * <ol>
 * <li>Added payTransactionKey</li>
 * </ol>
 * </p>
 * @author faeton, TCSASSEMBLER, j3_guile
 * @version 1.1
 */
public class PaymentTransaction extends IdentifiableEntity {
    /**
     * Represents the pay transaction key. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     *
     * @since 1.1
     */
    private Integer payTransactionKey;
    /**
     * Represents the pay trans batch number. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String payTransBatchNumber;
    /**
     * Represents the pay trans block number. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String payTransBlockNumber;
    /**
     * Represents the pay trans sequence number. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String payTransSequenceNumber;
    /**
     * Represents the scm claim number. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String scmClaimNumber;
    /**
     * Represents the scm date of birth. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date scmDateOfBirth;
    /**
     * Represents the pay trans payment amount. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private BigDecimal payTransPaymentAmount;
    /**
     * Represents the pay trans transaction date. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Date payTransTransactionDate;
    /**
     * Represents the pay trans status code. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Long payTransStatusCode;
    /**
     * Represents the pay trans status date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date payTransStatusDate;
    /**
     * Represents the technician user key. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Long technicianUserKey;
    /**
     * Represents the payment applied order code. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String paymentAppliedOrderCode;
    /**
     * Represents the post flag. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Boolean postFlag;
    /**
     * Represents the csd. It is accessible by getter and modified by setter. It can be any value. The default value is
     * null.
     */
    private String csd;
    /**
     * Represents if user inserted. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Boolean userInserted;
    /**
     * Represents if it is ach payment or not. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Boolean achPayment;
    /**
     * Represents the payment status code. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer paymentStatusCode;
    /**
     * Represents if resolved suspense or not. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Boolean resolvedSuspense;
    /**
     * Represents if the transaction is update to completed or not. It is accessible by getter and modified by setter.
     * It can be any value. The default value is null.
     */
    private Boolean updateToCompleted;

    /**
     * Instantiates a new payment transaction.
     */
    public PaymentTransaction() {
    }

    /**
     * Gets the pay trans batch number.
     *
     * @return the pay trans batch number
     */
    public String getPayTransBatchNumber() {
        return payTransBatchNumber;
    }

    /**
     * Sets the pay trans batch number.
     *
     * @param payTransBatchNumber the new pay trans batch number
     */
    public void setPayTransBatchNumber(String payTransBatchNumber) {
        this.payTransBatchNumber = payTransBatchNumber;
    }

    /**
     * Gets the pay trans block number.
     *
     * @return the pay trans block number
     */
    public String getPayTransBlockNumber() {
        return payTransBlockNumber;
    }

    /**
     * Sets the pay trans block number.
     *
     * @param payTransBlockNumber the new pay trans block number
     */
    public void setPayTransBlockNumber(String payTransBlockNumber) {
        this.payTransBlockNumber = payTransBlockNumber;
    }

    /**
     * Gets the pay trans sequence number.
     *
     * @return the pay trans sequence number
     */
    public String getPayTransSequenceNumber() {
        return payTransSequenceNumber;
    }

    /**
     * Sets the pay trans sequence number.
     *
     * @param payTransSequenceNumber the new pay trans sequence number
     */
    public void setPayTransSequenceNumber(String payTransSequenceNumber) {
        this.payTransSequenceNumber = payTransSequenceNumber;
    }

    /**
     * Gets the scm claim number.
     *
     * @return the scm claim number
     */
    public String getScmClaimNumber() {
        return scmClaimNumber;
    }

    /**
     * Sets the scm claim number.
     *
     * @param scmClaimNumber the new scm claim number
     */
    public void setScmClaimNumber(String scmClaimNumber) {
        this.scmClaimNumber = scmClaimNumber;
    }

    /**
     * Gets the scm date of birth.
     *
     * @return the scm date of birth
     */
    public Date getScmDateOfBirth() {
        return scmDateOfBirth;
    }

    /**
     * Sets the scm date of birth.
     *
     * @param scmDateOfBirth the new scm date of birth
     */
    public void setScmDateOfBirth(Date scmDateOfBirth) {
        this.scmDateOfBirth = scmDateOfBirth;
    }

    /**
     * Gets the pay trans payment amount.
     *
     * @return the pay trans payment amount
     */
    public BigDecimal getPayTransPaymentAmount() {
        return payTransPaymentAmount;
    }

    /**
     * Sets the pay trans payment amount.
     *
     * @param payTransPaymentAmount the new pay trans payment amount
     */
    public void setPayTransPaymentAmount(BigDecimal payTransPaymentAmount) {
        this.payTransPaymentAmount = payTransPaymentAmount;
    }

    /**
     * Gets the pay trans transaction date.
     *
     * @return the pay trans transaction date
     */
    public Date getPayTransTransactionDate() {
        return payTransTransactionDate;
    }

    /**
     * Sets the pay trans transaction date.
     *
     * @param payTransTransactionDate the new pay trans transaction date
     */
    public void setPayTransTransactionDate(Date payTransTransactionDate) {
        this.payTransTransactionDate = payTransTransactionDate;
    }

    /**
     * Gets the pay trans status code.
     *
     * @return the pay trans status code
     */
    public Long getPayTransStatusCode() {
        return payTransStatusCode;
    }

    /**
     * Sets the pay trans status code.
     *
     * @param payTransStatusCode the new pay trans status code
     */
    public void setPayTransStatusCode(Long payTransStatusCode) {
        this.payTransStatusCode = payTransStatusCode;
    }

    /**
     * Gets the pay trans status date.
     *
     * @return the pay trans status date
     */
    public Date getPayTransStatusDate() {
        return payTransStatusDate;
    }

    /**
     * Sets the pay trans status date.
     *
     * @param payTransStatusDate the new pay trans status date
     */
    public void setPayTransStatusDate(Date payTransStatusDate) {
        this.payTransStatusDate = payTransStatusDate;
    }

    /**
     * Gets the technician user key.
     *
     * @return the technician user key
     */
    public Long getTechnicianUserKey() {
        return technicianUserKey;
    }

    /**
     * Sets the technician user key.
     *
     * @param technicianUserKey the new technician user key
     */
    public void setTechnicianUserKey(Long technicianUserKey) {
        this.technicianUserKey = technicianUserKey;
    }

    /**
     * Gets the payment applied order code.
     *
     * @return the payment applied order code
     */
    public String getPaymentAppliedOrderCode() {
        return paymentAppliedOrderCode;
    }

    /**
     * Sets the payment applied order code.
     *
     * @param paymentAppliedOrderCode the new payment applied order code
     */
    public void setPaymentAppliedOrderCode(String paymentAppliedOrderCode) {
        this.paymentAppliedOrderCode = paymentAppliedOrderCode;
    }

    /**
     * Gets the post flag.
     *
     * @return the post flag
     */
    public Boolean getPostFlag() {
        return postFlag;
    }

    /**
     * Sets the post flag.
     *
     * @param postFlag the new post flag
     */
    public void setPostFlag(Boolean postFlag) {
        this.postFlag = postFlag;
    }

    /**
     * Gets the csd.
     *
     * @return the csd
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the csd.
     *
     * @param csd the new csd
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the flag specifying whether it is user inserted.
     *
     * @return the flag specifying whether it is user inserted
     */
    public Boolean getUserInserted() {
        return userInserted;
    }

    /**
     * Sets the flag specifying whether it is user inserted.
     *
     * @param userInserted the flag specifying whether it is user inserted
     */
    public void setUserInserted(Boolean userInserted) {
        this.userInserted = userInserted;
    }

    /**
     * Gets the flag specifying whether it is ach payment.
     *
     * @return the flag specifying whether it is ach payment
     */
    public Boolean getAchPayment() {
        return achPayment;
    }

    /**
     * Sets the flag specifying whether it is ach payment.
     *
     * @param achPayment the flag specifying whether it is ach payment
     */
    public void setAchPayment(Boolean achPayment) {
        this.achPayment = achPayment;
    }

    /**
     * Gets the payment status code.
     *
     * @return the payment status code
     */
    public Integer getPaymentStatusCode() {
        return paymentStatusCode;
    }

    /**
     * Sets the payment status code.
     *
     * @param paymentStatusCode the new payment status code
     */
    public void setPaymentStatusCode(Integer paymentStatusCode) {
        this.paymentStatusCode = paymentStatusCode;
    }

    /**
     * Gets the flag specifying whether it is resolved suspense.
     *
     * @return the flag specifying whether it is resolved suspense
     */
    public Boolean getResolvedSuspense() {
        return resolvedSuspense;
    }

    /**
     * Sets the flag specifying whether it is resolved suspense.
     *
     * @param resolvedSuspense the flag specifying whether it is resolved suspense
     */
    public void setResolvedSuspense(Boolean resolvedSuspense) {
        this.resolvedSuspense = resolvedSuspense;
    }

    /**
     * Gets the flag specifying whether it is resolved suspense.
     *
     * @return the flag specifying whether it is resolved suspense
     */
    public Boolean getUpdateToCompleted() {
        return updateToCompleted;
    }

    /**
     * Sets the flag specifying whether it is resolved suspense.
     *
     * @param updateToCompleted the flag specifying whether it is resolved suspense
     */
    public void setUpdateToCompleted(Boolean updateToCompleted) {
        this.updateToCompleted = updateToCompleted;
    }
    
    /**
     * Gets the value of the field <code>payTransactionKey</code>.
     * @return the payTransactionKey
     * @since 1.1
     */
    public Integer getPayTransactionKey() {
        return payTransactionKey;
    }

    /**
     * Sets the value of the field <code>payTransactionKey</code>.
     * @param payTransactionKey the payTransactionKey to set
     * @since 1.1
     */
    public void setPayTransactionKey(Integer payTransactionKey) {
        this.payTransactionKey = payTransactionKey;
    }
}
