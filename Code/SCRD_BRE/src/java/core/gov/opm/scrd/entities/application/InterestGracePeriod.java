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
 * Represents the entity specifying interest grace period.
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
public class InterestGracePeriod extends IdentifiableEntity {
    /**
     * <p>
     * Represents the claim number of period. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String claimNumber;
    /**
     * <p>
     * Represents the flag specifying whether period is POST/1082 Redeposit. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private Boolean post982Redeposit;
    /**
     * <p>
     * Represents the flag specifying whether period is PRE/1082 Redeposit. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private Boolean pre1082Redeposit;
    /**
     * <p>
     * Represents the flag specifying whether period is POST/982 Deposit. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean post982Deposit;
    /**
     * <p>
     * Represents the flag specifying whether period is PRE/1082 Deposit. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean pre1082Deposit;
    /**
     * <p>
     * Represents the flag specifying whether period is FERS Deposit. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean fersDeposit;

    /**
     * Creates an instance of InterestGracePeriod.
     */
    public InterestGracePeriod() {
        // Empty
    }

    /**
     * Gets the claim number of period.
     *
     * @return the claim number of period.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number of period.
     *
     * @param claimNumber
     *            the claim number of period.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the flag specifying whether period is POST/1082 Redeposit.
     *
     * @return the flag specifying whether period is POST/1082 Redeposit.
     */
    public Boolean getPost982Redeposit() {
        return post982Redeposit;
    }

    /**
     * Sets the flag specifying whether period is POST/1082 Redeposit.
     *
     * @param post982Redeposit
     *            the flag specifying whether period is POST/1082 Redeposit.
     */
    public void setPost982Redeposit(Boolean post982Redeposit) {
        this.post982Redeposit = post982Redeposit;
    }

    /**
     * Gets the flag specifying whether period is PRE/1082 Redeposit.
     *
     * @return the flag specifying whether period is PRE/1082 Redeposit.
     */
    public Boolean getPre1082Redeposit() {
        return pre1082Redeposit;
    }

    /**
     * Sets the flag specifying whether period is PRE/1082 Redeposit.
     *
     * @param pre1082Redeposit
     *            the flag specifying whether period is PRE/1082 Redeposit.
     */
    public void setPre1082Redeposit(Boolean pre1082Redeposit) {
        this.pre1082Redeposit = pre1082Redeposit;
    }

    /**
     * Gets the flag specifying whether period is POST/982 Deposit.
     *
     * @return the flag specifying whether period is POST/982 Deposit.
     */
    public Boolean getPost982Deposit() {
        return post982Deposit;
    }

    /**
     * Sets the flag specifying whether period is POST/982 Deposit.
     *
     * @param post982Deposit
     *            the flag specifying whether period is POST/982 Deposit.
     */
    public void setPost982Deposit(Boolean post982Deposit) {
        this.post982Deposit = post982Deposit;
    }

    /**
     * Gets the flag specifying whether period is PRE/1082 Deposit.
     *
     * @return the flag specifying whether period is PRE/1082 Deposit.
     */
    public Boolean getPre1082Deposit() {
        return pre1082Deposit;
    }

    /**
     * Sets the flag specifying whether period is PRE/1082 Deposit.
     *
     * @param pre1082Deposit
     *            the flag specifying whether period is PRE/1082 Deposit.
     */
    public void setPre1082Deposit(Boolean pre1082Deposit) {
        this.pre1082Deposit = pre1082Deposit;
    }

    /**
     * Gets the flag specifying whether period is FERS Deposit.
     *
     * @return the flag specifying whether period is FERS Deposit.
     */
    public Boolean getFersDeposit() {
        return fersDeposit;
    }

    /**
     * Sets the flag specifying whether period is FERS Deposit.
     *
     * @param fersDeposit
     *            the flag specifying whether period is FERS Deposit.
     */
    public void setFersDeposit(Boolean fersDeposit) {
        this.fersDeposit = fersDeposit;
    }
}
