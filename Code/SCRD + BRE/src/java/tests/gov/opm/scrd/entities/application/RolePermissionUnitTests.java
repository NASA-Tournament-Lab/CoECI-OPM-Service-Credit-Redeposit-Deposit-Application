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
 * Unit tests for {@link RolePermission} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class RolePermissionUnitTests {
    /**
     * <p>
     * Represents the <code>RolePermission</code> instance used in tests.
     * </p>
     */
    private RolePermission instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(RolePermissionUnitTests.class);
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
        instance = new RolePermission();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>RolePermission()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new RolePermission();

        assertNull("'role' should be correct.", TestsHelper.getField(instance, "role"));
        assertNull("'action' should be correct.", TestsHelper.getField(instance, "action"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getRole()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRole() {
        String value = "new_value";
        instance.setRole(value);

        assertEquals("'getRole' should be correct.",
            value, instance.getRole());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRole(String role)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRole() {
        String value = "new_value";
        instance.setRole(value);

        assertEquals("'setRole' should be correct.",
            value, TestsHelper.getField(instance, "role"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAction()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAction() {
        String value = "new_value";
        instance.setAction(value);

        assertEquals("'getAction' should be correct.",
            value, instance.getAction());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAction(String action)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAction() {
        String value = "new_value";
        instance.setAction(value);

        assertEquals("'setAction' should be correct.",
            value, TestsHelper.getField(instance, "action"));
    }
}
