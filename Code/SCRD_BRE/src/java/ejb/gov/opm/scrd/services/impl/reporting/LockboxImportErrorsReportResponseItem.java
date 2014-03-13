/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

/**
 * <p>
 * This class serves as the response item object that contains result details to be used to generate reports in
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
public class LockboxImportErrorsReportResponseItem {
    /**
     * Error code.
     */
    private String errorCode;

    /**
     * Error message.
     */
    private String errorMessage;

    /**
     * Description.
     */
    private String description;

    /**
     * Default constructor.
     */
    public LockboxImportErrorsReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>errorCode</code>.
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the field <code>errorCode</code>.
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the value of the field <code>errorMessage</code>.
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the field <code>errorMessage</code>.
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the value of the field <code>description</code>.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the field <code>description</code>.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
