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

import gov.opm.scrd.ExtendedServicePeriodComparator;
import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Calculation;
import gov.opm.scrd.entities.application.CalculationResult;
import gov.opm.scrd.entities.application.CalculationResultItem;
import gov.opm.scrd.entities.application.Dedeposit;
import gov.opm.scrd.entities.application.DeductionCalculationRequest;
import gov.opm.scrd.entities.application.DeductionCalculationResponse;
import gov.opm.scrd.entities.application.DeductionValidationRequest;
import gov.opm.scrd.entities.application.ExtendedServicePeriod;
import gov.opm.scrd.entities.application.InterestCalculationRequest;
import gov.opm.scrd.entities.application.InterestCalculationResponse;
import gov.opm.scrd.entities.application.Redeposit;
import gov.opm.scrd.entities.application.ServicePeriod;
import gov.opm.scrd.entities.application.SummaryData;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.CalculationStatus;
import gov.opm.scrd.entities.lookup.DepositType;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.services.CalculationExecutionService;
import gov.opm.scrd.services.DeductionCalculationRuleService;
import gov.opm.scrd.services.DeductionValidationRuleService;
import gov.opm.scrd.services.InterestCalculationRuleService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * This class is the implementation of the CalculationService. It utilizes RuleService for running the calculations.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Add interestCalculatedToDate:Date parameter to runCalculation() method</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Rules Engine - Integrate with Web App Assembly v1.0 ):</em>
 * <ul>
 *   <li>Updated the runCalculation() method to set the entities names in the upper case format,
 *     since the drools functions/rules use the upper case format of the names
 *     (for example drools uses 'DEPOSIT' instead of 'Deposit' for the Deposit period type name)</li>
 *   <li>
 *     Updated the runCalculation() method to set the service periods fields interestAccrualDate and connerCase
 *   </li>
 *   <li>
 *   Updated the runCalculation() method to set the item 'periodType' using the periodType of the extended service 
 *   period instead of the 'periodType' of the first service period in the extended service period
 *   </li>
 *   <li>
 *     Updated runCalculation() method : updated the calculation formula of the total interest.
 *   </li>
 *   <li>Added the extendedServicePeriodComparator field</li>
 *   <li>Added deductionValidationRuleService reference and the call to it before calling deduction calculation 
 *       rule service </li>
 * </ul>
 * </p>
 * 
 * <p>
 * <strong>Thread Safety:</strong>This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, sparemax, liuliquan, Schpotsky
 * @version 1.2
 */
@Stateless
@Local(CalculationExecutionService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CalculationExecutionServiceImpl extends BaseService implements CalculationExecutionService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = CalculationExecutionServiceImpl.class.getName();

    /**
     * Represents the RuleService instance used to perform deduction validation. It is injected by Spring. It can not
     * be null after injected.
     * 
     * @since 1.2
     */
    @Autowired
    private DeductionValidationRuleService deductionValidationRuleService;
    
    /**
     * Represents the RuleService instance used to perform deduction calculations. It is injected by Spring. It can not
     * be null after injected.
     */
    @Autowired
    private DeductionCalculationRuleService deductionCalculationRuleService;

    /**
     * Represents the RuleService instance used to perform interest calculations. It is injected by Spring. It can not
     * be null after injected.
     */
    @Autowired
    private InterestCalculationRuleService interestCalculationRuleService;

    /**
     * Represents the redeposit period type ("ReDeposit (R)") for calculated items that are marked as redeposits. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private PeriodType redepositPeriodType;

    /**
     * Represents the dedeposit period type ("Deposit (D)") for calculated items that are marked as dedeposits. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private PeriodType dedepositPeriodType;

    /**
     * Represents the redeposit retirement type ("CSRS (1)") for calculated items that are marked as redeposits. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private RetirementType csrsRetirementType;

    /**
     * Represents the date after which the deposit type is changed. It should be set to October 1, 1982 00:00:00 001 am.
     * It is injected by Spring. It can not be null after injected.
     */
    @Autowired
    private Date depositTypeChangeDate;

    /**
     * Represents the success calculation status. It is injected by Spring. It can not be null after injected.
     */
    @Autowired
    private CalculationStatus successCalculationStatus;

    /**
     * Represents the comparator used to sort ExtendedServicePeriod list.
     * 
     * @since 1.2
     */
    private ExtendedServicePeriodComparator extendedServicePeriodComparator = new ExtendedServicePeriodComparator();
    
    /**
     * Creates an instance of CalculationExecutionServiceImpl.
     */
    public CalculationExecutionServiceImpl() {
        // Empty
    }

    /**
     * Runs the calculation on the account data.
     *
     * @param calculations
     *            the calculation data for performing calculation.
     * @param interestCalculatedToDate
     *            the target date to which the interest will be calculated.
     *
     * @return The CalculationResult entity holding the data about result, can not be null.
     *
     * @throws IllegalArgumentException
     *             if calculations is null, contain null elements, interestCalculatedToDate is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     *             
     * <p>
     * <em>Changes in 1.2 (OPM - Rules Engine - Integrate with Web App Assembly v1.0 ):</em>
     * <ul>
     *  <li> updated to set the entities names in the upper case format, since the drools functions/rules 
     *      use the upper case format of the names (for example drools uses 'DEPOSIT' instead of 'Deposit' 
     *      for the Deposit period type name)</li>
     *  <li>
     *   Updated the runCalculation() method to set the service periods fields interestAccrualDate and connerCase
     *   </li>
     *   <li>
     *   Updated the runCalculation() method to set the item 'periodType' using the periodType of the extended service period
     *   instead of the 'periodType' of the first service period in the extended service period
     * </li>
     *  <li>updated the calculation formula of the total interest.</li>
     * </ul>
     * </p>
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public CalculationResult runCalculation(List<Calculation> calculations,
            Date interestCalculatedToDate) throws OPMException {
        String signature = CLASS_NAME
            + "#runCalculation(List<Calculation> calculations, Date interestCalculatedToDate)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"calculations", "interestCalculatedToDate"},
            new Object[] {calculations, interestCalculatedToDate});
        

        Helper.checkList(logger, signature, calculations, "calculations");
        Helper.checkNull(logger, signature, interestCalculatedToDate, "interestCalculatedToDate");

        try {
            // Run deduction calculation rule
            DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();

            List<ServicePeriod> servicePeriods = new ArrayList<ServicePeriod>();
            deductionRequest.setServicePeriods(servicePeriods);

            for (Calculation calculation : calculations) {
                ServicePeriod period = new ServicePeriod();
                
                period.setAmount(calculation.getAmount());
                period.setAppointmentType(calculation.getAppointmentType());
                period.setBeginDate(calculation.getBeginDate());
                period.setEndDate(calculation.getEndDate());
                period.setPayType(calculation.getPayType());
                period.setPeriodType(calculation.getPeriodType());
                period.setRetirementType(calculation.getRetirementType());
                period.setServiceType(calculation.getServiceType());
                period.setValidationErrors(new HashMap<String,String>());
                
                /** Version 1.2 updates Start **/
                period.getRetirementType().setName(period.getRetirementType().getName().toUpperCase());
                period.getPeriodType().setName(period.getPeriodType().getName().toUpperCase());
                period.getAppointmentType().setName(period.getAppointmentType().getName().toUpperCase());
                period.getServiceType().setName(period.getServiceType().getName().toUpperCase());
                period.getPayType().setName(period.getPayType().getName().toUpperCase());
                period.setInterestAccrualDate(calculation.getInterestAccrualDate());
                period.setConnerCase(calculation.getConnerCase());
                /** Version 1.2 updates End **/

                servicePeriods.add(period);
            }
            
            /** Added in version 1.2 Call the deduction validation rule service before calling the deduction calculation rule service */
            DeductionValidationRequest validationRequest = new DeductionValidationRequest();
            validationRequest.setServicePeriods(servicePeriods);
            deductionValidationRuleService.execute(validationRequest);
            
            
            DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

            // Run interest calculation rule
            InterestCalculationRequest interestRequest = new InterestCalculationRequest();
            
            /* BRE Integration Update -- the extended service periods should be sorted by begin date. */
            Collections.sort(deductionResponse.getExtendedServicePeriods(), extendedServicePeriodComparator);
            
            interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
            interestRequest.setInterestCalculatedToDate(interestCalculatedToDate);

            InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);

            // Create CalculationResult
            CalculationResult result = new CalculationResult();

            List<CalculationResultItem> items = new ArrayList<CalculationResultItem>();
            result.setItems(items);

            List<Redeposit> redeposits = new ArrayList<Redeposit>();
            result.setRedeposits(redeposits);

            List<Dedeposit> dedeposits = new ArrayList<Dedeposit>();
            result.setDedeposits(dedeposits);
            result.setCalculationStatus(successCalculationStatus);

            // Calculate the data for summary
            BigDecimal totalPaymentsRequired = BigDecimal.ZERO;
            BigDecimal totalInitialInterest = BigDecimal.ZERO;
            BigDecimal totalPaymentsApplied = BigDecimal.ZERO;

            // Set CalculationResultItem elements
            for (ExtendedServicePeriod period : interestResponse.getExtendedServicePeriods()) {
                CalculationResultItem item = new CalculationResultItem();
                item.setStartDate(period.getBeginDate());
                item.setEndDate(period.getEndDate());
                item.setMidDate(period.getMidPoint());
                item.setEffectiveDate(period.getMidPoint());
                
                
                /* BRE Integration (version 1.2) Update changed to get the extended service period 'period type'*/
                item.setPeriodType(period.getPeriodType());
                
                /* Unclear: item.setRetirementType(period.getServicePeriods().get(0).getRetirementType()); */
                item.setDeductionAmount(period.getTotalDeduction());
                
                /* BRE Integration Update (version 1.2)- updated the calculation formula of the total interest*/
                item.setTotalInterest(period.getBalanceWithInterest().subtract(period.getTotalDeduction()) );
               
                /* BRE Integration Update (version 1.2)- updated the calculation formula of the payments applied*/
                item.setPaymentsApplied(period.getTotalDeduction().abs().add(period.getTotalEarnings().abs()));
                
                item.setBalance(period.getTotalDeduction().add(period.getBalanceWithInterest()));

                items.add(item);

                String periodType = item.getPeriodType().getName();
                // Calculate redeposits/dedeposits
                /* BRE Integration Update : used .equalsIngoreCase() since the period type 
                 * name was sent in upper case to the BRE*/
                if (periodType.equalsIgnoreCase(redepositPeriodType.getName())
                /* Unclear: && item.getRetirementType().equals(csrsRetirementType) */) {

                    // Add this item to redeposits
                    DepositType depositType = item.getEndDate().before(depositTypeChangeDate) ? DepositType.PRE_10_82
                        : DepositType.POST_10_82;
                    Redeposit redeposit = new Redeposit();
                    redeposit.setDepositType(depositType);
                    redeposit.setDeposit(item.getDeductionAmount());
                    redeposit.setInterest(item.getTotalInterest());
                    redeposit.setTotal(redeposit.getDeposit().add(redeposit.getInterest()));

                    redeposits.add(redeposit);

                    // Update summary
                    totalPaymentsRequired = totalPaymentsRequired.add(redeposit.getDeposit());
                    totalInitialInterest = totalInitialInterest.add(redeposit.getInterest());
                    totalPaymentsApplied = totalPaymentsApplied.add(item.getPaymentsApplied());
                    
                    /* BRE Integration Update : used .equalsIngoreCase() since the period type 
                     * name was sent in upper case to the BRE*/
                } else if (periodType.equalsIgnoreCase(dedepositPeriodType.getName())) { 

                    // Add this item to dedeposits
                    DepositType depositType = item.getEndDate().before(depositTypeChangeDate) ? DepositType.PRE_10_82
                        : DepositType.POST_10_82;
                    Dedeposit dedeposit = new Dedeposit();
                    dedeposit.setDepositType(depositType);
                    dedeposit.setDeposit(item.getDeductionAmount());
                    dedeposit.setInterest(item.getTotalInterest());
                    dedeposit.setTotal(dedeposit.getDeposit().add(dedeposit.getInterest()));

                    dedeposits.add(dedeposit);

                    // Update summary
                    totalPaymentsRequired = totalPaymentsRequired.subtract(dedeposit.getDeposit());
                    totalInitialInterest = totalInitialInterest.subtract(dedeposit.getInterest());
                    totalPaymentsApplied = totalPaymentsApplied.subtract(item.getPaymentsApplied());
                }
            }

            SummaryData summaryData = new SummaryData();
            summaryData.setTotalPaymentsRequired(totalPaymentsRequired);
            summaryData.setTotalInitialInterest(totalInitialInterest);
            summaryData.setTotalPaymentsApplied(totalPaymentsApplied);
            summaryData.setTotalBalance(totalPaymentsRequired.add(totalInitialInterest).subtract(totalPaymentsApplied));
            result.setSummary(summaryData);

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (entityManager, deductionCalculationRuleService,
     *             interestCalculationRuleService, redepositPeriodType, dedepositPeriodType, csrsRetirementType,
     *             depositTypeChangeDate or successCalculationStatus is null).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(deductionCalculationRuleService == null, "'deductionCalculationRuleService' can't be null.");
        Helper.checkState(interestCalculationRuleService == null, "'interestCalculationRuleService' can't be null.");
        Helper.checkState(redepositPeriodType == null, "'redepositPeriodType' can't be null.");
        Helper.checkState(dedepositPeriodType == null, "'dedepositPeriodType' can't be null.");
        Helper.checkState(csrsRetirementType == null, "'csrsRetirementType' can't be null.");
        Helper.checkState(depositTypeChangeDate == null, "'depositTypeChangeDate' can't be null.");
        Helper.checkState(successCalculationStatus == null, "'successCalculationStatus' can't be null.");
    }
}
