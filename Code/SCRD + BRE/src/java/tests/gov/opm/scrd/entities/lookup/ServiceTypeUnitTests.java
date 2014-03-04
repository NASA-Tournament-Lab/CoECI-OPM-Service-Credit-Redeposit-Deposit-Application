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

package gov.opm.scrd.entities.lookup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link ServiceType} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: fersDepositAllowedAfter88</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.1
 */
public class ServiceTypeUnitTests {
    /**
     * <p>
     * Represents the <code>ServiceType</code> instance used in tests.
     * </p>
     */
    private ServiceType instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ServiceTypeUnitTests.class);
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
        instance = new ServiceType();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>ServiceType()</code>.<br>
     * Instance should be correctly created.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void testCtor() {
        instance = new ServiceType();

        assertNull("'fersDepositAllowedAfter88' should be correct.",
            TestsHelper.getField(instance, "fersDepositAllowedAfter88"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getFersDepositAllowedAfter88()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getFersDepositAllowedAfter88() {
        Integer value = 1;
        instance.setFersDepositAllowedAfter88(value);

        assertEquals("'getFersDepositAllowedAfter88' should be correct.",
            value, instance.getFersDepositAllowedAfter88());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFersDepositAllowedAfter88(Integer fersDepositAllowedAfter88)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setFersDepositAllowedAfter88() {
        Integer value = 1;
        instance.setFersDepositAllowedAfter88(value);

        assertEquals("'setFersDepositAllowedAfter88' should be correct.",
            value, TestsHelper.getField(instance, "fersDepositAllowedAfter88"));
    }
}
