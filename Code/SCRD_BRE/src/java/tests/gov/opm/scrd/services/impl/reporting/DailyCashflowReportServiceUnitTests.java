/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link DailyCashflowReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class DailyCashflowReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the DailyCashflowReportService instance.
     */
    private DailyCashflowReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(DailyCashflowReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new DailyCashflowReportService();
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
        DailyCashflowReportResponse response = service.getReport(new DailyCashflowReportRequest());
        assertEquals("The reportname is incorrect.", response.getReportName(),
                DailyCashflowReportService.class.getSimpleName());
        assertTrue("The lockbox items count is incorrect.", response.getLockboxItems().size() == 4);
        assertTrue("The payment items count is incorrect.", response.getPaymentItems().size() == 7);
        for (DailyCashflowLockboxItem item : response.getLockboxItems()) {
            if (item.getLockboxBankImportFile().equals("Payments in Error")) {
                assertTrue("The allNumber is incorrect.", item.getAllNumber() == 5);
                assertEquals("The allSum is incorrect.", item.getAllSum().setScale(2).toString(), "500.00");
                assertTrue("The achNumber is incorrect.", item.getAchNumber() == 0);
                assertEquals("The achSum is incorrect.", item.getAchSum().toString(), "0");
                assertTrue("The checkNumber is incorrect.", item.getCheckNumber() == 0);
                assertEquals("The checkSum is incorrect.", item.getCheckSum().toString(), "0");
            } else if (item.getLockboxBankImportFile().equals("Payments Posted")) {
                assertTrue("The allNumber is incorrect.", item.getAllNumber() == 3);
                assertEquals("The allSum is incorrect.", item.getAllSum().setScale(2).toString(), "300.00");
                assertTrue("The achNumber is incorrect.", item.getAchNumber() == 1);
                assertEquals("The achSum is incorrect.", item.getAchSum().setScale(2).toString(), "100.00");
                assertTrue("The checkNumber is incorrect.", item.getCheckNumber() == 1);
                assertEquals("The checkSum is incorrect.", item.getCheckSum().setScale(2).toString(), "100.00");
            } else if (item.getLockboxBankImportFile().equals("Payments Unresolved")) {
                assertTrue("The allNumber is incorrect.", item.getAllNumber() == 3);
                assertEquals("The allSum is incorrect.", item.getAllSum().setScale(2).toString(), "300.00");
                assertTrue("The achNumber is incorrect.", item.getAchNumber() == 1);
                assertEquals("The achSum is incorrect.", item.getAchSum().setScale(2).toString(), "100.00");
                assertTrue("The checkNumber is incorrect.", item.getCheckNumber() == 1);
                assertEquals("The checkSum is incorrect.", item.getCheckSum().setScale(2).toString(), "100.00");
            } else if (item.getLockboxBankImportFile().equals("Payments Suspended")) {
                assertTrue("The allNumber is incorrect.", item.getAllNumber() == 3);
                assertEquals("The allSum is incorrect.", item.getAllSum().setScale(2).toString(), "300.00");
                assertTrue("The achNumber is incorrect.", item.getAchNumber() == 1);
                assertEquals("The achSum is incorrect.", item.getAchSum().setScale(2).toString(), "100.00");
                assertTrue("The checkNumber is incorrect.", item.getCheckNumber() == 1);
                assertEquals("The checkSum is incorrect.", item.getCheckSum().setScale(2).toString(), "100.00");
            } else {
                fail("The lockboxBankImportFile is incorrect.");
            }
        }

        for (DailyCashflowPaymentItem item : response.getPaymentItems()) {
            if (item.getName().equals("Old Suspense resolved") || item.getName().equals("Reversals")) {
                assertEquals("The amount is incorrect.", item.getAmount().setScale(2).toString(), "300.00");
                assertTrue("The number is incorrect.", item.getNumber() == 3);
            } else if (item.getName().equals("DPLI Transfer") ||
                    item.getName().equals("Voluntary Contrib Transfer") ||
                    item.getName().equals("Balance Refund") ||
                    item.getName().equals("Auto Refund") ||
                    item.getName().equals("Suspense Fund")) {
                assertEquals("The amount is incorrect.", item.getAmount().setScale(2).toString(), "100.00");
                assertTrue("The number is incorrect.", item.getNumber() == 1);
            } else {
                fail("The name is incorrect.");
            }
        }
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
     * Creates a DailyCashflowReportResponse instance for test.
     *
     * @return the DailyCashflowReportResponse instance.
     */
    private static DailyCashflowReportResponse createResponse() {
        DailyCashflowReportResponse response = new DailyCashflowReportResponse();
        response.setReportName("Daily Cashflow Report");
        response.setReportGenerationDate(new Date());
        response.setLockboxItems(new ArrayList<DailyCashflowLockboxItem>());
        response.getLockboxItems().add(createLockboxItem());
        response.getLockboxItems().add(createLockboxItem());
        response.getLockboxItems().add(createLockboxItem());
        response.setPaymentItems(new ArrayList<DailyCashflowPaymentItem>());
        response.getPaymentItems().add(createPaymentItem());
        response.getPaymentItems().add(createPaymentItem());
        response.getPaymentItems().add(createPaymentItem());
        return response;
    }

    /**
     * Creates a DailyCashflowLockboxItem instance for test.
     *
     * @return the DailyCashflowLockboxItem instance.
     */
    private static DailyCashflowLockboxItem createLockboxItem() {
        DailyCashflowLockboxItem item = new DailyCashflowLockboxItem();
        item.setAchNumber(100);
        item.setAchSum(new BigDecimal("1000.00"));
        item.setCheckNumber(200);
        item.setCheckSum(new BigDecimal("2000.00"));
        item.setAllNumber(300);
        item.setAllSum(new BigDecimal("3000.00"));
        item.setLockboxBankImportFile("File Name");
        return item;
    }

    /**
     * Creates a DailyCashflowPaymentItem instance for test.
     *
     * @return the DailyCashflowPaymentItem instance.
     */
    private static DailyCashflowPaymentItem createPaymentItem() {
        DailyCashflowPaymentItem item = new DailyCashflowPaymentItem();
        item.setName("Payment Name");
        item.setNumber(10);
        item.setAmount(new BigDecimal("1000.00"));
        return item;
    }
}
