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
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link ApprovalItemSummary} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class ApprovalItemSummaryUnitTests {
    /**
     * <p>
     * Represents the <code>ApprovalItemSummary</code> instance used in tests.
     * </p>
     */
    private ApprovalItemSummary instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ApprovalItemSummaryUnitTests.class);
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
        instance = new ApprovalItemSummary();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>ApprovalItemSummary()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new ApprovalItemSummary();

        assertEquals("'pendingPaymentCount' should be correct.",
            0, TestsHelper.getField(instance, "pendingPaymentCount"));
        assertEquals("'interestAdjustmentCount' should be correct.",
            0, TestsHelper.getField(instance, "interestAdjustmentCount"));
        assertEquals("'paymentMoveCount' should be correct.", 0, TestsHelper.getField(instance, "paymentMoveCount"));
        assertEquals("'totalCount' should be correct.", 0, TestsHelper.getField(instance, "totalCount"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getPendingPaymentCount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPendingPaymentCount() {
        int value = 1;
        instance.setPendingPaymentCount(value);

        assertEquals("'getPendingPaymentCount' should be correct.",
            value, instance.getPendingPaymentCount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPendingPaymentCount(int pendingPaymentCount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPendingPaymentCount() {
        int value = 1;
        instance.setPendingPaymentCount(value);

        assertEquals("'setPendingPaymentCount' should be correct.",
            value, TestsHelper.getField(instance, "pendingPaymentCount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getInterestAdjustmentCount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getInterestAdjustmentCount() {
        int value = 1;
        instance.setInterestAdjustmentCount(value);

        assertEquals("'getInterestAdjustmentCount' should be correct.",
            value, instance.getInterestAdjustmentCount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setInterestAdjustmentCount(int interestAdjustmentCount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setInterestAdjustmentCount() {
        int value = 1;
        instance.setInterestAdjustmentCount(value);

        assertEquals("'setInterestAdjustmentCount' should be correct.",
            value, TestsHelper.getField(instance, "interestAdjustmentCount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentMoveCount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentMoveCount() {
        int value = 1;
        instance.setPaymentMoveCount(value);

        assertEquals("'getPaymentMoveCount' should be correct.",
            value, instance.getPaymentMoveCount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentMoveCount(int paymentMoveCount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentMoveCount() {
        int value = 1;
        instance.setPaymentMoveCount(value);

        assertEquals("'setPaymentMoveCount' should be correct.",
            value, TestsHelper.getField(instance, "paymentMoveCount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalCount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotalCount() {
        int value = 1;
        instance.setTotalCount(value);

        assertEquals("'getTotalCount' should be correct.",
            value, instance.getTotalCount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalCount(int totalCount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotalCount() {
        int value = 1;
        instance.setTotalCount(value);

        assertEquals("'setTotalCount' should be correct.",
            value, TestsHelper.getField(instance, "totalCount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>toString()</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_toString() {
        instance.setTotalCount(1);

        String content = instance.toString();
        assertTrue("'toString' should be correct.", content.contains("\"totalCount\":1"));
    }
}
