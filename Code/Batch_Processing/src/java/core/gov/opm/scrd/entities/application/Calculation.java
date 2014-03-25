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
import gov.opm.scrd.entities.lookup.AgencyCode;
import gov.opm.scrd.entities.lookup.AppointmentType;
import gov.opm.scrd.entities.lookup.PayType;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.ServiceType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This is the class representing the single item of the calculation.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added fields: agencyCode, hoursInYear, annualizedAmount, dateEntered, enteredBy</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Rules Engine - Integrate with Web App Assembly v1.0 ):</em>
 * <ul>
 * <li>Added fields: interestAccrualDate, connerCase</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.3 (OPM - Release I Assembly 1.0):</em>
 * <ul>
 * <li>Added fields: frozen</li>
 * </ul>
 * </p>
 * 
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax, bannie2492, TCSASSEMBLER
 * @version 1.3
 */
public class Calculation extends IdentifiableEntity {
    /**
     * <p>
     * Represents the begin date of calculation item. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private Date beginDate;
    /**
     * <p>
     * Represents the end date of calculation item. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date endDate;
    /**
     * <p>
     * Represents the retirement type of calculation item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private RetirementType retirementType;
    /**
     * <p>
     * Represents the period type of calculation item. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private PeriodType periodType;
    /**
     * <p>
     * Represents the appointment type of calculation item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private AppointmentType appointmentType;
    /**
     * <p>
     * Represents the service type of calculation item. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private ServiceType serviceType;
    /**
     * <p>
     * Represents the amount of calculation item. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal amount;
    /**
     * <p>
     * Represents the pay type of calculation item. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private PayType payType;
    /**
     * <p>
     * Represents the agency code of calculation item. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private AgencyCode agencyCode;
    /**
     * <p>
     * Represents the hours in year of calculation item. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Integer hoursInYear;
    /**
     * <p>
     * Represents the annualized amount of calculation item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private BigDecimal annualizedAmount;
    /**
     * <p>
     * Represents the entered date of calculation item. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Date dateEntered;
    /**
     * <p>
     * Represents the id of user who entered calculation item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Long enteredBy;

    
    /**
     * <p>
     * Represents the interest rate. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Release I Assembly 1.0)
     */
    private BigDecimal interestRate;


    /**
     * <p>
     * Represents the interest accrual date of the calculation.
     * It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     * 
     * @since 1.2 (OPM - Rules Engine - Integrate with Web App Assembly v1.0)
     */
    private Date interestAccrualDate;
    
    /**
     * <p>
     * Represents the flag indicating whether the calculation is a Conner case.
     * It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     * 
     * @since 1.2 (OPM - Rules Engine - Integrate with Web App Assembly v1.0)
     */
    private boolean connerCase;


    /**
     * <p>
     * Represents whether this calculation is frozen. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     *
     * @since 1.3 (OPM - Release I Assembly 1.0)
     */
    private boolean frozen;
    
    /**
     * Creates an instance of Calculation.
     */
    public Calculation() {
        // Empty
    }

    /**
     * Gets the begin date of calculation item.
     *
     * @return the begin date of calculation item.
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the begin date of calculation item.
     *
     * @param beginDate
     *            the begin date of calculation item.
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Gets the end date of calculation item.
     *
     * @return the end date of calculation item.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of calculation item.
     *
     * @param endDate
     *            the end date of calculation item.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the retirement type of calculation item.
     *
     * @return the retirement type of calculation item.
     */
    public RetirementType getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the retirement type of calculation item.
     *
     * @param retirementType
     *            the retirement type of calculation item.
     */
    public void setRetirementType(RetirementType retirementType) {
        this.retirementType = retirementType;
    }

    /**
     * Gets the period type of calculation item.
     *
     * @return the period type of calculation item.
     */
    public PeriodType getPeriodType() {
        return periodType;
    }

    /**
     * Sets the period type of calculation item.
     *
     * @param periodType
     *            the period type of calculation item.
     */
    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    /**
     * Gets the appointment type of calculation item.
     *
     * @return the appointment type of calculation item.
     */
    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    /**
     * Sets the appointment type of calculation item.
     *
     * @param appointmentType
     *            the appointment type of calculation item.
     */
    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Gets the service type of calculation item.
     *
     * @return the service type of calculation item.
     */
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * Sets the service type of calculation item.
     *
     * @param serviceType
     *            the service type of calculation item.
     */
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Gets the amount of calculation item.
     *
     * @return the amount of calculation item.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of calculation item.
     *
     * @param amount
     *            the amount of calculation item.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the pay type of calculation item.
     *
     * @return the pay type of calculation item.
     */
    public PayType getPayType() {
        return payType;
    }

    /**
     * Sets the pay type of calculation item.
     *
     * @param payType
     *            the pay type of calculation item.
     */
    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    /**
     * Gets the agency code of calculation item.
     *
     * @return the agency code of calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public AgencyCode getAgencyCode() {
        return agencyCode;
    }

    /**
     * Sets the agency code of calculation item.
     *
     * @param agencyCode
     *            the agency code of calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setAgencyCode(AgencyCode agencyCode) {
        this.agencyCode = agencyCode;
    }

    /**
     * Gets the hours in year of calculation item.
     *
     * @return the hours in year of calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Integer getHoursInYear() {
        return hoursInYear;
    }

    /**
     * Sets the hours in year of calculation item.
     *
     * @param hoursInYear
     *            the hours in year of calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setHoursInYear(Integer hoursInYear) {
        this.hoursInYear = hoursInYear;
    }

    /**
     * Gets the annualized amount of calculation item.
     *
     * @return the annualized amount of calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public BigDecimal getAnnualizedAmount() {
        return annualizedAmount;
    }

    /**
     * Sets the annualized amount of calculation item.
     *
     * @param annualizedAmount
     *            the annualized amount of calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setAnnualizedAmount(BigDecimal annualizedAmount) {
        this.annualizedAmount = annualizedAmount;
    }

    /**
     * Gets the entered date of calculation item.
     *
     * @return the entered date of calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Date getDateEntered() {
        return dateEntered;
    }

    /**
     * Sets the entered date of calculation item.
     *
     * @param dateEntered
     *            the entered date of calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    /**
     * Gets the id of user who entered calculation item.
     *
     * @return the id of user who entered calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Long getEnteredBy() {
        return enteredBy;
    }

    /**
     * Sets the id of user who entered calculation item.
     *
     * @param enteredBy
     *            the id of user who entered calculation item.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setEnteredBy(Long enteredBy) {
        this.enteredBy = enteredBy;
    }

    /**
     * Gets the interest rate of the entered calculation item.
     *
     * @return the interest rate of the entered calculation item.
     *
     * @since 1.2 (OPM - Release I Assembly 1.0)
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the interest rate of the entered calculation item.
     *
     * @param interestRate
     *            the interest rate of the entered calculation item.
     *
     * @since 1.2 (OPM - Release I Assembly 1.0)
     */
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Gets the interest accrual date of the calculation.
     * 
     * @return the interestAccrualDate
     * 
     * @since 1.2 (OPM - Rules Engine - Integrate with Web App Assembly v1.0)
     */
    public Date getInterestAccrualDate() {
        return interestAccrualDate;
    }

    /**
     * Sets the accrual date of the calculation.
     * 
     * @param interestAccrualDate the interestAccrualDate to set
     * 
     * @since 1.2 (OPM - Rules Engine - Integrate with Web App Assembly v1.0 )
     */
    public void setInterestAccrualDate(Date interestAccrualDate) {
        this.interestAccrualDate = interestAccrualDate;
    }

    /**
     * Gets the flag indicating whether the calculation is a Conner case.
     * 
     * @return the connerCase
     * 
     * @since 1.2 (OPM - Rules Engine - Integrate with Web App Assembly v1.0)
     */
    public boolean getConnerCase() {
        return connerCase;
    }

    /**
     * Sets the flag indicating whether the calculation is a Conner case.
     * 
     * @param connerCase the connerCase to set
     * 
     * @since 1.2 (OPM - Rules Engine - Integrate with Web App Assembly v1.0 )
     */
    public void setConnerCase(boolean connerCase) {
        this.connerCase = connerCase;
    }

    /**
     * Gets the frozen state of the entered calculation item.
     *
     * @return the frozen state of the entered calculation item.
     *
     * @since 1.3 (OPM - Release I Assembly 1.0)
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Sets the frozen state of the entered calculation item.
     *
     * @param frozen
     *            the frozen state of the entered calculation item.
     *
     * @since 1.3 (OPM - Release I Assembly 1.0)
     */
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
