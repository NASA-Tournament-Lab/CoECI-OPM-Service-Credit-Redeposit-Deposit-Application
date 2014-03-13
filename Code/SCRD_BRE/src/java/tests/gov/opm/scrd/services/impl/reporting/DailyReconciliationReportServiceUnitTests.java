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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link DailyReconciliationReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class DailyReconciliationReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the DailyReconciliationReportService instance.
     */
    private DailyReconciliationReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(DailyReconciliationReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new DailyReconciliationReportService();
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
        DailyReconciliationReportResponse response = service.getReport(new DailyReconciliationReportRequest());
        assertEquals("The reportname is incorrect.", response.getReportName(),
                DailyReconciliationReportService.class.getSimpleName());
        assertTrue("The items count is incorrect.", response.getItems().size() == 4);
        for (DailyReconciliationReportResponseItem item : response.getItems()) {
            if (item.getName().equals("Lockbox Total")) {
                assertTrue("The lockbox items is incorrect.", item.getItems() == 20);
                assertTrue("The lockbox receipts is incorrect.", item.getReceipts() == 9);
                assertTrue("The lockbox suspended is incorrect.", item.getSuspended() == 3);
                assertTrue("The lockbox reversed is incorrect.", item.getReversed() == 3);
                assertTrue("The lockbox processed is incorrect.", item.getProcessed() == 3);
            } else if (item.getName().equals("ACH Total")) {
                assertTrue("The ach items is incorrect.", item.getItems() == 5);
                assertTrue("The ach processed is incorrect.", item.getProcessed() == 1);
                assertTrue("The ach suspended is incorrect.", item.getSuspended() == 1);
                assertTrue("The ach reversed is incorrect.", item.getReversed() == 1);
                assertTrue("The ach receipts is incorrect.", item.getReceipts() == 3);
            } else if (item.getName().equals("Check Total")) {
                assertTrue("The check items is incorrect.", item.getItems() == 5);
                assertTrue("The check processed is incorrect.", item.getProcessed() == 1);
                assertTrue("The check suspended is incorrect.", item.getSuspended() == 1);
                assertTrue("The check reversed is incorrect.", item.getReversed() == 1);
                assertTrue("The check receipts is incorrect.", item.getReceipts() == 3);
            } else if (item.getName().equals("Fund Control Total")) {
                assertTrue("The fund control items is incorrect.", item.getItems() == 10);
                assertTrue("The fund control processed is incorrect.", item.getProcessed() == 1);
                assertTrue("The fund control suspended is incorrect.", item.getSuspended() == 1);
                assertTrue("The fund control reversed is incorrect.", item.getReversed() == 1);
                assertTrue("The fund control receipts is incorrect.", item.getReceipts() == 3);
            } else {
                fail("The item name is incorrect.");
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
     * Creates a DailyReconciliationReportResponse instance for test.
     *
     * @return the DailyReconciliationReportResponse instance.
     */
    private static DailyReconciliationReportResponse createResponse() {
        DailyReconciliationReportResponse response = new DailyReconciliationReportResponse();
        response.setReportName("Daily Reconciliation Report");
        response.setReportGenerationDate(new Date());
        response.setItems(new ArrayList<DailyReconciliationReportResponseItem>());
        response.getItems().add(createItem());
        response.getItems().add(createItem());
        response.getItems().add(createItem());
        return response;
    }

    /**
     * Creates a DailyReconciliationReportResponseItem instance for test.
     *
     * @return the DailyReconciliationReportResponseItem instance.
     */
    private static DailyReconciliationReportResponseItem createItem() {
        DailyReconciliationReportResponseItem item = new DailyReconciliationReportResponseItem();
        item.setName("Item Name");
        item.setItems(1000);
        item.setReceipts(2000);
        item.setProcessed(3000);
        item.setSuspended(4000);
        item.setReversed(5000);
        return item;
    }
}
