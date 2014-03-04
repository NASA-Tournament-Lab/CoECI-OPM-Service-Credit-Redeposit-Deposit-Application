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

/**
 * <p>
 * Represents the entity specifying print suppression cases of the account.
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
public class A01PrintSuppressionCases extends IdentifiableEntity {
    /**
     * <p>
     * Represents the claim number of case. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String claimNumber;
    /**
     * <p>
     * Represents the reason of case. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer reasonForPrintSuppression;

    /**
     * Creates an instance of A01PrintSuppressionCases.
     */
    public A01PrintSuppressionCases() {
        // Empty
    }

    /**
     * Gets the claim number of case.
     *
     * @return the claim number of case.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number of case.
     *
     * @param claimNumber
     *            the claim number of case.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the reason of case.
     *
     * @return the reason of case.
     */
    public Integer getReasonForPrintSuppression() {
        return reasonForPrintSuppression;
    }

    /**
     * Sets the reason of case.
     *
     * @param reasonForPrintSuppression
     *            the reason of case.
     */
    public void setReasonForPrintSuppression(Integer reasonForPrintSuppression) {
        this.reasonForPrintSuppression = reasonForPrintSuppression;
    }
}
