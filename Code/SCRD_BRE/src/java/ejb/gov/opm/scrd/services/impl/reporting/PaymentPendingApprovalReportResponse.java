/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;

/**
 * <p>
 * This class serves as the response object that contains result details
 * to be used to generate reports in {@link PaymentPendingApprovalReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class PaymentPendingApprovalReportResponse extends BaseReportResponse {
    /**
     * The active user.
     */
    private String activeUser;
    /**
     * Payment pending approval items.
     */
    private List<PaymentPendingApprovalReportResponseItem> items;

    /**
     * Default constructor.
     */
    public PaymentPendingApprovalReportResponse() {
    }

    /**
     * Gets the value of the field <code>activeUser</code>.
     * @return the activeUser
     */
    public String getActiveUser() {
        return activeUser;
    }

    /**
     * Sets the value of the field <code>activeUser</code>.
     * @param activeUser the activeUser to set
     */
    public void setActiveUser(String activeUser) {
        this.activeUser = activeUser;
    }

    /**
     * Gets the value of the field <code>items</code>.
     * @return the items
     */
    public List<PaymentPendingApprovalReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the value of the field <code>items</code>.
     * @param items the items to set
     */
    public void setItems(List<PaymentPendingApprovalReportResponseItem> items) {
        this.items = items;
    }
}
