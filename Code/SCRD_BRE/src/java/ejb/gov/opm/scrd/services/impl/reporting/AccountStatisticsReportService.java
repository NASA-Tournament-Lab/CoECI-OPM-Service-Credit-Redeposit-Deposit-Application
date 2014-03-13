/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates account statistics report. It uses local data
 * for generating report and iText/iText RTF for generating reports <p><strong>Thread-safety:</strong> This class is
 * effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
@Stateless
@LocalBean
public class AccountStatisticsReportService extends BaseReportService
        implements
        ReportService<AccountStatisticsReportRequest, AccountStatisticsReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = AccountStatisticsReportService.class
            .getName();

    /**
     * Represents the name of CSRS retirement type. It is modified by setter. It is injected by Spring. It can not be
     * null after injected.
     */
    @Autowired
    private String csrsRetirementTypeName;

    /**
     * Represents the name of FERS retirement type. It is modified by setter. It is injected by Spring. It can not be
     * null after injected.
     */
    @Autowired
    private String fersRetirementTypeName;

    /**
     * Represents the active status name of new account. It is modified by setter. It is injected by Spring. It can not
     * be null after injected.
     */
    @Autowired
    private String activeAccountStatusName;

    /**
     * Represents the history status name of new account. It is modified by setter. It is injected by Spring. It can not
     * be null after injected.
     */
    @Autowired
    private String historyAccountStatusName;

    /**
     * Represents the closed status name of new account. It is modified by setter. It is injected by Spring. It can not
     * be null after injected.
     */
    @Autowired
    private String closedAccountStatusName;

    /**
     * Creates a new instance of the {@link AccountStatisticsReportService} class.
     */
    public AccountStatisticsReportService() {
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
    public AccountStatisticsReportResponse getReport(
            AccountStatisticsReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(AccountStatisticsReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            AccountStatisticsReportResponse response = new AccountStatisticsReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());

            String accountQuery = "SELECT a FROM Account a WHERE a.status.name = :accountStatus";
            List<Account> openAccounts = getEntityManager()
                    .createQuery(accountQuery, Account.class)
                    .setParameter("accountStatus", activeAccountStatusName)
                    .getResultList();
            List<Account> historyAccounts = getEntityManager()
                    .createQuery(accountQuery, Account.class)
                    .setParameter("accountStatus", historyAccountStatusName)
                    .getResultList();
            List<Account> closedAccounts = getEntityManager()
                    .createQuery(accountQuery, Account.class)
                    .setParameter("accountStatus", closedAccountStatusName)
                    .getResultList();

            int totalActiveCsrs = 0;
            int totalActiveFers = 0;
            int totalHistoryCsrs = 0;
            int totalHistoryFers = 0;
            int totalActiveNoPayment = 0;
            int totalHistoryNoPayment = 0;
            int totalClosedNoPayment = 0;
            int totalActiveLastYear = 0;
            int totalHistoryLastYear = 0;
            int totalClosedLastYear = 0;

            Calendar now = Calendar.getInstance();
            Calendar lastYear = Calendar.getInstance();
            lastYear.set(now.get(Calendar.YEAR) - 1, now.get(Calendar.MONTH),
                    now.get(Calendar.DATE));
            for (Account account : openAccounts) {
                if (account.getFormType() != null
                        && account.getFormType().getName()
                        .equals(csrsRetirementTypeName)) {
                    totalActiveCsrs++;
                } else if (account.getFormType() != null
                        && account.getFormType().getName()
                        .equals(fersRetirementTypeName)) {
                    totalActiveFers++;
                }
                if (account.getPaymentHistory().isEmpty()) {
                    totalActiveNoPayment++;
                } else {
                    for (Payment payment : account.getPaymentHistory()) {
                        if (payment.getPaymentStatus().getName()
                                .equals("ORDINARY")
                                && payment.getDepositDate().getTime() > lastYear
                                .getTimeInMillis()) {
                            totalActiveLastYear++;
                            break;
                        }
                    }
                }
            }
            for (Account account : historyAccounts) {
                if (account.getFormType() != null
                        && account.getFormType().getName()
                        .equals(csrsRetirementTypeName)) {
                    totalHistoryCsrs++;
                } else if (account.getFormType() != null
                        && account.getFormType().getName()
                        .equals(fersRetirementTypeName)) {
                    totalHistoryFers++;
                }
                if (account.getPaymentHistory().isEmpty()) {
                    totalHistoryNoPayment++;
                } else {
                    for (Payment payment : account.getPaymentHistory()) {
                        if (payment.getPaymentStatus().getName()
                                .equals("ORDINARY")
                                && payment.getDepositDate().getTime() > lastYear
                                .getTimeInMillis()) {
                            totalHistoryLastYear++;
                            break;
                        }
                    }
                }
            }
            for (Account account : closedAccounts) {
                if (account.getPaymentHistory().isEmpty()) {
                    totalClosedNoPayment++;
                } else {
                    for (Payment payment : account.getPaymentHistory()) {
                        if (payment.getPaymentStatus().getName()
                                .equals("ORDINARY")
                                && payment.getDepositDate().getTime() > lastYear
                                .getTimeInMillis()) {
                            totalClosedLastYear++;
                            break;
                        }
                    }
                }
            }

            response.setTotalAccountsLastYear(totalClosedLastYear
                    + totalHistoryLastYear + totalActiveLastYear);
            response.setTotalAccountsNoPostedPayments(totalActiveNoPayment
                    + totalHistoryNoPayment + totalClosedNoPayment);
            response.setTotalCsrsAccounts(totalActiveCsrs + totalHistoryCsrs);
            response.setTotalFersAccounts(totalActiveFers + totalHistoryFers);
            response.setTotalActiveCsrsAccounts(totalActiveCsrs);
            response.setTotalActiveFersAccounts(totalActiveFers);
            response.setTotalHistoryClosedAccountsLastYear(totalClosedLastYear
                    + totalHistoryLastYear);
            response.setTotalHistoryClosedAccountsNoPostedPayment(totalClosedNoPayment
                    + totalHistoryNoPayment);
            response.setTotalHistoryCsrsAccounts(totalHistoryCsrs);
            response.setTotalHistoryFersAccounts(totalHistoryFers);
            response.setTotalOpenAccountsLastYear(totalActiveLastYear);
            response.setTotalOpenAccountsNoPostedPayments(totalActiveNoPayment);

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
    public byte[] exportReport(AccountStatisticsReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(AccountStatisticsReportResponse response)";
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

                if (!Helper.isNullOrEmpty(response.getReportName())) {
                    String title = response.getReportName();
                    if (response.getReportGenerationDate() != null) {
                        title += " As Of "
                                + ReportServiceHelper.REPORT_DATE_FORMAT
                                .format(response
                                        .getReportGenerationDate());
                    }
                    ReportServiceHelper.addReportTitle(document, title);
                }

                Table table = new Table(3);
                table.setWidths(new int[]{ 55, 5, 40 });
                table.setWidth(100);
                table.setSpacing(10);
                table.setBorder(ReportServiceHelper.RTF_NO_BORDER);
                int[] alignments = new int[]{
                        ReportServiceHelper.RTF_ALIGN_RIGHT, 0,
                        ReportServiceHelper.RTF_ALIGN_LEFT };
                ReportServiceHelper.addReportTableRow(table,
                        new Object[]{ "Total Active CSRS Accounts", null,
                                response.getTotalActiveCsrsAccounts() },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table,
                        new Object[]{ "Total Active FERS Accounts", null,
                                response.getTotalActiveFersAccounts() },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table,
                        new Object[]{ "Total History CSRS Accounts", null,
                                response.getTotalHistoryCsrsAccounts() },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table,
                        new Object[]{ "Total History FERS Accounts", null,
                                response.getTotalHistoryFersAccounts() },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(
                        table,
                        new Object[]{ "Total CSRS Accounts", null,
                                response.getTotalCsrsAccounts() },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(
                        table,
                        new Object[]{ "Total FERS Accounts", null,
                                response.getTotalFersAccounts() },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table, new Object[]{
                        "Total Open Accounts with no Posted Payments", null,
                        response.getTotalOpenAccountsNoPostedPayments() },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper
                        .addReportTableRow(
                                table,
                                new Object[]{
                                        "Total History & Closed Accounts with no Posted Payments",
                                        null,
                                        response.getTotalHistoryClosedAccountsNoPostedPayment() },
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                null, alignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table, new Object[]{
                        "Total All Accounts with no Posted Payments", null,
                        response.getTotalAccountsNoPostedPayments() },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper
                        .addReportTableRow(
                                table,
                                new Object[]{
                                        "Total Open Accounts with Payments within past 12 months",
                                        null,
                                        response.getTotalOpenAccountsLastYear() },
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                null, alignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper
                        .addReportTableRow(
                                table,
                                new Object[]{
                                        "Total History & Closed Accounts with Payments within past 12 months",
                                        null,
                                        response.getTotalHistoryClosedAccountsLastYear() },
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                null, alignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper
                        .addReportTableRow(
                                table,
                                new Object[]{
                                        "Total All Accounts with Payments within past 12 months",
                                        null,
                                        response.getTotalAccountsLastYear() },
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                null, alignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                document.add(table);
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                if (!Helper.isNullOrEmpty(response.getReportName())) {
                    String title = response.getReportName();
                    if (response.getReportGenerationDate() != null) {
                        title += " As Of "
                                + ReportServiceHelper.REPORT_DATE_FORMAT
                                .format(response
                                        .getReportGenerationDate());
                    }
                    ReportServiceHelper.addReportTitle(document, title);
                }

                PdfPTable table = new PdfPTable(3);
                table.setSpacingBefore(20);
                table.setWidthPercentage(100);
                table.setWidths(new int[]{ 55, 5, 40 });
                int[] alignments = new int[]{
                        ReportServiceHelper.PDF_ALIGN_RIGHT, 0,
                        ReportServiceHelper.PDF_ALIGN_LEFT };
                ReportServiceHelper.addReportTableRow(table,
                        new Object[]{ "Total Active CSRS Accounts", null,
                                response.getTotalActiveCsrsAccounts() },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table,
                        new Object[]{ "Total Active FERS Accounts", null,
                                response.getTotalActiveFersAccounts() },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table,
                        new Object[]{ "Total History CSRS Accounts", null,
                                response.getTotalHistoryCsrsAccounts() },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table,
                        new Object[]{ "Total History FERS Accounts", null,
                                response.getTotalHistoryFersAccounts() },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(
                        table,
                        new Object[]{ "Total CSRS Accounts", null,
                                response.getTotalCsrsAccounts() },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(
                        table,
                        new Object[]{ "Total FERS Accounts", null,
                                response.getTotalFersAccounts() },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table, new Object[]{
                        "Total Open Accounts with no Posted Payments", null,
                        response.getTotalOpenAccountsNoPostedPayments() },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper
                        .addReportTableRow(
                                table,
                                new Object[]{
                                        "Total History & Closed Accounts with no Posted Payments",
                                        null,
                                        response.getTotalHistoryClosedAccountsNoPostedPayment() },
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                null, alignments,
                                ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(table, new Object[]{
                        "Total All Accounts with no Posted Payments", null,
                        response.getTotalAccountsNoPostedPayments() },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper
                        .addReportTableRow(
                                table,
                                new Object[]{
                                        "Total Open Accounts with Payments within past 12 months",
                                        null,
                                        response.getTotalOpenAccountsLastYear() },
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                null, alignments,
                                ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper
                        .addReportTableRow(
                                table,
                                new Object[]{
                                        "Total History & Closed Accounts with Payments within past 12 months",
                                        null,
                                        response.getTotalHistoryClosedAccountsLastYear() },
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                null, alignments,
                                ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper
                        .addReportTableRow(
                                table,
                                new Object[]{
                                        "Total All Accounts with Payments within past 12 months",
                                        null,
                                        response.getTotalAccountsLastYear() },
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                null, alignments,
                                ReportServiceHelper.PDF_NO_BORDER);
                document.add(table);
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
     * Sets the name of CSRS retirement type.
     *
     * @param csrsRetirementTypeName
     *         the name of CSRS retirement type.
     */
    public void setCsrsRetirementTypeName(String csrsRetirementTypeName) {
        this.csrsRetirementTypeName = csrsRetirementTypeName;
    }

    /**
     * Sets the name of FERS retirement type.
     *
     * @param fersRetirementTypeName
     *         the name of FERS retirement type.
     */
    public void setFersRetirementTypeName(String fersRetirementTypeName) {
        this.fersRetirementTypeName = fersRetirementTypeName;
    }

    /**
     * Sets the active status name of new account.
     *
     * @param activeAccountStatusName
     *         the active status name of new account.
     */
    public void setActiveAccountStatusName(String activeAccountStatusName) {
        this.activeAccountStatusName = activeAccountStatusName;
    }

    /**
     * Sets the history status name of new account.
     *
     * @param historyAccountStatusName
     *         the history status name of new account.
     */
    public void setHistoryAccountStatusName(String historyAccountStatusName) {
        this.historyAccountStatusName = historyAccountStatusName;
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
        Helper.checkState(Helper.isNullOrEmpty(csrsRetirementTypeName),
                "The csrsRetirementTypeName cannot be null or empty.");
        Helper.checkState(Helper.isNullOrEmpty(fersRetirementTypeName),
                "The fersRetirementTypeName cannot be null or empty.");
        Helper.checkState(Helper.isNullOrEmpty(activeAccountStatusName),
                "The activeAccountStatusName cannot be null or empty.");
        Helper.checkState(Helper.isNullOrEmpty(historyAccountStatusName),
                "The historyAccountStatusName cannot be null or empty.");
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
    public byte[] renderChart(AccountStatisticsReportResponse response) throws ReportGenerationException {
        return null;
    }
}
