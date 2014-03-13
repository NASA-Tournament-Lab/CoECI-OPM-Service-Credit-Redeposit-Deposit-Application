/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details to be used to generate reports in
 * {@link MonthlyAdjustmentReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class MonthlyAdjustmentReportResponseItem {
    /**
     * Date.
     */
    private Date date;

    /**
     * Modifier.
     */
    private String modifier;

    /**
     * Account number.
     */
    private String accountNumber;

    /**
     * Description.
     */
    private String description;

    /**
     * Default constructor.
     */
    public MonthlyAdjustmentReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>date</code>.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the value of the field <code>date</code>.
     *
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the value of the field <code>modifier</code>.
     *
     * @return the modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * Sets the value of the field <code>modifier</code>.
     *
     * @param modifier the modifier to set
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * Gets the value of the field <code>accountNumber</code>.
     *
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the field <code>accountNumber</code>.
     *
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the value of the field <code>description</code>.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the field <code>description</code>.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
