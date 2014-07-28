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
import gov.opm.scrd.entities.application.PaymentReverse;
import gov.opm.scrd.entities.application.PaymentSearchFilter;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.entities.lookup.ComparisonType;
import gov.opm.scrd.entities.lookup.DepositComparisonType;
import gov.opm.scrd.entities.lookup.PaymentReversalReason;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.PaymentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * This class is the implementation of the PaymentService. It utilizes JPA EntityManager for necessary operations.
 * </p>
 * 
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Add get(long paymentId) method to get payment by Id</li>
 * <li>Add getPaymentNote(long paymentId) method to get payment note</li>
 * </ul>
 * </p>
 *  
 * <p>
 * <em>Changes in 1.2 (Defect Assembly - SCRD App - Part 2 1.0):</em>
 * <ul>
 * <li>Added reversalPendingApprovalStatus</li>
 * <li>Modified {@link #reverse(String, long, long, boolean)} to set the {@link PaymentReverse} instance into the
 * Payment being reversed and also to set the status of the Payment to {@link #reversalPendingApprovalStatus}
 * </ul>
 * </p>
 * 
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 * 
 * Changed in the OPM - Frontend - Payments Module Assembly: The search method is modified for the new fields of
 * PaymentSearchFilter.
 * 
 * @author faeton, sparemax, liuliquan, woodjhon
 * @version 1.2
 */
@Stateless
@Local(PaymentService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PaymentServiceImpl extends BaseService implements PaymentService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = PaymentServiceImpl.class.getName();

    /**
     * <p>
     * Represents the SQL to query Payment.
     * </p>
     */
    private static final String SQL_QUERY_PAYMENT = "SELECT e FROM Payment e WHERE e.deleted = false";
    /**
     * <p>
     * Represents the SQL to query Payment count.
     * </p>
     */
    private static final String SQL_QUERY_PAYMENT_COUNT = "SELECT COUNT(e) FROM Payment e WHERE e.deleted = false";

    /**
     * <p>
     * Represents the JPQL to query Payment note.
     * </p>
     * 
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private static final String JPQL_QUERY_PAYMENT_NOTE = "SELECT e.note FROM Payment e"
            + " WHERE e.deleted = false AND e.id = :id";

    /**
     * The mapping from ComparisonType to string.
     */
    private static final Map<ComparisonType, String> COMPARISON_TYPE_MAP = new HashMap<ComparisonType, String>();

    /**
     * The mapping from DepositComparisonType to string.
     */
    private static final Map<DepositComparisonType, String> DEPOSIT_COMPARISON_TYPE_MAP =
            new HashMap<DepositComparisonType, String>();

    /**
     * When a Payment is reversed, we tell it to transition into this status:
     * reversal pending approval. Note that this is provided by Spring and must
     * not be null post construction.
     * 
     * @since 1.2 (Defect Assembly - SCRD App - Part 2 1.0)
     */
    @Autowired
    private PaymentStatus reversalPendingApprovalStatus;
    
    /**
     * Initialization.
     */
    static {
        COMPARISON_TYPE_MAP.put(ComparisonType.LESS_THAN, "<");
        COMPARISON_TYPE_MAP.put(ComparisonType.LESS_THAN_OR_EQUAL, "<=");
        COMPARISON_TYPE_MAP.put(ComparisonType.EQUAL, "=");
        COMPARISON_TYPE_MAP.put(ComparisonType.GREATER_THAN, ">");
        COMPARISON_TYPE_MAP.put(ComparisonType.GREATER_THAN_OR_EQUAL, ">=");
        COMPARISON_TYPE_MAP.put(ComparisonType.NET_EQUAL, "<>");

        DEPOSIT_COMPARISON_TYPE_MAP.put(DepositComparisonType.DEPOSITED_BEFORE, "<");
        DEPOSIT_COMPARISON_TYPE_MAP.put(DepositComparisonType.DEPOSITED_BEFORE_OR_ON, "<=");
        DEPOSIT_COMPARISON_TYPE_MAP.put(DepositComparisonType.DEPOSITED_ON, "=");
        DEPOSIT_COMPARISON_TYPE_MAP.put(DepositComparisonType.DEPOSITED_ON_OR_AFTER, ">=");
        DEPOSIT_COMPARISON_TYPE_MAP.put(DepositComparisonType.DEPOSITED_AFTER, ">");
    }

    /**
     * Creates an instance of PaymentServiceImpl.
     */
    public PaymentServiceImpl() {
        // Empty
    }

    /**
     * Checks that the fields we expect to have been wired, have been set to legal values.
     * 
     * @since 1.2 (Defect Assembly - SCRD App - Part 2 1.0)
     */
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(reversalPendingApprovalStatus == null, "'reversalPendingApproval' can't be null.");
    }
    
    /**
     * Gets the payment by id.
     * 
     * @param paymentId
     *            the payment id to get.
     * 
     * @return The payment for the id or null if it can not be found.
     * 
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Payment get(long paymentId) throws OPMException {
        String signature = CLASS_NAME + "#get(long paymentId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId" }, new Object[] { paymentId });

        Helper.checkPositive(logger, signature, paymentId, "paymentId");

        try {
            // Get Payment
            Payment result =
                    Helper.getEntityById(getEntityManager(), logger, signature, Payment.class, paymentId, false);

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
     * Retrieves note of payment by id.
     * 
     * @param paymentId
     *            the id of payment to retrieve its note.
     * 
     * @return The note for the payment, can be null.
     * 
     * @throws IllegalArgumentException
     *             if paymentId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public String getPaymentNote(long paymentId) throws OPMException {
        String signature = CLASS_NAME + "#getPaymentNote(long paymentId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId" }, new Object[] { paymentId });

        Helper.checkPositive(logger, signature, paymentId, "paymentId");

        try {
            String result =
                    (String) getEntityManager().createQuery(JPQL_QUERY_PAYMENT_NOTE).setParameter("id", paymentId)
                            .getSingleResult();

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
     * Creates the payment for the account.
     * 
     * @param accountId
     *            the id of account to associate payment.
     * @param payment
     *            payment to create.
     * 
     * @return The id of the created payment instance.
     * 
     * @throws IllegalArgumentException
     *             if accountId is not positive or payment is null.
     * @throws EntityNotFoundException
     *             if there is no such account.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long create(long accountId, Payment payment) throws OPMException {
        String signature = CLASS_NAME + "#create(long accountId, Payment payment)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "accountId", "payment" }, new Object[] { accountId,
                payment });

        Helper.checkPositive(logger, signature, accountId, "accountId");
        Helper.checkNull(logger, signature, payment, "payment");

        EntityManager entityManager = getEntityManager();

        try {
            // Get the Account
            Account account = Helper.getEntityById(entityManager, logger, signature, Account.class, accountId, true);

            payment.setAccountId(accountId);
            entityManager.persist(payment);

            account.getPaymentHistory().add(payment);
            entityManager.merge(account);

            long result = payment.getId();
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
     * Updates payments.
     * 
     * @param payments
     *            payments to update.
     * 
     * @throws IllegalArgumentException
     *             if payment is null/contain null elements.
     * @throws EntityNotFoundException
     *             if there is no such payment for any element of payments array.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(List<Payment> payments) throws OPMException {
        String signature = CLASS_NAME + "#update(List<Payment> payments)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "payments" }, new Object[] { payments });

        Helper.checkList(logger, signature, payments, "payments");

        EntityManager entityManager = getEntityManager();

        try {
            for (Payment payment : payments) {
                // Check the Payment
                Helper.getEntityById(entityManager, logger, signature, Payment.class, payment.getId(), true);

                getEntityManager().merge(payment);
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
     * Deletes the payment by id.
     * 
     * @param paymentId
     *            the payment id to update.
     * 
     * @throws IllegalArgumentException
     *             if paymentId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such payment to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(long paymentId) throws OPMException {
        String signature = CLASS_NAME + "#delete(long paymentId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId" }, new Object[] { paymentId });

        Helper.checkPositive(logger, signature, paymentId, "paymentId");

        EntityManager entityManager = getEntityManager();

        try {
            // Get the Payment
            Payment payment = Helper.getEntityById(entityManager, logger, signature, Payment.class, paymentId, true);
            payment.setDeleted(true);

            entityManager.merge(payment);

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
     * Searches payments based on the filter.
     * 
     * @param filter
     *            the filter to search payment.
     * 
     * @return SearchResult&lt;Payment&t; instance holding information about search result.
     * 
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Payment> search(PaymentSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#search(PaymentSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "filter" }, new Object[] { filter });

        Helper.checkNull(logger, signature, filter, "filter");

        try {
            String orderByClause = Helper.buildOrderBy(filter);

            List<String> paramNames = new ArrayList<String>();
            List<Object> paramValues = new ArrayList<Object>();
            String whereClause = buildWhere(logger, signature, filter, paramNames, paramValues);

            EntityManager entityManager = getEntityManager();

            // Create query
            TypedQuery<Payment> query =
                    entityManager.createQuery(SQL_QUERY_PAYMENT + whereClause + orderByClause, Payment.class);
            Helper.setParameters(query, paramNames, paramValues);

            int pageNumber = filter.getPageNumber();
            int pageSize = filter.getPageSize();
            // Set paging
            if (pageNumber > 0) {
                query.setMaxResults(pageSize);
                query.setFirstResult((pageNumber - 1) * pageSize);
            }

            List<Payment> records = query.getResultList();

            SearchResult<Payment> result = new SearchResult<Payment>();
            result.setItems(records);

            if (pageNumber > 0) {
                // Create query
                TypedQuery<Number> countQuery =
                        entityManager.createQuery(SQL_QUERY_PAYMENT_COUNT + whereClause, Number.class);
                Helper.setParameters(countQuery, paramNames, paramValues);

                int totalCount = countQuery.getSingleResult().intValue();
                result.setTotal(totalCount);
                result.setTotalPageCount((totalCount + pageSize - 1) / pageSize);
            } else {
                result.setTotal(records.size());
                result.setTotalPageCount(records.isEmpty() ? 0 : 1);
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
     * Saves the note for the payment.
     * 
     * @param paymentId
     *            the id of payment to add note.
     * @param note
     *            the payment note to add.
     * 
     * @throws IllegalArgumentException
     *             if paymentId is not positive or note is null.
     * @throws EntityNotFoundException
     *             if there is no such payment to add note.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void savePaymentNote(long paymentId, String note) throws OPMException {
        String signature = CLASS_NAME + "#savePaymentNote(long paymentId, String note)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] { "paymentId", "note" }, new Object[] { paymentId,
                note });

        Helper.checkPositive(logger, signature, paymentId, "paymentId");
        Helper.checkNull(logger, signature, note, "note");

        EntityManager entityManager = getEntityManager();

        try {
            // Get the Payment
            Payment payment = Helper.getEntityById(entityManager, logger, signature, Payment.class, paymentId, true);
            payment.setNote(note);

            entityManager.merge(payment);

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
     * Reverses the payment.
     * 
     * @param reverser
     *            the name of the user performing operation.
     * @param paymentId
     *            the id of payment to reverse
     * @param paymentReversalReasonId
     *            the id of reversal reason
     * @param applyReversalToGL
     *            the flag specifying whether payment reversal should be applied to GL
     * 
     * @throws IllegalArgumentException
     *             if paymentId or paymentReversalReasonId is not positive, or reverser is null/empty.
     * @throws EntityNotFoundException
     *             if there is no such payment to reverse, no record for paymentReversalReasonId.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void reverse(String reverser, long paymentId, long paymentReversalReasonId, boolean applyReversalToGL)
            throws OPMException {
        String signature =
                CLASS_NAME + "#reverse(String reverser, long paymentId, long paymentReversalReasonId, "
                        + "boolean applyReversalToGL)";
        Logger logger = getLogger();

        LoggingHelper
                .logEntrance(logger, signature, new String[] { "reverser", "paymentId", "paymentReversalReasonId",
                        "applyReversalToGL" }, new Object[] { reverser, paymentId, paymentReversalReasonId,
                        applyReversalToGL });

        Helper.checkNullOrEmpty(logger, signature, reverser, "reverser");
        Helper.checkPositive(logger, signature, paymentId, "paymentId");
        Helper.checkPositive(logger, signature, paymentReversalReasonId, "paymentReversalReasonId");

        EntityManager entityManager = getEntityManager();

        try {
            // Check the Payment
	    Payment reversedPayment = Helper.getEntityById(entityManager, logger, signature, Payment.class, paymentId,
		    true);

            // Get the PaymentReversalReason
            PaymentReversalReason reason =
                    Helper.getEntityById(entityManager, logger, signature, PaymentReversalReason.class,
                            paymentReversalReasonId, true);

            PaymentReverse reverse = new PaymentReverse();
            reverse.setReverser(reverser);
            reverse.setPaymentId(paymentId);
            reverse.setReason(reason);
            reverse.setApplyToGL(applyReversalToGL);

            getEntityManager().persist(reverse);
            
            // https://github.com/nasa/SCRD/issues/47
            reversedPayment.reverse(reverse, reversalPendingApprovalStatus);

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
     * Builds the WHERE string.
     * 
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to be logged.
     * @param filter
     *            the filter
     * @param paramNames
     *            the parameter name
     * @param paramValues
     *            the parameter values
     * 
     * @return the WHERE string.
     * 
     * @throws OPMException
     *             if any error occurs
     */
    private static String buildWhere(Logger logger, String signature, PaymentSearchFilter filter,
            List<String> paramNames, List<Object> paramValues) throws OPMException {
        StringBuilder sb = new StringBuilder();

        if (filter.getAmount() != null && filter.getAmountComparisonType() != null) {
            Helper.appendCondition(sb,
                    "e.amount " + convertAmountComparisonType(logger, signature, filter.getAmountComparisonType())
                            + " :amount", filter.getAmount(), "amount", paramNames, paramValues);
        }
        if (filter.getDepositDate() != null && filter.getDepositComparisonType() != null
                && filter.getDepositComparisonType() != DepositComparisonType.DEPOSITED_BETWEEN) {
            Helper.appendCondition(
                    sb,
                    "e.depositDate "
                            + convertDepositComparisonType(logger, signature, filter.getDepositComparisonType())
                            + " :depositDate", filter.getDepositDate(), "depositDate", paramNames, paramValues);
        } else if (filter.getDepositComparisonType() == DepositComparisonType.DEPOSITED_BETWEEN) {
            if (filter.getDepositStartDate() == null && filter.getDepositEndDate() != null) {
                Helper.appendCondition(sb, "e.depositDate <= :depositEndDate", filter.getDepositEndDate(),
                        "depositEndDate", paramNames, paramValues);
            } else if (filter.getDepositStartDate() != null && filter.getDepositEndDate() == null) {
                Helper.appendCondition(sb, "e.depositDate >= :depositStartDate", filter.getDepositStartDate(),
                        "depositStartDate", paramNames, paramValues);

            } else if (filter.getDepositStartDate() != null && filter.getDepositEndDate() != null) {
                Helper.appendCondition(sb, "e.depositDate <= :depositEndDate", filter.getDepositEndDate(),
                        "depositEndDate", paramNames, paramValues);
                Helper.appendCondition(sb, "e.depositDate >= :depositStartDate", filter.getDepositStartDate(),
                        "depositStartDate", paramNames, paramValues);
            }

        }

        String csd = filter.getClaimNumber();
        if (csd != null) {
            csd = csd.replace("%", "\\%");
        }
        Helper.appendCondition(sb, "e.claimNumber LIKE :claimNumber", csd, "claimNumber", paramNames, paramValues);

        Helper.appendCondition(sb, "e.batchNumber LIKE :batchNumber", filter.getBatchNumber(), "batchNumber",
                paramNames, paramValues);
        Helper.appendCondition(sb, "e.blockNumber LIKE :blockNumber", filter.getBlockNumber(), "blockNumber",
                paramNames, paramValues);
        Helper.appendCondition(sb, "e.sequenceNumber LIKE :sequenceNumber", filter.getSequenceNumber(),
                "sequenceNumber", paramNames, paramValues);
        if (filter.getPaymentStatus() != null) {
            Helper.appendCondition(sb, "e.paymentStatus.name = :paymentStatus", filter.getPaymentStatus().getName(),
                    "paymentStatus", paramNames, paramValues);
        }

        if (filter.getPaymentAppliance() != null) {
            Helper.appendCondition(sb, "e.paymentAppliance.name = :paymentAppliance", filter.getPaymentAppliance()
                    .getName(), "paymentAppliance", paramNames, paramValues);
        }

        Boolean resolvedSuspense = filter.getResolvedSuspense();
        if (resolvedSuspense != null) {
            sb.append(Helper.AND);
            sb.append("e.paymentType " + (resolvedSuspense ? " = " : "<>") + "'SUSPENDED_PAYMENT'");
        }

        return sb.toString();
    }

    /**
     * This is helper method used by search() method for converting ComparisonType enum to string representation used in
     * sql query.
     * 
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to be logged.
     * @param type
     *            comparison type to convert.
     * 
     * @return Correspondent string representation of type, can not be null/empty.
     * 
     * @throws OPMException
     *             if type is unknown.
     */
    private static String convertAmountComparisonType(Logger logger, String signature, ComparisonType type)
            throws OPMException {
        String value = COMPARISON_TYPE_MAP.get(type);

        if (value == null) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The type is unknown."));
        }

        return value;
    }

    /**
     * This is helper method used by search() method for converting DespoitComparisonType enum to string representation
     * used in sql query.
     * 
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to be logged.
     * @param type
     *            comparison type to convert.
     * 
     * @return correspondent string representation of type, can not be null/empty.
     * 
     * @throws OPMException
     *             if type is unknown.
     */
    private static String convertDepositComparisonType(Logger logger, String signature, DepositComparisonType type)
            throws OPMException {
        String value = DEPOSIT_COMPARISON_TYPE_MAP.get(type);

        if (value == null) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The type is unknown."));
        }

        return value;
    }
}
