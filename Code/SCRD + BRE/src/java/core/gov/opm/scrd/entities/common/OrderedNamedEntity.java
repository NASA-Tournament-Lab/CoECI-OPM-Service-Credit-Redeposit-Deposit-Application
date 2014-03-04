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
 * Represents the named entity with display order.
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
public abstract class OrderedNamedEntity extends NamedEntity {
    /**
     * <p>
     * Represents the entity display order. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer displayOrder;

    /**
     * Creates an instance of OrderedNamedEntity.
     */
    protected OrderedNamedEntity() {
        // Empty
    }

    /**
     * Gets the entity display order.
     *
     * @return the entity display order.
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Sets the entity display order.
     *
     * @param displayOrder
     *            the entity display order.
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}
