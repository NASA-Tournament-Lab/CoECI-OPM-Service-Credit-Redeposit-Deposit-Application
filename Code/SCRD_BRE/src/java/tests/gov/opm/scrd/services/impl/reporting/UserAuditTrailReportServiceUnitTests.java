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
import java.util.ArrayList;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link UserAuditTrailReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class UserAuditTrailReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the UserAuditTrailReportService instance.
     */
    private UserAuditTrailReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(UserAuditTrailReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new UserAuditTrailReportService();
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
        UserAuditTrailReportRequest request = new UserAuditTrailReportRequest();
        request.setCsd("1");
        UserAuditTrailReportResponse response = service.getReport(request);

        assertEquals("The reportname is incorrect.", response.getReportName(),
                UserAuditTrailReportService.class.getSimpleName());
        assertTrue("The items count is incorrect.",
                response.getItems().size() == 1);
        UserAuditTrailReportResponseItem item = response.getItems().get(0);
        assertEquals("The activity is incorrect.", item.getActivity(),
                "action1");
        assertEquals("The role is incorrect.", item.getRole(), "role1");
        assertEquals("The csd is incorrect.", item.getCsd(), "1");
        assertEquals("The database table is incorrect.", item.getDatabaseTable(), "table");
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
     * Creates a UserAuditTrailReportResponse instance for test.
     *
     * @return the UserAuditTrailReportResponse instance.
     */
    private static UserAuditTrailReportResponse createResponse() {
        UserAuditTrailReportResponse response = new UserAuditTrailReportResponse();
        ArrayList<UserAuditTrailReportResponseItem> items = new ArrayList<UserAuditTrailReportResponseItem>();
        items.add(createItem());
        items.add(createItem());
        items.add(createItem());
        response.setItems(items);
        response.setReportName("User Audit Trail Report");
        response.setReportGenerationDate(new Date());
        return response;
    }

    /**
     * Creates a UserAuditTrailReportResponseItem for test.
     *
     * @return the UserAuditTrailReportResponseItem instance.
     */
    private static UserAuditTrailReportResponseItem createItem() {
        UserAuditTrailReportResponseItem item = new UserAuditTrailReportResponseItem();
        Date auditDateTime = new Date();
        String databaseTable = "db table";
        String activity = "activity";
        String csd = "12345";
        String description = "This is a long description. This is a long description. " +
                "This is a long description. This is a long description. This is a long description. " +
                "This is a long description. This is a long description.";
        String userDescription = "This is a long description. This is a long description. ";
        String role = "role";
        String supervisingRole = "role";
        String email = "123@123.com";
        String telephone = "12345-123";

        item.setAuditDateTime(auditDateTime);
        item.setDatabaseTable(databaseTable);
        item.setActivity(activity);
        item.setCsd(csd);
        item.setDescription(description);
        item.setUserDescription(userDescription);
        item.setRole(role);
        item.setSupervisingRole(supervisingRole);
        item.setEmail(email);
        item.setTelephone(telephone);
        return item;
    }
}
