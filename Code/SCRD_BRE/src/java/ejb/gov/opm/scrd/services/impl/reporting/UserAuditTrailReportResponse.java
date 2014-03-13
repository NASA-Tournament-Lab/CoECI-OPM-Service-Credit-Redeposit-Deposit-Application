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
public class UserAuditTrailReportResponse extends BaseReportResponse {
    /**
     * Represents the list of UserAuditTrailReportResponseItems. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private List<UserAuditTrailReportResponseItem> items;

    /**
     * Creates a new instance of the {@link UserAuditTrailReportResponse} class.
     */
    public UserAuditTrailReportResponse() {
        super();
    }

    /**
     * Gets the list of UserAuditTrailReportResponseItems field
     *
     * @return the list of UserAuditTrailReportResponseItems.
     */
    public List<UserAuditTrailReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the list of UserAuditTrailReportResponseItems.
     *
     * @param items
     *         the list of UserAuditTrailReportResponseItems.
     */
    public void setItems(List<UserAuditTrailReportResponseItem> items) {
        this.items = items;
    }
}
