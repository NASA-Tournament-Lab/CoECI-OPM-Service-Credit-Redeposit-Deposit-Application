/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * This class represents the response for the daily batch processing report. <p> <strong>Thread-safety:</strong> This
 * class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailyBatchProcessingReportResponse extends BaseReportResponse {
    /**
     * Represents the list of daily batch processing day items. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private List<DailyBatchProcessingReportDayItem> items;

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
     * Creates a new instance of the {@link DailyBatchProcessingReportResponse} class.
     */
    public DailyBatchProcessingReportResponse() {
        super();
    }

    /**
     * Gets the list of daily batch processing day items.
     *
     * @return the list of daily batch processing day items.
     */
    public List<DailyBatchProcessingReportDayItem> getItems() {
        return items;
    }

    /**
     * Sets the list of daily batch processing day items.
     *
     * @param items
     *         the list of daily batch processing day items.
     */
    public void setItems(List<DailyBatchProcessingReportDayItem> items) {
        this.items = items;
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
