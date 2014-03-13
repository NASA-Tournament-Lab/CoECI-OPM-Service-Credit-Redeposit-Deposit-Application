/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link ResolvedSuspenseHistoryReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class ResolvedSuspenseHistoryReportResponseItem {
    /**
     * The status code.
     */
    private String statusCode;
    /**
     * The batch number.
     */
    private String batchNumber;
    /**
     * Block number.
     */
    private String blkNumber;
    /**
     * Sequence number.
     */
    private String sequenceNumber;
    /**
     * CSD #.
     */
    private String csd;
    /**
     * Birthday.
     */
    private Date birthday;
    /**
     * Amount.
     */
    private BigDecimal amount;
    /**
     * Deposit date.
     */
    private Date depositDate;
    /**
     * The technician.
     */
    private String technician;
    /**
     * The note.
     */
    private String note;

    /**
     * Default constructor.
     */
    public ResolvedSuspenseHistoryReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>statusCode</code>.
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the field <code>statusCode</code>.
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets the value of the field <code>batchNumber</code>.
     * @return the batchNumber
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the value of the field <code>batchNumber</code>.
     * @param batchNumber the batchNumber to set
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the value of the field <code>blkNumber</code>.
     * @return the blkNumber
     */
    public String getBlkNumber() {
        return blkNumber;
    }

    /**
     * Sets the value of the field <code>blkNumber</code>.
     * @param blkNumber the blkNumber to set
     */
    public void setBlkNumber(String blkNumber) {
        this.blkNumber = blkNumber;
    }

    /**
     * Gets the value of the field <code>sequenceNumber</code>.
     * @return the sequenceNumber
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the value of the field <code>sequenceNumber</code>.
     * @param sequenceNumber the sequenceNumber to set
     */
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the value of the field <code>csd</code>.
     * @return the csd
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the value of the field <code>csd</code>.
     * @param csd the csd to set
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the value of the field <code>birthday</code>.
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Sets the value of the field <code>birthday</code>.
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets the value of the field <code>amount</code>.
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the field <code>amount</code>.
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the value of the field <code>depositDate</code>.
     * @return the depositDate
     */
    public Date getDepositDate() {
        return depositDate;
    }

    /**
     * Sets the value of the field <code>depositDate</code>.
     * @param depositDate the depositDate to set
     */
    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    /**
     * Gets the value of the field <code>technician</code>.
     * @return the technician
     */
    public String getTechnician() {
        return technician;
    }

    /**
     * Sets the value of the field <code>technician</code>.
     * @param technician the technician to set
     */
    public void setTechnician(String technician) {
        this.technician = technician;
    }

    /**
     * Gets the value of the field <code>note</code>.
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the field <code>note</code>.
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }
}
