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
import gov.opm.scrd.services.impl.reporting.MonthlySuspenseListReportRequest;
import gov.opm.scrd.services.impl.reporting.MonthlySuspenseListReportResponse;
import gov.opm.scrd.services.impl.reporting.MonthlySuspenseListReportResponseItem;
import gov.opm.scrd.services.impl.reporting.MonthlySuspenseListReportService;

import java.util.Calendar;
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
 * Unit tests for {@link MonthlySuspenseListReportService} class.
 * </p>
 *
 * @author j3_guile
 * @version 1.0
 */
public class MonthlySuspenseListReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>MonthlySuspenseListReportService</code> instance used in tests.
     * </p>
     */
    private MonthlySuspenseListReportService instance;

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
        return new JUnit4TestAdapter(MonthlySuspenseListReportServiceTests.class);
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

        instance = new MonthlySuspenseListReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Monthly Suspense Report");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(MonthlySuspenseListReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        MonthlySuspenseListReportRequest request = new MonthlySuspenseListReportRequest();
        Calendar monthStart = Calendar.getInstance();
        monthStart.set(Calendar.MONTH, 0);
        monthStart.set(Calendar.DATE, 1);
        monthStart.set(Calendar.HOUR_OF_DAY, 0);
        monthStart.set(Calendar.MINUTE, 0);
        monthStart.set(Calendar.SECOND, 0);
        monthStart.set(Calendar.MILLISECOND, 0);
        
        request.setMonth(monthStart.getTime());
        
        MonthlySuspenseListReportResponse response = instance.getReport(request);
        assertNotNull(response);

        // verify some values
        List<MonthlySuspenseListReportResponseItem> items = response.getItems();
        // sort by BatBlkSeq
        Collections.sort(items, new Comparator<MonthlySuspenseListReportResponseItem>() {
            @Override
            public int compare(MonthlySuspenseListReportResponseItem o1, MonthlySuspenseListReportResponseItem o2) {
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
        MonthlySuspenseListReportResponseItem item = items.get(0);
        assertEquals("111-11-11", item.getBatchNumber());
        assertEquals("1111111", item.getCsd());
        assertEquals("Suspended", item.getCurrentStatus());
    }

    /**
     * Count specific payments.
     *
     * @param items contains details
     * @param isACH true if to count ACH, else others
     * @return count of payments
     */
    private int countPayments(List<MonthlySuspenseListReportResponseItem> items, boolean isACH) {
        int count = 0;
        for (MonthlySuspenseListReportResponseItem item : items) {
            if (isACH && "ACH".equals(item.getType())) {
                count++;
            } else if (!isACH && "Chk".equals(item.getType())) {
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
        MonthlySuspenseListReportResponse response = instance.getReport(new MonthlySuspenseListReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/MonthlySuspenseListReportServiceTests.PDF";
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
        MonthlySuspenseListReportResponse response = instance.getReport(new MonthlySuspenseListReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/MonthlySuspenseListReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}