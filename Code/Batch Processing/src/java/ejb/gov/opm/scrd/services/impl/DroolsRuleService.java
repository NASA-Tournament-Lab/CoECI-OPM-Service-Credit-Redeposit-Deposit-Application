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

import gov.opm.scrd.services.OPMConfigurationException;

import javax.annotation.PostConstruct;

import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.jboss.logging.Logger;

/**
 * <p>
 * This class is the base class for RuleService implementations that use Drools as the
 * BRMS.
 * </p>
 *
 * <p>
 * <b>Thread Safety</b> This class is effectively thread safe (injected configurations
 * are not considered as thread safety factor). Drools is used in
 * thread safe manner in this module, because each rule service execution call
 * will result a dedicated Drools KnowledgeSession and the session will only be
 * used in single thread.
 * </p>
 *
 * @author albertwang, TCSASSEMBLER
 * @version 1.0
 * @since OPM Rules Engine Models Exceptions and Interest Calculation v1.0 Assembly
 */
public abstract class DroolsRuleService {

    /**
     * <p>
     * Represents the logger used for logging.
     * </p>
     * <p>
     * It will be initialized in constructor and will not change afterwards.
     * </p>
     * <p>
     * It can be accessed via protected getter.
     * </p>
     */
    private final Logger logger;

    /**
     * <p>
     * Represents the KnowledgeAgent used to access the knowledge base.
     * </p>
     * <p>
     * It is required and injected by Spring, it should not be null.
     * </p>
     */
    private KnowledgeAgent knowledgeAgent;

    /**
     * <p>
     * Represents the log path and name used for knowledge execution.
     * </p>
     * <p>
     * It is required and injected by Spring, it should not be null or empty.
     * </p>
     */
    private String knowledgeLogFileName;

    /**
     * <p>
     * Constructs the class of DroolsRuleService.
     * </p>
     *
     * <p>
     * In this constructor, it initialized the logger.
     * </p>
     */
    protected DroolsRuleService() {
        logger = Logger.getLogger(this.getClass());
    }

    /**
     * <p>
     * Check if all required fields are initialized properly.
     * </p>
     * <p>
     * In this class, the required fields are:
     * knowledgeAgent and knowledgeLogFileName.
     * </p>
     *
     * @throws OPMConfigurationException if any required field is not
     * initialized properly.
     */
    @PostConstruct
    public void checkConfiguration() {
        if (knowledgeAgent == null) {
            throw new OPMConfigurationException("The knowledgeAgent is not properly injected.");
        }
        if (knowledgeLogFileName == null || knowledgeLogFileName.trim().length() == 0) {
            throw new OPMConfigurationException("The knowledgeLogFileName is not properly injected.");
        }
    }

    /**
     * <p>
     * Gets the logger for logging.
     * </p>
     * @return the logger for logging.
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * <p>
     * Gets the KnowledgeBase cached in KnowledgeAgent.
     * </p>
     *
     * @return the KnowledgeBase cached in KnowledgeAgent
     */
    protected KnowledgeBase getKnowledgeBase() {
        return knowledgeAgent.getKnowledgeBase();
    }

    /**
     * <p>
     * Gets the log path and name used for knowledge execution.
     * </p>
     * @return the log path and name used for knowledge execution.
     */
    protected String getKnowledgeLogFileName() {
        return knowledgeLogFileName;
    }

    /**
     * <p>
     * Sets the KnowledgeAgent used to access the knowledge base.
     * </p>
     * @param knowledgeAgent the KnowledgeAgent used to access the knowledge base.
     */
    public void setKnowledgeAgent(KnowledgeAgent knowledgeAgent) {
        this.knowledgeAgent = knowledgeAgent;
    }

    /**
     * <p>
     * Sets the log path and name used for knowledge execution.
     * </p>
     * @param knowledgeLogFileName the log path and name used for knowledge execution.
     */
    public void setKnowledgeLogFileName(String knowledgeLogFileName) {
        this.knowledgeLogFileName = knowledgeLogFileName;
    }
}
