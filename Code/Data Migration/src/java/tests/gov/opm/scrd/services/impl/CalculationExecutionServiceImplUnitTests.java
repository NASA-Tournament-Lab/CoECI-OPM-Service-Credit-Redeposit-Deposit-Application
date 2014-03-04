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

package gov.opm.scrd.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.Calculation;
import gov.opm.scrd.entities.application.CalculationResult;
import gov.opm.scrd.entities.application.CalculationResultItem;
import gov.opm.scrd.entities.application.Dedeposit;
import gov.opm.scrd.entities.application.Redeposit;
import gov.opm.scrd.entities.application.SummaryData;
import gov.opm.scrd.entities.common.NamedEntity;
import gov.opm.scrd.entities.lookup.AppointmentType;
import gov.opm.scrd.entities.lookup.CalculationStatus;
import gov.opm.scrd.entities.lookup.PayType;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.ServiceType;
import gov.opm.scrd.services.DeductionCalculationRuleService;
import gov.opm.scrd.services.InterestCalculationRuleService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Unit tests for {@link CalculationExecutionServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class CalculationExecutionServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the context used in tests.
     * </p>
     */
    private static ApplicationContext context;

    /**
     * <p>
     * Represents the <code>CalculationExecutionServiceImpl</code> instance used in tests.
     * </p>
     */
    private CalculationExecutionServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the deduction calculation rule service used in tests.
     * </p>
     */
    private DeductionCalculationRuleService deductionCalculationRuleService;

    /**
     * <p>
     * Represents the interest calculation rule service used in tests.
     * </p>
     */
    private InterestCalculationRuleService interestCalculationRuleService;

    /**
     * <p>
     * Represents the redeposit period type used in tests.
     * </p>
     */
    private PeriodType redepositPeriodType;

    /**
     * <p>
     * Represents the dedeposit period type used in tests.
     * </p>
     */
    private PeriodType dedepositPeriodType;

    /**
     * <p>
     * Represents the csrs retirement type used in tests.
     * </p>
     */
    private RetirementType csrsRetirementType;

    /**
     * <p>
     * Represents the deposit type change date used in tests.
     * </p>
     */
    private Date depositTypeChangeDate;

    /**
     * <p>
     * Represents the success calculation status used in tests.
     * </p>
     */
    private CalculationStatus successCalculationStatus;

    /**
     * <p>
     * Represents the calculations used in tests.
     * </p>
     */
    private List<Calculation> calculations;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(CalculationExecutionServiceImplUnitTests.class);
    }

    /**
     * Initialization.
     */
    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        entityManager = getEntityManager();

        logger = Logger.getLogger(getClass());

        deductionCalculationRuleService = (DeductionCalculationRuleService) context
            .getBean("deductionCalculationRuleService");
        interestCalculationRuleService = (InterestCalculationRuleService) context
            .getBean("interestCalculationRuleService");

        redepositPeriodType = new PeriodType();
        redepositPeriodType.setName("DEPOSIT");
        dedepositPeriodType = new PeriodType();
        dedepositPeriodType.setName("DEDEPOSIT");
        csrsRetirementType = new RetirementType();
        depositTypeChangeDate = new Date();
        successCalculationStatus = new CalculationStatus();

        instance = new CalculationExecutionServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "deductionCalculationRuleService", deductionCalculationRuleService);
        TestsHelper.setField(instance, "interestCalculationRuleService", interestCalculationRuleService);
        TestsHelper.setField(instance, "redepositPeriodType", redepositPeriodType);
        TestsHelper.setField(instance, "dedepositPeriodType", dedepositPeriodType);
        TestsHelper.setField(instance, "csrsRetirementType", csrsRetirementType);
        TestsHelper.setField(instance, "depositTypeChangeDate", depositTypeChangeDate);
        TestsHelper.setField(instance, "successCalculationStatus", successCalculationStatus);

        calculations = new ArrayList<Calculation>();
        calculations.add(getCalculation("05/07/1985", "02/28/1986", "CSRS", "DEPOSIT", "TEMPORARY", "FIREFIGHTER",
            "ANNUAL SALARY", 12862.00D));
        calculations.add(getCalculation("01/01/1987", "01/02/1988", "CSRS", "DEPOSIT", "TEMPORARY", "FIREFIGHTER",
            "ANNUAL SALARY", 13248.00D));
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>CalculationExecutionServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new CalculationExecutionServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
        assertNull("'deductionCalculationRuleService' should be correct.",
            TestsHelper.getField(instance, "deductionCalculationRuleService"));
        assertNull("'interestCalculationRuleService' should be correct.",
            TestsHelper.getField(instance, "interestCalculationRuleService"));
        assertNull("'redepositPeriodType' should be correct.",
            TestsHelper.getField(instance, "redepositPeriodType"));
        assertNull("'dedepositPeriodType' should be correct.",
            TestsHelper.getField(instance, "dedepositPeriodType"));
        assertNull("'csrsRetirementType' should be correct.",
            TestsHelper.getField(instance, "csrsRetirementType"));
        assertNull("'depositTypeChangeDate' should be correct.",
            TestsHelper.getField(instance, "depositTypeChangeDate"));
        assertNull("'successCalculationStatus' should be correct.",
            TestsHelper.getField(instance, "successCalculationStatus"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>runCalculation(List&lt;Calculation&gt; calculations)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_runCalculation_1() throws Exception {
        calculations.clear();
        CalculationResult res = instance.runCalculation(calculations);

        List<CalculationResultItem> items = res.getItems();
        List<Redeposit> redeposits = res.getRedeposits();
        List<Dedeposit> dedeposits = res.getDedeposits();
        assertEquals("'runCalculation' should be correct.", 0, items.size());
        assertEquals("'runCalculation' should be correct.", 0, redeposits.size());
        assertEquals("'runCalculation' should be correct.", 0, dedeposits.size());
        assertSame("'runCalculation' should be correct.",
            successCalculationStatus, res.getCalculationStatus());

        SummaryData summaryData = res.getSummary();
        assertEquals("'runCalculation' should be correct.",
            0D, summaryData.getTotalPaymentsApplied().doubleValue(), 0.0001);
        assertEquals("'runCalculation' should be correct.",
            0D, summaryData.getTotalInitialInterest().doubleValue(), 0.0001);
        assertEquals("'runCalculation' should be correct.",
            0D, summaryData.getTotalPaymentsApplied().doubleValue(), 0.0001);
        assertEquals("'runCalculation' should be correct.",
            0D, summaryData.getTotalBalance().doubleValue(), 0.0001);
    }

    /**
     * <p>
     * Accuracy test for the method <code>runCalculation(List&lt;Calculation&gt; calculations)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_runCalculation_2() throws Exception {
        CalculationResult res = instance.runCalculation(calculations);

        List<CalculationResultItem> items = res.getItems();
        List<Redeposit> redeposits = res.getRedeposits();
        List<Dedeposit> dedeposits = res.getDedeposits();
        assertEquals("'runCalculation' should be correct.", 2, items.size());
        assertEquals("'runCalculation' should be correct.", 2, redeposits.size());
        assertEquals("'runCalculation' should be correct.", 0, dedeposits.size());
        assertSame("'runCalculation' should be correct.",
            successCalculationStatus, res.getCalculationStatus());

        SummaryData summaryData = res.getSummary();
        assertEquals("'runCalculation' should be correct.",
            0D, summaryData.getTotalPaymentsApplied().doubleValue(), 0.01);
        assertEquals("'runCalculation' should be correct.",
            8377.27D, summaryData.getTotalInitialInterest().doubleValue(), 0.01);
        assertEquals("'runCalculation' should be correct.",
            0D, summaryData.getTotalPaymentsApplied().doubleValue(), 0.01);
        assertEquals("'runCalculation' should be correct.",
            10164.19D, summaryData.getTotalBalance().doubleValue(), 0.01);
    }

    /**
     * <p>
     * Accuracy test for the method <code>runCalculation(List&lt;Calculation&gt; calculations)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_runCalculation_3() throws Exception {
        redepositPeriodType.setName("REDEPOSIT");
        dedepositPeriodType.setName("DEPOSIT");

        CalculationResult res = instance.runCalculation(calculations);

        List<CalculationResultItem> items = res.getItems();
        List<Redeposit> redeposits = res.getRedeposits();
        List<Dedeposit> dedeposits = res.getDedeposits();
        assertEquals("'runCalculation' should be correct.", 2, items.size());
        assertEquals("'runCalculation' should be correct.", 0, redeposits.size());
        assertEquals("'runCalculation' should be correct.", 2, dedeposits.size());
        assertSame("'runCalculation' should be correct.",
            successCalculationStatus, res.getCalculationStatus());

        SummaryData summaryData = res.getSummary();
        assertEquals("'runCalculation' should be correct.",
            0D, summaryData.getTotalPaymentsApplied().doubleValue(), 0.01);
        assertEquals("'runCalculation' should be correct.",
            -8377.27D, summaryData.getTotalInitialInterest().doubleValue(), 0.01);
        assertEquals("'runCalculation' should be correct.",
            0D, summaryData.getTotalPaymentsApplied().doubleValue(), 0.01);
        assertEquals("'runCalculation' should be correct.",
            -10164.19D, summaryData.getTotalBalance().doubleValue(), 0.01);
    }

    /**
     * <p>
     * Failure test for the method <code>runCalculation(List&lt;Calculation&gt; calculations)</code> with calculations
     * is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_runCalculation_calculationsNull() throws Exception {
        instance.runCalculation(null);
    }

    /**
     * <p>
     * Failure test for the method <code>runCalculation(List&lt;Calculation&gt; calculations)</code> with calculations
     * contains null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_runCalculation_calculationsContainsNull() throws Exception {
        calculations.add(null);
        instance.runCalculation(calculations);
    }

    /**
     * Creates an instance of Calculation.
     *
     * @param values
     *            the values
     *            the pay type
     *
     * @return the Calculation instance
     *
     * @throws Exception
     *             to JUnit.
     */
    private static Calculation getCalculation(Object... values) throws Exception {
        Calculation calculation = new Calculation();

        int index = 0;
        calculation.setBeginDate(TestsHelper.DATE_FORMAT.parse((String) values[index++]));
        calculation.setEndDate(TestsHelper.DATE_FORMAT.parse((String) values[index++]));

        calculation.setRetirementType(setName(new RetirementType(), (String) values[index++]));
        calculation.setPeriodType(setName(new PeriodType(), (String) values[index++]));
        calculation.setAppointmentType(setName(new AppointmentType(), (String) values[index++]));
        calculation.setServiceType(setName(new ServiceType(), (String) values[index++]));
        calculation.setPayType(setName(new PayType(), (String) values[index++]));
        calculation.setAmount(new BigDecimal((Double) values[index++]));

        return calculation;
    }

    /**
     * Sets name of the entity.
     *
     * @param <T>
     *            the entity type
     * @param entity
     *            the entity
     * @param name
     *            the name
     *
     * @return the entity.
     */
    private static <T extends NamedEntity> T setName(T entity, String name) {
        entity.setName(name);

        return entity;
    }
}
