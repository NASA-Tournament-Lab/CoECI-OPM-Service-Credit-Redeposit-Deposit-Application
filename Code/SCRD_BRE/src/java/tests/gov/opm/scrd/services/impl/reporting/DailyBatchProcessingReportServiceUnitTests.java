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
import java.util.ArrayList;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link DailyBatchProcessingReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class DailyBatchProcessingReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the BalancedScorecardAccountReportService instance.
     */
    private DailyBatchProcessingReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(DailyBatchProcessingReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new DailyBatchProcessingReportService();
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
        DailyBatchProcessingReportResponse response = service.getReport(new DailyBatchProcessingReportRequest());
        assertEquals("The reportname is incorrect.", response.getReportName(),
                DailyBatchProcessingReportService.class.getSimpleName());
        assertTrue("The grandPay is incorrect.", response.getGrandPay() == 200);
        assertEquals("The grandTotal is incorrect.", response.getGrandTotal().setScale(2).toString(), "20000.00");
        assertTrue("The day items count is incorrect.", response.getItems().size() == 4);
        for (DailyBatchProcessingReportDayItem dayItem : response.getItems()) {
            assertEquals("The day item grandTotal is incorrect.", dayItem.getGrandTotal().setScale(2).toString(),
                    "5000.00");
            assertTrue("The day item grandPay is incorrect.", dayItem.getGrandPay() == 50);
            assertTrue("The items count is incorrect.", dayItem.getItems().size() == 5);
            for (DailyBatchProcessingReportItem item : dayItem.getItems()) {
                assertEquals("The accountStatusDescription is incorrect.", item.getDescription(),
                        "account status description");
                assertEquals("The transactionStatus is incorrect.", item.getTransactionStatus(),
                        "transaction status");
                assertTrue("The pay is incorrect.", item.getPay() == 10);
                assertEquals("The total is incorrect.", item.getTotal().setScale(2).toString(), "1000.00");
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
     * Creates a DailyBatchProcessingReportResponse instance for test.
     *
     * @return the DailyBatchProcessingReportResponse instance.
     */
    private static DailyBatchProcessingReportResponse createResponse() {
        DailyBatchProcessingReportResponse response = new DailyBatchProcessingReportResponse();
        response.setReportName("Service Credit Daily Batch Processing Totals");
        response.setReportGenerationDate(new Date());
        response.setItems(new ArrayList<DailyBatchProcessingReportDayItem>());
        response.getItems().add(createDayItem());
        response.getItems().add(createDayItem());
        response.getItems().add(createDayItem());
        response.setGrandPay(15000);
        response.setGrandTotal(new BigDecimal("30000.00"));
        response.setGrandRefund(new BigDecimal("150.00"));
        return response;
    }

    /**
     * Creates a DailyBatchProcessingReportDayItem instance for test.
     *
     * @return the DailyBatchProcessingReportDayItem instance.
     */
    private static DailyBatchProcessingReportDayItem createDayItem() {
        DailyBatchProcessingReportDayItem item = new DailyBatchProcessingReportDayItem();
        item.setItems(new ArrayList<DailyBatchProcessingReportItem>());
        item.getItems().add(createItem());
        item.getItems().add(createItem());
        item.getItems().add(createItem());
        item.getItems().add(createItem());
        item.getItems().add(createItem());
        item.setGrandPay(5000);
        item.setGrandTotal(new BigDecimal("10000.00"));
        item.setGrandRefund(new BigDecimal("50.00"));
        item.setDate(new Date());
        return item;
    }

    /**
     * Creates a DailyBatchProcessingReportItem instance for test.
     *
     * @return the DailyBatchProcessingReportItem instance.
     */
    private static DailyBatchProcessingReportItem createItem() {
        DailyBatchProcessingReportItem item = new DailyBatchProcessingReportItem();
        item.setBatchDate(new Date());
        item.setTransactionDate(new Date());
        item.setTransactionStatus("Transaction Status");
        item.setPay(1000);
        item.setTotal(new BigDecimal("2000.00"));
        item.setRefund(new BigDecimal("10.00"));
        item.setDescription("Description");
        return item;
    }
}
