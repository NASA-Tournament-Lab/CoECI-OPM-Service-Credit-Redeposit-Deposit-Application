/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services;

/**
 * <p>
 * This exception is thrown when there is any problem when performing report export.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Exception is not thread safe because its base class is not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 */
public class ReportExportException extends OPMException {
    /**
     * <p>
     * The serial version id.
     * </p>
     */
    private static final long serialVersionUID = 2776843622671717596L;

    /**
     * <p>
     * Constructs a new <code>ReportExportException</code> instance.
     * </p>
     */
    public ReportExportException() {
        // empty
    }

    /**
     * <p>
     * Constructs a new <code>ReportExportException</code> instance with error message.
     * </p>
     *
     * @param message
     *            the error message.
     */
    public ReportExportException(String message) {
        super(message);
    }

    /**
     * <p>
     * Constructs a new <code>ReportExportException</code> instance with inner cause.
     * </p>
     *
     * @param cause
     *            the inner cause.
     */
    public ReportExportException(Throwable cause) {
        super(cause);
    }

    /**
     * <p>
     * Constructs a new <code>ReportExportException</code> instance with error message and inner cause.
     * </p>
     *
     * @param message
     *            the error message.
     * @param cause
     *            the inner cause.
     */
    public ReportExportException(String message, Throwable cause) {
        super(message, cause);
    }
}
