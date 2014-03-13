/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.batchprocessing.PaymentTransaction;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link DailySuspenseReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class DailySuspenseReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the DailySuspenseReportService instance.
     */
    private DailySuspenseReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(DailySuspenseReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new DailySuspenseReportService();
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
        DailySuspenseReportResponse response = service.getReport(new DailySuspenseReportRequest());
        assertEquals("The reportname is incorrect.", response.getReportName(),
                DailySuspenseReportService.class.getSimpleName());
        assertTrue("The items size is incorrect.", response.getItems().size() == 20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (DailySuspenseReportResponseItem item : response.getItems()) {
            assertEquals("The batchNumber is incorrect.", item.getBatchNumber(), "123");
            assertEquals("The blkNumber is incorrect.", item.getBlkNumber(), "123");
            assertEquals("The amount is incorrect.", item.getAmount().setScale(2).toString(), "100.00");
            assertEquals("The payDate is incorrect.", dateFormat.format(item.getPayDate()), "01/01/2014");
            assertEquals("The birthDate is incorrect.", dateFormat.format(item.getBirthDate()), "01/01/2014");
            assertEquals("The technician is incorrect.", item.getTechnician(), "key");
            assertEquals("The changedOn is incorrect.", dateFormat.format(item.getChangedOn()), "01/01/2014");
            PaymentTransaction paymentTransaction = getEntityManager().createQuery("SELECT p FROM " +
                    "PaymentTransaction p WHERE p.csd = :csd", PaymentTransaction.class).setParameter(
                    "csd", item.getCsd()).getSingleResult();
            assertEquals("The paymentStatus is incorrect.", item.getPaymentStatus(),
                    paymentTransaction.getPayTransStatusCode().toString());
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
     * Creates a DailySuspenseReportResponse instance for test.
     *
     * @return the DailySuspenseReportResponse instance.
     */
    private static DailySuspenseReportResponse createResponse() {
        DailySuspenseReportResponse response = new DailySuspenseReportResponse();
        response.setReportName("Daily Suspense Report");
        response.setReportGenerationDate(new Date());
        response.setItems(new ArrayList<DailySuspenseReportResponseItem>());
        response.getItems().add(createItem());
        response.getItems().add(createItem());
        return response;
    }

    /**
     * Creates a DailySuspenseReportResponseItem instance for test.
     *
     * @return the DailySuspenseReportResponseItem instance.
     */
    private static DailySuspenseReportResponseItem createItem() {
        DailySuspenseReportResponseItem item = new DailySuspenseReportResponseItem();
        item.setBatchNumber("1234");
        item.setBlkNumber("05");
        item.setSequenceNumber("333");
        item.setAmount(new BigDecimal("1000.00"));
        item.setPayDate(new Date());
        item.setCsd("123456");
        item.setBirthDate(new Date());
        item.setTechnician("X123X");
        item.setPaymentStatus("Payment Status");
        item.setChangedOn(new Date());
        return item;
    }
}
