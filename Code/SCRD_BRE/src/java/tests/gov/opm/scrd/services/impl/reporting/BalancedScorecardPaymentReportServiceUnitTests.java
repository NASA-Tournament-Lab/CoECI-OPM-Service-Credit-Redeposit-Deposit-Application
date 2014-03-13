/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link BalancedScorecardPaymentReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class BalancedScorecardPaymentReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the BalancedScorecardAccountReportService instance.
     */
    private BalancedScorecardPaymentReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BalancedScorecardPaymentReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new BalancedScorecardPaymentReportService();
        TestsHelper.initService(service, getEntityManager());
        createDailyReportData();
    }

    /**
     * Tests the getReport method with <code>null</code> request.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetReportWithNull() throws ReportGenerationException {
        service.getReport(null);
    }

    /**
     * Tests the getReport method.
     */
    @Test
    public void testGetReport() throws ReportGenerationException {
        BalancedScorecardPaymentReportResponse response = service.getReport(
                new BalancedScorecardPaymentReportRequest());
        assertEquals("The reportname is incorrect.", response.getReportName(),
                BalancedScorecardPaymentReportService.class.getSimpleName());
        assertItem(response.getFinalCheckLockboxItem().getSuspenseRefund(), 0, "0.00", "Resolved");
        assertItem(response.getFinalCheckLockboxItem().getCheckSubTotal(), 0, "0.00", "Resolved");
        assertItem(response.getFinalCheckLockboxItem().getVoluntaryContributions(), 0, "0", "Resolved");
        assertItem(response.getFinalCheckLockboxItem().getPostedComplete(), 0, "0.00", "Resolved");
        assertItem(response.getFinalCheckLockboxItem().getAnnuityComplete(), 0, "0.00", "Resolved");
        assertItem(response.getFinalCheckLockboxItem().getPostedCompleteResolved(), 0, "0.00", "Resolved");
        assertEquals(response.getFinalCheckLockboxItem().getLockboxBankSubTotal().setScale(2).toString(), "0.00");
        assertItem(response.getFinalCheckBatchProcessItem().getBatchProcessingTotal(), 2, "200.00", "Resolved");
        assertEquals(response.getFinalCheckBatchProcessItem().getBatchProcessSubTotal().setScale(2).toString(), "200.00");
        assertItem(response.getFinalAchLockboxItem().getSuspenseRefundComplete(), 0, "0.00", "Resolved");
        assertItem(response.getFinalAchLockboxItem().getPostedCompleteResolved(), 0, "0.00", "Resolved");
        assertItem(response.getFinalAchLockboxItem().getPostedComplete(), 0, "0", "Resolved");
        assertItem(response.getFinalAchLockboxItem().getDebitVoucher(), 0, "0", "Resolved");
        assertItem(response.getFinalAchLockboxItem().getDirectPayLife(), 0, "0", "Resolved");
        assertEquals(response.getFinalAchLockboxItem().getAchSubTotal().toString(), "0");
        assertItem(response.getFinalCheckOverTheCounterItem().getPostedComplete(), 3, "300.00", "Resolved");
        assertEquals(response.getFinalCheckOverTheCounterItem().getOverTheCounterSubTotal().setScale(2).toString(), "300.00");
        assertItem(response.getPendingAchLockboxItem().getUnresolved(), 2, "200.00", "Suspense");
        assertItem(response.getPendingAchLockboxItem().getDirectPayLife(), 0, "0", "Suspense");
        assertItem(response.getPendingAchLockboxItem().getSuspended(), 2, "200.00", "Suspense");
        assertItem(response.getPendingAchLockboxItem().getSuspendedRefund(), 0, "0", "Suspense");
        assertItem(response.getPendingAchLockboxItem().getPostedPendingResolved(), 0, "0", "Suspense");
        assertEquals(response.getPendingAchLockboxItem().getAchSubTotal().setScale(2).toString(), "400.00");
        assertItem(response.getPendingCheckLockboxItem().getUnresolved(), 1, "100.00", "Suspense");
        assertItem(response.getPendingCheckLockboxItem().getPostedPending(), 0, "0", "Suspense");
        assertItem(response.getPendingCheckLockboxItem().getSuspenseRefund(), 0, "0", "Suspense");
        assertEquals(response.getPendingCheckLockboxItem().getCheckSubTotal().setScale(2).toString(), "100.00");
        assertItem(response.getPendingAdjustmentLockboxItem().getPostedPendingApproval(), 0, "0", "Suspense");
        assertEquals(response.getPendingAdjustmentLockboxItem().getAdjustmentSubTotal().toString(), "0");
        assertItem(response.getPendingCheckOverTheCounterItem().getPostedPending(), 0, "0", "Suspense");
        assertItem(response.getPendingCheckOverTheCounterItem().getPostedComplete(), 0, "0", "Suspense");
        assertEquals(response.getFinalPaymentTotal().setScale(2).toString(), "500.00");
        assertEquals(response.getPendingPaymentTotal().setScale(2).toString(), "500.00");
        assertEquals(response.getGrandTotal().setScale(2).toString(), "1000.00");
    }

    /**
     * Tests the exportReport method with RTF file type.
     */
    @Test
    public void testExportReportRTF() throws ReportGenerationException,
            IOException, ParseException {
        TestsHelper.exportReport(service, createResponse(), ExportType.RTF);
    }

    /**
     * Tests the exportReport method with PDF file type.
     */
    @Test
    public void testExportReportPDF() throws ReportGenerationException,
            IOException {
        TestsHelper.exportReport(service, createResponse(), ExportType.PDF);
    }

    /**
     * Asserts the field values of the SimpleBalancedItem are expected.
     *
     * @param item
     *         the SimpleBalancedItem.
     * @param number
     *         the expected number.
     * @param total
     *         the expected total.
     * @param suspended
     *         the expected suspended.
     */
    private static void assertItem(SimpleBalancedItem item, int number, String total, String suspended) {
        assertTrue("The item number is incorrect.", number == item.getNumber());
        assertEquals("The item total is incorrect.", Double.parseDouble(total), item.getTotal().doubleValue(), 1e-6);
        assertEquals("The item suspended is incorrect.", suspended, item.getSuspended());
    }

    /**
     * Creates a BalancedScorecardPaymentReportResponse instance for test.
     *
     * @return the BalancedScorecardPaymentReportResponse instance.
     */
    private static BalancedScorecardPaymentReportResponse createResponse() {
        BalancedScorecardPaymentReportResponse response = new BalancedScorecardPaymentReportResponse();
        response.setReportName("Balanced Scorecard Payment Processing");
        response.setReportGenerationDate(new Date());
        response.setFiscalYear(2014);
        response.setQuarter(1);
        response.setStartDate(new Date());
        response.setEndDate(new Date());

        response.setFinalCheckBatchProcessItem(new FinalCheckBatchProcessItem());
        response.getFinalCheckBatchProcessItem().setBatchProcessingTotal(createItem());
        response.getFinalCheckBatchProcessItem().setBatchProcessSubTotal(new BigDecimal("1000.00"));

        response.setFinalAchLockboxItem(new FinalAchLockboxItem());
        response.getFinalAchLockboxItem().setDebitVoucher(createItem());
        response.getFinalAchLockboxItem().setDirectPayLife(createItem());
        response.getFinalAchLockboxItem().setPostedComplete(createItem());
        response.getFinalAchLockboxItem().setPostedCompleteResolved(createItem());
        response.getFinalAchLockboxItem().setSuspenseRefundComplete(createItem());
        response.getFinalAchLockboxItem().setAchSubTotal(new BigDecimal("5000.00"));

        response.setFinalCheckLockboxItem(new FinalCheckLockboxItem());
        response.getFinalCheckLockboxItem().setAnnuityComplete(createItem());
        response.getFinalCheckLockboxItem().setDirectPayLife(createItem());
        response.getFinalCheckLockboxItem().setPostedCompleteResolved(createItem());
        response.getFinalCheckLockboxItem().setPostedComplete(createItem());
        response.getFinalCheckLockboxItem().setSuspenseRefund(createItem());
        response.getFinalCheckLockboxItem().setVoluntaryContributions(createItem());
        response.getFinalCheckLockboxItem().setCheckSubTotal(createItem());
        response.getFinalCheckLockboxItem().setLockboxBankSubTotal(new BigDecimal("7000.00"));

        response.setFinalCheckOverTheCounterItem(new FinalCheckOverTheCounterItem());
        response.getFinalCheckOverTheCounterItem().setPostedComplete(createItem());
        response.getFinalCheckOverTheCounterItem().setOverTheCounterSubTotal(new BigDecimal("1000.00"));

        response.setFinalPaymentTotal(new BigDecimal("1400.00"));

        response.setPendingAchLockboxItem(new PendingAchLockboxItem());
        response.getPendingAchLockboxItem().setDirectPayLife(createItem());
        response.getPendingAchLockboxItem().setPostedPending(createItem());
        response.getPendingAchLockboxItem().setPostedPendingResolved(createItem());
        response.getPendingAchLockboxItem().setSuspended(createItem());
        response.getPendingAchLockboxItem().setSuspendedRefund(createItem());
        response.getPendingAchLockboxItem().setUnresolved(createItem());
        response.getPendingAchLockboxItem().setAchSubTotal(new BigDecimal("6000.00"));

        response.setPendingAdjustmentLockboxItem(new PendingAdjustmentLockboxItem());
        response.getPendingAdjustmentLockboxItem().setPostedPendingApproval(createItem());
        response.getPendingAdjustmentLockboxItem().setAdjustmentSubTotal(new BigDecimal("1000.00"));

        response.setPendingCheckLockboxItem(new PendingCheckLockboxItem());
        response.getPendingCheckLockboxItem().setPostedPending(createItem());
        response.getPendingCheckLockboxItem().setSuspenseRefund(createItem());
        response.getPendingCheckLockboxItem().setUnresolved(createItem());
        response.getPendingCheckLockboxItem().setCheckSubTotal(new BigDecimal("1000.00"));

        response.setPendingCheckOverTheCounterItem(new PendingCheckOverTheCounterItem());
        response.getPendingCheckOverTheCounterItem().setPostedPending(createItem());
        response.getPendingCheckOverTheCounterItem().setPostedComplete(createItem());

        response.setPendingPaymentTotal(new BigDecimal("8000.00"));
        response.setGrandTotal(new BigDecimal("22000.00"));
        return response;
    }

    /**
     * Creates a SimpleBalancedItem instance for test.
     *
     * @return the SimpleBalancedItem instance.
     */
    private static SimpleBalancedItem createItem() {
        SimpleBalancedItem item = new SimpleBalancedItem();
        item.setName("Item Name");
        item.setNumber(1000);
        item.setSuspended("Suspended");
        item.setTotal(new BigDecimal("1000.00"));
        return item;
    }
}
