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
import gov.opm.scrd.entities.lookup.CalculationStatus;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * This is the class representing the aggregated result of the calculation.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added fields: paymentOrder, interestAccrualDate</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.1
 */
public class CalculationResult extends IdentifiableEntity {
    /**
     * <p>
     * Represents the individual result of calculation result. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private List<CalculationResultItem> items;
    /**
     * <p>
     * Represents the redeposit items of calculation result. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private List<Redeposit> redeposits;
    /**
     * <p>
     * Represents the dedeposit items of calculation result. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private List<Dedeposit> dedeposits;
    /**
     * <p>
     * Represents the summary of calculation result. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private SummaryData summary;
    /**
     * <p>
     * Represents the status of calculation result. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private CalculationStatus calculationStatus;
    /**
     * <p>
     * Represents the flag specifying whether calculation is official(there can be a single official calculation). It is
     * managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean official;
    /**
     * <p>
     * Represents the flag specifying whether calculation can be applied to real payments, so user can trigger the bill.
     * It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean applyToRealPayments;
    /**
     * <p>
     * Represents the payment order of calculation result. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Integer paymentOrder;
    /**
     * <p>
     * Represents the date of interest accrual of calculation result. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Date interestAccrualDate;

    /**
     * Creates an instance of CalculationResult.
     */
    public CalculationResult() {
        // Empty
    }

    /**
     * Gets the individual result of calculation result.
     *
     * @return the individual result of calculation result.
     */
    public List<CalculationResultItem> getItems() {
        return items;
    }

    /**
     * Sets the individual result of calculation result.
     *
     * @param items
     *            the individual result of calculation result.
     */
    public void setItems(List<CalculationResultItem> items) {
        this.items = items;
    }

    /**
     * Gets the redeposit items of calculation result.
     *
     * @return the redeposit items of calculation result.
     */
    public List<Redeposit> getRedeposits() {
        return redeposits;
    }

    /**
     * Sets the redeposit items of calculation result.
     *
     * @param redeposits
     *            the redeposit items of calculation result.
     */
    public void setRedeposits(List<Redeposit> redeposits) {
        this.redeposits = redeposits;
    }

    /**
     * Gets the dedeposit items of calculation result.
     *
     * @return the dedeposit items of calculation result.
     */
    public List<Dedeposit> getDedeposits() {
        return dedeposits;
    }

    /**
     * Sets the dedeposit items of calculation result.
     *
     * @param dedeposits
     *            the dedeposit items of calculation result.
     */
    public void setDedeposits(List<Dedeposit> dedeposits) {
        this.dedeposits = dedeposits;
    }

    /**
     * Gets the summary of calculation result.
     *
     * @return the summary of calculation result.
     */
    public SummaryData getSummary() {
        return summary;
    }

    /**
     * Sets the summary of calculation result.
     *
     * @param summary
     *            the summary of calculation result.
     */
    public void setSummary(SummaryData summary) {
        this.summary = summary;
    }

    /**
     * Gets the status of calculation result.
     *
     * @return the status of calculation result.
     */
    public CalculationStatus getCalculationStatus() {
        return calculationStatus;
    }

    /**
     * Sets the status of calculation result.
     *
     * @param calculationStatus
     *            the status of calculation result.
     */
    public void setCalculationStatus(CalculationStatus calculationStatus) {
        this.calculationStatus = calculationStatus;
    }

    /**
     * Gets the flag specifying whether calculation is official(there can be a single official calculation).
     *
     * @return the flag specifying whether calculation is official(there can be a single official calculation).
     */
    public boolean isOfficial() {
        return official;
    }

    /**
     * Sets the flag specifying whether calculation is official(there can be a single official calculation).
     *
     * @param official
     *            the flag specifying whether calculation is official(there can be a single official calculation).
     */
    public void setOfficial(boolean official) {
        this.official = official;
    }

    /**
     * Gets the flag specifying whether calculation can be applied to real payments, so user can trigger the bill.
     *
     * @return the flag specifying whether calculation can be applied to real payments, so user can trigger the bill.
     */
    public boolean isApplyToRealPayments() {
        return applyToRealPayments;
    }

    /**
     * Sets the flag specifying whether calculation can be applied to real payments, so user can trigger the bill.
     *
     * @param applyToRealPayments
     *            the flag specifying whether calculation can be applied to real payments, so user can trigger the bill.
     */
    public void setApplyToRealPayments(boolean applyToRealPayments) {
        this.applyToRealPayments = applyToRealPayments;
    }

    /**
     * Gets the payment order of calculation result.
     *
     * @return the payment order of calculation result.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Integer getPaymentOrder() {
        return paymentOrder;
    }

    /**
     * Sets the payment order of calculation result.
     *
     * @param paymentOrder
     *            the payment order of calculation result.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setPaymentOrder(Integer paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    /**
     * Gets the date of interest accrual of calculation result.
     *
     * @return the date of interest accrual of calculation result.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Date getInterestAccrualDate() {
        return interestAccrualDate;
    }

    /**
     * Sets the date of interest accrual of calculation result.
     *
     * @param interestAccrualDate
     *            the date of interest accrual of calculation result.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setInterestAccrualDate(Date interestAccrualDate) {
        this.interestAccrualDate = interestAccrualDate;
    }
}
