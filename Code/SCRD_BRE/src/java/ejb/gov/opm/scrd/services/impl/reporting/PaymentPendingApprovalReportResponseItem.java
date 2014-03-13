/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link PaymentPendingApprovalReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class PaymentPendingApprovalReportResponseItem {
    /**
     * The approval user.
     */
    private String approvalUser;
    /**
     * Batch number.
     */
    private String batchNumber;
    /**
     * Deposit date.
     */
    private Date depositDate;
    /**
     * Modified time.
     */
    private Date modifiedTime;
    /**
     * The amount.
     */
    private BigDecimal amount;
    /**
     * CSD #.
     */
    private String csd;
    /**
     * The date of birth.
     */
    private Date dateOfBirth;
    /**
     * The account status.
     */
    private String accountStatus;
    /**
     * Payment info.
     */
    private String paymentInfo;

    /**
     * Default constructor.
     */
    public PaymentPendingApprovalReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>approvalUser</code>.
     * @return the approvalUser
     */
    public String getApprovalUser() {
        return approvalUser;
    }

    /**
     * Sets the value of the field <code>approvalUser</code>.
     * @param approvalUser the approvalUser to set
     */
    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
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
     * Gets the value of the field <code>depositDate</code>.
     * @return the depositDate
     */
    public Date getDepositDate() {
        return depositDate;
    }

    /**
     * Sets the value of the field <code>depositDate</code>.
     * @param depositDate the depositDate to set
     */
    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    /**
     * Gets the value of the field <code>modifiedTime</code>.
     * @return the modifiedTime
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * Sets the value of the field <code>modifiedTime</code>.
     * @param modifiedTime the modifiedTime to set
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
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
     * Gets the value of the field <code>dateOfBirth</code>.
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the field <code>dateOfBirth</code>.
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
     * Gets the value of the field <code>paymentInfo</code>.
     * @return the paymentInfo
     */
    public String getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * Sets the value of the field <code>paymentInfo</code>.
     * @param paymentInfo the paymentInfo to set
     */
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
