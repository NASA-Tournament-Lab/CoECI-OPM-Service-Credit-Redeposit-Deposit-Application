/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

/**
 * This class represents the response item for the namesake report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class UserPermissionsRolesReportResponseItem {
    /**
     * Represents role field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private String role;

    /**
     * Represents username field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String userName;

    /**
     * Represents opm name field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String opmName;

    /**
     * Represents telephone field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String telephone;

    /**
     * Represents email field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private String email;

    /**
     * Represents user status field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String userStatus;

    /**
     * Represents supervisor field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String supervisor;

    /**
     * Creates a new instance of the {@link UserPermissionsRolesReportResponseItem} class.
     */
    public UserPermissionsRolesReportResponseItem() {
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
     * Gets the username field.
     *
     * @return the field value.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username field.
     *
     * @param userName
     *         the field value.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the opm name field.
     *
     * @return the field value.
     */
    public String getOpmName() {
        return opmName;
    }

    /**
     * Sets the opm name field.
     *
     * @param opmName
     *         the field value.
     */
    public void setOpmName(String opmName) {
        this.opmName = opmName;
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
     * Gets the user status field.
     *
     * @return the field value.
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * Sets the user status field.
     *
     * @param userStatus
     *         the field value.
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * Gets the supervisor field.
     *
     * @return the field value.
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * Sets the supervisor field.
     *
     * @param supervisor
     *         the field value.
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
