/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link PaymentHistoryReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class PaymentHistoryReportResponseItem {
    /**
     * Date of payment.
     */
    private Date dateOfPayment;
    /**
     * Process date.
     */
    private Date processDate;
    /**
     * Balance due before payment.
     */
    private BigDecimal balanceDueBeforePayment;
    /**
     * Interest on prior.
     */
    private BigDecimal interestOnPrior;
    /**
     * Due before payment.
     */
    private BigDecimal dueBeforePayment;
    /**
     * Payment amount.
     */
    private BigDecimal paymentAmount;
    /**
     * Balance due after payment.
     */
    private BigDecimal balanceDueAfterPayment;
    /**
     * Total of payments to date.
     */
    private BigDecimal totalOfPaymentsToDate;

    /**
     * Default constructor.
     */
    public PaymentHistoryReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>dateOfPayment</code>.
     * @return the dateOfPayment
     */
    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    /**
     * Sets the value of the field <code>dateOfPayment</code>.
     * @param dateOfPayment the dateOfPayment to set
     */
    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    /**
     * Gets the value of the field <code>processDate</code>.
     * @return the processDate
     */
    public Date getProcessDate() {
        return processDate;
    }

    /**
     * Sets the value of the field <code>processDate</code>.
     * @param processDate the processDate to set
     */
    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    /**
     * Gets the value of the field <code>balanceDueBeforePayment</code>.
     * @return the balanceDueBeforePayment
     */
    public BigDecimal getBalanceDueBeforePayment() {
        return balanceDueBeforePayment;
    }

    /**
     * Sets the value of the field <code>balanceDueBeforePayment</code>.
     * @param balanceDueBeforePayment the balanceDueBeforePayment to set
     */
    public void setBalanceDueBeforePayment(BigDecimal balanceDueBeforePayment) {
        this.balanceDueBeforePayment = balanceDueBeforePayment;
    }

    /**
     * Gets the value of the field <code>interestOnPrior</code>.
     * @return the interestOnPrior
     */
    public BigDecimal getInterestOnPrior() {
        return interestOnPrior;
    }

    /**
     * Sets the value of the field <code>interestOnPrior</code>.
     * @param interestOnPrior the interestOnPrior to set
     */
    public void setInterestOnPrior(BigDecimal interestOnPrior) {
        this.interestOnPrior = interestOnPrior;
    }

    /**
     * Gets the value of the field <code>dueBeforePayment</code>.
     * @return the dueBeforePayment
     */
    public BigDecimal getDueBeforePayment() {
        return dueBeforePayment;
    }

    /**
     * Sets the value of the field <code>dueBeforePayment</code>.
     * @param dueBeforePayment the dueBeforePayment to set
     */
    public void setDueBeforePayment(BigDecimal dueBeforePayment) {
        this.dueBeforePayment = dueBeforePayment;
    }

    /**
     * Gets the value of the field <code>paymentAmount</code>.
     * @return the paymentAmount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the field <code>paymentAmount</code>.
     * @param paymentAmount the paymentAmount to set
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Gets the value of the field <code>balanceDueAfterPayment</code>.
     * @return the balanceDueAfterPayment
     */
    public BigDecimal getBalanceDueAfterPayment() {
        return balanceDueAfterPayment;
    }

    /**
     * Sets the value of the field <code>balanceDueAfterPayment</code>.
     * @param balanceDueAfterPayment the balanceDueAfterPayment to set
     */
    public void setBalanceDueAfterPayment(BigDecimal balanceDueAfterPayment) {
        this.balanceDueAfterPayment = balanceDueAfterPayment;
    }

    /**
     * Gets the value of the field <code>totalOfPaymentsToDate</code>.
     * @return the totalOfPaymentsToDate
     */
    public BigDecimal getTotalOfPaymentsToDate() {
        return totalOfPaymentsToDate;
    }

    /**
     * Sets the value of the field <code>totalOfPaymentsToDate</code>.
     * @param totalOfPaymentsToDate the totalOfPaymentsToDate to set
     */
    public void setTotalOfPaymentsToDate(BigDecimal totalOfPaymentsToDate) {
        this.totalOfPaymentsToDate = totalOfPaymentsToDate;
    }
}
