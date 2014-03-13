/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
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
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class handles data retrieval and export of the active credit balances over twenty-five dollars report.
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
public class ActiveCreditBalancesReportService extends BaseReportService implements
    ReportService<ActiveCreditBalancesReportRequest, ActiveCreditBalancesReportResponse> {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ActiveCreditBalancesReportService.class.getName();

    /**
     * Default constructor.
     */
    public ActiveCreditBalancesReportService() {
    }
    
    /**
     * Generates the report for active credit balances records.
     *
     * @param request the request object
     * @return the model for the requested report.
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public ActiveCreditBalancesReportResponse getReport(ActiveCreditBalancesReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(ActiveCreditBalancesReportRequest request)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");

        try {
            // http://apps.topcoder.com/forums/?module=Thread&threadID=808983&start=0
            List<InvoiceData> invoices = getEntityManager().createQuery(
                "SELECT i FROM InvoiceData i WHERE i.deleted = false AND i.accountBalance >= 25 "
                    + "AND i.accountNoteType = 'ACH_OVER_PAYMENT'", InvoiceData.class).getResultList();

            ActiveCreditBalancesReportResponse response = new ActiveCreditBalancesReportResponse();
            List<ActiveCreditBalancesReportResponseItem> items =
                new ArrayList<ActiveCreditBalancesReportResponseItem>();

            for (InvoiceData invoice : invoices) {
                List<PaymentTransaction> transactions = getEntityManager()
                    .createQuery(
                        "SELECT t FROM PaymentTransaction t WHERE t.deleted = false "
                            + "AND t.payTransactionKey = :payTransactionKey", PaymentTransaction.class)
                    .setParameter("payTransactionKey", invoice.getPayTransactionKey()).getResultList();

                for (PaymentTransaction tx : transactions) {
                    ActiveCreditBalancesReportResponseItem item = new ActiveCreditBalancesReportResponseItem();
                    item.setCsd(tx.getCsd());
                    item.setCreditBalance(invoice.getOverThePaymentAmount());
                    item.setDateOfOverPayment(tx.getPayTransTransactionDate());
                    items.add(item);
                }
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
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public byte[] exportReport(ActiveCreditBalancesReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(ActiveCreditBalancesReportResponse response, ExportType exportType)";
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
     * Renders a table of records.
     *
     * @param response the report model
     * @param document the current document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private static void renderItems(ActiveCreditBalancesReportResponse response, Document document)
        throws DocumentException {

        // group title
        Paragraph paragraph = new Paragraph("", ReportHelper.TABLE_HEADER_FONT);
        document.add(paragraph);

        // table styling
        Table table = new Table(5);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(60);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {20, 20, 20, 10, 30});

        Cell csdCell = new Cell(new Phrase("CSD Number", ReportHelper.TABLE_BIG_HEADER_FONT));
        csdCell.setBorder(Cell.BOTTOM);
        csdCell.setBorderWidthBottom(1);

        table.addCell(csdCell);
        table.addCell(new Phrase("                ", ReportHelper.TABLE_BIG_HEADER_FONT));
        table.addCell(ReportHelper.linedMoneyCell("Credit Balance", ReportHelper.TABLE_BIG_HEADER_FONT));
        table.addCell(new Phrase("                ", ReportHelper.TABLE_BIG_HEADER_FONT));
        table.addCell(ReportHelper.linedMoneyCell("Date of Over Payment", ReportHelper.TABLE_BIG_HEADER_FONT));

        // process rows
        List<ActiveCreditBalancesReportResponseItem> items = response.getItems();
        for (ActiveCreditBalancesReportResponseItem row : items) {
            table.addCell(new Phrase(row.getCsd(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase("            ", ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(row.getCreditBalance()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase("            ", ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatDate(row.getDateOfOverPayment()),
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
    public byte[] renderChart(ActiveCreditBalancesReportResponse response) throws ReportGenerationException {
        return null;
    }

}
