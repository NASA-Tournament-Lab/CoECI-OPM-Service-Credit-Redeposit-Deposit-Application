/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.List;

/**
 * This class represents the response for the balanced lockbox report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class BalancedLockboxReportResponse extends BaseBalancedReportResponse {
    /**
     * Represents the change records items field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private List<BalancedLockboxReportResponseItem> changeRecordsItems;

    /**
     * Represents the ach imported items field. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private List<BalancedLockboxReportResponseItem> achImportedItems;

    /**
     * Represents the checks imported items field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private List<BalancedLockboxReportResponseItem> checksImportedItems;

    /**
     * Represents the debit vouchers items field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private List<BalancedLockboxReportResponseItem> debitVouchersItems;

    /**
     * Represents the total imported field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer totalImported;

    /**
     * Represents the ach reversed items field. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private List<BalancedLockboxReportResponseItem> achReversedItems;

    /**
     * Represents the total reversed field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer totalReversed;

    /**
     * Creates a new instance of the {@link BalancedLockboxReportResponse} class.
     */
    public BalancedLockboxReportResponse() {
        super();
    }

    /**
     * Gets the change records items field.
     *
     * @return the field.
     */
    public List<BalancedLockboxReportResponseItem> getChangeRecordsItems() {
        return changeRecordsItems;
    }

    /**
     * Sets the change records items field.
     *
     * @param changeRecordsItems
     *         the field value.
     */
    public void setChangeRecordsItems(List<BalancedLockboxReportResponseItem> changeRecordsItems) {
        this.changeRecordsItems = changeRecordsItems;
    }

    /**
     * Gets the ach imported items field.
     *
     * @return the field.
     */
    public List<BalancedLockboxReportResponseItem> getAchImportedItems() {
        return achImportedItems;
    }

    /**
     * Sets the ach imported items field.
     *
     * @param achImportedItems
     *         the field value.
     */
    public void setAchImportedItems(List<BalancedLockboxReportResponseItem> achImportedItems) {
        this.achImportedItems = achImportedItems;
    }

    /**
     * Gets the checks imported items field.
     *
     * @return the field.
     */
    public List<BalancedLockboxReportResponseItem> getChecksImportedItems() {
        return checksImportedItems;
    }

    /**
     * Sets the checks imported items field.
     *
     * @param checksImportedItems
     *         the field value.
     */
    public void setChecksImportedItems(List<BalancedLockboxReportResponseItem> checksImportedItems) {
        this.checksImportedItems = checksImportedItems;
    }

    /**
     * Gets the total imported field.
     *
     * @return the field.
     */
    public Integer getTotalImported() {
        return totalImported;
    }

    /**
     * Sets the total imported field.
     *
     * @param totalImported
     *         the field value.
     */
    public void setTotalImported(Integer totalImported) {
        this.totalImported = totalImported;
    }

    /**
     * Gets the ach reversed items field.
     *
     * @return the field.
     */
    public List<BalancedLockboxReportResponseItem> getAchReversedItems() {
        return achReversedItems;
    }

    /**
     * Sets the ach reversed items field.
     *
     * @param achReversedItems
     *         the field value.
     */
    public void setAchReversedItems(List<BalancedLockboxReportResponseItem> achReversedItems) {
        this.achReversedItems = achReversedItems;
    }

    /**
     * Gets the total reversed field.
     *
     * @return the field.
     */
    public Integer getTotalReversed() {
        return totalReversed;
    }

    /**
     * Sets the total reversed field.
     *
     * @param totalReversed
     *         the field value.
     */
    public void setTotalReversed(Integer totalReversed) {
        this.totalReversed = totalReversed;
    }

    /**
     * Gets the devbit vourchers items field.
     *
     * @return the field.
     */
    public List<BalancedLockboxReportResponseItem> getDebitVouchersItems() {
        return debitVouchersItems;
    }

    /**
     * Sets the devbit vourchers items field.
     *
     * @param achReversedItems
     *         the field value.
     */
    public void setDebitVouchersItems(List<BalancedLockboxReportResponseItem> debitVouchersItems) {
        this.debitVouchersItems = debitVouchersItems;
    }
}
