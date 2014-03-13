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

import gov.opm.scrd.entities.common.BasicPagedSearchFilter;
import gov.opm.scrd.entities.lookup.ComparisonType;
import gov.opm.scrd.entities.lookup.DepositComparisonType;
import gov.opm.scrd.entities.lookup.PaymentAppliance;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PaymentType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Represents search filter used for searching payments.
 * </p>
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Changed to extend BasicPagedSearchFilter.</li>
 * </ol>
 * </p>
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 * Changed in the OPM - Frontend - Payments Module Assembly:
 * Add the claimNumber(or CSD) field for search.
 * Add depositeStartDate and depositeEndDate for search.
 * Add PaymentAppliance for search.
 * 
 * 
 * @author faeton, sparemax, TCSASSEMBLER
 * @version 1.1
 */
public class PaymentSearchFilter extends BasicPagedSearchFilter {
    /**
     * <p>
     * Represents the comparison type of payment amount of filter. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private ComparisonType amountComparisonType;

    /**
     * <p>
     * Represents the payment amount to compare of filter. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal amount;

    /**
     * <p>
     * Represents the batch number of filter. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String batchNumber;

    /**
     * <p>
     * Represents the block number of filter. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String blockNumber;

    /**
     * <p>
     * Represents the sequence number of filter. it is managed with a getter and setter It may have any value It is
     * fully mutable.
     * </p>
     */
    private String sequenceNumber;

    /**
     * <p>
     * Represents the flag specifying whether suspense is resolved for payment of filter. It is managed with a getter
     * and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Boolean resolvedSuspense;

    /**
     * <p>
     * Represents the payment status of filter. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private PaymentStatus paymentStatus;

    /**
     * <p>
     * Represents the payment type of filter. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private PaymentType paymentType;

    /**
     * <p>
     * Represents the comparison type for deposit date of filter. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private DepositComparisonType depositComparisonType;

    /**
     * <p>
     * Represents the deposit date to compare of filter. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private Date depositDate;

    /**
     * The claim number to search
     */
    private String claimNumber;

    /**
     * Represents the deposit end date attribute.It's set and accessed in the set/get methods.
     * It can be any value.
     * The default value is null.
     */
    private Date depositEndDate;

    /**
     * Represents the deposit start date attribute.It's set and accessed in the set/get methods.
     * It can be any value.
     * The default value is null.
     */
    private Date depositStartDate;
    
    /**
     * The payment appliance field.
     * 
     */
    private PaymentAppliance paymentAppliance;

    /**
     * Creates an instance of PaymentSearchFilter.
     */
    public PaymentSearchFilter() {
        // Empty
    }
    
    /**
     * Get the payment appliance.
     * 
     * @return the payment appliance
     */
    public PaymentAppliance getPaymentAppliance() {
        return this.paymentAppliance;
    }
    
    /**
     * Set the payment appliance.
     * 
     * @param paymentAppliance to set.
     */
    public void setPaymentAppliance(PaymentAppliance paymentAppliance) {
        this.paymentAppliance = paymentAppliance;
    }

    /**
     * Set the claim number.
     * @return the claim number.
     */
    public String getClaimNumber() {
        return this.claimNumber;
    }

    /**
     * Get the claim number.
     * @param claimNumber to set.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the comparison type of payment amount of filter.
     * @return the comparison type of payment amount of filter.
     */
    public ComparisonType getAmountComparisonType() {
        return amountComparisonType;
    }

    /**
     * Sets the comparison type of payment amount of filter.
     * @param amountComparisonType
     *            the comparison type of payment amount of filter.
     */
    public void setAmountComparisonType(ComparisonType amountComparisonType) {
        this.amountComparisonType = amountComparisonType;
    }

    /**
     * Gets the payment amount to compare of filter.
     * @return the payment amount to compare of filter.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the payment amount to compare of filter.
     * @param amount
     *            the payment amount to compare of filter.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the batch number of filter.
     * @return the batch number of filter.
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the batch number of filter.
     * @param batchNumber
     *            the batch number of filter.
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the block number of filter.
     * @return the block number of filter.
     */
    public String getBlockNumber() {
        return blockNumber;
    }

    /**
     * Sets the block number of filter.
     * @param blockNumber
     *            the block number of filter.
     */
    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    /**
     * Gets the sequence number of filter.
     * @return the sequence number of filter.
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequence number of filter.
     * @param sequenceNumber
     *            the sequence number of filter.
     */
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the flag specifying whether suspense is resolved for payment of filter.
     * @return the flag specifying whether suspense is resolved for payment of filter.
     */
    public Boolean getResolvedSuspense() {
        return resolvedSuspense;
    }

    /**
     * Sets the flag specifying whether suspense is resolved for payment of filter.
     * @param resolvedSuspense
     *            the flag specifying whether suspense is resolved for payment of filter.
     */
    public void setResolvedSuspense(Boolean resolvedSuspense) {
        this.resolvedSuspense = resolvedSuspense;
    }

    /**
     * Gets the payment status of filter.
     * @return the payment status of filter.
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the payment status of filter.
     * @param paymentStatus
     *            the payment status of filter.
     */
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Gets the payment type of filter.
     * @return the payment type of filter.
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type of filter.
     * @param paymentType
     *            the payment type of filter.
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the comparison type for deposit date of filter.
     * @return the comparison type for deposit date of filter.
     */
    public DepositComparisonType getDepositComparisonType() {
        return depositComparisonType;
    }

    /**
     * Sets the comparison type for deposit date of filter.
     * @param depositComparisonType
     *            the comparison type for deposit date of filter.
     */
    public void setDepositComparisonType(DepositComparisonType depositComparisonType) {
        this.depositComparisonType = depositComparisonType;
    }

    /**
     * Gets the deposit date to compare of filter.
     * @return the deposit date to compare of filter.
     */
    public Date getDepositDate() {
        return depositDate;
    }

    /**
     * Sets the deposit date to compare of filter.
     * @param depositDate
     *            the deposit date to compare of filter.
     */
    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    /**
     * Get deposit end date
     * 
     * @return the depositEndDate
     */
    public Date getDepositEndDate() {
        return this.depositEndDate;
    }

    /**
     * Set deposit end date
     * 
     * @param depositEndDate the depositEndDate to set
     */
    public void setDepositEndDate(Date depositEndDate) {
        this.depositEndDate = depositEndDate;
    }

    /**
     * Get deposit start date
     * 
     * @return the depositStartDate
     */
    public Date getDepositStartDate() {
        return this.depositStartDate;
    }

    /**
     * Set deposit start date
     * 
     * @param depositStartDate the depositStartDate to set
     */
    public void setDepositStartDate(Date depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

}

