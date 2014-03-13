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

import java.util.List;

/**
 *
 * <p>
 * Represents request for deduction calculation.
 * </p>
 *
 * <p>
 * Thread Safety: This class is not thread safe since it's mutable.
 * </p>
 *
 * @author albertwang, TCSASSEMBLER
 * @version 1.0
 * @since OPM Rules Engine Models Exceptions and Interest Calculation v1.0 Assembly
 */
public class DeductionCalculationRequest implements RuleRequest {

    /**
     * Represents the service periods that are used as calculation input.
     */
    private List<ServicePeriod> servicePeriods;

    /**
     * Creates the instance of DeductionCalculationRequest.
     */
    public DeductionCalculationRequest() {
        // does nothing
    }


    /**
     * Gets the service periods that are used as calculation input.
     * @return the service periods that are used as calculation input.
     */
    public List<ServicePeriod> getServicePeriods() {
        return servicePeriods;
    }

    /**
     * Sets the service periods that are used as calculation input.
     * @param servicePeriods the service periods that are used as calculation input.
     */
    public void setServicePeriods(List<ServicePeriod> servicePeriods) {
        this.servicePeriods = servicePeriods;
    }
}
