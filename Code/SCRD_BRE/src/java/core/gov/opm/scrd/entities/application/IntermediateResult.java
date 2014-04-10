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
import java.util.Date;

/**
 * <p>
 * This is the class representing the single item of the intermediate result of the calculation.
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author yedtoss
 * @version 1.0
 */
public class IntermediateResult extends IdentifiableEntity {

     /**
     * Represents the intermediate interest/deduction amount.
     */
    private BigDecimal intermediateAmount;

    /**
     * Represents the intermediate interest/deduction rate.
     */
    private Double intermediateRate;

    /**
     * Represents the intermediate begin date.
     */
    private Date intermediateBeginDate;

    /**
     * Represents the intermediate end date.
     */
    private Date intermediateEndDate;

    /**
     * Represents balance with interest.
     * 
     * It will be set by InterestCalculationRuleService.
     */
    private BigDecimal balanceWithInterest;


    /**
    * Represents the interst calculated to date
    */
    private Date interestCalculatedToDate;


    /**
     * Represents before balance with interest.
     * 
     * It will be set by InterestCalculationRuleService.
     */
    private BigDecimal beforeBalanceWithInterest;


    /**
    * Represents the start year factor
    */
    private BigDecimal startYearFactor;


    /**
    * Represents the number of days in period
    */
    private Integer periodInDays;


    /**
     * Represents the intermediate begin date.
     */
    private Date periodBeginDate;

    /**
     * Represents the intermediate end date.
     */
    private Date periodEndDate;



    /**
     * Represents the period type.
     */
    private String periodType;


    /**
     * Represents the retirement type. (CSRS or FERS)
     */
    private String retirementType;


    /**
     * Represents the intermediate interest/deduction rate.
     */
    private Double intermediateRate2;


    /**
    * Represents the number of days in period
    */
    private Integer periodInDays2;


    /**
    * Represents the composite rate 1
    */
    private BigDecimal compositeRate1;


    /**
    * Represents the composite rate 1
    */
    private BigDecimal compositeRate2;

    /**
     * Represents current year for which the interest will be calculated.
     * 
     * It will be set by InterestCalculationRuleService.
     */
    private int interestCalculationYear;

    /**
     * Represents the interest accrual date.
     */
    private Date interestAccrualDate;


    /**
     * Getter for the intermediateAmount.
     *
     * @return the intermediateAmount
     */
    public BigDecimal getIntermediateAmount() {
        return intermediateAmount;
    }

    /**
     * Setter for the intermediateAmount.
     *
     * @param intermediateAmount the intermediateAmount to set
     */
    public void setIntermediateAmount(BigDecimal intermediateAmount) {
        this.intermediateAmount = intermediateAmount;
    }

     /**
     * Getter for the intermediateRate.
     *
     * @return the intermediateRate
     */
    public Double getIntermediateRate() {
        return intermediateRate;
    }

    /**
     * Setter for the intermediateRate.
     *
     * @param intermediateRate the intermediateRate to set
     */
    public void setIntermediateRate(Double intermediateRate) {
        this.intermediateRate = intermediateRate;
    }

    /**
     * Getter for the intermediateBeginDate.
     *
     * @return the intermediateBeginDate
     */
    public Date getIntermediateBeginDate() {
        return intermediateBeginDate;
    }

    /**
     * Setter for the intermediateBeginDate.
     *
     * @param intermediateBeginDate the intermediateBeginDate to set
     */
    public void setIntermediateBeginDate(Date intermediateBeginDate) {
        this.intermediateBeginDate = intermediateBeginDate;
    }

     /**
     * Getter for the intermediateEndDate.
     *
     * @return the intermediateEndDate
     */
    public Date getIntermediateEndDate() {
        return intermediateEndDate;
    }

    /**
     * Setter for the intermediateEndDate.
     *
     * @param intermediateEndDate the intermediateEndDate to set
     */
    public void setIntermediateEndDate(Date intermediateEndDate) {
        this.intermediateEndDate = intermediateEndDate;
    }

    /**
     * Gets balance with interest.
     * @return balance with interest.
     */
    public BigDecimal getBalanceWithInterest() {
        return balanceWithInterest;
    }

    /**
     * Sets balance with interest.
     * @param balanceWithInterest balance with interest.
     */
    public void setBalanceWithInterest(BigDecimal balanceWithInterest) {
        this.balanceWithInterest = balanceWithInterest;
    }


    /**
     * Getter for the interestCalculatedToDate.
     *
     * @return the interestCalculatedToDate
     */
    public Date getInterestCalculatedToDate() {
        return interestCalculatedToDate;
    }

    /**
     * Setter for the interestCalculatedToDate.
     *
     * @param interestCalculatedToDate the interestCalculatedToDate to set
     */
    public void setInterestCalculatedToDate(Date interestCalculatedToDate) {
        this.interestCalculatedToDate = interestCalculatedToDate;
    }


    /**
     * Gets balance with interest.
     * @return balance with interest.
     */
    public BigDecimal getBeforeBalanceWithInterest() {
        return beforeBalanceWithInterest;
    }

    /**
     * Sets balance with interest.
     * @param beforeBalanceWithInterest before balance with interest.
     */
    public void setBeforeBalanceWithInterest(BigDecimal beforeBalanceWithInterest) {
        this.beforeBalanceWithInterest = beforeBalanceWithInterest;
    }


    /**
     * Gets balance with interest.
     * @return balance with interest.
     */
    public BigDecimal getStartYearFactor() {
        return startYearFactor;
    }

    /**
     * Sets balance with interest.
     * @param beforeBalanceWithInterest before balance with interest.
     */
    public void setStartYearFactor(BigDecimal startYearFactor) {
        this.startYearFactor= startYearFactor;
    }


    /**
     * Gets balance with interest.
     * @return balance with interest.
     */
    public Integer getPeriodInDays() {
        return periodInDays;
    }

    /**
     * Sets balance with interest.
     * @param beforeBalanceWithInterest before balance with interest.
     */
    public void setPeriodInDays(Integer periodInDays) {
        this.periodInDays= periodInDays;
    }


    /**
     * Getter for the intermediateBeginDate.
     *
     * @return the intermediateBeginDate
     */
    public Date getPeriodBeginDate() {
        return periodBeginDate;
    }

    /**
     * Setter for the intermediateBeginDate.
     *
     * @param intermediateBeginDate the intermediateBeginDate to set
     */
    public void setPeriodBeginDate(Date periodBeginDate) {
        this.periodBeginDate = periodBeginDate;
    }

     /**
     * Getter for the intermediateEndDate.
     *
     * @return the intermediateEndDate
     */
    public Date getPeriodEndDate() {
        return periodEndDate;
    }

    /**
     * Setter for the intermediateEndDate.
     *
     * @param intermediateEndDate the intermediateEndDate to set
     */
    public void setPeriodEndDate(Date periodEndDate) {
        this.periodEndDate = periodEndDate;
    }


    /**
     * Gets the retirement type.
     * @return the retirement type.
     */
    public String getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the retirement type.
     * @param retirementType the retirement type.
     */
    public void setRetirementType(String retirementType) {
        this.retirementType = retirementType;
    }

    /**
     * Getter for the periodType.
     *
     * @return the periodType
     */
    public String getPeriodType() {
        return periodType;
    }

    /**
     * Setter for the periodType.
     *
     * @param periodType the periodType to set
     */
    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }


    /**
     * Getter for the intermediateRate.
     *
     * @return the intermediateRate
     */
    public Double getIntermediateRate2() {
        return intermediateRate2;
    }

    /**
     * Setter for the intermediateRate.
     *
     * @param intermediateRate the intermediateRate to set
     */
    public void setIntermediateRate2(Double intermediateRate2) {
        this.intermediateRate2 = intermediateRate2;
    }

    /**
     * Gets balance with interest.
     * @return balance with interest.
     */
    public Integer getPeriodInDays2() {
        return periodInDays2;
    }

    /**
     * Sets balance with interest.
     * @param beforeBalanceWithInterest before balance with interest.
     */
    public void setPeriodInDays2(Integer periodInDays2) {
        this.periodInDays2= periodInDays2;
    }


     /**
     * Getter for the intermediateAmount.
     *
     * @return the intermediateAmount
     */
    public BigDecimal getCompositeRate1() {
        return compositeRate1;
    }

    /**
     * Setter for the intermediateAmount.
     *
     * @param intermediateAmount the intermediateAmount to set
     */
    public void setCompositeRate1(BigDecimal compositeRate1) {
        this.compositeRate1 = compositeRate1;
    }


    /**
     * Getter for the intermediateAmount.
     *
     * @return the intermediateAmount
     */
    public BigDecimal getCompositeRate2() {
        return compositeRate2;
    }

    /**
     * Setter for the intermediateAmount.
     *
     * @param intermediateAmount the intermediateAmount to set
     */
    public void setCompositeRate2(BigDecimal compositeRate2) {
        this.compositeRate2 = compositeRate2;
    }

    /**
     * Gets current year for which the interest will be calculated.
     * @return current year for which the interest will be calculated.
     */
    public int getInterestCalculationYear() {
        return interestCalculationYear;
    }

    /**
     * Sets current year for which the interest will be calculated.
     * @param interestCalculationYear current year for which the interest will be calculated.
     */
    public void setInterestCalculationYear(int interestCalculationYear) {
        this.interestCalculationYear = interestCalculationYear;
    }

    /**
     * Getter for the interestAccrualDate.
     *
     * @return the interestAccrualDate
     */
    public Date getInterestAccrualDate() {
        return interestAccrualDate;
    }

    /**
     * Setter for the interestAccrualDate.
     *
     * @param interestAccrualDate the interestAccrualDate to set
     */
    public void setInterestAccrualDate(Date interestAccrualDate) {
        this.interestAccrualDate = interestAccrualDate;
    }


}