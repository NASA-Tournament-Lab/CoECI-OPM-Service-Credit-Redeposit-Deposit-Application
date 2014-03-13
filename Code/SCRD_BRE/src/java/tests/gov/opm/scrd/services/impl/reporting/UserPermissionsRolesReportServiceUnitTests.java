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
 * Unit tests for {@link UserPermissionsRolesReportService}.
 * 
 * @author RaitoShum
 * @version 1.0
 */
public class UserPermissionsRolesReportServiceUnitTests extends BasePersistenceTests {
	/**
	 * Represents the UserPermissionsRolesReportService instance.
	 */
	private UserPermissionsRolesReportService service;

	/**
	 * <p>
	 * Adapter for earlier versions of JUnit.
	 * </p>
	 * 
	 * @return a test suite.
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(
				UserPermissionsRolesReportServiceUnitTests.class);
	}

	/**
	 * Sets up the test environment.
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception {
	    super.setUp();
		service = new UserPermissionsRolesReportService();
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
		UserPermissionsRolesReportRequest request = new UserPermissionsRolesReportRequest();
		UserPermissionsRolesReportResponse response = service
				.getReport(request);

		assertEquals("The reportname is incorrect.", response.getReportName(),
				UserPermissionsRolesReportService.class.getSimpleName());
		assertTrue("The items count is incorrect.",
				response.getItems().size() == 10);
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
	 * Creates a UserPermissionsRolesReportResponse instance for test.
	 * 
	 * @return the UserPermissionsRolesReportResponse instance.
	 */
	private static UserPermissionsRolesReportResponse createResponse() {
		UserPermissionsRolesReportResponse response = new UserPermissionsRolesReportResponse();
		ArrayList<UserPermissionsRolesReportResponseItem> items = new ArrayList<UserPermissionsRolesReportResponseItem>();
		items.add(createItem("role1"));
		items.add(createItem("role1"));
		items.add(createItem("role2"));
		items.add(createItem("role2"));
		response.setItems(items);
		response.setReportGenerationDate(new Date());
		response.setReportName("User Permission Roles in Service Credit");
		return response;
	}

	/**
	 * Creates a UserPermissionsRolesReportResponseItem instance for test.
	 * 
	 * @param role
	 *            the role to use.
	 * @return the UserPermissionsRolesReportResponseItem instance.
	 */
	private static UserPermissionsRolesReportResponseItem createItem(String role) {
		UserPermissionsRolesReportResponseItem item = new UserPermissionsRolesReportResponseItem();
		String userName = "userName";
		String opmName = "opmName";
		String telephone = "telephone";
		String email = "email";
		String userStatus = "userStatus";
		String supervisor = "supervisor";

		item.setRole(role);
		item.setUserName(userName);
		item.setOpmName(opmName);
		item.setTelephone(telephone);
		item.setEmail(email);
		item.setUserStatus(userStatus);
		item.setSupervisor(supervisor);
		return item;
	}
}
