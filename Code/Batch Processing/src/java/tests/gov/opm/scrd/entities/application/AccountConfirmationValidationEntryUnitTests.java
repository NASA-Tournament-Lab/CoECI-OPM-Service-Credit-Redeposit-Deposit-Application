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
 * Unit tests for {@link AccountConfirmationValidationEntry} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class AccountConfirmationValidationEntryUnitTests {
    /**
     * <p>
     * Represents the <code>AccountConfirmationValidationEntry</code> instance used in tests.
     * </p>
     */
    private AccountConfirmationValidationEntry instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AccountConfirmationValidationEntryUnitTests.class);
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
        instance = new AccountConfirmationValidationEntry();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AccountConfirmationValidationEntry()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AccountConfirmationValidationEntry();

        assertNull("'fieldName' should be correct.", TestsHelper.getField(instance, "fieldName"));
        assertNull("'valid' should be correct.", TestsHelper.getField(instance, "valid"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getFieldName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getFieldName() {
        String value = "new_value";
        instance.setFieldName(value);

        assertEquals("'getFieldName' should be correct.",
            value, instance.getFieldName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFieldName(String fieldName)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFieldName() {
        String value = "new_value";
        instance.setFieldName(value);

        assertEquals("'setFieldName' should be correct.",
            value, TestsHelper.getField(instance, "fieldName"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getValid()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getValid() {
        Boolean value = true;
        instance.setValid(value);

        assertEquals("'getValid' should be correct.",
            value, instance.getValid());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setValid(Boolean valid)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setValid() {
        Boolean value = true;
        instance.setValid(value);

        assertEquals("'setValid' should be correct.",
            value, TestsHelper.getField(instance, "valid"));
    }
}
