/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link ChangeHistoryReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class ChangeHistoryReportResponseItem {
    /**
     * Date.
     */
    private Date date;

    /**
     * Modified.
     */
    private String modified;

    /**
     * Description.
     */
    private String description;

    /**
     * Default constructor.
     */
    public ChangeHistoryReportResponseItem() {
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

    /**
     * Gets the value of the field <code>modified</code>.
     * @return the modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * Sets the value of the field <code>modified</code>.
     * @param modified the modified to set
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * Gets the value of the field <code>description</code>.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the field <code>description</code>.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

