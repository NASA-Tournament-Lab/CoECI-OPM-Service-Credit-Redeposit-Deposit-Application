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

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This is the base class for an entity with an ID.
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * 
 * @author argolite,TCSASSEMBLER
 * @version 1.0
 */
@MappedSuperclass
public abstract class IdentifiableEntity {
    /**
     * Represents the ID of the entity.
     */
    @Id
    private long id;

    /**
     * Empty constructor.
     */
    protected IdentifiableEntity() {

    }

    /**
     * Getter for the id field.
     * 
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for the id field.
     * 
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
