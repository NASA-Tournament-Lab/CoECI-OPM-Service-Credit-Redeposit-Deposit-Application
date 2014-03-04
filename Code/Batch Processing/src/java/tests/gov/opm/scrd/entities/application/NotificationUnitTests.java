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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.lookup.Role;

import java.util.ArrayList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link Notification} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class NotificationUnitTests {
    /**
     * <p>
     * Represents the <code>Notification</code> instance used in tests.
     * </p>
     */
    private Notification instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(NotificationUnitTests.class);
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
        instance = new Notification();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Notification()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Notification();

        assertNull("'sender' should be correct.", TestsHelper.getField(instance, "sender"));
        assertFalse("'read' should be correct.", (Boolean) TestsHelper.getField(instance, "read"));
        assertNull("'recipient' should be correct.", TestsHelper.getField(instance, "recipient"));
        assertNull("'role' should be correct.", TestsHelper.getField(instance, "role"));
        assertNull("'readBy' should be correct.", TestsHelper.getField(instance, "readBy"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getSender()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSender() {
        String value = "new_value";
        instance.setSender(value);

        assertEquals("'getSender' should be correct.",
            value, instance.getSender());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSender(String sender)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSender() {
        String value = "new_value";
        instance.setSender(value);

        assertEquals("'setSender' should be correct.",
            value, TestsHelper.getField(instance, "sender"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isRead()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isRead() {
        boolean value = true;
        instance.setRead(value);

        assertTrue("'isRead' should be correct.", instance.isRead());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRead(boolean read)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRead() {
        boolean value = true;
        instance.setRead(value);

        assertTrue("'setRead' should be correct.",
            (Boolean) TestsHelper.getField(instance, "read"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRecipient()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRecipient() {
        String value = "new_value";
        instance.setRecipient(value);

        assertEquals("'getRecipient' should be correct.",
            value, instance.getRecipient());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRecipient(String recipient)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRecipient() {
        String value = "new_value";
        instance.setRecipient(value);

        assertEquals("'setRecipient' should be correct.",
            value, TestsHelper.getField(instance, "recipient"));
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
     * Accuracy test for the method <code>getReadBy()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getReadBy() {
        List<String> value = new ArrayList<String>();
        instance.setReadBy(value);

        assertSame("'getReadBy' should be correct.",
            value, instance.getReadBy());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setReadBy(List&lt;String&gt; readBy)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setReadBy() {
        List<String> value = new ArrayList<String>();
        instance.setReadBy(value);

        assertSame("'setReadBy' should be correct.",
            value, TestsHelper.getField(instance, "readBy"));
    }
}
