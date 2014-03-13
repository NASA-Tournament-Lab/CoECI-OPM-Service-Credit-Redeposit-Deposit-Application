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

import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link AccountNote} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: priority</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.1
 */
public class AccountNoteUnitTests {
    /**
     * <p>
     * Represents the <code>AccountNote</code> instance used in tests.
     * </p>
     */
    private AccountNote instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AccountNoteUnitTests.class);
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
        instance = new AccountNote();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AccountNote()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AccountNote();

        assertNull("'date' should be correct.", TestsHelper.getField(instance, "date"));
        assertNull("'writer' should be correct.", TestsHelper.getField(instance, "writer"));
        assertNull("'text' should be correct.", TestsHelper.getField(instance, "text"));
        assertEquals("'accountId' should be correct.", 0L, TestsHelper.getField(instance, "accountId"));
        assertNull("'accountId' should be correct.", TestsHelper.getField(instance, "priority"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDate() {
        Date value = new Date();
        instance.setDate(value);

        assertSame("'getDate' should be correct.",
            value, instance.getDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDate(Date date)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDate() {
        Date value = new Date();
        instance.setDate(value);

        assertSame("'setDate' should be correct.",
            value, TestsHelper.getField(instance, "date"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getWriter()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getWriter() {
        String value = "new_value";
        instance.setWriter(value);

        assertEquals("'getWriter' should be correct.",
            value, instance.getWriter());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setWriter(String writer)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setWriter() {
        String value = "new_value";
        instance.setWriter(value);

        assertEquals("'setWriter' should be correct.",
            value, TestsHelper.getField(instance, "writer"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getText()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getText() {
        String value = "new_value";
        instance.setText(value);

        assertEquals("'getText' should be correct.",
            value, instance.getText());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setText(String text)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setText() {
        String value = "new_value";
        instance.setText(value);

        assertEquals("'setText' should be correct.",
            value, TestsHelper.getField(instance, "text"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccountId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccountId() {
        long value = 1L;
        instance.setAccountId(value);

        assertEquals("'getAccountId' should be correct.",
            value, instance.getAccountId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccountId(long accountId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccountId() {
        long value = 1L;
        instance.setAccountId(value);

        assertEquals("'setAccountId' should be correct.",
            value, TestsHelper.getField(instance, "accountId"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPriority()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getPriority() {
        Integer value = 1;
        instance.setPriority(value);

        assertEquals("'getPriority' should be correct.",
            value, instance.getPriority());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPriority(Integer priority)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setPriority() {
        Integer value = 1;
        instance.setPriority(value);

        assertEquals("'setPriority' should be correct.",
            value, TestsHelper.getField(instance, "priority"));
    }
}
