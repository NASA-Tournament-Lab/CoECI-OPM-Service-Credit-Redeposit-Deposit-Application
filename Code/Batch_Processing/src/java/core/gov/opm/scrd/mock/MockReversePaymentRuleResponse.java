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

import gov.opm.scrd.entities.application.RuleResponse;


/**
 * Mock implementation.
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class MockReversePaymentRuleResponse implements RuleResponse {
    /**
     * Represents the allowed. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private boolean allowed;

    /**
     * Represents the description. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String description;

    /**
     * Represents the return code. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private int returnCode;

/**
         * Instantiates a new mock reverse payment rule response.
         */
    public MockReversePaymentRuleResponse() {
    }

/**
         * Instantiates a new mock reverse payment rule response.
         *
         * @param allowed the allowed
         */
    public MockReversePaymentRuleResponse(boolean allowed) {
        this.allowed = allowed;
    }

    /**
     * Checks if is allowed.
     *
     * @return true, if is allowed
     */
    public boolean isAllowed() {
        return this.allowed;
    }

    /**
     * Sets the allowed.
     *
     * @param allowed the new allowed
     */
    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
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
