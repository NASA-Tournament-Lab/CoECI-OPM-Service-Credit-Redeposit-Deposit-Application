/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.impl.reporting.ChangeHistoryReportRequest;
import gov.opm.scrd.services.impl.reporting.ChangeHistoryReportResponse;
import gov.opm.scrd.services.impl.reporting.ChangeHistoryReportResponseItem;
import gov.opm.scrd.services.impl.reporting.ChangeHistoryReportService;
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
 * Unit tests for {@link ChangeHistoryReportService} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class ChangeHistoryReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>ChangeHistoryReportService</code> instance used in tests.
     * </p>
     */
    private ChangeHistoryReportService instance;

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
        return new JUnit4TestAdapter(ChangeHistoryReportServiceTests.class);
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

        instance = new ChangeHistoryReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Service Credit Change History Report");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(ChangeHistoryReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        ChangeHistoryReportResponse response = instance.getReport(createRequest());
        assertNotNull(response);

        // verify some values
        List<ChangeHistoryReportResponseItem> items = response.getItems();
        assertNotNull(items);
        // sort by modified
        Collections.sort(items, new Comparator<ChangeHistoryReportResponseItem>() {
            /**
             * Compare the beans for more accurate testing.
             *
             * @param o1 First bean for comparison
             * @param o2 Second bean for comparison
             * @return comparison result
             */
            @Override
            public int compare(ChangeHistoryReportResponseItem o1, ChangeHistoryReportResponseItem o2) {
                if (o1.getModified() != null && o2.getModified() != null) {
                    return o1.getModified().compareTo(o2.getModified());
                }
                return 0;
            }
        });
        assertEquals(16, items.size()); // should contain 16 items
        // verify at least one item
        ChangeHistoryReportResponseItem item = items.get(0);
        assertEquals("USER1", item.getModified());
        assertEquals("01/01/2014", ReportHelper.formatDate(item.getDate()));
        assertEquals("Master: Post 9/82 Redeposit Changed from old value 4 to new value 4", item.getDescription());
    }

    /**
     * Creates a test request.
     *
     * @return a test request
     */
    private ChangeHistoryReportRequest createRequest() {
        ChangeHistoryReportRequest request = new ChangeHistoryReportRequest();
        request.setCsd("1111111");
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
        ChangeHistoryReportResponse response = instance.getReport(createRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/ChangeHistoryReportServiceTests.PDF";
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
        ChangeHistoryReportResponse response = instance.getReport(createRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/ChangeHistoryReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}
