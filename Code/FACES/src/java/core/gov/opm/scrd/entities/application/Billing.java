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

import java.math.BigDecimal;

/**
 * <p>
 * This is the class representing the information about each individual billing of the account.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Release I Assembly 1.0):</em>
 * <ul>
 * <li>Add field frozen</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax, bannie2492
 * @version 1.1
 */
public class Billing extends IdentifiableEntity {
    /**
     * <p>
     * Represents the name of single billing item. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String name;
    /**
     * <p>
     * Represents the initial billing amount of single billing item. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private BigDecimal initialBilling;
    /**
     * <p>
     * Represents the additional interest of single billing item. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private BigDecimal additionalInterest;
    /**
     * <p>
     * Represents the total payments amount of single billing item. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private BigDecimal totalPayments;
    /**
     * <p>
     * Represents the balance of single billing item. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private BigDecimal balance;
    /**
     * <p>
     * Represents the order index of single billing item. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private int paymentOrder;
    /**
     * <p>
     * Represents the frozen indicator of single billing item. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    private boolean frozen;

    /**
     * Creates an instance of Billing.
     */
    public Billing() {
        // Empty
    }

    /**
     * Gets the name of single billing item.
     *
     * @return the name of single billing item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of single billing item.
     *
     * @param name
     *            the name of single billing item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the initial billing amount of single billing item.
     *
     * @return the initial billing amount of single billing item.
     */
    public BigDecimal getInitialBilling() {
        return initialBilling;
    }

    /**
     * Sets the initial billing amount of single billing item.
     *
     * @param initialBilling
     *            the initial billing amount of single billing item.
     */
    public void setInitialBilling(BigDecimal initialBilling) {
        this.initialBilling = initialBilling;
    }

    /**
     * Gets the additional interest of single billing item.
     *
     * @return the additional interest of single billing item.
     */
    public BigDecimal getAdditionalInterest() {
        return additionalInterest;
    }

    /**
     * Sets the additional interest of single billing item.
     *
     * @param additionalInterest
     *            the additional interest of single billing item.
     */
    public void setAdditionalInterest(BigDecimal additionalInterest) {
        this.additionalInterest = additionalInterest;
    }

    /**
     * Gets the total payments amount of single billing item.
     *
     * @return the total payments amount of single billing item.
     */
    public BigDecimal getTotalPayments() {
        return totalPayments;
    }

    /**
     * Sets the total payments amount of single billing item.
     *
     * @param totalPayments
     *            the total payments amount of single billing item.
     */
    public void setTotalPayments(BigDecimal totalPayments) {
        this.totalPayments = totalPayments;
    }

    /**
     * Gets the balance of single billing item.
     *
     * @return the balance of single billing item.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the balance of single billing item.
     *
     * @param balance
     *            the balance of single billing item.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets the order index of single billing item.
     *
     * @return the order index of single billing item.
     */
    public int getPaymentOrder() {
        return paymentOrder;
    }

    /**
     * Sets the order index of single billing item.
     *
     * @param paymentOrder
     *            the order index of single billing item.
     */
    public void setPaymentOrder(int paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    /**
     * Gets the frozen indicator of single billing item.
     *
     * @return the frozen indicator of single billing item.
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Sets the frozen indicator of single billing item.
     *
     * @param frozen
     *            the frozen indicator of single billing item.
     * @since 1.1 (OPM - Release I Assembly 1.0)
     */
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
