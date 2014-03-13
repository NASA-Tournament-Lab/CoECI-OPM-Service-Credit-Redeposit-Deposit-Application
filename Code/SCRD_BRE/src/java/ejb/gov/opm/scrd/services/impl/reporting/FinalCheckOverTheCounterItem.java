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
public class FinalCheckOverTheCounterItem {
    /**
     * Represents the posted complete field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private SimpleBalancedItem postedComplete;

    /**
     * Represents the over the counter sub total field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal overTheCounterSubTotal;

    /**
     * Creates a new instance of the {@link FinalCheckOverTheCounterItem} class.
     */
    public FinalCheckOverTheCounterItem() {
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
     * Gets the over the counter sub total field.
     *
     * @return the field value.
     */
    public BigDecimal getOverTheCounterSubTotal() {
        return overTheCounterSubTotal;
    }

    /**
     * Sets the over the counter sub total field.
     *
     * @param overTheCounterSubTotal
     *         the field value.
     */
    public void setOverTheCounterSubTotal(BigDecimal overTheCounterSubTotal) {
        this.overTheCounterSubTotal = overTheCounterSubTotal;
    }
}
