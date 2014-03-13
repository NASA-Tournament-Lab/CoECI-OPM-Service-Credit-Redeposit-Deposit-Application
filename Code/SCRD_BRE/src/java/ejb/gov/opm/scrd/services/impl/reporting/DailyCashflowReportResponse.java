/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;

/**
 * This class represents the response for the daily cash flow report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailyCashflowReportResponse extends BaseReportResponse {
    /**
     * Represents the list of daily cash flow lockbox items. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private List<DailyCashflowLockboxItem> lockboxItems;

    /**
     * Represents the list of daily cash flow payment items. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private List<DailyCashflowPaymentItem> paymentItems;

    /**
     * Creates a new instance of the {@link DailyCashflowReportResponse} class.
     */
    public DailyCashflowReportResponse() {
        super();
    }

    /**
     * Gets the list of daily cash flow lockbox items.
     *
     * @return the list of daily cash flow lockbox items.
     */
    public List<DailyCashflowLockboxItem> getLockboxItems() {
        return lockboxItems;
    }

    /**
     * Sets the list of daily cash flow lockbox items.
     *
     * @param lockboxItems
     *         the list of daily cash flow lockbox items.
     */
    public void setLockboxItems(List<DailyCashflowLockboxItem> lockboxItems) {
        this.lockboxItems = lockboxItems;
    }

    /**
     * Gets the list of daily cash flow payment items.
     *
     * @return the list of daily cash flow payment items.
     */
    public List<DailyCashflowPaymentItem> getPaymentItems() {
        return paymentItems;
    }

    /**
     * Sets the list of daily cash flow payment items.
     *
     * @param paymentItems
     *         the list of daily cash flow payment items.
     */
    public void setPaymentItems(List<DailyCashflowPaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
    }
}
