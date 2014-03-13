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
 * This is the base class for all entities with a name.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public abstract class NamedEntity extends IdentifiableEntity {
    /**
     * <p>
     * Represents the name of the entity. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String name;

    /**
     * Creates an instance of NamedEntity.
     */
    protected NamedEntity() {
        // Empty
    }

    /**
     * Gets the name of the entity.
     *
     * @return the name of the entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the entity.
     *
     * @param name
     *            the name of the entity.
     */
    public void setName(String name) {
        this.name = name;
    }
}
