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

import gov.opm.scrd.entities.lookup.AppointmentType;
import gov.opm.scrd.entities.lookup.PayType;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.ServiceType;

import java.util.Date;

/**
 * Represents a calculation request.
 *
 * <b>Thread Safety</b> This class is mutable and not thread safe.
 *
 * @author argolite, j3_guile
 * @version 1.0
 */
public class CalculationRequest implements RuleRequest {

    /**
     * The begin date.
     */
    private Date beginDate;

    /**
     * The end date.
     */
    private Date endDate;

    /**
     * The retirement type.
     */
    private RetirementType retirementType;

    /**
     * The period type.
     */
    private PeriodType periodType;

    /**
     * The appointment type.
     */
    private AppointmentType appointmentType;

    /**
     * The service type.
     */
    private ServiceType serviceType;

    /**
     * The amount.
     */
    private float amount;

    /**
     * The pay type.
     */
    private PayType payType;

    /**
     * Empty constructor.
     */
    public CalculationRequest() {
    }

    /**
     * Gets the value of the field <code>beginDate</code>.
     *
     * @return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the value of the field <code>beginDate</code>.
     *
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Gets the value of the field <code>endDate</code>.
     *
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the field <code>endDate</code>.
     *
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the value of the field <code>retirementType</code>.
     *
     * @return the retirementType
     */
    public RetirementType getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the value of the field <code>retirementType</code>.
     *
     * @param retirementType the retirementType to set
     */
    public void setRetirementType(RetirementType retirementType) {
        this.retirementType = retirementType;
    }

    /**
     * Gets the value of the field <code>periodType</code>.
     *
     * @return the periodType
     */
    public PeriodType getPeriodType() {
        return periodType;
    }

    /**
     * Sets the value of the field <code>periodType</code>.
     *
     * @param periodType the periodType to set
     */
    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    /**
     * Gets the value of the field <code>appointmentType</code>.
     *
     * @return the appointmentType
     */
    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    /**
     * Sets the value of the field <code>appointmentType</code>.
     *
     * @param appointmentType the appointmentType to set
     */
    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Gets the value of the field <code>serviceType</code>.
     *
     * @return the serviceType
     */
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the field <code>serviceType</code>.
     *
     * @param serviceType the serviceType to set
     */
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Gets the value of the field <code>amount</code>.
     *
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets the value of the field <code>amount</code>.
     *
     * @param amount the amount to set
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Gets the value of the field <code>payType</code>.
     *
     * @return the payType
     */
    public PayType getPayType() {
        return payType;
    }

    /**
     * Sets the value of the field <code>payType</code>.
     *
     * @param payType the payType to set
     */
    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    /**
     * Gets a readable string representation of this entity.
     *
     * @return this entity as a string.
     */
    @Override
    public String toString() {
        return "CalculationRequest [beginDate=" + beginDate + ", endDate=" + endDate + ", retirementType="
            + retirementType + ", periodType=" + periodType + ", appointmentType=" + appointmentType + ", serviceType="
            + serviceType + ", amount=" + amount + ", payType=" + payType + "]";
    }
}
