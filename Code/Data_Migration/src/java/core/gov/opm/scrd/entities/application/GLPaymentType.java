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
 * Represents the entity specifying General Ledger payment type.
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
public class GLPaymentType extends IdentifiableEntity {
    /**
     * <p>
     * Represents the code of type. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String paymentCode;
    /**
     * <p>
     * Represents the description of type. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String codeDescription;

    /**
     * Creates an instance of GLPaymentType.
     */
    public GLPaymentType() {
        // Empty
    }

    /**
     * Gets the code of type.
     *
     * @return the code of type.
     */
    public String getPaymentCode() {
        return paymentCode;
    }

    /**
     * Sets the code of type.
     *
     * @param paymentCode
     *            the code of type.
     */
    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    /**
     * Gets the description of type.
     *
     * @return the description of type.
     */
    public String getCodeDescription() {
        return codeDescription;
    }

    /**
     * Sets the description of type.
     *
     * @param codeDescription
     *            the description of type.
     */
    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }
}
