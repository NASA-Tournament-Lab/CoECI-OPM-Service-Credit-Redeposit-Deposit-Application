/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.batchprocessing.AllDetails;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class handles data retrieval and export of the payment by type range report.
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
public class PaymentByTypeRangeReportService extends BaseReportService implements
    ReportService<PaymentByTypeRangeReportRequest, PaymentByTypeRangeReportResponse> {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = PaymentByTypeRangeReportService.class.getName();

    /**
     * Default constructor.
     */
    public PaymentByTypeRangeReportService() {
    }

    /**
     * Creates the report for the given request. The report is encapsulated in the ReportResponse object.
     *
     * @param request the request data to generate report.
     * @return ReportResponse instance containing the report data, can not be null.
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public PaymentByTypeRangeReportResponse getReport(PaymentByTypeRangeReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(PaymentByTypeRangeReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");
        Helper.checkNull(logger, signature, request.getStartDate(), "request.startDate");
        Helper.checkNull(logger, signature, request.getEndDate(), "request.endDate");

        try {
            List<Payment> payments = getEntityManager()
                .createQuery(
                    "SELECT p FROM Payment p WHERE p.deleted = false AND "
                        + "p.depositDate >= :startDate and p.depositDate <= :endDate", Payment.class)
                .setParameter("startDate", request.getStartDate()).setParameter("endDate", request.getEndDate())
                .getResultList();

            PaymentByTypeRangeReportResponse response = new PaymentByTypeRangeReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setStartDate(request.getStartDate());
            response.setEndDate(request.getEndDate());
            response.setItems(new ArrayList<PaymentByTypeRangeReportResponseItem>());

            for (Payment payment : payments) {
                PaymentByTypeRangeReportResponseItem item = new PaymentByTypeRangeReportResponseItem();
                item.setDate(payment.getDepositDate());
                item.setCsd(payment.getClaimNumber());
                item.setPaymentAmount(payment.getAmount());
                AllDetails details = ReportHelper.getAllDetails(getEntityManager(), getLogger(),
                    payment.getTransactionKey());
                if (details != null) {
                    item.setRetirementType(details.getScmRetirementTypeDescription());
                }
                response.getItems().add(item);
            }

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
    public byte[] exportReport(PaymentByTypeRangeReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(PaymentByTypeRangeReportResponse response, ExportType exportType)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"response", "exportType"}, new Object[] {response,
            exportType});

        Helper.checkNull(logger, signature, response, "response");
        Helper.checkNull(logger, signature, exportType, "exportType");
        Helper.checkNull(logger, signature, response.getStartDate(), "response.startDate");
        Helper.checkNull(logger, signature, response.getEndDate(), "response.endDate");

        try {

            byte[] result = null;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            document.setMargins(ReportHelper.MARGIN_LR, ReportHelper.MARGIN_LR, ReportHelper.MARGIN_TB,
                ReportHelper.MARGIN_TB);

            // Associate it with output stream
            ReportHelper.initDocumentFormat(document, outputStream, exportType);

            String title = MessageFormat.format("Payments from {0} through {1}", new SimpleDateFormat("MMMM dd, yyyy")
                .format(response.getStartDate()), new SimpleDateFormat("MMMM dd, yyyy").format(response.getEndDate()));
            HeaderFooter head = ReportHelper.generateSimpleHeader(document, response.getReportGenerationDate(), title);

            document.setHeader(head);
            document.open();

            renderResults(response, document);

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
     * Renders the records into the table.
     *
     * @param response the report model
     * @param document the current document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderResults(PaymentByTypeRangeReportResponse response, Document document) throws DocumentException {
        // table styling
        Table table = new Table(4);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {25, 25, 25, 25});
        table.addCell(new Phrase("Retirement Type", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("CSD", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(ReportHelper.moneyCell("Amount", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Date", ReportHelper.TABLE_HEADER_UNDERLINE));

        // process rows
        List<PaymentByTypeRangeReportResponseItem> items = response.getItems();
        for (PaymentByTypeRangeReportResponseItem row : items) {
            table.addCell(new Phrase(row.getRetirementType(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getCsd(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getPaymentAmount()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDate(row.getDate(), "EEEE, MMMM dd, yyyy"),
                ReportHelper.TABLE_DATA_FONT));
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
    public byte[] renderChart(PaymentByTypeRangeReportResponse response) throws ReportGenerationException {
        return null;
    }
}
