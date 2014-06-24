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

import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.ServiceType;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * <p>
 * Represents an extended service period.
 * </p>
 * 
 * <p>
 * <b>Thread Safety: </b> This class is not thread safe since it's mutable.
 * </p>
 * <p>
 * version 1.1 - OPM - Rules Engine - Scenarios Conversion 2 - Interest Update Assembly
 * <ul>
 * <li>Added periodType, interestAccuralDate and connerCase properties.</li>
 * <li>Added a setInterestAccrualDate(String) to be used in drool rules.</li>
 * </ul>
 * </p>
 * 
 * @author albertwang, TCSASSEMBLER, yedtoss
 * @version 1.1
 * @since OPM Rules Engine Models Exceptions and Interest Calculation v1.0 Assembly
 */
public class ExtendedServicePeriod {
    /**
     * The SimpleDateFormat instance used to parse the Interest Accrual Date.
     */
    private static final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

    /**
     * Represents the begin date.
     */
    private Date beginDate;

    /**
     * Represents the end date.
     */
    private Date endDate;

    /**
     * Represents mid-point date.
     * 
     * It will be set by InterestCalculationRuleService.
     */
    private Date midPoint;

    /**
     * Represents the service periods of the extended service period.
     */
    private List<ServicePeriod> servicePeriods;

    /**
     * Represents total earnings.
     * 
     * It will be set by DeductionCalculationRuleService.
     */
    private BigDecimal totalEarnings;

    /**
     * Represents total deduction.
     * 
     * It will be set by DeductionCalculationRuleService.
     */
    private BigDecimal totalDeduction;

    /**
     * Represents balance with interest.
     * 
     * It will be set by InterestCalculationRuleService.
     */
    private BigDecimal balanceWithInterest;

    /**
     * Represents current year for which the interest will be calculated.
     * 
     * It will be set by InterestCalculationRuleService.
     */
    private int interestCalculationYear;

    /**
     * Represents the period type.
     */
    private PeriodType periodType;

    /**
     * Represents the service type.
     */
    private ServiceType serviceType;

    /**
     * Represents the retirement type. (CSRS or FERS)
     */
    private RetirementType retirementType;

    /**
     * Represents the interest accrual date.
     */
    private Date interestAccrualDate;

    /**
     * Indicates if the service period is considered as Conner Case.
     */
    private boolean connerCase;

    /**
     * Represents the validation errors.
     */
    private List<String> validationErrors;


    /**
     * Represents the intermediate result.
     */
    private List<IntermediateResult> intermediateResults;
    
    /**
     * The total earning for this period
     */
    private BigDecimal totalRunningEarnings;
    
    /**
     * The total deduction for this period
     */
    private BigDecimal totalRunningDeductions;

   

    /**
     * Creates the instance of ExtendedServicePeriod.
     */
    public ExtendedServicePeriod() {
        // does nothing
    }

    /**
     * Gets the validation errors.
     * @return the validation errors.
     */
    public List<String> getValidationErrors() {
        return validationErrors;
    }

    /**
     * Sets the validation errors.
     * @param validationErrors the validation errors.
     */
    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    /**
     * Gets the begin date.
     * @return the begin date.
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the begin date.
     * @param beginDate the begin date.
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Gets the end date.
     * @return the end date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     * @param endDate the end date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets mid-point date.
     * @return mid-point date.
     */
    public Date getMidPoint() {
        return midPoint;
    }

    /**
     * Sets mid-point date.
     * @param midPoint mid-point date.
     */
    public void setMidPoint(Date midPoint) {
        this.midPoint = midPoint;
    }

    /**
     * Gets the service periods of the extended service period.
     * @return the service periods of the extended service period.
     */
    public List<ServicePeriod> getServicePeriods() {
        return servicePeriods;
    }

    /**
     * Sets the service periods of the extended service period.
     * @param servicePeriods the service periods of the extended service period.
     */
    public void setServicePeriods(List<ServicePeriod> servicePeriods) {
        this.servicePeriods = servicePeriods;
    }

    /**
     * Gets total earnings.
     * @return total earnings.
     */
    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    /**
     * Sets total earnings.
     * @param totalEarnings total earnings.
     */
    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    /**
     * Gets total deduction.
     * @return total deduction.
     */
    public BigDecimal getTotalDeduction() {
        return totalDeduction;
    }

    /**
     * Sets total deduction.
     * @param totalDeduction total deduction.
     */
    public void setTotalDeduction(BigDecimal totalDeduction) {
        this.totalDeduction = totalDeduction;
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
     * Gets the retirement type.
     * @return the retirement type.
     */
    public RetirementType getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the retirement type.
     * @param retirementType the retirement type.
     */
    public void setRetirementType(RetirementType retirementType) {
        this.retirementType = retirementType;
    }

    /**
     * Getter for the periodType.
     *
     * @return the periodType
     */
    public PeriodType getPeriodType() {
        return periodType;
    }

    /**
     * Setter for the periodType.
     *
     * @param periodType the periodType to set
     */
    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
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

    /**
     * Set the interestAccrualDate from the given string.
     * 
     * @param interestAccrualDate the interestAccrualDate in string format.
     * @throws ParseException if fail to parse the date.
     */
    public void setInterestAccrualDate(String interestAccrualDate) throws ParseException {
        this.interestAccrualDate = DATE_PARSER.parse(interestAccrualDate);
    }

    /**
     * Getter for the connerCase.
     *
     * @return the connerCase
     */
    public boolean isConnerCase() {
        return connerCase;
    }

    /**
     * Setter for the connerCase.
     *
     * @param connerCase the connerCase to set
     */
    public void setConnerCase(boolean connerCase) {
        this.connerCase = connerCase;
    }

    /**
     * Getter for the serviceType.
     *
     * @return the serviceType
     */
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * Setter for the serviceType.
     *
     * @param serviceType the serviceType to set
     */
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }


    /**
     * Gets the intermediateResults.
     * @return the intermediateResults.
     */
    public List<IntermediateResult> getIntermediateResults() {
        return intermediateResults;
    }

    /**
     * Sets the intermediateResults.
     * @param intermediateResults the intermediateResults.
     */
    public void setIntermediateResults(List<IntermediateResult> intermediateResults) {
        this.intermediateResults = intermediateResults;
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
     * Converts the ExtendedServicePeriod instance to string.
     * @return the string of that period instance.
     */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("{");
        str.append("beginDate:" + beginDate + ",");
        str.append("endDate:" + endDate + ",");
        str.append("totalDeduction:" + totalDeduction + ",");
        str.append("balanceWithInterest:" + balanceWithInterest + ",");
        str.append("midPoint:" + midPoint + ",");
        str.append("totalEarnings:" + totalEarnings + ",");
        str.append("retirementType:" + retirementType + ",");
        str.append("servicePeriods:" + toString(servicePeriods) + ",");
        str.append("interestCalculationYear:" + interestCalculationYear + ",");
        str.append("periodType:" + periodType + ",");
        str.append("serviceType:" + serviceType + ",");
        str.append("interestAccrualDate:" + interestAccrualDate + ",");
        str.append("connerCase:" + connerCase + ",");
        str.append("validationErrors:" + toString(validationErrors));
        str.append("}");
        return str.toString();
    }

    /**
     * Converts the array to string.
     * 
     * @param array
     *            the array to convert.
     * @return the array of string format.
     */
    private String toString(List<?> array) {
        if (array == null) {
            return String.valueOf(array);
        }
        StringBuffer str = new StringBuffer("[");
        for (Object item : (List<?>) array) {
            if (!str.toString().equals("[")) {
                str.append(",");
            }
            str.append(String.valueOf(item));
        }
        str.append("]");
        return str.toString();
    }
}
