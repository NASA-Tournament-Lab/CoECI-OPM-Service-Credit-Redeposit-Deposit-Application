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
 * Unit tests for {@link GLPaymentType} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class GLPaymentTypeUnitTests {
    /**
     * <p>
     * Represents the <code>GLPaymentType</code> instance used in tests.
     * </p>
     */
    private GLPaymentType instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(GLPaymentTypeUnitTests.class);
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
        instance = new GLPaymentType();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>GLPaymentType()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new GLPaymentType();

        assertNull("'paymentCode' should be correct.", TestsHelper.getField(instance, "paymentCode"));
        assertNull("'codeDescription' should be correct.", TestsHelper.getField(instance, "codeDescription"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getPaymentCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentCode() {
        String value = "new_value";
        instance.setPaymentCode(value);

        assertEquals("'getPaymentCode' should be correct.",
            value, instance.getPaymentCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentCode(String paymentCode)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentCode() {
        String value = "new_value";
        instance.setPaymentCode(value);

        assertEquals("'setPaymentCode' should be correct.",
            value, TestsHelper.getField(instance, "paymentCode"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCodeDescription()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCodeDescription() {
        String value = "new_value";
        instance.setCodeDescription(value);

        assertEquals("'getCodeDescription' should be correct.",
            value, instance.getCodeDescription());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCodeDescription(String codeDescription)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCodeDescription() {
        String value = "new_value";
        instance.setCodeDescription(value);

        assertEquals("'setCodeDescription' should be correct.",
            value, TestsHelper.getField(instance, "codeDescription"));
    }
}
