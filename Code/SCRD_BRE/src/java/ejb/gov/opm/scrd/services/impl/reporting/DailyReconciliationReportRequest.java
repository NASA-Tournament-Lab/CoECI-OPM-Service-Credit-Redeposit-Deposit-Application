/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.entities.application.ReportRequest;

import java.util.Date;

/**
 * This class represents the request for the daily reconciliation report. <p> <strong>Thread-safety:</strong> This class
 * is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailyReconciliationReportRequest implements ReportRequest {
    /**
     * Represents the date field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Date date;

    /**
     * Represents the null flag field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Boolean nullFlag;

    /**
     * Creates a new instance of the {@link DailyReconciliationReportRequest} class.
     */
    public DailyReconciliationReportRequest() {
    }

    /**
     * Gets the date field.
     *
     * @return the field value.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date field.
     *
     * @param date
     *         the field value.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the null flag field.
     *
     * @return the field value.
     */
    public Boolean getNullFlag() {
        return nullFlag;
    }

    /**
     * Sets the null flag field.
     *
     * @param nullFlag
     *         the field value.
     */
    public void setNullFlag(Boolean nullFlag) {
        this.nullFlag = nullFlag;
    }
}
