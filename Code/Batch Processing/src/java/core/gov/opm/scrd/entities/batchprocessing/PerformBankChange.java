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

package gov.opm.scrd.entities.batchprocessing;

import gov.opm.scrd.entities.common.IdentifiableEntity;

import java.util.Date;


/**
 * <p>This class is simply a POJO containing bank change data.</p>
 *  <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public class PerformBankChange extends IdentifiableEntity {
    /**
     * Represents the scm claim number. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String scmClaimNumber;

    /**
     * Represents the scm date of birth. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Date scmDateOfBirth;

    /**
     * Represents the field number. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String fieldNumber;

    /**
     * Represents the data element. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String dataElement;

    /**
     * Represents the scm city. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String scmCity;

    /**
     * Represents the scm state. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String scmState;

    /**
     * Represents the scm zip code. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String scmZipCode;

    /**
     * Represents the scm first name. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String scmFName;

    /**
     * Represents the scm m initial. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String scmMInitial;

    /**
     * Represents the scm last name. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String scmLastName;

    /**
     * Represents the scm suffix. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String scmSuffix;

    /**
     * Instantiates a new perform bank change.
     */
    public PerformBankChange() {
    }

    /**
     * Gets the scm claim number.
     *
     * @return the scm claim number
     */
    public String getScmClaimNumber() {
        return scmClaimNumber;
    }

    /**
     * Sets the scm claim number.
     *
     * @param scmClaimNumber the new scm claim number to set
     */
    public void setScmClaimNumber(String scmClaimNumber) {
        this.scmClaimNumber = scmClaimNumber;
    }

    /**
     * Gets the scm date of birth.
     *
     * @return the scm date of birth
     */
    public Date getScmDateOfBirth() {
        return scmDateOfBirth;
    }

    /**
     * Sets the scm date of birth.
     *
     * @param scmDateOfBirth the new scm date of birth to set
     */
    public void setScmDateOfBirth(Date scmDateOfBirth) {
        this.scmDateOfBirth = scmDateOfBirth;
    }

    /**
     * Gets the field number.
     *
     * @return the field number
     */
    public String getFieldNumber() {
        return fieldNumber;
    }

    /**
     * Sets the field number.
     *
     * @param fieldNumber the new field number to set
     */
    public void setFieldNumber(String fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    /**
     * Gets the data element.
     *
     * @return the data element
     */
    public String getDataElement() {
        return dataElement;
    }

    /**
     * Sets the data element.
     *
     * @param dataElement the new data element to set
     */
    public void setDataElement(String dataElement) {
        this.dataElement = dataElement;
    }

    /**
     * Gets the scm city.
     *
     * @return the scm city
     */
    public String getScmCity() {
        return scmCity;
    }

    /**
     * Sets the scm city.
     *
     * @param scmCity the new scm city to set
     */
    public void setScmCity(String scmCity) {
        this.scmCity = scmCity;
    }

    /**
     * Gets the scm state.
     *
     * @return the scm state
     */
    public String getScmState() {
        return scmState;
    }

    /**
     * Sets the scm state.
     *
     * @param scmState the new scm state to set
     */
    public void setScmState(String scmState) {
        this.scmState = scmState;
    }

    /**
     * Gets the scm zip code.
     *
     * @return the scm zip code
     */
    public String getScmZipCode() {
        return scmZipCode;
    }

    /**
     * Sets the scm zip code.
     *
     * @param scmZipCode the new scm zip code to set
     */
    public void setScmZipCode(String scmZipCode) {
        this.scmZipCode = scmZipCode;
    }

    /**
     * Gets the scm f name.
     *
     * @return the scm f name
     */
    public String getScmFName() {
        return scmFName;
    }

    /**
     * Sets the scm first name.
     *
     * @param scmFName the new scm first name to set
     */
    public void setScmFName(String scmFName) {
        this.scmFName = scmFName;
    }

    /**
     * Gets the scm m initial.
     *
     * @return the scm m initial
     */
    public String getScmMInitial() {
        return scmMInitial;
    }

    /**
     * Sets the scm m initial.
     *
     * @param scmMInitial the new scm m initial to set
     */
    public void setScmMInitial(String scmMInitial) {
        this.scmMInitial = scmMInitial;
    }

    /**
     * Gets the scm last name.
     *
     * @return the scm last name
     */
    public String getScmLastName() {
        return scmLastName;
    }

    /**
     * Sets the scm last name.
     *
     * @param scmLastName the new scm last name to set
     */
    public void setScmLastName(String scmLastName) {
        this.scmLastName = scmLastName;
    }

    /**
     * Gets the scm suffix.
     *
     * @return the scm suffix
     */
    public String getScmSuffix() {
        return scmSuffix;
    }

    /**
     * Sets the scm suffix.
     *
     * @param scmSuffix the new scm suffix to set
     */
    public void setScmSuffix(String scmSuffix) {
        this.scmSuffix = scmSuffix;
    }
}
