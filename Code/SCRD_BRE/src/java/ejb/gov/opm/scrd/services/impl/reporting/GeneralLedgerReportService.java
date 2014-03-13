/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.batchprocessing.AllDetails;
import gov.opm.scrd.entities.batchprocessing.AuditBatchLogId;
import gov.opm.scrd.entities.batchprocessing.MainframeImport;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;

/**
 * <p>
 * This class handles data retrieval and export of the general ledger report.
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
public class GeneralLedgerReportService extends BaseReportService implements
    ReportService<GeneralLedgerReportRequest, GeneralLedgerReportResponse> {

    /**
     * For julian date conversion.
     */
    private static final int JGREG = 15 + 31 * (10 + 12 * 1582);

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = GeneralLedgerReportService.class.getName();

    /**
     * Default constructor.
     */
    public GeneralLedgerReportService() {
    }

    /**
     * Generates the report for active credit balances records.
     *
     * @param request the request object
     * @return the model for the requested report.
     * @throws IllegalArgumentException if the request is null.
     * @throws ReportGenerationException if there is any problem when generating response.
     */
    public GeneralLedgerReportResponse getReport(GeneralLedgerReportRequest request) throws ReportGenerationException {
        String signature = CLASS_NAME + "#getReport(GeneralLedgerReportRequest request)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request"}, new Object[] {request});
        Helper.checkNull(logger, signature, request, "request");

        // see http://apps.topcoder.com/forums/?module=Thread&threadID=810373&start=0
        try {
            GeneralLedgerReportResponse response = new GeneralLedgerReportResponse();
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
            String batchLogId = batchId.getAuditBatchLogId();

            List<MainframeImport> mfImportsRecords = getEntityManager()
                .createQuery("SELECT m FROM MainframeImport m WHERE m.auditBatchLogId = :batchLogId",
                    MainframeImport.class).setParameter("batchLogId", batchLogId).getResultList();

            Map<GroupingKey, GeneralLedgerReportResponseItem> groups =
                new HashMap<GeneralLedgerReportService.GroupingKey, GeneralLedgerReportResponseItem>();

            for (MainframeImport mfi : mfImportsRecords) {
                PaymentTransaction txn = ReportHelper.getPaymentTransaction(getEntityManager(), getLogger(),
                    mfi.getPayTransactionKey());
                if (txn == null) {
                    // cannot include details of missing transaction
                    continue;
                }
                AllDetails detail = ReportHelper.getAllDetails(getEntityManager(), getLogger(),
                    String.valueOf(mfi.getPayTransactionKey()));
                if (detail == null) {
                    continue;
                }
                Date convertedDate = convertDate(detail.getJulianDate());

                // get account for plan type
                Account account = ReportHelper.getAccountData(getEntityManager(), getLogger(),
                    detail.getScmClaimNumber());

                GroupingKey key = new GroupingKey();
                key.groupDate = ReportHelper.formatDate(detail.getPaymentDate());
                key.plan = account.getPlanType();
                key.agency = detail.getAgency();
                key.type = detail.getPaymentType();
                key.accountingCode = detail.getGlAccountingCode();
                key.glDate = String.valueOf(convertedDate);
                key.glFiller = detail.getGlFiller();
                key.glCode = detail.getGlCode();
                key.sourceCode = detail.getRevenueSourceCode();

                if (groups.get(key) == null) {
                    GeneralLedgerReportResponseItem item = new GeneralLedgerReportResponseItem();
                    item.setGroupDate(ReportHelper.formatDate(detail.getPaymentDate()));
                    item.setPlan(account.getPlanType());
                    item.setAgency(detail.getAgency());
                    item.setType(detail.getPaymentType());
                    item.setAccountingCode(detail.getGlAccountingCode());
                    item.setGlCode(detail.getGlCode());
                    item.setGlFiller(detail.getGlFiller());
                    item.setGlDate(convertedDate);
                    item.setSourceCode(detail.getRevenueSourceCode());
                    item.setPayments(new ArrayList<GeneralLedgerReportResponsePaymentDetails>());
                    item.setPaymentsNumber(0);
                    item.setReceiptAmount(new BigDecimal(0));
                    groups.put(key, item);
                }

                GeneralLedgerReportResponseItem item = groups.get(key);
                item.setPaymentsNumber(item.getPaymentsNumber() + 1);
                item.setReceiptAmount(ReportHelper.sum(item.getReceiptAmount(), txn.getPayTransPaymentAmount()));

                // http://apps.topcoder.com/forums/?module=Thread&threadID=810138&start=0
                GeneralLedgerReportResponsePaymentDetails line = new GeneralLedgerReportResponsePaymentDetails();
                line.setId(detail.getPayTransactionKey());
                line.setCsd(txn.getCsd());
                line.setClaimantDateOfBirth(ReportHelper.formatDate(txn.getScmDateOfBirth()));
                line.setClaimantName(detail.getClaimantName());
                line.setPaymentDate(txn.getPayTransTransactionDate());
                line.setPaymentAmount(txn.getPayTransPaymentAmount());
                item.getPayments().add(line);
            }

            response.setItems(new ArrayList<GeneralLedgerReportResponseItem>(groups.values()));
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
     * Converts the given Julian date.
     *
     * http://www.coderanch.com/t/410264/java/java/Julian-Gregorian-date-conversion
     * @param julianDate the Julian date to convert
     * @return the converted date
     */
    private static Date convertDate(Integer julianDate) {
        if (julianDate != null) {
            int jalpha, ja, jb, jc, jd, je, year, month, day;
            ja = (int) julianDate;
            if (ja >= JGREG) {
                jalpha = (int) (((ja - 1867216) - 0.25) / 36524.25);
                ja = ja + 1 + jalpha - jalpha / 4;
            }

            jb = ja + 1524;
            jc = (int) (6680.0 + ((jb - 2439870) - 122.1) / 365.25);
            jd = 365 * jc + jc / 4;
            je = (int) ((jb - jd) / 30.6001);
            day = jb - jd - (int) (30.6001 * je);
            month = je - 1;
            if (month > 12) {
                month = month - 12;
            }
            year = jc - 4715;
            if (month > 2) {
                year--;
            }
            if (year <= 0) {
                year--;
            }
            return new GregorianCalendar(year, month - 1, day).getTime();
        } else {
            return null;
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
    public byte[] exportReport(GeneralLedgerReportResponse response, ExportType exportType)
        throws ReportGenerationException {
        String signature = CLASS_NAME + "#exportReport(GeneralLedgerReportResponse response, ExportType exportType)";
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

            List<GeneralLedgerReportResponseItem> items = response.getItems();

            // group by date
            Map<String, List<GeneralLedgerReportResponseItem>> groups =
                new HashMap<String, List<GeneralLedgerReportResponseItem>>();
            for (GeneralLedgerReportResponseItem item : items) {
                if (groups.get(item.getGroupDate()) == null) {
                    groups.put(item.getGroupDate(), new ArrayList<GeneralLedgerReportResponseItem>());
                }
                groups.get(item.getGroupDate()).add(item);
            }
            
            for (Entry<String, List<GeneralLedgerReportResponseItem>> entry : groups.entrySet()) {
                document.add(new Paragraph(" ")); // spacer
                // group title
                Paragraph paragraph = new Paragraph(entry.getKey(), ReportHelper.TABLE_HEADER_FONT);
                document.add(paragraph);
                List<GeneralLedgerReportResponseItem> subgroups = entry.getValue();
                for (GeneralLedgerReportResponseItem item : subgroups) {
                    renderDetails(item, document);
                }
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
     * Renders a table of account details.
     *
     * @param item the report model
     * @param document the current document
     * @throws DocumentException may be thrown by the iText library while rendering the elements
     */
    private void renderDetails(GeneralLedgerReportResponseItem item, Document document)
        throws DocumentException {

        // table styling
        Table table = new Table(10);
        table.setBorder(Table.NO_BORDER);
        Cell cell = new Cell();
        cell.setBorder(Cell.NO_BORDER);
        table.setDefaultCell(cell);
        table.setWidth(100);
        table.setPadding(1);

        // table header and column widths
        table.setWidths(new float[] {10, 10, 5, 15, 10, 10, 10, 10, 10, 10});
        table.addCell(ReportHelper.linedCell("\nPlan", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedCell("\nAgency", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedCell("\nType", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.linedCell("Accounting \nCode", ReportHelper.TABLE_HEADER_FONT));
        Cell glInfo = ReportHelper.linedCell("\nGL Date, Filler & Code", ReportHelper.TABLE_HEADER_FONT);
        glInfo.setColspan(3);
        table.addCell(glInfo);
        table.addCell(ReportHelper.linedCell("Source \nCode", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.centeredLinedCell("#     \nPayments", ReportHelper.TABLE_HEADER_FONT));
        table.addCell(ReportHelper.centeredLinedMoneyCell("Receipt    \nAmount", ReportHelper.TABLE_HEADER_FONT));

        // table data
        table.addCell(new Phrase(item.getPlan(), ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase(item.getAgency(), ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase(item.getType(), ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase(item.getAccountingCode(), ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase(ReportHelper.formatDate(item.getGlDate()), ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase(item.getGlFiller(), ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase(item.getGlCode(), ReportHelper.TABLE_DATA_FONT));
        table.addCell(new Phrase(item.getSourceCode(), ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatInteger(item.getPaymentsNumber()),
            ReportHelper.TABLE_DATA_FONT));
        table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(item.getReceiptAmount()),
            ReportHelper.TABLE_DATA_FONT));

        // spacer
        Cell spacerCell = new Cell("");
        spacerCell.setColspan(10);
        table.addCell(spacerCell);

        // payment details
        List<GeneralLedgerReportResponsePaymentDetails> payments = item.getPayments();
        for (GeneralLedgerReportResponsePaymentDetails payment : payments) {
            table.addCell(new Phrase("", ReportHelper.TABLE_DATA_FONT));
            table.addCell(new Phrase("ID: " + payment.getId(), ReportHelper.TABLE_DATA_FONT));

            Cell claimCell = new Cell(new Phrase("CSD: " + payment.getCsd() + " DOB: "
                + payment.getClaimantDateOfBirth(), ReportHelper.TABLE_DATA_FONT));
            claimCell.setColspan(2);
            claimCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(claimCell);

            Cell nameCell = new Cell(new Phrase("Name: " + payment.getClaimantName(), ReportHelper.TABLE_DATA_FONT));
            nameCell.setColspan(4);
            nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(nameCell);

            table.addCell(ReportHelper.moneyCell(ReportHelper.formatDate(payment.getPaymentDate()),
                ReportHelper.TABLE_DATA_FONT));
            table.addCell(ReportHelper.moneyCell(ReportHelper.formatMoney(payment.getPaymentAmount()),
                ReportHelper.TABLE_DATA_FONT));
        }

        document.add(table);
    }

    /**
     * Grouping key for the non aggregate columns of the report.
     *
     * @author TCSASSEMBLER
     * @version 1.0
     */
    private class GroupingKey {

        /**
         * Group date.
         */
        private String groupDate;
        /**
         * Plan.
         */
        private String plan;

        /**
         * Agency.
         */
        private String agency;

        /**
         * Type.
         */
        private String type;

        /**
         * Accounting code.
         */
        private String accountingCode;

        /**
         * General ledger date.
         */
        private String glDate;

        /**
         * General filler.
         */
        private String glFiller;

        /**
         * General ledger code.
         */
        private String glCode;

        /**
         * Source code.
         */
        private String sourceCode;

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
            return plan.hashCode();
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
            return String.valueOf(this.plan).equals(String.valueOf(that.plan))
                && String.valueOf(this.groupDate).equals(String.valueOf(that.groupDate))
                && String.valueOf(this.agency).equals(String.valueOf(that.agency))
                && String.valueOf(this.type).equals(String.valueOf(that.type))
                && String.valueOf(this.accountingCode).equals(String.valueOf(that.accountingCode))
                && String.valueOf(this.glDate).equals(String.valueOf(that.glDate))
                && String.valueOf(this.glFiller).equals(String.valueOf(that.glFiller))
                && String.valueOf(this.glCode).equals(String.valueOf(that.glCode))
                && String.valueOf(this.sourceCode).equals(String.valueOf(that.sourceCode));
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
    public byte[] renderChart(GeneralLedgerReportResponse response) throws ReportGenerationException {
        return null;
    }

}
