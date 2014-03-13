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
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates balanced lockbox report. It uses local data for
 * generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This class is
 * effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
@Stateless
@LocalBean
public class BalancedScorecardAccountReportService extends BaseReportService implements
        ReportService<BalancedScorecardAccountReportRequest, BalancedScorecardAccountReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = BalancedScorecardAccountReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link BalancedScorecardAccountReportService} class.
     */
    public BalancedScorecardAccountReportService() {
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
    public BalancedScorecardAccountReportResponse getReport(BalancedScorecardAccountReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(BalancedScorecardAccountReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            BalancedScorecardAccountReportResponse response = new BalancedScorecardAccountReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            ReportServiceHelper.setScorecardQuarterDate(response);

            response.setNewAccountItems(new ArrayList<BalancedScorecardAccountReportResponseItem>());
            response.setClosedAccountItems(new ArrayList<BalancedScorecardAccountReportResponseItem>());
            response.setInitialBillingItems(new ArrayList<BalancedScorecardAccountReportResponseItem>());

            String auditRecordQueryStr = "SELECT a FROM AuditRecord a WHERE a.date BETWEEN :start AND :end"
                    + " AND actionName = :actionName";
            List<AuditRecord> closedAccountRecords = getEntityManager().createQuery(auditRecordQueryStr,
                    AuditRecord.class).setParameter("start", response.getStartDate()).setParameter("end",
                    response.getEndDate()).setParameter("actionName", "Closed Account").getResultList();
            List<AuditRecord> newAccountRecords = getEntityManager().createQuery(auditRecordQueryStr,
                    AuditRecord.class).setParameter("start", response.getStartDate()).setParameter("end",
                    response.getEndDate()).setParameter("actionName", "New Account").getResultList();
            List<AuditRecord> initialBillingRecords = getEntityManager().createQuery(auditRecordQueryStr,
                    AuditRecord.class).setParameter("start", response.getStartDate()).setParameter("end",
                    response.getEndDate()).setParameter("actionName", "Initial Bill Triggered").getResultList();

            response.setClosedAccountTotal(addItem(closedAccountRecords, response.getClosedAccountItems()));
            response.setNewAccountTotal(addItem(newAccountRecords, response.getNewAccountItems()));
            response.setInitialBillingTotal(addItem(initialBillingRecords, response.getInitialBillingItems()));

            LoggingHelper.logExit(logger, signature, new Object[]{ response });
            return response;
        } catch (PersistenceException e) {
            throw LoggingHelper
                    .logException(
                            logger,
                            signature,
                            new ReportGenerationException(
                                    "An error has occurred when accessing persistence:" + e.getMessage(),
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
    public byte[] exportReport(BalancedScorecardAccountReportResponse response, ExportType exportType)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(BalancedLockboxReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int[] cellWidths = new int[]
                    {
                            25, 15, 45, 15
                    };
            if (exportType == ExportType.RTF || exportType == ExportType.DOC) {
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addBalancedReportTitle(document, response.getReportName(),
                        response.getFiscalYear(), response.getQuarter(), response.getStartDate(),
                        response.getEndDate());
                int[] alignments = new int[]
                        {
                                ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_ALIGN_LEFT,
                                ReportServiceHelper.RTF_ALIGN_RIGHT
                        };
                addItemsTable(document, response.getClosedAccountItems(), "Closed Accounts", alignments, cellWidths,
                        response.getClosedAccountTotal());
                addItemsTable(document, response.getInitialBillingItems(), "Initial Billings", alignments, cellWidths,
                        response.getInitialBillingTotal());
                addItemsTable(document, response.getNewAccountItems(), "New Accounts", alignments, cellWidths,
                        response.getNewAccountTotal());

                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addBalancedReportTitle(document, response.getReportName(),
                        response.getFiscalYear(), response.getQuarter(), response.getStartDate(),
                        response.getEndDate());
                int[] alignments = new int[]
                        {
                                ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_ALIGN_LEFT,
                                ReportServiceHelper.PDF_ALIGN_RIGHT
                        };

                addItemsTable(document, response.getClosedAccountItems(), "Closed Accounts", alignments, cellWidths,
                        response.getClosedAccountTotal());
                addItemsTable(document, response.getInitialBillingItems(), "Initial Billings", alignments, cellWidths,
                        response.getInitialBillingTotal());
                addItemsTable(document, response.getNewAccountItems(), "New Accounts", alignments, cellWidths,
                        response.getNewAccountTotal());

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
     * Adds the item to the list and return the total number.
     *
     * @param records
     *         the list of audit record.
     * @param items
     *         the list of items.
     * @return teh total number.
     */
    private Integer addItem(List<AuditRecord> records, List<BalancedScorecardAccountReportResponseItem> items) {
        HashMap<String, BalancedScorecardAccountReportResponseItem> map = new HashMap<String,
                BalancedScorecardAccountReportResponseItem>();
        String accountQueryStr = "SELECT a FROM Account a WHERE a.claimNumber = :csd";
        int totalNumber = 0;
        for (AuditRecord record : records) {
            String csd = null;
            for (AuditParameterRecord parameter : record.getParameters()) {
                if (parameter.getPropertyName().equals("claim number")) {
                    csd = parameter.getNewValue();
                    break;
                }
            }
            if (csd != null) {
                Account account = getEntityManager().createQuery(accountQueryStr,
                        Account.class).setParameter("csd", csd).getSingleResult();
                BalancedScorecardAccountReportResponseItem item = map.get(account.getFormType().getId() + "|" +
                        account.getStatus().getId());
                if (item == null) {
                    item = new BalancedScorecardAccountReportResponseItem();
                    item.setNumber(0);
                    item.setAccountType(account.getFormType().getName());
                    item.setAccountStatus(account.getStatus().getName());
                    map.put(account.getFormType().getId() + "|" + account.getStatus().getName(), item);
                    items.add(item);
                }
                item.setNumber(item.getNumber() + 1);
                totalNumber++;
            }
        }
        return totalNumber;
    }

    /**
     * Adds the table for items to the report.
     *
     * @param document
     *         the report document.
     * @param items
     *         the list of items.
     * @param itemType
     *         the item type.
     * @param alignments
     *         the alignments.
     * @param cellWidths
     *         the table cell widths.
     * @param total
     *         the total.
     * @throws com.lowagie.text.DocumentException
     *         if any error occurs.
     */
    private static void addItemsTable(com.lowagie.text.Document document,
                                      List<BalancedScorecardAccountReportResponseItem> items,
                                      String itemType, int[] alignments, int[] cellWidths, Integer total)
            throws com.lowagie.text.DocumentException {
        if (items != null) {
            Table table = new Table(4);
            table.setWidths(cellWidths);
            ReportServiceHelper.addReportTableRow(table, new String[]{
                    itemType, "Type", "Account Status Description", "Number"
            }, ReportServiceHelper.RTF_REPORT_HEADER_FONT,
                    null,
                    alignments, ReportServiceHelper.RTF_BORDER_BOTTOM);
            for (int i = 0; i < items.size(); i++) {
                BalancedScorecardAccountReportResponseItem item = items.get(i);
                ReportServiceHelper.addReportTableRow(table, new Object[]{
                        "", item.getAccountType(), item.getAccountStatus(), item.getNumber()
                }, ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null, alignments,
                        i == items.size() - 1 ?
                                ReportServiceHelper.RTF_BORDER_BOTTOM : ReportServiceHelper.RTF_NO_BORDER);
            }
            table.addCell(ReportServiceHelper.createEmptyCell(2, ReportServiceHelper.RTF_BORDER_BOTTOM));
            table.addCell(ReportServiceHelper.createTableCell("Total Number of " + itemType,
                    ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null, ReportServiceHelper.RTF_ALIGN_CENTER,
                    ReportServiceHelper.RTF_BORDER_BOTTOM, 1));
            table.addCell(ReportServiceHelper.createTableCell(total,
                    ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null, ReportServiceHelper.RTF_ALIGN_RIGHT,
                    ReportServiceHelper.RTF_BORDER_BOTTOM, 1));
            document.add(table);
        }
    }

    /**
     * Adds the table for items to the report.
     *
     * @param document
     *         the report document.
     * @param items
     *         the list of items.
     * @param itemType
     *         the item type.
     * @param alignments
     *         the alignments.
     * @param cellWidths
     *         the table cell widths.
     * @param total
     *         the total.
     * @throws com.itextpdf.text.DocumentException
     *         if any error occurs.
     */
    private static void addItemsTable(com.itextpdf.text.Document document,
                                      List<BalancedScorecardAccountReportResponseItem> items,
                                      String itemType, int[] alignments, int[] cellWidths, Integer total)
            throws com.itextpdf.text.DocumentException {
        if (items != null) {
            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(20);
            table.setWidths(cellWidths);
            ReportServiceHelper.addReportTableRow(table, new String[]{
                    itemType, "Type", "Account Status Description", "Number"
            }, ReportServiceHelper.PDF_REPORT_HEADER_FONT,
                    null,
                    alignments, ReportServiceHelper.PDF_BORDER_BOTTOM);
            for (int i = 0; i < items.size(); i++) {
                BalancedScorecardAccountReportResponseItem item = items.get(i);
                ReportServiceHelper.addReportTableRow(table, new Object[]{
                        "", item.getAccountType(), item.getAccountStatus(), item.getNumber()
                }, ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null, alignments,
                        i == items.size() - 1 ?
                                ReportServiceHelper.PDF_BORDER_BOTTOM : ReportServiceHelper.PDF_NO_BORDER);
            }
            table.addCell(ReportServiceHelper.createEmptyPdfCell(2, ReportServiceHelper.PDF_BORDER_BOTTOM));
            table.addCell(ReportServiceHelper.createTableCell("Total Number of " + itemType,
                    ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null, ReportServiceHelper.PDF_ALIGN_CENTER,
                    ReportServiceHelper.PDF_BORDER_BOTTOM, 1));
            table.addCell(ReportServiceHelper.createTableCell(total,
                    ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null, ReportServiceHelper.PDF_ALIGN_RIGHT,
                    ReportServiceHelper.PDF_BORDER_BOTTOM, 1));
            document.add(table);
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
    public byte[] renderChart(BalancedScorecardAccountReportResponse response) throws ReportGenerationException {
        return null;
    }
}
