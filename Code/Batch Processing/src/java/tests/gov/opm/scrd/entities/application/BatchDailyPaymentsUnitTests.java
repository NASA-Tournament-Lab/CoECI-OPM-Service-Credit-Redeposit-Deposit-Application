/*
    Copyright 2014 OPM.gov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package gov.opm.scrd.entities.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.lookup.AccountStatus;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link BatchDailyPayments} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class BatchDailyPaymentsUnitTests {
    /**
     * <p>
     * Represents the <code>BatchDailyPayments</code> instance used in tests.
     * </p>
     */
    private BatchDailyPayments instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BatchDailyPaymentsUnitTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Before
    public void setUp() throws Exception {
        instance = new BatchDailyPayments();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>BatchDailyPayments()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new BatchDailyPayments();

        assertNull("'auditBatchLogId' should be correct.", TestsHelper.getField(instance, "auditBatchLogId"));
        assertNull("'payTransactionKey' should be correct.", TestsHelper.getField(instance, "payTransactionKey"));
        assertNull("'numberPaymentToday' should be correct.", TestsHelper.getField(instance, "numberPaymentToday"));
        assertNull("'batchTime' should be correct.", TestsHelper.getField(instance, "batchTime"));
        assertNull("'accountStatus' should be correct.", TestsHelper.getField(instance, "accountStatus"));
        assertNull("'payTransStatusCode' should be correct.", TestsHelper.getField(instance, "payTransStatusCode"));
        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'accountBalance' should be correct.", TestsHelper.getField(instance, "accountBalance"));
        assertNull("'overPaymentAmount' should be correct.", TestsHelper.getField(instance, "overPaymentAmount"));
        assertNull("'achPayment' should be correct.", TestsHelper.getField(instance, "achPayment"));
        assertNull("'achStopLetter' should be correct.", TestsHelper.getField(instance, "achStopLetter"));
        assertNull("'printInvoice' should be correct.", TestsHelper.getField(instance, "printInvoice"));
        assertNull("'refundRequired' should be correct.", TestsHelper.getField(instance, "refundRequired"));
        assertNull("'reversedPayment' should be correct.", TestsHelper.getField(instance, "reversedPayment"));
        assertNull("'updateToCompleted' should be correct.", TestsHelper.getField(instance, "updateToCompleted"));
        assertNull("'printInitialBill' should be correct.", TestsHelper.getField(instance, "printInitialBill"));
        assertNull("'latestBatch' should be correct.", TestsHelper.getField(instance, "latestBatch"));
        assertNull("'errorProcessing' should be correct.", TestsHelper.getField(instance, "errorProcessing"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getAuditBatchId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAuditBatchId() {
        Long value = 1L;
        instance.setAuditBatchId(value);

        assertEquals("'getAuditBatchId' should be correct.",
            value, instance.getAuditBatchId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAuditBatchId(Long auditBatchId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAuditBatchId() {
        Long value = 1L;
        instance.setAuditBatchId(value);

        assertEquals("'setAuditBatchId' should be correct.",
            value, TestsHelper.getField(instance, "auditBatchId"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPayTransactionKey()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPayTransactionKey() {
        Integer value = 1;
        instance.setPayTransactionKey(value);

        assertEquals("'getPayTransactionKey' should be correct.",
            value, instance.getPayTransactionKey());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPayTransactionKey(Integer payTransactionKey)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPayTransactionKey() {
        Integer value = 1;
        instance.setPayTransactionKey(value);

        assertEquals("'setPayTransactionKey' should be correct.",
            value, TestsHelper.getField(instance, "payTransactionKey"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumberPaymentToday()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumberPaymentToday() {
        Integer value = 1;
        instance.setNumberPaymentToday(value);

        assertEquals("'getNumberPaymentToday' should be correct.",
            value, instance.getNumberPaymentToday());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumberPaymentToday(Integer numberPaymentToday)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumberPaymentToday() {
        Integer value = 1;
        instance.setNumberPaymentToday(value);

        assertEquals("'setNumberPaymentToday' should be correct.",
            value, TestsHelper.getField(instance, "numberPaymentToday"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBatchTime()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBatchTime() {
        Date value = new Date();
        instance.setBatchTime(value);

        assertSame("'getBatchTime' should be correct.",
            value, instance.getBatchTime());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBatchTime(Date batchTime)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBatchTime() {
        Date value = new Date();
        instance.setBatchTime(value);

        assertSame("'setBatchTime' should be correct.",
            value, TestsHelper.getField(instance, "batchTime"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccountStatus()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccountStatus() {
        AccountStatus value = new AccountStatus();
        instance.setAccountStatus(value);

        assertSame("'getAccountStatus' should be correct.",
            value, instance.getAccountStatus());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccountStatus(AccountStatus accountStatus)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccountStatus() {
        AccountStatus value = new AccountStatus();
        instance.setAccountStatus(value);

        assertSame("'setAccountStatus' should be correct.",
            value, TestsHelper.getField(instance, "accountStatus"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPayTransStatusCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPayTransStatusCode() {
        Integer value = 1;
        instance.setPayTransStatusCode(value);

        assertEquals("'getPayTransStatusCode' should be correct.",
            value, instance.getPayTransStatusCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPayTransStatusCode(Integer payTransStatusCode)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPayTransStatusCode() {
        Integer value = 1;
        instance.setPayTransStatusCode(value);

        assertEquals("'setPayTransStatusCode' should be correct.",
            value, TestsHelper.getField(instance, "payTransStatusCode"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getClaimNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getClaimNumber() {
        String value = "new_value";
        instance.setClaimNumber(value);

        assertEquals("'getClaimNumber' should be correct.",
            value, instance.getClaimNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setClaimNumber(String claimNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setClaimNumber() {
        String value = "new_value";
        instance.setClaimNumber(value);

        assertEquals("'setClaimNumber' should be correct.",
            value, TestsHelper.getField(instance, "claimNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccountBalance()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccountBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccountBalance(value);

        assertSame("'getAccountBalance' should be correct.",
            value, instance.getAccountBalance());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccountBalance(BigDecimal accountBalance)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccountBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccountBalance(value);

        assertSame("'setAccountBalance' should be correct.",
            value, TestsHelper.getField(instance, "accountBalance"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getOverPaymentAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getOverPaymentAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setOverPaymentAmount(value);

        assertSame("'getOverPaymentAmount' should be correct.",
            value, instance.getOverPaymentAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setOverPaymentAmount(BigDecimal overPaymentAmount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setOverPaymentAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setOverPaymentAmount(value);

        assertSame("'setOverPaymentAmount' should be correct.",
            value, TestsHelper.getField(instance, "overPaymentAmount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAchPayment()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAchPayment() {
        Boolean value = true;
        instance.setAchPayment(value);

        assertEquals("'getAchPayment' should be correct.",
            value, instance.getAchPayment());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAchPayment(Boolean achPayment)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAchPayment() {
        Boolean value = true;
        instance.setAchPayment(value);

        assertEquals("'setAchPayment' should be correct.",
            value, TestsHelper.getField(instance, "achPayment"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAchStopLetter()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAchStopLetter() {
        Boolean value = true;
        instance.setAchStopLetter(value);

        assertEquals("'getAchStopLetter' should be correct.",
            value, instance.getAchStopLetter());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAchStopLetter(Boolean achStopLetter)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAchStopLetter() {
        Boolean value = true;
        instance.setAchStopLetter(value);

        assertEquals("'setAchStopLetter' should be correct.",
            value, TestsHelper.getField(instance, "achStopLetter"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPrintInvoice()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPrintInvoice() {
        Boolean value = true;
        instance.setPrintInvoice(value);

        assertEquals("'getPrintInvoice' should be correct.",
            value, instance.getPrintInvoice());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPrintInvoice(Boolean printInvoice)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPrintInvoice() {
        Boolean value = true;
        instance.setPrintInvoice(value);

        assertEquals("'setPrintInvoice' should be correct.",
            value, TestsHelper.getField(instance, "printInvoice"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRefundRequired()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRefundRequired() {
        Boolean value = true;
        instance.setRefundRequired(value);

        assertEquals("'getRefundRequired' should be correct.",
            value, instance.getRefundRequired());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRefundRequired(Boolean refundRequired)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRefundRequired() {
        Boolean value = true;
        instance.setRefundRequired(value);

        assertEquals("'setRefundRequired' should be correct.",
            value, TestsHelper.getField(instance, "refundRequired"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReversedPayment()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getReversedPayment() {
        Boolean value = true;
        instance.setReversedPayment(value);

        assertEquals("'getReversedPayment' should be correct.",
            value, instance.getReversedPayment());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setReversedPayment(Boolean reversedPayment)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setReversedPayment() {
        Boolean value = true;
        instance.setReversedPayment(value);

        assertEquals("'setReversedPayment' should be correct.",
            value, TestsHelper.getField(instance, "reversedPayment"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getUpdateToCompleted()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getUpdateToCompleted() {
        Boolean value = true;
        instance.setUpdateToCompleted(value);

        assertEquals("'getUpdateToCompleted' should be correct.",
            value, instance.getUpdateToCompleted());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setUpdateToCompleted(Boolean updateToCompleted)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setUpdateToCompleted() {
        Boolean value = true;
        instance.setUpdateToCompleted(value);

        assertEquals("'setUpdateToCompleted' should be correct.",
            value, TestsHelper.getField(instance, "updateToCompleted"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPrintInitialBill()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPrintInitialBill() {
        Boolean value = true;
        instance.setPrintInitialBill(value);

        assertEquals("'getPrintInitialBill' should be correct.",
            value, instance.getPrintInitialBill());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPrintInitialBill(Boolean printInitialBill)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPrintInitialBill() {
        Boolean value = true;
        instance.setPrintInitialBill(value);

        assertEquals("'setPrintInitialBill' should be correct.",
            value, TestsHelper.getField(instance, "printInitialBill"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLatestBatch()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLatestBatch() {
        Boolean value = true;
        instance.setLatestBatch(value);

        assertEquals("'getLatestBatch' should be correct.",
            value, instance.getLatestBatch());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLatestBatch(Boolean latestBatch)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLatestBatch() {
        Boolean value = true;
        instance.setLatestBatch(value);

        assertEquals("'setLatestBatch' should be correct.",
            value, TestsHelper.getField(instance, "latestBatch"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getErrorProcessing()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getErrorProcessing() {
        Boolean value = true;
        instance.setErrorProcessing(value);

        assertEquals("'getErrorProcessing' should be correct.",
            value, instance.getErrorProcessing());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setErrorProcessing(Boolean errorProcessing)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setErrorProcessing() {
        Boolean value = true;
        instance.setErrorProcessing(value);

        assertEquals("'setErrorProcessing' should be correct.",
            value, TestsHelper.getField(instance, "errorProcessing"));
    }
}
