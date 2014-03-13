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

package gov.opm.scrd.web.controllers;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.NamedEntity;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.ApplicationDesignation;
import gov.opm.scrd.entities.lookup.AppointmentType;
import gov.opm.scrd.entities.lookup.ClaimOfficer;
import gov.opm.scrd.entities.lookup.Country;
import gov.opm.scrd.entities.lookup.FormType;
import gov.opm.scrd.entities.lookup.PayType;
import gov.opm.scrd.entities.lookup.PaymentAppliance;
import gov.opm.scrd.entities.lookup.PaymentReversalReason;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PeriodType;
import gov.opm.scrd.entities.lookup.RetirementType;
import gov.opm.scrd.entities.lookup.Role;
import gov.opm.scrd.entities.lookup.ServiceType;
import gov.opm.scrd.entities.lookup.State;
import gov.opm.scrd.entities.lookup.Suffix;
import gov.opm.scrd.entities.lookup.TransferType;
import gov.opm.scrd.entities.lookup.UserStatus;
import gov.opm.scrd.services.LookupService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * This class represents the controller used to retrieve lookup values. It provides list of methods that will be
 * AJAX-called and provide necessary data.
 * </p>
 * 
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the injected services are
 * not modified in controllers by methods after configuration and configuration will be done in a thread safe manner by
 * Spring IoC framework.
 * </p>
 * 
 * Changed in the OPM - Frontend - Payments Module Assembly Add lookup method for PaymentAppliance entity.
 * 
 * @author faeton, sparemax, woodjhon
 * @version 1.1
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
@Controller
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LookupController extends BaseController {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = LookupController.class.getName();

    /**
     * Represents the LookupService instance for retrieving data. It is modified by setter. It is injected by Spring. It
     * can not be null after injected.
     */
    @EJB(mappedName = WebHelper.LOOKUP_SERVICE_JNDI)
    private LookupService lookupService;

    /**
     * Represents the lookup name for entity PaymentReversalReason. It is modified by setter. It is injected by Spring.
     * It can not be null/empty after injected.
     */
    @Autowired
    private String paymentReversalReasonLookupName;

    /**
     * Represents the lookup name for entity Suffix. It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    @Autowired
    private String suffixLookupName;

    /**
     * Represents the lookup name for entity ApplicationDesignation. It is modified by setter. It is injected by Spring.
     * It can not be null/empty after injected.
     */
    @Autowired
    private String applicationDesignationLookupName;

    /**
     * Represents the lookup name for entity State. It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    @Autowired
    private String stateLookupName;

    /**
     * Represents the lookup name for entity PaymentStatus. It is modified by setter. It is injected by Spring. It can
     * not be null/empty after injected.
     */
    @Autowired
    private String paymentStatusLookupName;

    /**
     * Represents the lookup name for entity ServiceType. It is modified by setter. It is injected by Spring. It can not
     * be null/empty after injected.
     */
    @Autowired
    private String serviceTypeLookupName;

    /**
     * Represents the lookup name for entity FormType. It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    @Autowired
    private String formTypeLookupName;

    /**
     * Represents the lookup name for entity ClaimOfficer. It is modified by setter. It is injected by Spring. It can
     * not be null/empty after injected.
     */
    @Autowired
    private String claimOfficerLookupName;

    /**
     * Represents the lookup name for entity PayType. It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    @Autowired
    private String payTypeLookupName;

    /**
     * Represents the lookup name for entity AccountStatus. It is modified by setter. It is injected by Spring. It can
     * not be null/empty after injected.
     */
    @Autowired
    private String accountStatusLookupName;

    /**
     * Represents the lookup name for entity Role. It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    @Autowired
    private String roleLookupName;

    /**
     * Represents the lookup name for entity RetirementType. It is modified by setter. It is injected by Spring. It can
     * not be null/empty after injected.
     */
    @Autowired
    private String retirementTypeLookupName;

    /**
     * Represents the lookup name for entity AppointmentType. It is modified by setter. It is injected by Spring. It can
     * not be null/empty after injected.
     */
    @Autowired
    private String appointmentTypeLookupName;

    /**
     * Represents the lookup name for entity TransferType. It is modified by setter. It is injected by Spring. It can
     * not be null/empty after injected.
     */
    @Autowired
    private String transferTypeLookupName;

    /**
     * Represents the lookup name for entity PeriodType. It is modified by setter. It is injected by Spring. It can not
     * be null/empty after injected.
     */
    @Autowired
    private String periodTypeLookupName;

    /**
     * Represents the lookup name for entity UserStatus. It is modified by setter. It is injected by Spring. It can not
     * be null/empty after injected.
     */
    @Autowired
    private String userStatusLookupName;

    /**
     * Represents the lookup name for entity Country. It is modified by setter. It is injected by Spring. It can not be
     * null/empty after injected.
     */
    @Autowired
    private String countryLookupName;

    /**
     * Represents the lookup name for entity PaymentAppliance. It is modified by setter. It is injected by Spring. It
     * can not be null/empty after injected.
     */
    @Autowired
    private String paymentApplianceLookupName;

    /**
     * Creates an instance of LookupController.
     */
    public LookupController() {
        // Empty
    }

    /**
     * Returns all payment reversal reasons.
     * 
     * @return Whole list of lookup values, may be empty.
     * 
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/paymentAppliances", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PaymentAppliance> getAllPaymentAppliances() throws OPMException {
        String signature = CLASS_NAME + "#getAllPaymentAppliances()";

        return getLookups(getLogger(), signature, paymentApplianceLookupName);
    }

    /**
     * Returns all payment reversal reasons.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/paymentReversalReasons", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PaymentReversalReason> getAllPaymentReversalReasons() throws OPMException {
        String signature = CLASS_NAME + "#getAllPaymentReversalReasons()";

        return getLookups(getLogger(), signature, paymentReversalReasonLookupName);
    }

    /**
     * Returns all name suffixes.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/suffixes", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Suffix> getAllSuffixes() throws OPMException {
        String signature = CLASS_NAME + "#getAllSuffixes()";

        return getLookups(getLogger(), signature, suffixLookupName);
    }

    /**
     * Returns all application designations.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/applicationDesignations", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ApplicationDesignation> getAllApplicationDesignations() throws OPMException {
        String signature = CLASS_NAME + "#getAllApplicationDesignations()";

        return getLookups(getLogger(), signature, applicationDesignationLookupName);
    }

    /**
     * Returns all states.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/states", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<State> getAllStates() throws OPMException {
        String signature = CLASS_NAME + "#getAllStates()";

        return getLookups(getLogger(), signature, stateLookupName);
    }

    /**
     * Returns all payment statuses.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/paymentStatuses", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PaymentStatus> getAllPaymentStatuses() throws OPMException {
        String signature = CLASS_NAME + "#getAllPaymentStatuses()";

        List<PaymentStatus> status = getLookups(getLogger(), signature, paymentStatusLookupName);
        Collections.sort(status, new Comparator<PaymentStatus>() {
            /**
             * Compares the payment status, sort by name.
             * @param o1 the first payment status to compare.
             * @param o2 the second payment status to compare.
             * @return &gt;0 if o1 is after o2, 0 if they are equal, otherwise &lt;0
             */
            @Override
            public int compare(PaymentStatus o1, PaymentStatus o2) {
                return o1.getName().compareTo(o2.getName());
            }
            
        });
        return status;
    }

    /**
     * Returns all service types.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/serviceTypes", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ServiceType> getAllServiceTypes() throws OPMException {
        String signature = CLASS_NAME + "#getAllServiceTypes()";

        return getLookups(getLogger(), signature, serviceTypeLookupName);
    }

    /**
     * Returns all form types.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/formTypes", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<FormType> getAllFormTypes() throws OPMException {
        String signature = CLASS_NAME + "#getAllFormTypes()";

        return getLookups(getLogger(), signature, formTypeLookupName);
    }

    /**
     * Returns all claim officers.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/claimOfficers", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ClaimOfficer> getAllClaimOfficers() throws OPMException {
        String signature = CLASS_NAME + "#getAllClaimOfficers()";

        return getLookups(getLogger(), signature, claimOfficerLookupName);
    }

    /**
     * Returns all pay types.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/payTypes", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PayType> getAllPayTypes() throws OPMException {
        String signature = CLASS_NAME + "#getAllPayTypes()";

        return getLookups(getLogger(), signature, payTypeLookupName);
    }

    /**
     * Returns all account statuses.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/accountStatuses", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<AccountStatus> getAllAccountStatuses() throws OPMException {
        String signature = CLASS_NAME + "#getAllAccountStatuses()";

        return getLookups(getLogger(), signature, accountStatusLookupName);
    }

    /**
     * Returns all roles.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/roles", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Role> getAllRoles() throws OPMException {
        String signature = CLASS_NAME + "#getAllRoles()";

        return getLookups(getLogger(), signature, roleLookupName);
    }

    /**
     * Returns all retirement types.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/retirementTypes", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<RetirementType> getAllRetirementTypes() throws OPMException {
        String signature = CLASS_NAME + "#getAllRetirementTypes()";

        return getLookups(getLogger(), signature, retirementTypeLookupName);
    }

    /**
     * Returns all appointment types.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/appointmentTypes", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<AppointmentType> getAllAppointmentTypes() throws OPMException {
        String signature = CLASS_NAME + "#getAllAppointmentTypes()";

        return getLookups(getLogger(), signature, appointmentTypeLookupName);
    }

    /**
     * Returns all transfer types.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/transferTypes", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<TransferType> getAllTransferTypes() throws OPMException {
        String signature = CLASS_NAME + "#getAllTransferTypes()";

        return getLookups(getLogger(), signature, transferTypeLookupName);
    }

    /**
     * Returns all period types.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/periodTypes", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PeriodType> getAllPeriodTypes() throws OPMException {
        String signature = CLASS_NAME + "#getAllPeriodTypes()";

        return getLookups(getLogger(), signature, periodTypeLookupName);
    }

    /**
     * Returns all user statuses.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/userStatuses", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<UserStatus> getAllUserStatuses() throws OPMException {
        String signature = CLASS_NAME + "#getAllUserStatuses()";

        return getLookups(getLogger(), signature, userStatusLookupName);
    }

    /**
     * Returns all countries.
     *
     * @return Whole list of lookup values, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @RequestMapping(value = "lookup/countries", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Country> getAllCountries() throws OPMException {
        String signature = CLASS_NAME + "#getAllCountries()";

        return getLookups(getLogger(), signature, countryLookupName);
    }

    /**
     * Sets the LookupService instance for retrieving data.
     *
     * @param lookupService
     *            the LookupService instance for retrieving data.
     */
    public void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    /**
     * Sets the lookup name for entity PaymentReversalReason.
     *
     * @param paymentReversalReasonLookupName
     *            the lookup name for entity PaymentReversalReason.
     */
    public void setPaymentReversalReasonLookupName(String paymentReversalReasonLookupName) {
        this.paymentReversalReasonLookupName = paymentReversalReasonLookupName;
    }

    /**
     * Sets the lookup name for entity Suffix.
     *
     * @param suffixLookupName
     *            the lookup name for entity Suffix.
     */
    public void setSuffixLookupName(String suffixLookupName) {
        this.suffixLookupName = suffixLookupName;
    }

    /**
     * Sets the lookup name for entity ApplicationDesignation.
     *
     * @param applicationDesignationLookupName
     *            the lookup name for entity ApplicationDesignation.
     */
    public void setApplicationDesignationLookupName(String applicationDesignationLookupName) {
        this.applicationDesignationLookupName = applicationDesignationLookupName;
    }

    /**
     * Sets the lookup name for entity State.
     *
     * @param stateLookupName
     *            the lookup name for entity State.
     */
    public void setStateLookupName(String stateLookupName) {
        this.stateLookupName = stateLookupName;
    }

    /**
     * Sets the lookup name for entity PaymentStatus.
     *
     * @param paymentStatusLookupName
     *            the lookup name for entity PaymentStatus.
     */
    public void setPaymentStatusLookupName(String paymentStatusLookupName) {
        this.paymentStatusLookupName = paymentStatusLookupName;
    }

    /**
     * Sets the lookup name for entity ServiceType.
     *
     * @param serviceTypeLookupName
     *            the lookup name for entity ServiceType.
     */
    public void setServiceTypeLookupName(String serviceTypeLookupName) {
        this.serviceTypeLookupName = serviceTypeLookupName;
    }

    /**
     * Sets the lookup name for entity FormType.
     *
     * @param formTypeLookupName
     *            the lookup name for entity FormType.
     */
    public void setFormTypeLookupName(String formTypeLookupName) {
        this.formTypeLookupName = formTypeLookupName;
    }

    /**
     * Sets the lookup name for entity ClaimOfficer.
     *
     * @param claimOfficerLookupName
     *            the lookup name for entity ClaimOfficer.
     */
    public void setClaimOfficerLookupName(String claimOfficerLookupName) {
        this.claimOfficerLookupName = claimOfficerLookupName;
    }

    /**
     * Sets the lookup name for entity PayType.
     *
     * @param payTypeLookupName
     *            the lookup name for entity PayType.
     */
    public void setPayTypeLookupName(String payTypeLookupName) {
        this.payTypeLookupName = payTypeLookupName;
    }

    /**
     * Sets the lookup name for entity AccountStatus.
     *
     * @param accountStatusLookupName
     *            the lookup name for entity AccountStatus.
     */
    public void setAccountStatusLookupName(String accountStatusLookupName) {
        this.accountStatusLookupName = accountStatusLookupName;
    }

    /**
     * Sets the lookup name for entity Role.
     *
     * @param roleLookupName
     *            the lookup name for entity Role.
     */
    public void setRoleLookupName(String roleLookupName) {
        this.roleLookupName = roleLookupName;
    }

    /**
     * Sets the lookup name for entity RetirementType.
     *
     * @param retirementTypeLookupName
     *            the lookup name for entity RetirementType.
     */
    public void setRetirementTypeLookupName(String retirementTypeLookupName) {
        this.retirementTypeLookupName = retirementTypeLookupName;
    }

    /**
     * Sets the lookup name for entity AppointmentType.
     *
     * @param appointmentTypeLookupName
     *            the lookup name for entity AppointmentType.
     */
    public void setAppointmentTypeLookupName(String appointmentTypeLookupName) {
        this.appointmentTypeLookupName = appointmentTypeLookupName;
    }

    /**
     * Sets the lookup name for entity TransferType.
     *
     * @param transferTypeLookupName
     *            the lookup name for entity TransferType.
     */
    public void setTransferTypeLookupName(String transferTypeLookupName) {
        this.transferTypeLookupName = transferTypeLookupName;
    }

    /**
     * Sets the lookup name for entity PeriodType.
     *
     * @param periodTypeLookupName
     *            the lookup name for entity PeriodType.
     */
    public void setPeriodTypeLookupName(String periodTypeLookupName) {
        this.periodTypeLookupName = periodTypeLookupName;
    }

    /**
     * Sets the lookup name for entity UserStatus.
     *
     * @param userStatusLookupName
     *            the lookup name for entity UserStatus.
     */
    public void setUserStatusLookupName(String userStatusLookupName) {
        this.userStatusLookupName = userStatusLookupName;
    }

    /**
     * Sets the lookup name for entity Country.
     *
     * @param countryLookupName
     *            the lookup name for entity Country.
     */
    public void setCountryLookupName(String countryLookupName) {
        this.countryLookupName = countryLookupName;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService, userService or lookupService is null; userSessionKey,
     *             paymentReversalReasonLookupName, suffixLookupName, applicationDesignationLookupName, stateLookupName,
     *             paymentStatusLookupName, serviceTypeLookupName, formTypeLookupName, claimOfficerLookupName,
     *             payTypeLookupName, accountStatusLookupName, roleLookupName, retirementTypeLookupName,
     *             appointmentTypeLookupName, transferTypeLookupName, periodTypeLookupName, userStatusLookupName or
     *             countryLookupName is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(lookupService == null, "'lookupService' can't be null.");

        Helper.checkState(WebHelper.isNullOrEmpty(paymentReversalReasonLookupName),
            "'paymentReversalReasonLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(suffixLookupName), "'suffixLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(applicationDesignationLookupName),
            "'applicationDesignationLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(stateLookupName), "'stateLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(paymentStatusLookupName),
            "'paymentStatusLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(serviceTypeLookupName),
            "'serviceTypeLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(formTypeLookupName), "'formTypeLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(claimOfficerLookupName),
            "'claimOfficerLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(payTypeLookupName), "'payTypeLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(accountStatusLookupName),
            "'accountStatusLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(roleLookupName), "'roleLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(retirementTypeLookupName),
            "'retirementTypeLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(appointmentTypeLookupName),
            "'appointmentTypeLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(transferTypeLookupName),
            "'transferTypeLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(periodTypeLookupName), "'periodTypeLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(userStatusLookupName), "'userStatusLookupName' can't be null/empty.");
        Helper.checkState(WebHelper.isNullOrEmpty(countryLookupName), "'countryLookupName' can't be null/empty.");
    }

    /**
     * Gets the lookup entities.
     *
     * @param <T>
     *            the entity type
     * @param logger
     *            the logger
     * @param signature
     *            the signature
     * @param lookupName
     *            the lookup name
     *
     * @return the lookup entities.
     *
     * @throws OPMException
     *             if any error occurs
     */
    @SuppressWarnings("unchecked")
    private <T extends NamedEntity> List<T> getLookups(Logger logger, String signature, String lookupName)
        throws OPMException {
        LoggingHelper.logEntrance(logger, signature, null, null);

        List<NamedEntity> list = lookupService.getLookups(lookupName);

        List<T> result = new ArrayList<T>();
        for (NamedEntity element : list) {
            result.add((T) element);
        }

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }
}
