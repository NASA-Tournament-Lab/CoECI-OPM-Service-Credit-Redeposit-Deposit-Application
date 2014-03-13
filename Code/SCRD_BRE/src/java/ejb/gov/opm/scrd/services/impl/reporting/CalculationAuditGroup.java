/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.Date;
import java.util.List;

/**
 * This class represents the response group for the namesake report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class CalculationAuditGroup {
    /**
     * Represents calculation date field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date calculationDate;

    /**
     * Represents action field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String action;

    /**
     * Represents effective date field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date effective;

    /**
     * Represents list of calculation audit items. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private List<CalculationAuditItem> items;

    /**
     * Creates a new instance of the {@link CalculationAuditGroup} class.
     */
    public CalculationAuditGroup() {
    }

    /**
     * Gets the calculation date field.
     *
     * @return the field value.
     */
    public Date getCalculationDate() {
        return calculationDate;
    }

    /**
     * Sets the calculation date field.
     *
     * @param calculationDate
     *         the field value.
     */
    public void setCalculationDate(Date calculationDate) {
        this.calculationDate = calculationDate;
    }

    /**
     * Gets the action field.
     *
     * @return the field value.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action field.
     *
     * @param action
     *         the field value.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets the effective date field.
     *
     * @return the field value.
     */
    public Date getEffective() {
        return effective;
    }

    /**
     * Sets the effective date field.
     *
     * @param effective
     *         the field value.
     */
    public void setEffective(Date effective) {
        this.effective = effective;
    }

    /**
     * Gets the list of calculation audit items.
     *
     * @return the field value.
     */
    public List<CalculationAuditItem> getItems() {
        return items;
    }

    /**
     * Sets the list of calculation audit items.
     *
     * @param items
     *         the field value.
     */
    public void setItems(List<CalculationAuditItem> items) {
        this.items = items;
    }
}
