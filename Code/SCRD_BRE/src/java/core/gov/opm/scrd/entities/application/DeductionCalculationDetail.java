/**
 * Copyright 2014 OPM.gov

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
 * Entity to capture deduction calculation details.
 * </p>
 * 
 * @author albertwang, yedtoss
 *
 * @version 1.0
 */
public class DeductionCalculationDetail extends IdentifiableEntity{
    
    //  All commented code are currently disabled
    /**
     * The number of years for computing the deduction
     */
    //private int years;
    
    /**
     * The number of months for computing the deduction
     */
    //private int months;
    
    /**
     * The number of days for computing the deduction
     */
    //private int days;
    
    /**
     * The total number of days for computing the deduction
     */
    //private int totalDays;
    
    
    /**
     * The time factor the deduction
     */
    //private double timeFactor;
    
    /**
     * The deduction rate
     */
    private double deductionRate;
    
    /**
     * The deduction for this period
     */
    private BigDecimal runningDeductions;
    
    /**
     * The total earning for this period
     */
    private BigDecimal totalRunningEarnings;
    
    /**
     * The total deduction for this period
     */
    private BigDecimal totalRunningDeductions;

    /**
     * <p>
     * Gets the deductionRate value.
     * </p>
     *
     * @return the deductionRate
     */
    public double getDeductionRate() {
        return deductionRate;
    }

    /**
     * <p>
     * Sets the given value to deductionRate.
     * </p>
     *
     * @param deductionRate the deductionRate to set
     */
    public void setDeductionRate(double deductionRate) {
        this.deductionRate = deductionRate;
    }

    /**
     * <p>
     * Gets the totalRunningEarnings value.
     * </p>
     *
     * @return the totalRunningEarnings
     */
    public BigDecimal getTotalRunningEarnings() {
        return totalRunningEarnings;
    }

    /**
     * <p>
     * Sets the given value to totalRunningEarnings.
     * </p>
     *
     * @param totalRunningEarnings the totalRunningEarnings to set
     */
    public void setTotalRunningEarnings(BigDecimal totalRunningEarnings) {
        this.totalRunningEarnings = totalRunningEarnings;
    }

    /**
     * <p>
     * Gets the totalRunningDeductions value.
     * </p>
     *
     * @return the totalRunningDeductions
     */
    public BigDecimal getTotalRunningDeductions() {
        return totalRunningDeductions;
    }

    /**
     * <p>
     * Sets the given value to totalRunningDeductions.
     * </p>
     *
     * @param totalRunningDeductions the totalRunningDeductions to set
     */
    public void setTotalRunningDeductions(BigDecimal totalRunningDeductions) {
        this.totalRunningDeductions = totalRunningDeductions;
    }

    /**
     * <p>
     * Gets the runningDeductions value.
     * </p>
     *
     * @return the runningDeductions
     */
    public BigDecimal getRunningDeductions() {
        return runningDeductions;
    }

    /**
     * <p>
     * Sets the given value to runningDeductions.
     * </p>
     *
     * @param runningDeductions the runningDeductions to set
     */
    public void setRunningDeductions(BigDecimal runningDeductions) {
        this.runningDeductions = runningDeductions;
    }

}
