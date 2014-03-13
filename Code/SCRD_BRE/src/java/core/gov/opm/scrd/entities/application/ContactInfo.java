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
 * Represents the entity specifying contact info.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class ContactInfo extends IdentifiableEntity {
    /**
     * <p>
     * Represents the name of contact. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String name;
    /**
     * <p>
     * Represents the text of contact. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String text;

    /**
     * Creates an instance of ContactInfo.
     */
    public ContactInfo() {
        // Empty
    }

    /**
     * Gets the name of contact.
     *
     * @return the name of contact.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of contact.
     *
     * @param name
     *            the name of contact.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the text of contact.
     *
     * @return the text of contact.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of contact.
     *
     * @param text
     *            the text of contact.
     */
    public void setText(String text) {
        this.text = text;
    }
}
