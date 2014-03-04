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
 * Unit tests for {@link UserPermission} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class UserPermissionUnitTests {
    /**
     * <p>
     * Represents the <code>UserPermission</code> instance used in tests.
     * </p>
     */
    private UserPermission instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(UserPermissionUnitTests.class);
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
        instance = new UserPermission();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>UserPermission()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new UserPermission();

        assertNull("'username' should be correct.", TestsHelper.getField(instance, "username"));
        assertNull("'action' should be correct.", TestsHelper.getField(instance, "action"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getUsername()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getUsername() {
        String value = "new_value";
        instance.setUsername(value);

        assertEquals("'getUsername' should be correct.",
            value, instance.getUsername());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setUsername(String username)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setUsername() {
        String value = "new_value";
        instance.setUsername(value);

        assertEquals("'setUsername' should be correct.",
            value, TestsHelper.getField(instance, "username"));
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
