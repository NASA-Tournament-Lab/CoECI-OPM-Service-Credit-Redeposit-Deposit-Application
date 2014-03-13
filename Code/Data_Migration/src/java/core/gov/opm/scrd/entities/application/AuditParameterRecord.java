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
 * Represents the audit parameter record containing the audit information about entity fields.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class AuditParameterRecord extends IdentifiableEntity {
    /**
     * <p>
     * Represents the id of the field to audit. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Long itemId;
    /**
     * <p>
     * Represents the type(class name of the field) of the field to audit. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private String itemType;
    /**
     * <p>
     * Represents the name of the field to audit. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String propertyName;
    /**
     * <p>
     * Represents the previous value of the field to audit. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String previousValue;
    /**
     * <p>
     * Represents the new value of the field to audit. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private String newValue;

    /**
     * Creates an instance of AuditParameterRecord.
     */
    public AuditParameterRecord() {
        // Empty
    }

    /**
     * Gets the id of the field to audit.
     *
     * @return the id of the field to audit.
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * Sets the id of the field to audit.
     *
     * @param itemId
     *            the id of the field to audit.
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the type(class name of the field) of the field to audit.
     *
     * @return the type(class name of the field) of the field to audit.
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Sets the type(class name of the field) of the field to audit.
     *
     * @param itemType
     *            the type(class name of the field) of the field to audit.
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the name of the field to audit.
     *
     * @return the name of the field to audit.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the name of the field to audit.
     *
     * @param propertyName
     *            the name of the field to audit.
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * Gets the previous value of the field to audit.
     *
     * @return the previous value of the field to audit.
     */
    public String getPreviousValue() {
        return previousValue;
    }

    /**
     * Sets the previous value of the field to audit.
     *
     * @param previousValue
     *            the previous value of the field to audit.
     */
    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    /**
     * Gets the new value of the field to audit.
     *
     * @return the new value of the field to audit.
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * Sets the new value of the field to audit.
     *
     * @param newValue
     *            the new value of the field to audit.
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
