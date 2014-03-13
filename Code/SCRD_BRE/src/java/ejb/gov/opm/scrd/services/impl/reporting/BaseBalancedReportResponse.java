/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.Date;

/**
 * The base class for balanced report response <p> <strong>Thread-safety:</strong> This class is mutable and not thread
 * - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0.
 */
public abstract class BaseBalancedReportResponse extends BaseReportResponse {
    /**
     * Represents the fiscal year field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer fiscalYear;

    /**
     * Represents the quarter field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer quarter;

    /**
     * Represents the start date field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date startDate;

    /**
     * Represents the end date field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date endDate;

    /**
     * Creates a new instance of the {@link BaseBalancedReportResponse} class.
     */
    protected BaseBalancedReportResponse() {
        super();
    }

    /**
     * Gets the fiscal year field.
     *
     * @return the field.
     */
    public Integer getFiscalYear() {
        return fiscalYear;
    }

    /**
     * Sets the fiscal year field.
     *
     * @param fiscalYear
     *         the field value.
     */
    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     * Gets the quarters field.
     *
     * @return the field.
     */
    public Integer getQuarter() {
        return quarter;
    }

    /**
     * Sets the quarters field.
     *
     * @param quarter
     *         the field value.
     */
    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    /**
     * Gets the start date field.
     *
     * @return the field.
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
     * @return the field.
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
}
