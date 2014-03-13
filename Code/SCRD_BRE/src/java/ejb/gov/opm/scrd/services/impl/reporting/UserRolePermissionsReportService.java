/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.RolePermission;
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
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates user role permissions report. It uses local
 * data for generating report and iText/iText RTF for generating reports. <p> <strong>Thread-safety:</strong> This class
 * is effectively thread - safe after configuration, the configuration is done in a thread - safe manner. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
@Stateless
@LocalBean
public class UserRolePermissionsReportService extends BaseReportService
        implements
        ReportService<UserRolePermissionsReportRequest, UserRolePermissionsReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = UserRolePermissionsReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link UserRolePermissionsReportService} class.
     */
    public UserRolePermissionsReportService() {
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
    public UserRolePermissionsReportResponse getReport(
            UserRolePermissionsReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(UserRolePermissionsReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            UserRolePermissionsReportResponse response = new UserRolePermissionsReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setRoles(new ArrayList<String>());
            response.setRoleDescriptions(new ArrayList<String>());
            response.setPermissions(new ArrayList<String>());
            response.setAllowedPermissions(new HashMap<String, List<String>>());

            TypedQuery<RolePermission> query = getEntityManager().createQuery(
                    "SELECT r from RolePermission r", RolePermission.class);
            List<RolePermission> rolePermissions = query.getResultList();

            for (RolePermission rolePermission : rolePermissions) {
                String role = rolePermission.getRole();
                String description = rolePermission.getAction();
                String permission = rolePermission.getAction();

                if (!response.getRoles().contains(role)) {
                    response.getRoles().add(role);
                    response.getRoleDescriptions().add(description);
                    response.getAllowedPermissions().put(role,
                            new ArrayList<String>());
                }
                if (!response.getPermissions().contains(permission)) {
                    response.getPermissions().add(permission);
                }

                response.getAllowedPermissions().get(role).add(permission);
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
    public byte[] exportReport(UserRolePermissionsReportResponse response,
                               ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(UserRolePermissionsReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");

        try {
            if (Helper.isNullOrEmpty(response.getAllowedPermissions())
                    || Helper.isNullOrEmpty(response.getRoleDescriptions())
                    || Helper.isNullOrEmpty(response.getRoles())
                    || Helper.isNullOrEmpty(response.getPermissions())) {
                LoggingHelper.logExit(getLogger(), signature, null);
                return new byte[0];
            }

            if (response.getRoleDescriptions().size() != response.getRoles()
                    .size()) {
                throw LoggingHelper.logException(getLogger(), signature,
                        new ReportGenerationException(
                                "The roles or roleDescriptions is invalid."));
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            int columnCount = response.getPermissions().size() + 2;
            int[] alignments = new int[columnCount];
            int permissionColumnWidth = 65 / response.getPermissions().size();
            int[] cellWidths = new int[columnCount];
            cellWidths[0] = 10;
            cellWidths[1] = 25;
            for (int i = 2; i < columnCount; i++) {
                cellWidths[i] = permissionColumnWidth;
            }
            for (int i = 0; i < columnCount; i++) {
                alignments[i] = exportType == ExportType.DOC
                        || exportType == ExportType.RTF ? ReportServiceHelper.RTF_ALIGN_LEFT
                        : ReportServiceHelper.PDF_ALIGN_LEFT;
            }

            if (exportType == ExportType.DOC || exportType == ExportType.RTF) {
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());
                if (response.getReportGenerationDate() != null) {
                    com.lowagie.text.Paragraph date = new com.lowagie.text.Paragraph(
                            DateFormat.getDateInstance(DateFormat.FULL).format(
                                    response.getReportGenerationDate()),
                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT);
                    date.setAlignment(ReportServiceHelper.RTF_ALIGN_LEFT);
                    document.add(date);
                }

                Table table = new Table(columnCount);
                table.setWidth(100);
                table.setWidths(cellWidths);

                String[] headColumns = new String[columnCount];
                headColumns[0] = "Role";
                headColumns[1] = "User Role Description";
                for (int i = 0; i < response.getPermissions().size(); i++) {
                    headColumns[i + 2] = response.getPermissions().get(i);
                }
                ReportServiceHelper.addReportTableRow(table, headColumns,
                        ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null,
                        alignments, ReportServiceHelper.RTF_NO_BORDER);
                for (int j = 0; j < response.getRoles().size(); j++) {
                    String role = response.getRoles().get(j);
                    List<String> permissions = response.getAllowedPermissions()
                            .get(role);
                    table.addCell(ReportServiceHelper.createTableCell(role,
                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null,
                            ReportServiceHelper.RTF_ALIGN_LEFT, null, 1));
                    table.addCell(ReportServiceHelper.createTableCell(response
                            .getRoleDescriptions().get(j),
                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null,
                            ReportServiceHelper.RTF_ALIGN_LEFT, null, 1));
                    for (int i = 0; i < response.getPermissions().size(); i++) {
                        Color bgColor = i % 2 == 0 ? Color.LIGHT_GRAY : null;
                        if (permissions == null) {
                            table.addCell(ReportServiceHelper
                                    .createTableCell(
                                            "-",
                                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                            bgColor,
                                            ReportServiceHelper.RTF_ALIGN_CENTER,
                                            null, 1));
                        } else {
                            String permission = response.getPermissions()
                                    .get(i);
                            String column = permissions.contains(permission) ? "X"
                                    : "-";
                            table.addCell(ReportServiceHelper
                                    .createTableCell(
                                            column,
                                            ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                            bgColor,
                                            ReportServiceHelper.RTF_ALIGN_CENTER,
                                            null, 1));
                        }
                    }
                }
                document.add(table);
                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addReportTitle(document,
                        response.getReportName());
                if (response.getReportGenerationDate() != null) {
                    com.itextpdf.text.Paragraph date = new com.itextpdf.text.Paragraph(
                            DateFormat.getDateInstance(DateFormat.FULL).format(
                                    response.getReportGenerationDate()),
                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT);
                    date.setAlignment(ReportServiceHelper.PDF_ALIGN_LEFT);
                    document.add(date);
                }

                PdfPTable table = new PdfPTable(columnCount);
                table.setSpacingBefore(20);
                table.setWidthPercentage(100);
                table.setWidths(cellWidths);

                ReportServiceHelper.addReportTableRow(table, new String[]{
                        "Role", "Role Description" },
                        ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null,
                        alignments, ReportServiceHelper.PDF_NO_BORDER);
                for (String permission : response.getPermissions()) {
                    PdfPCell cell = ReportServiceHelper.createTableCell(
                            permission,
                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null,
                            null, ReportServiceHelper.PDF_NO_BORDER, 1);
                    cell.setRotation(90);
                    table.addCell(cell);
                }
                for (int j = 0; j < response.getRoles().size(); j++) {
                    String role = response.getRoles().get(j);
                    List<String> permissions = response.getAllowedPermissions()
                            .get(role);
                    table.addCell(ReportServiceHelper.createTableCell(role,
                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null,
                            ReportServiceHelper.PDF_ALIGN_LEFT, null, 1));
                    table.addCell(ReportServiceHelper.createTableCell(response
                            .getRoleDescriptions().get(j),
                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null,
                            ReportServiceHelper.PDF_ALIGN_LEFT, null, 1));
                    for (int i = 0; i < response.getPermissions().size(); i++) {
                        BaseColor bgColor = i % 2 == 0 ? BaseColor.LIGHT_GRAY
                                : null;
                        if (permissions == null) {
                            table.addCell(ReportServiceHelper
                                    .createTableCell(
                                            "-",
                                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                            bgColor,
                                            ReportServiceHelper.PDF_ALIGN_CENTER,
                                            null, 1));
                        } else {
                            String permission = response.getPermissions()
                                    .get(i);
                            String column = permissions.contains(permission) ? "X"
                                    : "-";
                            table.addCell(ReportServiceHelper
                                    .createTableCell(
                                            column,
                                            ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                            bgColor,
                                            ReportServiceHelper.PDF_ALIGN_CENTER,
                                            null, 1));
                        }
                    }
                }
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
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(UserRolePermissionsReportResponse response) throws ReportGenerationException {
        return null;
    }
}
