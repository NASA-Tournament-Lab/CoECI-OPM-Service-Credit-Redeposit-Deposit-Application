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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.RefundTransaction;
import gov.opm.scrd.entities.application.SuspendedPayment;
import gov.opm.scrd.entities.lookup.PaymentStatus;
import gov.opm.scrd.entities.lookup.PaymentType;
import gov.opm.scrd.services.EntityNotFoundException;

import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link SuspensionServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class SuspensionServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>SuspensionServiceImpl</code> instance used in tests.
     * </p>
     */
    private SuspensionServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the posted pending approval status used in tests.
     * </p>
     */
    private PaymentStatus postedPendingApprovalStatus;

    /**
     * <p>
     * Represents the account id used in tests.
     * </p>
     */
    private String suspender;

    /**
     * <p>
     * Represents the account id used in tests.
     * </p>
     */
    private Account account;

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
     * Represents the transfer type id used in tests.
     * </p>
     */
    private long transferTypeId;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(SuspensionServiceImplUnitTests.class);
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

        postedPendingApprovalStatus = new PaymentStatus();
        postedPendingApprovalStatus.setName("pending");
        create(postedPendingApprovalStatus);

        instance = new SuspensionServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "postedPendingApprovalStatus", postedPendingApprovalStatus);

        account = getAccount();
        create(account);

        accountId = account.getId();

        payment = getPayment();
        payment.setMasterAccountId(accountId);
        payment.setPaymentType(PaymentType.SUSPENDED_PAYMENT);
        create(payment);

        suspender = payment.getClaimant();

        transferTypeId = create(getTransferType());
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>SuspensionServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new SuspensionServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
        assertNull("'postedPendingApprovalStatus' should be correct.",
            TestsHelper.getField(instance, "postedPendingApprovalStatus"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSuspensionCount(String suspender)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getSuspensionCount_1() throws Exception {
        int res = instance.getSuspensionCount(suspender);

        assertEquals("'getSuspensionCount' should be correct.", 1, res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSuspensionCount(String suspender)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getSuspensionCount_2() throws Exception {
        payment.setDeleted(true);
        update(payment);

        int res = instance.getSuspensionCount(suspender);

        assertEquals("'getSuspensionCount' should be correct.", 0, res);
    }

    /**
     * <p>
     * Failure test for the method <code>getSuspensionCount(String suspender)</code> with suspender is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getSuspensionCount_suspenderNull() throws Exception {
        instance.getSuspensionCount(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSuspendedPayments(String suspender)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getSuspendedPayments_1() throws Exception {

        List<SuspendedPayment> res = instance.getSuspendedPayments(suspender);

        assertEquals("'getSuspendedPayments' should be correct.", 1, res.size());
        assertEquals("'getSuspendedPayments' should be correct.", payment.getPaymentStatus().getId(), res.get(0)
            .getPaymentStatus().getId());
        assertNotNull("'getSuspendedPayments' should be correct.", res.get(0).getDepositDate());
        assertEquals("'getSuspendedPayments' should be correct.",
            payment.getBatchNumber(), res.get(0).getBatchNumber());
        assertEquals("'getSuspendedPayments' should be correct.",
            payment.getBlockNumber(), res.get(0).getBlockNumber());
        assertEquals("'getSuspendedPayments' should be correct.",
            payment.getSequenceNumber(), res.get(0).getSequenceNumber());
        assertEquals("'getSuspendedPayments' should be correct.",
            payment.getClaimNumber(), res.get(0).getClaimNumber());
        assertEquals("'getSuspendedPayments' should be correct.", payment.isAch(), res.get(0).isAch());
        assertEquals("'getSuspendedPayments' should be correct.", payment.getAmount().intValue(), res.get(0)
            .getAmount().intValue());
        assertNotNull("'getSuspendedPayments' should be correct.", res.get(0).getClaimantBirthdate());
        assertEquals("'getSuspendedPayments' should be correct.", payment.getClaimant(), res.get(0).getClaimant());
        assertEquals("'getSuspendedPayments' should be correct.", payment.getAccountBalance().intValue(), res.get(0)
            .getAccountBalance().intValue());
        assertEquals("'getSuspendedPayments' should be correct.", payment.getAccountStatus().getId(), res.get(0)
            .getAccountStatus().getId());
        assertEquals("'getSuspendedPayments' should be correct.", payment.getMasterClaimNumber(), res.get(0)
            .getMasterClaimNumber());
        assertNotNull("'getSuspendedPayments' should be correct.", res.get(0).getMasterClaimantBirthdate());
        assertEquals("'getSuspendedPayments' should be correct.", payment.getMasterAccountStatus().getId(), res.get(0)
            .getMasterAccountStatus().getId());
        assertEquals("'getSuspendedPayments' should be correct.", payment.getMasterAccountBalance().intValue(), res
            .get(0).getMasterAccountBalance().intValue());
        assertEquals("'getSuspendedPayments' should be correct.", payment.getId(), res.get(0).getPaymentId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSuspendedPayments(String suspender)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getSuspendedPayments_2() throws Exception {
        payment.setDeleted(true);
        update(payment);
        List<SuspendedPayment> res = instance.getSuspendedPayments(suspender);

        assertEquals("'getSuspendedPayments' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Failure test for the method <code>getSuspendedPayments(String suspender)</code> with suspender is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getSuspendedPayments_suspenderNull() throws Exception {
        instance.getSuspendedPayments(null);
    }

    /**
     * <p>
     * Failure test for the method <code>getSuspendedPayments(String suspender)</code> with suspender is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getSuspendedPayments_suspenderEmpty() throws Exception {
        instance.getSuspendedPayments(TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Accuracy test for the method <code>resetPayment(long paymentId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_resetPayment_1() throws Exception {
        entityManager.getTransaction().begin();
        SuspendedPayment res = instance.resetPayment(payment.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'resetPayment' should be correct.", payment.getMasterClaimNumber(),
            retrievedPayment.getClaimNumber());
        assertNotNull("'resetPayment' should be correct.", retrievedPayment.getClaimantBirthdate());
        assertEquals("'resetPayment' should be correct.", payment.getMasterAccountStatus().getId(), retrievedPayment
            .getAccountStatus().getId());
        assertEquals("'resetPayment' should be correct.", payment.getMasterAccountBalance().intValue(),
            retrievedPayment.getAccountBalance().intValue());
        assertEquals("'resetPayment' should be correct.",
            payment.getMasterAccountId(), retrievedPayment.getAccountId());

        assertEquals("'resetPayment' should be correct.", retrievedPayment.getPaymentStatus().getId(), res
            .getPaymentStatus().getId());
        assertNotNull("'resetPayment' should be correct.", res.getDepositDate());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getBatchNumber(), res.getBatchNumber());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getBlockNumber(), res.getBlockNumber());
        assertEquals("'resetPayment' should be correct.",
            retrievedPayment.getSequenceNumber(), res.getSequenceNumber());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getClaimNumber(), res.getClaimNumber());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.isAch(), res.isAch());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getAmount().intValue(), res.getAmount()
            .intValue());
        assertNotNull("'resetPayment' should be correct.", res.getClaimantBirthdate());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getClaimant(), res.getClaimant());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getAccountBalance().intValue(), res
            .getAccountBalance().intValue());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getAccountStatus().getId(), res
            .getAccountStatus().getId());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getMasterClaimNumber(),
            res.getMasterClaimNumber());
        assertNotNull("'resetPayment' should be correct.", res.getMasterClaimantBirthdate());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getMasterAccountStatus().getId(), res
            .getMasterAccountStatus().getId());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getMasterAccountBalance().intValue(), res
            .getMasterAccountBalance().intValue());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getId(), res.getPaymentId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>resetPayment(long paymentId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_resetPayment_2() throws Exception {
        payment.setMasterAccountId(null);
        update(payment);
        entityManager.getTransaction().begin();
        SuspendedPayment res = instance.resetPayment(payment.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertNull("'resetPayment' should be correct.", retrievedPayment.getClaimNumber());
        assertNull("'resetPayment' should be correct.", retrievedPayment.getClaimantBirthdate());
        assertNull("'resetPayment' should be correct.", retrievedPayment.getAccountStatus());
        assertNull("'resetPayment' should be correct.", retrievedPayment.getAccountBalance());
        assertNull("'resetPayment' should be correct.", retrievedPayment.getAccountId());

        assertEquals("'resetPayment' should be correct.", retrievedPayment.getPaymentStatus().getId(), res
            .getPaymentStatus().getId());
        assertNotNull("'resetPayment' should be correct.", res.getDepositDate());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getBatchNumber(), res.getBatchNumber());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getBlockNumber(), res.getBlockNumber());
        assertEquals("'resetPayment' should be correct.",
            retrievedPayment.getSequenceNumber(), res.getSequenceNumber());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getClaimNumber(), res.getClaimNumber());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.isAch(), res.isAch());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getAmount().intValue(), res.getAmount()
            .intValue());
        assertNull("'resetPayment' should be correct.", res.getClaimantBirthdate());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getClaimant(), res.getClaimant());
        assertNull("'resetPayment' should be correct.", res.getAccountBalance());
        assertNull("'resetPayment' should be correct.", res.getAccountStatus());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getMasterClaimNumber(),
            res.getMasterClaimNumber());
        assertNotNull("'resetPayment' should be correct.", res.getMasterClaimantBirthdate());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getMasterAccountStatus().getId(), res
            .getMasterAccountStatus().getId());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getMasterAccountBalance().intValue(), res
            .getMasterAccountBalance().intValue());
        assertEquals("'resetPayment' should be correct.", retrievedPayment.getId(), res.getPaymentId());
    }

    /**
     * <p>
     * Failure test for the method <code>resetPayment(long paymentId)</code> with paymentId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_resetPayment_paymentIdNegative() throws Exception {
        instance.resetPayment(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>resetPayment(long paymentId)</code> with paymentId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_resetPayment_paymentIdZero() throws Exception {
        instance.resetPayment(0);
    }

    /**
     * <p>
     * Failure test for the method <code>resetPayment(long paymentId)</code> with payment doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_resetPayment_paymentNotExist() throws Exception {
        instance.resetPayment(Long.MAX_VALUE);
    }

    /**
     * <p>
     * Accuracy test for the method <code>postPayments(long[] paymentIds)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_postPayments() throws Exception {
        entityManager.getTransaction().begin();
        List<SuspendedPayment> res = instance.postPayments(new long[] {payment.getId()});
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertEquals("'postPayments' should be correct.", 1, res.size());
        assertEquals("'postPayments' should be correct.", postedPendingApprovalStatus.getId(), res.get(0)
            .getPaymentStatus().getId());
        assertNotNull("'postPayments' should be correct.", res.get(0).getDepositDate());
        assertEquals("'postPayments' should be correct.", payment.getBatchNumber(), res.get(0).getBatchNumber());
        assertEquals("'postPayments' should be correct.", payment.getBlockNumber(), res.get(0).getBlockNumber());
        assertEquals("'postPayments' should be correct.", payment.getSequenceNumber(), res.get(0).getSequenceNumber());
        assertEquals("'postPayments' should be correct.", payment.getClaimNumber(), res.get(0).getClaimNumber());
        assertEquals("'postPayments' should be correct.", payment.isAch(), res.get(0).isAch());
        assertEquals("'postPayments' should be correct.", payment.getAmount().intValue(), res.get(0).getAmount()
            .intValue());
        assertNotNull("'postPayments' should be correct.", res.get(0).getClaimantBirthdate());
        assertEquals("'postPayments' should be correct.", payment.getClaimant(), res.get(0).getClaimant());
        assertEquals("'postPayments' should be correct.", payment.getAccountBalance().intValue(), res.get(0)
            .getAccountBalance().intValue());
        assertEquals("'postPayments' should be correct.", payment.getAccountStatus().getId(), res.get(0)
            .getAccountStatus().getId());
        assertEquals("'postPayments' should be correct.", payment.getMasterClaimNumber(), res.get(0)
            .getMasterClaimNumber());
        assertNotNull("'postPayments' should be correct.", res.get(0).getMasterClaimantBirthdate());
        assertEquals("'postPayments' should be correct.", payment.getMasterAccountStatus().getId(), res.get(0)
            .getMasterAccountStatus().getId());
        assertEquals("'postPayments' should be correct.", payment.getMasterAccountBalance().intValue(), res.get(0)
            .getMasterAccountBalance().intValue());
        assertEquals("'postPayments' should be correct.", payment.getId(), res.get(0).getPaymentId());

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());
        assertEquals("'postPayments' should be correct.", postedPendingApprovalStatus.getId(), retrievedPayment
            .getPaymentStatus().getId());
    }

    /**
     * <p>
     * Failure test for the method <code>postPayments(long[] paymentIds)</code> with paymentIds is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_postPayments_paymentIdsNull() throws Exception {
        instance.postPayments(null);
    }

    /**
     * <p>
     * Failure test for the method <code>postPayments(long[] paymentIds)</code> with payment doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_postPayments_paymentNotExist() throws Exception {
        instance.postPayments(new long[] {Long.MAX_VALUE});
    }

    /**
     * <p>
     * Accuracy test for the method <code>linkPaymentToAccount(long paymentId, long accountId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_linkPaymentToAccount() throws Exception {
        entityManager.getTransaction().begin();
        SuspendedPayment res = instance.linkPaymentToAccount(payment.getId(), accountId);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'linkPaymentToAccount' should be correct.", account.getClaimNumber(),
            retrievedPayment.getClaimNumber());
        assertNotNull("'linkPaymentToAccount' should be correct.", retrievedPayment.getClaimantBirthdate());
        assertEquals("'linkPaymentToAccount' should be correct.", account.getStatus().getId(), retrievedPayment
            .getAccountStatus().getId());
        assertEquals("'linkPaymentToAccount' should be correct.", account.getBalance().intValue(), retrievedPayment
            .getAccountBalance().intValue());
        assertEquals("'linkPaymentToAccount' should be correct.", account.getId(), retrievedPayment.getAccountId()
            .longValue());

        assertEquals("'linkPaymentToAccount' should be correct.", account.getClaimNumber(),
            retrievedPayment.getMasterClaimNumber());
        assertNotNull("'linkPaymentToAccount' should be correct.", retrievedPayment.getClaimantBirthdate());
        assertEquals("'linkPaymentToAccount' should be correct.", account.getStatus().getId(), retrievedPayment
            .getMasterAccountStatus().getId());
        assertEquals("'linkPaymentToAccount' should be correct.", account.getBalance().intValue(), retrievedPayment
            .getMasterAccountBalance().intValue());
        assertEquals("'linkPaymentToAccount' should be correct.", payment.getMasterAccountId(),
            retrievedPayment.getMasterAccountId());

        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getPaymentStatus().getId(), res
            .getPaymentStatus().getId());
        assertNotNull("'linkPaymentToAccount' should be correct.", res.getDepositDate());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getBatchNumber(),
            res.getBatchNumber());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getBlockNumber(),
            res.getBlockNumber());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getSequenceNumber(),
            res.getSequenceNumber());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getClaimNumber(),
            res.getClaimNumber());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.isAch(), res.isAch());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getAmount().intValue(), res
            .getAmount().intValue());
        assertNotNull("'linkPaymentToAccount' should be correct.", res.getClaimantBirthdate());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getClaimant(), res.getClaimant());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getAccountBalance().intValue(), res
            .getAccountBalance().intValue());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getAccountStatus().getId(), res
            .getAccountStatus().getId());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getMasterClaimNumber(),
            res.getMasterClaimNumber());
        assertNotNull("'linkPaymentToAccount' should be correct.", res.getMasterClaimantBirthdate());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getMasterAccountStatus().getId(),
            res.getMasterAccountStatus().getId());
        assertEquals("'linkPaymentToAccount' should be correct.",
            retrievedPayment.getMasterAccountBalance().intValue(), res.getMasterAccountBalance().intValue());
        assertEquals("'linkPaymentToAccount' should be correct.", retrievedPayment.getId(), res.getPaymentId());
    }

    /**
     * <p>
     * Failure test for the method <code>linkPaymentToAccount(long paymentId, long accountId)</code> with paymentId is
     * negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_linkPaymentToAccount_paymentIdNegative() throws Exception {
        instance.linkPaymentToAccount(-1, accountId);
    }

    /**
     * <p>
     * Failure test for the method <code>linkPaymentToAccount(long paymentId, long accountId)</code> with paymentId is
     * zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_linkPaymentToAccount_paymentIdZero() throws Exception {
        instance.linkPaymentToAccount(0, accountId);
    }

    /**
     * <p>
     * Failure test for the method <code>linkPaymentToAccount(long paymentId, long accountId)</code> with accountId is
     * negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_linkPaymentToAccount_accountIdNegative() throws Exception {
        instance.linkPaymentToAccount(payment.getId(), -1);
    }

    /**
     * <p>
     * Failure test for the method <code>linkPaymentToAccount(long paymentId, long accountId)</code> with accountId is
     * zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_linkPaymentToAccount_accountIdZero() throws Exception {
        instance.linkPaymentToAccount(payment.getId(), 0);
    }

    /**
     * <p>
     * Failure test for the method <code>linkPaymentToAccount(long paymentId, long accountId)</code> with payment
     * doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_linkPaymentToAccount_paymentNotExist() throws Exception {
        instance.linkPaymentToAccount(Long.MAX_VALUE, accountId);
    }

    /**
     * <p>
     * Failure test for the method <code>linkPaymentToAccount(long paymentId, long accountId)</code> with account
     * doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_linkPaymentToAccount_accountNotExist() throws Exception {
        instance.linkPaymentToAccount(payment.getId(), Long.MAX_VALUE);
    }

    /**
     * <p>
     * Accuracy test for the method <code>transferPayment(long paymentId, long transferTypeId,
     * Boolean refund)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_transferPayment_1() throws Exception {
        entityManager.getTransaction().begin();
        SuspendedPayment res = instance.transferPayment(payment.getId(), transferTypeId, false);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'transferPayment' should be correct.", payment.getMasterClaimNumber(),
            retrievedPayment.getClaimNumber());
        assertNotNull("'transferPayment' should be correct.", retrievedPayment.getClaimantBirthdate());
        assertEquals("'transferPayment' should be correct.", payment.getMasterAccountStatus().getId(), retrievedPayment
            .getAccountStatus().getId());
        assertEquals("'transferPayment' should be correct.", payment.getMasterAccountBalance().intValue(),
            retrievedPayment.getAccountBalance().intValue());
        assertEquals("'transferPayment' should be correct.", payment.getMasterAccountId(),
            retrievedPayment.getAccountId());

        assertEquals("'transferPayment' should be correct.", retrievedPayment.getPaymentStatus().getId(), res
            .getPaymentStatus().getId());
        assertNotNull("'transferPayment' should be correct.", res.getDepositDate());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getBatchNumber(), res.getBatchNumber());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getBlockNumber(), res.getBlockNumber());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getSequenceNumber(),
            res.getSequenceNumber());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getClaimNumber(), res.getClaimNumber());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.isAch(), res.isAch());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getAmount().intValue(), res.getAmount()
            .intValue());
        assertNotNull("'transferPayment' should be correct.", res.getClaimantBirthdate());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getClaimant(), res.getClaimant());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getAccountBalance().intValue(), res
            .getAccountBalance().intValue());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getAccountStatus().getId(), res
            .getAccountStatus().getId());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getMasterClaimNumber(),
            res.getMasterClaimNumber());
        assertNotNull("'transferPayment' should be correct.", res.getMasterClaimantBirthdate());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getMasterAccountStatus().getId(), res
            .getMasterAccountStatus().getId());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getMasterAccountBalance().intValue(), res
            .getMasterAccountBalance().intValue());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getId(), res.getPaymentId());

        List<RefundTransaction> list = entityManager.createQuery("SELECT e FROM RefundTransaction e",
            RefundTransaction.class).getResultList();
        assertEquals("'transferPayment' should be correct.", 0, list.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>transferPayment(long paymentId, long transferTypeId,
     * Boolean refund)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_transferPayment_2() throws Exception {
        entityManager.getTransaction().begin();
        SuspendedPayment res = instance.transferPayment(payment.getId(), transferTypeId, true);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'transferPayment' should be correct.", payment.getMasterClaimNumber(),
            retrievedPayment.getClaimNumber());
        assertNotNull("'transferPayment' should be correct.", retrievedPayment.getClaimantBirthdate());
        assertEquals("'transferPayment' should be correct.", payment.getMasterAccountStatus().getId(), retrievedPayment
            .getAccountStatus().getId());
        assertEquals("'transferPayment' should be correct.", payment.getMasterAccountBalance().intValue(),
            retrievedPayment.getAccountBalance().intValue());
        assertEquals("'transferPayment' should be correct.", payment.getMasterAccountId(),
            retrievedPayment.getAccountId());

        assertEquals("'transferPayment' should be correct.", retrievedPayment.getPaymentStatus().getId(), res
            .getPaymentStatus().getId());
        assertNotNull("'transferPayment' should be correct.", res.getDepositDate());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getBatchNumber(), res.getBatchNumber());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getBlockNumber(), res.getBlockNumber());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getSequenceNumber(),
            res.getSequenceNumber());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getClaimNumber(), res.getClaimNumber());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.isAch(), res.isAch());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getAmount().intValue(), res.getAmount()
            .intValue());
        assertNotNull("'transferPayment' should be correct.", res.getClaimantBirthdate());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getClaimant(), res.getClaimant());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getAccountBalance().intValue(), res
            .getAccountBalance().intValue());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getAccountStatus().getId(), res
            .getAccountStatus().getId());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getMasterClaimNumber(),
            res.getMasterClaimNumber());
        assertNotNull("'transferPayment' should be correct.", res.getMasterClaimantBirthdate());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getMasterAccountStatus().getId(), res
            .getMasterAccountStatus().getId());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getMasterAccountBalance().intValue(), res
            .getMasterAccountBalance().intValue());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getId(), res.getPaymentId());

        List<RefundTransaction> list = entityManager.createQuery("SELECT e FROM RefundTransaction e",
            RefundTransaction.class).getResultList();
        assertEquals("'transferPayment' should be correct.", 1, list.size());

        RefundTransaction refundTransaction = list.get(0);
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getTransactionKey(),
            refundTransaction.getTransactionKey());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getAmount().intValue(), refundTransaction
            .getAmount().intValue());
        assertEquals("'transferPayment' should be correct.", retrievedPayment.getClaimNumber(),
            refundTransaction.getClaimNumber());
        assertNotNull("'transferPayment' should be correct.", refundTransaction.getRefundDate());
        assertEquals("'transferPayment' should be correct.", transferTypeId, refundTransaction.getTransferType()
            .getId());
    }

    /**
     * <p>
     * Failure test for the method <code>transferPayment(long paymentId, long transferTypeId, Boolean refund)</code>
     * with paymentId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_transferPayment_paymentIdNegative() throws Exception {
        instance.transferPayment(-1, 1, false);
    }

    /**
     * <p>
     * Failure test for the method <code>transferPayment(long paymentId, long transferTypeId, Boolean refund)</code>
     * with paymentId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_transferPayment_paymentIdZero() throws Exception {
        instance.transferPayment(0, 1, false);
    }

    /**
     * <p>
     * Failure test for the method <code>transferPayment(long paymentId, long transferTypeId, Boolean refund)</code>
     * with accountId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_transferPayment_accountIdNegative() throws Exception {
        instance.transferPayment(payment.getId(), -1, false);
    }

    /**
     * <p>
     * Failure test for the method <code>transferPayment(long paymentId, long transferTypeId, Boolean refund)</code>
     * with accountId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_transferPayment_accountIdZero() throws Exception {
        instance.transferPayment(payment.getId(), 0, false);
    }

    /**
     * <p>
     * Failure test for the method <code>transferPayment(long paymentId, long transferTypeId, Boolean refund)</code>
     * with payment doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_transferPayment_paymentNotExist() throws Exception {
        instance.transferPayment(Long.MAX_VALUE, accountId, false);
    }

    /**
     * <p>
     * Failure test for the method <code>transferPayment(long paymentId, long transferTypeId, Boolean refund)</code>
     * with transfer type doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_transferPayment_transferTypeNotExist() throws Exception {
        entityManager.getTransaction().begin();
        try {
            instance.transferPayment(payment.getId(), Long.MAX_VALUE, true);
        } finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }
}
