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

import java.util.Date;
import java.util.List;

/**
 * <p>
 * This is the class representing the summary of all billing data in the account. It also contains the list of each
 * individual billing item of the account.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class BillingSummary extends IdentifiableEntity {
    /**
     * <p>
     * Represents timestamp of the computation the of billing summary. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Date computedDate;
    /**
     * <p>
     * Represents the last deposit of billing items of billing summary. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Date lastDepositDate;
    /**
     * <p>
     * Represents the first billing timestamp of billing summary. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private Date firstBillingDate;
    /**
     * <p>
     * Represents the timestamp of last interest calculation of billing summary. It is managed with a getter and setter.
     * It may have any value. It is fully mutable.
     * </p>
     */
    private Date lastInterestCalculation;
    /**
     * <p>
     * Represents the transaction type of billing summary. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String transactionType;
    /**
     * <p>
     * Represents the timestamp of last transaction of billing summary. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Date lastTransactionDate;
    /**
     * <p>
     * Represents the list of individual billings of billing summary. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private List<Billing> billings;
    /**
     * <p>
     * Represents the flag specifying whether ACH payments should be stopped. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private Boolean stopACHPayments;

    /**
     * Creates an instance of BillingSummary.
     */
    public BillingSummary() {
        // Empty
    }

    /**
     * Gets the mestamp of the computation the of billing summary.
     *
     * @return the mestamp of the computation the of billing summary.
     */
    public Date getComputedDate() {
        return computedDate;
    }

    /**
     * Sets the mestamp of the computation the of billing summary.
     *
     * @param computedDate
     *            the mestamp of the computation the of billing summary.
     */
    public void setComputedDate(Date computedDate) {
        this.computedDate = computedDate;
    }

    /**
     * Gets the last deposit of billing items of billing summary.
     *
     * @return the last deposit of billing items of billing summary.
     */
    public Date getLastDepositDate() {
        return lastDepositDate;
    }

    /**
     * Sets the last deposit of billing items of billing summary.
     *
     * @param lastDepositDate
     *            the last deposit of billing items of billing summary.
     */
    public void setLastDepositDate(Date lastDepositDate) {
        this.lastDepositDate = lastDepositDate;
    }

    /**
     * Gets the first billing timestamp of billing summary.
     *
     * @return the first billing timestamp of billing summary.
     */
    public Date getFirstBillingDate() {
        return firstBillingDate;
    }

    /**
     * Sets the first billing timestamp of billing summary.
     *
     * @param firstBillingDate
     *            the first billing timestamp of billing summary.
     */
    public void setFirstBillingDate(Date firstBillingDate) {
        this.firstBillingDate = firstBillingDate;
    }

    /**
     * Gets the timestamp of last interest calculation of billing summary.
     *
     * @return the timestamp of last interest calculation of billing summary.
     */
    public Date getLastInterestCalculation() {
        return lastInterestCalculation;
    }

    /**
     * Sets the timestamp of last interest calculation of billing summary.
     *
     * @param lastInterestCalculation
     *            the timestamp of last interest calculation of billing summary.
     */
    public void setLastInterestCalculation(Date lastInterestCalculation) {
        this.lastInterestCalculation = lastInterestCalculation;
    }

    /**
     * Gets the transaction type of billing summary.
     *
     * @return the transaction type of billing summary.
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the transaction type of billing summary.
     *
     * @param transactionType
     *            the transaction type of billing summary.
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets the timestamp of last transaction of billing summary.
     *
     * @return the timestamp of last transaction of billing summary.
     */
    public Date getLastTransactionDate() {
        return lastTransactionDate;
    }

    /**
     * Sets the timestamp of last transaction of billing summary.
     *
     * @param lastTransactionDate
     *            the timestamp of last transaction of billing summary.
     */
    public void setLastTransactionDate(Date lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    /**
     * Gets the list of individual billings of billing summary.
     *
     * @return the list of individual billings of billing summary.
     */
    public List<Billing> getBillings() {
        return billings;
    }

    /**
     * Sets the list of individual billings of billing summary.
     *
     * @param billings
     *            the list of individual billings of billing summary.
     */
    public void setBillings(List<Billing> billings) {
        this.billings = billings;
    }

    /**
     * Gets the flag specifying whether ACH payments should be stopped.
     *
     * @return the flag specifying whether ACH payments should be stopped.
     */
    public Boolean getStopACHPayments() {
        return stopACHPayments;
    }

    /**
     * Sets the flag specifying whether ACH payments should be stopped.
     *
     * @param stopACHPayments
     *            the flag specifying whether ACH payments should be stopped.
     */
    public void setStopACHPayments(Boolean stopACHPayments) {
        this.stopACHPayments = stopACHPayments;
    }
}
