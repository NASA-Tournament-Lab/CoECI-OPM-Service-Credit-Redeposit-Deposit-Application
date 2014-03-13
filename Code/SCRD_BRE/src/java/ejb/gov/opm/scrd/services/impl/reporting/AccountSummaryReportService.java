/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;

/**
 * This class is the implementation of the ReportService which generates account summary report. It uses local data for
 * generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This class is
 * effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
@Stateless
@LocalBean
public class AccountSummaryReportService extends BaseReportService
        implements
        ReportService<AccountSummaryReportRequest, AccountSummaryReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = AccountSummaryReportService.class
            .getName();

    /**
     * Represents the name of CSRS retirement type. It is modified by setter. It is injected by Spring. It can not be
     * null after injected.
     */
    @Autowired
    private String receiptPaymentTypeName;

    /**
     * Represents the name of CSRS debit voucher adjustment payment type. It is modified by setter. It is injected by
     * Spring. It can not be null after injected.
     */
    @Autowired
    private String debitVoucherAdjustmentPaymentTypeName;

    /**
     * Represents the name of CSRS replacement adjustment payment type. It is modified by setter. It is injected by
     * Spring. It can not be null after injected.
     */
    @Autowired
    private String replacementAdjustmentPaymentTypeName;

    /**
     * Creates a new instance of the {@link AccountSummaryReportService} class.
     */
    public AccountSummaryReportService() {
        super();
    }

    /**
     * Adds a row for the field to the RTF table.
     *
     * @param table
     *         the table.
     * @param label
     *         the field label.
     * @param value
     *         the field value.
     * @param emptyCellColSpan
     *         how many columns the empty cell will span.
     */
    private static void addContentRow(Table table, String label, String value,
                                      int emptyCellColSpan) {
        if (emptyCellColSpan > 0) {
            table.addCell(ReportServiceHelper.createEmptyCell(emptyCellColSpan,
                    ReportServiceHelper.RTF_NO_BORDER));
        }

        table.addCell(ReportServiceHelper
                .createTableCell(
                        label,
                        emptyCellColSpan > 0 ? ReportServiceHelper.RTF_REPORT_CONTENT_FONT
                                : ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                        null, ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_BORDER_BOTTOM,
                        4 - emptyCellColSpan - 1));
        table.addCell(ReportServiceHelper
                .createTableCell(
                        value,
                        emptyCellColSpan > 0 ? ReportServiceHelper.RTF_REPORT_CONTENT_FONT
                                : ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                        null, ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_BORDER_BOTTOM, 1));
    }

    /**
     * Adds a row for the field to the PDF table.
     *
     * @param table
     *         the table.
     * @param label
     *         the field label.
     * @param value
     *         the field value.
     * @param emptyCellColSpan
     *         how many columns the empty cell will span.
     */
    private static void addContentRow(PdfPTable table, String label,
                                      String value, int emptyCellColSpan) {
        if (emptyCellColSpan > 0) {
            table.addCell(ReportServiceHelper.createEmptyPdfCell(
                    emptyCellColSpan, ReportServiceHelper.PDF_NO_BORDER));
        }

        table.addCell(ReportServiceHelper
                .createTableCell(
                        label,
                        emptyCellColSpan > 0 ? ReportServiceHelper.PDF_REPORT_CONTENT_FONT
                                : ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                        null, ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_BORDER_BOTTOM,
                        4 - emptyCellColSpan - 1));
        table.addCell(ReportServiceHelper
                .createTableCell(
                        value,
                        emptyCellColSpan > 0 ? ReportServiceHelper.PDF_REPORT_CONTENT_FONT
                                : ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                        null, ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_BORDER_BOTTOM, 1));
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
    public AccountSummaryReportResponse getReport(
            AccountSummaryReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(AccountSummaryReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            AccountSummaryReportResponse response = new AccountSummaryReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());

            response.setReceipts(getEntityManager()
                    .createQuery(
                            "SELECT COALESCE(SUM(p.payTransPaymentAmount), 0) FROM PaymentTransaction p WHERE "
                                    + "p.csd = :csd AND p.payTransStatusCode = :payTransStatusCode",
                            BigDecimal.class).setParameter("csd", request.getCsd()).setParameter
                            ("payTransStatusCode", receiptPaymentTypeName).getSingleResult());
            response.setSuspense(getEntityManager()
                    .createQuery(
                            "SELECT COALESCE(SUM(p.payTransPaymentAmount), 0) FROM PaymentTransaction p WHERE "
                                    + "p.resolvedSuspense = :resolvedSuspense AND "
                                    + "p.csd = :csd", BigDecimal.class)
                    .setParameter("csd", request.getCsd())
                    .setParameter("resolvedSuspense", false).getSingleResult());
            response.setReplacedAccounts(getEntityManager()
                    .createQuery(
                            "SELECT COALESCE(SUM(p.payTransPaymentAmount), 0) FROM PaymentTransaction p WHERE "
                                    + "p.resolvedSuspense = :resolvedSuspense AND p.payTransStatusCode"
                                    + " = :payTransStatusCode AND p.csd = :csd",
                            BigDecimal.class)
                    .setParameter("resolvedSuspense", false)
                    .setParameter("csd", request.getCsd())
                    .setParameter("payTransStatusCode",
                            replacementAdjustmentPaymentTypeName)
                    .getSingleResult());
            response.setAdjustmentPlus(getEntityManager()
                    .createQuery(
                            "SELECT COALESCE(SUM(p.payTransPaymentAmount), 0) FROM PaymentTransaction p WHERE "
                                    + "p.userInserted = :userInserted AND p.payTransStatusCode"
                                    + " = :payTransStatusCode AND p.csd = :csd",
                            BigDecimal.class)
                    .setParameter("userInserted", true)
                    .setParameter("csd", request.getCsd())
                    .setParameter("payTransStatusCode",
                            replacementAdjustmentPaymentTypeName)
                    .getSingleResult());
            response.setDebitVouchers(getEntityManager()
                    .createQuery(
                            "SELECT COALESCE(SUM(p.payTransPaymentAmount), 0) FROM PaymentTransaction p WHERE "
                                    + "p.payTransStatusCode = :payTransStatusCode AND "
                                    + "p.csd = :csd", BigDecimal.class)
                    .setParameter("csd", request.getCsd())
                    .setParameter("payTransStatusCode",
                            debitVoucherAdjustmentPaymentTypeName)
                    .getSingleResult());
            response.setAdjustmentMinus(getEntityManager()
                    .createQuery(
                            "SELECT COALESCE(SUM(r.amount), 0) FROM RefundTransaction r WHERE"
                                    + " r.claimNumber = :csd", BigDecimal.class)
                    .setParameter("csd", request.getCsd()).getSingleResult());
            response.setTotalReceipts(response.getReceipts().add(response.getSuspense()));
            response.setTotalAdditions(response.getAdjustmentPlus().add(response.getReplacedAccounts()));
            response.setTotalDeductions(response.getAdjustmentMinus().add(response.getDebitVouchers()));
            response.setNetChange(response.getTotalReceipts().add(response.getTotalAdditions()).subtract(response
                    .getTotalDeductions()));

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
    public byte[] exportReport(AccountSummaryReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(AccountSummaryReportResponse response)";
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

                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());
                ReportServiceHelper.addReportDate(document,
                        response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT);

                int[] cellWidths = new int[]{ 10, 10, 30, 50 };

                Table additionTable = new Table(4);
                additionTable.setWidths(cellWidths);
                additionTable.addCell(ReportServiceHelper.createTableCell(
                        "Additions to Account",
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_NO_BORDER, 4));
                addContentRow(additionTable, "Receipts",
                        Helper.getMoney(response.getReceipts()), 1);
                addContentRow(additionTable, "Suspense",
                        Helper.getMoney(response.getSuspense()), 1);
                addContentRow(additionTable, "Total Receipts",
                        Helper.getMoney(response.getTotalReceipts()), 2);
                addContentRow(additionTable, "ReplacedAccounts",
                        Helper.getMoney(response.getReplacedAccounts()), 1);
                addContentRow(additionTable, "AdjustmentPlus",
                        Helper.getMoney(response.getAdjustmentPlus()), 1);
                addContentRow(additionTable, "Total Additions",
                        Helper.getMoney(response.getTotalAdditions()), 2);
                document.add(additionTable);

                Table deductionTable = new Table(4);
                deductionTable.setWidths(cellWidths);
                deductionTable.addCell(ReportServiceHelper.createTableCell(
                        "Deductions from Account",
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_NO_BORDER, 4));
                addContentRow(deductionTable, "DebitVouchers",
                        Helper.getMoney(response.getDebitVouchers()), 1);
                addContentRow(deductionTable, "AdjustmentMinus",
                        Helper.getMoney(response.getAdjustmentMinus()), 1);
                addContentRow(deductionTable, "Total Deductions",
                        Helper.getMoney(response.getTotalDeductions()), 2);
                addContentRow(deductionTable, "Net Change to Account",
                        Helper.getMoney(response.getNetChange()), 0);
                document.add(deductionTable);
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());
                ReportServiceHelper.addReportDate(document,
                        response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT);

                int[] cellWidths = new int[]{ 10, 10, 30, 50 };

                PdfPTable additionTable = new PdfPTable(4);
                additionTable.setWidths(cellWidths);
                additionTable.addCell(ReportServiceHelper.createTableCell(
                        "Additions to Account",
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_NO_BORDER, 4));
                addContentRow(additionTable, "Receipts",
                        Helper.getMoney(response.getReceipts()), 1);
                addContentRow(additionTable, "Suspense",
                        Helper.getMoney(response.getSuspense()), 1);
                addContentRow(additionTable, "Total Receipts",
                        Helper.getMoney(response.getTotalReceipts()), 2);
                addContentRow(additionTable, "ReplacedAccounts",
                        Helper.getMoney(response.getReplacedAccounts()), 1);
                addContentRow(additionTable, "AdjustmentPlus",
                        Helper.getMoney(response.getAdjustmentPlus()), 1);
                addContentRow(additionTable, "Total Additions",
                        Helper.getMoney(response.getTotalAdditions()), 2);
                document.add(additionTable);

                PdfPTable deductionTable = new PdfPTable(4);
                deductionTable.setWidths(cellWidths);
                deductionTable.addCell(ReportServiceHelper.createTableCell(
                        "Deductions from Account",
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_NO_BORDER, 4));
                addContentRow(deductionTable, "DebitVouchers",
                        Helper.getMoney(response.getDebitVouchers()), 1);
                addContentRow(deductionTable, "AdjustmentMinus",
                        Helper.getMoney(response.getAdjustmentMinus()), 1);
                addContentRow(deductionTable, "Total Deductions",
                        Helper.getMoney(response.getTotalDeductions()), 2);
                addContentRow(deductionTable, "Net Change to Account",
                        Helper.getMoney(response.getNetChange()), 0);
                document.add(deductionTable);
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
     * Sets the name of receipt payment type.
     *
     * @param receiptPaymentTypeName
     *         the name of receipt payment type.
     */
    public void setReceiptPaymentTypeName(String receiptPaymentTypeName) {
        this.receiptPaymentTypeName = receiptPaymentTypeName;
    }

    /**
     * Sets the name of debit voucher adjustment retirement type.
     *
     * @param debitVoucherAdjustmentPaymentTypeName
     *         the name of debit voucher adjustment retirement type.
     */
    public void setDebitVoucherAdjustmentPaymentTypeName(
            String debitVoucherAdjustmentPaymentTypeName) {
        this.debitVoucherAdjustmentPaymentTypeName = debitVoucherAdjustmentPaymentTypeName;
    }

    /**
     * Sets the name of replacement adjustment retirement type.
     *
     * @param replacementAdjustmentPaymentTypeName
     *         the name of replacement adjustment retirement type.
     */
    public void setReplacementAdjustmentPaymentTypeName(
            String replacementAdjustmentPaymentTypeName) {
        this.replacementAdjustmentPaymentTypeName = replacementAdjustmentPaymentTypeName;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *         if the instance was not initialized properly.
     */
    @PostConstruct
    @Override
    protected void checkInit() {
        super.checkInit();
        Helper.checkState(Helper.isNullOrEmpty(receiptPaymentTypeName),
                "The receiptPaymentTypeName cannot be null or empty.");
        Helper.checkState(
                Helper.isNullOrEmpty(debitVoucherAdjustmentPaymentTypeName),
                "The debitVoucherAdjustmentPaymentTypeName cannot be null or empty.");
        Helper.checkState(
                Helper.isNullOrEmpty(replacementAdjustmentPaymentTypeName),
                "The replacementAdjustmentPaymentTypeName cannot be null or empty.");
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(AccountSummaryReportResponse response) throws ReportGenerationException {
        return null;
    }
}
