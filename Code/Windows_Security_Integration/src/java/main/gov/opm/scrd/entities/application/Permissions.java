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

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * This is the permission class to check authorization.
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * 
 * @author argolite,TCSASSEMBLER
 * @version 1.0
 */
@Entity
public class Permissions extends IdentifiableEntity {

    /**
     * Represents the user name.
     */
    private String userName;

    /**
     * Represents the action name.
     */
    private String action;

    /**
     * Represents the valid roles to perform action.
     */
    @OneToMany
    private List<Role> roles;

    /**
     * Empty constructor.
     */
    public Permissions() {

    }

    /**
     * Getter for the userName field.
     * 
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for the userName field.
     * 
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for the action field.
     * 
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * Setter for the action field.
     * 
     * @param action
     *            the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Getter for the roles field.
     * 
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Setter for the roles field.
     * 
     * @param roles
     *            the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
