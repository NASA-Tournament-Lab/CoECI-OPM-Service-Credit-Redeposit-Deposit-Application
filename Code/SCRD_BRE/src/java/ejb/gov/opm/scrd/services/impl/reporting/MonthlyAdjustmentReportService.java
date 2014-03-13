/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class is the implementation of the ReportService which generates monthly adjustment report. It uses local data
 * for generating report and iText/iText RTF for generating reports.
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
public class MonthlyAdjustmentReportService extends BaseReportService implements
    ReportService<MonthlyAdjustmentReportRequest, MonthlyAdjustmentReportResponse> {

    /**
     * Query used for inspected audit records.
     */
    private static final String QUERY_AUDIT_RECORDS = "SELECT a FROM AuditRecord a WHERE a.deleted = false "
        + "AND a.date >= :startDate AND a.date <= :endDate";

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = MonthlyAdjustmentReportService.class.getName();

    /**
     * Default constructor.
     */
    public MonthlyAdjustmentReportService() {
    }

    /**
     * Creates the report for the given request.
     *
     * @param request the request data to generate report.
     * @return instance containing the report data, can not be null.
     *
     * @throws. IllegalArgumentException if the request is null or the startDate or endDate is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public MonthlyAdjustmentReportResponse getReport(MonthlyAdjustmentReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(MonthlyAdjustmentReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");
        Helper.checkNull(logger, signature, request.getStartDate(), "request.startDate");
        Helper.checkNull(logger, signature, request.getEndDate(), "request.endDate");

        try {
            MonthlyAdjustmentReportResponse response = new MonthlyAdjustmentReportResponse();
            response.setReportGenerationDate(new Date());
            response.setReportName(getReportName());
            response.setStartDate(request.getStartDate());
            response.setEndDate(request.getEndDate());
            response.setItems(new ArrayList<MonthlyAdjustmentReportResponseItem>());

            List<AuditRecord> auditRecords = getEntityManager().createQuery(QUERY_AUDIT_RECORDS, AuditRecord.class)
                .setParameter("startDate", request.getStartDate()).setParameter("endDate", request.getEndDate())
                .getResultList();

            for (AuditRecord record : auditRecords) {
                List<AuditParameterRecord> parameters = record.getParameters();
                for (AuditParameterRecord param : parameters) {
                    MonthlyAdjustmentReportResponseItem item = new MonthlyAdjustmentReportResponseItem();
                    if (!"Account".equals(param.getItemType())) {
                        // we only need account changes
                        continue;
                    }

                    item.setModifier(record.getUsername());
                    item.setAccountNumber(String.valueOf(param.getItemId()));
                    item.setDate(record.getDate());
                    // description being the concatenation of Action, previousValue and newValue.
                    item.setDescription(MessageFormat.format("{0} from {1} to {2}", record.getActionName(),
                        param.getPreviousValue(), param.getNewValue()));
                    response.getItems().add(item);
                }
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
     * Exports the report for the given request. The contents of the exported report are returned from this method.
     *
     * @param response the report data to generate report.
     * @param exportType the type of the report data to generate.
     * @return The byte array of contents of the exported report, can not be null.
     *
     * @throws IllegalArgumentException if the response/exportType is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public byte[] exportReport(MonthlyAdjustmentReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(MonthlyAdjustmentReportResponse response, ExportType exportType)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"response", "exportType"}, new Object[] {response,
            exportType});

        Helper.checkNull(logger, signature, response, "response");
        Helper.checkNull(logger, signature, exportType, "exportType");

        File tmpFile = null;
        Document document = null;
        try {
            byte[] result = null;

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document = new Document();
            document.setMargins(ReportHelper.MARGIN_LR, ReportHelper.MARGIN_LR, ReportHelper.MARGIN_TB,
                ReportHelper.MARGIN_TB);

            // Associate it with output stream
            ReportHelper.initDocumentFormat(document, outputStream, exportType);

            String subTitleFormat = "for the period from {0} to {1}";
            String subTitle = MessageFormat.format(subTitleFormat, ReportHelper.formatDate(response.getStartDate()),
                ReportHelper.formatDate(response.getEndDate()));
            HeaderFooter head = ReportHelper.generateHeaderNoBorder(document, null, response.getReportName(), subTitle);

            document.setHeader(head);
            document.open();

            tmpFile = File.createTempFile("jfc", ".png");
            renderItems(response, document, tmpFile);

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
            // per reviewer, we should do this even if it does not make sense
            // since we needed to close it to build the PDF before return
            if (document != null) {
                document.close();
            }
        }
    }

    /**
     * Renders the contents of the report.
     *
     * @param response the report data
     * @param document the current report document
     * @param chartFile the temporary file for writing the chart
     * @throws IOException if the chart cannot be written
     * @throws DocumentException for any other errors encountered
     */
    private void renderItems(MonthlyAdjustmentReportResponse response, Document document, File chartFile)
        throws DocumentException, IOException {
        Map<GroupingKey, List<MonthlyAdjustmentReportResponseItem>> groups = groupItems(response);
        // tracks the number of fields changed by a user
        Map<String, Integer> userChangeCount = new HashMap<String, Integer>();
        // tracks the number of accounts changed by a user
        Map<String, Set<String>> userAccounts = new HashMap<String, Set<String>>();

        Set<GroupingKey> keySet = groups.keySet();
        for (GroupingKey groupingKey : keySet) {
            renderGroup(document, groupingKey, groups.get(groupingKey));
            if (userChangeCount.get(groupingKey.user) == null) {
                userChangeCount.put(groupingKey.user, groups.get(groupingKey).size());
            } else {
                userChangeCount.put(groupingKey.user, userChangeCount.get(groupingKey.user)
                    + groups.get(groupingKey).size());
            }
            if (userAccounts.get(groupingKey.user) == null) {
                userAccounts.put(groupingKey.user, new HashSet<String>());
            }
            userAccounts.get(groupingKey.user).add(groupingKey.claimNumber);
        }

        renderSummary(document, userChangeCount, userAccounts);
        renderChart(document, userAccounts, chartFile);
    }

    /**
     * Renders the chart showing how many accounts each user has modified.
     *
     * @param document the current report
     * @param userAccounts the map representing the number of accounts per user
     * @param chartFile the temporary file
     * @throws IOException if the chart cannot be written
     * @throws DocumentException for any other errors encountered
     */
    private void renderChart(Document document, Map<String, Set<String>> userAccounts, File chartFile)
        throws IOException, DocumentException {

        renderChartToFile(userAccounts, chartFile);
        Image image = Image.getInstance(chartFile.getAbsolutePath());
        document.add(image);

    }

    private void renderChartToFile(Map<String, Set<String>> userAccounts, File chartFile) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Set<String>> item : userAccounts.entrySet()) {
            for (Map.Entry<String, Set<String>> item2 : userAccounts.entrySet()) {
                if (item == item2) {
                    dataset.addValue(item.getValue().size(), item2.getKey(), item2.getKey());
                } else {
                    dataset.addValue(0, item2.getKey(), item.getKey());
                }
            }
        }

        // Create the chart
        JFreeChart chart = ChartFactory.createStackedBarChart("", "", "Number of Modified Accounts", dataset,
            PlotOrientation.HORIZONTAL, true, true, false);
        Plot plot = chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setOutlineVisible(false);

        final CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        categoryPlot.setRangeGridlinesVisible(true);
        categoryPlot.setRangeGridlineStroke(new BasicStroke(0.5f));
        categoryPlot.setRangeGridlinePaint(Color.gray);

        LegendTitle legend = chart.getLegend();
        legend.setItemFont(new Font("Helvetica", 0, 10));
        legend.setPosition(RectangleEdge.RIGHT);

        ChartUtilities.saveChartAsPNG(chartFile, chart, 500, 300);
    }

    /**
     * Renders the summary of adjustments.
     *
     * @param document the current document
     * @param userChangeCount the map representing the number of changes per user
     * @param userAccounts the map representing the number of accounts per user
     * @throws DocumentException for any errors encountered
     */
    private void renderSummary(Document document, Map<String, Integer> userChangeCount,
        Map<String, Set<String>> userAccounts) throws DocumentException {
        Table table = new Table(1);
        table.setBorder(Table.TOP | Table.LEFT | Table.BOTTOM);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);
        // table header and column widths
        table.setWidths(new float[] {100});
        String groupLabel = "{0} made {1} changes to {2} accounts during this reporting period.";
        for (Map.Entry<String, Integer> user : userChangeCount.entrySet()) {
            Set<String> accountsModified = userAccounts.get(user.getKey());
            String userSummary = MessageFormat.format(groupLabel, user.getKey(), user.getValue(),
                accountsModified.size());
            table.addCell(new Phrase(userSummary, ReportHelper.TABLE_DATA_FONT));
        }
        document.add(table);
    }

    /**
     * Renders all the records that are part of a group.
     *
     * @param document the current report
     * @param groupingKey the grouping key
     * @param items the items in the group
     * @throws DocumentException for any errors encountered
     */
    private void renderGroup(Document document, GroupingKey groupingKey,
        List<MonthlyAdjustmentReportResponseItem> items) throws DocumentException {

        Table table = new Table(2);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {10, 90});

        String groupLabel = "{0}  {1} changed account #{2}";
        String groupHeader = MessageFormat.format(groupLabel, groupingKey.date, groupingKey.user,
            groupingKey.claimNumber);
        Cell headerCell = new Cell(new Phrase(groupHeader, ReportHelper.TABLE_HEADER_FONT));
        headerCell.setColspan(2);
        headerCell.setBorder(Cell.BOTTOM);
        table.addCell(headerCell);
        for (MonthlyAdjustmentReportResponseItem row : items) {
            table.addCell(new Phrase(ReportHelper.formatDate(row.getDate(), "hh:mm a"), ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase(row.getDescription(), ReportHelper.TABLE_DATA_FONT));
        }
        document.add(table);
        document.add(new Phrase(" ")); // spacer
    }

    /**
     * Groups the report items by user, account and date.
     *
     * @param response the report data
     * @return the grouped report data
     */
    private Map<GroupingKey, List<MonthlyAdjustmentReportResponseItem>> groupItems(
        MonthlyAdjustmentReportResponse response) {
        Map<GroupingKey, List<MonthlyAdjustmentReportResponseItem>> groups =
            new HashMap<GroupingKey, List<MonthlyAdjustmentReportResponseItem>>();

        // group same day, same user, same account changes
        List<MonthlyAdjustmentReportResponseItem> items = response.getItems();
        for (MonthlyAdjustmentReportResponseItem item : items) {
            GroupingKey key = new GroupingKey();
            key.claimNumber = item.getAccountNumber();
            key.date = ReportHelper.formatDate(item.getDate()); // strips time
            key.user = item.getModifier();

            if (groups.get(key) == null) {
                List<MonthlyAdjustmentReportResponseItem> list = new ArrayList<MonthlyAdjustmentReportResponseItem>();
                groups.put(key, list);
            }
            groups.get(key).add(item);
        }
        return groups;
    }

    /**
     * Grouping key for the non aggregate columns of the report.
     *
     * @author TCSASSEMBLER
     * @version 1.0
     */
    private class GroupingKey {

        /**
         * The date of the change.
         */
        private String date;

        /**
         * The user.
         */
        private String user;

        /**
         * The claim number.
         */
        private String claimNumber;

        /**
         * Private constructor.
         */
        private GroupingKey() {
        }

        /**
         * Gets the hash code.
         *
         * @return the hash code
         */
        public int hashCode() {
            return claimNumber.hashCode();
        }

        /**
         * Checks for equality.
         *
         * @param obj the instance to compare to
         * @return true if the key represents the same group
         */
        public boolean equals(Object obj) {
            if (obj.getClass() != this.getClass()) {
                return false;
            }
            GroupingKey that = (GroupingKey) obj;
            return String.valueOf(this.claimNumber).equals(String.valueOf(that.claimNumber))
                && String.valueOf(this.date).equals(String.valueOf(that.date))
                && String.valueOf(this.user).equals(String.valueOf(that.user));
        }
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(MonthlyAdjustmentReportResponse response) throws ReportGenerationException {
        Map<GroupingKey, List<MonthlyAdjustmentReportResponseItem>> groups = groupItems(response);
        // tracks the number of accounts changed by a user
        Map<String, Set<String>> userAccounts = new HashMap<String, Set<String>>();

        Set<GroupingKey> keySet = groups.keySet();
        for (GroupingKey groupingKey : keySet) {
            if (userAccounts.get(groupingKey.user) == null) {
                userAccounts.put(groupingKey.user, new HashSet<String>());
            }
            userAccounts.get(groupingKey.user).add(groupingKey.claimNumber);
        }
        try {
            File chartFile = File.createTempFile("jfc", ".png");
            renderChartToFile(userAccounts, chartFile);
            return ReportHelper.readFileToByteArray(chartFile);
        } catch (IOException e) {
            throw new ReportGenerationException("Failed to render chart", e);
        }
    }

}
