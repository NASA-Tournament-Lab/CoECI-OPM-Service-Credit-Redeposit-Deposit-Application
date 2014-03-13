/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountHolder;
import gov.opm.scrd.entities.application.Payment;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates closed account report. It uses local data for
 * generating report and iText/iText RTF for generating reports.
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
@Stateless
@LocalBean
public class ClosedAccountReportService extends BaseReportService implements
        ReportService<ClosedAccountReportRequest, ClosedAccountReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = ClosedAccountReportService.class
            .getName();

    /**
     * Represents the closed status name of new account. It is modified by setter. It is injected by Spring. It can not
     * be null after injected.
     */
    @Autowired
    private String closedAccountStatusName;

    /**
     * Creates a new instance of the {@link ClosedAccountReportService} class.
     */
    public ClosedAccountReportService() {
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
    public ClosedAccountReportResponse getReport(
            ClosedAccountReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(ClosedAccountReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            ClosedAccountReportResponse response = new ClosedAccountReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            ArrayList<ClosedAccountReportResponseEntry> entries = new ArrayList<ClosedAccountReportResponseEntry>();

            List<Account> accounts = getEntityManager()
                    .createQuery(
                            "SELECT e FROM Account e WHERE e.status.name = :status",
                            Account.class)
                    .setParameter("status", closedAccountStatusName)
                    .getResultList();
            for (Account account : accounts) {
                ClosedAccountReportResponseEntry entry = new ClosedAccountReportResponseEntry();
                AccountHolder holder = account.getHolder();
                entry.setName(holder.getFirstName() + " "
                        + holder.getLastName());
                entry.setDateOfBirth(holder.getBirthDate());
                entry.setClaimNumber(account.getClaimNumber());
                entry.setSsn(holder.getSsn());
                entry.setCloseDate(account.getLastActionDate());
                entry.setBillingName(holder.getFirstName() + " " + holder.getLastName());
                long lastActivityTime = 0;
                if (account.getBillingSummary() != null) {
                    lastActivityTime = account.getBillingSummary()
                            .getLastDepositDate().getTime();
                    entry.setBillingDate(account.getBillingSummary()
                            .getFirstBillingDate());
                }

                ArrayList<ClosedAccountReportResponseItem> items = new ArrayList<ClosedAccountReportResponseItem>();
                long lastPaymentTime = 0;
                for (Payment payment : account.getPaymentHistory()) {
                    ClosedAccountReportResponseItem item = new ClosedAccountReportResponseItem();
                    item.setDepositsPost982(payment.getPostDepositAmount());
                    item.setDepositsPre1082(payment.getPreDepositAmount());
                    item.setRedepositsPost982(payment.getPostRedepositAmount());
                    item.setRedepositsPre1082(payment.getPreRedepositAmount());
                    item.setName(entry.getBillingName());

                    long depositTime = payment.getDepositDate().getTime();
                    if (depositTime > lastActivityTime) {
                        lastActivityTime = depositTime;
                    }
                    if (depositTime > lastPaymentTime) {
                        lastPaymentTime = depositTime;
                    }
                    items.add(item);
                }
                if (lastPaymentTime != 0) {
                    entry.setDateOfLastPayment(new Date(lastPaymentTime));
                }
                if (lastActivityTime != 0) {
                    entry.setDateOfLastActivity(new Date(lastActivityTime));
                }
                entry.setItems(items);

                // periods and convertedBy will be null currently.

                entries.add(entry);
            }

            response.setEntries(entries);
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
    public byte[] exportReport(ClosedAccountReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(ClosedAccountReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            if (exportType == ExportType.RTF || exportType == ExportType.DOC) {
                Document document = new Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());
                if (response.getReportGenerationDate() != null) {
                    ReportServiceHelper.addReportDate(document, response.getReportGenerationDate(),
                            ReportServiceHelper.REPORT_DATE_FORMAT, ReportServiceHelper.RTF_ALIGN_CENTER);
                }

                if (response.getEntries() != null) {
                    for (ClosedAccountReportResponseEntry entry : response
                            .getEntries()) {
                        Table dataTable = new Table(7);
                        dataTable.setWidth(100);
                        dataTable.setWidths(new int[]{ 30, 10, 8, 13, 13, 13,
                                13 });
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                "NAME: " + entry.getBillingName(),
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_NO_BORDER, 3));
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                "CLAIM NUMBER: " + entry.getClaimNumber(),
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_NO_BORDER, 4));
                        String birthDate = "DATE OF BIRTH: ";
                        if (entry.getDateOfBirth() != null) {
                            birthDate += ReportServiceHelper.REPORT_DATE_FORMAT
                                    .format(entry.getDateOfBirth());
                        }
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                birthDate,
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_NO_BORDER, 3));
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                "SOCAL SECURITY NUMBER: " + entry.getSsn(),
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_NO_BORDER, 4));
                        String closeDate = "DATE OF CLOSED: ";
                        if (entry.getCloseDate() != null) {
                            closeDate += ReportServiceHelper.REPORT_DATE_FORMAT
                                    .format(entry.getCloseDate());
                        }
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                closeDate,
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_NO_BORDER, 7));
                        dataTable.addCell(ReportServiceHelper.createEmptyCell(
                                2, ReportServiceHelper.RTF_NO_BORDER));

                        int[] dataAlignments = new int[]{
                                ReportServiceHelper.RTF_ALIGN_RIGHT,
                                ReportServiceHelper.RTF_ALIGN_RIGHT,
                                ReportServiceHelper.RTF_ALIGN_RIGHT,
                                ReportServiceHelper.RTF_ALIGN_RIGHT,
                                ReportServiceHelper.RTF_ALIGN_RIGHT, };
                        ReportServiceHelper.addReportTableRow(dataTable,
                                new String[]{ "FERS", "REDEPOSITS POST-982",
                                        "REDEPOSITS PRE-1082",
                                        "DEPOSTIS POST-982",
                                        "DEPOSITS PRE-1082" },
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, dataAlignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                        if (entry.getItems() != null) {
                            for (ClosedAccountReportResponseItem item : entry
                                    .getItems()) {
                                dataTable
                                        .addCell(ReportServiceHelper.createTableCell(
                                                item.getName(),
                                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                                null,
                                                ReportServiceHelper.RTF_ALIGN_LEFT,
                                                ReportServiceHelper.RTF_NO_BORDER,
                                                2));
                                ReportServiceHelper
                                        .addReportTableRow(
                                                dataTable,
                                                new Object[]{
                                                        Helper.getMoney(item
                                                                .getFers()),
                                                        Helper.getMoney(item
                                                                .getRedepositsPost982()),
                                                        Helper.getMoney(item
                                                                .getRedepositsPre1082()),
                                                        Helper.getMoney(item
                                                                .getDepositsPost982()),
                                                        Helper.getMoney(item
                                                                .getDepositsPre1082()) },
                                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                                null,
                                                dataAlignments,
                                                ReportServiceHelper.RTF_NO_BORDER);
                            }
                        }
                        dataTable.addCell(ReportServiceHelper.createEmptyCell(
                                7, ReportServiceHelper.RTF_NO_BORDER));
                        String lastPayment = entry.getDateOfLastPayment() == null ? null
                                : ReportServiceHelper.REPORT_DATE_FORMAT
                                .format(entry.getDateOfLastPayment());
                        ReportServiceHelper.addReportTableRow(dataTable,
                                new String[]{ "DATE OF LAST PAYMENT",
                                        lastPayment },
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, new int[]{
                                ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_ALIGN_RIGHT },
                                ReportServiceHelper.RTF_NO_BORDER);
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                "COVERED BY:",
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_RIGHT,
                                ReportServiceHelper.RTF_NO_BORDER, 2));
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                entry.getCoveredBy(),
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_NO_BORDER, 3));
                        String lastActivity = entry.getDateOfLastActivity() == null ? null :
                                ReportServiceHelper.REPORT_DATE_FORMAT.format(entry.getDateOfLastActivity());
                        ReportServiceHelper.addReportTableRow(dataTable, new Object[]{ "DATE OF LAST ACTIVITY",
                                lastActivity }, ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null, new int[]{
                                ReportServiceHelper.RTF_ALIGN_LEFT, ReportServiceHelper.RTF_ALIGN_RIGHT
                        }, ReportServiceHelper.RTF_NO_BORDER);
                        dataTable.addCell(ReportServiceHelper.createEmptyCell(5, ReportServiceHelper.RTF_NO_BORDER));
                        document.add(dataTable);

                        if (entry.getPeriods() != null) {
                            Table periodTable = new Table(4);
                            periodTable.setWidth(100);
                            periodTable.setWidths(new int[]{ 15, 15, 10, 60 });
                            int[] periodAlignments = new int[]{
                                    ReportServiceHelper.RTF_ALIGN_LEFT,
                                    ReportServiceHelper.RTF_ALIGN_LEFT,
                                    ReportServiceHelper.RTF_ALIGN_LEFT,
                                    ReportServiceHelper.RTF_ALIGN_LEFT };
                            ReportServiceHelper
                                    .addReportTableRow(
                                            periodTable,
                                            new Object[]{ null, null, null,
                                                    "PERIODS OF SERVICE COVERED" },
                                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                            null, periodAlignments,
                                            ReportServiceHelper.PDF_NO_BORDER);
                            ReportServiceHelper
                                    .addReportTableRow(
                                            periodTable,
                                            new Object[]{ "FROM", "TO",
                                                    "TYPE", "" },
                                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                            null, periodAlignments,
                                            ReportServiceHelper.RTF_NO_BORDER);

                            for (CoveredServicePeriod period : entry
                                    .getPeriods()) {
                                String from = period.getFrom() == null ? null
                                        : ReportServiceHelper.REPORT_DATE_FORMAT
                                        .format(period.getFrom());
                                String to = period.getTo() == null ? null
                                        : ReportServiceHelper.REPORT_DATE_FORMAT
                                        .format(period.getTo());
                                ReportServiceHelper
                                        .addReportTableRow(
                                                periodTable,
                                                new String[]{ from, to,
                                                        period.getType(), null },
                                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                                null,
                                                periodAlignments,
                                                ReportServiceHelper.RTF_NO_BORDER);
                            }
                            document.add(periodTable);
                        }
                    }
                }
                com.lowagie.text.Paragraph legend = new com.lowagie.text.Paragraph(
                        "TYPE: R DENOTES REDEPOSIT PERIODS , D DENOTES DEPOSIT PERIODS",
                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT);
                legend.setSpacingBefore(10);
                document.add(legend);
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());
                if (response.getReportGenerationDate() != null) {
                    ReportServiceHelper.addReportDate(document, response.getReportGenerationDate(),
                            ReportServiceHelper.REPORT_DATE_FORMAT, ReportServiceHelper.PDF_ALIGN_CENTER);
                }

                if (response.getEntries() != null) {
                    for (ClosedAccountReportResponseEntry entry : response
                            .getEntries()) {
                        PdfPTable dataTable = new PdfPTable(7);
                        dataTable.setWidthPercentage(100);
                        dataTable.setSpacingBefore(20);
                        dataTable.setWidths(new int[]{ 30, 10, 8, 13, 13, 13,
                                13 });
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                "NAME: " + entry.getBillingName(),
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_NO_BORDER, 3));
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                "CLAIM NUMBER: " + entry.getClaimNumber(),
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_NO_BORDER, 4));
                        String birthDate = "DATE OF BIRTH: ";
                        if (entry.getDateOfBirth() != null) {
                            birthDate += ReportServiceHelper.REPORT_DATE_FORMAT
                                    .format(entry.getDateOfBirth());
                        }
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                birthDate,
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_NO_BORDER, 3));
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                "SOCAL SECURITY NUMBER: " + entry.getSsn(),
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_NO_BORDER, 4));
                        String closeDate = "DATE OF CLOSED: ";
                        if (entry.getCloseDate() != null) {
                            closeDate += ReportServiceHelper.REPORT_DATE_FORMAT
                                    .format(entry.getCloseDate());
                        }
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                closeDate,
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_NO_BORDER, 7));
                        dataTable.addCell(ReportServiceHelper
                                .createEmptyPdfCell(2,
                                        ReportServiceHelper.PDF_NO_BORDER));

                        int[] dataAlignments = new int[]{
                                ReportServiceHelper.PDF_ALIGN_RIGHT,
                                ReportServiceHelper.PDF_ALIGN_RIGHT,
                                ReportServiceHelper.PDF_ALIGN_RIGHT,
                                ReportServiceHelper.PDF_ALIGN_RIGHT,
                                ReportServiceHelper.PDF_ALIGN_RIGHT, };
                        ReportServiceHelper.addReportTableRow(dataTable,
                                new Object[]{ "FERS", "REDEPOSITS POST-982",
                                        "REDEPOSITS PRE-1082",
                                        "DEPOSTIS POST-982",
                                        "DEPOSITS PRE-1082" },
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, dataAlignments,
                                ReportServiceHelper.PDF_NO_BORDER);
                        if (entry.getItems() != null) {
                            for (ClosedAccountReportResponseItem item : entry
                                    .getItems()) {
                                dataTable
                                        .addCell(ReportServiceHelper.createTableCell(
                                                item.getName(),
                                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                                null,
                                                ReportServiceHelper.PDF_ALIGN_LEFT,
                                                ReportServiceHelper.PDF_NO_BORDER,
                                                2));
                                ReportServiceHelper
                                        .addReportTableRow(
                                                dataTable,
                                                new Object[]{
                                                        Helper.getMoney(item
                                                                .getFers()),
                                                        Helper.getMoney(item
                                                                .getRedepositsPost982()),
                                                        Helper.getMoney(item
                                                                .getRedepositsPre1082()),
                                                        Helper.getMoney(item
                                                                .getDepositsPost982()),
                                                        Helper.getMoney(item
                                                                .getDepositsPre1082()) },
                                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                                null,
                                                dataAlignments,
                                                ReportServiceHelper.PDF_NO_BORDER);
                            }
                        }
                        dataTable.addCell(ReportServiceHelper
                                .createEmptyPdfCell(7,
                                        ReportServiceHelper.PDF_NO_BORDER));
                        String lastPayment = entry.getDateOfLastPayment() == null ? null
                                : ReportServiceHelper.REPORT_DATE_FORMAT
                                .format(entry.getDateOfLastPayment());
                        ReportServiceHelper.addReportTableRow(dataTable,
                                new String[]{ "DATE OF LAST PAYMENT",
                                        lastPayment },
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, new int[]{
                                ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_ALIGN_RIGHT },
                                ReportServiceHelper.PDF_NO_BORDER);
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                "COVERED BY:",
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_RIGHT,
                                ReportServiceHelper.PDF_NO_BORDER, 2));
                        dataTable.addCell(ReportServiceHelper.createTableCell(
                                entry.getCoveredBy(),
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_NO_BORDER, 3));
                        String lastActivity = entry.getDateOfLastActivity() == null ? null :
                                ReportServiceHelper.REPORT_DATE_FORMAT.format(entry.getDateOfLastActivity());
                        ReportServiceHelper.addReportTableRow(dataTable, new Object[]{ "DATE OF LAST ACTIVITY",
                                lastActivity }, ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null, new int[]{
                                ReportServiceHelper.PDF_ALIGN_LEFT, ReportServiceHelper.PDF_ALIGN_RIGHT
                        }, ReportServiceHelper.PDF_NO_BORDER);
                        dataTable.addCell(ReportServiceHelper.createEmptyPdfCell(5,
                                ReportServiceHelper.PDF_NO_BORDER));
                        document.add(dataTable);

                        if (entry.getPeriods() != null) {
                            PdfPTable periodTable = new PdfPTable(4);
                            periodTable.setWidthPercentage(100);
                            periodTable.setWidths(new int[]{ 15, 15, 10, 60 });
                            periodTable.setSpacingBefore(10);

                            int[] periodAlignments = new int[]{
                                    ReportServiceHelper.PDF_ALIGN_LEFT,
                                    ReportServiceHelper.PDF_ALIGN_LEFT,
                                    ReportServiceHelper.PDF_ALIGN_LEFT,
                                    ReportServiceHelper.PDF_ALIGN_LEFT };
                            ReportServiceHelper
                                    .addReportTableRow(
                                            periodTable,
                                            new String[]{ null, null, null,
                                                    "PERIODS OF SERVICE COVERED" },
                                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                            null, periodAlignments,
                                            ReportServiceHelper.PDF_NO_BORDER);
                            ReportServiceHelper
                                    .addReportTableRow(
                                            periodTable,
                                            new String[]{ "FROM", "TO",
                                                    "TYPE", null },
                                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                            null, periodAlignments,
                                            ReportServiceHelper.PDF_NO_BORDER);
                            for (CoveredServicePeriod period : entry
                                    .getPeriods()) {
                                String from = period.getFrom() == null ? null
                                        : ReportServiceHelper.REPORT_DATE_FORMAT
                                        .format(period.getFrom());
                                String to = period.getTo() == null ? null
                                        : ReportServiceHelper.REPORT_DATE_FORMAT
                                        .format(period.getTo());
                                ReportServiceHelper
                                        .addReportTableRow(
                                                periodTable,
                                                new String[]{ from, to,
                                                        period.getType(), null },
                                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                                null,
                                                periodAlignments,
                                                ReportServiceHelper.PDF_NO_BORDER);
                            }
                            document.add(periodTable);
                        }
                    }
                }
                com.itextpdf.text.Paragraph legend = new com.itextpdf.text.Paragraph(
                        "TYPE: R DENOTES REDEPOSIT PERIODS , D DENOTES DEPOSIT PERIODS",
                        ReportServiceHelper.PDF_REPORT_CONTENT_FONT);
                legend.setSpacingBefore(10);
                document.add(legend);
                document.close();
            }
            LoggingHelper.logExit(getLogger(), signature, null);
            return outputStream.toByteArray();
        } catch (DocumentException ex) {
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
     * Sets the closed status name of new account.
     *
     * @param closedAccountStatusName
     *         the closed status name of new account.
     */
    public void setClosedAccountStatusName(String closedAccountStatusName) {
        this.closedAccountStatusName = closedAccountStatusName;
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
        Helper.checkState(Helper.isNullOrEmpty(closedAccountStatusName),
                "The closedAccountStatusName cannot be null or empty.");
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(ClosedAccountReportResponse response) throws ReportGenerationException {
        return null;
    }
}
