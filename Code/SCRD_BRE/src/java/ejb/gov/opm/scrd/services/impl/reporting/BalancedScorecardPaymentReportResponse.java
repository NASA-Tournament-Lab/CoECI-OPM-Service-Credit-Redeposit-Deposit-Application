/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;

/**
 * This class represents the response for the balanced scorecard payment report. <p> <strong>Thread-safety:</strong>
 * This class is mutable and not thread - safe. </p>
 * Changes:
 *   v1.1 remove useless import.
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
public class BalancedScorecardPaymentReportResponse extends BaseBalancedReportResponse {
    /**
     * Represents final check batch process item field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private FinalCheckBatchProcessItem finalCheckBatchProcessItem;

    /**
     * Represents final ach lockbox item field. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private FinalAchLockboxItem finalAchLockboxItem;

    /**
     * Represents final check lockbox item field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private FinalCheckLockboxItem finalCheckLockboxItem;

    /**
     * Represents final check over the counter item field. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private FinalCheckOverTheCounterItem finalCheckOverTheCounterItem;

    /**
     * Represents final payment total field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal finalPaymentTotal;

    /**
     * Represents pending ach lockbox item field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private PendingAchLockboxItem pendingAchLockboxItem;

    /**
     * Represents pending adjustment lockbox item field. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private PendingAdjustmentLockboxItem pendingAdjustmentLockboxItem;

    /**
     * Represents pending check lockbox item field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private PendingCheckLockboxItem pendingCheckLockboxItem;

    /**
     * Represents pending check over the counter item field. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private PendingCheckOverTheCounterItem pendingCheckOverTheCounterItem;

    /**
     * Represents pending payment total field. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private BigDecimal pendingPaymentTotal;

    /**
     * Represents grand total field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal grandTotal;

    /**
     * Creates a new instance of the {@link BalancedScorecardPaymentReportResponse} class.
     */
    public BalancedScorecardPaymentReportResponse() {
        super();
    }

    /**
     * Gets the final check batch processing item field.
     *
     * @return the field value.
     */
    public FinalCheckBatchProcessItem getFinalCheckBatchProcessItem() {
        return finalCheckBatchProcessItem;
    }

    /**
     * Sets the final check batch processing item field.
     *
     * @param finalCheckBatchProcessItem
     *         the field value.
     */
    public void setFinalCheckBatchProcessItem(FinalCheckBatchProcessItem finalCheckBatchProcessItem) {
        this.finalCheckBatchProcessItem = finalCheckBatchProcessItem;
    }

    /**
     * Gets the final ach lockbox item field.
     *
     * @return the field value.
     */
    public FinalAchLockboxItem getFinalAchLockboxItem() {
        return finalAchLockboxItem;
    }

    /**
     * Sets the final ach lockbox item field.
     *
     * @param finalAchLockboxItem
     *         the field value.
     */
    public void setFinalAchLockboxItem(FinalAchLockboxItem finalAchLockboxItem) {
        this.finalAchLockboxItem = finalAchLockboxItem;
    }

    /**
     * Gets the final check lockbox item field.
     *
     * @return the field value.
     */
    public FinalCheckLockboxItem getFinalCheckLockboxItem() {
        return finalCheckLockboxItem;
    }

    /**
     * Sets the final check lockbox item field.
     *
     * @param finalCheckLockboxItem
     *         the field value.
     */
    public void setFinalCheckLockboxItem(FinalCheckLockboxItem finalCheckLockboxItem) {
        this.finalCheckLockboxItem = finalCheckLockboxItem;
    }

    /**
     * Gets the final check over the counter item field.
     *
     * @return the field value.
     */
    public FinalCheckOverTheCounterItem getFinalCheckOverTheCounterItem() {
        return finalCheckOverTheCounterItem;
    }

    /**
     * Sets the final check over the counter item field.
     *
     * @param finalCheckOverTheCounterItem
     *         the field value.
     */
    public void setFinalCheckOverTheCounterItem(FinalCheckOverTheCounterItem finalCheckOverTheCounterItem) {
        this.finalCheckOverTheCounterItem = finalCheckOverTheCounterItem;
    }

    /**
     * Gets the final payment total field.
     *
     * @return the field value.
     */
    public BigDecimal getFinalPaymentTotal() {
        return finalPaymentTotal;
    }

    /**
     * Sets the final payment total field.
     *
     * @param finalPaymentTotal
     *         the field value.
     */
    public void setFinalPaymentTotal(BigDecimal finalPaymentTotal) {
        this.finalPaymentTotal = finalPaymentTotal;
    }

    /**
     * Gets the pending ach lockbox item field.
     *
     * @return the field value.
     */
    public PendingAchLockboxItem getPendingAchLockboxItem() {
        return pendingAchLockboxItem;
    }

    /**
     * Sets the pending ach lockbox item field.
     *
     * @param pendingAchLockboxItem
     *         the field value.
     */
    public void setPendingAchLockboxItem(PendingAchLockboxItem pendingAchLockboxItem) {
        this.pendingAchLockboxItem = pendingAchLockboxItem;
    }

    /**
     * Gets the pending adjustment lockbox item field.
     *
     * @return the field value.
     */
    public PendingAdjustmentLockboxItem getPendingAdjustmentLockboxItem() {
        return pendingAdjustmentLockboxItem;
    }

    /**
     * Sets the pending adjustment lockbox item field.
     *
     * @param pendingAdjustmentLockboxItem
     *         the field value.
     */
    public void setPendingAdjustmentLockboxItem(PendingAdjustmentLockboxItem pendingAdjustmentLockboxItem) {
        this.pendingAdjustmentLockboxItem = pendingAdjustmentLockboxItem;
    }

    /**
     * Gets the pending check lockbox item field.
     *
     * @return the field value.
     */
    public PendingCheckLockboxItem getPendingCheckLockboxItem() {
        return pendingCheckLockboxItem;
    }

    /**
     * Sets the pending check lockbox item field.
     *
     * @param pendingCheckLockboxItem
     *         the field value.
     */
    public void setPendingCheckLockboxItem(PendingCheckLockboxItem pendingCheckLockboxItem) {
        this.pendingCheckLockboxItem = pendingCheckLockboxItem;
    }

    /**
     * Gets the pending check over the counter item field.
     *
     * @return the field value.
     */
    public PendingCheckOverTheCounterItem getPendingCheckOverTheCounterItem() {
        return pendingCheckOverTheCounterItem;
    }

    /**
     * Sets the pending check over the counter item field.
     *
     * @param pendingCheckOverTheCounterItem
     *         the field value.
     */
    public void setPendingCheckOverTheCounterItem(PendingCheckOverTheCounterItem pendingCheckOverTheCounterItem) {
        this.pendingCheckOverTheCounterItem = pendingCheckOverTheCounterItem;
    }

    /**
     * Gets the pending payment total field.
     *
     * @return the field value.
     */
    public BigDecimal getPendingPaymentTotal() {
        return pendingPaymentTotal;
    }

    /**
     * Sets the pending payment total field.
     *
     * @param pendingPaymentTotal
     *         the field value.
     */
    public void setPendingPaymentTotal(BigDecimal pendingPaymentTotal) {
        this.pendingPaymentTotal = pendingPaymentTotal;
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
}
