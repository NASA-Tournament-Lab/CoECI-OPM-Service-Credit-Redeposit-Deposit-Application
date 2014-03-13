/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services;

/**
 * <p>
 * This exception is thrown when there is any problem when performing report generation.
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
public class ReportGenerationException extends OPMException {
    /**
     * <p>
     * The serial version id.
     * </p>
     */
    private static final long serialVersionUID = -7712129315681740543L;

    /**
     * <p>
     * Constructs a new <code>ReportGenerationException</code> instance.
     * </p>
     */
    public ReportGenerationException() {
        // empty
    }

    /**
     * <p>
     * Constructs a new <code>ReportGenerationException</code> instance with error message.
     * </p>
     *
     * @param message
     *            the error message.
     */
    public ReportGenerationException(String message) {
        super(message);
    }

    /**
     * <p>
     * Constructs a new <code>ReportGenerationException</code> instance with inner cause.
     * </p>
     *
     * @param cause
     *            the inner cause.
     */
    public ReportGenerationException(Throwable cause) {
        super(cause);
    }

    /**
     * <p>
     * Constructs a new <code>ReportGenerationException</code> instance with error message and inner cause.
     * </p>
     *
     * @param message
     *            the error message.
     * @param cause
     *            the inner cause.
     */
    public ReportGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
