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

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link DeductionRate} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class DeductionRateUnitTests {
    /**
     * <p>
     * Represents the <code>DeductionRate</code> instance used in tests.
     * </p>
     */
    private DeductionRate instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(DeductionRateUnitTests.class);
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
        instance = new DeductionRate();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>DeductionRate()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new DeductionRate();

        assertNull("'serviceType' should be correct.", TestsHelper.getField(instance, "serviceType"));
        assertNull("'retirementType' should be correct.", TestsHelper.getField(instance, "retirementType"));
        assertNull("'startDate' should be correct.", TestsHelper.getField(instance, "startDate"));
        assertNull("'endDate' should be correct.", TestsHelper.getField(instance, "endDate"));
        assertNull("'daysInPeriod' should be correct.", TestsHelper.getField(instance, "daysInPeriod"));
        assertNull("'rate' should be correct.", TestsHelper.getField(instance, "rate"));
        assertNull("'serviceTypeDescription' should be correct.",
            TestsHelper.getField(instance, "serviceTypeDescription"));
        assertNull("'deductionConversionFactor' should be correct.",
            TestsHelper.getField(instance, "deductionConversionFactor"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getServiceType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getServiceType() {
        String value = "new_value";
        instance.setServiceType(value);

        assertEquals("'getServiceType' should be correct.",
            value, instance.getServiceType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setServiceType(String serviceType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setServiceType() {
        String value = "new_value";
        instance.setServiceType(value);

        assertEquals("'setServiceType' should be correct.",
            value, TestsHelper.getField(instance, "serviceType"));
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
     * Accuracy test for the method <code>getStartDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStartDate() {
        Date value = new Date();
        instance.setStartDate(value);

        assertSame("'getStartDate' should be correct.",
            value, instance.getStartDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStartDate(Date startDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStartDate() {
        Date value = new Date();
        instance.setStartDate(value);

        assertSame("'setStartDate' should be correct.",
            value, TestsHelper.getField(instance, "startDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getEndDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getEndDate() {
        Date value = new Date();
        instance.setEndDate(value);

        assertSame("'getEndDate' should be correct.",
            value, instance.getEndDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setEndDate(Date endDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setEndDate() {
        Date value = new Date();
        instance.setEndDate(value);

        assertSame("'setEndDate' should be correct.",
            value, TestsHelper.getField(instance, "endDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDaysInPeriod()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDaysInPeriod() {
        Integer value = 1;
        instance.setDaysInPeriod(value);

        assertEquals("'getDaysInPeriod' should be correct.",
            value, instance.getDaysInPeriod());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDaysInPeriod(Integer daysInPeriod)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDaysInPeriod() {
        Integer value = 1;
        instance.setDaysInPeriod(value);

        assertEquals("'setDaysInPeriod' should be correct.",
            value, TestsHelper.getField(instance, "daysInPeriod"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRate() {
        BigDecimal value = new BigDecimal(1);
        instance.setRate(value);

        assertSame("'getRate' should be correct.",
            value, instance.getRate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRate(BigDecimal rate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRate() {
        BigDecimal value = new BigDecimal(1);
        instance.setRate(value);

        assertSame("'setRate' should be correct.",
            value, TestsHelper.getField(instance, "rate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getServiceTypeDescription()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getServiceTypeDescription() {
        String value = "new_value";
        instance.setServiceTypeDescription(value);

        assertEquals("'getServiceTypeDescription' should be correct.",
            value, instance.getServiceTypeDescription());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setServiceTypeDescription(String serviceTypeDescription)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setServiceTypeDescription() {
        String value = "new_value";
        instance.setServiceTypeDescription(value);

        assertEquals("'setServiceTypeDescription' should be correct.",
            value, TestsHelper.getField(instance, "serviceTypeDescription"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDeductionConversionFactor()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDeductionConversionFactor() {
        BigDecimal value = new BigDecimal(1);
        instance.setDeductionConversionFactor(value);

        assertSame("'getDeductionConversionFactor' should be correct.",
            value, instance.getDeductionConversionFactor());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDeductionConversionFactor(BigDecimal deductionConversionFactor)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDeductionConversionFactor() {
        BigDecimal value = new BigDecimal(1);
        instance.setDeductionConversionFactor(value);

        assertSame("'setDeductionConversionFactor' should be correct.",
            value, TestsHelper.getField(instance, "deductionConversionFactor"));
    }
}
