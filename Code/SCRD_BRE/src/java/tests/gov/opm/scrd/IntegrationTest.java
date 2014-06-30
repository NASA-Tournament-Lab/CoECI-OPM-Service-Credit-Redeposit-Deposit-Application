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

package gov.opm.scrd;

import gov.opm.scrd.entities.application.DeductionCalculationRequest;
import gov.opm.scrd.entities.application.DeductionCalculationResponse;
import gov.opm.scrd.entities.application.InterestCalculationRequest;
import gov.opm.scrd.entities.application.InterestCalculationResponse;
import gov.opm.scrd.services.DeductionCalculationRuleService;
import gov.opm.scrd.services.InterestCalculationRuleService;

import java.io.File;
import java.util.Collections;

import junit.framework.Assert;
import junit.framework.JUnit4TestAdapter;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * Provides integration tests(both deduction and calculation tests) for all the scenarios
 * in the provided PDF documents.
 * </p>
 * 
 * @author TCSASSEMBLER
 * @version 1.0
 * @since OPM Rules Engine - Scenarios Conversion 2 - Deduction Update Assembly
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class IntegrationTest {

    /**
     * <p>
     * Delta for comparing double.
     * </p>
     */
    private static final double delta = 0.04;

    /**
     * Represents the test scenarios file folder.
     */
    private static final String SCENARIOS_FILE_FOLDER =
            "test_files" + File.separator + "deduction" + File.separator;
    /**
     * Represents the deduction calculation rule service.
     *
     * It is injected by Spring via IoC.
     */
    @Autowired
    private DeductionCalculationRuleService deductionCalculationRuleService;

    /**
     * Represents the interest calculation rule service.
     *
     * It is injected by Spring via IoC.
     */
    @Autowired
    private InterestCalculationRuleService interestCalculationRuleService;

    /**
     * Represents the comparator used to sort ExtendedServicePeriod list.
     */
    private ExtendedServicePeriodComparator extendedServicePeriodComparator = new ExtendedServicePeriodComparator();

    /**
     * <p>
     * Creates a test suite for the tests in this test case.
     * </p>
     * 
     * @return a Test suite for this test case.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(IntegrationTest.class);
    }

    /**
     * Ret Scenario - CSRS Deposit on Earnings Spanning 10-1-82 .pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario1() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-01.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 7, 30, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 622.70,
              TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);

        Assert.assertEquals("The interest for the extended service period is not correct.", 168.02,
              TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
    }

    /**
     * Ret Scenario- CSRS mixed red and dep.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario2() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-02.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2009, 12, 9, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 796.90,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 228.89,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 851.24,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(2)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 10330.72,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(3)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 383.35,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(4)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 88.22,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(5)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 680.68,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(6)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 1629.62,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(7)), delta);
    }

    /**
     * Ret Scenario - CSRS dep,redep, peace corps.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario3() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-03.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 8, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 4476.07,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 212.43,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 396.03,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(2)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 13797.56,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(3)), delta);
    }

    /**
     * Ret Scenario - CSRS Intermittent Svc Dep and FERS Redeposit.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario4() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-04.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 6, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 200.36,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 1334.26,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 8681.10,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(2)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 4202.14,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(3)), delta);
    }

    /**
     * Ret Scenario- DC Govt hourly rates.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario5() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-05.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 7, 30, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 219.82,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 180.08,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 2010.73,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(2)), delta);
    }

    /**
     * Ret Scenario-Postal Flex and LT Deposit.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario6() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-06.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 7, 30, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 4837.87,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 2651.21,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
    }

    /**
     * Ret Scenario - CSRS Two Refunds on Two Days.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario7() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-07.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 7, 31, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 1229.22,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 44942.87,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
    }

    /**
     * Ret Scenario - Mixed CSRS & FERS new categories needed.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario8() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-08.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 9, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 40639.23,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 838.01,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 1116.06,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(2)), delta);
    }

    /**
     * Ret Scenario - FERS Dep & Redep.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario9() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-09.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 2, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 162.34,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 1123.91,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
    }

    /**
     * Ret Scenario- FERS dep,redep, and Peace Corps.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario10() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-10.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 8, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 170.22,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 154.38,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 916.77,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(2)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 1996.74,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(3)), delta);
    }

    /**
     * Ret Scenario- FERS Deposit & Leave without Pay.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario11() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-11.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 7, 10, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 159.65,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
    }

    /**
     * Ret Scenario- FERS FSPS LEO.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario12() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-12.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 7, 10, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 1772.15,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
    }

    /**
     * Ret Scenario- CSRS Peace Corps.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario13() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-13.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2008, 9, 1, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 49.43,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);

    }

    /**
     * Ret Scenario- CSRS and FERS VISTA Svc with Earnings.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario14() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-14.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 9, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 115.49,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 0.0,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
    }

    /**
     * Ret Scenario- Conner Case CSRS component thru 1988.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario15() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-15.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 6, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 334.70,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 626.37,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 840.76,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(2)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 889.09,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(3)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 889.86,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(4)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 666.95,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(5)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 1277.50,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(6)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 3330.43,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(7)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 3326.74,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(8)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 14145.0,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(9)), delta);
    }

    /**
     * Ret Scenario - Conner Case CSRS component after 1988.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario16() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-16.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 6, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        // NOTE that the Conner Case interest calculation steps in the PDF is NOT correct, as per
        // the PDF: "No interest is charged until Jan 1,1999, workaround - we have to do
        // a manual computation of interest from 01/01/1999 - present".
        // So basically the actual interest calculation result is presented at manual computation
        // on the last page of the PDF, i.e. the total balance with interest for all extended service
        // periods is $1,3788.99
        // The following verification uses correct interest result computed manually
        Assert.assertEquals("The interest for the extended service period is not correct.", 0.0,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 810.11,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 1815.23,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(2)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 759.85,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(3)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 943.76,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(4)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 1967.42,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(5)), delta);
    }

    /**
     * Ret Scenario - Conner Case FERS component.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario17() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-17.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 6, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 197.60,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 373.99,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 507.72,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(2)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 543.55,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(3)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 550.81,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(4)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 655.31,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(5)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 618.53,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(6)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 617.81,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(7)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 2626.91,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(8)), delta);
    }

    /**
     * Ret Scenario - CSRS Firefighter Deposit.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario18() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-18.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 8, 7, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 3277.70,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 6283.24,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
    }

    /**
     * Ret Scenario-Mixed DC Gov and GS Agency Hourly Rates after 3-1-86.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario19() throws Exception {
        // Calculation Deduction
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        deductionRequest.setServicePeriods(TestsHelper.readServicePeriodsFromFile(
                SCENARIOS_FILE_FOLDER + "scenario-19.txt"));
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

        // Calculate Interest
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new DateTime(2013, 7, 30, 0, 0).toDate());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

        // Check calculation result
        Assert.assertEquals("The interest for the extended service period is not correct.", 957.58,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(0)), delta);
        Assert.assertEquals("The interest for the extended service period is not correct.", 8696.61,
                TestsHelper.getTotalInterest(interestResponse.getExtendedServicePeriods().get(1)), delta);
    }
}
