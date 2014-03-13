/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.batchprocessing.AllDetails;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class handles data retrieval and export of the transaction register new receipts report.
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
public class TransactionRegisterNewReceiptsReportService extends BaseReportService implements
    ReportService<TransactionRegisterNewReceiptsReportRequest, TransactionRegisterNewReceiptsReportResponse> {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = TransactionRegisterNewReceiptsReportService.class.getName();

    /**
     * Default constructor.
     */
    public TransactionRegisterNewReceiptsReportService() {
    }

    /**
     * Generates the report for active credit balances records.
     *
     * @param request the request object
     * @return the model for the requested report.
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public TransactionRegisterNewReceiptsReportResponse getReport(TransactionRegisterNewReceiptsReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(TransactionRegisterNewReceiptsReportRequest request)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");

        try {
            TransactionRegisterNewReceiptsReportResponse response = new TransactionRegisterNewReceiptsReportResponse();
            List<TransactionRegisterNewReceiptsReportResponseItem> items =
                new ArrayList<TransactionRegisterNewReceiptsReportResponseItem>();

            // see http://apps.topcoder.com/forums/?module=Thread&threadID=810371&start=0&mc=7#1845278
            List<PaymentTransaction> transactions = getEntityManager().createQuery(
                "SELECT p FROM PaymentTransaction p WHERE p.deleted = false", PaymentTransaction.class).getResultList();

            for (PaymentTransaction transaction : transactions) {
                TransactionRegisterNewReceiptsReportResponseItem item =
                    new TransactionRegisterNewReceiptsReportResponseItem();

                boolean postal = false;
                AllDetails details = ReportHelper.getAllDetails(getEntityManager(), getLogger(),
                    String.valueOf(transaction.getPayTransactionKey()));
                if (details != null) {
                    if ("18".equals(details.getAgency())) {
                        postal = true;
                    }

                    if (postal) {
                        item.setFers(details.getTotalPostalFers());
                    } else {
                        item.setFers(details.getTotalNonPostalFers());
                    }
                }

                InvoiceData invoice = ReportHelper.getInvoiceData(getEntityManager(), logger, transaction);
                if (invoice != null) {
                    item.setClaimantName(invoice.getScmName());
                    item.setRedepositsPost982(invoice.getPost1082RedepositTotalPayment());
                    item.setRedepositsPre1082(invoice.getPre1082RedepositTotalPayment());
                    item.setDepositsPost982(invoice.getPost1082DepositTotalPayment());
                    item.setDepositsPre1082(invoice.getPre1082DepositTotalPayment());
                }
                item.setDate(transaction.getPayTransTransactionDate());
                item.setCsd(transaction.getCsd());

                Account account = ReportHelper.getAccountData(getEntityManager(), getLogger(), transaction);
                if (account != null) {
                    // per flytoj2ee we should not use retirement type and use plan type instead
                    item.setRetirementType(account.getPlanType());
                }

                item.setActionTime(transaction.getPayTransStatusDate());
                item.setAmount(transaction.getPayTransPaymentAmount());
                item.setActionType(transaction.getPayTransStatusCode().toString());
                item.setPaymentType(getPaymentType(transaction, postal));
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
     * Payment type per http://apps.topcoder.com/forums/?module=Thread&threadID=810371&start=0&mc=7#1845278.
     *
     * @param transaction the transaction to determine the payment type.
     * @param postal flag indicating if postal
     * @return the payment type
     */
    private String getPaymentType(PaymentTransaction transaction, boolean postal) {
        StringBuilder sb = new StringBuilder();
        sb.append(ReportHelper.formatDate(transaction.getPayTransTransactionDate())).append(" ");
        if (transaction.getResolvedSuspense() != null && transaction.getResolvedSuspense()) {
            if (transaction.getAchPayment() != null && transaction.getAchPayment()) {
                sb.append("Suspense Lockbox ACH");
            } else {
                sb.append("Suspense Lockbox Checks");
            }
        } else {
            if (transaction.getUserInserted() != null && transaction.getUserInserted()) {
                sb.append("Manual Payments");
            } else {
                if (transaction.getAchPayment() != null && transaction.getAchPayment()) {
                    sb.append("Accepted Lockbox ACH");
                } else {
                    sb.append("Accepted Lockbox Checks");
                }
            }
        }

        if (postal) {
            sb.append(" for Postal");
        } else {
            sb.append(" for Non - Postal");
        }
        return sb.toString();
    }

    /**
     * Renders the report using the given model.
     *
     * @param response the report model
     * @param exportType the file type to generate
     * @return the rendered report document
     * @throws IllegalArgumentException if any argument is null
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public byte[] exportReport(TransactionRegisterNewReceiptsReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(TransactionRegisterNewReceiptsReportResponse response, ExportType exportType)";
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

            // See http://apps.topcoder.com/forums/?module=Thread&threadID=810371&start=0
            Map<String, List<TransactionRegisterNewReceiptsReportResponseItem>> grouped =
                new HashMap<String, List<TransactionRegisterNewReceiptsReportResponseItem>>();
            List<TransactionRegisterNewReceiptsReportResponseItem> items = response.getItems();
            if (items != null) {
                for (TransactionRegisterNewReceiptsReportResponseItem item : items) {
                    if (grouped.get(item.getPaymentType()) == null) {
                        grouped.put(item.getPaymentType(),
                            new ArrayList<TransactionRegisterNewReceiptsReportResponseItem>());
                    }
                    grouped.get(item.getPaymentType()).add(item);
                }
            }

            Set<Entry<String, List<TransactionRegisterNewReceiptsReportResponseItem>>> entrySet = grouped.entrySet();
            for (Entry<String, List<TransactionRegisterNewReceiptsReportResponseItem>> entry : entrySet) {
                renderItems(entry.getValue(), document, entry.getKey());
            }

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
     * Renders a table of records.
     *
     * @param items Table details
     * @param document the current document
     * @param title The group title, currently not used
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderItems(List<TransactionRegisterNewReceiptsReportResponseItem> items, Document document,
        String title) throws DocumentException {

        document.add(new Paragraph(title, ReportHelper.TABLE_HEADER_FONT));

        // table styling
        Table table = new Table(10);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {15, 10, 7, 15, 8, 9, 9, 9, 9, 9});
        table.addCell(ReportHelper.linedCell("Claimant \nName", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedCell("\n CSD #", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedCell("Ret. \nType", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedCell("Action Code    \n& Time", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedMoneyCell("\nFERS    ", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedMoneyCell("Deposits    \n(Pre-10/82)", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedMoneyCell("Redeposits    \n(Pre-10/82)", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedMoneyCell("Deposits    \n(Post-9/82)", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedMoneyCell("Redeposits    \n(Post-9/82)", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedMoneyCell("Payment    \nAmount", ReportHelper.TABLE_HEADER_FONT));

        // process rows
        BigDecimal total = BigDecimal.ZERO;
        for (TransactionRegisterNewReceiptsReportResponseItem row : items) {
            table.addCell(new Phrase(row.getClaimantName(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getCsd(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getRetirementType(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getActionType() + " " + ReportHelper.formatDateWithTime(row.getActionTime()),
                ReportHelper.TABLE_DATA_FONT));
            table
                .addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getFers()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getDepositsPre1082()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getRedepositsPre1082()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getDepositsPost982()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getRedepositsPost982()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getAmount()),
                ReportHelper.TABLE_DATA_FONT));

            total = total.add(row.getAmount() == null ? BigDecimal.ZERO : row.getAmount());
        }

        // total row
        Cell spacerCell = new Cell("");
        spacerCell.setColspan(9);
        table.addCell(spacerCell);

        Cell grandTotalCell = ReportHelper.moneyCell(ReportHelper.formatMoney(total), ReportHelper.TABLE_HEADER_FONT);
        table.addCell(grandTotalCell);

        spacerCell = new Cell("");
        spacerCell.setColspan(10);
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
    public byte[] renderChart(TransactionRegisterNewReceiptsReportResponse response) throws ReportGenerationException {
        return null;
    }
}
