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
import java.util.List;

/**
 * <p>
 * This is the class representing the FERS deposit/redeposit single total item of the calculation information. It
 * contains reference to smaller concrete calculations set in @see Calculation and the aggregated result data in @see
 * CalculationResult.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added fields: version, lineNumber</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Release I Assembly 1.0):</em>
 * <ul>
 * <li>Added fields: accountId</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax, bannie2492
 * @version 1.2
 */
public class CalculationVersion extends IdentifiableEntity {
    /**
     * <p>
     * Represents the name of calculation version. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String name;
    /**
     * <p>
     * Represents the list of individual calculation items of calculation version. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private List<Calculation> calculations;
    /**
     * <p>
     * Represents the result of calculation version. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private CalculationResult calculationResult;
    /**
     * <p>
     * Represents the calculation date. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Date calculationDate;
    /**
     * <p>
     * Represents the version of calculation version. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Integer version;
    /**
     * <p>
     * Represents the line number of calculation version. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Integer lineNumber;
    
    /**
     * <p>
     * Represents the account owning this calculation version. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     *
     * @since 1.2 (OPM - Release I Assembly 1.0)
     */
    private long accountId;

    /**
     * Creates an instance of CalculationVersion.
     */
    public CalculationVersion() {
        // Empty
    }

    /**
     * Gets the name of calculation version.
     *
     * @return the name of calculation version.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of calculation version.
     *
     * @param name
     *            the name of calculation version.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of individual calculation items of calculation version.
     *
     * @return the list of individual calculation items of calculation version.
     */
    public List<Calculation> getCalculations() {
        return calculations;
    }

    /**
     * Sets the list of individual calculation items of calculation version.
     *
     * @param calculations
     *            the list of individual calculation items of calculation version.
     */
    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    /**
     * Gets the result of calculation version.
     *
     * @return the result of calculation version.
     */
    public CalculationResult getCalculationResult() {
        return calculationResult;
    }

    /**
     * Sets the result of calculation version.
     *
     * @param calculationResult
     *            the result of calculation version.
     */
    public void setCalculationResult(CalculationResult calculationResult) {
        this.calculationResult = calculationResult;
    }

    /**
     * Gets the calculation date.
     *
     * @return the calculation date.
     */
    public Date getCalculationDate() {
        return calculationDate;
    }

    /**
     * Sets the calculation date.
     *
     * @param calculationDate
     *            the calculation date.
     */
    public void setCalculationDate(Date calculationDate) {
        this.calculationDate = calculationDate;
    }

    /**
     * Gets the version of calculation version.
     *
     * @return the version of calculation version.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the version of calculation version.
     *
     * @param version
     *            the version of calculation version.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Gets the line number of calculation version.
     *
     * @return the line number of calculation version.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the line number of calculation version.
     *
     * @param lineNumber
     *            the line number of calculation version.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Gets he account id of calculation version.
     *
     * @return he account id of calculation version.
     *
     * @since 1.2 (OPM - Release I Assembly 1.0)
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * Sets the account id of calculation version.
     *
     * @param accountId
     *            he account id of calculation version.
     *
     * @since 1.2 (OPM - Release I Assembly 1.0)
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    
    
}
