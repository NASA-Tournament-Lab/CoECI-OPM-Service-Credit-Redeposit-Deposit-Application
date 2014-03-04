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

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.DeductionCalculationRequest;
import gov.opm.scrd.entities.application.DeductionCalculationResponse;
import gov.opm.scrd.entities.application.ExtendedServicePeriod;
import gov.opm.scrd.entities.application.ServicePeriod;
import gov.opm.scrd.services.DeductionCalculationRuleService;
import gov.opm.scrd.services.RuleServiceException;

import java.util.ArrayList;
import java.util.List;

import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * <p>
 * This class is the implementation of DeductionCalculationRuleService.
 * </p>
 *
 * <p>
 * Spring Sample Configuration:
 *
 * <pre>
 *
 *  &lt;drools:kagent id=&quot;deductionKnowledgeAgent&quot;
 *  kbase=&quot;deductionKnowledgeBase&quot; new-instance=&quot;true&quot; /&gt;
 *  &lt;drools:kbase id=&quot;deductionKnowledgeBase&quot;&gt;
 *             &lt;drools:resources&gt;
 *                 &lt;drools:resource type=&quot;CHANGE_SET&quot;
 *                 source=&quot;classpath:deduction-change-set.xml&quot; /&gt;
 *             &lt;/drools:resources&gt;
 *     &lt;/drools:kbase&gt;
 *
 * &lt;bean id=&quot;deductionCalculationRuleService&quot;
 *              class=&quot;gov.opm.scrd.services.impl.DeductionCalculationRuleServiceImpl&quot;&gt;
 *              &lt;property name=&quot;knowledgeLogFileName&quot; value=&quot;log/Deduction&quot; /&gt;
 *             &lt;property name=&quot;knowledgeAgent&quot; ref=&quot;deductionKnowledgeAgent&quot; /&gt;
 * &lt;/bean&gt;
 *
 * </pre>
 *
 * </p>
 * <p>
 * Sample Usage:
 *
 * <pre>
 *     // service is injected by Spring
 *     private DeductionCalculationRuleService deductionCalculationRuleService;
 *     ...
 *     List&lt;ServicePeriod&gt; periods = new ArrayList&lt;ServicePeriod&gt;();
 *     ServicePeriod period = TestsHelper
 *                 .parseServicePeriod(
 *                 &quot;05/07/1985,02/28/1986,CSRS,DEPOSIT,TEMPORARY,FIREFIGHTER,12862.00,ANNUAL SALARY&quot;);
 *     periods.add(period);
 *     DeductionCalculationRequest request = new DeductionCalculationRequest();
 *     request.setServicePeriods(periods);
 *     DeductionCalculationResponse response = deductionCalculationRuleService.execute(request);
 *     ...
 * </pre>
 *
 * </p>
 * <p>
 * <b>Thread Safety</b> This class is effectively thread safe (injected
 * configurations are not considered as thread safety factor). Drools is used in
 * thread safe manner in this module, because each rule service execution call
 * will result a dedicated Drools KnowledgeSession and the session will only be
 * used in single thread.
 * </p>
 *
 * @author albertwang, TCSASSEMBLER
 * @version 1.0
 * @since OPM - Implement Business Rules Engine Deduction Calculation Assembly
 *        v1.0
 */
public class DeductionCalculationRuleServiceImpl extends DroolsRuleService
        implements DeductionCalculationRuleService {

    /**
     * <p>
     * Represents the global name of extendedServicePeriods in drools.
     * </p>
     */
    private static final String EXTENDED_SERVICE_PERIODS = "extendedServicePeriods";

    /**
     * <p>
     * Represents the name of this class for logging.
     * </p>
     */
    private static final String CLASS_NAME = DeductionCalculationRuleServiceImpl.class.getName();

    /**
     * <p>
     * Creates the instance of DeductionCalculationRuleServiceImpl.
     * </p>
     *
     * <p>
     * This is the default constructor of DeductionCalculationRuleServiceImpl.
     * </p>
     */
    public DeductionCalculationRuleServiceImpl() {
        // does nothing
    }

    /**
     * <p>
     * Executes the rules to the service periods to calculate their
     * deductions. This methods also merges the service period to extended service period.
     * </p>
     *
     * @param request
     *            that contains the service periods to calculate.
     *
     * @return the response of the extended service periods with calculated
     *         deductions (and also earnings, mid-point etc.).
     *
     * @throws IllegalArgumentException if the request is null.
     * @throws RuleServiceException
     *             if any error occurs when executing the rule.
     */
    @Override
    public DeductionCalculationResponse execute(DeductionCalculationRequest request)
        throws RuleServiceException {

        // log the entrance
        String signature = CLASS_NAME + "#execute(DeductionCalculationRequest request)";
        LoggingHelper.logEntrance(getLogger(), signature,
                new String[] {"request"}, new Object[] {request});

        // validate the parameters
        ServiceHelper.checkNull(request, "request");

        StatefulKnowledgeSession ksession = null;
        KnowledgeRuntimeLogger logger = null;

        try {
            // start new session and the file logger
            ksession = getKnowledgeBase().newStatefulKnowledgeSession();
            logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, getKnowledgeLogFileName());

            // Populate facts
            for (ServicePeriod sp : request.getServicePeriods()) {
                ksession.insert(sp);
            }

            // Set global variables
            List<ExtendedServicePeriod> esps = new ArrayList<ExtendedServicePeriod>();
            ksession.setGlobal(EXTENDED_SERVICE_PERIODS, esps);


            // Fire all rules
            ksession.fireAllRules();

            // populate the final result
            DeductionCalculationResponse response = new DeductionCalculationResponse();

            response.setExtendedServicePeriods(esps);

            // log and return
            LoggingHelper.logExit(getLogger(), signature, new Object[] {response});

            return response;

        } catch (RuntimeException e) {
            // log the exception and wrap it to RuleServiceException
            throw LoggingHelper.logException(getLogger(), signature,
                    new RuleServiceException("Unable to complete rule execution.", e));
        } finally {
            // closes the resources
            if (logger != null) {
                logger.close();
            }

            if (ksession != null) {
                ksession.dispose();
            }
        }
    }
}
