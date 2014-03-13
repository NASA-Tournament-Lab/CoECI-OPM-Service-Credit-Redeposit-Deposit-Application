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
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link PaymentRefundLink} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class PaymentRefundLinkUnitTests {
    /**
     * <p>
     * Represents the <code>PaymentRefundLink</code> instance used in tests.
     * </p>
     */
    private PaymentRefundLink instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PaymentRefundLinkUnitTests.class);
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
        instance = new PaymentRefundLink();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>PaymentRefundLink()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new PaymentRefundLink();

        assertNull("'paymentNeedingRefund' should be correct.", TestsHelper.getField(instance, "paymentNeedingRefund"));
        assertNull("'refundForPayment' should be correct.", TestsHelper.getField(instance, "refundForPayment"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getPaymentNeedingRefund()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentNeedingRefund() {
        Long value = 1L;
        instance.setPaymentNeedingRefund(value);

        assertEquals("'getPaymentNeedingRefund' should be correct.",
            value, instance.getPaymentNeedingRefund());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentNeedingRefund(Long paymentNeedingRefund)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentNeedingRefund() {
        Long value = 1L;
        instance.setPaymentNeedingRefund(value);

        assertEquals("'setPaymentNeedingRefund' should be correct.",
            value, TestsHelper.getField(instance, "paymentNeedingRefund"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRefundForPayment()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRefundForPayment() {
        Long value = 1L;
        instance.setRefundForPayment(value);

        assertEquals("'getRefundForPayment' should be correct.",
            value, instance.getRefundForPayment());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRefundForPayment(Long refundForPayment)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRefundForPayment() {
        Long value = 1L;
        instance.setRefundForPayment(value);

        assertEquals("'setRefundForPayment' should be correct.",
            value, TestsHelper.getField(instance, "refundForPayment"));
    }
}
