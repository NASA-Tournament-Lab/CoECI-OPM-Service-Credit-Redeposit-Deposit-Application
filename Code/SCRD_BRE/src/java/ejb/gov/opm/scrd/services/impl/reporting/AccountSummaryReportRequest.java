/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.entities.application.ReportRequest;

/**
 * This class represents the request for the namesake report. <p> <strong>Thread-safety:</strong> This class is mutable
 * and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class AccountSummaryReportRequest implements ReportRequest {
    /**
     * Represents claim number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String csd;

    /**
     * Creates a new instance of the {@link AccountSummaryReportRequest} class.
     */
    public AccountSummaryReportRequest() {
    }

    /**
     * Gets the claim number field.
     *
     * @return the claim number field.
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the claim number field.
     *
     * @param csd
     *         the field value.
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }
}
