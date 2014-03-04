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
import gov.opm.scrd.entities.application.Calculation;
import gov.opm.scrd.entities.application.CalculationResult;
import gov.opm.scrd.entities.application.CalculationResultItem;
import gov.opm.scrd.entities.application.Dedeposit;
import gov.opm.scrd.entities.application.DeductionCalculationRequest;
import gov.opm.scrd.entities.application.DeductionCalculationResponse;
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
import gov.opm.scrd.services.InterestCalculationRuleService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.joda.time.DateTime;
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
 * <em>Changes in 1.2 (OPM - Release I Assembly 1.0):</em>
 * <ul>
 * <li>Remove member field depositTypeChangeDate</li>
 * <li>Add member field fersRetirementType</li>
 * <li>Remove interestCalculatedToDate:Date parameter to runCalculation() method</li>
 * <li>Change implementaton of runCalculation() method</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, sparemax, liuliquan, bannie2492
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
     * Represents the redeposit period type ("ReDeposit") for calculated items that are marked as redeposits. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private PeriodType redepositPeriodType;
    /**
     * Represents the dedeposit period type ("Deposit") for calculated items that are marked as dedeposits. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private PeriodType dedepositPeriodType;
    /**
     * Represents the redeposit retirement type ("CSRS") for calculated items that are marked as redeposits. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private RetirementType csrsRetirementType;
    /**
     * Represents the redeposit retirement type ("FERS") for calculated items that are marked as redeposits. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private RetirementType fersRetirementType;
    /**
     * Represents the success calculation status. It is injected by Spring. It can not be null after injected.
     */
    @Autowired
    private CalculationStatus successCalculationStatus;

    /**
     * Creates an instance of CalculationExecutionServiceImpl.
     */
    public CalculationExecutionServiceImpl() {
        // Empty
    }

    /**
     * Runs the calculation on the account data.
     *
     * @param calculations the calculation data for performing calculation.
     *
     * @return The CalculationResult entity holding the data about result, can not be null.
     *
     * @throws IllegalArgumentException if calculations is null, contain null elements, interestCalculatedToDate is null
     * @throws OPMException if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public CalculationResult runCalculation(List<Calculation> calculations) throws OPMException {
        String signature = CLASS_NAME
                + "#runCalculation(List<Calculation> calculations)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[]{"calculations"},
                new Object[]{calculations});

        Helper.checkList(logger, signature, calculations, "calculations");

        try {
            // Run deduction calculation rule
            DeductionCalculationResponse deductionResponse = runDeduction(calculations);

            // Run interest calculation rule
            InterestCalculationResponse interestResponse = calculateInterest(deductionResponse);

            // Create CalculationResult
            CalculationResult result = new CalculationResult();

            List<CalculationResultItem> items = new ArrayList<CalculationResultItem>();
            result.setItems(items);

            // Initialize deposits and dedeposits
            List<Redeposit> redeposits = new ArrayList<Redeposit>();
            Redeposit fersRedeposit = createRedeposit(DepositType.FERS_REDEPOSIT);
            Redeposit csrsPost391Redeposit = createRedeposit(DepositType.CSRS_POST_3_91_REDEPOSIT);
            Redeposit csrsPost82Pre91Redeposit = createRedeposit(DepositType.CSRS_POST_82_PRE_91_REDEPOSIT);
            Redeposit csrsPre1082Redeposit = createRedeposit(DepositType.CSRS_PRE_10_82_REDEPOSIT);

            List<Dedeposit> dedeposits = new ArrayList<Dedeposit>();
            Dedeposit fersDedeposit = createDedeposit(DepositType.FERS_DEPOSIT);
            Dedeposit csrsPost1082Dedeposit = createDedeposit(DepositType.CSRS_POST_10_82_DEPOSIT);
            Dedeposit csrsPre1082Dedeposit = createDedeposit(DepositType.CSRS_PRE_10_82_DEPOSIT);
            Dedeposit csrsPeaceCorpsDedeposit = createDedeposit(DepositType.CSRS_PEACE_CORPS);
            Dedeposit fersPeaceCorpsDedeposit = createDedeposit(DepositType.FERS_PEACE_CORPS);

            result.setCalculationStatus(successCalculationStatus);

            // Calculate the data for summary
            BigDecimal totalPaymentsRequired = BigDecimal.ZERO;
            BigDecimal totalInitialInterest = BigDecimal.ZERO;
            BigDecimal totalPaymentsApplied = BigDecimal.ZERO;

            // Split dates
            Date splitDate1082 = new DateTime(1982, 10, 1, 0, 0).toDate();
            Date splitDate391 = new DateTime(1991, 3, 1, 0, 0).toDate();

            // Set CalculationResultItem elements
            for (ExtendedServicePeriod period : interestResponse.getExtendedServicePeriods()) {
                CalculationResultItem item = new CalculationResultItem();
                item.setStartDate(period.getBeginDate());
                item.setEndDate(period.getEndDate());
                item.setMidDate(period.getMidPoint());
                item.setRefundDate(period.getRefundDate());
                item.setPeriodType(period.getServicePeriods().get(0).getPeriodType());
                item.setRetirementType(period.getRetirementType());
                item.setDeductionAmount(period.getTotalDeduction());
                item.setTotalInterest(period.getBalanceWithInterest());
                item.setPaymentsApplied(BigDecimal.ZERO);
                item.setBalance(period.getTotalDeduction().add(period.getBalanceWithInterest()));

                items.add(item);

                String periodType = item.getPeriodType().getName();
                String serviceType = period.getServiceType().getName();

                // Calculate redeposits/dedeposits
                if (periodType.equals(dedepositPeriodType.getName())
                        && item.getRetirementType().getName().equals(fersRetirementType.getName())) {
                    fersDedeposit = modifyDedeposit(fersDedeposit, item);
                    item.setServiceCategory(DepositType.FERS_DEPOSIT);
                } else if (periodType.equals(redepositPeriodType.getName())
                        && item.getRetirementType().getName().equals(fersRetirementType.getName())) {
                    fersRedeposit = modifyRedeposit(fersRedeposit, item);
                    item.setServiceCategory(DepositType.FERS_REDEPOSIT);
                } else if (periodType.equals(redepositPeriodType.getName())
                        && item.getRetirementType().getName().equals(csrsRetirementType.getName())) {
                    if (item.getEndDate().before(splitDate1082)) {
                        csrsPre1082Redeposit = modifyRedeposit(csrsPre1082Redeposit, item);
                        item.setServiceCategory(DepositType.CSRS_PRE_10_82_REDEPOSIT);
                    } else if (!item.getStartDate().before(splitDate1082) && item.getEndDate().before(splitDate391)) {
                        csrsPost82Pre91Redeposit = modifyRedeposit(csrsPost82Pre91Redeposit, item);
                        item.setServiceCategory(DepositType.CSRS_POST_82_PRE_91_REDEPOSIT);
                    } else if (!item.getStartDate().before(splitDate391)) {
                        csrsPost391Redeposit = modifyRedeposit(csrsPost391Redeposit, item);
                        item.setServiceCategory(DepositType.CSRS_POST_3_91_REDEPOSIT);
                    }
                } else if (periodType.equals(dedepositPeriodType.getName()) && item.getRetirementType()
                        .getName().equals(csrsRetirementType.getName())) {
                    if (item.getEndDate().before(splitDate1082)) {
                        csrsPre1082Dedeposit = modifyDedeposit(csrsPre1082Dedeposit, item);
                        item.setServiceCategory(DepositType.CSRS_PRE_10_82_DEPOSIT);
                    } else if (!item.getStartDate().before(splitDate1082)) {
                        csrsPost1082Dedeposit = modifyDedeposit(csrsPost1082Dedeposit, item);
                        item.setServiceCategory(DepositType.CSRS_POST_10_82_DEPOSIT);
                    }
                } else if (item.getRetirementType().equals(csrsRetirementType)
                        && "Peace Corp/Vista".equals(serviceType)) {
                    csrsPeaceCorpsDedeposit = modifyDedeposit(csrsPeaceCorpsDedeposit, item);
                    item.setServiceCategory(DepositType.CSRS_PEACE_CORPS);
                } else if (item.getRetirementType().equals(fersRetirementType)
                        && "Peace Corp/Vista".equals(serviceType)) {
                    fersPeaceCorpsDedeposit = modifyDedeposit(fersPeaceCorpsDedeposit, item);
                    item.setServiceCategory(DepositType.FERS_PEACE_CORPS);
                }
                if (periodType.equals(redepositPeriodType.getName())) {
                    // Update summary
                    totalPaymentsRequired = totalPaymentsRequired.add(item.getDeductionAmount());
                    totalInitialInterest = totalInitialInterest.add(item.getTotalInterest());
                    totalPaymentsApplied = totalPaymentsApplied.add(item.getPaymentsApplied());
                } else if (periodType.equals(dedepositPeriodType.getName())) {
                    // Update summary
                    totalPaymentsRequired = totalPaymentsRequired.subtract(item.getDeductionAmount());
                    totalInitialInterest = totalInitialInterest.subtract(item.getTotalInterest());
                    totalPaymentsApplied = totalPaymentsApplied.subtract(item.getPaymentsApplied());
                }
            }
            redeposits.add(fersRedeposit);
            redeposits.add(csrsPost391Redeposit);
            redeposits.add(csrsPost82Pre91Redeposit);
            redeposits.add(csrsPre1082Redeposit);
            result.setRedeposits(redeposits);
            dedeposits.add(fersDedeposit);
            dedeposits.add(csrsPost1082Dedeposit);
            dedeposits.add(csrsPre1082Dedeposit);
            dedeposits.add(csrsPeaceCorpsDedeposit);
            dedeposits.add(fersPeaceCorpsDedeposit);
            result.setDedeposits(dedeposits);

            SummaryData summaryData = new SummaryData();
            summaryData.setTotalPaymentsRequired(totalPaymentsRequired);
            summaryData.setTotalInitialInterest(totalInitialInterest);
            summaryData.setTotalPaymentsApplied(totalPaymentsApplied);
            summaryData.setTotalBalance(totalPaymentsRequired.add(totalInitialInterest).subtract(totalPaymentsApplied));
            result.setSummary(summaryData);

            LoggingHelper.logExit(logger, signature, new Object[]{result});
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
     * @throws OPMConfigurationException if the instance was not initialized properly (entityManager,
     * deductionCalculationRuleService, interestCalculationRuleService, redepositPeriodType, dedepositPeriodType,
     * csrsRetirementType, fersRetirementType or successCalculationStatus is null).
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
        Helper.checkState(fersRetirementType == null, "'fersRetirementType' can't be null.");
        Helper.checkState(successCalculationStatus == null, "'successCalculationStatus' can't be null.");
    }

    /**
     * Modify and reset the dedeposit entity.
     * @param dedeposit the dedeposit entity
     * @param item the caculation result item
     * @return the modified dedeposit entity
     */
    private static Dedeposit modifyDedeposit(Dedeposit dedeposit, CalculationResultItem item) {
        dedeposit.setDeposit(dedeposit.getDeposit().add(item.getDeductionAmount()));
        dedeposit.setInterest(dedeposit.getInterest().add(item.getTotalInterest()));
        dedeposit.setTotal(dedeposit.getTotal().add(item.getBalance()));
        return dedeposit;
    }

    /**
     * Modify and reset the redeposit entity.
     * @param redeposit the redeposit entity
     * @param item the caculation result item
     * @return the modified redeposit entity
     */
    private static Redeposit modifyRedeposit(Redeposit redeposit, CalculationResultItem item) {
        redeposit.setDeposit(redeposit.getDeposit().add(item.getDeductionAmount()));
        redeposit.setInterest(redeposit.getInterest().add(item.getTotalInterest()));
        redeposit.setTotal(redeposit.getTotal().add(item.getBalance()));
        return redeposit;
    }

    /**
     * Create Redeposit based on the deposit type.
     * @param type the deposite type
     * @return the created Redeposit instance
     */
    private static Redeposit createRedeposit(DepositType type) {
        Redeposit redeposit = new Redeposit();
        redeposit.setDepositType(type);
        redeposit.setDeposit(BigDecimal.ZERO);
        redeposit.setInterest(BigDecimal.ZERO);
        redeposit.setTotal(BigDecimal.ZERO);
        return redeposit;
    }

    /**
     * Create Dedeposit based on the deposit type.
     * @param type the deposite type
     * @return the created Dedeposit instance
     */
    private static Dedeposit createDedeposit(DepositType type) {
        Dedeposit dedeposit = new Dedeposit();
        dedeposit.setDepositType(type);
        dedeposit.setDeposit(BigDecimal.ZERO);
        dedeposit.setInterest(BigDecimal.ZERO);
        dedeposit.setTotal(BigDecimal.ZERO);
        return dedeposit;
    }

    /**
     * Run the dededuction rule.
     * @param calculations the list of calculations
     * @return the deduction calculation response
     * @throws OPMException if there any error while firing the rules
     */
    private DeductionCalculationResponse runDeduction(List<Calculation> calculations) throws OPMException {
        DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();
        List<ServicePeriod> servicePeriods = new ArrayList<ServicePeriod>();
        deductionRequest.setServicePeriods(servicePeriods);
        for (Calculation calculation : calculations) {
            if (!calculation.isFrozen()) {
                ServicePeriod period = new ServicePeriod();
                period.setBeginDate(calculation.getBeginDate());
                period.setEndDate(calculation.getEndDate());
                period.setRetirementType(calculation.getRetirementType());
                period.setPeriodType(calculation.getPeriodType());
                period.setAppointmentType(calculation.getAppointmentType());
                period.setServiceType(calculation.getServiceType());
                period.setPayType(calculation.getPayType());
                period.setAmount(calculation.getAmount());
                period.setRefundDate(calculation.getRefundDate());
                period.setValidationErrors(new ArrayList<String>());
                servicePeriods.add(period);
            }
        }
        DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);
        return deductionResponse;
    }

    /**
     * Run the interest calculation rule.
     * @param deductionResponse the deduction response
     * @return the interest calculation response
     * @throws OPMException if there any error while firing the rules
     */
    private InterestCalculationResponse calculateInterest(DeductionCalculationResponse deductionResponse) throws OPMException {
        InterestCalculationRequest interestRequest = new InterestCalculationRequest();
        interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
        interestRequest.setInterestCalculatedToDate(new Date());
        InterestCalculationResponse interestResponse = interestCalculationRuleService.execute(interestRequest);
        return interestResponse;
    }
}
