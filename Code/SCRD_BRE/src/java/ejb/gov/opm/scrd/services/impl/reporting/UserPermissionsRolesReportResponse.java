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
public class UserPermissionsRolesReportResponse extends BaseReportResponse {
    /**
     * Represents the list of UserPermissionsRolesReportResponseItems. It is accessible by getter and modified by
     * setter. It can be any value. The default value is null.
     */
    private List<UserPermissionsRolesReportResponseItem> items;

    /**
     * Creates a new instance of the {@link UserPermissionsRolesReportResponse} class.
     */
    public UserPermissionsRolesReportResponse() {
        super();
    }

    /**
     * Gets the list of UserPermissionsRolesReportResponseItems.
     *
     * @return the list of UserPermissionsRolesReportResponseItems.
     */
    public List<UserPermissionsRolesReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the list of UserPermissionsRolesReportResponseItems.
     *
     * @param items
     *         the list of UserPermissionsRolesReportResponseItems.
     */
    public void setItems(List<UserPermissionsRolesReportResponseItem> items) {
        this.items = items;
    }
}
