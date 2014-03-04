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
import gov.opm.scrd.entities.lookup.RetirementType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Represents the entity specifying deduction rate.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class DeductionRate extends IdentifiableEntity {
    /**
     * <p>
     * Represents the service type of rate. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String serviceType;
    /**
     * <p>
     * Represents the retirement type of rate. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private RetirementType retirementType;
    /**
     * <p>
     * Represents the start date of rate. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Date startDate;
    /**
     * <p>
     * Represents the end date of rate. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Date endDate;
    /**
     * <p>
     * Represents the days in rate period. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer daysInPeriod;
    /**
     * <p>
     * Represents the factor of rate. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal rate;
    /**
     * <p>
     * Represents the service type description of rate. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private String serviceTypeDescription;
    /**
     * <p>
     * Represents the deduction conversion factor of rate. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal deductionConversionFactor;

    /**
     * Creates an instance of DeductionRate.
     */
    public DeductionRate() {
        // Empty
    }

    /**
     * Gets the service type of rate.
     *
     * @return the service type of rate.
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the service type of rate.
     *
     * @param serviceType
     *            the service type of rate.
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Gets the retirement type of rate.
     *
     * @return the retirement type of rate.
     */
    public RetirementType getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the retirement type of rate.
     *
     * @param retirementType
     *            the retirement type of rate.
     */
    public void setRetirementType(RetirementType retirementType) {
        this.retirementType = retirementType;
    }

    /**
     * Gets the start date of rate.
     *
     * @return the start date of rate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of rate.
     *
     * @param startDate
     *            the start date of rate.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of rate.
     *
     * @return the end date of rate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of rate.
     *
     * @param endDate
     *            the end date of rate.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the days in rate period.
     *
     * @return the days in rate period.
     */
    public Integer getDaysInPeriod() {
        return daysInPeriod;
    }

    /**
     * Sets the days in rate period.
     *
     * @param daysInPeriod
     *            the days in rate period.
     */
    public void setDaysInPeriod(Integer daysInPeriod) {
        this.daysInPeriod = daysInPeriod;
    }

    /**
     * Gets the factor of rate.
     *
     * @return the factor of rate.
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Sets the factor of rate.
     *
     * @param rate
     *            the factor of rate.
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * Gets the service type description of rate.
     *
     * @return the service type description of rate.
     */
    public String getServiceTypeDescription() {
        return serviceTypeDescription;
    }

    /**
     * Sets the service type description of rate.
     *
     * @param serviceTypeDescription
     *            the service type description of rate.
     */
    public void setServiceTypeDescription(String serviceTypeDescription) {
        this.serviceTypeDescription = serviceTypeDescription;
    }

    /**
     * Gets the deduction conversion factor of rate.
     *
     * @return the deduction conversion factor of rate.
     */
    public BigDecimal getDeductionConversionFactor() {
        return deductionConversionFactor;
    }

    /**
     * Sets the deduction conversion factor of rate.
     *
     * @param deductionConversionFactor
     *            the deduction conversion factor of rate.
     */
    public void setDeductionConversionFactor(BigDecimal deductionConversionFactor) {
        this.deductionConversionFactor = deductionConversionFactor;
    }
}
