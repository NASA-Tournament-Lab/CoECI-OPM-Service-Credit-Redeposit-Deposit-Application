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
import gov.opm.scrd.services.impl.AccountServiceImpl;
import gov.opm.scrd.services.impl.reporting.PaymentHistoryReportRequest;
import gov.opm.scrd.services.impl.reporting.PaymentHistoryReportResponse;
import gov.opm.scrd.services.impl.reporting.PaymentHistoryReportResponseItem;
import gov.opm.scrd.services.impl.reporting.PaymentHistoryReportService;
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
 * Unit tests for {@link PaymentHistoryReportService} class.
 * </p>
 *
 * @author j3_guile
 * @version 1.0
 */
public class PaymentHistoryReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>PaymentHistoryReportService</code> instance used in tests.
     * </p>
     */
    private PaymentHistoryReportService instance;

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
        return new JUnit4TestAdapter(PaymentHistoryReportServiceTests.class);
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

        instance = new PaymentHistoryReportService();

        AccountServiceImpl as = new AccountServiceImpl();
        TestsHelper.setField(as, "logger", logger);
        TestsHelper.setField(as, "entityManager", entityManager);

        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Payment Transaction History");
        TestsHelper.setField(instance, "accountService", as);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(PaymentHistoryReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        PaymentHistoryReportRequest request = new PaymentHistoryReportRequest();
        request.setCsd("1111111");
        PaymentHistoryReportResponse response = instance.getReport(request);
        assertNotNull(response);

        // verify some values
        List<PaymentHistoryReportResponseItem> items = response.getItems();
        assertNotNull(items);
        assertTrue(items.size() == 2); // should contain 2 items

        // sort
        Collections.sort(items, new Comparator<PaymentHistoryReportResponseItem>() {
            @Override
            public int compare(PaymentHistoryReportResponseItem o1, PaymentHistoryReportResponseItem o2) {
                if (o1.getPaymentAmount() != null && o2.getPaymentAmount() != null) {
                    return o1.getPaymentAmount().compareTo(o2.getPaymentAmount());
                }
                return 0;
            }
        });
        PaymentHistoryReportResponseItem item = items.get(0);
        assertEquals(ReportHelper.formatMoney(new BigDecimal(40.00)),
            ReportHelper.formatMoney(item.getBalanceDueAfterPayment()));
        assertEquals(ReportHelper.formatMoney(new BigDecimal(55.00)),
            ReportHelper.formatMoney(item.getBalanceDueBeforePayment()));
        assertEquals(ReportHelper.formatMoney(new BigDecimal(60.00)),
            ReportHelper.formatMoney(item.getDueBeforePayment()));
        assertEquals(ReportHelper.formatMoney(new BigDecimal(5.00)),
            ReportHelper.formatMoney(item.getInterestOnPrior()));
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
        PaymentHistoryReportRequest request = new PaymentHistoryReportRequest();
        request.setCsd("1111111");
        PaymentHistoryReportResponse response = instance.getReport(request);
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/PaymentHistoryReportServiceTests.PDF";
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
        PaymentHistoryReportRequest request = new PaymentHistoryReportRequest();
        request.setCsd("1111111");
        PaymentHistoryReportResponse response = instance.getReport(request);
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/PaymentHistoryReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}