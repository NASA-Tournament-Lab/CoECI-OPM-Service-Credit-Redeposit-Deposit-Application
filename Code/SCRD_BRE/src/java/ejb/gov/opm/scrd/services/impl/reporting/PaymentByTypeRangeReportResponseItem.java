/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link PaymentByTypeRangeReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class PaymentByTypeRangeReportResponseItem {
    /**
     * Retirement type.
     */
    private String retirementType;
    /**
     * Payment amount.
     */
    private BigDecimal paymentAmount;
    /**
     * CSD #.
     */
    private String csd;
    /**
     * Date.
     */
    private Date date;

    /**
     * Default constructor.
     */
    public PaymentByTypeRangeReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>retirementType</code>.
     * @return the retirementType
     */
    public String getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the value of the field <code>retirementType</code>.
     * @param retirementType the retirementType to set
     */
    public void setRetirementType(String retirementType) {
        this.retirementType = retirementType;
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
     * Gets the value of the field <code>csd</code>.
     * @return the csd
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the value of the field <code>csd</code>.
     * @param csd the csd to set
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the value of the field <code>date</code>.
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the value of the field <code>date</code>.
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
