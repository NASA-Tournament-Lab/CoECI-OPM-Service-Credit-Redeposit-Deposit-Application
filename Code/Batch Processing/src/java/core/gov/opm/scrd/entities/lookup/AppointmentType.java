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

package gov.opm.scrd.entities.lookup;

import gov.opm.scrd.entities.common.OrderedNamedEntity;

/**
 * <p>
 * Represents the named entity specifying the appointment type of the calculation data of account.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Changed to extend OrderedNamedEntity.</li>
 * <li>Added fields: description, category</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.1
 */
public class AppointmentType extends OrderedNamedEntity {
    /**
     * <p>
     * Represents the entity description. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String description;
    /**
     * <p>
     * Represents the appointment type category. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String category;

    /**
     * Creates an instance of AppointmentType.
     */
    public AppointmentType() {
        // Empty
    }

    /**
     * Gets the entity description.
     *
     * @return the entity description.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the entity description.
     *
     * @param description
     *            the entity description.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the appointment type category.
     *
     * @return the appointment type category.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the appointment type category.
     *
     * @param category
     *            the appointment type category.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
