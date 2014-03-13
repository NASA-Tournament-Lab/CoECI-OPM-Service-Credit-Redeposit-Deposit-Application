/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Cell;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.batchprocessing.MainframeImport;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.entities.batchprocessing.PaymentTransactionCodes;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;
import org.jboss.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * This class is the implementation of the ReportService which generates balanced scorecard payment report. It uses
 * local data for generating report and iText/iText RTF for generating reports. <p><strong>Thread-safety:</strong> This
 * class is effectively thread - safe after configuration, the configuration is done in a thread - safe manner.</p>
 *
 * Changes:
 *  v1.2 change the compare of the PaymentTransactionCodes.
 * 
 * @author AleaActaEst, RaitoShum
 * @version 1.2
 */
@Stateless
@LocalBean
public class BalancedScorecardPaymentReportService extends BaseReportService implements
        ReportService<BalancedScorecardPaymentReportRequest, BalancedScorecardPaymentReportResponse> {
    /**
     * <p> Represents the class name. </p>
     */
    private static final String CLASS_NAME = BalancedScorecardPaymentReportService.class
            .getName();

    /**
     * Creates a new instance of the {@link BalancedScorecardPaymentReportService} class.
     */
    public BalancedScorecardPaymentReportService() {
        super();
    }

    /**
     * Creates the report for the given request.
     *
     * @param request
     *         the request data to generate report.
     * @return ReportResponse instance containing the report data, can not be null.
     * @throws IllegalArgumentException
     *         if the request is null.
     * @throws ReportGenerationException
     *         if there is any problem when generating response.
     */
    public BalancedScorecardPaymentReportResponse getReport(BalancedScorecardPaymentReportRequest request)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#getReport(BalancedScorecardPaymentReportRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "request" }, new Object[]{ request });
        Helper.checkNull(logger, signature, request, "request");
        try {
            BalancedScorecardPaymentReportResponse response = new BalancedScorecardPaymentReportResponse();
            response.setReportName(getReportName());
            response.setReportGenerationDate(new Date());
            response.setGrandTotal(new BigDecimal(0));
            response.setPendingPaymentTotal(new BigDecimal(0));
            response.setFinalPaymentTotal(new BigDecimal(0));

            FinalCheckBatchProcessItem finalCheckBatchProcessItem = new FinalCheckBatchProcessItem();
            finalCheckBatchProcessItem.setBatchProcessingTotal(createItem("Batch Processing", "Resolved"));
            finalCheckBatchProcessItem.setBatchProcessSubTotal(new BigDecimal(0));
            response.setFinalCheckBatchProcessItem(finalCheckBatchProcessItem);

            FinalAchLockboxItem finalAchLockboxItem = new FinalAchLockboxItem();
            finalAchLockboxItem.setDebitVoucher(createItem("Debit Vouchers", "Resolved"));
            finalAchLockboxItem.setDirectPayLife(createItem("Direct Pay Life", "Resolved"));
            finalAchLockboxItem.setSuspenseRefundComplete(createItem("Suspense Refund Complete", "Resolved"));
            finalAchLockboxItem.setPostedComplete(createItem("Posted Complete", "Resolved"));
            finalAchLockboxItem.setPostedCompleteResolved(createItem("Posted Complete Resolved", "Resolved"));
            finalAchLockboxItem.setAchSubTotal(new BigDecimal(0));
            response.setFinalAchLockboxItem(finalAchLockboxItem);

            FinalCheckLockboxItem finalCheckLockboxItem = new FinalCheckLockboxItem();
            finalCheckLockboxItem.setAnnuityComplete(createItem("Annuity Complete", "Resolved"));
            finalCheckLockboxItem.setDirectPayLife(createItem("Direct Pay Life", "Resolved"));
            finalCheckLockboxItem.setPostedCompleteResolved(createItem("Posted Complete Resolved", "Resolved"));
            finalCheckLockboxItem.setPostedComplete(createItem("Posted Complete", "Resolved"));
            finalCheckLockboxItem.setSuspenseRefund(createItem("Suspense Refund", "Resolved"));
            finalCheckLockboxItem.setCheckSubTotal(createItem("Check Sub Total", "Resolved"));
            finalCheckLockboxItem.setVoluntaryContributions(createItem("Voluntary Contributions", "Resolved"));
            finalCheckLockboxItem.setLockboxBankSubTotal(new BigDecimal(0));
            response.setFinalCheckLockboxItem(finalCheckLockboxItem);

            FinalCheckOverTheCounterItem finalCheckOverTheCounterItem = new FinalCheckOverTheCounterItem();
            finalCheckOverTheCounterItem.setPostedComplete(createItem("Posted Complete", "Resolved"));
            finalCheckOverTheCounterItem.setOverTheCounterSubTotal(new BigDecimal(0));
            response.setFinalCheckOverTheCounterItem(finalCheckOverTheCounterItem);

            PendingAchLockboxItem pendingAchLockboxItem = new PendingAchLockboxItem();
            pendingAchLockboxItem.setPostedPendingResolved(createItem("Posted Pending Resolved", "Suspense"));
            pendingAchLockboxItem.setDirectPayLife(createItem("Direct Pay Life", "Suspense"));
            pendingAchLockboxItem.setPostedPending(createItem("Posted Pending", "Suspense"));
            pendingAchLockboxItem.setSuspended(createItem("Suspended", "Suspense"));
            pendingAchLockboxItem.setSuspendedRefund(createItem("Suspense Refund", "Suspense"));
            pendingAchLockboxItem.setUnresolved(createItem("Unresolved", "Suspense"));
            pendingAchLockboxItem.setAchSubTotal(new BigDecimal(0));
            response.setPendingAchLockboxItem(pendingAchLockboxItem);

            PendingAdjustmentLockboxItem pendingAdjustmentLockboxItem = new PendingAdjustmentLockboxItem();
            pendingAdjustmentLockboxItem.setPostedPendingApproval(createItem("Posted Pending Approval",
                    "Suspense"));
            pendingAdjustmentLockboxItem.setAdjustmentSubTotal(new BigDecimal(0));
            response.setPendingAdjustmentLockboxItem(pendingAdjustmentLockboxItem);

            PendingCheckLockboxItem pendingCheckLockboxItem = new PendingCheckLockboxItem();
            pendingCheckLockboxItem.setPostedPending(createItem("Posted Pending", "Suspense"));
            pendingCheckLockboxItem.setSuspenseRefund(createItem("Suspense Refund", "Suspense"));
            pendingCheckLockboxItem.setUnresolved(createItem("Unresolved", "Suspense"));
            pendingCheckLockboxItem.setCheckSubTotal(new BigDecimal(0));
            response.setPendingCheckLockboxItem(pendingCheckLockboxItem);

            PendingCheckOverTheCounterItem pendingCheckOverTheCounterItem = new PendingCheckOverTheCounterItem();
            pendingCheckOverTheCounterItem.setPostedComplete(createItem("Posted Complete", "Suspense"));
            pendingCheckOverTheCounterItem.setPostedPending(createItem("Posted Pending", "Suspense"));
            response.setPendingCheckOverTheCounterItem(pendingCheckOverTheCounterItem);

            List<Object[]> result = ReportServiceHelper.getDailyReportData(getEntityManager());
            for (Object[] objects : result) {
                PaymentTransaction paymentTransaction = (PaymentTransaction) objects[2];
                MainframeImport mainframeImport = (MainframeImport) objects[1];
                if (paymentTransaction.getResolvedSuspense()) {
                    if (paymentTransaction.getUserInserted() && paymentTransaction.getPayTransPaymentAmount()
                            .doubleValue() > 0 && PaymentTransactionCodes.POSTED_PENDING.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        finalCheckOverTheCounterItem.setOverTheCounterSubTotal(setItem(
                                finalCheckOverTheCounterItem.getPostedComplete(),
                                finalCheckOverTheCounterItem.getOverTheCounterSubTotal(),
                                paymentTransaction));
                        response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                .getPayTransPaymentAmount()));
                    } else if (mainframeImport.getAchFlag() && !mainframeImport.getErrorFlag()) {
                        if (PaymentTransactionCodes.DIRECT_PAY_LIFE_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalAchLockboxItem.setAchSubTotal(setItem(finalAchLockboxItem.getDirectPayLife(),
                                    finalAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.DEBIT_VOUCHER_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalAchLockboxItem.setAchSubTotal(setItem(finalAchLockboxItem.getDebitVoucher(),
                                    finalAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.POSTED_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalAchLockboxItem.setAchSubTotal(setItem(finalAchLockboxItem.getPostedComplete(),
                                    finalAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.POSTED_PENDING_APPROVAL.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalAchLockboxItem.setAchSubTotal(setItem(finalAchLockboxItem.getPostedCompleteResolved(),
                                    finalAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.SUSPENSE_REFUND_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalAchLockboxItem.setAchSubTotal(setItem(finalAchLockboxItem.getSuspenseRefundComplete(),
                                    finalAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        }
                    } else if (!mainframeImport.getAchFlag() && !mainframeImport.getErrorFlag()) {
                        if (!paymentTransaction.getUserInserted() || (paymentTransaction.getUserInserted() &&
                                PaymentTransactionCodes.MANUAL_ADJUSTMENT_CANCELLED_PENDING_APPROVAL.getCode() != paymentTransaction.getPaymentStatusCode()
                                && PaymentTransactionCodes.MANUAL_ADJUSTMENT_CANCELLED_PENDING.getCode() != paymentTransaction.getPaymentStatusCode() &&
                                PaymentTransactionCodes.MANUAL_ADJUSTMENT_CANCELLED.getCode() != paymentTransaction.getPaymentStatusCode())) {
                            finalCheckBatchProcessItem.setBatchProcessSubTotal(setItem(finalCheckBatchProcessItem
                                    .getBatchProcessingTotal(),
                                    finalCheckBatchProcessItem.getBatchProcessSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.ANNUITY_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalCheckLockboxItem.setLockboxBankSubTotal(setItem(finalCheckLockboxItem
                                    .getAnnuityComplete(),
                                    finalCheckLockboxItem.getLockboxBankSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                            setItem(finalCheckLockboxItem.getCheckSubTotal(), null, paymentTransaction);
                        } else if (PaymentTransactionCodes.DIRECT_PAY_LIFE_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalCheckLockboxItem.setLockboxBankSubTotal(setItem(finalCheckLockboxItem
                                    .getDirectPayLife(),
                                    finalCheckLockboxItem.getLockboxBankSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                            setItem(finalCheckLockboxItem.getCheckSubTotal(), null, paymentTransaction);
                        } else if (PaymentTransactionCodes.POSTED_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalCheckLockboxItem.setLockboxBankSubTotal(setItem(finalCheckLockboxItem.
                                    getPostedComplete(),
                                    finalCheckLockboxItem.getLockboxBankSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                            setItem(finalCheckLockboxItem.getCheckSubTotal(), null, paymentTransaction);
                        } else if (PaymentTransactionCodes.POSTED_PENDING_APPROVAL.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalCheckLockboxItem.setLockboxBankSubTotal(setItem(finalCheckLockboxItem
                                    .getPostedCompleteResolved(),
                                    finalCheckLockboxItem.getLockboxBankSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                            setItem(finalCheckLockboxItem.getCheckSubTotal(), null, paymentTransaction);
                        } else if (PaymentTransactionCodes.SUSPENSE_REFUND_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalCheckLockboxItem.setLockboxBankSubTotal(setItem(finalCheckLockboxItem.
                                    getSuspenseRefund(),
                                    finalCheckLockboxItem.getLockboxBankSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                            setItem(finalCheckLockboxItem.getCheckSubTotal(), null, paymentTransaction);
                        } else if (PaymentTransactionCodes.VOLUNTARY_CONTRIBUTIONS_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            finalCheckLockboxItem.setLockboxBankSubTotal(setItem(finalCheckLockboxItem
                                    .getVoluntaryContributions(),
                                    finalCheckLockboxItem.getLockboxBankSubTotal(),
                                    paymentTransaction));
                            response.setFinalPaymentTotal(response.getFinalPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                            setItem(finalCheckLockboxItem.getCheckSubTotal(), null, paymentTransaction);
                        }
                    }
                } else {
                    if (paymentTransaction.getUserInserted() && paymentTransaction.getPayTransPaymentAmount()
                            .doubleValue() > 0) {
                        if (PaymentTransactionCodes.POSTED_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            setItem(pendingCheckOverTheCounterItem.getPostedComplete(),
                                    null, paymentTransaction);
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.POSTED_PENDING.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            setItem(pendingCheckOverTheCounterItem.getPostedPending(),
                                    null, paymentTransaction);
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        }
                    } else if (PaymentTransactionCodes.ADJUSTMENT_PENDING.getCode() == paymentTransaction.getPaymentStatusCode()) {
                        pendingAdjustmentLockboxItem.setAdjustmentSubTotal(setItem(pendingAdjustmentLockboxItem.
                                getPostedPendingApproval(),
                                pendingAdjustmentLockboxItem.getAdjustmentSubTotal(),
                                paymentTransaction));
                        response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                .getPayTransPaymentAmount()));
                    } else if (mainframeImport.getAchFlag() && !mainframeImport.getErrorFlag()) {
                        if (PaymentTransactionCodes.DIRECT_PAY_LIFE_COMPLETE.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            pendingAchLockboxItem.setAchSubTotal(setItem(pendingAchLockboxItem.getDirectPayLife(),
                                    pendingAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.POSTED_PENDING.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            pendingAchLockboxItem.setAchSubTotal(setItem(pendingAchLockboxItem.getPostedPending(),
                                    pendingAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.POSTED_PENDING_APPROVAL.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            pendingAchLockboxItem.setAchSubTotal(setItem(pendingAchLockboxItem.
                                    getPostedPendingResolved(),
                                    pendingAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.SUSPENSE_REFUND_PENDING.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            pendingAchLockboxItem.setAchSubTotal(setItem(pendingAchLockboxItem.getSuspendedRefund(),
                                    pendingAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.SUSPENDED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            pendingAchLockboxItem.setAchSubTotal(setItem(pendingAchLockboxItem.getSuspended(),
                                    pendingAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.UNRESOLVED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            pendingAchLockboxItem.setAchSubTotal(setItem(pendingAchLockboxItem.getUnresolved(),
                                    pendingAchLockboxItem.getAchSubTotal(),
                                    paymentTransaction));
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        }
                    } else if (!mainframeImport.getAchFlag() && !mainframeImport.getErrorFlag()) {
                        if (PaymentTransactionCodes.POSTED_PENDING.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            pendingCheckLockboxItem.setCheckSubTotal(setItem(pendingCheckLockboxItem.getPostedPending(),
                                    pendingCheckLockboxItem.getCheckSubTotal(),
                                    paymentTransaction));
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.SUSPENSE_REFUND_PENDING.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            pendingCheckLockboxItem.setCheckSubTotal(setItem(pendingCheckLockboxItem.getSuspenseRefund(),
                                    pendingCheckLockboxItem.getCheckSubTotal(),
                                    paymentTransaction));
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        } else if (PaymentTransactionCodes.UNRESOLVED.getCode() == paymentTransaction.getPaymentStatusCode()) {
                            pendingCheckLockboxItem.setCheckSubTotal(setItem(pendingCheckLockboxItem.getUnresolved(),
                                    pendingCheckLockboxItem.getCheckSubTotal(),
                                    paymentTransaction));
                            response.setPendingPaymentTotal(response.getPendingPaymentTotal().add(paymentTransaction
                                    .getPayTransPaymentAmount()));
                        }
                    }
                }
            }
            response.setGrandTotal(response.getFinalPaymentTotal().add(response.getPendingPaymentTotal()));
            ReportServiceHelper.setScorecardQuarterDate(response);
            LoggingHelper.logExit(logger, signature, new Object[]{ response });
            return response;
        } catch (PersistenceException e) {
            throw LoggingHelper
                    .logException(
                            logger,
                            signature,
                            new ReportGenerationException(
                                    "An error has occurred when accessing persistence.",
                                    e));
        }
    }

    /**
     * Exports the report for the given request.
     *
     * @param response
     *         the request data to generate report.
     * @param exportType
     *         the type of the report data to generate.
     * @return The byte array of contents of the exported report, can not be null.
     * @throws IllegalArgumentException
     *         if the request/exportType is null.
     * @throws ReportGenerationException
     *         if there is any problem when generating response.
     */
    public byte[] exportReport(BalancedScorecardPaymentReportResponse response, ExportType exportType)
            throws ReportGenerationException {
        String signature = CLASS_NAME
                + "#exportReport(BalancedScorecardPaymentReportResponse response)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{ "response" }, new Object[]{ response });
        Helper.checkNull(logger, signature, response, "response");
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int[] cellWidths = new int[]{ 10, 40, 15, 20, 15 };
            if (exportType == ExportType.RTF || exportType == ExportType.DOC) {
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                RtfWriter2.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addBalancedReportTitle(document, response.getReportName(),
                        response.getFiscalYear(), response.getQuarter(), response.getStartDate(),
                        response.getEndDate());

                int[] dataAlignments = new int[]{
                        ReportServiceHelper.RTF_ALIGN_LEFT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT
                };
                int[] totalAlignments = new int[]{
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT,
                        ReportServiceHelper.RTF_ALIGN_RIGHT
                };
                Table finalItemsTable = new Table(5);
                finalItemsTable.setWidths(cellWidths);
                finalItemsTable.addCell(ReportServiceHelper.createTableCell("Payment processing is Final for the " +
                        "following items", new RtfFont(ReportServiceHelper.REPORT_FONT_FAMILY, 8,
                        RtfFont.BOLD | RtfFont.ITALIC), null, ReportServiceHelper.RTF_ALIGN_CENTER,
                        ReportServiceHelper.RTF_BORDER_TOP, 5));

                int finalPaymentTotalNumber = 0;
                FinalCheckBatchProcessItem finalCheckBatchProcessItem = response.getFinalCheckBatchProcessItem();
                addRow(finalItemsTable, new Object[]{ "Final Check from Batch Process", "Number", "Total" },
                        dataAlignments, true, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckBatchProcessItem.getBatchProcessingTotal().getName(),
                        finalCheckBatchProcessItem.getBatchProcessingTotal().getNumber(),
                        finalCheckBatchProcessItem.getBatchProcessingTotal().getTotal()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        "Batch Process Subtotal",
                        finalCheckBatchProcessItem.getBatchProcessingTotal().getNumber(),
                        finalCheckBatchProcessItem.getBatchProcessSubTotal()
                }, totalAlignments, true, ReportServiceHelper.RTF_NO_BORDER);
                finalPaymentTotalNumber += finalCheckBatchProcessItem.getBatchProcessingTotal().getNumber();

                FinalAchLockboxItem finalAchLockboxItem = response.getFinalAchLockboxItem();
                addRow(finalItemsTable, new Object[]{
                        "Final ACH from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended" }, dataAlignments, true, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getDebitVoucher().getName(),
                        finalAchLockboxItem.getDebitVoucher().getNumber(),
                        finalAchLockboxItem.getDebitVoucher().getTotal(),
                        finalAchLockboxItem.getDebitVoucher().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getDirectPayLife().getName(),
                        finalAchLockboxItem.getDirectPayLife().getNumber(),
                        finalAchLockboxItem.getDirectPayLife().getTotal(),
                        finalAchLockboxItem.getDirectPayLife().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getPostedCompleteResolved().getName(),
                        finalAchLockboxItem.getPostedCompleteResolved().getNumber(),
                        finalAchLockboxItem.getPostedCompleteResolved().getTotal(),
                        finalAchLockboxItem.getPostedCompleteResolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getPostedComplete().getName(),
                        finalAchLockboxItem.getPostedComplete().getNumber(),
                        finalAchLockboxItem.getPostedComplete().getTotal(),
                        finalAchLockboxItem.getPostedComplete().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getSuspenseRefundComplete().getName(),
                        finalAchLockboxItem.getSuspenseRefundComplete().getNumber(),
                        finalAchLockboxItem.getSuspenseRefundComplete().getTotal(),
                        finalAchLockboxItem.getSuspenseRefundComplete().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        "ACH Subtotal",
                        finalAchLockboxItem.getDebitVoucher().getNumber() + finalAchLockboxItem.getDirectPayLife()
                                .getNumber() + finalAchLockboxItem.getPostedCompleteResolved().getNumber() +
                                finalAchLockboxItem.getPostedComplete().getNumber() +
                                finalAchLockboxItem.getSuspenseRefundComplete().getNumber(),
                        finalAchLockboxItem.getAchSubTotal()
                }, totalAlignments, true, ReportServiceHelper.RTF_NO_BORDER);
                finalPaymentTotalNumber += finalAchLockboxItem.getDebitVoucher().getNumber() +
                        finalAchLockboxItem.getDirectPayLife().getNumber() +
                        finalAchLockboxItem.getPostedCompleteResolved().getNumber() +
                        finalAchLockboxItem.getPostedComplete().getNumber() +
                        finalAchLockboxItem.getSuspenseRefundComplete().getNumber();

                FinalCheckLockboxItem finalCheckLockboxItem = response.getFinalCheckLockboxItem();
                addRow(finalItemsTable, new Object[]{
                        "Final Check from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended" }, dataAlignments, true, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getAnnuityComplete().getName(),
                        finalCheckLockboxItem.getAnnuityComplete().getNumber(),
                        finalCheckLockboxItem.getAnnuityComplete().getTotal(),
                        finalCheckLockboxItem.getAnnuityComplete().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getDirectPayLife().getName(),
                        finalCheckLockboxItem.getDirectPayLife().getNumber(),
                        finalCheckLockboxItem.getDirectPayLife().getTotal(),
                        finalCheckLockboxItem.getDirectPayLife().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getPostedCompleteResolved().getName(),
                        finalCheckLockboxItem.getPostedCompleteResolved().getNumber(),
                        finalCheckLockboxItem.getPostedCompleteResolved().getTotal(),
                        finalCheckLockboxItem.getPostedCompleteResolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getPostedComplete().getName(),
                        finalCheckLockboxItem.getPostedComplete().getNumber(),
                        finalCheckLockboxItem.getPostedComplete().getTotal(),
                        finalCheckLockboxItem.getPostedComplete().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getSuspenseRefund().getName(),
                        finalCheckLockboxItem.getSuspenseRefund().getNumber(),
                        finalCheckLockboxItem.getSuspenseRefund().getTotal(),
                        finalCheckLockboxItem.getSuspenseRefund().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getVoluntaryContributions().getName(),
                        finalCheckLockboxItem.getVoluntaryContributions().getNumber(),
                        finalCheckLockboxItem.getVoluntaryContributions().getTotal(),
                        finalCheckLockboxItem.getVoluntaryContributions().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getCheckSubTotal().getName(),
                        finalCheckLockboxItem.getCheckSubTotal().getNumber(),
                        finalCheckLockboxItem.getCheckSubTotal().getTotal()
                }, totalAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        "Lockbox Bank Subtotal",
                        finalCheckLockboxItem.getAnnuityComplete().getNumber() +
                                finalCheckLockboxItem.getDirectPayLife().getNumber() +
                                finalCheckLockboxItem.getPostedCompleteResolved().getNumber() +
                                finalCheckLockboxItem.getPostedComplete().getNumber() +
                                finalCheckLockboxItem.getVoluntaryContributions().getNumber() +
                                finalCheckLockboxItem.getSuspenseRefund().getNumber() +
                                finalCheckLockboxItem.getCheckSubTotal().getNumber(),
                        finalCheckLockboxItem.getLockboxBankSubTotal()
                }, totalAlignments, false, ReportServiceHelper.RTF_NO_BORDER);
                finalPaymentTotalNumber += finalCheckLockboxItem.getAnnuityComplete().getNumber() +
                        finalCheckLockboxItem.getDirectPayLife().getNumber() +
                        finalCheckLockboxItem.getPostedCompleteResolved().getNumber() +
                        finalCheckLockboxItem.getPostedComplete().getNumber() +
                        finalCheckLockboxItem.getVoluntaryContributions().getNumber() +
                        finalCheckLockboxItem.getSuspenseRefund().getNumber() +
                        finalCheckLockboxItem.getCheckSubTotal().getNumber();
                FinalCheckOverTheCounterItem finalCheckOverTheCounterItem = response.getFinalCheckOverTheCounterItem();
                addRow(finalItemsTable, new Object[]{
                        "Final Check from Over-the-counter",
                        "Number",
                        "Total"
                }, dataAlignments, true, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckOverTheCounterItem.getPostedComplete().getName(),
                        finalCheckOverTheCounterItem.getPostedComplete().getNumber(),
                        finalCheckOverTheCounterItem.getPostedComplete().getTotal()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        "Over-the-counter Subtotal",
                        finalCheckOverTheCounterItem.getPostedComplete().getNumber(),
                        finalCheckOverTheCounterItem.getOverTheCounterSubTotal()
                }, totalAlignments, true, ReportServiceHelper.RTF_NO_BORDER);
                finalPaymentTotalNumber += finalCheckOverTheCounterItem.getPostedComplete().getNumber();

                addTotalRow(finalItemsTable, new Object[]{ "Final Payment Totals", finalPaymentTotalNumber,
                        response.getFinalPaymentTotal() }, ReportServiceHelper.RTF_REPORT_HEADER_FONT, 1, 1);
                document.add(finalItemsTable);

                Table pendingItemsTable = new Table(5);
                pendingItemsTable.setWidths(cellWidths);
                pendingItemsTable.addCell(ReportServiceHelper.createTableCell("Payment processing is Pending " +
                        "for the following items", new RtfFont(ReportServiceHelper.REPORT_FONT_FAMILY, 8,
                        RtfFont.BOLD | RtfFont.ITALIC), null, ReportServiceHelper.RTF_ALIGN_CENTER,
                        ReportServiceHelper.RTF_BORDER_TOP, 5));
                int pendingPaymentTotalNumber = 0;
                PendingAchLockboxItem pendingAchLockboxItem = response.getPendingAchLockboxItem();
                addRow(pendingItemsTable, new Object[]{
                        "Pending ACH from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended"
                }, dataAlignments, true, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getDirectPayLife().getName(),
                        pendingAchLockboxItem.getDirectPayLife().getNumber(),
                        pendingAchLockboxItem.getDirectPayLife().getTotal(),
                        pendingAchLockboxItem.getDirectPayLife().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getPostedPendingResolved().getName(),
                        pendingAchLockboxItem.getPostedPendingResolved().getNumber(),
                        pendingAchLockboxItem.getPostedPendingResolved().getTotal(),
                        pendingAchLockboxItem.getPostedPendingResolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getPostedPending().getName(),
                        pendingAchLockboxItem.getPostedPending().getNumber(),
                        pendingAchLockboxItem.getPostedPending().getTotal(),
                        pendingAchLockboxItem.getPostedPending().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getSuspended().getName(),
                        pendingAchLockboxItem.getSuspended().getNumber(),
                        pendingAchLockboxItem.getSuspended().getTotal(),
                        pendingAchLockboxItem.getSuspended().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getSuspendedRefund().getName(),
                        pendingAchLockboxItem.getSuspendedRefund().getNumber(),
                        pendingAchLockboxItem.getSuspendedRefund().getTotal(),
                        pendingAchLockboxItem.getSuspendedRefund().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getUnresolved().getName(),
                        pendingAchLockboxItem.getUnresolved().getNumber(),
                        pendingAchLockboxItem.getUnresolved().getTotal(),
                        pendingAchLockboxItem.getUnresolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        "ACH Subtotal",
                        pendingAchLockboxItem.getDirectPayLife().getNumber() +
                                pendingAchLockboxItem.getPostedPendingResolved().getNumber() +
                                pendingAchLockboxItem.getPostedPending().getNumber() +
                                pendingAchLockboxItem.getSuspended().getNumber() +
                                pendingAchLockboxItem.getSuspendedRefund().getNumber() +
                                pendingAchLockboxItem.getUnresolved().getNumber(),
                        pendingAchLockboxItem.getAchSubTotal()
                }, totalAlignments, true, ReportServiceHelper.RTF_NO_BORDER);
                pendingPaymentTotalNumber += pendingAchLockboxItem.getDirectPayLife().getNumber() +
                        pendingAchLockboxItem.getPostedPendingResolved().getNumber() +
                        pendingAchLockboxItem.getPostedPending().getNumber() +
                        pendingAchLockboxItem.getSuspended().getNumber() +
                        pendingAchLockboxItem.getSuspendedRefund().getNumber() +
                        pendingAchLockboxItem.getUnresolved().getNumber();

                PendingAdjustmentLockboxItem pendingAdjustmentLockboxItem = response.getPendingAdjustmentLockboxItem();
                addRow(pendingItemsTable, new Object[]{
                        "Pending Adjustment from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended"
                }, dataAlignments, true, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getName(),
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getNumber(),
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getTotal(),
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        "Adjustment Subtotal",
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getNumber(),
                        pendingAdjustmentLockboxItem.getAdjustmentSubTotal()
                }, totalAlignments, true, ReportServiceHelper.RTF_NO_BORDER);
                pendingPaymentTotalNumber += pendingAdjustmentLockboxItem.getPostedPendingApproval().getNumber();

                PendingCheckLockboxItem pendingCheckLockboxItem = response.getPendingCheckLockboxItem();
                addRow(pendingItemsTable, new Object[]{
                        "Pending Check from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended"
                }, dataAlignments, true, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingCheckLockboxItem.getPostedPending().getName(),
                        pendingCheckLockboxItem.getPostedPending().getNumber(),
                        pendingCheckLockboxItem.getPostedPending().getTotal(),
                        pendingCheckLockboxItem.getPostedPending().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingCheckLockboxItem.getSuspenseRefund().getName(),
                        pendingCheckLockboxItem.getSuspenseRefund().getNumber(),
                        pendingCheckLockboxItem.getSuspenseRefund().getTotal(),
                        pendingCheckLockboxItem.getSuspenseRefund().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingCheckLockboxItem.getUnresolved().getName(),
                        pendingCheckLockboxItem.getUnresolved().getNumber(),
                        pendingCheckLockboxItem.getUnresolved().getTotal(),
                        pendingCheckLockboxItem.getUnresolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        "Check Subtotal",
                        pendingCheckLockboxItem.getSuspenseRefund().getNumber() +
                                pendingCheckLockboxItem.getUnresolved().getNumber() +
                                pendingCheckLockboxItem.getPostedPending().getNumber(),
                        pendingAchLockboxItem.getAchSubTotal()
                }, totalAlignments, true, ReportServiceHelper.RTF_NO_BORDER);
                pendingPaymentTotalNumber += pendingCheckLockboxItem.getSuspenseRefund().getNumber() +
                        pendingCheckLockboxItem.getUnresolved().getNumber() +
                        pendingCheckLockboxItem.getPostedPending().getNumber();

                PendingCheckOverTheCounterItem pendingCheckOverTheCounterItem = response
                        .getPendingCheckOverTheCounterItem();
                addRow(pendingItemsTable, new Object[]{
                        "Pending Check from Over-the-counter",
                        "Number",
                        "Total"
                }, dataAlignments, true, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingCheckOverTheCounterItem.getPostedPending().getName(),
                        pendingCheckOverTheCounterItem.getPostedPending().getNumber(),
                        pendingCheckOverTheCounterItem.getPostedPending().getTotal()
                }, dataAlignments, false, ReportServiceHelper.RTF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        "Over-the-counter Subtotal",
                        pendingCheckOverTheCounterItem.getPostedPending().getNumber(),
                        pendingAchLockboxItem.getAchSubTotal()
                }, totalAlignments, true, ReportServiceHelper.RTF_NO_BORDER);
                pendingPaymentTotalNumber += pendingCheckOverTheCounterItem.getPostedPending().getNumber();

                addTotalRow(pendingItemsTable, new Object[]{ "Pending Payment Totals", pendingPaymentTotalNumber,
                        response.getPendingPaymentTotal() }, ReportServiceHelper.RTF_REPORT_HEADER_FONT, 1, 1);
                addTotalRow(pendingItemsTable, new Object[]{ "Grand Total", pendingPaymentTotalNumber +
                        finalPaymentTotalNumber },
                        ReportServiceHelper.RTF_REPORT_HEADER_FONT, 1, 1);
                document.add(pendingItemsTable);

                document.close();
            } else {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                ReportServiceHelper.addBalancedReportTitle(document, response.getReportName(),
                        response.getFiscalYear(), response.getQuarter(), response.getStartDate(),
                        response.getEndDate());

                int[] dataAlignments = new int[]{
                        ReportServiceHelper.PDF_ALIGN_LEFT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT
                };
                int[] totalAlignments = new int[]{
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT,
                        ReportServiceHelper.PDF_ALIGN_RIGHT
                };
                PdfPTable finalItemsTable = new PdfPTable(5);
                finalItemsTable.setSpacingBefore(20);
                finalItemsTable.setWidths(cellWidths);
                finalItemsTable.addCell(ReportServiceHelper.createTableCell("Payment processing is Final for the " +
                        "following items", new Font(Font.FontFamily.HELVETICA, 8,
                        Font.BOLD | Font.ITALIC), null, ReportServiceHelper.PDF_ALIGN_CENTER,
                        ReportServiceHelper.PDF_BORDER_TOP, 5));

                int finalPaymentTotalNumber = 0;
                FinalCheckBatchProcessItem finalCheckBatchProcessItem = response.getFinalCheckBatchProcessItem();
                addRow(finalItemsTable, new Object[]{ "Final Check from Batch Process", "Number", "Total" },
                        dataAlignments, true, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckBatchProcessItem.getBatchProcessingTotal().getName(),
                        finalCheckBatchProcessItem.getBatchProcessingTotal().getNumber(),
                        finalCheckBatchProcessItem.getBatchProcessingTotal().getTotal()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        "Batch Process Subtotal",
                        finalCheckBatchProcessItem.getBatchProcessingTotal().getNumber(),
                        finalCheckBatchProcessItem.getBatchProcessSubTotal()
                }, totalAlignments, true, ReportServiceHelper.PDF_NO_BORDER);
                finalPaymentTotalNumber += finalCheckBatchProcessItem.getBatchProcessingTotal().getNumber();

                FinalAchLockboxItem finalAchLockboxItem = response.getFinalAchLockboxItem();
                addRow(finalItemsTable, new Object[]{
                        "Final ACH from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended" }, dataAlignments, true, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getDebitVoucher().getName(),
                        finalAchLockboxItem.getDebitVoucher().getNumber(),
                        finalAchLockboxItem.getDebitVoucher().getTotal(),
                        finalAchLockboxItem.getDebitVoucher().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getDirectPayLife().getName(),
                        finalAchLockboxItem.getDirectPayLife().getNumber(),
                        finalAchLockboxItem.getDirectPayLife().getTotal(),
                        finalAchLockboxItem.getDirectPayLife().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getPostedCompleteResolved().getName(),
                        finalAchLockboxItem.getPostedCompleteResolved().getNumber(),
                        finalAchLockboxItem.getPostedCompleteResolved().getTotal(),
                        finalAchLockboxItem.getPostedCompleteResolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getPostedComplete().getName(),
                        finalAchLockboxItem.getPostedComplete().getNumber(),
                        finalAchLockboxItem.getPostedComplete().getTotal(),
                        finalAchLockboxItem.getPostedComplete().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalAchLockboxItem.getSuspenseRefundComplete().getName(),
                        finalAchLockboxItem.getSuspenseRefundComplete().getNumber(),
                        finalAchLockboxItem.getSuspenseRefundComplete().getTotal(),
                        finalAchLockboxItem.getSuspenseRefundComplete().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        "ACH Subtotal",
                        finalAchLockboxItem.getDebitVoucher().getNumber() + finalAchLockboxItem.getDirectPayLife()
                                .getNumber() + finalAchLockboxItem.getPostedCompleteResolved().getNumber() +
                                finalAchLockboxItem.getPostedComplete().getNumber() +
                                finalAchLockboxItem.getSuspenseRefundComplete().getNumber(),
                        finalAchLockboxItem.getAchSubTotal()
                }, totalAlignments, true, ReportServiceHelper.PDF_NO_BORDER);
                finalPaymentTotalNumber += finalAchLockboxItem.getDebitVoucher().getNumber() +
                        finalAchLockboxItem.getDirectPayLife().getNumber() +
                        finalAchLockboxItem.getPostedCompleteResolved().getNumber() +
                        finalAchLockboxItem.getPostedComplete().getNumber() +
                        finalAchLockboxItem.getSuspenseRefundComplete().getNumber();

                FinalCheckLockboxItem finalCheckLockboxItem = response.getFinalCheckLockboxItem();
                addRow(finalItemsTable, new Object[]{
                        "Final Check from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended" }, dataAlignments, true, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getAnnuityComplete().getName(),
                        finalCheckLockboxItem.getAnnuityComplete().getNumber(),
                        finalCheckLockboxItem.getAnnuityComplete().getTotal(),
                        finalCheckLockboxItem.getAnnuityComplete().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getDirectPayLife().getName(),
                        finalCheckLockboxItem.getDirectPayLife().getNumber(),
                        finalCheckLockboxItem.getDirectPayLife().getTotal(),
                        finalCheckLockboxItem.getDirectPayLife().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getPostedCompleteResolved().getName(),
                        finalCheckLockboxItem.getPostedCompleteResolved().getNumber(),
                        finalCheckLockboxItem.getPostedCompleteResolved().getTotal(),
                        finalCheckLockboxItem.getPostedCompleteResolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getPostedComplete().getName(),
                        finalCheckLockboxItem.getPostedComplete().getNumber(),
                        finalCheckLockboxItem.getPostedComplete().getTotal(),
                        finalCheckLockboxItem.getPostedComplete().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getSuspenseRefund().getName(),
                        finalCheckLockboxItem.getSuspenseRefund().getNumber(),
                        finalCheckLockboxItem.getSuspenseRefund().getTotal(),
                        finalCheckLockboxItem.getSuspenseRefund().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getVoluntaryContributions().getName(),
                        finalCheckLockboxItem.getVoluntaryContributions().getNumber(),
                        finalCheckLockboxItem.getVoluntaryContributions().getTotal(),
                        finalCheckLockboxItem.getVoluntaryContributions().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckLockboxItem.getCheckSubTotal().getName(),
                        finalCheckLockboxItem.getCheckSubTotal().getNumber(),
                        finalCheckLockboxItem.getCheckSubTotal().getTotal()
                }, totalAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        "Lockbox Bank Subtotal",
                        finalCheckLockboxItem.getAnnuityComplete().getNumber() +
                                finalCheckLockboxItem.getDirectPayLife().getNumber() +
                                finalCheckLockboxItem.getPostedCompleteResolved().getNumber() +
                                finalCheckLockboxItem.getPostedComplete().getNumber() +
                                finalCheckLockboxItem.getVoluntaryContributions().getNumber() +
                                finalCheckLockboxItem.getSuspenseRefund().getNumber() +
                                finalCheckLockboxItem.getCheckSubTotal().getNumber(),
                        finalCheckLockboxItem.getLockboxBankSubTotal()
                }, totalAlignments, false, ReportServiceHelper.PDF_NO_BORDER);
                finalPaymentTotalNumber += finalCheckLockboxItem.getAnnuityComplete().getNumber() +
                        finalCheckLockboxItem.getDirectPayLife().getNumber() +
                        finalCheckLockboxItem.getPostedCompleteResolved().getNumber() +
                        finalCheckLockboxItem.getPostedComplete().getNumber() +
                        finalCheckLockboxItem.getVoluntaryContributions().getNumber() +
                        finalCheckLockboxItem.getSuspenseRefund().getNumber() +
                        finalCheckLockboxItem.getCheckSubTotal().getNumber();
                FinalCheckOverTheCounterItem finalCheckOverTheCounterItem = response.getFinalCheckOverTheCounterItem();
                addRow(finalItemsTable, new Object[]{
                        "Final Check from Over-the-counter",
                        "Number",
                        "Total"
                }, dataAlignments, true, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        finalCheckOverTheCounterItem.getPostedComplete().getName(),
                        finalCheckOverTheCounterItem.getPostedComplete().getNumber(),
                        finalCheckOverTheCounterItem.getPostedComplete().getTotal()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(finalItemsTable, new Object[]{
                        "Over-the-counter Subtotal",
                        finalCheckOverTheCounterItem.getPostedComplete().getNumber(),
                        finalCheckOverTheCounterItem.getOverTheCounterSubTotal()
                }, totalAlignments, true, ReportServiceHelper.PDF_NO_BORDER);
                finalPaymentTotalNumber += finalCheckOverTheCounterItem.getPostedComplete().getNumber();

                addTotalRow(finalItemsTable, new Object[]{ "Final Payment Totals", finalPaymentTotalNumber,
                        response.getFinalPaymentTotal() }, ReportServiceHelper.PDF_REPORT_HEADER_FONT, 0.5f, 0.5f);
                document.add(finalItemsTable);

                PdfPTable pendingItemsTable = new PdfPTable(5);
                pendingItemsTable.setSpacingBefore(10);
                pendingItemsTable.setWidths(cellWidths);
                pendingItemsTable.addCell(ReportServiceHelper.createTableCell("Payment processing is Pending " +
                        "for the following items", new Font(Font.FontFamily.HELVETICA, 8,
                        Font.BOLD | Font.ITALIC), null, ReportServiceHelper.PDF_ALIGN_CENTER,
                        ReportServiceHelper.PDF_BORDER_TOP, 5));
                int pendingPaymentTotalNumber = 0;
                PendingAchLockboxItem pendingAchLockboxItem = response.getPendingAchLockboxItem();
                addRow(pendingItemsTable, new Object[]{
                        "Pending ACH from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended"
                }, dataAlignments, true, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getDirectPayLife().getName(),
                        pendingAchLockboxItem.getDirectPayLife().getNumber(),
                        pendingAchLockboxItem.getDirectPayLife().getTotal(),
                        pendingAchLockboxItem.getDirectPayLife().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getPostedPendingResolved().getName(),
                        pendingAchLockboxItem.getPostedPendingResolved().getNumber(),
                        pendingAchLockboxItem.getPostedPendingResolved().getTotal(),
                        pendingAchLockboxItem.getPostedPendingResolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getPostedPending().getName(),
                        pendingAchLockboxItem.getPostedPending().getNumber(),
                        pendingAchLockboxItem.getPostedPending().getTotal(),
                        pendingAchLockboxItem.getPostedPending().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getSuspended().getName(),
                        pendingAchLockboxItem.getSuspended().getNumber(),
                        pendingAchLockboxItem.getSuspended().getTotal(),
                        pendingAchLockboxItem.getSuspended().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getSuspendedRefund().getName(),
                        pendingAchLockboxItem.getSuspendedRefund().getNumber(),
                        pendingAchLockboxItem.getSuspendedRefund().getTotal(),
                        pendingAchLockboxItem.getSuspendedRefund().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAchLockboxItem.getUnresolved().getName(),
                        pendingAchLockboxItem.getUnresolved().getNumber(),
                        pendingAchLockboxItem.getUnresolved().getTotal(),
                        pendingAchLockboxItem.getUnresolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        "ACH Subtotal",
                        pendingAchLockboxItem.getDirectPayLife().getNumber() +
                                pendingAchLockboxItem.getPostedPendingResolved().getNumber() +
                                pendingAchLockboxItem.getPostedPending().getNumber() +
                                pendingAchLockboxItem.getSuspended().getNumber() +
                                pendingAchLockboxItem.getSuspendedRefund().getNumber() +
                                pendingAchLockboxItem.getUnresolved().getNumber(),
                        pendingAchLockboxItem.getAchSubTotal()
                }, totalAlignments, true, ReportServiceHelper.PDF_NO_BORDER);
                pendingPaymentTotalNumber += pendingAchLockboxItem.getDirectPayLife().getNumber() +
                        pendingAchLockboxItem.getPostedPendingResolved().getNumber() +
                        pendingAchLockboxItem.getPostedPending().getNumber() +
                        pendingAchLockboxItem.getSuspended().getNumber() +
                        pendingAchLockboxItem.getSuspendedRefund().getNumber() +
                        pendingAchLockboxItem.getUnresolved().getNumber();

                PendingAdjustmentLockboxItem pendingAdjustmentLockboxItem = response.getPendingAdjustmentLockboxItem();
                addRow(pendingItemsTable, new Object[]{
                        "Pending Adjustment from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended"
                }, dataAlignments, true, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getName(),
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getNumber(),
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getTotal(),
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        "Adjustment Subtotal",
                        pendingAdjustmentLockboxItem.getPostedPendingApproval().getNumber(),
                        pendingAdjustmentLockboxItem.getAdjustmentSubTotal()
                }, totalAlignments, true, ReportServiceHelper.PDF_NO_BORDER);
                pendingPaymentTotalNumber += pendingAdjustmentLockboxItem.getPostedPendingApproval().getNumber();

                PendingCheckLockboxItem pendingCheckLockboxItem = response.getPendingCheckLockboxItem();
                addRow(pendingItemsTable, new Object[]{
                        "Pending Check from Lockbox Bank",
                        "Number",
                        "Total",
                        "Suspended"
                }, dataAlignments, true, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingCheckLockboxItem.getPostedPending().getName(),
                        pendingCheckLockboxItem.getPostedPending().getNumber(),
                        pendingCheckLockboxItem.getPostedPending().getTotal(),
                        pendingCheckLockboxItem.getPostedPending().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingCheckLockboxItem.getSuspenseRefund().getName(),
                        pendingCheckLockboxItem.getSuspenseRefund().getNumber(),
                        pendingCheckLockboxItem.getSuspenseRefund().getTotal(),
                        pendingCheckLockboxItem.getSuspenseRefund().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingCheckLockboxItem.getUnresolved().getName(),
                        pendingCheckLockboxItem.getUnresolved().getNumber(),
                        pendingCheckLockboxItem.getUnresolved().getTotal(),
                        pendingCheckLockboxItem.getUnresolved().getSuspended()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        "Check Subtotal",
                        pendingCheckLockboxItem.getSuspenseRefund().getNumber() +
                                pendingCheckLockboxItem.getUnresolved().getNumber() +
                                pendingCheckLockboxItem.getPostedPending().getNumber(),
                        pendingAchLockboxItem.getAchSubTotal()
                }, totalAlignments, true, ReportServiceHelper.PDF_NO_BORDER);
                pendingPaymentTotalNumber += pendingCheckLockboxItem.getSuspenseRefund().getNumber() +
                        pendingCheckLockboxItem.getUnresolved().getNumber() +
                        pendingCheckLockboxItem.getPostedPending().getNumber();

                PendingCheckOverTheCounterItem pendingCheckOverTheCounterItem = response
                        .getPendingCheckOverTheCounterItem();
                addRow(pendingItemsTable, new Object[]{
                        "Pending Check from Over-the-counter",
                        "Number",
                        "Total"
                }, dataAlignments, true, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        pendingCheckOverTheCounterItem.getPostedPending().getName(),
                        pendingCheckOverTheCounterItem.getPostedPending().getNumber(),
                        pendingCheckOverTheCounterItem.getPostedPending().getTotal()
                }, dataAlignments, false, ReportServiceHelper.PDF_BORDER_BOTTOM);
                addRow(pendingItemsTable, new Object[]{
                        "Over-the-counter Subtotal",
                        pendingCheckOverTheCounterItem.getPostedPending().getNumber(),
                        pendingAchLockboxItem.getAchSubTotal()
                }, totalAlignments, true, ReportServiceHelper.PDF_NO_BORDER);
                pendingPaymentTotalNumber += pendingCheckOverTheCounterItem.getPostedPending().getNumber();

                addTotalRow(pendingItemsTable, new Object[]{ "Pending Payment Totals", pendingPaymentTotalNumber,
                        response.getPendingPaymentTotal() }, ReportServiceHelper.PDF_REPORT_HEADER_FONT, 0.5f, 0f);
                addTotalRow(pendingItemsTable, new Object[]{ "Grand Total", pendingPaymentTotalNumber +
                        finalPaymentTotalNumber },
                        ReportServiceHelper.PDF_REPORT_HEADER_FONT, 0.5f, 0.5f);
                document.add(pendingItemsTable);

                document.close();
            }

            LoggingHelper.logExit(getLogger(), signature, null);
            return outputStream.toByteArray();
        } catch (com.lowagie.text.DocumentException ex) {
            throw LoggingHelper.logException(getLogger(), signature,
                    new ReportGenerationException(
                            "Error occurred while exporting the report.", ex));
        } catch (com.itextpdf.text.DocumentException ex) {
            throw LoggingHelper.logException(getLogger(), signature,
                    new ReportGenerationException(
                            "Error occurred while exporting the report.", ex));
        }
    }

    /**
     * Creates a SimpleBalancedItem.
     *
     * @param name
     *         the name.
     * @param suspended
     *         the suspended.
     * @return the created SimpleBalancedItem.
     */
    private static SimpleBalancedItem createItem(String name, String suspended) {
        SimpleBalancedItem item = new SimpleBalancedItem();
        item.setName(name);
        item.setNumber(0);
        item.setTotal(new BigDecimal(0));
        item.setSuspended(suspended);
        return item;
    }

    /**
     * Sets the SimpleBalancedItem.
     *
     * @param item
     *         the SimpleBalancedItem to set.
     * @param total
     *         the original total.
     * @param paymentTransaction
     *         the PaymentTransaction.
     * @return the counted total.
     */
    private static BigDecimal setItem(SimpleBalancedItem item, BigDecimal total, PaymentTransaction paymentTransaction) {
        item.setTotal(item.getTotal().add(paymentTransaction.getPayTransPaymentAmount()));
        item.setNumber(item.getNumber() + 1);
        if (total != null) {
            total = total.add(paymentTransaction.getPayTransPaymentAmount());
        }
        return total;
    }

    /**
     * Adds a row to the table.
     *
     * @param table
     *         the table.
     * @param contents
     *         the columns contents.
     * @param alignments
     *         the alignments.
     * @param header
     *         the header flag.
     * @param border
     *         the border.
     */
    private static void addRow(Table table, Object[] contents, int[] alignments, boolean header, int border) {
        table.addCell(ReportServiceHelper.createEmptyCell(1, ReportServiceHelper.RTF_NO_BORDER));
        ReportServiceHelper.addReportTableRow(table, contents, header ? ReportServiceHelper.RTF_REPORT_HEADER_FONT :
                ReportServiceHelper.RTF_REPORT_CONTENT_FONT, null, alignments, border);
        if (contents.length == 3) {
            table.addCell(ReportServiceHelper.createEmptyCell(1, ReportServiceHelper.RTF_NO_BORDER));
        }
    }

    /**
     * Adds a row to the table.
     *
     * @param table
     *         the table.
     * @param contents
     *         the columns contents.
     * @param alignments
     *         the alignments.
     * @param header
     *         the header flag.
     * @param border
     *         the border.
     */
    private static void addRow(PdfPTable table, Object[] contents, int[] alignments, boolean header, int border) {
        table.addCell(ReportServiceHelper.createEmptyPdfCell(1, ReportServiceHelper.PDF_NO_BORDER));
        ReportServiceHelper.addReportTableRow(table, contents, header ? ReportServiceHelper.PDF_REPORT_HEADER_FONT :
                ReportServiceHelper.PDF_REPORT_CONTENT_FONT, null, alignments, border);
        if (contents.length == 3) {
            table.addCell(ReportServiceHelper.createEmptyPdfCell(1, ReportServiceHelper.PDF_NO_BORDER));
        }
    }

    /**
     * Adds a total row to the table.
     *
     * @param table
     *         the table.
     * @param contents
     *         the columns contents.
     * @param font
     *         the font to use.
     * @param borderTop
     *         the border top width.
     * @param borderBottom
     *         the border bottom width.
     */
    private static void addTotalRow(Table table, Object[] contents, RtfFont font, int borderTop,
                                    int borderBottom) {
        Cell labelCell = ReportServiceHelper.createTableCell(contents[0], font, null,
                ReportServiceHelper.RTF_ALIGN_RIGHT, null, 2);
        labelCell.setBorderWidthTop(borderTop);
        labelCell.setBorderWidthBottom(borderBottom);
        table.addCell(labelCell);

        Cell numberCell = ReportServiceHelper.createTableCell(contents[1], font, null,
                ReportServiceHelper.RTF_ALIGN_RIGHT, null, 1);
        numberCell.setBorderWidthTop(borderTop);
        numberCell.setBorderWidthBottom(borderBottom);
        table.addCell(numberCell);

        Cell totalCell = ReportServiceHelper.createTableCell(contents.length == 3 ? contents[2] : null, font, null,
                ReportServiceHelper.RTF_ALIGN_RIGHT, null, 1);
        totalCell.setBorderWidthTop(borderTop);
        totalCell.setBorderWidthBottom(borderBottom);
        table.addCell(totalCell);

        Cell emptyCell = ReportServiceHelper.createEmptyCell(1, null);
        emptyCell.setBorderWidthTop(borderTop);
        emptyCell.setBorderWidthBottom(borderBottom);
        table.addCell(emptyCell);
    }

    /**
     * Adds a total row to the table.
     *
     * @param table
     *         the table.
     * @param contents
     *         the columns contents.
     * @param font
     *         the font to use.
     * @param borderTop
     *         the border top width.
     * @param borderBottom
     *         the border bottom width.
     */
    private static void addTotalRow(PdfPTable table, Object[] contents, Font font, float borderTop,
                                    float borderBottom) {
        PdfPCell labelCell = ReportServiceHelper.createTableCell(contents[0], font, null,
                ReportServiceHelper.PDF_ALIGN_RIGHT, null, 2);
        labelCell.setBorderWidth(0);
        labelCell.setBorderWidthTop(borderTop);
        labelCell.setBorderWidthBottom(borderBottom);
        table.addCell(labelCell);

        PdfPCell numberCell = ReportServiceHelper.createTableCell(contents[1], font, null,
                ReportServiceHelper.PDF_ALIGN_RIGHT, null, 1);
        numberCell.setBorderWidth(0);
        numberCell.setBorderWidthTop(borderTop);
        numberCell.setBorderWidthBottom(borderBottom);
        table.addCell(numberCell);

        PdfPCell totalCell = ReportServiceHelper.createTableCell(contents.length == 3 ? contents[2] : null, font, null,
                ReportServiceHelper.PDF_ALIGN_RIGHT, null, 1);
        totalCell.setBorderWidth(0);
        totalCell.setBorderWidthTop(borderTop);
        totalCell.setBorderWidthBottom(borderBottom);
        table.addCell(totalCell);

        PdfPCell emptyCell = ReportServiceHelper.createEmptyPdfCell(1, null);
        emptyCell.setBorderWidth(0);
        emptyCell.setBorderWidthTop(borderTop);
        emptyCell.setBorderWidthBottom(borderBottom);
        table.addCell(emptyCell);
    }

    /**
     * Renders the chart image.
     * 
     * @param response the service response for rendering.
     * @return the byte array of the image.
     * @throws ReportGenerationException if there are any error.
     */
    @Override
    public byte[] renderChart(BalancedScorecardPaymentReportResponse response) throws ReportGenerationException {
        return null;
    }
}
