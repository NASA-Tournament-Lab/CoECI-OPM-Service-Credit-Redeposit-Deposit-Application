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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link DeductionRatesReportService}.
 * 
 * @author RaitoShum
 * @version 1.0
 */
public class DeductionRatesReportServiceUnitTests extends BasePersistenceTests {
	/**
	 * Represents the DeductionRatesReportService instance.
	 */
	private DeductionRatesReportService service;

	/**
	 * <p>
	 * Adapter for earlier versions of JUnit.
	 * </p>
	 * 
	 * @return a test suite.
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DeductionRatesReportServiceUnitTests.class);
	}

	/**
	 * Sets up the test environment.
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception {
	    super.setUp();
		service = new DeductionRatesReportService();
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
		DeductionRatesReportRequest request = new DeductionRatesReportRequest();
		DeductionRatesReportResponse response = service.getReport(request);

		assertEquals("The reportname is incorrect.", response.getReportName(),
				DeductionRatesReportService.class.getSimpleName());
		assertTrue("The count of serviceTypes is incorrect.", response
				.getItems().keySet().size() == 3);
		for (String key : response.getItems().keySet()) {
			List<DeductionRatesReportResponseItem> items = response.getItems()
					.get(key);
			if (key.equals("service type 1")) {
				assertTrue("The items count is incorrect.", items.size() == 4);
			} else {
				assertTrue("The items count is incorrect.", items.size() == 2);
			}
			for (DeductionRatesReportResponseItem item : items) {
				if (key.equals("service type 2")) {
					assertEquals("The item is incorrect.",
							item.getRetirementType(),
							TestsHelper.CSRS_RETIREMENT_TYPE_NAME);
				} else {
					assertEquals("The item is incorrect.",
							item.getRetirementType(),
							TestsHelper.FERS_RETIREMENT_TYPE_NAME);
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
	 * Creates a DeductionRatesReportResponse instance for test.
	 * 
	 * @return the DeductionRatesReportResponse.
	 */
	private static DeductionRatesReportResponse createResponse() {
		DeductionRatesReportResponse response = new DeductionRatesReportResponse();
		HashMap<String, List<DeductionRatesReportResponseItem>> items = new HashMap<String, List<DeductionRatesReportResponseItem>>();
		ArrayList<DeductionRatesReportResponseItem> rateItems1 = new ArrayList<DeductionRatesReportResponseItem>();
		rateItems1.add(createItem());
		rateItems1.add(createItem());
		items.put("Job 1", rateItems1);
		ArrayList<DeductionRatesReportResponseItem> rateItems2 = new ArrayList<DeductionRatesReportResponseItem>();
		rateItems2.add(createItem());
		rateItems2.add(createItem());
		items.put("Job 2", rateItems2);
		response.setItems(items);
		response.setReportName("Service Credit Deduction Rates");
		response.setReportGenerationDate(new Date());
		return response;
	}

	/**
	 * Creates a DeductionRatesReportResponseItem instance for test.
	 * 
	 * @return the DeductionRatesReportResponseItem.
	 */
	private static DeductionRatesReportResponseItem createItem() {
		DeductionRatesReportResponseItem item = new DeductionRatesReportResponseItem();
		String retirementType = "retirementType";
		Date startDate = new Date();
		Date endDate = new Date();
		Integer days = 1;
		BigDecimal rate = new BigDecimal("0.0700");

		item.setRetirementType(retirementType);
		item.setStartDate(startDate);
		item.setEndDate(endDate);
		item.setDays(days);
		item.setRate(rate);
		return item;
	}
}
