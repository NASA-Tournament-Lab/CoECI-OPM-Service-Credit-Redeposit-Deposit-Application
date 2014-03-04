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
 * Represents a calculation response.
 *
 * <b>Thread Safety</b> This class is mutable and not thread safe.
 *
 * @author argolite, j3_guile
 * @version 1.0
 */
public class CalculationResponse implements RuleResponse {

    /**
     * The calculated amount.
     */
    private float amount;

    /**
     * Empty constructor.
     */
    public CalculationResponse() {
    }

    /**
     * Gets the value of the field <code>amount</code>.
     *
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets the value of the field <code>amount</code>.
     *
     * @param amount the amount to set
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Gets a readable string representation of this entity.
     *
     * @return this entity as a string.
     */
    @Override
    public String toString() {
        return "CalculationResponse [amount=" + amount + "]";
    }
}
