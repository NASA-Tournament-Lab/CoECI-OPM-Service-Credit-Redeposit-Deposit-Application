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

import java.util.Date;

/**
 * <p>
 * Represents the entity specifying the accounts that do not have service.
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
public class ClaimWithoutService extends IdentifiableEntity {
    /**
     * <p>
     * Represents the claim number of entity. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String claimNumber;
    /**
     * <p>
     * Represents the date of birth of entity. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date dateOfBirth;

    /**
     * Creates an instance of ClaimWithoutService.
     */
    public ClaimWithoutService() {
        // Empty
    }

    /**
     * Gets the claim number of entity.
     *
     * @return the claim number of entity.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number of entity.
     *
     * @param claimNumber
     *            the claim number of entity.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the date of birth of entity.
     *
     * @return the date of birth of entity.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of entity.
     *
     * @param dateOfBirth
     *            the date of birth of entity.
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
