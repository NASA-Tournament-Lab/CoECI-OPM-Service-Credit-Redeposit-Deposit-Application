/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.batchprocessing.PaymentTransactionCodes;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
 * This class handles data retrieval and export of the monthly suspense list report.
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
public class MonthlySuspenseListReportService extends BaseReportService implements
    ReportService<MonthlySuspenseListReportRequest, MonthlySuspenseListReportResponse> {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = MonthlySuspenseListReportService.class.getName();
    /**
     * Suspense status.
     */
    private static final List<Integer> SUSPENSE_STATUS = Arrays.asList(PaymentTransactionCodes.SUSPENDED.getCode(),
        PaymentTransactionCodes.POSTED_PENDING.getCode(),
        PaymentTransactionCodes.SUSPENSE_REFUND_PENDING_APPROVAL.getCode(),
        PaymentTransactionCodes.CREDIT_BALANCE_REFUND_PENDING_APPROVAL.getCode(),
        PaymentTransactionCodes.UNRESOLVED.getCode());
    /**
     * Formats the month with year.
     */
    private static final String TITLE_DATE_FORMAT = "MMMMM yyyy";

    /**
     * Default constructor.
     */
    public MonthlySuspenseListReportService() {
    }

    /**
     * Creates the report for the given request. The report is encapsulated in the ReportResponse object.
     *
     * @param request the request data to generate report.
     * @return ReportResponse instance containing the report data, can not be null.
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public MonthlySuspenseListReportResponse getReport(MonthlySuspenseListReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(MonthlySuspenseListReportRequest request)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});

        Helper.checkNull(logger, signature, request, "Monthly suspense list report request");

        try {
            Date month = request.getMonth();
            if (month == null) {
                month = new Date();
            }

            Calendar monthStart = Calendar.getInstance();
            monthStart.setTime(month);
            monthStart.set(Calendar.DATE, 1);
            monthStart.set(Calendar.HOUR_OF_DAY, 0);
            monthStart.set(Calendar.MINUTE, 0);
            monthStart.set(Calendar.SECOND, 0);
            monthStart.set(Calendar.MILLISECOND, 0);

            Calendar nextMonth = Calendar.getInstance();
            nextMonth.setTime(monthStart.getTime());
            nextMonth.add(Calendar.MONTH, 1);

            List<PaymentTransaction> transactions = getEntityManager()
                .createQuery(
                    "SELECT t FROM PaymentTransaction t WHERE t.deleted = false AND t.paymentStatusCode IN (:statuses) "
                        + "AND t.payTransStatusDate >= :monthStart AND t.payTransStatusDate < :nextMonthStart",
                    PaymentTransaction.class).setParameter("statuses", SUSPENSE_STATUS)
                .setParameter("monthStart", monthStart.getTime()).setParameter("nextMonthStart", nextMonth.getTime())
                .getResultList();

            MonthlySuspenseListReportResponse response = new MonthlySuspenseListReportResponse();
            List<MonthlySuspenseListReportResponseItem> items = new ArrayList<MonthlySuspenseListReportResponseItem>();

            for (PaymentTransaction tx : transactions) {
                InvoiceData invoice = ReportHelper.getInvoiceData(getEntityManager(), getLogger(), tx);
                MonthlySuspenseListReportResponseItem item = new MonthlySuspenseListReportResponseItem();
                item.setBatchNumber(tx.getPayTransBatchNumber() + "-" + tx.getPayTransBlockNumber() + "-"
                    + tx.getPayTransSequenceNumber());
                item.setCsd(tx.getCsd());
                item.setType(tx.getAchPayment() ? "ACH" : "Chk");
                item.setImported(tx.getPayTransStatusDate());
                item.setDeposited(tx.getPayTransTransactionDate());
                if (invoice != null) {
                    item.setCurrentStatus(invoice.getPayTransStatusDescription());
                    item.setAmount(invoice.getPayTransPaymentAmount());
                }
                items.add(item);
            }

            response.setItems(items);
            response.setReportName(getReportName() + " for "
                + new SimpleDateFormat(TITLE_DATE_FORMAT).format(monthStart.getTime()));
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
    public byte[] exportReport(MonthlySuspenseListReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(MonthlySuspenseListReportResponse response, ExportType exportType)";
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
     * Renders the table of records.
     *
     * @param response the report model
     * @param document the current document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderItems(MonthlySuspenseListReportResponse response, Document document) throws DocumentException {

        // group title
        Paragraph paragraph = new Paragraph("", ReportHelper.TABLE_HEADER_FONT);
        document.add(paragraph);

        // table styling
        Table table = new Table(7);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {15, 8, 13, 13, 13, 13, 25});
        table.addCell(new Phrase("BatBlkSeq", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("Type", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("CSD", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("Deposited", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell("Amount", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("Imported", ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("Current Status", ReportHelper.TABLE_DATA_FONT));

        // process rows
        List<MonthlySuspenseListReportResponseItem> items = response.getItems();
        BigDecimal subTotal = new BigDecimal(0);
        for (MonthlySuspenseListReportResponseItem row : items) {
            subTotal = subTotal.add(row.getAmount() == null ? BigDecimal.ZERO : row.getAmount());
            table.addCell(new Phrase(row.getBatchNumber(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getType(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getCsd(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDate(row.getDeposited()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getAmount()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDateWithTime(row.getImported()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getCurrentStatus(), ReportHelper.TABLE_DATA_FONT));
        }

        // label for the total
        Cell summaryCell = new Cell(new Phrase("Grand total:", ReportHelper.TABLE_HEADER_FONT));
        summaryCell.setColspan(4);
        summaryCell.setBorder(Cell.TOP);
        summaryCell.setBorderWidthTop(1);
        summaryCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(summaryCell);

        // grand total value
        Cell subTotalCell = ReportHelper.moneyCell(ReportHelper.formatMoney(subTotal), ReportHelper.TABLE_HEADER_FONT);
        subTotalCell.setBorder(Cell.TOP);
        subTotalCell.setBorderWidthTop(1);
        table.addCell(subTotalCell);

        // complete the table with empty cells
        Cell spacerCell = new Cell("");
        spacerCell.setBorder(Cell.TOP);
        spacerCell.setBorderWidthTop(1);
        spacerCell.setColspan(2);
        table.addCell(spacerCell);

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
    public byte[] renderChart(MonthlySuspenseListReportResponse response) throws ReportGenerationException {
        return null;
    }
}
