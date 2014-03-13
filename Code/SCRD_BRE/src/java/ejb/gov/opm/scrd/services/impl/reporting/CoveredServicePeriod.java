/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.Date;

/**
 * This class represents the response service period for the namesake report. <p> <strong>Thread-safety:</strong> This
 * class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class CoveredServicePeriod {
    /**
     * Represents from date field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Date from;

    /**
     * Represents to date field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Date to;

    /**
     * Represents service type field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String type;

    /**
     * Creates a new instance of the {@link CoveredServicePeriod} class.
     */
    public CoveredServicePeriod() {
    }

    /**
     * Gets the from date field.
     *
     * @return the field value.
     */
    public Date getFrom() {
        return from;
    }

    /**
     * Sets the from date field.
     *
     * @param from
     *         the field value.
     */
    public void setFrom(Date from) {
        this.from = from;
    }

    /**
     * Gets the to date field.
     *
     * @return the field value.
     */
    public Date getTo() {
        return to;
    }

    /**
     * Sets the to date field.
     *
     * @param to
     *         the field value.
     */
    public void setTo(Date to) {
        this.to = to;
    }

    /**
     * Gets the service type field.
     *
     * @return the field value.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the service type field.
     *
     * @param type
     *         the field value.
     */
    public void setType(String type) {
        this.type = type;
    }
}
