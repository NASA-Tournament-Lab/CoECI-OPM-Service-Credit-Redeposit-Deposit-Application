/*
    Copyright 2014 OPM.gov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package gov.opm.scrd.entities.application;

import gov.opm.scrd.entities.common.IdentifiableEntity;

/**
 * <p>
 * This is the class representing the permissions table for the specific user role.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class RolePermission extends IdentifiableEntity {
    /**
     * <p>
     * Represents the role of the user. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String role;
    /**
     * <p>
     * Represents the action performed by the user. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String action;

    /**
     * Creates an instance of RolePermission.
     */
    public RolePermission() {
        // Empty
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role
     *            the role of the user.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the action performed by the user.
     *
     * @return the action performed by the user.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action performed by the user.
     *
     * @param action
     *            the action performed by the user.
     */
    public void setAction(String action) {
        this.action = action;
    }
}
