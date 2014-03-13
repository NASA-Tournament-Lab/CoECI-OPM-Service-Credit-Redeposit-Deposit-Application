/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link MonthlySuspenseListReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class MonthlySuspenseListReportResponseItem {
    /**
     * The batch number.
     */
    private String batchNumber;
    /**
     * The type.
     */
    private String type;
    /**
     * CSD #.
     */
    private String csd;
    /**
     * The date amount was deposited.
     */
    private Date deposited;
    /**
     * The date the date was imported.
     */
    private Date imported;
    /**
     * Current status.
     */
    private String currentStatus;
    /**
     * Amount.
     */
    private BigDecimal amount;

    /**
     * Default constructor.
     */
    public MonthlySuspenseListReportResponseItem() {
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
     * Gets the value of the field <code>type</code>.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the field <code>type</code>.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * Gets the value of the field <code>deposited</code>.
     * @return the deposited
     */
    public Date getDeposited() {
        return deposited;
    }

    /**
     * Sets the value of the field <code>deposited</code>.
     * @param deposited the deposited to set
     */
    public void setDeposited(Date deposited) {
        this.deposited = deposited;
    }

    /**
     * Gets the value of the field <code>imported</code>.
     * @return the imported
     */
    public Date getImported() {
        return imported;
    }

    /**
     * Sets the value of the field <code>imported</code>.
     * @param imported the imported to set
     */
    public void setImported(Date imported) {
        this.imported = imported;
    }

    /**
     * Gets the value of the field <code>currentStatus</code>.
     * @return the currentStatus
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * Sets the value of the field <code>currentStatus</code>.
     * @param currentStatus the currentStatus to set
     */
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
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
}
