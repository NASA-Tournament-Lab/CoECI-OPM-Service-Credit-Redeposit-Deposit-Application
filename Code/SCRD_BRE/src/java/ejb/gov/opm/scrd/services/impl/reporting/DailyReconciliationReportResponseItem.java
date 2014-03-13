/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

/**
 * This class represents the response item for the daily reconciliation report. <p> <strong>Thread-safety:</strong> This
 * class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailyReconciliationReportResponseItem {
    /**
     * Represents the name field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String name;

    /**
     * Represents the items field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer items;

    /**
     * Represents the receipts field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer receipts;

    /**
     * Represents the processed field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer processed;

    /**
     * Represents the suspended field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer suspended;

    /**
     * Represents the reversed field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer reversed;

    /**
     * Creates a new instance of the {@link DailyReconciliationReportResponseItem} class.
     */
    public DailyReconciliationReportResponseItem() {
    }

    /**
     * Gets the name field.
     *
     * @return the field value.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name field.
     *
     * @param name
     *         the field value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the items field.
     *
     * @return the field value.
     */
    public Integer getItems() {
        return items;
    }

    /**
     * Sets the items field.
     *
     * @param items
     *         the field value.
     */
    public void setItems(Integer items) {
        this.items = items;
    }

    /**
     * Gets the receipts field.
     *
     * @return the field value.
     */
    public Integer getReceipts() {
        return receipts;
    }

    /**
     * Sets the receipts field.
     *
     * @param receipts
     *         the field value.
     */
    public void setReceipts(Integer receipts) {
        this.receipts = receipts;
    }

    /**
     * Gets the processed field.
     *
     * @return the field value.
     */
    public Integer getProcessed() {
        return processed;
    }

    /**
     * Sets the processed field.
     *
     * @param processed
     *         the field value.
     */
    public void setProcessed(Integer processed) {
        this.processed = processed;
    }

    /**
     * Gets the suspended field.
     *
     * @return the field value.
     */
    public Integer getSuspended() {
        return suspended;
    }

    /**
     * Sets the suspended field.
     *
     * @param suspended
     *         the field value.
     */
    public void setSuspended(Integer suspended) {
        this.suspended = suspended;
    }

    /**
     * Gets the reversed field.
     *
     * @return the field value.
     */
    public Integer getReversed() {
        return reversed;
    }

    /**
     * Sets the reversed field.
     *
     * @param reversed
     *         the field value.
     */
    public void setReversed(Integer reversed) {
        this.reversed = reversed;
    }
}
