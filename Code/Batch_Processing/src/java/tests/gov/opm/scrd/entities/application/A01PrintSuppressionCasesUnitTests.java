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
 * Unit tests for {@link A01PrintSuppressionCases} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class A01PrintSuppressionCasesUnitTests {
    /**
     * <p>
     * Represents the <code>A01PrintSuppressionCases</code> instance used in tests.
     * </p>
     */
    private A01PrintSuppressionCases instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(A01PrintSuppressionCasesUnitTests.class);
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
        instance = new A01PrintSuppressionCases();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>A01PrintSuppressionCases()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new A01PrintSuppressionCases();

        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'reasonForPrintSuppression' should be correct.",
            TestsHelper.getField(instance, "reasonForPrintSuppression"));
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
     * Accuracy test for the method <code>getReasonForPrintSuppression()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getReasonForPrintSuppression() {
        Integer value = 1;
        instance.setReasonForPrintSuppression(value);

        assertEquals("'getReasonForPrintSuppression' should be correct.",
            value, instance.getReasonForPrintSuppression());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setReasonForPrintSuppression(Integer reasonForPrintSuppression)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setReasonForPrintSuppression() {
        Integer value = 1;
        instance.setReasonForPrintSuppression(value);

        assertEquals("'setReasonForPrintSuppression' should be correct.",
            value, TestsHelper.getField(instance, "reasonForPrintSuppression"));
    }
}
