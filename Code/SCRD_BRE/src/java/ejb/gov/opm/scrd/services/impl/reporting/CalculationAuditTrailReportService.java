/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.CalculationResultItem;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.CalculationResultItemUpdateStatus;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;
import org.jboss.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates calculation audit trail report. It uses local
 * data for generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This class
 * is effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
@Stateless
@LocalBean
public class CalculationAuditTrailReportService extends BaseReportService
        implements
        ReportService<CalculationAuditTrailReportRequest, CalculationAuditTrailReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = CalculationAuditTrailReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link CalculationAuditTrailReportService} class.
     */
    public CalculationAuditTrailReportService() {
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
    public CalculationAuditTrailReportResponse getReport(
            CalculationAuditTrailReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(CalculationAuditTrailReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            CalculationAuditTrailReportResponse response = new CalculationAuditTrailReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setGroups(new ArrayList<CalculationAuditGroup>());

            Account account = getEntityManager()
                    .createQuery(
                            "SELECT a FROM Account a WHERE a.claimNumber = :csd",
                            Account.class)
                    .setParameter("csd", request.getCsd()).getSingleResult();
            response.setCsd(request.getCsd());
       
            List<CalculationVersion> allVersions = account.getCalculationVersions();

            for (CalculationVersion version : allVersions) {
                CalculationAuditGroup group = new CalculationAuditGroup();
                group.setCalculationDate(version.getCalculationDate());
                group.setAction(version.getName());
                group.setEffective(version.getCalculationDate());
                group.setItems(new ArrayList<CalculationAuditItem>());

                if (version.getCalculationResult() == null) {
                    continue;
                }
                for (CalculationResultItem calculation : version.getCalculationResult().getItems()) {
                    CalculationAuditItem item = new CalculationAuditItem();
                    item.setOfficial(version.getCalculationResult()
                            .isOfficial());
                    item.setStartDate(calculation.getStartDate());
                    item.setPeriod(calculation.getPeriodType().getName());
                    item.setEndDate(calculation.getEndDate());
                    item.setDeduction(calculation.getDeductionAmount());
                    item.setInterest(calculation.getTotalInterest());
                    item.setPayments(calculation.getPaymentsApplied());
                    item.setLine(calculation.getLine());
                    item.setVersion(calculation.getVersion());
                    item.setInserted(calculation.getStatus() == CalculationResultItemUpdateStatus.INSERTED);
                    item.setUpdated(calculation.getStatus() == CalculationResultItemUpdateStatus.UPDATED);
                    item.setDeleted(calculation.getStatus() == CalculationResultItemUpdateStatus.DELETED);
                    group.getItems().add(item);
                }

                response.getGroups().add(group);
            }

            LoggingHelper.logExit(logger, signature, new Object[]{ response });
            return response;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                    new ReportGenerationException(
                            "The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            e.printStackTrace();
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
    public byte[] exportReport(CalculationAuditTrailReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(CalculationAuditTrailReportResponse response)";
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

                if (!Helper.isNullOrEmpty(response.getReportName())) {
                    ReportServiceHelper.addReportTitle(
                            document,
                            response.getReportName() + " CSD #"
                                    + response.getCsd());
                }
                ReportServiceHelper.addReportDate(document,
                        response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT,
                        ReportServiceHelper.RTF_ALIGN_LEFT);

                DateFormat longDateFormat = DateFormat.getDateTimeInstance(
                        DateFormat.LONG, DateFormat.MEDIUM);
                String[] headColumns = new String[]{ "Offical", "Version",
                        "Line", "Period", "Start Date", "End Date",
                        "Deduction", "Interest", "Payments" };
                int[] alignments = new int[]{
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT };
                int[] cellWidths = new int[]{ 7, 7, 5, 11, 10, 15, 15, 15, 15 };
                if (response.getGroups() != null) {
                    for (CalculationAuditGroup group : response.getGroups()) {
                        Table table = new Table(9);
                        table.setWidth(100);
                        table.setWidths(cellWidths);
                        table.setSpacing(5);
                        table.setBorder(ReportServiceHelper.RTF_NO_BORDER);
                        String calc = response.getReportGenerationDate() == null ? null
                                : "Calc as of "
                                + ReportServiceHelper.REPORT_DATE_FORMAT
                                .format(group
                                        .getCalculationDate())
                                + "  " + group.getAction();
                        table.addCell(ReportServiceHelper.createTableCell(calc,
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_RIGHT,
                                ReportServiceHelper.RTF_NO_BORDER, 6));
                        table.addCell(ReportServiceHelper.createTableCell(
                                group.getEffective() == null ? null
                                        : longDateFormat.format(group
                                        .getEffective()),
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_RIGHT,
                                ReportServiceHelper.RTF_NO_BORDER, 3));
                        ReportServiceHelper.addReportTableRow(table,
                                headColumns,
                                ReportServiceHelper.RTF_REPORT_UNDERLINE_FONT,
                                null, alignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                        if (group.getItems() != null) {
                            for (CalculationAuditItem item : group.getItems()) {
                                ReportServiceHelper
                                        .addReportTableRow(
                                                table,
                                                new Object[]{
                                                        item.isOfficial() ? "X"
                                                                : "",
                                                        item.getVersion() },
                                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                                null,
                                                alignments,
                                                ReportServiceHelper.RTF_NO_BORDER);

                                Color bgColor;
                                if (item.isDeleted()) {
                                    bgColor = Color.RED;
                                } else if (item.isUpdated()) {
                                    bgColor = Color.YELLOW;
                                } else {
                                    bgColor = Color.GREEN;
                                }
                                table.addCell(ReportServiceHelper.createTableCell(
                                        item.getLine(),
                                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                        bgColor,
                                        ReportServiceHelper.RTF_ALIGN_LEFT,
                                        ReportServiceHelper.RTF_NO_BORDER, 1));

                                ReportServiceHelper
                                        .addReportTableRow(
                                                table,
                                                new Object[]{
                                                        item.getPeriod(),
                                                        ReportServiceHelper.REPORT_DATE_FORMAT.format(item
                                                                .getStartDate()),
                                                        ReportServiceHelper.REPORT_DATE_FORMAT.format(item
                                                                .getEndDate()),
                                                        Helper.getMoney(item
                                                                .getDeduction()),
                                                        Helper.getMoney(item
                                                                .getInterest()),
                                                        Helper.getMoney(item
                                                                .getPayments()) },
                                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                                null,
                                                new int[]{
                                                        ReportServiceHelper.RTF_ALIGN_LEFT,
                                                        ReportServiceHelper.RTF_ALIGN_LEFT,
                                                        ReportServiceHelper.RTF_ALIGN_LEFT,
                                                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                                                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                                                        ReportServiceHelper.RTF_ALIGN_RIGHT },
                                                ReportServiceHelper.RTF_NO_BORDER);
                            }
                        }
                        document.add(table);
                    }
                }

                Table legendTable = new Table(1);
                legendTable.addCell(ReportServiceHelper.createTableCell(
                        "Items highlighted in Green were inserted",
                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                        Color.GREEN, ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_NO_BORDER, 0));
                legendTable.addCell(ReportServiceHelper.createTableCell(
                        "Items highlighted in Yellow were updated",
                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                        Color.YELLOW, ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_NO_BORDER, 0));
                legendTable.addCell(ReportServiceHelper.createTableCell(
                        "Items highlighted in Red were deleted",
                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT, Color.RED,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_NO_BORDER, 0));
                document.add(legendTable);
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                if (!Helper.isNullOrEmpty(response.getReportName())) {
                    ReportServiceHelper.addReportTitle(
                            document,
                            response.getReportName() + " CSD #"
                                    + response.getCsd());
                }
                ReportServiceHelper.addReportDate(document,
                        response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT,
                        ReportServiceHelper.PDF_ALIGN_LEFT);

                DateFormat longDateFormat = DateFormat.getDateTimeInstance(
                        DateFormat.LONG, DateFormat.MEDIUM);
                String[] headColumns = new String[]{ "Offical", "Version",
                        "Line", "Period", "Start Date", "End Date",
                        "Deduction", "Interest", "Payments" };
                int[] alignments = new int[]{
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT };
                int[] cellWidths = new int[]{ 7, 7, 5, 11, 10, 15, 15, 15, 15 };

                if (response.getGroups() != null) {
                    for (CalculationAuditGroup group : response.getGroups()) {
                        PdfPTable table = new PdfPTable(9);
                        table.setWidthPercentage(100);
                        table.setWidths(cellWidths);
                        String calc = group.getCalculationDate() == null ? null
                                : "Calc as of "
                                + ReportServiceHelper.REPORT_DATE_FORMAT
                                .format(group
                                        .getCalculationDate())
                                + "  " + group.getAction();
                        table.addCell(ReportServiceHelper.createTableCell(calc,
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_RIGHT,
                                ReportServiceHelper.PDF_NO_BORDER, 6));
                        table.addCell(ReportServiceHelper.createTableCell(
                                longDateFormat.format(group.getEffective()),
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_RIGHT,
                                ReportServiceHelper.PDF_NO_BORDER, 3));
                        ReportServiceHelper.addReportTableRow(table,
                                headColumns,
                                ReportServiceHelper.PDF_REPORT_UNDERLINE_FONT,
                                null, alignments,
                                ReportServiceHelper.PDF_NO_BORDER);
                        for (CalculationAuditItem item : group.getItems()) {
                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new Object[]{
                                                    item.isOfficial() ? "X"
                                                            : "",
                                                    item.getVersion() },
                                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                            null, alignments,
                                            ReportServiceHelper.PDF_NO_BORDER);

                            BaseColor bgColor;
                            if (item.isDeleted()) {
                                bgColor = BaseColor.RED;
                            } else if (item.isUpdated()) {
                                bgColor = BaseColor.YELLOW;
                            } else {
                                bgColor = BaseColor.GREEN;
                            }
                            table.addCell(ReportServiceHelper.createTableCell(
                                    item.getLine(),
                                    ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                    bgColor,
                                    ReportServiceHelper.PDF_ALIGN_LEFT,
                                    ReportServiceHelper.PDF_NO_BORDER, 1));

                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new Object[]{
                                                    item.getPeriod(),
                                                    ReportServiceHelper.REPORT_DATE_FORMAT.format(item
                                                            .getStartDate()),
                                                    ReportServiceHelper.REPORT_DATE_FORMAT.format(item
                                                            .getEndDate()),
                                                    Helper.getMoney(item
                                                            .getDeduction()),
                                                    Helper.getMoney(item
                                                            .getInterest()),
                                                    Helper.getMoney(item
                                                            .getPayments()) },
                                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                            null,
                                            new int[]{
                                                    ReportServiceHelper.PDF_ALIGN_LEFT,
                                                    ReportServiceHelper.PDF_ALIGN_LEFT,
                                                    ReportServiceHelper.PDF_ALIGN_LEFT,
                                                    ReportServiceHelper.PDF_ALIGN_RIGHT,
                                                    ReportServiceHelper.PDF_ALIGN_RIGHT,
                                                    ReportServiceHelper.PDF_ALIGN_RIGHT },
                                            ReportServiceHelper.PDF_NO_BORDER);
                        }
                        document.add(table);
                    }
                }

                PdfPTable legendTable = new PdfPTable(1);
                legendTable.setSpacingBefore(30);
                legendTable.addCell(createLegendCell(
                        "Items highlighted in Green were inserted",
                        BaseColor.GREEN));
                legendTable.addCell(createLegendCell(
                        "Items highlighted in Yellow were updated",
                        BaseColor.YELLOW));
                legendTable
                        .addCell(createLegendCell(
                                "Items highlighted in Red were deleted",
                                BaseColor.RED));
                document.add(legendTable);
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
     * Creates a PDF cell for the legend table.
     *
     * @param content
     *         the content text.
     * @param bgColor
     *         the background color to use.
     * @return the created PDF cell.
     */
    private static PdfPCell createLegendCell(String content, BaseColor bgColor) {
        PdfPCell cell = ReportServiceHelper.createTableCell(content,
                ReportServiceHelper.PDF_REPORT_CONTENT_FONT, bgColor,
                ReportServiceHelper.PDF_ALIGN_LEFT,
                ReportServiceHelper.PDF_NO_BORDER, 0);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
        return cell;
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(CalculationAuditTrailReportResponse response) throws ReportGenerationException {
        return null;
    }
}
