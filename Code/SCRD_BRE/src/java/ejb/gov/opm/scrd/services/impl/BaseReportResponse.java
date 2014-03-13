/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl;

import gov.opm.scrd.entities.application.ReportResponse;

import java.util.Date;

/**
 * <p>
 * This class is the base class for all concrete implementations of ReportResponse in the application. It simply
 * contains fields for report name and generation date.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 */
public abstract class BaseReportResponse implements ReportResponse {
    /**
     * Represents the report name. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String reportName;

    /**
     * Represents the report generation date. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Date reportGenerationDate;

    /**
     * Creates an instance of BaseReportResponse.
     */
    protected BaseReportResponse() {
        // Empty
    }

    /**
     * Gets the report name.
     *
     * @return the report name.
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * Sets the report name.
     *
     * @param reportName
     *            the report name.
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * Gets the report generation date.
     *
     * @return the report generation date.
     */
    public Date getReportGenerationDate() {
        return reportGenerationDate;
    }

    /**
     * Sets the report generation date.
     *
     * @param reportGenerationDate
     *            the report generation date.
     */
    public void setReportGenerationDate(Date reportGenerationDate) {
        this.reportGenerationDate = reportGenerationDate;
    }
}
