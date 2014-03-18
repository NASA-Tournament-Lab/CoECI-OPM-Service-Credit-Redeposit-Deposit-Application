/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link AccountSummaryReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class AccountSummaryReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the AccountSummaryReportService instance.
     */
    private AccountSummaryReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AccountSummaryReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new AccountSummaryReportService();
        TestsHelper.initService(service, getEntityManager());
        service.setReceiptPaymentTypeId(1L);
        service.setReplacementAdjustmentPaymentTypeId(2L);
        service.setDebitVoucherAdjustmentPaymentTypeId(3L);
        super.createAccountReportData();
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
    public void testGerReport() throws ReportGenerationException {
        AccountSummaryReportRequest request = new AccountSummaryReportRequest();
        request.setCsd("1");
        AccountSummaryReportResponse response = service.getReport(request);
        assertEquals("The reportname is incorrect.", response.getReportName(),
                AccountSummaryReportService.class.getSimpleName());
        assertEquals("The receipts is incorrect.", response.getReceipts().setScale(2).toString(), "200.00");
        assertEquals("The suspense is incorrect.", response.getSuspense().setScale(2).toString(), "100.00");
        assertEquals("The replacedAccounts is incorrect.", response.getReplacedAccounts().setScale(2).toString(), "50.00");
        assertEquals("The adjustmentPlus is incorrect.", response.getAdjustmentPlus().setScale(2).toString(), "0.00");
        assertEquals("The debitVouchers is incorrect.", response.getDebitVouchers().setScale(2).toString(), "50.00");
        assertEquals("The adjustmentMinus is incorrect.", response.getAdjustmentMinus().setScale(2).toString(), "40.00");
        assertEquals("The totalReceipts is incorrect.", response.getTotalReceipts().setScale(2).toString(), "300.00");
        assertEquals("The totalAdditions is incorrect.", response.getTotalAdditions().setScale(2).toString(), "50.00");
        assertEquals("The totalDeductions is incorrect.", response.getTotalDeductions().setScale(2).toString(), "90.00");
        assertEquals("The netChange is incorrect.", response.getNetChange().setScale(2).toString(), "260.00");
    }

    /**
     * Tests the exportReport method with RTF file type.
     */
    @Test
    public void testExportReportRTF() throws ReportGenerationException,
            IOException {
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
     * Creates a AccountSummaryReportResponse instance for test.
     *
     * @return the AccountSummaryReportResponse instance.
     */
    private static AccountSummaryReportResponse createResponse() {
        AccountSummaryReportResponse response = new AccountSummaryReportResponse();
        BigDecimal receipts = new BigDecimal("2000.00");
        BigDecimal suspense = new BigDecimal("1000.00");
        BigDecimal totalReceipts = receipts.add(suspense);
        BigDecimal replacedAccounts = new BigDecimal("1000.00");
        BigDecimal adjustmentPlus = new BigDecimal("1000.00");
        BigDecimal totalAdditions = replacedAccounts.add(adjustmentPlus);
        BigDecimal debitVouchers = new BigDecimal("1000.00");
        BigDecimal adjustmentMinus = new BigDecimal("1000.00");
        BigDecimal totalDeductions = debitVouchers.add(adjustmentMinus);
        BigDecimal netChange = totalReceipts.add(totalAdditions).subtract(totalDeductions);

        response.setReceipts(receipts);
        response.setSuspense(suspense);
        response.setTotalReceipts(totalReceipts);
        response.setReplacedAccounts(replacedAccounts);
        response.setAdjustmentPlus(adjustmentPlus);
        response.setTotalAdditions(totalAdditions);
        response.setDebitVouchers(debitVouchers);
        response.setAdjustmentMinus(adjustmentMinus);
        response.setTotalDeductions(totalDeductions);
        response.setNetChange(netChange);
        response.setReportGenerationDate(new Date());
        response.setReportName("Accounting Summary Report");
        return response;
    }
}
