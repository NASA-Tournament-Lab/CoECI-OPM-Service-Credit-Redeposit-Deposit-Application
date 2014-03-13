/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.AuditBatchLogId;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
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
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * This class is the implementation of the ReportService which generates daily batch processing report. It uses local
 * data for generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This class
 * is effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
@Stateless
@LocalBean
public class DailyBatchProcessingReportService extends BaseReportService implements
        ReportService<DailyBatchProcessingReportRequest, DailyBatchProcessingReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = DailyBatchProcessingReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link DailyBatchProcessingReportService} class.
     */
    public DailyBatchProcessingReportService() {
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
    public DailyBatchProcessingReportResponse getReport(DailyBatchProcessingReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(DailyBatchProcessingReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            DailyBatchProcessingReportResponse response = new DailyBatchProcessingReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setItems(new ArrayList<DailyBatchProcessingReportDayItem>());

            List<Object[]> result = ReportServiceHelper.getDailyReportData(getEntityManager());
            HashMap<Date, DailyBatchProcessingReportDayItem> dayItems = new
                    HashMap<Date, DailyBatchProcessingReportDayItem>();
            for (Object[] objects : result) {
                AuditBatchLogId auditBatchLogId = (AuditBatchLogId) objects[0];
                InvoiceData invoiceData = (InvoiceData) objects[3];

                DailyBatchProcessingReportItem item = new DailyBatchProcessingReportItem();
                item.setBatchDate(auditBatchLogId.getBatchDate());
                item.setTransactionDate(invoiceData.getPayTransTransactionDate());
                item.setTransactionStatus(invoiceData.getPayTransStatusDescription());
                item.setPay(invoiceData.getNumberPaymentsToday());
                item.setTotal(invoiceData.getTodaysPaymentTotal());
                item.setDescription(invoiceData.getAccountStatusDescription());
                item.setTransactionStatus(invoiceData.getPayTransStatusDescription());

                if (dayItems.get(item.getBatchDate()) == null) {
                    DailyBatchProcessingReportDayItem dayItem = new DailyBatchProcessingReportDayItem();
                    dayItem.setDate(item.getBatchDate());
                    dayItem.setItems(new ArrayList<DailyBatchProcessingReportItem>());
                    dayItem.getItems().add(item);
                    dayItems.put(item.getBatchDate(), dayItem);
                } else {
                    dayItems.get(item.getBatchDate()).getItems().add(item);
                }
            }

            BigDecimal grandTotal = new BigDecimal("0");
            int grandPay = 0;
            for (Date date : dayItems.keySet()) {
                DailyBatchProcessingReportDayItem dayItem = dayItems.get(date);
                BigDecimal itemGrandTotal = new BigDecimal("0");
                int itemGrandPay = 0;
                for (DailyBatchProcessingReportItem item : dayItem.getItems()) {
                    itemGrandPay += item.getPay();
                    itemGrandTotal = itemGrandTotal.add(item.getTotal());
                }
                grandTotal = grandTotal.add(itemGrandTotal);
                grandPay += itemGrandPay;
                dayItem.setGrandPay(itemGrandPay);
                dayItem.setGrandTotal(itemGrandTotal);
                response.getItems().add(dayItem);
            }
            response.setGrandTotal(grandTotal);
            response.setGrandPay(grandPay);

            LoggingHelper.logExit(logger, signature, new Object[]{ response });
            return response;
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
    public byte[] exportReport(DailyBatchProcessingReportResponse response, ExportType exportType)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(DailyBatchProcessingReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            if (exportType == ExportType.RTF || exportType == ExportType.DOC) {
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportDate(document, response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT, ReportServiceHelper.RTF_ALIGN_LEFT);
                ReportServiceHelper.addReportTitle(document, response.getReportName());

                if (response.getItems() != null) {
                    int[] cellWidths = new int[]{ 12, 13, 25, 5, 15, 10, 20 };
                    int[] alignments = new int[]{
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT
                    };
                    for (DailyBatchProcessingReportDayItem item : response.getItems()) {
                        addItem(document, item, cellWidths, alignments);
                    }
                    Table totalTable = new Table(2);
                    totalTable.setWidths(new int[]{ 30, 70 });
                    totalTable.addCell(ReportServiceHelper.createEmptyCell(1, ReportServiceHelper.RTF_NO_BORDER));
                    totalTable.addCell(ReportServiceHelper.createTableCell("Grand Total: " + NumberFormat
                            .getIntegerInstance(Locale.ENGLISH).format(response.getGrandPay()) + " (" +
                            Helper.getMoney(response.getGrandRefund()) + ") " +
                            Helper.getMoney(response.getGrandTotal()),
                            ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                            ReportServiceHelper.RTF_ALIGN_RIGHT, ReportServiceHelper.RTF_BORDER_BOTTOM, 1));

                    document.add(totalTable);
                    document.close();
                }
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportDate(document, response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT, ReportServiceHelper.PDF_ALIGN_LEFT);
                ReportServiceHelper.addReportTitle(document, response.getReportName());

                if (response.getItems() != null) {
                    int[] cellWidths = new int[]{ 12, 13, 25, 5, 15, 10, 20 };
                    int[] alignments = new int[]{
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT
                    };
                    for (DailyBatchProcessingReportDayItem item : response.getItems()) {
                        addItem(document, item, cellWidths, alignments);
                    }
                    PdfPTable totalTable = new PdfPTable(2);
                    totalTable.setSpacingBefore(20);
                    totalTable.setWidths(new int[]{ 30, 70 });
                    totalTable.addCell(ReportServiceHelper.createEmptyPdfCell(1, ReportServiceHelper.PDF_NO_BORDER));
                    totalTable.addCell(ReportServiceHelper.createTableCell("Grand Total: " + NumberFormat
                            .getIntegerInstance(Locale.ENGLISH).format(response.getGrandPay()) + " (" +
                            Helper.getMoney(response.getGrandRefund()) + ") " +
                            Helper.getMoney(response.getGrandTotal()),
                            ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                            ReportServiceHelper.PDF_ALIGN_RIGHT, ReportServiceHelper.PDF_BORDER_BOTTOM, 1));

                    document.add(totalTable);
                    document.close();
                }
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
     * Adds item to the document.
     *
     * @param document
     *         the document.
     * @param item
     *         the DailyBatchProcessingReportDayItem.
     * @param cellWidths
     *         the array of cell widths.
     * @param alignments
     *         the array of alignments.
     * @throws com.lowagie.text.DocumentException
     *         if any error occurs.
     */
    private static void addItem(com.lowagie.text.Document document, DailyBatchProcessingReportDayItem item,
                                int[] cellWidths, int[] alignments)
            throws com.lowagie.text.DocumentException {
        Table table = new Table(7);
        table.setWidths(cellWidths);
        String date = item.getDate() == null ? null : ReportServiceHelper.REPORT_DATE_FORMAT.format(
                item.getDate());
        if (date != null) {
            table.addCell(ReportServiceHelper.createTableCell(ReportServiceHelper.REPORT_DATE_FORMAT.format(
                    item.getDate()), ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null,
                    ReportServiceHelper.RTF_ALIGN_LEFT, ReportServiceHelper.RTF_BORDER_BOTTOM, 1));
            table.addCell(ReportServiceHelper.createEmptyCell(6, ReportServiceHelper.RTF_NO_BORDER));
        }
        if (item.getItems() != null) {
            ReportServiceHelper.addReportTableRow(table, new String[]{
                    "Batch Date", "Transaction", "Transaction Status", "# Pay", "Total", "Refund",
                    "Description" }, ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                    null, alignments,
                    ReportServiceHelper.RTF_NO_BORDER);
            for (DailyBatchProcessingReportItem reportItem : item.getItems()) {
                String batchDate = reportItem.getBatchDate() == null ? null :
                        ReportServiceHelper.REPORT_DATE_FORMAT.format(reportItem.getBatchDate());
                String transactionDate = reportItem.getTransactionDate() == null ? null :
                        ReportServiceHelper.REPORT_DATE_FORMAT.format(reportItem.getTransactionDate());
                ReportServiceHelper.addReportTableRow(table, new Object[]{
                        batchDate, transactionDate, reportItem.getTransactionStatus(), reportItem.getPay(),
                        reportItem.getTotal(), reportItem.getRefund(), reportItem.getDescription() },
                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null, alignments,
                        ReportServiceHelper.RTF_NO_BORDER);
            }
            table.addCell(ReportServiceHelper.createTableCell(date, ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                    null, ReportServiceHelper.RTF_ALIGN_LEFT, ReportServiceHelper.RTF_NO_BORDER, 3));
            ReportServiceHelper.addReportTableRow(table, new Object[]{ item.getGrandPay(), item.getGrandTotal(),
                    item.getGrandRefund(), item.getGrandTotal().subtract(item.getGrandRefund()) },
                    ReportServiceHelper.RTF_REPORT_HEADER_FONT, null, new int[]{ alignments[3], alignments[4],
                    alignments[5], alignments[6] }, ReportServiceHelper.RTF_BORDER_BOTTOM |
                    ReportServiceHelper.RTF_BORDER_TOP);
        }
        document.add(table);
    }

    /**
     * Adds item to the document.
     *
     * @param document
     *         the document.
     * @param item
     *         the DailyBatchProcessingReportDayItem.
     * @param cellWidths
     *         the array of cell widths.
     * @param alignments
     *         the array of alignments.
     * @throws com.itextpdf.text.DocumentException
     *         if any error occurs.
     */
    private static void addItem(com.itextpdf.text.Document document, DailyBatchProcessingReportDayItem item,
                                int[] cellWidths, int[] alignments)
            throws com.itextpdf.text.DocumentException {
        PdfPTable table = new PdfPTable(7);
        table.setSpacingBefore(20);
        table.setWidths(cellWidths);
        String date = item.getDate() == null ? null : ReportServiceHelper.REPORT_DATE_FORMAT.format(
                item.getDate());
        if (date != null) {
            table.addCell(ReportServiceHelper.createTableCell(ReportServiceHelper.REPORT_DATE_FORMAT.format(
                    item.getDate()), ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null,
                    ReportServiceHelper.PDF_ALIGN_LEFT, ReportServiceHelper.RTF_BORDER_BOTTOM, 1));
            table.addCell(ReportServiceHelper.createEmptyPdfCell(6, ReportServiceHelper.RTF_NO_BORDER));
        }
        if (item.getItems() != null) {
            ReportServiceHelper.addReportTableRow(table, new String[]{
                    "Batch Date", "Transaction", "Transaction Status", "#Pay", "Total", "Refund",
                    "Description" }, ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                    null, alignments,
                    ReportServiceHelper.PDF_NO_BORDER);
            for (DailyBatchProcessingReportItem reportItem : item.getItems()) {
                String batchDate = reportItem.getBatchDate() == null ? null :
                        ReportServiceHelper.REPORT_DATE_FORMAT.format(reportItem.getBatchDate());
                String transactionDate = reportItem.getTransactionDate() == null ? null :
                        ReportServiceHelper.REPORT_DATE_FORMAT.format(reportItem.getTransactionDate());
                ReportServiceHelper.addReportTableRow(table, new Object[]{
                        batchDate, transactionDate, reportItem.getTransactionStatus(), reportItem.getPay(),
                        reportItem.getTotal(), reportItem.getRefund(), reportItem.getDescription() },
                        ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null, alignments,
                        ReportServiceHelper.PDF_NO_BORDER);
            }
            table.addCell(ReportServiceHelper.createTableCell(date, ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                    null, ReportServiceHelper.PDF_ALIGN_LEFT, ReportServiceHelper.PDF_NO_BORDER, 3));
            ReportServiceHelper.addReportTableRow(table, new Object[]{ item.getGrandPay(), item.getGrandTotal(),
                    item.getGrandRefund(), item.getGrandTotal().subtract(item.getGrandRefund()) },
                    ReportServiceHelper.PDF_REPORT_HEADER_FONT, null, new int[]{ alignments[3], alignments[4],
                    alignments[5], alignments[6] }, ReportServiceHelper.PDF_BORDER_BOTTOM |
                    ReportServiceHelper.PDF_BORDER_TOP);
        }
        document.add(table);
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(DailyBatchProcessingReportResponse response) throws ReportGenerationException {
        return null;
    }
}
