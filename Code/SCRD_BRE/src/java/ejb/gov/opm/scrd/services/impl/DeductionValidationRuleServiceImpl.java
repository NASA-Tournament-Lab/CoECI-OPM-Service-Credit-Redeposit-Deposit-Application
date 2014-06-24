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
import gov.opm.scrd.entities.application.DeductionValidationRequest;
import gov.opm.scrd.entities.application.DeductionValidationResponse;
import gov.opm.scrd.entities.application.ServicePeriod;
import gov.opm.scrd.services.DeductionValidationRuleService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.RuleServiceException;

import javax.annotation.PostConstruct;

import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * <p>
 * This class is the implementation of DeductionValidationRuleService.
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
 */
public class DeductionValidationRuleServiceImpl extends DroolsRuleService
        implements DeductionValidationRuleService {
    /**
     * <p>
     * Represents the name of this class for logging.
     * </p>
     */
    private static final String CLASS_NAME = DeductionValidationRuleServiceImpl.class.getName();
    
    /**
     * <p>
     * Creates the instance of DeductionCalculationRuleServiceImpl.
     * </p>
     * 
     * <p>
     * This is the default constructor of DeductionCalculationRuleServiceImpl.
     * </p>
     */
    public DeductionValidationRuleServiceImpl() {
        // does nothing
    }

    /**
     * <p>
     * Executes the rules to the service periods to validate service periods.
     * </p>
     * 
     * @param request
     *            that contains the service periods to validate.
     * 
     * @return the response
     * 
     * @throws IllegalArgumentException if the request is null.
     * @throws RuleServiceException
     *             if any error occurs when executing the rule.
     */
    @Override
    public DeductionValidationResponse execute(DeductionValidationRequest request)
        throws RuleServiceException {

        // log the entrance
        String signature = CLASS_NAME + "#execute(DeductionValidationRequest request)";
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

            // Fire all rules
            ksession.fireAllRules();

            // populate the final result
            DeductionValidationResponse response = new DeductionValidationResponse();

            response.setServicePeriods(request.getServicePeriods());

            //ksession.dispose();

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

    /**
     * <p>
     * Check if all fields are initialized properly.
     * </p>
     * 
     * @throws OPMConfigurationException if any needed field is not
     * initialized properly.
     */
    @Override
    @PostConstruct
    public void checkConfiguration() {
        super.checkConfiguration();
        // Initialize knowledge agent
        KnowledgeAgent knowledgeAgent = KnowledgeAgentFactory.newKnowledgeAgent("deductionValidationKnowledgeAgent");
        knowledgeAgent.applyChangeSet(ResourceFactory.newClassPathResource("deduction-validation-change-set.xml"));
        this.setKnowledgeAgent(knowledgeAgent);
    }
}
