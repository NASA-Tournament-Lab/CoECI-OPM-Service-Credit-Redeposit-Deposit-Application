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
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link AccountStatisticsReportService}.
 * 
 * @author RaitoShum
 * @version 1.0
 */
public class AccountStatisticsReportServiceUnitTests extends BasePersistenceTests {
	/**
	 * Represents the AccountStatisticsReportService instance.
	 */
	private AccountStatisticsReportService service;

	/**
	 * <p>
	 * Adapter for earlier versions of JUnit.
	 * </p>
	 * 
	 * @return a test suite.
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(
				AccountStatisticsReportServiceUnitTests.class);
	}

	/**
	 * Sets up the test environment.
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception {
	    super.setUp();
		service = new AccountStatisticsReportService();
		service.setActiveAccountStatusName(TestsHelper.ACTIVE_ACCOUNT_STATUS_NAME);
		service.setClosedAccountStatusName(TestsHelper.CLOSED_ACCOUNT_STATUS_NAME);
		service.setHistoryAccountStatusName(TestsHelper.HISTORY_ACCOUNT_STATUS_NAME);
		service.setCsrsRetirementTypeName(TestsHelper.CSRS_RETIREMENT_TYPE_NAME);
		service.setFersRetirementTypeName(TestsHelper.FERS_RETIREMENT_TYPE_NAME);
		TestsHelper.initService(service, getEntityManager());
		createAccountReportData();
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
		AccountStatisticsReportRequest request = new AccountStatisticsReportRequest();
		AccountStatisticsReportResponse response = service.getReport(request);

		assertEquals("The reportname is incorrect.", response.getReportName(),
				AccountStatisticsReportService.class.getSimpleName());
		
		assertTrue("The totalFersAccounts is incorrect.",
				response.getTotalFersAccounts() == 13);
		assertTrue("The totalCsrsAccounts is incorrect.",
				response.getTotalCsrsAccounts() == 3);
		assertTrue("The totalAccountsLastYear is incorrect.",
				response.getTotalAccountsLastYear() == 3);
		assertTrue(
				"The totalHistoryClosedAccountsNoPostedPayment is incorrect.",
				response.getTotalHistoryClosedAccountsNoPostedPayment() == 9);
		assertTrue("The totalAccountsNoPostedPayments is incorrect.",
				response.getTotalAccountsNoPostedPayments() == 15);
		assertTrue("The totalHistoryClosedAccountsLastYear is incorrect.",
				response.getTotalHistoryClosedAccountsLastYear() == 1);
		assertTrue("The totalActiveCsrsAccounts is incorrect.",
				response.getTotalActiveCsrsAccounts() == 2);
		assertTrue("The totalActiveFersAccounts is incorrect.",
				response.getTotalActiveFersAccounts() == 7);
		assertTrue("The totalHistoryFersAccounts is incorrect.",
				response.getTotalHistoryFersAccounts() == 6);
		assertTrue("The totalHistoryCsrsAccounts is incorrect.",
				response.getTotalHistoryCsrsAccounts() == 1);
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
	 * Creates a AccountStatisticsReportResponse instance for test.
	 * 
	 * @return the AccountStatisticsReportResponse instance.
	 */
	private static AccountStatisticsReportResponse createResponse() {
		AccountStatisticsReportResponse response = new AccountStatisticsReportResponse();
		Integer totalActiveCsrsAccounts = 1000;
		Integer totalActiveFersAccounts = 2000;
		Integer totalHistoryCsrsAccounts = 2000;
		Integer totalHistoryFersAccounts = 3000;
		Integer totalCsrsAccounts = 4000;
		Integer totalFersAccounts = 5000;
		Integer totalOpenAccountsNoPostedPayments = 6000;
		Integer totalHistoryClosedAccountsNoPostedPayment = 7000;
		Integer totalAccountsNoPostedPayments = 8000;
		Integer totalOpenAccountsLastYear = 9000;
		Integer totalHistoryClosedAccountsLastYear = 10000;
		Integer totalAccountsLastYear = 11000;
		response.setTotalActiveCsrsAccounts(totalActiveCsrsAccounts);
		response.setTotalActiveFersAccounts(totalActiveFersAccounts);
		response.setTotalHistoryCsrsAccounts(totalHistoryCsrsAccounts);
		response.setTotalHistoryFersAccounts(totalHistoryFersAccounts);
		response.setTotalCsrsAccounts(totalCsrsAccounts);
		response.setTotalFersAccounts(totalFersAccounts);
		response.setTotalOpenAccountsNoPostedPayments(totalOpenAccountsNoPostedPayments);
		response.setTotalOpenAccountsLastYear(totalOpenAccountsLastYear);
		response.setTotalHistoryClosedAccountsLastYear(totalHistoryClosedAccountsLastYear);
		response.setTotalHistoryClosedAccountsNoPostedPayment(totalHistoryClosedAccountsNoPostedPayment);
		response.setTotalAccountsNoPostedPayments(totalAccountsNoPostedPayments);
		response.setTotalAccountsLastYear(totalAccountsLastYear);
		response.setReportGenerationDate(new Date());
		response.setReportName("Account Statistics");
		return response;
	}
}
