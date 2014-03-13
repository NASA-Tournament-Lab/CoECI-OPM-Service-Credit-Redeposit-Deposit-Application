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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import gov.opm.scrd.TestsHelper;

import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link UserAccountAssignment} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class UserAccountAssignmentUnitTests {
    /**
     * <p>
     * Represents the <code>UserAccountAssignment</code> instance used in tests.
     * </p>
     */
    private UserAccountAssignment instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(UserAccountAssignmentUnitTests.class);
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
        instance = new UserAccountAssignment();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>UserAccountAssignment()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new UserAccountAssignment();

        assertNull("'user' should be correct.", TestsHelper.getField(instance, "user"));
        assertNull("'account' should be correct.", TestsHelper.getField(instance, "account"));
        assertNull("'assignmentDate' should be correct.", TestsHelper.getField(instance, "assignmentDate"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getUser()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getUser() {
        User value = new User();
        instance.setUser(value);

        assertSame("'getUser' should be correct.",
            value, instance.getUser());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setUser(User user)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setUser() {
        User value = new User();
        instance.setUser(value);

        assertSame("'setUser' should be correct.",
            value, TestsHelper.getField(instance, "user"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccount() {
        Account value = new Account();
        instance.setAccount(value);

        assertSame("'getAccount' should be correct.",
            value, instance.getAccount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccount(Account account)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccount() {
        Account value = new Account();
        instance.setAccount(value);

        assertSame("'setAccount' should be correct.",
            value, TestsHelper.getField(instance, "account"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAssignmentDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAssignmentDate() {
        Date value = new Date();
        instance.setAssignmentDate(value);

        assertSame("'getAssignmentDate' should be correct.",
            value, instance.getAssignmentDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAssignmentDate(Date assignmentDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAssignmentDate() {
        Date value = new Date();
        instance.setAssignmentDate(value);

        assertSame("'setAssignmentDate' should be correct.",
            value, TestsHelper.getField(instance, "assignmentDate"));
    }
}
