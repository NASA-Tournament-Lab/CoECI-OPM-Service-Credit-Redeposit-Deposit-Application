/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * This class serves as the response object that contains result details to be used to generate reports in
 * {@link LockboxImportErrorsReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class LockboxImportErrorsReportResponse extends BaseReportResponse {
    /**
     * Batch Number.
     */
    private String batchNumber;

    /**
     * Import Date.
     */
    private Date importDate;

    /**
     * Details per row.
     */
    private List<LockboxImportErrorsReportResponseItem> items;

    /**
     * Default constructor.
     */
    public LockboxImportErrorsReportResponse() {
    }

    /**
     * Gets the value of the field <code>batchNumber</code>.
     * @return the batchNumber
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the value of the field <code>batchNumber</code>.
     * @param batchNumber the batchNumber to set
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the value of the field <code>importDate</code>.
     * @return the importDate
     */
    public Date getImportDate() {
        return importDate;
    }

    /**
     * Sets the value of the field <code>importDate</code>.
     * @param importDate the importDate to set
     */
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    /**
     * Gets the value of the field <code>items</code>.
     * @return the items
     */
    public List<LockboxImportErrorsReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the value of the field <code>items</code>.
     * @param items the items to set
     */
    public void setItems(List<LockboxImportErrorsReportResponseItem> items) {
        this.items = items;
    }
}
