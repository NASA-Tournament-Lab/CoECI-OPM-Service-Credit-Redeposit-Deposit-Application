/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Cell;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.RefundTransaction;
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
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This class is the implementation of the ReportService which generates refund section account assignment report. It
 * uses local data for generating report and iText/iText RTF for generating reports. <p> <strong>Thread-safety:</strong>
 * This class is effectively thread - safe after configuration, the configuration is done in a thread - safe manner.
 * </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
@Stateless
@LocalBean
public class RefundSectionAccountAssignmentsReportService extends
        BaseReportService
        implements
        ReportService<RefundSectionAccountAssignmentsReportRequest, RefundSectionAccountAssignmentsReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = RefundSectionAccountAssignmentsReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link RefundSectionAccountAssignmentsReportService} class.
     */
    public RefundSectionAccountAssignmentsReportService() {
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
    public RefundSectionAccountAssignmentsReportResponse getReport(
            RefundSectionAccountAssignmentsReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(RefundSectionAccountAssignmentsReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            RefundSectionAccountAssignmentsReportResponse response =
                    new RefundSectionAccountAssignmentsReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setGroups(new ArrayList<RefundUserAccountGroup>());

            List<RefundTransaction> refundTransactions = getEntityManager()
                    .createQuery("SELECT r FROM RefundTransaction r",
                            RefundTransaction.class).getResultList();

            Map<String, RefundUserAccountGroup> groupMap = new HashMap<String, RefundUserAccountGroup>();

            for (RefundTransaction refundTransaction : refundTransactions) {
                if (groupMap.get(refundTransaction.getRefundUsername()) == null) {
                    User user = getEntityManager()
                            .createQuery(
                                    "SELECT u FROM User u WHERE u.username = :username",
                                    User.class)
                            .setParameter("username",
                                    refundTransaction.getRefundUsername())
                            .getSingleResult();

                    RefundUserAccountGroup group = new RefundUserAccountGroup();
                    group.setUser(user.getUsername());
                    group.setEmail(user.getEmail());
                    group.setTelephone(user.getTelephone());
                    group.setNetworkId(user.getNetworkId());
                    group.setRole(user.getRole().getName());
                    group.setItems(new ArrayList<RefundUserAccountItem>());
                    Long supervisorId = user.getSupervisorId();
                    if (supervisorId != null) {
                        User supervisor = getEntityManager().find(User.class, supervisorId);
                        if (supervisor.getRole() != null) {
                            group.setSupervisingRole(supervisor.getRole()
                                .getName());
                        }
                    }
                    response.getGroups().add(group);
                    groupMap.put(refundTransaction.getRefundUsername(), group);
                }
                RefundUserAccountItem item = new RefundUserAccountItem();
                item.setCsd(refundTransaction.getClaimNumber());
                item.setAssignmentDate(refundTransaction.getRefundDate());

                long diff = response.getReportGenerationDate().getTime()
                        - refundTransaction.getRefundDate().getTime();
                item.setDaysNumber((int) (diff / (24 * 60 * 60 * 1000)));

                Account account = getEntityManager()
                        .createQuery(
                                "SELECT a FROM Account a WHERE a.claimNumber = :claimNumber",
                                Account.class)
                        .setParameter("claimNumber",
                                refundTransaction.getClaimNumber())
                        .getSingleResult();
                item.setAccountStatusDescription(account.getStatus().getName());
                groupMap.get(refundTransaction.getRefundUsername()).getItems()
                        .add(item);
            }

            int maxDays = 0;
            int totalDays = 0;
            int itemCount = 0;
            for (RefundUserAccountGroup group : response.getGroups()) {
                int groupMaxDays = 0;
                int totalGroupDays = 0;
                for (RefundUserAccountItem item : group.getItems()) {
                    totalGroupDays += item.getDaysNumber();
                    if (item.getDaysNumber() > groupMaxDays) {
                        groupMaxDays = item.getDaysNumber();
                    }
                    itemCount++;
                }
                totalDays += totalGroupDays;
                group.setAverageDays(totalDays / group.getItems().size());
                group.setMaximumDays(groupMaxDays);
                if (groupMaxDays > maxDays) {
                    maxDays = groupMaxDays;
                }
            }
            response.setMaximumDays(maxDays);
            if (itemCount != 0) {
                response.setAverageDays(totalDays / itemCount);
            } else {
                response.setAverageDays(0);
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
    public byte[] exportReport(
            RefundSectionAccountAssignmentsReportResponse response,
            ExportType exportType) throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(RefundSectionAccountAssignmentsReportResponse response)";
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

                if (response.getGroups() != null) {
                    int[] alignments = new int[]{
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_LEFT };
                    int[] sumAlignments = new int[]{
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_LEFT,
                            ReportServiceHelper.RTF_ALIGN_RIGHT,
                            ReportServiceHelper.RTF_ALIGN_LEFT };
                    float[] cellWidths = new float[]{ 20, 30, 20, 30 };
                    for (int i = 0; i < response.getGroups().size(); i++) {
                        Table table = new Table(4);
                        table.setWidths(cellWidths);
                        table.setSpacing(5);
                        table.setWidth(100);
                        if (i == 0) {
                            table.setBorder(ReportServiceHelper.RTF_BORDER_TOP);
                            table.setBorderWidth(2);
                        } else {
                            table.setBorder(ReportServiceHelper.RTF_NO_BORDER);
                        }

                        RefundUserAccountGroup group = response.getGroups()
                                .get(i);
                        table.addCell(ReportServiceHelper.createTableCell(
                                group.getAverageDays() + " "
                                        + group.getMaximumDays() + " ("
                                        + group.getUser() + ") "
                                        + group.getEmail() + "    "
                                        + group.getTelephone(),
                                ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_NO_BORDER, 4));

                        table.addCell(ReportServiceHelper.createTableCell(
                                "Net ID: " + group.getNetworkId() + " Role: "
                                        + group.getRole() + " Supervised by"
                                        + group.getSupervisingRole(),
                                ReportServiceHelper.RTF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_NO_BORDER, 4));

                        ReportServiceHelper
                                .addReportTableRow(
                                        table,
                                        new String[]{ "CSD #",
                                                "Assignment Date", "# Days",
                                                "Account Status Description" },
                                        ReportServiceHelper.RTF_REPORT_UNDERLINE_FONT,
                                        null, alignments,
                                        ReportServiceHelper.RTF_NO_BORDER);
                        for (RefundUserAccountItem item : group.getItems()) {
                            String assignmentDate = item.getAssignmentDate() == null ? null
                                    : getAssignmentDate(item.getAssignmentDate());
                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new Object[]{
                                                    item.getCsd(),
                                                    assignmentDate,
                                                    item.getDaysNumber(),
                                                    item.getAccountStatusDescription() },
                                            ReportServiceHelper.RTF_REPORT_UNDERLINE_FONT,
                                            null, alignments,
                                            ReportServiceHelper.RTF_NO_BORDER);
                        }

                        ReportServiceHelper
                                .addReportTableRow(
                                        table,
                                        new String[]{
                                                group.getAverageDays()
                                                        + " "
                                                        + group.getMaximumDays(),
                                                null,
                                                "Average # Days:   "
                                                        + group.getAverageDays(),
                                                null },
                                        ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                        null, sumAlignments,
                                        ReportServiceHelper.RTF_NO_BORDER);
                        ReportServiceHelper
                                .addReportTableRow(
                                        table,
                                        new String[]{
                                                null,
                                                null,
                                                "Maximum # Days:   "
                                                        + group.getMaximumDays(),
                                                null },
                                        ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                        null,
                                        sumAlignments,
                                        i < response.getGroups().size() - 1 ? ReportServiceHelper.RTF_BORDER_BOTTOM
                                                : ReportServiceHelper.RTF_NO_BORDER);
                        if (i == response.getGroups().size() - 1) {
                            Cell separatorCell = new Cell();
                            separatorCell
                                    .setBorder(ReportServiceHelper.RTF_BORDER_TOP);
                            separatorCell.setBorderWidth(2);
                            separatorCell.setColspan(4);
                            table.addCell(separatorCell);

                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new String[]{
                                                    null,
                                                    null,
                                                    "Average # Days:   "
                                                            + response
                                                            .getAverageDays(),
                                                    null },
                                            ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                            null, sumAlignments,
                                            ReportServiceHelper.RTF_NO_BORDER);
                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new String[]{
                                                    null,
                                                    null,
                                                    "Maximum # Days:   "
                                                            + response
                                                            .getMaximumDays(),
                                                    null },
                                            ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                                            null, sumAlignments,
                                            ReportServiceHelper.RTF_NO_BORDER);
                        }
                        document.add(table);
                    }
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
                        ReportServiceHelper.PDF_ALIGN_LEFT);

                if (response.getGroups() != null) {
                    int[] alignments = new int[]{
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_LEFT };
                    int[] sumAlignments = new int[]{
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_LEFT,
                            ReportServiceHelper.PDF_ALIGN_RIGHT,
                            ReportServiceHelper.PDF_ALIGN_LEFT };
                    float[] cellWidths = new float[]{ 20, 30, 20, 30 };

                    for (int i = 0; i < response.getGroups().size(); i++) {
                        PdfPTable table = new PdfPTable(4);
                        if (i == 0) {
                            table.setSpacingBefore(5);
                        }
                        table.setWidths(cellWidths);
                        table.setWidthPercentage(100);

                        RefundUserAccountGroup group = response.getGroups()
                                .get(i);
                        table.addCell(ReportServiceHelper.createTableCell(
                                group.getAverageDays() + " "
                                        + group.getMaximumDays() + " ("
                                        + group.getUser() + ") "
                                        + group.getEmail() + "    "
                                        + group.getTelephone(),
                                ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_LEFT,
                                i == 0 ? ReportServiceHelper.PDF_BORDER_TOP
                                        : ReportServiceHelper.PDF_NO_BORDER, 4));

                        table.addCell(ReportServiceHelper.createTableCell(
                                "Net ID: " + group.getNetworkId() + " Role: "
                                        + group.getRole() + " Supervised by"
                                        + group.getSupervisingRole(),
                                ReportServiceHelper.PDF_REPORT_CONTENT_FONT,
                                null, ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_NO_BORDER, 4));

                        ReportServiceHelper
                                .addReportTableRow(
                                        table,
                                        new String[]{ "CSD #",
                                                "Assignment Date", "# Days",
                                                "Account Status Description" },
                                        ReportServiceHelper.PDF_REPORT_UNDERLINE_FONT,
                                        null, alignments,
                                        ReportServiceHelper.PDF_NO_BORDER);
                        for (RefundUserAccountItem item : group.getItems()) {
                            String assignmentDate = item.getAssignmentDate() == null ? null
                                    : getAssignmentDate(item.getAssignmentDate());
                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new Object[]{
                                                    item.getCsd(),
                                                    assignmentDate,
                                                    item.getDaysNumber(),
                                                    item.getAccountStatusDescription() },
                                            ReportServiceHelper.PDF_REPORT_UNDERLINE_FONT,
                                            null, alignments,
                                            ReportServiceHelper.PDF_NO_BORDER);
                        }

                        ReportServiceHelper
                                .addReportTableRow(
                                        table,
                                        new String[]{
                                                group.getAverageDays()
                                                        + " "
                                                        + group.getMaximumDays(),
                                                null,
                                                "Average # Days:   "
                                                        + group.getAverageDays(),
                                                null },
                                        ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                        null, sumAlignments,
                                        ReportServiceHelper.PDF_NO_BORDER);
                        ReportServiceHelper
                                .addReportTableRow(
                                        table,
                                        new String[]{
                                                null,
                                                null,
                                                "Maximum # Days:   "
                                                        + group.getMaximumDays(),
                                                null },
                                        ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                        null,
                                        sumAlignments,
                                        i < response.getGroups().size() - 1 ? ReportServiceHelper.PDF_BORDER_BOTTOM
                                                : ReportServiceHelper.PDF_NO_BORDER);
                        if (i == response.getGroups().size() - 1) {
                            PdfPCell separatorCell = new PdfPCell();
                            separatorCell
                                    .setBorder(ReportServiceHelper.PDF_BORDER_TOP);
                            separatorCell.setBorderWidth(2);
                            separatorCell.setColspan(4);
                            separatorCell.setFixedHeight(10);
                            table.addCell(separatorCell);

                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new String[]{
                                                    null,
                                                    null,
                                                    "Average # Days:   "
                                                            + response
                                                            .getAverageDays(),
                                                    null },
                                            ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                            null, sumAlignments,
                                            ReportServiceHelper.PDF_NO_BORDER);
                            ReportServiceHelper
                                    .addReportTableRow(
                                            table,
                                            new String[]{
                                                    null,
                                                    null,
                                                    "Maximum # Days:   "
                                                            + response
                                                            .getMaximumDays(),
                                                    null },
                                            ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                                            null, sumAlignments,
                                            ReportServiceHelper.PDF_NO_BORDER);
                        }
                        document.add(table);
                    }
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
     * Gets the String presents the assignment date.
     *
     * @param date
     *         the assignment date.
     * @return the String represents the assignment date.
     */
    private static String getAssignmentDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.ENGLISH);

        return dateFormat.format(date) + " " + timeFormat.format(date);
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(RefundSectionAccountAssignmentsReportResponse response) throws ReportGenerationException {
        return null;
    }
}
