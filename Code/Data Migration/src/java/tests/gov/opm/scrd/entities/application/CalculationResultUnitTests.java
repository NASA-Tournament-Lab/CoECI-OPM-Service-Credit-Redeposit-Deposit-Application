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
import gov.opm.scrd.entities.lookup.CalculationStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link CalculationResult} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: paymentOrder, interestAccrualDate</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.1
 */
public class CalculationResultUnitTests {
    /**
     * <p>
     * Represents the <code>CalculationResult</code> instance used in tests.
     * </p>
     */
    private CalculationResult instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(CalculationResultUnitTests.class);
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
        instance = new CalculationResult();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>CalculationResult()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new CalculationResult();

        assertNull("'items' should be correct.", TestsHelper.getField(instance, "items"));
        assertNull("'redeposits' should be correct.", TestsHelper.getField(instance, "redeposits"));
        assertNull("'dedeposits' should be correct.", TestsHelper.getField(instance, "dedeposits"));
        assertNull("'summary' should be correct.", TestsHelper.getField(instance, "summary"));
        assertNull("'calculationStatus' should be correct.", TestsHelper.getField(instance, "calculationStatus"));
        assertFalse("'official' should be correct.", (Boolean) TestsHelper.getField(instance, "official"));
        assertFalse("'applyToRealPayments' should be correct.",
            (Boolean) TestsHelper.getField(instance, "applyToRealPayments"));
        assertNull("'paymentOrder' should be correct.", TestsHelper.getField(instance, "paymentOrder"));
        assertNull("'interestAccrualDate' should be correct.", TestsHelper.getField(instance, "interestAccrualDate"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getItems()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getItems() {
        List<CalculationResultItem> value = new ArrayList<CalculationResultItem>();
        instance.setItems(value);

        assertSame("'getItems' should be correct.",
            value, instance.getItems());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setItems(List&lt;CalculationResultItem&gt; items)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setItems() {
        List<CalculationResultItem> value = new ArrayList<CalculationResultItem>();
        instance.setItems(value);

        assertSame("'setItems' should be correct.",
            value, TestsHelper.getField(instance, "items"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRedeposits()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRedeposits() {
        List<Redeposit> value = new ArrayList<Redeposit>();
        instance.setRedeposits(value);

        assertSame("'getRedeposits' should be correct.",
            value, instance.getRedeposits());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRedeposits(List&lt;Redeposit&gt; redeposits)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRedeposits() {
        List<Redeposit> value = new ArrayList<Redeposit>();
        instance.setRedeposits(value);

        assertSame("'setRedeposits' should be correct.",
            value, TestsHelper.getField(instance, "redeposits"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDedeposits()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDedeposits() {
        List<Dedeposit> value = new ArrayList<Dedeposit>();
        instance.setDedeposits(value);

        assertSame("'getDedeposits' should be correct.",
            value, instance.getDedeposits());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDedeposits(List&lt;Dedeposit&gt; dedeposits)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDedeposits() {
        List<Dedeposit> value = new ArrayList<Dedeposit>();
        instance.setDedeposits(value);

        assertSame("'setDedeposits' should be correct.",
            value, TestsHelper.getField(instance, "dedeposits"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSummary()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSummary() {
        SummaryData value = new SummaryData();
        instance.setSummary(value);

        assertSame("'getSummary' should be correct.",
            value, instance.getSummary());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSummary(SummaryData summary)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSummary() {
        SummaryData value = new SummaryData();
        instance.setSummary(value);

        assertSame("'setSummary' should be correct.",
            value, TestsHelper.getField(instance, "summary"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCalculationStatus()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCalculationStatus() {
        CalculationStatus value = new CalculationStatus();
        instance.setCalculationStatus(value);

        assertSame("'getCalculationStatus' should be correct.",
            value, instance.getCalculationStatus());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCalculationStatus(CalculationStatus calculationStatus)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCalculationStatus() {
        CalculationStatus value = new CalculationStatus();
        instance.setCalculationStatus(value);

        assertSame("'setCalculationStatus' should be correct.",
            value, TestsHelper.getField(instance, "calculationStatus"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isOfficial()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isOfficial() {
        boolean value = true;
        instance.setOfficial(value);

        assertTrue("'isOfficial' should be correct.", instance.isOfficial());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setOfficial(boolean official)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setOfficial() {
        boolean value = true;
        instance.setOfficial(value);

        assertTrue("'setOfficial' should be correct.",
            (Boolean) TestsHelper.getField(instance, "official"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isApplyToRealPayments()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isApplyToRealPayments() {
        boolean value = true;
        instance.setApplyToRealPayments(value);

        assertTrue("'isApplyToRealPayments' should be correct.", instance.isApplyToRealPayments());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setApplyToRealPayments(boolean applyToRealPayments)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setApplyToRealPayments() {
        boolean value = true;
        instance.setApplyToRealPayments(value);

        assertTrue("'setApplyToRealPayments' should be correct.",
            (Boolean) TestsHelper.getField(instance, "applyToRealPayments"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentOrder()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getPaymentOrder() {
        Integer value = 1;
        instance.setPaymentOrder(value);

        assertEquals("'getPaymentOrder' should be correct.",
            value, instance.getPaymentOrder());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentOrder(Integer paymentOrder)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setPaymentOrder() {
        Integer value = 1;
        instance.setPaymentOrder(value);

        assertEquals("'setPaymentOrder' should be correct.",
            value, TestsHelper.getField(instance, "paymentOrder"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getInterestAccrualDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getInterestAccrualDate() {
        Date value = new Date();
        instance.setInterestAccrualDate(value);

        assertSame("'getInterestAccrualDate' should be correct.",
            value, instance.getInterestAccrualDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setInterestAccrualDate(Date interestAccrualDate)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setInterestAccrualDate() {
        Date value = new Date();
        instance.setInterestAccrualDate(value);

        assertSame("'setInterestAccrualDate' should be correct.",
            value, TestsHelper.getField(instance, "interestAccrualDate"));
    }
}
