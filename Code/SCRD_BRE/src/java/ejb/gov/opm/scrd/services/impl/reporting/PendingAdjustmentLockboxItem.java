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
public class PendingAdjustmentLockboxItem {
    /**
     * Represents the posted pending approval field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private SimpleBalancedItem postedPendingApproval;

    /**
     * Represents the adjustment sub total field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal adjustmentSubTotal;

    /**
     * Creates a new instance of the {@link PendingAdjustmentLockboxItem} class.
     */
    public PendingAdjustmentLockboxItem() {
    }

    /**
     * Gets the posted pending approval field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getPostedPendingApproval() {
        return postedPendingApproval;
    }

    /**
     * Sets the posted pending approval field.
     *
     * @param postedPendingApproval
     *         the field value.
     */
    public void setPostedPendingApproval(SimpleBalancedItem postedPendingApproval) {
        this.postedPendingApproval = postedPendingApproval;
    }

    /**
     * Gets the adjustment sub total field.
     *
     * @return the field value.
     */
    public BigDecimal getAdjustmentSubTotal() {
        return adjustmentSubTotal;
    }

    /**
     * Sets the adjustment sub total field.
     *
     * @param adjustmentSubTotal
     *         the field value.
     */
    public void setAdjustmentSubTotal(BigDecimal adjustmentSubTotal) {
        this.adjustmentSubTotal = adjustmentSubTotal;
    }
}
