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
import gov.opm.scrd.entities.lookup.ActionTab;
import gov.opm.scrd.entities.lookup.Role;
import gov.opm.scrd.entities.lookup.UserStatus;

/**
 * <p>
 * This is the class representing the user of the application.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Changes supervisor:User field to supervisorId:Long</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - SCRD - Frontend Account Module Assembly 1.0):</em>
 * <ul>
 * <li>add defaultAccountId : long</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax, liuliquan, TCSACCEMBLER
 * @version 1.2
 */
public class User extends IdentifiableEntity {
    /**
     * <p>
     * Represents the name of the user. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String username;
    /**
     * <p>
     * Represents the tab on the home page that is shown for the user when he opens main page of the portal. If not set,
     * "View Account" tab will be shown. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private ActionTab defaultTab;
    /**
     * <p>
     * Represents the id of the network associated with the user. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private String networkId;
    /**
     * <p>
     * Represents the role of the user. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Role role;
    /**
     * <p>
     * Represents the first name of the user. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String firstName;
    /**
     * <p>
     * Represents the last name of the user. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String lastName;
    /**
     * <p>
     * Represents the email name of the user. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String email;
    /**
     * <p>
     * Represents the telephone of the user. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String telephone;
    /**
     * <p>
     * Represents the supervisor ID of the user. There can be no supervisor associated, in which case this field is set
     * to null. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private Long supervisorId;
    /**
     * <p>
     * Represents the status of the user. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private UserStatus status;
    /**
     * <p>
     * Represents id of account that should be displayed when the user select account detail page as the home page. It
     * is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - SCRD - Frontend Account Module Assembly 1.0)
     */
    private Long defaultTabAccountId;

    /**
     * Creates an instance of User.
     */
    public User() {
        // Empty
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the name of the user.
     *
     * @param username
     *            the name of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the tab on the home page that is shown for the user when he opens main page of the portal.
     *
     * @return the tab on the home page that is shown for the user when he opens main page of the portal.
     */
    public ActionTab getDefaultTab() {
        return defaultTab;
    }

    /**
     * Sets the tab on the home page that is shown for the user when he opens main page of the portal.
     *
     * @param defaultTab
     *            the tab on the home page that is shown for the user when he opens main page of the portal.
     */
    public void setDefaultTab(ActionTab defaultTab) {
        this.defaultTab = defaultTab;
    }

    /**
     * Gets the id of the network associated with the user.
     *
     * @return the id of the network associated with the user.
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * Sets the id of the network associated with the user.
     *
     * @param networkId
     *            the id of the network associated with the user.
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role
     *            the role of the user.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the first name of the user.
     *
     * @return the first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName
     *            the first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return the last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName
     *            the last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email name of the user.
     *
     * @return the email name of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email name of the user.
     *
     * @param email
     *            the email name of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the telephone of the user.
     *
     * @return the telephone of the user.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the telephone of the user.
     *
     * @param telephone
     *            the telephone of the user.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Gets the supervisor ID of the user.
     *
     * @return the supervisor ID of the user.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    public Long getSupervisorId() {
        return supervisorId;
    }

    /**
     * Sets the supervisor ID of the user.
     *
     * @param supervisorId
     *            the supervisor ID of the user.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    /**
     * Gets the status of the user.
     *
     * @return the status of the user.
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     *
     * @param status
     *            the status of the user.
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Gets default display account of the user.
     *
     * @return the default display account of the user.
     *
     * @since 1.2 (OPM - SCRD - Frontend Account Module Assembly 1.0)
     */
    public Long getDefaultTabAccountId() {
        return defaultTabAccountId;
    }

    /**
     * Sets the default display account id of the user.
     *
     * @param defaultTabAccountId
     *            the default display account the user.
     *
     * @since 1.2 (OPM - SCRD - Frontend Account Module Assembly 1.0)
     */
    public void setDefaultTabAccountId(Long defaultTabAccountId) {
        this.defaultTabAccountId = defaultTabAccountId;
    }
}
