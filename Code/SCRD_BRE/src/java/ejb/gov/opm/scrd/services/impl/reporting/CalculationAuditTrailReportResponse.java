/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;

/**
 * This class represents the response for the namesake report. <p> <strong>Thread-safety:</strong> This class is mutable
 * and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class CalculationAuditTrailReportResponse extends BaseReportResponse {
    /**
     * Represents claim number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String csd;

    /**
     * Represents list of CalculationAuditGroups. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private List<CalculationAuditGroup> groups;

    /**
     * Creates a new instance of the {@link CalculationAuditTrailReportResponse} class.
     */
    public CalculationAuditTrailReportResponse() {
        super();
    }

    /**
     * Gets the claim number field.
     *
     * @return field value.
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the cliam number field.
     *
     * @param csd
     *         the field value.
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the list of CalculationAuditGroups.
     *
     * @return field value.
     */
    public List<CalculationAuditGroup> getGroups() {
        return groups;
    }

    /**
     * Sets the list of CalculationAuditGroups.
     *
     * @param groups
     *         the field value.
     */
    public void setGroups(List<CalculationAuditGroup> groups) {
        this.groups = groups;
    }
}
