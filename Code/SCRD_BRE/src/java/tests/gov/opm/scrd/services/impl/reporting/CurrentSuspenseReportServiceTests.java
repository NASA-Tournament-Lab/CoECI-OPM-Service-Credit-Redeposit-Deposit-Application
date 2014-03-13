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
import gov.opm.scrd.services.impl.reporting.CurrentSuspenseReportRequest;
import gov.opm.scrd.services.impl.reporting.CurrentSuspenseReportResponse;
import gov.opm.scrd.services.impl.reporting.CurrentSuspenseReportResponseItem;
import gov.opm.scrd.services.impl.reporting.CurrentSuspenseReportService;

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
 * Unit tests for {@link CurrentSuspenseReportService} class.
 * </p>
 *
 * @author j3_guile
 * @version 1.0
 */
public class CurrentSuspenseReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>CurrentSuspenseReportService</code> instance used in tests.
     * </p>
     */
    private CurrentSuspenseReportService instance;

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
        return new JUnit4TestAdapter(CurrentSuspenseReportServiceTests.class);
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

        instance = new CurrentSuspenseReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Current Suspense");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(CurrentSuspenseReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        CurrentSuspenseReportResponse response = instance.getReport(new CurrentSuspenseReportRequest());
        assertNotNull(response);

        // verify some values
        List<CurrentSuspenseReportResponseItem> items = response.getItems();
        // sort by BatBlkSeq
        Collections.sort(items, new Comparator<CurrentSuspenseReportResponseItem>() {
            @Override
            public int compare(CurrentSuspenseReportResponseItem o1, CurrentSuspenseReportResponseItem o2) {
                if (o1.getBatchNumber() != null && o2.getBatchNumber() != null) {
                    return o1.getBatchNumber().compareTo(o2.getBatchNumber());
                }
                return 0;
            }
        });
        assertNotNull(items);
        assertTrue(items.size() == 6); // should contain 6 items
        assertTrue("ACH payments should be 2", countPayments(items, true) == 2);
        assertTrue("Check payments should be 4", countPayments(items, false) == 4);
        // verify at least one item
        CurrentSuspenseReportResponseItem item = items.get(0);
        assertEquals("111-11-11", item.getBatchNumber());
        assertEquals("Suspended", item.getPaymentStatus());
        assertEquals("claimant 1", item.getClaimant());
    }

    /**
     * Count specific payments.
     * @param items contains details
     * @param isACH true if to count ACH, else others
     * @return count of payments
     */
    private int countPayments(List<CurrentSuspenseReportResponseItem> items, boolean isACH) {
        int count = 0;
        for (CurrentSuspenseReportResponseItem item : items) {
            if (isACH && item.isACH()) {
                count++;
            } else if (!isACH && !item.isACH()) {
                count++;
            }
        }
        return count;
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
        CurrentSuspenseReportResponse response = instance.getReport(new CurrentSuspenseReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/CurrentSuspenseReportServiceTests.PDF";
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
        CurrentSuspenseReportResponse response = instance.getReport(new CurrentSuspenseReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/CurrentSuspenseReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}