/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;

/**
 * <p>
 * This class serves as the response object that contains result details
 * to be used to generate reports in {@link ResolvedSuspenseHistoryReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class ResolvedSuspenseHistoryReportResponse extends BaseReportResponse {
    /**
     * Resolved suspense history items.
     */
    private List<ResolvedSuspenseHistoryReportResponseItem> dataItems;
    /**
     * Resolved suspense history report histogram items.
     */
    private List<ResolvedSuspenseHistoryReportHistogramItem> histogramItems;

    /**
     * Default constructor.
     */
    public ResolvedSuspenseHistoryReportResponse() {
    }

    /**
     * Gets the value of the field <code>dataItems</code>.
     * @return the dataItems
     */
    public List<ResolvedSuspenseHistoryReportResponseItem> getDataItems() {
        return dataItems;
    }

    /**
     * Sets the value of the field <code>dataItems</code>.
     * @param dataItems the dataItems to set
     */
    public void setDataItems(List<ResolvedSuspenseHistoryReportResponseItem> dataItems) {
        this.dataItems = dataItems;
    }

    /**
     * Gets the value of the field <code>histogramItems</code>.
     * @return the histogramItems
     */
    public List<ResolvedSuspenseHistoryReportHistogramItem> getHistogramItems() {
        return histogramItems;
    }

    /**
     * Sets the value of the field <code>histogramItems</code>.
     * @param histogramItems the histogramItems to set
     */
    public void setHistogramItems(List<ResolvedSuspenseHistoryReportHistogramItem> histogramItems) {
        this.histogramItems = histogramItems;
    }
}
