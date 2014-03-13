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
public class FinalCheckBatchProcessItem {
    /**
     * Represents batch processing total field. It is accesible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private SimpleBalancedItem batchProcessingTotal;

    /**
     * Represents the batch processing sub total field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal batchProcessSubTotal;

    /**
     * Creates a new instance of the {@link FinalCheckBatchProcessItem} class.
     */
    public FinalCheckBatchProcessItem() {
    }

    /**
     * Gets the batch processing total field.
     *
     * @return the field value.
     */
    public SimpleBalancedItem getBatchProcessingTotal() {
        return batchProcessingTotal;
    }

    /**
     * Sets the batch processing total field.
     *
     * @param batchProcessingTotal
     *         the field value.
     */
    public void setBatchProcessingTotal(SimpleBalancedItem batchProcessingTotal) {
        this.batchProcessingTotal = batchProcessingTotal;
    }

    /**
     * Gets the batch processing sub total field.
     *
     * @return the field value.
     */
    public BigDecimal getBatchProcessSubTotal() {
        return batchProcessSubTotal;
    }

    /**
     * Sets the batch processing sub total field.
     *
     * @param batchProcessSubTotal
     *         the field value.
     */
    public void setBatchProcessSubTotal(BigDecimal batchProcessSubTotal) {
        this.batchProcessSubTotal = batchProcessSubTotal;
    }
}
