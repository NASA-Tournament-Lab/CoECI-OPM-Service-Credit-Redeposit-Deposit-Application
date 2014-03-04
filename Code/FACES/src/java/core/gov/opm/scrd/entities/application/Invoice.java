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
 * Represents the entity specifying invoice transaction.
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
public class Invoice extends IdentifiableEntity {
    /**
     * <p>
     * Represents the transaction key of invoice. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Long payTransactionKey;
    /**
     * <p>
     * Represents the deposit of invoice. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal deposit;
    /**
     * <p>
     * Represents the redeposit the of invoice. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal redeposit;
    /**
     * <p>
     * Represents the tot var redeposit amount the of invoice. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal totVarRedeposit;
    /**
     * <p>
     * Represents the non ded amount the of invoice. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private BigDecimal nonDed;
    /**
     * <p>
     * Represents the fers W amount of invoice. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal fersW;
    /**
     * <p>
     * Represents the namesake account value of invoice. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private BigDecimal accIntDep;
    /**
     * <p>
     * Represents the namesake account value of invoice. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private BigDecimal accIntRdep;
    /**
     * <p>
     * Represents the namesake account value of invoice. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private BigDecimal accIntNonDep;
    /**
     * <p>
     * Represents the namesake account value of invoice. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private BigDecimal accIntVarRdep;
    /**
     * <p>
     * Represents the namesake account value of invoice. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private BigDecimal accIntFers;
    /**
     * <p>
     * Represents the tot payd amount of invoice. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal totPayd;
    /**
     * <p>
     * Represents the tot payr amount of invoice. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal totPayr;
    /**
     * <p>
     * Represents the tot payn amount of invoice. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal totPayn;
    /**
     * <p>
     * Represents the tot payvr amount of invoice. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal totPayvr;
    /**
     * <p>
     * Represents the tot payfers amount of invoice. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private BigDecimal totPayfers;
    /**
     * <p>
     * Represents the last payment date of invoice. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date lastPay;
    /**
     * <p>
     * Represents the calculation date of invoice. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date calcDate;
    /**
     * <p>
     * Represents the id of last invoice of invoice. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private Long lastInvoiceId;

    /**
     * Creates an instance of Invoice.
     */
    public Invoice() {
        // Empty
    }

    /**
     * Gets the transaction key of invoice.
     *
     * @return the transaction key of invoice.
     */
    public Long getPayTransactionKey() {
        return payTransactionKey;
    }

    /**
     * Sets the transaction key of invoice.
     *
     * @param payTransactionKey
     *            the transaction key of invoice.
     */
    public void setPayTransactionKey(Long payTransactionKey) {
        this.payTransactionKey = payTransactionKey;
    }

    /**
     * Gets the deposit of invoice.
     *
     * @return the deposit of invoice.
     */
    public BigDecimal getDeposit() {
        return deposit;
    }

    /**
     * Sets the deposit of invoice.
     *
     * @param deposit
     *            the deposit of invoice.
     */
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    /**
     * Gets the redeposit the of invoice.
     *
     * @return the redeposit the of invoice.
     */
    public BigDecimal getRedeposit() {
        return redeposit;
    }

    /**
     * Sets the redeposit the of invoice.
     *
     * @param redeposit
     *            the redeposit the of invoice.
     */
    public void setRedeposit(BigDecimal redeposit) {
        this.redeposit = redeposit;
    }

    /**
     * Gets the tot var redeposit amount the of invoice.
     *
     * @return the tot var redeposit amount the of invoice.
     */
    public BigDecimal getTotVarRedeposit() {
        return totVarRedeposit;
    }

    /**
     * Sets the tot var redeposit amount the of invoice.
     *
     * @param totVarRedeposit
     *            the tot var redeposit amount the of invoice.
     */
    public void setTotVarRedeposit(BigDecimal totVarRedeposit) {
        this.totVarRedeposit = totVarRedeposit;
    }

    /**
     * Gets the non ded amount the of invoice.
     *
     * @return the non ded amount the of invoice.
     */
    public BigDecimal getNonDed() {
        return nonDed;
    }

    /**
     * Sets the non ded amount the of invoice.
     *
     * @param nonDed
     *            the non ded amount the of invoice.
     */
    public void setNonDed(BigDecimal nonDed) {
        this.nonDed = nonDed;
    }

    /**
     * Gets the fers W amount of invoice.
     *
     * @return the fers W amount of invoice.
     */
    public BigDecimal getFersW() {
        return fersW;
    }

    /**
     * Sets the fers W amount of invoice.
     *
     * @param fersW
     *            the fers W amount of invoice.
     */
    public void setFersW(BigDecimal fersW) {
        this.fersW = fersW;
    }

    /**
     * Gets the namesake account value of invoice.
     *
     * @return the namesake account value of invoice.
     */
    public BigDecimal getAccIntDep() {
        return accIntDep;
    }

    /**
     * Sets the namesake account value of invoice.
     *
     * @param accIntDep
     *            the namesake account value of invoice.
     */
    public void setAccIntDep(BigDecimal accIntDep) {
        this.accIntDep = accIntDep;
    }

    /**
     * Gets the namesake account value of invoice.
     *
     * @return the namesake account value of invoice.
     */
    public BigDecimal getAccIntRdep() {
        return accIntRdep;
    }

    /**
     * Sets the namesake account value of invoice.
     *
     * @param accIntRdep
     *            the namesake account value of invoice.
     */
    public void setAccIntRdep(BigDecimal accIntRdep) {
        this.accIntRdep = accIntRdep;
    }

    /**
     * Gets the namesake account value of invoice.
     *
     * @return the namesake account value of invoice.
     */
    public BigDecimal getAccIntNonDep() {
        return accIntNonDep;
    }

    /**
     * Sets the namesake account value of invoice.
     *
     * @param accIntNonDep
     *            the namesake account value of invoice.
     */
    public void setAccIntNonDep(BigDecimal accIntNonDep) {
        this.accIntNonDep = accIntNonDep;
    }

    /**
     * Gets the namesake account value of invoice.
     *
     * @return the namesake account value of invoice.
     */
    public BigDecimal getAccIntVarRdep() {
        return accIntVarRdep;
    }

    /**
     * Sets the namesake account value of invoice.
     *
     * @param accIntVarRdep
     *            the namesake account value of invoice.
     */
    public void setAccIntVarRdep(BigDecimal accIntVarRdep) {
        this.accIntVarRdep = accIntVarRdep;
    }

    /**
     * Gets the namesake account value of invoice.
     *
     * @return the namesake account value of invoice.
     */
    public BigDecimal getAccIntFers() {
        return accIntFers;
    }

    /**
     * Sets the namesake account value of invoice.
     *
     * @param accIntFers
     *            the namesake account value of invoice.
     */
    public void setAccIntFers(BigDecimal accIntFers) {
        this.accIntFers = accIntFers;
    }

    /**
     * Gets the tot payd amount of invoice.
     *
     * @return the tot payd amount of invoice.
     */
    public BigDecimal getTotPayd() {
        return totPayd;
    }

    /**
     * Sets the tot payd amount of invoice.
     *
     * @param totPayd
     *            the tot payd amount of invoice.
     */
    public void setTotPayd(BigDecimal totPayd) {
        this.totPayd = totPayd;
    }

    /**
     * Gets the tot payr amount of invoice.
     *
     * @return the tot payr amount of invoice.
     */
    public BigDecimal getTotPayr() {
        return totPayr;
    }

    /**
     * Sets the tot payr amount of invoice.
     *
     * @param totPayr
     *            the tot payr amount of invoice.
     */
    public void setTotPayr(BigDecimal totPayr) {
        this.totPayr = totPayr;
    }

    /**
     * Gets the tot payn amount of invoice.
     *
     * @return the tot payn amount of invoice.
     */
    public BigDecimal getTotPayn() {
        return totPayn;
    }

    /**
     * Sets the tot payn amount of invoice.
     *
     * @param totPayn
     *            the tot payn amount of invoice.
     */
    public void setTotPayn(BigDecimal totPayn) {
        this.totPayn = totPayn;
    }

    /**
     * Gets the tot payvr amount of invoice.
     *
     * @return the tot payvr amount of invoice.
     */
    public BigDecimal getTotPayvr() {
        return totPayvr;
    }

    /**
     * Sets the tot payvr amount of invoice.
     *
     * @param totPayvr
     *            the tot payvr amount of invoice.
     */
    public void setTotPayvr(BigDecimal totPayvr) {
        this.totPayvr = totPayvr;
    }

    /**
     * Gets the tot payfers amount of invoice.
     *
     * @return the tot payfers amount of invoice.
     */
    public BigDecimal getTotPayfers() {
        return totPayfers;
    }

    /**
     * Sets the tot payfers amount of invoice.
     *
     * @param totPayfers
     *            the tot payfers amount of invoice.
     */
    public void setTotPayfers(BigDecimal totPayfers) {
        this.totPayfers = totPayfers;
    }

    /**
     * Gets the last payment date of invoice.
     *
     * @return the last payment date of invoice.
     */
    public Date getLastPay() {
        return lastPay;
    }

    /**
     * Sets the last payment date of invoice.
     *
     * @param lastPay
     *            the last payment date of invoice.
     */
    public void setLastPay(Date lastPay) {
        this.lastPay = lastPay;
    }

    /**
     * Gets the calculation date of invoice.
     *
     * @return the calculation date of invoice.
     */
    public Date getCalcDate() {
        return calcDate;
    }

    /**
     * Sets the calculation date of invoice.
     *
     * @param calcDate
     *            the calculation date of invoice.
     */
    public void setCalcDate(Date calcDate) {
        this.calcDate = calcDate;
    }

    /**
     * Gets the id of last invoice of invoice.
     *
     * @return the id of last invoice of invoice.
     */
    public Long getLastInvoiceId() {
        return lastInvoiceId;
    }

    /**
     * Sets the id of last invoice of invoice.
     *
     * @param lastInvoiceId
     *            the id of last invoice of invoice.
     */
    public void setLastInvoiceId(Long lastInvoiceId) {
        this.lastInvoiceId = lastInvoiceId;
    }
}
