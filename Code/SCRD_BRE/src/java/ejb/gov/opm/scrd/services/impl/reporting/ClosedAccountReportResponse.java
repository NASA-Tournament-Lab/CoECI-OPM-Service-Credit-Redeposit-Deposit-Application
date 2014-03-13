/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;

/**
 * This class represents the response for the namesake report. <p> <strong>Thread-safety:</strong> This class is mutable
 * and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class ClosedAccountReportResponse extends BaseReportResponse {
    /**
     * Represents the list of entries.
     */
    private List<ClosedAccountReportResponseEntry> entries;

    /**
     * Creates a new instance of the {@link ClosedAccountReportResponse} class.
     */
    public ClosedAccountReportResponse() {
        super();
    }

    /**
     * Gets the list of entries entries.
     *
     * @return the list of entries.
     */
    public List<ClosedAccountReportResponseEntry> getEntries() {
        return entries;
    }

    /**
     * Sets the list of entries.
     *
     * @param entries
     *         the list of entries.
     */
    public void setEntries(List<ClosedAccountReportResponseEntry> entries) {
        this.entries = entries;
    }
}
