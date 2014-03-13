/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.entities.application.ReportResponse;
import gov.opm.scrd.services.impl.BaseReportResponse;

/**
 * This class represents the response for the namesake report. <p> <strong>Thread-safety:</strong> This class is mutable
 * and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class AccountStatisticsReportResponse extends BaseReportResponse
        implements ReportResponse {
    /**
     * Represents the number of total active CSRS accounts. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Integer totalActiveCsrsAccounts;

    /**
     * Represents the number of total active FERS accounts. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Integer totalActiveFersAccounts;

    /**
     * Represents the number of total history CSRS accounts. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private Integer totalHistoryCsrsAccounts;

    /**
     * Represents the number of total history FERS accounts. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private Integer totalHistoryFersAccounts;

    /**
     * Represents the number of total CSRS accounts. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer totalCsrsAccounts;

    /**
     * Represents the number of total FERS accounts. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer totalFersAccounts;

    /**
     * Represents the number of total open accounts with no posted payments. It is accessible by getter and modified by
     * setter. It can be any value. The default value is null.
     */
    private Integer totalOpenAccountsNoPostedPayments;

    /**
     * Represents the number of total history and closed accounts with no posted payments. It is accessible by getter
     * and modified by setter. It can be any value. The default value is null.
     */
    private Integer totalHistoryClosedAccountsNoPostedPayment;

    /**
     * Represents the number of total accounts with no posted payment. It is accessible by getter and modified by
     * setter. It can be any value. The default value is null.
     */
    private Integer totalAccountsNoPostedPayments;

    /**
     * Represents the number of open accounts with payment last year. It is accessible by getter and modified by setter.
     * It can be any value. The default value is null.
     */
    private Integer totalOpenAccountsLastYear;

    /**
     * Represents the number of history and closed accounts with payment last year. It is accessible by getter and
     * modified by setter. It can be any value. The default value is null.
     */
    private Integer totalHistoryClosedAccountsLastYear;

    /**
     * Represents the number of total accounts with payment last year. It is accessible by getter and modified by
     * setter. It can be any value. The default value is null.
     */
    private Integer totalAccountsLastYear;

    /**
     * Creates a new instance of the {@link AccountStatisticsReportResponse} class.
     */
    public AccountStatisticsReportResponse() {
        super();
    }

    /**
     * Gets the the number of total active CSRS accounts.
     *
     * @return the field value.
     */
    public Integer getTotalActiveCsrsAccounts() {
        return totalActiveCsrsAccounts;
    }

    /**
     * Sets the the number of total active CSRS accounts.
     *
     * @param totalActiveCsrsAccounts
     *         the field value.
     */
    public void setTotalActiveCsrsAccounts(Integer totalActiveCsrsAccounts) {
        this.totalActiveCsrsAccounts = totalActiveCsrsAccounts;
    }

    /**
     * Gets the number of total active FERS accounts.
     *
     * @return the field value.
     */
    public Integer getTotalActiveFersAccounts() {
        return totalActiveFersAccounts;
    }

    /**
     * Sets the number of total active FERS accounts.
     *
     * @param totalActiveFersAccounts
     *         the field value.
     */
    public void setTotalActiveFersAccounts(Integer totalActiveFersAccounts) {
        this.totalActiveFersAccounts = totalActiveFersAccounts;
    }

    /**
     * Gets the number of total history CSRS accounts.
     *
     * @return the field value.
     */
    public Integer getTotalHistoryCsrsAccounts() {
        return totalHistoryCsrsAccounts;
    }

    /**
     * Sets the number of total history CSRS accounts.
     *
     * @param totalHistoryCsrsAccounts
     *         the field value.
     */
    public void setTotalHistoryCsrsAccounts(Integer totalHistoryCsrsAccounts) {
        this.totalHistoryCsrsAccounts = totalHistoryCsrsAccounts;
    }

    /**
     * Gets the number of total history FERS accounts.
     *
     * @return the field value.
     */
    public Integer getTotalHistoryFersAccounts() {
        return totalHistoryFersAccounts;
    }

    /**
     * Sets the number of total history FERS accounts.
     *
     * @param totalHistoryFersAccounts
     *         the field value.
     */
    public void setTotalHistoryFersAccounts(Integer totalHistoryFersAccounts) {
        this.totalHistoryFersAccounts = totalHistoryFersAccounts;
    }

    /**
     * Gets the number of total CSRS accounts.
     *
     * @return the field value.
     */
    public Integer getTotalCsrsAccounts() {
        return totalCsrsAccounts;
    }

    /**
     * Sets the number of total CSRS accounts.
     *
     * @param totalCsrsAccounts
     *         the field value.
     */
    public void setTotalCsrsAccounts(Integer totalCsrsAccounts) {
        this.totalCsrsAccounts = totalCsrsAccounts;
    }

    /**
     * Gets the number of total FERS accounts.
     *
     * @return the field value.
     */
    public Integer getTotalFersAccounts() {
        return totalFersAccounts;
    }

    /**
     * Sets the number of total FERS accounts.
     *
     * @param totalFersAccounts
     *         the field value.
     */
    public void setTotalFersAccounts(Integer totalFersAccounts) {
        this.totalFersAccounts = totalFersAccounts;
    }

    /**
     * Gets the number of total open accounts with no posted payments.
     *
     * @return the field value.
     */
    public Integer getTotalOpenAccountsNoPostedPayments() {
        return totalOpenAccountsNoPostedPayments;
    }

    /**
     * Sets the number of total open accounts with no posted payments.
     *
     * @param totalOpenAccountsNoPostedPayments
     *         the field value.
     */
    public void setTotalOpenAccountsNoPostedPayments(
            Integer totalOpenAccountsNoPostedPayments) {
        this.totalOpenAccountsNoPostedPayments = totalOpenAccountsNoPostedPayments;
    }

    /**
     * Gets the number of total history and closed accounts with no posted payments.
     *
     * @return the field value.
     */
    public Integer getTotalHistoryClosedAccountsNoPostedPayment() {
        return totalHistoryClosedAccountsNoPostedPayment;
    }

    /**
     * Sets the number of total history and closed accounts with no posted payments.
     *
     * @param totalHistoryClosedAccountsNoPostedPayment
     *         the field value.
     */
    public void setTotalHistoryClosedAccountsNoPostedPayment(
            Integer totalHistoryClosedAccountsNoPostedPayment) {
        this.totalHistoryClosedAccountsNoPostedPayment = totalHistoryClosedAccountsNoPostedPayment;
    }

    /**
     * Gets the number of total accounts with no posted payments.
     *
     * @return the field value.
     */
    public Integer getTotalAccountsNoPostedPayments() {
        return totalAccountsNoPostedPayments;
    }

    /**
     * Sets the number of total accounts with no posted payments.
     *
     * @param totalAccountsNoPostedPayments
     *         the field value.
     */
    public void setTotalAccountsNoPostedPayments(
            Integer totalAccountsNoPostedPayments) {
        this.totalAccountsNoPostedPayments = totalAccountsNoPostedPayments;
    }

    /**
     * Gets the number of total open accounts with payments last year.
     *
     * @return the field value.
     */
    public Integer getTotalOpenAccountsLastYear() {
        return totalOpenAccountsLastYear;
    }

    /**
     * Sets the number of total open accounts with payments last year.
     *
     * @param totalOpenAccountsLastYear
     *         the field value.
     */
    public void setTotalOpenAccountsLastYear(Integer totalOpenAccountsLastYear) {
        this.totalOpenAccountsLastYear = totalOpenAccountsLastYear;
    }

    /**
     * Gets the number of total history and closed accounts with payments last year.
     *
     * @return the field value.
     */
    public Integer getTotalHistoryClosedAccountsLastYear() {
        return totalHistoryClosedAccountsLastYear;
    }

    /**
     * Sets the number of total history and closed accounts with payments last year.
     *
     * @param totalHistoryClosedAccountsLastYear
     *         the field value.
     */
    public void setTotalHistoryClosedAccountsLastYear(
            Integer totalHistoryClosedAccountsLastYear) {
        this.totalHistoryClosedAccountsLastYear = totalHistoryClosedAccountsLastYear;
    }

    /**
     * Gets the number of total accounts with payments last year.
     *
     * @return the field value.
     */
    public Integer getTotalAccountsLastYear() {
        return totalAccountsLastYear;
    }

    /**
     * Sets the number of total accounts with payments last year.
     *
     * @param totalAccountsLastYear
     *         the field value.
     */
    public void setTotalAccountsLastYear(Integer totalAccountsLastYear) {
        this.totalAccountsLastYear = totalAccountsLastYear;
    }
}
