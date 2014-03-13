/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.entities.application.ReportRequest;

/**
 * <p>
 * This class serves as the request object that would contain parameters (currently empty) to be used to generate
 * reports in {@link LockboxImportErrorsReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is immutable and thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class LockboxImportErrorsReportRequest implements ReportRequest {
    /**
     * Default constructor.
     */
    public LockboxImportErrorsReportRequest() {
    }
}
