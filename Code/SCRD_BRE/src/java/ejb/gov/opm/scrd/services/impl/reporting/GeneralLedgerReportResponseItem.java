/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link GeneralLedgerReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class GeneralLedgerReportResponseItem {
    /**
     * The grouping date.
     */
    private String groupDate;
    /**
     * Plan.
     */
    private String plan;

    /**
     * Agency.
     */
    private String agency;

    /**
     * Type.
     */
    private String type;

    /**
     * Accounting code.
     */
    private String accountingCode;

    /**
     * General ledger date.
     */
    private Date glDate;

    /**
     * General ledger filler.
     */
    private String glFiller;

    /**
     * General ledger code.
     */
    private String glCode;

    /**
     * Source code.
     */
    private String sourceCode;

    /**
     * Payments number.
     */
    private Integer paymentsNumber;

    /**
     * Receipt amount.
     */
    private BigDecimal receiptAmount;

    /**
     * Payments for the group.
     */
    private List<GeneralLedgerReportResponsePaymentDetails> payments;

    /**
     * Default constructor.
     */
    public GeneralLedgerReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>plan</code>.
     * @return the plan
     */
    public String getPlan() {
        return plan;
    }

    /**
     * Sets the value of the field <code>plan</code>.
     * @param plan the plan to set
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**
     * Gets the value of the field <code>agency</code>.
     * @return the agency
     */
    public String getAgency() {
        return agency;
    }

    /**
     * Sets the value of the field <code>agency</code>.
     * @param agency the agency to set
     */
    public void setAgency(String agency) {
        this.agency = agency;
    }

    /**
     * Gets the value of the field <code>type</code>.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the field <code>type</code>.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the value of the field <code>accountingCode</code>.
     * @return the accountingCode
     */
    public String getAccountingCode() {
        return accountingCode;
    }

    /**
     * Sets the value of the field <code>accountingCode</code>.
     * @param accountingCode the accountingCode to set
     */
    public void setAccountingCode(String accountingCode) {
        this.accountingCode = accountingCode;
    }

    /**
     * Gets the value of the field <code>glDate</code>.
     * @return the glDate
     */
    public Date getGlDate() {
        return glDate;
    }

    /**
     * Sets the value of the field <code>glDate</code>.
     * @param glDate the glDate to set
     */
    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    /**
     * Gets the value of the field <code>glFiller</code>.
     * @return the glFiller
     */
    public String getGlFiller() {
        return glFiller;
    }

    /**
     * Sets the value of the field <code>glFiller</code>.
     * @param glFiller the glFiller to set
     */
    public void setGlFiller(String glFiller) {
        this.glFiller = glFiller;
    }

    /**
     * Gets the value of the field <code>glCode</code>.
     * @return the glCode
     */
    public String getGlCode() {
        return glCode;
    }

    /**
     * Sets the value of the field <code>glCode</code>.
     * @param glCode the glCode to set
     */
    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    /**
     * Gets the value of the field <code>sourceCode</code>.
     * @return the sourceCode
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * Sets the value of the field <code>sourceCode</code>.
     * @param sourceCode the sourceCode to set
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * Gets the value of the field <code>paymentsNumber</code>.
     * @return the paymentsNumber
     */
    public Integer getPaymentsNumber() {
        return paymentsNumber;
    }

    /**
     * Sets the value of the field <code>paymentsNumber</code>.
     * @param paymentsNumber the paymentsNumber to set
     */
    public void setPaymentsNumber(Integer paymentsNumber) {
        this.paymentsNumber = paymentsNumber;
    }

    /**
     * Gets the value of the field <code>receiptAmount</code>.
     * @return the receiptAmount
     */
    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    /**
     * Sets the value of the field <code>receiptAmount</code>.
     * @param receiptAmount the receiptAmount to set
     */
    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    /**
     * Gets the value of the field <code>payments</code>.
     * @return the payments
     */
    public List<GeneralLedgerReportResponsePaymentDetails> getPayments() {
        return payments;
    }

    /**
     * Sets the value of the field <code>payments</code>.
     * @param payments the payments to set
     */
    public void setPayments(List<GeneralLedgerReportResponsePaymentDetails> payments) {
        this.payments = payments;
    }

    /**
     * Gets the value of the field <code>groupDate</code>.
     * @return the groupDate
     */
    public String getGroupDate() {
        return groupDate;
    }

    /**
     * Sets the value of the field <code>groupDate</code>.
     * @param groupDate the groupDate to set
     */
    public void setGroupDate(String groupDate) {
        this.groupDate = groupDate;
    }
}

