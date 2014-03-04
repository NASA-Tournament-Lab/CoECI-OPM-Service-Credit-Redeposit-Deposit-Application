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
import gov.opm.scrd.entities.application.DeductionRate;
import gov.opm.scrd.entities.application.ExtendedServicePeriod;
import gov.opm.scrd.entities.application.ServicePeriod;
import gov.opm.scrd.services.DeductionCalculationRuleService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.RuleServiceException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
 *              &lt;property name=&quot;knowledgeAgent&quot; ref=&quot;deductionKnowledgeAgent&quot; /&gt;
 *              &lt;property name=&quot;servicePeriodSplitDates&quot;&gt;
                    &lt;list&gt;
                        &lt;value&gt;10-01-1982&lt;/value&gt;
                    &lt;/list&gt;
                &lt;/property&gt;
 * &lt;/bean&gt;
 *
 * &lt;bean class=&quot;org.springframework.beans.factory.config.CustomEditorConfigurer&quot;&gt;
 *      &lt;property name=&quot;customEditors&quot;&gt;
 *          &lt;map&gt;
 *              &lt;entry key=&quot;java.util.Date&quot;&gt; &lt;ref local=&quot;customDateEditor&quot; /&gt;
 *              &lt;/entry&gt;
 *          &lt;/map&gt;
 *      &lt;/property&gt;
 * &lt;/bean&gt;
 *
 * &lt;bean id=&quot;customDateEditor&quot; class=&quot;org.springframework.beans
 *      .propertyeditors.CustomDateEditor&quot;&gt;
 *      &lt;constructor-arg&gt;
 *          &lt;bean class=&quot;java.text.SimpleDateFormat&quot;&gt;
 *              &lt;constructor-arg value=&quot;MM-dd-yyyy&quot; /&gt;
 *          &lt;/bean&gt;
 *      &lt;/constructor-arg&gt;
 *      &lt;constructor-arg value=&quot;true&quot; /&gt;
 * &lt;/bean&gt;
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
 *                 &quot;05/30/1978,02/19/1983,CSRS,DEPOSIT,TEMPORARY,GS,5861.08,EARNINGS,null,false&quot;);
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
 * <p>
 * version 1.1 - OPM - Rules Engine - Scenarios Conversion 2 - Deduction Update Assembly
 * <ul>
 * <li>Added code to start the 'Deduction' ruleflow</li>
 * <li>Added configurable service period split dates</li>
 * </ul>
 * </p>
 *
 * @author albertwang, TCSASSEMBLER
 * @version 1.1
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
     * Represents the global name of servicePeriodSplitDates in drools.
     * </p>
     * @since 1.1 OPM - Rules Engine - Scenarios Conversion 2 - Deduction Update Assembly
     */
    private static final String SERVICE_PERIOD_SPLIT_DATES = "servicePeriodSplitDates";

    /**
     * <p>
     * Represents the name of this class for logging.
     * </p>
     */
    private static final String CLASS_NAME = DeductionCalculationRuleServiceImpl.class.getName();

    /**
     * <p>
     * Represents the service period split dates. It will be be used as a global constant for the deduction
     * rules, its main purpose is to prevent the rules from merging service periods at split dates.
     * Currently only one date is needed : 1982-10-01
     * </p>
     * <p>
     * It is optional and injected by Spring. It can be null or empty list, but items in the list cannot be null.
     * </p>
     * @since 1.1 OPM - Rules Engine - Scenarios Conversion 2 - Deduction Update Assembly
     */
    private List<Date> servicePeriodSplitDates;
    
    /**
     * The decision table template for deduction_table.xls.
     */
    private DecisionTableTemplate deductionTableTemplate;
    
    /**
     * The EntityManager used to access database.
     */
    @PersistenceContext
    private EntityManager entityManager;
    
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
            ksession.setGlobal(SERVICE_PERIOD_SPLIT_DATES,
                    servicePeriodSplitDates == null ? Collections.EMPTY_LIST : servicePeriodSplitDates);

            // Start deduction process
            ksession.startProcess("Deduction");

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

    /**
     * <p>
     * Check if all fields are initialized properly.
     * </p>
     * <p>
     * In this class, this method will check if the servicePeriodSplitDates contains null.
     * </p>
     * 
     * @throws OPMConfigurationException if any needed field is not
     * initialized properly.
     * @since 1.1 OPM - Rules Engine - Scenarios Conversion 2 - Deduction Update Assembly
     */
    @Override
    @PostConstruct
    public void checkConfiguration() {
        super.checkConfiguration();
        if (this.entityManager == null) {
            throw new OPMConfigurationException("entityManager cannot be null.");
        }
        if (servicePeriodSplitDates != null) {
            for (Date date : servicePeriodSplitDates) {
                if (date == null) {
                    throw new OPMConfigurationException("Items in servicePeriodSplitDates list cannot be null.");
                }
            }
        }
        if (this.deductionTableTemplate == null) {
            throw new OPMConfigurationException("deductionTableTemplate cannot be null.");
        }
        try {
            // Generate deduction table
            this.generateDeductionTable();

            // Initialize knowledge agent
            KnowledgeAgentConfiguration config = KnowledgeAgentFactory.newKnowledgeAgentConfiguration();
            // We have to set the newInstance property to false so that the knowledge base changes can be reflected
            config.setProperty("drools.agent.newInstance", "false"); 
            KnowledgeAgent knowledgeAgent = KnowledgeAgentFactory.newKnowledgeAgent("deductionKnowledgeAgent", config);
            
            // Substitute the classpath resource location with the file system resource location
            String changeSet = ServiceHelper.inputStreamToString(
                    ResourceFactory.newClassPathResource("deduction-change-set.xml").getInputStream());
            changeSet = changeSet.replace("classpath:rules/deduction_table.xls",
                    "file:" + this.deductionTableTemplate.getDecisionTableFile());
            knowledgeAgent.applyChangeSet(ResourceFactory.newByteArrayResource(changeSet.getBytes("utf-8")));
            this.setKnowledgeAgent(knowledgeAgent);
        } catch (IOException e) {
            throw new OPMConfigurationException("Failed to initialize KnowledgeAgent.");
        }
    }

    /**
     * Setter of corresponding field.
     * @param servicePeriodSplitDates the value to set
     */
    public void setServicePeriodSplitDates(List<Date> servicePeriodSplitDates) {
        this.servicePeriodSplitDates = servicePeriodSplitDates;
    }
    
    /**
     * Setter of corresponding field.
     * @param deductionTableTemplate the value to set
     */
    public void setDeductionTableTemplate(DecisionTableTemplate deductionTableTemplate) {
        this.deductionTableTemplate = deductionTableTemplate;
    }

    /**
     * Generate deduction_table.xls based on deduction rates queried from database.
     */
    private void generateDeductionTable() throws OPMConfigurationException {
        OutputStream templateOutput = null;
        try {
            int currentRow = this.deductionTableTemplate.getStartCellRow();
            int startColumn = this.deductionTableTemplate.getStartCellColumn();
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            
            Workbook workbook = WorkbookFactory.create(new FileInputStream(this.deductionTableTemplate.getTemplateFile()));
            Sheet sheet = workbook.getSheetAt(0); 
            
            // Query deduction rates
            TypedQuery<DeductionRate> query = entityManager.createQuery(
                "SELECT r FROM DeductionRate r JOIN FETCH r.retirementType WHERE r.deleted = false ORDER BY r.id",
                DeductionRate.class);
            
            for (DeductionRate rate : query.getResultList()) {
                Row row = sheet.getRow(currentRow++);
                if (row == null) {
                    row = sheet.createRow(currentRow - 1);
                }
                // Service Type column
                Cell cell = row.getCell(startColumn);
                if (cell == null) {
                    cell = row.createCell(startColumn);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(rate.getServiceType());
                // Retirement Type column
                cell = row.getCell(startColumn + 1);
                if (cell == null) {
                    cell = row.createCell(startColumn + 1);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(rate.getRetirementType().getName());
                // Date range column
                cell = row.getCell(startColumn + 2);
                if (cell == null) {
                    cell = row.createCell(startColumn + 2);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(df.format(rate.getStartDate()) + "," + df.format(rate.getEndDate()));
                // Date range column
                cell = row.getCell(startColumn + 3);
                if (cell == null) {
                    cell = row.createCell(startColumn + 3);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(df.format(rate.getStartDate()) + ","
                    + df.format(rate.getEndDate()) + "," + rate.getRate());
            }
            templateOutput = new FileOutputStream(this.deductionTableTemplate.getDecisionTableFile());
            workbook.write(templateOutput);
        } catch (Exception ex) {
            throw new OPMConfigurationException("Failed to generate deduction rates decision table.", ex);
        } finally {
            if (templateOutput != null) {
                try {
                    templateOutput.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
