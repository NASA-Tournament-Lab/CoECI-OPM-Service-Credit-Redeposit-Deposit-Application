/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;
import java.util.Map;

/**
 * This class represents the response for the namesake report. <p> <strong>Thread-safety:</strong> This class is mutable
 * and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class UserRolePermissionsReportResponse extends BaseReportResponse {
    /**
     * Represents the list of roles. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private List<String> roles;

    /**
     * Represents the list of role descriptions. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private List<String> roleDescriptions;

    /**
     * Represents the list of permissions. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private List<String> permissions;

    /**
     * Represents the map between roles and allowed permissions. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private Map<String, List<String>> allowedPermissions;

    /**
     * Creates a new instance of the {@link UserRolePermissionsReportResponse} class.
     */
    public UserRolePermissionsReportResponse() {
        super();
    }

    /**
     * Gets the list of roles.
     *
     * @return the list of roles.
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets the list of roles.
     *
     * @param roles
     *         the list of roles.
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Gets the list of role descriptions.
     *
     * @return the list of role descriptions.
     */
    public List<String> getRoleDescriptions() {
        return roleDescriptions;
    }

    /**
     * Sets the list of role descriptions.
     *
     * @param roleDescriptions
     *         the list of role descriptions.
     */
    public void setRoleDescriptions(List<String> roleDescriptions) {
        this.roleDescriptions = roleDescriptions;
    }

    /**
     * Gets the map between roles and allowed descriptions.
     *
     * @return the map between roles and allowed descriptions.
     */
    public Map<String, List<String>> getAllowedPermissions() {
        return allowedPermissions;
    }

    /**
     * Sets the map between roles and allowed descriptions.
     *
     * @param allowedPermissions
     *         the map between roles and allowed descriptions.
     */
    public void setAllowedPermissions(
            Map<String, List<String>> allowedPermissions) {
        this.allowedPermissions = allowedPermissions;
    }

    /**
     * Gets the list of descriptions.
     *
     * @return the list of descriptions.
     */
    public List<String> getPermissions() {
        return permissions;
    }

    /**
     * Sets the list of descriptions.
     *
     * @param permissions
     *         the list of descriptions.
     */
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
