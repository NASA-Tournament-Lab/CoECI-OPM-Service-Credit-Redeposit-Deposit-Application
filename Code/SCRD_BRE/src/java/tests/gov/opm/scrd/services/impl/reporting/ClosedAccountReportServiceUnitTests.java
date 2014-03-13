/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
 * Unit tests for {@link ClosedAccountReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class ClosedAccountReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the ClosedAccountReportService instance.
     */
    private ClosedAccountReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ClosedAccountReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new ClosedAccountReportService();
        TestsHelper.initService(service, getEntityManager());
        service.setClosedAccountStatusName(TestsHelper.CLOSED_ACCOUNT_STATUS_NAME);
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
        ClosedAccountReportRequest request = new ClosedAccountReportRequest();
        ClosedAccountReportResponse response = service.getReport(request);
        assertEquals("The reportname is incorrect.", response.getReportName(),
                ClosedAccountReportService.class.getSimpleName());
        assertEquals("The entries size is incorrect.", response.getEntries().size(), 3);
        for (ClosedAccountReportResponseEntry entry : response.getEntries()) {
            if (entry.getClaimNumber().equals("11")) {
                assertEquals("The name is incorrect.", entry.getName(), "Heather Scott");
                assertEquals("The billingName is incorrect.", entry.getBillingName(), "Heather Scott");
                assertEquals("The ssn is incorrect.", entry.getSsn(), "024-34-5876");
            } else if (entry.getClaimNumber().equals("17")) {
                assertEquals("The name is incorrect.", entry.getName(), "Thomas Austin");
                assertEquals("The billingName is incorrect.", entry.getBillingName(), "Thomas Austin");
                assertEquals("The ssn is incorrect.", entry.getSsn(), "300-96-5313");
            } else if (entry.getClaimNumber().equals("25")) {
                assertEquals("The name is incorrect.", entry.getName(), "Raymond Thomas");
                assertEquals("The billingName is incorrect.", entry.getBillingName(), "Raymond Thomas");
                assertEquals("The ssn is incorrect.", entry.getSsn(), "356-50-4778");
            } else {
                fail("The claimNumber is incorrect.");
            }
            assertNull("The closedDate should be null.", entry.getCloseDate());
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
     * Creates a ClosedAccountReportResponse instance for test.
     *
     * @return the ClosedAccountReportResponse instance.
     */
    private static ClosedAccountReportResponse createResponse() {
        ClosedAccountReportResponse response = new ClosedAccountReportResponse();
        response.setReportName("Closed Account Report");
        response.setReportGenerationDate(new Date());
        ArrayList<ClosedAccountReportResponseEntry> entries = new ArrayList<ClosedAccountReportResponseEntry>();
        entries.add(createEntry());
        entries.add(createEntry());
        response.setEntries(entries);
        return response;
    }

    /**
     * Creates a ClosedAccountReportResponseEntry instance for test.
     *
     * @return the ClosedAccountReportResponseEntry instance.
     */
    private static ClosedAccountReportResponseEntry createEntry() {
        ClosedAccountReportResponseEntry entry = new ClosedAccountReportResponseEntry();
        String name = "name";
        Date dateOfBirth = new Date();
        Date closeDate = new Date();
        String claimNumber = "claimNumber";
        String ssn = "ssn";
        String billingName = "billingName";
        Date billingDate = new Date();
        ArrayList<ClosedAccountReportResponseItem> items = new ArrayList<ClosedAccountReportResponseItem>();
        items.add(createItem());
        items.add(createItem());
        items.add(createItem());
        Date dateOfLastPayment = new Date();
        String coveredBy = "coveredBy";
        Date dateOfLastActivity = new Date();
        ArrayList<CoveredServicePeriod> periods = new ArrayList<CoveredServicePeriod>();
        periods.add(createPeriod());
        periods.add(createPeriod());
        entry.setName(name);
        entry.setDateOfBirth(dateOfBirth);
        entry.setCloseDate(closeDate);
        entry.setClaimNumber(claimNumber);
        entry.setSsn(ssn);
        entry.setBillingName(billingName);
        entry.setBillingDate(billingDate);
        entry.setItems(items);
        entry.setDateOfLastPayment(dateOfLastPayment);
        entry.setCoveredBy(coveredBy);
        entry.setDateOfLastActivity(dateOfLastActivity);
        entry.setPeriods(periods);
        return entry;
    }

    /**
     * Creates a ClosedAccountReportResponseItem instance for test.
     *
     * @return the ClosedAccountReportResponseItem instance.
     */
    private static ClosedAccountReportResponseItem createItem() {
        ClosedAccountReportResponseItem item = new ClosedAccountReportResponseItem();
        String name = "name";
        BigDecimal fers = new BigDecimal("1.11");
        BigDecimal redepositsPost982 = new BigDecimal("1.11");
        BigDecimal redepositsPre1082 = new BigDecimal("2.22");
        BigDecimal depositsPost982 = new BigDecimal("3.33");
        BigDecimal depositsPre1082 = new BigDecimal("4.44");

        item.setName(name);
        item.setFers(fers);
        item.setRedepositsPost982(redepositsPost982);
        item.setRedepositsPre1082(redepositsPre1082);
        item.setDepositsPost982(depositsPost982);
        item.setDepositsPre1082(depositsPre1082);
        return item;
    }

    /**
     * Creates a CoveredServicePeriod instance for test.
     *
     * @return the CoveredServicePeriod instance.
     */
    private static CoveredServicePeriod createPeriod() {
        CoveredServicePeriod period = new CoveredServicePeriod();
        Date from = new Date();
        Date to = new Date();
        String type = "type";

        period.setFrom(from);
        period.setTo(to);
        period.setType(type);
        return period;
    }
}
