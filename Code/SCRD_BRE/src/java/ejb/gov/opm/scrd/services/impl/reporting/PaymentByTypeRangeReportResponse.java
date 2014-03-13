/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * This class serves as the response object that contains result details
 * to be used to generate reports in {@link PaymentByTypeRangeReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class PaymentByTypeRangeReportResponse extends BaseReportResponse {
    /**
     * Start date.
     */
    private Date startDate;
    /**
     * End date.
     */
    private Date endDate;
    /**
     * Payment by Type range report items.
     */
    private List<PaymentByTypeRangeReportResponseItem> items;

    /**
     * Default constructor.
     */
    public PaymentByTypeRangeReportResponse() {
    }

    /**
     * Gets the value of the field <code>startDate</code>.
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the field <code>startDate</code>.
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the value of the field <code>endDate</code>.
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the field <code>endDate</code>.
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the value of the field <code>items</code>.
     * @return the items
     */
    public List<PaymentByTypeRangeReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the value of the field <code>items</code>.
     * @param items the items to set
     */
    public void setItems(List<PaymentByTypeRangeReportResponseItem> items) {
        this.items = items;
    }
}

