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
 * @version 1.1
 */
public class UserAuditTrailReportResponseItem {
    /**
     * Represents the audit date time field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date auditDateTime;

    /**
     * Represents the database table field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String databaseTable;

    /**
     * Represents the activity field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String activity;

    /**
     * Represents the claim number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String csd;

    /**
     * Represents the description field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String description;

    /**
     * Represents the user description field. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String userDescription;

    /**
     * Represents the role field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String role;

    /**
     * Represents the supervisor role field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String supervisingRole;

    /**
     * Represents the email field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String email;

    /**
     * Represents the telephone field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String telephone;

    /**
     * Creates a new instance of the {@link UserAuditTrailReportResponseItem} class.
     */
    public UserAuditTrailReportResponseItem() {
    }

    /**
     * Gets the audit date time field.
     *
     * @return the field value.
     */
    public Date getAuditDateTime() {
        return auditDateTime;
    }

    /**
     * Sets the audit date time field.
     *
     * @param auditDateTime
     *         the field value.
     */
    public void setAuditDateTime(Date auditDateTime) {
        this.auditDateTime = auditDateTime;
    }

    /**
     * Gets the database table field.
     *
     * @return the field value.
     */
    public String getDatabaseTable() {
        return databaseTable;
    }

    /**
     * Sets the database table field.
     *
     * @param databaseTable
     *         the field value.
     */
    public void setDatabaseTable(String databaseTable) {
        this.databaseTable = databaseTable;
    }

    /**
     * Gets the activity field.
     *
     * @return the field value.
     */
    public String getActivity() {
        return activity;
    }

    /**
     * Sets the activity field.
     *
     * @param activity
     *         the field value.
     */
    public void setActivity(String activity) {
        this.activity = activity;
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
     * Gets the description field.
     *
     * @return the field value.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description field.
     *
     * @param description
     *         the field value.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the user description field.
     *
     * @return the field value.
     */
    public String getUserDescription() {
        return userDescription;
    }

    /**
     * Sets the user description field.
     *
     * @param userDescription
     *         the field value.
     */
    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    /**
     * Gets the role field.
     *
     * @return the field value.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role field.
     *
     * @param role
     *         the field value.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the supervisor role field.
     *
     * @return the field value.
     */
    public String getSupervisingRole() {
        return supervisingRole;
    }

    /**
     * Sets the supervisor role field.
     *
     * @param supervisingRole
     *         the field value.
     */
    public void setSupervisingRole(String supervisingRole) {
        this.supervisingRole = supervisingRole;
    }

    /**
     * Gets the email field.
     *
     * @return the field value.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email field.
     *
     * @param email
     *         the field value.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the telephone field.
     *
     * @return the field value.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the telephone field.
     *
     * @param telephone
     *         the field value.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
