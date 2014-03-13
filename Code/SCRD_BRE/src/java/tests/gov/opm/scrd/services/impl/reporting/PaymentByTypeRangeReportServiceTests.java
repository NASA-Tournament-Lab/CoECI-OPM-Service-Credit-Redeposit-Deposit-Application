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
import gov.opm.scrd.services.impl.reporting.PaymentByTypeRangeReportRequest;
import gov.opm.scrd.services.impl.reporting.PaymentByTypeRangeReportResponse;
import gov.opm.scrd.services.impl.reporting.PaymentByTypeRangeReportResponseItem;
import gov.opm.scrd.services.impl.reporting.PaymentByTypeRangeReportService;
import gov.opm.scrd.services.impl.reporting.ReportHelper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link PaymentByTypeRangeReportService} class.
 * </p>
 *
 * @author j3_guile
 * @version 1.0
 */
public class PaymentByTypeRangeReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>PaymentByTypeRangeReportService</code> instance used in tests.
     * </p>
     */
    private PaymentByTypeRangeReportService instance;

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
        return new JUnit4TestAdapter(PaymentByTypeRangeReportServiceTests.class);
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

        instance = new PaymentByTypeRangeReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Current Suspense");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(PaymentByTypeRangeReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        PaymentByTypeRangeReportRequest request = new PaymentByTypeRangeReportRequest();
        request.setStartDate(new Date(0));
        request.setEndDate(new Date());
        PaymentByTypeRangeReportResponse response = instance.getReport(request);
        assertNotNull(response);

        // verify some values
        List<PaymentByTypeRangeReportResponseItem> items = response.getItems();
        assertNotNull(items);
        assertTrue(items.size() == 2); // should contain 2 items

        // sort
        Collections.sort(items, new Comparator<PaymentByTypeRangeReportResponseItem>() {
            @Override
            public int compare(PaymentByTypeRangeReportResponseItem o1, PaymentByTypeRangeReportResponseItem o2) {
                if (o1.getPaymentAmount() != null && o2.getPaymentAmount() != null) {
                    return o1.getPaymentAmount().compareTo(o2.getPaymentAmount());
                }
                return 0;
            }
        });
        PaymentByTypeRangeReportResponseItem item = items.get(0);
        assertEquals("1111111", item.getCsd());
        assertEquals(ReportHelper.formatMoney(new BigDecimal(20.00)),
            ReportHelper.formatMoney(item.getPaymentAmount()));
        assertEquals("01/01/2014", ReportHelper.formatDate(item.getDate()));
        assertEquals("Ret Type 1", item.getRetirementType());
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
        PaymentByTypeRangeReportRequest request = new PaymentByTypeRangeReportRequest();
        request.setStartDate(new Date(0));
        request.setEndDate(new Date());

        PaymentByTypeRangeReportResponse response = instance.getReport(request);
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/PaymentByTypeRangeReportServiceTests.PDF";
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
        PaymentByTypeRangeReportRequest request = new PaymentByTypeRangeReportRequest();
        request.setStartDate(new Date(0));
        request.setEndDate(new Date());

        PaymentByTypeRangeReportResponse response = instance.getReport(request);
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/PaymentByTypeRangeReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}