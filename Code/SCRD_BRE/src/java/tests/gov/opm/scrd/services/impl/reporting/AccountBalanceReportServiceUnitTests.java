/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.ReportGenerationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link AccountBalanceReportService}.
 *
 * @author RaitoShum
 * @version 1.0
 */
public class AccountBalanceReportServiceUnitTests extends BasePersistenceTests {
    /**
     * Represents the AccountBalanceReportService instance.
     */
    private AccountBalanceReportService service;

    /**
     * <p> Adapter for earlier versions of JUnit. </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AccountBalanceReportServiceUnitTests.class);
    }

    /**
     * Sets up the test environment.
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new AccountBalanceReportService();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.0#", symbols);
        decimalFormat.setParseBigDecimal(true);
        service.setAuditNumberFormat(decimalFormat);
        service.setAuditDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
        TestsHelper.initService(service, getEntityManager());
        createAccountReportData();
        
    }

    /**
     * Tests the getReport method with <code>null</code> request.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetReportWithNull() throws ReportGenerationException {
        service.getReport(null);
    }

    /**
     * Tests the getReport method.
     */
    @Test
    public void testGerReport() throws ReportGenerationException {
        AccountBalanceReportRequest request = new AccountBalanceReportRequest();
        request.setCsd("2");
        AccountBalanceReportResponse response = service.getReport(request);
        assertEquals("The reportname is incorrect.", response.getReportName(),
                AccountBalanceReportService.class.getSimpleName());
        assertTrue("The items size is incorrect.", response.getItems().keySet().size() == 1);
        for (Date date : response.getItems().keySet()) {
            AccountBalanceReportResponseItem item = response.getItems().get(date).get(0);
            assertEquals("The auditUser is incorrect.", item.getAuditUser(), "user2");
            assertEquals("The payCode is incorrect.", item.getPayCode(), "payCode");
            assertEquals("The accountStatus is incorrect.", item.getStatus(), "accountStatus");
            assertTrue("The lastPay is incorrect.", item.getLastPay().getTime() == date.getTime());
            assertEquals("The deposit is incorrect.", item.getDeposit().toString(), "30.00");
            assertEquals("The additionalInterest is incorrect.", item.getAdditionalInterest().toString(),
                    "20.00");
            assertEquals("The payment is incorrect.", item.getPayment().toString(), "10.00");
            assertEquals("The balance is incorrect.", item.getBalance().toString(), "40.00");
            assertEquals("The subTotals is incorrect.", item.getSubTotals().toString(), "40.00");
            assertEquals("The grandTotals is incorrect.", item.getGrandTotal().toString(), "40.00");
        }
    }

    /**
     * Tests the exportReport method with RTF file type.
     */
    @Test
    public void testExportReportRTF() throws ReportGenerationException,
            IOException, ParseException {
        TestsHelper.exportReport(service, createResponse(), ExportType.RTF);
    }

    /**
     * Tests the exportReport method with PDF file type.
     */
    @Test
    public void testExportReportPDF() throws ReportGenerationException,
            IOException, ParseException {
        TestsHelper.exportReport(service, createResponse(), ExportType.PDF);
    }

    /**
     * Creates a AccountBalanceReportResponse instance for test.
     *
     * @return the AccountBalanceReportResponse instance.
     */
    private static AccountBalanceReportResponse createResponse()
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        AccountBalanceReportResponse response = new AccountBalanceReportResponse();
        response.setReportGenerationDate(new Date());
        response.setReportName("Account Balance History");
        response.setCsd("123456");
        response.setItems(new HashMap<Date, List<AccountBalanceReportResponseItem>>());

        Date auditTime1 = dateFormat.parse("01/01/2014");
        ArrayList<AccountBalanceReportResponseItem> items1 = new ArrayList<AccountBalanceReportResponseItem>();
        items1.add(createItem(auditTime1));
        items1.add(createItem(auditTime1));

        Date auditTime2 = dateFormat.parse("02/01/2014");
        ArrayList<AccountBalanceReportResponseItem> items2 = new ArrayList<AccountBalanceReportResponseItem>();
        items2.add(createItem(auditTime2));
        items2.add(createItem(auditTime2));

        response.getItems().put(auditTime1, items1);
        response.getItems().put(auditTime2, items2);

        return response;
    }

    /**
     * Creates a AccountBalanceReportResponseItem instance for test.
     *
     * @param auditTime
     *         the audit time.
     * @return the AccountBalanceReportResponseItem instance.
     */
    private static AccountBalanceReportResponseItem createItem(Date auditTime) {
        AccountBalanceReportResponseItem item = new AccountBalanceReportResponseItem();
        String auditUser = "auditUser";
        String payCode = "C";
        Date lastPay = new Date();
        BigDecimal deposit = new BigDecimal("1.11");
        BigDecimal additionalInterest = new BigDecimal("2.22");
        BigDecimal payment = new BigDecimal("3.33");
        BigDecimal balance = new BigDecimal("4.44");
        BigDecimal subTotals = new BigDecimal("5.55");
        BigDecimal grandTotal = new BigDecimal("6.66");

        item.setAuditTime(auditTime);
        item.setAuditUser(auditUser);
        item.setPayCode(payCode);
        item.setLastPay(lastPay);
        item.setDeposit(deposit);
        item.setAdditionalInterest(additionalInterest);
        item.setPayment(payment);
        item.setBalance(balance);
        item.setSubTotals(subTotals);
        item.setGrandTotal(grandTotal);
        item.setStatus("Status");
        return item;
    }
}
