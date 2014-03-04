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
 * Unit tests for {@link CalculationVersion} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: version, lineNumber</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.1
 */
public class CalculationVersionUnitTests {
    /**
     * <p>
     * Represents the <code>CalculationVersion</code> instance used in tests.
     * </p>
     */
    private CalculationVersion instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(CalculationVersionUnitTests.class);
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
        instance = new CalculationVersion();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>CalculationVersion()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new CalculationVersion();

        assertNull("'name' should be correct.", TestsHelper.getField(instance, "name"));
        assertNull("'calculations' should be correct.", TestsHelper.getField(instance, "calculations"));
        assertNull("'calculationResult' should be correct.", TestsHelper.getField(instance, "calculationResult"));
        assertNull("'calculationDate' should be correct.", TestsHelper.getField(instance, "calculationDate"));
        assertNull("'version' should be correct.", TestsHelper.getField(instance, "version"));
        assertNull("'lineNumber' should be correct.", TestsHelper.getField(instance, "lineNumber"));
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
     * Accuracy test for the method <code>getCalculations()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCalculations() {
        List<Calculation> value = new ArrayList<Calculation>();
        instance.setCalculations(value);

        assertSame("'getCalculations' should be correct.",
            value, instance.getCalculations());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCalculations(List&lt;Calculation&gt; calculations)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCalculations() {
        List<Calculation> value = new ArrayList<Calculation>();
        instance.setCalculations(value);

        assertSame("'setCalculations' should be correct.",
            value, TestsHelper.getField(instance, "calculations"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCalculationResult()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCalculationResult() {
        CalculationResult value = new CalculationResult();
        instance.setCalculationResult(value);

        assertSame("'getCalculationResult' should be correct.",
            value, instance.getCalculationResult());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCalculationResult(CalculationResult calculationResult)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCalculationResult() {
        CalculationResult value = new CalculationResult();
        instance.setCalculationResult(value);

        assertSame("'setCalculationResult' should be correct.",
            value, TestsHelper.getField(instance, "calculationResult"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCalculationDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCalculationDate() {
        Date value = new Date();
        instance.setCalculationDate(value);

        assertSame("'getCalculationDate' should be correct.",
            value, instance.getCalculationDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCalculationDate(Date calculationDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCalculationDate() {
        Date value = new Date();
        instance.setCalculationDate(value);

        assertSame("'setCalculationDate' should be correct.",
            value, TestsHelper.getField(instance, "calculationDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getVersion()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getVersion() {
        Integer value = 1;
        instance.setVersion(value);

        assertEquals("'getVersion' should be correct.",
            value, instance.getVersion());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setVersion(Integer version)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setVersion() {
        Integer value = 1;
        instance.setVersion(value);

        assertEquals("'setVersion' should be correct.",
            value, TestsHelper.getField(instance, "version"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLineNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getLineNumber() {
        Integer value = 1;
        instance.setLineNumber(value);

        assertEquals("'getLineNumber' should be correct.",
            value, instance.getLineNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLineNumber(Integer lineNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setLineNumber() {
        Integer value = 1;
        instance.setLineNumber(value);

        assertEquals("'setLineNumber' should be correct.",
            value, TestsHelper.getField(instance, "lineNumber"));
    }
}
