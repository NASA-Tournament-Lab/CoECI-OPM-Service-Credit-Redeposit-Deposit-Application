/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.batchprocessing;

import gov.opm.scrd.entities.common.IdentifiableEntity;

import java.math.BigDecimal;

import java.util.Date;


/**
 * <p>This class is simply a POJO containing details necessary to create General Ledger file.</p>
 *  <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public class AllDetails extends IdentifiableEntity {
    /**
     * Represents the payment type. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String paymentType;

    /**
     * Represents the payment date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date paymentDate;

    /**
     * Represents the julian date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer julianDate;

    /**
     * Represents the julian date report. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer julianDateReport;

    /**
     * Represents the gl filler. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String glFiller;

    /**
     * Represents the gl code. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String glCode;

    /**
     * Represents the fiscal year. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer fiscalYear;

    /**
     * Represents the gl accounting code. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String glAccountingCode;

    /**
     * Represents the recipient amount. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private BigDecimal recipientAmount;

    /**
     * Represents the revenue source code. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String revenueSourceCode;

    /**
     * Represents the agency. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String agency;

    /**
     * Represents the pay transaction key. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String payTransactionKey;

    /**
     * Represents the scm claim number. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String scmClaimNumber;

    /**
     * Represents the scm date of birth. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String scmDateOfBirth;

    /**
     * Represents the scm retirement type description. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private String scmRetirementTypeDescription;

    /**
     * Represents the claimant name. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String claimantName;

    /**
     * Represents the print date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date printDate;

    /**
     * Represents the total non postal fers. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal totalNonPostalFers;

    /**
     * Represents the total postal fers. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal totalPostalFers;

    /**
     * Represents the total csrs. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal totalCsrs;

    /**
     * Represents the julian now. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer julianNow;

    /**
     * Instantiates a new all details.
     */
    public AllDetails() {
    }

    /**
     * Gets the payment type.
     *
     * @return the payment type
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type.
     *
     * @param paymentType the new payment type
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the payment date.
     *
     * @return the payment date
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the payment date.
     *
     * @param paymentDate the new payment date
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Gets the julian date.
     *
     * @return the julian date
     */
    public Integer getJulianDate() {
        return julianDate;
    }

    /**
     * Sets the julian date.
     *
     * @param julianDate the new julian date
     */
    public void setJulianDate(Integer julianDate) {
        this.julianDate = julianDate;
    }

    /**
     * Gets the julian date report.
     *
     * @return the julian date report
     */
    public Integer getJulianDateReport() {
        return julianDateReport;
    }

    /**
     * Sets the julian date report.
     *
     * @param julianDateReport the new julian date report
     */
    public void setJulianDateReport(Integer julianDateReport) {
        this.julianDateReport = julianDateReport;
    }

    /**
     * Gets the gl filler.
     *
     * @return the gl filler
     */
    public String getGlFiller() {
        return glFiller;
    }

    /**
     * Sets the gl filler.
     *
     * @param glFiller the new gl filler
     */
    public void setGlFiller(String glFiller) {
        this.glFiller = glFiller;
    }

    /**
     * Gets the gl code.
     *
     * @return the gl code
     */
    public String getGlCode() {
        return glCode;
    }

    /**
     * Sets the gl code.
     *
     * @param glCode the new gl code
     */
    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    /**
     * Gets the fiscal year.
     *
     * @return the fiscal year
     */
    public Integer getFiscalYear() {
        return fiscalYear;
    }

    /**
     * Sets the fiscal year.
     *
     * @param fiscalYear the new fiscal year
     */
    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     * Gets the gl accounting code.
     *
     * @return the gl accounting code
     */
    public String getGlAccountingCode() {
        return glAccountingCode;
    }

    /**
     * Sets the gl accounting code.
     *
     * @param glAccountingCode the new gl accounting code
     */
    public void setGlAccountingCode(String glAccountingCode) {
        this.glAccountingCode = glAccountingCode;
    }

    /**
     * Gets the recipient amount.
     *
     * @return the recipient amount
     */
    public BigDecimal getRecipientAmount() {
        return recipientAmount;
    }

    /**
     * Sets the recipient amount.
     *
     * @param recipientAmount the new recipient amount
     */
    public void setRecipientAmount(BigDecimal recipientAmount) {
        this.recipientAmount = recipientAmount;
    }

    /**
     * Gets the revenue source code.
     *
     * @return the revenue source code
     */
    public String getRevenueSourceCode() {
        return revenueSourceCode;
    }

    /**
     * Sets the revenue source code.
     *
     * @param revenueSourceCode the new revenue source code
     */
    public void setRevenueSourceCode(String revenueSourceCode) {
        this.revenueSourceCode = revenueSourceCode;
    }

    /**
     * Gets the agency.
     *
     * @return the agency
     */
    public String getAgency() {
        return agency;
    }

    /**
     * Sets the agency.
     *
     * @param agency the new agency
     */
    public void setAgency(String agency) {
        this.agency = agency;
    }

    /**
     * Gets the pay transaction key.
     *
     * @return the pay transaction key
     */
    public String getPayTransactionKey() {
        return payTransactionKey;
    }

    /**
     * Sets the pay transaction key.
     *
     * @param payTransactionKey the new pay transaction key
     */
    public void setPayTransactionKey(String payTransactionKey) {
        this.payTransactionKey = payTransactionKey;
    }

    /**
     * Gets the scm claim number.
     *
     * @return the scm claim number
     */
    public String getScmClaimNumber() {
        return scmClaimNumber;
    }

    /**
     * Sets the scm claim number.
     *
     * @param scmClaimNumber the new scm claim number
     */
    public void setScmClaimNumber(String scmClaimNumber) {
        this.scmClaimNumber = scmClaimNumber;
    }

    /**
     * Gets the scm date of birth.
     *
     * @return the scm date of birth
     */
    public String getScmDateOfBirth() {
        return scmDateOfBirth;
    }

    /**
     * Sets the scm date of birth.
     *
     * @param scmDateOfBirth the new scm date of birth
     */
    public void setScmDateOfBirth(String scmDateOfBirth) {
        this.scmDateOfBirth = scmDateOfBirth;
    }

    /**
     * Gets the scm retirement type description.
     *
     * @return the scm retirement type description
     */
    public String getScmRetirementTypeDescription() {
        return scmRetirementTypeDescription;
    }

    /**
     * Sets the scm retirement type description.
     *
     * @param scmRetirementTypeDescription the new scm retirement type description
     */
    public void setScmRetirementTypeDescription(String scmRetirementTypeDescription) {
        this.scmRetirementTypeDescription = scmRetirementTypeDescription;
    }

    /**
     * Gets the claimant name.
     *
     * @return the claimant name
     */
    public String getClaimantName() {
        return claimantName;
    }

    /**
     * Sets the claimant name.
     *
     * @param claimantName the new claimant name
     */
    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    /**
     * Gets the prints the date.
     *
     * @return the prints the date
     */
    public Date getPrintDate() {
        return printDate;
    }

    /**
     * Sets the prints the date.
     *
     * @param printDate the new prints the date
     */
    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    /**
     * Gets the total non postal fers.
     *
     * @return the total non postal fers
     */
    public BigDecimal getTotalNonPostalFers() {
        return totalNonPostalFers;
    }

    /**
     * Sets the total non postal fers.
     *
     * @param totalNonPostalFers the new total non postal fers
     */
    public void setTotalNonPostalFers(BigDecimal totalNonPostalFers) {
        this.totalNonPostalFers = totalNonPostalFers;
    }

    /**
     * Gets the total postal fers.
     *
     * @return the total postal fers
     */
    public BigDecimal getTotalPostalFers() {
        return totalPostalFers;
    }

    /**
     * Sets the total postal fers.
     *
     * @param totalPostalFers the new total postal fers
     */
    public void setTotalPostalFers(BigDecimal totalPostalFers) {
        this.totalPostalFers = totalPostalFers;
    }

    /**
     * Gets the total csrs.
     *
     * @return the total csrs
     */
    public BigDecimal getTotalCsrs() {
        return totalCsrs;
    }

    /**
     * Sets the total csrs.
     *
     * @param totalCsrs the new total csrs
     */
    public void setTotalCsrs(BigDecimal totalCsrs) {
        this.totalCsrs = totalCsrs;
    }

    /**
     * Gets the julian now.
     *
     * @return the julian now
     */
    public Integer getJulianNow() {
        return julianNow;
    }

    /**
     * Sets the julian now.
     *
     * @param julianNow the new julian now
     */
    public void setJulianNow(Integer julianNow) {
        this.julianNow = julianNow;
    }
}
