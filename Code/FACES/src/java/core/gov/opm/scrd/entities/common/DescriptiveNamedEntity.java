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

package gov.opm.scrd.entities.common;

/**
 * <p>
 * Represents the named entity with description.
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
public abstract class DescriptiveNamedEntity extends NamedEntity {
    /**
     * <p>
     * Represents the entity description. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String description;

    /**
     * Creates an instance of DescriptiveNamedEntity.
     */
    protected DescriptiveNamedEntity() {
        // Empty
    }

    /**
     * Gets the entity description.
     *
     * @return the entity description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the entity description.
     *
     * @param description
     *            the entity description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
