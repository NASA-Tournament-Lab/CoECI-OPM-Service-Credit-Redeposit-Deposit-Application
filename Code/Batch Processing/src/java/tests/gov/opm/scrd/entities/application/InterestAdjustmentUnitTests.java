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
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.ApprovalStatus;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link InterestAdjustment} class.
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
public class InterestAdjustmentUnitTests {
    /**
     * <p>
     * Represents the <code>InterestAdjustment</code> instance used in tests.
     * </p>
     */
    private InterestAdjustment instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(InterestAdjustmentUnitTests.class);
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
        instance = new InterestAdjustment();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>InterestAdjustment()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new InterestAdjustment();

        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'preDepositAmount' should be correct.", TestsHelper.getField(instance, "preDepositAmount"));
        assertNull("'preRedepositAmount' should be correct.", TestsHelper.getField(instance, "preRedepositAmount"));
        assertNull("'postDepositAmount' should be correct.", TestsHelper.getField(instance, "postDepositAmount"));
        assertNull("'postRedepositAmount' should be correct.", TestsHelper.getField(instance, "postRedepositAmount"));
        assertNull("'paymentTransactionAmount' should be correct.",
            TestsHelper.getField(instance, "paymentTransactionAmount"));
        assertNull("'adjustmentDate' should be correct.", TestsHelper.getField(instance, "adjustmentDate"));
        assertNull("'accountStatus' should be correct.", TestsHelper.getField(instance, "accountStatus"));
        assertNull("'accountHolderBirthdate' should be correct.",
            TestsHelper.getField(instance, "accountHolderBirthdate"));
        assertNull("'approvalUser' should be correct.", TestsHelper.getField(instance, "approvalUser"));
        assertNull("'approvalStatus' should be correct.", TestsHelper.getField(instance, "approvalStatus"));
        assertEquals("'paymentId' should be correct.", 0L, TestsHelper.getField(instance, "paymentId"));
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
     * Accuracy test for the method <code>getPreDepositAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPreDepositAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPreDepositAmount(value);

        assertSame("'getPreDepositAmount' should be correct.",
            value, instance.getPreDepositAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPreDepositAmount(BigDecimal preDepositAmount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPreDepositAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPreDepositAmount(value);

        assertSame("'setPreDepositAmount' should be correct.",
            value, TestsHelper.getField(instance, "preDepositAmount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPreRedepositAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPreRedepositAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPreRedepositAmount(value);

        assertSame("'getPreRedepositAmount' should be correct.",
            value, instance.getPreRedepositAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPreRedepositAmount(BigDecimal preRedepositAmount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPreRedepositAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPreRedepositAmount(value);

        assertSame("'setPreRedepositAmount' should be correct.",
            value, TestsHelper.getField(instance, "preRedepositAmount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPostDepositAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPostDepositAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPostDepositAmount(value);

        assertSame("'getPostDepositAmount' should be correct.",
            value, instance.getPostDepositAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPostDepositAmount(BigDecimal postDepositAmount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPostDepositAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPostDepositAmount(value);

        assertSame("'setPostDepositAmount' should be correct.",
            value, TestsHelper.getField(instance, "postDepositAmount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPostRedepositAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPostRedepositAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPostRedepositAmount(value);

        assertSame("'getPostRedepositAmount' should be correct.",
            value, instance.getPostRedepositAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPostRedepositAmount(BigDecimal postRedepositAmount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPostRedepositAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPostRedepositAmount(value);

        assertSame("'setPostRedepositAmount' should be correct.",
            value, TestsHelper.getField(instance, "postRedepositAmount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentTransactionAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentTransactionAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPaymentTransactionAmount(value);

        assertSame("'getPaymentTransactionAmount' should be correct.",
            value, instance.getPaymentTransactionAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentTransactionAmount(BigDecimal paymentTransactionAmount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentTransactionAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setPaymentTransactionAmount(value);

        assertSame("'setPaymentTransactionAmount' should be correct.",
            value, TestsHelper.getField(instance, "paymentTransactionAmount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAdjustmentDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAdjustmentDate() {
        Date value = new Date();
        instance.setAdjustmentDate(value);

        assertSame("'getAdjustmentDate' should be correct.",
            value, instance.getAdjustmentDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAdjustmentDate(Date adjustmentDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAdjustmentDate() {
        Date value = new Date();
        instance.setAdjustmentDate(value);

        assertSame("'setAdjustmentDate' should be correct.",
            value, TestsHelper.getField(instance, "adjustmentDate"));
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
