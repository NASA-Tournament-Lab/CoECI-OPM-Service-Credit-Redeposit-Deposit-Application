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
 * Represents response for deduction validation.
 * </p>
 * 
 * <p>
 * Thread Safety: This class is not thread safe since it's mutable.
 * </p>
 * 
 * @author albertwang, TCSASSEMBLER
 * @version 1.0
 */
public class DeductionValidationResponse implements RuleResponse {
    /**
     * Represents the service periods.
     */
    private List<ServicePeriod> servicePeriods;

    /**
     * Constructor.
     */
    public DeductionValidationResponse() {
    }

    /**
     * Getter of corresponding field.
     * @return the field.
     */
    public List<ServicePeriod> getServicePeriods() {
        return servicePeriods;
    }

    /**
     * Setter of the corresponding field.
     * @param servicePeriods the value to set
     */
    public void setServicePeriods(List<ServicePeriod> servicePeriods) {
        this.servicePeriods = servicePeriods;
    }
    
}
