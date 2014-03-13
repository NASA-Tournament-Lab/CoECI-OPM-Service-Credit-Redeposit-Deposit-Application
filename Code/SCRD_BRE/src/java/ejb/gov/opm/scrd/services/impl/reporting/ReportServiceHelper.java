/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.lowagie.text.Cell;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.style.RtfFont;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Provides helper members for {@link ReportService} implementations.
 *
 * @author RaitoShum
 * @version 1.0
 */
final class ReportServiceHelper {
    /**
     * The font family.
     */
    static final String REPORT_FONT_FAMILY = "Helvetica";

    /**
     * The RTF font for content.
     */
    static final RtfFont RTF_REPORT_CONTENT_FONT = new RtfFont(
            REPORT_FONT_FAMILY, 8);

    /**
     * The RTF font for header.
     */
    static final RtfFont RTF_REPORT_HEADER_FONT = new RtfFont(
            REPORT_FONT_FAMILY, 8, RtfFont.BOLD);

    /**
     * The RTF font with underline.
     */
    static final RtfFont RTF_REPORT_UNDERLINE_FONT = new RtfFont(
            REPORT_FONT_FAMILY, 8, RtfFont.UNDERLINE);

    /**
     * The PDF font for content.
     */
    static final Font PDF_REPORT_CONTENT_FONT = new Font(FontFamily.HELVETICA,
            6);

    /**
     * The PDF font for header.
     */
    static final Font PDF_REPORT_HEADER_FONT = new Font(FontFamily.HELVETICA,
            6, Font.BOLD);

    /**
     * The PDF font with underline.
     */
    static final Font PDF_REPORT_UNDERLINE_FONT = new Font(
            FontFamily.HELVETICA, 6, Font.UNDERLINE);

    /**
     * The default DateFormat to format Date in report.
     */
    static final SimpleDateFormat REPORT_DATE_FORMAT = new SimpleDateFormat(
            "MM/dd/yyyy");

    /**
     * The value representing no border in RTF.
     */
    static final int RTF_NO_BORDER = com.lowagie.text.Rectangle.NO_BORDER;

    /**
     * The value representing border top in RTF.
     */
    static final int RTF_BORDER_TOP = com.lowagie.text.Rectangle.TOP;

    /**
     * The value representing border bottom in RTF.
     */
    static final int RTF_BORDER_BOTTOM = com.lowagie.text.Rectangle.BOTTOM;

    /**
     * The value representing align left in RTF.
     */
    static final int RTF_ALIGN_LEFT = com.lowagie.text.Element.ALIGN_LEFT;

    /**
     * The value representing align right in RTF.
     */
    static final int RTF_ALIGN_RIGHT = com.lowagie.text.Element.ALIGN_RIGHT;

    /**
     * The value representing align center in RTF.
     */
    static final int RTF_ALIGN_CENTER = com.lowagie.text.Element.ALIGN_CENTER;

    /**
     * The value representing align center in PDF.
     */
    static final int PDF_ALIGN_CENTER = com.itextpdf.text.Element.ALIGN_CENTER;

    /**
     * The value representing align left in PDF.
     */
    static final int PDF_ALIGN_LEFT = com.itextpdf.text.Element.ALIGN_LEFT;

    /**
     * The value representing align right in PDF.
     */
    static final int PDF_ALIGN_RIGHT = com.itextpdf.text.Element.ALIGN_RIGHT;

    /**
     * The value representing no border in PDF.
     */
    static final int PDF_NO_BORDER = com.itextpdf.text.Rectangle.NO_BORDER;

    /**
     * The value representing border top in PDF.
     */
    static final int PDF_BORDER_TOP = com.itextpdf.text.Rectangle.TOP;

    /**
     * The value representing border bottom in PDF.
     */
    static final int PDF_BORDER_BOTTOM = com.itextpdf.text.Rectangle.BOTTOM;

    /**
     * Represents the SQL query string to get the data for daily report.
     */
    static final String DAILY_REPORT_DATA_SQL =
            "SELECT a, m, p, i FROM AuditBatchLogId a, MainframeImport m, " +
                    "PaymentTransaction p, InvoiceData i " +
                    "WHERE m.auditBatchLogId = a.auditBatchLogId " +
                    "AND m.payTransactionKey = p.payTransactionKey " +
                    "AND p.payTransactionKey = i.payTransactionKey";

    /**
     * Gets the data for daily report.
     *
     * @param entityManager
     *         the entity manager.
     * @return the data for daily report.
     */
    @SuppressWarnings("unchecked")
    static List<Object[]> getDailyReportData(EntityManager entityManager) {
        Query query = entityManager.createQuery(DAILY_REPORT_DATA_SQL);
        return query.getResultList();
    }

    /**
     * Sets the quarter and date of the BaseBalancedReportResponse.
     *
     * @param response
     *         the BaseBalancedReportResponse instance.
     * @throws ReportGenerationException
     *         if any error occurs while setting the quarter and date.
     */
    static void setScorecardQuarterDate(BaseBalancedReportResponse response) throws ReportGenerationException {
        try {
            int quarter;
            Calendar runTime = Calendar.getInstance();
            runTime.setTime(new Date());
            if (runTime.get(Calendar.MONTH) <= 2) {
                quarter = 1;
            } else if (runTime.get(Calendar.MONTH) <= 5) {
                quarter = 2;
            } else if (runTime.get(Calendar.MONTH) <= 8) {
                quarter = 3;
            } else {
                quarter = 4;
            }
            response.setQuarter(quarter);
            response.setFiscalYear(runTime.get(Calendar.YEAR));
            if (quarter != 1) {
                response.setStartDate(REPORT_DATE_FORMAT.parse((quarter - 2) * 3 + 1 +
                        "/01/" + response.getFiscalYear()));
                response.setEndDate(REPORT_DATE_FORMAT.parse((quarter - 1) * 3 + 1 +
                        "/01/" + response.getFiscalYear()));
            } else {
                response.setStartDate(REPORT_DATE_FORMAT.parse("10/1/" + (response.getFiscalYear() - 1)));
                response.setEndDate(REPORT_DATE_FORMAT.parse("1/1/" + response.getFiscalYear()));
            }
        } catch (ParseException ex) {
            throw new ReportGenerationException(
                    "Error occurred while setting the scorecard quarter and date.", ex);
        }
    }

    /**
     * Adds a row to the RTF table.
     *
     * @param table
     *         the table.
     * @param columns
     *         the content of each columns.
     * @param font
     *         the font to use.
     * @param backgroundColor
     *         the background color.
     * @param alignments
     *         the alignments of each columns.
     * @param border
     *         the border to use.
     */
    static void addReportTableRow(Table table, Object[] columns, RtfFont font,
                                  Color backgroundColor, int[] alignments, Integer border) {
        for (int i = 0; i < columns.length; i++) {
            table.addCell(createTableCell(columns[i], font, backgroundColor,
                    alignments == null ? null : alignments[i], border, 1));
        }
    }

    /**
     * Adds a row to the PDF table.
     *
     * @param table
     *         the table.
     * @param columns
     *         the content of each columns.
     * @param font
     *         the font to use.
     * @param backgroundColor
     *         the background color.
     * @param alignments
     *         the alignments of each columns.
     * @param border
     *         the border to use.
     */
    static void addReportTableRow(PdfPTable table, Object[] columns, Font font,
                                  BaseColor backgroundColor, int[] alignments, Integer border) {
        for (int i = 0; i < columns.length; i++) {
            table.addCell(createTableCell(columns[i], font, backgroundColor,
                    alignments == null ? null : alignments[i], border, 1));
        }
    }

    /**
     * Creates a cell for RTF table.
     *
     * @param content
     *         the content.
     * @param font
     *         the font to use.
     * @param backgroundColor
     *         the background color.
     * @param alignment
     *         the alignment to use.
     * @param border
     *         the border.
     * @param colSpan
     *         how many columns the cell will span.
     * @return the created cell.
     */
    static Cell createTableCell(Object content, RtfFont font,
                                Color backgroundColor, Integer alignment, Integer border,
                                int colSpan) {
        Cell cell = new Cell();
        if (border != null) {
            cell.setBorder(border);
        }
        if (backgroundColor != null) {
            cell.setBackgroundColor(backgroundColor);
        }
        if (content != null) {
            if (alignment != null) {
                cell.setHorizontalAlignment(alignment);
            }
            cell.setColspan(colSpan);
            String value;
            if (content instanceof BigDecimal) {
                value = Helper.getMoney((BigDecimal) content);
            } else if (content instanceof Number) {
                value = NumberFormat.getIntegerInstance(Locale.ENGLISH).format(content);
            } else {
                value = content.toString();
            }
            cell.add(new com.lowagie.text.Paragraph(value, font));
        }
        return cell;
    }

    /**
     * Creates a cell for PDF table.
     *
     * @param content
     *         the content.
     * @param font
     *         the font to use.
     * @param backgroundColor
     *         the background color.
     * @param alignment
     *         the alignment to use.
     * @param border
     *         the border.
     * @param colSpan
     *         how many columns the cell will span.
     * @return the created cell.
     */
    static PdfPCell createTableCell(Object content, Font font,
                                    BaseColor backgroundColor, Integer alignment, Integer border,
                                    int colSpan) {
        PdfPCell cell = new PdfPCell();
        if (border != null) {
            cell.setBorder(border);
        }
        if (backgroundColor != null) {
            cell.setBackgroundColor(backgroundColor);
        }
        cell.setColspan(colSpan);
        if (content != null) {
            String value;
            if (content instanceof BigDecimal) {
                value = Helper.getMoney((BigDecimal) content);
            } else if (content instanceof Number) {
                value = NumberFormat.getIntegerInstance(Locale.ENGLISH).format(content);
            } else {
                value = content.toString();
            }
            com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph(value, font);
            if (alignment != null) {
                p.setAlignment(alignment);
            }
            cell.addElement(p);
        }
        return cell;
    }

    /**
     * Adds a title paragraph to the RTF document.
     *
     * @param document
     *         the document.
     * @param title
     *         the title text.
     * @throws com.lowagie.text.DocumentException
     *         if any error occurs.
     */
    static void addReportTitle(com.lowagie.text.Document document, String title)
            throws com.lowagie.text.DocumentException {
        if (Helper.isNullOrEmpty(title)) {
            return;
        }
        com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(title,
                new RtfFont("Helvetica", 16, RtfFont.STYLE_BOLD));
        p.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
        document.add(p);
    }

    /**
     * Adds a title paragraph to the PDF document.
     *
     * @param document
     *         the document.
     * @param title
     *         the title text.
     * @throws com.itextpdf.text.DocumentException
     *         if any error occurs.
     */
    static void addReportTitle(com.itextpdf.text.Document document, String title)
            throws com.itextpdf.text.DocumentException {
        if (Helper.isNullOrEmpty(title)) {
            return;
        }
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph(title,
                new Font(FontFamily.HELVETICA, 16, Font.BOLD));
        p.setAlignment(PDF_ALIGN_CENTER);
        document.add(p);
    }

    /**
     * Adds a report date paragraph to the RTF document.
     *
     * @param document
     *         the document.
     * @param date
     *         the report date.
     * @param dateFormat
     *         the DateFormat instance to format to date.
     * @param alignment
     *         the alignment to use.
     * @throws com.lowagie.text.DocumentException
     *         if any error occurs.
     */
    static void addReportDate(com.lowagie.text.Document document, Date date,
                              DateFormat dateFormat, int alignment)
            throws com.lowagie.text.DocumentException {
        if (date == null) {
            return;
        }
        com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(
                dateFormat.format(date), RTF_REPORT_CONTENT_FONT);
        p.setAlignment(alignment);
        document.add(p);
    }

    /**
     * Adds a report date paragraph to the PDF document.
     *
     * @param document
     *         the document.
     * @param date
     *         the report date.
     * @param dateFormat
     *         the DateFormat instance to format to date.
     * @param alignment
     *         the alignment to use.
     * @throws com.itextpdf.text.DocumentException
     *         if any error occurs.
     */
    static void addReportDate(com.itextpdf.text.Document document, Date date,
                              DateFormat dateFormat, int alignment)
            throws com.itextpdf.text.DocumentException {
        if (date == null) {
            return;
        }
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph(
                dateFormat.format(date), PDF_REPORT_CONTENT_FONT);
        p.setAlignment(alignment);
        document.add(p);
    }

    /**
     * Creates an empty cell for RTF table.
     *
     * @param colSpan
     *         how many columns the cell will span.
     * @param border
     *         the border to use.
     * @return the created empty cell.
     */
    static Cell createEmptyCell(int colSpan, Integer border) {
        Cell cell = new Cell();
        if (border != null) {
            cell.setBorder(border);
        }
        cell.setColspan(colSpan);
        return cell;
    }

    /**
     * Creates an empty cell for PDF table.
     *
     * @param colSpan
     *         how many columns the cell will span.
     * @param border
     *         the border to use.
     * @return the created empty cell.
     */
    static PdfPCell createEmptyPdfCell(int colSpan, Integer border) {
        PdfPCell cell = new PdfPCell();
        if (border != null) {
            cell.setBorder(border);
        }
        cell.setColspan(colSpan);
        return cell;
    }

    /**
     * Adds the report title for balanced report.
     *
     * @param document
     *         the report document.
     * @param reportName
     *         the report name.
     * @param fiscalYear
     *         the fiscal year.
     * @param quarter
     *         the quarter.
     * @param startDate
     *         the start date.
     * @param endDate
     *         the end date.
     * @throws com.lowagie.text.DocumentException
     *         if any error occurs.
     */
    static void addBalancedReportTitle(com.lowagie.text.Document document, String reportName, Integer fiscalYear,
                                       Integer quarter, Date startDate, Date endDate)
            throws com.lowagie.text.DocumentException {
        com.lowagie.text.Paragraph title = new com.lowagie.text.Paragraph(reportName,
                new RtfFont(REPORT_FONT_FAMILY, 16, RtfFont.STYLE_BOLD));
        title.setAlignment(RTF_ALIGN_CENTER);
        document.add(title);
        if (fiscalYear != null && quarter != null) {
            com.lowagie.text.Paragraph subTitle1 = new com.lowagie.text.Paragraph("Fiscal Year " + fiscalYear +
                    " - Quarter" + quarter, new RtfFont(REPORT_FONT_FAMILY, 14, RtfFont.STYLE_BOLD));
            subTitle1.setAlignment(RTF_ALIGN_CENTER);
            document.add(subTitle1);
        }
        if (startDate != null && endDate != null) {
            com.lowagie.text.Paragraph subTitle2 = new com.lowagie.text.Paragraph("Processed between " +
                    "" + REPORT_DATE_FORMAT.format(startDate) + " and " +
                    "" + REPORT_DATE_FORMAT.format(endDate),
                    new RtfFont(REPORT_FONT_FAMILY, 12, RtfFont.STYLE_BOLD));
            subTitle2.setAlignment(RTF_ALIGN_CENTER);
            document.add(subTitle2);
        }
    }

    /**
     * Adds the report title for balanced report.
     *
     * @param document
     *         the report document.
     * @param reportName
     *         the report name.
     * @param fiscalYear
     *         the fiscal year.
     * @param quarter
     *         the quarter.
     * @param startDate
     *         the start date.
     * @param endDate
     *         the end date.
     * @throws com.itextpdf.text.DocumentException
     *         if any error occurs.
     */
    static void addBalancedReportTitle(com.itextpdf.text.Document document, String reportName, Integer fiscalYear,
                                       Integer quarter, Date startDate, Date endDate)
            throws com.itextpdf.text.DocumentException {
        com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph(reportName,
                new Font(FontFamily.HELVETICA, 16, Font.BOLD));
        title.setAlignment(PDF_ALIGN_CENTER);
        document.add(title);
        if (fiscalYear != null && quarter != null) {
            com.itextpdf.text.Paragraph subTitle1 = new com.itextpdf.text.Paragraph("Fiscal Year " + fiscalYear +
                    " - Quarter" + quarter, new Font(FontFamily.HELVETICA, 14, Font.BOLD));
            subTitle1.setAlignment(PDF_ALIGN_CENTER);
            document.add(subTitle1);
        }
        if (startDate != null && endDate != null) {
            com.itextpdf.text.Paragraph subTitle2 = new com.itextpdf.text.Paragraph("Processed between " +
                    "" + REPORT_DATE_FORMAT.format(startDate) + " and " +
                    "" + REPORT_DATE_FORMAT.format(endDate),
                    new Font(FontFamily.HELVETICA, 12, Font.BOLD));
            subTitle2.setAlignment(PDF_ALIGN_CENTER);
            document.add(subTitle2);
        }
    }
}
