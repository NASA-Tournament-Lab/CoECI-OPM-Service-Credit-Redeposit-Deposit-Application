/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.AllDetails;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class handles data retrieval and export of the total payment summary report.
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
public class TotalPaymentSummaryReportService extends BaseReportService implements
    ReportService<TotalPaymentSummaryReportRequest, TotalPaymentSummaryReportResponse> {
    /**
     * Query used to count active accounts.
     */
    private static final String QUERY_COUNT_ACCOUNTS = "SELECT COUNT(a) FROM Account a WHERE a.deleted = false"
        + " AND a.frozen = false";
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = TotalPaymentSummaryReportService.class.getName();
    /**
     * The chart width.
     */
    @Autowired
    private Integer chartWidth;
    /**
     * The chart height.
     */
    @Autowired
    private Integer chartHeight;

    /**
     * Default constructor.
     */
    public TotalPaymentSummaryReportService() {
    }

    /**
     * Generates the report for resolved suspense history report records.
     *
     * @param request the request object
     * @return the model for the requested report.
     * @throws IllegalArgumentException - if the request is null.
     * @throws ReportGenerationException - if there is any problem when generating response.
     */
    public TotalPaymentSummaryReportResponse getReport(TotalPaymentSummaryReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(TotalPaymentSummaryReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");
        try {
            List<PaymentTransaction> transactions = getEntityManager().createQuery(
                "SELECT t FROM PaymentTransaction t WHERE t.deleted = false", PaymentTransaction.class).getResultList();
            TotalPaymentSummaryReportResponse response = new TotalPaymentSummaryReportResponse();
            response.setReportGenerationDate(new Date());
            response.setReportName(getReportName());

            BigDecimal totalPost1082Deposit = new BigDecimal(0);
            BigDecimal totalPre1082Deposit = new BigDecimal(0);
            BigDecimal totalPost1082Redeposit = new BigDecimal(0);
            BigDecimal totalPre1082Redeposit = new BigDecimal(0);
            BigDecimal totalFersTotalPayment = new BigDecimal(0);
            BigDecimal totalCsrs = new BigDecimal(0);
            BigDecimal totalNonPostalFers = new BigDecimal(0);
            BigDecimal totalPostalFers = new BigDecimal(0);

            for (PaymentTransaction tx : transactions) {
                InvoiceData invoice = ReportHelper.getInvoiceData(getEntityManager(), getLogger(), tx);
                if (invoice == null) {
                    continue;
                }
                totalPost1082Deposit = sum(totalPost1082Deposit, invoice.getPost1082DepositTotalPayment());
                totalPre1082Deposit = sum(totalPre1082Deposit, invoice.getPre1082DepositTotalPayment());
                totalPost1082Redeposit = sum(totalPost1082Redeposit, invoice.getPost1082RedepositTotalPayment());
                totalPre1082Redeposit = sum(totalPre1082Redeposit, invoice.getPre1082RedepositTotalPayment());
                totalFersTotalPayment = sum(totalFersTotalPayment, invoice.getFersTotalPayment());

                // http://apps.topcoder.com/forums/?module=Thread&threadID=809439&start=0&mc=18
                //  (all_details) table which you can lookup the agency for a payment using the payTransactionKey.
                AllDetails details = ReportHelper.getAllDetails(getEntityManager(), getLogger(),
                    String.valueOf(tx.getPayTransactionKey()));
                if (details != null) {
                    totalPostalFers = sum(totalPostalFers, details.getTotalPostalFers());
                    totalNonPostalFers = sum(totalNonPostalFers, details.getTotalNonPostalFers());
                    totalCsrs = sum(totalCsrs, details.getTotalCsrs());
                }
            }

            response.setTotalDepositsPre1082(totalPre1082Deposit);
            response.setTotalRedepositsPre1082(totalPre1082Redeposit);
            response.setTotalDepositsPost982(totalPost1082Deposit);
            response.setTotalRedepositsPost982(totalPost1082Redeposit);
            response.setFersNonPostalDepositsPost982(totalNonPostalFers);
            response.setFersPostalDepositsPost982(totalPostalFers);
            response.setCsrsDepositsPost982(totalCsrs);
            response.setFersDepositsPost982(totalFersTotalPayment);

            BigDecimal grandTotal = new BigDecimal(0);
            grandTotal = sum(grandTotal, totalPre1082Deposit);
            grandTotal = sum(grandTotal, totalPre1082Redeposit);
            grandTotal = sum(grandTotal, totalPost1082Deposit);
            grandTotal = sum(grandTotal, totalPost1082Redeposit);
            grandTotal = sum(grandTotal, totalCsrs);
            grandTotal = sum(grandTotal, totalFersTotalPayment);
            response.setTotalPaymentsOnFile(grandTotal);
            Long accounts = (Long) getEntityManager().createQuery(QUERY_COUNT_ACCOUNTS).getSingleResult();
            response.setAccountNumberOnFile(accounts.intValue());

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
     * Renders the report using the given model.
     *
     * @param response the report model
     * @param exportType the file type to generate
     * @return the rendered report document
     * @throws IllegalArgumentException if any argument is null
     * @throws ReportGenerationException - if there is any problem when generating response.
     */
    public byte[] exportReport(TotalPaymentSummaryReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(TotalPaymentSummaryReportResponse response, ExportType exportType)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"response", "exportType"}, new Object[] {response,
            exportType});

        Helper.checkNull(logger, signature, response, "response");
        Helper.checkNull(logger, signature, exportType, "exportType");

        File tmpFile = null;
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

            tmpFile = File.createTempFile("jfc", ".png");
            renderSummmary(response, document);
            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            renderChart(response, document, tmpFile);

            document.close();
            result = outputStream.toByteArray();
            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (DocumentException e) {
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "An error has occurred when rendering the document.", e));
        } catch (IOException e) {
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "An error has occurred when rendering the document.", e));
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
    }

    /**
     * Renders chart.
     * @param response the response object
     * @param document the report document
     * @param chartFile the chart file
     * @throws IOException when an error occurs while accessing IO
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderChart(TotalPaymentSummaryReportResponse response, Document document, File chartFile)
        throws IOException, DocumentException {
        Image image = renderChartToFile(response, chartFile);
        document.add(image);
    }

    private Image renderChartToFile(TotalPaymentSummaryReportResponse response, File chartFile) throws IOException,
            BadElementException, MalformedURLException {
        // For generating pie chart for total payments(example for total deposits) perform following:
        // Create the dataset
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Pre 10/82 Deposit", response.getTotalDepositsPre1082());
        dataset.setValue("Pre 10/82 Redeposit", response.getTotalRedepositsPre1082());
        dataset.setValue("CSRS: Post 9/82 Deposit", response.getCsrsDepositsPost982());
        dataset.setValue("FERS Postal", response.getFersPostalDepositsPost982());
        dataset.setValue("FERS Non Postal", response.getFersNonPostalDepositsPost982());
        dataset.setValue("Post 9/82 Deposit", response.getTotalDepositsPost982());
        dataset.setValue("Post 9/82 Redeposit", response.getTotalRedepositsPost982());

        // Set following data
        // Create the chart
        JFreeChart chart = ChartFactory.createPieChart("Total Payments", dataset, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("Helvetica", 0, 10));
        plot.setBackgroundPaint(Color.white);
        plot.setIgnoreZeroValues(false);
        plot.setOutlineVisible(false);

        chart.getTitle().setFont(new Font("Helvetica", 1, 14));

        LegendTitle legend = chart.getLegend();
        legend.setItemFont(new Font("Helvetica", 0, 10));
        legend.setPosition(RectangleEdge.RIGHT);

        ChartUtilities.saveChartAsPNG(chartFile, chart, chartWidth, chartHeight);
        Image image = Image.getInstance(chartFile.getAbsolutePath());
        return image;
    }

    /**
     * Renders the summary details.
     * @param response the response object
     * @param document the report document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderSummmary(TotalPaymentSummaryReportResponse response, Document document)
        throws DocumentException {
        // table styling
        Table table = new Table(2);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(80);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {80, 20});

        table.addCell(new Phrase("Total Pre 10/82 Deposit Payments:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(response.getTotalDepositsPre1082()),
            ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase("Total Post 9/82 Deposit Payments:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(response.getTotalDepositsPost982()),
            ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase("CSRS Post 9/82 Deposit Payments:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(response.getCsrsDepositsPost982()),
            ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase("FERS Post 9/82 Deposit Payments:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(response.getFersDepositsPost982()),
            ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase("FERS Post 9/82 Postal Deposit Payments:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(response.getFersPostalDepositsPost982()),
            ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase("FERS Post 9/82 Non-Postal Deposit Payments:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(response.getFersNonPostalDepositsPost982()),
            ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase("Total Pre 10/82 Redeposit Payments:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(response.getTotalRedepositsPre1082()),
            ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase("Total Post 9/82 Redeposit Payments:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(response.getTotalRedepositsPost982()),
            ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase("Total Payments on File:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(response.getTotalPaymentsOnFile()),
            ReportHelper.TABLE_DATA_FONT));

        table.addCell(new Phrase("Number of Accounts on File:", ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatInteger(response.getAccountNumberOnFile()),
            ReportHelper.TABLE_DATA_FONT));

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
    public byte[] renderChart(TotalPaymentSummaryReportResponse response) throws ReportGenerationException {
        final String signature = CLASS_NAME + ".renderChart(TotalPaymentSummaryReportResponse response)";
        Logger logger = getLogger();
        try {
            File chartFile = File.createTempFile("jfc", ".png");
            this.renderChartToFile(response, chartFile);
            return ReportHelper.readFileToByteArray(chartFile);
        } catch (BadElementException e) {
            throw LoggingHelper.logException(logger, signature, 
                    new ReportGenerationException("Failed to generate image", e));
        } catch (MalformedURLException e) {
            throw LoggingHelper.logException(logger, signature, 
                    new ReportGenerationException("Failed to generate image", e));
        } catch (IOException e) {
            throw LoggingHelper.logException(logger, signature, 
                    new ReportGenerationException("Failed to generate image", e));
        }
    }
}
