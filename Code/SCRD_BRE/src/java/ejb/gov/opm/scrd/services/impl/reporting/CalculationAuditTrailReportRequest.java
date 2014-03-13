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
public class CalculationAuditTrailReportRequest implements ReportRequest {
    /**
     * Represents the flag indicates if current account is included. It is accessible by getter and modified by setter.
     * It can be any value. The default value is null.
     */
    private Boolean includeCurrentAccount;

    /**
     * Represents claim number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String csd;

    /**
     * Creates a new instance of the {@link CalculationAuditTrailReportRequest} class.
     */
    public CalculationAuditTrailReportRequest() {
    }

    /**
     * Gets the flag indicates if current account is included.
     *
     * @return the field value.
     */
    public Boolean getIncludeCurrentAccount() {
        return includeCurrentAccount;
    }

    /**
     * Sets the flag indicates if current account is included.
     *
     * @param includeCurrentAccount
     *         the field value.
     */
    public void setIncludeCurrentAccount(Boolean includeCurrentAccount) {
        this.includeCurrentAccount = includeCurrentAccount;
    }

    /**
     * Gets the claim number field.
     *
     * @return the field value.
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
