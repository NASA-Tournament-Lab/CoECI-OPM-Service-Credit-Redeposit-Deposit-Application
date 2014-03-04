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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link IdentifiableEntity} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class IdentifiableEntityUnitTests {
    /**
     * <p>
     * Represents the <code>IdentifiableEntity</code> instance used in tests.
     * </p>
     */
    private IdentifiableEntity instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(IdentifiableEntityUnitTests.class);
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
        instance = new MockIdentifiableEntity();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>IdentifiableEntity()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new MockIdentifiableEntity();

        assertEquals("'id' should be correct.", 0L, TestsHelper.getField(instance, "id"));
        assertFalse("'deleted' should be correct.", (Boolean) TestsHelper.getField(instance, "deleted"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getId() {
        long value = 1L;
        instance.setId(value);

        assertEquals("'getId' should be correct.",
            value, instance.getId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setId(long id)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setId() {
        long value = 1L;
        instance.setId(value);

        assertEquals("'setId' should be correct.",
            value, TestsHelper.getField(instance, "id"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isDeleted()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isDeleted() {
        boolean value = true;
        instance.setDeleted(value);

        assertTrue("'isDeleted' should be correct.", instance.isDeleted());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDeleted(boolean deleted)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDeleted() {
        boolean value = true;
        instance.setDeleted(value);

        assertTrue("'setDeleted' should be correct.",
            (Boolean) TestsHelper.getField(instance, "deleted"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>equals(Object obj)</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_equals_False1() {
        Object obj = new Object();

        assertFalse("'equals' should be correct.",
            instance.equals(obj));
    }

    /**
     * <p>
     * Accuracy test for the method <code>equals(Object obj)</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_equals_False2() {
        Object obj = null;

        assertFalse("'equals' should be correct.",
            instance.equals(obj));
    }

    /**
     * <p>
     * Accuracy test for the method <code>equals(Object obj)</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_equals_False3() {
        Error obj = new Error();

        assertFalse("'equals' should be correct.",
            instance.equals(obj));
    }

    /**
     * <p>
     * Accuracy test for the method <code>equals(Object obj)</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_equals_False4() {
        instance.setId(1);
        MockIdentifiableEntity obj = new MockIdentifiableEntity();
        obj.setId(2);

        assertFalse("'equals' should be correct.",
            instance.equals(obj));
    }

    /**
     * <p>
     * Accuracy test for the method <code>equals(Object obj)</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_equals_True1() {
        assertTrue("'equals' should be correct.",
            instance.equals(instance));
    }

    /**
     * <p>
     * Accuracy test for the method <code>equals(Object obj)</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_equals_True2() {
        instance.setId(1);
        MockIdentifiableEntity obj = new MockIdentifiableEntity();
        obj.setId(1);

        assertTrue("'equals' should be correct.",
            instance.equals(obj));
    }

    /**
     * <p>
     * Accuracy test for the method <code>hashCode()</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_hashCode() {
        instance.setId(1);

        assertEquals("'hashCode' should be correct.", 1, instance.hashCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>toString()</code>.<br>
     * The result should be correct.
     * </p>
     */
    @Test
    public void test_toString() {
        instance.setId(1);
        instance.setDeleted(true);

        String content = instance.toString();
        assertTrue("'toString' should be correct.", content.contains("\"id\":1"));
        assertTrue("'toString' should be correct.", content.contains("\"deleted\":true"));
    }
}
