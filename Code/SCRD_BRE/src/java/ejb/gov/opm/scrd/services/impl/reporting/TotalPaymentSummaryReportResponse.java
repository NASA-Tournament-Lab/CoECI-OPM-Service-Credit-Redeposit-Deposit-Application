/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.math.BigDecimal;

/**
 * <p>
 * This class serves as the response object that contains result details
 * to be used to generate reports in {@link TotalPaymentSummaryReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class TotalPaymentSummaryReportResponse extends BaseReportResponse {
    /**
     * Total deposits pre 1082.
     */
    private BigDecimal totalDepositsPre1082;
    /**
     * Total deposits post 982.
     */
    private BigDecimal totalDepositsPost982;
    /**
     * CSRS deposits post 982.
     */
    private BigDecimal csrsDepositsPost982;
    /**
     * FERS deposits post 982.
     */
    private BigDecimal fersDepositsPost982;
    /**
     * FERS postal deposits post 982.
     */
    private BigDecimal fersPostalDepositsPost982;
    /**
     * FERS non postal deposits post 982.
     */
    private BigDecimal fersNonPostalDepositsPost982;
    /**
     * Total redeposits pre 1082.
     */
    private BigDecimal totalRedepositsPre1082;
    /**
     * Total redeposits post 982.
     */
    private BigDecimal totalRedepositsPost982;
    /**
     * Total payments on file.
     */
    private BigDecimal totalPaymentsOnFile;
    /**
     * Account number on file.
     */
    private Integer accountNumberOnFile;

    /**
     * Default constructor.
     */
    public TotalPaymentSummaryReportResponse() {
    }

    /**
     * Gets the value of the field <code>totalDepositsPre1082</code>.
     * @return the totalDepositsPre1082
     */
    public BigDecimal getTotalDepositsPre1082() {
        return totalDepositsPre1082;
    }

    /**
     * Sets the value of the field <code>totalDepositsPre1082</code>.
     * @param totalDepositsPre1082 the totalDepositsPre1082 to set
     */
    public void setTotalDepositsPre1082(BigDecimal totalDepositsPre1082) {
        this.totalDepositsPre1082 = totalDepositsPre1082;
    }

    /**
     * Gets the value of the field <code>totalDepositsPost982</code>.
     * @return the totalDepositsPost982
     */
    public BigDecimal getTotalDepositsPost982() {
        return totalDepositsPost982;
    }

    /**
     * Sets the value of the field <code>totalDepositsPost982</code>.
     * @param totalDepositsPost982 the totalDepositsPost982 to set
     */
    public void setTotalDepositsPost982(BigDecimal totalDepositsPost982) {
        this.totalDepositsPost982 = totalDepositsPost982;
    }

    /**
     * Gets the value of the field <code>csrsDepositsPost982</code>.
     * @return the csrsDepositsPost982
     */
    public BigDecimal getCsrsDepositsPost982() {
        return csrsDepositsPost982;
    }

    /**
     * Sets the value of the field <code>csrsDepositsPost982</code>.
     * @param csrsDepositsPost982 the csrsDepositsPost982 to set
     */
    public void setCsrsDepositsPost982(BigDecimal csrsDepositsPost982) {
        this.csrsDepositsPost982 = csrsDepositsPost982;
    }

    /**
     * Gets the value of the field <code>fersDepositsPost982</code>.
     * @return the fersDepositsPost982
     */
    public BigDecimal getFersDepositsPost982() {
        return fersDepositsPost982;
    }

    /**
     * Sets the value of the field <code>fersDepositsPost982</code>.
     * @param fersDepositsPost982 the fersDepositsPost982 to set
     */
    public void setFersDepositsPost982(BigDecimal fersDepositsPost982) {
        this.fersDepositsPost982 = fersDepositsPost982;
    }

    /**
     * Gets the value of the field <code>fersPostalDepositsPost982</code>.
     * @return the fersPostalDepositsPost982
     */
    public BigDecimal getFersPostalDepositsPost982() {
        return fersPostalDepositsPost982;
    }

    /**
     * Sets the value of the field <code>fersPostalDepositsPost982</code>.
     * @param fersPostalDepositsPost982 the fersPostalDepositsPost982 to set
     */
    public void setFersPostalDepositsPost982(BigDecimal fersPostalDepositsPost982) {
        this.fersPostalDepositsPost982 = fersPostalDepositsPost982;
    }

    /**
     * Gets the value of the field <code>fersNonPostalDepositsPost982</code>.
     * @return the fersNonPostalDepositsPost982
     */
    public BigDecimal getFersNonPostalDepositsPost982() {
        return fersNonPostalDepositsPost982;
    }

    /**
     * Sets the value of the field <code>fersNonPostalDepositsPost982</code>.
     * @param fersNonPostalDepositsPost982 the fersNonPostalDepositsPost982 to set
     */
    public void setFersNonPostalDepositsPost982(BigDecimal fersNonPostalDepositsPost982) {
        this.fersNonPostalDepositsPost982 = fersNonPostalDepositsPost982;
    }

    /**
     * Gets the value of the field <code>totalRedepositsPre1082</code>.
     * @return the totalRedepositsPre1082
     */
    public BigDecimal getTotalRedepositsPre1082() {
        return totalRedepositsPre1082;
    }

    /**
     * Sets the value of the field <code>totalRedepositsPre1082</code>.
     * @param totalRedepositsPre1082 the totalRedepositsPre1082 to set
     */
    public void setTotalRedepositsPre1082(BigDecimal totalRedepositsPre1082) {
        this.totalRedepositsPre1082 = totalRedepositsPre1082;
    }

    /**
     * Gets the value of the field <code>totalRedepositsPost982</code>.
     * @return the totalRedepositsPost982
     */
    public BigDecimal getTotalRedepositsPost982() {
        return totalRedepositsPost982;
    }

    /**
     * Sets the value of the field <code>totalRedepositsPost982</code>.
     * @param totalRedepositsPost982 the totalRedepositsPost982 to set
     */
    public void setTotalRedepositsPost982(BigDecimal totalRedepositsPost982) {
        this.totalRedepositsPost982 = totalRedepositsPost982;
    }

    /**
     * Gets the value of the field <code>totalPaymentsOnFile</code>.
     * @return the totalPaymentsOnFile
     */
    public BigDecimal getTotalPaymentsOnFile() {
        return totalPaymentsOnFile;
    }

    /**
     * Sets the value of the field <code>totalPaymentsOnFile</code>.
     * @param totalPaymentsOnFile the totalPaymentsOnFile to set
     */
    public void setTotalPaymentsOnFile(BigDecimal totalPaymentsOnFile) {
        this.totalPaymentsOnFile = totalPaymentsOnFile;
    }

    /**
     * Gets the value of the field <code>accountNumberOnFile</code>.
     * @return the accountNumberOnFile
     */
    public Integer getAccountNumberOnFile() {
        return accountNumberOnFile;
    }

    /**
     * Sets the value of the field <code>accountNumberOnFile</code>.
     * @param accountNumberOnFile the accountNumberOnFile to set
     */
    public void setAccountNumberOnFile(Integer accountNumberOnFile) {
        this.accountNumberOnFile = accountNumberOnFile;
    }
}
