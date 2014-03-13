/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;

/**
 * This class represents the response item for the balanced lockbox report. <p> <strong>Thread-safety:</strong> This
 * class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class BalancedLockboxReportResponseItem {
    /**
     * Represents import status field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String importStatus;

    /**
     * Represents item number field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer itemNumber;

    /**
     * Represents total number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal totalNumber;

    /**
     * Creates a new instance of the {@link BalancedLockboxReportResponseItem} class.
     */
    public BalancedLockboxReportResponseItem() {
    }

    /**
     * Gets the import status field.
     *
     * @return the field value.
     */
    public String getImportStatus() {
        return importStatus;
    }

    /**
     * Sets the import status field.
     *
     * @param importStatus
     *         the field value.
     */
    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    /**
     * Gets the item number field.
     *
     * @return the field value.
     */
    public Integer getItemNumber() {
        return itemNumber;
    }

    /**
     * Sets the item number field.
     *
     * @param itemNumber
     *         the field value.
     */
    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Gets the total number field.
     *
     * @return the field value.
     */
    public BigDecimal getTotalNumber() {
        return totalNumber;
    }

    /**
     * Sets the total number field.
     *
     * @param totalNumber
     *         the field value.
     */
    public void setTotalNumber(BigDecimal totalNumber) {
        this.totalNumber = totalNumber;
    }
}
