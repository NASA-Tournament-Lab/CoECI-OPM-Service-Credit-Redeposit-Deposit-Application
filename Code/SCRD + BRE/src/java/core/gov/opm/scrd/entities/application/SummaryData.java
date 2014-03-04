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

import java.math.BigDecimal;

/**
 * <p>
 * This is the class representing the summary of the aggregated calculation result.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class SummaryData extends IdentifiableEntity {
    /**
     * <p>
     * Represents the total number of required payments of calculation summary. It is managed with a getter and setter.
     * It may have any value. It is fully mutable.
     * </p>
     */
    private BigDecimal totalPaymentsRequired;
    /**
     * <p>
     * Represents the total number of initial interests of calculation summary. It is managed with a getter and setter.
     * It may have any value. It is fully mutable.
     * </p>
     */
    private BigDecimal totalInitialInterest;
    /**
     * <p>
     * Represents the total number of previously applied payments of calculation summary. It is managed with a getter
     * and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private BigDecimal totalPaymentsApplied;
    /**
     * <p>
     * Represents the total balance of calculation summary. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal totalBalance;

    /**
     * Creates an instance of SummaryData.
     */
    public SummaryData() {
        // Empty
    }

    /**
     * Gets the total number of required payments of calculation summary.
     *
     * @return the total number of required payments of calculation summary.
     */
    public BigDecimal getTotalPaymentsRequired() {
        return totalPaymentsRequired;
    }

    /**
     * Sets the total number of required payments of calculation summary.
     *
     * @param totalPaymentsRequired
     *            the total number of required payments of calculation summary.
     */
    public void setTotalPaymentsRequired(BigDecimal totalPaymentsRequired) {
        this.totalPaymentsRequired = totalPaymentsRequired;
    }

    /**
     * Gets the total number of initial interests of calculation summary.
     *
     * @return the total number of initial interests of calculation summary.
     */
    public BigDecimal getTotalInitialInterest() {
        return totalInitialInterest;
    }

    /**
     * Sets the total number of initial interests of calculation summary.
     *
     * @param totalInitialInterest
     *            the total number of initial interests of calculation summary.
     */
    public void setTotalInitialInterest(BigDecimal totalInitialInterest) {
        this.totalInitialInterest = totalInitialInterest;
    }

    /**
     * Gets the total number of previously applied payments of calculation summary.
     *
     * @return the total number of previously applied payments of calculation summary.
     */
    public BigDecimal getTotalPaymentsApplied() {
        return totalPaymentsApplied;
    }

    /**
     * Sets the total number of previously applied payments of calculation summary.
     *
     * @param totalPaymentsApplied
     *            the total number of previously applied payments of calculation summary.
     */
    public void setTotalPaymentsApplied(BigDecimal totalPaymentsApplied) {
        this.totalPaymentsApplied = totalPaymentsApplied;
    }

    /**
     * Gets the total balance of calculation summary.
     *
     * @return the total balance of calculation summary.
     */
    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    /**
     * Sets the total balance of calculation summary.
     *
     * @param totalBalance
     *            the total balance of calculation summary.
     */
    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }
}
