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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.lookup.AccountStatus;
import gov.opm.scrd.entities.lookup.FormType;
import gov.opm.scrd.entities.lookup.PayCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link Account} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Services - Account and Payment Services Assembly 1.0):</em>
 * <ol>
 * <li>Updated test cases for claimNumber.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: totalDeposit, totalRedeposit, totalVarRedeposit, totalNonDeposit, totalFersW,
 * accIntDep, accIntRdep, accIntNonDep, accIntVarRdep, accIntFers, totPayd, totPayr, totPayn, totPayvr, totPayfers,
 * computationDate, varIntComputationDate, lastAction, lastPay, payCode, timePeriod, additionalService, noInterest,
 * code20Date, flagPreredeposit, flagPostredeposit, priorClaimNumber, paymentOrder, newClaimNumber, stopAchPayment,
 * dbtsAccount</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.2
 */
public class AccountUnitTests {
    /**
     * <p>
     * Represents the <code>Account</code> instance used in tests.
     * </p>
     */
    private Account instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AccountUnitTests.class);
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
        instance = new Account();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Account()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Account();

        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'planType' should be correct.", TestsHelper.getField(instance, "planType"));
        assertNull("'formType' should be correct.", TestsHelper.getField(instance, "formType"));
        assertNull("'holder' should be correct.", TestsHelper.getField(instance, "holder"));
        assertNull("'status' should be correct.", TestsHelper.getField(instance, "status"));
        assertFalse("'grace' should be correct.", (Boolean) TestsHelper.getField(instance, "grace"));
        assertFalse("'frozen' should be correct.", (Boolean) TestsHelper.getField(instance, "frozen"));
        assertNull("'notes' should be correct.", TestsHelper.getField(instance, "notes"));
        assertNull("'claimOfficer' should be correct.", TestsHelper.getField(instance, "claimOfficer"));
        assertNull("'claimOfficerAssignmentDate' should be correct.",
            TestsHelper.getField(instance, "claimOfficerAssignmentDate"));
        assertNull("'claimantBirthdate' should be correct.", TestsHelper.getField(instance, "claimantBirthdate"));
        assertNull("'balance' should be correct.", TestsHelper.getField(instance, "balance"));
        assertNull("'returnedFromRecordsDate' should be correct.",
            TestsHelper.getField(instance, "returnedFromRecordsDate"));
        assertNull("'billingSummary' should be correct.", TestsHelper.getField(instance, "billingSummary"));
        assertNull("'fersDepositCalculationVersions' should be correct.",
            TestsHelper.getField(instance, "fersDepositCalculationVersions"));
        assertNull("'fersRedepositCalculationVersions' should be correct.",
            TestsHelper.getField(instance, "fersRedepositCalculationVersions"));
        assertNull("'paymentHistory' should be correct.", TestsHelper.getField(instance, "paymentHistory"));
        assertNull("'validity' should be correct.", TestsHelper.getField(instance, "validity"));
        assertNull("'totalDeposit' should be correct.", TestsHelper.getField(instance, "totalDeposit"));
        assertNull("'totalRedeposit' should be correct.", TestsHelper.getField(instance, "totalRedeposit"));
        assertNull("'totalVarRedeposit' should be correct.", TestsHelper.getField(instance, "totalVarRedeposit"));
        assertNull("'totalNonDeposit' should be correct.", TestsHelper.getField(instance, "totalNonDeposit"));
        assertNull("'totalFersW' should be correct.", TestsHelper.getField(instance, "totalFersW"));
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
        assertNull("'computationDate' should be correct.", TestsHelper.getField(instance, "computationDate"));
        assertNull("'varIntComputationDate' should be correct.",
            TestsHelper.getField(instance, "varIntComputationDate"));
        assertNull("'lastAction' should be correct.", TestsHelper.getField(instance, "lastAction"));
        assertNull("'lastPay' should be correct.", TestsHelper.getField(instance, "lastPay"));
        assertNull("'payCode' should be correct.", TestsHelper.getField(instance, "payCode"));
        assertNull("'timePeriod' should be correct.", TestsHelper.getField(instance, "timePeriod"));
        assertNull("'additionalService' should be correct.", TestsHelper.getField(instance, "additionalService"));
        assertNull("'noInterest' should be correct.", TestsHelper.getField(instance, "noInterest"));
        assertNull("'code20Date' should be correct.", TestsHelper.getField(instance, "code20Date"));
        assertNull("'flagPreredeposit' should be correct.", TestsHelper.getField(instance, "flagPreredeposit"));
        assertNull("'flagPostredeposit' should be correct.", TestsHelper.getField(instance, "flagPostredeposit"));
        assertNull("'priorClaimNumber' should be correct.", TestsHelper.getField(instance, "priorClaimNumber"));
        assertNull("'paymentOrder' should be correct.", TestsHelper.getField(instance, "paymentOrder"));
        assertNull("'newClaimNumber' should be correct.", TestsHelper.getField(instance, "newClaimNumber"));
        assertNull("'stopAchPayment' should be correct.", TestsHelper.getField(instance, "stopAchPayment"));
        assertNull("'dbtsAccount' should be correct.", TestsHelper.getField(instance, "dbtsAccount"));
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
     * Accuracy test for the method <code>getPlanType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPlanType() {
        String value = "new_value";
        instance.setPlanType(value);

        assertEquals("'getPlanType' should be correct.",
            value, instance.getPlanType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPlanType(String planType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPlanType() {
        String value = "new_value";
        instance.setPlanType(value);

        assertEquals("'setPlanType' should be correct.",
            value, TestsHelper.getField(instance, "planType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFormType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getFormType() {
        FormType value = new FormType();
        instance.setFormType(value);

        assertSame("'getFormType' should be correct.",
            value, instance.getFormType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFormType(FormType formType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFormType() {
        FormType value = new FormType();
        instance.setFormType(value);

        assertSame("'setFormType' should be correct.",
            value, TestsHelper.getField(instance, "formType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getHolder()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getHolder() {
        AccountHolder value = new AccountHolder();
        instance.setHolder(value);

        assertSame("'getHolder' should be correct.",
            value, instance.getHolder());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setHolder(AccountHolder holder)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setHolder() {
        AccountHolder value = new AccountHolder();
        instance.setHolder(value);

        assertSame("'setHolder' should be correct.",
            value, TestsHelper.getField(instance, "holder"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStatus()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStatus() {
        AccountStatus value = new AccountStatus();
        instance.setStatus(value);

        assertSame("'getStatus' should be correct.",
            value, instance.getStatus());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStatus(AccountStatus status)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStatus() {
        AccountStatus value = new AccountStatus();
        instance.setStatus(value);

        assertSame("'setStatus' should be correct.",
            value, TestsHelper.getField(instance, "status"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isGrace()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isGrace() {
        boolean value = true;
        instance.setGrace(value);

        assertTrue("'isGrace' should be correct.", instance.isGrace());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setGrace(boolean grace)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setGrace() {
        boolean value = true;
        instance.setGrace(value);

        assertTrue("'setGrace' should be correct.",
            (Boolean) TestsHelper.getField(instance, "grace"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isFrozen()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isFrozen() {
        boolean value = true;
        instance.setFrozen(value);

        assertTrue("'isFrozen' should be correct.", instance.isFrozen());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFrozen(boolean frozen)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFrozen() {
        boolean value = true;
        instance.setFrozen(value);

        assertTrue("'setFrozen' should be correct.",
            (Boolean) TestsHelper.getField(instance, "frozen"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNotes()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNotes() {
        List<AccountNote> value = new ArrayList<AccountNote>();
        instance.setNotes(value);

        assertSame("'getNotes' should be correct.",
            value, instance.getNotes());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNotes(List&lt;AccountNote&gt; notes)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNotes() {
        List<AccountNote> value = new ArrayList<AccountNote>();
        instance.setNotes(value);

        assertSame("'setNotes' should be correct.",
            value, TestsHelper.getField(instance, "notes"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getClaimOfficer()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getClaimOfficer() {
        String value = "new_value";
        instance.setClaimOfficer(value);

        assertEquals("'getClaimOfficer' should be correct.",
            value, instance.getClaimOfficer());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setClaimOfficer(String claimOfficer)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setClaimOfficer() {
        String value = "new_value";
        instance.setClaimOfficer(value);

        assertEquals("'setClaimOfficer' should be correct.",
            value, TestsHelper.getField(instance, "claimOfficer"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getClaimOfficerAssignmentDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getClaimOfficerAssignmentDate() {
        Date value = new Date();
        instance.setClaimOfficerAssignmentDate(value);

        assertSame("'getClaimOfficerAssignmentDate' should be correct.",
            value, instance.getClaimOfficerAssignmentDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setClaimOfficerAssignmentDate(Date claimOfficerAssignmentDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setClaimOfficerAssignmentDate() {
        Date value = new Date();
        instance.setClaimOfficerAssignmentDate(value);

        assertSame("'setClaimOfficerAssignmentDate' should be correct.",
            value, TestsHelper.getField(instance, "claimOfficerAssignmentDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getClaimantBirthdate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getClaimantBirthdate() {
        Date value = new Date();
        instance.setClaimantBirthdate(value);

        assertSame("'getClaimantBirthdate' should be correct.",
            value, instance.getClaimantBirthdate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setClaimantBirthdate(Date claimantBirthdate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setClaimantBirthdate() {
        Date value = new Date();
        instance.setClaimantBirthdate(value);

        assertSame("'setClaimantBirthdate' should be correct.",
            value, TestsHelper.getField(instance, "claimantBirthdate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBalance()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setBalance(value);

        assertSame("'getBalance' should be correct.",
            value, instance.getBalance());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBalance(BigDecimal balance)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBalance() {
        BigDecimal value = new BigDecimal(1);
        instance.setBalance(value);

        assertSame("'setBalance' should be correct.",
            value, TestsHelper.getField(instance, "balance"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReturnedFromRecordsDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getReturnedFromRecordsDate() {
        Date value = new Date();
        instance.setReturnedFromRecordsDate(value);

        assertSame("'getReturnedFromRecordsDate' should be correct.",
            value, instance.getReturnedFromRecordsDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setReturnedFromRecordsDate(Date returnedFromRecordsDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setReturnedFromRecordsDate() {
        Date value = new Date();
        instance.setReturnedFromRecordsDate(value);

        assertSame("'setReturnedFromRecordsDate' should be correct.",
            value, TestsHelper.getField(instance, "returnedFromRecordsDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBillingSummary()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBillingSummary() {
        BillingSummary value = new BillingSummary();
        instance.setBillingSummary(value);

        assertSame("'getBillingSummary' should be correct.",
            value, instance.getBillingSummary());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBillingSummary(BillingSummary billingSummary)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBillingSummary() {
        BillingSummary value = new BillingSummary();
        instance.setBillingSummary(value);

        assertSame("'setBillingSummary' should be correct.",
            value, TestsHelper.getField(instance, "billingSummary"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCalculationVersions()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCalculationVersions() {
        List<CalculationVersion> value = new ArrayList<CalculationVersion>();
        instance.setCalculationVersions(value);

        assertSame("'getCalculationVersions' should be correct.",
            value, instance.getCalculationVersions());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFersDepositCalculationVersions(List&lt;CalculationVersion&gt;
     * fersDepositCalculationVersions)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCalculationVersions() {
        List<CalculationVersion> value = new ArrayList<CalculationVersion>();
        instance.setCalculationVersions(value);

        assertSame("'setCalculationVersions' should be correct.",
            value, TestsHelper.getField(instance, "calculationVersions"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentHistory()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentHistory() {
        List<Payment> value = new ArrayList<Payment>();
        instance.setPaymentHistory(value);

        assertSame("'getPaymentHistory' should be correct.",
            value, instance.getPaymentHistory());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentHistory(List&lt;Payment&gt; paymentHistory)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentHistory() {
        List<Payment> value = new ArrayList<Payment>();
        instance.setPaymentHistory(value);

        assertSame("'setPaymentHistory' should be correct.",
            value, TestsHelper.getField(instance, "paymentHistory"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getValidity()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getValidity() {
        AccountConfirmationValidation value = new AccountConfirmationValidation();
        instance.setValidity(value);

        assertSame("'getValidity' should be correct.",
            value, instance.getValidity());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setValidity(AccountConfirmationValidation validity)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setValidity() {
        AccountConfirmationValidation value = new AccountConfirmationValidation();
        instance.setValidity(value);

        assertSame("'setValidity' should be correct.",
            value, TestsHelper.getField(instance, "validity"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalDeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getTotalDeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalDeposit(value);

        assertSame("'getTotalDeposit' should be correct.",
            value, instance.getTotalDeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalDeposit(BigDecimal totalDeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setTotalDeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalDeposit(value);

        assertSame("'setTotalDeposit' should be correct.",
            value, TestsHelper.getField(instance, "totalDeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalRedeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getTotalRedeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalRedeposit(value);

        assertSame("'getTotalRedeposit' should be correct.",
            value, instance.getTotalRedeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalRedeposit(BigDecimal totalRedeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setTotalRedeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalRedeposit(value);

        assertSame("'setTotalRedeposit' should be correct.",
            value, TestsHelper.getField(instance, "totalRedeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalVarRedeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getTotalVarRedeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalVarRedeposit(value);

        assertSame("'getTotalVarRedeposit' should be correct.",
            value, instance.getTotalVarRedeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalVarRedeposit(BigDecimal totalVarRedeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setTotalVarRedeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalVarRedeposit(value);

        assertSame("'setTotalVarRedeposit' should be correct.",
            value, TestsHelper.getField(instance, "totalVarRedeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalNonDeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getTotalNonDeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalNonDeposit(value);

        assertSame("'getTotalNonDeposit' should be correct.",
            value, instance.getTotalNonDeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalNonDeposit(BigDecimal totalNonDeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setTotalNonDeposit() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalNonDeposit(value);

        assertSame("'setTotalNonDeposit' should be correct.",
            value, TestsHelper.getField(instance, "totalNonDeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotalFersW()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getTotalFersW() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalFersW(value);

        assertSame("'getTotalFersW' should be correct.",
            value, instance.getTotalFersW());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotalFersW(BigDecimal totalFersW)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setTotalFersW() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotalFersW(value);

        assertSame("'setTotalFersW' should be correct.",
            value, TestsHelper.getField(instance, "totalFersW"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAccIntDep()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     * Accuracy test for the method <code>getComputationDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getComputationDate() {
        Date value = new Date();
        instance.setComputationDate(value);

        assertSame("'getComputationDate' should be correct.",
            value, instance.getComputationDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setComputationDate(Date computationDate)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setComputationDate() {
        Date value = new Date();
        instance.setComputationDate(value);

        assertSame("'setComputationDate' should be correct.",
            value, TestsHelper.getField(instance, "computationDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getVarIntComputationDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getVarIntComputationDate() {
        Date value = new Date();
        instance.setVarIntComputationDate(value);

        assertSame("'getVarIntComputationDate' should be correct.",
            value, instance.getVarIntComputationDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setVarIntComputationDate(Date varIntComputationDate)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setVarIntComputationDate() {
        Date value = new Date();
        instance.setVarIntComputationDate(value);

        assertSame("'setVarIntComputationDate' should be correct.",
            value, TestsHelper.getField(instance, "varIntComputationDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastAction()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getLastAction() {
        String value = "Action";
        instance.setLastAction(value);

        assertSame("'getLastAction' should be correct.",
            value, instance.getLastAction());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastAction(Date lastAction)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setLastAction() {
        String value = "Action";
        instance.setLastAction(value);

        assertSame("'setLastAction' should be correct.",
            value, TestsHelper.getField(instance, "lastAction"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLastPay()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
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
     * Accuracy test for the method <code>getPayCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getPayCode() {
        PayCode value = new PayCode();
        instance.setPayCode(value);

        assertSame("'getPayCode' should be correct.",
            value, instance.getPayCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPayCode(PayCode payCode)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setPayCode() {
        PayCode value = new PayCode();
        instance.setPayCode(value);

        assertSame("'setPayCode' should be correct.",
            value, TestsHelper.getField(instance, "payCode"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTimePeriod()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getTimePeriod() {
        String value = "new_value";
        instance.setTimePeriod(value);

        assertEquals("'getTimePeriod' should be correct.",
            value, instance.getTimePeriod());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTimePeriod(String timePeriod)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setTimePeriod() {
        String value = "new_value";
        instance.setTimePeriod(value);

        assertEquals("'setTimePeriod' should be correct.",
            value, TestsHelper.getField(instance, "timePeriod"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAdditionalService()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getAdditionalService() {
        String value = "new_value";
        instance.setAdditionalService(value);

        assertEquals("'getAdditionalService' should be correct.",
            value, instance.getAdditionalService());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAdditionalService(String additionalService)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setAdditionalService() {
        String value = "new_value";
        instance.setAdditionalService(value);

        assertEquals("'setAdditionalService' should be correct.",
            value, TestsHelper.getField(instance, "additionalService"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNoInterest()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getNoInterest() {
        Boolean value = true;
        instance.setNoInterest(value);

        assertEquals("'getNoInterest' should be correct.",
            value, instance.getNoInterest());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNoInterest(Boolean noInterest)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setNoInterest() {
        Boolean value = true;
        instance.setNoInterest(value);

        assertEquals("'setNoInterest' should be correct.",
            value, TestsHelper.getField(instance, "noInterest"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCode20Date()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getCode20Date() {
        Date value = new Date();
        instance.setCode20Date(value);

        assertSame("'getCode20Date' should be correct.",
            value, instance.getCode20Date());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCode20Date(Date code20Date)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setCode20Date() {
        Date value = new Date();
        instance.setCode20Date(value);

        assertSame("'setCode20Date' should be correct.",
            value, TestsHelper.getField(instance, "code20Date"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFlagPreredeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getFlagPreredeposit() {
        Boolean value = true;
        instance.setFlagPreredeposit(value);

        assertEquals("'getFlagPreredeposit' should be correct.",
            value, instance.getFlagPreredeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFlagPreredeposit(Boolean flagPreredeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setFlagPreredeposit() {
        Boolean value = true;
        instance.setFlagPreredeposit(value);

        assertEquals("'setFlagPreredeposit' should be correct.",
            value, TestsHelper.getField(instance, "flagPreredeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFlagPostredeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getFlagPostredeposit() {
        Boolean value = true;
        instance.setFlagPostredeposit(value);

        assertEquals("'getFlagPostredeposit' should be correct.",
            value, instance.getFlagPostredeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFlagPostredeposit(Boolean flagPostredeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setFlagPostredeposit() {
        Boolean value = true;
        instance.setFlagPostredeposit(value);

        assertEquals("'setFlagPostredeposit' should be correct.",
            value, TestsHelper.getField(instance, "flagPostredeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPriorClaimNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getPriorClaimNumber() {
        String value = "new_value";
        instance.setPriorClaimNumber(value);

        assertEquals("'getPriorClaimNumber' should be correct.",
            value, instance.getPriorClaimNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPriorClaimNumber(String priorClaimNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setPriorClaimNumber() {
        String value = "new_value";
        instance.setPriorClaimNumber(value);

        assertEquals("'setPriorClaimNumber' should be correct.",
            value, TestsHelper.getField(instance, "priorClaimNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentOrder()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getPaymentOrder() {
        String value = "new_value";
        instance.setPaymentOrder(value);

        assertEquals("'getPaymentOrder' should be correct.",
            value, instance.getPaymentOrder());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentOrder(String paymentOrder)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setPaymentOrder() {
        String value = "new_value";
        instance.setPaymentOrder(value);

        assertEquals("'setPaymentOrder' should be correct.",
            value, TestsHelper.getField(instance, "paymentOrder"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNewClaimNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getNewClaimNumber() {
        String value = "new_value";
        instance.setNewClaimNumber(value);

        assertEquals("'getNewClaimNumber' should be correct.",
            value, instance.getNewClaimNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNewClaimNumber(String newClaimNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setNewClaimNumber() {
        String value = "new_value";
        instance.setNewClaimNumber(value);

        assertEquals("'setNewClaimNumber' should be correct.",
            value, TestsHelper.getField(instance, "newClaimNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStopAchPayment()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getStopAchPayment() {
        Boolean value = true;
        instance.setStopAchPayment(value);

        assertEquals("'getStopAchPayment' should be correct.",
            value, instance.getStopAchPayment());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStopAchPayment(Boolean stopAchPayment)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setStopAchPayment() {
        Boolean value = true;
        instance.setStopAchPayment(value);

        assertEquals("'setStopAchPayment' should be correct.",
            value, TestsHelper.getField(instance, "stopAchPayment"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDbtsAccount()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getDbtsAccount() {
        Boolean value = true;
        instance.setDbtsAccount(value);

        assertEquals("'getDbtsAccount' should be correct.",
            value, instance.getDbtsAccount());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDbtsAccount(Boolean dbtsAccount)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setDbtsAccount() {
        Boolean value = true;
        instance.setDbtsAccount(value);

        assertEquals("'setDbtsAccount' should be correct.",
            value, TestsHelper.getField(instance, "dbtsAccount"));
    }
}
