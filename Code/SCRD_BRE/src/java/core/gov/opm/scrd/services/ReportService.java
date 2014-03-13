/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services;

import gov.opm.scrd.entities.application.ReportRequest;
import gov.opm.scrd.entities.application.ReportResponse;

/**
 * <p>
 * This interface defines a contract for performing the report management in the application. Basically, the report data
 * can be obtained from the application data located in various sources given the request data. The request data is
 * encapsulated in a special request object that extends marker ReportRequest interface. The report will be contained a
 * special response object that extends marker ReportResponse interface. This object containing the report can then be
 * exported to some output type specified in ExportType enum.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> The implementations are required to be effectively thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 *
 * @param <S>
 *            the request type
 * @param <T>
 *            the response type
 */
public interface ReportService<S extends ReportRequest, T extends ReportResponse> {
    /**
     * Creates the report for the given request. The concrete report and format is up to the implementation. The report
     * is encapsulated in the ReportResponse object.
     *
     * @param request
     *            the request data to generate report.
     *
     * @return ReportResponse instance containing the report data, can not be null.
     *
     * @throws IllegalArgumentException
     *             if the request is null.
     * @throws ReportGenerationException
     *             if there is any problem when generating response.
     */
    public T getReport(S request) throws ReportGenerationException;

    /**
     * Exports the report for the given response. The concrete report and format is up to the implementation. The
     * contents of the exported report are returned from this method.
     *
     * @param response
     *            the response object containing report data to generate report.
     * @param exportType
     *            the type of the report data to generate.
     *
     * @return The byte array of contents of the exported report, can not be null.
     *
     * @throws IllegalArgumentException
     *             if response or exportType is null.
     * @throws ReportGenerationException
     *             if there is any problem when generating contents of the report.
     */
    public byte[] exportReport(T response, ExportType exportType) throws ReportGenerationException;
    
    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    public byte[] renderChart(T response) throws ReportGenerationException;
}
