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
 * Unit tests for {@link Holiday} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class HolidayUnitTests {
    /**
     * <p>
     * Represents the <code>Holiday</code> instance used in tests.
     * </p>
     */
    private Holiday instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(HolidayUnitTests.class);
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
        instance = new Holiday();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Holiday()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Holiday();

        assertNull("'holiday' should be correct.", TestsHelper.getField(instance, "holiday"));
        assertNull("'exactDate' should be correct.", TestsHelper.getField(instance, "exactDate"));
        assertNull("'weekDay' should be correct.", TestsHelper.getField(instance, "weekDay"));
        assertNull("'monthNumber' should be correct.", TestsHelper.getField(instance, "monthNumber"));
        assertNull("'dayOfMonth' should be correct.", TestsHelper.getField(instance, "dayOfMonth"));
        assertNull("'weekOfMonth' should be correct.", TestsHelper.getField(instance, "weekOfMonth"));
        assertNull("'holidayId' should be correct.", TestsHelper.getField(instance, "holidayId"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getHoliday()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getHoliday() {
        String value = "new_value";
        instance.setHoliday(value);

        assertEquals("'getHoliday' should be correct.",
            value, instance.getHoliday());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setHoliday(String holiday)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setHoliday() {
        String value = "new_value";
        instance.setHoliday(value);

        assertEquals("'setHoliday' should be correct.",
            value, TestsHelper.getField(instance, "holiday"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getExactDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getExactDate() {
        Boolean value = true;
        instance.setExactDate(value);

        assertEquals("'getExactDate' should be correct.",
            value, instance.getExactDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setExactDate(Boolean exactDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setExactDate() {
        Boolean value = true;
        instance.setExactDate(value);

        assertEquals("'setExactDate' should be correct.",
            value, TestsHelper.getField(instance, "exactDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getWeekDay()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getWeekDay() {
        Integer value = 1;
        instance.setWeekDay(value);

        assertEquals("'getWeekDay' should be correct.",
            value, instance.getWeekDay());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setWeekDay(Integer weekDay)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setWeekDay() {
        Integer value = 1;
        instance.setWeekDay(value);

        assertEquals("'setWeekDay' should be correct.",
            value, TestsHelper.getField(instance, "weekDay"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getMonthNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getMonthNumber() {
        Integer value = 1;
        instance.setMonthNumber(value);

        assertEquals("'getMonthNumber' should be correct.",
            value, instance.getMonthNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setMonthNumber(Integer monthNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setMonthNumber() {
        Integer value = 1;
        instance.setMonthNumber(value);

        assertEquals("'setMonthNumber' should be correct.",
            value, TestsHelper.getField(instance, "monthNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDayOfMonth()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDayOfMonth() {
        Integer value = 1;
        instance.setDayOfMonth(value);

        assertEquals("'getDayOfMonth' should be correct.",
            value, instance.getDayOfMonth());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDayOfMonth(Integer dayOfMonth)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDayOfMonth() {
        Integer value = 1;
        instance.setDayOfMonth(value);

        assertEquals("'setDayOfMonth' should be correct.",
            value, TestsHelper.getField(instance, "dayOfMonth"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getWeekOfMonth()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getWeekOfMonth() {
        Integer value = 1;
        instance.setWeekOfMonth(value);

        assertEquals("'getWeekOfMonth' should be correct.",
            value, instance.getWeekOfMonth());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setWeekOfMonth(Integer weekOfMonth)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setWeekOfMonth() {
        Integer value = 1;
        instance.setWeekOfMonth(value);

        assertEquals("'setWeekOfMonth' should be correct.",
            value, TestsHelper.getField(instance, "weekOfMonth"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getHolidayId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getHolidayId() {
        Integer value = 1;
        instance.setHolidayId(value);

        assertEquals("'getHolidayId' should be correct.",
            value, instance.getHolidayId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setHolidayId(Integer holidayId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setHolidayId() {
        Integer value = 1;
        instance.setHolidayId(value);

        assertEquals("'setHolidayId' should be correct.",
            value, TestsHelper.getField(instance, "holidayId"));
    }
}
