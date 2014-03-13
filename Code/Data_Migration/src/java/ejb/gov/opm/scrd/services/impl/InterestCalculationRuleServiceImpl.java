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
import gov.opm.scrd.entities.application.InterestRate;
import gov.opm.scrd.services.InterestCalculationRuleService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.RuleServiceException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentConfiguration;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.ResourceFactory;
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
 * <p>
 * Version 1.1 - Moves some common code to a helper method.
 * </p>
 * <p>
 * version 1.2 - OPM - Rules Engine - Scenarios Conversion 2 - Interest Update Assembly
 * <ul>
 * <li>Added code to start the 'Interest' ruleflow</li>
 * </ul>
 * </p>
 * 
 * @author albertwang, TCSASSEMBLER
 * @version 1.2
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
     * The EntityManager used to access database.
     */
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * The decision table template for csrs_interest.xls.
     */
    private DecisionTableTemplate csrsInterestTemplate;

    /**
     * The decision table template for csrs_peace_corps_interest.xls.
     */
    private DecisionTableTemplate csrsPeaceCorpsInterestTemplate;
    
    /**
     * The decision table template for csrs_redeposit_interest.xls.
     */
    private DecisionTableTemplate csrsRedepositInterestTemplate;
    
    /**
     * The decision table template for fers_interest.xls.
     */
    private DecisionTableTemplate fersInterestTemplate;
    
    /**
     * The decision table template for fers_peace_corps_interest.xls.
     */
    private DecisionTableTemplate fersPeaceCorpsInterestTemplate;

    /**
     * The decision table template for fers_redeposit_interest.xls.
     */
    private DecisionTableTemplate fersRedepositInterestTemplate;

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

            // start ruleflow process
            ksession.startProcess("Interest");

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

    /**
     * Setter of corresponding field.
     * @param csrsInterestTemplate the value to set
     */
    public void setCsrsInterestTemplate(DecisionTableTemplate csrsInterestTemplate) {
        this.csrsInterestTemplate = csrsInterestTemplate;
    }

    /**
     * Setter of corresponding field.
     * @param csrsPeaceCorpsInterestTemplate the value to set
     */
    public void setCsrsPeaceCorpsInterestTemplate(DecisionTableTemplate csrsPeaceCorpsInterestTemplate) {
        this.csrsPeaceCorpsInterestTemplate = csrsPeaceCorpsInterestTemplate;
    }

    /**
     * Setter of corresponding field.
     * @param csrsRedepositInterestTemplate the value to set
     */
    public void setCsrsRedepositInterestTemplate(DecisionTableTemplate csrsRedepositInterestTemplate) {
        this.csrsRedepositInterestTemplate = csrsRedepositInterestTemplate;
    }

    /**
     * Setter of corresponding field.
     * @param fersInterestTemplate the value to set
     */
    public void setFersInterestTemplate(DecisionTableTemplate fersInterestTemplate) {
        this.fersInterestTemplate = fersInterestTemplate;
    }
    
    /**
     * Setter of corresponding field.
     * @param fersPeaceCorpsInterestTemplate the value to set
     */
    public void setFersPeaceCorpsInterestTemplate(DecisionTableTemplate fersPeaceCorpsInterestTemplate) {
        this.fersPeaceCorpsInterestTemplate = fersPeaceCorpsInterestTemplate;
    }

    /**
     * Setter of corresponding field.
     * @param fersRedepositInterestTemplate the value to set
     */
    public void setFersRedepositInterestTemplate(DecisionTableTemplate fersRedepositInterestTemplate) {
        this.fersRedepositInterestTemplate = fersRedepositInterestTemplate;
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
        if (this.entityManager == null) {
            throw new OPMConfigurationException("entityManager cannot be null.");
        }
        if (this.csrsInterestTemplate == null) {
            throw new OPMConfigurationException("csrsInterestTemplate cannot be null.");
        }
        if (this.csrsPeaceCorpsInterestTemplate == null) {
            throw new OPMConfigurationException("csrsPeaceCorpsInterestTemplate cannot be null.");
        }
        if (this.csrsRedepositInterestTemplate == null) {
            throw new OPMConfigurationException("csrsRedepositInterestTemplate cannot be null.");
        }
        if (this.fersInterestTemplate == null) {
            throw new OPMConfigurationException("fersInterestTemplate cannot be null.");
        }
        if (this.fersPeaceCorpsInterestTemplate == null) {
            throw new OPMConfigurationException("fersPeaceCorpsInterestTemplate cannot be null.");
        }
        if (this.fersRedepositInterestTemplate == null) {
            throw new OPMConfigurationException("fersRedepositInterestTemplate cannot be null.");
        }
        try {
            // Generate interest tables
            this.generateInterestTables();

            // Initialize knowledge agent
            KnowledgeAgentConfiguration config = KnowledgeAgentFactory.newKnowledgeAgentConfiguration();
            // We have to set the newInstance property to false so that the knowledge base changes can be reflected
            config.setProperty("drools.agent.newInstance", "false"); 
            KnowledgeAgent knowledgeAgent = KnowledgeAgentFactory.newKnowledgeAgent("interestKnowledgeAgent", config);
            
            // Substitute the classpath resource location with the file system resource location
            String changeSet = ServiceHelper.inputStreamToString(
                    ResourceFactory.newClassPathResource("interest-change-set.xml").getInputStream());
            changeSet = changeSet.replace("classpath:rules/csrs_interest.xls",
                    "file:" + this.csrsInterestTemplate.getDecisionTableFile());
            changeSet = changeSet.replace("classpath:rules/csrs_peacecorps_interest.xls",
                    "file:" + this.csrsPeaceCorpsInterestTemplate.getDecisionTableFile());
            changeSet = changeSet.replace("classpath:rules/csrs_redeposit_interest.xls",
                    "file:" + this.csrsRedepositInterestTemplate.getDecisionTableFile());
            changeSet = changeSet.replace("classpath:rules/fers_interest.xls",
                    "file:" + this.fersInterestTemplate.getDecisionTableFile());
            changeSet = changeSet.replace("classpath:rules/fers_peacecorps_interest.xls",
                    "file:" + this.fersPeaceCorpsInterestTemplate.getDecisionTableFile());
            changeSet = changeSet.replace("classpath:rules/fers_redeposit_interest.xls",
                    "file:" + this.fersRedepositInterestTemplate.getDecisionTableFile());
            knowledgeAgent.applyChangeSet(ResourceFactory.newByteArrayResource(changeSet.getBytes("utf-8")));
            this.setKnowledgeAgent(knowledgeAgent);
        } catch (IOException e) {
            throw new OPMConfigurationException("Failed to initialize KnowledgeAgent.");
        }
    }
    
    
    /**
     * Generate interest tables.
     * @throws OPMConfigurationException
     */
    private void generateInterestTables() throws OPMConfigurationException {
        OutputStream csrsInterestTemplateOutput = null;
        OutputStream csrsPeaceCorpsInterestTemplateOutput = null;
        OutputStream csrsRedepositInterestTemplateOutput = null;
        OutputStream fersInterestTemplateOutput = null;
        OutputStream fersPeaceCorpsInterestTemplateOutput = null;
        OutputStream fersRedepositInterestTemplateOutput = null;
        try {
            // Query interest rates
            TypedQuery<InterestRate> query = entityManager.createQuery(
                "SELECT r FROM InterestRate r WHERE r.deleted = false ORDER BY r.interestYear",
                InterestRate.class);
            List<InterestRate> interestRates = query.getResultList();
            
            // csrs_interest.xls
            int currentRow = this.csrsInterestTemplate.getStartCellRow();
            int startColumn = this.csrsInterestTemplate.getStartCellColumn();
            
            Workbook workbook = WorkbookFactory.create(new FileInputStream(this.csrsInterestTemplate.getTemplateFile()));
            Sheet sheet = workbook.getSheetAt(0); 
            
            for (InterestRate rate : interestRates) {
                Row row = sheet.getRow(currentRow++);
                if (row == null) {
                    row = sheet.createRow(currentRow - 1);
                }
                // Year column
                Cell cell = row.getCell(startColumn);
                if (cell == null) {
                    cell = row.createCell(startColumn);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(String.valueOf(rate.getInterestYear()));
                // Rate column
                cell = row.getCell(startColumn + 1);
                if (cell == null) {
                    cell = row.createCell(startColumn + 1);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(rate.getInterestRate().toString());
            }
            csrsInterestTemplateOutput = new FileOutputStream(this.csrsInterestTemplate.getDecisionTableFile());
            workbook.write(csrsInterestTemplateOutput);
            
            // csrs_peacecorps_interest.xls
            currentRow = this.csrsPeaceCorpsInterestTemplate.getStartCellRow();
            startColumn = this.csrsPeaceCorpsInterestTemplate.getStartCellColumn();
            
            workbook = WorkbookFactory.create(new FileInputStream(this.csrsPeaceCorpsInterestTemplate.getTemplateFile()));
            sheet = workbook.getSheetAt(0);
            
            for (int i = 0; i < interestRates.size() - 1; i++) {
                InterestRate rate1 = interestRates.get(i);
                if (rate1.getInterestYear() >= 1995) {
                    InterestRate rate2 = interestRates.get(i + 1);
                    Row row = sheet.getRow(currentRow++);
                    if (row == null) {
                        row = sheet.createRow(currentRow - 1);
                    }
                    // Year column
                    Cell cell = row.getCell(startColumn);
                    if (cell == null) {
                        cell = row.createCell(startColumn);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(String.valueOf(rate1.getInterestYear()) + "," + String.valueOf(rate2.getInterestYear()));
                    // Rate column
                    cell = row.getCell(startColumn + 1);
                    if (cell == null) {
                        cell = row.createCell(startColumn + 1);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(rate1.getInterestRate().toString() + "," + rate2.getInterestRate().toString());
                }
            }
            csrsPeaceCorpsInterestTemplateOutput = new FileOutputStream(this.csrsPeaceCorpsInterestTemplate.getDecisionTableFile());
            workbook.write(csrsPeaceCorpsInterestTemplateOutput);
            
            // csrs_redeposit_interest.xls
            currentRow = this.csrsRedepositInterestTemplate.getStartCellRow();
            startColumn = this.csrsRedepositInterestTemplate.getStartCellColumn();
            
            workbook = WorkbookFactory.create(new FileInputStream(this.csrsRedepositInterestTemplate.getTemplateFile()));
            sheet = workbook.getSheetAt(0); 
            
            for (InterestRate rate : interestRates) {
                Row row = sheet.getRow(currentRow++);
                if (row == null) {
                    row = sheet.createRow(currentRow - 1);
                }
                // Year column
                Cell cell = row.getCell(startColumn);
                if (cell == null) {
                    cell = row.createCell(startColumn);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(String.valueOf(rate.getInterestYear()));
                // Rate column
                cell = row.getCell(startColumn + 1);
                if (cell == null) {
                    cell = row.createCell(startColumn + 1);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(rate.getInterestRate().toString());
            }
            csrsRedepositInterestTemplateOutput = new FileOutputStream(this.csrsRedepositInterestTemplate.getDecisionTableFile());
            workbook.write(csrsRedepositInterestTemplateOutput);
            
            // fers_interest.xls
            currentRow = this.fersInterestTemplate.getStartCellRow();
            startColumn = this.fersInterestTemplate.getStartCellColumn();
            
            workbook = WorkbookFactory.create(new FileInputStream(this.fersInterestTemplate.getTemplateFile()));
            sheet = workbook.getSheetAt(0); 
            
            for (InterestRate rate : interestRates) {
                Row row = sheet.getRow(currentRow++);
                if (row == null) {
                    row = sheet.createRow(currentRow - 1);
                }
                // Year column
                Cell cell = row.getCell(startColumn);
                if (cell == null) {
                    cell = row.createCell(startColumn);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(String.valueOf(rate.getInterestYear()));
                // Rate column
                cell = row.getCell(startColumn + 1);
                if (cell == null) {
                    cell = row.createCell(startColumn + 1);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(rate.getInterestRate().toString());
            }
            fersInterestTemplateOutput = new FileOutputStream(this.fersInterestTemplate.getDecisionTableFile());
            workbook.write(fersInterestTemplateOutput);
            
            // fers_peacecorps_interest.xls
            currentRow = this.fersPeaceCorpsInterestTemplate.getStartCellRow();
            startColumn = this.fersPeaceCorpsInterestTemplate.getStartCellColumn();
            
            workbook = WorkbookFactory.create(new FileInputStream(this.fersPeaceCorpsInterestTemplate.getTemplateFile()));
            sheet = workbook.getSheetAt(0);
            
            for (int i = 0; i < interestRates.size() - 1; i++) {
                InterestRate rate1 = interestRates.get(i);
                if (rate1.getInterestYear() >= 1995) {
                    InterestRate rate2 = interestRates.get(i + 1);
                    Row row = sheet.getRow(currentRow++);
                    if (row == null) {
                        row = sheet.createRow(currentRow - 1);
                    }
                    // Year column
                    Cell cell = row.getCell(startColumn);
                    if (cell == null) {
                        cell = row.createCell(startColumn);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(String.valueOf(rate1.getInterestYear()) + "," + String.valueOf(rate2.getInterestYear()));
                    // Rate column
                    cell = row.getCell(startColumn + 1);
                    if (cell == null) {
                        cell = row.createCell(startColumn + 1);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(rate1.getInterestRate().toString() + "," + rate2.getInterestRate().toString());
                }
            }
            fersPeaceCorpsInterestTemplateOutput = new FileOutputStream(this.fersPeaceCorpsInterestTemplate.getDecisionTableFile());
            workbook.write(fersPeaceCorpsInterestTemplateOutput);
            
            // fers_redeposit_interest.xls
            currentRow = this.fersRedepositInterestTemplate.getStartCellRow();
            startColumn = this.fersRedepositInterestTemplate.getStartCellColumn();
            
            workbook = WorkbookFactory.create(new FileInputStream(this.fersRedepositInterestTemplate.getTemplateFile()));
            sheet = workbook.getSheetAt(0); 
            
            for (InterestRate rate : interestRates) {
                Row row = sheet.getRow(currentRow++);
                if (row == null) {
                    row = sheet.createRow(currentRow - 1);
                }
                // Year column
                Cell cell = row.getCell(startColumn);
                if (cell == null) {
                    cell = row.createCell(startColumn);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(String.valueOf(rate.getInterestYear()));
                // Rate column
                cell = row.getCell(startColumn + 1);
                if (cell == null) {
                    cell = row.createCell(startColumn + 1);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(rate.getInterestRate().toString());
            }
            fersRedepositInterestTemplateOutput = new FileOutputStream(this.fersRedepositInterestTemplate.getDecisionTableFile());
            workbook.write(fersRedepositInterestTemplateOutput);
        } catch (Exception ex) {
            throw new OPMConfigurationException("Failed to generate interest rates decision table.", ex);
        } finally {
            if (csrsInterestTemplateOutput != null) {
                try {
                    csrsInterestTemplateOutput.close();
                } catch (IOException e) {
                }
            }
            if (csrsPeaceCorpsInterestTemplateOutput != null) {
                try {
                    csrsPeaceCorpsInterestTemplateOutput.close();
                } catch (IOException e) {
                }
            }
            if (csrsRedepositInterestTemplateOutput != null) {
                try {
                    csrsRedepositInterestTemplateOutput.close();
                } catch (IOException e) {
                }
            }
            if (fersInterestTemplateOutput != null) {
                try {
                    fersInterestTemplateOutput.close();
                } catch (IOException e) {
                }
            }
            if (fersPeaceCorpsInterestTemplateOutput != null) {
                try {
                    fersInterestTemplateOutput.close();
                } catch (IOException e) {
                }
            }
            if (fersRedepositInterestTemplateOutput != null) {
                try {
                    fersInterestTemplateOutput.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
