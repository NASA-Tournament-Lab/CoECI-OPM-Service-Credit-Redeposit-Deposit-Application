/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.batchprocessing.AllDetails;
import gov.opm.scrd.entities.batchprocessing.AuditBatchLogId;
import gov.opm.scrd.entities.batchprocessing.InvoiceData;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.common.NamedEntity;
import gov.opm.scrd.services.ExportType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * Contains utility methods for common reporting tasks.
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Reporting - Lockbox and Miscellaneous Module Assembly):</em>
 * <ol>
 * <li>Added new methods, see all methods with tag @since 1.1 thats what the tag is for</li>
 * </ol>
 * </p>
 *
 * @author j3_guile, TCSASSEMBLER
 * @version 1.1
 */
public final class ReportHelper {

    /**
     * Date format used in the header.
     */
    public static final String HEADER_DATE_FORMAT = "EEEE, MMMM d, yyyy";
    /**
     * Common table data font.
     */
    public static final Font TABLE_DATA_FONT = new Font(Font.HELVETICA, 7f);
    /**
     * Common table header font.
     */
    public static final Font TABLE_HEADER_FONT = new Font(Font.HELVETICA, 7f, Font.BOLD);
    /**
     * Common table Big header font.
     */
    public static final Font TABLE_BIG_HEADER_FONT = new Font(Font.HELVETICA, 9f, Font.BOLD);
    /**
     * Title date font.
     */
    public static final Font HEADER_DATE_FONT = new Font(Font.HELVETICA, 7f, Font.NORMAL);
    /**
     * Page margins (left right).
     */
    public static final Font REPORT_HEADER_FONT = new Font(Font.HELVETICA, 14f, Font.BOLD);
    /**
     * Title date font.
     */
    public static final Font HEADER_SUB_TITLE_FONT = new Font(Font.HELVETICA, 7f, Font.NORMAL);
    /**
     * Page margins (left right).
     */
    public static final float MARGIN_LR = 10f;
    /**
     * Page margins (top bottom).
     */
    public static final float MARGIN_TB = 20f;
    /**
     * Common table header font with underline.
     */
    public static final Font TABLE_HEADER_UNDERLINE = new Font(Font.HELVETICA, 7f, Font.BOLD | Font.UNDERLINE);

    /**
     * Private constructor to prevent instantiation.
     */
    private ReportHelper() {
    }

    /**
     * Creates a common format header for the reports.
     *
     * @param document the report document
     * @param reportGenerationDate the date of printing
     * @param reportNameText the name of the report
     * @return the header to include
     */
    public static HeaderFooter generateSimpleHeader(Document document, Date reportGenerationDate,
        String reportNameText) {
        Paragraph header = new Paragraph();

        if (reportNameText != null) {
            Paragraph reportName = new Paragraph(reportNameText, ReportHelper.REPORT_HEADER_FONT);
            header.add(reportName);
        }

        if (reportGenerationDate != null) {
            Paragraph reportDate = new Paragraph(
                new SimpleDateFormat(HEADER_DATE_FORMAT, Locale.US).format(reportGenerationDate),
                ReportHelper.HEADER_DATE_FONT);
            header.add(reportDate);
        }

        HeaderFooter head = new HeaderFooter(new Phrase(header), false);
        head.setAlignment(Element.ALIGN_CENTER);
        head.setBorder(Rectangle.BOTTOM);
        return head;
    }

    /**
     * Creates a common format header for the reports, without a header border.
     *
     * @param document the report document
     * @param reportGenerationDate the date of printing
     * @param reportNameText the name of the report
     * @param subTitle report sub title
     * @return the header to include
     * @since 1.1
     */
    public static HeaderFooter generateHeaderNoBorder(Document document, Date reportGenerationDate,
        String reportNameText, String subTitle) {
        Paragraph header = new Paragraph();

        if (reportNameText != null) {
            Paragraph reportName = new Paragraph(reportNameText, ReportHelper.REPORT_HEADER_FONT);
            header.add(reportName);
        }

        if (reportGenerationDate != null) {
            Paragraph reportDate = new Paragraph(
                new SimpleDateFormat(HEADER_DATE_FORMAT, Locale.US).format(reportGenerationDate),
                ReportHelper.HEADER_DATE_FONT);
            header.add(reportDate);
        }

        if (subTitle != null) {
            Paragraph sub = new Paragraph(subTitle, ReportHelper.HEADER_SUB_TITLE_FONT);
            header.add(sub);
        }

        HeaderFooter head = new HeaderFooter(new Phrase(header), false);
        head.setAlignment(Element.ALIGN_CENTER);
        head.setBorder(Rectangle.NO_BORDER);
        return head;
    }

    /**
     * Formats the given value as a date.
     *
     * @param value the value to format
     * @return the formatted value, empty string if value is null
     */
    public static String formatDate(Date value) {
        return formatDate(value, "MM/dd/yyyy");
    }

    /**
     * Formats the given value as a date with time.
     *
     * @param value the value to format
     * @return the formatted value, empty string if value is null
     */
    public static String formatDateWithTime(Date value) {
        return formatDate(value, "M/d/yyyy hh:mm a");
    }

    /**
     * Formats the given value as a currency.
     *
     * @param value the value to format
     * @return the formatted value, empty string if value is null
     */
    public static String formatMoney(BigDecimal value) {
        if (value == null) {
            return "";
        }
        if (value.doubleValue() < 0) {
            return new DecimalFormat("($#,##0.00)").format(value.abs());
        } else {
            return new DecimalFormat("$#,##0.00").format(value);
        }
    }

    /**
     * Formats the given value.
     *
     * @param value the value to format
     * @return the formatted value, empty string if value is null
     */
    public static String formatInteger(Integer value) {
        if (value == null) {
            return "";
        }
        return new DecimalFormat("#,##0").format(value);
    }

    /**
     * Formats the given date.
     *
     * @param date the date to format
     * @param format the format pattern
     * @return the formatted date, empty string if the date is null
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(format, Locale.US).format(date);
    }

    /**
     * This method retrieves the correlated invoice data to a transaction.
     *
     * @param em the entity manager
     * @param logger the logger
     * @param tx the transaction
     * @return the correlated invoice, null if not found, or the first match found
     */
    public static InvoiceData getInvoiceData(EntityManager em, Logger logger, PaymentTransaction tx) {
        List<InvoiceData> invoices = em
            .createQuery("SELECT i FROM InvoiceData i WHERE i.deleted = false AND i.payTransactionKey = :txId",
                InvoiceData.class).setParameter("txId", tx.getPayTransactionKey()).getResultList();
        InvoiceData invoice = null;
        if (invoices.size() > 1) {
            logger.warn("Multiple invoice data found for transaction " + tx.getId() + ". This could be invalid data.");
            invoice = invoices.get(0);
        } else if (invoices.size() == 0) {
            logger.warn("No invoice data found for transaction " + tx.getId() + ". This could be invalid data.");
        } else {
            invoice = invoices.get(0);
        }
        return invoice;
    }

    /**
     * Retrieves lookup names referenced by identifier.
     *
     * @param em the entity manager
     * @param lookupType the type of lookup entity
     * @param key the identifier for the lookup
     * @return the name of the entity
     */
    public static String getLookupName(EntityManager em, Class<? extends NamedEntity> lookupType, Integer key) {
        NamedEntity entity = em.find(lookupType, key.longValue());
        if (entity == null) {
            return null;
        }
        return entity.getName();
    }

    /**
     * This method retrieves the correlated account data to a transaction.
     *
     * @param em the entity manager
     * @param logger the logger
     * @param tx the transaction
     * @return the correlated account, null if not found, or the first match found
     */
    public static Account getAccountData(EntityManager em, Logger logger, PaymentTransaction tx) {
        List<Account> accounts = em
            .createQuery("SELECT a FROM Account a WHERE a.deleted = false AND a.claimNumber = :csd", Account.class)
            .setParameter("csd", tx.getCsd()).getResultList();
        Account account = null;
        if (accounts.size() > 1) {
            logger.warn("Multiple account data found for transaction " + tx.getId() + ". This could be invalid data.");
            account = accounts.get(0);
        } else if (accounts.size() == 0) {
            logger.warn("No account data found for transaction " + tx.getId() + ". This could be invalid data.");
        } else {
            account = accounts.get(0);
        }
        return account;
    }

    /**
     * This method retrieves the correlated transaction data given a key.
     *
     * @param em the entity manager
     * @param logger the logger
     * @param key the transaction key
     * @return the correlated transaction, null if not found, or the first match found
     * @since 1.1
     */
    public static PaymentTransaction getPaymentTransaction(EntityManager em, Logger logger, Integer key) {
        List<PaymentTransaction> transactions = em
            .createQuery("SELECT p FROM PaymentTransaction p WHERE p.deleted = false AND p.payTransactionKey = :key",
                PaymentTransaction.class).setParameter("key", key).getResultList();
        PaymentTransaction transaction = null;
        if (transactions.size() > 1) {
            logger.warn("Multiple transactions found for key " + key + ". This could be invalid data.");
            transaction = transactions.get(0);
        } else if (transactions.size() == 0) {
            logger.warn("No transaction data found for transaction " + key + ". This could be invalid data.");
        } else {
            transaction = transactions.get(0);
        }
        return transaction;
    }

    /**
     * This method retrieves the audit batch log ID data given its key.
     *
     * @param em the entity manager
     * @param logger the logger
     * @param key the audit log key
     * @return the audit log entity, null if not found, or the first match found
     * @since 1.1
     */
    public static AuditBatchLogId getAuditBatchLogId(EntityManager em, Logger logger, String key) {
        List<AuditBatchLogId> logIds = em
            .createQuery("SELECT a FROM AuditBatchLogId a WHERE a.auditBatchLogId = :key",
                AuditBatchLogId.class).setParameter("key", key).getResultList();
        AuditBatchLogId logId = null;
        if (logIds.size() > 1) {
            logger.warn("Multiple log IDs found for " + key + ". This could be invalid data.");
            logId = logIds.get(0);
        } else if (logIds.size() == 0) {
            logger.warn("No log ID data found for " + key + ". This could be invalid data.");
        } else {
            logId = logIds.get(0);
        }
        return logId;
    }

    /**
     * This method retrieves the account by CSD.
     *
     * @param em the entity manager
     * @param logger the logger
     * @param csd the claim number
     * @return the correlated account, null if not found, or the first match found
     */
    public static Account getAccountData(EntityManager em, Logger logger, String csd) {
        List<Account> accounts = em
            .createQuery("SELECT a FROM Account a WHERE a.deleted = false AND a.claimNumber = :csd", Account.class)
            .setParameter("csd", csd).getResultList();
        Account account = null;
        if (accounts.size() > 1) {
            logger.warn("Multiple account data CSD " + csd + ". This could be invalid data.");
            account = accounts.get(0);
        } else if (accounts.size() == 0) {
            logger.warn("No account data found for CSD " + csd + ". This could be invalid data.");
        } else {
            account = accounts.get(0);
        }
        return account;
    }


    /**
     * This method retrieves the correlated details to a transaction.
     *
     * @param em the entity manager
     * @param logger the logger
     * @param key the transaction key
     * @return the correlated details, null if not found, or the first match found
     */
    public static AllDetails getAllDetails(EntityManager em, Logger logger, String key) {
        List<AllDetails> accounts = em
            .createQuery("SELECT a FROM AllDetails a WHERE a.deleted = false AND a.payTransactionKey = :txId",
                AllDetails.class).setParameter("txId", key).getResultList();
        AllDetails details = null;
        if (accounts.size() > 1) {
            logger.warn("Multiple data found for transaction " + key + ". This could be invalid data.");
            details = accounts.get(0);
        } else if (accounts.size() == 0) {
            logger.warn("No data found for transaction " + key + ". This could be invalid data.");
        } else {
            details = accounts.get(0);
        }
        return details;
    }

    /**
     * Initializes the document writer format.
     * @param document the document
     * @param outputStream the export stream
     * @param exportType the export format
     * @throws DocumentException if the document cannot be initialized or is not one of the itext supported format
     */
    public static void initDocumentFormat(Document document, ByteArrayOutputStream outputStream, ExportType exportType)
        throws DocumentException {
        if (exportType == ExportType.PDF) {
            PdfWriter.getInstance(document, outputStream);
        } else if (exportType == ExportType.RTF || exportType == ExportType.DOC) {
            RtfWriter2.getInstance(document, outputStream);
        }
    }

    /**
     * Creates a money cell (aligned right, padded with spaces).
     * @param phrase the text to use
     * @param font the font to use
     * @return the formatted table cell
     * @throws BadElementException if the cell cannot be generated
     */
    public static Cell moneyCell(String phrase, Font font) throws BadElementException {
        Cell rightCell = new Cell(new Phrase(phrase + "    ", font));
        rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return rightCell;
    }

    /**
     * Creates a money cell (aligned right, padded with spaces).
     * @param phrase the text to use
     * @param font the font to use
     * @return the formatted table cell
     * @throws BadElementException if the cell cannot be generated
     * @since 1.1
     */
    public static Cell linedMoneyCell(String phrase, Font font) throws BadElementException {
        Cell rightCell = new Cell(new Phrase(phrase + "    ", font));
        rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        rightCell.setBorder(Cell.BOTTOM);
        rightCell.setBorderWidthBottom(1);
        return rightCell;
    }

    /**
     * Creates a money cell (aligned right, padded with spaces).
     * @param phrase the text to use
     * @param font the font to use
     * @return the formatted table cell
     * @throws BadElementException if the cell cannot be generated
     */
    public static Cell centeredLinedMoneyCell(String phrase, Font font) throws BadElementException {
        Cell rightCell = new Cell(new Phrase(phrase + "    ", font));
        rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        rightCell.setBorder(Cell.BOTTOM);
        rightCell.setBorderWidthBottom(1);
        return rightCell;
    }


    /**
     * Creates an underlined cell.
     * @param phrase the text to use
     * @param font the font to use
     * @return the formatted table cell
     * @throws BadElementException if the cell cannot be generated
     * @since 1.1
     */
    public static Cell linedCell(String phrase, Font font) throws BadElementException {
        Cell cell = new Cell(new Phrase(phrase, font));
        cell.setBorder(Cell.BOTTOM);
        cell.setBorderWidthBottom(1);
        return cell;
    }

    /**
     * Creates an underlined cell.
     * @param phrase the text to use
     * @param font the font to use
     * @return the formatted table cell
     * @throws BadElementException if the cell cannot be generated
     */
    public static Cell centeredLinedCell(String phrase, Font font) throws BadElementException {
        Cell cell = new Cell(new Phrase(phrase, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Cell.BOTTOM);
        cell.setBorderWidthBottom(1);
        return cell;
    }

    
    /**
     * Creates a Text underlined cell.
     * @param phrase the text to use
     * @param font the font to use
     * @return the formatted table cell
     * @throws BadElementException if the cell cannot be generated
     * @since 1.1
     */
    public static Cell textLinedCell(String phrase, Font font) throws BadElementException {
        Chunk ch = new Chunk(phrase, font);
        ch.setUnderline(1f, -2f);
        Cell cell = new Cell(new Phrase(ch));
        return cell;
    }

    /**
     * Creates a Text Money underlined cell.
     * @param phrase the text to use
     * @param font the font to use
     * @return the formatted table cell
     * @throws BadElementException if the cell cannot be generated
     * @since 1.1
     */
    public static Cell textLinedMoneyCell(String phrase, Font font) throws BadElementException {
        Chunk ch = new Chunk(phrase, font);
        ch.setUnderline(1f, -2f);
        Cell rightCell = new Cell(new Phrase(ch));
        rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return rightCell;
    }
    
    /**
     * Null checking sum function for decimal objects.
     *
     * @param base the base value
     * @param value the value to add
     * @return the sum of the two numbers, nulls are treated as zero
     * @since 1.1
     */
    public static BigDecimal sum(BigDecimal base, BigDecimal value) {
        if (base == null && value == null) {
            return new BigDecimal(0);
        }
        if (base == null) {
            return value;
        }
        if (value == null) {
            return base;
        }
        return base.add(value);
    }
    
    public static byte[] readFileToByteArray(File file) throws IOException {
        byte[] bytes = new byte[(int) file.length()];
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            is.read(bytes);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return bytes;
    }

}
