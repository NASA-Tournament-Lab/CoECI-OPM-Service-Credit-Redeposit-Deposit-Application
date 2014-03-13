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
 * Represents the entity specifying interest rate.
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
public class InterestRate extends IdentifiableEntity {
    /**
     * <p>
     * Represents the interest year. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Integer interestYear;
    /**
     * <p>
     * Represents the interest rate. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private BigDecimal interestRate;

    /**
     * Creates an instance of InterestRate.
     */
    public InterestRate() {
        // Empty
    }

    /**
     * Gets the interest year.
     *
     * @return the interest year.
     */
    public Integer getInterestYear() {
        return interestYear;
    }

    /**
     * Sets the interest year.
     *
     * @param interestYear
     *            the interest year.
     */
    public void setInterestYear(Integer interestYear) {
        this.interestYear = interestYear;
    }

    /**
     * Gets the interest rate.
     *
     * @return the interest rate.
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the interest rate.
     *
     * @param interestRate
     *            the interest rate.
     */
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
