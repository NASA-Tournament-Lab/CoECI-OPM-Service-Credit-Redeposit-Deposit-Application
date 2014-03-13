/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.List;

/**
 * This class represents the response group for the namesake report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class RefundUserAccountGroup {
    /**
     * Represents the list of RefundUserAccountItems. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private List<RefundUserAccountItem> items;

    /**
     * Represents the user field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String user;

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
     * Represents the network id field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String networkId;

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
     * Represents the average days field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer averageDays;

    /**
     * Represents the maximum days field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer maximumDays;

    /**
     * Creates a new instance of the {@link RefundUserAccountGroup} class.
     */
    public RefundUserAccountGroup() {
    }

    /**
     * Gets the list of RefundUserAccountItems.
     *
     * @return the field value.
     */
    public List<RefundUserAccountItem> getItems() {
        return items;
    }

    /**
     * Sets the list of RefundUserAccountItems.
     *
     * @param items
     *         the field value.
     */
    public void setItems(List<RefundUserAccountItem> items) {
        this.items = items;
    }

    /**
     * Gets the user field.
     *
     * @return the field value.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user field.
     *
     * @param user
     *         the field value.
     */
    public void setUser(String user) {
        this.user = user;
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

    /**
     * Gets the network id field.
     *
     * @return the field value.
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * Sets the network id field.
     *
     * @param networkId
     *         the field value.
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
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
