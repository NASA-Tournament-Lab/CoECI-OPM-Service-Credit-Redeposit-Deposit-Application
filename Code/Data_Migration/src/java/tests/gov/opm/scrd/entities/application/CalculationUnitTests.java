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
import gov.opm.scrd.entities.lookup.AgencyCode;
import gov.opm.scrd.entities.lookup.AppointmentType;
import gov.opm.scrd.entities.lookup.PayType;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.ServiceType;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link Calculation} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: agencyCode, hoursInYear, annualizedAmount, dateEntered, enteredBy</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.1
 */
public class CalculationUnitTests {
    /**
     * <p>
     * Represents the <code>Calculation</code> instance used in tests.
     * </p>
     */
    private Calculation instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(CalculationUnitTests.class);
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
        instance = new Calculation();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Calculation()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Calculation();

        assertNull("'beginDate' should be correct.", TestsHelper.getField(instance, "beginDate"));
        assertNull("'endDate' should be correct.", TestsHelper.getField(instance, "endDate"));
        assertNull("'retirementType' should be correct.", TestsHelper.getField(instance, "retirementType"));
        assertNull("'periodType' should be correct.", TestsHelper.getField(instance, "periodType"));
        assertNull("'appointmentType' should be correct.", TestsHelper.getField(instance, "appointmentType"));
        assertNull("'serviceType' should be correct.", TestsHelper.getField(instance, "serviceType"));
        assertNull("'amount' should be correct.", TestsHelper.getField(instance, "amount"));
        assertNull("'payType' should be correct.", TestsHelper.getField(instance, "payType"));
        assertNull("'agencyCode' should be correct.", TestsHelper.getField(instance, "agencyCode"));
        assertNull("'hoursInYear' should be correct.", TestsHelper.getField(instance, "hoursInYear"));
        assertNull("'annualizedAmount' should be correct.", TestsHelper.getField(instance, "annualizedAmount"));
        assertNull("'dateEntered' should be correct.", TestsHelper.getField(instance, "dateEntered"));
        assertNull("'enteredBy' should be correct.", TestsHelper.getField(instance, "enteredBy"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getBeginDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBeginDate() {
        Date value = new Date();
        instance.setBeginDate(value);

        assertSame("'getBeginDate' should be correct.",
            value, instance.getBeginDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBeginDate(Date beginDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBeginDate() {
        Date value = new Date();
        instance.setBeginDate(value);

        assertSame("'setBeginDate' should be correct.",
            value, TestsHelper.getField(instance, "beginDate"));
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
     * Accuracy test for the method <code>getPeriodType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPeriodType() {
        PeriodType value = new PeriodType();
        instance.setPeriodType(value);

        assertSame("'getPeriodType' should be correct.",
            value, instance.getPeriodType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPeriodType(PeriodType periodType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPeriodType() {
        PeriodType value = new PeriodType();
        instance.setPeriodType(value);

        assertSame("'setPeriodType' should be correct.",
            value, TestsHelper.getField(instance, "periodType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAppointmentType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAppointmentType() {
        AppointmentType value = new AppointmentType();
        instance.setAppointmentType(value);

        assertSame("'getAppointmentType' should be correct.",
            value, instance.getAppointmentType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAppointmentType(AppointmentType appointmentType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAppointmentType() {
        AppointmentType value = new AppointmentType();
        instance.setAppointmentType(value);

        assertSame("'setAppointmentType' should be correct.",
            value, TestsHelper.getField(instance, "appointmentType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getServiceType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getServiceType() {
        ServiceType value = new ServiceType();
        instance.setServiceType(value);

        assertSame("'getServiceType' should be correct.",
            value, instance.getServiceType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setServiceType(ServiceType serviceType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setServiceType() {
        ServiceType value = new ServiceType();
        instance.setServiceType(value);

        assertSame("'setServiceType' should be correct.",
            value, TestsHelper.getField(instance, "serviceType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setAmount(value);

        assertSame("'getAmount' should be correct.",
            value, instance.getAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAmount(BigDecimal amount)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setAmount(value);

        assertSame("'setAmount' should be correct.",
            value, TestsHelper.getField(instance, "amount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPayType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPayType() {
        PayType value = new PayType();
        instance.setPayType(value);

        assertSame("'getPayType' should be correct.",
            value, instance.getPayType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPayType(PayType payType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPayType() {
        PayType value = new PayType();
        instance.setPayType(value);

        assertSame("'setPayType' should be correct.",
            value, TestsHelper.getField(instance, "payType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAgencyCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getAgencyCode() {
        AgencyCode value = new AgencyCode();
        instance.setAgencyCode(value);

        assertSame("'getAgencyCode' should be correct.",
            value, instance.getAgencyCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAgencyCode(AgencyCode agencyCode)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setAgencyCode() {
        AgencyCode value = new AgencyCode();
        instance.setAgencyCode(value);

        assertSame("'setAgencyCode' should be correct.",
            value, TestsHelper.getField(instance, "agencyCode"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getHoursInYear()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getHoursInYear() {
        Integer value = 1;
        instance.setHoursInYear(value);

        assertEquals("'getHoursInYear' should be correct.",
            value, instance.getHoursInYear());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setHoursInYear(Integer hoursInYear)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setHoursInYear() {
        Integer value = 1;
        instance.setHoursInYear(value);

        assertEquals("'setHoursInYear' should be correct.",
            value, TestsHelper.getField(instance, "hoursInYear"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAnnualizedAmount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getAnnualizedAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setAnnualizedAmount(value);

        assertSame("'getAnnualizedAmount' should be correct.",
            value, instance.getAnnualizedAmount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAnnualizedAmount(BigDecimal annualizedAmount)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setAnnualizedAmount() {
        BigDecimal value = new BigDecimal(1);
        instance.setAnnualizedAmount(value);

        assertSame("'setAnnualizedAmount' should be correct.",
            value, TestsHelper.getField(instance, "annualizedAmount"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDateEntered()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getDateEntered() {
        Date value = new Date();
        instance.setDateEntered(value);

        assertSame("'getDateEntered' should be correct.",
            value, instance.getDateEntered());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDateEntered(Date dateEntered)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setDateEntered() {
        Date value = new Date();
        instance.setDateEntered(value);

        assertSame("'setDateEntered' should be correct.",
            value, TestsHelper.getField(instance, "dateEntered"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getEnteredBy()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getEnteredBy() {
        Long value = 1L;
        instance.setEnteredBy(value);

        assertEquals("'getEnteredBy' should be correct.",
            value, instance.getEnteredBy());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setEnteredBy(Long enteredBy)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setEnteredBy() {
        Long value = 1L;
        instance.setEnteredBy(value);

        assertEquals("'setEnteredBy' should be correct.",
            value, TestsHelper.getField(instance, "enteredBy"));
    }
}
