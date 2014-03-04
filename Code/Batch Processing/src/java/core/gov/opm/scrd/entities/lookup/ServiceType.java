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
 * Represents the named entity specifying the service type of the calculation data of account.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Changed to extend OrderedNamedEntity.</li>
 * <li>Added fields: fersDepositAllowedAfter88</li>
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
public class ServiceType extends OrderedNamedEntity {
    /**
     * <p>
     * Represents the number of FERS deposit allowed after 1988. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Integer fersDepositAllowedAfter88;

    /**
     * Creates an instance of ServiceType.
     */
    public ServiceType() {
        // Empty
    }

    /**
     * Gets the number of FERS deposit allowed after 1988.
     *
     * @return the number of FERS deposit allowed after 1988.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Integer getFersDepositAllowedAfter88() {
        return fersDepositAllowedAfter88;
    }

    /**
     * Sets the number of FERS deposit allowed after 1988.
     *
     * @param fersDepositAllowedAfter88
     *            the number of FERS deposit allowed after 1988.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setFersDepositAllowedAfter88(Integer fersDepositAllowedAfter88) {
        this.fersDepositAllowedAfter88 = fersDepositAllowedAfter88;
    }
}
