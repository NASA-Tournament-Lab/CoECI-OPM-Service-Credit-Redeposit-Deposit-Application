/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.impl.reporting.MonthlyAdjustmentReportRequest;
import gov.opm.scrd.services.impl.reporting.MonthlyAdjustmentReportResponse;
import gov.opm.scrd.services.impl.reporting.MonthlyAdjustmentReportResponseItem;
import gov.opm.scrd.services.impl.reporting.MonthlyAdjustmentReportService;
import gov.opm.scrd.services.impl.reporting.ReportHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link MonthlyAdjustmentReportService} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class MonthlyAdjustmentReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>MonthlyAdjustmentReportService</code> instance used in tests.
     * </p>
     */
    private MonthlyAdjustmentReportService instance;

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
        return new JUnit4TestAdapter(MonthlyAdjustmentReportServiceTests.class);
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

        instance = new MonthlyAdjustmentReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Adjustments Report");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(MonthlyAdjustmentReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        MonthlyAdjustmentReportResponse response = instance.getReport(createRequest());
        assertNotNull(response);
        // verify some values
        List<MonthlyAdjustmentReportResponseItem> items = response.getItems();
        assertNotNull(items);
        // sort by modified
        Collections.sort(items, new Comparator<MonthlyAdjustmentReportResponseItem>() {
            /**
             * Compare the beans for more accurate testing.
             *
             * @param o1 First bean for comparison
             * @param o2 Second bean for comparison
             * @return comparison result
             */
            @Override
            public int compare(MonthlyAdjustmentReportResponseItem o1, MonthlyAdjustmentReportResponseItem o2) {
                if (o1.getModifier() != null && o2.getModifier() != null) {
                    return o2.getModifier().compareTo(o1.getModifier());
                }
                return 0;
            }
        });
        assertEquals(17, items.size()); // should contain 17 items
        // verify at least one item
        MonthlyAdjustmentReportResponseItem item = items.get(0);
        assertEquals("USER2", item.getModifier());
        assertEquals("1111111", item.getAccountNumber());
        assertEquals("01/01/2014", ReportHelper.formatDate(item.getDate()));
        assertEquals("Master: First Name Changed from old value 6 to new value 6", item.getDescription());
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
        MonthlyAdjustmentReportResponse response = instance.getReport(createRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/MonthlyAdjustmentReportServiceTests.PDF";
        writeFile(exportReport, filename);
    }

    /**
     * Creates a test request.
     *
     * @return a test request
     */
    private MonthlyAdjustmentReportRequest createRequest() {
        MonthlyAdjustmentReportRequest request = new MonthlyAdjustmentReportRequest();
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
    public void test_exportReportRTF() throws Exception {
        MonthlyAdjustmentReportResponse response = instance.getReport(createRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/MonthlyAdjustmentReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}
