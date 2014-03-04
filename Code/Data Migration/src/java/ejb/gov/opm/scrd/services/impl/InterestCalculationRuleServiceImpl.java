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
import gov.opm.scrd.entities.application.ExtendedServicePeriod;
import gov.opm.scrd.entities.application.InterestCalculationRequest;
import gov.opm.scrd.entities.application.InterestCalculationResponse;
import gov.opm.scrd.services.InterestCalculationRuleService;
import gov.opm.scrd.services.RuleServiceException;

import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * <p>
 * This class is the implementation of InterestCalculationRuleService.
 * </p>
 *
 * <p>
 * Spring Sample Configuration:
 *
 * <pre>
 *         &lt;drools:kbase id=&quot;knowledgeBase&quot;&gt;
 *             &lt;drools:resources&gt;
 *                 &lt;drools:resource type=&quot;CHANGE_SET&quot; source=&quot;classpath:change-set.xml&quot; /&gt;
 *             &lt;/drools:resources&gt;
 *         &lt;/drools:kbase&gt;
 *
 *         &lt;bean id=&quot;interestCalculationRuleService&quot;
 *             class=&quot;gov.opm.scrd.services.impl.InterestCalculationRuleServiceImpl&quot;&gt;
 *             &lt;property name=&quot;knowledgeLogFileName&quot; value=&quot;log/test&quot; /&gt;
 *             &lt;property name=&quot;knowledgeAgent&quot; ref=&quot;knowledgeAgent&quot; /&gt;
 *         &lt;/bean&gt;
 *
 * </pre>
 *
 * </p>
 * <p>
 * Sample Usage:
 *
 * <pre>
 * // construct the request
 * InterestCalculationRequest request = new InterestCalculationRequest();
 * request.setInterestCalculatedToDate(new DateTime(2013, 7, 30, 0, 0).toDate());
 * List&lt;ExtendedServicePeriod&gt; extendedServicePeriods = new ArrayList&lt;ExtendedServicePeriod&gt;();
 * ExtendedServicePeriod extendedServicePeriod = new ExtendedServicePeriod();
 * extendedServicePeriod.setValidationErrors(new ArrayList<String>());
 * RetirementType retirementType = new RetirementType();
 * retirementType.setName(&quot;FERS&quot;);
 * extendedServicePeriod.setRetirementType(retirementType);
 * extendedServicePeriod.setTotalDeduction(new BigDecimal(787.80));
 * extendedServicePeriod.setTotalEarnings(new BigDecimal(10503.97));
 * extendedServicePeriod.setBeginDate(new DateTime(1985, 5, 7, 0, 0).toDate());
 * extendedServicePeriod.setEndDate(new DateTime(1986, 2, 28, 0, 0).toDate());
 * extendedServicePeriods.add(extendedServicePeriod);
 * request.setExtendedServicePeriods(extendedServicePeriods);
 *
 * // get the service from spring:
 * interestCalculationRuleService = context
 *         .getBean(&quot;interestCalculationRuleService&quot;, InterestCalculationRuleService.class);
 *
 * // execute the rules
 * InterestCalculationResponse response = interestCalculationRuleService.execute(request);
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
 * Changes:
 * Version 1.1 - Moves some common code to a helper method.
 *
 * @author albertwang, TCSASSEMBLER
 * @version 1.1
 *
 * @since OPM Rules Engine Models Exceptions and Interest Calculation v1.0
 *        Assembly
 *
 */
public class InterestCalculationRuleServiceImpl extends DroolsRuleService
        implements InterestCalculationRuleService {

    /**
     * <p>
     * Represents the name of the drools global variable:
     * interestCalculatedToDate.
     * </p>
     */
    private static final String INTEREST_CALCULATED_TO_DATE = "interestCalculatedToDate";

    /**
     * <p>
     * Represents the name of this class for logging.
     * </p>
     */
    private static final String CLASS_NAME = InterestCalculationRuleServiceImpl.class.getName();

    /**
     * <p>
     * Creates the instance of InterestCalculationRuleServiceImpl.
     * </p>
     *
     * <p>
     * This is the default constructor of InterestCalculationRuleServiceImpl.
     * </p>
     */
    public InterestCalculationRuleServiceImpl() {
        // does nothing
    }

    /**
     * <p>
     * Executes the rules to the extended service periods to calculate their
     * interests.
     * </p>
     *
     * @param request
     *            that contains the extended service periods and the date to
     *            calculated to.
     *
     * @return the response of the extended service periods with calculated
     *         interests.
     *
     * @throws IllegalArgumentException if the request is null.
     * @throws RuleServiceException
     *             if any error occurs when executing the rule.
     */
    @Override
    public InterestCalculationResponse execute(InterestCalculationRequest request)
        throws RuleServiceException {

        // log the entrance
        String signature = CLASS_NAME + "#execute(InterestCalculationRequest request)";
        LoggingHelper.logEntrance(getLogger(), signature,
                new String[] {"request"}, new Object[] {request});

        ServiceHelper.checkNull(request, "request");

        StatefulKnowledgeSession ksession = null;
        KnowledgeRuntimeLogger logger = null;

        try {
            // start new session and the file logger
            ksession = getKnowledgeBase().newStatefulKnowledgeSession();
            logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, getKnowledgeLogFileName());

            // Populate facts
            for (ExtendedServicePeriod esp : request.getExtendedServicePeriods()) {
                ksession.insert(esp);
            }

            // Set global variables
            ksession.setGlobal(INTEREST_CALCULATED_TO_DATE, request.getInterestCalculatedToDate());

            // Fire all rules
            ksession.fireAllRules();

            // populate the final result
            InterestCalculationResponse response = new InterestCalculationResponse();

            response.setExtendedServicePeriods(request.getExtendedServicePeriods());

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
