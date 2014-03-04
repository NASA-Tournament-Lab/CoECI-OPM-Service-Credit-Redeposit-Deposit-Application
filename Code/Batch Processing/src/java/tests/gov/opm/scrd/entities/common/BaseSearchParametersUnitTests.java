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

package gov.opm.scrd.entities.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link BaseSearchParameters} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class BaseSearchParametersUnitTests {
    /**
     * <p>
     * Represents the <code>BaseSearchParameters</code> instance used in tests.
     * </p>
     */
    private BaseSearchParameters instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BaseSearchParametersUnitTests.class);
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
        instance = new MockBaseSearchParameters();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>BaseSearchParameters()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new MockBaseSearchParameters();

        assertNull("'sortColumn' should be correct.", TestsHelper.getField(instance, "sortColumn"));
        assertNull("'sortOrder' should be correct.", TestsHelper.getField(instance, "sortOrder"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getSortColumn()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSortColumn() {
        String value = "new_value";
        instance.setSortColumn(value);

        assertEquals("'getSortColumn' should be correct.",
            value, instance.getSortColumn());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSortColumn(String sortColumn)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSortColumn() {
        String value = "new_value";
        instance.setSortColumn(value);

        assertEquals("'setSortColumn' should be correct.",
            value, TestsHelper.getField(instance, "sortColumn"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSortOrder()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSortOrder() {
        SortOrder value = SortOrder.ASC;
        instance.setSortOrder(value);

        assertEquals("'getSortOrder' should be correct.",
            value, instance.getSortOrder());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSortOrder(SortOrder sortOrder)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSortOrder() {
        SortOrder value = SortOrder.ASC;
        instance.setSortOrder(value);

        assertEquals("'setSortOrder' should be correct.",
            value, TestsHelper.getField(instance, "sortOrder"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>toString()</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_toString() {
        instance.setSortColumn("sortColumn1");
        instance.setSortOrder(SortOrder.ASC);

        String content = instance.toString();
        assertTrue("'toString' should be correct.", content.contains("\"sortColumn\":\"sortColumn1\""));
        assertTrue("'toString' should be correct.", content.contains("\"sortOrder\":\"ASC\""));
    }
}
