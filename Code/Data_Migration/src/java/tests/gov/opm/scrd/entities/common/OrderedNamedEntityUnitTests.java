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
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link OrderedNamedEntity} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class OrderedNamedEntityUnitTests {
    /**
     * <p>
     * Represents the <code>OrderedNamedEntity</code> instance used in tests.
     * </p>
     */
    private OrderedNamedEntity instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(OrderedNamedEntityUnitTests.class);
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
        instance = new MockOrderedNamedEntity();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>OrderedNamedEntity()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new MockOrderedNamedEntity();

        assertNull("'displayOrder' should be correct.", TestsHelper.getField(instance, "displayOrder"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getDisplayOrder()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDisplayOrder() {
        Integer value = 1;
        instance.setDisplayOrder(value);

        assertEquals("'getDisplayOrder' should be correct.",
            value, instance.getDisplayOrder());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDisplayOrder(Integer displayOrder)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDisplayOrder() {
        Integer value = 1;
        instance.setDisplayOrder(value);

        assertEquals("'setDisplayOrder' should be correct.",
            value, TestsHelper.getField(instance, "displayOrder"));
    }
}
