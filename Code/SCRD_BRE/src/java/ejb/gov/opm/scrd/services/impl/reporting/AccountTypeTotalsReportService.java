/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Cell;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates account type totals report . It uses local data
 * for generating report and iText/iText RTF for generating reports.
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
@Stateless
public class AccountTypeTotalsReportService extends BaseReportService
        implements
        ReportService<AccountTypeTotalsReportRequest, AccountTypeTotalsReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = AccountTypeTotalsReportService.class
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
     * Represents the name of status name of new account. It is modified by setter. It is injected by Spring. It can not
     * be null after injected.
     */
    @Autowired
    private String newAccountStatusName;

    /**
     * Represents the name of status name of active account. It is modified by setter. It is injected by Spring. It can
     * not be null after injected.
     */
    @Autowired
    private String activeAccountStatusName;

    /**
     * Creates a new instance of the {@link AccountTypeTotalsReportService} class.
     */
    public AccountTypeTotalsReportService() {
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
    public AccountTypeTotalsReportResponse getReport(
            AccountTypeTotalsReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(AccountTypeTotalsReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");

        try {
            AccountTypeTotalsReportResponse response = new AccountTypeTotalsReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setCsrsAccounts(new HashMap<String, Integer>());
            response.setFersAccounts(new HashMap<String, Integer>());

            int totalAccounts = 0;
            List<AccountStatus> accountStatuses = getEntityManager()
                    .createQuery("SELECT a FROM AccountStatus a",
                            AccountStatus.class).getResultList();
            for (AccountStatus accountStatus : accountStatuses) {
                String accountCountQuery = "SELECT COUNT(*) FROM Account a WHERE "
                        + "a.status.name = :accountStatus AND a.formType.name = :formType";

                TypedQuery<Long> csrsCountQuery = getEntityManager()
                        .createQuery(accountCountQuery, Long.class);
                csrsCountQuery.setParameter("accountStatus",
                        accountStatus.getName());
                csrsCountQuery.setParameter("formType", csrsRetirementTypeName);
                Integer csrsCount = csrsCountQuery.getSingleResult().intValue();

                response.getCsrsAccounts().put(accountStatus.getName(),
                        csrsCount);
                totalAccounts += csrsCount;

                TypedQuery<Long> fersCountQuery = getEntityManager()
                        .createQuery(accountCountQuery, Long.class);
                fersCountQuery.setParameter("accountStatus",
                        accountStatus.getName());
                fersCountQuery.setParameter("formType", fersRetirementTypeName);
                Integer fersCount = fersCountQuery.getSingleResult().intValue();

                response.getFersAccounts().put(accountStatus.getName(),
                        fersCount);
                totalAccounts += fersCount;
            }

            String unknowAccountCountQuery = "SELECT COUNT(*) FROM Account a "
                    + "WHERE a.status.name = :accountStatus AND a.formType = NULL";
            TypedQuery<Long> unknownNewCountQuery = getEntityManager()
                    .createQuery(unknowAccountCountQuery, Long.class);
            unknownNewCountQuery.setParameter("accountStatus",
                    newAccountStatusName);
            response.setUnknownNewAccounts(unknownNewCountQuery
                    .getSingleResult().intValue());

            TypedQuery<Long> unknownActiveCountQuery = getEntityManager()
                    .createQuery(unknowAccountCountQuery, Long.class);
            unknownActiveCountQuery.setParameter("accountStatus",
                    activeAccountStatusName);
            response.setUnknownActiveAccounts(unknownActiveCountQuery
                    .getSingleResult().intValue());

            response.setUnknownTotal(response.getUnknownNewAccounts()
                    + response.getUnknownActiveAccounts());
            response.setTotalAccounts(totalAccounts
                    + response.getUnknownTotal());

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
    public byte[] exportReport(AccountTypeTotalsReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(AccountTypeTotalsReportResponse response)";
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

                float[] cellWidths = new float[]{ 15, 70, 25 };
                int[] alignments = new int[]{
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT };
                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());
                if (response.getReportGenerationDate() != null) {
                    com.lowagie.text.Paragraph date = new com.lowagie.text.Paragraph(
                            "Printed on "
                                    + DateFormat.getDateInstance(
                                    DateFormat.FULL).format(
                                    response.getReportGenerationDate()),
                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT);
                    date.setAlignment(ReportServiceHelper.RTF_ALIGN_CENTER);
                    date.setSpacingBefore(10);
                    document.add(date);
                }

                Table csrsTable = new Table(3);
                csrsTable.setWidths(cellWidths);
                ReportServiceHelper.addReportTableRow(csrsTable,
                        new Object[]{ "CSRS", "Account Status Description",
                                "# Of Accounts" },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_BORDER_BOTTOM);
                Integer totalCsrsAccounts = 0;
                if (response.getCsrsAccounts() != null) {
                    for (String csrsStatus : response.getCsrsAccounts()
                            .keySet()) {
                        Integer count = response.getCsrsAccounts().get(
                                csrsStatus);
                        ReportServiceHelper.addReportTableRow(csrsTable,
                                new Object[]{ null, csrsStatus, count },
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, alignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                        totalCsrsAccounts += count;
                    }
                }
                addFooter(csrsTable,
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                        "Number of CSRS Accounts", totalCsrsAccounts);
                document.add(csrsTable);

                Table fersTable = new Table(3);
                fersTable.setWidths(cellWidths);
                ReportServiceHelper.addReportTableRow(fersTable,
                        new Object[]{ "FERS", "Account Status Description",
                                "# Of Accounts" },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_BORDER_BOTTOM);
                Integer totalFersAccounts = 0;
                if (response.getFersAccounts() != null) {
                    for (String fersStatus : response.getFersAccounts()
                            .keySet()) {
                        Integer count = response.getFersAccounts().get(
                                fersStatus);
                        ReportServiceHelper.addReportTableRow(fersTable,
                                new Object[]{ null, fersStatus, count },
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, alignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                        totalFersAccounts += count;
                    }
                }
                addFooter(fersTable,
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                        "Number of FERS Accounts", totalFersAccounts);
                document.add(fersTable);

                Table unknownTable = new Table(3);
                unknownTable.setWidths(cellWidths);
                ReportServiceHelper.addReportTableRow(unknownTable,
                        new Object[]{ "Unknown", "Account Status Description",
                                "# Of Accounts" },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.RTF_BORDER_BOTTOM);
                ReportServiceHelper.addReportTableRow(
                        unknownTable,
                        new Object[]{ "", "New Account",
                                response.getUnknownNewAccounts() },
                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(
                        unknownTable,
                        new Object[]{ "", "Active",
                                response.getUnknownActiveAccounts() },
                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                addFooter(unknownTable,
                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                        "Number of Unknown Accounts",
                        response.getUnknownTotal());
                document.add(unknownTable);

                com.lowagie.text.Paragraph sparator = new com.lowagie.text.Paragraph();
                sparator.setSpacingAfter(1);
                document.add(sparator);

                Table totalTable = new Table(3);
                totalTable.setWidths(cellWidths);
                addFooter(totalTable,
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                        "Number of All Accounts", response.getTotalAccounts());
                document.add(totalTable);
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                float[] cellWidths = new float[]{ 15, 70, 25 };
                int[] alignments = new int[]{
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT };
                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());
                if (response.getReportGenerationDate() != null) {
                    com.itextpdf.text.Paragraph date = new com.itextpdf.text.Paragraph(
                            "Printed on "
                                    + DateFormat.getDateInstance(
                                    DateFormat.FULL).format(
                                    response.getReportGenerationDate()),
                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT);
                    date.setAlignment(ReportServiceHelper.PDF_ALIGN_CENTER);
                    date.setSpacingBefore(10);
                    document.add(date);
                }

                PdfPTable csrsTable = new PdfPTable(3);
                csrsTable.setWidths(cellWidths);
                ReportServiceHelper.addReportTableRow(csrsTable,
                        new Object[]{ "CSRS", "Account Status Description",
                                "# Of Accounts" },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_BORDER_BOTTOM);
                Integer totalCsrsAccounts = 0;
                if (response.getCsrsAccounts() != null) {
                    for (String csrsStatus : response.getCsrsAccounts()
                            .keySet()) {
                        Integer count = response.getCsrsAccounts().get(
                                csrsStatus);
                        ReportServiceHelper.addReportTableRow(csrsTable,
                                new Object[]{ null, csrsStatus, count },
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, alignments,
                                ReportServiceHelper.PDF_NO_BORDER);
                        totalCsrsAccounts += count;
                    }
                }
                addFooter(csrsTable,
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                        "Number of CSRS Accounts", totalCsrsAccounts);
                document.add(csrsTable);

                PdfPTable fersTable = new PdfPTable(3);
                fersTable.setWidths(cellWidths);
                ReportServiceHelper.addReportTableRow(fersTable,
                        new Object[]{ "FERS", "Account Status Description",
                                "# Of Accounts" },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_BORDER_BOTTOM);
                Integer totalFersAccounts = 0;
                if (response.getFersAccounts() != null) {
                    for (String fersStatus : response.getFersAccounts()
                            .keySet()) {
                        Integer count = response.getFersAccounts().get(
                                fersStatus);
                        ReportServiceHelper.addReportTableRow(fersTable,
                                new Object[]{ "", fersStatus, count },
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, alignments,
                                ReportServiceHelper.PDF_NO_BORDER);
                        totalFersAccounts += count;
                    }
                }
                addFooter(fersTable,
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                        "Number of FERS Accounts", totalFersAccounts);
                document.add(fersTable);

                PdfPTable unknownTable = new PdfPTable(3);
                unknownTable.setWidths(cellWidths);
                ReportServiceHelper.addReportTableRow(unknownTable,
                        new Object[]{ "Unknown", "Account Status Description",
                                "# Of Accounts" },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                        alignments, ReportServiceHelper.PDF_BORDER_BOTTOM);
                ReportServiceHelper.addReportTableRow(
                        unknownTable,
                        new Object[]{ "", "New Account",
                                response.getUnknownNewAccounts() },
                        ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                ReportServiceHelper.addReportTableRow(
                        unknownTable,
                        new Object[]{ "", "Active",
                                response.getUnknownActiveAccounts() },
                        ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                addFooter(unknownTable,
                        ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                        "Number of Unknown Accounts",
                        response.getUnknownTotal());
                document.add(unknownTable);

                com.itextpdf.text.Paragraph sparator = new com.itextpdf.text.Paragraph();
                sparator.setSpacingAfter(1);
                document.add(sparator);

                PdfPTable totalTable = new PdfPTable(3);
                totalTable.setWidths(cellWidths);
                addFooter(totalTable,
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                        "Number of All Accounts", response.getTotalAccounts());
                document.add(totalTable);
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
     * Sets the the name of CSRS retirement type.
     *
     * @param csrsRetirementTypeName
     *         the name of CSRS retirement type.
     */
    public void setCsrsRetirementTypeName(String csrsRetirementTypeName) {
        this.csrsRetirementTypeName = csrsRetirementTypeName;
    }

    /**
     * Sets the the name of FERS retirement type.
     *
     * @param fersRetirementTypeName
     *         the name of CSRS retirement type.
     */
    public void setFersRetirementTypeName(String fersRetirementTypeName) {
        this.fersRetirementTypeName = fersRetirementTypeName;
    }

    /**
     * Sets the the name of status name of new account.
     *
     * @param newAccountStatusName
     *         name status name of new account.
     */
    public void setNewAccountStatusName(String newAccountStatusName) {
        this.newAccountStatusName = newAccountStatusName;
    }

    /**
     * Sets the the name of status name of active account.
     *
     * @param activeAccountStatusName
     *         name status name of active account.
     */
    public void setActiveAccountStatusName(String activeAccountStatusName) {
        this.activeAccountStatusName = activeAccountStatusName;
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
        Helper.checkState(Helper.isNullOrEmpty(newAccountStatusName),
                "The newAccountStatusName cannot be null or empty.");
    }

    /**
     * Adds the footer style row for the field to the RTF table.
     *
     * @param table
     *         the table.
     * @param font
     *         the font to use.
     * @param name
     *         the field name.
     * @param value
     *         the field value.
     */
    private static void addFooter(Table table, RtfFont font, String name,
                                  Integer value) {
        Cell emptyCell = new Cell();
        emptyCell.setBorder(ReportServiceHelper.RTF_NO_BORDER);
        table.addCell(emptyCell);

        Cell footerNameCell = new Cell();
        footerNameCell.setBorderColorTop(Color.BLACK);
        footerNameCell.setBorderWidthTop(1);
        footerNameCell
                .setHorizontalAlignment(ReportServiceHelper.RTF_ALIGN_LEFT);
        footerNameCell.add(new com.lowagie.text.Phrase(name, font));
        footerNameCell.setBorder(ReportServiceHelper.RTF_BORDER_TOP);
        table.addCell(footerNameCell);

        Cell footerValueCell = new Cell();
        footerValueCell.setBorderColorTop(Color.BLACK);
        footerValueCell.setBorderWidthTop(1);
        if (value != null) {
            footerValueCell
                    .setHorizontalAlignment(ReportServiceHelper.RTF_ALIGN_RIGHT);
            footerValueCell.add(new com.lowagie.text.Phrase(value.toString(),
                    font));
        }
        footerValueCell.setBorder(ReportServiceHelper.RTF_BORDER_TOP);
        table.addCell(footerValueCell);
    }

    /**
     * Adds the footer style row for the field to the PDF table.
     *
     * @param table
     *         the table.
     * @param font
     *         the font to use.
     * @param name
     *         the field name.
     * @param value
     *         the field value.
     */
    private static void addFooter(PdfPTable table, Font font, String name,
                                  Integer value) {
        PdfPCell emptyCell = new PdfPCell();
        emptyCell.setBorder(ReportServiceHelper.PDF_NO_BORDER);
        table.addCell(emptyCell);

        PdfPCell footerNameCell = new PdfPCell();
        footerNameCell.setBorderColorTop(BaseColor.BLACK);
        footerNameCell
                .setHorizontalAlignment(ReportServiceHelper.PDF_ALIGN_LEFT);
        footerNameCell.addElement(new com.itextpdf.text.Phrase(name, font));
        footerNameCell.setBorder(ReportServiceHelper.PDF_BORDER_TOP);
        table.addCell(footerNameCell);

        PdfPCell footerValueCell = new PdfPCell();
        footerValueCell.setBorderColorTop(BaseColor.BLACK);
        if (value != null) {
            com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph(
                    value.toString(), font);
            p.setAlignment(ReportServiceHelper.PDF_ALIGN_RIGHT);
            footerValueCell.addElement(p);
        }
        footerValueCell.setBorder(ReportServiceHelper.PDF_BORDER_TOP);
        table.addCell(footerValueCell);
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(AccountTypeTotalsReportResponse response) throws ReportGenerationException {
        return null;
    }
}
