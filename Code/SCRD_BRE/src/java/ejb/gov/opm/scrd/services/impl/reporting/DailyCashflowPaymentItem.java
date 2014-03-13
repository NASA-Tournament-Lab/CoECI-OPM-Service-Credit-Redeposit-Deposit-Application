/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;

/**
 * This class represents the response item for the daily cash flow report. <p> <strong>Thread-safety:</strong> This
 * class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class DailyCashflowPaymentItem {
    /**
     * Represents the name field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String name;

    /**
     * Represents the number field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer number;

    /**
     * Represents the amount field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal amount;

    /**
     * Creates a new instance of the {@link DailyCashflowPaymentItem} class.
     */
    public DailyCashflowPaymentItem() {
    }

    /**
     * Gets the name field.
     *
     * @return the field value.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name field.
     *
     * @param name
     *         the field value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the number field.
     *
     * @return the field value.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Sets the number field.
     *
     * @param number
     *         the field value.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Gets the pay field.
     *
     * @return the field value.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount field.
     *
     * @param amount
     *         the field value.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
