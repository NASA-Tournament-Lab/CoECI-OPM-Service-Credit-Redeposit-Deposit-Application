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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountConfirmationValidation;
import gov.opm.scrd.entities.application.AccountNote;
import gov.opm.scrd.entities.application.AccountSearchFilter;
import gov.opm.scrd.entities.application.Billing;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.entities.lookup.CalculationEndDateCalculationType;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.OPMException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link AccountServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class AccountServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>AccountServiceImpl</code> instance used in tests.
     * </p>
     */
    private AccountServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the account used in tests.
     * </p>
     */
    private Account account;

    /**
     * <p>
     * Represents the note used in tests.
     * </p>
     */
    private AccountNote note;

    /**
     * <p>
     * Represents the calculation version used in tests.
     * </p>
     */
    private CalculationVersion calculationVersion;

    /**
     * <p>
     * Represents the filter used in tests.
     * </p>
     */
    private AccountSearchFilter filter;

    /**
     * <p>
     * Represents the billings used in tests.
     * </p>
     */
    private List<Billing> billings;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AccountServiceImplUnitTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        entityManager = getEntityManager();

        logger = Logger.getLogger(getClass());

        instance = new AccountServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);

        account = getAccount();

        note = getAccountNote();

        calculationVersion = getCalculationVersion();

        filter = new AccountSearchFilter();

        billings = new ArrayList<Billing>();
        billings.add(getBilling());
        billings.add(getBilling());
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AccountServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AccountServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>create(Account account)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_create() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertTrue("'create' should be correct.", account.getId() > 0);

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertFalse("'create' should be correct.", retrievedAccount.isDeleted());
        assertEquals("'create' should be correct.", account.getClaimNumber(), retrievedAccount.getClaimNumber());
        assertEquals("'create' should be correct.", account.getPlanType(), retrievedAccount.getPlanType());
        assertEquals("'create' should be correct.", account.getFormType().getId(), retrievedAccount.getFormType()
            .getId());
        assertEquals("'create' should be correct.", account.getHolder().getId(), retrievedAccount.getHolder().getId());
        assertEquals("'create' should be correct.", account.getStatus().getId(), retrievedAccount.getStatus().getId());
        assertEquals("'create' should be correct.", account.isGrace(), retrievedAccount.isGrace());
        assertEquals("'create' should be correct.", account.isFrozen(), retrievedAccount.isFrozen());
        assertEquals("'create' should be correct.", account.getClaimOfficer(), retrievedAccount.getClaimOfficer());
        assertNotNull("'create' should be correct.", retrievedAccount.getClaimOfficerAssignmentDate());
        assertNotNull("'create' should be correct.", retrievedAccount.getClaimantBirthdate());
        assertEquals("'create' should be correct.", account.getBalance().intValue(), retrievedAccount.getBalance()
            .intValue());
        assertNotNull("'create' should be correct.", retrievedAccount.getReturnedFromRecordsDate());
        assertEquals("'create' should be correct.", account.getCalculationVersions().size(),
            retrievedAccount.getCalculationVersions().size());
    }

    /**
     * <p>
     * Failure test for the method <code>create(Account account)</code> with account is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_create_accountNull() throws Exception {
        instance.create(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>update(Account account)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_update() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        account.setClaimNumber("new");
        entityManager.getTransaction().begin();
        instance.update(account);
        entityManager.getTransaction().commit();

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertEquals("'update' should be correct.", account.getClaimNumber(), retrievedAccount.getClaimNumber());
    }

    /**
     * <p>
     * Failure test for the method <code>update(Account account)</code> with account is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_update_accountNull() throws Exception {
        instance.update(null);
    }

    /**
     * <p>
     * Failure test for the method <code>update(Account account)</code> with account doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_update_accountNotExist() throws Exception {
        account.setId(Long.MAX_VALUE);

        instance.update(account);
    }

    /**
     * <p>
     * Accuracy test for the method <code>updateEmployee(Account account)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_updateEmployee() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        account.getHolder().setFirstName("new");
        entityManager.getTransaction().begin();
        instance.updateEmployee(account);
        entityManager.getTransaction().commit();

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertEquals("'updateEmployee' should be correct.", account.getHolder().getFirstName(), retrievedAccount
            .getHolder().getFirstName());
    }

    /**
     * <p>
     * Failure test for the method <code>updateEmployee(Account account)</code> with account is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_updateEmployee_accountNull() throws Exception {
        instance.updateEmployee(null);
    }

    /**
     * <p>
     * Failure test for the method <code>updateEmployee(Account account)</code> with account doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_updateEmployee_accountNotExist() throws Exception {
        account.setId(Long.MAX_VALUE);

        instance.updateEmployee(account);
    }

    /**
     * <p>
     * Failure test for the method <code>updateEmployee(Account account)</code> with account.getHolder() is null.<br>
     * <code>OPMException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMException.class)
    public void test_updateEmployee_accountHolderNull() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        account.setHolder(null);
        instance.updateEmployee(account);
    }

    /**
     * <p>
     * Accuracy test for the method <code>delete(long accountId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_delete() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.delete(account.getId());
        entityManager.getTransaction().commit();

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertTrue("'delete' should be correct.", retrievedAccount.isDeleted());
    }

    /**
     * <p>
     * Failure test for the method <code>delete(long accountId)</code> with accountId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_delete_accountIdNegative() throws Exception {
        instance.delete(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>delete(long accountId)</code> with accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_delete_accountIdZero() throws Exception {
        instance.delete(0);
    }

    /**
     * <p>
     * Failure test for the method <code>delete(long accountId)</code> with account doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_delete_accountNotExist() throws Exception {
        instance.delete(Long.MAX_VALUE);
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long accountId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_1() throws Exception {
        Account res = instance.get(Long.MAX_VALUE);

        assertNull("'get' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long accountId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_2() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Account res = instance.get(account.getId());

        assertFalse("'get' should be correct.", res.isDeleted());
        assertEquals("'get' should be correct.", account.getClaimNumber(), res.getClaimNumber());
        assertEquals("'get' should be correct.", account.getPlanType(), res.getPlanType());
        assertEquals("'get' should be correct.", account.getFormType().getId(), res.getFormType().getId());
        assertEquals("'get' should be correct.", account.getHolder().getId(), res.getHolder().getId());
        assertEquals("'get' should be correct.", account.getStatus().getId(), res.getStatus().getId());
        assertEquals("'get' should be correct.", account.isGrace(), res.isGrace());
        assertEquals("'get' should be correct.", account.isFrozen(), res.isFrozen());
        assertEquals("'get' should be correct.", account.getClaimOfficer(), res.getClaimOfficer());
        assertNotNull("'get' should be correct.", res.getClaimOfficerAssignmentDate());
        assertNotNull("'get' should be correct.", res.getClaimantBirthdate());
        assertEquals("'get' should be correct.", account.getBalance().intValue(), res.getBalance().intValue());
        assertNotNull("'get' should be correct.", res.getReturnedFromRecordsDate());
        assertEquals("'get' should be correct.", account.getCalculationVersions().size(), res
            .getCalculationVersions().size());
    }

    /**
     * <p>
     * Failure test for the method <code>get(long accountId)</code> with accountId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_get_accountIdNegative() throws Exception {
        instance.get(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>get(long accountId)</code> with accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_get_accountIdZero() throws Exception {
        instance.get(0);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getByClaimNumber(String claimNumber)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getByClaimNumber_1() throws Exception {
        Account res = instance.getByClaimNumber("not_\n_exist");

        assertNull("'getByClaimNumber' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getByClaimNumber(String claimNumber)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getByClaimNumber_2() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Account res = instance.getByClaimNumber(account.getClaimNumber());

        assertFalse("'getByClaimNumber' should be correct.", res.isDeleted());
        assertEquals("'getByClaimNumber' should be correct.", account.getClaimNumber(), res.getClaimNumber());
        assertEquals("'getByClaimNumber' should be correct.", account.getPlanType(), res.getPlanType());
        assertEquals("'getByClaimNumber' should be correct.", account.getFormType().getId(), res.getFormType().getId());
        assertEquals("'getByClaimNumber' should be correct.", account.getHolder().getId(), res.getHolder().getId());
        assertEquals("'getByClaimNumber' should be correct.", account.getStatus().getId(), res.getStatus().getId());
        assertEquals("'getByClaimNumber' should be correct.", account.isGrace(), res.isGrace());
        assertEquals("'getByClaimNumber' should be correct.", account.isFrozen(), res.isFrozen());
        assertEquals("'getByClaimNumber' should be correct.", account.getClaimOfficer(), res.getClaimOfficer());
        assertNotNull("'getByClaimNumber' should be correct.", res.getClaimOfficerAssignmentDate());
        assertNotNull("'getByClaimNumber' should be correct.", res.getClaimantBirthdate());
        assertEquals("'getByClaimNumber' should be correct.", account.getBalance().intValue(), res.getBalance()
            .intValue());
        assertNotNull("'getByClaimNumber' should be correct.", res.getReturnedFromRecordsDate());
        assertEquals("'getByClaimNumber' should be correct.", account.getCalculationVersions().size(), res
            .getCalculationVersions().size());
    }

    /**
     * <p>
     * Failure test for the method <code>getByClaimNumber(String claimNumber)</code> with claimNumber is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getByClaimNumber_claimNumberNull() throws Exception {
        instance.getByClaimNumber(null);
    }

    /**
     * <p>
     * Failure test for the method <code>getByClaimNumber(String claimNumber)</code> with claimNumber is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getByClaimNumber_claimNumberEmpty() throws Exception {
        instance.getByClaimNumber(TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(AccountSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_1() throws Exception {
        clearDB();

        SearchResult<Account> res = instance.search(filter);

        assertEquals("'search' should be correct.", 0, res.getTotal());
        assertEquals("'search' should be correct.", 0, res.getTotalPageCount());

        List<Account> items = res.getItems();
        assertEquals("'search' should be correct.", 0, items.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(AccountSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_2() throws Exception {
        account.setDeleted(true);
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        SearchResult<Account> res = instance.search(filter);

        assertEquals("'search' should be correct.", 0, res.getTotal());
        assertEquals("'search' should be correct.", 0, res.getTotalPageCount());

        List<Account> items = res.getItems();
        assertEquals("'search' should be correct.", 0, items.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(AccountSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_3() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        SearchResult<Account> res = instance.search(filter);

        assertEquals("'search' should be correct.", 1, res.getTotal());
        assertEquals("'search' should be correct.", 1, res.getTotalPageCount());

        List<Account> items = res.getItems();
        assertEquals("'search' should be correct.", 1, items.size());
        Account item = items.get(0);
        assertEquals("'search' should be correct.", account.getId(), item.getId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(AccountSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_4() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        filter.setClaimNumber(account.getClaimNumber());
        filter.setSsn(account.getHolder().getSsn());
        filter.setFirstName(account.getHolder().getFirstName());
        filter.setMiddleName(account.getHolder().getMiddleInitial());
        filter.setLastName(account.getHolder().getLastName());
        SearchResult<Account> res = instance.search(filter);

        assertEquals("'search' should be correct.", 1, res.getTotal());
        assertEquals("'search' should be correct.", 1, res.getTotalPageCount());

        List<Account> items = res.getItems();
        assertEquals("'search' should be correct.", 1, items.size());
        Account item = items.get(0);
        assertEquals("'search' should be correct.", account.getId(), item.getId());
    }

    /**
     * <p>
     * Failure test for the method <code>search(AccountSearchFilter filter)</code> with filter is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_search_filterNull() throws Exception {
        instance.search(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>addNote(long accountId, AccountNote note)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_addNote() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.addNote(account.getId(), note);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertTrue("'addNote' should be correct.", note.getId() > 0);

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertEquals("'addNote' should be correct.", 1, retrievedAccount.getNotes().size());
        assertEquals("'addNote' should be correct.", note.getId(), retrievedAccount.getNotes().get(0).getId());
        assertEquals("'addNote' should be correct.",
            account.getId(), retrievedAccount.getNotes().get(0).getAccountId());
    }

    /**
     * <p>
     * Failure test for the method <code>addNote(long accountId, AccountNote note)</code> with accountId is
     * negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addNote_accountIdNegative() throws Exception {
        instance.addNote(-1, note);
    }

    /**
     * <p>
     * Failure test for the method <code>addNote(long accountId, AccountNote note)</code> with accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addNote_accountIdZero() throws Exception {
        instance.addNote(0, note);
    }

    /**
     * <p>
     * Failure test for the method <code>addNote(long accountId, AccountNote note)</code> with note is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addNote_noteNull() throws Exception {
        instance.addNote(1, null);
    }

    /**
     * <p>
     * Failure test for the method <code>addNote(long accountId, AccountNote note)</code> with account doesn't
     * exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_addNote_accountNotExist() throws Exception {
        instance.addNote(Long.MAX_VALUE, note);
    }

    /**
     * <p>
     * Accuracy test for the method <code>addNoteByClaimNumber(String claimNumber, AccountNote note)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_addNoteByClaimNumber() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.addNoteByClaimNumber(account.getClaimNumber(), note);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertTrue("'addNoteByClaimNumber' should be correct.", note.getId() > 0);

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertEquals("'addNoteByClaimNumber' should be correct.", 1, retrievedAccount.getNotes().size());
        assertEquals("'addNoteByClaimNumber' should be correct.", note.getId(), retrievedAccount.getNotes().get(0)
            .getId());
        assertEquals("'addNoteByClaimNumber' should be correct.", account.getId(), retrievedAccount.getNotes().get(0)
            .getAccountId());
    }

    /**
     * <p>
     * Failure test for the method <code>addNoteByClaimNumber(String claimNumber, AccountNote note)</code> with
     * claimNumber is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addNoteByClaimNumber_claimNumberNull() throws Exception {
        instance.addNoteByClaimNumber(null, note);
    }

    /**
     * <p>
     * Failure test for the method <code>addNoteByClaimNumber(String claimNumber, AccountNote note)</code> with
     * claimNumber is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addNoteByClaimNumber_claimNumberEmpty() throws Exception {
        instance.addNoteByClaimNumber(TestsHelper.EMPTY_STRING, note);
    }

    /**
     * <p>
     * Failure test for the method <code>addNoteByClaimNumber(String claimNumber, AccountNote note)</code> with note is
     * null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addNoteByClaimNumber_noteNull() throws Exception {
        instance.addNoteByClaimNumber("claimNumber", null);
    }

    /**
     * <p>
     * Failure test for the method <code>addNoteByClaimNumber(String claimNumber, AccountNote note)</code> with account
     * doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_addNoteByClaimNumber_accountNotExist() throws Exception {
        instance.addNoteByClaimNumber("note_\n_exist", note);
    }

    /**
     * <p>
     * Accuracy test for the method <code>updateNote(AccountNote note)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_updateNote() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.addNote(account.getId(), note);
        entityManager.getTransaction().commit();

        note.setText("new");
        entityManager.getTransaction().begin();
        instance.updateNote(note);
        entityManager.getTransaction().commit();

        AccountNote retrievedNote = entityManager.find(AccountNote.class, note.getId());

        assertEquals("'updateNote' should be correct.", note.getText(), retrievedNote.getText());
    }

    /**
     * <p>
     * Failure test for the method <code>updateNote(AccountNote note)</code> with note is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_updateNote_noteNull() throws Exception {
        instance.updateNote(null);
    }

    /**
     * <p>
     * Failure test for the method <code>updateNote(AccountNote note)</code> with note doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_updateNote_noteNotExist() throws Exception {
        note.setId(Long.MAX_VALUE);

        instance.updateNote(note);
    }

    /**
     * <p>
     * Accuracy test for the method <code>deleteNote(AccountNote note)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_deleteNote() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.addNote(account.getId(), note);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        instance.deleteNote(note);
        entityManager.getTransaction().commit();
        entityManager.clear();

        AccountNote retrievedNote = entityManager.find(AccountNote.class, note.getId());
        assertNull("'deleteNote' should be correct.", retrievedNote);

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertEquals("'deleteNote' should be correct.", 0, retrievedAccount.getNotes().size());
    }

    /**
     * <p>
     * Failure test for the method <code>deleteNote(AccountNote note)</code> with note is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_deleteNote_noteNull() throws Exception {
        instance.deleteNote(null);
    }

    /**
     * <p>
     * Failure test for the method <code>deleteNote(AccountNote note)</code> with note doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_deleteNote_noteNotExist() throws Exception {
        note.setId(Long.MAX_VALUE);

        instance.deleteNote(note);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNotes(long accountId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getNotes_1() throws Exception {
        clearDB();

        List<AccountNote> notes = instance.getNotes(Long.MAX_VALUE);

        assertEquals("'getNotes' should be correct.", 0, notes.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNotes(long accountId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getNotes_2() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.addNote(account.getId(), note);
        entityManager.getTransaction().commit();

        List<AccountNote> notes = instance.getNotes(account.getId());

        assertEquals("'getNotes' should be correct.", 1, notes.size());
        AccountNote retrievedNote = notes.get(0);
        assertNotNull("'getNotes' should be correct.", retrievedNote.getDate());
        assertEquals("'getNotes' should be correct.", note.getWriter(), retrievedNote.getWriter());
        assertEquals("'getNotes' should be correct.", note.getText(), retrievedNote.getText());
        assertEquals("'getNotes' should be correct.", note.getAccountId(), retrievedNote.getAccountId());
    }

    /**
     * <p>
     * Failure test for the method <code>getNotes(long accountId)</code> with accountId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getNotes_accountIdNegative() throws Exception {
        instance.getNotes(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>getNotes(long accountId)</code> with accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getNotes_accountIdZero() throws Exception {
        instance.getNotes(0);
    }

    /**
     * <p>
     * Accuracy test for the method <code>saveCalculationVersion(long accountId,
     * CalculationVersion calculationVersion)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_saveCalculationVersion() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.saveCalculationVersion(account.getId(), calculationVersion);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertEquals("'saveCalculationVersion' should be correct.", 1, retrievedAccount
            .getCalculationVersions().size());
        assertTrue("'saveCalculationVersion' should be correct.", retrievedAccount
            .getCalculationVersions().get(0).getId() > 0);
    }

    /**
     * <p>
     * Failure test for the method <code>saveCalculationVersion(long accountId,
     * CalculationVersion calculationVersion)</code> with accountId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_saveCalculationVersion_accountIdNegative() throws Exception {
        instance.saveCalculationVersion(-1, calculationVersion);
    }

    /**
     * <p>
     * Failure test for the method <code>saveCalculationVersion(long accountId,
     * CalculationVersion calculationVersion)</code> with accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_saveCalculationVersion_accountIdZero() throws Exception {
        instance.saveCalculationVersion(0, calculationVersion);
    }

    /**
     * <p>
     * Failure test for the method <code>saveCalculationVersion(long accountId,
     * CalculationVersion calculationVersion)</code> with calculationVersion is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_saveCalculationVersion_calculationVersionNull() throws Exception {
        instance.saveCalculationVersion(1, null);
    }

    /**
     * <p>
     * Failure test for the method <code>saveCalculationVersion(long accountId,
     * CalculationVersion calculationVersion)</code> with account doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_saveCalculationVersion_accountNotExist() throws Exception {
        instance.saveCalculationVersion(Long.MAX_VALUE, calculationVersion);
    }

    /**
     * <p>
     * Accuracy test for the method <code>deleteCalculationVersion(long calculationVersionId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_deleteCalculationVersion() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.saveCalculationVersion(account.getId(), calculationVersion);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        long calculationVersionId = retrievedAccount.getCalculationVersions().get(0).getId();
        entityManager.clear();
        entityManager.getTransaction().begin();
        instance.deleteCalculationVersion(calculationVersionId);
        entityManager.getTransaction().commit();
        entityManager.clear();

        CalculationVersion retrievedCalculationVersion = entityManager.find(CalculationVersion.class,
            calculationVersionId);
        assertNull("'deleteCalculationVersion' should be correct.", retrievedCalculationVersion);

        retrievedAccount = entityManager.find(Account.class, account.getId());
        assertEquals("'deleteCalculationVersion' should be correct.", 0, retrievedAccount
            .getCalculationVersions().size());
    }

    /**
     * <p>
     * Failure test for the method <code>deleteCalculationVersion(long calculationVersionId)</code> with accountId is
     * negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_deleteCalculationVersion_accountIdNegative() throws Exception {
        instance.deleteCalculationVersion(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>deleteCalculationVersion(long calculationVersionId)</code> with accountId is
     * zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_deleteCalculationVersion_accountIdZero() throws Exception {
        instance.deleteCalculationVersion(0);
    }

    /**
     * <p>
     * Failure test for the method <code>deleteCalculationVersion(long calculationVersionId)</code> with calculation
     * version doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_deleteCalculationVersion_calculationVersionNotExist() throws Exception {
        instance.deleteCalculationVersion(Long.MAX_VALUE);
    }

    /**
     * <p>
     * Accuracy test for the method <code>saveBillings(long accountId, List&lt;Billing&gt; billings)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_saveBillings() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.saveCalculationVersion(account.getId(), calculationVersion);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.saveBillings(account.getId(), billings);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertTrue("'deleteCalculationVersion' should be correct.", retrievedAccount.getBillingSummary().getId() > 0);
        List<Billing> retrievedBillings = retrievedAccount.getBillingSummary().getBillings();
        assertEquals("'deleteCalculationVersion' should be correct.", billings.size(), retrievedBillings.size());
        assertTrue("'deleteCalculationVersion' should be correct.", retrievedBillings.get(0).getId() > 0);
        assertTrue("'deleteCalculationVersion' should be correct.", retrievedBillings.get(1).getId() > 0);
    }

    /**
     * <p>
     * Failure test for the method <code>saveBillings(long accountId, List&lt;Billing&gt; billings)</code> with
     * accountId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_saveBillings_accountIdNegative() throws Exception {
        instance.saveBillings(-1, Arrays.asList(new Billing()));
    }

    /**
     * <p>
     * Failure test for the method <code>saveBillings(long accountId, List&lt;Billing&gt; billings)</code> with
     * accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_saveBillings_accountIdZero() throws Exception {
        instance.saveBillings(0, Arrays.asList(new Billing()));
    }

    /**
     * <p>
     * Failure test for the method <code>saveBillings(long accountId, List&lt;Billing&gt; billings)</code> with billings
     * is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_saveBillings_billingsNull() throws Exception {
        instance.saveBillings(1, null);
    }

    /**
     * <p>
     * Failure test for the method <code>saveBillings(long accountId, List&lt;Billing&gt; billings)</code> with account
     * doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_saveBillings_accountNotExist() throws Exception {
        instance.saveBillings(Long.MAX_VALUE, Arrays.asList(new Billing()));
    }

    /**
     * <p>
     * Accuracy test for the method <code>approve(AccountConfirmationValidation validity)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_approve() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        AccountConfirmationValidation validity = getAccountConfirmationValidation();
        validity.setAccountId(account.getId());

        entityManager.getTransaction().begin();
        instance.approve(validity);
        entityManager.getTransaction().commit();
        entityManager.clear();

        AccountConfirmationValidation retrievedValidity = entityManager.find(AccountConfirmationValidation.class,
            validity.getId());
        assertEquals("'approve' should be correct.", ApprovalStatus.APPROVED, retrievedValidity.getDataCheckStatus());
    }

    /**
     * <p>
     * Failure test for the method <code>approve(AccountConfirmationValidation validity)</code> with validity is
     * null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approve_validityNull() throws Exception {
        instance.approve(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>reject(AccountConfirmationValidation validity)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_reject() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        AccountConfirmationValidation validity = getAccountConfirmationValidation();
        validity.setAccountId(account.getId());

        entityManager.getTransaction().begin();
        instance.reject(validity);
        entityManager.getTransaction().commit();
        entityManager.clear();

        AccountConfirmationValidation retrievedValidity = entityManager.find(AccountConfirmationValidation.class,
            validity.getId());
        assertEquals("'reject' should be correct.", ApprovalStatus.DISAPPROVED, retrievedValidity.getDataCheckStatus());
    }

    /**
     * <p>
     * Failure test for the method <code>reject(AccountConfirmationValidation validity)</code> with validity is
     * null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_reject_validityNull() throws Exception {
        instance.reject(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>updateInterest(long accountId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_updateInterest() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        instance.updateInterest(account.getId());

        // Left for final fixes
    }

    /**
     * <p>
     * Failure test for the method <code>updateInterest(long accountId)</code> with accountId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_updateInterest_accountIdNegative() throws Exception {
        instance.updateInterest(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>updateInterest(long accountId)</code> with accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_updateInterest_accountIdZero() throws Exception {
        instance.updateInterest(0);
    }

    /**
     * <p>
     * Failure test for the method <code>updateInterest(long accountId)</code> with account doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_updateInterest_accountNotExist() throws Exception {
        instance.updateInterest(Long.MAX_VALUE);
    }

    /**
     * <p>
     * Accuracy test for the method <code>countAccountsAssignedToUser(String username)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_countAccountsAssignedToUser_1() throws Exception {
        clearDB();

        int res = instance.countAccountsAssignedToUser(account.getClaimOfficer());

        assertEquals("'countAccountsAssignedToUser' should be correct.", 0, res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>countAccountsAssignedToUser(String username)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_countAccountsAssignedToUser() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        int res = instance.countAccountsAssignedToUser(account.getClaimOfficer());

        assertEquals("'countAccountsAssignedToUser' should be correct.", 1, res);
    }

    /**
     * <p>
     * Failure test for the method <code>countAccountsAssignedToUser(String username)</code> with username is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_countAccountsAssignedToUser_usernameNull() throws Exception {
        instance.countAccountsAssignedToUser(null);
    }

    /**
     * <p>
     * Failure test for the method <code>countAccountsAssignedToUser(String username)</code> with username is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_countAccountsAssignedToUser_usernameEmpty() throws Exception {
        instance.countAccountsAssignedToUser(TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Accuracy test for the method <code>assignAccount(long accountId, String claimOfficer)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_assignAccount() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.assignAccount(account.getId(), "new");
        entityManager.getTransaction().commit();
        entityManager.clear();

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertEquals("'assignAccount' should be correct.", "new", retrievedAccount.getClaimOfficer());
        assertNotNull("'assignAccount' should be correct.", retrievedAccount.getClaimOfficerAssignmentDate());
    }

    /**
     * <p>
     * Failure test for the method <code>assignAccount(long accountId, String claimOfficer)</code> with accountId is
     * negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_assignAccount_accountIdNegative() throws Exception {
        instance.assignAccount(-1, "claimOfficer");
    }

    /**
     * <p>
     * Failure test for the method <code>assignAccount(long accountId, String claimOfficer)</code> with accountId is
     * zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_assignAccount_accountIdZero() throws Exception {
        instance.assignAccount(0, "claimOfficer");
    }

    /**
     * <p>
     * Failure test for the method <code>assignAccount(long accountId, String claimOfficer)</code> with claimOfficer is
     * null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_assignAccount_claimOfficerNull() throws Exception {
        instance.assignAccount(1, null);
    }

    /**
     * <p>
     * Failure test for the method <code>assignAccount(long accountId, String claimOfficer)</code> with claimOfficer is
     * empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_assignAccount_claimOfficerEmpty() throws Exception {
        instance.assignAccount(1, TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Failure test for the method <code>assignAccount(long accountId, String claimOfficer)</code> with account doesn't
     * exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_assignAccount_accountNotExist() throws Exception {
        instance.assignAccount(Long.MAX_VALUE, "claimOfficer");
    }

    /**
     * <p>
     * Accuracy test for the method <code>unassignAccount(long accountId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_unassignAccount() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.unassignAccount(account.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();

        Account retrievedAccount = entityManager.find(Account.class, account.getId());

        assertNull("'unassignAccount' should be correct.", retrievedAccount.getClaimOfficer());
        assertNull("'unassignAccount' should be correct.", retrievedAccount.getClaimOfficerAssignmentDate());
    }

    /**
     * <p>
     * Failure test for the method <code>unassignAccount(long accountId)</code> with accountId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_unassignAccount_accountIdNegative() throws Exception {
        instance.unassignAccount(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>unassignAccount(long accountId)</code> with accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_unassignAccount_accountIdZero() throws Exception {
        instance.unassignAccount(0);
    }

    /**
     * <p>
     * Failure test for the method <code>unassignAccount(long accountId)</code> with account doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_unassignAccount_accountNotExist() throws Exception {
        instance.unassignAccount(Long.MAX_VALUE);
    }

    /**
     * <p>
     * Accuracy test for the method <code>calculateEndDate(Integer value, CalculationEndDateCalculationType type)</code>
     * .<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_calculateEndDate() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(account);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(
            "INSERT INTO opm.date_calculation_data (deleted, calculation_type, day_offset, month_offset, value)"
                + " VALUES(false, 'DAY_260', 1, 1, 10)").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();

        Date res = instance.calculateEndDate(31, CalculationEndDateCalculationType.DAY_260);

        assertTrue("'calculateEndDate' should be correct.", res.after(new Date()));
    }

    /**
     * <p>
     * Failure test for the method <code>calculateEndDate(Integer value, CalculationEndDateCalculationType type)</code>
     * with value is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_calculateEndDate_valueNull() throws Exception {
        instance.calculateEndDate(-1, CalculationEndDateCalculationType.DAY_260);
    }

    /**
     * <p>
     * Failure test for the method <code>calculateEndDate(Integer value, CalculationEndDateCalculationType type)</code>
     * with value is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_calculateEndDate_valueNegative() throws Exception {
        instance.calculateEndDate(-1, CalculationEndDateCalculationType.DAY_260);
    }

    /**
     * <p>
     * Failure test for the method <code>calculateEndDate(Integer value, CalculationEndDateCalculationType type)</code>
     * with type is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_calculateEndDate_typeNull() throws Exception {
        instance.calculateEndDate(1, null);
    }
}
