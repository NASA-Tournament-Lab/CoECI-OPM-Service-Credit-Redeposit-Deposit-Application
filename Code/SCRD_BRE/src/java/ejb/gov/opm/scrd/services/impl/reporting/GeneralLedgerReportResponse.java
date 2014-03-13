/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;

/**
 * <p>
 * This class serves as the response object that contains result details to be used to generate reports in
 * {@link GeneralLedgerReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class GeneralLedgerReportResponse extends BaseReportResponse {
    /**
     * Detail per row.
     */
    private List<GeneralLedgerReportResponseItem> items;

    /**
     * Default constructor.
     */
    public GeneralLedgerReportResponse() {
    }

    /**
     * Gets the value of the field <code>items</code>.
     *
     * @return the items
     */
    public List<GeneralLedgerReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the value of the field <code>items</code>.
     *
     * @param items the items to set
     */
    public void setItems(List<GeneralLedgerReportResponseItem> items) {
        this.items = items;
    }
}
