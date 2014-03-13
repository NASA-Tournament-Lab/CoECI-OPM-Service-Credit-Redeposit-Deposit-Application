/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.DeductionRate;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;
import org.jboss.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the implementation of the ReportService which generates deduction rates report. It uses local data for
 * generating report and iText/iText RTF for generating reports. <p> <strong>Thread-safety:</strong> This class is
 * effectively thread - safe after configuration, the configuration is done in a thread - safe manner. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
@Stateless
@LocalBean
public class DeductionRatesReportService extends BaseReportService
        implements
        ReportService<DeductionRatesReportRequest, DeductionRatesReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = DeductionRatesReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link DeductionRatesReportService} class.
     */
    public DeductionRatesReportService() {
        super();
    }

    /**
     * Creates the report for the given request.
     *
     * @param request
     *         the request data to generate report.
     * @return ReportResponse instance containing the report data, can not be null.
     * @throws IllegalArgumentException
     *         if the request is null.
     * @throws ReportGenerationException
     *         if there is any problem when generating response.
     */
    public DeductionRatesReportResponse getReport(
            DeductionRatesReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(DeductionRatesReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");

        try {
            DeductionRatesReportResponse response = new DeductionRatesReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());

            List<DeductionRate> rates = getEntityManager().createQuery(
                    "SELECT d FROM DeductionRate d", DeductionRate.class)
                    .getResultList();

            Map<String, List<DeductionRatesReportResponseItem>> items = new HashMap<String, List<DeductionRatesReportResponseItem>>();
            for (DeductionRate rate : rates) {
                if (items.get(rate.getServiceType()) == null) {
                    items.put(rate.getServiceType(),
                            new ArrayList<DeductionRatesReportResponseItem>());
                }

                DeductionRatesReportResponseItem item = new DeductionRatesReportResponseItem();
                item.setRetirementType(rate.getRetirementType().getName());
                item.setStartDate(rate.getStartDate());
                item.setDays(rate.getDaysInPeriod());
                item.setRate(rate.getRate());
                item.setEndDate(rate.getEndDate());

                items.get(rate.getServiceType()).add(item);
            }

            response.setItems(items);
            LoggingHelper.logExit(logger, signature, new Object[]{ response });
            return response;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                    new ReportGenerationException(
                            "The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper
                    .logException(
                            logger,
                            signature,
                            new ReportGenerationException(
                                    "An error has occurred when accessing persistence.",
                                    e));
        }
    }

    /**
     * Exports the report for the given request.
     *
     * @param response
     *         the request data to generate report.
     * @param exportType
     *         the type of the report data to generate.
     * @return The byte array of contents of the exported report, can not be null.
     * @throws IllegalArgumentException
     *         if the request/exportType is null.
     * @throws ReportGenerationException
     *         if there is any problem when generating response.
     */
    public byte[] exportReport(DeductionRatesReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(DeductionRatesReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            if (exportType == ExportType.DOC || exportType == ExportType.RTF) {
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportDate(document,
                        response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT,
                        ReportServiceHelper.RTF_ALIGN_LEFT);
                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());

                if (response.getItems() != null) {
                    int[] alignments = new int[]{
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT };
                    int[] cellWidths = new int[]{ 30, 20, 20, 15, 15 };
                    DateFormat dateFormat = DateFormat
                            .getDateInstance(DateFormat.LONG);
                    int j = 1;
                    for (String jobName : response.getItems().keySet()) {
                        Table table = new Table(5);
                        table.setSpacing(1);
                        table.setWidths(cellWidths);
                        table.addCell(ReportServiceHelper.createTableCell(
                                "FERS",
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_LEFT, null,
                                1));

                        for (int i = 0; i < response.getItems().get(jobName)
                                .size(); i++) {
                            if (i == 0) {
                                table.addCell(ReportServiceHelper
                                        .createTableCell(
                                                jobName,
                                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                                null,
                                                ReportServiceHelper.RTF_ALIGN_LEFT,
                                                null, 4));
                                table.addCell(ReportServiceHelper
                                        .createEmptyCell(
                                                1,
                                                ReportServiceHelper.RTF_NO_BORDER));
                                ReportServiceHelper
                                        .addReportTableRow(
                                                table,
                                                new String[]{ "Start Date",
                                                        "End Date", "Days",
                                                        "Rate" },
                                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                                null,
                                                alignments,
                                                ReportServiceHelper.RTF_BORDER_BOTTOM);
                            }

                            DeductionRatesReportResponseItem item = response
                                    .getItems().get(jobName).get(i);
                            table.addCell(ReportServiceHelper.createEmptyCell(
                                    1, ReportServiceHelper.RTF_NO_BORDER));
                            String startDate = item.getStartDate() == null ? null
                                    : dateFormat.format(item.getStartDate());
                            String endDate = item.getEndDate() == null ? null
                                    : dateFormat.format(item.getEndDate());
                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new Object[]{
                                                    startDate,
                                                    endDate,
                                                    item.getDays(),
                                                    item.getRate() == null ? null
                                                            : item.getRate()
                                                            + "%" },
                                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                            null, alignments,
                                            ReportServiceHelper.RTF_NO_BORDER);
                        }
                        if (j == response.getItems().keySet().size()) {
                            table.setBorder(ReportServiceHelper.RTF_BORDER_BOTTOM);
                            table.setBorderWidth(2);
                        } else {
                            table.setBorder(ReportServiceHelper.RTF_NO_BORDER);
                        }
                        document.add(table);
                        j++;
                    }
                }
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportDate(document,
                        response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT,
                        ReportServiceHelper.PDF_ALIGN_LEFT);
                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());

                if (response.getItems() != null) {
                    int[] alignments = new int[]{
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT };
                    int[] cellWidths = new int[]{ 30, 20, 20, 15, 15 };
                    DateFormat dateFormat = DateFormat
                            .getDateInstance(DateFormat.LONG);
                    int j = 1;
                    for (String jobName : response.getItems().keySet()) {
                        PdfPTable table = new PdfPTable(5);
                        table.setWidths(cellWidths);
                        table.setSpacingBefore(20);
                        table.addCell(ReportServiceHelper.createTableCell(
                                "FERS",
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_LEFT, null,
                                1));

                        for (int i = 0; i < response.getItems().get(jobName)
                                .size(); i++) {
                            if (i == 0) {
                                table.addCell(ReportServiceHelper
                                        .createTableCell(
                                                jobName,
                                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                                null,
                                                ReportServiceHelper.PDF_ALIGN_LEFT,
                                                null, 4));
                                table.addCell(ReportServiceHelper
                                        .createEmptyPdfCell(
                                                1,
                                                ReportServiceHelper.PDF_NO_BORDER));
                                ReportServiceHelper
                                        .addReportTableRow(
                                                table,
                                                new String[]{ "Start Date",
                                                        "End Date", "Days",
                                                        "Rate" },
                                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                                null,
                                                alignments,
                                                ReportServiceHelper.PDF_BORDER_BOTTOM);
                            }

                            DeductionRatesReportResponseItem item = response
                                    .getItems().get(jobName).get(i);

                            int border = j == response.getItems().keySet()
                                    .size()
                                    && i == response.getItems().get(jobName)
                                    .size() - 1 ? ReportServiceHelper.PDF_BORDER_BOTTOM
                                    : ReportServiceHelper.PDF_NO_BORDER;
                            table.addCell(ReportServiceHelper
                                    .createEmptyPdfCell(1, border));
                            String startDate = item.getStartDate() == null ? null
                                    : dateFormat.format(item.getStartDate());
                            String endDate = item.getEndDate() == null ? null
                                    : dateFormat.format(item.getEndDate());
                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new Object[]{
                                                    startDate,
                                                    endDate,
                                                    item.getDays(),
                                                    item.getRate() == null ? null
                                                            : item.getRate()
                                                            + "%" },
                                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                            null, alignments, border);
                        }
                        document.add(table);
                        j++;
                    }
                }
                document.close();
            }
            LoggingHelper.logExit(getLogger(), signature, null);
            return outputStream.toByteArray();
        } catch (com.lowagie.text.DocumentException ex) {
            throw LoggingHelper.logException(getLogger(), signature,
                    new ReportGenerationException(
                            "Error occurred while exporting the report.", ex));
        } catch (com.itextpdf.text.DocumentException ex) {
            throw LoggingHelper.logException(getLogger(), signature,
                    new ReportGenerationException(
                            "Error occurred while exporting the report.", ex));
        }
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(DeductionRatesReportResponse response) throws ReportGenerationException {
        return null;
    }
}
