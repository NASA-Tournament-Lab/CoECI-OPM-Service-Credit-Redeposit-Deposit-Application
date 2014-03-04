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
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.PaymentSearchFilter;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.PaymentService;
import gov.opm.scrd.web.controllers.ValidationException;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class represents the controller used to manage payment data.
 * 
 * It's mutable but the spring mvc framework will guarantee that it's used in the thread safe model.
 * 
 * @author faeton, woodjhon
 * @version 1.0
 */
@Controller
public class PaymentController extends BaseAuditController {
    /**
     * The class name used for the logging
     */
    public static final String CLASS_NAME = PaymentController.class.getName();

    /**
     * Represents the PaymentService instance for managing payment data. It is modified by setter. It is injected by
     * Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.PAYMENT_SERVICE_JNDI)
    private PaymentService paymentService;

    /**
     * The key used to get the current selected account id from the http session. It's autowired, the default value is
     * 'accountId'.
     */
    @Autowired
    private String currentSelectedAccountIdKey = "accountId";

    /**
     * This is the constructor of this class.
     */
    public PaymentController() {
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
    @RequestMapping(value = "payment/view", method = RequestMethod.GET)
    public ModelAndView view(HttpSession session) throws OPMException {
        final String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "session" }, new Object[] { session });
        ModelAndView returnValue = populateModelAndView(session, new ModelAndView("payment"));
        LoggingHelper.logExit(logger, signature, new Object[] { returnValue });
        return returnValue;
    }

    /**
     * Set current account id method.
     * 
     * @param accountId
     *            The accountId to use
     * @param session
     *            The session to use
     */
    @RequestMapping(value = "payment/setAccountId", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void setCurrentAccountId(@RequestParam long accountId, HttpSession session) {
        final String signature = CLASS_NAME + "#setCurrentAccountId(@RequestParam long accountId, HttpSession session)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "accountId", "session" }, new Object[] { accountId,
                session });

        session.setAttribute(this.currentSelectedAccountIdKey, accountId);
        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Searches the payments based on the filter.
     * 
     * @param filter
     *            search filter.
     * @return Serialized JSON representation of the SearchResult<Payment>
     * @throws ValidationException
     *             if filter is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "payments/search", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Payment> search(@RequestBody PaymentSearchFilter filter) throws ValidationException,
            OPMException {
        final String signature = CLASS_NAME + "#search(PaymentSearchFilter filter)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "filter" }, new Object[] { filter });

        WebHelper.checkRequest(logger, signature, new boolean[] { filter == null },
                new String[] { "'filter' can't be null." }, "Failed to search payments.");

        SearchResult<Payment> returnValue = paymentService.search(filter);
        LoggingHelper.logExit(logger, signature, new Object[] { returnValue });
        return returnValue;
    }

    /**
     * Reverses the payment for the account
     * 
     * @param paymentId
     *            the id of payment to reverse
     * @param paymentReversalReasonId
     *            the id of the PaymentReversalReason named entity
     * @param applyReversalToGL
     *            flag, specifying whether reversal should be applied to GL.
     * @param request
     *            the http request to use
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "payment/reverse", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void reverse(@RequestParam long paymentId, @RequestParam long paymentReversalReasonId,
            @RequestParam boolean applyReversalToGL, HttpServletRequest request) throws OPMException {
        final String signature =
                CLASS_NAME + "#reverse(@RequestParam long paymentId, @RequestParam long paymentReversalReasonId, "
                        + "@RequestParam boolean applyReversalToGL, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId", "paymentReversalReasonId",
                "applyReversalToGL", "request" }, new Object[] { paymentId, paymentReversalReasonId, applyReversalToGL,
                request });

        Payment old = paymentService.get(paymentId);

        paymentService.reverse(super.getCurrentUser(request.getSession()).getUsername(), paymentId,
                paymentReversalReasonId, applyReversalToGL);

        Payment newPayment = paymentService.get(paymentId);
        WebHelper.auditEntity(request, this.getAuditService(), this.getCurrentUser(request.getSession()), "reverse",
                old, newPayment);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Add the payment for the account id.
     * 
     * @param accountId
     *            the id of account
     * @param payment
     *            the payment to add
     * @param request
     *            the http request to use
     * @throws ValidationException
     *             if payment is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "payment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @ResponseBody
    public long create(@RequestParam long accountId, @RequestBody Payment payment, HttpServletRequest request)
            throws ValidationException, OPMException {
        final String signature =
                CLASS_NAME + "#create(@RequestParam long accountId, Payment payment, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "accountId", "payment", "request" }, new Object[] {
                accountId, payment, request });

        WebHelper.checkRequest(logger, signature, new boolean[] { payment == null },
                new String[] { "'payment' can't be null." }, "Failed to create payment.");

        long id = paymentService.create(accountId, payment);

        WebHelper.auditEntity(request, this.getAuditService(), this.getCurrentUser(request.getSession()), "create",
                new Payment(), payment);

        LoggingHelper.logExit(logger, signature, new Object[] { id });
        return id;
    }

    /**
     * Updates the list of payments for the account
     * 
     * @param payments
     *            the list of payments to add
     * @param request
     *            the http request to use
     * @throws ValidationException
     *             if payments is null, contains null elements
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "payment", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(@RequestBody List<Payment> payments, HttpServletRequest request) throws ValidationException,
            OPMException {
        final String signature =
                CLASS_NAME + "#update(@RequestBody List<Payment> payments, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "payments", "request" }, new Object[] { payments,
                request });

        WebHelper.checkRequest(logger, signature, new boolean[] { payments == null },
                new String[] { "'payment' can't be null." }, "Failed to update payments.");
        WebHelper.checkRequest(logger, signature, new boolean[] { payments.contains(null) },
                new String[] { "'payment' can't contain null element." }, "Failed to update payments.");

        List<Payment> olds = new ArrayList<Payment>();
        for (Payment p : payments) {
            olds.add(paymentService.get(p.getId()));
        }
        paymentService.update(payments);

        int i = 0;
        for (Payment old : olds) {
            WebHelper.auditEntity(request, this.getAuditService(), this.getCurrentUser(request.getSession()), "update",
                    old, payments.get(i));
            i++;
        }

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Delete the payment for the account
     * 
     * @param paymentId
     *            the id of payment to delete
     * @param request
     *            the http request to use
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "payment/{paymentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(@PathVariable long paymentId, HttpServletRequest request) throws OPMException {
        final String signature = CLASS_NAME + "#delete(@PathVariable long paymentId, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId", "request" }, new Object[] { paymentId,
                request });

        paymentService.delete(paymentId);

        User user = getCurrentUser(request.getSession());
        WebHelper.audit(request, getAuditService(), user, "delete", new Object[] { user.getId(), "User", "deleted",
                "false", "true" });
        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Add the note for the payment.
     * 
     * @param paymentId
     *            the id of payment to delete
     * @param note
     *            the payment note.
     * @param request
     *            the http request to use
     * @throws ValidationException
     *             if note is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "payment/{paymentId}/note", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void savePaymentNote(@PathVariable long paymentId, @RequestParam String note, HttpServletRequest request)
            throws ValidationException, OPMException {
        final String signature =
                CLASS_NAME + "#savePaymentNote(@PathVariable long paymentId, @RequestParam String note, "
                        + "HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId", "note" }, new Object[] { paymentId,
                note });

        WebHelper.checkRequest(logger, signature, new boolean[] { note == null },
                new String[] { "'note' can't be null." }, "Failed to save payment note.");

        WebHelper.checkRequest(logger, signature, new boolean[] { note.trim().length() == 0 },
                new String[] { "'note' can't be empty." }, "Failed to save payment note.");

        Payment old = paymentService.get(paymentId);
        paymentService.savePaymentNote(paymentId, note);

        Payment newPayment = paymentService.get(paymentId);
        WebHelper.auditEntity(request, this.getAuditService(), this.getCurrentUser(request.getSession()),
                "savePaymentNote", old, newPayment);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Simple setter for a namesake field.
     * 
     * @param paymentService
     *            value for a namesake field.
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
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
        Helper.checkState(paymentService == null, "'paymentService' can not be null.");
        Helper.checkState(currentSelectedAccountIdKey == null, "'currentSelectedAccountIdKey' can not be null.");
        Helper.checkState(currentSelectedAccountIdKey.trim().length() == 0,
                "'currentSelectedAccountIdKey' can not be empty.");

    }

}
