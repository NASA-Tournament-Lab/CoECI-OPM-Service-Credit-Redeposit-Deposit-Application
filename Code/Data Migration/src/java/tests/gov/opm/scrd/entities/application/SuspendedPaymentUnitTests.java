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
import gov.opm.scrd.entities.lookup.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link SuspendedPayment} class.
 * </p>
 *
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Updated test cases for claimNumber and masterClaimNumber.</li>
 * </ol>
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class SuspendedPaymentUnitTests {
    /**
     * <p>
     * Represents the <code>SuspendedPayment</code> instance used in tests.
     * </p>
     */
    private SuspendedPayment instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(SuspendedPaymentUnitTests.class);
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
        instance = new SuspendedPayment();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>SuspendedPayment()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new SuspendedPayment();

        assertNull("'paymentStatus' should be correct.", TestsHelper.getField(instance, "paymentStatus"));
        assertNull("'depositDate' should be correct.", TestsHelper.getField(instance, "depositDate"));
        assertNull("'batchNumber' should be correct.", TestsHelper.getField(instance, "batchNumber"));
        assertNull("'blockNumber' should be correct.", TestsHelper.getField(instance, "blockNumber"));
        assertNull("'sequenceNumber' should be correct.", TestsHelper.getField(instance, "sequenceNumber"));
        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertFalse("'ach' should be correct.", (Boolean) TestsHelper.getField(instance, "ach"));
        assertNull("'amount' should be correct.", TestsHelper.getField(instance, "amount"));
        assertNull("'claimantBirthdate' should be correct.", TestsHelper.getField(instance, "claimantBirthdate"));
        assertNull("'claimant' should be correct.", TestsHelper.getField(instance, "claimant"));
        assertNull("'accountBalance' should be correct.", TestsHelper.getField(instance, "accountBalance"));
        assertNull("'accountStatus' should be correct.", TestsHelper.getField(instance, "accountStatus"));
        assertNull("'masterClaimNumber' should be correct.", TestsHelper.getField(instance, "masterClaimNumber"));
        assertNull("'masterClaimantBirthdate' should be correct.",
            TestsHelper.getField(instance, "masterClaimantBirthdate"));
        assertNull("'masterAccountStatus' should be correct.", TestsHelper.getField(instance, "masterAccountStatus"));
        assertNull("'masterAccountBalance' should be correct.", TestsHelper.getField(instance, "masterAccountBalance"));
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
     * Accuracy test for the method <code>getDepositDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDepositDate() {
        Date value = new Date();
        instance.setDepositDate(value);

        assertSame("'getDepositDate' should be correct.",
            value, instance.getDepositDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDepositDate(Date depositDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDepositDate() {
        Date value = new Date();
        instance.setDepositDate(value);

        assertSame("'setDepositDate' should be correct.",
            value, TestsHelper.getField(instance, "depositDate"));
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
     * Accuracy test for the method <code>isAch()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isAch() {
        boolean value = true;
        instance.setAch(value);

        assertTrue("'isAch' should be correct.", instance.isAch());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAch(boolean ach)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAch() {
        boolean value = true;
        instance.setAch(value);

        assertTrue("'setAch' should be correct.",
            (Boolean) TestsHelper.getField(instance, "ach"));
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
     * Accuracy test for the method <code>getClaimantBirthdate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getClaimantBirthdate() {
        Date value = new Date();
        instance.setClaimantBirthdate(value);

        assertSame("'getClaimantBirthdate' should be correct.",
            value, instance.getClaimantBirthdate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setClaimantBirthdate(Date claimantBirthdate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setClaimantBirthdate() {
        Date value = new Date();
        instance.setClaimantBirthdate(value);

        assertSame("'setClaimantBirthdate' should be correct.",
            value, TestsHelper.getField(instance, "claimantBirthdate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getClaimant()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getClaimant() {
        String value = "new_value";
        instance.setClaimant(value);

        assertEquals("'getClaimant' should be correct.",
            value, instance.getClaimant());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setClaimant(String claimant)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setClaimant() {
        String value = "new_value";
        instance.setClaimant(value);

        assertEquals("'setClaimant' should be correct.",
            value, TestsHelper.getField(instance, "claimant"));
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
     * Accuracy test for the method <code>getMasterClaimNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getMasterClaimNumber() {
        String value = "new_value";
        instance.setMasterClaimNumber(value);

        assertEquals("'getMasterClaimNumber' should be correct.",
            value, instance.getMasterClaimNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setMasterClaimNumber(String masterClaimNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setMasterClaimNumber() {
        String value = "new_value";
        instance.setMasterClaimNumber(value);

        assertEquals("'setMasterClaimNumber' should be correct.",
            value, TestsHelper.getField(instance, "masterClaimNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getMasterClaimantBirthdate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getMasterClaimantBirthdate() {
        Date value = new Date();
        instance.setMasterClaimantBirthdate(value);

        assertSame("'getMasterClaimantBirthdate' should be correct.",
            value, instance.getMasterClaimantBirthdate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setMasterClaimantBirthdate(Date masterClaimantBirthdate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setMasterClaimantBirthdate() {
        Date value = new Date();
        instance.setMasterClaimantBirthdate(value);

        assertSame("'setMasterClaimantBirthdate' should be correct.",
            value, TestsHelper.getField(instance, "masterClaimantBirthdate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getMasterAccountStatus()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getMasterAccountStatus() {
        AccountStatus value = new AccountStatus();
        instance.setMasterAccountStatus(value);

        assertSame("'getMasterAccountStatus' should be correct.",
            value, instance.getMasterAccountStatus());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setMasterAccountStatus(AccountStatus masterAccountStatus)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setMasterAccountStatus() {
        AccountStatus value = new AccountStatus();
        instance.setMasterAccountStatus(value);

        assertSame("'setMasterAccountStatus' should be correct.",
            value, TestsHelper.getField(instance, "masterAccountStatus"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getMasterAccountBalance()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getMasterAccountBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setMasterAccountBalance(value);

        assertSame("'getMasterAccountBalance' should be correct.",
            value, instance.getMasterAccountBalance());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setMasterAccountBalance(BigDecimal masterAccountBalance)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setMasterAccountBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setMasterAccountBalance(value);

        assertSame("'setMasterAccountBalance' should be correct.",
            value, TestsHelper.getField(instance, "masterAccountBalance"));
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
