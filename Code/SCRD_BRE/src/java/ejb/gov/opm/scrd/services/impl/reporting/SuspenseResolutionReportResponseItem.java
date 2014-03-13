/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details to be used to generate reports in
 * {@link SuspenseResolutionReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class SuspenseResolutionReportResponseItem {
    /**
     * Supervisor.
     */
    private String supervisor;
    /**
     * CSD.
     */
    private String csd;

    /**
     * Suspense.
     */
    private BigDecimal suspense;

    /**
     * Resolved.
     */
    private BigDecimal resolved;

    /**
     * Processed.
     */
    private BigDecimal processed;

    /**
     * Started as.
     */
    private String startedAs;

    /**
     * Resolution.
     */
    private String resolution;

    /**
     * Payment.
     */
    private String payment;

    /**
     * Account.
     */
    private BigDecimal account;

    /**
     * Transaction date.
     */
    private Date transactionDate;

    /**
     * Default constructor.
     */
    public SuspenseResolutionReportResponseItem() {
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
     * Gets the value of the field <code>suspense</code>.
     *
     * @return the suspense
     */
    public BigDecimal getSuspense() {
        return suspense;
    }

    /**
     * Sets the value of the field <code>suspense</code>.
     *
     * @param suspense the suspense to set
     */
    public void setSuspense(BigDecimal suspense) {
        this.suspense = suspense;
    }

    /**
     * Gets the value of the field <code>resolved</code>.
     *
     * @return the resolved
     */
    public BigDecimal getResolved() {
        return resolved;
    }

    /**
     * Sets the value of the field <code>resolved</code>.
     *
     * @param resolved the resolved to set
     */
    public void setResolved(BigDecimal resolved) {
        this.resolved = resolved;
    }

    /**
     * Gets the value of the field <code>processed</code>.
     *
     * @return the processed
     */
    public BigDecimal getProcessed() {
        return processed;
    }

    /**
     * Sets the value of the field <code>processed</code>.
     *
     * @param processed the processed to set
     */
    public void setProcessed(BigDecimal processed) {
        this.processed = processed;
    }

    /**
     * Gets the value of the field <code>startedAs</code>.
     *
     * @return the startedAs
     */
    public String getStartedAs() {
        return startedAs;
    }

    /**
     * Sets the value of the field <code>startedAs</code>.
     *
     * @param startedAs the startedAs to set
     */
    public void setStartedAs(String startedAs) {
        this.startedAs = startedAs;
    }

    /**
     * Gets the value of the field <code>resolution</code>.
     *
     * @return the resolution
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Sets the value of the field <code>resolution</code>.
     *
     * @param resolution the resolution to set
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * Gets the value of the field <code>payment</code>.
     *
     * @return the payment
     */
    public String getPayment() {
        return payment;
    }

    /**
     * Sets the value of the field <code>payment</code>.
     *
     * @param payment the payment to set
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * Gets the value of the field <code>account</code>.
     *
     * @return the account
     */
    public BigDecimal getAccount() {
        return account;
    }

    /**
     * Sets the value of the field <code>account</code>.
     *
     * @param account the account to set
     */
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    /**
     * Gets the value of the field <code>supervisor</code>.
     *
     * @return the supervisor
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * Sets the value of the field <code>supervisor</code>.
     *
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * Gets the value of the field <code>transactionDate</code>.
     *
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the field <code>transactionDate</code>.
     *
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

}
