/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;

/**
 * This class represents the response item for the daily cash flow report. <p> <strong>Thread-safety:</strong> This
 * class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailyCashflowLockboxItem {
    /**
     * Represents lockbox bank import file field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String lockboxBankImportFile;

    /**
     * Represents ach number field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer achNumber;

    /**
     * Represents ach sum field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal achSum;

    /**
     * Represents check number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer checkNumber;

    /**
     * Represents check sum field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal checkSum;

    /**
     * Represents all number field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer allNumber;

    /**
     * Represents all sum field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal allSum;

    /**
     * Creates a new instance of the {@link DailyCashflowLockboxItem} class.
     */
    public DailyCashflowLockboxItem() {
    }

    /**
     * Gets the lockbox bank import file field.
     *
     * @return the field value.
     */
    public String getLockboxBankImportFile() {
        return lockboxBankImportFile;
    }

    /**
     * Sets the lockbox bank import file field.
     *
     * @param lockboxBankImportFile
     *         the field value.
     */
    public void setLockboxBankImportFile(String lockboxBankImportFile) {
        this.lockboxBankImportFile = lockboxBankImportFile;
    }

    /**
     * Gets the ach number field.
     *
     * @return the field value.
     */
    public Integer getAchNumber() {
        return achNumber;
    }

    /**
     * Sets the ach number field.
     *
     * @param achNumber
     *         the field value.
     */
    public void setAchNumber(Integer achNumber) {
        this.achNumber = achNumber;
    }

    /**
     * Gets the ach sum field.
     *
     * @return the field value.
     */
    public BigDecimal getAchSum() {
        return achSum;
    }

    /**
     * Sets the ach sum field.
     *
     * @param achSum
     *         the field value.
     */
    public void setAchSum(BigDecimal achSum) {
        this.achSum = achSum;
    }

    /**
     * Gets the check number field.
     *
     * @return the field value.
     */
    public Integer getCheckNumber() {
        return checkNumber;
    }

    /**
     * Sets the check number field.
     *
     * @param checkNumber
     *         the field value.
     */
    public void setCheckNumber(Integer checkNumber) {
        this.checkNumber = checkNumber;
    }

    /**
     * Gets the check sum field.
     *
     * @return the field value.
     */
    public BigDecimal getCheckSum() {
        return checkSum;
    }

    /**
     * Sets the check sum field.
     *
     * @param checkSum
     *         the field value.
     */
    public void setCheckSum(BigDecimal checkSum) {
        this.checkSum = checkSum;
    }

    /**
     * Gets the all number field.
     *
     * @return the field value.
     */
    public Integer getAllNumber() {
        return allNumber;
    }

    /**
     * Sets the all number field.
     *
     * @param allNumber
     *         the field value.
     */
    public void setAllNumber(Integer allNumber) {
        this.allNumber = allNumber;
    }

    /**
     * Gets the all sum field.
     *
     * @return the field value.
     */
    public BigDecimal getAllSum() {
        return allSum;
    }

    /**
     * Sets the all sum field.
     *
     * @param allSum
     *         the field value.
     */
    public void setAllSum(BigDecimal allSum) {
        this.allSum = allSum;
    }
}
