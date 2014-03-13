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
import java.util.HashMap;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link UserRolePermissionsReportService}.
 * 
 * @author RaitoShum
 * @version 1.0
 */
public class UserRolePermissionsReportServiceUnitTests extends BasePersistenceTests {
	/**
	 * Represents the UserRolePermissionsReportService instance.
	 */
	private UserRolePermissionsReportService service;

	/**
	 * <p>
	 * Adapter for earlier versions of JUnit.
	 * </p>
	 * 
	 * @return a test suite.
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(
				UserRolePermissionsReportServiceUnitTests.class);
	}

	/**
	 * Sets up the test environment.
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception {
	    super.setUp();
		service = new UserRolePermissionsReportService();
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
		UserRolePermissionsReportRequest request = new UserRolePermissionsReportRequest();
		UserRolePermissionsReportResponse response = service.getReport(request);

		assertEquals("The reportname is incorrect.", response.getReportName(),
				UserRolePermissionsReportService.class.getSimpleName());
		assertTrue("The roles count is incorrect.",
				response.getRoles().size() == 5);
		assertTrue("The permissions count is incorrect.", response
				.getPermissions().size() == 10);
		assertTrue("The descriptions count is incorrect.", response
				.getRoleDescriptions().size() == 5);

		for (String role : response.getAllowedPermissions().keySet()) {
			for (int j = 0; j < response.getAllowedPermissions().get(role)
					.size(); j++) {
				String action = "action"
						+ ((Integer.parseInt(role.substring(4)) - 1) * 2 + j + 1);
				assertEquals("The allowed permission is incorrect.", response
						.getAllowedPermissions().get(role).get(j), action);
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
	 * Creates a UserRolePermissionsReportResponse instance for test.
	 * 
	 * @return the UserRolePermissionsReportResponse instance.
	 */
	private static UserRolePermissionsReportResponse createResponse() {
		UserRolePermissionsReportResponse response = new UserRolePermissionsReportResponse();
		ArrayList<String> roles = new ArrayList<String>();
		ArrayList<String> roleDescriptions = new ArrayList<String>();
		ArrayList<String> permissions = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			roles.add("Role" + i);
			roleDescriptions.add("Role Description");
			permissions.add("Permission" + (i * 2 - 1));
			permissions.add("Permission" + (i * 2));
		}
		HashMap<String, List<String>> allowedPermissions = new HashMap<String, List<String>>();
		for (int i = 0; i < 5; i++) {
			ArrayList<String> rolePermissions = new ArrayList<String>();
			rolePermissions.add(permissions.get(i * 2));
			rolePermissions.add(permissions.get(i * 2 + 1));
			allowedPermissions.put(roles.get(i), rolePermissions);
		}
		response.setReportName("Service Credit Role Permission");
		response.setReportGenerationDate(new Date());
		response.setAllowedPermissions(allowedPermissions);
		response.setPermissions(permissions);
		response.setRoleDescriptions(roleDescriptions);
		response.setRoles(roles);
		return response;
	}
}
