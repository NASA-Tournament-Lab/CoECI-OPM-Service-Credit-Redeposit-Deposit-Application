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
import gov.opm.scrd.services.impl.reporting.ActiveCreditBalancesReportRequest;
import gov.opm.scrd.services.impl.reporting.ActiveCreditBalancesReportResponse;
import gov.opm.scrd.services.impl.reporting.ActiveCreditBalancesReportResponseItem;
import gov.opm.scrd.services.impl.reporting.ActiveCreditBalancesReportService;
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
 * Unit tests for {@link ActiveCreditBalancesReportService} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class ActiveCreditBalancesReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>ActiveCreditBalancesReportService</code> instance used in tests.
     * </p>
     */
    private ActiveCreditBalancesReportService instance;

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
        return new JUnit4TestAdapter(ActiveCreditBalancesReportServiceTests.class);
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

        instance = new ActiveCreditBalancesReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "Active Credit Balances Over Twenty-Five Dollars");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(ActiveCreditBalancesReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        ActiveCreditBalancesReportResponse response = instance.getReport(new ActiveCreditBalancesReportRequest());
        assertNotNull(response);

        // verify some values
        List<ActiveCreditBalancesReportResponseItem> items = response.getItems();
        // sort by credit balance
        Collections.sort(items, new Comparator<ActiveCreditBalancesReportResponseItem>() {
            /**
             * Compare the beans for more accurate testing.
             *
             * @param o1 First bean for comparison
             * @param o2 Second bean for comparison
             * @return comparison result
             */
            @Override
            public int compare(ActiveCreditBalancesReportResponseItem o1, ActiveCreditBalancesReportResponseItem o2) {
                if (o1.getCreditBalance() != null && o2.getCreditBalance() != null) {
                    return o2.getCreditBalance().compareTo(o1.getCreditBalance());
                }
                return 0;
            }
        });
        assertNotNull(items);
        assertTrue(items.size() == 56); // should contain 55 items
        // verify at least one item
        ActiveCreditBalancesReportResponseItem item = items.get(0);
        assertEquals("2222222", item.getCsd());
        assertEquals("$100.00", ReportHelper.formatMoney(item.getCreditBalance()));
        assertEquals("01/02/2014", ReportHelper.formatDate(item.getDateOfOverPayment()));
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
        ActiveCreditBalancesReportResponse response = instance.getReport(new ActiveCreditBalancesReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/ActiveCreditBalancesReportServiceTests.PDF";
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
        ActiveCreditBalancesReportResponse response = instance.getReport(new ActiveCreditBalancesReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/ActiveCreditBalancesReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}
