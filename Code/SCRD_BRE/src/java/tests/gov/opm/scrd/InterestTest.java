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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.JUnit4TestAdapter;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.ExtendedServicePeriod;
import gov.opm.scrd.entities.application.InterestCalculationRequest;
import gov.opm.scrd.entities.application.InterestCalculationResponse;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.ServiceType;
import gov.opm.scrd.services.InterestCalculationRuleService;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * Provides tests for all the scenarios in the provided PDF documents.
 * </p>
 * <p>
 * Changes:
 * <ul>
 * <li>Fixed the scenario #16 where the original test code was not correct, because
 * it didn't follow the scenario's "workaround".
 * </ul>
 * </p>
 * 
 * @author TCSASSEMBLER
 * @version 1.1
 * @since OPM - Rules Engine - Scenarios Conversion 2 - Interest Update Assembly
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class InterestTest {
    /**
     * The CSRS retirement type.
     */
    private static RetirementType csrsRetirementType;

    /**
     * The FERS retirement type.
     */
    private static RetirementType fersRetirementType;

    /**
     * The DEPOSIT period type.
     */
    private static PeriodType depositPeriodType;

    /**
     * The REDEPOSIT period type.
     */
    private static PeriodType redepositPeriodType;

    /**
     * The PEACE CORPS service type.
     */
    private static ServiceType peaceCorpsServiceType;

    /**
     * The PEACE CORPS/VISTA service type.
     */
    private static ServiceType peaceCorpsVistaServiceType;

    /**
     * A dummy service type to differentiate PEACE CORPS service type.
     */
    private static ServiceType otherServiceType;

    /**
     * Represents the interest calculation rule service for CSRS and FERS type.
     * 
     * It is injected by Spring via IoC.
     */
    @Autowired
    private InterestCalculationRuleService interestCalculationRuleService;

    /**
     * <p>
     * Creates a test suite for the tests in this test case.
     * </p>
     * 
     * @return a Test suite for this test case.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(InterestTest.class);
    }

    /**
     * Create all the lookup entities
     */
    @BeforeClass
    public static void setUpClass() {
        csrsRetirementType = new RetirementType();
        csrsRetirementType.setName("CSRS");

        fersRetirementType = new RetirementType();
        fersRetirementType.setName("FERS");

        depositPeriodType = new PeriodType();
        depositPeriodType.setName("DEPOSIT");

        redepositPeriodType = new PeriodType();
        redepositPeriodType.setName("REDEPOSIT");

        peaceCorpsServiceType = new ServiceType();
        peaceCorpsServiceType.setName("PEACE CORPS");

        peaceCorpsVistaServiceType = new ServiceType();
        peaceCorpsVistaServiceType.setName("PEACE CORPS/VISTA");

        otherServiceType = new ServiceType();
        otherServiceType.setName("OTHER");
    }

    /**
     * Ret Scenario - CSRS Deposit on Earnings Spanning 10-1-82 .pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario1() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 7, 30, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 4 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(376.73));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(376.73 + 622.78));
        extendedServicePeriod.setBeginDate(new DateTime(1978, 5, 30, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1982, 9, 30, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2: (in Page 5 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(33.55));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(479.23));
        extendedServicePeriod.setBeginDate(new DateTime(1982, 10, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1983, 2, 19, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 622.70,
                TestsHelper.getTotalInterest(periods.get(0)));

        Assert.assertEquals("The interest for the extended service period is not correct.", 168.02,
                TestsHelper.getTotalInterest(periods.get(1)));

    }

    /**
     * Ret Scenario- CSRS mixed red and dep.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario2() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2009, 12, 9, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 4 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(313.24));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(313.24 + 796.90));
        extendedServicePeriod.setBeginDate(new DateTime(1966, 7, 10, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1967, 10, 1, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2: (in Page 6 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(109.89));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(109.89 + 228.89));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1971, 11, 9, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1971);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(109.89));
        extendedServicePeriod.setBeginDate(new DateTime(1971, 5, 17, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1971, 9, 2, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 3: (in Page 8 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(504.49));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(504.49 + 851.24));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1976, 7, 2, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1976);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(504.49));
        extendedServicePeriod.setBeginDate(new DateTime(1975, 10, 3, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1976, 6, 1, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 4: (in Page 9 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(2459.79));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(2459.79 + 10330.72));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1983, 11, 2, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1983);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(2459.79));
        extendedServicePeriod.setBeginDate(new DateTime(1981, 5, 13, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1983, 9, 9, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 5: (in Page 10 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(137.69));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(137.69 + 383.35));
        extendedServicePeriod.setBeginDate(new DateTime(1987, 7, 27, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1987, 9, 26, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 6: (in Page 11 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(74.02));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(74.02 + 88.22));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1994, 7, 27, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1994);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(74.02));
        extendedServicePeriod.setBeginDate(new DateTime(1987, 9, 27, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1988, 4, 29, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 7: (in Page 12 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(571.12));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(571.12 + 680.68));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1994, 7, 27, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1994);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(571.12));
        extendedServicePeriod.setBeginDate(new DateTime(1990, 2, 25, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1994, 4, 30, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 8: (in Page 13 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(3361.60));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(3361.60 + 1630.42));
        extendedServicePeriod.setBeginDate(new DateTime(1999, 11, 30, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(2001, 7, 14, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 796.90,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 228.89,
                TestsHelper.getTotalInterest(periods.get(1)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 851.24,
                TestsHelper.getTotalInterest(periods.get(2)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 10330.72,
                TestsHelper.getTotalInterest(periods.get(3)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 383.35,
                TestsHelper.getTotalInterest(periods.get(4)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 88.22,
                TestsHelper.getTotalInterest(periods.get(5)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 680.68,
                TestsHelper.getTotalInterest(periods.get(6)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 1629.62,
                TestsHelper.getTotalInterest(periods.get(7)));

    }

    /**
     * Ret Scenario - CSRS dep,redep, peace corps.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario3() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 8, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 4 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(2479.57));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(2479.57 + 4476.07));
        extendedServicePeriod.setBeginDate(new DateTime(1976, 8, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1980, 11, 6, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2: (in Page 5 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(peaceCorpsServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(167.12));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(167.12 + 212.43));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1995, 10, 1, 0, 0).toDate());
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(167.12));
        extendedServicePeriod.setInterestCalculationYear(1995);
        extendedServicePeriod.setBeginDate(new DateTime(1981, 6, 4, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1982, 9, 30, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 3: (in Page 7 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(peaceCorpsServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(311.56));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(311.56 + 396.03));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1995, 10, 1, 0, 0).toDate());
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(311.56));
        extendedServicePeriod.setInterestCalculationYear(1995);
        extendedServicePeriod.setBeginDate(new DateTime(1982, 10, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1984, 11, 13, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 4: (in Page 9 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(4450.0));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(4450.0 + 13797.56));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1988, 2, 5, 0, 0).toDate());
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(4450.0));
        extendedServicePeriod.setInterestCalculationYear(1988);
        extendedServicePeriod.setBeginDate(new DateTime(1985, 8, 8, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1987, 12, 31, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 4476.07,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 212.43,
                TestsHelper.getTotalInterest(periods.get(1)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 396.03,
                TestsHelper.getTotalInterest(periods.get(2)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 13797.56,
                TestsHelper.getTotalInterest(periods.get(3)));

    }

    /**
     * Ret Scenario - CSRS Intermittent Svc Dep and FERS Redeposit.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario4() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 6, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 4 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(99.44));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(99.44 + 200.38));
        extendedServicePeriod.setBeginDate(new DateTime(1975, 11, 10, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1976, 9, 6, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2: (in Page 5 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(847.73));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(847.73 + 1334.44));
        extendedServicePeriod.setBeginDate(new DateTime(1980, 6, 28, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1982, 9, 30, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 3: (in Page 7 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(1819.14));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(1819.14 + 8681.10));
        extendedServicePeriod.setBeginDate(new DateTime(1982, 10, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1985, 11, 8, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 4: (in Page 8 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(1880.0));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(1880.0 + 4202.14));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1990, 12, 1, 0, 0).toDate());
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(1880.0));
        extendedServicePeriod.setInterestCalculationYear(1990);
        extendedServicePeriod.setBeginDate(new DateTime(1987, 10, 10, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1990, 10, 15, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 200.36,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 1334.26,
                TestsHelper.getTotalInterest(periods.get(1)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 8681.10,
                TestsHelper.getTotalInterest(periods.get(2)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 4202.14,
                TestsHelper.getTotalInterest(periods.get(3)));

    }

    /**
     * Ret Scenario- DC Govt hourly rates.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario5() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 7, 30, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 3 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(92.86));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(92.86 + 219.82));
        extendedServicePeriod.setBeginDate(new DateTime(1972, 5, 26, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1972, 8, 17, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2: (in Page 5 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(99.35));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(99.35 + 180.08));
        extendedServicePeriod.setBeginDate(new DateTime(1978, 7, 16, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1978, 9, 1, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 3: (in Page 7 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(568.44));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(568.44 + 2010.73));
        extendedServicePeriod.setBeginDate(new DateTime(1986, 9, 14, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1987, 2, 27, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 219.82,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 180.08,
                TestsHelper.getTotalInterest(periods.get(1)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 2010.73,
                TestsHelper.getTotalInterest(periods.get(2)));

    }

    /**
     * Ret Scenario-Postal Flex and LT Deposit.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario6() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 7, 30, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 2 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(1365.0));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(1365.0 + 4837.87));
        extendedServicePeriod.setBeginDate(new DateTime(1986, 4, 7, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1987, 7, 24, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2: (in Page 3 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(1218.59));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(1218.59 + 2651.21));
        extendedServicePeriod.setBeginDate(new DateTime(1990, 10, 11, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1991, 7, 2, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 4837.87,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 2651.21,
                TestsHelper.getTotalInterest(periods.get(1)));

    }

    /**
     * Ret Scenario - CSRS Two Refunds on Two Days.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario7() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 7, 31, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 2 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(17689.87));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(17689.87 + 44942.87));

        extendedServicePeriod.setInterestAccrualDate(new DateTime(1989, 11, 7, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1989);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(17689.87));
        extendedServicePeriod.setBeginDate(new DateTime(1974, 1, 13, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1988, 7, 16, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2: (in Page 3 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(500.0));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(500.0 + 1229.22));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1990, 2, 15, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1990);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(500.0));
        extendedServicePeriod.setBeginDate(new DateTime(1974, 1, 13, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1988, 7, 16, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 44942.87,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 1229.22,
                TestsHelper.getTotalInterest(periods.get(1)));

    }

    /**
     * Ret Scenario - Mixed CSRS & FERS new categories needed.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario8() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 9, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 2 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(8400.0));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(8400.0 + 40639.23));

        extendedServicePeriod.setInterestAccrualDate(new DateTime(1983, 12, 1, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1983);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(8400.0));
        extendedServicePeriod.setBeginDate(new DateTime(1978, 7, 10, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1983, 8, 31, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2: (in Page 3 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(273.72));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(273.72 + 838.25));
        extendedServicePeriod.setBeginDate(new DateTime(1987, 6, 6, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1988, 12, 31, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 3: (in Page 5 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(610.0));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(610.0 + 1116.06));

        extendedServicePeriod.setInterestAccrualDate(new DateTime(1992, 7, 27, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1992);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(610.0));
        extendedServicePeriod.setBeginDate(new DateTime(1989, 1, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1992, 6, 10, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 40639.23,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 838.01,
                TestsHelper.getTotalInterest(periods.get(1)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 1116.06,
                TestsHelper.getTotalInterest(periods.get(2)));

    }

    /**
     * Ret Scenario - FERS Dep & Redep.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario9() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 2, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 5 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(41.21));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(41.21 + 162.34));
        extendedServicePeriod.setBeginDate(new DateTime(1986, 1, 19, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1986, 3, 14, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2 (in Page 10 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(564.97));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(564.97 + 1123.91));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1991, 11, 15, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1991);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(564.97));
        extendedServicePeriod.setBeginDate(new DateTime(1986, 3, 15, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1990, 9, 15, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 162.34,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 1123.91,
                TestsHelper.getTotalInterest(periods.get(1)));
    }

    /**
     * Ret Scenario- FERS dep,redep, and Peace Corps.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario10() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 8, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 2 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(31.21));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(31.21 + 170.22));
        extendedServicePeriod.setBeginDate(new DateTime(1980, 5, 27, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1980, 8, 22, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2 (in Page 4 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(peaceCorpsServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(121.45));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(121.45 + 154.38));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1995, 10, 1, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1995);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(121.45));
        extendedServicePeriod.setBeginDate(new DateTime(1982, 10, 27, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1984, 9, 30, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 3 (in Page 6 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(231.25));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(231.25 + 916.77));
        extendedServicePeriod.setBeginDate(new DateTime(1985, 7, 29, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1986, 7, 28, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 4 (in Page 7 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(redepositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(1438.0));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(1438.0 + 1996.74));
        extendedServicePeriod.setInterestAccrualDate(new DateTime(1995, 2, 3, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1995);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(1438.0));
        extendedServicePeriod.setBeginDate(new DateTime(1989, 10, 10, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1994, 9, 16, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 170.22,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 154.38,
                TestsHelper.getTotalInterest(periods.get(1)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 916.77,
                TestsHelper.getTotalInterest(periods.get(2)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 1996.74,
                TestsHelper.getTotalInterest(periods.get(3)));
    }

    /**
     * Ret Scenario- FERS Deposit & Leave without Pay.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario11() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 7, 10, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 2 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(31.03));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(31.03 + 159.65));
        extendedServicePeriod.setBeginDate(new DateTime(1981, 5, 11, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1983, 1, 2, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 159.65,
                TestsHelper.getTotalInterest(periods.get(0)));

    }

    /**
     * Ret Scenario- FERS FSPS LEO.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario12() throws Exception {
        // No Extended Service Periods in this pdf.
    }

    /**
     * Ret Scenario- CSRS Peace Corps.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario13() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2008, 9, 1, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 3 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(peaceCorpsServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(54.07));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(54.07 + 49.43));

        extendedServicePeriod.setInterestAccrualDate(new DateTime(1995, 10, 1, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1995);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(54.07));
        extendedServicePeriod.setBeginDate(new DateTime(1974, 9, 30, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1975, 8, 8, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 49.43,
                TestsHelper.getTotalInterest(periods.get(0)));
    }

    /**
     * Ret Scenario- CSRS and FERS VISTA Svc with Earnings.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario14() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 9, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 3 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(peaceCorpsVistaServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(90.86));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(90.86 + 115.49));

        extendedServicePeriod.setInterestAccrualDate(new DateTime(1995, 10, 1, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(1995);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(90.86));
        extendedServicePeriod.setBeginDate(new DateTime(1972, 2, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1974, 6, 2, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2 (in Page 5 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(peaceCorpsVistaServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(75));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(75 + 0));

        extendedServicePeriod.setInterestAccrualDate(new DateTime(2012, 10, 10, 0, 0).toDate());
        extendedServicePeriod.setInterestCalculationYear(2012);
        extendedServicePeriod.setBalanceWithInterest(new BigDecimal(75));
        extendedServicePeriod.setBeginDate(new DateTime(2007, 7, 7, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(2009, 8, 7, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 115.49,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 0.0,
                TestsHelper.getTotalInterest(periods.get(1)));
    }

    /**
     * Ret Scenario- Conner Case CSRS component thru 1988.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario15() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 6, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 5 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(176.04));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(176.04 + 334.74));
        extendedServicePeriod.setBeginDate(new DateTime(1977, 5, 22, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1977, 9, 30, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2 (in Page 7 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(345.52));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(345.52 + 626.45));
        extendedServicePeriod.setBeginDate(new DateTime(1978, 4, 23, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1978, 12, 3, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 3 (in Page 8 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(485.42));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(485.42 + 840.76));
        extendedServicePeriod.setBeginDate(new DateTime(1979, 4, 2, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1979, 12, 15, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 4 (in Page 10 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(538.40));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(538.40 + 889.09));
        extendedServicePeriod.setBeginDate(new DateTime(1980, 4, 14, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1980, 12, 13, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 5 (in Page 12 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(564.39));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(564.39 + 889.86));
        extendedServicePeriod.setBeginDate(new DateTime(1981, 4, 5, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1981, 11, 28, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 6 (in Page 13 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(442.39));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(442.39 + 667.04));
        extendedServicePeriod.setBeginDate(new DateTime(1982, 4, 4, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1982, 9, 30, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 7 (in Page 15 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(254.55));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(254.55 + 1277.50));
        extendedServicePeriod.setBeginDate(new DateTime(1982, 10, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1983, 1, 8, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 8 (in Page 16 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(681.08));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(681.08 + 3330.43));
        extendedServicePeriod.setBeginDate(new DateTime(1983, 4, 3, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1983, 12, 24, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 9 (in Page 18 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(704.92));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(704.92 + 3326.74));
        extendedServicePeriod.setBeginDate(new DateTime(1984, 4, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1984, 12, 22, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 10 (in Page 19 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(4091.64));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(4091.64 + 14145.0));
        extendedServicePeriod.setBeginDate(new DateTime(1985, 3, 31, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1988, 12, 31, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 334.70,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 626.37,
                TestsHelper.getTotalInterest(periods.get(1)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 840.76,
                TestsHelper.getTotalInterest(periods.get(2)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 889.09,
                TestsHelper.getTotalInterest(periods.get(3)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 889.86,
                TestsHelper.getTotalInterest(periods.get(4)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 666.95,
                TestsHelper.getTotalInterest(periods.get(5)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 1277.50,
                TestsHelper.getTotalInterest(periods.get(6)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 3330.43,
                TestsHelper.getTotalInterest(periods.get(7)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 3326.74,
                TestsHelper.getTotalInterest(periods.get(8)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 14145.0,
                TestsHelper.getTotalInterest(periods.get(9)));

    }

    /**
     * Ret Scenario - Conner Case CSRS component after 1988.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario16() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 6, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 5 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setConnerCase(true);
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(0));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(0 + 0));

        extendedServicePeriod.setBeginDate(new DateTime(1989, 1, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1989, 2, 25, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2 (in Page 6 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setConnerCase(true);
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(962.74));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(962.74 + 2551.63));
        extendedServicePeriod.setBeginDate(new DateTime(1989, 2, 26, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1989, 11, 9, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 3 (in Page 7 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setConnerCase(true);
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(2157.22));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(2157.22 + 4730));
        extendedServicePeriod.setBeginDate(new DateTime(1990, 3, 11, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1991, 12, 15, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 4 (in Page 8 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setConnerCase(true);
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(903.01));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(903.01 + 1651.04));
        extendedServicePeriod.setBeginDate(new DateTime(1992, 4, 13, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1992, 11, 14, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 5 (in Page 9 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setConnerCase(true);
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(1121.56));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(1121.56 + 1818.49));
        extendedServicePeriod.setBeginDate(new DateTime(1993, 4, 4, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1993, 12, 25, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 6 (in Page 10 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setConnerCase(true);
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(2338.08));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(2338.08 + 3278.19));
        extendedServicePeriod.setBeginDate(new DateTime(1994, 3, 6, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1995, 10, 27, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        // Check calculation result
        // NOTE that the Conner Case interest calculation steps in the PDF is NOT correct, as per
        // the PDF: "No interest is charged until Jan 1,1999, workaround - we have to do
        // a manual computation of interest from 01/01/1999 - present". 
        // So basically the actual interest calculation result is presented at manual computation
        // on the last page of the PDF, i.e. the total balance with interest for all extended service
        // periods is $1,3788.99
        // The following verification uses correct interest result computed manually
        Assert.assertEquals("The interest for the extended service period is not correct.", 0.0,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 810.11,
                TestsHelper.getTotalInterest(periods.get(1)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 1815.23,
                TestsHelper.getTotalInterest(periods.get(2)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 759.85,
                TestsHelper.getTotalInterest(periods.get(3)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 943.76,
                TestsHelper.getTotalInterest(periods.get(4)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 1967.42,
                TestsHelper.getTotalInterest(periods.get(5)));
    }

    /**
     * Ret Scenario - Conner Case FERS component.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario17() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 6, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 6 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(32.70));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(32.70 + 197.62));
        extendedServicePeriod.setBeginDate(new DateTime(1977, 5, 22, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1977, 9, 30, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2 (in Page 8 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(64.17));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(64.17 + 374.02));
        extendedServicePeriod.setBeginDate(new DateTime(1978, 4, 23, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1978, 12, 3, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 3 (in Page 9 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(90.15));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(90.15 + 507.72));
        extendedServicePeriod.setBeginDate(new DateTime(1979, 4, 2, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1979, 12, 15, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 4 (in Page 11 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(99.99));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(99.99 + 543.55));
        extendedServicePeriod.setBeginDate(new DateTime(1980, 4, 14, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1980, 12, 13, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 5 (in Page 13 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(104.82));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(104.82 + 417.80));
        extendedServicePeriod.setBeginDate(new DateTime(1981, 4, 5, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1981, 11, 28, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 6 (in Page 14 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(129.44));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(129.44 + 655.37));
        extendedServicePeriod.setBeginDate(new DateTime(1982, 4, 4, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1983, 1, 8, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 7 (in Page 16 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(126.49));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(126.49 + 618.53));
        extendedServicePeriod.setBeginDate(new DateTime(1983, 4, 3, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1983, 12, 24, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 8 (in Page 17 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(130.91));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(130.91 + 617.81));
        extendedServicePeriod.setBeginDate(new DateTime(1984, 4, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1984, 12, 22, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 9 (in Page 19 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(fersRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(759.87));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(759.87 + 2626.91));
        extendedServicePeriod.setBeginDate(new DateTime(1985, 3, 31, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1988, 12, 31, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 197.60,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 373.99,
                TestsHelper.getTotalInterest(periods.get(1)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 507.72,
                TestsHelper.getTotalInterest(periods.get(2)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 543.55,
                TestsHelper.getTotalInterest(periods.get(3)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 550.81,
                TestsHelper.getTotalInterest(periods.get(4)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 655.31,
                TestsHelper.getTotalInterest(periods.get(5)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 618.53,
                TestsHelper.getTotalInterest(periods.get(6)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 617.81,
                TestsHelper.getTotalInterest(periods.get(7)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 2626.91,
                TestsHelper.getTotalInterest(periods.get(8)));

    }

    /**
     * Ret Scenario - CSRS Firefighter Deposit.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario18() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 8, 7, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 2 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(787.80));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(787.80 + 3277.70));
        extendedServicePeriod.setBeginDate(new DateTime(1985, 5, 7, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1986, 2, 28, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2 (in Page 3 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(2006.96));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(2006.96 + 6283.24));
        extendedServicePeriod.setBeginDate(new DateTime(1987, 1, 1, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1988, 12, 31, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 3277.70,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 6283.24,
                TestsHelper.getTotalInterest(periods.get(1)));

    }

    /**
     * Ret Scenario-Mixed DC Gov and GS Agency Hourly Rates after 3-1-86.pdf
     * 
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void testScenario19() throws Exception {
        InterestCalculationRequest request = new InterestCalculationRequest();
        request.setInterestCalculatedToDate(new DateTime(2013, 7, 30, 0, 0).toDate());
        List<ExtendedServicePeriod> extendedServicePeriods = new ArrayList<ExtendedServicePeriod>();

        // extended service period 1 (in Page 4 of the pdf file)
        ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(588.27));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(588.27 + 957.58));
        extendedServicePeriod.setBeginDate(new DateTime(1980, 5, 20, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1981, 6, 1, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        // extended service period 2 (in Page 6 of the pdf file)
        extendedServicePeriod = new ExtendedServicePeriod();
        extendedServicePeriod.setValidationErrors(new ArrayList<String>());
        extendedServicePeriod.setRetirementType(csrsRetirementType);
        extendedServicePeriod.setPeriodType(depositPeriodType);
        extendedServicePeriod.setServiceType(otherServiceType);
        extendedServicePeriod.setTotalDeduction(new BigDecimal(2372.70));
        extendedServicePeriod.setTotalEarnings(new BigDecimal(2372.70 + 8699.91));
        extendedServicePeriod.setBeginDate(new DateTime(1985, 5, 6, 0, 0).toDate());
        extendedServicePeriod.setEndDate(new DateTime(1987, 12, 31, 0, 0).toDate());
        extendedServicePeriods.add(extendedServicePeriod);

        request.setExtendedServicePeriods(extendedServicePeriods);
        InterestCalculationResponse response = interestCalculationRuleService.execute(request);

        // verify the result
        List<ExtendedServicePeriod> periods = response.getExtendedServicePeriods();

        Assert.assertEquals("The interest for the extended service period is not correct.", 957.58,
                TestsHelper.getTotalInterest(periods.get(0)));
        Assert.assertEquals("The interest for the extended service period is not correct.", 8696.61,
                TestsHelper.getTotalInterest(periods.get(1)));

    }

}
