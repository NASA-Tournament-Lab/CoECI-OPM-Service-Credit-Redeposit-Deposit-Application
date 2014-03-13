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

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Represents the audit record containing the information about data modification/removal in the application. This
 * entity contains general information about audited entity and a reference to list of individual parameters containing
 * information about entity fields.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class AuditRecord extends IdentifiableEntity {
    /**
     * <p>
     * Represents the name of the user who performed the operation to audit. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private String username;
    /**
     * <p>
     * Represents the IP address of the user who performed the operation to audit. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String ipAddress;
    /**
     * <p>
     * Represents the name of the operation to audit. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private String actionName;
    /**
     * <p>
     * Represents the timestamp of the operation to audit. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Date date;

    /**
     * <p> Represents the description. </p>
     */
    private String description;

    /**
     * <p>
     * Represents the references to each audit item field to audit. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private List<AuditParameterRecord> parameters;

    /**
     * Creates an instance of AuditRecord.
     */
    public AuditRecord() {
        // Empty
    }

    /**
     * Gets the name of the user who performed the operation to audit.
     *
     * @return the name of the user who performed the operation to audit.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the name of the user who performed the operation to audit.
     *
     * @param username
     *            the name of the user who performed the operation to audit.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the IP address of the user who performed the operation to audit.
     *
     * @return the IP address of the user who performed the operation to audit.
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the IP address of the user who performed the operation to audit.
     *
     * @param ipAddress
     *            the IP address of the user who performed the operation to audit.
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Gets the name of the operation to audit.
     *
     * @return the name of the operation to audit.
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Sets the name of the operation to audit.
     *
     * @param actionName
     *            the name of the operation to audit.
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * Gets the timestamp of the operation to audit.
     *
     * @return the timestamp of the operation to audit.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the timestamp of the operation to audit.
     *
     * @param date
     *            the timestamp of the operation to audit.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the references to each audit item field to audit.
     *
     * @return the references to each audit item field to audit.
     */
    public List<AuditParameterRecord> getParameters() {
        return parameters;
    }

    /**
     * Sets the references to each audit item field to audit.
     *
     * @param parameters
     *            the references to each audit item field to audit.
     */
    public void setParameters(List<AuditParameterRecord> parameters) {
        this.parameters = parameters;
    }


    /**
     * Gets the description.
     *
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description
     *         the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
