/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.Date;
import java.util.List;

/**
 * This class represents the response entry for the namesake report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class ClosedAccountReportResponseEntry {
    /**
     * Represents name field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private String name;

    /**
     * Represents date of birth field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date dateOfBirth;

    /**
     * Represents close date field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Date closeDate;

    /**
     * Represents claim number field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String claimNumber;

    /**
     * Represents ssn field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private String ssn;

    /**
     * Represents billing name field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String billingName;

    /**
     * Represents billing date field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date billingDate;

    /**
     * Represents list of ClosedAccountReportResponseItems. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private List<ClosedAccountReportResponseItem> items;

    /**
     * Represents date of last payment field. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Date dateOfLastPayment;

    /**
     * Represents the covered by field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String coveredBy;

    /**
     * Represents date of last activity field. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Date dateOfLastActivity;

    /**
     * Represents list of covered service periods. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private List<CoveredServicePeriod> periods;

    /**
     * Creates a new instance of the {@link ClosedAccountReportResponseEntry} class.
     */
    public ClosedAccountReportResponseEntry() {
    }

    /**
     * Gets the name field.
     *
     * @return the field value.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name field.
     *
     * @param name
     *         the field value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date of birth field.
     *
     * @return the field value.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth field.
     *
     * @param dateOfBirth
     *         the field value.
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the close date field.
     *
     * @return the field value.
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * Sets the close date field.
     *
     * @param closeDate
     *         the field value.
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * Gets the claim number field.
     *
     * @return the field value.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number field.
     *
     * @param claimNumber
     *         the field value.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the ssn field.
     *
     * @return the field value.
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the ssn field.
     *
     * @param ssn
     *         the field value.
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * Gets the billing name field.
     *
     * @return the field value.
     */
    public String getBillingName() {
        return billingName;
    }

    /**
     * Sets the billing name field.
     *
     * @param billingName
     *         the field value.
     */
    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    /**
     * Gets the billing date field.
     *
     * @return the field value.
     */
    public Date getBillingDate() {
        return billingDate;
    }

    /**
     * Sets the billing date field.
     *
     * @param billingDate
     *         the field value.
     */
    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    /**
     * Gets the list of ClosedAccountReportResponseItems.
     *
     * @return the field value.
     */
    public List<ClosedAccountReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the list of ClosedAccountReportResponseItems.
     *
     * @param items
     *         the field value.
     */
    public void setItems(List<ClosedAccountReportResponseItem> items) {
        this.items = items;
    }

    /**
     * Gets the date of last payment field.
     *
     * @return the field value.
     */
    public Date getDateOfLastPayment() {
        return dateOfLastPayment;
    }

    /**
     * Sets the date of last payment field.
     *
     * @param dateOfLastPayment
     *         the field value.
     */
    public void setDateOfLastPayment(Date dateOfLastPayment) {
        this.dateOfLastPayment = dateOfLastPayment;
    }

    /**
     * Gets the the covered by field.
     *
     * @return the field value.
     */
    public String getCoveredBy() {
        return coveredBy;
    }

    /**
     * Sets the covered by field.
     *
     * @param coveredBy
     *         the field value.
     */
    public void setCoveredBy(String coveredBy) {
        this.coveredBy = coveredBy;
    }

    /**
     * Gets the date of last activity field.
     *
     * @return the field value.
     */
    public Date getDateOfLastActivity() {
        return dateOfLastActivity;
    }

    /**
     * Sets the date of last activity field.
     *
     * @param dateOfLastActivity
     *         the field value.
     */
    public void setDateOfLastActivity(Date dateOfLastActivity) {
        this.dateOfLastActivity = dateOfLastActivity;
    }

    /**
     * Gets the list of covered service period field.
     *
     * @return the field value.
     */
    public List<CoveredServicePeriod> getPeriods() {
        return periods;
    }

    /**
     * Sets the list of covered service period field.
     *
     * @param periods
     *         the field value.
     */
    public void setPeriods(List<CoveredServicePeriod> periods) {
        this.periods = periods;
    }
}
