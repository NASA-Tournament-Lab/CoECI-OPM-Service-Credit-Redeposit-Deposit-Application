/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.batchprocessing.AllDetails;
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
import java.text.MessageFormat;
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
import com.lowagie.text.Element;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * Creates the suspense resolution report as described in
 * http://apps.topcoder.com/forums/?module=Thread&threadID=810139&start=0.
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
public class SuspenseResolutionReportService extends BaseReportService implements
    ReportService<SuspenseResolutionReportRequest, SuspenseResolutionReportResponse> {

    /**
     * Query used for inspected transactions.
     */
    private static final String QUERY_TRANSACTIONS = "SELECT a FROM PaymentTransaction a WHERE a.deleted = false "
        + "AND a.payTransTransactionDate >= :startDate AND a.payTransTransactionDate <= :endDate "
        + "AND a.resolvedSuspense = true";

    /**
     * Query used for transaction groups.
     */
    private static final String QUERY_GROUP = "SELECT a FROM PaymentTransaction a WHERE a.deleted = false "
        + "AND a.payTransTransactionDate <= :transactionDate ORDER BY a.payTransStatusDate";

    /**
     * Query used for getting the user.
     */
    private static final String QUERY_USER = "SELECT a FROM User a WHERE a.deleted = false "
        + "AND a.username = :username";

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = SuspenseResolutionReportService.class.getName();

    /**
     * Default constructor.
     */
    public SuspenseResolutionReportService() {
    }

    /**
     * Generates the report for suspense resolution records.
     *
     * @param request the request object
     * @return the model for the requested report.
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public SuspenseResolutionReportResponse getReport(SuspenseResolutionReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(SuspenseResolutionReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");
        Helper.checkNull(logger, signature, request.getStartDate(), "request.startDate");
        Helper.checkNull(logger, signature, request.getEndDate(), "request.endDate");

        try {
            SuspenseResolutionReportResponse response = new SuspenseResolutionReportResponse();
            response.setReportGenerationDate(new Date());
            response.setReportName(getReportName());
            response.setStartDate(request.getStartDate());
            response.setEndDate(request.getEndDate());
            response.setItems(new ArrayList<SuspenseResolutionReportResponseItem>());

            List<PaymentTransaction> transactions = getEntityManager()
                .createQuery(QUERY_TRANSACTIONS, PaymentTransaction.class)
                .setParameter("startDate", request.getStartDate()).setParameter("endDate", request.getEndDate())
                .getResultList();
            // for each PaymentTransaction [record] get the csd of the account to which it belongs
            for (PaymentTransaction record : transactions) {

                // Once we have this get all the other PaymentTransaction with the same csd WHERE their
                // payTransTransactionDate <= [record].payTransTransactionDate. Lets call this the GROUP.
                // In the group sort the records by payTransStatusDate.
                List<PaymentTransaction> group = getEntityManager().createQuery(QUERY_GROUP, PaymentTransaction.class)
                    .setParameter("transactionDate", record.getPayTransTransactionDate()).getResultList();

                SuspenseResolutionReportResponseItem item = new SuspenseResolutionReportResponseItem();
                item.setCsd(record.getScmClaimNumber());
                // The actual [record] will hold the "processed" amount as payTransPaymentAmount
                item.setProcessed(record.getPayTransPaymentAmount());
                // The first record in this group will hold the starting status of the payment as paymentStatusCode
                if (!group.isEmpty()) {
                    item.setStartedAs(String.valueOf(group.iterator().next().getPaymentStatusCode()));
                }
                // Use the AllDetails table to get a corresponding record for the payTransactionKey of the actual
                // [record].payTransactionKey, which will hold the type of payment as AllDetails.paymentType
                String key = String.valueOf(record.getPayTransactionKey());
                AllDetails detail = ReportHelper.getAllDetails(getEntityManager(), getLogger(), key);
                if (detail != null) {
                    item.setPayment(detail.getPaymentType());
                }

                for (PaymentTransaction groupItem : group) {
                    item.setTransactionDate(groupItem.getPayTransTransactionDate());
                    // In the group look for the record with payTransStatusCode == PaymentTransactionCodes.SUSPENDED
                    // and get the PaymentTransaction.payTransPaymentAmount which will give us the "suspense" amount
                    if (groupItem.getPaymentStatusCode() != null
                        && groupItem.getPaymentStatusCode() == PaymentTransactionCodes.SUSPENDED.getCode()) {
                        item.setSuspense(groupItem.getPayTransPaymentAmount());
                    }
                    // In the group look for the record with payTransStatusCode ==
                    // PaymentTransactionCodes.SUSPENSE_REFUND_COMPLETE and get the
                    // PaymentTransaction.payTransPaymentAmount which will give us the "resolved" amount
                    if (groupItem.getPaymentStatusCode() != null
                        && groupItem.getPaymentStatusCode() == PaymentTransactionCodes.SUSPENSE_REFUND_COMPLETE
                            .getCode()) {
                        item.setResolved(groupItem.getPayTransPaymentAmount());
                    }
                }

                InvoiceData invoice = ReportHelper.getInvoiceData(getEntityManager(), getLogger(), record);
                if (invoice != null) {
                    // From InvoiceData get the record matched on [record].payTransactionKey and get the
                    // accountBalanceNew
                    item.setAccount(invoice.getAccountBalanceNew());
                    // From InvoiceData get the record matched on [record].payTransactionKey and get the
                    // payTransStatusDescription
                    item.setResolution(invoice.getPayTransStatusDescription());
                }

                // TO get the user (i.e. supervisor) get the User from the Account for the csd. Once you have the
                // account User get the User.supervisor.
                Account account = ReportHelper.getAccountData(getEntityManager(), getLogger(), record);
                if (account == null) {
                    continue;
                }
                List<User> users = getEntityManager().createQuery(QUERY_USER, User.class)
                    .setParameter("username", account.getClaimOfficer()).getResultList();
                if (users.isEmpty()) {
                    // the report is grouped by supervisor, if there is no supervisor, we cannot group it
                    continue;
                }
                Long supervisorId = users.iterator().next().getSupervisorId();
                if (supervisorId == null) {
                    // the report is grouped by supervisor, if there is no supervisor, we cannot group it
                    continue;
                }
                User supervisor = getEntityManager().find(User.class, supervisorId);
                if (supervisor != null) {
                    item.setSupervisor(supervisor.getFirstName() + " " + supervisor.getLastName());
                }
                response.getItems().add(item);
            }

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
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public byte[] exportReport(SuspenseResolutionReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(SuspenseResolutionReportResponse response, ExportType exportType)";
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

            // group by supervisor
            Map<String, List<SuspenseResolutionReportResponseItem>> grouped =
                new HashMap<String, List<SuspenseResolutionReportResponseItem>>();
            List<SuspenseResolutionReportResponseItem> items = response.getItems();
            if (items != null) {
                for (SuspenseResolutionReportResponseItem item : items) {
                    if (grouped.get(item.getSupervisor()) == null) {
                        grouped.put(item.getSupervisor(), new ArrayList<SuspenseResolutionReportResponseItem>());
                    }
                    grouped.get(item.getSupervisor()).add(item);
                }
            }

            if (items.isEmpty()) {
                // render empty body like in the screenshot
                renderItems(response, new ArrayList<SuspenseResolutionReportResponseItem>(), document, "");
            }

            Set<Entry<String, List<SuspenseResolutionReportResponseItem>>> entrySet = grouped.entrySet();
            for (Entry<String, List<SuspenseResolutionReportResponseItem>> entry : entrySet) {
                renderItems(response, entry.getValue(), document, entry.getKey());
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
     * @param response Response records
     * @param items Table details
     * @param document the current document
     * @param title The group title, currently not used
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderItems(SuspenseResolutionReportResponse response,
        List<SuspenseResolutionReportResponseItem> items, Document document, String title) throws DocumentException {

        // table styling
        Table table = new Table(10);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {8, 8, 10, 10, 10, 22, 2, 2, 10, 10});

        // group header
        Cell groupHeaderCell = new Cell(new Phrase("Supervisor: " + title, ReportHelper.TABLE_HEADER_FONT));
        groupHeaderCell.setColspan(10);
        table.addCell(groupHeaderCell);

        table.addCell(ReportHelper.textLinedCell("CSD #", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.textLinedMoneyCell("Suspense", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.textLinedMoneyCell("Resolved", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.textLinedMoneyCell("Processed", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.textLinedCell("Started As", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.textLinedCell("Resolution", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(new Phrase("     ", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(new Phrase("     ", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.textLinedCell("Payment       ", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.textLinedMoneyCell("Account", ReportHelper.TABLE_HEADER_FONT));

        // process rows
        BigDecimal total = BigDecimal.ZERO;
        Date oldest = null;
        for (SuspenseResolutionReportResponseItem row : items) {
            table.addCell(new Phrase(row.getCsd(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getSuspense()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getResolved()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getProcessed()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getStartedAs(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getResolution(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase("     ", ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase("     ", ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getPayment(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getAccount()),
                ReportHelper.TABLE_DATA_FONT));

            total = total.add(row.getResolved() == null ? BigDecimal.ZERO : row.getResolved());

            if (row.getTransactionDate() != null) {
                if (oldest == null) {
                    oldest = row.getTransactionDate();
                } else if (oldest.before(row.getTransactionDate())) {
                    oldest = row.getTransactionDate();
                }
            }
        }

        // total row
        Cell spacerCell = new Cell("");
        spacerCell.setColspan(10);
        table.addCell(spacerCell);

        // add report note
        Cell note1Cell = new Cell(new Phrase("processed payments.", ReportHelper.TABLE_DATA_FONT));
        note1Cell.setColspan(10);
        table.addCell(note1Cell);

        String template = "employees resolved payments dated betwee {0} and {1}. The oldest one entered the";
        String longText = MessageFormat.format(template,
            ReportHelper.formatDate(response.getStartDate(), "MMMM dd, yyyy"),
            ReportHelper.formatDate(response.getEndDate(), "MMMM dd, yyyy"));
        Cell note2Cell = new Cell(new Phrase(longText, ReportHelper.TABLE_HEADER_FONT));
        note2Cell.setColspan(10);
        note2Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(note2Cell);

        template = "Service Credit system on {0}.";
        String shortText = MessageFormat.format(template, ReportHelper.formatDate(oldest, "MMMM dd, yyyy"));
        Cell note3Cell = new Cell(new Phrase(shortText, ReportHelper.TABLE_HEADER_FONT));
        note3Cell.setColspan(10);
        note3Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(note3Cell);

        // add total row
        Cell doubleUnderlinedCell = new Cell("     ");
        doubleUnderlinedCell.setBorder(Cell.BOTTOM);
        doubleUnderlinedCell.setBorderWidthBottom(2);
        doubleUnderlinedCell.setColspan(10);
        table.addCell(doubleUnderlinedCell);

        Cell singleUnderlinedCell = new Cell("     ");
        singleUnderlinedCell.setBorder(Cell.BOTTOM);
        singleUnderlinedCell.setBorderWidthBottom(1);
        singleUnderlinedCell.setColspan(10);
        table.addCell(singleUnderlinedCell);

        Cell totalTextCell = new Cell(new Phrase("Users Resolved Payments Totalling:", ReportHelper.TABLE_HEADER_FONT));
        totalTextCell.setColspan(8);
        totalTextCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(totalTextCell);

        Cell totalCell = new Cell(new Phrase(ReportHelper.formatMoney(total), ReportHelper.TABLE_HEADER_FONT));
        totalCell.setColspan(2);
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(totalCell);

        table.addCell(singleUnderlinedCell);

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
    public byte[] renderChart(SuspenseResolutionReportResponse response) throws ReportGenerationException {
        return null;
    }
}
