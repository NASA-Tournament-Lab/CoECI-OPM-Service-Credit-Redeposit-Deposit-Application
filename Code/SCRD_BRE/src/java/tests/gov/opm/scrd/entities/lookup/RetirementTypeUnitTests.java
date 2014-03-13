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

package gov.opm.scrd.entities.lookup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link RetirementType} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: description</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.1
 */
public class RetirementTypeUnitTests {
    /**
     * <p>
     * Represents the <code>RetirementType</code> instance used in tests.
     * </p>
     */
    private RetirementType instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(RetirementTypeUnitTests.class);
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
        instance = new RetirementType();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>RetirementType()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new RetirementType();

        assertNull("'description' should be correct.", TestsHelper.getField(instance, "description"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getDescription()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getDescription() {
        String value = "new_value";
        instance.setDescription(value);

        assertEquals("'getDescription' should be correct.",
            value, instance.getDescription());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDescription(String description)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setDescription() {
        String value = "new_value";
        instance.setDescription(value);

        assertEquals("'setDescription' should be correct.",
            value, TestsHelper.getField(instance, "description"));
    }
}
