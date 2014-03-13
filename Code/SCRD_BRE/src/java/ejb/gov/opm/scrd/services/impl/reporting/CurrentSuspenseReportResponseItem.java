/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link CurrentSuspenseReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class CurrentSuspenseReportResponseItem {
    /**
     * Flag to indicate ACH.
     */
    private boolean isACH;
    /**
     * Payment status.
     */
    private String paymentStatus;
    /**
     * Date.
     */
    private Date date;
    /**
     * The batch number.
     */
    private String batchNumber;
    /**
     * CSD.
     */
    private String csd;
    /**
     * The amount.
     */
    private BigDecimal amount;
    /**
     * Birth date of claimant.
     */
    private Date birthDate;
    /**
     * The claimant.
     */
    private String claimant;
    /**
     * The name.
     */
    private String name;
    /**
     * Balance.
     */
    private BigDecimal balance;
    /**
     * Account status.
     */
    private String accountStatus;

    /**
     * Default constructor.
     */
    public CurrentSuspenseReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>paymentStatus</code>.
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the field <code>paymentStatus</code>.
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Gets the value of the field <code>date</code>.
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the value of the field <code>date</code>.
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the value of the field <code>batchNumber</code>.
     * @return the batchNumber
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the value of the field <code>batchNumber</code>.
     * @param batchNumber the batchNumber to set
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the value of the field <code>csd</code>.
     * @return the csd
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the value of the field <code>csd</code>.
     * @param csd the csd to set
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the value of the field <code>amount</code>.
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the field <code>amount</code>.
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the value of the field <code>birthDate</code>.
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the field <code>birthDate</code>.
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the value of the field <code>claimant</code>.
     * @return the claimant
     */
    public String getClaimant() {
        return claimant;
    }

    /**
     * Sets the value of the field <code>claimant</code>.
     * @param claimant the claimant to set
     */
    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    /**
     * Gets the value of the field <code>name</code>.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the field <code>name</code>.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of the field <code>balance</code>.
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the value of the field <code>balance</code>.
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets the value of the field <code>accountStatus</code>.
     * @return the accountStatus
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the value of the field <code>accountStatus</code>.
     * @param accountStatus the accountStatus to set
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Gets the value of the field <code>isACH</code>.
     * @return the isACH
     */
    public boolean isACH() {
        return isACH;
    }

    /**
     * Sets the value of the field <code>isACH</code>.
     * @param isACH the isACH to set
     */
    public void setACH(boolean isACH) {
        this.isACH = isACH;
    }
}
