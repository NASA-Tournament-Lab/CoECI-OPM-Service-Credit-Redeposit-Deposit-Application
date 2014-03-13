/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;

/**
 * <p>
 * This class serves as the response object that contains result details
 * to be used to generate reports in {@link MonthlySuspenseListReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class MonthlySuspenseListReportResponse extends BaseReportResponse {
    /**
     * Monthly suspense list details per row.
     */
    private List<MonthlySuspenseListReportResponseItem> items;

    /**
     * Default constructor.
     */
    public MonthlySuspenseListReportResponse() {
    }

    /**
     * Gets the value of the field <code>items</code>.
     * @return the items
     */
    public List<MonthlySuspenseListReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the value of the field <code>items</code>.
     * @param items the items to set
     */
    public void setItems(List<MonthlySuspenseListReportResponseItem> items) {
        this.items = items;
    }
}

