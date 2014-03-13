/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.batchprocessing.PaymentTransactionCodes;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class handles data retrieval and export of the manual payment report.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManualPaymentReportService extends BaseReportService implements
    ReportService<ManualPaymentReportRequest, ManualPaymentReportResponse> {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ManualPaymentReportService.class.getName();
    /**
     * <p>
     * Represents the status values that are considered when fetching records.
     * </p>
     */
    private static final List<Integer> COMPLETE_STATUS = Arrays.asList(
        PaymentTransactionCodes.ADJUSTMENT_COMPLETE.getCode(), PaymentTransactionCodes.POSTED_COMPLETE.getCode());

    /**
     * Default constructor.
     */
    public ManualPaymentReportService() {
    }

    /**
     * Creates the report for the given request. The report is encapsulated in the ReportResponse object.
     *
     * @param request The request data to generate report.
     * @return ReportResponse instance containing the report data, can not be null.
     * @throws. IllegalArgumentException - if the request is null.
     * @throws ReportGenerationException - if there is any problem when generating response.
     */
    public ManualPaymentReportResponse getReport(ManualPaymentReportRequest request) throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(ManualPaymentReportRequest request)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});

        Helper.checkNull(logger, signature, request, "Manual payment report request");
        try {

            List<PaymentTransaction> transactions = getEntityManager()
                .createQuery(
                    "SELECT t FROM PaymentTransaction t WHERE t.deleted = false AND "
                        + "t.paymentStatusCode IN (:statuses)", PaymentTransaction.class)
                .setParameter("statuses", COMPLETE_STATUS).getResultList();

            ManualPaymentReportResponse response = new ManualPaymentReportResponse();
            List<ManualPaymentReportResponseItem> items = new ArrayList<ManualPaymentReportResponseItem>();

            for (PaymentTransaction tx : transactions) {
                InvoiceData invoice = ReportHelper.getInvoiceData(getEntityManager(), getLogger(), tx);

                ManualPaymentReportResponseItem item = new ManualPaymentReportResponseItem();
                item.setBatchNumber(tx.getPayTransBatchNumber() + " " + tx.getPayTransBlockNumber() + " "
                    + tx.getPayTransSequenceNumber());
                item.setBirthDate(tx.getScmDateOfBirth());
                item.setCsd(tx.getCsd());
                item.setPayDate(tx.getPayTransTransactionDate());
                // item.setBatchDate(batchDate);
                item.setPaymentStatusDescription(ReportHelper.getLookupName(getEntityManager(), PaymentStatus.class,
                    tx.getPaymentStatusCode()));

                if (invoice != null) {
                    item.setPaymentAmount(invoice.getPayTransPaymentAmount());
                    item.setClaimant(invoice.getScmName());
                    item.setClaimNumber(invoice.getScmClaimNumber());
                }
                items.add(item);
            }

            response.setItems(items);
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            LoggingHelper.logExit(logger, signature, new Object[] {response});
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
     * Renders the report using the given model.
     *
     * @param response the report model
     * @param exportType the file type to generate
     * @return the rendered report document
     * @throws IllegalArgumentException if any argument is null
     * @throws ReportGenerationException - if there is any problem when generating response.
     */
    public byte[] exportReport(ManualPaymentReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#exportReport(ManualPaymentReportResponse response, ExportType exportType)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"response", "exportType"}, new Object[] {response,
            exportType});

        Helper.checkNull(logger, signature, response, "response");
        Helper.checkNull(logger, signature, exportType, "exportType");

        try {
            byte[] result = null;

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            document.setMargins(ReportHelper.MARGIN_LR, ReportHelper.MARGIN_LR, ReportHelper.MARGIN_TB,
                ReportHelper.MARGIN_TB);

            // Associate it with output stream
            ReportHelper.initDocumentFormat(document, outputStream, exportType);

            HeaderFooter head = ReportHelper.generateSimpleHeader(document, response.getReportGenerationDate(),
                response.getReportName());

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
        }
    }

    /**
     * Renders a table of records, by the selected group.
     *
     * @param response the report model
     * @param document the current document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderItems(ManualPaymentReportResponse response, Document document) throws DocumentException {
        boolean first = true;
        if (response.getItems() != null && !response.getItems().isEmpty()) {
            Map<String, List<ManualPaymentReportResponseItem>> byClaimant = groupByClaimant(response);
            Set<Entry<String, List<ManualPaymentReportResponseItem>>> entrySet = byClaimant.entrySet();
            BigDecimal grandTotal = new BigDecimal(0);
            for (Entry<String, List<ManualPaymentReportResponseItem>> entry : entrySet) {

                if (!first) {
                    document.add(new Paragraph("_________________________________________________"
                        + "_____________________________________"));
                }
                first = false;

                // group title
                Paragraph paragraph = new Paragraph(entry.getKey(), ReportHelper.TABLE_HEADER_FONT);
                document.add(paragraph);

                // start
                // table styling
                Table table = new Table(7);
                table.setBorder(Table.NO_BORDER);
                Cell cell = new Cell();
                cell.setBorder(Cell.NO_BORDER);
                table.setDefaultCell(cell);
                table.setWidth(100);
                table.setPadding(1);

                // table header and column widths
                table.setWidths(new float[] {10, 10, 12, 12, 15, 17, 24});
                table.addCell(new Phrase("BatBlkSeq", ReportHelper.TABLE_HEADER_UNDERLINE));
                table.addCell(new Phrase("CSD #", ReportHelper.TABLE_HEADER_UNDERLINE));
                table.addCell(new Phrase("Birth Date", ReportHelper.TABLE_HEADER_UNDERLINE));
                table.addCell(ReportHelper.moneyCell("Payment Amount", ReportHelper.TABLE_HEADER_UNDERLINE));
                table.addCell(new Phrase("Pay Date", ReportHelper.TABLE_HEADER_UNDERLINE));
                table.addCell(new Phrase("Batch Date", ReportHelper.TABLE_HEADER_UNDERLINE));
                table.addCell(new Phrase("Payment Status Description", ReportHelper.TABLE_HEADER_UNDERLINE));

                // process rows
                List<ManualPaymentReportResponseItem> items = entry.getValue();
                BigDecimal subTotal = new BigDecimal(0);
                for (ManualPaymentReportResponseItem row : items) {
                    subTotal = subTotal.add(row.getPaymentAmount());

                    table.addCell(new Phrase(row.getBatchNumber(), ReportHelper.TABLE_DATA_FONT));
                    table.addCell(new Phrase(row.getCsd(), ReportHelper.TABLE_DATA_FONT));
                    table
                        .addCell(new Phrase(ReportHelper.formatDate(row.getBirthDate()), ReportHelper.TABLE_DATA_FONT));
                    table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getPaymentAmount()),
                        ReportHelper.TABLE_DATA_FONT));
                    table.addCell(new Phrase(ReportHelper.formatDate(row.getPayDate()), ReportHelper.TABLE_DATA_FONT));
                    table.addCell(new Phrase(ReportHelper.formatDateWithTime(row.getBatchDate()),
                        ReportHelper.TABLE_DATA_FONT));
                    table.addCell(new Phrase(row.getPaymentStatusDescription(), ReportHelper.TABLE_DATA_FONT));
                }

                // label for the total
                Cell summaryCell = new Cell(new Phrase(items.size() + " Payment(s) Totaling  ",
                    ReportHelper.TABLE_HEADER_FONT));
                summaryCell.setColspan(3);
                summaryCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(summaryCell);

                // group total value
                Cell subTotalCell = ReportHelper.moneyCell(ReportHelper.formatMoney(subTotal),
                    ReportHelper.TABLE_HEADER_FONT);
                table.addCell(subTotalCell);

                // complete the table with empty cells
                Cell spacerCell = new Cell("");
                spacerCell.setColspan(3);
                table.addCell(spacerCell);

                document.add(table);

                // add subtotal to grand
                grandTotal = grandTotal.add(subTotal);
            }

            renderSummaryTable(response, document, grandTotal);
        }
    }

    /**
     * Renders the summary table.
     * @param grandTotal the report total
     * @param response the report model
     * @param document the current document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderSummaryTable(ManualPaymentReportResponse response, Document document, BigDecimal grandTotal)
        throws DocumentException {
        // render grand total
        // table styling
        Table table = new Table(7);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {17, 10, 10, 10, 12, 17, 24});
        // label for the total
        Cell summaryCell = new Cell(new Phrase("Grand Total:", ReportHelper.TABLE_HEADER_FONT));
        summaryCell.setColspan(3);
        summaryCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(summaryCell);

        // group total value
        Cell subTotalCell = ReportHelper.moneyCell(ReportHelper.formatMoney(grandTotal),
            ReportHelper.TABLE_HEADER_FONT);
        table.addCell(subTotalCell);

        // complete the table with empty cells
        Cell spacerCell = new Cell(new Phrase("in " + response.getItems().size() + " Payment(s)",
            ReportHelper.TABLE_HEADER_FONT));
        spacerCell.setColspan(3);
        table.addCell(spacerCell);

        document.add(table);
    }

    /**
     * Group items by Claimant.
     *
     * @param response The manual payment report response
     * @return manual payments group by claimant.
     */
    private Map<String, List<ManualPaymentReportResponseItem>> groupByClaimant(ManualPaymentReportResponse response) {
        Map<String, List<ManualPaymentReportResponseItem>> map =
            new HashMap<String, List<ManualPaymentReportResponseItem>>();
        List<ManualPaymentReportResponseItem> items = response.getItems();
        for (ManualPaymentReportResponseItem row : items) {
            List<ManualPaymentReportResponseItem> claimantItems = map.get(row.getClaimant());
            if (claimantItems == null) {
                claimantItems = new ArrayList<ManualPaymentReportResponseItem>();
            }
            claimantItems.add(row);
            map.put(row.getClaimant(), claimantItems);
        }
        return map;
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(ManualPaymentReportResponse response) throws ReportGenerationException {
        return null;
    }

}
