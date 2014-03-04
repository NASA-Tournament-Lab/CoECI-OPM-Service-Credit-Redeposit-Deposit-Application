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
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link PaymentInterestDetail} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class PaymentInterestDetailUnitTests {
    /**
     * <p>
     * Represents the <code>PaymentInterestDetail</code> instance used in tests.
     * </p>
     */
    private PaymentInterestDetail instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PaymentInterestDetailUnitTests.class);
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
        instance = new PaymentInterestDetail();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>PaymentInterestDetail()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new PaymentInterestDetail();

        assertNull("'payTransactionKey' should be correct.", TestsHelper.getField(instance, "payTransactionKey"));
        assertNull("'accountType' should be correct.", TestsHelper.getField(instance, "accountType"));
        assertNull("'numWholeYears' should be correct.", TestsHelper.getField(instance, "numWholeYears"));
        assertNull("'calculatedInterest' should be correct.", TestsHelper.getField(instance, "calculatedInterest"));
        assertNull("'lastPayToEOYFactor' should be correct.", TestsHelper.getField(instance, "lastPayToEOYFactor"));
        assertNull("'partialToThisPayFactor' should be correct.",
            TestsHelper.getField(instance, "partialToThisPayFactor"));
        assertNull("'thisInterestRate' should be correct.", TestsHelper.getField(instance, "thisInterestRate"));
        assertNull("'lastPaymentDate' should be correct.", TestsHelper.getField(instance, "lastPaymentDate"));
        assertNull("'transactionDate' should be correct.", TestsHelper.getField(instance, "transactionDate"));
        assertNull("'computedDate' should be correct.", TestsHelper.getField(instance, "computedDate"));
        assertNull("'post' should be correct.", TestsHelper.getField(instance, "post"));
        assertNull("'gui' should be correct.", TestsHelper.getField(instance, "gui"));
        assertNull("'lastPaymentWasThisYear' should be correct.",
            TestsHelper.getField(instance, "lastPaymentWasThisYear"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getPayTransactionKey()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPayTransactionKey() {
        Long value = 1L;
        instance.setPayTransactionKey(value);

        assertEquals("'getPayTransactionKey' should be correct.",
            value, instance.getPayTransactionKey());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPayTransactionKey(Long payTransactionKey)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPayTransactionKey() {
        Long value = 1L;
        instance.setPayTransactionKey(value);

        assertEquals("'setPayTransactionKey' should be correct.",
            value, TestsHelper.getField(instance, "payTransactionKey"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getAccountType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccountType() {
        Long value = 1L;
        instance.setAccountType(value);

        assertEquals("'getAccountType' should be correct.",
            value, instance.getAccountType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccountType(Long accountType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccountType() {
        Long value = 1L;
        instance.setAccountType(value);

        assertEquals("'setAccountType' should be correct.",
            value, TestsHelper.getField(instance, "accountType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumWholeYears()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumWholeYears() {
        Integer value = 1;
        instance.setNumWholeYears(value);

        assertEquals("'getNumWholeYears' should be correct.",
            value, instance.getNumWholeYears());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumWholeYears(Integer numWholeYears)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumWholeYears() {
        Integer value = 1;
        instance.setNumWholeYears(value);

        assertEquals("'setNumWholeYears' should be correct.",
            value, TestsHelper.getField(instance, "numWholeYears"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCalculatedInterest()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCalculatedInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setCalculatedInterest(value);

        assertSame("'getCalculatedInterest' should be correct.",
            value, instance.getCalculatedInterest());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCalculatedInterest(BigDecimal calculatedInterest)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCalculatedInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setCalculatedInterest(value);

        assertSame("'setCalculatedInterest' should be correct.",
            value, TestsHelper.getField(instance, "calculatedInterest"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastPayToEOYFactor()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastPayToEOYFactor() {
        BigDecimal value = new BigDecimal(1);
        instance.setLastPayToEOYFactor(value);

        assertSame("'getLastPayToEOYFactor' should be correct.",
            value, instance.getLastPayToEOYFactor());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastPayToEOYFactor(BigDecimal lastPayToEOYFactor)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastPayToEOYFactor() {
        BigDecimal value = new BigDecimal(1);
        instance.setLastPayToEOYFactor(value);

        assertSame("'setLastPayToEOYFactor' should be correct.",
            value, TestsHelper.getField(instance, "lastPayToEOYFactor"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPartialToThisPayFactor()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPartialToThisPayFactor() {
        BigDecimal value = new BigDecimal(1);
        instance.setPartialToThisPayFactor(value);

        assertSame("'getPartialToThisPayFactor' should be correct.",
            value, instance.getPartialToThisPayFactor());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPartialToThisPayFactor(BigDecimal partialToThisPayFactor)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPartialToThisPayFactor() {
        BigDecimal value = new BigDecimal(1);
        instance.setPartialToThisPayFactor(value);

        assertSame("'setPartialToThisPayFactor' should be correct.",
            value, TestsHelper.getField(instance, "partialToThisPayFactor"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getThisInterestRate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getThisInterestRate() {
        BigDecimal value = new BigDecimal(1);
        instance.setThisInterestRate(value);

        assertSame("'getThisInterestRate' should be correct.",
            value, instance.getThisInterestRate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setThisInterestRate(BigDecimal thisInterestRate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setThisInterestRate() {
        BigDecimal value = new BigDecimal(1);
        instance.setThisInterestRate(value);

        assertSame("'setThisInterestRate' should be correct.",
            value, TestsHelper.getField(instance, "thisInterestRate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastPaymentDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastPaymentDate() {
        Date value = new Date();
        instance.setLastPaymentDate(value);

        assertSame("'getLastPaymentDate' should be correct.",
            value, instance.getLastPaymentDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastPaymentDate(Date lastPaymentDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastPaymentDate() {
        Date value = new Date();
        instance.setLastPaymentDate(value);

        assertSame("'setLastPaymentDate' should be correct.",
            value, TestsHelper.getField(instance, "lastPaymentDate"));
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
     * Accuracy test for the method <code>getPost()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPost() {
        Boolean value = true;
        instance.setPost(value);

        assertEquals("'getPost' should be correct.",
            value, instance.getPost());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPost(Boolean post)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPost() {
        Boolean value = true;
        instance.setPost(value);

        assertEquals("'setPost' should be correct.",
            value, TestsHelper.getField(instance, "post"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getGui()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getGui() {
        Boolean value = true;
        instance.setGui(value);

        assertEquals("'getGui' should be correct.",
            value, instance.getGui());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setGui(Boolean gui)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setGui() {
        Boolean value = true;
        instance.setGui(value);

        assertEquals("'setGui' should be correct.",
            value, TestsHelper.getField(instance, "gui"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastPaymentWasThisYear()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastPaymentWasThisYear() {
        Boolean value = true;
        instance.setLastPaymentWasThisYear(value);

        assertEquals("'getLastPaymentWasThisYear' should be correct.",
            value, instance.getLastPaymentWasThisYear());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastPaymentWasThisYear(Boolean lastPaymentWasThisYear)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastPaymentWasThisYear() {
        Boolean value = true;
        instance.setLastPaymentWasThisYear(value);

        assertEquals("'setLastPaymentWasThisYear' should be correct.",
            value, TestsHelper.getField(instance, "lastPaymentWasThisYear"));
    }
}
