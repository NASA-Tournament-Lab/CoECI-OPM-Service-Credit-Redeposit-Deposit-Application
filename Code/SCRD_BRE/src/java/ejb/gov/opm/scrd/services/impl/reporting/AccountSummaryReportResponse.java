/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.math.BigDecimal;

/**
 * This class is the implementation of the ReportService which generates account summary report. It uses local data for
 * generating report and iText/iText RTF for generating reports. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class AccountSummaryReportResponse extends BaseReportResponse {
    /**
     * Represents the receipts field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal receipts;

    /**
     * Represents suspense field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal suspense;

    /**
     * Represents total receipts field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal totalReceipts;

    /**
     * Represents replaced accounts field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal replacedAccounts;

    /**
     * Represents adjustment plus field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal adjustmentPlus;

    /**
     * Represents total additions field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal totalAdditions;

    /**
     * Represents debit vouchers field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal debitVouchers;

    /**
     * Represents adjustment minus field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal adjustmentMinus;

    /**
     * Represents total deductions field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal totalDeductions;

    /**
     * Represents net change field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal netChange;

    /**
     * Creates a new instance of the {@link AccountSummaryReportResponse} class.
     */
    public AccountSummaryReportResponse() {
        super();
    }

    /**
     * Gets the receipts field.
     *
     * @return the field value.
     */
    public BigDecimal getReceipts() {
        return receipts;
    }

    /**
     * Sets the receipts field.
     *
     * @param receipts
     *         the field value.
     */
    public void setReceipts(BigDecimal receipts) {
        this.receipts = receipts;
    }

    /**
     * Gets the suspense field.
     *
     * @return the field value.
     */
    public BigDecimal getSuspense() {
        return suspense;
    }

    /**
     * Sets the suspense field.
     *
     * @param suspense
     *         the field value.
     */
    public void setSuspense(BigDecimal suspense) {
        this.suspense = suspense;
    }

    /**
     * Gets the total receipts field.
     *
     * @return the field value.
     */
    public BigDecimal getTotalReceipts() {
        return totalReceipts;
    }

    /**
     * Sets the total receipts field.
     *
     * @param totalReceipts
     *         the field value.
     */
    public void setTotalReceipts(BigDecimal totalReceipts) {
        this.totalReceipts = totalReceipts;
    }

    /**
     * Gets the replaced accounts field.
     *
     * @return the field value.
     */
    public BigDecimal getReplacedAccounts() {
        return replacedAccounts;
    }

    /**
     * Sets the replaced accounts field.
     *
     * @param replacedAccounts
     *         the field value.
     */
    public void setReplacedAccounts(BigDecimal replacedAccounts) {
        this.replacedAccounts = replacedAccounts;
    }

    /**
     * Gets the adjustment plus field.
     *
     * @return the field value.
     */
    public BigDecimal getAdjustmentPlus() {
        return adjustmentPlus;
    }

    /**
     * Sets the adjustment plus field.
     *
     * @param adjustmentPlus
     *         the field value.
     */
    public void setAdjustmentPlus(BigDecimal adjustmentPlus) {
        this.adjustmentPlus = adjustmentPlus;
    }

    /**
     * Gets the total additions field.
     *
     * @return the field value.
     */
    public BigDecimal getTotalAdditions() {
        return totalAdditions;
    }

    /**
     * Sets the total additions field.
     *
     * @param totalAdditions
     *         the field value.
     */
    public void setTotalAdditions(BigDecimal totalAdditions) {
        this.totalAdditions = totalAdditions;
    }

    /**
     * Gets the debit vouchers field.
     *
     * @return the field value.
     */
    public BigDecimal getDebitVouchers() {
        return debitVouchers;
    }

    /**
     * Sets the debit vouchers field.
     *
     * @param debitVouchers
     *         the field value.
     */
    public void setDebitVouchers(BigDecimal debitVouchers) {
        this.debitVouchers = debitVouchers;
    }

    /**
     * Gets the adjustment minus field.
     *
     * @return the field value.
     */
    public BigDecimal getAdjustmentMinus() {
        return adjustmentMinus;
    }

    /**
     * Sets the adjustment minus field.
     *
     * @param adjustmentMinus
     *         the field value.
     */
    public void setAdjustmentMinus(BigDecimal adjustmentMinus) {
        this.adjustmentMinus = adjustmentMinus;
    }

    /**
     * Gets the total deductions field.
     *
     * @return the field value.
     */
    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    /**
     * Sets the total deductions field.
     *
     * @param totalDeductions
     *         the field value.
     */
    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    /**
     * Gets the net change field.
     *
     * @return the field value.
     */
    public BigDecimal getNetChange() {
        return netChange;
    }

    /**
     * Sets the net change field.
     *
     * @param netChange
     *         the field value.
     */
    public void setNetChange(BigDecimal netChange) {
        this.netChange = netChange;
    }
}
