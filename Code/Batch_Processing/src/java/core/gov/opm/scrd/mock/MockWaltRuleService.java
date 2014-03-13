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
 * Mock Implementation.
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class MockWaltRuleService implements RuleService<MockWaltRuleRequest, MockWaltRuleResponse> {
    /**
     * Represents the return code. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private int returnCode;

    /**
     * Instantiates a new mock walt rule service.
     */
    public MockWaltRuleService() {
    }

    /**
     * Instantiates a new mock walt rule service.
     *
     * @param returnCode the return code
     */
    public MockWaltRuleService(int returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * Mock the execute method.
     *
     * @param request the rule request
     *
     * @return the response of execution
     *
     * @throws OPMException if any error
     */
    @Override
    public MockWaltRuleResponse execute(MockWaltRuleRequest request)
        throws OPMException {
        MockWaltRuleResponse response = new MockWaltRuleResponse();
        response.setReturnCode(returnCode);

        return response;
    }

    /**
     * Gets the return code.
     *
     * @return the return code
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * Sets the return code.
     *
     * @param returnCode the new return code
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
