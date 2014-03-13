/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * This class is the implementation of the ReportService which generates account balance report. It uses local data for
 * generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This class is
 * effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * @author AleaActaEst, RaitoShums
 * @version 1.1
 */
@Stateless
@LocalBean
public class AccountBalanceReportService extends BaseReportService
        implements
        ReportService<AccountBalanceReportRequest, AccountBalanceReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = AccountBalanceReportService.class
            .getName();

    /**
     * Represents the NumberFormat instance for parse number data from audit records It is modified by setter. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private NumberFormat auditNumberFormat;

    /**
     * Represents the DateFormat instance for parse date data from audit records It is modified by setter. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private DateFormat auditDateFormat;

    /**
     * Creates a new instance of the {@link AccountBalanceReportService} class.
     */
    public AccountBalanceReportService() {
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
    @Override
    public AccountBalanceReportResponse getReport(
            AccountBalanceReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(AccountBalanceReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");

        try {
            TypedQuery<Account> accountQuery = getEntityManager().createQuery(
                    "SELECT a FROM Account a WHERE a.claimNumber = :csd",
                    Account.class);
            accountQuery.setParameter("csd", request.getCsd());
            Account account = accountQuery.getSingleResult();

            AccountBalanceReportResponse response = new AccountBalanceReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setCsd(request.getCsd());
            response.setItems(new HashMap<Date, List<AccountBalanceReportResponseItem>>());

            TypedQuery<AuditRecord> auditRecordsQuery = getEntityManager()
                    .createQuery(
                            "SELECT DISTINCT a FROM AuditRecord a "
                                    + "JOIN a.parameters p WHERE p.itemId = :id",
                            AuditRecord.class);
            auditRecordsQuery.setParameter("id", account.getId());
            List<AuditRecord> auditRecords = auditRecordsQuery.getResultList();

            for (AuditRecord record : auditRecords) {
                AccountBalanceReportResponseItem item = new AccountBalanceReportResponseItem();

                item.setAuditTime(record.getDate());
                item.setAuditUser(record.getUsername());

                for (AuditParameterRecord auditParameterRecord : record
                        .getParameters()) {
                    if (auditParameterRecord.getPropertyName()
                            .equals("payCode")) {
                        item.setPayCode(auditParameterRecord.getNewValue());
                    }
                    if (auditParameterRecord.getPropertyName().equals(
                            "accountStatus")) {
                        item.setStatus(auditParameterRecord.getNewValue());
                    }
                    if (auditParameterRecord.getPropertyName()
                            .equals("lastPay")) {
                        item.setLastPay(auditDateFormat
                                .parse(auditParameterRecord.getNewValue()));
                    }
                    if (auditParameterRecord.getPropertyName()
                            .equals("deposit")) {
                        item.setDeposit(new BigDecimal(
                                auditNumberFormat.parse(auditParameterRecord.getNewValue()).toString()));
                    }
                    if (auditParameterRecord.getPropertyName().equals(
                            "additionalInterest")) {
                        item.setAdditionalInterest(new BigDecimal(
                                auditNumberFormat.parse(auditParameterRecord.getNewValue()).toString()));
                    }
                    if (auditParameterRecord.getPropertyName()
                            .equals("payment")) {
                        item.setPayment(new BigDecimal(
                                auditNumberFormat.parse(auditParameterRecord.getNewValue()).toString()));
                    }
                    if (auditParameterRecord.getPropertyName().equals(
                            "subTotals")) {
                        item.setSubTotals(new BigDecimal(
                                auditNumberFormat.parse(auditParameterRecord.getNewValue()).toString()));
                    }
                    if (auditParameterRecord.getPropertyName().equals(
                            "grandTotal")) {
                        item.setGrandTotal(new BigDecimal(
                                auditNumberFormat.parse(auditParameterRecord.getNewValue()).toString()));
                    }
                }

                item.setBalance(item.getDeposit().add(item.getAdditionalInterest().subtract((item.getPayment()))));

                Date auditTime = item.getAuditTime();
                DateTime dt = new DateTime(auditTime);
                Date key = new DateTime(dt.year().get(), dt.monthOfYear().get(), dt.dayOfMonth().get(), 0, 0, 0).toDate();
                if (response.getItems().get(key) == null) {
                    response.getItems().put(key,
                            new ArrayList<AccountBalanceReportResponseItem>());
                }
                response.getItems().get(key).add(item);
            }
            LoggingHelper.logExit(logger, signature, new Object[]{ response });
            return response;
        } catch (ParseException ex) {
            throw LoggingHelper.logException(logger, signature,
                    new ReportGenerationException(
                            "The record value format is incorrect.", ex));
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
    public byte[] exportReport(AccountBalanceReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(AccountBalanceReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");

        try {
            DateFormat auditTimeFormat = DateFormat
                    .getTimeInstance(DateFormat.SHORT);
            SimpleDateFormat valueDateFormat = new SimpleDateFormat(
                    "MM/dd/yyyy");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            if (exportType == ExportType.DOC || exportType == ExportType.RTF) {
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportDate(document,
                        response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT,
                        ReportServiceHelper.RTF_ALIGN_LEFT);
                if (!Helper.isNullOrEmpty(response.getReportName())) {
                    ReportServiceHelper.addReportTitle(
                            document,
                            response.getReportName() + " for CSD #"
                                    + response.getCsd());
                }

                if (!Helper.isNullOrEmpty(response.getItems())) {
                    Table table = new Table(9);
                    table.setWidth(100);

                    int[] alignments = new int[]{
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT };
                    BigDecimal grandTotals = new BigDecimal("0.00");
                    for (Date auditTime : response.getItems().keySet()) {
                        table.addCell(ReportServiceHelper.createTableCell(
                                DateFormat.getDateInstance(DateFormat.FULL)
                                        .format(auditTime),
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                Color.LIGHT_GRAY,
                                ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_NO_BORDER, 2));

                        for (int i = 0; i < 7; i++) {
                            table.addCell(ReportServiceHelper.createEmptyCell(
                                    1, ReportServiceHelper.RTF_NO_BORDER));
                        }

                        ReportServiceHelper
                                .addReportTableRow(
                                        table,
                                        new String[]{ "Audit Time",
                                                "Audit User", "C", "Status",
                                                "Lasy Pay", "Deposits",
                                                "Add'l Interest", "Payments",
                                                "Balance" },
                                        ReportServiceHelper.RTF_REPORT_UNDERLINE_FONT,
                                        null, alignments,
                                        ReportServiceHelper.RTF_NO_BORDER);
                        BigDecimal subTotals = new BigDecimal("0.00");
                        for (AccountBalanceReportResponseItem item : response
                                .getItems().get(auditTime)) {
                            String auditTimeStr = auditTimeFormat
                                    .format(auditTime);
                            String lastPay = item.getLastPay() == null ? null
                                    : valueDateFormat.format(item.getLastPay());
                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new String[]{
                                                    auditTimeStr,
                                                    item.getAuditUser(),
                                                    item.getPayCode(),
                                                    item.getStatus(),
                                                    lastPay,
                                                    Helper.getMoney(item
                                                            .getDeposit()),
                                                    Helper.getMoney(item
                                                            .getAdditionalInterest()),
                                                    Helper.getMoney(item
                                                            .getPayment()),
                                                    Helper.getMoney(item
                                                            .getBalance()) },
                                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                            null, alignments,
                                            ReportServiceHelper.RTF_NO_BORDER);
                            subTotals = subTotals.add(item.getBalance());
                        }
                        table.addCell(ReportServiceHelper.createTableCell("Sub Totals: " + Helper.getMoney(subTotals),
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                                ReportServiceHelper.RTF_ALIGN_RIGHT, ReportServiceHelper.RTF_BORDER_TOP,
                                9));
                        grandTotals = grandTotals.add(subTotals);
                    }
                    table.addCell(ReportServiceHelper.createTableCell("Grand Totals: " + Helper.getMoney(grandTotals),
                            ReportServiceHelper.RTF_REPORT_HEADER_FONT, null, ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_BORDER_TOP, 9));
                    document.add(table);
                }
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportDate(document,
                        response.getReportGenerationDate(),
                        ReportServiceHelper.REPORT_DATE_FORMAT,
                        ReportServiceHelper.RTF_ALIGN_LEFT);
                if (!Helper.isNullOrEmpty(response.getReportName())) {
                    ReportServiceHelper.addReportTitle(
                            document,
                            response.getReportName() + " for CSD #"
                                    + response.getCsd());
                }

                if (!Helper.isNullOrEmpty(response.getItems())) {
                    PdfPTable table = new PdfPTable(9);
                    table.setWidthPercentage(100);

                    int[] alignments = new int[]{
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT };
                    BigDecimal grandTotals = new BigDecimal("0.00");
                    for (Date auditTime : response.getItems().keySet()) {
                        table.addCell(ReportServiceHelper.createTableCell(
                                DateFormat.getDateInstance(DateFormat.FULL)
                                        .format(auditTime),
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                BaseColor.LIGHT_GRAY,
                                ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_NO_BORDER, 2));

                        for (int i = 0; i < 7; i++) {
                            table.addCell(ReportServiceHelper
                                    .createEmptyPdfCell(1,
                                            ReportServiceHelper.PDF_NO_BORDER));
                        }

                        ReportServiceHelper
                                .addReportTableRow(
                                        table,
                                        new String[]{ "Audit Time",
                                                "Audit User", "C", "Status",
                                                "Lasy Pay", "Deposits",
                                                "Add'l Interest", "Payments",
                                                "Balance" },
                                        ReportServiceHelper.PDF_REPORT_UNDERLINE_FONT,
                                        null, alignments,
                                        ReportServiceHelper.PDF_NO_BORDER);
                        BigDecimal subTotals = new BigDecimal("0.00");
                        for (AccountBalanceReportResponseItem item : response
                                .getItems().get(auditTime)) {
                            String auditTimeStr = auditTimeFormat
                                    .format(auditTime);
                            String lastPay = item.getLastPay() == null ? null
                                    : valueDateFormat.format(item.getLastPay());
                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new String[]{
                                                    auditTimeStr,
                                                    item.getAuditUser(),
                                                    item.getPayCode(),
                                                    item.getStatus(),
                                                    lastPay,
                                                    Helper.getMoney(item
                                                            .getDeposit()),
                                                    Helper.getMoney(item
                                                            .getAdditionalInterest()),
                                                    Helper.getMoney(item
                                                            .getPayment()),
                                                    Helper.getMoney(item
                                                            .getBalance()) },
                                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                            null, alignments,
                                            ReportServiceHelper.PDF_NO_BORDER);
                            subTotals = subTotals.add(item.getBalance());
                        }
                        grandTotals = grandTotals.add(subTotals);
                        table.addCell(ReportServiceHelper.createTableCell("Sub Totals: " + Helper.getMoney(subTotals),
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                                ReportServiceHelper.RTF_ALIGN_RIGHT, ReportServiceHelper.RTF_BORDER_TOP,
                                9));
                    }
                    table.addCell(ReportServiceHelper.createTableCell("Grand Totals: " + Helper.getMoney(grandTotals),
                            ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                            ReportServiceHelper.RTF_ALIGN_RIGHT, ReportServiceHelper.RTF_BORDER_TOP,
                            9));
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
     * Sets the NumberFormat instance for parse number data from audit records
     *
     * @param auditNumberFormat
     *         the NumberFormat instance for parse number data from audit records
     */
    public void setAuditNumberFormat(NumberFormat auditNumberFormat) {
        this.auditNumberFormat = auditNumberFormat;
    }

    /**
     * Sets the DateFormat instance for parse date data from audit records
     *
     * @param auditDateFormat
     *         the DateFormat instance for date number data from audit records
     */
    public void setAuditDateFormat(DateFormat auditDateFormat) {
        this.auditDateFormat = auditDateFormat;
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
        Helper.checkState(auditNumberFormat == null,
                "The auditNumberFormat cannot be null.");
        Helper.checkState(auditDateFormat == null,
                "The auditDateFormat cannot be null.");
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(AccountBalanceReportResponse response) 
            throws ReportGenerationException {
        return null;
    }
}
