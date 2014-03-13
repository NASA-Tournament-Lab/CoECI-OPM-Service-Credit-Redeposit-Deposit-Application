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
import static org.junit.Assert.assertSame;
import gov.opm.scrd.TestsHelper;

import java.util.ArrayList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for SearchResult&lt;T&gt; class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class SearchResultUnitTests {
    /**
     * <p>
     * Represents the <code>SearchResult&lt;T&gt;</code> instance used in tests.
     * </p>
     */
    private SearchResult<Object> instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(SearchResultUnitTests.class);
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
        instance = new SearchResult<Object>();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>SearchResult()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new SearchResult<Object>();

        assertEquals("'total' should be correct.", 0, TestsHelper.getField(instance, "total"));
        assertEquals("'totalPageCount' should be correct.", 0, TestsHelper.getField(instance, "totalPageCount"));
        assertNull("'items' should be correct.", TestsHelper.getField(instance, "items"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getTotal()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotal() {
        int value = 1;
        instance.setTotal(value);

        assertEquals("'getTotal' should be correct.",
            value, instance.getTotal());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotal(int total)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotal() {
        int value = 1;
        instance.setTotal(value);

        assertEquals("'setTotal' should be correct.",
            value, TestsHelper.getField(instance, "total"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalPageCount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotalPageCount() {
        int value = 1;
        instance.setTotalPageCount(value);

        assertEquals("'getTotalPageCount' should be correct.",
            value, instance.getTotalPageCount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalPageCount(int totalPageCount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotalPageCount() {
        int value = 1;
        instance.setTotalPageCount(value);

        assertEquals("'setTotalPageCount' should be correct.",
            value, TestsHelper.getField(instance, "totalPageCount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getItems()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getItems() {
        List<Object> value = new ArrayList<Object>();
        instance.setItems(value);

        assertSame("'getItems' should be correct.",
            value, instance.getItems());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setItems(List&lt;T&gt; items)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setItems() {
        List<Object> value = new ArrayList<Object>();
        instance.setItems(value);

        assertSame("'setItems' should be correct.",
            value, TestsHelper.getField(instance, "items"));
    }
}
