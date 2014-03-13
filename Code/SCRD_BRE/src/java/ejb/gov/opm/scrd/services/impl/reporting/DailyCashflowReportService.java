/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
import gov.opm.scrd.entities.batchprocessing.LockboxPaymentType;
import gov.opm.scrd.entities.batchprocessing.MainframeImport;
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
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates daily cashflow report. It uses local data for
 * generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This class is
 * effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * Changes:
 *  v1.1 change the compare of the PaymentTransactionCodes.
 * 
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
@Stateless
@LocalBean
public class DailyCashflowReportService extends BaseReportService implements
        ReportService<DailyCashflowReportRequest, DailyCashflowReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = DailyCashflowReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link DailyCashflowReportService} class.
     */
    public DailyCashflowReportService() {
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
    public DailyCashflowReportResponse getReport(DailyCashflowReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(DailyCashflowReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            DailyCashflowReportResponse response = new DailyCashflowReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());

            List<Object[]> result = ReportServiceHelper.getDailyReportData(getEntityManager());
            response.setLockboxItems(new ArrayList<DailyCashflowLockboxItem>());
            response.setPaymentItems(new ArrayList<DailyCashflowPaymentItem>());

            HashMap<String, DailyCashflowPaymentItem> paymentItems = new HashMap<String, DailyCashflowPaymentItem>();
            HashMap<String, DailyCashflowLockboxItem> lockboxItems = new HashMap<String, DailyCashflowLockboxItem>();
            for (Object[] objects : result) {
                MainframeImport mainframeImport = (MainframeImport) objects[1];
                PaymentTransaction paymentTransaction = (PaymentTransaction) objects[2];
                InvoiceData invoiceData = (InvoiceData) objects[3];

                DailyCashflowLockboxItem lockboxItem = null;
                if (mainframeImport.getErrorFlag()) {
                    lockboxItem = getLockboxItem("Payments in Error", lockboxItems);
                } else if (PaymentTransactionCodes.POSTED_PENDING.getCode() == paymentTransaction.getPaymentStatusCode() ||
                        PaymentTransactionCodes.POSTED_PENDING_APPROVAL.getCode() == paymentTransaction.getPaymentStatusCode()
                        || PaymentTransactionCodes.POSTED_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                    lockboxItem = getLockboxItem("Payments Posted", lockboxItems);
                } else if (PaymentTransactionCodes.UNRESOLVED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                    lockboxItem = getLockboxItem("Payments Unresolved", lockboxItems);
                } else if (PaymentTransactionCodes.SUSPENDED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                    lockboxItem = getLockboxItem("Payments Suspended", lockboxItems);
                }
                if (lockboxItem != null) {
                    lockboxItem.setAllNumber(lockboxItem.getAllNumber() + 1);
                    lockboxItem.setAllSum(lockboxItem.getAllSum().add(paymentTransaction.getPayTransPaymentAmount()));
                    if (mainframeImport.getPaymentType() == LockboxPaymentType.ACH) {
                        lockboxItem.setAchSum(lockboxItem.getAchSum().add(
                                paymentTransaction.getPayTransPaymentAmount()));
                        lockboxItem.setAchNumber(lockboxItem.getAchNumber() + 1);
                    } else if (mainframeImport.getPaymentType() == LockboxPaymentType.CHECK) {
                        lockboxItem.setCheckSum(lockboxItem.getCheckSum().add(
                                paymentTransaction.getPayTransPaymentAmount()));
                        lockboxItem.setCheckNumber(lockboxItem.getCheckNumber() + 1);
                    }
                }

                DailyCashflowPaymentItem paymentItem = null;
                Date now = new Date();
                if (PaymentTransactionCodes.DIRECT_PAY_LIFE_PENDING.getCode() == paymentTransaction.getPaymentStatusCode()) {
                    paymentItem = getPaymentItem("DPLI Transfer", paymentItems);
                } else if (PaymentTransactionCodes.VOLUNTARY_CONTRIBUTIONS_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                    paymentItem = getPaymentItem("Voluntary Contrib Transfer", paymentItems);
                } else if (PaymentTransactionCodes.REVERSE_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                    paymentItem = getPaymentItem("Reversals", paymentItems);
                } else if (PaymentTransactionCodes.CREDIT_BALANCE_REFUND_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                    paymentItem = getPaymentItem("Balance Refund", paymentItems);
                } else if (PaymentTransactionCodes.BATCH_AUTO_REFUND.getCode() == paymentTransaction.getPaymentStatusCode()) {
                    paymentItem = getPaymentItem("Auto Refund", paymentItems);
                } else if (PaymentTransactionCodes.SUSPENSE_REFUND_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode() &&
                        paymentTransaction.getResolvedSuspense() &&
                        compareDate(mainframeImport.getImportDate(), now) == -1) {
                    paymentItem = getPaymentItem("Over-the-counter", paymentItems);
                } else if (PaymentTransactionCodes.SUSPENSE_REFUND_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode() &&
                        paymentTransaction.getResolvedSuspense()) {
                    paymentItem = getPaymentItem("Suspense Fund", paymentItems);
                } else if (PaymentTransactionCodes.POSTED_PENDING.getCode() == paymentTransaction.getPaymentStatusCode() && !invoiceData.getRefundRequired()) {
                    paymentItem = getPaymentItem("Old Suspense resolved", paymentItems);
                } else if (PaymentTransactionCodes.POSTED_PENDING.getCode() == paymentTransaction.getPaymentStatusCode() &&
                        paymentTransaction.getResolvedSuspense() &&
                        compareDate(mainframeImport.getImportDate(), now) == 0) {
                    paymentItem = getPaymentItem("Today’s Suspense Resolved", paymentItems);
                } else if (PaymentTransactionCodes.POSTED_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode() &&
                        compareDate(mainframeImport.getImportDate(), now) == 0) {
                    paymentItem = getPaymentItem("Today’s Posted Import", paymentItems);
                }
                if (paymentItem != null) {
                    paymentItem.setNumber(paymentItem.getNumber() + 1);
                    paymentItem.setAmount(paymentItem.getAmount().add(paymentTransaction.getPayTransPaymentAmount()));
                }
            }

            for (String key : lockboxItems.keySet()) {
                response.getLockboxItems().add(lockboxItems.get(key));
            }
            for (String key : paymentItems.keySet()) {
                response.getPaymentItems().add(paymentItems.get(key));
            }

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
    public byte[] exportReport(DailyCashflowReportResponse response, ExportType exportType)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(DailyCashflowReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int[] lockboxTableCellWidths = new int[]{ 30, 10, 15, 10, 15, 10, 20 };
            int[] paymentsTableCellWidths = new int[]{ 50, 20, 30 };
            if (exportType == ExportType.RTF || exportType == ExportType.DOC) {
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportTitle(document, response.getReportName());
                int[] alignments = new int[]{
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT
                };

                if (response.getLockboxItems() != null) {
                    Table table = new Table(7);
                    table.setWidth(100);
                    table.setWidths(lockboxTableCellWidths);
                    ReportServiceHelper.addReportTableRow(table, new String[]{ "Lockbox Bank Import File",
                            "# ACH", "ACH SUM", "# Chk", "Check Sum", "# All", "All Sum" },
                            ReportServiceHelper.RTF_REPORT_HEADER_FONT, null, alignments,
                            ReportServiceHelper.RTF_BORDER_BOTTOM);

                    int totalAchNum = 0;
                    BigDecimal totalAchSum = new BigDecimal(0);
                    int totalCheckNum = 0;
                    BigDecimal totalCheckSum = new BigDecimal(0);
                    int totalAllNum = 0;
                    BigDecimal totalAllSum = new BigDecimal(0);
                    for (DailyCashflowLockboxItem item : response.getLockboxItems()) {
                        totalAchNum += item.getAchNumber();
                        totalAchSum = totalAchSum.add(item.getAchSum());
                        totalCheckNum += item.getCheckNumber();
                        totalCheckSum = totalCheckSum.add(item.getCheckSum());
                        totalAllNum += item.getAllNumber();
                        totalAllSum = totalAllSum.add(item.getAllSum());
                        ReportServiceHelper.addReportTableRow(table,
                                new Object[]{ item.getLockboxBankImportFile(), item.getAchNumber(), item.getAchSum(),
                                        item.getCheckNumber(), item.getCheckNumber(), item.getAllNumber(),
                                        item.getAllSum() }, ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null,
                                alignments, ReportServiceHelper.RTF_NO_BORDER);
                    }
                    ReportServiceHelper.addReportTableRow(table,
                            new Object[]{ "File Payments", totalAchNum, totalAchSum, totalCheckNum, totalCheckSum,
                                    totalAllNum, totalAllSum }, ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                            alignments, ReportServiceHelper.RTF_BORDER_TOP);
                    document.add(table);
                }

                if (response.getPaymentItems() != null) {
                    Table table = new Table(3);
                    table.setAlignment(ReportServiceHelper.RTF_ALIGN_LEFT);
                    table.setWidths(paymentsTableCellWidths);
                    table.setWidth(50);
                    ReportServiceHelper.addReportTableRow(table, new String[]{ "Processing Payments", "#",
                            "Amount" }, ReportServiceHelper.RTF_REPORT_HEADER_FONT, null, alignments,
                            ReportServiceHelper.RTF_BORDER_BOTTOM);

                    int totalNumber = 0;
                    BigDecimal totalAmount = new BigDecimal(0);
                    for (DailyCashflowPaymentItem item : response.getPaymentItems()) {
                        totalNumber += item.getNumber();
                        totalAmount = totalAmount.add(item.getAmount());
                        ReportServiceHelper.addReportTableRow(table,
                                new Object[]{ item.getName(), item.getNumber(), item.getAmount() },
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null,
                                alignments, ReportServiceHelper.RTF_NO_BORDER);
                    }
                    ReportServiceHelper.addReportTableRow(table,
                            new Object[]{ "File Payments", totalNumber, totalAmount },
                            ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                            alignments, ReportServiceHelper.RTF_BORDER_TOP);
                    document.add(table);
                }

                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportTitle(document, response.getReportName());
                int[] alignments = new int[]{
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT
                };

                if (response.getLockboxItems() != null) {
                    PdfPTable table = new PdfPTable(7);
                    table.setSpacingBefore(20);
                    table.setWidthPercentage(100);
                    table.setWidths(lockboxTableCellWidths);
                    ReportServiceHelper.addReportTableRow(table, new String[]{ "Lockbox Bank Import File",
                            "# ACH", "ACH SUM", "# Chk", "Check Sum", "# All", "All Sum" },
                            ReportServiceHelper.PDF_REPORT_HEADER_FONT, null, alignments,
                            ReportServiceHelper.PDF_BORDER_BOTTOM);

                    int totalAchNum = 0;
                    BigDecimal totalAchSum = new BigDecimal(0);
                    int totalCheckNum = 0;
                    BigDecimal totalCheckSum = new BigDecimal(0);
                    int totalAllNum = 0;
                    BigDecimal totalAllSum = new BigDecimal(0);
                    for (DailyCashflowLockboxItem item : response.getLockboxItems()) {
                        totalAchNum += item.getAchNumber();
                        totalAchSum = totalAchSum.add(item.getAchSum());
                        totalCheckNum += item.getCheckNumber();
                        totalCheckSum = totalCheckSum.add(item.getCheckSum());
                        totalAllNum += item.getAllNumber();
                        totalAllSum = totalAllSum.add(item.getAllSum());
                        ReportServiceHelper.addReportTableRow(table,
                                new Object[]{ item.getLockboxBankImportFile(), item.getAchNumber(), item.getAchSum(),
                                        item.getCheckNumber(), item.getCheckNumber(), item.getAllNumber(),
                                        item.getAllSum() }, ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null,
                                alignments, ReportServiceHelper.PDF_NO_BORDER);
                    }
                    ReportServiceHelper.addReportTableRow(table,
                            new Object[]{ "File Payments", totalAchNum, totalAchSum, totalCheckNum, totalCheckSum,
                                    totalAllNum, totalAllSum }, ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                            alignments, ReportServiceHelper.PDF_BORDER_TOP);
                    document.add(table);
                }

                if (response.getPaymentItems() != null) {
                    PdfPTable table = new PdfPTable(3);
                    table.setSpacingBefore(30);
                    table.setHorizontalAlignment(ReportServiceHelper.PDF_ALIGN_LEFT);
                    table.setWidths(paymentsTableCellWidths);
                    table.setWidthPercentage(50);
                    ReportServiceHelper.addReportTableRow(table, new String[]{ "Processing Payments", "#",
                            "Amount" }, ReportServiceHelper.PDF_REPORT_HEADER_FONT, null, alignments,
                            ReportServiceHelper.PDF_BORDER_BOTTOM);

                    int totalNumber = 0;
                    BigDecimal totalAmount = new BigDecimal(0);
                    for (DailyCashflowPaymentItem item : response.getPaymentItems()) {
                        totalNumber += item.getNumber();
                        totalAmount = totalAmount.add(item.getAmount());
                        ReportServiceHelper.addReportTableRow(table,
                                new Object[]{ item.getName(), item.getNumber(), item.getAmount() },
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null,
                                alignments, ReportServiceHelper.PDF_NO_BORDER);
                    }
                    ReportServiceHelper.addReportTableRow(table,
                            new Object[]{ "File Payments", totalNumber, totalAmount },
                            ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                            alignments, ReportServiceHelper.PDF_BORDER_TOP);
                    document.add(table);
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
     * Gets the DailyCashflowLockboxItem.
     *
     * @param importType
     *         the item import type.
     * @param map
     *         the mapping.
     * @return the DailyCashflowLockboxItem instance.
     */
    private static DailyCashflowLockboxItem getLockboxItem(String importType, HashMap<String,
            DailyCashflowLockboxItem> map) {
        DailyCashflowLockboxItem item = map.get(importType);
        if (item == null) {
            item = new DailyCashflowLockboxItem();
            item.setLockboxBankImportFile(importType);
            item.setAchSum(new BigDecimal(0));
            item.setAllSum(new BigDecimal(0));
            item.setAllNumber(0);
            item.setCheckNumber(0);
            item.setAchNumber(0);
            item.setCheckSum(new BigDecimal(0));
            map.put(importType, item);
        }
        return item;
    }

    /**
     * Gets the DailyCashflowPaymentItem.
     *
     * @param name
     *         the item name.
     * @param map
     *         the mapping.
     * @return the DailyCashflowPaymentItem instance.
     */
    private static DailyCashflowPaymentItem getPaymentItem(String name, HashMap<String,
            DailyCashflowPaymentItem> map) {
        DailyCashflowPaymentItem item = map.get(name);
        if (item == null) {
            item = new DailyCashflowPaymentItem();
            item.setName(name);
            item.setNumber(0);
            item.setAmount(new BigDecimal(0));
            map.put(name, item);
        }
        return item;
    }

    /**
     * Compares the day of date with that of the current date.
     *
     * @param date
     *         the date to compare
     * @param now
     *         the current date.
     * @return the result, -2 indicating the date is <code>null</code>, -1 indicating the day is before today, 0
     * indicating the day is today, 1 indicating the day is after today.
     */
    private static int compareDate(Date date, Date now) {
        if (date == null) {
            return -2;
        }
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        dateCal.set(Calendar.HOUR_OF_DAY, 0);
        dateCal.set(Calendar.MINUTE, 0);
        dateCal.set(Calendar.SECOND, 0);
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTime(now);
        nowCal.set(Calendar.HOUR_OF_DAY, 0);
        nowCal.set(Calendar.MINUTE, 0);
        nowCal.set(Calendar.SECOND, 0);
        if (dateCal.getTime().before(nowCal.getTime())) {
            return -1;
        } else if (dateCal.getTime() == nowCal.getTime()) {
            return 0;
        } else {
            return 1;
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
    public byte[] renderChart(DailyCashflowReportResponse response) throws ReportGenerationException {
        return null;
    }
}
