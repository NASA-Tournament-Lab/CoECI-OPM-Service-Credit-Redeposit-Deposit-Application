/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;

/**
 * This class represents the response item for the balanced scorecard payment report. <p>
 * <strong>Thread-safety:</strong> This class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class PendingCheckLockboxItem {
    /**
     * Represents the posted pending field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem postedPending;

    /**
     * Represents the suspense refund field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem suspenseRefund;

    /**
     * Represents the unresolved field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem unresolved;

    /**
     * Represents the check sub total field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal checkSubTotal;

    /**
     * Creates a new instance of the {@link PendingCheckLockboxItem} class.
     */
    public PendingCheckLockboxItem() {
    }

    /**
     * Gets the posted pending field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getPostedPending() {
        return postedPending;
    }

    /**
     * Sets the posted pending field.
     *
     * @param postedPending
     *         the field value.
     */
    public void setPostedPending(SimpleBalancedItem postedPending) {
        this.postedPending = postedPending;
    }

    /**
     * Gets the suspense refund field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getSuspenseRefund() {
        return suspenseRefund;
    }

    /**
     * Sets the suspense refund field.
     *
     * @param suspenseRefund
     *         the field value.
     */
    public void setSuspenseRefund(SimpleBalancedItem suspenseRefund) {
        this.suspenseRefund = suspenseRefund;
    }

    /**
     * Gets the unresolved field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getUnresolved() {
        return unresolved;
    }

    /**
     * Sets the unresolved field.
     *
     * @param unresolved
     *         the field value.
     */
    public void setUnresolved(SimpleBalancedItem unresolved) {
        this.unresolved = unresolved;
    }

    /**
     * Gets the check sub total field.
     *
     * @return the field value.
     */
    public BigDecimal getCheckSubTotal() {
        return checkSubTotal;
    }

    /**
     * Sets the check sub total field.
     *
     * @param checkSubTotal
     *         the field value.
     */
    public void setCheckSubTotal(BigDecimal checkSubTotal) {
        this.checkSubTotal = checkSubTotal;
    }
}
