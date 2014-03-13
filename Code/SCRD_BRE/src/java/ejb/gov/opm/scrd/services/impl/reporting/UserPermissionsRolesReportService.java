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
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates user permissions report. It uses local data for
 * generating report and iText/iText RTF for generating reports.
 * <p> <strong>Thread-safety:</strong> This class is effectively thread - safe after configuration, the configuration is
 * done in a thread - safe manner. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
@Stateless
@LocalBean
public class UserPermissionsRolesReportService extends BaseReportService
        implements
        ReportService<UserPermissionsRolesReportRequest, UserPermissionsRolesReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = UserPermissionsRolesReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link UserPermissionsRolesReportService} class.
     */
    public UserPermissionsRolesReportService() {
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
    public UserPermissionsRolesReportResponse getReport(
            UserPermissionsRolesReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(UserPermissionsRolesReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");

        try {
            UserPermissionsRolesReportResponse response = new UserPermissionsRolesReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setItems(new ArrayList<UserPermissionsRolesReportResponseItem>());

            List<User> users = getEntityManager().createQuery(
                    "SELECT u FROM User u", User.class).getResultList();
            for (User user : users) {
                UserPermissionsRolesReportResponseItem item = new UserPermissionsRolesReportResponseItem();
                item.setRole(user.getRole().getName());
                item.setUserName(user.getUsername());
                item.setOpmName(user.getLastName());
                item.setTelephone(user.getTelephone());
                item.setUserStatus(user.getStatus().getName());
                Long supervisorId = user.getSupervisorId();
                if (supervisorId != null) {
                    User supervisor = getEntityManager().find(User.class, supervisorId);
                    if (supervisor != null) {
                        item.setSupervisor(supervisor.getUsername());
                    }
                }
                item.setEmail(user.getEmail());
                response.getItems().add(item);
            }

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
    public byte[] exportReport(UserPermissionsRolesReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(UserPermissionsRolesReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");

        try {
            if (response.getItems() == null) {
                LoggingHelper.logExit(getLogger(), signature, null);
                return new byte[0];
            }
            HashMap<String, ArrayList<UserPermissionsRolesReportResponseItem>> map = new HashMap<String, ArrayList<UserPermissionsRolesReportResponseItem>>();
            for (UserPermissionsRolesReportResponseItem item : response
                    .getItems()) {
                if (map.get(item.getRole()) == null) {
                    ArrayList<UserPermissionsRolesReportResponseItem> items = new ArrayList<UserPermissionsRolesReportResponseItem>();
                    map.put(item.getRole(), items);
                }
                map.get(item.getRole()).add(item);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            if (exportType == ExportType.DOC || exportType == ExportType.RTF) {
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                if (response.getReportGenerationDate() != null) {
                    com.lowagie.text.Paragraph date = new com.lowagie.text.Paragraph(
                            DateFormat.getDateInstance(DateFormat.LONG).format(
                                    response.getReportGenerationDate()),
                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT);
                    date.setAlignment(ReportServiceHelper.RTF_ALIGN_LEFT);
                    document.add(date);
                }
                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());

                for (String role : map.keySet()) {
                    Table table = new Table(6);
                    table.setWidth(100);

                    table.addCell(ReportServiceHelper.createTableCell(role,
                            ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                            Color.LIGHT_GRAY,
                            ReportServiceHelper.RTF_ALIGN_LEFT, null, 6));
                    int[] alignments = new int[6];
                    for (int i = 0; i < 6; i++) {
                        alignments[i] = ReportServiceHelper.RTF_ALIGN_LEFT;
                    }
                    ReportServiceHelper.addReportTableRow(table, new String[]{
                            "User Name", "OPM Name", "Phone Number", "Email",
                            "User Status", "Supervisor" },
                            ReportServiceHelper.RTF_REPORT_HEADER_FONT, null,
                            alignments, null);
                    for (UserPermissionsRolesReportResponseItem item : map
                            .get(role)) {
                        ReportServiceHelper.addReportTableRow(
                                table,
                                new String[]{ item.getUserName(),
                                        item.getOpmName(), item.getTelephone(),
                                        item.getEmail(), item.getUserStatus(),
                                        item.getSupervisor() },
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, alignments,
                                ReportServiceHelper.RTF_NO_BORDER);
                    }
                    document.add(table);
                }

                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                if (response.getReportGenerationDate() != null) {
                    com.itextpdf.text.Paragraph date = new com.itextpdf.text.Paragraph(
                            DateFormat.getDateInstance(DateFormat.LONG).format(
                                    response.getReportGenerationDate()),
                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT);
                    date.setAlignment(ReportServiceHelper.PDF_ALIGN_LEFT);
                    document.add(date);
                }
                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());

                for (String role : map.keySet()) {
                    PdfPTable table = new PdfPTable(6);
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(20);

                    table.addCell(ReportServiceHelper.createTableCell(role,
                            ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                            BaseColor.LIGHT_GRAY,
                            ReportServiceHelper.PDF_ALIGN_LEFT, null, 6));
                    int[] alignments = new int[6];
                    for (int i = 0; i < 6; i++) {
                        alignments[i] = ReportServiceHelper.PDF_ALIGN_LEFT;
                    }

                    ReportServiceHelper.addReportTableRow(table, new String[]{
                            "User Name", "OPM Name", "Phone Number", "Email",
                            "User Status", "Supervisor" },
                            ReportServiceHelper.PDF_REPORT_HEADER_FONT, null,
                            alignments, null);
                    for (UserPermissionsRolesReportResponseItem item : map
                            .get(role)) {
                        ReportServiceHelper.addReportTableRow(
                                table,
                                new String[]{ item.getUserName(),
                                        item.getOpmName(), item.getTelephone(),
                                        item.getEmail(), item.getUserStatus(),
                                        item.getSupervisor() },
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, alignments,
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
    public byte[] renderChart(UserPermissionsRolesReportResponse response) throws ReportGenerationException {
        return null;
    }
}
