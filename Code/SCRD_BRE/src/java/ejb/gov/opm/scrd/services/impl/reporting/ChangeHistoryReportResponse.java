/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * This class serves as the response object that contains result details
 * to be used to generate reports in {@link ChangeHistoryReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class ChangeHistoryReportResponse extends BaseReportResponse {
    /**
     * CSD.
     */
    private String csd;

    /**
     * Birth date.
     */
    private Date birthDay;

    /**
     * Claim Name.
     */
    private String claimName;

    /**
     * Details per row.
     */
    private List<ChangeHistoryReportResponseItem> items;

    /**
     * Default constructor.
     */
    public ChangeHistoryReportResponse() {
    }

    /**
     * Gets the value of the field <code>csd</code>.
     * @return the csd
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the value of the field <code>csd</code>.
     * @param csd the csd to set
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the value of the field <code>birthDay</code>.
     * @return the birthDay
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * Sets the value of the field <code>birthDay</code>.
     * @param birthDay the birthDay to set
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * Gets the value of the field <code>claimName</code>.
     * @return the claimName
     */
    public String getClaimName() {
        return claimName;
    }

    /**
     * Sets the value of the field <code>claimName</code>.
     * @param claimName the claimName to set
     */
    public void setClaimName(String claimName) {
        this.claimName = claimName;
    }

    /**
     * Gets the value of the field <code>items</code>.
     * @return the items
     */
    public List<ChangeHistoryReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the value of the field <code>items</code>.
     * @param items the items to set
     */
    public void setItems(List<ChangeHistoryReportResponseItem> items) {
        this.items = items;
    }
}

