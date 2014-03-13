/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountHolder;
import gov.opm.scrd.entities.application.AccountSearchFilter;
import gov.opm.scrd.entities.application.Address;
import gov.opm.scrd.entities.application.Billing;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.services.AccountService;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
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
 * This class handles data retrieval and export of the payment history report.
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
public class PaymentHistoryReportService extends BaseReportService implements
    ReportService<PaymentHistoryReportRequest, PaymentHistoryReportResponse> {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = PaymentHistoryReportService.class.getName();

    /**
     * The account service.
     */
    @EJB
    private AccountService accountService;

    /**
     * Default constructor.
     */
    public PaymentHistoryReportService() {
    }

    /**
     * Creates the report for the given request. The report is encapsulated in the ReportResponse object.
     *
     * @param request the request data to generate report.
     * @return ReportResponse instance containing the report data, can not be null.
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public PaymentHistoryReportResponse getReport(PaymentHistoryReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(PaymentHistoryReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");

        try {
            AccountSearchFilter filter = new AccountSearchFilter();
            filter.setClaimNumber(request.getCsd());
            SearchResult<Account> accounts = accountService.search(filter);
            if (accounts.getTotal() == 0) {
                ReportGenerationException error = new ReportGenerationException(
                    "Could not generate report because of missing or ambiguous account match for CSD: "
                        + filter.getClaimNumber());
                LoggingHelper.logException(getLogger(), signature, error);
                throw error;
            }

            Account account = accounts.getItems().get(0);
            AccountHolder accountHolder = account.getHolder();
            if (accountHolder == null) {
                ReportGenerationException error = new ReportGenerationException(
                    "Could not generate report because of missing account holder data.");
                LoggingHelper.logException(getLogger(), signature, error);
                throw error;
            }

            PaymentHistoryReportResponse response = new PaymentHistoryReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());

            Address address = accountHolder.getAddress();
            if (address != null) {
                response.setAddress1(address.getStreet1());
                response.setAddress2(address.getStreet2());
                response.setCity(address.getCity());
                response.setState(address.getState() == null ? null : address.getState().getName());
                response.setZip(address.getZipCode());
            }
            response.setCsd(account.getClaimNumber());
            response.setPhone(accountHolder.getTelephone());
            response.setUsername(getFullName(accountHolder));
            response.setItems(new ArrayList<PaymentHistoryReportResponseItem>());

            if (account.getBillingSummary() != null) {
                List<Billing> billings = account.getBillingSummary().getBillings();
                for (Billing billing : billings) {
                    int sequence = billing.getPaymentOrder();
                    List<Payment> payments = getEntityManager()
                        .createQuery(
                            "SELECT p FROM Payment p WHERE p.deleted = false AND p.sequence = :sequence "
                                + "AND p.claimNumber = :claimNumber", Payment.class).setParameter("sequence", sequence)
                        .setParameter("claimNumber", account.getClaimNumber()).getResultList();

                    if (payments.isEmpty()) {
                        // this bill is not paid yet ?
                        continue;
                    }

                    Payment payment = payments.get(0);

                    PaymentHistoryReportResponseItem item = new PaymentHistoryReportResponseItem();
                    item.setBalanceDueAfterPayment(billing.getBalance());
                    item.setBalanceDueBeforePayment(billing.getInitialBilling());
                    item.setDateOfPayment(payment.getDepositDate());
                    item.setDueBeforePayment(sum(billing.getInitialBilling(), billing.getAdditionalInterest()));
                    item.setInterestOnPrior(billing.getAdditionalInterest());
                    item.setPaymentAmount(payment.getAmount());
                    item.setProcessDate(payment.getTransactionDate());
                    item.setTotalOfPaymentsToDate(billing.getTotalPayments());
                    response.getItems().add(item);
                }
            }
            return response;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "An error has occurred when accessing persistence.", e));
        } catch (OPMException e) {
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "An error has occurred when generating report data.", e));
        }
    }

    /**
     * Gets account holder's full name.
     * @param accountHolder The account holder.
     * @return full name
     */
    private String getFullName(AccountHolder accountHolder) {
        StringBuilder sb = new StringBuilder();
        if (accountHolder.getFirstName() != null) {
            sb.append(accountHolder.getFirstName());
        }
        if (sb.length() > 0) {
            sb.append(" ");
        }
        if (accountHolder.getMiddleInitial() != null) {
            sb.append(accountHolder.getMiddleInitial());
        }
        if (sb.length() > 0) {
            sb.append(" ");
        }
        if (accountHolder.getLastName() != null) {
            sb.append(accountHolder.getLastName());
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
     * @throws ReportGenerationException - if there is any problem when generating response.
     */
    public byte[] exportReport(PaymentHistoryReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(PaymentHistoryReportResponse response, ExportType exportType)";
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

            renderAccountHolder(response, document);
            renderBillingEntries(response, document);

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
     * Renders the billing entries.
     * @param response The payment history report response
     * @param document the document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderBillingEntries(PaymentHistoryReportResponse response, Document document)
        throws DocumentException {
        // table styling
        Table table = new Table(8);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.LEFT | Cell.RIGHT);
        cell.setHorizontalAlignment(Cell.ALIGN_RIGHT);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(2);

        // table header and column widths
        table.setWidths(new float[] {10, 10, 10, 15, 15, 15, 15, 15});
        table.addCell(headerCell(new Phrase("Date of Payment", ReportHelper.TABLE_DATA_FONT)));
        table.addCell(headerCell(new Phrase("Process Date", ReportHelper.TABLE_DATA_FONT)));
        table.addCell(headerCell(new Phrase("Balance Due Before Payment", ReportHelper.TABLE_DATA_FONT)));
        table.addCell(headerCell(new Phrase("Interest On Prior", ReportHelper.TABLE_DATA_FONT)));
        table.addCell(headerCell(new Phrase("Due Before Payment", ReportHelper.TABLE_DATA_FONT)));
        table.addCell(headerCell(new Phrase("Payment Amount", ReportHelper.TABLE_DATA_FONT)));
        table.addCell(headerCell(new Phrase("Balance Due After Payment", ReportHelper.TABLE_DATA_FONT)));
        table.addCell(headerCell(new Phrase("Total of Payments to Date", ReportHelper.TABLE_DATA_FONT)));

        // process rows
        List<PaymentHistoryReportResponseItem> items = response.getItems();
        for (PaymentHistoryReportResponseItem row : items) {
            table.addCell(new Phrase(ReportHelper.formatDate(row.getDateOfPayment()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDate(row.getProcessDate()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getBalanceDueBeforePayment()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getInterestOnPrior()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getDueBeforePayment()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getPaymentAmount()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getBalanceDueAfterPayment()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getTotalOfPaymentsToDate()),
                ReportHelper.TABLE_DATA_FONT));
        }
        document.add(table);
    }

    /**
     * Gets the header cell.
     * @param phrase The phrase
     * @return cell header
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private Cell headerCell(Phrase phrase) throws DocumentException {
        Cell cell = new Cell(phrase);
        cell.setBorder(Cell.LEFT | Cell.RIGHT | Cell.BOTTOM);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        return cell;
    }

    /**
     * Renders account holder.
     * @param response the response object.
     * @param document the document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderAccountHolder(PaymentHistoryReportResponse response, Document document)
        throws DocumentException {
        Table table = new Table(2);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(80);
        table.setPadding(0);

        // table header and column widths
        table.setWidths(new float[] {80, 20});

        table.addCell(new Phrase(response.getUsername(), ReportHelper.TABLE_HEADER_FONT));
        table.addCell(new Phrase("CSD#" + response.getCsd(), ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase(response.getAddress1(), ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase("", ReportHelper.TABLE_DATA_FONT));
        if (response.getAddress2() != null) {
            table.addCell(new Phrase(response.getAddress2(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase("", ReportHelper.TABLE_DATA_FONT));
        }
        table.addCell(new Phrase(formatStateLine(response), ReportHelper.TABLE_DATA_FONT));
        document.add(table);
        document.add(new Paragraph(" "));
    }

    /**
     * Formats the state line.
     * @param response The response object
     * @return state line
     */
    private String formatStateLine(PaymentHistoryReportResponse response) {
        StringBuilder sb = new StringBuilder();
        if (response.getCity() != null) {
            sb.append(response.getCity());
        }
        if (sb.length() > 0) {
            sb.append(", ");
        }
        if (response.getState() != null) {
            sb.append(response.getState());
        }
        if (sb.length() > 0) {
            sb.append("  ");
        }
        if (response.getZip() != null) {
            sb.append(response.getZip());
        }
        String stateLine = sb.toString();
        return stateLine;
    }

    /**
     * Null checking sum function for decimal objects.
     *
     * @param base the base value
     * @param value the value to add
     * @return the sum of the two numbers, nulls are treated as zero
     */
    private BigDecimal sum(BigDecimal base, BigDecimal value) {
        if (base == null && value != null) {
            return value;
        }

        if (value == null) {
            return base;
        }
        return base.add(value);
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(PaymentHistoryReportResponse response) throws ReportGenerationException {
        return null;
    }
}
