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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.entities.lookup.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link PendingPayment} class.
 * </p>
 *
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Updated test cases for claimNumber.</li>
 * </ol>
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class PendingPaymentUnitTests {
    /**
     * <p>
     * Represents the <code>PendingPayment</code> instance used in tests.
     * </p>
     */
    private PendingPayment instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PendingPaymentUnitTests.class);
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
        instance = new PendingPayment();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>PendingPayment()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new PendingPayment();

        assertNull("'paymentStatus' should be correct.", TestsHelper.getField(instance, "paymentStatus"));
        assertNull("'batchNumber' should be correct.", TestsHelper.getField(instance, "batchNumber"));
        assertNull("'blockNumber' should be correct.", TestsHelper.getField(instance, "blockNumber"));
        assertNull("'sequenceNumber' should be correct.", TestsHelper.getField(instance, "sequenceNumber"));
        assertNull("'transactionDate' should be correct.", TestsHelper.getField(instance, "transactionDate"));
        assertNull("'statusDate' should be correct.", TestsHelper.getField(instance, "statusDate"));
        assertNull("'amount' should be correct.", TestsHelper.getField(instance, "amount"));
        assertFalse("'applyToGL' should be correct.", (Boolean) TestsHelper.getField(instance, "applyToGL"));
        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'accountHolderBirthdate' should be correct.",
            TestsHelper.getField(instance, "accountHolderBirthdate"));
        assertNull("'accountStatus' should be correct.", TestsHelper.getField(instance, "accountStatus"));
        assertNull("'approvalUser' should be correct.", TestsHelper.getField(instance, "approvalUser"));
        assertNull("'approvalStatus' should be correct.", TestsHelper.getField(instance, "approvalStatus"));
        assertEquals("'paymentId' should be correct.", 0L, TestsHelper.getField(instance, "paymentId"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getPaymentStatus()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentStatus() {
        PaymentStatus value = new PaymentStatus();
        instance.setPaymentStatus(value);

        assertSame("'getPaymentStatus' should be correct.",
            value, instance.getPaymentStatus());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentStatus(PaymentStatus paymentStatus)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentStatus() {
        PaymentStatus value = new PaymentStatus();
        instance.setPaymentStatus(value);

        assertSame("'setPaymentStatus' should be correct.",
            value, TestsHelper.getField(instance, "paymentStatus"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBatchNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBatchNumber() {
        String value = "new_value";
        instance.setBatchNumber(value);

        assertEquals("'getBatchNumber' should be correct.",
            value, instance.getBatchNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBatchNumber(String batchNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBatchNumber() {
        String value = "new_value";
        instance.setBatchNumber(value);

        assertEquals("'setBatchNumber' should be correct.",
            value, TestsHelper.getField(instance, "batchNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBlockNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBlockNumber() {
        String value = "new_value";
        instance.setBlockNumber(value);

        assertEquals("'getBlockNumber' should be correct.",
            value, instance.getBlockNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBlockNumber(String blockNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBlockNumber() {
        String value = "new_value";
        instance.setBlockNumber(value);

        assertEquals("'setBlockNumber' should be correct.",
            value, TestsHelper.getField(instance, "blockNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSequenceNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSequenceNumber() {
        String value = "new_value";
        instance.setSequenceNumber(value);

        assertEquals("'getSequenceNumber' should be correct.",
            value, instance.getSequenceNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSequenceNumber(String sequenceNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSequenceNumber() {
        String value = "new_value";
        instance.setSequenceNumber(value);

        assertEquals("'setSequenceNumber' should be correct.",
            value, TestsHelper.getField(instance, "sequenceNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTransactionDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTransactionDate() {
        Date value = new Date();
        instance.setTransactionDate(value);

        assertSame("'getTransactionDate' should be correct.",
            value, instance.getTransactionDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTransactionDate(Date transactionDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTransactionDate() {
        Date value = new Date();
        instance.setTransactionDate(value);

        assertSame("'setTransactionDate' should be correct.",
            value, TestsHelper.getField(instance, "transactionDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStatusDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStatusDate() {
        Date value = new Date();
        instance.setStatusDate(value);

        assertSame("'getStatusDate' should be correct.",
            value, instance.getStatusDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStatusDate(Date statusDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStatusDate() {
        Date value = new Date();
        instance.setStatusDate(value);

        assertSame("'setStatusDate' should be correct.",
            value, TestsHelper.getField(instance, "statusDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setAmount(value);

        assertSame("'getAmount' should be correct.",
            value, instance.getAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAmount(BigDecimal amount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setAmount(value);

        assertSame("'setAmount' should be correct.",
            value, TestsHelper.getField(instance, "amount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isApplyToGL()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isApplyToGL() {
        boolean value = true;
        instance.setApplyToGL(value);

        assertTrue("'isApplyToGL' should be correct.", instance.isApplyToGL());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setApplyToGL(boolean applyToGL)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setApplyToGL() {
        boolean value = true;
        instance.setApplyToGL(value);

        assertTrue("'setApplyToGL' should be correct.",
            (Boolean) TestsHelper.getField(instance, "applyToGL"));
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
     * Accuracy test for the method <code>getAccountHolderBirthdate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccountHolderBirthdate() {
        Date value = new Date();
        instance.setAccountHolderBirthdate(value);

        assertSame("'getAccountHolderBirthdate' should be correct.",
            value, instance.getAccountHolderBirthdate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccountHolderBirthdate(Date accountHolderBirthdate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccountHolderBirthdate() {
        Date value = new Date();
        instance.setAccountHolderBirthdate(value);

        assertSame("'setAccountHolderBirthdate' should be correct.",
            value, TestsHelper.getField(instance, "accountHolderBirthdate"));
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
     * Accuracy test for the method <code>getApprovalUser()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getApprovalUser() {
        String value = "new_value";
        instance.setApprovalUser(value);

        assertEquals("'getApprovalUser' should be correct.",
            value, instance.getApprovalUser());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setApprovalUser(String approvalUser)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setApprovalUser() {
        String value = "new_value";
        instance.setApprovalUser(value);

        assertEquals("'setApprovalUser' should be correct.",
            value, TestsHelper.getField(instance, "approvalUser"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getApprovalStatus()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getApprovalStatus() {
        ApprovalStatus value = ApprovalStatus.APPROVED;
        instance.setApprovalStatus(value);

        assertEquals("'getApprovalStatus' should be correct.",
            value, instance.getApprovalStatus());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setApprovalStatus(ApprovalStatus approvalStatus)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setApprovalStatus() {
        ApprovalStatus value = ApprovalStatus.APPROVED;
        instance.setApprovalStatus(value);

        assertEquals("'setApprovalStatus' should be correct.",
            value, TestsHelper.getField(instance, "approvalStatus"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentId() {
        long value = 1L;
        instance.setPaymentId(value);

        assertEquals("'getPaymentId' should be correct.",
            value, instance.getPaymentId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentId(long paymentId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentId() {
        long value = 1L;
        instance.setPaymentId(value);

        assertEquals("'setPaymentId' should be correct.",
            value, TestsHelper.getField(instance, "paymentId"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>toString()</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_toString() {
        instance.setPaymentId(1);

        String content = instance.toString();
        assertTrue("'toString' should be correct.", content.contains("\"paymentId\":1"));
    }
}
