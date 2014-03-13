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
public class FinalAchLockboxItem {
    /**
     * Represents the debit voucher field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem debitVoucher;

    /**
     * Represents the direct pay life field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem directPayLife;

    /**
     * Represents the posted complete resolved field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private SimpleBalancedItem postedCompleteResolved;

    /**
     * Represents the posted complete field. It is accessible by getter and modified by setter. It can be any value.
     * The
     * default value is null.
     */
    private SimpleBalancedItem postedComplete;

    /**
     * Represents the suspense refund complete field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private SimpleBalancedItem suspenseRefundComplete;

    /**
     * Represents the ach sub total field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal achSubTotal;

    /**
     * Creates a new instance of the {@link FinalAchLockboxItem} class.
     */
    public FinalAchLockboxItem() {
    }

    /**
     * Gets the debit voucher field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getDebitVoucher() {
        return debitVoucher;
    }

    /**
     * Sets the debit voucher field.
     *
     * @param debitVoucher
     *         the field value.
     */
    public void setDebitVoucher(SimpleBalancedItem debitVoucher) {
        this.debitVoucher = debitVoucher;
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
     * Gets the posted complete resolved field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getPostedCompleteResolved() {
        return postedCompleteResolved;
    }

    /**
     * Sets the posted complete resolved field.
     *
     * @param postedCompleteResolved
     *         the field value.
     */
    public void setPostedCompleteResolved(SimpleBalancedItem postedCompleteResolved) {
        this.postedCompleteResolved = postedCompleteResolved;
    }

    /**
     * Gets the posted complete field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getPostedComplete() {
        return postedComplete;
    }

    /**
     * Sets the posted complete field.
     *
     * @param postedComplete
     *         the field value.
     */
    public void setPostedComplete(SimpleBalancedItem postedComplete) {
        this.postedComplete = postedComplete;
    }

    /**
     * Gets the suspense refund complete field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getSuspenseRefundComplete() {
        return suspenseRefundComplete;
    }

    /**
     * Sets the suspense refund complete field.
     *
     * @param suspenseRefundComplete
     *         the field value.
     */
    public void setSuspenseRefundComplete(SimpleBalancedItem suspenseRefundComplete) {
        this.suspenseRefundComplete = suspenseRefundComplete;
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
