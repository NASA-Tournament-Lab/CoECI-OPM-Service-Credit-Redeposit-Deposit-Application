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

package gov.opm.scrd.entities.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import gov.opm.scrd.TestsHelper;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link PaymentMoveTransaction} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class PaymentMoveTransactionUnitTests {
    /**
     * <p>
     * Represents the <code>PaymentMoveTransaction</code> instance used in tests.
     * </p>
     */
    private PaymentMoveTransaction instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PaymentMoveTransactionUnitTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Before
    public void setUp() throws Exception {
        instance = new PaymentMoveTransaction();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>PaymentMoveTransaction()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new PaymentMoveTransaction();

        assertNull("'payTransactionKey' should be correct.", TestsHelper.getField(instance, "payTransactionKey"));
        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'totPayd' should be correct.", TestsHelper.getField(instance, "totPayd"));
        assertNull("'totPayr' should be correct.", TestsHelper.getField(instance, "totPayr"));
        assertNull("'totPayn' should be correct.", TestsHelper.getField(instance, "totPayn"));
        assertNull("'totPayvr' should be correct.", TestsHelper.getField(instance, "totPayvr"));
        assertNull("'totPayfers' should be correct.", TestsHelper.getField(instance, "totPayfers"));
        assertNull("'modificationDate' should be correct.", TestsHelper.getField(instance, "modificationDate"));
        assertNull("'approvalDate' should be correct.", TestsHelper.getField(instance, "approvalDate"));
        assertNull("'processedDate' should be correct.", TestsHelper.getField(instance, "processedDate"));
        assertNull("'technicianUserKey' should be correct.", TestsHelper.getField(instance, "technicianUserKey"));
        assertNull("'managerUserKey' should be correct.", TestsHelper.getField(instance, "managerUserKey"));
        assertNull("'approved' should be correct.", TestsHelper.getField(instance, "approved"));
        assertNull("'disapproved' should be correct.", TestsHelper.getField(instance, "disapproved"));
        assertNull("'modified' should be correct.", TestsHelper.getField(instance, "modified"));
        assertNull("'note' should be correct.", TestsHelper.getField(instance, "note"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getPayTransactionKey()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPayTransactionKey() {
        Long value = 1L;
        instance.setPayTransactionKey(value);

        assertEquals("'getPayTransactionKey' should be correct.",
            value, instance.getPayTransactionKey());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPayTransactionKey(Long payTransactionKey)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPayTransactionKey() {
        Long value = 1L;
        instance.setPayTransactionKey(value);

        assertEquals("'setPayTransactionKey' should be correct.",
            value, TestsHelper.getField(instance, "payTransactionKey"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getClaimNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getClaimNumber() {
        String value = "new_value";
        instance.setClaimNumber(value);

        assertEquals("'getClaimNumber' should be correct.",
            value, instance.getClaimNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setClaimNumber(String claimNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setClaimNumber() {
        String value = "new_value";
        instance.setClaimNumber(value);

        assertEquals("'setClaimNumber' should be correct.",
            value, TestsHelper.getField(instance, "claimNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotPayd()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotPayd() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayd(value);

        assertSame("'getTotPayd' should be correct.",
            value, instance.getTotPayd());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotPayd(BigDecimal totPayd)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotPayd() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayd(value);

        assertSame("'setTotPayd' should be correct.",
            value, TestsHelper.getField(instance, "totPayd"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotPayr()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotPayr() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayr(value);

        assertSame("'getTotPayr' should be correct.",
            value, instance.getTotPayr());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotPayr(BigDecimal totPayr)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotPayr() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayr(value);

        assertSame("'setTotPayr' should be correct.",
            value, TestsHelper.getField(instance, "totPayr"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotPayn()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotPayn() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayn(value);

        assertSame("'getTotPayn' should be correct.",
            value, instance.getTotPayn());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotPayn(BigDecimal totPayn)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotPayn() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayn(value);

        assertSame("'setTotPayn' should be correct.",
            value, TestsHelper.getField(instance, "totPayn"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotPayvr()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotPayvr() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayvr(value);

        assertSame("'getTotPayvr' should be correct.",
            value, instance.getTotPayvr());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotPayvr(BigDecimal totPayvr)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotPayvr() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayvr(value);

        assertSame("'setTotPayvr' should be correct.",
            value, TestsHelper.getField(instance, "totPayvr"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotPayfers()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotPayfers() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayfers(value);

        assertSame("'getTotPayfers' should be correct.",
            value, instance.getTotPayfers());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotPayfers(BigDecimal totPayfers)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotPayfers() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotPayfers(value);

        assertSame("'setTotPayfers' should be correct.",
            value, TestsHelper.getField(instance, "totPayfers"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getModificationDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getModificationDate() {
        Date value = new Date();
        instance.setModificationDate(value);

        assertSame("'getModificationDate' should be correct.",
            value, instance.getModificationDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setModificationDate(Date modificationDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setModificationDate() {
        Date value = new Date();
        instance.setModificationDate(value);

        assertSame("'setModificationDate' should be correct.",
            value, TestsHelper.getField(instance, "modificationDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getApprovalDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getApprovalDate() {
        Date value = new Date();
        instance.setApprovalDate(value);

        assertSame("'getApprovalDate' should be correct.",
            value, instance.getApprovalDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setApprovalDate(Date approvalDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setApprovalDate() {
        Date value = new Date();
        instance.setApprovalDate(value);

        assertSame("'setApprovalDate' should be correct.",
            value, TestsHelper.getField(instance, "approvalDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getProcessedDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getProcessedDate() {
        Date value = new Date();
        instance.setProcessedDate(value);

        assertSame("'getProcessedDate' should be correct.",
            value, instance.getProcessedDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setProcessedDate(Date processedDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setProcessedDate() {
        Date value = new Date();
        instance.setProcessedDate(value);

        assertSame("'setProcessedDate' should be correct.",
            value, TestsHelper.getField(instance, "processedDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTechnicianUserKey()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTechnicianUserKey() {
        Long value = 1L;
        instance.setTechnicianUserKey(value);

        assertEquals("'getTechnicianUserKey' should be correct.",
            value, instance.getTechnicianUserKey());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTechnicianUserKey(Long technicianUserKey)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTechnicianUserKey() {
        Long value = 1L;
        instance.setTechnicianUserKey(value);

        assertEquals("'setTechnicianUserKey' should be correct.",
            value, TestsHelper.getField(instance, "technicianUserKey"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getManagerUserKey()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getManagerUserKey() {
        Long value = 1L;
        instance.setManagerUserKey(value);

        assertEquals("'getManagerUserKey' should be correct.",
            value, instance.getManagerUserKey());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setManagerUserKey(Long managerUserKey)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setManagerUserKey() {
        Long value = 1L;
        instance.setManagerUserKey(value);

        assertEquals("'setManagerUserKey' should be correct.",
            value, TestsHelper.getField(instance, "managerUserKey"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getApproved()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getApproved() {
        Boolean value = true;
        instance.setApproved(value);

        assertEquals("'getApproved' should be correct.",
            value, instance.getApproved());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setApproved(Boolean approved)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setApproved() {
        Boolean value = true;
        instance.setApproved(value);

        assertEquals("'setApproved' should be correct.",
            value, TestsHelper.getField(instance, "approved"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDisapproved()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDisapproved() {
        Boolean value = true;
        instance.setDisapproved(value);

        assertEquals("'getDisapproved' should be correct.",
            value, instance.getDisapproved());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDisapproved(Boolean disapproved)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDisapproved() {
        Boolean value = true;
        instance.setDisapproved(value);

        assertEquals("'setDisapproved' should be correct.",
            value, TestsHelper.getField(instance, "disapproved"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getModified()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getModified() {
        Boolean value = true;
        instance.setModified(value);

        assertEquals("'getModified' should be correct.",
            value, instance.getModified());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setModified(Boolean modified)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setModified() {
        Boolean value = true;
        instance.setModified(value);

        assertEquals("'setModified' should be correct.",
            value, TestsHelper.getField(instance, "modified"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNote()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNote() {
        String value = "new_value";
        instance.setNote(value);

        assertEquals("'getNote' should be correct.",
            value, instance.getNote());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNote(String note)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNote() {
        String value = "new_value";
        instance.setNote(value);

        assertEquals("'setNote' should be correct.",
            value, TestsHelper.getField(instance, "note"));
    }
}
