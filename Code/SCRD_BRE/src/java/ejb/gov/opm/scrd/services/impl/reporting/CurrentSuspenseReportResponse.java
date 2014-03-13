/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * This class serves as the response object that contains result details
 * to be used to generate reports in {@link CurrentSuspenseReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class CurrentSuspenseReportResponse extends BaseReportResponse {
    /**
     * Details per row.
     */
    private List<CurrentSuspenseReportResponseItem> items;
    /**
     * ACH payment lockbox.
     */
    private BigDecimal achPaymentLockbox;
    /**
     * Total payments.
     */
    private BigDecimal totalPayment;

    /**
     * Default constructor.
     */
    public CurrentSuspenseReportResponse() {
    }

    /**
     * Gets the value of the field <code>items</code>.
     * @return the items
     */
    public List<CurrentSuspenseReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the value of the field <code>items</code>.
     * @param items the items to set
     */
    public void setItems(List<CurrentSuspenseReportResponseItem> items) {
        this.items = items;
    }

    /**
     * Gets the value of the field <code>achPaymentLockbox</code>.
     * @return the achPaymentLockbox
     */
    public BigDecimal getAchPaymentLockbox() {
        return achPaymentLockbox;
    }

    /**
     * Sets the value of the field <code>achPaymentLockbox</code>.
     * @param achPaymentLockbox the achPaymentLockbox to set
     */
    public void setAchPaymentLockbox(BigDecimal achPaymentLockbox) {
        this.achPaymentLockbox = achPaymentLockbox;
    }

    /**
     * Gets the value of the field <code>totalPayment</code>.
     * @return the totalPayment
     */
    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    /**
     * Sets the value of the field <code>totalPayment</code>.
     * @param totalPayment the totalPayment to set
     */
    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

}
