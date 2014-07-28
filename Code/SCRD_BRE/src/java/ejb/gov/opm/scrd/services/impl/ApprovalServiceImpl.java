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
import gov.opm.scrd.entities.application.ApprovalItemSummary;
import gov.opm.scrd.entities.application.InterestAdjustment;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.PaymentMove;
import gov.opm.scrd.entities.application.PendingPayment;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PaymentType;
import gov.opm.scrd.services.ApprovalService;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * This class is the implementation of the ApprovalService. It utilizes JPA EntityManager for necessary operations.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Modify getApprovalSummary() method to count number of InterestAdjustments & PaymentMoves with
 * unapproved status</li>
 * <li>Modify getPendingPayments(), getInterestAdjustments() and getPaymentMoves() methods to load user
 * ( Payment#accountId -> Account#claimOfficer -> User)</li>
 * <li>Modify getPendingPayments method to retrieve based on configured payment status</li>
 * <li>Modify approvePendingPayments() and disapprovePendingPayments() methods to update payment status
 * to configured status</li>
 * </ul>
 * </p>
 * 
 * <p>
 * <em>Changes in 1.2 (Defect Assembly - SCRD App - Part 2 1.0):</em>
 * <ul>
 * <li>Added reversalPendingApprovalStatus, reversalPendingStatus</li>
 * </ul>
 * </p>
 * 
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, sparemax, liuliquan
 * @version 1.2
 */
@Stateless
@Local(ApprovalService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApprovalServiceImpl extends BaseService implements ApprovalService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ApprovalServiceImpl.class.getName();

    /**
     * <p>
     * Represents the JPQL to query pending Payment.
     * </p>
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private static final String JPQL_QUERY_PENDING_PAYMENT = "SELECT e FROM Payment e"
        + " WHERE e.deleted = false AND e.paymentStatus.id IN :pendingPaymentStatusIdList"
        + " AND e.approvalUser = :approvalUser AND e.paymentType <> 'INTEREST_ADJUSTMENT'"
        + " AND e.paymentType <> 'PAYMENT_MOVE'";

    /**
     * <p>
     * Represents the JPQL to query Payment count by payment type.
     * </p>
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private static final String JPQL_QUERY_PENDING_PAYMENT_COUNT = "SELECT COUNT(e) FROM Payment e"
        + " WHERE e.deleted = false AND e.paymentStatus.id IN :pendingPaymentStatusIdList"
        + " AND e.approvalUser = :approvalUser AND e.paymentType <> 'INTEREST_ADJUSTMENT'"
        + " AND e.paymentType <> 'PAYMENT_MOVE'";

    /**
     * <p>
     * Represents the JPQL to query interest adjustment.
     * </p>
     */
    private static final String JPQL_QUERY_INTEREST_ADJUSTMENT = "SELECT e FROM Payment e"
        + " WHERE e.deleted = false AND e.paymentType = 'INTEREST_ADJUSTMENT' AND e.approvalUser = :approvalUser"
        + " AND e.approvalStatus = :approvalStatus";

    /**
     * <p>
     * Represents the JPQL to query Payment move.
     * </p>
     */
    private static final String JPQL_QUERY_PAYMENT_MOVE = "SELECT e FROM Payment e"
        + " WHERE e.deleted = false AND e.paymentType = 'PAYMENT_MOVE' AND e.approvalUser = :approvalUser"
        + " AND e.approvalStatus = :approvalStatus";

    /**
     * <p>
     * Represents the JPQL to query unapproved Payment count by payment type.
     * </p>
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private static final String JPQL_QUERY_UNAPPROVED_PAYMENT_COUNT_BY_TYPE = "SELECT COUNT(e) FROM Payment e"
        + " WHERE e.deleted = false AND e.approvalUser = :approvalUser AND e.paymentType = :paymentType"
        + " AND (e.approvalStatus is null OR e.approvalStatus = 'PENDING')";

    /**
     * <p>
     * Represents the JPQL to query User (claim officer) by account id.
     * </p>
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private static final String JPQL_QUERY_CLAIM_OFFICER = "SELECT e FROM User e"
        + " WHERE e.username = (SELECT a.claimOfficer FROM Account a WHERE a.id=:accountId)";

    /**
     * Represents the PaymentStatus for a pending payment which is approved. It is injected by
     * Spring. It can not be null after injected.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @Autowired
    private PaymentStatus approvedPaymentStatus;

    /**
     * Represents the PaymentStatus for a pending payment which is disapproved. It is injected by
     * Spring. It can not be null after injected.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @Autowired
    private PaymentStatus disapprovedPaymentStatus;

    /**
     * When a Payment is reversed and pending approval, it will have this PaymentStatus.
     * 
     * @since 1.2 (Defect Assembly - SCRD App - Part 2 1.0)
     */
    @Autowired
    private PaymentStatus reversalPendingApprovalStatus;

    /**
     * When a Payment is reversed and pending posting, it will have this PaymentStatus.
     * 
     * @since 1.2 (Defect Assembly - SCRD App - Part 2 1.0)
     */
    @Autowired
    private PaymentStatus reversalPendingStatus;

    /**
     * Represents the ids(comma separated) of PaymentStatus for a pending payment. It is injected by
     * Spring. It can not be null after injected.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @Autowired
    private String pendingPaymentStatusIds;

    /**
     * The list of pending payment status ids parsed from pendingPaymentStatusIds.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private List<Long> pendingPaymentStatusIdList = new ArrayList<Long>();

    /**
     * Creates an instance of ApprovalServiceImpl.
     */
    public ApprovalServiceImpl() {
        // Empty
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (entityManager, ruleService or
     *             approvedPaymentStatus/disapprovedPaymentStatus is null, or if
     *             pendingPaymentStatusIds is invalid).
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(approvedPaymentStatus == null, "'approvedPaymentStatus' can't be null.");
        Helper.checkState(disapprovedPaymentStatus == null, "'disapprovedPaymentStatus' can't be null.");

        Helper.checkState(pendingPaymentStatusIds == null || pendingPaymentStatusIds.isEmpty(),
                "'pendingPaymentStatusIds' can't be null/empty.");
        String[] ids = pendingPaymentStatusIds.split(",");

        try {
            for (String id : ids) {
                pendingPaymentStatusIdList.add(Long.parseLong(id.trim()));
            }
        } catch (NumberFormatException nfe) {
            throw new OPMConfigurationException("Invalid pending payment status id", nfe);
        }
    }

    /**
     * Gets the information about approval summary for the user.
     *
     * @param approver
     *            the name of user to get approval summary for.
     *
     * @return ApprovalItemSummary instance containing summary information, can not be null.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ApprovalItemSummary getApprovalSummary(String approver) throws OPMException {
        String signature = CLASS_NAME + "#getApprovalSummary(String approver)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"approver"},
            new Object[] {approver});

        Helper.checkNullOrEmpty(logger, signature, approver, "approver");

        EntityManager entityManager = getEntityManager();
        try {
            ApprovalItemSummary result = new ApprovalItemSummary();

            result.setPendingPaymentCount(((Number) entityManager.createQuery(JPQL_QUERY_PENDING_PAYMENT_COUNT)
                        .setParameter("approvalUser", approver)
                        .setParameter("pendingPaymentStatusIdList", pendingPaymentStatusIdList)
                        .getSingleResult()).intValue());

            result.setInterestAdjustmentCount(
                         ((Number) entityManager.createQuery(JPQL_QUERY_UNAPPROVED_PAYMENT_COUNT_BY_TYPE)
                        .setParameter("approvalUser", approver)
                        .setParameter("paymentType", PaymentType.INTEREST_ADJUSTMENT)
                        .getSingleResult()).intValue());

            result.setPaymentMoveCount(((Number) entityManager.createQuery(JPQL_QUERY_UNAPPROVED_PAYMENT_COUNT_BY_TYPE)
                        .setParameter("approvalUser", approver)
                        .setParameter("paymentType", PaymentType.PAYMENT_MOVE)
                        .getSingleResult()).intValue());

            result.setTotalCount(result.getPendingPaymentCount()
                    + result.getInterestAdjustmentCount() + result.getPaymentMoveCount());

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
     * Gets the pending payments for the user.
     *
     * @param approver
     *            the name of user to get pending payments.
     *
     * @return List of pending payments, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PendingPayment> getPendingPayments(String approver) throws OPMException {
        String signature = CLASS_NAME + "#getPendingPayments(String approver)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"approver"},
            new Object[] {approver});

        Helper.checkNullOrEmpty(logger, signature, approver, "approver");

        try {
            List<Payment> payments = getEntityManager().createQuery(JPQL_QUERY_PENDING_PAYMENT, Payment.class)
                .setParameter("approvalUser", approver)
                .setParameter("pendingPaymentStatusIdList", pendingPaymentStatusIdList)
                .getResultList();

            List<PendingPayment> result = new ArrayList<PendingPayment>();
            for (Payment payment : payments) {
                PendingPayment pendingPayment = new PendingPayment();

                pendingPayment.setPaymentStatus(payment.getPaymentStatus());
                pendingPayment.setBatchNumber(payment.getBatchNumber());
                pendingPayment.setBlockNumber(payment.getBlockNumber());
                pendingPayment.setSequenceNumber(payment.getSequenceNumber());
                pendingPayment.setTransactionDate(payment.getTransactionDate());
                pendingPayment.setStatusDate(payment.getStatusDate());
                pendingPayment.setAmount(payment.getAmount());
                pendingPayment.setApplyToGL(payment.isApplyToGL());
                pendingPayment.setClaimNumber(payment.getClaimNumber());
                pendingPayment.setAccountHolderBirthdate(payment.getAccountHolderBirthdate());
                pendingPayment.setAccountStatus(payment.getAccountStatus());
                pendingPayment.setApprovalUser(payment.getApprovalUser());
                pendingPayment.setApprovalStatus(payment.getApprovalStatus());
                pendingPayment.setPaymentId(payment.getId());

                User user = getAccountClaimOfficer(payment.getAccountId(), logger, signature);
                if (user != null) {
                    pendingPayment.setUserName(user.getFirstName());
                    pendingPayment.setUserRole(user.getRole());
                }
                result.add(pendingPayment);
            }

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
     * Gets account's claim officer.
     *
     * @param accountId
     *            the account id
     * @param logger
     *            the logger
     * @param signature
     *            the method signature
     *
     * @return account's claim officer, can be null.
     *
     * @throws IllegalStateException
     *             if the entity manager has been closed.
     * @throws PersistenceException
     *             if there is any problem when executing the method.
     */
    private User getAccountClaimOfficer(Long accountId, Logger logger, String signature) {
        if (accountId == null) {
            return null;
        }

        List<User> result = getEntityManager().createQuery(JPQL_QUERY_CLAIM_OFFICER, User.class)
                .setParameter("accountId", accountId)
                .getResultList();

        return result == null || result.isEmpty() ? null : result.get(0);
    }

    /**
     * Approves pending payments.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param pendingPaymentIds
     *            the ids of payments to approve.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, pendingPaymentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void approvePendingPayments(String approver, long[] pendingPaymentIds) throws OPMException {
        String signature = CLASS_NAME + "#approvePendingPayments(String approver, long[] pendingPaymentIds)";

        approvePayments(signature, approver, pendingPaymentIds, "pendingPaymentIds", true);
    }

    /**
     * Disapproves pending payments.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param pendingPaymentIds
     *            the ids of payments to disapprove.
     * @param reason
     *            the reason
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, pendingPaymentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void disapprovePendingPayments(String approver, long[] pendingPaymentIds, String reason)
        throws OPMException {
        String signature = CLASS_NAME
            + "#disapprovePendingPayments(String approver, long[] pendingPaymentIds, String reason)";

        disapprovePayments(signature, approver, pendingPaymentIds, reason, "pendingPaymentIds", true);
    }

    /**
     * Gets the interest adjustments for the user.
     *
     * @param approver
     *            the name of user to get interest adjustments.
     * @param approvalStatus
     *            the approval status of the interest adjustments.
     *
     * @return List of interest adjustments, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, approvalStatus is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<InterestAdjustment> getInterestAdjustments(String approver, ApprovalStatus approvalStatus)
        throws OPMException {
        String signature = CLASS_NAME + "#getInterestAdjustments(String approver, ApprovalStatus approvalStatus)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"approver", "approvalStatus"},
            new Object[] {approver, approvalStatus});

        Helper.checkNullOrEmpty(logger, signature, approver, "approver");
        Helper.checkNull(logger, signature, approvalStatus, "approvalStatus");

        try {
            List<Payment> payments = getEntityManager().createQuery(JPQL_QUERY_INTEREST_ADJUSTMENT, Payment.class)
                .setParameter("approvalUser", approver).setParameter("approvalStatus", approvalStatus).getResultList();

            List<InterestAdjustment> result = new ArrayList<InterestAdjustment>();
            for (Payment payment : payments) {
                InterestAdjustment interestAdjustment = new InterestAdjustment();

                interestAdjustment.setClaimNumber(payment.getClaimNumber());
                interestAdjustment.setPreDepositAmount(payment.getPreDepositAmount());
                interestAdjustment.setPreRedepositAmount(payment.getPreRedepositAmount());
                interestAdjustment.setPostDepositAmount(payment.getPostDepositAmount());
                interestAdjustment.setPostRedepositAmount(payment.getPostRedepositAmount());
                interestAdjustment.setPaymentTransactionAmount(payment.getAmount());
                interestAdjustment.setAdjustmentDate(payment.getTransactionDate());
                interestAdjustment.setAccountStatus(payment.getAccountStatus());
                interestAdjustment.setAccountHolderBirthdate(payment.getAccountHolderBirthdate());
                interestAdjustment.setApprovalUser(payment.getApprovalUser());
                interestAdjustment.setApprovalStatus(payment.getApprovalStatus());
                interestAdjustment.setPaymentId(payment.getId());

                User user = getAccountClaimOfficer(payment.getAccountId(), logger, signature);
                if (user != null) {
                    interestAdjustment.setUserName(user.getFirstName());
                    interestAdjustment.setUserStatus(user.getStatus());
                    interestAdjustment.setUserRole(user.getRole());
                }
                result.add(interestAdjustment);
            }

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
     * Approves interest adjustments.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param interestAdjustmentIds
     *            the ids of interest adjustments to approve.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, interestAdjustmentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void approveInterestAdjustments(String approver, long[] interestAdjustmentIds) throws OPMException {
        String signature = CLASS_NAME + "#approveInterestAdjustments(String approver, long[] interestAdjustmentIds)";

        approvePayments(signature, approver, interestAdjustmentIds, "interestAdjustmentIds", false);
    }

    /**
     * Disapproves interest adjustments.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param interestAdjustmentIds
     *            the ids of interest adjustments to disapprove.
     * @param reason
     *            the reason
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, interestAdjustmentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void disapproveInterestAdjustments(String approver, long[] interestAdjustmentIds, String reason)
        throws OPMException {
        String signature = CLASS_NAME
            + "#disapproveInterestAdjustments(String approver, long[] interestAdjustmentIds, String reason)";

        disapprovePayments(signature, approver, interestAdjustmentIds, reason, "interestAdjustmentIds", false);
    }

    /**
     * Gets the payment moves for the user.
     *
     * @param approver
     *            the name of user to get payment moves.
     * @param approvalStatus
     *            the approval status of the payment moves.
     *
     * @return List of payment moves, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, approvalStatus is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<PaymentMove> getPaymentMoves(String approver, ApprovalStatus approvalStatus) throws OPMException {
        String signature = CLASS_NAME + "#getPaymentMoves(String approver, ApprovalStatus approvalStatus)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"approver", "approvalStatus"},
            new Object[] {approver, approvalStatus});

        Helper.checkNullOrEmpty(logger, signature, approver, "approver");
        Helper.checkNull(logger, signature, approvalStatus, "approvalStatus");

        try {
            List<Payment> payments = getEntityManager().createQuery(JPQL_QUERY_PAYMENT_MOVE, Payment.class)
                .setParameter("approvalUser", approver).setParameter("approvalStatus", approvalStatus).getResultList();

            List<PaymentMove> result = new ArrayList<PaymentMove>();
            for (Payment payment : payments) {
                PaymentMove paymentMove = new PaymentMove();

                paymentMove.setClaimNumber(payment.getClaimNumber());
                paymentMove.setPreDepositAmount(payment.getPreDepositAmount());
                paymentMove.setPreRedepositAmount(payment.getPreRedepositAmount());
                paymentMove.setPostDepositAmount(payment.getPostDepositAmount());
                paymentMove.setPostRedepositAmount(payment.getPostRedepositAmount());
                paymentMove.setPaymentTransactionAmount(payment.getAmount());
                paymentMove.setAdjustmentDate(payment.getTransactionDate());
                paymentMove.setAccountStatus(payment.getAccountStatus());
                paymentMove.setAccountHolderBirthdate(payment.getAccountHolderBirthdate());
                paymentMove.setApprovalUser(payment.getApprovalUser());
                paymentMove.setApprovalStatus(payment.getApprovalStatus());
                paymentMove.setPaymentId(payment.getId());

                User user = getAccountClaimOfficer(payment.getAccountId(), logger, signature);
                if (user != null) {
                    paymentMove.setUserName(user.getFirstName());
                    paymentMove.setUserStatus(user.getStatus());
                    paymentMove.setUserRole(user.getRole());
                }
                result.add(paymentMove);
            }

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
     * Approves payment moves.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param paymentMoveIds
     *            the ids of payment moves to approve.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, paymentMoveIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void approvePaymentMoves(String approver, long[] paymentMoveIds) throws OPMException {
        String signature = CLASS_NAME + "#approvePaymentMoves(String approver, long[] paymentMoveIds)";

        approvePayments(signature, approver, paymentMoveIds, "paymentMoveIds", false);
    }

    /**
     * Disapproves payment moves.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param paymentMoveIds
     *            the ids of payment moves to disapprove.
     * @param reason
     *            the reason
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, paymentMoveIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void disapprovePaymentMoves(String approver, long[] paymentMoveIds, String reason) throws OPMException {
        String signature = CLASS_NAME
            + "#disapprovePaymentMoves(String approver, long[] paymentMoveIds, String reason)";

        disapprovePayments(signature, approver, paymentMoveIds, reason, "paymentMoveIds", false);
    }

    /**
     * Approves payments.
     *
     * @param signature
     *            the signature
     * @param approver
     *            the name of the user performing operation.
     * @param paymentIds
     *            the ids of payments to approve.
     * @param paymentIdsName
     *            the name of payments IDs parameter.
     * @param updateStatus
     *            Indicates whether to update status.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, paymentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    private void approvePayments(String signature, String approver, long[] paymentIds, String paymentIdsName,
            boolean updateStatus)
        throws OPMException {
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"approver", paymentIdsName},
            new Object[] {approver, paymentIds});

        Helper.checkNullOrEmpty(logger, signature, approver, "approver");
        Helper.checkNull(logger, signature, paymentIds, "paymentIds");

        EntityManager entityManager = getEntityManager();

        try {
            for (long paymentId : paymentIds) {
                // Get payment
                Payment payment = Helper
                    .getEntityById(entityManager, logger, signature, Payment.class, paymentId, true);

                payment.setApprovalUser(approver);
                payment.setApprovalStatus(ApprovalStatus.APPROVED);
                if (updateStatus) {
                    // https://github.com/nasa/SCRD/issues/47
                    if (payment.getPaymentStatus().equals(reversalPendingApprovalStatus)) {
                        // reversal pending approval transitions to reversal pending
                        payment.setPaymentStatus(reversalPendingStatus);
                    } else {
                        // (default behavior)
                        payment.setPaymentStatus(approvedPaymentStatus);
                    }
                }

                entityManager.merge(payment);
            }

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Disapproves payments.
     *
     * @param signature
     *            the signature.
     * @param approver
     *            the name of the user performing operation.
     * @param paymentIds
     *            the ids of payments to disapprove.
     * @param reason
     *            the reason
     * @param paymentIdsName
     *            the name of payments IDs parameter.
     * @param updateStatus
     *            Indicates whether to update status.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, paymentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    private void disapprovePayments(String signature, String approver, long[] paymentIds, String reason,
        String paymentIdsName, boolean updateStatus) throws OPMException {
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"approver", paymentIdsName, "reason"},
            new Object[] {approver, paymentIds, reason});

        Helper.checkNullOrEmpty(logger, signature, approver, "approver");
        Helper.checkNull(logger, signature, paymentIds, "paymentIds");

        EntityManager entityManager = getEntityManager();

        try {
            for (long paymentId : paymentIds) {
                // Get payment
                Payment payment = Helper
                    .getEntityById(entityManager, logger, signature, Payment.class, paymentId, true);

                payment.setApprovalUser(approver);
                payment.setApprovalStatus(ApprovalStatus.DISAPPROVED);
                payment.setApprovalReason(reason);
                if (updateStatus) {
                    payment.setPaymentStatus(disapprovedPaymentStatus);
                }

                entityManager.merge(payment);
            }

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }
}
