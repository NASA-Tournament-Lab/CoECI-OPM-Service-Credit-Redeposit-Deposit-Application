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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.lookup.PeriodType;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link CalculationResultItem} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class CalculationResultItemUnitTests {
    /**
     * <p>
     * Represents the <code>CalculationResultItem</code> instance used in tests.
     * </p>
     */
    private CalculationResultItem instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(CalculationResultItemUnitTests.class);
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
        instance = new CalculationResultItem();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>CalculationResultItem()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new CalculationResultItem();

        assertNull("'startDate' should be correct.", TestsHelper.getField(instance, "startDate"));
        assertNull("'endDate' should be correct.", TestsHelper.getField(instance, "endDate"));
        assertNull("'midDate' should be correct.", TestsHelper.getField(instance, "midDate"));
        assertNull("'effectiveDate' should be correct.", TestsHelper.getField(instance, "effectiveDate"));
        assertNull("'periodType' should be correct.", TestsHelper.getField(instance, "periodType"));
        assertNull("'deductionAmount' should be correct.", TestsHelper.getField(instance, "deductionAmount"));
        assertNull("'totalInterest' should be correct.", TestsHelper.getField(instance, "totalInterest"));
        assertNull("'paymentsApplied' should be correct.", TestsHelper.getField(instance, "paymentsApplied"));
        assertNull("'balance' should be correct.", TestsHelper.getField(instance, "balance"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getStartDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStartDate() {
        Date value = new Date();
        instance.setStartDate(value);

        assertSame("'getStartDate' should be correct.",
            value, instance.getStartDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStartDate(Date startDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStartDate() {
        Date value = new Date();
        instance.setStartDate(value);

        assertSame("'setStartDate' should be correct.",
            value, TestsHelper.getField(instance, "startDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getEndDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getEndDate() {
        Date value = new Date();
        instance.setEndDate(value);

        assertSame("'getEndDate' should be correct.",
            value, instance.getEndDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setEndDate(Date endDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setEndDate() {
        Date value = new Date();
        instance.setEndDate(value);

        assertSame("'setEndDate' should be correct.",
            value, TestsHelper.getField(instance, "endDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getMidDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getMidDate() {
        Date value = new Date();
        instance.setMidDate(value);

        assertSame("'getMidDate' should be correct.",
            value, instance.getMidDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setMidDate(Date midDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setMidDate() {
        Date value = new Date();
        instance.setMidDate(value);

        assertSame("'setMidDate' should be correct.",
            value, TestsHelper.getField(instance, "midDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRefundDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getEffectiveDate() {
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
     * Accuracy test for the method <code>getPeriodType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPeriodType() {
        PeriodType value = new PeriodType();
        instance.setPeriodType(value);

        assertSame("'getPeriodType' should be correct.",
            value, instance.getPeriodType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPeriodType(PeriodType periodType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPeriodType() {
        PeriodType value = new PeriodType();
        instance.setPeriodType(value);

        assertSame("'setPeriodType' should be correct.",
            value, TestsHelper.getField(instance, "periodType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDeductionAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDeductionAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setDeductionAmount(value);

        assertSame("'getDeductionAmount' should be correct.",
            value, instance.getDeductionAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDeductionAmount(BigDecimal deductionAmount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDeductionAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setDeductionAmount(value);

        assertSame("'setDeductionAmount' should be correct.",
            value, TestsHelper.getField(instance, "deductionAmount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalInterest()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotalInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalInterest(value);

        assertSame("'getTotalInterest' should be correct.",
            value, instance.getTotalInterest());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalInterest(BigDecimal totalInterest)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotalInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalInterest(value);

        assertSame("'setTotalInterest' should be correct.",
            value, TestsHelper.getField(instance, "totalInterest"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentsApplied()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentsApplied() {
        BigDecimal value = new BigDecimal(1);
        instance.setPaymentsApplied(value);

        assertSame("'getPaymentsApplied' should be correct.",
            value, instance.getPaymentsApplied());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentsApplied(BigDecimal paymentsApplied)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentsApplied() {
        BigDecimal value = new BigDecimal(1);
        instance.setPaymentsApplied(value);

        assertSame("'setPaymentsApplied' should be correct.",
            value, TestsHelper.getField(instance, "paymentsApplied"));
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
}
