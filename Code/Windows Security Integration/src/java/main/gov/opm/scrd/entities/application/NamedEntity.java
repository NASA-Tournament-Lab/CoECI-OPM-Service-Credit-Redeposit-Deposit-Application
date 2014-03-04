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

import javax.persistence.MappedSuperclass;

/**
 * This is the base class for an entity with an ID and Name.
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * 
 * @author argolite,TCSASSEMBLER
 * @version 1.0
 */
@MappedSuperclass
public abstract class NamedEntity extends IdentifiableEntity {
    /**
     * Represents the name of the entity.
     */
    private String name;

    /**
     * Empty constructor.
     */
    protected NamedEntity() {

    }

    /**
     * Getter for the name field.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name field.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
