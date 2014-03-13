/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;
import java.util.Map;

/**
 * This class represents the response for the namesake report. <p> <strong>Thread-safety:</strong> This class is mutable
 * and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DeductionRatesReportResponse extends BaseReportResponse {
    /**
     * Represents map of service types and  DeductionRatesReportResponseItems. It is accessible by getter and modified
     * by setter. It can be any value. The default value is null.
     */
    private Map<String, List<DeductionRatesReportResponseItem>> items;

    /**
     * Creates a new instance of the {@link DeductionRatesReportResponse} class.
     */
    public DeductionRatesReportResponse() {
        super();
    }

    /**
     * Gets the map of service types and  DeductionRatesReportResponseItems.
     *
     * @return the map of service types and  DeductionRatesReportResponseItems.
     */
    public Map<String, List<DeductionRatesReportResponseItem>> getItems() {
        return items;
    }

    /**
     * Sets the map of service types and  DeductionRatesReportResponseItems.
     *
     * @param items
     *         the map of service types and  DeductionRatesReportResponseItems.
     */
    public void setItems(Map<String, List<DeductionRatesReportResponseItem>> items) {
        this.items = items;
    }
}
