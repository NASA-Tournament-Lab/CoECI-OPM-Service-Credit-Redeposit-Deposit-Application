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
import gov.opm.scrd.entities.lookup.ActionTab;
import gov.opm.scrd.entities.lookup.Role;
import gov.opm.scrd.entities.lookup.UserStatus;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link User} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class UserUnitTests {
    /**
     * <p>
     * Represents the <code>User</code> instance used in tests.
     * </p>
     */
    private User instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(UserUnitTests.class);
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
        instance = new User();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>User()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new User();

        assertNull("'username' should be correct.", TestsHelper.getField(instance, "username"));
        assertNull("'defaultTab' should be correct.", TestsHelper.getField(instance, "defaultTab"));
        assertNull("'networkId' should be correct.", TestsHelper.getField(instance, "networkId"));
        assertNull("'role' should be correct.", TestsHelper.getField(instance, "role"));
        assertNull("'firstName' should be correct.", TestsHelper.getField(instance, "firstName"));
        assertNull("'lastName' should be correct.", TestsHelper.getField(instance, "lastName"));
        assertNull("'email' should be correct.", TestsHelper.getField(instance, "email"));
        assertNull("'telephone' should be correct.", TestsHelper.getField(instance, "telephone"));
        assertNull("'supervisor' should be correct.", TestsHelper.getField(instance, "supervisor"));
        assertNull("'status' should be correct.", TestsHelper.getField(instance, "status"));
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
     * Accuracy test for the method <code>getDefaultTab()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDefaultTab() {
        ActionTab value = ActionTab.ADMIN;
        instance.setDefaultTab(value);

        assertEquals("'getDefaultTab' should be correct.",
            value, instance.getDefaultTab());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDefaultTab(ActionTab defaultTab)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDefaultTab() {
        ActionTab value = ActionTab.ADMIN;
        instance.setDefaultTab(value);

        assertEquals("'setDefaultTab' should be correct.",
            value, TestsHelper.getField(instance, "defaultTab"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNetworkId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNetworkId() {
        String value = "new_value";
        instance.setNetworkId(value);

        assertEquals("'getNetworkId' should be correct.",
            value, instance.getNetworkId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNetworkId(String networkId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNetworkId() {
        String value = "new_value";
        instance.setNetworkId(value);

        assertEquals("'setNetworkId' should be correct.",
            value, TestsHelper.getField(instance, "networkId"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRole()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRole() {
        Role value = new Role();
        instance.setRole(value);

        assertSame("'getRole' should be correct.",
            value, instance.getRole());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRole(Role role)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRole() {
        Role value = new Role();
        instance.setRole(value);

        assertSame("'setRole' should be correct.",
            value, TestsHelper.getField(instance, "role"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFirstName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getFirstName() {
        String value = "new_value";
        instance.setFirstName(value);

        assertEquals("'getFirstName' should be correct.",
            value, instance.getFirstName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFirstName(String firstName)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFirstName() {
        String value = "new_value";
        instance.setFirstName(value);

        assertEquals("'setFirstName' should be correct.",
            value, TestsHelper.getField(instance, "firstName"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastName() {
        String value = "new_value";
        instance.setLastName(value);

        assertEquals("'getLastName' should be correct.",
            value, instance.getLastName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastName(String lastName)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastName() {
        String value = "new_value";
        instance.setLastName(value);

        assertEquals("'setLastName' should be correct.",
            value, TestsHelper.getField(instance, "lastName"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getEmail()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getEmail() {
        String value = "new_value";
        instance.setEmail(value);

        assertEquals("'getEmail' should be correct.",
            value, instance.getEmail());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setEmail(String email)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setEmail() {
        String value = "new_value";
        instance.setEmail(value);

        assertEquals("'setEmail' should be correct.",
            value, TestsHelper.getField(instance, "email"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTelephone()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTelephone() {
        String value = "new_value";
        instance.setTelephone(value);

        assertEquals("'getTelephone' should be correct.",
            value, instance.getTelephone());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTelephone(String telephone)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTelephone() {
        String value = "new_value";
        instance.setTelephone(value);

        assertEquals("'setTelephone' should be correct.",
            value, TestsHelper.getField(instance, "telephone"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSupervisor()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSupervisor() {
        User value = new User();
        instance.setSupervisor(value);

        assertSame("'getSupervisor' should be correct.",
            value, instance.getSupervisor());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSupervisor(User supervisor)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSupervisor() {
        User value = new User();
        instance.setSupervisor(value);

        assertSame("'setSupervisor' should be correct.",
            value, TestsHelper.getField(instance, "supervisor"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStatus()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStatus() {
        UserStatus value = new UserStatus();
        instance.setStatus(value);

        assertSame("'getStatus' should be correct.",
            value, instance.getStatus());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStatus(UserStatus status)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStatus() {
        UserStatus value = new UserStatus();
        instance.setStatus(value);

        assertSame("'setStatus' should be correct.",
            value, TestsHelper.getField(instance, "status"));
    }
}
