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
import java.util.Date;
import java.util.HashMap;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link AccountTypeTotalsReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class AccountTypeTotalsReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the AccountTypeTotalsReportService instance.
     */
    private AccountTypeTotalsReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(
                AccountTypeTotalsReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new AccountTypeTotalsReportService();
        service.setCsrsRetirementTypeName(TestsHelper.CSRS_RETIREMENT_TYPE_NAME);
        service.setFersRetirementTypeName(TestsHelper.FERS_RETIREMENT_TYPE_NAME);
        service.setNewAccountStatusName(TestsHelper.NEW_ACCOUNT_STATUS_NAME);
        service.setActiveAccountStatusName(TestsHelper.ACTIVE_ACCOUNT_STATUS_NAME);
        TestsHelper.initService(service, getEntityManager());
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
        AccountTypeTotalsReportRequest request = new AccountTypeTotalsReportRequest();
        AccountTypeTotalsReportResponse response = service.getReport(request);

        assertEquals("The reportname is incorrect.", response.getReportName(),
                AccountTypeTotalsReportService.class.getSimpleName());
        assertTrue("The unknownTotal is incorrect.",
                response.getUnknownTotal() == 2);
        assertTrue("The totalAccounts is incorrect.",
                response.getTotalAccounts() == 25);
        assertTrue("The unknownActiveAccounts is incorrect.",
                response.getUnknownActiveAccounts() == 1);
        assertTrue("The unknownNewAccounts is incorrect.",
                response.getUnknownNewAccounts() == 1);
        for (String key : response.getCsrsAccounts().keySet()) {
            if (key.equals("History")) {
                assertTrue("The History CSRS result is incorrect.", response
                        .getCsrsAccounts().get(key) == 1);
            } else if (key.equals("Active")) {
                assertTrue("The Active CSRS result is incorrect.", response
                        .getCsrsAccounts().get(key) == 2);
            } else {
                assertTrue("The CSRS result is incorrect.", response
                        .getCsrsAccounts().get(key) == 0);
            }
        }
        for (String key : response.getFersAccounts().keySet()) {
            int count = response.getFersAccounts().get(key);
            if (key.equals("Active")) {
                assertTrue("The Active FERS result is incorrect.", count == 6);
            } else if (key.equals("Suspended")) {
                assertTrue("The Suspended FERS result is incorrect.",
                        count == 4);
            } else if (key.equals("Closed")) {
                assertTrue("The Closed FERS result is incorrect.", count == 3);
            } else if (key.equals("History")) {
                assertTrue("The History FERS result is incorrect.", count == 6);
            } else if (key.equals("New")) {
                assertTrue("The New FERS result is incorrect.", count == 1);
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
            IOException, ParseException {
        TestsHelper.exportReport(service, createResponse(), ExportType.PDF);
    }

    /**
     * Creates a AccountTypeTotalsReportResponse instance for test.
     *
     * @return the AccountTypeTotalsReportResponse.
     */
    private static AccountTypeTotalsReportResponse createResponse() {
        AccountTypeTotalsReportResponse response = new AccountTypeTotalsReportResponse();
        HashMap<String, Integer> csrsAccounts = new HashMap<String, Integer>();
        csrsAccounts.put("New Account", 10);
        csrsAccounts.put("Active", 10);
        csrsAccounts.put("Rejected", 10);
        HashMap<String, Integer> fersAccounts = new HashMap<String, Integer>();
        fersAccounts.put("New Account", 20);
        fersAccounts.put("Active", 20);
        fersAccounts.put("Rejected", 20);
        Integer unknownNewAccounts = 0;
        Integer unknownActiveAccounts = 1;
        Integer unknownTotal = 2;
        Integer totalAccounts = 3;
        response.setReportName("Account Type Totals");
        response.setReportGenerationDate(new Date());
        response.setCsrsAccounts(csrsAccounts);
        response.setFersAccounts(fersAccounts);
        response.setUnknownActiveAccounts(unknownActiveAccounts);
        response.setUnknownNewAccounts(unknownNewAccounts);
        response.setUnknownTotal(unknownTotal);
        response.setTotalAccounts(totalAccounts);
        return response;
    }
}
