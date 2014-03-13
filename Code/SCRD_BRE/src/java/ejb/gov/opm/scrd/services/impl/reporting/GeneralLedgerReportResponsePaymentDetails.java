/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link GeneralLedgerReportService} service.
 * </p>
 *
 * http://apps.topcoder.com/forums/?module=Thread&threadID=810138&start=0
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class GeneralLedgerReportResponsePaymentDetails {

    /**
     * The id.
     */
    private String id;

    /**
     * The CSD.
     */
    private String csd;

    /**
     * The DOB.
     */
    private String claimantDateOfBirth;

    /**
     * The claimant name.
     */
    private String claimantName;

    /**
     * The payment date.
     */
    private Date paymentDate;

    /**
     * Payment amount.
     */
    private BigDecimal paymentAmount;

    /**
     * Default constructor.
     */
    public GeneralLedgerReportResponsePaymentDetails() {
    }

    /**
     * Gets the value of the field <code>id</code>.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the field <code>id</code>.
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * Gets the value of the field <code>claimantDateOfBirth</code>.
     * @return the claimantDateOfBirth
     */
    public String getClaimantDateOfBirth() {
        return claimantDateOfBirth;
    }

    /**
     * Sets the value of the field <code>claimantDateOfBirth</code>.
     * @param claimantDateOfBirth the claimantDateOfBirth to set
     */
    public void setClaimantDateOfBirth(String claimantDateOfBirth) {
        this.claimantDateOfBirth = claimantDateOfBirth;
    }

    /**
     * Gets the value of the field <code>claimantName</code>.
     * @return the claimantName
     */
    public String getClaimantName() {
        return claimantName;
    }

    /**
     * Sets the value of the field <code>claimantName</code>.
     * @param claimantName the claimantName to set
     */
    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    /**
     * Gets the value of the field <code>paymentDate</code>.
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the value of the field <code>paymentDate</code>.
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Gets the value of the field <code>paymentAmount</code>.
     * @return the paymentAmount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the field <code>paymentAmount</code>.
     * @param paymentAmount the paymentAmount to set
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}

