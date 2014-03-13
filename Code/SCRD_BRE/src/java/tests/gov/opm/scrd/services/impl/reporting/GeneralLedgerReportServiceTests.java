/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.impl.reporting.GeneralLedgerReportRequest;
import gov.opm.scrd.services.impl.reporting.GeneralLedgerReportResponse;
import gov.opm.scrd.services.impl.reporting.GeneralLedgerReportResponseItem;
import gov.opm.scrd.services.impl.reporting.GeneralLedgerReportService;
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
 * Unit tests for {@link GeneralLedgerReportService} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class GeneralLedgerReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>GeneralLedgerReportService</code> instance used in tests.
     * </p>
     */
    private GeneralLedgerReportService instance;

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
        return new JUnit4TestAdapter(GeneralLedgerReportServiceTests.class);
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

        instance = new GeneralLedgerReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "SYBAC Payment Details to General Ledger Report");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(GeneralLedgerReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        GeneralLedgerReportResponse response = instance.getReport(new GeneralLedgerReportRequest());
        assertNotNull(response);

        // verify some values
        List<GeneralLedgerReportResponseItem> items = response.getItems();
        // sort by plan
        Collections.sort(items, new Comparator<GeneralLedgerReportResponseItem>() {
            /**
             * Compare the beans for more accurate testing.
             *
             * @param o1 First bean for comparison
             * @param o2 Second bean for comparison
             * @return comparison result
             */
            @Override
            public int compare(GeneralLedgerReportResponseItem o1, GeneralLedgerReportResponseItem o2) {
                if (o1.getPlan() != null && o2.getPlan() != null) {
                    return o1.getPlan().compareTo(o2.getPlan());
                }
                return 0;
            }
        });
        assertNotNull(items);
        assertEquals(13, items.size()); // should contain 13 items
        // verify at least one item
        GeneralLedgerReportResponseItem item = items.get(0);
        assertEquals("CSRS", item.getPlan());
        assertEquals("$101.01", ReportHelper.formatMoney(item.getReceiptAmount()));
        assertEquals("1", ReportHelper.formatInteger(item.getPaymentsNumber()));
        assertEquals("01/02/1982", item.getPayments().get(0).getClaimantDateOfBirth());
        assertEquals("test claimant", item.getPayments().get(0).getClaimantName());
        assertEquals("2222222", item.getPayments().get(0).getCsd());
        assertEquals("$101.01", ReportHelper.formatMoney(item.getPayments().get(0).getPaymentAmount()));
        assertEquals("01/02/2014", ReportHelper.formatDate(item.getPayments().get(0).getPaymentDate()));
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
        GeneralLedgerReportResponse response = instance.getReport(new GeneralLedgerReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/GeneralLedgerReportServiceTests.PDF";
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
        GeneralLedgerReportResponse response = instance.getReport(new GeneralLedgerReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/GeneralLedgerReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}
