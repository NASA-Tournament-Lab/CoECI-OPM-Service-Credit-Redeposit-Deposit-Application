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

import java.math.BigDecimal;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link SummaryData} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class SummaryDataUnitTests {
    /**
     * <p>
     * Represents the <code>SummaryData</code> instance used in tests.
     * </p>
     */
    private SummaryData instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(SummaryDataUnitTests.class);
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
        instance = new SummaryData();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>SummaryData()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new SummaryData();

        assertNull("'totalPaymentsRequired' should be correct.",
            TestsHelper.getField(instance, "totalPaymentsRequired"));
        assertNull("'totalInitialInterest' should be correct.", TestsHelper.getField(instance, "totalInitialInterest"));
        assertNull("'totalPaymentsApplied' should be correct.", TestsHelper.getField(instance, "totalPaymentsApplied"));
        assertNull("'totalBalance' should be correct.", TestsHelper.getField(instance, "totalBalance"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getTotalPaymentsRequired()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotalPaymentsRequired() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalPaymentsRequired(value);

        assertSame("'getTotalPaymentsRequired' should be correct.",
            value, instance.getTotalPaymentsRequired());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalPaymentsRequired(BigDecimal totalPaymentsRequired)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotalPaymentsRequired() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalPaymentsRequired(value);

        assertSame("'setTotalPaymentsRequired' should be correct.",
            value, TestsHelper.getField(instance, "totalPaymentsRequired"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalInitialInterest()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotalInitialInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalInitialInterest(value);

        assertSame("'getTotalInitialInterest' should be correct.",
            value, instance.getTotalInitialInterest());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalInitialInterest(BigDecimal totalInitialInterest)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotalInitialInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalInitialInterest(value);

        assertSame("'setTotalInitialInterest' should be correct.",
            value, TestsHelper.getField(instance, "totalInitialInterest"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalPaymentsApplied()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotalPaymentsApplied() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalPaymentsApplied(value);

        assertSame("'getTotalPaymentsApplied' should be correct.",
            value, instance.getTotalPaymentsApplied());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalPaymentsApplied(BigDecimal totalPaymentsApplied)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotalPaymentsApplied() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalPaymentsApplied(value);

        assertSame("'setTotalPaymentsApplied' should be correct.",
            value, TestsHelper.getField(instance, "totalPaymentsApplied"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalBalance()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotalBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalBalance(value);

        assertSame("'getTotalBalance' should be correct.",
            value, instance.getTotalBalance());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalBalance(BigDecimal totalBalance)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotalBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalBalance(value);

        assertSame("'setTotalBalance' should be correct.",
            value, TestsHelper.getField(instance, "totalBalance"));
    }
}
