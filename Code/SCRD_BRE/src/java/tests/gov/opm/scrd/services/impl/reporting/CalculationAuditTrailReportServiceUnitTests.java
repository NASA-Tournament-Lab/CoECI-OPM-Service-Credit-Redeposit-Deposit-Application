/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.lookup.CalculationResultItemUpdateStatus;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link CalculationAuditTrailReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class CalculationAuditTrailReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the CalculationAuditTrailReportService instance.
     */
    private CalculationAuditTrailReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(
                CalculationAuditTrailReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new CalculationAuditTrailReportService();
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
        CalculationAuditTrailReportRequest request = new CalculationAuditTrailReportRequest();
        request.setCsd("1");
        CalculationAuditTrailReportResponse response = service.getReport(request);
        assertEquals("The calculationDate is incorrect.", response.getReportName(),
                CalculationAuditTrailReportService.class.getSimpleName());
        assertEquals("The csd is incorrect.", response.getCsd(), request.getCsd());
        for (int i = 0; i < response.getGroups().size(); i++) {
            CalculationAuditGroup group = response.getGroups().get(i);
            assertEquals("The action is incorrect.", group.getAction(), "calculation version " + (i + 1));
            for (int j = 0; j < group.getItems().size(); j++) {
                CalculationAuditItem item = group.getItems().get(j);
                BigDecimal deduction = item.getDeduction();
                assertEquals("The interest is incorrect.", item.getInterest().toString(),
                        deduction.toString());
                assertEquals("The payments is incorrect.", item.getPayments().toString(),
                        deduction.toString());
                assertTrue("The line is incorrect.", item.getLine() == 1);
                assertTrue("The version is incorrect.", item.getVersion() == 1);
            }
        }
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
     * Creates a CalculationAuditTrailReportResponse instance for test.
     *
     * @return the CalculationAuditTrailReportResponse instance.
     */
    private static CalculationAuditTrailReportResponse createResponse() {
        CalculationAuditTrailReportResponse response = new CalculationAuditTrailReportResponse();
        String csd = "CSD";
        ArrayList<CalculationAuditGroup> groups = new ArrayList<CalculationAuditGroup>();
        CalculationAuditGroup group1 = createGroup();
        group1.getItems().add(createItem(CalculationResultItemUpdateStatus.INSERTED));
        group1.getItems().add(createItem(CalculationResultItemUpdateStatus.DELETED));

        CalculationAuditGroup group2 = createGroup();
        group2.getItems().add(createItem(CalculationResultItemUpdateStatus.INSERTED));
        group2.getItems().add(createItem(CalculationResultItemUpdateStatus.UPDATED));

        groups.add(group1);
        groups.add(group2);
        response.setCsd(csd);
        response.setGroups(groups);
        response.setReportName("Calculation Log for FERS");
        response.setReportGenerationDate(new Date());
        return response;
    }

    /**
     * Creates a CalculationAuditGroup instance for test.
     *
     * @return the CalculationAuditGroup instance.
     */
    private static CalculationAuditGroup createGroup() {
        CalculationAuditGroup group = new CalculationAuditGroup();
        Date calculationDate = new Date();
        String action = "action";
        Date effective = new Date();
        ArrayList<CalculationAuditItem> items = new ArrayList<CalculationAuditItem>();

        group.setCalculationDate(calculationDate);
        group.setAction(action);
        group.setEffective(effective);
        group.setItems(items);
        return group;
    }

    /**
     * Creates a CalculationAuditItem instance for test.
     *
     * @param status
     *         the update status.
     * @return the CalculationAuditItem instance.
     */
    private static CalculationAuditItem createItem(CalculationResultItemUpdateStatus status) {
        CalculationAuditItem item = new CalculationAuditItem();
        Integer version = 1;
        Integer line = 2;
        String period = "period";
        Date startDate = new Date();
        Date endDate = new Date();
        BigDecimal deduction = new BigDecimal("1.11");
        BigDecimal interest = new BigDecimal("2.22");
        BigDecimal payments = new BigDecimal("3.33");

        item.setOfficial(true);
        item.setVersion(version);
        item.setLine(line);
        item.setPeriod(period);
        item.setStartDate(startDate);
        item.setEndDate(endDate);
        item.setDeduction(deduction);
        item.setInterest(interest);
        item.setPayments(payments);
        item.setInserted(status == CalculationResultItemUpdateStatus.INSERTED);
        item.setUpdated(status == CalculationResultItemUpdateStatus.UPDATED);
        item.setDeleted(status == CalculationResultItemUpdateStatus.DELETED);
        return item;
    }
}
