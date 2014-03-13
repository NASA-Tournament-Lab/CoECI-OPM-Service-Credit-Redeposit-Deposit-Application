/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.impl.reporting.ReportHelper;
import gov.opm.scrd.services.impl.reporting.TotalPaymentSummaryReportRequest;
import gov.opm.scrd.services.impl.reporting.TotalPaymentSummaryReportResponse;
import gov.opm.scrd.services.impl.reporting.TotalPaymentSummaryReportService;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link TotalPaymentSummaryReportService} class.
 * </p>
 *
 * @author j3_guile
 * @version 1.0
 */
public class TotalPaymentSummaryReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>TotalPaymentSummaryReportService</code> instance used in tests.
     * </p>
     */
    private TotalPaymentSummaryReportService instance;

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
        return new JUnit4TestAdapter(TotalPaymentSummaryReportServiceTests.class);
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
        createReportingPaymentData();

        instance = new TotalPaymentSummaryReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "SC-007-001B Summary of Total Payments");
        TestsHelper.setField(instance, "chartWidth", 550);
        TestsHelper.setField(instance, "chartHeight", 300);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(TotalPaymentSummaryReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        TotalPaymentSummaryReportResponse response = instance.getReport(new TotalPaymentSummaryReportRequest());
        assertNotNull(response);
        // verify some values
        assertTrue(response.getAccountNumberOnFile() == 3);
        assertEquals(ReportHelper.formatMoney(new BigDecimal(8323.20)),
            ReportHelper.formatMoney(response.getTotalPaymentsOnFile()));
        assertEquals(ReportHelper.formatMoney(new BigDecimal(1600)),
            ReportHelper.formatMoney(response.getTotalRedepositsPre1082()));
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
        TotalPaymentSummaryReportResponse response = instance.getReport(new TotalPaymentSummaryReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/TotalPaymentSummaryReportServiceTests.PDF";
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
        TotalPaymentSummaryReportResponse response = instance.getReport(new TotalPaymentSummaryReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/TotalPaymentSummaryReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}