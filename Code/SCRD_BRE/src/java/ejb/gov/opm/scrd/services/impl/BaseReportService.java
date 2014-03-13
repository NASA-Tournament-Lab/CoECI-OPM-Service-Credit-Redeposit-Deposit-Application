/*
 * Copyright (C) 2013-2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.OPMConfigurationException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * <p>
 * This class is the base class for all concrete implementations of ReportService in the application.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Reporting Payment Module Assembly):</em>
 * <ol>
 * <li>Changed to resource injection</li>
 * </ol>
 * </p>
 * @author faeton, sparemax, TCSASSEMBLER
 * @version 1.1
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 */
public abstract class BaseReportService extends BaseService {
    /**
     * Represents the report name. It is accessible by getter. It can be any value. The default value is null.
     */
    @Resource
    private String reportName;

    /**
     * Creates an instance of BaseReportService.
     */
    protected BaseReportService() {
        // Empty
    }

    /**
     * Gets the report name.
     *
     * @return the report name.
     */
    protected String getReportName() {
        return reportName;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (entityManager is null; reportName is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();
        Helper.checkState((reportName == null) || (reportName.trim().length() == 0),
            "'reportName' can't be null/empty.");
    }
}
