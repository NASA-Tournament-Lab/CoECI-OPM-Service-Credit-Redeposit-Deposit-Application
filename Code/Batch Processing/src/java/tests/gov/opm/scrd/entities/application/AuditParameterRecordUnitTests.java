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
 * Unit tests for {@link AuditParameterRecord} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class AuditParameterRecordUnitTests {
    /**
     * <p>
     * Represents the <code>AuditParameterRecord</code> instance used in tests.
     * </p>
     */
    private AuditParameterRecord instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AuditParameterRecordUnitTests.class);
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
        instance = new AuditParameterRecord();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AuditParameterRecord()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AuditParameterRecord();

        assertNull("'itemId' should be correct.", TestsHelper.getField(instance, "itemId"));
        assertNull("'itemType' should be correct.", TestsHelper.getField(instance, "itemType"));
        assertNull("'propertyName' should be correct.", TestsHelper.getField(instance, "propertyName"));
        assertNull("'previousValue' should be correct.", TestsHelper.getField(instance, "previousValue"));
        assertNull("'newValue' should be correct.", TestsHelper.getField(instance, "newValue"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getItemId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getItemId() {
        Long value = 1L;
        instance.setItemId(value);

        assertEquals("'getItemId' should be correct.",
            value, instance.getItemId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setItemId(Long itemId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setItemId() {
        Long value = 1L;
        instance.setItemId(value);

        assertEquals("'setItemId' should be correct.",
            value, TestsHelper.getField(instance, "itemId"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getItemType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getItemType() {
        String value = "new_value";
        instance.setItemType(value);

        assertEquals("'getItemType' should be correct.",
            value, instance.getItemType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setItemType(String itemType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setItemType() {
        String value = "new_value";
        instance.setItemType(value);

        assertEquals("'setItemType' should be correct.",
            value, TestsHelper.getField(instance, "itemType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPropertyName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPropertyName() {
        String value = "new_value";
        instance.setPropertyName(value);

        assertEquals("'getPropertyName' should be correct.",
            value, instance.getPropertyName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPropertyName(String propertyName)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPropertyName() {
        String value = "new_value";
        instance.setPropertyName(value);

        assertEquals("'setPropertyName' should be correct.",
            value, TestsHelper.getField(instance, "propertyName"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPreviousValue()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPreviousValue() {
        String value = "new_value";
        instance.setPreviousValue(value);

        assertEquals("'getPreviousValue' should be correct.",
            value, instance.getPreviousValue());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPreviousValue(String previousValue)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPreviousValue() {
        String value = "new_value";
        instance.setPreviousValue(value);

        assertEquals("'setPreviousValue' should be correct.",
            value, TestsHelper.getField(instance, "previousValue"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNewValue()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNewValue() {
        String value = "new_value";
        instance.setNewValue(value);

        assertEquals("'getNewValue' should be correct.",
            value, instance.getNewValue());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNewValue(String newValue)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNewValue() {
        String value = "new_value";
        instance.setNewValue(value);

        assertEquals("'setNewValue' should be correct.",
            value, TestsHelper.getField(instance, "newValue"));
    }
}
