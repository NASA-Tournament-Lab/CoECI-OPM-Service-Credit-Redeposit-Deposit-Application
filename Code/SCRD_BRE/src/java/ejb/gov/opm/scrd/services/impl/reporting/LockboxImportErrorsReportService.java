/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.batchprocessing.AuditBatchLogId;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.MessageFormat;
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
 * This class handles data retrieval and export of the LockBox import errors report.
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
public class LockboxImportErrorsReportService extends BaseReportService implements
    ReportService<LockboxImportErrorsReportRequest, LockboxImportErrorsReportResponse> {

    /**
     * Query used for inspected audit records.
     */
    private static final String QUERY_AUDIT_RECORDS = "SELECT a FROM AuditRecord a WHERE a.deleted = false "
        + "AND a.actionName = :actionName AND a.date = :batchDate";

    /**
     * Action name used to find all errors.
     */
    private static final String AUDIT_ACTION_NAME = "Lock Box Import Error";

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = LockboxImportErrorsReportService.class.getName();

    /**
     * Default constructor.
     */
    public LockboxImportErrorsReportService() {
    }

    /**
     * Retrieves the the LockBox import errors report data model.
     *
     * @param request the request data to generate report.
     * @return instance containing the report data, can not be null.
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public LockboxImportErrorsReportResponse getReport(LockboxImportErrorsReportRequest request)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(LockboxImportErrorsReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");
        try {
            LockboxImportErrorsReportResponse response = new LockboxImportErrorsReportResponse();
            response.setReportGenerationDate(new Date());
            response.setReportName(getReportName());

            // Go into the AuditBatchLogId table and get the last batchDate and get the batchNumber
            List<AuditBatchLogId> batchLogIds = getEntityManager()
                .createQuery("SELECT a FROM AuditBatchLogId a ORDER BY a.batchDate DESC", AuditBatchLogId.class)
                .setMaxResults(1).getResultList();

            if (batchLogIds.isEmpty()) {
                // we cannot generate an import report since there are no batch runs on record
                throw LoggingHelper.logException(logger, signature, new ReportGenerationException(
                    "Could not generate report as there is not batch run found at this time."));
            }

            AuditBatchLogId batchId = batchLogIds.get(0);
            response.setBatchNumber(String.valueOf(batchId.getBatchNumber()));
            response.setImportDate(batchId.getBatchDate());
            response.setItems(new ArrayList<LockboxImportErrorsReportResponseItem>());

            // Go into the AuditRecord table and get all the records WHERE actionName = "Lock Box Import Error" and date
            // = batchDate
            List<AuditRecord> auditRecords = getEntityManager().createQuery(QUERY_AUDIT_RECORDS, AuditRecord.class)
                .setParameter("actionName", AUDIT_ACTION_NAME).setParameter("batchDate", batchId.getBatchDate())
                .getResultList();
            

            for (AuditRecord record : auditRecords) {
                LockboxImportErrorsReportResponseItem item = new LockboxImportErrorsReportResponseItem();
                List<AuditParameterRecord> parameters = record.getParameters();
                for (AuditParameterRecord param : parameters) {
                    if ("errorCode".equals(param.getPropertyName())) {
                        item.setErrorCode(param.getNewValue());
                    } else if ("errorMessage".equals(param.getPropertyName())) {
                        item.setErrorMessage(param.getNewValue());
                    } else if ("description".equals(param.getPropertyName())) {
                        item.setDescription(param.getNewValue());
                    }
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
     * Exports the report for the given request. The contents of the exported report are returned from this method.
     *
     * @param response the report data to generate report.
     * @param exportType the type of the report data to generate.
     * @return The byte array of contents of the exported report, can not be null.
     *
     * @throws IllegalArgumentException if the response/exportType is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public byte[] exportReport(LockboxImportErrorsReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME
            + "#exportReport(LockboxImportErrorsReportResponse response, ExportType exportType)";
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
     * Renders the table of records.
     *
     * @param response the report model
     * @param document the current document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderItems(LockboxImportErrorsReportResponse response, Document document) throws DocumentException {
        // current specification supports only one group
        String groupLabel = "Batch #{0} Imported on {1}";
        String groupHeader = MessageFormat.format(groupLabel,
            ReportHelper.formatInteger(Integer.valueOf(response.getBatchNumber())),
            ReportHelper.formatDate(response.getImportDate(), "M/d/yyyy"));
        document.add(new Paragraph(groupHeader, ReportHelper.TABLE_HEADER_FONT));

        // table styling
        Table table = new Table(2);
        table.setBorder(Table.TOP | Table.BOTTOM);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {5, 95});
        // process rows
        List<LockboxImportErrorsReportResponseItem> items = response.getItems();
        for (LockboxImportErrorsReportResponseItem row : items) {
            table.addCell(new Phrase(row.getErrorCode(), ReportHelper.TABLE_DATA_FONT));
            Cell grayCell = new Cell(new Phrase(row.getErrorMessage(), ReportHelper.TABLE_HEADER_FONT));
            grayCell.setBackgroundColor(Color.gray);
            table.addCell(grayCell);

            Cell descriptionCell = new Cell(new Phrase(row.getDescription(), ReportHelper.TABLE_DATA_FONT));
            descriptionCell.setColspan(2);
            table.addCell(descriptionCell);
        }
        document.add(table);

        Table summary = new Table(1);
        summary.setWidth(100);
        summary.setPadding(1);
        summary.setWidths(new float[] {100});
        summary.setBorder(Table.TOP);
        Cell summaryCell = new Cell(new Phrase("Number of Records in Error = "
            + new DecimalFormat("#,##0").format(items.size()), ReportHelper.TABLE_HEADER_FONT));
        summaryCell.setBorder(Cell.TOP);
        summaryCell.setBorderWidth(1);
        summaryCell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        summary.addCell(summaryCell);
        document.add(summary);
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(LockboxImportErrorsReportResponse response) throws ReportGenerationException {
        return null;
    }
}
