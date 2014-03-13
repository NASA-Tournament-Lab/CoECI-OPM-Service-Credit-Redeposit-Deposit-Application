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

import java.math.BigDecimal;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link Billing} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class BillingUnitTests {
    /**
     * <p>
     * Represents the <code>Billing</code> instance used in tests.
     * </p>
     */
    private Billing instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BillingUnitTests.class);
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
        instance = new Billing();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Billing()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Billing();

        assertNull("'name' should be correct.", TestsHelper.getField(instance, "name"));
        assertNull("'initialBilling' should be correct.", TestsHelper.getField(instance, "initialBilling"));
        assertNull("'additionalInterest' should be correct.", TestsHelper.getField(instance, "additionalInterest"));
        assertNull("'totalPayments' should be correct.", TestsHelper.getField(instance, "totalPayments"));
        assertNull("'balance' should be correct.", TestsHelper.getField(instance, "balance"));
        assertEquals("'paymentOrder' should be correct.", 0, TestsHelper.getField(instance, "paymentOrder"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getName() {
        String value = "new_value";
        instance.setName(value);

        assertEquals("'getName' should be correct.",
            value, instance.getName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setName(String name)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setName() {
        String value = "new_value";
        instance.setName(value);

        assertEquals("'setName' should be correct.",
            value, TestsHelper.getField(instance, "name"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getInitialBilling()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getInitialBilling() {
        BigDecimal value = new BigDecimal(1);
        instance.setInitialBilling(value);

        assertSame("'getInitialBilling' should be correct.",
            value, instance.getInitialBilling());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setInitialBilling(BigDecimal initialBilling)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setInitialBilling() {
        BigDecimal value = new BigDecimal(1);
        instance.setInitialBilling(value);

        assertSame("'setInitialBilling' should be correct.",
            value, TestsHelper.getField(instance, "initialBilling"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAdditionalInterest()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAdditionalInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setAdditionalInterest(value);

        assertSame("'getAdditionalInterest' should be correct.",
            value, instance.getAdditionalInterest());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAdditionalInterest(BigDecimal additionalInterest)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAdditionalInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setAdditionalInterest(value);

        assertSame("'setAdditionalInterest' should be correct.",
            value, TestsHelper.getField(instance, "additionalInterest"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalPayments()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotalPayments() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalPayments(value);

        assertSame("'getTotalPayments' should be correct.",
            value, instance.getTotalPayments());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalPayments(BigDecimal totalPayments)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotalPayments() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalPayments(value);

        assertSame("'setTotalPayments' should be correct.",
            value, TestsHelper.getField(instance, "totalPayments"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBalance()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setBalance(value);

        assertSame("'getBalance' should be correct.",
            value, instance.getBalance());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBalance(BigDecimal balance)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setBalance(value);

        assertSame("'setBalance' should be correct.",
            value, TestsHelper.getField(instance, "balance"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentOrder()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentOrder() {
        int value = 1;
        instance.setPaymentOrder(value);

        assertEquals("'getPaymentOrder' should be correct.",
            value, instance.getPaymentOrder());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentOrder(int paymentOrder)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentOrder() {
        int value = 1;
        instance.setPaymentOrder(value);

        assertEquals("'setPaymentOrder' should be correct.",
            value, TestsHelper.getField(instance, "paymentOrder"));
    }
}
