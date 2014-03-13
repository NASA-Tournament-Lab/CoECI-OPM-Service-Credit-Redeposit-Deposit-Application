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
import gov.opm.scrd.entities.application.InterestAdjustment;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.PaymentMove;
import gov.opm.scrd.entities.application.PendingPayment;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.services.ApprovalService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.PaymentService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
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
 * <p>
 * This class represents the controller used to show approval tab.
 * Specifically, it is used to manage pending payments, payment moves and interest adjustments.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, liuliquan
 * @version 1.0
 * @since OPM - Frontend - Miscellaneous Module Assembly
 */
@Controller
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApprovalController extends BaseAuditController {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ApprovalController.class.getName();

    /**
     * Represents the ApprovalService instance to manage pending payments, payment moves and interest adjustments. It is
     * modified by setter. It is injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.APPROVAL_SERVICE_JNDI)
    private ApprovalService approvalService;

    /**
     * Represents the PaymentService instance to manage pending payments, payment moves and interest adjustments. It is
     * modified by setter. It is injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.PAYMENT_SERVICE_JNDI)
    private PaymentService paymentService;

    /**
     * This is the constructor of this class.
     */
    public ApprovalController() {
    }

    /**
     * This method is responsible for viewing the page of the controller.
     *
     * @param session
     *            current http session.
     *
     * @return The populated ModelAndView instance.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/view", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView view(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[] {"session"},
                new Object[] {session});

        ModelAndView modelAndView = populateModelAndView(session, new ModelAndView("approval"));

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }

    /**
     * Retrieves note of payment by id.
     *
     * @param paymentId
     *            the id of payment to retrieve its note.
     *
     * @return The note for the payment, can be null.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/paymentNote/{paymentId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public String getPaymentNote(@PathVariable long paymentId) throws OPMException {
        String signature = CLASS_NAME + "#getPaymentNote(long paymentId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[] {"paymentId"},
                new Object[] {paymentId});

        String result = paymentService.getPaymentNote(paymentId);

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Save note of payment.
     *
     * @param request
     *            the http servlet request.
     * @param paymentId
     *            the id of payment to save its note.
     * @param note
     *            the note to save.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @throws ValidationException
     *             if note is null or empty, or if payment id does not exist.
     */
    @RequestMapping(value = "approval/paymentNote/{paymentId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void savePaymentNote(HttpServletRequest request,
        @PathVariable long paymentId, @RequestBody String note)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME
            + "#savePaymentNote(HttpServletRequest request, long paymentId, String note)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[] {"request", "paymentId", "note"},
                new Object[] {request, paymentId, note});

        // Check note
        WebHelper.checkRequest(logger, signature,
            new boolean[] {WebHelper.isNullOrEmpty(note)},
            new String[] {"'note' can't be null."},
            "Failed to save payment note.");

        // Check existing payment
        Payment existingPayment = paymentService.get(paymentId);

        WebHelper.checkRequest(logger, signature, new boolean[] {existingPayment == null},
                        new String[] {"Payment id " + paymentId + " does not exist"},
                        "Failed to save payment note.");

        // Audit
        WebHelper.audit(request, getAuditService(), getCurrentUser(request.getSession()), "savePaymentNote",
            new Object[] {paymentId, "Payment", "note",
                existingPayment.getNote(), note});

        paymentService.savePaymentNote(paymentId, note);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Returns the pending payments for the approver.
     *
     * @param session
     *            current http session.
     *
     * @return The list of pending payments.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/pendingPayments", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<PendingPayment> getPendingPayments(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#getPendingPayments(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[] {"session"},
                new Object[] {session});

        List<PendingPayment> result = approvalService.getPendingPayments(getCurrentUser(session).getUsername());

        // Load the lazy-loading objects
        for (PendingPayment pp : result) {
            if (pp.getAccountStatus() != null) {
                pp.getAccountStatus().getName();
            }
            if (pp.getPaymentStatus() != null) {
                pp.getPaymentStatus().getName();
            }
            if (pp.getUserRole() != null) {
                pp.getUserRole().getName();
            }
        }

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Approves the pending payments.
     *
     * @param request
     *            the http servlet request.
     * @param pendingPaymentIds
     *            the ids of payments to approve.
     *
     * @throws ValidationException
     *             if pendingPaymentIds is null or if any id does not exist
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/pendingPayments/approve", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void approvePendingPayments(HttpServletRequest request,
        @RequestParam long[] pendingPaymentIds) throws OPMException, ValidationException {
        String signature = CLASS_NAME
            + "#approvePendingPayments(HttpServletRequest request, long[] pendingPaymentIds)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request", "pendingPaymentIds"},
                new Object[] {request, pendingPaymentIds});

        // Check request
        WebHelper.checkRequest(logger, signature, new boolean[] {pendingPaymentIds == null},
                new String[] {"'pendingPaymentIds' can't be null."}, "Failed to approve pending payments.");

        User currentUser = getCurrentUser(request.getSession());

        for (long paymentId : pendingPaymentIds) {
            Payment existingPayment = paymentService.get(paymentId);

            WebHelper.checkRequest(logger, signature, new boolean[] {existingPayment == null},
                    new String[] {"Payment id " + paymentId + " does not exist"},
                    "Failed to approve pending payments.");

            // Audit
            WebHelper.audit(request, getAuditService(), currentUser, "approvePendingPayments",
                new Object[] {paymentId, "Payment", "approvalStatus",
                    existingPayment.getApprovalStatus(), ApprovalStatus.APPROVED});
        }

        approvalService.approvePendingPayments(currentUser.getUsername(), pendingPaymentIds);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Disapproves the pending payments.
     *
     * @param request
     *            the http servlet request.
     * @param pendingPaymentIds
     *            the ids of payments to disapprove.
     * @param reason
     *            the disapproval reason.
     *
     * @throws ValidationException
     *             if pendingPaymentIds is null or if any id does not exist, reason is null/empty
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/pendingPayments/disapprove", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void disapprovePendingPayments(HttpServletRequest request,
        @RequestParam long[] pendingPaymentIds, @RequestParam String reason)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME
                + "#disapprovePendingPayments(HttpServletRequest request, "
                + "long[] pendingPaymentIds, String reason)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request", "pendingPaymentIds", "reason"},
                new Object[] {request, pendingPaymentIds, reason});

        // Check request
        WebHelper.checkRequest(logger, signature,
                new boolean[] {pendingPaymentIds == null, WebHelper.isNullOrEmpty(reason)},
                new String[] {"'pendingPaymentIds' can't be null.", "'reason' can't be null/empty."},
                "Failed to disapprove pending payments.");

        User currentUser = getCurrentUser(request.getSession());

        for (long paymentId : pendingPaymentIds) {
            Payment existingPayment = paymentService.get(paymentId);

            WebHelper.checkRequest(logger, signature, new boolean[] {existingPayment == null},
                    new String[] {"Payment id " + paymentId + " does not exist"},
                    "Failed to disapprove pending payments.");

            // Audit
            WebHelper.audit(request, getAuditService(), currentUser, "disapprovePendingPayments",
                new Object[] {paymentId, "Payment", "approvalStatus",
                    existingPayment.getApprovalStatus(), ApprovalStatus.DISAPPROVED},
                new Object[] {paymentId, "Payment", "approvalReason",
                    existingPayment.getApprovalReason(), reason});
        }

        approvalService.disapprovePendingPayments(currentUser.getUsername(), pendingPaymentIds, reason);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Returns the interest adjustment for the approver and approval status.
     *
     * @param session
     *            current http session.
     * @param approvalStatus
     *            the status of the approval.
     *
     * @return The list of interest adjustments.
     *
     * @throws ValidationException
     *             if approvalStatus is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/interestAdjustments", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<InterestAdjustment> getInterestAdjustments(HttpSession session,
        @RequestParam ApprovalStatus approvalStatus)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#getInterestAdjustments(HttpSession session, ApprovalStatus approvalStatus)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[] {"session", "approvalStatus"},
                new Object[] {session, approvalStatus});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {approvalStatus == null},
            new String[] {"'approvalStatus' can't be null."},
            "Failed to get interest adjustments.");

        List<InterestAdjustment> result = approvalService.getInterestAdjustments(
                getCurrentUser(session).getUsername(), approvalStatus);

        // Load the lazy-loading objects
        for (InterestAdjustment ia : result) {
            if (ia.getAccountStatus() != null) {
                ia.getAccountStatus().getName();
            }
            if (ia.getUserStatus() != null) {
                ia.getUserStatus().getName();
            }
            if (ia.getUserRole() != null) {
                ia.getUserRole().getName();
            }
        }
        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Approves the interest adjustments.
     *
     * @param request
     *            the http servlet request.
     * @param interestAdjustmentIds
     *            the ids of interest adjustments to approve.
     *
     * @throws ValidationException
     *             if interestAdjustmentIds is null or if any id does not exist
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/interestAdjustments/approve", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void approveInterestAdjustments(HttpServletRequest request,
        long[] interestAdjustmentIds) throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#approveInterestAdjustments(HttpServletRequest request,"
                + " long[] interestAdjustmentIds)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request", "interestAdjustmentIds"},
                new Object[] {request, interestAdjustmentIds});

        // Check request
        WebHelper.checkRequest(logger, signature, new boolean[] {interestAdjustmentIds == null},
                new String[] {"'interestAdjustmentIds' can't be null."}, "Failed to approve interest adjustments.");

        User currentUser = getCurrentUser(request.getSession());

        for (long paymentId : interestAdjustmentIds) {
            Payment existingPayment = paymentService.get(paymentId);

            WebHelper.checkRequest(logger, signature, new boolean[] {existingPayment == null},
                            new String[] {"Payment id " + paymentId + " does not exist"},
                            "Failed to approve interest adjustments.");

            // Audit
            WebHelper.audit(request, getAuditService(), currentUser, "approveInterestAdjustments",
                new Object[] {paymentId, "Payment", "approvalStatus",
                    existingPayment.getApprovalStatus(), ApprovalStatus.APPROVED});
        }

        approvalService.approveInterestAdjustments(currentUser.getUsername(), interestAdjustmentIds);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Disapproves the interest adjustments.
     *
     * @param request
     *            the http servlet request.
     * @param interestAdjustmentIds
     *            the ids of interest adjustments to disapprove.
     * @param reason
     *            the disapproval reason.
     *
     * @throws ValidationException
     *             if interestAdjustmentIds is null or if any id does not exist, reason is null/empty
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/interestAdjustments/disapprove", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void disapproveInterestAdjustments(HttpServletRequest request,
        @RequestParam long[] interestAdjustmentIds, @RequestParam String reason)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME
                + "#disapproveInterestAdjustments(HttpServletRequest request, "
                + "long[] interestAdjustmentIds, String reason)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[] {"request", "interestAdjustmentIds", "reason"},
                new Object[] {request, interestAdjustmentIds, reason});

        // Check request
        WebHelper.checkRequest(logger, signature,
                new boolean[] {interestAdjustmentIds == null, WebHelper.isNullOrEmpty(reason)},
                new String[] {"'interestAdjustmentIds' can't be null.", "'reason' can't be null/empty."},
                "Failed to disapprove interest adjustments.");

        User currentUser = getCurrentUser(request.getSession());

        for (long paymentId : interestAdjustmentIds) {
            Payment existingPayment = paymentService.get(paymentId);

            WebHelper.checkRequest(logger, signature, new boolean[] {existingPayment == null},
                    new String[] {"Payment id " + paymentId + " does not exist"},
                    "Failed to disapprove interest adjustments.");

            // Audit
            WebHelper.audit(request, getAuditService(), currentUser, "disapproveInterestAdjustments",
                new Object[] {paymentId, "Payment", "approvalStatus",
                    existingPayment.getApprovalStatus(), ApprovalStatus.DISAPPROVED},
                new Object[] {paymentId, "Payment", "approvalReason",
                    existingPayment.getApprovalReason(), reason});
        }

        approvalService.disapproveInterestAdjustments(currentUser.getUsername(), interestAdjustmentIds, reason);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Returns the payment moves for the approver and approval status.
     *
     * @param session
     *            current http session.
     * @param approvalStatus
     *            the status of the approval.
     *
     * @return The list of payment moves.
     *
     * @throws ValidationException
     *             if approvalStatus is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/paymentMoves", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<PaymentMove> getPaymentMoves(HttpSession session, @RequestParam ApprovalStatus approvalStatus)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#getPaymentMoves(HttpSession session, ApprovalStatus approvalStatus)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[] {"session", "approvalStatus"},
                new Object[] {session, approvalStatus});

        // Check request
        WebHelper.checkRequest(logger, signature,
                new boolean[] {approvalStatus == null},
                new String[] {"'approvalStatus' can't be null."},
                "Failed to get payment moves.");

        List<PaymentMove> result = approvalService.getPaymentMoves(
                getCurrentUser(session).getUsername(), approvalStatus);

        // Load the lazy-loading objects
        for (PaymentMove pm : result) {
            if (pm.getAccountStatus() != null) {
                pm.getAccountStatus().getName();
            }
            if (pm.getUserStatus() != null) {
                pm.getUserStatus().getName();
            }
            if (pm.getUserRole() != null) {
                pm.getUserRole().getName();
            }
        }
        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Approves the payment moves.
     *
     * @param request
     *            the http servlet request.
     * @param paymentMoveIds
     *            the ids of payment moves to approve.
     *
     * @throws ValidationException
     *             if paymentMoveIds is null or if any id does not exist
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/paymentMoves/approve", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void approvePaymentMoves(HttpServletRequest request,
        long[] paymentMoveIds) throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#approvePaymentMoves(HttpServletRequest request,"
                + " long[] paymentMoveIds)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request", "paymentMoveIds"},
                new Object[] {request, paymentMoveIds});

        // Check request
        WebHelper.checkRequest(logger, signature, new boolean[] {paymentMoveIds == null},
                new String[] {"'paymentMoveIds' can't be null."}, "Failed to approve payment moves.");

        User currentUser = getCurrentUser(request.getSession());

        for (long paymentId : paymentMoveIds) {
            Payment existingPayment = paymentService.get(paymentId);

            WebHelper.checkRequest(logger, signature, new boolean[] {existingPayment == null},
                    new String[] {"Payment id " + paymentId + " does not exist"},
                    "Failed to approve payment moves.");

            // Audit
            WebHelper.audit(request, getAuditService(), currentUser, "approvePaymentMoves",
                new Object[] {paymentId, "Payment", "approvalStatus",
                    existingPayment.getApprovalStatus(), ApprovalStatus.APPROVED});
        }

        approvalService.approvePaymentMoves(currentUser.getUsername(), paymentMoveIds);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Disapproves the payment moves.
     *
     * @param request
     *            the http servlet request.
     * @param paymentMoveIds
     *            the ids of payment moves to disapprove.
     * @param reason
     *            the disapproval reason.
     *
     * @throws ValidationException
     *             if paymentMoveIds is null or if any id does not exist, reason is null/empty
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "approval/paymentMoves/disapprove", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void disapprovePaymentMoves(HttpServletRequest request,
        @RequestParam long[] paymentMoveIds, @RequestParam String reason)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME
                + "#disapprovePaymentMoves(HttpServletRequest request, "
                + "long[] paymentMoveIds, String reason)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[] {"request", "paymentMoveIds", "reason"},
                new Object[] {request, paymentMoveIds, reason});

        // Check request
        WebHelper.checkRequest(logger, signature,
                new boolean[] {paymentMoveIds == null, WebHelper.isNullOrEmpty(reason)},
                new String[] {"'paymentMoveIds' can't be null.", "'reason' can't be null/empty."},
                "Failed to disapprove payment moves.");

        User currentUser = getCurrentUser(request.getSession());

        for (long paymentId : paymentMoveIds) {
            Payment existingPayment = paymentService.get(paymentId);

            WebHelper.checkRequest(logger, signature, new boolean[] {existingPayment == null},
                    new String[] {"Payment id " + paymentId + " does not exist"},
                    "Failed to disapprove payment moves.");

            // Audit
            WebHelper.audit(request, getAuditService(), currentUser, "disapprovePaymentMoves",
                new Object[] {paymentId, "Payment", "approvalStatus",
                    existingPayment.getApprovalStatus(), ApprovalStatus.DISAPPROVED},
                new Object[] {paymentId, "Payment", "approvalReason",
                    existingPayment.getApprovalReason(), reason});
        }

        approvalService.disapprovePaymentMoves(currentUser.getUsername(), paymentMoveIds, reason);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Setter method for property <tt>approvalService</tt>.
     * @param approvalService value to be assigned to property approvalService
     */
    public void setApprovalService(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    /**
     * Setter method for property <tt>paymentService</tt>.
     * @param paymentService value to be assigned to property paymentService
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService, userService, auditService, approvalService or paymentService is null;
     *             userSessionKey is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(approvalService == null, "'approvalService' can't be null.");
        Helper.checkState(paymentService == null, "'paymentService' can't be null.");
    }
}

