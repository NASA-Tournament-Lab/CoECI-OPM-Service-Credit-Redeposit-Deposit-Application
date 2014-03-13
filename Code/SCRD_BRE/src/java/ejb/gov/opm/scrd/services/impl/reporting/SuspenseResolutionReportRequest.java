/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.Date;

import gov.opm.scrd.entities.application.ReportRequest;

/**
 * <p>
 * This class serves as the request object that would contain parameters (currently empty) to be used to generate
 * reports in {@link SuspenseResolutionReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is immutable and thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class SuspenseResolutionReportRequest implements ReportRequest {
    /**
     * Start date.
     */
    private Date startDate;

    /**
     * End date.
     */
    private Date endDate;

    /**
     * Default constructor.
     */
    public SuspenseResolutionReportRequest() {
    }

    /**
     * Gets the value of the field <code>startDate</code>.
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the field <code>startDate</code>.
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the value of the field <code>endDate</code>.
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the field <code>endDate</code>.
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
