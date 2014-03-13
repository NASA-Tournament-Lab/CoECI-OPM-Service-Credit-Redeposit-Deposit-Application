/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.impl.reporting.ReportHelper;
import gov.opm.scrd.services.impl.reporting.SuspenseResolutionReportRequest;
import gov.opm.scrd.services.impl.reporting.SuspenseResolutionReportResponse;
import gov.opm.scrd.services.impl.reporting.SuspenseResolutionReportResponseItem;
import gov.opm.scrd.services.impl.reporting.SuspenseResolutionReportService;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link SuspenseResolutionReportService} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class SuspenseResolutionReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>SuspenseResolutionReportService</code> instance used in tests.
     * </p>
     */
    private SuspenseResolutionReportService instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(SuspenseResolutionReportServiceTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        entityManager = getEntityManager();

        logger = Logger.getLogger(getClass());
        createReportingData();

        instance = new SuspenseResolutionReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Suspense Resolution Report");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(SuspenseResolutionReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        SuspenseResolutionReportResponse response = instance.getReport(createRequest());
        assertNotNull(response);

        // verify some values
        List<SuspenseResolutionReportResponseItem> items = response.getItems();
        assertTrue(items.size() == 6); // should contain 6 items
        // verify at least one item
        SuspenseResolutionReportResponseItem item = items.get(0);
        assertEquals("1111111", item.getCsd());
        assertEquals("$101.01", ReportHelper.formatMoney(item.getSuspense()));
        assertEquals("", ReportHelper.formatMoney(item.getResolved()));
        assertEquals("$101.01", ReportHelper.formatMoney(item.getProcessed()));
        assertEquals("10", item.getStartedAs());
        assertEquals("Suspended", item.getResolution());
        assertEquals("A7", item.getPayment());
        assertEquals("$246.13", ReportHelper.formatMoney(item.getAccount()));
    }

    /**
     * Creates a test request.
     * @return the test request
     */
    private SuspenseResolutionReportRequest createRequest() {
        SuspenseResolutionReportRequest request = new SuspenseResolutionReportRequest();
        request.setStartDate(new GregorianCalendar(2014, 0, 1).getTime());
        request.setEndDate(new GregorianCalendar(2014, 1, 1).getTime());
        return request;
    }

    /**
     * <p>
     * Accuracy test for the method <code>exportReport</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_exportReportPDF() throws Exception {
        SuspenseResolutionReportResponse response = instance.getReport(createRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/SuspenseResolutionReportServiceTests.PDF";
        writeFile(exportReport, filename);
    }

    /**
     * <p>
     * Accuracy test for the method <code>exportReport</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_exportReportRTF() throws Exception {
        SuspenseResolutionReportResponse response = instance.getReport(createRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/SuspenseResolutionReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}
