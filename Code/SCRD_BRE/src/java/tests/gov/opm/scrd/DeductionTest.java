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
import gov.opm.scrd.entities.application.ExtendedServicePeriod;
import gov.opm.scrd.entities.application.ServicePeriod;
import gov.opm.scrd.services.DeductionCalculationRuleService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.RuleServiceException;
import gov.opm.scrd.services.impl.DeductionCalculationRuleServiceImpl;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * Provides deduction calculation tests for all the scenarios in the provided PDF documents.
 * </p>
 * 
 * 
 * @author TCSASSEMBLER
 * @version 1.0
 * @since OPM - Rules Engine - Scenarios Conversion 2 - Deduction Update Assembly
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
public class DeductionTest {
    /**
     * Represents the test scenarios file folder.
     */
    private static final String SCENARIOS_FILE_FOLDER =
            "test_files" + File.separator + "deduction" + File.separator;

    /**
     * Represents the deduction calculation rule service for CSRS and FERS type.
     * 
     * It is injected by Spring via IoC.
     */
    @Autowired
    private DeductionCalculationRuleService deductionCalculationRuleService;

    /**
     * Represents the Spring application context.
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * <p>
     * Creates a test suite for the tests in this test case.
     * </p>
     * 
     * @return a Test suite for this test case.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(DeductionTest.class);
    }

    /**
     * Failure test for DeductionCalculationRuleServiceImpl when log file name is not set.
     */
    @Test(expected = OPMConfigurationException.class)
    public void testConfigurationErrorLogFileNameNotSet() {
        DeductionCalculationRuleServiceImpl service = new DeductionCalculationRuleServiceImpl();
        service.setKnowledgeLogFileName(null);
        service.checkConfiguration();
    }

    /**
     * Failure test for DeductionCalculationRuleServiceImpl when log file name is set as empty.
     */
    @Test(expected = OPMConfigurationException.class)
    public void testConfigurationErrorLogFileNameEmpty() {
        DeductionCalculationRuleServiceImpl service = new DeductionCalculationRuleServiceImpl();
        service.setKnowledgeLogFileName("  ");
        service.checkConfiguration();
    }

    /**
     * Failure test for DeductionCalculationRuleService.execute when request is null.
     *
     * @throws Exception to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExecuteNullRequest() throws Exception {
        deductionCalculationRuleService.execute(null);
    }

    /**
     * Failure test for DeductionCalculationRuleService.execute when request is invalid.
     *
     * @throws Exception to JUnit.
     */
    @Test(expected = RuleServiceException.class)
    public void testExecuteInvalidRequest() throws Exception {
        deductionCalculationRuleService.execute(new DeductionCalculationRequest());
    }


    /**
     * Ret Scenario - CSRS Deposit on Earnings Spanning 10-1-82 .pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario1() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-01.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods, the original service period shouldn't be changed
        ServicePeriod period = request.getServicePeriods().get(0);
        Assert.assertNull("The earning is not correct", period.getEarnings());
        Assert.assertNull("The deduction is not correct", period.getDeduction());

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 2: (in Page 5 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "10/01/1982,02/19/1983,CSRS,DEPOSIT,33.55,479.23,null,false"),
                // extended service period 1 (in Page 4 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/30/1978,09/30/1982,CSRS,DEPOSIT,376.73,5381.85,null,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario- CSRS mixed red and dep.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario2() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-02.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 4819.03, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 313.24, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 4928.92, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 109.89, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 5433.41, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 504.49, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(3);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 7893.20, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 2459.79, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(4);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 9860.20, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 137.69, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(5);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 9934.22, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 74.02, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(6);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 10505.34, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 571.12, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(7);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 12905.17, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 174.10, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(8);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 42112.33, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 2157.25, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(9);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 56830.15, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1030.25, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 8: (in Page 13 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "11/30/1999,07/14/2001,CSRS,DEPOSIT,3361.60,46324.80,null,false"),
                // extended service period 7: (in Page 12 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "02/25/1990,04/30/1994,CSRS,REDEPOSIT,571.12,571.12,07/27/1994,false"),
                // extended service period 6: (in Page 11 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "09/27/1987,04/29/1988,CSRS,REDEPOSIT,74.02,74.02,07/27/1994,false"),
                // extended service period 5: (in Page 10 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "07/27/1987,09/26/1987,CSRS,DEPOSIT,137.69,1967.00,null,false"),
                // extended service period 4: (in Page 9 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/13/1981,09/09/1983,CSRS,REDEPOSIT,2459.79,2459.79,11/02/1983,false"),
                // extended service period 3: (in Page 8 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "10/03/1975,06/01/1976,CSRS,REDEPOSIT,504.49,504.49,07/02/1976,false"),
                // extended service period 2: (in Page 6 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/17/1971,09/02/1971,CSRS,REDEPOSIT,109.89,109.89,11/09/1971,false"),
                // extended service period 1 (in Page 4 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "07/10/1966,10/01/1967,CSRS,DEPOSIT,313.24,4819.03,null,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario - CSRS dep,redep, peace corps.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario3() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-03.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 1361.22, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 95.29, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 8748.64, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 517.12, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 15180.75, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 450.25, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(3);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 16705.74, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 106.75, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(4);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 25326.72, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 603.47, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(5);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 34525.33, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 643.90, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(6);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 35422.40, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 62.79, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(7);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 36409.90, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 69.12, TestsHelper.toDouble(period.getDeduction()));

        // this period is split to two new periods
        period = request.getServicePeriods().get(8);
        Assert.assertNull("The earnings is not correct", period.getEarnings());
        Assert.assertNull("The deduction is not correct", period.getDeduction());

        period = request.getServicePeriods().get(9);
        totalEarnings = BigDecimal.valueOf(42260.73);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 46710.73, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 4450.00, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        List<ExtendedServicePeriod> esps = Arrays.asList(
                // extended service period 4: (in Page 9 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "08/08/1985,12/31/1987,CSRS,REDEPOSIT,4450.00,4450.00,02/05/1988,false"),
                // extended service period 3: (in Page 7 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "10/01/1982,11/13/1984,CSRS,DEPOSIT,311.56,4450.83,10/01/1995,false"),
                // extended service period 2: (in Page 5 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "06/04/1981,09/30/1982,CSRS,DEPOSIT,167.12,2387.50,10/01/1995,false"),
                // extended service period 1 (in Page 4 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "08/01/1976,11/06/1980,CSRS,DEPOSIT,2479.57,35422.40,null,false"));
        esps.get(1).setServiceType(TestsHelper.createServiceType("PEACE CORPS/VISTA"));
        esps.get(2).setServiceType(TestsHelper.createServiceType("PEACE CORPS/VISTA"));
        
        TestsHelper.assertEqualsExtendedServicePeriodList(esps, response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario - CSRS Intermittent Svc Dep and FERS Redeposit.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario4() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-04.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 1420.64, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 99.44, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 1420.64, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 3371.90, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 136.59, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(3);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 9830.64, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 452.11, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(4);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 13531.07, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 259.03, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(5);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 15467.68, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 135.56, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(6);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 24234.08, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 613.65, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(7);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 31437.94, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 504.27, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(8);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 39518.80, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 565.66, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(9);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 41398.80, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1880.00, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 4: (in Page 8 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "10/10/1987,10/15/1990,FERS,REDEPOSIT,1880.00,1880.00,12/01/1990,false"),
                // extended service period 3: (in Page 7 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "10/01/1982,11/08/1985,CSRS,DEPOSIT,1819.14,25987.73,null,false"),
                // extended service period 2: (in Page 5 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "06/28/1980,09/30/1982,CSRS,DEPOSIT,847.73,12110.43,null,false"),
                // extended service period 1 (in Page 4 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "11/10/1975,09/06/1976,CSRS,DEPOSIT,99.44,1420.64,null,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario- DC Govt hourly rates.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario5() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-05.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 1326.58, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 92.86, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 2745.84, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 99.35, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 10866.40, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 568.44, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 3: (in Page 7 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "09/14/1986,02/27/1987,CSRS,DEPOSIT,568.44,8120.56,null,false"),
                // extended service period 2: (in Page 5 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "07/16/1978,09/01/1978,CSRS,DEPOSIT,99.35,1419.26,null,false"),
                // extended service period 1 (in Page 3 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/26/1972,08/17/1972,CSRS,DEPOSIT,92.86,1326.58,null,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario-Postal Flex and LT Deposit.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario6() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-06.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 19500.00, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1365.00, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 36908.45, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1218.59, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 2: (in Page 3 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "10/11/1990,07/02/1991,CSRS,DEPOSIT,1218.59,17408.45,null,false"),
                // extended service period 1 (in Page 2 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/07/1986,07/24/1987,CSRS,DEPOSIT,1365.00,19500.00,null,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario - CSRS Two Refunds on Two Days.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario7() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-07.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 17689.87, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 17689.87, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 18189.87, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 500.00, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 2: (in Page 3 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "01/13/1974,07/16/1988,CSRS,REDEPOSIT,500.00,500.00,02/15/1990,false"),
                // extended service period 1 (in Page 2 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "01/13/1974,07/16/1988,CSRS,REDEPOSIT,17689.87,17689.87,11/07/1989,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario - Mixed CSRS & FERS new categories needed.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario8() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-08.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 8400.00, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 8400.00, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 16017.60, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 99.03, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 29455.52, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 174.69, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(3);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 30065.52, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 610.00, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 3: (in Page 5 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "01/01/1989,06/10/1992,FERS,REDEPOSIT,610.00,610.00,07/27/1992,false"),
                // extended service period 2: (in Page 3 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "06/06/1987,12/31/1988,FERS,DEPOSIT,273.72,21055.52,null,false"),
                // extended service period 1 (in Page 2 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "07/10/1978,08/31/1983,CSRS,REDEPOSIT,8400.00,8400.00,12/01/1983,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario - FERS Dep & Redep.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario9() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-09.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 2375.71, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 30.88, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 3170.28, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 10.33, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 3735.25, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 564.97, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 2 (in Page 10 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "03/15/1986,09/15/1990,FERS,REDEPOSIT,564.97,564.97,11/15/1991,false"),
                // extended service period 1 (in Page 5 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "01/19/1986,03/14/1986,FERS,DEPOSIT,41.21,3170.28,null,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario- FERS dep,redep, and Peace Corps.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario10() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-10.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 2400.60, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 31.21, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 6448.93, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 121.45, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 16909.48, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 135.99, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(3);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 24237.12, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 95.26, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(4);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 25675.12, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1438.00, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        List<ExtendedServicePeriod> esps = Arrays.asList(
                // extended service period 4 (in Page 7 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "10/10/1989,09/16/1994,FERS,REDEPOSIT,1438.00,1438.00,02/03/1995,false"),
                // extended service period 3 (in Page 6 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "07/29/1985,07/28/1986,FERS,DEPOSIT,231.25,17788.20,null,false"),
                // extended service period 2 (in Page 4 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "10/27/1982,09/30/1984,FERS,DEPOSIT,121.45,4048.33,10/01/1995,false"),
                // extended service period 1 (in Page 2 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/27/1980,08/22/1980,FERS,DEPOSIT,31.21,2400.60,null,false"));
        esps.get(2).setServiceType(TestsHelper.createServiceType("PEACE CORPS/VISTA"));
        TestsHelper.assertEqualsExtendedServicePeriodList(esps, response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario- FERS Deposit & Leave without Pay.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario11() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-11.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 2386.94, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 31.03, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 2386.94, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 1 (in Page 2 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/11/1981,01/02/1983,FERS,DEPOSIT,31.03,2386.94,null,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario- FERS FSPS LEO.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario12() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-12.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 20551.07, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 267.16, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 28851.11, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 107.90, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 45154.97, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 211.95, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(3);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 47292.27, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 27.78, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(4);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 74483.50, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 353.49, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(5);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 107622.15, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 430.80, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(6);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 136328.56, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 373.18, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(7);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 141153.99, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 62.73, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(8);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 178444.59, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 484.78, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(9);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 208847.79, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 395.24, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(10);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 215043.17, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 80.54, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(11);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 277438.07, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 811.13, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(12);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 294546.88, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 222.41, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(13);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 370787.92, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 991.13, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(14);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 389201.39, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 239.38, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(15);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 471766.95, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1073.35, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(16);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 491639.58, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 258.34, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(17);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 578742.31, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1132.34, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(18);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 689422.98, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1438.85, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 1 (in Page 3 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "07/13/2003,10/22/2011,FERS,DEPOSIT,8962.48,689422.96,null,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario- CSRS Peace Corps.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario13() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-13.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 772.50, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 54.07, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        List<ExtendedServicePeriod> esps = Arrays.asList(
                // extended service period 1 (in Page 3 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "09/30/1974,08/08/1975,CSRS,DEPOSIT,54.07,772.50,10/01/1995,false"));
        esps.get(0).setServiceType(TestsHelper.createServiceType("PEACE CORPS/VISTA"));
        TestsHelper.assertEqualsExtendedServicePeriodList(esps, response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario- CSRS and FERS VISTA Svc with Earnings.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario14() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-14.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 1298.00, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 90.86, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 3798.00, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 75.00, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        List<ExtendedServicePeriod> esps = Arrays.asList(
                // extended service period 2 (in Page 5 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "07/07/2007,08/07/2009,FERS,DEPOSIT,75.00,2500.00,10/10/2012,false"),
                // extended service period 1 (in Page 3 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "02/01/1972,06/02/1974,CSRS,DEPOSIT,90.86,1298.00,10/01/1995,false"));
        esps.get(0).setServiceType(TestsHelper.createServiceType("PEACE CORPS/VISTA"));
        esps.get(1).setServiceType(TestsHelper.createServiceType("PEACE CORPS/VISTA"));
        TestsHelper.assertEqualsExtendedServicePeriodList(esps, response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario- Conner Case CSRS component thru 1988.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario15() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-15.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 1259.64, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 88.17, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 2514.88, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 87.87, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 6149.46, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 254.42, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(3);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 7450.84, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 91.10, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(4);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 12276.77, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 337.82, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(5);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 12667.57, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 27.36, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(6);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 14385.25, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 120.24, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(7);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 19725.67, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 373.83, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(8);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 22076.66, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 164.57, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(9);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 28175.59, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 426.92, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(10);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 30139.40, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 137.47, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(11);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 36459.29, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 442.39, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(12);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 36530.71, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 5.00, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(13);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 40095.78, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 249.55, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(14);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 49825.44, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 681.08, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(15);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 59895.70, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 704.92, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(16);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 68689.59, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 615.57, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(17);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 70075.91, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 97.04, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(18);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 70075.91, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(19);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 86513.59, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1150.64, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(20);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 86513.59, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(21);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 102222.63, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1099.63, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(22);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 103679.10, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 101.95, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(23);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 103679.10, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(24);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 118347.79, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1026.81, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(25);
        totalEarnings = totalEarnings.add(period.getEarnings());
        Assert.assertEquals("The earnings is not correct", 118347.79, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 10 (in Page 19 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "03/31/1985,12/31/1988,CSRS,DEPOSIT,4091.64,58452.09,null,true"),
                // extended service period 9 (in Page 18 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/01/1984,12/22/1984,CSRS,DEPOSIT,704.92,10070.26,null,true"),
                // extended service period 8 (in Page 16 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/03/1983,12/24/1983,CSRS,DEPOSIT,681.08,9729.66,null,true"),
                // extended service period 7 (in Page 15 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "10/01/1982,01/08/1983,CSRS,DEPOSIT,254.55,3636.49,null,true"),
                // extended service period 6 (in Page 13 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/04/1982,09/30/1982,CSRS,DEPOSIT,442.39,6319.89,null,true"),
                // extended service period 5 (in Page 12 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/05/1981,11/28/1981,CSRS,DEPOSIT,564.39,8062.73,null,true"),
                // extended service period 4 (in Page 10 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/14/1980,12/13/1980,CSRS,DEPOSIT,538.40,7691.41,null,true"),
                // extended service period 3 (in Page 8 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/02/1979,12/15/1979,CSRS,DEPOSIT,485.42,6934.41,null,true"),
                // extended service period 2 (in Page 7 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/23/1978,12/03/1978,CSRS,DEPOSIT,345.52,4935.96,null,true"),
                // extended service period 1 (in Page 5 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/22/1977,09/30/1977,CSRS,DEPOSIT,176.04,2514.88,null,true")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario - Conner Case CSRS component after 1988.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario16() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-16.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 0.0, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 13753.40, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 962.74, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 28394.78, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1024.90, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(3);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 28394.78, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(4);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 44570.80, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1132.32, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(5);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 44570.80, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(6);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 57471.00, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 903.01, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(7);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 59610.67, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 149.78, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(8);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 73493.28, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 971.78, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(9);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 93139.22, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1375.22, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(10);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 94074.03, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 65.44, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(11);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 94074.03, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 0.0, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(12);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 106894.29, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 897.42, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 6 (in Page 10 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "03/06/1994,10/27/1995,CSRS,DEPOSIT,2338.08,33401.01,null,true"),
                // extended service period 5 (in Page 9 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/04/1993,12/25/1993,CSRS,DEPOSIT,1121.56,16022.28,null,true"),
                // extended service period 4 (in Page 8 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/13/1992,11/14/1992,CSRS,DEPOSIT,903.01,12900.20,null,true"),
                // extended service period 3 (in Page 7 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "03/11/1990,12/15/1991,CSRS,DEPOSIT,2157.22,30817.40,null,true"),
                // extended service period 2 (in Page 6 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "02/26/1989,11/09/1989,CSRS,DEPOSIT,962.74,13753.40,null,true"),
                // extended service period 1 (in Page 5 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "01/01/1989,02/25/1989,CSRS,NO EARNINGS,0.00,0.00,null,true")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario - Conner Case FERS component.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario17() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-17.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        // Error delta
        double delta = 1e-10 + 0.01;
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 1259.64, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 16.38, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 2514.88, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 16.32, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 6149.46, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 47.25, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(3);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 7450.84, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 16.92, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(4);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 12276.77, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 62.74, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(5);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 12667.57, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 5.08, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(6);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 14385.25, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 22.33, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(7);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 19725.67, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 69.43, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(8);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 22076.66, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 30.56, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(9);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 28175.59, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 79.29, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(10);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 30139.40, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 25.53, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(11);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 36459.29, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 82.16, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(12);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 36530.71, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 0.93, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(13);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 40095.78, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 46.35, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(14);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 49825.44, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 126.49, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(15);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 59895.70, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 130.91, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(16);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 68689.59, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 114.32, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(17);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 70075.91, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 18.02, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(18);
        Assert.assertEquals("The earnings is not correct", 70075.91, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 0.00, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(19);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 86513.59, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 213.69, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(20);
        Assert.assertEquals("The earnings is not correct", 86513.59, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 0.00, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(21);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 102222.63, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 204.22, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(22);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 103679.10, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 18.93, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(23);
        Assert.assertEquals("The earnings is not correct", 103679.10, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 0.00, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(24);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 118347.79, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 190.69, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(25);
        Assert.assertEquals("The earnings is not correct", 118347.79, TestsHelper.toDouble(totalEarnings), delta);
        Assert.assertEquals("The deduction is not correct", 0.00, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 9 (in Page 19 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "03/31/1985,12/31/1988,FERS,DEPOSIT,759.87,58452.09,null,true"),
                // extended service period 8 (in Page 17 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/01/1984,12/22/1984,FERS,DEPOSIT,130.91,10070.26,null,true"),
                // extended service period 7 (in Page 16 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/03/1983,12/24/1983,FERS,DEPOSIT,126.49,9729.66,null,true"),
                // extended service period 6 (in Page 14 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/04/1982,01/08/1983,FERS,DEPOSIT,129.44,9956.38,null,true"),
                // extended service period 5 (in Page 13 of the pdf file)
                // NOTE: it has been confirmed the example PDF has mistake on this extended service period
                // http://apps.topcoder.com/forums/?module=Thread&threadID=807574&start=0
                TestsHelper.parseExtendedServicePeriod(
                        "04/05/1981,11/28/1981,FERS,DEPOSIT,104.82,8062.73,null,true"),
                // extended service period 4 (in Page 11 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/14/1980,12/13/1980,FERS,DEPOSIT,99.99,7691.41,null,true"),
                // extended service period 3 (in Page 9 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/02/1979,12/15/1979,FERS,DEPOSIT,90.15,6934.41,null,true"),
                // extended service period 2 (in Page 8 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "04/23/1978,12/03/1978,FERS,DEPOSIT,64.17,4935.96,null,true"),
                // extended service period 1 (in Page 6 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/22/1977,09/30/1977,FERS,DEPOSIT,32.70,2514.88,null,true")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario - CSRS Firefighter Deposit.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario18() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-18.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 10503.97, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 787.80, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 23825.58, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 999.12, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 37263.50, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1007.84, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 2 (in Page 3 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "01/01/1987,12/31/1988,CSRS,DEPOSIT,2006.96,26759.53,null,false"),
                // extended service period 1 (in Page 2 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/07/1985,02/28/1986,CSRS,DEPOSIT,787.80,10503.97,null,false")),
                response.getExtendedServicePeriods());
    }

    /**
     * Ret Scenario-Mixed DC Gov and GS Agency Hourly Rates after 3-1-86.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario19() throws Exception {
        DeductionCalculationRequest request = new DeductionCalculationRequest();
        request.setServicePeriods(TestsHelper.readServicePeriodsFromFile(SCENARIOS_FILE_FOLDER + "scenario-19.txt"));

        DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);

        // Check service periods
        BigDecimal totalEarnings = BigDecimal.ZERO;

        ServicePeriod period = request.getServicePeriods().get(0);

        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 8403.89, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 588.27, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(1);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 23167.85, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1033.48, TestsHelper.toDouble(period.getDeduction()));

        period = request.getServicePeriods().get(2);
        totalEarnings = totalEarnings.add(period.getEarnings()).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals("The earnings is not correct", 42299.55, TestsHelper.toDouble(totalEarnings));
        Assert.assertEquals("The deduction is not correct", 1339.22, TestsHelper.toDouble(period.getDeduction()));

        // Check main calculation result
        TestsHelper.assertEqualsExtendedServicePeriodList(Arrays.asList(
                // extended service period 2 (in Page 6 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/06/1985,12/31/1987,CSRS,DEPOSIT,2372.70,33895.66,null,false"),
                // extended service period 1 (in Page 4 of the pdf file)
                TestsHelper.parseExtendedServicePeriod(
                        "05/20/1980,06/01/1981,CSRS,DEPOSIT,588.27,8403.89,null,false")),
                response.getExtendedServicePeriods());
    }
}
