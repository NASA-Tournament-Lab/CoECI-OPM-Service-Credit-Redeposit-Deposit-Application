/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class represents the response item for the namesake report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DeductionRatesReportResponseItem {
    /**
     * Represents retirement type field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String retirementType;

    /**
     * Represents start date field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Date startDate;

    /**
     * Represents end date field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Date endDate;

    /**
     * Represents number of days field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer days;

    /**
     * Represents rate field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private BigDecimal rate;

    /**
     * Creates a new instance of the {@link DeductionRatesReportResponseItem} class.
     */
    public DeductionRatesReportResponseItem() {
    }

    /**
     * Gets the retirement type field.
     *
     * @return the field value.
     */
    public String getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the retirement type field.
     *
     * @param retirementType
     *         the field value.
     */
    public void setRetirementType(String retirementType) {
        this.retirementType = retirementType;
    }

    /**
     * Gets the start date field.
     *
     * @return the field value.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date field.
     *
     * @param startDate
     *         the field value.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date field.
     *
     * @return the field value.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date field.
     *
     * @param endDate
     *         the field value.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the number of days field.
     *
     * @return the field value.
     */
    public Integer getDays() {
        return days;
    }

    /**
     * Sets the number of days field.
     *
     * @param days
     *         the field value.
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * Gets the rate field.
     *
     * @return the field value.
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Sets the rate field.
     *
     * @param rate
     *         the field value.
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
