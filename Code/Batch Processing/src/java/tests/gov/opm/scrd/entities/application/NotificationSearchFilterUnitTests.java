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
import gov.opm.scrd.entities.lookup.Role;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link NotificationSearchFilter} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class NotificationSearchFilterUnitTests {
    /**
     * <p>
     * Represents the <code>NotificationSearchFilter</code> instance used in tests.
     * </p>
     */
    private NotificationSearchFilter instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(NotificationSearchFilterUnitTests.class);
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
        instance = new NotificationSearchFilter();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>NotificationSearchFilter()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new NotificationSearchFilter();

        assertNull("'sender' should be correct.", TestsHelper.getField(instance, "sender"));
        assertNull("'read' should be correct.", TestsHelper.getField(instance, "read"));
        assertNull("'recipient' should be correct.", TestsHelper.getField(instance, "recipient"));
        assertNull("'recipientRole' should be correct.", TestsHelper.getField(instance, "recipientRole"));
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
     * Accuracy test for the method <code>getRead()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRead() {
        Boolean value = true;
        instance.setRead(value);

        assertEquals("'getRead' should be correct.",
            value, instance.getRead());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRead(Boolean read)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRead() {
        Boolean value = true;
        instance.setRead(value);

        assertEquals("'setRead' should be correct.",
            value, TestsHelper.getField(instance, "read"));
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
     * Accuracy test for the method <code>getRecipientRole()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRecipientRole() {
        Role value = new Role();
        instance.setRecipientRole(value);

        assertSame("'getRecipientRole' should be correct.",
            value, instance.getRecipientRole());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRecipientRole(Role recipientRole)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRecipientRole() {
        Role value = new Role();
        instance.setRecipientRole(value);

        assertSame("'setRecipientRole' should be correct.",
            value, TestsHelper.getField(instance, "recipientRole"));
    }
}
