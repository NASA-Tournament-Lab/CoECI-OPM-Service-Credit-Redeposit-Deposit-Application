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
import gov.opm.scrd.entities.lookup.RetirementType;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link GLCode} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class GLCodeUnitTests {
    /**
     * <p>
     * Represents the <code>GLCode</code> instance used in tests.
     * </p>
     */
    private GLCode instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(GLCodeUnitTests.class);
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
        instance = new GLCode();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>GLCode()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new GLCode();

        assertNull("'name' should be correct.", TestsHelper.getField(instance, "name"));
        assertNull("'code' should be correct.", TestsHelper.getField(instance, "code"));
        assertNull("'paymentType' should be correct.", TestsHelper.getField(instance, "paymentType"));
        assertNull("'retirementType' should be correct.", TestsHelper.getField(instance, "retirementType"));
        assertNull("'postOffice' should be correct.", TestsHelper.getField(instance, "postOffice"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getName() {
        String value = "new_value";
        instance.setName(value);

        assertEquals("'getName' should be correct.",
            value, instance.getName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setName(String name)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setName() {
        String value = "new_value";
        instance.setName(value);

        assertEquals("'setName' should be correct.",
            value, TestsHelper.getField(instance, "name"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCode() {
        String value = "new_value";
        instance.setCode(value);

        assertEquals("'getCode' should be correct.",
            value, instance.getCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCode(String code)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCode() {
        String value = "new_value";
        instance.setCode(value);

        assertEquals("'setCode' should be correct.",
            value, TestsHelper.getField(instance, "code"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentType() {
        String value = "new_value";
        instance.setPaymentType(value);

        assertEquals("'getPaymentType' should be correct.",
            value, instance.getPaymentType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentType(String paymentType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentType() {
        String value = "new_value";
        instance.setPaymentType(value);

        assertEquals("'setPaymentType' should be correct.",
            value, TestsHelper.getField(instance, "paymentType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRetirementType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRetirementType() {
        RetirementType value = new RetirementType();
        instance.setRetirementType(value);

        assertSame("'getRetirementType' should be correct.",
            value, instance.getRetirementType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRetirementType(RetirementType retirementType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRetirementType() {
        RetirementType value = new RetirementType();
        instance.setRetirementType(value);

        assertSame("'setRetirementType' should be correct.",
            value, TestsHelper.getField(instance, "retirementType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPostOffice()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPostOffice() {
        Boolean value = true;
        instance.setPostOffice(value);

        assertEquals("'getPostOffice' should be correct.",
            value, instance.getPostOffice());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPostOffice(Boolean postOffice)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPostOffice() {
        Boolean value = true;
        instance.setPostOffice(value);

        assertEquals("'setPostOffice' should be correct.",
            value, TestsHelper.getField(instance, "postOffice"));
    }
}
