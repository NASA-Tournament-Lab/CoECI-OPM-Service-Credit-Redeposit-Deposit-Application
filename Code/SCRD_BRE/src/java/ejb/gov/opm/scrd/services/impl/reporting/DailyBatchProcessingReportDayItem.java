/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * This class represents the response item for the daily batch processing report. <p> <strong>Thread-safety:</strong>
 * This class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailyBatchProcessingReportDayItem {
    /**
     * Represents date field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private Date date;

    /**
     * Represents grand pay field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer grandPay;

    /**
     * Represents grand total field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal grandTotal;

    /**
     * Represents grand refund field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal grandRefund;

    /**
     * Represents list of daily batch processing report items. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private List<DailyBatchProcessingReportItem> items;

    /**
     * Creates a new instance of the {@link DailyBatchProcessingReportDayItem} class.
     */
    public DailyBatchProcessingReportDayItem() {
    }

    /**
     * Gets the date field.
     *
     * @return the field value.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date field.
     *
     * @param date
     *         the field value.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the grand pay field.
     *
     * @return the field value.
     */
    public Integer getGrandPay() {
        return grandPay;
    }

    /**
     * Sets the grand pay field.
     *
     * @param grandPay
     *         the field value.
     */
    public void setGrandPay(Integer grandPay) {
        this.grandPay = grandPay;
    }

    /**
     * Gets the grand total field.
     *
     * @return the field value.
     */
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    /**
     * Sets the grand total field.
     *
     * @param grandTotal
     *         the field value.
     */
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * Gets the list of daily batch processing items.
     *
     * @return the list of daily batch processing items.
     */
    public List<DailyBatchProcessingReportItem> getItems() {
        return items;
    }

    /**
     * Sets the list of daily batch processing items.
     *
     * @param items
     *         the list of daily batch processing items.
     */
    public void setItems(List<DailyBatchProcessingReportItem> items) {
        this.items = items;
    }

    /**
     * Gets the grand refund field.
     *
     * @return the field value.
     */
    public BigDecimal getGrandRefund() {
        return grandRefund;
    }

    /**
     * Sets the grand refund field.
     *
     * @param grandRefund
     *         the field value.
     */
    public void setGrandRefund(BigDecimal grandRefund) {
        this.grandRefund = grandRefund;
    }
}
