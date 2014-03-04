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



/**
 * <p>
 * Base class for calculation response.
 * </p>
 * <p>
 * <b>Thread Safety:</b> This class is thread safe since it does not contain properties.
 * </p>
 *
 * @author albertwang, TCSASSEMBLER
 * @version 1.0
 * @since OPM Rules Engine Models Exceptions and Interest Calculation v1.0 Assembly
 */
public abstract class BaseCalculationResponse implements RuleResponse {

    /**
     * Creates the instance of BaseCalculationResponse.
     */
    protected BaseCalculationResponse() {
        // does nothing
    }
}
