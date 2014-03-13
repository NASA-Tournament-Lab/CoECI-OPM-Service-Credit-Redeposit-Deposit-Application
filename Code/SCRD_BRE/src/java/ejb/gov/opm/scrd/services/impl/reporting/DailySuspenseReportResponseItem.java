/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class represents the response item for the daily suspense report. <p> <strong>Thread-safety:</strong> This class
 * is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailySuspenseReportResponseItem {
    /**
     * Represents the batch number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String batchNumber;

    /**
     * Represents the blk number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String blkNumber;

    /**
     * Represents the sequence number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String sequenceNumber;

    /**
     * Represents the amount field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal amount;

    /**
     * Represents the pay date field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date payDate;

    /**
     * Represents the csd field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String csd;

    /**
     * Represents the birth date field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date birthDate;

    /**
     * Represents the technician field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String technician;

    /**
     * Represents the description field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String description;

    /**
     * Represents the changed on field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date changedOn;

    /**
     * Represents the payment status field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String paymentStatus;

    /**
     * Creates a new instance of the {@link DailySuspenseReportResponseItem} class.
     */
    public DailySuspenseReportResponseItem() {
    }

    /**
     * Gets the batch number field.
     *
     * @return the field value.
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the batch number field.
     *
     * @param batchNumber
     *         the field value.
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the blk number field.
     *
     * @return the field value.
     */
    public String getBlkNumber() {
        return blkNumber;
    }

    /**
     * Sets the blk number field.
     *
     * @param blkNumber
     *         the field value.
     */
    public void setBlkNumber(String blkNumber) {
        this.blkNumber = blkNumber;
    }

    /**
     * Gets the sequence number field.
     *
     * @return the field value.
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequence number field.
     *
     * @param sequenceNumber
     *         the field value.
     */
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the amount field.
     *
     * @return the field value.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount field.
     *
     * @param amount
     *         the field value.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the pay date field.
     *
     * @return the field value.
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * Sets the pay date field.
     *
     * @param payDate
     *         the field value.
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * Gets the csd field.
     *
     * @return the field value.
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the csd field.
     *
     * @param csd
     *         the field value.
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the brith date field.
     *
     * @return the field value.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date field.
     *
     * @param birthDate
     *         the field value.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the technician field.
     *
     * @return the field value.
     */
    public String getTechnician() {
        return technician;
    }

    /**
     * Sets the technician field.
     *
     * @param technician
     *         the field value.
     */
    public void setTechnician(String technician) {
        this.technician = technician;
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

    /**
     * Gets the changed on field.
     *
     * @return the field value.
     */
    public Date getChangedOn() {
        return changedOn;
    }

    /**
     * Sets the changed on field.
     *
     * @param changedOn
     *         the field value.
     */
    public void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }

    /**
     * Gets the payment status field.
     *
     * @return the field value.
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the payment status field.
     *
     * @param paymentStatus
     *         the field value.
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
