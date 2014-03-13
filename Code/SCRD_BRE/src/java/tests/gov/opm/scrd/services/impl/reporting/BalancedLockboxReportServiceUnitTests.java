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
 * Unit tests for {@link BalancedLockboxReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class BalancedLockboxReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the BalancedLockboxReportService instance.
     */
    private BalancedLockboxReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BalancedLockboxReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new BalancedLockboxReportService();
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
        BalancedLockboxReportResponse response = service.getReport(new BalancedLockboxReportRequest());
        assertEquals("The reportname is incorrect.", response.getReportName(),
                BalancedLockboxReportService.class.getSimpleName());
        assertTrue("The total imported is incorrect.", response.getTotalImported() == 20);
        assertTrue("The total reversed is incorrect.", response.getTotalReversed() == 3);
        assertTrue("The change record items size is incorrect.", response.getChangeRecordsItems().size() == 2);
        for (BalancedLockboxReportResponseItem item : response.getChangeRecordsItems()) {
            assertTrue("The itemNumber count is incorrect.", item.getItemNumber() == 10);
            assertEquals("The totalNumber is incorrect.", item.getTotalNumber().setScale(2).toString(), "1000.00");
        }
        assertTrue("The ach imported items size is incorrect.", response.getAchImportedItems().size() == 3);
        for (BalancedLockboxReportResponseItem item : response.getAchImportedItems()) {
            assertTrue("The itemNumber count is incorrect.", item.getItemNumber() == 1);
            assertEquals("The totalNumber is incorrect.", item.getTotalNumber().setScale(2).toString(), "100.00");
        }
        assertTrue("The check imported items size is incorrect.", response.getChecksImportedItems().size() == 3);
        for (BalancedLockboxReportResponseItem item : response.getChecksImportedItems()) {
            assertTrue("The itemNumber count is incorrect.", item.getItemNumber() == 1);
            assertEquals("The totalNumber is incorrect.", item.getTotalNumber().setScale(2).toString(), "100.00");
        }
        assertTrue("The ach reversed items size is incorrect.", response.getAchReversedItems().size() == 1);
        for (BalancedLockboxReportResponseItem item : response.getAchReversedItems()) {
            assertTrue("The itemNumber count is incorrect.", item.getItemNumber() == 1);
            assertEquals("The totalNumber is incorrect.", item.getTotalNumber().setScale(2).toString(), "100.00");
        }
        assertTrue("The debit vouchers items size is incorrect.", response.getDebitVouchersItems().size() == 0);
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
     * Creates a BalancedLockboxReportResponse instance for test.
     *
     * @return the BalancedLockboxReportResponse instance.
     */
    private static BalancedLockboxReportResponse createResponse() {
        BalancedLockboxReportResponse response = new BalancedLockboxReportResponse();
        response.setReportGenerationDate(new Date());
        response.setReportName("Balanced Scorecard Lockbox Imports");
        response.setFiscalYear(2014);
        response.setQuarter(1);
        response.setStartDate(new Date());
        response.setEndDate(new Date());
        response.setTotalImported(6000);
        response.setTotalReversed(3000);

        response.setChangeRecordsItems(new ArrayList<BalancedLockboxReportResponseItem>());
        response.getChangeRecordsItems().add(createItem());
        response.getChangeRecordsItems().add(createItem());
        response.getChangeRecordsItems().add(createItem());

        response.setAchImportedItems(new ArrayList<BalancedLockboxReportResponseItem>());
        response.getAchImportedItems().add(createItem());
        response.getAchImportedItems().add(createItem());
        response.getAchImportedItems().add(createItem());

        response.setChecksImportedItems(new ArrayList<BalancedLockboxReportResponseItem>());
        response.getChecksImportedItems().add(createItem());
        response.getChecksImportedItems().add(createItem());
        response.getChecksImportedItems().add(createItem());

        response.setAchReversedItems(new ArrayList<BalancedLockboxReportResponseItem>());
        response.getAchReversedItems().add(createItem());
        response.getAchReversedItems().add(createItem());
        response.getAchReversedItems().add(createItem());

        response.setDebitVouchersItems(new ArrayList<BalancedLockboxReportResponseItem>());
        response.getDebitVouchersItems().add(createItem());
        response.getDebitVouchersItems().add(createItem());

        return response;
    }

    /**
     * Creates a BalancedLockboxReportResponseItem instance for test.
     *
     * @return the BalancedLockboxReportResponseItem instance.
     */
    private static BalancedLockboxReportResponseItem createItem() {
        BalancedLockboxReportResponseItem item = new BalancedLockboxReportResponseItem();
        item.setImportStatus("Import Status");
        item.setItemNumber(1000);
        item.setTotalNumber(new BigDecimal("1000.00"));
        return item;
    }
}
