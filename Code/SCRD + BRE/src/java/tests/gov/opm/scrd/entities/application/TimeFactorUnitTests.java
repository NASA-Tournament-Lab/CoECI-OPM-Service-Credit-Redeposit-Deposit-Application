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
 * Unit tests for {@link TimeFactor} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class TimeFactorUnitTests {
    /**
     * <p>
     * Represents the <code>TimeFactor</code> instance used in tests.
     * </p>
     */
    private TimeFactor instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TimeFactorUnitTests.class);
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
        instance = new TimeFactor();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>TimeFactor()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new TimeFactor();

        assertNull("'numDays' should be correct.", TestsHelper.getField(instance, "numDays"));
        assertNull("'numMonths' should be correct.", TestsHelper.getField(instance, "numMonths"));
        assertNull("'timeFactor' should be correct.", TestsHelper.getField(instance, "timeFactor"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getNumDays()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumDays() {
        Integer value = 1;
        instance.setNumDays(value);

        assertEquals("'getNumDays' should be correct.",
            value, instance.getNumDays());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumDays(Integer numDays)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumDays() {
        Integer value = 1;
        instance.setNumDays(value);

        assertEquals("'setNumDays' should be correct.",
            value, TestsHelper.getField(instance, "numDays"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumMonths()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumMonths() {
        Integer value = 1;
        instance.setNumMonths(value);

        assertEquals("'getNumMonths' should be correct.",
            value, instance.getNumMonths());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumMonths(Integer numMonths)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumMonths() {
        Integer value = 1;
        instance.setNumMonths(value);

        assertEquals("'setNumMonths' should be correct.",
            value, TestsHelper.getField(instance, "numMonths"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTimeFactor()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTimeFactor() {
        BigDecimal value = new BigDecimal(1);
        instance.setTimeFactor(value);

        assertSame("'getTimeFactor' should be correct.",
            value, instance.getTimeFactor());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTimeFactor(BigDecimal timeFactor)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTimeFactor() {
        BigDecimal value = new BigDecimal(1);
        instance.setTimeFactor(value);

        assertSame("'setTimeFactor' should be correct.",
            value, TestsHelper.getField(instance, "timeFactor"));
    }
}
