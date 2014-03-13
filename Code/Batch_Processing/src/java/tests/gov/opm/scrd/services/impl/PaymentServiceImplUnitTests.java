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
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.PaymentReverse;
import gov.opm.scrd.entities.application.PaymentSearchFilter;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.entities.lookup.PaymentType;
import gov.opm.scrd.services.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link PaymentServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class PaymentServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>PaymentServiceImpl</code> instance used in tests.
     * </p>
     */
    private PaymentServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the account id used in tests.
     * </p>
     */
    private long accountId;

    /**
     * <p>
     * Represents the payment used in tests.
     * </p>
     */
    private Payment payment;

    /**
     * <p>
     * Represents the filter used in tests.
     * </p>
     */
    private PaymentSearchFilter filter;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PaymentServiceImplUnitTests.class);
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

        instance = new PaymentServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);

        Account account = getAccount();
        create(account);

        accountId = account.getId();

        payment = getPayment();

        filter = new PaymentSearchFilter();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>PaymentServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new PaymentServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>create(long accountId, Payment payment)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_create() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(accountId, payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertTrue("'create' should be correct.", payment.getId() > 0);

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertFalse("'create' should be correct.", retrievedPayment.isDeleted());
        assertEquals("'create' should be correct.", payment.getBatchNumber(), retrievedPayment.getBatchNumber());
        assertEquals("'create' should be correct.", payment.getBlockNumber(), retrievedPayment.getBlockNumber());
        assertEquals("'create' should be correct.", payment.getSequenceNumber(), retrievedPayment.getSequenceNumber());
        assertEquals("'create' should be correct.", payment.getPaymentStatus().getId(), retrievedPayment
            .getPaymentStatus().getId());
        assertEquals("'create' should be correct.", payment.getClaimNumber(), retrievedPayment.getClaimNumber());
        assertNotNull("'create' should be correct.", retrievedPayment.getAccountHolderBirthdate());
        assertNotNull("'create' should be correct.", retrievedPayment.getDepositDate());
        assertEquals("'create' should be correct.", payment.getAmount().intValue(), retrievedPayment.getAmount()
            .intValue());
        assertEquals("'create' should be correct.", payment.getSsn(), retrievedPayment.getSsn());
        assertEquals("'create' should be correct.", payment.getClaimant(), retrievedPayment.getClaimant());
        assertNotNull("'create' should be correct.", retrievedPayment.getClaimantBirthdate());
        assertEquals("'create' should be correct.", payment.getImportId(), retrievedPayment.getImportId());
        assertEquals("'create' should be correct.", payment.getSequence(), retrievedPayment.getSequence());
        assertNotNull("'create' should be correct.", retrievedPayment.getTransactionDate());
        assertNotNull("'create' should be correct.", retrievedPayment.getStatusDate());
        assertEquals("'create' should be correct.",
            payment.getApplyTo().getId(), retrievedPayment.getApplyTo().getId());
        assertEquals("'create' should be correct.", payment.isApplyToGL(), retrievedPayment.isApplyToGL());
        assertEquals("'create' should be correct.", payment.getNote(), retrievedPayment.getNote());
        assertEquals("'create' should be correct.", payment.getTransactionKey(), retrievedPayment.getTransactionKey());
        assertEquals("'create' should be correct.", payment.isAch(), retrievedPayment.isAch());
        assertEquals("'create' should be correct.", payment.getAccountBalance().intValue(), retrievedPayment
            .getAccountBalance().intValue());
        assertEquals("'create' should be correct.", payment.getAccountStatus().getId(), retrievedPayment
            .getAccountStatus().getId());
        assertEquals("'create' should be correct.", payment.getMasterClaimNumber(),
            retrievedPayment.getMasterClaimNumber());
        assertNotNull("'create' should be correct.", retrievedPayment.getMasterClaimantBirthdate());
        assertEquals("'create' should be correct.", payment.getMasterAccountStatus().getId(), retrievedPayment
            .getMasterAccountStatus().getId());
        assertEquals("'create' should be correct.", payment.getMasterAccountBalance().intValue(), retrievedPayment
            .getMasterAccountBalance().intValue());
        assertEquals("'create' should be correct.",
            payment.getMasterAccountId(), retrievedPayment.getMasterAccountId());
        assertEquals("'create' should be correct.", payment.getPreDepositAmount().intValue(), retrievedPayment
            .getPreDepositAmount().intValue());
        assertEquals("'create' should be correct.", payment.getPreRedepositAmount().intValue(), retrievedPayment
            .getPreRedepositAmount().intValue());
        assertEquals("'create' should be correct.", payment.getPostDepositAmount().intValue(), retrievedPayment
            .getPostDepositAmount().intValue());
        assertEquals("'create' should be correct.", payment.getPostRedepositAmount().intValue(), retrievedPayment
            .getPostRedepositAmount().intValue());
        assertEquals("'create' should be correct.", payment.getApprovalUser(), retrievedPayment.getApprovalUser());
        assertEquals("'create' should be correct.", payment.getApprovalStatus(), retrievedPayment.getApprovalStatus());
        assertEquals("'create' should be correct.", payment.getApprovalReason(), retrievedPayment.getApprovalReason());
        assertEquals("'create' should be correct.", payment.getPaymentType(), retrievedPayment.getPaymentType());
        assertEquals("'create' should be correct.", payment.getAccountId(), retrievedPayment.getAccountId());

        Account account = entityManager.find(Account.class, accountId);
        assertEquals("'create' should be correct.", 1, account.getPaymentHistory().size());
        assertEquals("'create' should be correct.", payment.getId(), account.getPaymentHistory().get(0).getId());
    }

    /**
     * <p>
     * Failure test for the method <code>create(long accountId, Payment payment)</code> with accountId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_create_accountIdNegative() throws Exception {
        instance.create(-1, payment);
    }

    /**
     * <p>
     * Failure test for the method <code>create(long accountId, Payment payment)</code> with accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_create_accountIdZero() throws Exception {
        instance.create(0, payment);
    }

    /**
     * <p>
     * Failure test for the method <code>create(long accountId, Payment payment)</code> with payment is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_create_paymentNull() throws Exception {
        instance.create(1, null);
    }

    /**
     * <p>
     * Failure test for the method <code>create(long accountId, Payment payment)</code> with account doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_create_accountNotExist() throws Exception {
        instance.create(Long.MAX_VALUE, payment);
    }

    /**
     * <p>
     * Accuracy test for the method <code>update(List&lt;Payment&gt; payments)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_update() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(accountId, payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        payment.setBatchNumber("new");
        entityManager.getTransaction().begin();
        instance.update(Arrays.asList(payment));
        entityManager.getTransaction().commit();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'update' should be correct.", payment.getBatchNumber(), retrievedPayment.getBatchNumber());
    }

    /**
     * <p>
     * Failure test for the method <code>update(List&lt;Payment&gt; payments)</code> with payments is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_update_paymentsNull() throws Exception {
        instance.update(null);
    }

    /**
     * <p>
     * Failure test for the method <code>update(List&lt;Payment&gt; payments)</code> with payments contains null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_update_paymentsContains() throws Exception {
        instance.update(Arrays.asList((Payment) null));
    }

    /**
     * <p>
     * Failure test for the method <code>update(List&lt;Payment&gt; payments)</code> with payment doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_update_paymentNotExist() throws Exception {
        payment.setId(Long.MAX_VALUE);

        instance.update(Arrays.asList(payment));
    }

    /**
     * <p>
     * Accuracy test for the method <code>delete(long paymentId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_delete() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(accountId, payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.delete(payment.getId());
        entityManager.getTransaction().commit();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertTrue("'delete' should be correct.", retrievedPayment.isDeleted());
    }

    /**
     * <p>
     * Failure test for the method <code>delete(long paymentId)</code> with paymentId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_delete_paymentIdNegative() throws Exception {
        instance.delete(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>delete(long paymentId)</code> with paymentId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_delete_paymentIdZero() throws Exception {
        instance.delete(0);
    }

    /**
     * <p>
     * Failure test for the method <code>delete(long paymentId)</code> with payment doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_delete_paymentNotExist() throws Exception {
        instance.delete(Long.MAX_VALUE);
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(PaymentSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_1() throws Exception {
        clearDB();

        SearchResult<Payment> res = instance.search(filter);

        assertEquals("'search' should be correct.", 0, res.getTotal());
        assertEquals("'search' should be correct.", 0, res.getTotalPageCount());

        List<Payment> items = res.getItems();
        assertEquals("'search' should be correct.", 0, items.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(PaymentSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_2() throws Exception {
        payment.setDeleted(true);
        entityManager.getTransaction().begin();
        instance.create(accountId, payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        SearchResult<Payment> res = instance.search(filter);

        assertEquals("'search' should be correct.", 0, res.getTotal());
        assertEquals("'search' should be correct.", 0, res.getTotalPageCount());

        List<Payment> items = res.getItems();
        assertEquals("'search' should be correct.", 0, items.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(PaymentSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_3() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(accountId, payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        SearchResult<Payment> res = instance.search(filter);

        assertEquals("'search' should be correct.", 1, res.getTotal());
        assertEquals("'search' should be correct.", 1, res.getTotalPageCount());

        List<Payment> items = res.getItems();
        assertEquals("'search' should be correct.", 1, items.size());
        Payment item = items.get(0);
        assertEquals("'search' should be correct.", payment.getId(), item.getId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(PaymentSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_4() throws Exception {
        payment.setPaymentType(PaymentType.SUSPENDED_PAYMENT);
        entityManager.getTransaction().begin();
        instance.create(accountId, payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        filter.setBatchNumber(payment.getBatchNumber());
        filter.setBlockNumber(payment.getBlockNumber());
        filter.setSequenceNumber(payment.getSequenceNumber());
        filter.setResolvedSuspense(true);
        SearchResult<Payment> res = instance.search(filter);

        assertEquals("'search' should be correct.", 1, res.getTotal());
        assertEquals("'search' should be correct.", 1, res.getTotalPageCount());

        List<Payment> items = res.getItems();
        assertEquals("'search' should be correct.", 1, items.size());
        Payment item = items.get(0);
        assertEquals("'search' should be correct.", payment.getId(), item.getId());
    }

    /**
     * <p>
     * Failure test for the method <code>search(PaymentSearchFilter filter)</code> with filter is null.<br>
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
     * Accuracy test for the method <code>savePaymentNote(long paymentId, String note)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_savePaymentNote() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(accountId, payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.savePaymentNote(payment.getId(), "The note.");
        entityManager.getTransaction().commit();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'savePaymentNote' should be correct.", "The note.", retrievedPayment.getNote());
    }

    /**
     * <p>
     * Failure test for the method <code>savePaymentNote(long paymentId, String note)</code> with paymentId is negative.
     * <br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_savePaymentNote_paymentIdNegative() throws Exception {
        instance.savePaymentNote(-1, "note");
    }

    /**
     * <p>
     * Failure test for the method <code>savePaymentNote(long paymentId, String note)</code> with paymentId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_savePaymentNote_paymentIdZero() throws Exception {
        instance.savePaymentNote(0, "note");
    }

    /**
     * <p>
     * Failure test for the method <code>savePaymentNote(long paymentId, String note)</code> with note is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_savePaymentNote_noteNull() throws Exception {
        instance.savePaymentNote(1, null);
    }

    /**
     * <p>
     * Failure test for the method <code>savePaymentNote(long paymentId, String note)</code> with payment doesn't exist.
     * <br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_savePaymentNote_paymentNotExist() throws Exception {
        instance.savePaymentNote(Long.MAX_VALUE, "note");
    }

    /**
     * <p>
     * Accuracy test for the method <code>reverse(String reverser, long paymentId, long paymentReversalReasonId,
     * boolean applyReversalToGL)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_reverse() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(accountId, payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        long paymentReversalReasonId = create(getPaymentReversalReason());

        entityManager.getTransaction().begin();
        instance.reverse("reverser1", payment.getId(), paymentReversalReasonId, true);
        entityManager.getTransaction().commit();

        List<PaymentReverse> paymentReverses = entityManager.createQuery("SELECT e FROM PaymentReverse e",
            PaymentReverse.class).getResultList();

        assertEquals("'reverse' should be correct.", 1, paymentReverses.size());

        PaymentReverse paymentReverse = paymentReverses.get(0);
        assertTrue("'reverse' should be correct.", paymentReverse.getId() > 0);
        assertEquals("'reverse' should be correct.", "reverser1", paymentReverse.getReverser());
        assertEquals("'reverse' should be correct.", payment.getId(), paymentReverse.getPaymentId());
        assertEquals("'reverse' should be correct.", paymentReversalReasonId, paymentReverse.getReason().getId());
        assertTrue("'reverse' should be correct.", paymentReverse.isApplyToGL());
    }

    /**
     * <p>
     * Failure test for the method <code>reverse(String reverser, long paymentId, long paymentReversalReasonId,
     * boolean applyReversalToGL)</code> with reverser is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_reverse_reverserNull() throws Exception {
        instance.reverse(null, 1, 1, true);
    }

    /**
     * <p>
     * Failure test for the method <code>reverse(String reverser, long paymentId, long paymentReversalReasonId,
     * boolean applyReversalToGL)</code> with reverser is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_reverse_reverserEmpty() throws Exception {
        instance.reverse(TestsHelper.EMPTY_STRING, 1, 1, true);
    }

    /**
     * <p>
     * Failure test for the method <code>reverse(String reverser, long paymentId, long paymentReversalReasonId,
     * boolean applyReversalToGL)</code> with paymentId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_reverse_paymentIdNegative() throws Exception {
        instance.reverse("reverser", -1, 1, true);
    }

    /**
     * <p>
     * Failure test for the method <code>reverse(String reverser, long paymentId, long paymentReversalReasonId,
     * boolean applyReversalToGL)</code> with paymentId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_reverse_paymentIdZero() throws Exception {
        instance.reverse("reverser", 0, 1, true);
    }

    /**
     * <p>
     * Failure test for the method <code>reverse(String reverser, long paymentId, long paymentReversalReasonId,
     * boolean applyReversalToGL)</code> with paymentReversalReasonId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_reverse_paymentReversalReasonIdNegative() throws Exception {
        instance.reverse("reverser", 1, -1, true);
    }

    /**
     * <p>
     * Failure test for the method <code>reverse(String reverser, long paymentId, long paymentReversalReasonId,
     * boolean applyReversalToGL)</code> with paymentReversalReasonId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_reverse_paymentReversalReasonIdZero() throws Exception {
        instance.reverse("reverser", 1, 0, true);
    }

    /**
     * <p>
     * Failure test for the method <code>reverse(String reverser, long paymentId, long paymentReversalReasonId,
     * boolean applyReversalToGL)</code> with payment doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_reverse_paymentNotExist() throws Exception {
        long paymentReversalReasonId = create(getPaymentReversalReason());

        instance.reverse("reverser", Long.MAX_VALUE, paymentReversalReasonId, true);
    }

    /**
     * <p>
     * Failure test for the method <code>reverse(String reverser, long paymentId, long paymentReversalReasonId,
     * boolean applyReversalToGL)</code> with payment reversal reason doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_reverse_paymentReversalReasonNotExist() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(accountId, payment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        instance.reverse("reverser", payment.getId(), Long.MAX_VALUE, true);
    }
}
