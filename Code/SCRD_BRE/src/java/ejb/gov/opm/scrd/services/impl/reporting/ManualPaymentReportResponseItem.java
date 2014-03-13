/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details to be used to generate reports in
 * {@link ManualPaymentReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class ManualPaymentReportResponseItem {
    /**
     * The batch number.
     */
    private String batchNumber;
    /**
     * CSD #.
     */
    private String csd;
    /**
     * Birth date.
     */
    private Date birthDate;
    /**
     * Payment amount.
     */
    private BigDecimal paymentAmount;
    /**
     * The pay date.
     */
    private Date payDate;
    /**
     * The batch date.
     */
    private Date batchDate;
    /**
     * Payment status description.
     */
    private String paymentStatusDescription;
    /**
     * Claimant.
     */
    private String claimant;
    /**
     * Claim Number.
     */
    private String claimNumber;

    /**
     * Default constructor.
     */
    public ManualPaymentReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>batchNumber</code>.
     *
     * @return the batchNumber
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the value of the field <code>batchNumber</code>.
     *
     * @param batchNumber the batchNumber to set
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the value of the field <code>csd</code>.
     *
     * @return the csd
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the value of the field <code>csd</code>.
     *
     * @param csd the csd to set
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the value of the field <code>birthDate</code>.
     *
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the field <code>birthDate</code>.
     *
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the value of the field <code>paymentAmount</code>.
     *
     * @return the paymentAmount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the field <code>paymentAmount</code>.
     *
     * @param paymentAmount the paymentAmount to set
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Gets the value of the field <code>payDate</code>.
     *
     * @return the payDate
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * Sets the value of the field <code>payDate</code>.
     *
     * @param payDate the payDate to set
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * Gets the value of the field <code>batchDate</code>.
     *
     * @return the batchDate
     */
    public Date getBatchDate() {
        return batchDate;
    }

    /**
     * Sets the value of the field <code>batchDate</code>.
     *
     * @param batchDate the batchDate to set
     */
    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    /**
     * Gets the value of the field <code>paymentStatusDescription</code>.
     *
     * @return the paymentStatusDescription
     */
    public String getPaymentStatusDescription() {
        return paymentStatusDescription;
    }

    /**
     * Sets the value of the field <code>paymentStatusDescription</code>.
     *
     * @param paymentStatusDescription the paymentStatusDescription to set
     */
    public void setPaymentStatusDescription(String paymentStatusDescription) {
        this.paymentStatusDescription = paymentStatusDescription;
    }

    /**
     * Gets the value of the field <code>claimant</code>.
     *
     * @return the claimant
     */
    public String getClaimant() {
        return claimant;
    }

    /**
     * Sets the value of the field <code>claimant</code>.
     *
     * @param claimant the claimant to set
     */
    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    /**
     * Gets the value of the field <code>claimNumber</code>.
     *
     * @return the claimNumber
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the value of the field <code>claimNumber</code>.
     *
     * @param claimNumber the claimNumber to set
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

}
