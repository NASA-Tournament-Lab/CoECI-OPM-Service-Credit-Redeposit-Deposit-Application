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
import gov.opm.scrd.services.impl.reporting.ManualPaymentReportRequest;
import gov.opm.scrd.services.impl.reporting.ManualPaymentReportResponse;
import gov.opm.scrd.services.impl.reporting.ManualPaymentReportResponseItem;
import gov.opm.scrd.services.impl.reporting.ManualPaymentReportService;
import gov.opm.scrd.services.impl.reporting.ReportHelper;

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
 * Unit tests for {@link ManualPaymentReportService} class.
 * </p>
 *
 * @author j3_guile
 * @version 1.0
 */
public class ManualPaymentReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>ManualPaymentReportService</code> instance used in tests.
     * </p>
     */
    private ManualPaymentReportService instance;

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
        return new JUnit4TestAdapter(ManualPaymentReportServiceTests.class);
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

        instance = new ManualPaymentReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Manual Payments");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(ManualPaymentReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        ManualPaymentReportResponse response = instance.getReport(new ManualPaymentReportRequest());
        assertNotNull(response);

        // verify some values
        List<ManualPaymentReportResponseItem> items = response.getItems();
        // sort by BatBlkSeq
        Collections.sort(items, new Comparator<ManualPaymentReportResponseItem>() {
            @Override
            public int compare(ManualPaymentReportResponseItem o1, ManualPaymentReportResponseItem o2) {
                if (o1.getBatchNumber() != null && o2.getBatchNumber() != null) {
                    return o1.getBatchNumber().compareTo(o2.getBatchNumber());
                }
                return 0;
            }
        });
        assertNotNull(items);
        assertTrue(items.size() == 4); // should contain 4 items
        // verify at least one item
        ManualPaymentReportResponseItem item = items.get(0);
        assertEquals("1111111", item.getCsd());
        assertEquals("Posted - Complete", item.getPaymentStatusDescription());
        assertEquals(ReportHelper.formatMoney(new BigDecimal(4321.76)),
            ReportHelper.formatMoney(item.getPaymentAmount()));
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
        ManualPaymentReportResponse response = instance.getReport(new ManualPaymentReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/ManualPaymentReportServiceTests.PDF";
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
        ManualPaymentReportResponse response = instance.getReport(new ManualPaymentReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/ManualPaymentReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}