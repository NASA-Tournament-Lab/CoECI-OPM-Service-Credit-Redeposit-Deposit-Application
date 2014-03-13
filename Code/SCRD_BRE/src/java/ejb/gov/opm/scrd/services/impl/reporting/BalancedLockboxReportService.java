/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.LockboxPaymentType;
import gov.opm.scrd.entities.batchprocessing.MainframeImport;
import gov.opm.scrd.entities.batchprocessing.MainframeRecordType;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.batchprocessing.PaymentTransactionCodes;
import gov.opm.scrd.entities.common.Helper;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates balanced lockbox report. It uses local data for
 * generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This class is
 * effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * Changes:
 *   v1.2 changes the compare of the PaymentTransactionCodes
 * @author AleaActaEst, RaitoShum
 * @version 1.2
 */
@Stateless
@LocalBean
public class BalancedLockboxReportService extends BaseReportService implements
        ReportService<BalancedLockboxReportRequest, BalancedLockboxReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = BalancedLockboxReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link BalancedLockboxReportService} class.
     */
    public BalancedLockboxReportService() {
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
    @SuppressWarnings("unchecked")
    public BalancedLockboxReportResponse getReport(BalancedLockboxReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(BalancedLockboxReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            BalancedLockboxReportResponse response = new BalancedLockboxReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            ReportServiceHelper.setScorecardQuarterDate(response);

            String queryStr = "SELECT a, m, p FROM AuditBatchLogId a, MainframeImport m, " +
                    "PaymentTransaction p WHERE m.auditBatchLogId = a.auditBatchLogId " +
                    "AND m.payTransactionKey = p.payTransactionKey";
            List<Object[]> result = getEntityManager().createQuery(queryStr).getResultList();
            response.setChangeRecordsItems(new ArrayList<BalancedLockboxReportResponseItem>());
            response.setAchImportedItems(new ArrayList<BalancedLockboxReportResponseItem>());
            response.setAchReversedItems(new ArrayList<BalancedLockboxReportResponseItem>());
            response.setChecksImportedItems(new ArrayList<BalancedLockboxReportResponseItem>());
            response.setDebitVouchersItems(new ArrayList<BalancedLockboxReportResponseItem>());

            HashMap<String, BalancedLockboxReportResponseItem> changeRecordItems = new HashMap<String,
                    BalancedLockboxReportResponseItem>();
            HashMap<String, BalancedLockboxReportResponseItem> achItems = new
                    HashMap<String, BalancedLockboxReportResponseItem>();
            HashMap<String, BalancedLockboxReportResponseItem> checkItems = new HashMap<String,
                    BalancedLockboxReportResponseItem>();
            HashMap<String, BalancedLockboxReportResponseItem> reversedItems = new HashMap<String,
                    BalancedLockboxReportResponseItem>();
            HashMap<String, BalancedLockboxReportResponseItem> debitVouchersItems = new HashMap<String,
                    BalancedLockboxReportResponseItem>();
            int totalImported = 0;
            int totalReversed = 0;
            for (Object[] objects : result) {
                MainframeImport mainframeImport = (MainframeImport) objects[1];
                PaymentTransaction paymentTransaction = (PaymentTransaction) objects[2];
                if (mainframeImport.getRecordString().equals(MainframeRecordType.VALID_C_TRANSACTION.toString())) {
                    setItem(changeRecordItems, "Change Completed", paymentTransaction);
                    totalImported++;
                } else if (mainframeImport.getRecordString().equals(
                        MainframeRecordType.BAD_C_TRANSACTION.toString())) {
                    setItem(changeRecordItems, "Change Error", paymentTransaction);
                    totalImported++;
                }
                if (mainframeImport.getPaymentType() == LockboxPaymentType.ACH) {
                    if (PaymentTransactionCodes.POSTED_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()
                            || PaymentTransactionCodes.POSTED_PENDING.getCode() == paymentTransaction
                                    .getPaymentStatusCode()) {
                        setItem(achItems, "Posted", paymentTransaction);
                    } else if (PaymentTransactionCodes.SUSPENDED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        setItem(achItems, "Suspended", paymentTransaction);
                    } else if (PaymentTransactionCodes.UNRESOLVED.getCode() == paymentTransaction
                            .getPaymentStatusCode()) {
                        setItem(achItems, "Unresolved", paymentTransaction);
                    } else if (PaymentTransactionCodes.REVERSE_COMPLETE.getCode() == paymentTransaction
                            .getPaymentStatusCode()) {
                        setItem(reversedItems, "ACH Reversed", paymentTransaction);
                    }
                } else if (mainframeImport.getPaymentType() == LockboxPaymentType.CHECK) {
                    if (PaymentTransactionCodes.POSTED_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()
                            || PaymentTransactionCodes.POSTED_PENDING.getCode() == paymentTransaction
                                    .getPaymentStatusCode()) {
                        setItem(checkItems, "Posted", paymentTransaction);
                    } else if (PaymentTransactionCodes.SUSPENDED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        setItem(checkItems, "Suspended", paymentTransaction);
                    } else if (PaymentTransactionCodes.UNRESOLVED.getCode() == paymentTransaction
                            .getPaymentStatusCode()) {
                        setItem(checkItems, "Unresolved", paymentTransaction);
                    }
                }
                if (PaymentTransactionCodes.SUSPENSE_DEBIT_VOUCHER.getCode() == paymentTransaction
                        .getPaymentStatusCode()) {
                    setItem(debitVouchersItems, "Debit Vouchers", paymentTransaction);
                }
                if (PaymentTransactionCodes.REVERSE_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                    totalReversed++;
                }
            }

            for (String key : changeRecordItems.keySet()) {
                response.getChangeRecordsItems().add(changeRecordItems.get(key));
            }
            for (String key : achItems.keySet()) {
                response.getAchImportedItems().add(achItems.get(key));
            }
            for (String key : checkItems.keySet()) {
                response.getChecksImportedItems().add(checkItems.get(key));
            }
            for (String key : reversedItems.keySet()) {
                response.getAchReversedItems().add(reversedItems.get(key));
            }

            response.setTotalImported(totalImported);
            response.setTotalReversed(totalReversed);

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
    public byte[] exportReport(BalancedLockboxReportResponse response, ExportType exportType)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(BalancedLockboxReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int doubleColumnsWidth = 50;
            int[] doubleColumnsWidths = new int[]{ 80, 20 };
            int tripleColumnsWidth = 75;
            int[] tripleColumnsWidths = new int[]{ 50, 20, 30 };
            if (exportType == ExportType.RTF || exportType == ExportType.DOC) {
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addBalancedReportTitle(document, response.getReportName(),
                        response.getFiscalYear(), response.getQuarter(), response.getStartDate(),
                        response.getEndDate());
                int[] alignments = new int[]{
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT
                };

                Object[] changeRecordTotals = addItems(document, response.getChangeRecordsItems(), doubleColumnsWidth,
                        doubleColumnsWidths, alignments, 2, "Change Records Imported from Lockbox");
                addTotal(document, changeRecordTotals, doubleColumnsWidth, doubleColumnsWidths, alignments, 2, true);
                Object[] achImportedTotals = addItems(document, response.getAchImportedItems(), tripleColumnsWidth,
                        tripleColumnsWidths, alignments, 3, "ACHs Imported from Lockbox");
                Object[] checkImportedTotals = 
                        addItems(document, response.getChecksImportedItems(), tripleColumnsWidth, 
                                tripleColumnsWidths, alignments, 3, "Checks Imported from Lockbox");
                Object[] importedTotals = new Object[]{
                        response.getTotalImported(),
                        ((BigDecimal) achImportedTotals[1]).add((BigDecimal) checkImportedTotals[1])
                };
                addTotal(document, importedTotals, tripleColumnsWidth, tripleColumnsWidths, alignments, 3, true);
                Object[] reversedTotals = addItems(document, response.getAchReversedItems(), tripleColumnsWidth,
                        tripleColumnsWidths, alignments, 3, "ACHs Reversed from Lockbox");
                addTotal(document, new Object[]{ response.getTotalReversed(), reversedTotals[1] }, tripleColumnsWidth,
                        tripleColumnsWidths, alignments, 3, false);
                addItems(document, response.getDebitVouchersItems(), tripleColumnsWidth,
                        tripleColumnsWidths, alignments, 3, "Debit Vouchers from Lockbox");

                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addBalancedReportTitle(document, response.getReportName(),
                        response.getFiscalYear(), response.getQuarter(), response.getStartDate(),
                        response.getEndDate());
                int[] alignments = new int[]{
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT
                };

                Object[] changeRecordTotals = addItems(document, response.getChangeRecordsItems(), doubleColumnsWidth,
                        doubleColumnsWidths, alignments, 2, "Change Records Imported from Lockbox");
                addTotal(document, changeRecordTotals, doubleColumnsWidth, doubleColumnsWidths, alignments, 2, true);
                Object[] achImportedTotals = addItems(document, response.getAchImportedItems(), tripleColumnsWidth,
                        tripleColumnsWidths, alignments, 3, "ACHs Imported from Lockbox");
                Object[] checkImportedTotals = addItems(
                        document, response.getChecksImportedItems(), tripleColumnsWidth,
                        tripleColumnsWidths, alignments, 3, "Checks Imported from Lockbox");
                Object[] importedTotals = new Object[]{
                        response.getTotalImported(),
                        ((BigDecimal) achImportedTotals[1]).add((BigDecimal) checkImportedTotals[1])
                };
                addTotal(document, importedTotals, tripleColumnsWidth, tripleColumnsWidths, alignments, 3, true);
                Object[] reversedTotals = addItems(document, response.getAchReversedItems(), tripleColumnsWidth,
                        tripleColumnsWidths, alignments, 3, "ACHs Reversed from Lockbox");
                addTotal(document, new Object[]{ response.getTotalReversed(), reversedTotals[1] }, tripleColumnsWidth,
                        tripleColumnsWidths, alignments, 3, false);
                addItems(document, response.getDebitVouchersItems(), tripleColumnsWidth,
                        tripleColumnsWidths, alignments, 3, "Debit Vouchers from Lockbox");

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
     * Sets the BalancedLockboxReportResponseItem.
     *
     * @param map
     *         the map.
     * @param key
     *         the key.
     * @param paymentTransaction
     *         the PaymentTransaction.
     */
    private static void setItem(HashMap<String, BalancedLockboxReportResponseItem> map,
                                String key, PaymentTransaction paymentTransaction) {
        BalancedLockboxReportResponseItem item = map.get(key);
        if (item == null) {
            item = new BalancedLockboxReportResponseItem();
            item.setItemNumber(0);
            item.setImportStatus(key);
            item.setTotalNumber(new BigDecimal(0));
            map.put(key, item);
        }
        item.setItemNumber(item.getItemNumber() + 1);
        item.setTotalNumber(item.getTotalNumber().add(paymentTransaction.getPayTransPaymentAmount()));
    }

    /**
     * Adds the items to the report.
     *
     * @param document
     *         the report document.
     * @param items
     *         the items.
     * @param width
     *         the table width.
     * @param widths
     *         the table cell widths.
     * @param alignments
     *         the alignments.
     * @param columnsCount
     *         the columns count.
     * @param totalLabel
     *         the total label text.
     * @return the array containing the item number and total number.
     * @throws com.lowagie.text.DocumentException
     *         if any error occurs.
     */
    private static Object[] addItems(com.lowagie.text.Document document,
                                     List<BalancedLockboxReportResponseItem> items, int width, int[] widths,
                                     int[] alignments, int columnsCount, String totalLabel)
            throws com.lowagie.text.DocumentException {
        if (items != null) {
            Table table = new Table(columnsCount);
            table.setWidth(width);
            table.setWidths(widths);
            table.setAlignment(ReportServiceHelper.RTF_ALIGN_LEFT);
            String[] titles = columnsCount == 2 ? new String[]{ "Import Status", "# Items" } :
                    new String[]{ "Import Status", "# Items", "Imported Total" };
            ReportServiceHelper.addReportTableRow(table, titles, ReportServiceHelper.RTF_REPORT_UNDERLINE_FONT,
                    null, alignments, ReportServiceHelper.RTF_NO_BORDER);
            int totalItemNumber = 0;
            BigDecimal totalTotalNumber = new BigDecimal(0);
            for (int i = 0; i < items.size(); i++) {
                BalancedLockboxReportResponseItem item = items.get(i);
                Object[] contents = columnsCount == 2 ? new Object[]{ item.getImportStatus(), item.getItemNumber() } :
                        new Object[]{ item.getImportStatus(), item.getItemNumber(), item.getTotalNumber() };
                ReportServiceHelper.addReportTableRow(table, contents, ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                        null, alignments, i == items.size() - 1 ? ReportServiceHelper.RTF_BORDER_BOTTOM :
                        ReportServiceHelper.RTF_NO_BORDER);
                totalItemNumber += item.getItemNumber();
                if (item.getTotalNumber() != null) {
                    totalTotalNumber = totalTotalNumber.add(item.getTotalNumber());
                }
            }
            Object[] totalColumns = columnsCount == 2 ? new Object[]{ totalLabel, totalItemNumber } :
                    new Object[]{ totalLabel, totalItemNumber, totalTotalNumber };
            ReportServiceHelper.addReportTableRow(table, totalColumns, ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                    null, alignments, ReportServiceHelper.RTF_BORDER_BOTTOM);
            document.add(table);

            return new Object[]{ totalItemNumber, totalTotalNumber };
        }
        return null;
    }

    /**
     * Adds the items to the report.
     *
     * @param document
     *         the report document.
     * @param items
     *         the items.
     * @param width
     *         the table width.
     * @param widths
     *         the table cell widths.
     * @param alignments
     *         the alignments.
     * @param columnsCount
     *         the columns count.
     * @param totalLabel
     *         the total label text.
     * @return the array containing the item number and total number.
     * @throws com.itextpdf.text.DocumentException
     *         if any error occurs.
     */
    private static Object[] addItems(com.itextpdf.text.Document document,
                                     List<BalancedLockboxReportResponseItem> items, int width, int[] widths,
                                     int[] alignments, int columnsCount, String totalLabel)
            throws com.itextpdf.text.DocumentException {
        if (items != null) {
            PdfPTable table = new PdfPTable(columnsCount);
            table.setSpacingBefore(20);
            table.setWidthPercentage(width);
            table.setWidths(widths);
            table.setHorizontalAlignment(ReportServiceHelper.RTF_ALIGN_LEFT);
            String[] titles = columnsCount == 2 ? new String[]{ "Import Status", "# Items" } :
                    new String[]{ "Import Status", "# Items", "Imported Total" };
            ReportServiceHelper.addReportTableRow(table, titles, ReportServiceHelper.PDF_REPORT_UNDERLINE_FONT,
                    null, alignments, ReportServiceHelper.PDF_NO_BORDER);
            int totalItemNumber = 0;
            BigDecimal totalTotalNumber = new BigDecimal(0);
            for (int i = 0; i < items.size(); i++) {
                BalancedLockboxReportResponseItem item = items.get(i);
                Object[] contents = columnsCount == 2 ? new Object[]{ item.getImportStatus(), item.getItemNumber() } :
                        new Object[]{ item.getImportStatus(), item.getItemNumber(), item.getTotalNumber() };
                ReportServiceHelper.addReportTableRow(table, contents, ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                        null, alignments, i == items.size() - 1 ? ReportServiceHelper.PDF_BORDER_BOTTOM :
                        ReportServiceHelper.PDF_NO_BORDER);
                totalItemNumber += item.getItemNumber();
                if (item.getTotalNumber() != null) {
                    totalTotalNumber = totalTotalNumber.add(item.getTotalNumber());
                }
            }
            Object[] totalColumns = columnsCount == 2 ? new Object[]{ totalLabel, totalItemNumber } :
                    new Object[]{ totalLabel, totalItemNumber, totalTotalNumber };
            ReportServiceHelper.addReportTableRow(table, totalColumns, ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                    null, alignments, ReportServiceHelper.PDF_BORDER_BOTTOM);
            document.add(table);

            return new Object[]{ totalItemNumber, totalTotalNumber };
        }
        return null;
    }

    /**
     * Adds the total data to the report.
     *
     * @param document
     *         the report document.
     * @param totals
     *         the array of total data.
     * @param width
     *         the table width.
     * @param widths
     *         the table cell widths.
     * @param alignments
     *         the alignments.
     * @param columnsCount
     *         the columns count.\
     * @param imported
     *         the imported flag..
     * @throws com.lowagie.text.DocumentException
     *         if any error occurs.
     */
    private static void addTotal(com.lowagie.text.Document document, Object[] totals, int width, int[] widths,
                                 int[] alignments, int columnsCount, boolean imported) throws com.lowagie.text
            .DocumentException {
        Table table = new Table(columnsCount);
        table.setWidth(width);
        table.setWidths(widths);
        table.setAlignment(ReportServiceHelper.RTF_ALIGN_LEFT);
        String totalLabel = imported ? "Total Imported This Quarter" : "Total Reversed This Quarter";
        ReportServiceHelper.addReportTableRow(table, columnsCount == 2 ?
                new Object[]{ totalLabel, totals[0] } :
                new Object[]{ totalLabel, totals[0], totals[1] },
                ReportServiceHelper.RTF_REPORT_HEADER_FONT, Color.LIGHT_GRAY, alignments,
                ReportServiceHelper.RTF_NO_BORDER);
        document.add(table);
    }

    /**
     * Adds the total data to the report.
     *
     * @param document
     *         the report document.
     * @param totals
     *         the array of total data.
     * @param width
     *         the table width.
     * @param widths
     *         the table cell widths.
     * @param alignments
     *         the alignments.
     * @param columnsCount
     *         the columns count.\
     * @param imported
     *         the imported flag..
     * @throws com.itextpdf.text.DocumentException
     *         if any error occurs.
     */
    private static void addTotal(com.itextpdf.text.Document document, Object[] totals, int width, int[] widths,
                                 int[] alignments, int columnsCount, boolean imported)
            throws com.itextpdf.text.DocumentException {
        PdfPTable table = new PdfPTable(columnsCount);
        table.setWidthPercentage(width);
        table.setWidths(widths);
        table.setSpacingBefore(10);
        table.setHorizontalAlignment(ReportServiceHelper.RTF_ALIGN_LEFT);
        String totalLabel = imported ? "Total Imported This Quarter" : "Total Reversed This Quarter";
        ReportServiceHelper.addReportTableRow(table, columnsCount == 2 ?
                new Object[]{ totalLabel, totals[0] } :
                new Object[]{ totalLabel, totals[0], totals[1] },
                ReportServiceHelper.PDF_REPORT_HEADER_FONT, BaseColor.LIGHT_GRAY, alignments,
                ReportServiceHelper.PDF_NO_BORDER);
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
    public byte[] renderChart(BalancedLockboxReportResponse response) throws ReportGenerationException {
        return null;
    }
}
