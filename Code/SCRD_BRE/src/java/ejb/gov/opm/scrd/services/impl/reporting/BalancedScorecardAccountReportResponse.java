/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.List;

/**
 * This class represents the response for the balanced scorecard account report. <p> <strong>Thread-safety:</strong>
 * This class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class BalancedScorecardAccountReportResponse extends BaseBalancedReportResponse {
    /**
     * Represents the closed account items list. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private List<BalancedScorecardAccountReportResponseItem> closedAccountItems;

    /**
     * Represents the initial billing items list. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private List<BalancedScorecardAccountReportResponseItem> initialBillingItems;

    /**
     * Represents the new account items list. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private List<BalancedScorecardAccountReportResponseItem> newAccountItems;

    /**
     * Represents the total closed account field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer closedAccountTotal;

    /**
     * Represents the total initial billing field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer initialBillingTotal;

    /**
     * Represents the total new account field. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Integer newAccountTotal;

    /**
     * Creates a new instance of the {@link BalancedScorecardAccountReportResponse} class.
     */
    public BalancedScorecardAccountReportResponse() {
        super();
    }

    /**
     * Gets the closed account items field.
     *
     * @return the field.
     */
    public List<BalancedScorecardAccountReportResponseItem> getClosedAccountItems() {
        return closedAccountItems;
    }

    /**
     * Sets the closed account items field.
     *
     * @param closedAccountItems
     *         the field value.
     */
    public void setClosedAccountItems(List<BalancedScorecardAccountReportResponseItem> closedAccountItems) {
        this.closedAccountItems = closedAccountItems;
    }

    /**
     * Gets the initial billing items field.
     *
     * @return the field.
     */
    public List<BalancedScorecardAccountReportResponseItem> getInitialBillingItems() {
        return initialBillingItems;
    }

    /**
     * Sets the initial billing items field.
     *
     * @param initialBillingItems
     *         the field value.
     */
    public void setInitialBillingItems(List<BalancedScorecardAccountReportResponseItem> initialBillingItems) {
        this.initialBillingItems = initialBillingItems;
    }

    /**
     * Gets the new account items list field.
     *
     * @return the field.
     */
    public List<BalancedScorecardAccountReportResponseItem> getNewAccountItems() {
        return newAccountItems;
    }

    /**
     * Sets the new account items list field.
     *
     * @param newAccountItems
     *         the field value.
     */
    public void setNewAccountItems(List<BalancedScorecardAccountReportResponseItem> newAccountItems) {
        this.newAccountItems = newAccountItems;
    }

    /**
     * Gets the closed account total field.
     *
     * @return the field.
     */
    public Integer getClosedAccountTotal() {
        return closedAccountTotal;
    }

    /**
     * Sets the closed account total field.
     *
     * @param closedAccountTotal
     *         the field value.
     */
    public void setClosedAccountTotal(Integer closedAccountTotal) {
        this.closedAccountTotal = closedAccountTotal;
    }

    /**
     * Gets the initial billing total field.
     *
     * @return the field.
     */
    public Integer getInitialBillingTotal() {
        return initialBillingTotal;
    }

    /**
     * Sets the initial billing total field.
     *
     * @param initialBillingTotal
     *         the field value.
     */
    public void setInitialBillingTotal(Integer initialBillingTotal) {
        this.initialBillingTotal = initialBillingTotal;
    }

    /**
     * Gets the new account total field.
     *
     * @return the field.
     */
    public Integer getNewAccountTotal() {
        return newAccountTotal;
    }

    /**
     * Sets the new account total field.
     *
     * @param newAccountTotal
     *         the field value.
     */
    public void setNewAccountTotal(Integer newAccountTotal) {
        this.newAccountTotal = newAccountTotal;
    }
}
