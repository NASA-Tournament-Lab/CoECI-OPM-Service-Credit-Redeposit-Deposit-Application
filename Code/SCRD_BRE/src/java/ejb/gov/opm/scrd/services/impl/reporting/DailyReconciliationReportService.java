/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates daily reconciliation report. It uses local data
 * for generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This class is
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
public class DailyReconciliationReportService extends BaseReportService implements
        ReportService<DailyReconciliationReportRequest, DailyReconciliationReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = DailyReconciliationReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link DailyReconciliationReportService} class.
     */
    public DailyReconciliationReportService() {
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
    public DailyReconciliationReportResponse getReport(DailyReconciliationReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(DailyReconciliationReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            DailyReconciliationReportResponse response = new DailyReconciliationReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());

            List<Object[]> result = ReportServiceHelper.getDailyReportData(getEntityManager());
            DailyReconciliationReportResponseItem lockboxTotal = new DailyReconciliationReportResponseItem();
            lockboxTotal.setName("Lockbox Total");
            lockboxTotal.setItems(result.size());
            DailyReconciliationReportResponseItem achTotal = new DailyReconciliationReportResponseItem();
            achTotal.setName("ACH Total");

            DailyReconciliationReportResponseItem checkTotal = new DailyReconciliationReportResponseItem();
            checkTotal.setName("Check Total");
            DailyReconciliationReportResponseItem fundControlTotal = new DailyReconciliationReportResponseItem();
            fundControlTotal.setName("Fund Control Total");

            int lockboxProcessed = 0;
            int lockboxSuspended = 0;
            int lockboxReversed = 0;
            int achItems = 0;
            int achProcessed = 0;
            int achSuspended = 0;
            int achReversed = 0;
            int checkProcessed = 0;
            int checkSuspended = 0;
            int checkReversed = 0;
            int checkItems = 0;
            int fundControlProcessed = 0;
            int fundControlSuspended = 0;
            int fundControlReversed = 0;
            int fundControlItems = 0;
            for (Object[] objects : result) {
                MainframeImport mainframeImport = (MainframeImport) objects[1];
                PaymentTransaction paymentTransaction = (PaymentTransaction) objects[2];

                if (mainframeImport.getPaymentType() == LockboxPaymentType.ACH) {
                    achItems++;
                    
                    if (PaymentTransactionCodes.ACCEPTED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        lockboxProcessed++;
                        achProcessed++;
                    } else if (PaymentTransactionCodes.SUSPENDED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        lockboxSuspended++;
                        achSuspended++;
                    } else if (PaymentTransactionCodes.REVERSE_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        lockboxReversed++;
                        achReversed++;
                    }
                } else if (mainframeImport.getPaymentType() == LockboxPaymentType.CHECK) {
                    checkItems++;
                    if (PaymentTransactionCodes.ACCEPTED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        lockboxProcessed++;
                        checkProcessed++;
                    } else if (PaymentTransactionCodes.SUSPENDED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        lockboxSuspended++;
                        checkSuspended++;
                    } else if (PaymentTransactionCodes.REVERSE_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        lockboxReversed++;
                        checkReversed++;
                    }
                } else if (mainframeImport.getPaymentType() == LockboxPaymentType.UNKNOWN) {
                    fundControlItems++;
                    if (PaymentTransactionCodes.ACCEPTED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        lockboxProcessed++;
                        fundControlProcessed++;
                    } else if (PaymentTransactionCodes.SUSPENDED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        lockboxSuspended++;
                        fundControlSuspended++;
                    } else if (PaymentTransactionCodes.REVERSE_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        lockboxReversed++;
                        fundControlReversed++;
                    }
                }
            }

            lockboxTotal.setProcessed(lockboxProcessed);
            lockboxTotal.setSuspended(lockboxSuspended);
            lockboxTotal.setReversed(lockboxReversed);
            lockboxTotal.setReceipts(lockboxProcessed + lockboxReversed + lockboxSuspended);
            achTotal.setItems(achItems);
            achTotal.setProcessed(achProcessed);
            achTotal.setSuspended(achSuspended);
            achTotal.setReversed(achReversed);
            achTotal.setReceipts(achProcessed + achSuspended + achReversed);
            checkTotal.setItems(checkItems);
            checkTotal.setProcessed(checkProcessed);
            checkTotal.setSuspended(checkSuspended);
            checkTotal.setReversed(checkReversed);
            checkTotal.setReceipts(checkProcessed + checkSuspended + checkReversed);
            fundControlTotal.setItems(fundControlItems);
            fundControlTotal.setProcessed(fundControlProcessed);
            fundControlTotal.setSuspended(fundControlSuspended);
            fundControlTotal.setReversed(fundControlReversed);
            fundControlTotal.setReceipts(fundControlProcessed + fundControlSuspended + fundControlReversed);

            response.setItems(new ArrayList<DailyReconciliationReportResponseItem>());
            response.getItems().add(lockboxTotal);
            response.getItems().add(achTotal);
            response.getItems().add(checkTotal);
            response.getItems().add(fundControlTotal);

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
    public byte[] exportReport(DailyReconciliationReportResponse response, ExportType exportType)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(DailyReconciliationReportResponse response)";
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

                ReportServiceHelper.addReportTitle(document, "Service Credit Billing and Collection System");
                ReportServiceHelper.addReportTitle(document, response.getReportName());
                ReportServiceHelper.addReportDate(document, response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT, ReportServiceHelper.RTF_ALIGN_CENTER);

                if (response.getItems() != null) {
                    int[] alignments = new int[]{
                            ReportServiceHelper.RTF_ALIGN_CENTER,
                            ReportServiceHelper.RTF_ALIGN_CENTER,
                            ReportServiceHelper.RTF_ALIGN_CENTER,
                            ReportServiceHelper.RTF_ALIGN_CENTER,
                            ReportServiceHelper.RTF_ALIGN_CENTER,
                    };
                    Table table = new Table(6);
                    table.addCell(ReportServiceHelper.createEmptyCell(1, ReportServiceHelper.RTF_NO_BORDER));
                    ReportServiceHelper.addReportTableRow(table, new String[]{ "# Items", "Receipts", "Processed",
                            "Suspended", "Reversed" }, ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                            alignments, ReportServiceHelper.RTF_BORDER_BOTTOM);
                    for (DailyReconciliationReportResponseItem item : response.getItems()) {
                        table.addCell(ReportServiceHelper.createTableCell(item.getName(),
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                                ReportServiceHelper.RTF_ALIGN_LEFT, ReportServiceHelper.RTF_NO_BORDER, 1));
                        ReportServiceHelper.addReportTableRow(table, new Object[]{ item.getItems(),
                                item.getReceipts(), item.getProcessed(), item.getSuspended(), item.getReversed() },
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null, alignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                    }
                    document.add(table);
                }
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportTitle(document, "Service Credit Billing and Collection System");
                ReportServiceHelper.addReportTitle(document, response.getReportName());
                ReportServiceHelper.addReportDate(document, response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT, ReportServiceHelper.PDF_ALIGN_CENTER);
                if (response.getItems() != null) {
                    int[] alignments = new int[]{
                            ReportServiceHelper.PDF_ALIGN_CENTER,
                            ReportServiceHelper.PDF_ALIGN_CENTER,
                            ReportServiceHelper.PDF_ALIGN_CENTER,
                            ReportServiceHelper.PDF_ALIGN_CENTER,
                            ReportServiceHelper.PDF_ALIGN_CENTER,
                    };
                    PdfPTable table = new PdfPTable(6);
                    table.setSpacingBefore(20);
                    table.addCell(ReportServiceHelper.createEmptyPdfCell(1, ReportServiceHelper.PDF_NO_BORDER));
                    ReportServiceHelper.addReportTableRow(table, new String[]{ "# Items", "Receipts", "Processed",
                            "Suspended", "Reversed" }, ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                            alignments, ReportServiceHelper.PDF_BORDER_BOTTOM);
                    for (DailyReconciliationReportResponseItem item : response.getItems()) {
                        table.addCell(ReportServiceHelper.createTableCell(item.getName(),
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                                ReportServiceHelper.PDF_ALIGN_LEFT, ReportServiceHelper.PDF_NO_BORDER, 1));
                        ReportServiceHelper.addReportTableRow(table, new Object[]{ item.getItems(),
                                item.getReceipts(), item.getProcessed(), item.getSuspended(), item.getReversed() },
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null, alignments,
                                ReportServiceHelper.PDF_NO_BORDER);
                    }
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
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(DailyReconciliationReportResponse response) throws ReportGenerationException {
        return null;
    }
}
