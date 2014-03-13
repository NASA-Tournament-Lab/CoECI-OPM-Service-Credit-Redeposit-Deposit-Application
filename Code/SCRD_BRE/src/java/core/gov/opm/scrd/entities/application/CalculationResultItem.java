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
import gov.opm.scrd.entities.lookup.CalculationResultItemUpdateStatus;
import gov.opm.scrd.entities.lookup.DepositType;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This is the class representing the single item of the aggregated result of the calculation.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Release I Assembly 1.0):</em>
 * <ul>
 * <li>add field retirementType, serviceCategeory</li>
 * <li>chanage field effectiveDate to refundDate</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax, bannie2492
 * @version 1.0
 */
public class CalculationResultItem extends IdentifiableEntity {
    /**
     * <p>
     * Represents the start date of calculation result item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Date startDate;
    /**
     * <p>
     * Represents the end date of calculation result item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Date endDate;
    /**
     * <p>
     * Represents the mid date of calculation result item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Date midDate;
    /**
     * <p>
     * Represents the effective date of calculation result item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Date effectiveDate;
    /**
     * <p>
     * Represents the period type of calculation result item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private PeriodType periodType;
    /**
     * <p>
     * Represents the deduction amount of calculation result item. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private BigDecimal deductionAmount;
    /**
     * <p>
     * Represents the total interest of calculation result item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal totalInterest;
    /**
     * <p>
     * Represents the amount of applied payments of calculation result item. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private BigDecimal paymentsApplied;
    /**
     * <p>
     * Represents the balance of calculation result item. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private BigDecimal balance;

    /**
     * Represents the update status.
     */
    private CalculationResultItemUpdateStatus status;

    /**
     * Represents the version.
     */
    private Integer version;

    /**
     * Represents the line.
     */
    private Integer line;

    /**
     * <p>
     * Represents the balance of calculation result item. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    private RetirementType retirementType;

    /**
     * Represents the service category.
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    private DepositType serviceCategory;

    /**
     * Creates an instance of CalculationResultItem.
     */
    public CalculationResultItem() {
        // Empty
    }

    /**
     * Gets the start date of calculation result item.
     *
     * @return the start date of calculation result item.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of calculation result item.
     *
     * @param startDate
     *            the start date of calculation result item.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of calculation result item.
     *
     * @return the end date of calculation result item.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of calculation result item.
     *
     * @param endDate
     *            the end date of calculation result item.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the mid date of calculation result item.
     *
     * @return the mid date of calculation result item.
     */
    public Date getMidDate() {
        return midDate;
    }

    /**
     * Sets the mid date of calculation result item.
     *
     * @param midDate
     *            the mid date of calculation result item.
     */
    public void setMidDate(Date midDate) {
        this.midDate = midDate;
    }

    /**
     * Gets the effective date of calculation result item.
     *
     * @return the effective date of calculation result item.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the effective date of calculation result item.
     *
     * @param effectiveDate
     *            the effective date of calculation result item.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Gets the period type of calculation result item.
     *
     * @return the period type of calculation result item.
     */
    public PeriodType getPeriodType() {
        return periodType;
    }

    /**
     * Sets the period type of calculation result item.
     *
     * @param periodType
     *            the period type of calculation result item.
     */
    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    /**
     * Gets the deduction amount of calculation result item.
     *
     * @return the deduction amount of calculation result item.
     */
    public BigDecimal getDeductionAmount() {
        return deductionAmount;
    }

    /**
     * Sets the deduction amount of calculation result item.
     *
     * @param deductionAmount
     *            the deduction amount of calculation result item.
     */
    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    /**
     * Gets the total interest of calculation result item.
     *
     * @return the total interest of calculation result item.
     */
    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    /**
     * Sets the total interest of calculation result item.
     *
     * @param totalInterest
     *            the total interest of calculation result item.
     */
    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    /**
     * Gets the amount of applied payments of calculation result item.
     *
     * @return the amount of applied payments of calculation result item.
     */
    public BigDecimal getPaymentsApplied() {
        return paymentsApplied;
    }

    /**
     * Sets the amount of applied payments of calculation result item.
     *
     * @param paymentsApplied
     *            the amount of applied payments of calculation result item.
     */
    public void setPaymentsApplied(BigDecimal paymentsApplied) {
        this.paymentsApplied = paymentsApplied;
    }

    /**
     * Gets the balance of calculation result item.
     *
     * @return the balance of calculation result item.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the balance of calculation result item.
     *
     * @param balance
     *            the balance of calculation result item.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    /**
     * Gets the update status.
     *
     * @return the update status.
     */
    public CalculationResultItemUpdateStatus getStatus() {
        return status;
    }

    /**
     * Sets the update status.
     *
     * @param status
     *         the update status.
     */
    public void setStatus(CalculationResultItemUpdateStatus status) {
        this.status = status;
    }

    /**
     * Gets the version.
     *
     * @return the version.
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *         the version.
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Gets the line.
     *
     * @return the line.
     */
    public Integer getLine() {
        return line;
    }

    /**
     * Sets the line.
     *
     * @param line
     *         the line.
     */
    public void setLine(Integer line) {
        this.line = line;
    }

    /**
     * Gets the retirement type.
     *
     * @return the retirement type.
     *
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    public RetirementType getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the retirement type.
     *
     * @param retirementType
     *            the retirement type.
     *
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    public void setRetirementType(RetirementType retirementType) {
        this.retirementType = retirementType;
    }
     /**
     * Gets the serviceCategory.
     * @return the serviceCategory.
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    public DepositType getServiceCategory() {
        return serviceCategory;
    }

    /**
     * Sets the serviceCategory.
     * @param serviceCategory the serviceCategory.
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    public void setServiceCategory(DepositType serviceCategory) {
        this.serviceCategory = serviceCategory;
    }
}
