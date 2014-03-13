/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Cell;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;
import org.jboss.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * This class is the implementation of the ReportService which generates user audit trail report. It uses local data for
 * generating report and iText/iText RTF for generating reports. <p> <strong>Thread Safety:</b> This class is
 * effectively thread - safe after configuration, the configuration is done in a thread - safe manner. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
@Stateless
@LocalBean
public class UserAuditTrailReportService extends BaseReportService
        implements
        ReportService<UserAuditTrailReportRequest, UserAuditTrailReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = UserAuditTrailReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link UserAuditTrailReportService} class.
     */
    public UserAuditTrailReportService() {
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
    public UserAuditTrailReportResponse getReport(
            UserAuditTrailReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(UserAuditTrailReportRequest request)";
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

            UserAuditTrailReportResponse response = new UserAuditTrailReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setItems(new ArrayList<UserAuditTrailReportResponseItem>());

            TypedQuery<AuditRecord> auditRecordsQuery = getEntityManager()
                    .createQuery(
                            "SELECT DISTINCT a FROM AuditRecord a "
                                    + "JOIN a.parameters p WHERE p.itemId = :id",
                            AuditRecord.class);
            auditRecordsQuery.setParameter("id", account.getId());
            List<AuditRecord> auditRecords = auditRecordsQuery.getResultList();

            for (AuditRecord record : auditRecords) {
                UserAuditTrailReportResponseItem item = new UserAuditTrailReportResponseItem();
                item.setActivity(record.getActionName());
                item.setAuditDateTime(record.getDate());
                item.setDescription(record.getDescription());
                for (AuditParameterRecord parameter : record.getParameters()) {
                    if (parameter.getPropertyName().equals("databaseTable")) {
                        item.setDatabaseTable(parameter.getNewValue());
                        break;
                    }
                }

                User user = getEntityManager()
                        .createQuery(
                                "SELECT u FROM User u WHERE u.username = :username",
                                User.class)
                        .setParameter("username", record.getUsername())
                        .getSingleResult();
                item.setRole(user.getRole().getName());
                Long supervisorId = user.getSupervisorId();
                if (supervisorId != null) {
                    User supervisor = getEntityManager().find(User.class, supervisorId);
                    if (supervisor != null && supervisor.getRole() != null) {
                        item.setSupervisingRole(supervisor.getRole()
                            .getName());
                    }
                }
                item.setEmail(user.getEmail());
                item.setTelephone(user.getTelephone());
                item.setCsd(request.getCsd());
                item.setDescription(record.getActionName());
                item.setUserDescription("Changed by: " + user.getFirstName() + " " + user.getLastName());

                response.getItems().add(item);
            }

            LoggingHelper.logExit(logger, signature, new Object[]{ response });
            return response;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                    new ReportGenerationException(
                            "The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            e.printStackTrace();
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
    public byte[] exportReport(UserAuditTrailReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(UserAuditTrailReportResponse response)";
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
                        ReportServiceHelper.RTF_ALIGN_LEFT);

                if (response.getItems() != null) {
                    HashMap<String, ArrayList<UserAuditTrailReportResponseItem>> map =
                            new HashMap<String, ArrayList<UserAuditTrailReportResponseItem>>();
                    for (UserAuditTrailReportResponseItem item : response.getItems()) {
                        if (map.get(item.getUserDescription()) == null) {
                            map.put(item.getUserDescription(), new ArrayList<UserAuditTrailReportResponseItem>());
                        }
                        map.get(item.getUserDescription()).add(item);
                    }

                    Table table = new Table(5);
                    table.setWidths(new int[]{ 7, 20, 7, 6, 60 });
                    table.setSpacing(2);
                    table.setBorder(ReportServiceHelper.RTF_NO_BORDER);
                    table.setWidth(100);
                    int[] alignments = new int[5];
                    for (int i = 0; i < 5; i++) {
                        alignments[i] = ReportServiceHelper.RTF_ALIGN_LEFT;
                    }

                    RtfFont headerFontUnderline = new RtfFont(ReportServiceHelper.REPORT_FONT_FAMILY, 8,
                            RtfFont.UNDERLINE | RtfFont.BOLD);
                    DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.ENGLISH);
                    for (String key : map.keySet()) {
                        ArrayList<UserAuditTrailReportResponseItem> items = map.get(key);
                        Cell headerCell = ReportServiceHelper.createTableCell(ReportServiceHelper.REPORT_DATE_FORMAT.
                                format(items.get(0).getAuditDateTime()) + " - " + key, headerFontUnderline, null,
                                ReportServiceHelper.RTF_ALIGN_LEFT, null, 5);
                        headerCell.setBorderWidthTop(1);
                        headerCell.setBorderWidthLeft(1);
                        headerCell.setBorderWidthRight(1);
                        table.addCell(headerCell);
                        String[] titles = new String[]{ "Time", "Database Table", "Activity", "CSD #",
                                "Description of Change" };
                        for (int i = 0; i < titles.length; i++) {
                            Cell titleCell = ReportServiceHelper.createTableCell(titles[i],
                                    ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                                    alignments[i], null, 1);
                            titleCell.setBorderWidthTop(0);
                            if (i == 0) {
                                titleCell.setBorderWidthLeft(1);
                            } else if (i == titles.length - 1) {
                                titleCell.setBorderWidthRight(1);
                            }
                            titleCell.setBorderWidthBottom(1);
                            table.addCell(titleCell);
                        }
                        for (UserAuditTrailReportResponseItem item : items) {
                            ReportServiceHelper.addReportTableRow(table, new Object[]{
                                    timeFormat.format(item.getAuditDateTime()),
                                    item.getDatabaseTable(),
                                    item.getActivity(),
                                    item.getCsd(),
                                    item.getDescription()
                            }, ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null, alignments,
                                    ReportServiceHelper.RTF_NO_BORDER);
                        }
                    }
                    document.add(table);
                }
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
                        ReportServiceHelper.PDF_ALIGN_CENTER);

                if (response.getItems() != null) {
                    HashMap<String, ArrayList<UserAuditTrailReportResponseItem>> map =
                            new HashMap<String, ArrayList<UserAuditTrailReportResponseItem>>();
                    for (UserAuditTrailReportResponseItem item : response.getItems()) {
                        if (map.get(item.getUserDescription()) == null) {
                            map.put(item.getUserDescription(), new ArrayList<UserAuditTrailReportResponseItem>());
                        }
                        map.get(item.getUserDescription()).add(item);
                    }

                    PdfPTable table = new PdfPTable(5);
                    table.setSpacingBefore(20);
                    table.setWidths(new int[]{ 7, 20, 7, 6, 60 });
                    table.setWidthPercentage(100);
                    int[] alignments = new int[5];
                    for (int i = 0; i < 5; i++) {
                        alignments[i] = ReportServiceHelper.RTF_ALIGN_LEFT;
                    }

                    Font headerFontUnderline = new Font(Font.FontFamily.HELVETICA, 8,
                            Font.UNDERLINE | Font.BOLD);
                    DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.ENGLISH);
                    for (String key : map.keySet()) {
                        ArrayList<UserAuditTrailReportResponseItem> items = map.get(key);
                        PdfPCell headerCell = ReportServiceHelper.createTableCell(
                                ReportServiceHelper.REPORT_DATE_FORMAT.
                                        format(items.get(0).getAuditDateTime()) + " - " + key, headerFontUnderline, null,
                                ReportServiceHelper.PDF_ALIGN_LEFT, null, 5);
                        headerCell.setBorderWidth(1);
                        headerCell.setBorderWidthBottom(0);
                        table.addCell(headerCell);
                        String[] titles = new String[]{ "Time", "Database Table", "Activity", "CSD #",
                                "Description of Change" };
                        for (int i = 0; i < titles.length; i++) {
                            PdfPCell titleCell = ReportServiceHelper.createTableCell(titles[i],
                                    ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                                    alignments[i], null, 1);
                            titleCell.setBorderWidth(0);
                            if (i == 0) {
                                titleCell.setBorderWidthLeft(1);
                            } else if (i == titles.length - 1) {
                                titleCell.setBorderWidthRight(1);
                            }
                            titleCell.setBorderWidthBottom(1);
                            table.addCell(titleCell);
                        }
                        for (UserAuditTrailReportResponseItem item : items) {
                            ReportServiceHelper.addReportTableRow(table, new Object[]{
                                    timeFormat.format(item.getAuditDateTime()),
                                    item.getDatabaseTable(),
                                    item.getActivity(),
                                    item.getCsd(),
                                    item.getDescription()
                            }, ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null, alignments,
                                    ReportServiceHelper.PDF_NO_BORDER);
                        }
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
    public byte[] renderChart(UserAuditTrailReportResponse response) throws ReportGenerationException {
        return null;
    }
}
