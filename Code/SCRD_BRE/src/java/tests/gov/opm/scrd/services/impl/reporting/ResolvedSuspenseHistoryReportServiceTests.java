/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.impl.reporting.ReportHelper;
import gov.opm.scrd.services.impl.reporting.ResolvedSuspenseHistoryReportRequest;
import gov.opm.scrd.services.impl.reporting.ResolvedSuspenseHistoryReportResponse;
import gov.opm.scrd.services.impl.reporting.ResolvedSuspenseHistoryReportResponseItem;
import gov.opm.scrd.services.impl.reporting.ResolvedSuspenseHistoryReportService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link ResolvedSuspenseHistoryReportService} class.
 * </p>
 *
 * @author j3_guile
 * @version 1.0
 */
public class ResolvedSuspenseHistoryReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>ResolvedSuspenseHistoryReportService</code> instance used in tests.
     * </p>
     */
    private ResolvedSuspenseHistoryReportService instance;

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
        return new JUnit4TestAdapter(ResolvedSuspenseHistoryReportServiceTests.class);
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

        instance = new ResolvedSuspenseHistoryReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Resolved Suspense History");
        TestsHelper.setField(instance, "chartWidth", 550);
        TestsHelper.setField(instance, "chartHeight", 300);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(ResolvedSuspenseHistoryReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        ResolvedSuspenseHistoryReportResponse response = instance.getReport(new ResolvedSuspenseHistoryReportRequest());
        assertNotNull(response);

        // verify some values
        List<ResolvedSuspenseHistoryReportResponseItem> items = response.getDataItems();
        assertNotNull(items);
        // sort by BatBlkSeq
        Collections.sort(items, new Comparator<ResolvedSuspenseHistoryReportResponseItem>() {
            @Override
            public int compare(ResolvedSuspenseHistoryReportResponseItem o1,
                ResolvedSuspenseHistoryReportResponseItem o2) {
                if (o1.getBatchNumber() != null && o2.getBatchNumber() != null) {
                    return o1.getBatchNumber().compareTo(o2.getBatchNumber());
                }
                return 0;
            }
        });
        assertTrue(items.size() == 6); // should contain 6 items
        // verify at least one item
        ResolvedSuspenseHistoryReportResponseItem item = items.get(0);
        assertEquals("1111111", item.getCsd());
        assertEquals("note 1", item.getNote());
        assertEquals(ReportHelper.formatMoney(new BigDecimal(101.01)), ReportHelper.formatMoney(item.getAmount()));
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
        ResolvedSuspenseHistoryReportResponse response = instance.getReport(new ResolvedSuspenseHistoryReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/ResolvedSuspenseHistoryReportServiceTests.PDF";
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
        ResolvedSuspenseHistoryReportResponse response = instance.getReport(new ResolvedSuspenseHistoryReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/ResolvedSuspenseHistoryReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}