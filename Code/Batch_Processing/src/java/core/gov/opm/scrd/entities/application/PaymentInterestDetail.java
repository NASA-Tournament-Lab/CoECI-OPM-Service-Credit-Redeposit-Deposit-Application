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
import java.util.Date;

/**
 * <p>
 * Represents the entity specifying payment interest detail.
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
public class PaymentInterestDetail extends IdentifiableEntity {
    /**
     * <p>
     * Represents the transaction key of payment interest detail. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private Long payTransactionKey;

    /**
     * <p>
     * Represents the account type of payment interest detail. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Long accountType;

    /**
     * <p>
     * Represents the number of whole years of payment interest detail. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Integer numWholeYears;
    /**
     * <p>
     * Represents the calculated interest of payment interest detail. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private BigDecimal calculatedInterest;
    /**
     * <p>
     * Represents the last pay to EOY factor amount of payment interest detail. It is managed with a getter and setter.
     * It may have any value. It is fully mutable.
     * </p>
     */
    private BigDecimal lastPayToEOYFactor;
    /**
     * <p>
     * Represents the partial to this pay factor amount of payment interest detail. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private BigDecimal partialToThisPayFactor;
    /**
     * <p>
     * Represents the interest rate of payment interest detail. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal thisInterestRate;
    /**
     * <p>
     * Represents the last payment date of payment interest detail. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private Date lastPaymentDate;
    /**
     * <p>
     * Represents the transaction date of payment interest detail. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private Date transactionDate;
    /**
     * <p>
     * Represents the of computed date payment interest detail. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Date computedDate;
    /**
     * <p>
     * Represents the flag specifying whether payment interest detail is post. It is managed with a getter and setter.
     * It may have any value. It is fully mutable.
     * </p>
     */
    private Boolean post;
    /**
     * <p>
     * Represents the flag specifying whether payment interest detail is GUI. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private Boolean gui;
    /**
     * <p>
     * Represents the flag specifying whether payment interest detail last payment was this year. It is managed with a
     * getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Boolean lastPaymentWasThisYear;

    /**
     * Creates an instance of PaymentInterestDetail.
     */
    public PaymentInterestDetail() {
        // Empty
    }

    /**
     * Gets the transaction key of payment interest detail.
     *
     * @return the transaction key of payment interest detail.
     */
    public Long getPayTransactionKey() {
        return payTransactionKey;
    }

    /**
     * Sets the transaction key of payment interest detail.
     *
     * @param payTransactionKey
     *            the transaction key of payment interest detail.
     */
    public void setPayTransactionKey(Long payTransactionKey) {
        this.payTransactionKey = payTransactionKey;
    }


    /**
     * Gets the account type of payment interest detail.
     *
     * @return the account type of payment interest detail.
     */
    public Long getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type of payment interest detail.
     *
     * @param accountType
     *            the account type of payment interest detail.
     */
    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the number of whole years of payment interest detail.
     *
     * @return the number of whole years of payment interest detail.
     */
    public Integer getNumWholeYears() {
        return numWholeYears;
    }

    /**
     * Sets the number of whole years of payment interest detail.
     *
     * @param numWholeYears
     *            the number of whole years of payment interest detail.
     */
    public void setNumWholeYears(Integer numWholeYears) {
        this.numWholeYears = numWholeYears;
    }

    /**
     * Gets the calculated interest of payment interest detail.
     *
     * @return the calculated interest of payment interest detail.
     */
    public BigDecimal getCalculatedInterest() {
        return calculatedInterest;
    }

    /**
     * Sets the calculated interest of payment interest detail.
     *
     * @param calculatedInterest
     *            the calculated interest of payment interest detail.
     */
    public void setCalculatedInterest(BigDecimal calculatedInterest) {
        this.calculatedInterest = calculatedInterest;
    }

    /**
     * Gets the last pay to EOY factor amount of payment interest detail.
     *
     * @return the last pay to EOY factor amount of payment interest detail.
     */
    public BigDecimal getLastPayToEOYFactor() {
        return lastPayToEOYFactor;
    }

    /**
     * Sets the last pay to EOY factor amount of payment interest detail.
     *
     * @param lastPayToEOYFactor
     *            the last pay to EOY factor amount of payment interest detail.
     */
    public void setLastPayToEOYFactor(BigDecimal lastPayToEOYFactor) {
        this.lastPayToEOYFactor = lastPayToEOYFactor;
    }

    /**
     * Gets the partial to this pay factor amount of payment interest detail.
     *
     * @return the partial to this pay factor amount of payment interest detail.
     */
    public BigDecimal getPartialToThisPayFactor() {
        return partialToThisPayFactor;
    }

    /**
     * Sets the partial to this pay factor amount of payment interest detail.
     *
     * @param partialToThisPayFactor
     *            the partial to this pay factor amount of payment interest detail.
     */
    public void setPartialToThisPayFactor(BigDecimal partialToThisPayFactor) {
        this.partialToThisPayFactor = partialToThisPayFactor;
    }

    /**
     * Gets the interest rate of payment interest detail.
     *
     * @return the interest rate of payment interest detail.
     */
    public BigDecimal getThisInterestRate() {
        return thisInterestRate;
    }

    /**
     * Sets the interest rate of payment interest detail.
     *
     * @param thisInterestRate
     *            the interest rate of payment interest detail.
     */
    public void setThisInterestRate(BigDecimal thisInterestRate) {
        this.thisInterestRate = thisInterestRate;
    }

    /**
     * Gets the last payment date of payment interest detail.
     *
     * @return the last payment date of payment interest detail.
     */
    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * Sets the last payment date of payment interest detail.
     *
     * @param lastPaymentDate
     *            the last payment date of payment interest detail.
     */
    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    /**
     * Gets the transaction date of payment interest detail.
     *
     * @return the transaction date of payment interest detail.
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the transaction date of payment interest detail.
     *
     * @param transactionDate
     *            the transaction date of payment interest detail.
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the of computed date payment interest detail.
     *
     * @return the of computed date payment interest detail.
     */
    public Date getComputedDate() {
        return computedDate;
    }

    /**
     * Sets the of computed date payment interest detail.
     *
     * @param computedDate
     *            the of computed date payment interest detail.
     */
    public void setComputedDate(Date computedDate) {
        this.computedDate = computedDate;
    }

    /**
     * Gets the flag specifying whether payment interest detail is post.
     *
     * @return the flag specifying whether payment interest detail is post.
     */
    public Boolean getPost() {
        return post;
    }

    /**
     * Sets the flag specifying whether payment interest detail is post.
     *
     * @param post
     *            the flag specifying whether payment interest detail is post.
     */
    public void setPost(Boolean post) {
        this.post = post;
    }

    /**
     * Gets the flag specifying whether payment interest detail is GUI.
     *
     * @return the flag specifying whether payment interest detail is GUI.
     */
    public Boolean getGui() {
        return gui;
    }

    /**
     * Sets the flag specifying whether payment interest detail is GUI.
     *
     * @param gui
     *            the flag specifying whether payment interest detail is GUI.
     */
    public void setGui(Boolean gui) {
        this.gui = gui;
    }

    /**
     * Gets the flag specifying whether payment interest detail last payment was this year.
     *
     * @return the flag specifying whether payment interest detail last payment was this year.
     */
    public Boolean getLastPaymentWasThisYear() {
        return lastPaymentWasThisYear;
    }

    /**
     * Sets the flag specifying whether payment interest detail last payment was this year.
     *
     * @param lastPaymentWasThisYear
     *            the flag specifying whether payment interest detail last payment was this year.
     */
    public void setLastPaymentWasThisYear(Boolean lastPaymentWasThisYear) {
        this.lastPaymentWasThisYear = lastPaymentWasThisYear;
    }
}
