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
import gov.opm.scrd.entities.lookup.RetirementType;

/**
 * <p>
 * Represents the entity specifying General Ledger code.
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
public class GLCode extends IdentifiableEntity {
    /**
     * <p>
     * Represents the name of code. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String name;
    /**
     * <p>
     * Represents the code text of code. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String code;
    /**
     * <p>
     * Represents the payment type of code. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String paymentType;
    /**
     * <p>
     * Represents the retirement type of code. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private RetirementType retirementType;
    /**
     * <p>
     * Represents the flag specifying whether code is post office. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private Boolean postOffice;

    /**
     * Creates an instance of GLCode.
     */
    public GLCode() {
        // Empty
    }

    /**
     * Gets the name of code.
     *
     * @return the name of code.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of code.
     *
     * @param name
     *            the name of code.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the code text of code.
     *
     * @return the code text of code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code text of code.
     *
     * @param code
     *            the code text of code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the payment type of code.
     *
     * @return the payment type of code.
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type of code.
     *
     * @param paymentType
     *            the payment type of code.
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the retirement type of code.
     *
     * @return the retirement type of code.
     */
    public RetirementType getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the retirement type of code.
     *
     * @param retirementType
     *            the retirement type of code.
     */
    public void setRetirementType(RetirementType retirementType) {
        this.retirementType = retirementType;
    }

    /**
     * Gets the flag specifying whether code is post office.
     *
     * @return the flag specifying whether code is post office.
     */
    public Boolean getPostOffice() {
        return postOffice;
    }

    /**
     * Sets the flag specifying whether code is post office.
     *
     * @param postOffice
     *            the flag specifying whether code is post office.
     */
    public void setPostOffice(Boolean postOffice) {
        this.postOffice = postOffice;
    }
}
