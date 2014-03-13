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
import gov.opm.scrd.entities.lookup.ApplicationDesignation;
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PaymentType;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link Payment} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Services - Account and Payment Services Assembly 1.0):</em>
 * <ol>
 * <li>Added test cases for approvalReason.</li>
 * <li>Updated test cases for claimNumber.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: govRefund, disapprove, historyPayment, resolvedSuspense, userInserted, postFlag,
 * orderCode, statusCode</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.2
 */
public class PaymentUnitTests {
    /**
     * <p>
     * Represents the <code>Payment</code> instance used in tests.
     * </p>
     */
    private Payment instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PaymentUnitTests.class);
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
        instance = new Payment();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Payment()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Payment();

        assertNull("'batchNumber' should be correct.", TestsHelper.getField(instance, "batchNumber"));
        assertNull("'blockNumber' should be correct.", TestsHelper.getField(instance, "blockNumber"));
        assertNull("'sequenceNumber' should be correct.", TestsHelper.getField(instance, "sequenceNumber"));
        assertNull("'paymentStatus' should be correct.", TestsHelper.getField(instance, "paymentStatus"));
        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'accountHolderBirthdate' should be correct.",
            TestsHelper.getField(instance, "accountHolderBirthdate"));
        assertNull("'depositDate' should be correct.", TestsHelper.getField(instance, "depositDate"));
        assertNull("'amount' should be correct.", TestsHelper.getField(instance, "amount"));
        assertNull("'ssn' should be correct.", TestsHelper.getField(instance, "ssn"));
        assertNull("'claimant' should be correct.", TestsHelper.getField(instance, "claimant"));
        assertNull("'claimantBirthdate' should be correct.", TestsHelper.getField(instance, "claimantBirthdate"));
        assertNull("'importId' should be correct.", TestsHelper.getField(instance, "importId"));
        assertEquals("'sequence' should be correct.", 0, TestsHelper.getField(instance, "sequence"));
        assertNull("'transactionDate' should be correct.", TestsHelper.getField(instance, "transactionDate"));
        assertNull("'statusDate' should be correct.", TestsHelper.getField(instance, "statusDate"));
        assertNull("'applyTo' should be correct.", TestsHelper.getField(instance, "applyTo"));
        assertFalse("'applyToGL' should be correct.", (Boolean) TestsHelper.getField(instance, "applyToGL"));
        assertNull("'note' should be correct.", TestsHelper.getField(instance, "note"));
        assertNull("'transactionKey' should be correct.", TestsHelper.getField(instance, "transactionKey"));
        assertFalse("'ach' should be correct.", (Boolean) TestsHelper.getField(instance, "ach"));
        assertNull("'accountBalance' should be correct.", TestsHelper.getField(instance, "accountBalance"));
        assertNull("'accountStatus' should be correct.", TestsHelper.getField(instance, "accountStatus"));
        assertNull("'masterClaimNumber' should be correct.", TestsHelper.getField(instance, "masterClaimNumber"));
        assertNull("'masterClaimantBirthdate' should be correct.",
            TestsHelper.getField(instance, "masterClaimantBirthdate"));
        assertNull("'masterAccountStatus' should be correct.", TestsHelper.getField(instance, "masterAccountStatus"));
        assertNull("'masterAccountBalance' should be correct.", TestsHelper.getField(instance, "masterAccountBalance"));
        assertNull("'masterAccountId' should be correct.", TestsHelper.getField(instance, "masterAccountId"));
        assertNull("'preDepositAmount' should be correct.", TestsHelper.getField(instance, "preDepositAmount"));
        assertNull("'preRedepositAmount' should be correct.", TestsHelper.getField(instance, "preRedepositAmount"));
        assertNull("'postDepositAmount' should be correct.", TestsHelper.getField(instance, "postDepositAmount"));
        assertNull("'postRedepositAmount' should be correct.", TestsHelper.getField(instance, "postRedepositAmount"));
        assertNull("'approvalUser' should be correct.", TestsHelper.getField(instance, "approvalUser"));
        assertNull("'approvalStatus' should be correct.", TestsHelper.getField(instance, "approvalStatus"));
        assertNull("'approvalReason' should be correct.", TestsHelper.getField(instance, "approvalReason"));
        assertNull("'paymentType' should be correct.", TestsHelper.getField(instance, "paymentType"));
        assertNull("'accountId' should be correct.", TestsHelper.getField(instance, "accountId"));
        assertNull("'govRefund' should be correct.", TestsHelper.getField(instance, "govRefund"));
        assertNull("'disapprove' should be correct.", TestsHelper.getField(instance, "disapprove"));
        assertNull("'historyPayment' should be correct.", TestsHelper.getField(instance, "historyPayment"));
        assertNull("'resolvedSuspense' should be correct.", TestsHelper.getField(instance, "resolvedSuspense"));
        assertNull("'userInserted' should be correct.", TestsHelper.getField(instance, "userInserted"));
        assertNull("'postFlag' should be correct.", TestsHelper.getField(instance, "postFlag"));
        assertNull("'orderCode' should be correct.", TestsHelper.getField(instance, "orderCode"));
        assertNull("'statusCode' should be correct.", TestsHelper.getField(instance, "statusCode"));
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
     * Accuracy test for the method <code>getSsn()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSsn() {
        String value = "new_value";
        instance.setSsn(value);

        assertEquals("'getSsn' should be correct.",
            value, instance.getSsn());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSsn(String ssn)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSsn() {
        String value = "new_value";
        instance.setSsn(value);

        assertEquals("'setSsn' should be correct.",
            value, TestsHelper.getField(instance, "ssn"));
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
     * Accuracy test for the method <code>getImportId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getImportId() {
        String value = "new_value";
        instance.setImportId(value);

        assertEquals("'getImportId' should be correct.",
            value, instance.getImportId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setImportId(String importId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setImportId() {
        String value = "new_value";
        instance.setImportId(value);

        assertEquals("'setImportId' should be correct.",
            value, TestsHelper.getField(instance, "importId"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSequence()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSequence() {
        int value = 1;
        instance.setSequence(value);

        assertEquals("'getSequence' should be correct.",
            value, instance.getSequence());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSequence(int sequence)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSequence() {
        int value = 1;
        instance.setSequence(value);

        assertEquals("'setSequence' should be correct.",
            value, TestsHelper.getField(instance, "sequence"));
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
     * Accuracy test for the method <code>getApplyTo()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getApplyTo() {
        ApplicationDesignation value = new ApplicationDesignation();
        instance.setApplyTo(value);

        assertSame("'getApplyTo' should be correct.",
            value, instance.getApplyTo());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setApplyTo(ApplicationDesignation applyTo)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setApplyTo() {
        ApplicationDesignation value = new ApplicationDesignation();
        instance.setApplyTo(value);

        assertSame("'setApplyTo' should be correct.",
            value, TestsHelper.getField(instance, "applyTo"));
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
     * Accuracy test for the method <code>getNote()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNote() {
        String value = "new_value";
        instance.setNote(value);

        assertEquals("'getNote' should be correct.",
            value, instance.getNote());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNote(String note)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNote() {
        String value = "new_value";
        instance.setNote(value);

        assertEquals("'setNote' should be correct.",
            value, TestsHelper.getField(instance, "note"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTransactionKey()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTransactionKey() {
        String value = "new_value";
        instance.setTransactionKey(value);

        assertEquals("'getTransactionKey' should be correct.",
            value, instance.getTransactionKey());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTransactionKey(String transactionKey)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTransactionKey() {
        String value = "new_value";
        instance.setTransactionKey(value);

        assertEquals("'setTransactionKey' should be correct.",
            value, TestsHelper.getField(instance, "transactionKey"));
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
     * Accuracy test for the method <code>getMasterAccountId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getMasterAccountId() {
        Long value = 1L;
        instance.setMasterAccountId(value);

        assertEquals("'getMasterAccountId' should be correct.",
            value, instance.getMasterAccountId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setMasterAccountId(Long masterAccountId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setMasterAccountId() {
        Long value = 1L;
        instance.setMasterAccountId(value);

        assertEquals("'setMasterAccountId' should be correct.",
            value, TestsHelper.getField(instance, "masterAccountId"));
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
     * Accuracy test for the method <code>getApprovalReason()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getApprovalReason() {
        String value = "new_value";
        instance.setApprovalReason(value);

        assertEquals("'getApprovalReason' should be correct.",
            value, instance.getApprovalReason());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setApprovalReason(String approvalReason)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setApprovalReason() {
        String value = "new_value";
        instance.setApprovalReason(value);

        assertEquals("'setApprovalReason' should be correct.",
            value, TestsHelper.getField(instance, "approvalReason"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentType() {
        PaymentType value = PaymentType.INTEREST_ADJUSTMENT;
        instance.setPaymentType(value);

        assertEquals("'getPaymentType' should be correct.",
            value, instance.getPaymentType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentType(PaymentType paymentType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentType() {
        PaymentType value = PaymentType.INTEREST_ADJUSTMENT;
        instance.setPaymentType(value);

        assertEquals("'setPaymentType' should be correct.",
            value, TestsHelper.getField(instance, "paymentType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccountId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccountId() {
        Long value = 1L;
        instance.setAccountId(value);

        assertEquals("'getAccountId' should be correct.",
            value, instance.getAccountId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccountId(Long accountId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccountId() {
        Long value = 1L;
        instance.setAccountId(value);

        assertEquals("'setAccountId' should be correct.",
            value, TestsHelper.getField(instance, "accountId"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getGovRefund()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getGovRefund() {
        Boolean value = true;
        instance.setGovRefund(value);

        assertEquals("'getGovRefund' should be correct.",
            value, instance.getGovRefund());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setGovRefund(Boolean govRefund)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setGovRefund() {
        Boolean value = true;
        instance.setGovRefund(value);

        assertEquals("'setGovRefund' should be correct.",
            value, TestsHelper.getField(instance, "govRefund"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDisapprove()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getDisapprove() {
        Boolean value = true;
        instance.setDisapprove(value);

        assertEquals("'getDisapprove' should be correct.",
            value, instance.getDisapprove());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDisapprove(Boolean disapprove)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setDisapprove() {
        Boolean value = true;
        instance.setDisapprove(value);

        assertEquals("'setDisapprove' should be correct.",
            value, TestsHelper.getField(instance, "disapprove"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getHistoryPayment()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getHistoryPayment() {
        Boolean value = true;
        instance.setHistoryPayment(value);

        assertEquals("'getHistoryPayment' should be correct.",
            value, instance.getHistoryPayment());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setHistoryPayment(Boolean historyPayment)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setHistoryPayment() {
        Boolean value = true;
        instance.setHistoryPayment(value);

        assertEquals("'setHistoryPayment' should be correct.",
            value, TestsHelper.getField(instance, "historyPayment"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getResolvedSuspense()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getResolvedSuspense() {
        Boolean value = true;
        instance.setResolvedSuspense(value);

        assertEquals("'getResolvedSuspense' should be correct.",
            value, instance.getResolvedSuspense());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setResolvedSuspense(Boolean resolvedSuspense)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setResolvedSuspense() {
        Boolean value = true;
        instance.setResolvedSuspense(value);

        assertEquals("'setResolvedSuspense' should be correct.",
            value, TestsHelper.getField(instance, "resolvedSuspense"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getUserInserted()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getUserInserted() {
        Boolean value = true;
        instance.setUserInserted(value);

        assertEquals("'getUserInserted' should be correct.",
            value, instance.getUserInserted());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setUserInserted(Boolean userInserted)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setUserInserted() {
        Boolean value = true;
        instance.setUserInserted(value);

        assertEquals("'setUserInserted' should be correct.",
            value, TestsHelper.getField(instance, "userInserted"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPostFlag()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getPostFlag() {
        Boolean value = true;
        instance.setPostFlag(value);

        assertEquals("'getPostFlag' should be correct.",
            value, instance.getPostFlag());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPostFlag(Boolean postFlag)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setPostFlag() {
        Boolean value = true;
        instance.setPostFlag(value);

        assertEquals("'setPostFlag' should be correct.",
            value, TestsHelper.getField(instance, "postFlag"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getOrderCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getOrderCode() {
        PaymentsAppliedOrderCode value = new PaymentsAppliedOrderCode();
        instance.setOrderCode(value);

        assertSame("'getOrderCode' should be correct.",
            value, instance.getOrderCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setOrderCode(PaymentsAppliedOrderCode orderCode)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setOrderCode() {
        PaymentsAppliedOrderCode value = new PaymentsAppliedOrderCode();
        instance.setOrderCode(value);

        assertSame("'setOrderCode' should be correct.",
            value, TestsHelper.getField(instance, "orderCode"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStatusCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getStatusCode() {
        PayTransStatusCode value = new PayTransStatusCode();
        instance.setStatusCode(value);

        assertSame("'getStatusCode' should be correct.",
            value, instance.getStatusCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStatusCode(PayTransStatusCode statusCode)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setStatusCode() {
        PayTransStatusCode value = new PayTransStatusCode();
        instance.setStatusCode(value);

        assertSame("'setStatusCode' should be correct.",
            value, TestsHelper.getField(instance, "statusCode"));
    }
}
