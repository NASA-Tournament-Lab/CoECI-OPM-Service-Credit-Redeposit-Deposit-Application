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
 * This is the class representing the information validation of single field.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class AccountConfirmationValidationEntry extends IdentifiableEntity {
    /**
     * <p>
     * Represents the field name of validation data. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private String fieldName;
    /**
     * <p>
     * Represents the flag specifying whether data is valid. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Boolean valid;

    /**
     * Creates an instance of AccountConfirmationValidationEntry.
     */
    public AccountConfirmationValidationEntry() {
        // Empty
    }

    /**
     * Gets the field name of validation data.
     *
     * @return the field name of validation data.
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the field name of validation data.
     *
     * @param fieldName
     *            the field name of validation data.
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Gets the flag specifying whether data is valid.
     *
     * @return the flag specifying whether data is valid.
     */
    public Boolean getValid() {
        return valid;
    }

    /**
     * Sets the flag specifying whether data is valid.
     *
     * @param valid
     *            the flag specifying whether data is valid.
     */
    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
