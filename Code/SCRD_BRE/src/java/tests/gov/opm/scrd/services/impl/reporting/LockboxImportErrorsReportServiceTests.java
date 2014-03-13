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
import gov.opm.scrd.services.impl.reporting.LockboxImportErrorsReportRequest;
import gov.opm.scrd.services.impl.reporting.LockboxImportErrorsReportResponse;
import gov.opm.scrd.services.impl.reporting.LockboxImportErrorsReportResponseItem;
import gov.opm.scrd.services.impl.reporting.LockboxImportErrorsReportService;
import gov.opm.scrd.services.impl.reporting.ReportHelper;

import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link LockboxImportErrorsReportService} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class LockboxImportErrorsReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>LockboxImportErrorsReportService</code> instance used in tests.
     * </p>
     */
    private LockboxImportErrorsReportService instance;

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
        return new JUnit4TestAdapter(LockboxImportErrorsReportServiceTests.class);
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

        instance = new LockboxImportErrorsReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Lock Box Import Errors");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(LockboxImportErrorsReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        LockboxImportErrorsReportResponse response = instance.getReport(new LockboxImportErrorsReportRequest());
        assertNotNull(response);
        assertEquals("01/01/2014", ReportHelper.formatDate(response.getImportDate()));
        assertEquals("1", response.getBatchNumber());
        // verify some values
        List<LockboxImportErrorsReportResponseItem> items = response.getItems();
        assertNotNull(items);
        assertTrue(items.size() == 3); // should contain 3 items
        // verify at least one item
        LockboxImportErrorsReportResponseItem item = items.get(0);
        assertEquals("R", item.getErrorCode());
        assertEquals("R60610201 915785010100 Bad Address or name", item.getErrorMessage());
        assertEquals("ValidC_NoChangeOccurred: Change command did not update database for CSD #",
            item.getDescription());
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
        LockboxImportErrorsReportResponse response = instance.getReport(new LockboxImportErrorsReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/LockboxImportErrorsReportServiceTests.PDF";
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
        LockboxImportErrorsReportResponse response = instance.getReport(new LockboxImportErrorsReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/LockboxImportErrorsReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}
