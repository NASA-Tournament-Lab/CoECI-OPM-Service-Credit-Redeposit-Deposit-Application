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
 * This is the base class for all entities that have an identification number. Also has a flag for soft deletes.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public abstract class IdentifiableEntity {
    /**
     * Represents the primary identifier of the entity. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     */
    private long id;

    /**
     * Represents the flag whether the entity is logically deleted. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     */
    private boolean deleted;

    /**
     * Creates an instance of IdentifiableEntity.
     */
    protected IdentifiableEntity() {
        // Empty
    }

    /**
     * Gets the primary identifier of the entity.
     *
     * @return the primary identifier of the entity.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the primary identifier of the entity.
     *
     * @param id
     *            the primary identifier of the entity.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the flag whether the entity is logically deleted.
     *
     * @return the flag whether the entity is logically deleted.
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the flag whether the entity is logically deleted.
     *
     * @param deleted
     *            the flag whether the entity is logically deleted.
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Determines whether the specified Object is equal to the current Object.
     *
     * @param obj
     *            the Object to compare with the current Object
     *
     * @return true if the specified Object is equal to the current Object; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        return (id == ((IdentifiableEntity) obj).id);
    }

    /**
     * Serves as a hash function for a particular type.
     *
     * @return the hash code for the current Object.
     */
    @Override
    public int hashCode() {
        return (int) id;
    }

    /**
     * Converts the entity to a string.
     *
     * @return the string with entity data.
     */
    @Override
    public String toString() {
        return Helper.toString(this);
    }
}
