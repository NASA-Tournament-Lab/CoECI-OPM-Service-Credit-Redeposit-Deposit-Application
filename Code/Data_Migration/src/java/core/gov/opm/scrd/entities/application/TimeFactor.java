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

import java.math.BigDecimal;

/**
 * <p>
 * Represents the entity specifying time factor.
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
public class TimeFactor extends IdentifiableEntity {
    /**
     * <p>
     * Represents the number of days of factor. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Integer numDays;
    /**
     * <p>
     * Represents the number of months of factor. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Integer numMonths;
    /**
     * <p>
     * Represents the factor value. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private BigDecimal timeFactor;

    /**
     * Creates an instance of TimeFactor.
     */
    public TimeFactor() {
        // Empty
    }

    /**
     * Gets the number of days of factor.
     *
     * @return the number of days of factor.
     */
    public Integer getNumDays() {
        return numDays;
    }

    /**
     * Sets the number of days of factor.
     *
     * @param numDays
     *            the number of days of factor.
     */
    public void setNumDays(Integer numDays) {
        this.numDays = numDays;
    }

    /**
     * Gets the number of months of factor.
     *
     * @return the number of months of factor.
     */
    public Integer getNumMonths() {
        return numMonths;
    }

    /**
     * Sets the number of months of factor.
     *
     * @param numMonths
     *            the number of months of factor.
     */
    public void setNumMonths(Integer numMonths) {
        this.numMonths = numMonths;
    }

    /**
     * Gets the factor value.
     *
     * @return the factor value.
     */
    public BigDecimal getTimeFactor() {
        return timeFactor;
    }

    /**
     * Sets the factor value.
     *
     * @param timeFactor
     *            the factor value.
     */
    public void setTimeFactor(BigDecimal timeFactor) {
        this.timeFactor = timeFactor;
    }
}
