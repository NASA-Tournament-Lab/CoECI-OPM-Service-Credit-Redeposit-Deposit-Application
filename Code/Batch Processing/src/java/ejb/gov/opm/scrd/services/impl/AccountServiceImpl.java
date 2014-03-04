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
import gov.opm.scrd.entities.application.AccountConfirmationValidation;
import gov.opm.scrd.entities.application.AccountHolder;
import gov.opm.scrd.entities.application.AccountNote;
import gov.opm.scrd.entities.application.AccountSearchFilter;
import gov.opm.scrd.entities.application.Billing;
import gov.opm.scrd.entities.application.BillingSummary;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.entities.lookup.CalculationEndDateCalculationType;
import gov.opm.scrd.services.AccountService;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.OPMException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

/**
 * <p>
 * This class is the implementation of the AccountService. It utilizes JPA EntityManager for necessary operations.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Release I Assembly 1.0):</em>
 * <ul>
 * <li>remove saveFERSDepositCalculationVersion, saveFERSRedepositCalculationVersion</li>
 * <li>add method saveCalculationVersion.</li>
 * <li>change saveBilling to adhere the Account entity.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, sparemax, bannie2492
 * @version 1.1
 */
@Stateless
@Local(AccountService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountServiceImpl extends BaseService implements AccountService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = AccountServiceImpl.class.getName();

    /**
     * <p>
     * Represents the days per month.
     * </p>
     */
    private static final int DAYS_PER_MONTH = 30;

    /**
     * <p>
     * Represents the JPQL to query Account by claim number.
     * </p>
     */
    private static final String JPQL_QUERY_ACCOUNT_BY_CLAIM_NUMBER = "SELECT e FROM Account e"
        + " WHERE e.deleted = false AND e.claimNumber = :claimNumber";

    /**
     * <p>
     * Represents the JPQL to query Account count by claim officer.
     * </p>
     */
    private static final String JPQL_QUERY_ACCOUNT_COUNT_BY_CLAIM_OFFICER = "SELECT COUNT(e) FROM Account e"
        + " WHERE e.deleted = false AND e.claimOfficer = :username";

    /**
     * <p>
     * Represents the JPQL to query AccountNote by account id.
     * </p>
     */
    private static final String JPQL_QUERY_NOTE_BY_ACCOUNT_ID = "SELECT e FROM AccountNote e"
        + " WHERE e.deleted = false AND e.accountId = :accountId";
    /**
     * <p>
     * Represents the SQL to query date calculation data value.
     * </p>
     */
    private static final String SQL_QUERY_DATE_CALCULATION_DATA_VALUE = "SELECT value FROM opm.date_calculation_data"
        + " WHERE deleted = false AND calculation_type=:type AND day_offset=:dayOffset AND month_offset=:monthOffset";
    /**
     * <p>
     * Represents the SQL to query Account.
     * </p>
     */
    private static final String SQL_QUERY_ACCOUNT = "SELECT e FROM Account e WHERE e.deleted = false";
    /**
     * <p>
     * Represents the SQL to query Account count.
     * </p>
     */
    private static final String SQL_QUERY_ACCOUNT_COUNT = "SELECT COUNT(e) FROM Account e WHERE e.deleted = false";

    /**
     * Creates an instance of AccountServiceImpl.
     */
    public AccountServiceImpl() {
        // Empty
    }

    /**
     * Creates the account.
     *
     * @param account
     *            the account to create.
     *
     * @return The id of the created account instance.
     *
     * @throws IllegalArgumentException
     *             if account is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long create(Account account) throws OPMException {
        String signature = CLASS_NAME + "#create(Account account)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"account"},
            new Object[] {account});

        Helper.checkNull(logger, signature, account, "account");

        try {
            getEntityManager().persist(account);

            long result = account.getId();
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
     * Updates the account.
     *
     * @param account
     *            the account to update.
     *
     * @throws IllegalArgumentException
     *             if account is null.
     * @throws EntityNotFoundException
     *             if there is no such account to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Account account) throws OPMException {
        String signature = CLASS_NAME + "#create(Account account)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"account"},
            new Object[] {account});

        Helper.checkNull(logger, signature, account, "account");

        EntityManager entityManager = getEntityManager();
        try {
            // Check the Account
            Helper.getEntityById(entityManager, logger, signature, Account.class, account.getId(), true);

            entityManager.merge(account);

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
     * Updates the employee data of the account.
     *
     * @param account
     *            the account to update.
     *
     * @throws IllegalArgumentException
     *             if account is null.
     * @throws EntityNotFoundException
     *             if there is no such account to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateEmployee(Account account) throws OPMException {
        String signature = CLASS_NAME + "#create(Account account)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"account"},
            new Object[] {account});

        Helper.checkNull(logger, signature, account, "account");

        EntityManager entityManager = getEntityManager();
        try {
            // Check the Account
            Helper.getEntityById(entityManager, logger, signature, Account.class, account.getId(), true);

            AccountHolder holder = account.getHolder();
            Helper.checkFieldNull(logger, signature, holder, "account.getHolder()");

            getEntityManager().merge(holder);

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
     * Deletes the account by id.
     *
     * @param accountId
     *            the account id to update.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such account to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(long accountId) throws OPMException {
        String signature = CLASS_NAME + "#delete(long accountId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"accountId"},
            new Object[] {accountId});

        Helper.checkPositive(logger, signature, accountId, "accountId");

        EntityManager entityManager = getEntityManager();
        try {
            // Get the Account
            Account account = Helper.getEntityById(entityManager, logger, signature, Account.class, accountId, true);
            account.setDeleted(true);

            entityManager.merge(account);

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
     * Gets the account by id.
     *
     * @param accountId
     *            the account id to get.
     *
     * @return The account for the id or null if it can not be found.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Account get(long accountId) throws OPMException {
        String signature = CLASS_NAME + "#get(long accountId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"accountId"},
            new Object[] {accountId});

        Helper.checkPositive(logger, signature, accountId, "accountId");

        try {
            // Get account
            Account result = Helper.getEntityById(getEntityManager(), logger, signature, Account.class, accountId,
                false);

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
     * Gets the account by claim number.
     *
     * @param claimNumber
     *            the claim number to get account.
     *
     * @return The account for the claim number or null if it can not be found.
     *
     * @throws IllegalArgumentException
     *             if claimNumber is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Account getByClaimNumber(String claimNumber) throws OPMException {
        String signature = CLASS_NAME + "#getByClaimNumber(String claimNumber)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"claimNumber"},
            new Object[] {claimNumber});

        Helper.checkNullOrEmpty(logger, signature, claimNumber, "claimNumber");

        try {
            Account result = Helper.getValue(getEntityManager(), logger, signature, Account.class,
                JPQL_QUERY_ACCOUNT_BY_CLAIM_NUMBER, new String[] {"claimNumber"}, new Object[] {claimNumber}, false);

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
     * Searches accounts based on the filter.
     *
     * @param filter
     *            the filter to search account.
     *
     * @return SearchResult&lt;Account&gt; instance holding information about search result.
     *
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Account> search(AccountSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#search(AccountSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"filter"},
            new Object[] {filter});

        Helper.checkNull(logger, signature, filter, "filter");

        try {
            String orderByClause = Helper.buildOrderBy(filter);

            List<String> paramNames = new ArrayList<String>();
            List<Object> paramValues = new ArrayList<Object>();
            String whereClause = buildWhere(filter, paramNames, paramValues);

            EntityManager entityManager = getEntityManager();

            // Create query
            TypedQuery<Account> query = entityManager.createQuery(SQL_QUERY_ACCOUNT + whereClause + orderByClause,
                Account.class);
            Helper.setParameters(query, paramNames, paramValues);

            int pageNumber = filter.getPageNumber();
            int pageSize = filter.getPageSize();
            // Set paging
            if (pageNumber > 0) {
                query.setMaxResults(pageSize);
                query.setFirstResult((pageNumber - 1) * pageSize);
            }

            List<Account> records = query.getResultList();

            SearchResult<Account> result = new SearchResult<Account>();
            result.setItems(records);

            if (pageNumber > 0) {
                // Create query
                TypedQuery<Number> countQuery = entityManager.createQuery(SQL_QUERY_ACCOUNT_COUNT + whereClause,
                    Number.class);
                Helper.setParameters(countQuery, paramNames, paramValues);

                int totalCount = countQuery.getSingleResult().intValue();
                result.setTotal(totalCount);
                result.setTotalPageCount((totalCount + pageSize - 1) / pageSize);
            } else {
                result.setTotal(records.size());
                result.setTotalPageCount(records.isEmpty() ? 0 : 1);
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
     * Adds the note for the account.
     *
     * @param accountId
     *            the id of account to add note.
     * @param note
     *            the account note to add.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive or note is null.
     * @throws EntityNotFoundException
     *             if there is no such account to add note.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addNote(long accountId, AccountNote note) throws OPMException {
        String signature = CLASS_NAME + "#addNote(long accountId, AccountNote note)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"accountId", "note"},
            new Object[] {accountId, note});

        Helper.checkPositive(logger, signature, accountId, "accountId");
        Helper.checkNull(logger, signature, note, "note");

        EntityManager entityManager = getEntityManager();

        try {
            // Get the Account
            Account account = Helper.getEntityById(entityManager, logger, signature, Account.class, accountId, true);

            note.setAccountId(accountId);
            entityManager.persist(note);

            account.getNotes().add(note);
            entityManager.merge(account);

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
     * Adds the note for the account giving account claim number.
     *
     * @param claimNumber
     *            the claimNumber of account to add note.
     * @param note
     *            the account note to add.
     *
     * @throws IllegalArgumentException
     *             if claimNumber is null/empty or note is null.
     * @throws EntityNotFoundException
     *             if there is no such account to add note.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addNoteByClaimNumber(String claimNumber, AccountNote note) throws OPMException {
        String signature = CLASS_NAME + "#addNoteByClaimNumber(String claimNumber, AccountNote note)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"claimNumber", "note"},
            new Object[] {claimNumber, note});

        Helper.checkNullOrEmpty(logger, signature, claimNumber, "claimNumber");
        Helper.checkNull(logger, signature, note, "note");

        EntityManager entityManager = getEntityManager();
        try {
            Account account = Helper.getValue(entityManager, logger, signature, Account.class,
                JPQL_QUERY_ACCOUNT_BY_CLAIM_NUMBER, new String[] {"claimNumber"}, new Object[] {claimNumber}, true);

            note.setAccountId(account.getId());
            entityManager.persist(note);

            account.getNotes().add(note);
            entityManager.merge(account);

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
     * Updates account note.
     *
     * @param note
     *            the account note to update.
     *
     * @throws IllegalArgumentException
     *             if note is null.
     * @throws EntityNotFoundException
     *             if there is no such note to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateNote(AccountNote note) throws OPMException {
        String signature = CLASS_NAME + "#updateNote(AccountNote note)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"note"},
            new Object[] {note});

        Helper.checkNull(logger, signature, note, "note");

        EntityManager entityManager = getEntityManager();
        try {
            // Check note
            Helper.getEntityById(entityManager, logger, signature, AccountNote.class, note.getId(), true);

            entityManager.merge(note);
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
     * Deletes account note.
     *
     * @param note
     *            the account note to delete.
     *
     * @throws IllegalArgumentException
     *             if note is null.
     * @throws EntityNotFoundException
     *             if there is no such note to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteNote(AccountNote note) throws OPMException {
        String signature = CLASS_NAME + "#deleteNote(AccountNote note)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"note"},
            new Object[] {note});

        Helper.checkNull(logger, signature, note, "note");

        EntityManager entityManager = getEntityManager();
        try {
            // Check note
            Helper.getEntityById(entityManager, logger, signature, AccountNote.class, note.getId(), true);

            entityManager.remove(note);

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
     * Retrieves all notes for the account given account id.
     *
     * @param accountId
     *            the id of account to retrieve notes.
     *
     * @return List of account notes for the account, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<AccountNote> getNotes(long accountId) throws OPMException {
        String signature = CLASS_NAME + "#getNotes(long accountId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"accountId"},
            new Object[] {accountId});

        Helper.checkPositive(logger, signature, accountId, "accountId");

        try {
            List<AccountNote> result = Helper.getValues(getEntityManager(), logger, signature, AccountNote.class,
                JPQL_QUERY_NOTE_BY_ACCOUNT_ID, new String[] {"accountId"}, new Object[] {accountId});

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
     * Saves calculation version for the account.
     *
     * @param accountId
     *            the id of account to save calculation version.
     * @param calculationVersion
     *            the calculation version to save.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive or calculationVersion is null.
     * @throws EntityNotFoundException
     *             if there is no such account to save data.
     * @throws OPMException
     *             if there is any problem when executing the method.
     *
     * @since 1.1 (OPM  - Interest Update Assembly 1.0)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveCalculationVersion(long accountId, CalculationVersion calculationVersion)
        throws OPMException {
        String signature = CLASS_NAME
            + "#saveCalculationVersion(long accountId, CalculationVersion calculationVersion)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"accountId", "calculationVersion"},
            new Object[] {accountId, calculationVersion});

        Helper.checkPositive(logger, signature, accountId, "accountId");
        Helper.checkNull(logger, signature, calculationVersion, "calculationVersion");

        EntityManager entityManager = getEntityManager();
        try {
            // Get account
            Account account = Helper.getEntityById(entityManager, logger, signature, Account.class, accountId, true);

            if (calculationVersion.getId() != 0) {
                for (int i = 0; i < account.getCalculationVersions().size(); i++) {
                    if (account.getCalculationVersions().get(i).getId() == calculationVersion.getId()) {
                        account.getCalculationVersions().set(i, calculationVersion);
                    }
                }
            } else {
                account.getCalculationVersions().add(calculationVersion);
            }
            entityManager.merge(account);

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
     * Deletes the calculation version for the id.
     *
     * @param calculationVersionId
     *            the id of calculation version to delete.
     *
     * @throws IllegalArgumentException
     *             if calculationVersionId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such calculation version to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCalculationVersion(long calculationVersionId) throws OPMException {
        String signature = CLASS_NAME + "#deleteCalculationVersion(long calculationVersionId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"calculationVersionId"},
            new Object[] {calculationVersionId});

        Helper.checkPositive(logger, signature, calculationVersionId, "calculationVersionId");

        EntityManager entityManager = getEntityManager();
        try {
            // Get calculation version
            CalculationVersion calculationVersion = Helper.getEntityById(entityManager, logger, signature,
                CalculationVersion.class, calculationVersionId, true);

            entityManager.remove(calculationVersion);

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
     * Saves the billings data.
     *
     * @param accountId
     *            the id of account to save billing data
     * @param billings
     *            the billings data to save.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive or billings is null or contains null elements.
     * @throws EntityNotFoundException
     *             if there is no such note to save billings.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveBillings(long accountId, List<Billing> billings) throws OPMException {
        String signature = CLASS_NAME + "#saveBillings(long accountId, List<Billing> billings)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"accountId", "billings"},
            new Object[] {accountId, billings});

        Helper.checkPositive(logger, signature, accountId, "accountId");
        Helper.checkList(logger, signature, billings, "billings");

        EntityManager entityManager = getEntityManager();
        try {
            // Get the Account
            Account account = Helper.getEntityById(entityManager, logger, signature, Account.class, accountId, true);

            Date calculationDate = null;

            for (CalculationVersion version : account.getCalculationVersions()) {
                if (version.getCalculationResult().isOfficial()) {
                    calculationDate = version.getCalculationDate();
                    break;
                }
            }

            if (calculationDate == null) {
                throw LoggingHelper.logException(logger, signature, new OPMException(
                    "No official calculation date when saving billings for accountId=" + accountId));
            }

            BillingSummary summary = new BillingSummary();
            summary.setComputedDate(calculationDate);
            summary.setLastDepositDate(null);
            summary.setFirstBillingDate(new Date());
            summary.setLastInterestCalculation(calculationDate);
            summary.setTransactionType("Billing");
            summary.setLastTransactionDate(calculationDate);
            summary.setBillings(billings);
            summary.setStopACHPayments(null);

            account.setBillingSummary(summary);

            entityManager.merge(account);

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
     * Approves the account.
     *
     * @param validity
     *            the information about account approval.
     *
     * @throws IllegalArgumentException
     *             if validity is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void approve(AccountConfirmationValidation validity) throws OPMException {
        String signature = CLASS_NAME + "#approve(AccountConfirmationValidation validity)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"validity"},
            new Object[] {validity});

        Helper.checkNull(logger, signature, validity, "validity");

        try {
            validity.setDataCheckStatus(ApprovalStatus.APPROVED);

            getEntityManager().persist(validity);

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
     * Rejects the account.
     *
     * @param validity
     *            the information about account rejection.
     *
     * @throws IllegalArgumentException
     *             if validity is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void reject(AccountConfirmationValidation validity) throws OPMException {
        String signature = CLASS_NAME + "#reject(AccountConfirmationValidation validity)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"validity"},
            new Object[] {validity});

        Helper.checkNull(logger, signature, validity, "validity");

        try {
            validity.setDataCheckStatus(ApprovalStatus.DISAPPROVED);

            getEntityManager().persist(validity);

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
     * Updates account interest.
     *
     * @param accountId
     *            the id of account to update interest.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such account to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateInterest(long accountId) throws OPMException {
        String signature = CLASS_NAME + "#updateInterest(long accountId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"accountId"},
            new Object[] {accountId});

        Helper.checkPositive(logger, signature, accountId, "accountId");

        EntityManager entityManager = getEntityManager();

        try {
            // Get the Account
            Helper.getEntityById(entityManager, logger, signature, Account.class, accountId, true);

            // Left for final fixes

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
     * Retrieves the count of accounts assigned to the user.
     *
     * @param username
     *            the name of user to count accounts for.
     *
     * @return Number of accounts assigned to the user, can be 0 or positive integer.
     *
     * @throws IllegalArgumentException
     *             if username is null/empty
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public int countAccountsAssignedToUser(String username) throws OPMException {
        String signature = CLASS_NAME + "#countAccountsAssignedToUser(String username)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"username"},
            new Object[] {username});

        Helper.checkNullOrEmpty(logger, signature, username, "username");

        try {
            int result = Helper.getValue(getEntityManager(), logger, signature, Long.class,
                JPQL_QUERY_ACCOUNT_COUNT_BY_CLAIM_OFFICER, new String[] {"username"}, new Object[] {username}, false)
                .intValue();

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
     * Assigns account to the claim officer.
     *
     * @param accountId
     *            the id of account to assign claim officer.
     * @param claimOfficer
     *            the name of the claim officer to assign account.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive or claimOfficer is null/empty.
     * @throws EntityNotFoundException
     *             if there is no such account to assign.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void assignAccount(long accountId, String claimOfficer) throws OPMException {
        String signature = CLASS_NAME + "#assignAccount(long accountId, String claimOfficer)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"accountId", "claimOfficer"},
            new Object[] {accountId, claimOfficer});

        Helper.checkPositive(logger, signature, accountId, "accountId");
        Helper.checkNullOrEmpty(logger, signature, claimOfficer, "claimOfficer");

        EntityManager entityManager = getEntityManager();
        try {
            // GEt the Account
            Account account = Helper.getEntityById(entityManager, logger, signature, Account.class, accountId, true);

            account.setClaimOfficer(claimOfficer);
            account.setClaimOfficerAssignmentDate(new Date());

            entityManager.merge(account);

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
     * Unassigns account from claim officer.
     *
     * @param accountId
     *            the id of account to unassign from claim officer.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such account to unassign.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void unassignAccount(long accountId) throws OPMException {
        String signature = CLASS_NAME + "#unassignAccount(long accountId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"accountId"},
            new Object[] {accountId});

        Helper.checkPositive(logger, signature, accountId, "accountId");

        EntityManager entityManager = getEntityManager();
        try {
            // GEt the Account
            Account account = Helper.getEntityById(entityManager, logger, signature, Account.class, accountId, true);

            account.setClaimOfficer(null);
            account.setClaimOfficerAssignmentDate(null);

            entityManager.merge(account);

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
     * Calculates the end date for the given time value.
     *
     * @param value
     *            the value of the time to calculate.
     * @param type
     *            the calculation type of the operation.
     *
     * @return The end date for the given calculation type and value, can not be null.
     *
     * @throws IllegalArgumentException
     *             if value is null/negative integer, type is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Date calculateEndDate(Integer value, CalculationEndDateCalculationType type) throws OPMException {
        String signature = CLASS_NAME + "#calculateEndDate(Integer value, CalculationEndDateCalculationType type)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"value", "type"},
            new Object[] {value, type});

        Helper.checkNull(logger, signature, value, "value");
        if (value < 0) {
            // Log exception
            throw LoggingHelper.logException(logger, signature, new IllegalArgumentException(
                "'value' should not be negative."));
        }

        Helper.checkNull(logger, signature, type, "type");

        try {
            int daysValue = ((Number) getEntityManager().createNativeQuery(SQL_QUERY_DATE_CALCULATION_DATA_VALUE)
                .setParameter("type", type.name()).setParameter("dayOffset", value % DAYS_PER_MONTH)
                .setParameter("monthOffset", value / DAYS_PER_MONTH).getSingleResult()).intValue();

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, daysValue);

            Date result = calendar.getTime();
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
     * Builds the WHERE string.
     *
     * @param filter
     *            the filter
     * @param paramNames
     *            the parameter name
     * @param paramValues
     *            the parameter values
     *
     * @return the WHERE string.
     */
    private static String buildWhere(AccountSearchFilter filter, List<String> paramNames, List<Object> paramValues) {
        StringBuilder sb = new StringBuilder();

        Helper.appendCondition(sb, "e.claimNumber LIKE :claimNumber", filter.getClaimNumber(), "claimNumber",
            paramNames, paramValues);
        Helper.appendCondition(sb, "e.holder.ssn LIKE :ssn", filter.getSsn(), "ssn", paramNames, paramValues);
        Helper.appendCondition(sb, "e.holder.firstName LIKE :firstName", filter.getFirstName(), "firstName",
            paramNames, paramValues);
        Helper.appendCondition(sb, "e.holder.middleInitial LIKE :middleName", filter.getMiddleName(), "middleName",
            paramNames, paramValues);
        Helper.appendCondition(sb, "e.holder.lastName LIKE :lastName", filter.getLastName(), "lastName",
            paramNames, paramValues);
        Helper.appendCondition(sb, "e.holder.birthDate = :birthDate", filter.getBirthDate(), "birthDate", paramNames,
            paramValues);

        Boolean assigned = filter.getAssigned();
        if (assigned != null) {
            sb.append(Helper.AND);

            if (assigned) {
                sb.append("e.claimOfficer IS NOT NULL");
            } else {
                sb.append("e.claimOfficer IS NULL");
            }
        }

        return sb.toString();
    }
}
