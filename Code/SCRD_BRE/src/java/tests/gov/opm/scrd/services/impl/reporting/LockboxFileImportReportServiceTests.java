/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.impl.reporting.LockboxFileImportReportRequest;
import gov.opm.scrd.services.impl.reporting.LockboxFileImportReportResponse;
import gov.opm.scrd.services.impl.reporting.LockboxFileImportReportResponseItem;
import gov.opm.scrd.services.impl.reporting.LockboxFileImportReportService;
import gov.opm.scrd.services.impl.reporting.ReportHelper;

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
 * Unit tests for {@link LockboxFileImportReportService} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class LockboxFileImportReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>LockboxFileImportReportService</code> instance used in tests.
     * </p>
     */
    private LockboxFileImportReportService instance;

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
        return new JUnit4TestAdapter(LockboxFileImportReportServiceTests.class);
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

        instance = new LockboxFileImportReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Lockbox Bank File Import Totals");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(LockboxFileImportReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        LockboxFileImportReportResponse response = instance.getReport(new LockboxFileImportReportRequest());
        assertNotNull(response);
        // verify some values
        List<LockboxFileImportReportResponseItem> items = response.getItems();
        assertNotNull(items);
        // sort by logid
        Collections.sort(items, new Comparator<LockboxFileImportReportResponseItem>() {
            /**
             * Compare the beans for more accurate testing.
             *
             * @param o1 First bean for comparison
             * @param o2 Second bean for comparison
             * @return comparison result
             */
            @Override
            public int compare(LockboxFileImportReportResponseItem o1, LockboxFileImportReportResponseItem o2) {
                if (o1.getLogId() != null && o2.getLogId() != null) {
                    return o2.getLogId().compareTo(o1.getLogId());
                }
                return 0;
            }
        });
        assertTrue(items.size() == 3); // should contain 2 items
        // verify at least one item
        LockboxFileImportReportResponseItem item = items.get(0);
        assertEquals("2", item.getLogId());
        assertEquals("01/02/2014", ReportHelper.formatDate(item.getDepositDate()));
        assertEquals("01/01/2014", ReportHelper.formatDate(item.getImportDate()));
        assertEquals(Integer.valueOf("0"), item.getCheckCount());
        assertEquals(Integer.valueOf(1), item.getAchCount());
        assertNull(item.getCheckTotal());
        assertEquals("$101.01", ReportHelper.formatMoney(item.getAchTotal()));
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
        LockboxFileImportReportResponse response = instance.getReport(new LockboxFileImportReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/LockboxFileImportReportServiceTests.PDF";
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
        LockboxFileImportReportResponse response = instance.getReport(new LockboxFileImportReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/LockboxFileImportReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}
