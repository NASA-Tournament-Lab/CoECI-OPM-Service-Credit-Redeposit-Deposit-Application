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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * This class represents a service period.
 * </p>
 * <p>
 * <em>Changes in 1.1 (OPM - Release I Assembly 1.0):</em>
 * <ul>
 * <li>add field refundDate</li>
 * </ul>
 * </p>
 * <p>
 * <b>Thread Safety:</b> This class is not thread safe since it's mutable.
 * </p>
 *
 * @author albertwang, bannie2492
 * @version 1.1
 * @since OPM Rules Engine Models Exceptions and Interest Calculation v1.0 Assembly
 */
public class ServicePeriod {
    /**
     * Represents the begin date.
     */
    private Date beginDate;

    /**
     * Represents the end date.
     */
    private Date endDate;

    /**
     * Represents the retirement type.
     */
    private RetirementType retirementType;

    /**
     * Represents the period type.
     */
    private PeriodType periodType;

    /**
     * Represents the appointment type.
     */
    private AppointmentType appointmentType;

    /**
     * Represents the service type.
     */
    private ServiceType serviceType;

    /**
     * Represents refund date.
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    private Date refundDate;

    /**
     * Represents the pay type.
     */
    private PayType payType;

    /**
     * Represents the amount.
     */
    private BigDecimal amount;

    /**
     * Represents the earnings.
     *
     * It will be calculated and set by DeductionCalculationRuleService.
     */
    private BigDecimal earnings;

    /**
     * Represents the deduction.
     *
     * It will be calculated and set by DeductionCalculationRuleService.
     */
    private BigDecimal deduction;

    /**
     * Represents the validation errors.
     */
    private List<String> validationErrors;

    /**
     * Creates the instance of InterestCalculationResponse.
     */
    public ServicePeriod() {
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
     * @return the begin date
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the begin date.
     * @param beginDate the begin date
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
     * Gets the period type.
     * @return the period type.
     */
    public PeriodType getPeriodType() {
        return periodType;
    }

    /**
     * Sets the period type.
     * @param periodType the period type.
     */
    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    /**
     * Gets the appointment type.
     * @return the appointment type.
     */
    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    /**
     * Sets the appointment type.
     * @param appointmentType the appointment type.
     */
    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Gets the service type.
     * @return the service type.
     */
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * Sets the service type.
     * @param serviceType the service type.
     */
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Gets the pay type.
     * @return the pay type.
     */
    public PayType getPayType() {
        return payType;
    }

    /**
     * Sets the pay type.
     * @param payType the pay type.
     */
    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    /**
     * Gets the amount.
     * @return the amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     * @param amount the amount.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the earnings.
     * @return the earnings.
     */
    public BigDecimal getEarnings() {
        return earnings;
    }

    /**
     * Sets the earnings.
     * @param earnings the earnings.
     */
    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }

    /**
     * Gets the deduction.
     * @return the deduction.
     */
    public BigDecimal getDeduction() {
        return deduction;
    }

    /**
     * Sets the deduction.
     * @param deduction the deduction.
     */
    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
    }

    /**
     * Gets the refund date.
     * @return the refund date.
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    public Date getRefundDate() {
        return refundDate;
    }

    /**
     * Sets the refund date.
     * @param refundDate refund date.
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    /**
     * Converts the ServicePeriod instance to string.
     * @return the string of that period instance.
     */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("{");
        str.append("amount:" + amount + ",");
        str.append("appointmentType:" + appointmentType + ",");
        str.append("beginDate:" + beginDate + ",");
        str.append("deduction:" + deduction + ",");
        str.append("earnings:" + earnings + ",");
        str.append("endDate:" + endDate + ",");
        str.append("payType:" + payType + ",");
        str.append("periodType:" + periodType + ",");
        str.append("retirementType:" + retirementType + ",");
        str.append("serviceType:" + serviceType + ",");
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
