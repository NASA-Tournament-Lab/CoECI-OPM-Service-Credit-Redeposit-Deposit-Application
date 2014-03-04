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

import java.math.BigDecimal;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link InterestRate} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class InterestRateUnitTests {
    /**
     * <p>
     * Represents the <code>InterestRate</code> instance used in tests.
     * </p>
     */
    private InterestRate instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(InterestRateUnitTests.class);
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
        instance = new InterestRate();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>InterestRate()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new InterestRate();

        assertNull("'interestYear' should be correct.", TestsHelper.getField(instance, "interestYear"));
        assertNull("'interestRate' should be correct.", TestsHelper.getField(instance, "interestRate"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getInterestYear()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getInterestYear() {
        Integer value = 1;
        instance.setInterestYear(value);

        assertEquals("'getInterestYear' should be correct.",
            value, instance.getInterestYear());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setInterestYear(Integer interestYear)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setInterestYear() {
        Integer value = 1;
        instance.setInterestYear(value);

        assertEquals("'setInterestYear' should be correct.",
            value, TestsHelper.getField(instance, "interestYear"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getInterestRate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getInterestRate() {
        BigDecimal value = new BigDecimal(1);
        instance.setInterestRate(value);

        assertSame("'getInterestRate' should be correct.",
            value, instance.getInterestRate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setInterestRate(BigDecimal interestRate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setInterestRate() {
        BigDecimal value = new BigDecimal(1);
        instance.setInterestRate(value);

        assertSame("'setInterestRate' should be correct.",
            value, TestsHelper.getField(instance, "interestRate"));
    }
}
