/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;

/**
 * This class represents the response for the daily suspense report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailySuspenseReportResponse extends BaseReportResponse {
    /**
     * Represents the list of items. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private List<DailySuspenseReportResponseItem> items;

    /**
     * Creates a new instance of the {@link DailySuspenseReportResponse} class.
     */
    public DailySuspenseReportResponse() {
        super();
    }

    /**
     * Gets the list of items.
     *
     * @return the list of items.
     */
    public List<DailySuspenseReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the list of items.
     *
     * @param items
     *         the list of items.
     */
    public void setItems(List<DailySuspenseReportResponseItem> items) {
        this.items = items;
    }
}
