/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link LockboxFileImportReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class LockboxFileImportReportResponseItem {
    /**
     * Import date.
     */
    private Date importDate;

    /**
     * Log Id.
     */
    private String logId;

    /**
     * Deposit date.
     */
    private Date depositDate;

    /**
     * Check count.
     */
    private Integer checkCount;

    /**
     * ACH count.
     */
    private Integer achCount;

    /**
     * Other count.
     */
    private Integer otherCount;

    /**
     * Check total.
     */
    private BigDecimal checkTotal;

    /**
     * ACH total.
     */
    private BigDecimal achTotal;

    /**
     * Other total.
     */
    private BigDecimal otherTotal;

    /**
     * Default constructor.
     */
    public LockboxFileImportReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>importDate</code>.
     * @return the importDate
     */
    public Date getImportDate() {
        return importDate;
    }

    /**
     * Sets the value of the field <code>importDate</code>.
     * @param importDate the importDate to set
     */
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    /**
     * Gets the value of the field <code>logId</code>.
     * @return the logId
     */
    public String getLogId() {
        return logId;
    }

    /**
     * Sets the value of the field <code>logId</code>.
     * @param logId the logId to set
     */
    public void setLogId(String logId) {
        this.logId = logId;
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
     * Gets the value of the field <code>checkCount</code>.
     * @return the checkCount
     */
    public Integer getCheckCount() {
        return checkCount;
    }

    /**
     * Sets the value of the field <code>checkCount</code>.
     * @param checkCount the checkCount to set
     */
    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    /**
     * Gets the value of the field <code>achCount</code>.
     * @return the achCount
     */
    public Integer getAchCount() {
        return achCount;
    }

    /**
     * Sets the value of the field <code>achCount</code>.
     * @param achCount the achCount to set
     */
    public void setAchCount(Integer achCount) {
        this.achCount = achCount;
    }

    /**
     * Gets the value of the field <code>otherCount</code>.
     * @return the otherCount
     */
    public Integer getOtherCount() {
        return otherCount;
    }

    /**
     * Sets the value of the field <code>otherCount</code>.
     * @param otherCount the otherCount to set
     */
    public void setOtherCount(Integer otherCount) {
        this.otherCount = otherCount;
    }

    /**
     * Gets the value of the field <code>checkTotal</code>.
     * @return the checkTotal
     */
    public BigDecimal getCheckTotal() {
        return checkTotal;
    }

    /**
     * Sets the value of the field <code>checkTotal</code>.
     * @param checkTotal the checkTotal to set
     */
    public void setCheckTotal(BigDecimal checkTotal) {
        this.checkTotal = checkTotal;
    }

    /**
     * Gets the value of the field <code>achTotal</code>.
     * @return the achTotal
     */
    public BigDecimal getAchTotal() {
        return achTotal;
    }

    /**
     * Sets the value of the field <code>achTotal</code>.
     * @param achTotal the achTotal to set
     */
    public void setAchTotal(BigDecimal achTotal) {
        this.achTotal = achTotal;
    }

    /**
     * Gets the value of the field <code>otherTotal</code>.
     * @return the otherTotal
     */
    public BigDecimal getOtherTotal() {
        return otherTotal;
    }

    /**
     * Sets the value of the field <code>otherTotal</code>.
     * @param otherTotal the otherTotal to set
     */
    public void setOtherTotal(BigDecimal otherTotal) {
        this.otherTotal = otherTotal;
    }
}

