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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link BillingSummary} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class BillingSummaryUnitTests {
    /**
     * <p>
     * Represents the <code>BillingSummary</code> instance used in tests.
     * </p>
     */
    private BillingSummary instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BillingSummaryUnitTests.class);
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
        instance = new BillingSummary();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>BillingSummary()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new BillingSummary();

        assertNull("'computedDate' should be correct.", TestsHelper.getField(instance, "computedDate"));
        assertNull("'lastDepositDate' should be correct.", TestsHelper.getField(instance, "lastDepositDate"));
        assertNull("'firstBillingDate' should be correct.", TestsHelper.getField(instance, "firstBillingDate"));
        assertNull("'lastInterestCalculation' should be correct.",
            TestsHelper.getField(instance, "lastInterestCalculation"));
        assertNull("'transactionType' should be correct.", TestsHelper.getField(instance, "transactionType"));
        assertNull("'lastTransactionDate' should be correct.", TestsHelper.getField(instance, "lastTransactionDate"));
        assertNull("'billings' should be correct.", TestsHelper.getField(instance, "billings"));
        assertNull("'stopACHPayments' should be correct.", TestsHelper.getField(instance, "stopACHPayments"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getComputedDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getComputedDate() {
        Date value = new Date();
        instance.setComputedDate(value);

        assertSame("'getComputedDate' should be correct.",
            value, instance.getComputedDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setComputedDate(Date computedDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setComputedDate() {
        Date value = new Date();
        instance.setComputedDate(value);

        assertSame("'setComputedDate' should be correct.",
            value, TestsHelper.getField(instance, "computedDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastDepositDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastDepositDate() {
        Date value = new Date();
        instance.setLastDepositDate(value);

        assertSame("'getLastDepositDate' should be correct.",
            value, instance.getLastDepositDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastDepositDate(Date lastDepositDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastDepositDate() {
        Date value = new Date();
        instance.setLastDepositDate(value);

        assertSame("'setLastDepositDate' should be correct.",
            value, TestsHelper.getField(instance, "lastDepositDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFirstBillingDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getFirstBillingDate() {
        Date value = new Date();
        instance.setFirstBillingDate(value);

        assertSame("'getFirstBillingDate' should be correct.",
            value, instance.getFirstBillingDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFirstBillingDate(Date firstBillingDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFirstBillingDate() {
        Date value = new Date();
        instance.setFirstBillingDate(value);

        assertSame("'setFirstBillingDate' should be correct.",
            value, TestsHelper.getField(instance, "firstBillingDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastInterestCalculation()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastInterestCalculation() {
        Date value = new Date();
        instance.setLastInterestCalculation(value);

        assertSame("'getLastInterestCalculation' should be correct.",
            value, instance.getLastInterestCalculation());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastInterestCalculation(Date lastInterestCalculation)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastInterestCalculation() {
        Date value = new Date();
        instance.setLastInterestCalculation(value);

        assertSame("'setLastInterestCalculation' should be correct.",
            value, TestsHelper.getField(instance, "lastInterestCalculation"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTransactionType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTransactionType() {
        String value = "new_value";
        instance.setTransactionType(value);

        assertEquals("'getTransactionType' should be correct.",
            value, instance.getTransactionType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTransactionType(String transactionType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTransactionType() {
        String value = "new_value";
        instance.setTransactionType(value);

        assertEquals("'setTransactionType' should be correct.",
            value, TestsHelper.getField(instance, "transactionType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastTransactionDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastTransactionDate() {
        Date value = new Date();
        instance.setLastTransactionDate(value);

        assertSame("'getLastTransactionDate' should be correct.",
            value, instance.getLastTransactionDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastTransactionDate(Date lastTransactionDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastTransactionDate() {
        Date value = new Date();
        instance.setLastTransactionDate(value);

        assertSame("'setLastTransactionDate' should be correct.",
            value, TestsHelper.getField(instance, "lastTransactionDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBillings()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBillings() {
        List<Billing> value = new ArrayList<Billing>();
        instance.setBillings(value);

        assertSame("'getBillings' should be correct.",
            value, instance.getBillings());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBillings(List&lt;Billing&gt; billings)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBillings() {
        List<Billing> value = new ArrayList<Billing>();
        instance.setBillings(value);

        assertSame("'setBillings' should be correct.",
            value, TestsHelper.getField(instance, "billings"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStopACHPayments()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStopACHPayments() {
        Boolean value = true;
        instance.setStopACHPayments(value);

        assertEquals("'getStopACHPayments' should be correct.",
            value, instance.getStopACHPayments());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStopACHPayments(Boolean stopACHPayments)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStopACHPayments() {
        Boolean value = true;
        instance.setStopACHPayments(value);

        assertEquals("'setStopACHPayments' should be correct.",
            value, TestsHelper.getField(instance, "stopACHPayments"));
    }
}
