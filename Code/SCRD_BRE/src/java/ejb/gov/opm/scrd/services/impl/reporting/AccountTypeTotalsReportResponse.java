/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.Map;

/**
 * This class represents the response for the namesake report. <p> <strong>Thread-safety:</strong> This class is mutable
 * and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class AccountTypeTotalsReportResponse extends BaseReportResponse {
    /**
     * Represents the totals of CSRS accounts. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Map<String, Integer> csrsAccounts;

    /**
     * Represents the totals of FERS accounts. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Map<String, Integer> fersAccounts;

    /**
     * Represents the totals unknown new accounts. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer unknownNewAccounts;

    /**
     * Represents the totals of unknown active accounts. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Integer unknownActiveAccounts;

    /**
     * Represents the totals of unknown accounts. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer unknownTotal;

    /**
     * Represents total of accounts. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer totalAccounts;

    /**
     * Creates a new instance of the {@link AccountTypeTotalsReportResponse} class.
     */
    public AccountTypeTotalsReportResponse() {
        super();
    }

    /**
     * Gets the total of CSRS accounts.
     *
     * @return the field value
     */
    public Map<String, Integer> getCsrsAccounts() {
        return csrsAccounts;
    }

    /**
     * Sets the total of CSRS accounts.
     *
     * @param csrsAccounts
     *         the field value.
     */
    public void setCsrsAccounts(Map<String, Integer> csrsAccounts) {
        this.csrsAccounts = csrsAccounts;
    }

    /**
     * Gets the total of FERS accounts.
     *
     * @return the field value
     */
    public Map<String, Integer> getFersAccounts() {
        return fersAccounts;
    }

    /**
     * Sets the total of FERS accounts.
     *
     * @param fersAccounts
     *         the field value.
     */
    public void setFersAccounts(Map<String, Integer> fersAccounts) {
        this.fersAccounts = fersAccounts;
    }

    /**
     * Gets the total of unknown new accounts.
     *
     * @return the field value
     */
    public Integer getUnknownNewAccounts() {
        return unknownNewAccounts;
    }

    /**
     * Sets the total of unknown new accounts.
     *
     * @param unknownNewAccounts
     *         the field value.
     */
    public void setUnknownNewAccounts(Integer unknownNewAccounts) {
        this.unknownNewAccounts = unknownNewAccounts;
    }

    /**
     * Gets the total of unknown active accounts.
     *
     * @return the field value
     */
    public Integer getUnknownActiveAccounts() {
        return unknownActiveAccounts;
    }

    /**
     * Sets the total of unknown active accounts.
     *
     * @param unknownActiveAccounts
     *         the field value.
     */
    public void setUnknownActiveAccounts(Integer unknownActiveAccounts) {
        this.unknownActiveAccounts = unknownActiveAccounts;
    }

    /**
     * Gets the total of unknown accounts.
     *
     * @return the field value
     */
    public Integer getUnknownTotal() {
        return unknownTotal;
    }

    /**
     * Sets the total of unknwon accounts.
     *
     * @param unknownTotal
     *         the field value.
     */
    public void setUnknownTotal(Integer unknownTotal) {
        this.unknownTotal = unknownTotal;
    }

    /**
     * Gets the total of accounts.
     *
     * @return the field value
     */
    public Integer getTotalAccounts() {
        return totalAccounts;
    }

    /**
     * Sets the total accounts.
     *
     * @param totalAccounts
     *         the field value.
     */
    public void setTotalAccounts(Integer totalAccounts) {
        this.totalAccounts = totalAccounts;
    }
}
