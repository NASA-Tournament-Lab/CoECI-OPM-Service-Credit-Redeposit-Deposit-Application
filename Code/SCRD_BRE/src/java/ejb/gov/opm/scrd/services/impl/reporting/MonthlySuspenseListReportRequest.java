/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.Date;

import gov.opm.scrd.entities.application.ReportRequest;

/**
 * <p>
 * This class serves as the request object that contains parameters to be used to generate reports in
 * {@link MonthlySuspenseListReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class MonthlySuspenseListReportRequest implements ReportRequest {
    /**
     * Month suspense.
     */
    private Date month;

    /**
     * Default constructor.
     */
    public MonthlySuspenseListReportRequest() {
    }

    /**
     * Gets the value of the field <code>month</code>.
     *
     * @return the month
     */
    public Date getMonth() {
        return month;
    }

    /**
     * Sets the value of the field <code>month</code>.
     *
     * @param month the month to set
     */
    public void setMonth(Date month) {
        this.month = month;
    }
}
