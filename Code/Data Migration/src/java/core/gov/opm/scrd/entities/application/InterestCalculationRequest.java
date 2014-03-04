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

import java.util.Date;
import java.util.List;

/**
 * <p>
 * This class represents the request for interest calculation.
 * </p>
 * <p>
 * <b>Thread Safety:</b>
 * This class is not thread safe since it's mutable.
 * </p>
 *
 * @author albertwang, TCSASSEMBLER
 * @version 1.0
 * @since OPM Rules Engine Models Exceptions and Interest Calculation v1.0 Assembly
 *
 */
public class InterestCalculationRequest implements RuleRequest {
    /**
     * Represents the extended service periods that are input of the interest calculation process.
     */
    private List<ExtendedServicePeriod> extendedServicePeriods;

    /**
     * Represents the target date to which the interest will be calculated.
     */
    private Date interestCalculatedToDate;

    /**
     * Creates the instance of InterestCalculationRequest.
     */
    public InterestCalculationRequest() {
        // does nothing
    }


    /**
     * Gets the extended service periods that are input of the interest calculation process.
     * @return the extended service periods that are input of the interest calculation process.
     */
    public List<ExtendedServicePeriod> getExtendedServicePeriods() {
        return extendedServicePeriods;
    }

    /**
     * Sets the extended service periods that are input of the interest calculation process.
     * @param extendedServicePeriods the extended service periods that are input of the interest calculation process.
     */
    public void setExtendedServicePeriods(List<ExtendedServicePeriod> extendedServicePeriods) {
        this.extendedServicePeriods = extendedServicePeriods;
    }

    /**
     * Gets the target date to which the interest will be calculated.
     * @return the target date to which the interest will be calculated.
     */
    public Date getInterestCalculatedToDate() {
        return interestCalculatedToDate;
    }

    /**
     * Sets the target date to which the interest will be calculated.
     * @param interestCalculatedToDate the target date to which the interest will be calculated.
     */
    public void setInterestCalculatedToDate(Date interestCalculatedToDate) {
        this.interestCalculatedToDate = interestCalculatedToDate;
    }
}

