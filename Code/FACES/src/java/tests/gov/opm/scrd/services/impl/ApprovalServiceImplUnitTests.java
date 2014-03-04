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
import gov.opm.scrd.entities.application.ApprovalItemSummary;
import gov.opm.scrd.entities.application.InterestAdjustment;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.PaymentMove;
import gov.opm.scrd.entities.application.PendingPayment;
import gov.opm.scrd.entities.lookup.ApprovalStatus;
import gov.opm.scrd.entities.lookup.PaymentStatus;
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
 * Unit tests for {@link ApprovalServiceImpl} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Modify setUp() method to setup the payment status ids for pending payments</li>
 * </ul>
 * </p>
 *
 * @author sparemax, liuliquan
 * @version 1.1
 */
public class ApprovalServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>ApprovalServiceImpl</code> instance used in tests.
     * </p>
     */
    private ApprovalServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the payment used in tests.
     * </p>
     */
    private Payment payment;

    /**
     * <p>
     * Represents the approver used in tests.
     * </p>
     */
    private String approver;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ApprovalServiceImplUnitTests.class);
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

        instance = new ApprovalServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);


        PaymentStatus pendingPaymentStatus = getPaymentStatus();
        create(pendingPaymentStatus);

        PaymentStatus approvedPaymentStatus = getPaymentStatus();
        create(approvedPaymentStatus);

        PaymentStatus disapprovedPaymentStatus = getPaymentStatus();
        create(disapprovedPaymentStatus);

        TestsHelper.setField(instance, "pendingPaymentStatusIdList", Arrays.asList(pendingPaymentStatus.getId()));
        TestsHelper.setField(instance, "approvedPaymentStatus", approvedPaymentStatus);
        TestsHelper.setField(instance, "disapprovedPaymentStatus", disapprovedPaymentStatus);


        Account account = getAccount();
        create(account);

        payment = getPayment();
        payment.setAccountId(account.getId());
        payment.setPaymentStatus(pendingPaymentStatus);
        payment.setPaymentType(PaymentType.PENDING_PAYMENT);
        create(payment);

        approver = payment.getApprovalUser();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>ApprovalServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new ApprovalServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getApprovalSummary(String approver)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getApprovalSummary_1() throws Exception {
        clearDB();

        ApprovalItemSummary res = instance.getApprovalSummary(approver);

        assertEquals("'getApprovalSummary' should be correct.", 0, res.getTotalCount());
        assertEquals("'getApprovalSummary' should be correct.", 0, res.getPendingPaymentCount());
        assertEquals("'getApprovalSummary' should be correct.", 0, res.getInterestAdjustmentCount());
        assertEquals("'getApprovalSummary' should be correct.", 0, res.getPaymentMoveCount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getApprovalSummary(String approver)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getApprovalSummary_2() throws Exception {
        ApprovalItemSummary res = instance.getApprovalSummary(approver);

        assertEquals("'getApprovalSummary' should be correct.", 1, res.getTotalCount());
        assertEquals("'getApprovalSummary' should be correct.", 1, res.getPendingPaymentCount());
        assertEquals("'getApprovalSummary' should be correct.", 0, res.getInterestAdjustmentCount());
        assertEquals("'getApprovalSummary' should be correct.", 0, res.getPaymentMoveCount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getApprovalSummary(String approver)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getApprovalSummary_3() throws Exception {
        payment.setPaymentType(PaymentType.INTEREST_ADJUSTMENT);
        update(payment);

        ApprovalItemSummary res = instance.getApprovalSummary(approver);

        assertEquals("'getApprovalSummary' should be correct.", 1, res.getTotalCount());
        assertEquals("'getApprovalSummary' should be correct.", 0, res.getPendingPaymentCount());
        assertEquals("'getApprovalSummary' should be correct.", 1, res.getInterestAdjustmentCount());
        assertEquals("'getApprovalSummary' should be correct.", 0, res.getPaymentMoveCount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getApprovalSummary(String approver)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getApprovalSummary_4() throws Exception {
        payment.setPaymentType(PaymentType.PAYMENT_MOVE);
        update(payment);

        ApprovalItemSummary res = instance.getApprovalSummary(approver);

        assertEquals("'getApprovalSummary' should be correct.", 1, res.getTotalCount());
        assertEquals("'getApprovalSummary' should be correct.", 0, res.getPendingPaymentCount());
        assertEquals("'getApprovalSummary' should be correct.", 0, res.getInterestAdjustmentCount());
        assertEquals("'getApprovalSummary' should be correct.", 1, res.getPaymentMoveCount());
    }

    /**
     * <p>
     * Failure test for the method <code>getApprovalSummary(String approver)</code> with approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getApprovalSummary_approverNull() throws Exception {
        instance.getApprovalSummary(null);
    }

    /**
     * <p>
     * Failure test for the method <code>getApprovalSummary(String approver)</code> with approver is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getApprovalSummary_approverEmpty() throws Exception {
        instance.getApprovalSummary(TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPendingPayments(String approver)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getPendingPayments_1() throws Exception {
        clearDB();

        List<PendingPayment> res = instance.getPendingPayments(approver);

        assertEquals("'getPendingPayments' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPendingPayments(String approver)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getPendingPayments_2() throws Exception {
        List<PendingPayment> res = instance.getPendingPayments(approver);

        assertEquals("'getPendingPayments' should be correct.", 1, res.size());
        assertEquals("'getPendingPayments' should be correct.", payment.getPaymentStatus().getId(), res.get(0)
            .getPaymentStatus().getId());
        assertEquals("'getPendingPayments' should be correct.", payment.getBatchNumber(), res.get(0).getBatchNumber());
        assertEquals("'getPendingPayments' should be correct.", payment.getBlockNumber(), res.get(0).getBlockNumber());
        assertEquals("'getPendingPayments' should be correct.", payment.getSequenceNumber(), res.get(0)
            .getSequenceNumber());
        assertNotNull("'getPendingPayments' should be correct.", res.get(0).getTransactionDate());
        assertNotNull("'getPendingPayments' should be correct.", res.get(0).getStatusDate());
        assertEquals("'getPendingPayments' should be correct.", payment.getAmount().intValue(), res.get(0).getAmount()
            .intValue());
        assertEquals("'getPendingPayments' should be correct.", payment.isApplyToGL(), res.get(0).isApplyToGL());
        assertEquals("'getPendingPayments' should be correct.", payment.getClaimNumber(), res.get(0).getClaimNumber());
        assertNotNull("'getPendingPayments' should be correct.", res.get(0).getAccountHolderBirthdate());
        assertEquals("'getPendingPayments' should be correct.", payment.getAccountStatus().getId(), res.get(0)
            .getAccountStatus().getId());
        assertEquals("'getPendingPayments' should be correct.",
            payment.getApprovalUser(), res.get(0).getApprovalUser());
        assertEquals("'getPendingPayments' should be correct.", payment.getApprovalStatus(), res.get(0)
            .getApprovalStatus());
        assertEquals("'getPendingPayments' should be correct.", payment.getId(), res.get(0).getPaymentId());
    }

    /**
     * <p>
     * Failure test for the method <code>getPendingPayments(String approver)</code> with approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPendingPayments_approverNull() throws Exception {
        instance.getPendingPayments(null);
    }

    /**
     * <p>
     * Failure test for the method <code>getPendingPayments(String approver)</code> with approver is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPendingPayments_approverEmpty() throws Exception {
        instance.getPendingPayments(TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Accuracy test for the method <code>approvePendingPayments(String approver, long[] pendingPaymentIds)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_approvePendingPayments() throws Exception {
        entityManager.getTransaction().begin();
        instance.approvePendingPayments(approver, new long[] {payment.getId()});
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'approvePendingPayments' should be correct.", approver, retrievedPayment.getApprovalUser());
        assertEquals("'approvePendingPayments' should be correct.", ApprovalStatus.APPROVED,
            retrievedPayment.getApprovalStatus());
    }

    /**
     * <p>
     * Failure test for the method <code>approvePendingPayments(String approver, long[] pendingPaymentIds)</code> with
     * approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approvePendingPayments_approverNull() throws Exception {
        instance.approvePendingPayments(null, new long[] {payment.getId()});
    }

    /**
     * <p>
     * Failure test for the method <code>approvePendingPayments(String approver, long[] pendingPaymentIds)</code> with
     * approver is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approvePendingPayments_approverEmpty() throws Exception {
        instance.approvePendingPayments(TestsHelper.EMPTY_STRING, new long[] {payment.getId()});
    }

    /**
     * <p>
     * Failure test for the method <code>approvePendingPayments(String approver, long[] pendingPaymentIds)</code> with
     * pendingPaymentIds is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approvePendingPayments_pendingPaymentIdsNull() throws Exception {
        instance.approvePendingPayments(approver, null);
    }

    /**
     * <p>
     * Failure test for the method <code>approvePendingPayments(String approver, long[] pendingPaymentIds)</code> with
     * payment doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_approvePendingPayments_paymentNotExist() throws Exception {
        instance.approvePendingPayments(approver, new long[] {Long.MAX_VALUE});
    }

    /**
     * <p>
     * Accuracy test for the method <code>disapprovePendingPayments(String approver, long[] pendingPaymentIds,
     * String reason)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_disapprovePendingPayments() throws Exception {
        entityManager.getTransaction().begin();
        instance.disapprovePendingPayments(approver, new long[] {payment.getId()}, "reason");
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'disapprovePendingPayments' should be correct.", approver, retrievedPayment.getApprovalUser());
        assertEquals("'disapprovePendingPayments' should be correct.", ApprovalStatus.DISAPPROVED,
            retrievedPayment.getApprovalStatus());
        assertEquals("'disapprovePendingPayments' should be correct.", "reason", retrievedPayment.getApprovalReason());
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapprovePendingPayments(String approver, long[] pendingPaymentIds,
     * String reason)</code> with approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_disapprovePendingPayments_approverNull() throws Exception {
        instance.disapprovePendingPayments(null, new long[] {payment.getId()}, "reason");
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapprovePendingPayments(String approver, long[] pendingPaymentIds,
     * String reason)</code> with approver is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_disapprovePendingPayments_approverEmpty() throws Exception {
        instance.disapprovePendingPayments(TestsHelper.EMPTY_STRING, new long[] {payment.getId()}, "reason");
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapprovePendingPayments(String approver, long[] pendingPaymentIds,
     * String reason)</code> with pendingPaymentIds is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_disapprovePendingPayments_pendingPaymentIdsNull() throws Exception {
        instance.disapprovePendingPayments(approver, null, "reason");
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapprovePendingPayments(String approver, long[] pendingPaymentIds,
     * String reason)</code> with payment doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_disapprovePendingPayments_paymentNotExist() throws Exception {
        instance.disapprovePendingPayments(approver, new long[] {Long.MAX_VALUE}, "reason");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getInterestAdjustments(String approver, ApprovalStatus approvalStatus)</code>.
     * <br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getInterestAdjustments_1() throws Exception {
        clearDB();

        List<InterestAdjustment> res = instance.getInterestAdjustments(approver, ApprovalStatus.PENDING);

        assertEquals("'getInterestAdjustments' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getInterestAdjustments(String approver, ApprovalStatus approvalStatus)</code>.
     * <br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getInterestAdjustments_2() throws Exception {
        payment.setPaymentType(PaymentType.INTEREST_ADJUSTMENT);
        update(payment);

        List<InterestAdjustment> res = instance.getInterestAdjustments(approver, ApprovalStatus.PENDING);

        assertEquals("'getInterestAdjustments' should be correct.", 1, res.size());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getClaimNumber(), res.get(0)
            .getClaimNumber());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getPreDepositAmount().intValue(), res
            .get(0).getPreDepositAmount().intValue());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getPreRedepositAmount().intValue(), res
            .get(0).getPreRedepositAmount().intValue());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getPostDepositAmount().intValue(),
            res.get(0).getPostDepositAmount().intValue());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getPostRedepositAmount().intValue(), res
            .get(0).getPostRedepositAmount().intValue());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getAmount().intValue(), res.get(0)
            .getPaymentTransactionAmount().intValue());
        assertNotNull("'getInterestAdjustments' should be correct.", res.get(0).getAdjustmentDate());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getAccountStatus().getId(), res.get(0)
            .getAccountStatus().getId());
        assertNotNull("'getInterestAdjustments' should be correct.", res.get(0).getAccountHolderBirthdate());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getApprovalUser(), res.get(0)
            .getApprovalUser());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getApprovalStatus(), res.get(0)
            .getApprovalStatus());
        assertEquals("'getInterestAdjustments' should be correct.", payment.getId(), res.get(0).getPaymentId());
    }

    /**
     * <p>
     * Failure test for the method <code>getInterestAdjustments(String approver, ApprovalStatus approvalStatus)</code>
     * with approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getInterestAdjustments_approverNull() throws Exception {
        instance.getInterestAdjustments(null, ApprovalStatus.PENDING);
    }

    /**
     * <p>
     * Failure test for the method <code>getInterestAdjustments(String approver, ApprovalStatus approvalStatus)</code>
     * with approver is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getInterestAdjustments_approverEmpty() throws Exception {
        instance.getInterestAdjustments(TestsHelper.EMPTY_STRING, ApprovalStatus.PENDING);
    }

    /**
     * <p>
     * Failure test for the method <code>getInterestAdjustments(String approver, ApprovalStatus approvalStatus)</code>
     * with approvalStatus is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getInterestAdjustments_approvalStatusNull() throws Exception {
        instance.getInterestAdjustments(approver, null);
    }

    /**
     * <p>
     * Accuracy test for the method
     * <code>approveInterestAdjustments(String approver, long[] interestAdjustmentIds)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_approveInterestAdjustments() throws Exception {
        payment.setPaymentType(PaymentType.INTEREST_ADJUSTMENT);
        update(payment);

        entityManager.getTransaction().begin();
        instance.approveInterestAdjustments(approver, new long[] {payment.getId()});
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'approveInterestAdjustments' should be correct.", approver, retrievedPayment.getApprovalUser());
        assertEquals("'approveInterestAdjustments' should be correct.", ApprovalStatus.APPROVED,
            retrievedPayment.getApprovalStatus());
    }

    /**
     * <p>
     * Failure test for the method
     * <code>approveInterestAdjustments(String approver, long[] interestAdjustmentIds)</code> with approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approveInterestAdjustments_approverNull() throws Exception {
        instance.approveInterestAdjustments(null, new long[] {payment.getId()});
    }

    /**
     * <p>
     * Failure test for the method
     * <code>approveInterestAdjustments(String approver, long[] interestAdjustmentIds)</code> with approver is
     * empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approveInterestAdjustments_approverEmpty() throws Exception {
        instance.approveInterestAdjustments(TestsHelper.EMPTY_STRING, new long[] {payment.getId()});
    }

    /**
     * <p>
     * Failure test for the method
     * <code>approveInterestAdjustments(String approver, long[] interestAdjustmentIds)</code> with interestAdjustmentIds
     * is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approveInterestAdjustments_interestAdjustmentIdsNull() throws Exception {
        instance.approveInterestAdjustments(approver, null);
    }

    /**
     * <p>
     * Failure test for the method
     * <code>approveInterestAdjustments(String approver, long[] interestAdjustmentIds)</code> with paymentIds doesn't
     * exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_approveInterestAdjustments_paymentNotExist() throws Exception {
        instance.approveInterestAdjustments(approver, new long[] {Long.MAX_VALUE});
    }

    /**
     * <p>
     * Accuracy test for the method <code>disapproveInterestAdjustments(String approver, long[] interestAdjustmentIds,
     * String reason)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_disapproveInterestAdjustments() throws Exception {
        payment.setPaymentType(PaymentType.INTEREST_ADJUSTMENT);
        update(payment);

        entityManager.getTransaction().begin();
        instance.disapproveInterestAdjustments(approver, new long[] {payment.getId()}, "reason");
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'disapproveInterestAdjustments' should be correct.",
            approver, retrievedPayment.getApprovalUser());
        assertEquals("'disapproveInterestAdjustments' should be correct.", ApprovalStatus.DISAPPROVED,
            retrievedPayment.getApprovalStatus());
        assertEquals("'disapproveInterestAdjustments' should be correct.", "reason",
            retrievedPayment.getApprovalReason());
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapproveInterestAdjustments(String approver, long[] interestAdjustmentIds,
     * String reason)</code> with approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_disapproveInterestAdjustments_approverNull() throws Exception {
        instance.disapproveInterestAdjustments(null, new long[] {payment.getId()}, "reason");
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapproveInterestAdjustments(String approver, long[] interestAdjustmentIds,
     * String reason)</code> with approver is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_disapproveInterestAdjustments_approverEmpty() throws Exception {
        instance.disapproveInterestAdjustments(TestsHelper.EMPTY_STRING, new long[] {payment.getId()}, "reason");
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapproveInterestAdjustments(String approver, long[] interestAdjustmentIds,
     * String reason)</code> with interestAdjustmentIds is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_disapproveInterestAdjustments_interestAdjustmentIdsNull() throws Exception {
        instance.disapproveInterestAdjustments(approver, null, "reason");
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapproveInterestAdjustments(String approver, long[] interestAdjustmentIds,
     * String reason)</code> with paymentIds doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_disapproveInterestAdjustments_paymentNotExist() throws Exception {
        instance.disapproveInterestAdjustments(approver, new long[] {Long.MAX_VALUE}, "reason");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentMoves(String approver, ApprovalStatus approvalStatus)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getPaymentMoves_1() throws Exception {
        clearDB();

        List<PaymentMove> res = instance.getPaymentMoves(approver, ApprovalStatus.PENDING);

        assertEquals("'getPaymentMoves' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentMoves(String approver, ApprovalStatus approvalStatus)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getPaymentMoves_2() throws Exception {
        payment.setPaymentType(PaymentType.PAYMENT_MOVE);
        update(payment);

        List<PaymentMove> res = instance.getPaymentMoves(approver, ApprovalStatus.PENDING);

        assertEquals("'getPaymentMoves' should be correct.", 1, res.size());
        assertEquals("'getPaymentMoves' should be correct.", payment.getClaimNumber(), res.get(0).getClaimNumber());
        assertEquals("'getPaymentMoves' should be correct.", payment.getPreDepositAmount().intValue(), res.get(0)
            .getPreDepositAmount().intValue());
        assertEquals("'getPaymentMoves' should be correct.", payment.getPreRedepositAmount().intValue(), res.get(0)
            .getPreRedepositAmount().intValue());
        assertEquals("'getPaymentMoves' should be correct.", payment.getPostDepositAmount().intValue(), res.get(0)
            .getPostDepositAmount().intValue());
        assertEquals("'getPaymentMoves' should be correct.", payment.getPostRedepositAmount().intValue(), res.get(0)
            .getPostRedepositAmount().intValue());
        assertEquals("'getPaymentMoves' should be correct.", payment.getAmount().intValue(), res.get(0)
            .getPaymentTransactionAmount().intValue());
        assertNotNull("'getPaymentMoves' should be correct.", res.get(0).getAdjustmentDate());
        assertEquals("'getPaymentMoves' should be correct.", payment.getAccountStatus().getId(), res.get(0)
            .getAccountStatus().getId());
        assertNotNull("'getPaymentMoves' should be correct.", res.get(0).getAccountHolderBirthdate());
        assertEquals("'getPaymentMoves' should be correct.", payment.getApprovalUser(), res.get(0).getApprovalUser());
        assertEquals("'getPaymentMoves' should be correct.", payment.getApprovalStatus(), res.get(0)
            .getApprovalStatus());
        assertEquals("'getPaymentMoves' should be correct.", payment.getId(), res.get(0).getPaymentId());
    }

    /**
     * <p>
     * Failure test for the method <code>getPaymentMoves(String approver, ApprovalStatus approvalStatus)</code> with
     * approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPaymentMoves_approverNull() throws Exception {
        instance.getPaymentMoves(null, ApprovalStatus.PENDING);
    }

    /**
     * <p>
     * Failure test for the method <code>getPaymentMoves(String approver, ApprovalStatus approvalStatus)</code> with
     * approver is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPaymentMoves_approverEmpty() throws Exception {
        instance.getPaymentMoves(TestsHelper.EMPTY_STRING, ApprovalStatus.PENDING);
    }

    /**
     * <p>
     * Failure test for the method <code>getPaymentMoves(String approver, ApprovalStatus approvalStatus)</code> with
     * approvalStatus is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPaymentMoves_approvalStatusNull() throws Exception {
        instance.getPaymentMoves(approver, null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>approvePaymentMoves(String approver, long[] paymentMoveIds)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_approvePaymentMoves() throws Exception {
        payment.setPaymentType(PaymentType.PAYMENT_MOVE);
        update(payment);

        entityManager.getTransaction().begin();
        instance.approvePaymentMoves(approver, new long[] {payment.getId()});
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'approvePaymentMoves' should be correct.", approver, retrievedPayment.getApprovalUser());
        assertEquals("'approvePaymentMoves' should be correct.", ApprovalStatus.APPROVED,
            retrievedPayment.getApprovalStatus());
    }

    /**
     * <p>
     * Failure test for the method <code>approvePaymentMoves(String approver, long[] paymentMoveIds)</code> with
     * approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approvePaymentMoves_approverNull() throws Exception {
        instance.approvePaymentMoves(null, new long[] {payment.getId()});
    }

    /**
     * <p>
     * Failure test for the method <code>approvePaymentMoves(String approver, long[] paymentMoveIds)</code> with
     * approver is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approvePaymentMoves_approverEmpty() throws Exception {
        instance.approvePaymentMoves(TestsHelper.EMPTY_STRING, new long[] {payment.getId()});
    }

    /**
     * <p>
     * Failure test for the method <code>approvePaymentMoves(String approver, long[] paymentMoveIds)</code> with
     * paymentMoveIds is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_approvePaymentMoves_paymentMoveIdsNull() throws Exception {
        instance.approvePaymentMoves(approver, null);
    }

    /**
     * <p>
     * Failure test for the method <code>approvePaymentMoves(String approver, long[] paymentMoveIds)</code> with payment
     * doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_approvePaymentMoves_paymentNotExist() throws Exception {
        instance.approvePaymentMoves(approver, new long[] {Long.MAX_VALUE});
    }

    /**
     * <p>
     * Accuracy test for the method <code>disapprovePaymentMoves(String approver, long[] paymentMoveIds,
     * String reason)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_disapprovePaymentMoves() throws Exception {
        payment.setPaymentType(PaymentType.PAYMENT_MOVE);
        update(payment);

        entityManager.getTransaction().begin();
        instance.disapprovePaymentMoves(approver, new long[] {payment.getId()}, "reason");
        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment retrievedPayment = entityManager.find(Payment.class, payment.getId());

        assertEquals("'disapprovePaymentMoves' should be correct.", approver, retrievedPayment.getApprovalUser());
        assertEquals("'disapprovePaymentMoves' should be correct.", ApprovalStatus.DISAPPROVED,
            retrievedPayment.getApprovalStatus());
        assertEquals("'disapprovePaymentMoves' should be correct.", "reason", retrievedPayment.getApprovalReason());
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapprovePaymentMoves(String approver, long[] paymentMoveIds,
     * String reason)</code> with approver is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_disapprovePaymentMoves_approverNull() throws Exception {
        instance.disapprovePaymentMoves(null, new long[] {payment.getId()}, "reason");
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapprovePaymentMoves(String approver, long[] paymentMoveIds,
     * String reason)</code> with approver is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_disapprovePaymentMoves_approverEmpty() throws Exception {
        instance.disapprovePaymentMoves(TestsHelper.EMPTY_STRING, new long[] {payment.getId()}, "reason");
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapprovePaymentMoves(String approver, long[] paymentMoveIds,
     * String reason)</code> with paymentMoveIds is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_disapprovePaymentMoves_paymentMoveIdsNull() throws Exception {
        instance.disapprovePaymentMoves(approver, null, "reason");
    }

    /**
     * <p>
     * Failure test for the method <code>disdisapprovePaymentMoves(String approver, long[] paymentMoveIds,
     * String reason)</code> with payment doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_disapprovePaymentMoves_paymentNotExist() throws Exception {
        instance.disapprovePaymentMoves(approver, new long[] {Long.MAX_VALUE}, "reason");
    }
}
