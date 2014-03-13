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
import gov.opm.scrd.entities.lookup.DepositType;

import java.math.BigDecimal;

/**
 * <p>
 * This is the class representing the dedeposit calculation result item information.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class Dedeposit extends IdentifiableEntity {
    /**
     * <p>
     * Represents the type of dedeposit item. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private DepositType depositType;
    /**
     * <p>
     * Represents the amount of dedeposit item. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal deposit;
    /**
     * <p>
     * Represents the interest of dedeposit item. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal interest;
    /**
     * <p>
     * Represents the total amount of dedeposit item. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private BigDecimal total;

    /**
     * Creates an instance of Dedeposit.
     */
    public Dedeposit() {
        // Empty
    }

    /**
     * Gets the type of dedeposit item.
     *
     * @return the type of dedeposit item.
     */
    public DepositType getDepositType() {
        return depositType;
    }

    /**
     * Sets the type of dedeposit item.
     *
     * @param depositType
     *            the type of dedeposit item.
     */
    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    /**
     * Gets the amount of dedeposit item.
     *
     * @return the amount of dedeposit item.
     */
    public BigDecimal getDeposit() {
        return deposit;
    }

    /**
     * Sets the amount of dedeposit item.
     *
     * @param deposit
     *            the amount of dedeposit item.
     */
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    /**
     * Gets the interest of dedeposit item.
     *
     * @return the interest of dedeposit item.
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * Sets the interest of dedeposit item.
     *
     * @param interest
     *            the interest of dedeposit item.
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * Gets the total amount of dedeposit item.
     *
     * @return the total amount of dedeposit item.
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Sets the total amount of dedeposit item.
     *
     * @param total
     *            the total amount of dedeposit item.
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
