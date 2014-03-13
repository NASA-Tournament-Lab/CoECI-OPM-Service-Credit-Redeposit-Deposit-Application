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
 * Unit tests for {@link Invoice} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class InvoiceUnitTests {
    /**
     * <p>
     * Represents the <code>Invoice</code> instance used in tests.
     * </p>
     */
    private Invoice instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(InvoiceUnitTests.class);
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
        instance = new Invoice();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Invoice()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Invoice();

        assertNull("'payTransactionKey' should be correct.", TestsHelper.getField(instance, "payTransactionKey"));
        assertNull("'deposit' should be correct.", TestsHelper.getField(instance, "deposit"));
        assertNull("'redeposit' should be correct.", TestsHelper.getField(instance, "redeposit"));
        assertNull("'totVarRedeposit' should be correct.", TestsHelper.getField(instance, "totVarRedeposit"));
        assertNull("'nonDed' should be correct.", TestsHelper.getField(instance, "nonDed"));
        assertNull("'fersW' should be correct.", TestsHelper.getField(instance, "fersW"));
        assertNull("'accIntDep' should be correct.", TestsHelper.getField(instance, "accIntDep"));
        assertNull("'accIntRdep' should be correct.", TestsHelper.getField(instance, "accIntRdep"));
        assertNull("'accIntNonDep' should be correct.", TestsHelper.getField(instance, "accIntNonDep"));
        assertNull("'accIntVarRdep' should be correct.", TestsHelper.getField(instance, "accIntVarRdep"));
        assertNull("'accIntFers' should be correct.", TestsHelper.getField(instance, "accIntFers"));
        assertNull("'totPayd' should be correct.", TestsHelper.getField(instance, "totPayd"));
        assertNull("'totPayr' should be correct.", TestsHelper.getField(instance, "totPayr"));
        assertNull("'totPayn' should be correct.", TestsHelper.getField(instance, "totPayn"));
        assertNull("'totPayvr' should be correct.", TestsHelper.getField(instance, "totPayvr"));
        assertNull("'totPayfers' should be correct.", TestsHelper.getField(instance, "totPayfers"));
        assertNull("'lastPay' should be correct.", TestsHelper.getField(instance, "lastPay"));
        assertNull("'calcDate' should be correct.", TestsHelper.getField(instance, "calcDate"));
        assertNull("'lastInvoiceId' should be correct.", TestsHelper.getField(instance, "lastInvoiceId"));
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
     * Accuracy test for the method <code>getDeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setDeposit(value);

        assertSame("'getDeposit' should be correct.",
            value, instance.getDeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDeposit(BigDecimal deposit)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setDeposit(value);

        assertSame("'setDeposit' should be correct.",
            value, TestsHelper.getField(instance, "deposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRedeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRedeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setRedeposit(value);

        assertSame("'getRedeposit' should be correct.",
            value, instance.getRedeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRedeposit(BigDecimal redeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRedeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setRedeposit(value);

        assertSame("'setRedeposit' should be correct.",
            value, TestsHelper.getField(instance, "redeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotVarRedeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotVarRedeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotVarRedeposit(value);

        assertSame("'getTotVarRedeposit' should be correct.",
            value, instance.getTotVarRedeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotVarRedeposit(BigDecimal totVarRedeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotVarRedeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotVarRedeposit(value);

        assertSame("'setTotVarRedeposit' should be correct.",
            value, TestsHelper.getField(instance, "totVarRedeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNonDed()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNonDed() {
        BigDecimal value = new BigDecimal(1);
        instance.setNonDed(value);

        assertSame("'getNonDed' should be correct.",
            value, instance.getNonDed());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNonDed(BigDecimal nonDed)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNonDed() {
        BigDecimal value = new BigDecimal(1);
        instance.setNonDed(value);

        assertSame("'setNonDed' should be correct.",
            value, TestsHelper.getField(instance, "nonDed"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFersW()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getFersW() {
        BigDecimal value = new BigDecimal(1);
        instance.setFersW(value);

        assertSame("'getFersW' should be correct.",
            value, instance.getFersW());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFersW(BigDecimal fersW)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFersW() {
        BigDecimal value = new BigDecimal(1);
        instance.setFersW(value);

        assertSame("'setFersW' should be correct.",
            value, TestsHelper.getField(instance, "fersW"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccIntDep()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccIntDep() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntDep(value);

        assertSame("'getAccIntDep' should be correct.",
            value, instance.getAccIntDep());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccIntDep(BigDecimal accIntDep)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccIntDep() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntDep(value);

        assertSame("'setAccIntDep' should be correct.",
            value, TestsHelper.getField(instance, "accIntDep"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccIntRdep()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccIntRdep() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntRdep(value);

        assertSame("'getAccIntRdep' should be correct.",
            value, instance.getAccIntRdep());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccIntRdep(BigDecimal accIntRdep)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccIntRdep() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntRdep(value);

        assertSame("'setAccIntRdep' should be correct.",
            value, TestsHelper.getField(instance, "accIntRdep"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccIntNonDep()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccIntNonDep() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntNonDep(value);

        assertSame("'getAccIntNonDep' should be correct.",
            value, instance.getAccIntNonDep());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccIntNonDep(BigDecimal accIntNonDep)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccIntNonDep() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntNonDep(value);

        assertSame("'setAccIntNonDep' should be correct.",
            value, TestsHelper.getField(instance, "accIntNonDep"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccIntVarRdep()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccIntVarRdep() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntVarRdep(value);

        assertSame("'getAccIntVarRdep' should be correct.",
            value, instance.getAccIntVarRdep());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccIntVarRdep(BigDecimal accIntVarRdep)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccIntVarRdep() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntVarRdep(value);

        assertSame("'setAccIntVarRdep' should be correct.",
            value, TestsHelper.getField(instance, "accIntVarRdep"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccIntFers()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAccIntFers() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntFers(value);

        assertSame("'getAccIntFers' should be correct.",
            value, instance.getAccIntFers());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAccIntFers(BigDecimal accIntFers)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAccIntFers() {
        BigDecimal value = new BigDecimal(1);
        instance.setAccIntFers(value);

        assertSame("'setAccIntFers' should be correct.",
            value, TestsHelper.getField(instance, "accIntFers"));
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
     * Accuracy test for the method <code>getLastPay()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastPay() {
        Date value = new Date();
        instance.setLastPay(value);

        assertSame("'getLastPay' should be correct.",
            value, instance.getLastPay());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastPay(Date lastPay)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastPay() {
        Date value = new Date();
        instance.setLastPay(value);

        assertSame("'setLastPay' should be correct.",
            value, TestsHelper.getField(instance, "lastPay"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCalcDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCalcDate() {
        Date value = new Date();
        instance.setCalcDate(value);

        assertSame("'getCalcDate' should be correct.",
            value, instance.getCalcDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCalcDate(Date calcDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCalcDate() {
        Date value = new Date();
        instance.setCalcDate(value);

        assertSame("'setCalcDate' should be correct.",
            value, TestsHelper.getField(instance, "calcDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastInvoiceId()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastInvoiceId() {
        Long value = 1L;
        instance.setLastInvoiceId(value);

        assertEquals("'getLastInvoiceId' should be correct.",
            value, instance.getLastInvoiceId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastInvoiceId(Long lastInvoiceId)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastInvoiceId() {
        Long value = 1L;
        instance.setLastInvoiceId(value);

        assertEquals("'setLastInvoiceId' should be correct.",
            value, TestsHelper.getField(instance, "lastInvoiceId"));
    }
}
