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
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.RefundTransaction;
import gov.opm.scrd.entities.application.SuspendedPayment;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PaymentType;
import gov.opm.scrd.entities.lookup.TransferType;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.SuspensionService;

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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * This class is the implementation of the SuspensionService. It utilizes JPA EntityManager for necessary operations.
 * </p>
 * 
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 * 
 * Changed log in the OPM - Frontend - Payments Module Assembly: 
 * 1. getSuspendedPayment(Payment payment) will set the paymentNote for the suspended payment 
 * 2. resetPayment(long paymentId) will set the suspended payment status for the reset payment 
 * 3. add List<SuspendedPayment> getSuspendedPayments(long account) method.
 * 
 * @author faeton, sparemax, woodjhon
 * @version 1.1
 */
@Stateless
@Local(SuspensionService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SuspensionServiceImpl extends BaseService implements SuspensionService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = SuspensionServiceImpl.class.getName();

    /**
     * <p>
     * Represents the JPQL to query suspended Payment count.
     * </p>
     */
    private static final String JPQL_QUERY_SUSPENDED_PAYMENT_COUNT = "SELECT COUNT(e) FROM Payment e"
            + " WHERE e.deleted = false AND e.claimant = :suspender AND e.paymentType = 'SUSPENDED_PAYMENT'";
    /**
     * <p>
     * Represents the JPQL to query suspended Payment.
     * </p>
     */
    private static final String JPQL_QUERY_SUSPENDED_PAYMENT = "SELECT e FROM Payment e"
            + " WHERE e.deleted = false AND e.paymentType = 'SUSPENDED_PAYMENT' AND e.claimant = :suspender";

    /**
     * <p>
     * Represents the JPQL to query suspended Payment.
     * </p>
     */
    private static final String JPQL_QUERY_SUSPENDED_PAYMENT_BY_ACCOUNT_ID = "SELECT e FROM Payment e"
            + " WHERE e.deleted = false AND e.paymentType = 'SUSPENDED_PAYMENT' AND e.accountId = :accountId";

    /**
     * Represents the PaymentStatus entity corresponding to "Posted - Pending Approval" status. It is injected by
     * Spring. It can not be null after injected.
     */
    @Autowired
    private PaymentStatus postedPendingApprovalStatus;

    /**
     * Represents the PaymentStatus entity corresponding to "Suspended" status. It is injected by Spring. It can not be
     * null after injected.
     */
    @Autowired
    private PaymentStatus suspendedStatus;

    /**
     * Creates an instance of SuspensionServiceImpl.
     */
    public SuspensionServiceImpl() {
        // Empty
    }

    /**
     * Gets the number of suspensions for the given user.
     * 
     * @param suspender
     *            the name of user to get number of suspensions.
     * 
     * @return Number of suspensions for user, can be 0 or positive integer.
     * 
     * @throws IllegalArgumentException
     *             if suspender is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public int getSuspensionCount(String suspender) throws OPMException {
        String signature = CLASS_NAME + "#getSuspensionCount(String suspender)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "suspender" }, new Object[] { suspender });

        Helper.checkNullOrEmpty(logger, signature, suspender, "suspender");

        try {
            int result =
                    ((Number) getEntityManager().createQuery(JPQL_QUERY_SUSPENDED_PAYMENT_COUNT)
                            .setParameter("suspender", suspender).getSingleResult()).intValue();

            LoggingHelper.logExit(logger, signature, new Object[] { result });
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
     * Gets the suspended payments for the user.
     * 
     * @param suspender
     *            the name of user to get suspended payments.
     * 
     * @return List of suspended payments, can not be null/contain null elements.
     * 
     * @throws IllegalArgumentException
     *             if suspender is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<SuspendedPayment> getSuspendedPayments(String suspender) throws OPMException {
        String signature = CLASS_NAME + "#getSuspendedPayments(String suspender)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "suspender" }, new Object[] { suspender });

        Helper.checkNullOrEmpty(logger, signature, suspender, "suspender");

        try {
            List<Payment> payments =
                    getEntityManager().createQuery(JPQL_QUERY_SUSPENDED_PAYMENT, Payment.class)
                            .setParameter("suspender", suspender).getResultList();

            List<SuspendedPayment> result = new ArrayList<SuspendedPayment>();
            for (Payment payment : payments) {
                result.add(getSuspendedPayment(payment));
            }

            LoggingHelper.logExit(logger, signature, new Object[] { result });
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
     * Gets the suspended payments for the account id.
     * 
     * @param accountId
     *            the account id to get suspended payments.
     * 
     * @return List of suspended payments, can not be null/contain null elements.
     * @throws IllegalArgumentException
     *             if account id is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<SuspendedPayment> getSuspendedPayments(long accountId) throws OPMException {
        String signature = CLASS_NAME + "#getSuspendedPayments(long accountId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "accountId" }, new Object[] { accountId });

        Helper.checkPositive(logger, signature, accountId, "accountId");

        try {
            List<Payment> payments =
                    getEntityManager().createQuery(JPQL_QUERY_SUSPENDED_PAYMENT_BY_ACCOUNT_ID, Payment.class)
                            .setParameter("accountId", accountId).getResultList();

            List<SuspendedPayment> result = new ArrayList<SuspendedPayment>();
            for (Payment payment : payments) {
                result.add(getSuspendedPayment(payment));
            }

            LoggingHelper.logExit(logger, signature, new Object[] { result });
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
     * Reset the the payment by id and return the modified payment.
     * 
     * @param paymentId
     *            the payment id to reset.
     * 
     * @return The reset payment for the id, can not be null.
     * 
     * @throws IllegalArgumentException
     *             if paymentId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such payment to reset.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SuspendedPayment resetPayment(long paymentId) throws OPMException {
        String signature = CLASS_NAME + "#resetPayment(long paymentId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId" }, new Object[] { paymentId });

        Helper.checkPositive(logger, signature, paymentId, "paymentId");

        EntityManager entityManager = getEntityManager();
        try {
            // Check the Payment
            Payment resetPayment =
                    Helper.getEntityById(entityManager, logger, signature, Payment.class, paymentId, true);

            if (resetPayment.getMasterAccountId() == null) {
                resetPayment.setClaimNumber(null);
                resetPayment.setClaimantBirthdate(null);
                resetPayment.setAccountStatus(null);
                resetPayment.setAccountBalance(null);
                //resetPayment.setAccountId(null);
            } else {
                resetPayment.setClaimNumber(resetPayment.getMasterClaimNumber());
                resetPayment.setClaimantBirthdate(resetPayment.getMasterClaimantBirthdate());
                resetPayment.setAccountStatus(resetPayment.getMasterAccountStatus());
                resetPayment.setAccountBalance(resetPayment.getMasterAccountBalance());
                resetPayment.setAccountId(resetPayment.getMasterAccountId());
            }

            resetPayment.setPaymentType(PaymentType.SUSPENDED_PAYMENT);
            resetPayment.setPaymentStatus(this.suspendedStatus);

            entityManager.merge(resetPayment);

            SuspendedPayment result = getSuspendedPayment(resetPayment);

            LoggingHelper.logExit(logger, signature, new Object[] { result });
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
     * Post payments for the user.
     * 
     * @param paymentIds
     *            the payment ids to post.
     * 
     * @return The posted payments for the ids, can not be null/contain null elements.
     * 
     * @throws IllegalArgumentException
     *             if paymentIds is null.
     * @throws EntityNotFoundException
     *             if there is no such payment for any payment id in the list.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<SuspendedPayment> postPayments(long[] paymentIds) throws OPMException {
        String signature = CLASS_NAME + "#postPayments(long[] paymentIds)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentIds" }, new Object[] { paymentIds });

        Helper.checkNull(logger, signature, paymentIds, "paymentIds");

        EntityManager entityManager = getEntityManager();
        try {
            List<SuspendedPayment> result = new ArrayList<SuspendedPayment>();
            for (long paymentId : paymentIds) {
                // Check the Payment
                Payment payment =
                        Helper.getEntityById(entityManager, logger, signature, Payment.class, paymentId, true);

                payment.setPaymentStatus(postedPendingApprovalStatus);
                entityManager.merge(payment);

                SuspendedPayment suspendedPayment = getSuspendedPayment(payment);

                result.add(suspendedPayment);
            }

            LoggingHelper.logExit(logger, signature, new Object[] { result });
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
     * Link the payment to the account.
     * 
     * @param paymentId
     *            the payment id to link.
     * @param accountId
     *            the accountId to link.
     * 
     * @return The linked payment for the id, can not be null.
     * 
     * @throws IllegalArgumentException
     *             if paymentId or accountId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such payment/account for the id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SuspendedPayment linkPaymentToAccount(long paymentId, long accountId) throws OPMException {
        String signature = CLASS_NAME + "#linkPaymentToAccount(long paymentId, long accountId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId", "accountId" }, new Object[] {
                paymentId, accountId });

        Helper.checkPositive(logger, signature, paymentId, "paymentId");
        Helper.checkPositive(logger, signature, accountId, "accountId");

        EntityManager entityManager = getEntityManager();
        try {
            // Get payment and account
            Payment payment = Helper.getEntityById(entityManager, logger, signature, Payment.class, paymentId, true);
            Account account = Helper.getEntityById(entityManager, logger, signature, Account.class, accountId, true);

            // Save the account information
            account.getPaymentHistory().add(payment);
            entityManager.merge(account);

            // Save the payment information
            payment.setClaimNumber(account.getClaimNumber());
            payment.setClaimantBirthdate(account.getClaimantBirthdate());
            payment.setAccountStatus(account.getStatus());
            payment.setAccountBalance(account.getBalance());
            payment.setAccountId(account.getId());

            payment.setMasterClaimNumber(account.getClaimNumber());
            payment.setMasterClaimantBirthdate(account.getClaimantBirthdate());
            payment.setMasterAccountStatus(account.getStatus());
            payment.setMasterAccountBalance(account.getBalance());
            payment.setMasterAccountId(account.getId());

            entityManager.merge(payment);

            SuspendedPayment result = getSuspendedPayment(payment);

            LoggingHelper.logExit(logger, signature, new Object[] { result });
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
     * Transfers the payment given the type and refund option.
     * 
     * @param paymentId
     *            the payment id to transfer.
     * @param transferTypeId
     *            the id of the transfer type.
     * @param refund
     *            the flag specifying whether transfer should be refunded.
     * 
     * @return The transferred payment for the id, can not be null.
     * 
     * @throws IllegalArgumentException
     *             if paymentId or transferTypeId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such payment/transfer type for the id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SuspendedPayment transferPayment(long paymentId, long transferTypeId, Boolean refund) throws OPMException {
        String signature = CLASS_NAME + "#transferPayment(long paymentId, long transferTypeId, Boolean refund)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId", "transferTypeId", "refund" },
                new Object[] { paymentId, transferTypeId, refund });

        Helper.checkPositive(logger, signature, paymentId, "paymentId");
        Helper.checkPositive(logger, signature, transferTypeId, "transferTypeId");

        EntityManager entityManager = getEntityManager();
        try {
            // Get payment
            Payment payment = Helper.getEntityById(entityManager, logger, signature, Payment.class, paymentId, true);

            resetPayment(paymentId);

            if ((refund != null) && refund) {
                TransferType transferType =
                        Helper.getEntityById(entityManager, logger, signature, 
                                TransferType.class, transferTypeId, true);

                RefundTransaction transaction = new RefundTransaction();
                transaction.setTransactionKey(payment.getTransactionKey());
                transaction.setAmount(payment.getAmount());
                transaction.setClaimNumber(payment.getClaimNumber());
                transaction.setRefundDate(new Date());
                transaction.setTransferType(transferType);

                entityManager.persist(transaction);
            }

            SuspendedPayment result = getSuspendedPayment(payment);

            LoggingHelper.logExit(logger, signature, new Object[] { result });
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
     *             if the instance was not initialized properly (entityManager, ruleService or
     *             postedPendingApprovalStatus is null).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(postedPendingApprovalStatus == null, "'postedPendingApprovalStatus' can't be null.");
    }

    /**
     * Gets a SuspendedPayment instance.
     * 
     * @param payment
     *            the payment
     * 
     * @return the SuspendedPayment instance
     */
    private static SuspendedPayment getSuspendedPayment(Payment payment) {
        SuspendedPayment suspendedPayment = new SuspendedPayment();

        suspendedPayment.setPaymentStatus(payment.getPaymentStatus());
        suspendedPayment.setDepositDate(payment.getDepositDate());
        suspendedPayment.setBatchNumber(payment.getBatchNumber());
        suspendedPayment.setBlockNumber(payment.getBlockNumber());
        suspendedPayment.setSequenceNumber(payment.getSequenceNumber());
        suspendedPayment.setClaimNumber(payment.getClaimNumber());
        suspendedPayment.setAch(payment.isAch());
        suspendedPayment.setAmount(payment.getAmount());
        suspendedPayment.setClaimantBirthdate(payment.getClaimantBirthdate());
        suspendedPayment.setClaimant(payment.getClaimant());
        suspendedPayment.setAccountBalance(payment.getAccountBalance());
        suspendedPayment.setAccountStatus(payment.getAccountStatus());
        suspendedPayment.setMasterClaimNumber(payment.getMasterClaimNumber());
        suspendedPayment.setMasterClaimantBirthdate(payment.getMasterClaimantBirthdate());
        suspendedPayment.setMasterAccountStatus(payment.getMasterAccountStatus());
        suspendedPayment.setMasterAccountBalance(payment.getMasterAccountBalance());
        suspendedPayment.setPaymentId(payment.getId());

        suspendedPayment.setTransactionDate(payment.getTransactionDate());
        suspendedPayment.setPaymentNote(payment.getNote());

        return suspendedPayment;
    }
}
