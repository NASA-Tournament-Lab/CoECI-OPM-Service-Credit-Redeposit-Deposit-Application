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
import gov.opm.scrd.entities.lookup.PaymentReversalReason;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link PaymentReverse} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class PaymentReverseUnitTests {
    /**
     * <p>
     * Represents the <code>PaymentReverse</code> instance used in tests.
     * </p>
     */
    private PaymentReverse instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PaymentReverseUnitTests.class);
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
        instance = new PaymentReverse();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>PaymentReverse()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new PaymentReverse();

        assertEquals("'paymentId' should be correct.", 0L, TestsHelper.getField(instance, "paymentId"));
        assertNull("'reason' should be correct.", TestsHelper.getField(instance, "reason"));
        assertFalse("'applyToGL' should be correct.", (Boolean) TestsHelper.getField(instance, "applyToGL"));
        assertNull("'reverser' should be correct.", TestsHelper.getField(instance, "reverser"));
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
     * Accuracy test for the method <code>getReason()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getReason() {
        PaymentReversalReason value = new PaymentReversalReason();
        instance.setReason(value);

        assertSame("'getReason' should be correct.",
            value, instance.getReason());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setReason(PaymentReversalReason reason)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setReason() {
        PaymentReversalReason value = new PaymentReversalReason();
        instance.setReason(value);

        assertSame("'setReason' should be correct.",
            value, TestsHelper.getField(instance, "reason"));
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
     * Accuracy test for the method <code>getReverser()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getReverser() {
        String value = "new_value";
        instance.setReverser(value);

        assertEquals("'getReverser' should be correct.",
            value, instance.getReverser());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setReverser(String reverser)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setReverser() {
        String value = "new_value";
        instance.setReverser(value);

        assertEquals("'setReverser' should be correct.",
            value, TestsHelper.getField(instance, "reverser"));
    }
}
