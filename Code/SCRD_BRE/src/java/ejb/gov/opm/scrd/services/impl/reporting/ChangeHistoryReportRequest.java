/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.entities.application.ReportRequest;


/**
 * <p>
 * This class serves as the request object that would contain parameters
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
public class ChangeHistoryReportRequest implements ReportRequest {
    /**
     * Current account selected.
     */
    private Boolean currentAccountSelected;

    /**
     * CSD.
     */
    private String csd;

    /**
     * Default constructor.
     */
    public ChangeHistoryReportRequest() {
    }

    /**
     * Gets the value of the field <code>currentAccountSelected</code>.
     * @return the currentAccountSelected
     */
    public Boolean getCurrentAccountSelected() {
        return currentAccountSelected;
    }

    /**
     * Sets the value of the field <code>currentAccountSelected</code>.
     * @param currentAccountSelected the currentAccountSelected to set
     */
    public void setCurrentAccountSelected(Boolean currentAccountSelected) {
        this.currentAccountSelected = currentAccountSelected;
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
}

