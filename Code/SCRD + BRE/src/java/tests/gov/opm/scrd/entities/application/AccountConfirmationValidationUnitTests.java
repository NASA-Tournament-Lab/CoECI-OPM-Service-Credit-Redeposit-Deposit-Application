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
import gov.opm.scrd.entities.lookup.ApprovalStatus;

import java.util.ArrayList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link AccountConfirmationValidation} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class AccountConfirmationValidationUnitTests {
    /**
     * <p>
     * Represents the <code>AccountConfirmationValidation</code> instance used in tests.
     * </p>
     */
    private AccountConfirmationValidation instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AccountConfirmationValidationUnitTests.class);
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
        instance = new AccountConfirmationValidation();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AccountConfirmationValidation()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AccountConfirmationValidation();

        assertEquals("'accountId' should be correct.", 0L, TestsHelper.getField(instance, "accountId"));
        assertNull("'dataCheckStatus' should be correct.", TestsHelper.getField(instance, "dataCheckStatus"));
        assertNull("'dataCheckStatusValidator' should be correct.",
            TestsHelper.getField(instance, "dataCheckStatusValidator"));
        assertNull("'dataCheckStatusReason' should be correct.",
            TestsHelper.getField(instance, "dataCheckStatusReason"));
        assertNull("'entries' should be correct.", TestsHelper.getField(instance, "entries"));
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
     * Accuracy test for the method <code>getDataCheckStatus()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDataCheckStatus() {
        ApprovalStatus value = ApprovalStatus.APPROVED;
        instance.setDataCheckStatus(value);

        assertEquals("'getDataCheckStatus' should be correct.",
            value, instance.getDataCheckStatus());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDataCheckStatus(ApprovalStatus dataCheckStatus)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDataCheckStatus() {
        ApprovalStatus value = ApprovalStatus.APPROVED;
        instance.setDataCheckStatus(value);

        assertEquals("'setDataCheckStatus' should be correct.",
            value, TestsHelper.getField(instance, "dataCheckStatus"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDataCheckStatusValidator()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDataCheckStatusValidator() {
        String value = "new_value";
        instance.setDataCheckStatusValidator(value);

        assertEquals("'getDataCheckStatusValidator' should be correct.",
            value, instance.getDataCheckStatusValidator());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDataCheckStatusValidator(String dataCheckStatusValidator)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDataCheckStatusValidator() {
        String value = "new_value";
        instance.setDataCheckStatusValidator(value);

        assertEquals("'setDataCheckStatusValidator' should be correct.",
            value, TestsHelper.getField(instance, "dataCheckStatusValidator"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDataCheckStatusReason()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDataCheckStatusReason() {
        String value = "new_value";
        instance.setDataCheckStatusReason(value);

        assertEquals("'getDataCheckStatusReason' should be correct.",
            value, instance.getDataCheckStatusReason());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDataCheckStatusReason(String dataCheckStatusReason)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDataCheckStatusReason() {
        String value = "new_value";
        instance.setDataCheckStatusReason(value);

        assertEquals("'setDataCheckStatusReason' should be correct.",
            value, TestsHelper.getField(instance, "dataCheckStatusReason"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getEntries()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getEntries() {
        List<AccountConfirmationValidationEntry> value = new ArrayList<AccountConfirmationValidationEntry>();
        instance.setEntries(value);

        assertSame("'getEntries' should be correct.",
            value, instance.getEntries());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setEntries(List&lt;AccountConfirmationValidationEntry&gt; entries)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setEntries() {
        List<AccountConfirmationValidationEntry> value = new ArrayList<AccountConfirmationValidationEntry>();
        instance.setEntries(value);

        assertSame("'setEntries' should be correct.",
            value, TestsHelper.getField(instance, "entries"));
    }
}
