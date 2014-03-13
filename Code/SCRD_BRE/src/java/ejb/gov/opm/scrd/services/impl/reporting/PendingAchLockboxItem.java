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
public class PendingAchLockboxItem {
    /**
     * Represents the direct pay life field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem directPayLife;

    /**
     * Represents the posted pending resolved field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private SimpleBalancedItem postedPendingResolved;

    /**
     * Represents the posted pending field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem postedPending;

    /**
     * Represents the suspended field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem suspended;

    /**
     * Represents the name field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private SimpleBalancedItem suspendedRefund;

    /**
     * Represents the name field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private SimpleBalancedItem unresolved;

    /**
     * Represents the ach sub total field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal achSubTotal;

    /**
     * Creates a new instance of the {@link PendingAchLockboxItem} class.
     */
    public PendingAchLockboxItem() {
    }

    /**
     * Gets the direct pay life field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getDirectPayLife() {
        return directPayLife;
    }

    /**
     * Sets the direct pay life field.
     *
     * @param directPayLife
     *         the field value.
     */
    public void setDirectPayLife(SimpleBalancedItem directPayLife) {
        this.directPayLife = directPayLife;
    }

    /**
     * Gets the posted pending resolved field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getPostedPendingResolved() {
        return postedPendingResolved;
    }

    /**
     * Sets the posted pending resolved field.
     *
     * @param postedPendingResolved
     *         the field value.
     */
    public void setPostedPendingResolved(SimpleBalancedItem postedPendingResolved) {
        this.postedPendingResolved = postedPendingResolved;
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
     * Gets the suspended field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getSuspended() {
        return suspended;
    }

    /**
     * Sets the suspended field.
     *
     * @param suspended
     *         the field value.
     */
    public void setSuspended(SimpleBalancedItem suspended) {
        this.suspended = suspended;
    }

    /**
     * Gets the suspended refund field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getSuspendedRefund() {
        return suspendedRefund;
    }

    /**
     * Sets the suspended refund field.
     *
     * @param suspendedRefund
     *         the field value.
     */
    public void setSuspendedRefund(SimpleBalancedItem suspendedRefund) {
        this.suspendedRefund = suspendedRefund;
    }

    /**
     * Gets the name field.
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
     * Gets the ach sub total field.
     *
     * @return the field value.
     */
    public BigDecimal getAchSubTotal() {
        return achSubTotal;
    }

    /**
     * Sets the ach sub total field.
     *
     * @param achSubTotal
     *         the field value.
     */
    public void setAchSubTotal(BigDecimal achSubTotal) {
        this.achSubTotal = achSubTotal;
    }
}
