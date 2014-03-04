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
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * This class is the implementation of the CalculationService. It utilizes RuleService for running the calculations.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
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
     *
     * @return The CalculationResult entity holding the data about result, can not be null.
     *
     * @throws IllegalArgumentException
     *             if calculations is null, contain null elements.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public CalculationResult runCalculation(List<Calculation> calculations) throws OPMException {
        String signature = CLASS_NAME + "#runCalculation(List<Calculation> calculations)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"calculations"},
            new Object[] {calculations});

        Helper.checkList(logger, signature, calculations, "calculations");

        try {
            // Run deduction calculation rule
            DeductionCalculationRequest deductionRequest = new DeductionCalculationRequest();

            List<ServicePeriod> servicePeriods = new ArrayList<ServicePeriod>();
            deductionRequest.setServicePeriods(servicePeriods);

            for (Calculation calculation : calculations) {
                ServicePeriod period = new ServicePeriod();
                period.setBeginDate(calculation.getBeginDate());
                period.setEndDate(calculation.getEndDate());
                period.setRetirementType(calculation.getRetirementType());
                period.setPeriodType(calculation.getPeriodType());
                period.setAppointmentType(calculation.getAppointmentType());
                period.setServiceType(calculation.getServiceType());
                period.setPayType(calculation.getPayType());
                period.setAmount(calculation.getAmount());
                period.setValidationErrors(new ArrayList<String>());

                servicePeriods.add(period);
            }
            DeductionCalculationResponse deductionResponse = deductionCalculationRuleService.execute(deductionRequest);

            // Run interest calculation rule
            InterestCalculationRequest interestRequest = new InterestCalculationRequest();
            interestRequest.setExtendedServicePeriods(deductionResponse.getExtendedServicePeriods());
            interestRequest.setInterestCalculatedToDate(new Date());

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
                item.setPeriodType(period.getServicePeriods().get(0).getPeriodType());
                /* Unclear: item.setRetirementType(period.getServicePeriods().get(0).getRetirementType()); */
                item.setDeductionAmount(period.getTotalDeduction());
                item.setTotalInterest(period.getBalanceWithInterest());
                item.setPaymentsApplied(BigDecimal.ZERO);
                item.setBalance(period.getTotalDeduction().add(period.getBalanceWithInterest()));

                items.add(item);

                String periodType = item.getPeriodType().getName();
                // Calculate redeposits/dedeposits
                if (periodType.equals(redepositPeriodType.getName())
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
                } else if (periodType.equals(dedepositPeriodType.getName())) {

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
