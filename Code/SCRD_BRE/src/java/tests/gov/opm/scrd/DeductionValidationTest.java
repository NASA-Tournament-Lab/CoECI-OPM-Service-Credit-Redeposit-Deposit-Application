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

import gov.opm.scrd.entities.application.DeductionValidationRequest;
import gov.opm.scrd.entities.application.ServicePeriod;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.services.DeductionValidationRuleService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
 * Provides deduction validation tests.
 * </p>
 * 
 * @author TCSASSEMBLER
 * @version 1.0
 * @since OPM - Rules Engine - Scenarios Conversion 2 - Deduction Update Assembly
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml"})
public class DeductionValidationTest {
    /**
     * Represents the deduction validation rule service for CSRS and FERS type.
     * 
     * It is injected by Spring via IoC.
     */
    @Autowired
    private DeductionValidationRuleService deductionValidationRuleService;

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
        return new JUnit4TestAdapter(DeductionValidationTest.class);
    }

    /**
     * Test validation rule: begin date is required.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidationNoBeginDate() throws Exception {
        DeductionValidationRequest request = new DeductionValidationRequest();
        ServicePeriod sp = TestsHelper.parseServicePeriod(
                "07/10/1966,10/01/1967,CSRS,DEPOSIT,TEMPORARY,GS,3925.00,ANNUAL SALARY,null,false");
        sp.setBeginDate(null);
        request.setServicePeriods(Arrays.asList(sp));
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 1,
                sp.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                sp.getValidationErrors().containsValue("Begin date is required."));
    }

    /**
     * Test validation rule: end date is required.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidationNoEndDate() throws Exception {
        DeductionValidationRequest request = new DeductionValidationRequest();
        ServicePeriod sp = TestsHelper.parseServicePeriod(
                "07/10/1966,10/01/1967,CSRS,DEPOSIT,TEMPORARY,GS,3925.00,ANNUAL SALARY,null,false");
        sp.setEndDate(null);
        request.setServicePeriods(Arrays.asList(sp));
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 1,
                sp.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                sp.getValidationErrors().containsValue("End date is required."));
    }

    /**
     * Test validation rule: begin date is after end date.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidationBeginDateIsAfterEndDate() throws Exception {
        DeductionValidationRequest request = new DeductionValidationRequest();
        ServicePeriod sp = TestsHelper.parseServicePeriod(
                "07/10/1966,10/01/1965,CSRS,DEPOSIT,TEMPORARY,GS,3925.00,ANNUAL SALARY,null,false");
        request.setServicePeriods(Arrays.asList(sp));
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 1,
                sp.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                sp.getValidationErrors().containsValue("Begin date must not be later than End date."));
    }

    /**
     * Test validation rule: amount is required.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidationNoAmount() throws Exception {
        DeductionValidationRequest request = new DeductionValidationRequest();
        ServicePeriod sp = TestsHelper.parseServicePeriod(
                "07/10/1966,10/01/1967,CSRS,DEPOSIT,TEMPORARY,GS,3925.00,ANNUAL SALARY,null,false");
        sp.setAmount(null);
        request.setServicePeriods(Arrays.asList(sp));
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 1,
                sp.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                sp.getValidationErrors().containsValue("Amount is required."));
    }

    /**
     * Test validation rule: pay type is required.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidationNoPayType() throws Exception {
        DeductionValidationRequest request = new DeductionValidationRequest();
        ServicePeriod sp = TestsHelper.parseServicePeriod(
                "07/10/1966,10/01/1967,CSRS,DEPOSIT,TEMPORARY,GS,3925.00,ANNUAL SALARY,null,false");
        sp.setPayType(null);
        request.setServicePeriods(Arrays.asList(sp));
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 1,
                sp.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                sp.getValidationErrors().containsValue("PayType is required."));
    }

    /**
     * Test validation rule: retirement type is required.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidationNoRetirementType() throws Exception {
        DeductionValidationRequest request = new DeductionValidationRequest();
        ServicePeriod sp = TestsHelper.parseServicePeriod(
                "07/10/1966,10/01/1967,CSRS,DEPOSIT,TEMPORARY,GS,3925.00,ANNUAL SALARY,null,false");
        sp.setRetirementType(null);
        request.setServicePeriods(Arrays.asList(sp));
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 1,
                sp.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                sp.getValidationErrors().containsValue("RetirementType is required."));
    }

    /**
     * Test validation rule: service type is required.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidationNoServiceType() throws Exception {
        DeductionValidationRequest request = new DeductionValidationRequest();
        ServicePeriod sp = TestsHelper.parseServicePeriod(
                "07/10/1966,10/01/1967,CSRS,DEPOSIT,TEMPORARY,GS,3925.00,ANNUAL SALARY,null,false");
        sp.setServiceType(null);
        request.setServicePeriods(Arrays.asList(sp));
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 1,
                sp.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                sp.getValidationErrors().containsValue("ServiceType is required."));
    }

    /**
     * Test validation rule: period type is required.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidationNoPeriodType() throws Exception {
        DeductionValidationRequest request = new DeductionValidationRequest();
        ServicePeriod sp = TestsHelper.parseServicePeriod(
                "07/10/1966,10/01/1967,CSRS,DEPOSIT,TEMPORARY,GS,3925.00,ANNUAL SALARY,null,false");
        sp.setPeriodType(null);
        request.setServicePeriods(Arrays.asList(sp));
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 1,
                sp.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                sp.getValidationErrors().containsValue("PeriodType is required."));
    }

    /**
     * Test validation rule: interest accrual date is required for REDEPOSIT period.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testValidationNoInterestAccrualDateForRedeposit() throws Exception {
        DeductionValidationRequest request = new DeductionValidationRequest();
        ServicePeriod sp = TestsHelper.parseServicePeriod(
                "05/17/1971,09/02/1971,CSRS,REDEPOSIT,CAREER,WAGE GRADE,109.89,REFUND,11/09/1971,false");
        sp.setInterestAccrualDate(null);
        request.setServicePeriods(Arrays.asList(sp));
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 1,
                sp.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                sp.getValidationErrors().containsValue("Redeposit service period must have Interest Accrual Date."));
    }

    /**
     * Shows the usage if the service period is invalid.
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testErrorReporting1() throws Exception  {
        DeductionValidationRequest request = new DeductionValidationRequest();
        List<ServicePeriod> servicePeriods = new ArrayList<ServicePeriod>();
        ServicePeriod period = new ServicePeriod();
        period.setValidationErrors(new HashMap<String, String>());
        servicePeriods.add(period);
        request.setServicePeriods(servicePeriods);
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 7,
                period.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("Begin date is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("End date is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("Amount is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("PayType is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("RetirementType is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("ServiceType is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("PeriodType is required."));
    }

    /**
     * Shows the usage if the service period is invalid:
     * "Redeposit service period must have Interest Accrual Date"
     * 
     * @throws Exception to JUnit.
     */
    @Test
    public void testErrorReporting2() throws Exception  {
        DeductionValidationRequest request = new DeductionValidationRequest();
        List<ServicePeriod> servicePeriods = new ArrayList<ServicePeriod>();
        ServicePeriod period = new ServicePeriod();
        PeriodType periodType = new PeriodType();
        periodType.setName("REDEPOSIT");
        period.setPeriodType(periodType);
        period.setValidationErrors(new HashMap<String, String>());
        servicePeriods.add(period);
        request.setServicePeriods(servicePeriods);
        deductionValidationRuleService.execute(request);

        // check the error
        Assert.assertEquals("validation error size invalid", 7,
                period.getValidationErrors().size());

        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("Begin date is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("End date is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("Amount is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("PayType is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("RetirementType is required."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("Redeposit service period must have Interest Accrual Date."));
        Assert.assertTrue("validation message invalid",
                period.getValidationErrors().containsValue("ServiceType is required."));
    }
}
