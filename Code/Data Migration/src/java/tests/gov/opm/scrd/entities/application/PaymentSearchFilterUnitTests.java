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
import gov.opm.scrd.entities.lookup.ComparisonType;
import gov.opm.scrd.entities.lookup.DepositComparisonType;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PaymentType;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link PaymentSearchFilter} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class PaymentSearchFilterUnitTests {
    /**
     * <p>
     * Represents the <code>PaymentSearchFilter</code> instance used in tests.
     * </p>
     */
    private PaymentSearchFilter instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PaymentSearchFilterUnitTests.class);
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
        instance = new PaymentSearchFilter();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>PaymentSearchFilter()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new PaymentSearchFilter();

        assertNull("'amountComparisonType' should be correct.", TestsHelper.getField(instance, "amountComparisonType"));
        assertNull("'amount' should be correct.", TestsHelper.getField(instance, "amount"));
        assertNull("'batchNumber' should be correct.", TestsHelper.getField(instance, "batchNumber"));
        assertNull("'blockNumber' should be correct.", TestsHelper.getField(instance, "blockNumber"));
        assertNull("'sequenceNumber' should be correct.", TestsHelper.getField(instance, "sequenceNumber"));
        assertNull("'resolvedSuspense' should be correct.", TestsHelper.getField(instance, "resolvedSuspense"));
        assertNull("'paymentStatus' should be correct.", TestsHelper.getField(instance, "paymentStatus"));
        assertNull("'paymentType' should be correct.", TestsHelper.getField(instance, "paymentType"));
        assertNull("'depositComparisonType' should be correct.",
            TestsHelper.getField(instance, "depositComparisonType"));
        assertNull("'depositDate' should be correct.", TestsHelper.getField(instance, "depositDate"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getAmountComparisonType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAmountComparisonType() {
        ComparisonType value = ComparisonType.EQUAL;
        instance.setAmountComparisonType(value);

        assertEquals("'getAmountComparisonType' should be correct.",
            value, instance.getAmountComparisonType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAmountComparisonType(ComparisonType amountComparisonType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAmountComparisonType() {
        ComparisonType value = ComparisonType.EQUAL;
        instance.setAmountComparisonType(value);

        assertEquals("'setAmountComparisonType' should be correct.",
            value, TestsHelper.getField(instance, "amountComparisonType"));
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
     * Accuracy test for the method <code>getResolvedSuspense()</code>.<br>
     * The value should be properly retrieved.
     * </p>
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
     * Accuracy test for the method <code>getDepositComparisonType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDepositComparisonType() {
        DepositComparisonType value = DepositComparisonType.DEPOSITED_AFTER;
        instance.setDepositComparisonType(value);

        assertEquals("'getDepositComparisonType' should be correct.",
            value, instance.getDepositComparisonType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDepositComparisonType(DepositComparisonType
     * depositComparisonType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDepositComparisonType() {
        DepositComparisonType value = DepositComparisonType.DEPOSITED_AFTER;
        instance.setDepositComparisonType(value);

        assertEquals("'setDepositComparisonType' should be correct.",
            value, TestsHelper.getField(instance, "depositComparisonType"));
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
}
