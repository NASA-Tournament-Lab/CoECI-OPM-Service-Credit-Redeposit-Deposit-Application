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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link BalancedScorecardAccountReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class BalancedScorecardAccountReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the BalancedScorecardAccountReportService instance.
     */
    private BalancedScorecardAccountReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BalancedScorecardAccountReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new BalancedScorecardAccountReportService();
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
        BalancedScorecardAccountReportResponse response = service.getReport(
                new BalancedScorecardAccountReportRequest());
        assertEquals("The reportname is incorrect.", response.getReportName(),
                BalancedScorecardAccountReportService.class.getSimpleName());
        assertTrue("The closedAccountTotal is incorrect.", response.getClosedAccountTotal() == 3);
        assertTrue("The newAccountTotal is incorrect.", response.getNewAccountTotal() == 3);
        assertTrue("The initialBillingTotal is incorrect.", response.getInitialBillingTotal() == 3);
        for (BalancedScorecardAccountReportResponseItem item : response.getClosedAccountItems()) {
            assertTrue("The number is incorrect.", item.getNumber() == 1);
            assertEquals("The accountType is incorrect.", item.getAccountType(), "FERS");
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
     * Creates a BalancedScorecardAccountReportResponse instance for test.
     *
     * @return the BalancedScorecardAccountReportResponse instance.
     */
    private static BalancedScorecardAccountReportResponse createResponse() {
        BalancedScorecardAccountReportResponse response = new BalancedScorecardAccountReportResponse();
        response.setReportGenerationDate(new Date());
        response.setReportName("Balanced Scorecard Account Processing");
        response.setClosedAccountItems(new ArrayList<BalancedScorecardAccountReportResponseItem>());
        response.getClosedAccountItems().add(createItem());
        response.getClosedAccountItems().add(createItem());
        response.getClosedAccountItems().add(createItem());
        response.setInitialBillingItems(new ArrayList<BalancedScorecardAccountReportResponseItem>());
        response.getInitialBillingItems().add(createItem());
        response.getInitialBillingItems().add(createItem());
        response.getInitialBillingItems().add(createItem());
        response.setNewAccountItems(new ArrayList<BalancedScorecardAccountReportResponseItem>());
        response.getNewAccountItems().add(createItem());
        response.getNewAccountItems().add(createItem());
        response.getNewAccountItems().add(createItem());
        response.setClosedAccountTotal(3000);
        response.setInitialBillingTotal(3000);
        response.setNewAccountTotal(3000);
        return response;
    }

    /**
     * Creates a BalancedScorecardAccountReportResponseItem instance for test.
     *
     * @return the BalancedScorecardAccountReportResponseItem instance.
     */
    private static BalancedScorecardAccountReportResponseItem createItem() {
        BalancedScorecardAccountReportResponseItem item = new BalancedScorecardAccountReportResponseItem();
        item.setAccountStatus("This is the account status description.");
        item.setAccountType("FERS");
        item.setNumber(1000);
        return item;
    }
}
