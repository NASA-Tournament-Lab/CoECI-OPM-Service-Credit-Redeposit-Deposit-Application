/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class represents the response item for the daily batch processing report. <p> <strong>Thread-safety:</strong>
 * This class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailyBatchProcessingReportItem {
    /**
     * Represents batch date field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Date batchDate;

    /**
     * Represents transaction date field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date transactionDate;

    /**
     * Represents transaction status field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String transactionStatus;

    /**
     * Represents pay field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private Integer pay;

    /**
     * Represents total field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private BigDecimal total;

    /**
     * Represents refund field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal refund;

    /**
     * Represents description field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String description;

    /**
     * Creates a new instance of the {@link DailyBatchProcessingReportItem} class.
     */
    public DailyBatchProcessingReportItem() {
    }

    /**
     * Gets the batch date field.
     *
     * @return the field value.
     */
    public Date getBatchDate() {
        return batchDate;
    }

    /**
     * Sets the batch date field.
     *
     * @param batchDate
     *         the field value.
     */
    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    /**
     * Gets the transaction date field.
     *
     * @return the field value.
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the transaction date field.
     *
     * @param transactionDate
     *         the field value.
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the transaction status field.
     *
     * @return the field value.
     */
    public String getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * Sets the transaction status field.
     *
     * @param transactionStatus
     *         the field value.
     */
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    /**
     * Gets the pay field.
     *
     * @return the field value.
     */
    public Integer getPay() {
        return pay;
    }

    /**
     * Sets the pay field.
     *
     * @param pay
     *         the field value.
     */
    public void setPay(Integer pay) {
        this.pay = pay;
    }

    /**
     * Gets the total field.
     *
     * @return the field value.
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Sets the total field.
     *
     * @param total
     *         the field value.
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Gets the refund field.
     *
     * @return the field value.
     */
    public BigDecimal getRefund() {
        return refund;
    }

    /**
     * Sets the refund field.
     *
     * @param refund
     *         the field value.
     */
    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }

    /**
     * Gets the description field.
     *
     * @return the field value.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description field.
     *
     * @param description
     *         the field value.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
