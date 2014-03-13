/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services;

/**
 * <p>
 * Represents the enum specifying the possible export type of the reports.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This enumeration is immutable and thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 */
public enum ExportType {
    /**
     * Represents PDF export type.
     */
    PDF,

    /**
     * Represents RTF export type.
     */
    RTF,

    /**
     * Represents DOC export type.
     */
    DOC
}
