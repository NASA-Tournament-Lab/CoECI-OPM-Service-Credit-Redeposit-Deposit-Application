/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

/**
 * This class represents the response item for the balanced scorecard account report. <p>
 * <strong>Thread-safety:</strong> This class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class BalancedScorecardAccountReportResponseItem {
    /**
     * Represents the account type field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String accountType;

    /**
     * Represents the account status field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String accountStatus;

    /**
     * Represents the number field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer number;

    /**
     * Creates a new instance of the {@link BalancedScorecardAccountReportResponseItem} class.
     */
    public BalancedScorecardAccountReportResponseItem() {
    }

    /**
     * Gets the account type field.
     *
     * @return the field.
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type field.
     *
     * @param accountType
     *         the field value.
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the account status field.
     *
     * @return the field.
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the account status field.
     *
     * @param accountStatus
     *         the field value.
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Gets the number field.
     *
     * @return the field.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Sets the number field.
     *
     * @param number
     *         the field value.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}
