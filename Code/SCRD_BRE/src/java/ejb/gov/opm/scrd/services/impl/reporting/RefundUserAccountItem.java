/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.Date;

/**
 * This class represents the response item for the namesake report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class RefundUserAccountItem {
    /**
     * Represents claim number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String csd;

    /**
     * Represents assignment date field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date assignmentDate;

    /**
     * Represents days number field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer daysNumber;

    /**
     * Represents account status description field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String accountStatusDescription;

    /**
     * Creates a new instance of the {@link RefundUserAccountItem} class.
     */
    public RefundUserAccountItem() {
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

    /**
     * Gets the assignment date field.
     *
     * @return the field value.
     */
    public Date getAssignmentDate() {
        return assignmentDate;
    }

    /**
     * Sets the assignment date field.
     *
     * @param assignmentDate
     *         the field value.
     */
    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    /**
     * Gets the days number field.
     *
     * @return the field value.
     */
    public Integer getDaysNumber() {
        return daysNumber;
    }

    /**
     * Sets the days number field.
     *
     * @param daysNumber
     *         the field value.
     */
    public void setDaysNumber(Integer daysNumber) {
        this.daysNumber = daysNumber;
    }

    /**
     * Gets the account status description field.
     *
     * @return the field value.
     */
    public String getAccountStatusDescription() {
        return accountStatusDescription;
    }

    /**
     * Sets the account status description field.
     *
     * @param accountStatusDescription
     *         the field value.
     */
    public void setAccountStatusDescription(String accountStatusDescription) {
        this.accountStatusDescription = accountStatusDescription;
    }
}
