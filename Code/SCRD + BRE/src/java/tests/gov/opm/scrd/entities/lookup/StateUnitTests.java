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
 * Unit tests for {@link State} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: abbreviation</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.1
 */
public class StateUnitTests {
    /**
     * <p>
     * Represents the <code>State</code> instance used in tests.
     * </p>
     */
    private State instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(StateUnitTests.class);
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
        instance = new State();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>State()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new State();

        assertNull("'abbreviation' should be correct.", TestsHelper.getField(instance, "abbreviation"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getAbbreviation()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getAbbreviation() {
        String value = "new_value";
        instance.setAbbreviation(value);

        assertEquals("'getAbbreviation' should be correct.",
            value, instance.getAbbreviation());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAbbreviation(String abbreviation)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setAbbreviation() {
        String value = "new_value";
        instance.setAbbreviation(value);

        assertEquals("'setAbbreviation' should be correct.",
            value, TestsHelper.getField(instance, "abbreviation"));
    }
}
