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
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link HelpItem} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class HelpItemUnitTests {
    /**
     * <p>
     * Represents the <code>HelpItem</code> instance used in tests.
     * </p>
     */
    private HelpItem instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(HelpItemUnitTests.class);
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
        instance = new HelpItem();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>HelpItem()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new HelpItem();

        assertNull("'title' should be correct.", TestsHelper.getField(instance, "title"));
        assertNull("'summary' should be correct.", TestsHelper.getField(instance, "summary"));
        assertNull("'content' should be correct.", TestsHelper.getField(instance, "content"));
        assertNull("'related' should be correct.", TestsHelper.getField(instance, "related"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getTitle()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTitle() {
        String value = "new_value";
        instance.setTitle(value);

        assertEquals("'getTitle' should be correct.",
            value, instance.getTitle());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTitle(String title)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTitle() {
        String value = "new_value";
        instance.setTitle(value);

        assertEquals("'setTitle' should be correct.",
            value, TestsHelper.getField(instance, "title"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSummary()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSummary() {
        String value = "new_value";
        instance.setSummary(value);

        assertEquals("'getSummary' should be correct.",
            value, instance.getSummary());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSummary(String summary)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSummary() {
        String value = "new_value";
        instance.setSummary(value);

        assertEquals("'setSummary' should be correct.",
            value, TestsHelper.getField(instance, "summary"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getContent()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getContent() {
        String value = "new_value";
        instance.setContent(value);

        assertEquals("'getContent' should be correct.",
            value, instance.getContent());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setContent(String content)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setContent() {
        String value = "new_value";
        instance.setContent(value);

        assertEquals("'setContent' should be correct.",
            value, TestsHelper.getField(instance, "content"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRelated()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRelated() {
        List<HelpItem> value = new ArrayList<HelpItem>();
        instance.setRelated(value);

        assertSame("'getRelated' should be correct.",
            value, instance.getRelated());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRelated(List&lt;HelpItem&gt; related)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRelated() {
        List<HelpItem> value = new ArrayList<HelpItem>();
        instance.setRelated(value);

        assertSame("'setRelated' should be correct.",
            value, TestsHelper.getField(instance, "related"));
    }
}
