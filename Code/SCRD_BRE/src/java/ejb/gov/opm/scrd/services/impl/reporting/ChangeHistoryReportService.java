/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountHolder;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * This class is the implementation of the ReportService which generates change history report. It uses local data for
 * generating report and iText/iText RTF for generating reports.
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
public class ChangeHistoryReportService extends BaseReportService implements
    ReportService<ChangeHistoryReportRequest, ChangeHistoryReportResponse> {

    /**
     * Query used for inspected audit records.
     */
    private static final String QUERY_AUDIT_RECORDS = "SELECT a FROM AuditRecord a WHERE a.deleted = false";

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ChangeHistoryReportService.class.getName();

    /**
     * Default constructor.
     */
    public ChangeHistoryReportService() {
    }

    /**
     * Generates the report for change history records.
     *
     * @param request the request object
     * @return the model for the requested report.
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public ChangeHistoryReportResponse getReport(ChangeHistoryReportRequest request) throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(ChangeHistoryReportRequest request)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");

        try {
            ChangeHistoryReportResponse response = new ChangeHistoryReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());

            Account account = ReportHelper.getAccountData(getEntityManager(), getLogger(), request.getCsd());
            if (account == null) {
                throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                    "Cannot generate report because account data is not found."));
            }

            response.setBirthDay(account.getClaimantBirthdate());
            response.setClaimName(getFullName(account.getHolder()));
            response.setCsd(account.getClaimNumber());
            response.setItems(new ArrayList<ChangeHistoryReportResponseItem>());

            List<AuditRecord> auditRecords = getEntityManager().createQuery(QUERY_AUDIT_RECORDS, AuditRecord.class)
                .getResultList();

            for (AuditRecord record : auditRecords) {
                List<AuditParameterRecord> parameters = record.getParameters();
                for (AuditParameterRecord param : parameters) {
                    ChangeHistoryReportResponseItem item = new ChangeHistoryReportResponseItem();
                    if (!"Account".equals(param.getItemType())) {
                        // we only need account changes
                        continue;
                    }

                    if (!account.getClaimNumber().equals(String.valueOf(param.getItemId()))) {
                        // only matching account numbers
                        continue;
                    }

                    item.setDate(record.getDate());
                    // description being the concatenation of Action, previousValue and newValue.
                    item.setDescription(MessageFormat.format("{0} from {1} to {2}", record.getActionName(),
                        param.getPreviousValue(), param.getNewValue()));
                    item.setModified(record.getUsername());
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
    public byte[] exportReport(ChangeHistoryReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(ChangeHistoryReportResponse response, ExportType exportType)";
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

            HeaderFooter head = ReportHelper.generateHeaderNoBorder(document, null, response.getReportName(), null);

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
     * Renders the contents of the report.
     *
     * @param response the report data
     * @param document the current report document
     * @throws DocumentException for any other errors encountered
     */
    private void renderItems(ChangeHistoryReportResponse response, Document document) throws DocumentException {
        // generate header table
        Table table = new Table(3);
        table.setBorder(Table.TOP | Table.BOTTOM | Table.LEFT | Table.RIGHT);
        Cell cell = new Cell();
        cell.setBorder(Cell.LEFT);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        table.addCell(new Phrase("CSD #" + response.getCsd(), ReportHelper.TABLE_HEADER_FONT));
        table.addCell(new Phrase(ReportHelper.formatDate(response.getBirthDay()), ReportHelper.TABLE_HEADER_FONT));
        table.addCell(new Phrase(response.getClaimName(), ReportHelper.TABLE_HEADER_FONT));
        document.add(table);

        Map<GroupingKey, List<ChangeHistoryReportResponseItem>> groups = groupItems(response);
        Set<GroupingKey> keySet = groups.keySet();
        for (GroupingKey groupingKey : keySet) {
            renderGroup(document, groupingKey, groups.get(groupingKey));
        }

        if (response.getItems().isEmpty()) {
            document.add(new Paragraph("There are no changes on record.", ReportHelper.TABLE_DATA_FONT));
        }
    }

    /**
     * Renders all the records that are part of a group.
     *
     * @param document the current report
     * @param groupingKey the grouping key
     * @param list the items in the group
     * @throws DocumentException for any errors encountered
     */
    private void renderGroup(Document document, GroupingKey groupingKey, List<ChangeHistoryReportResponseItem> list)
        throws DocumentException {
        Table table = new Table(1);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {100});
        String groupLabel = "{0}                               {1}";
        String groupHeader = MessageFormat.format(groupLabel, groupingKey.date, groupingKey.user);
        Cell headerCell = new Cell(new Phrase(groupHeader, ReportHelper.TABLE_HEADER_FONT));
        headerCell.setBorder(Cell.BOTTOM);
        table.addCell(headerCell);
        for (ChangeHistoryReportResponseItem row : list) {
            table.addCell(new Phrase(row.getDescription(), ReportHelper.TABLE_DATA_FONT));
        }
        document.add(table);
        document.add(new Phrase(" ")); // spacer
    }

    /**
     * Gets account holder's full name.
     *
     * @param accountHolder The account holder.
     * @return full name
     */
    private static String getFullName(AccountHolder accountHolder) {
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
     * Groups the report items by user, account and date.
     *
     * @param response the report data
     * @return the grouped report data
     */
    private Map<GroupingKey, List<ChangeHistoryReportResponseItem>> groupItems(ChangeHistoryReportResponse response) {
        Map<GroupingKey, List<ChangeHistoryReportResponseItem>> groups =
            new HashMap<GroupingKey, List<ChangeHistoryReportResponseItem>>();

        // group same day, same user, same account changes
        List<ChangeHistoryReportResponseItem> items = response.getItems();
        for (ChangeHistoryReportResponseItem item : items) {
            GroupingKey key = new GroupingKey();
            key.date = ReportHelper.formatDate(item.getDate(), "MMMM d, yyyy h:mm aa");
            key.user = item.getModified();

            if (groups.get(key) == null) {
                List<ChangeHistoryReportResponseItem> list = new ArrayList<ChangeHistoryReportResponseItem>();
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
            return date.hashCode();
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
            return String.valueOf(this.date).equals(String.valueOf(that.date))
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
    public byte[] renderChart(ChangeHistoryReportResponse response) throws ReportGenerationException {
        return null;
    }

}
