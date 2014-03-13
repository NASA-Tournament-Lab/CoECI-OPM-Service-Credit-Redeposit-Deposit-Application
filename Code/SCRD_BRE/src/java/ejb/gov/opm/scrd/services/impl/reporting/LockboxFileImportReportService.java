/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.AuditBatchLogId;
import gov.opm.scrd.entities.batchprocessing.LockboxPaymentType;
import gov.opm.scrd.entities.batchprocessing.MainframeImport;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class is the implementation of the ReportService which generates lockbox file import report. It uses local data
 * for generating report and iText/iText RTF for generating reports.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LockboxFileImportReportService extends BaseReportService implements
    ReportService<LockboxFileImportReportRequest, LockboxFileImportReportResponse> {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = LockboxFileImportReportService.class.getName();

    /**
     * Default constructor.
     */
    public LockboxFileImportReportService() {
    }

    /**
     * Creates the report for the given request. The report is encapsulated in the ReportResponse object.
     *
     * @param request the request data to generate report.
     * @return instance containing the report data, can not be null.
     *
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public LockboxFileImportReportResponse getReport(LockboxFileImportReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(LockboxFileImportReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");
        try {
            List<MainframeImport> importRecords = getEntityManager().createQuery("SELECT i FROM MainframeImport i",
                MainframeImport.class).getResultList();

            LockboxFileImportReportResponse response = new LockboxFileImportReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            ArrayList<LockboxFileImportReportResponseItem> reportRows =
                new ArrayList<LockboxFileImportReportResponseItem>();
            response.setItems(reportRows);

            // we group it by log ID + import date
            Map<GroupingKey, LockboxFileImportReportResponseItem> groups =
                new HashMap<GroupingKey, LockboxFileImportReportResponseItem>();

            for (MainframeImport record : importRecords) {
                AuditBatchLogId logId = ReportHelper.getAuditBatchLogId(getEntityManager(), getLogger(),
                    record.getAuditBatchLogId());
                if (logId == null) {
                    // without the log ID, there is no available identifier.
                    getLogger().warn("Skipped record because of missing log identifier. " + record.getId());
                    continue;
                }

                // join with PaymentTransaction
                PaymentTransaction tx = ReportHelper.getPaymentTransaction(getEntityManager(), getLogger(),
                    record.getPayTransactionKey());

                if (tx == null) {
                    // without the tx, there is no payment record to process
                    getLogger().warn("Skipped record because of missing transaction. " + record.getId());
                    continue;
                }

                GroupingKey key = new GroupingKey();
                key.auditBatchLogId = logId.getAuditBatchLogId();
                // 5. payTransTransactionDate ---> this is the deposit date
                key.depositDate = tx.getPayTransTransactionDate();

                LockboxFileImportReportResponseItem row = groups.get(key);
                if (row == null) {
                    row = new LockboxFileImportReportResponseItem();
                    row.setLogId(ReportHelper.formatInteger(Integer.valueOf(key.auditBatchLogId)));
                    row.setDepositDate(key.depositDate);
                    row.setAchCount(0);
                    row.setCheckCount(0);
                    row.setOtherCount(0);
                    groups.put(key, row);
                }

                row.setImportDate(record.getImportDate());

                if (record.getPaymentType() != null && record.getPaymentType() == LockboxPaymentType.ACH) {
                    row.setAchCount(row.getAchCount() + 1);
                    row.setAchTotal(ReportHelper.sum(row.getAchTotal(), tx.getPayTransPaymentAmount()));
                } else if (record.getPaymentType() != null
                    && record.getPaymentType() == LockboxPaymentType.CHECK) { // check payment
                    row.setCheckCount(row.getCheckCount() + 1);
                    row.setCheckTotal(ReportHelper.sum(row.getCheckTotal(), tx.getPayTransPaymentAmount()));
                } else { // other payment see
                         // http://apps.topcoder.com/forums/?module=Thread&threadID=810369&start=0&mc=2#1841610
                    row.setOtherCount(row.getOtherCount() + 1);
                    row.setOtherTotal(ReportHelper.sum(row.getOtherTotal(), tx.getPayTransPaymentAmount()));
                }
            }

            response.getItems().addAll(groups.values());
            return response;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Exports the report for the given request. The contents of the exported report are returned from this method.
     *
     * @param response the report data to generate report.
     * @param exportType the type of the report data to generate.
     * @return The byte array of contents of the exported report, can not be null.
     *
     * @throws IllegalArgumentException if the response/exportType is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public byte[] exportReport(LockboxFileImportReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(LockboxFileImportReportResponse response, ExportType exportType)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"response", "exportType"}, new Object[] {response,
            exportType});

        Helper.checkNull(logger, signature, response, "response");
        Helper.checkNull(logger, signature, exportType, "exportType");

        Document document = null;
        try {
            byte[] result = null;

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document = new Document();
            document.setMargins(ReportHelper.MARGIN_LR, ReportHelper.MARGIN_LR, ReportHelper.MARGIN_TB,
                ReportHelper.MARGIN_TB);

            // Associate it with output stream
            ReportHelper.initDocumentFormat(document, outputStream, exportType);

            HeaderFooter head = ReportHelper.generateHeaderNoBorder(document, response.getReportGenerationDate(),
                response.getReportName(), null);

            document.setHeader(head);
            document.open();

            renderItems(response, document);

            document.close();
            result = outputStream.toByteArray();
            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (DocumentException e) {
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "An error has occurred when rendering the document.", e));
        } finally {
            // per reviewer, we should do this even if it does not make sense
            // since we needed to close it to build the PDF before return
            if (document != null) {
                document.close();
            }
        }
    }

    /**
     * Renders the table of records.
     *
     * @param response the report model
     * @param document the current document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderItems(LockboxFileImportReportResponse response, Document document) throws DocumentException {
        // table styling
        Table table = new Table(7);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {14, 14, 14, 14, 14, 14, 14});
        table.addCell(new Phrase("Log ID", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(new Phrase("Deposit Date", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(new Phrase("Import Date", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.moneyCell("Check Count", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.moneyCell("ACH Count", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.moneyCell("Check Total", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.moneyCell("ACH Total", ReportHelper.TABLE_HEADER_FONT));

        // process rows
        List<LockboxFileImportReportResponseItem> items = response.getItems();
        for (LockboxFileImportReportResponseItem row : items) {
            // each record can generate up to 3 rows
            boolean firstRow = true;
            boolean lastRow = false;

            int rowIndex = 0;
            int totalRows = countTotalRows(row);

            // Check payments if any
            if (row.getCheckCount() > 0) {
                rowIndex++;
                if (totalRows == rowIndex) {
                    lastRow = true;
                }
                table.addCell(addBorders(new Phrase(row.getLogId(), ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(new Phrase(ReportHelper.formatDate(row.getDepositDate()),
                    ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(new Phrase(ReportHelper.formatDate(row.getImportDate()),
                    ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(
                    ReportHelper.moneyCell(String.valueOf(row.getCheckCount()), ReportHelper.TABLE_DATA_FONT),
                    firstRow, lastRow));
                table.addCell(addBorders(ReportHelper.moneyCell("-", ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table
                    .addCell(addBorders(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getCheckTotal()),
                        ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(ReportHelper.moneyCell("-", ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                firstRow = false;
            }

            // ACH payments if any
            if (row.getAchCount() > 0) {
                rowIndex++;
                if (totalRows == rowIndex) {
                    lastRow = true;
                }
                table.addCell(addBorders(new Phrase(row.getLogId(), ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(new Phrase(ReportHelper.formatDate(row.getDepositDate()),
                    ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(new Phrase(ReportHelper.formatDate(row.getImportDate()),
                    ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(ReportHelper.moneyCell("-", ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(
                    ReportHelper.moneyCell(String.valueOf(row.getAchCount()), ReportHelper.TABLE_DATA_FONT), firstRow,
                    lastRow));
                table.addCell(addBorders(ReportHelper.moneyCell("-", ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(
                    ReportHelper.moneyCell(ReportHelper.formatMoney(row.getAchTotal()), ReportHelper.TABLE_DATA_FONT),
                    firstRow, lastRow));
                firstRow = false;
            }

            // Other payments if any
            if (row.getOtherCount() > 0) {
                rowIndex++;
                if (totalRows == rowIndex) {
                    lastRow = true;
                }
                table.addCell(addBorders(new Phrase(row.getLogId(), ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(new Phrase(ReportHelper.formatDate(row.getDepositDate()),
                    ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(new Phrase(ReportHelper.formatDate(row.getImportDate()),
                    ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(ReportHelper.moneyCell("-", ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table.addCell(addBorders(
                    ReportHelper.moneyCell(String.valueOf(row.getOtherCount()), ReportHelper.TABLE_DATA_FONT),
                    firstRow, lastRow));
                table.addCell(addBorders(ReportHelper.moneyCell("-", ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                table
                    .addCell(addBorders(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getOtherTotal()),
                        ReportHelper.TABLE_DATA_FONT), firstRow, lastRow));
                firstRow = false;
            }
        }
        document.add(table);
    }

    /**
     * Returns the number of display rows to be rendered for the given item.
     *
     * @param row the report row
     * @return the number of display rows, depending on the transaction involved
     */
    private int countTotalRows(LockboxFileImportReportResponseItem row) {
        int totalRows = 0;

        // find out how many rows there are so we can set the border correctly
        if (row.getAchCount() > 0) {
            totalRows++;
        }
        if (row.getCheckCount() > 0) {
            totalRows++;
        }
        if (row.getOtherCount() > 0) {
            totalRows++;
        }
        return totalRows;
    }

    /**
     * Adds the group borders to the given cell.
     *
     * @param cell the cell to add borders to
     * @param firstRow flag indicating this is the first in the group
     * @param lastRow flag indicating this is the last in the group
     * @return the decorated cell
     */
    private Cell addBorders(Cell cell, boolean firstRow, boolean lastRow) {
        if (firstRow) {
            cell.setBorder(Cell.TOP);
        }
        if (lastRow) {
            cell.setBorder(Cell.BOTTOM);
        }
        if (lastRow && firstRow) {
            cell.setBorder(Cell.BOTTOM | Cell.TOP);
        }
        return cell;
    }

    /**
     * Adds the group borders to the given cell.
     *
     * @param phrase the phrase to put in a bordered cell
     * @param firstRow flag indicating this is the first in the group
     * @param lastRow flag indicating this is the last in the group
     * @return the decorated cell
     * @throws DocumentException for any errors encountered
     */
    private Cell addBorders(Phrase phrase, boolean firstRow, boolean lastRow) throws DocumentException {
        Cell cell = new Cell(phrase);
        return addBorders(cell, firstRow, lastRow);
    }

    /**
     * Grouping key for the non aggregate columns of the report.
     *
     * @author TCSASSEMBLER
     * @version 1.0
     */
    private class GroupingKey {

        /**
         * The log ID.
         */
        private String auditBatchLogId;

        /**
         * The deposit date.
         */
        private Date depositDate;

        /**
         * Private constructor.
         */
        private GroupingKey() {
        }

        /**
         * Gets the hash code.
         *
         * @return the hash code
         */
        public int hashCode() {
            return auditBatchLogId.hashCode();
        }

        /**
         * Checks for equality.
         *
         * @param obj the instance to compare to
         * @return true if the key represents the same group
         */
        public boolean equals(Object obj) {
            if (obj.getClass() != this.getClass()) {
                return false;
            }

            GroupingKey that = (GroupingKey) obj;
            return String.valueOf(this.auditBatchLogId).equals(String.valueOf(that.auditBatchLogId))
                && String.valueOf(this.depositDate).equals(String.valueOf(that.depositDate));
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
    public byte[] renderChart(LockboxFileImportReportResponse response) throws ReportGenerationException {
        return null;
    }
}
