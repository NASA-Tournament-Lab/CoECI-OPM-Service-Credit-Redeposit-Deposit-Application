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
public class RefundSectionAccountAssignmentsReportResponse extends
        BaseReportResponse {
    /**
     * Represents the list of RefundUserAccountGroups. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private List<RefundUserAccountGroup> groups;

    /**
     * Represents the average days field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer averageDays;

    /**
     * Represents maximum days field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer maximumDays;

    /**
     * Creates a new instance of the {@link RefundSectionAccountAssignmentsReportResponse} class.
     */
    public RefundSectionAccountAssignmentsReportResponse() {
        super();
    }

    /**
     * Gets the list of RefundUserAccountGroups.
     *
     * @return the field value.
     */
    public List<RefundUserAccountGroup> getGroups() {
        return groups;
    }

    /**
     * Sets the list of RefundUserAccountGroups.
     *
     * @param groups
     *         the field value.
     */
    public void setGroups(List<RefundUserAccountGroup> groups) {
        this.groups = groups;
    }

    /**
     * Gets the average days field.
     *
     * @return the field value.
     */
    public Integer getAverageDays() {
        return averageDays;
    }

    /**
     * Sets the average days field.
     *
     * @param averageDays
     *         the field value.
     */
    public void setAverageDays(Integer averageDays) {
        this.averageDays = averageDays;
    }

    /**
     * Gets the maximum days field.
     *
     * @return the field value.
     */
    public Integer getMaximumDays() {
        return maximumDays;
    }

    /**
     * Sets the maximum days field.
     *
     * @param maximumDays
     *         the field value.
     */
    public void setMaximumDays(Integer maximumDays) {
        this.maximumDays = maximumDays;
    }
}
