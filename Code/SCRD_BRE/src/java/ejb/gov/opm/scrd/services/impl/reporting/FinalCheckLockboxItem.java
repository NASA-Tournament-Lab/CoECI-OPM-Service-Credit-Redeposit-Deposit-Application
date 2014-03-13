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
public class FinalCheckLockboxItem {
    /**
     * Represents the annuity complete field. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private SimpleBalancedItem annuityComplete;

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
     * Represents the posted complete field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem postedComplete;

    /**
     * Represents the suspense refund field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem suspenseRefund;

    /**
     * Represents the voluntary contributions field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private SimpleBalancedItem voluntaryContributions;

    /**
     * Represents the check sub total field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem checkSubTotal;

    /**
     * Represents the lockbox bank sub total field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal lockboxBankSubTotal;

    /**
     * Creates a new instance of the {@link FinalCheckLockboxItem} class.
     */
    public FinalCheckLockboxItem() {
    }

    /**
     * Gets the annuity complete field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getAnnuityComplete() {
        return annuityComplete;
    }

    /**
     * Sets the annuity complete field.
     *
     * @param annuityComplete
     *         the field value.
     */
    public void setAnnuityComplete(SimpleBalancedItem annuityComplete) {
        this.annuityComplete = annuityComplete;
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
     * Gets the voluntary contributions field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getVoluntaryContributions() {
        return voluntaryContributions;
    }

    /**
     * Sets the voluntary contributions field.
     *
     * @param voluntaryContributions
     *         the field value.
     */
    public void setVoluntaryContributions(SimpleBalancedItem voluntaryContributions) {
        this.voluntaryContributions = voluntaryContributions;
    }

    /**
     * Gets the name field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getCheckSubTotal() {
        return checkSubTotal;
    }

    /**
     * Sets the check sub total field.
     *
     * @param checkSubTotal
     *         the field value.
     */
    public void setCheckSubTotal(SimpleBalancedItem checkSubTotal) {
        this.checkSubTotal = checkSubTotal;
    }

    /**
     * Gets the lockbox bank sub total field.
     *
     * @return the field value.
     */
    public BigDecimal getLockboxBankSubTotal() {
        return lockboxBankSubTotal;
    }

    /**
     * Sets the lockbox bank sub total field.
     *
     * @param lockboxBankSubTotal
     *         the field value.
     */
    public void setLockboxBankSubTotal(BigDecimal lockboxBankSubTotal) {
        this.lockboxBankSubTotal = lockboxBankSubTotal;
    }
}
