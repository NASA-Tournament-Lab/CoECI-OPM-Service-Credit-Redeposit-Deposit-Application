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
import java.util.List;

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
 * This class handles data retrieval and export of the current suspense report.
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
public class CurrentSuspenseReportService extends BaseReportService implements
    ReportService<CurrentSuspenseReportRequest, CurrentSuspenseReportResponse> {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = CurrentSuspenseReportService.class.getName();

    /**
     * <p>
     * Represents the status values that are considered when fetching records.
     * </p>
     */
    private static final List<Integer> SUSPENSE_STATUS = Arrays.asList(PaymentTransactionCodes.SUSPENDED.getCode(),
        PaymentTransactionCodes.POSTED_PENDING.getCode(),
        PaymentTransactionCodes.SUSPENSE_REFUND_PENDING_APPROVAL.getCode(),
        PaymentTransactionCodes.CREDIT_BALANCE_REFUND_PENDING_APPROVAL.getCode(),
        PaymentTransactionCodes.UNRESOLVED.getCode());

    /**
     * Default constructor.
     */
    public CurrentSuspenseReportService() {
    }

    /**
     * Generates the report for current suspense records.
     *
     * @param request the request object
     * @return the model for the requested report.
     * @throws IllegalArgumentException - if the request is null.
     * @throws ReportGenerationException - if there is any problem when generating response.
     */
    public CurrentSuspenseReportResponse getReport(CurrentSuspenseReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(CurrentSuspenseReportRequest request)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");

        try {
            List<PaymentTransaction> transactions = getEntityManager()
                .createQuery(
                    "SELECT t FROM PaymentTransaction t WHERE t.deleted = false AND t.paymentStatusCode IN (:statuses)",
                    PaymentTransaction.class).setParameter("statuses", SUSPENSE_STATUS).getResultList();
            logger.debug(transactions.size() + " payment transactions found.");

            CurrentSuspenseReportResponse response = new CurrentSuspenseReportResponse();
            List<CurrentSuspenseReportResponseItem> items = new ArrayList<CurrentSuspenseReportResponseItem>();

            BigDecimal totalPayments = new BigDecimal(0);
            for (PaymentTransaction tx : transactions) {
                InvoiceData invoice = ReportHelper.getInvoiceData(getEntityManager(), getLogger(), tx);

                CurrentSuspenseReportResponseItem item = new CurrentSuspenseReportResponseItem();
                item.setBatchNumber(tx.getPayTransBatchNumber() + "-" + tx.getPayTransBlockNumber() + "-"
                    + tx.getPayTransSequenceNumber());
                item.setBirthDate(tx.getScmDateOfBirth());
                item.setCsd(tx.getCsd());
                item.setDate(tx.getPayTransTransactionDate());
                item.setACH(tx.getAchPayment() != null && tx.getAchPayment());
                if (invoice != null) {
                    item.setAccountStatus(invoice.getAccountStatusDescription());
                    item.setAmount(invoice.getPayTransPaymentAmount());
                    item.setBalance(invoice.getAccountBalance());
                    item.setClaimant(invoice.getScmName());
                    item.setName(invoice.getScmName());
                    item.setPaymentStatus(ReportHelper.getLookupName(getEntityManager(), PaymentStatus.class,
                        tx.getPaymentStatusCode()));
                }

                if (item.getAmount() != null) {
                    totalPayments = totalPayments.add(item.getAmount());
                }
                items.add(item);
            }

            response.setItems(items);
            response.setTotalPayment(totalPayments);
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
    public byte[] exportReport(CurrentSuspenseReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#exportReport(CurrentSuspenseReportResponse response, ExportType exportType)";
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

            // Check payments first
            renderItems(response, document, false, "Check Payments from Lockbox");

            // ACH Payments Next
            renderItems(response, document, true, "ACH Payments from Lockbox");

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
     * @param isACH if true, renders ACH payments only, otherwise, renders check payments
     * @param title the title of the group
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderItems(CurrentSuspenseReportResponse response, Document document, boolean isACH, String title)
        throws DocumentException {

        // group title
        Paragraph paragraph = new Paragraph(title, ReportHelper.TABLE_HEADER_FONT);
        document.add(paragraph);

        // table styling
        Table table = new Table(9);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {22, 8, 8, 8, 10, 8, 17, 10, 9});
        table.addCell(new Phrase("Payment Status", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("Date", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("BatBlkSeq", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("CSD", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell("Amount", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("BirthDate", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("Claimant Name", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell("Acct Balance", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("Status", ReportHelper.TABLE_DATA_FONT));

        // process rows
        List<CurrentSuspenseReportResponseItem> items = response.getItems();
        BigDecimal subTotal = new BigDecimal(0);
        for (CurrentSuspenseReportResponseItem row : items) {
            if (isACH && !row.isACH()) {
                continue;
            } else if (!isACH && row.isACH()) {
                continue;
            }
            if (row.getAmount() != null) {
                subTotal = subTotal.add(row.getAmount());
            }
            table.addCell(new Phrase(row.getPaymentStatus(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDate(row.getDate()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getBatchNumber(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getCsd(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getAmount()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDate(row.getBirthDate()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getClaimant(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getBalance()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getAccountStatus(), ReportHelper.TABLE_DATA_FONT));
        }

        // label for the total
        Cell summaryCell = new Cell(new Phrase(title + "   ", ReportHelper.TABLE_HEADER_FONT));
        summaryCell.setColspan(4);
        summaryCell.setBorder(Cell.TOP);
        summaryCell.setBorderWidthTop(1);
        summaryCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(summaryCell);

        // group total value
        Cell subTotalCell = ReportHelper.moneyCell(ReportHelper.formatMoney(subTotal), ReportHelper.TABLE_HEADER_FONT);
        subTotalCell.setBorder(Cell.TOP);
        subTotalCell.setBorderWidthTop(1);
        table.addCell(subTotalCell);

        // complete the table with empty cells
        Cell spacerCell = new Cell("");
        spacerCell.setColspan(4);
        table.addCell(spacerCell);

        if (isACH) { // show grand total
            spacerCell = new Cell("");
            spacerCell.setColspan(9);
            table.addCell(spacerCell);
            spacerCell = new Cell("");
            spacerCell.setColspan(4);
            table.addCell(spacerCell);

            Cell grandTotalCell = ReportHelper.moneyCell(ReportHelper.formatMoney(response.getTotalPayment()),
                ReportHelper.TABLE_HEADER_FONT);
            table.addCell(grandTotalCell);
        }
        document.add(table);
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(CurrentSuspenseReportResponse response) throws ReportGenerationException {
        return null;
    }
}
