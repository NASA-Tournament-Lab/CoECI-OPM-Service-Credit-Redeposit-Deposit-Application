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
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.SuspendedPayment;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.AccountService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.PaymentService;
import gov.opm.scrd.services.SuspensionService;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class represents the controller used to manage suspension data.
 * 
 * It's mutable but the spring mvc framework will guarantee that it's used in the thread safe model.
 * 
 * @author faeton, woodjhon
 * @version 1.0
 */
@Controller
public class SuspensionController extends BaseAuditController {
    /**
     * The class name used for the logging
     */
    public static final String CLASS_NAME = SuspensionController.class.getName();

    /**
     * The key used to get the current selected account id from the http session. It's autowired, the default value is
     * 'accountId'.
     */
    @Autowired
    private String currentSelectedAccountIdKey = "accountId";

    /**
     * Represents the SuspensionService instance for managing suspension data. It is modified by setter. It is injected
     * by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.SUSPENSION_SERVICE_JNDI)
    private SuspensionService suspensionService;

    /**
     * Represents the AccountService instance for managing account data. It is modified by setter. It is injected by
     * Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.ACCOUNT_SERVICE_JNDI)
    private AccountService accountService;

    /**
     * Represents the PaymentService instance for managing payment data. It is modified by setter. It is injected by
     * Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.PAYMENT_SERVICE_JNDI)
    private PaymentService paymentService;

    /**
     * This is the constructor of this class.
     */
    public SuspensionController() {
        super();
    }

    /**
     * This method is responsible for viewing the page of the controller.
     * 
     * @param session
     *            current http session.
     * @return The populated ModelAndView instance.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "suspension/view", method = RequestMethod.GET)
    public ModelAndView view(HttpSession session) throws OPMException {
        final String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "session" }, new Object[] { session });
        ModelAndView returnValue = populateModelAndView(session, new ModelAndView("suspension"));
        if (session.getAttribute(this.currentSelectedAccountIdKey) != null) {
            long accountId = (Long) session.getAttribute(this.currentSelectedAccountIdKey);
            returnValue.addObject("accountId", accountId);
        } else {
            returnValue.addObject("accountId", -1L);
        }
        LoggingHelper.logExit(logger, signature, new Object[] { returnValue });
        return returnValue;
    }

    /**
     * Get the account by id.
     * 
     * @param accountId
     *            the account id to use.
     * @return The account instance.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "suspension/getAccountById", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @ResponseBody
    public Account getAccountById(@RequestParam long accountId) throws OPMException {
        final String signature = CLASS_NAME + "#getAccountById(@RequestParam long accountId)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "accountId" }, new Object[] { accountId });

        Account returnValue = accountService.get(accountId);

        LoggingHelper.logExit(logger, signature, new Object[] { returnValue });
        return returnValue;
    }

    /**
     * Get the account by csd.
     * 
     * @param claimNumber
     *            the account claim number.
     * @return The account instance.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "suspension/getAccountByCsd", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @ResponseBody
    public Account getAccountByCsd(@RequestParam String claimNumber) throws OPMException {
        final String signature = CLASS_NAME + "#getAccountByCsd(@RequestParam String claimNumber)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "claimNumber" }, new Object[] { claimNumber });

        Account returnValue = accountService.getByClaimNumber(claimNumber);

        LoggingHelper.logExit(logger, signature, new Object[] { returnValue });
        return returnValue;
    }

    /**
     * Returns suspended payment for the given suspender.
     * 
     * @param session
     *            http session
     * @return Serialized list of suspended payments
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "suspension", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<SuspendedPayment> getSuspendedPayments(HttpSession session) throws OPMException {
        final String signature = CLASS_NAME + "#getSuspendedPayments(HttpSession session)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "session" }, new Object[] { session });
        List<SuspendedPayment> returnValue =
                suspensionService.getSuspendedPayments(getCurrentUser(session).getUsername());
        LoggingHelper.logExit(logger, signature, new Object[] { returnValue });
        return returnValue;
    }

    /**
     * Returns suspended payment for the given account id.
     * 
     * @param accoundId
     *            the account id to get the suspended payments.
     * @return Serialized list of suspended payments
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "suspension/{accountId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<SuspendedPayment> getSuspendedPayments(@PathVariable long accountId) throws OPMException {
        final String signature = CLASS_NAME + "#getSuspendedPayments(@PathVariable long accountId)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "accountId" }, new Object[] { accountId });

        List<SuspendedPayment> returnValue = suspensionService.getSuspendedPayments(accountId);

        LoggingHelper.logExit(logger, signature, new Object[] { returnValue });
        return returnValue;
    }

    /**
     * Reset suspended payment for the given id.
     * 
     * @param paymentId
     *            the id of suspended payment to reset
     * @param request
     *            the http request to use
     *            
     * @return the reset payment
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "suspension/{paymentId}/reset", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @ResponseBody
    public SuspendedPayment resetPayment(@PathVariable long paymentId, HttpServletRequest request) throws OPMException {
        final String signature = CLASS_NAME + "#resetPayment(@PathVariable long paymentId, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId", "request" }, new Object[] { paymentId,
                "request" });

        Payment old = paymentService.get(paymentId);
        SuspendedPayment sp = suspensionService.resetPayment(paymentId);
        Payment newPayment = paymentService.get(paymentId);
        WebHelper.auditEntity(request, this.getAuditService(), this.getCurrentUser(request.getSession()),
                "resetPayment", old, newPayment);

        LoggingHelper.logExit(logger, signature, new Object[]{sp});
        return sp;
    }

    /**
     * Post suspended payments for the given ids.
     * 
     * @param paymentIds
     *            the id of suspended payments to post
     * @param request
     *            the http request to use
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @return The list of updated payments after post.
     */
    @RequestMapping(value = "suspension/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<SuspendedPayment> postPayments(@RequestParam long[] paymentIds, HttpServletRequest request)
            throws OPMException {
        final String signature =
                CLASS_NAME + "#postPayments(@RequestParam long[] paymentIds, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentIds", "request" }, new Object[] {
                paymentIds, request });

        List<Payment> olds = new ArrayList<Payment>();
        for (long id : paymentIds) {
            olds.add(paymentService.get(id));
        }

        List<SuspendedPayment> returnValue = suspensionService.postPayments(paymentIds);

        for (Payment old : olds) {
            Payment newPayment = paymentService.get(old.getId());
            WebHelper.auditEntity(request, this.getAuditService(), this.getCurrentUser(request.getSession()),
                    "postPayments", old, newPayment);
        }

        LoggingHelper.logExit(logger, signature, new Object[] { returnValue });
        return returnValue;
    }

    /**
     * Link the suspended payment for the given id to the employee.
     * 
     * @param paymentId
     *            the id of suspended payment to link to employee
     * @param accountId
     *            the account id to link
     * @param request
     *            the http request to use
     * 
     * @return the suspended payment
     * 
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "suspension/{paymentId}/linkToEmployee", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SuspendedPayment linkPaymentToEmployee(@PathVariable long paymentId, @RequestParam long accountId,
            HttpServletRequest request) throws OPMException {
        final String signature =
                CLASS_NAME
                        + "#linkPaymentToEmployee(@PathVariable long paymentId, " +
                        "@RequestParam long accountId, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId", "accountId", "request" },
                new Object[] { paymentId, accountId, request });

        Payment old = paymentService.get(paymentId);
        SuspendedPayment returnValue = suspensionService.linkPaymentToAccount(paymentId, accountId);
        Payment newPayment = paymentService.get(paymentId);
        WebHelper.auditEntity(request, this.getAuditService(), this.getCurrentUser(request.getSession()),
                "linkPaymentToEmployee", old, newPayment);

        LoggingHelper.logExit(logger, signature, new Object[] { returnValue });
        return returnValue;
    }

    /**
     * Transfer the payment.
     * 
     * @param paymentId
     *            the id of suspended payment to transfer
     * @param transferTypeId
     *            the id of named entity TransferType to transfer
     * @param refund
     *            flag, specifying whether this payment is a refund
     * @param request
     *            the http request to use
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "suspension/{paymentId}/transferPayment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void transferPayment(@PathVariable long paymentId, @RequestParam long transferTypeId,
            @RequestParam Boolean refund, HttpServletRequest request) throws OPMException {
        final String signature =
                CLASS_NAME + "#transferPayment(@PathVariable long paymentId, @RequestParam long transferTypeId, "
                        + "@RequestParam Boolean refund, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[] { "paymentId", "transferTypeId", "refund", "request" }, new Object[] { paymentId,
                        transferTypeId, refund, request });

        Payment old = paymentService.get(paymentId);
        suspensionService.transferPayment(paymentId, transferTypeId, refund);

        Payment newPayment = paymentService.get(paymentId);
        WebHelper.auditEntity(request, this.getAuditService(), this.getCurrentUser(request.getSession()),
                "transferPayment", old, newPayment);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Simple setter for a namesake field.
     * 
     * @param suspensionService
     *            value for a namesake field.
     */
    public void setSuspensionService(SuspensionService suspensionService) {
        this.suspensionService = suspensionService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     * 
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly.
     */
    @PostConstruct
    protected void checkInit() throws OPMConfigurationException {
        super.checkInit();
        Helper.checkState(suspensionService == null, "'suspensionService' can not be null.");
        Helper.checkState(accountService == null, "'accountService' can not be null.");
        Helper.checkState(paymentService == null, "'paymentService' can not be null.");
        Helper.checkState(this.currentSelectedAccountIdKey == null, "'currentSelectedAccountKey' can not be null.");
        Helper.checkState(this.currentSelectedAccountIdKey.trim().length() == 0,
                "'currentSelectedAccountKey' can not be empty.");

    }

}
