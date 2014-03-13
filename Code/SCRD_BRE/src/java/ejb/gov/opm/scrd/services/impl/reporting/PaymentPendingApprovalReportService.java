/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.PaymentType;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class handles data retrieval and export of the payment pending approval report.
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
public class PaymentPendingApprovalReportService extends BaseReportService implements
    ReportService<PaymentPendingApprovalReportRequest, PaymentPendingApprovalReportResponse> {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = PaymentPendingApprovalReportService.class.getName();

    /**
     * Represents the pending approval payment type.
     * It is modified by setter.
     * It is injected by Spring.
     * It can not be null after injected.
     */
    @Autowired
    private PaymentType paymentType;

    /**
     * Generates the report for payments pending supervisor approval records.
     *
     * @param request the request object
     * @return the model for the requested report.
     * @throws IllegalArgumentException - if the request is null.
     * @throws ReportGenerationException - if there is any problem when generating response.
     */
    public PaymentPendingApprovalReportResponse getReport(PaymentPendingApprovalReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(CurrentSuspenseReportRequest request)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});

        Helper.checkNull(logger, signature, request, "request");
        try {
            List<Payment> payments = getEntityManager()
                .createQuery("SELECT p FROM Payment p WHERE p.deleted = false " + "AND p.paymentType = :paymentType",
                    Payment.class).setParameter("paymentType", paymentType).getResultList();

            PaymentPendingApprovalReportResponse response = new PaymentPendingApprovalReportResponse();
            List<PaymentPendingApprovalReportResponseItem> items =
                new ArrayList<PaymentPendingApprovalReportResponseItem>();

            for (Payment payment : payments) {
                PaymentPendingApprovalReportResponseItem item = new PaymentPendingApprovalReportResponseItem();
                item.setBatchNumber(payment.getBatchNumber() + "-" + payment.getBlockNumber() + "-"
                    + payment.getSequenceNumber());
                item.setDepositDate(payment.getDepositDate());
                item.setModifiedTime(payment.getStatusDate());
                item.setAmount(payment.getAmount());
                item.setCsd(payment.getClaimNumber());
                item.setDateOfBirth(payment.getMasterClaimantBirthdate());
                if (payment.getAccountStatus() != null) {
                    item.setAccountStatus(payment.getAccountStatus().getName());
                }
                item.setPaymentInfo(payment.getNote());
                item.setApprovalUser(payment.getApprovalUser());
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
    public byte[] exportReport(PaymentPendingApprovalReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(PaymentPendingApprovalReportResponse response, ExportType exportType)";
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

            List<PaymentPendingApprovalReportResponseItem> items = response.getItems();
            Set<String> approvers = new HashSet<String>();
            for (PaymentPendingApprovalReportResponseItem row : items) {
                approvers.add(row.getApprovalUser());
            }

            for (String user : approvers) {
                renderItems(response, document, user);
            }

            // summary
            renderGrouping(response, document);
            renderGrandTotal(response, document);

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
     * Renders the grand total.
     * @param response the response object
     * @param document the document report
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderGrandTotal(PaymentPendingApprovalReportResponse response, Document document)
        throws DocumentException {
        Table table = new Table(2);
        table.setWidths(new float[]{80, 20});
        table.setBorder(Table.NO_BORDER);
        table.setWidth(40);
        table.setPadding(1);
        Cell cell = new Cell(new Phrase("Grand Total", ReportHelper.TABLE_HEADER_FONT));
        cell.setBorder(Cell.BOTTOM);
        cell.setBorderWidth(1f);
        table.addCell(cell);
        Cell subTotal = new Cell(new Phrase(response.getItems().size() + "", ReportHelper.TABLE_HEADER_FONT));
        subTotal.setBorder(Cell.BOTTOM);
        subTotal.setBorderWidth(1f);
        table.addCell(subTotal);
        document.add(table);
    }

    /**
     * Render grouping.
     * @param response The response object
     * @param document The document report
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderGrouping(PaymentPendingApprovalReportResponse response, Document document)
        throws DocumentException {
        Table table = new Table(2);
        table.setWidths(new float[]{80, 20});
        table.setBorder(Table.NO_BORDER);
        table.setWidth(40);
        table.setPadding(1);
        Cell cell = new Cell(new Phrase("Recievables Technician", ReportHelper.TABLE_HEADER_FONT));
        cell.setBorder(Cell.TOP | Cell.BOTTOM);
        table.addCell(cell);
        Cell subTotal = new Cell(new Phrase(response.getItems().size() + "", ReportHelper.TABLE_HEADER_FONT));
        subTotal.setBorder(Cell.TOP | Cell.BOTTOM);
        table.addCell(subTotal);
        document.add(table);
    }

    /**
     * Renders a table of records.
     * @param response the report model
     * @param document the current document
     * @param user the group user
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderItems(PaymentPendingApprovalReportResponse response, Document document, String user)
        throws DocumentException {
        // group title
        Paragraph paragraph = new Paragraph("Active User          " + user, ReportHelper.TABLE_HEADER_FONT);
        document.add(paragraph);

        // table styling
        Table table = new Table(8);
        table.setBorder(Table.TOP);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {15, 10, 15, 10, 10, 10, 15, 15});
        table.addCell(new Phrase("Bat Blk Seq", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Deposit", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Modified Time", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(ReportHelper.moneyCell("Amount", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("CSD #", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("DOB", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Account Status", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Payment Info", ReportHelper.TABLE_HEADER_UNDERLINE));

        // process rows
        List<PaymentPendingApprovalReportResponseItem> items = response.getItems();
        for (PaymentPendingApprovalReportResponseItem row : items) {
            if (!row.getApprovalUser().equals(user)) {
                continue;
            }
            table.addCell(new Phrase(row.getBatchNumber(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDate(row.getDepositDate()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDateWithTime(row.getModifiedTime()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getAmount()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getCsd(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDate(row.getDateOfBirth()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getAccountStatus(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getPaymentInfo(), ReportHelper.TABLE_DATA_FONT));
        }
        document.add(table);
    }

    /**
     * Sets the value of the field <code>paymentType</code>.
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(PaymentPendingApprovalReportResponse response) throws ReportGenerationException {
        return null;
    }
}
