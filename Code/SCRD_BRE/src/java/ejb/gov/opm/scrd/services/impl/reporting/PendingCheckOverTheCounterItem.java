/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

/**
 * This class represents the response item for the balanced scorecard payment report. <p>
 * <strong>Thread-safety:</strong> This class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class PendingCheckOverTheCounterItem {
    /**
     * Represents the posted pending field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem postedPending;

    /**
     * Represents the posted complete field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem postedComplete;

    /**
     * Creates a new instance of the {@link PendingCheckOverTheCounterItem} class.
     */
    public PendingCheckOverTheCounterItem() {
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
}
