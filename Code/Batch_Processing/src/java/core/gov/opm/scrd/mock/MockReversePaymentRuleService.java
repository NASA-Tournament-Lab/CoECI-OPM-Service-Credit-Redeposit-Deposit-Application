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

package gov.opm.scrd.mock;

import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.RuleService;


/**
 * Mock implementation.
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class MockReversePaymentRuleService implements RuleService<MockReversePaymentRuleRequest, MockReversePaymentRuleResponse> {
    /**
     * Represents the allowed. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private boolean allowed;

/**
         * Instantiates a new mock reverse payment rule service.
         */
    public MockReversePaymentRuleService() {
    }

/**
         * Instantiates a new mock reverse payment rule service.
         *
         * @param allowed the allowed
         */
    public MockReversePaymentRuleService(boolean allowed) {
        this.allowed = allowed;
    }

    /**
     * Mock execute method.
     *
     * @param request the mock request
     *
     * @return the rule response
     *
     * @throws OPMException if any error
     */
    public MockReversePaymentRuleResponse execute(MockReversePaymentRuleRequest request)
        throws OPMException {
        MockReversePaymentRuleResponse response = new MockReversePaymentRuleResponse(allowed);

        return response;
    }

    /**
     * Checks if is allowed.
     *
     * @return true, if is allowed
     */
    public boolean isAllowed() {
        return allowed;
    }

    /**
     * Sets the allowed.
     *
     * @param allowed the new allowed
     */
    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
}
