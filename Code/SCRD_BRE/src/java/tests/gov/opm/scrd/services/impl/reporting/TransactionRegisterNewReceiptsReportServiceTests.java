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
import gov.opm.scrd.services.impl.reporting.ReportHelper;
import gov.opm.scrd.services.impl.reporting.TransactionRegisterNewReceiptsReportRequest;
import gov.opm.scrd.services.impl.reporting.TransactionRegisterNewReceiptsReportResponse;
import gov.opm.scrd.services.impl.reporting.TransactionRegisterNewReceiptsReportResponseItem;
import gov.opm.scrd.services.impl.reporting.TransactionRegisterNewReceiptsReportService;

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
 * Unit tests for {@link TransactionRegisterNewReceiptsReportService} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class TransactionRegisterNewReceiptsReportServiceTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>TransactionRegisterNewReceiptsReportService</code> instance used in tests.
     * </p>
     */
    private TransactionRegisterNewReceiptsReportService instance;

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
        return new JUnit4TestAdapter(TransactionRegisterNewReceiptsReportServiceTests.class);
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

        instance = new TransactionRegisterNewReceiptsReportService();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "reportName", "SYBAC Transaction Register");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReport(TransactionRegisterNewReceiptsReportRequest request)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception to JUnit.
     */
    @Test
    public void test_getReport() throws Exception {
        TransactionRegisterNewReceiptsReportResponse response = instance
            .getReport(new TransactionRegisterNewReceiptsReportRequest());
        assertNotNull(response);
        // verify some values
        List<TransactionRegisterNewReceiptsReportResponseItem> items = response.getItems();
        assertNotNull(items);
        // sort by CSD
        Collections.sort(items, new Comparator<TransactionRegisterNewReceiptsReportResponseItem>() {
            /**
             * Compare the beans for more accurate testing.
             *
             * @param o1 First bean for comparison
             * @param o2 Second bean for comparison
             * @return comparison result
             */
            @Override
            public int compare(TransactionRegisterNewReceiptsReportResponseItem o1,
                TransactionRegisterNewReceiptsReportResponseItem o2) {
                if (o1.getCsd() != null && o2.getCsd() != null) {
                    return o2.getCsd().compareTo(o1.getCsd());
                }
                return 0;
            }
        });
        assertTrue(items.size() == 56); // should contain 55 items
        // verify at least one item
        TransactionRegisterNewReceiptsReportResponseItem item = items.get(0);
        assertEquals("3333333", item.getCsd());
        assertEquals("claimant 3", item.getClaimantName());
        assertEquals("FERS", item.getRetirementType());
        assertEquals("$50.50", ReportHelper.formatMoney(item.getFers()));
        assertEquals("$101.01", ReportHelper.formatMoney(item.getAmount()));
        assertEquals("$100.00", ReportHelper.formatMoney(item.getDepositsPost982()));
        assertEquals("$100.00", ReportHelper.formatMoney(item.getDepositsPre1082()));
        assertEquals("$100.00", ReportHelper.formatMoney(item.getRedepositsPost982()));
        assertEquals("$100.00", ReportHelper.formatMoney(item.getRedepositsPre1082()));
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
        TransactionRegisterNewReceiptsReportResponse response = instance
            .getReport(new TransactionRegisterNewReceiptsReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.PDF);
        String filename = "log/TransactionRegisterNewReceiptsReportServiceTests.PDF";
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
        TransactionRegisterNewReceiptsReportResponse response = instance
            .getReport(new TransactionRegisterNewReceiptsReportRequest());
        byte[] exportReport = instance.exportReport(response, ExportType.RTF);
        String filename = "log/TransactionRegisterNewReceiptsReportServiceTests.RTF";
        writeFile(exportReport, filename);
    }
}
