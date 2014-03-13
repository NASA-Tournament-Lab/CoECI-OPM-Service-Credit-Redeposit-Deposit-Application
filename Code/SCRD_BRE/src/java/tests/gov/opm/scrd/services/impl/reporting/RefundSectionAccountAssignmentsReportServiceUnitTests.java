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
import java.util.Calendar;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link RefundSectionAccountAssignmentsReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class RefundSectionAccountAssignmentsReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents RefundSectionAccountAssignmentsReportService instance.
     */
    private RefundSectionAccountAssignmentsReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(
                RefundSectionAccountAssignmentsReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new RefundSectionAccountAssignmentsReportService();
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
    public void testGerReport() throws ReportGenerationException,
            ParseException {
        RefundSectionAccountAssignmentsReportRequest request = new RefundSectionAccountAssignmentsReportRequest();
        RefundSectionAccountAssignmentsReportResponse response = service
                .getReport(request);

        assertEquals("The reportname is incorrect.", response.getReportName(),
                RefundSectionAccountAssignmentsReportService.class
                        .getSimpleName());
        assertTrue("The groups count is incorrect.", response.getGroups()
                .size() == 5);
        long now = response.getReportGenerationDate().getTime();
        for (int i = 0; i < response.getGroups().size(); i++) {
            RefundUserAccountGroup group = response.getGroups().get(i);
            assertTrue("The items count is incorrect.",
                    group.getItems().size() == 2);
            assertEquals("The username is incorrect.", group.getUser(), "user"
                    + (i + 1));
            for (int j = 0; j < group.getItems().size(); j++) {
                RefundUserAccountItem item = group.getItems().get(j);
                String status = getEntityManager()
                        .createQuery(
                                "SELECT a.status.name FROM Account a WHERE a.claimNumber = :claimNumber",
                                String.class)
                        .setParameter("claimNumber", item.getCsd())
                        .getSingleResult();
                assertEquals("The status is incorrect.",
                        item.getAccountStatusDescription(), status);

                Calendar date = Calendar.getInstance();
                date.set(2014, 0, i * 2 + j);
                long daysNumber = (now - date.getTimeInMillis())
                        / (24 * 60 * 60 * 1000);
                assertTrue("The daysNumber is incorrect.",
                        item.getDaysNumber() == daysNumber);

                if (j == 0) {
                    assertTrue("The response's maximumDays is incorrect",
                            group.getMaximumDays() == daysNumber);
                    if (i == 0) {
                        assertTrue("The group's maximumDays is incorrect",
                                response.getMaximumDays() == daysNumber);
                    }
                }
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
     * Creates a RefundSectionAccountAssignmentsReportResponse instance for test.
     *
     * @return the RefundSectionAccountAssignmentsReportResponse.
     */
    private static RefundSectionAccountAssignmentsReportResponse createResponse() {
        RefundSectionAccountAssignmentsReportResponse response = new RefundSectionAccountAssignmentsReportResponse();
        response.setReportGenerationDate(new Date());
        response.setReportName("Refund Section Account Assignments");
        ArrayList<RefundUserAccountGroup> groups = new ArrayList<RefundUserAccountGroup>();
        groups.add(createGroup());
        groups.add(createGroup());
        response.setGroups(groups);
        response.setMaximumDays(1);
        response.setAverageDays(2);
        return response;
    }

    /**
     * Creates a RefundUserAccountGroup instance for test.
     *
     * @return the RefundUserAccountGroup instance.
     */
    private static RefundUserAccountGroup createGroup() {
        RefundUserAccountGroup group = new RefundUserAccountGroup();
        ArrayList<RefundUserAccountItem> items = new ArrayList<RefundUserAccountItem>();
        String user = "user";
        String email = "email";
        String telephone = "telephone";
        String networkId = "networkId";
        String role = "role";
        String supervisingRole = "supervisingRole";
        Integer averageDays = 1;
        Integer maximumDays = 2;

        group.setUser(user);
        group.setItems(items);
        group.setEmail(email);
        group.setTelephone(telephone);
        group.setNetworkId(networkId);
        group.setRole(role);
        group.setSupervisingRole(supervisingRole);
        group.setAverageDays(averageDays);
        group.setMaximumDays(maximumDays);
        group.getItems().add(createItem());
        group.getItems().add(createItem());
        return group;
    }

    /**
     * Creates a RefundUserAccountItem instance for test.
     *
     * @return the RefundUserAccountItem instance.
     */
    private static RefundUserAccountItem createItem() {
        RefundUserAccountItem item = new RefundUserAccountItem();
        String csd = "csd";
        Date assignmentDate = new Date();
        Integer daysNumber = 1;
        String accountStatusDescription = "accountStatusDescription";

        item.setCsd(csd);
        item.setAssignmentDate(assignmentDate);
        item.setDaysNumber(daysNumber);
        item.setAccountStatusDescription(accountStatusDescription);
        return item;
    }
}
