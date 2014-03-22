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
import gov.opm.scrd.entities.lookup.TransferType;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link RefundTransaction} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class RefundTransactionUnitTests {
    /**
     * <p>
     * Represents the <code>RefundTransaction</code> instance used in tests.
     * </p>
     */
    private RefundTransaction instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(RefundTransactionUnitTests.class);
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
        instance = new RefundTransaction();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>RefundTransaction()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new RefundTransaction();

        assertNull("'transactionKey' should be correct.", TestsHelper.getField(instance, "transactionKey"));
        assertNull("'amount' should be correct.", TestsHelper.getField(instance, "amount"));
        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'refundDate' should be correct.", TestsHelper.getField(instance, "refundDate"));
        assertNull("'refundUsername' should be correct.", TestsHelper.getField(instance, "refundUsername"));
        assertNull("'transferType' should be correct.", TestsHelper.getField(instance, "transferType"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getTransactionKey()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTransactionKey() {
        String value = "1";
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
        String value = "1";
        instance.setTransactionKey(value);

        assertEquals("'setTransactionKey' should be correct.",
            value, TestsHelper.getField(instance, "transactionKey"));
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
     * Accuracy test for the method <code>getRefundDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRefundDate() {
        Date value = new Date();
        instance.setRefundDate(value);

        assertSame("'getRefundDate' should be correct.",
            value, instance.getRefundDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRefundDate(Date refundDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRefundDate() {
        Date value = new Date();
        instance.setRefundDate(value);

        assertSame("'setRefundDate' should be correct.",
            value, TestsHelper.getField(instance, "refundDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRefundUsername()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRefundUsername() {
        String value = "new_value";
        instance.setRefundUsername(value);

        assertEquals("'getRefundUsername' should be correct.",
            value, instance.getRefundUsername());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRefundUsername(String refundUsername)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRefundUsername() {
        String value = "new_value";
        instance.setRefundUsername(value);

        assertEquals("'setRefundUsername' should be correct.",
            value, TestsHelper.getField(instance, "refundUsername"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTransferType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTransferType() {
        TransferType value = new TransferType();
        instance.setTransferType(value);

        assertSame("'getTransferType' should be correct.",
            value, instance.getTransferType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTransferType(TransferType transferType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTransferType() {
        TransferType value = new TransferType();
        instance.setTransferType(value);

        assertSame("'setTransferType' should be correct.",
            value, TestsHelper.getField(instance, "transferType"));
    }
}
