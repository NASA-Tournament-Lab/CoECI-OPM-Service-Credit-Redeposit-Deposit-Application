/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
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
import java.util.List;
import java.util.Locale;

/**
 * This class is the implementation of the ReportService which generates daily suspense report. It uses local data for
 * generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This class is
 * effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
@Stateless
@LocalBean
public class DailySuspenseReportService extends BaseReportService implements
        ReportService<DailySuspenseReportRequest, DailySuspenseReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = DailySuspenseReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link DailySuspenseReportService} class.
     */
    public DailySuspenseReportService() {
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
    public DailySuspenseReportResponse getReport(DailySuspenseReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(DailySuspenseReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            DailySuspenseReportResponse response = new DailySuspenseReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setItems(new ArrayList<DailySuspenseReportResponseItem>());

            List<Object[]> result = ReportServiceHelper.getDailyReportData(getEntityManager());
            for (Object[] objects : result) {
                PaymentTransaction paymentTransaction = (PaymentTransaction) objects[2];
                DailySuspenseReportResponseItem item = new DailySuspenseReportResponseItem();
                item.setBatchNumber(paymentTransaction.getPayTransBatchNumber());
                item.setBlkNumber(paymentTransaction.getPayTransBlockNumber());
                item.setSequenceNumber(paymentTransaction.getPayTransSequenceNumber());
                item.setAmount(paymentTransaction.getPayTransPaymentAmount());
                item.setPayDate(paymentTransaction.getPayTransTransactionDate());
                item.setBirthDate(paymentTransaction.getScmDateOfBirth());
                item.setCsd(paymentTransaction.getCsd());
                item.setTechnician(paymentTransaction.getTechnicianUserKey().toString());
                item.setChangedOn(paymentTransaction.getPayTransStatusDate());
                item.setPaymentStatus(paymentTransaction.getPayTransStatusCode().toString());
                response.getItems().add(item);
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
    public byte[] exportReport(DailySuspenseReportResponse response, ExportType exportType)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(DailySuspenseReportResponse response)";
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

                ReportServiceHelper.addReportTitle(document, response.getReportName());
                if (response.getReportGenerationDate() != null) {
                    ReportServiceHelper.addReportTitle(document, "Payments Imported on " + DateFormat
                            .getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, Locale.ENGLISH).format(
                                    response.getReportGenerationDate()));
                }
                int[] alignments = new int[]{
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT
                };

                for (DailySuspenseReportResponseItem item : response.getItems()) {
                    Table table1 = new Table(5);
                    table1.setAlignment(ReportServiceHelper.RTF_ALIGN_LEFT);
                    table1.setWidth(40);
                    table1.setWidths(new int[]{ 20, 10, 25, 20, 25 });
                    ReportServiceHelper.addReportTableRow(table1, new String[]{ "Batch", "Blk", "Seq", "Amount",
                            "Pay Date" }, ReportServiceHelper.RTF_REPORT_UNDERLINE_FONT, null, alignments,
                            ReportServiceHelper.RTF_NO_BORDER);
                    String payDate = item.getPayDate() == null ? null :
                            ReportServiceHelper.REPORT_DATE_FORMAT.format(item.getPayDate());
                    ReportServiceHelper.addReportTableRow(table1, new Object[]{ item.getBatchNumber(),
                            item.getBlkNumber(), item.getSequenceNumber(), item.getAmount(),
                            payDate }, ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null, alignments,
                            ReportServiceHelper.RTF_NO_BORDER);
                    document.add(table1);

                    Table table2 = new Table(5);
                    table2.setAlignment(ReportServiceHelper.RTF_ALIGN_RIGHT);
                    table2.setWidth(90);
                    table2.setWidths(new int[]{ 15, 15, 15, 35, 20 });
                    ReportServiceHelper.addReportTableRow(table2, new String[]{ "CSD", "Birth Date", "Technician",
                            "Set Payment To", "Changed On" }, ReportServiceHelper.RTF_REPORT_UNDERLINE_FONT,
                            null, alignments, ReportServiceHelper.RTF_NO_BORDER);
                    String birthDate = item.getBirthDate() == null ? null :
                            ReportServiceHelper.REPORT_DATE_FORMAT.format(item.getBirthDate());
                    String changedOn = item.getChangedOn() == null ? null :
                            ReportServiceHelper.REPORT_DATE_FORMAT.format(item.getChangedOn());
                    ReportServiceHelper.addReportTableRow(table2, new Object[]{ item.getCsd(),
                            birthDate, item.getTechnician(), item.getPaymentStatus(), changedOn },
                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                            null, alignments, ReportServiceHelper.RTF_NO_BORDER);
                    document.add(table2);
                }
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportTitle(document, response.getReportName());
                if (response.getReportGenerationDate() != null) {
                    ReportServiceHelper.addReportTitle(document, "Payments Imported on " + DateFormat
                            .getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, Locale.ENGLISH).format(
                                    response.getReportGenerationDate()));
                }
                int[] alignments = new int[]{
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT
                };

                for (DailySuspenseReportResponseItem item : response.getItems()) {
                    PdfPTable table1 = new PdfPTable(5);
                    table1.setSpacingBefore(20);
                    table1.setHorizontalAlignment(ReportServiceHelper.PDF_ALIGN_LEFT);
                    table1.setWidthPercentage(40);
                    table1.setWidths(new int[]{ 20, 10, 25, 20, 25 });
                    ReportServiceHelper.addReportTableRow(table1, new String[]{ "Batch", "Blk", "Seq", "Amount",
                            "Pay Date" }, ReportServiceHelper.PDF_REPORT_UNDERLINE_FONT, null, alignments,
                            ReportServiceHelper.PDF_NO_BORDER);
                    String payDate = item.getPayDate() == null ? null :
                            ReportServiceHelper.REPORT_DATE_FORMAT.format(item.getPayDate());
                    ReportServiceHelper.addReportTableRow(table1, new Object[]{ item.getBatchNumber(),
                            item.getBlkNumber(), item.getSequenceNumber(), item.getAmount(),
                            payDate }, ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null, alignments,
                            ReportServiceHelper.PDF_NO_BORDER);
                    document.add(table1);

                    PdfPTable table2 = new PdfPTable(5);
                    table2.setHorizontalAlignment(ReportServiceHelper.PDF_ALIGN_RIGHT);
                    table2.setWidthPercentage(90);
                    table2.setWidths(new int[]{ 15, 15, 15, 35, 20 });
                    ReportServiceHelper.addReportTableRow(table2, new String[]{ "CSD", "Birth Date", "Technician",
                            "Set Payment To", "Changed On" }, ReportServiceHelper.PDF_REPORT_UNDERLINE_FONT,
                            null, alignments, ReportServiceHelper.PDF_NO_BORDER);
                    String birthDate = item.getBirthDate() == null ? null :
                            ReportServiceHelper.REPORT_DATE_FORMAT.format(item.getBirthDate());
                    String changedOn = item.getChangedOn() == null ? null :
                            ReportServiceHelper.REPORT_DATE_FORMAT.format(item.getChangedOn());
                    ReportServiceHelper.addReportTableRow(table2, new Object[]{ item.getCsd(),
                            birthDate, item.getTechnician(), item.getPaymentStatus(), changedOn },
                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                            null, alignments, ReportServiceHelper.PDF_NO_BORDER);
                    document.add(table2);
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
    public byte[] renderChart(DailySuspenseReportResponse response) throws ReportGenerationException {

        return null;
    }
}
