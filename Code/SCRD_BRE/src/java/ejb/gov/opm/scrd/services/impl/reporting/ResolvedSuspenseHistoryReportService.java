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

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class handles data retrieval and export of the resolved suspense history report.
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
public class ResolvedSuspenseHistoryReportService extends BaseReportService implements
    ReportService<ResolvedSuspenseHistoryReportRequest, ResolvedSuspenseHistoryReportResponse> {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ResolvedSuspenseHistoryReportService.class.getName();
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
    public ResolvedSuspenseHistoryReportService() {
    }

    /**
     * Generates the report for resolved suspense history report records.
     *
     * @param request the request object
     * @return the model for the requested report.
     * @throws IllegalArgumentException - if the request is null.
     * @throws ReportGenerationException - if there is any problem when generating response.
     */
    public ResolvedSuspenseHistoryReportResponse getReport(ResolvedSuspenseHistoryReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(ResolvedSuspenseHistoryReportRequest request)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");
        try {

            List<PaymentTransaction> transactions = getEntityManager().createQuery(
                "SELECT t FROM PaymentTransaction t WHERE t.deleted = false AND t.resolvedSuspense = true",
                PaymentTransaction.class).getResultList();

            ResolvedSuspenseHistoryReportResponse response = new ResolvedSuspenseHistoryReportResponse();
            List<ResolvedSuspenseHistoryReportResponseItem> items =
                new ArrayList<ResolvedSuspenseHistoryReportResponseItem>();

            /* Histogram Items */
            Map<Integer, Integer> histograms = new HashMap<Integer, Integer>();

            for (PaymentTransaction tx : transactions) {
                ResolvedSuspenseHistoryReportResponseItem item = new ResolvedSuspenseHistoryReportResponseItem();
                item.setStatusCode(tx.getPaymentStatusCode().toString());
                item.setBatchNumber(tx.getPayTransBatchNumber());
                item.setBlkNumber(tx.getPayTransBlockNumber());
                item.setBirthday(tx.getScmDateOfBirth());
                item.setCsd(tx.getCsd());
                item.setDepositDate(tx.getPayTransStatusDate());
                item.setSequenceNumber(tx.getPayTransSequenceNumber());
                item.setTechnician(tx.getTechnicianUserKey().toString());
                item.setAmount(tx.getPayTransPaymentAmount());
                InvoiceData invoice = ReportHelper.getInvoiceData(getEntityManager(), getLogger(), tx);
                if (invoice != null) {
                    item.setNote(invoice.getNote());
                }
                items.add(item);

                Integer status = tx.getPaymentStatusCode();
                if (histograms.get(status) != null) {
                    histograms.put(status, histograms.get(status) + 1);
                } else {
                    histograms.put(status, 1);
                }
            }

            response.setDataItems(items);
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());

            // generate histogram data
            response.setHistogramItems(new ArrayList<ResolvedSuspenseHistoryReportHistogramItem>());
            response.getHistogramItems().add(getHistogram(histograms, PaymentTransactionCodes.ANNUITY_COMPLETE));
            response.getHistogramItems().add(getHistogram(histograms, PaymentTransactionCodes.DEBIT_VOUCHER_COMPLETE));
            response.getHistogramItems()
                .add(getHistogram(histograms, PaymentTransactionCodes.DIRECT_PAY_LIFE_COMPLETE));
            response.getHistogramItems().add(getHistogram(histograms, PaymentTransactionCodes.SUSPENSE_DEBIT_VOUCHER));
            response.getHistogramItems()
                .add(getHistogram(histograms, PaymentTransactionCodes.SUSPENSE_REFUND_COMPLETE));
            response.getHistogramItems().add(
                getHistogram(histograms, PaymentTransactionCodes.VOLUNTARY_CONTRIBUTIONS_COMPLETE));

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
     * Get historgram.
     * @param histograms the histograms
     * @param code the payment transaction codes
     * @return the resolved suspense history report
     */
    private ResolvedSuspenseHistoryReportHistogramItem getHistogram(Map<Integer, Integer> histograms,
        PaymentTransactionCodes code) {
        ResolvedSuspenseHistoryReportHistogramItem item = new ResolvedSuspenseHistoryReportHistogramItem();
        item.setName(getHistogramLabel(code));
        if (histograms.get(code.getCode()) != null) {
            item.setCount(histograms.get(code.getCode()));
        } else {
            item.setCount(0);
        }
        return item;
    }

    /**
     * Get historgram label.
     * @param code The payment transaction code.
     * @return the histogram label.
     */
    private String getHistogramLabel(PaymentTransactionCodes code) {
        switch (code) {
        case ANNUITY_COMPLETE:
            return "Annuity - Complete";
        case DEBIT_VOUCHER_COMPLETE:
            return "Debit Voucher - Complete";
        case DIRECT_PAY_LIFE_COMPLETE:
            return "Direct Pay - Complete";
        case SUSPENSE_DEBIT_VOUCHER:
            return "Suspense Debit Voucher - Complete";
        case SUSPENSE_REFUND_COMPLETE:
            return "Suspense Refund - Complete";
        case VOLUNTARY_CONTRIBUTIONS_COMPLETE:
            return "Voluntary Contributions - Complete";
        default:
            return code.toString();
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
    public byte[] exportReport(ResolvedSuspenseHistoryReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(ResolvedSuspenseHistoryReportResponse response, ExportType exportType)";
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

            BigDecimal grandTotal = new BigDecimal(0);
            grandTotal = grandTotal.add(renderItems(response, document, PaymentTransactionCodes.ANNUITY_COMPLETE));
            grandTotal = grandTotal
                .add(renderItems(response, document, PaymentTransactionCodes.DEBIT_VOUCHER_COMPLETE));
            grandTotal = grandTotal.add(renderItems(response, document,
                PaymentTransactionCodes.DIRECT_PAY_LIFE_COMPLETE));
            grandTotal = grandTotal
                .add(renderItems(response, document, PaymentTransactionCodes.SUSPENSE_DEBIT_VOUCHER));
            grandTotal = grandTotal.add(renderItems(response, document,
                PaymentTransactionCodes.SUSPENSE_REFUND_COMPLETE));
            grandTotal = grandTotal.add(renderItems(response, document,
                PaymentTransactionCodes.VOLUNTARY_CONTRIBUTIONS_COMPLETE));

            List<ResolvedSuspenseHistoryReportHistogramItem> histogramItems = response.getHistogramItems();
            int totalRows = 0;
            for (ResolvedSuspenseHistoryReportHistogramItem hist : histogramItems) {
                totalRows += hist.getCount();
            }

            Table totalRow = new Table(1);
            totalRow.setBorder(Table.NO_BORDER);
            totalRow.setPadding(2);
            Cell cell = new Cell(new Phrase("Grand Total: " + ReportHelper.formatInteger(totalRows) + " Payments "
                + ReportHelper.formatMoney(grandTotal), ReportHelper.TABLE_HEADER_FONT));
            cell.setBorder(Cell.BOTTOM);
            totalRow.addCell(cell);
            document.add(totalRow);

            tmpFile = File.createTempFile("jfc", ".png");
            renderChart(response, document, tmpFile);
            document.close();
            result = outputStream.toByteArray();
            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (DocumentException e) {
            e.printStackTrace();
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "An error has occurred when rendering the document.", e));
        } catch (IOException e) {
            e.printStackTrace();
            throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                "An error has occurred when rendering the document.", e));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
    }

    /**
     * Renders the items.
     * @param response the response object
     * @param document the report document
     * @param txCode the transaction code
     * @return the sub total
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private BigDecimal renderItems(ResolvedSuspenseHistoryReportResponse response, Document document,
        PaymentTransactionCodes txCode) throws DocumentException {
        // group title
        Paragraph paragraph = new Paragraph(getHistogramLabel(txCode), ReportHelper.TABLE_HEADER_FONT);

        // table styling
        Table table = new Table(9);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {3, 3, 3, 10, 10, 12, 8, 8, 34});
        table.addCell(new Phrase("Bat", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Blk", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Seq", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("CSD", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("BirthDate", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Amount", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Deposit Date", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Technician", ReportHelper.TABLE_HEADER_UNDERLINE));
        table.addCell(new Phrase("Note", ReportHelper.TABLE_HEADER_UNDERLINE));

        // process rows
        List<ResolvedSuspenseHistoryReportResponseItem> items = response.getDataItems();
        BigDecimal subTotal = new BigDecimal(0);
        int rows = 0;
        for (ResolvedSuspenseHistoryReportResponseItem row : items) {
            if (!String.valueOf(txCode.getCode()).equalsIgnoreCase(row.getStatusCode())) {
                continue;
            }
            rows++;
            subTotal = subTotal.add(row.getAmount());
            table.addCell(new Phrase(row.getBatchNumber(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getBlkNumber(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getSequenceNumber(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getCsd(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDate(row.getBirthday()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatMoney(row.getAmount()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(ReportHelper.formatDate(row.getDepositDate()), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getTechnician(), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getNote(), ReportHelper.TABLE_DATA_FONT));
        }

        // label for the total
        Cell summaryCell = new Cell(new Phrase(rows + " Payments Totalling" , ReportHelper.TABLE_HEADER_FONT));
        summaryCell.setColspan(5);
        summaryCell.setBorder(Cell.TOP);
        summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(summaryCell);

        // group total value
        Cell subTotalCell = new Cell(new Phrase(ReportHelper.formatMoney(subTotal), ReportHelper.TABLE_HEADER_FONT));
        subTotalCell.setBorder(Cell.TOP);
        table.addCell(subTotalCell);

        // group total value
        Cell titleCell = new Cell(new Phrase(getHistogramLabel(txCode), ReportHelper.TABLE_HEADER_FONT));
        titleCell.setColspan(3);
        titleCell.setBorder(Cell.TOP);
        table.addCell(titleCell);

        if (rows > 0) {
            document.add(paragraph);
            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
        }
        return subTotal;
    }

    /**
     * Renders chart.
     * @param response the response object
     * @param document the report document
     * @param chartFile the chart file
     * @throws IOException when an error occurs while accessing IO
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderChart(ResolvedSuspenseHistoryReportResponse response, Document document, File chartFile)
        throws IOException, DocumentException {
        renderChartImage(response, chartFile);
        Image image = Image.getInstance(chartFile.getAbsolutePath());
        document.add(image);
    }
    
    /**
     * Renders the chart image.
     * @param response
     * @param chartFile
     * @throws BadElementException
     * @throws MalformedURLException
     * @throws IOException
     */
    private void renderChartImage(ResolvedSuspenseHistoryReportResponse response, File chartFile) 
        throws BadElementException, MalformedURLException, IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ResolvedSuspenseHistoryReportHistogramItem item : response.getHistogramItems()) {
            for (ResolvedSuspenseHistoryReportHistogramItem item2 : response.getHistogramItems()) {
                if (item == item2) {
                    dataset.addValue(item.getCount(), item2.getName(), item.getName());
                } else {
                    dataset.addValue(0, item2.getName(), item.getName());
                }
            }
        }

        // Create the chart
        JFreeChart chart = ChartFactory.createStackedBarChart("Count of Resolved Suspense Items", "", "", dataset,
            PlotOrientation.VERTICAL, true, true, false);
        Plot plot = chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setOutlineVisible(false);
        chart.getTitle().setFont(new Font("Helvetica", 1, 14));

        CategoryPlot cplot = (CategoryPlot) chart.getPlot();
        final CategoryAxis domainAxis = cplot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        LegendTitle legend = chart.getLegend();
        legend.setItemFont(new Font("Helvetica", 0, 10));
        legend.setPosition(RectangleEdge.RIGHT);
        ChartUtilities.saveChartAsPNG(chartFile, chart, chartWidth, chartHeight);
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(ResolvedSuspenseHistoryReportResponse response) throws ReportGenerationException {
        Logger logger = super.getLogger();
        final String signature = CLASS_NAME + "#renderChart(ResolvedSuspenseHistoryReportResponse)";
        File tmpFile;
        try {
            tmpFile = File.createTempFile("jfc", ".png");
            renderChartImage(response, tmpFile);
            return ReportHelper.readFileToByteArray(tmpFile);
        } catch (BadElementException e) {
            throw LoggingHelper.logException(logger, signature, 
                    new ReportGenerationException("failed to render chart", e));
        } catch (MalformedURLException e) {
            throw LoggingHelper.logException(logger, signature, 
                    new ReportGenerationException("failed to render chart", e));
        } catch (IOException e) {
            throw LoggingHelper.logException(logger, signature, 
                    new ReportGenerationException("failed to render chart", e));
        }
        
        
    }
    
}
