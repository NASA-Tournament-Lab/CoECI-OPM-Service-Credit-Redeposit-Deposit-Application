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

import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link AccountSearchFilter} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class AccountSearchFilterUnitTests {
    /**
     * <p>
     * Represents the <code>AccountSearchFilter</code> instance used in tests.
     * </p>
     */
    private AccountSearchFilter instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AccountSearchFilterUnitTests.class);
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
        instance = new AccountSearchFilter();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AccountSearchFilter()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AccountSearchFilter();

        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'ssn' should be correct.", TestsHelper.getField(instance, "ssn"));
        assertNull("'firstName' should be correct.", TestsHelper.getField(instance, "firstName"));
        assertNull("'middleName' should be correct.", TestsHelper.getField(instance, "middleName"));
        assertNull("'lastName' should be correct.", TestsHelper.getField(instance, "lastName"));
        assertNull("'birthDate' should be correct.", TestsHelper.getField(instance, "birthDate"));
        assertFalse("'excludeHistory' should be correct.", (Boolean) TestsHelper.getField(instance, "excludeHistory"));
        assertNull("'assigned' should be correct.", TestsHelper.getField(instance, "assigned"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getClaimNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getClaimNumber() {
        String value = "new_value";
        instance.setClaimNumber(value);

        assertEquals("'getClaimNumber' should be correct.",
            value, instance.getClaimNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setClaimNumber(String claimNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setClaimNumber() {
        String value = "new_value";
        instance.setClaimNumber(value);

        assertEquals("'setClaimNumber' should be correct.",
            value, TestsHelper.getField(instance, "claimNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSsn()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSsn() {
        String value = "new_value";
        instance.setSsn(value);

        assertEquals("'getSsn' should be correct.",
            value, instance.getSsn());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSsn(String ssn)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSsn() {
        String value = "new_value";
        instance.setSsn(value);

        assertEquals("'setSsn' should be correct.",
            value, TestsHelper.getField(instance, "ssn"));
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
     * Accuracy test for the method <code>getMiddleName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getMiddleName() {
        String value = "new_value";
        instance.setMiddleName(value);

        assertEquals("'getMiddleName' should be correct.",
            value, instance.getMiddleName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setMiddleName(String middleName)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setMiddleName() {
        String value = "new_value";
        instance.setMiddleName(value);

        assertEquals("'setMiddleName' should be correct.",
            value, TestsHelper.getField(instance, "middleName"));
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
     * Accuracy test for the method <code>getBirthDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBirthDate() {
        Date value = new Date();
        instance.setBirthDate(value);

        assertSame("'getBirthDate' should be correct.",
            value, instance.getBirthDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBirthDate(Date birthDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBirthDate() {
        Date value = new Date();
        instance.setBirthDate(value);

        assertSame("'setBirthDate' should be correct.",
            value, TestsHelper.getField(instance, "birthDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isExcludeHistory()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isExcludeHistory() {
        boolean value = true;
        instance.setExcludeHistory(value);

        assertTrue("'isExcludeHistory' should be correct.", instance.isExcludeHistory());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setExcludeHistory(boolean excludeHistory)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setExcludeHistory() {
        boolean value = true;
        instance.setExcludeHistory(value);

        assertTrue("'setExcludeHistory' should be correct.",
            (Boolean) TestsHelper.getField(instance, "excludeHistory"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAssigned()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAssigned() {
        Boolean value = true;
        instance.setAssigned(value);

        assertEquals("'getAssigned' should be correct.",
            value, instance.getAssigned());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAssigned(Boolean assigned)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAssigned() {
        Boolean value = true;
        instance.setAssigned(value);

        assertEquals("'setAssigned' should be correct.",
            value, TestsHelper.getField(instance, "assigned"));
    }
}
