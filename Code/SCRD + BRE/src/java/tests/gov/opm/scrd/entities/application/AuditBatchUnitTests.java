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
 * Unit tests for {@link AuditBatch} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class AuditBatchUnitTests {
    /**
     * <p>
     * Represents the <code>AuditBatch</code> instance used in tests.
     * </p>
     */
    private AuditBatch instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AuditBatchUnitTests.class);
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
        instance = new AuditBatch();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AuditBatch()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AuditBatch();

        assertNull("'eventYear' should be correct.", TestsHelper.getField(instance, "eventYear"));
        assertNull("'eventMonth' should be correct.", TestsHelper.getField(instance, "eventMonth"));
        assertNull("'eventDay' should be correct.", TestsHelper.getField(instance, "eventDay"));
        assertNull("'fileReceived' should be correct.", TestsHelper.getField(instance, "fileReceived"));
        assertNull("'dailyAction' should be correct.", TestsHelper.getField(instance, "dailyAction"));
        assertNull("'manualBatch' should be correct.", TestsHelper.getField(instance, "manualBatch"));
        assertNull("'errorImporting' should be correct.", TestsHelper.getField(instance, "errorImporting"));
        assertNull("'errorProcessing' should be correct.", TestsHelper.getField(instance, "errorProcessing"));
        assertNull("'latestBatch' should be correct.", TestsHelper.getField(instance, "latestBatch"));
        assertNull("'amountImported' should be correct.", TestsHelper.getField(instance, "amountImported"));
        assertNull("'amountProcessed' should be correct.", TestsHelper.getField(instance, "amountProcessed"));
        assertNull("'numberAccepted' should be correct.", TestsHelper.getField(instance, "numberAccepted"));
        assertNull("'numberUnresolved' should be correct.", TestsHelper.getField(instance, "numberUnresolved"));
        assertNull("'numberSuspended' should be correct.", TestsHelper.getField(instance, "numberSuspended"));
        assertNull("'numberAchAccepted' should be correct.", TestsHelper.getField(instance, "numberAchAccepted"));
        assertNull("'numberAchUnresolved' should be correct.", TestsHelper.getField(instance, "numberAchUnresolved"));
        assertNull("'numberAchSuspended' should be correct.", TestsHelper.getField(instance, "numberAchSuspended"));
        assertNull("'numberChangeRequests' should be correct.", TestsHelper.getField(instance, "numberChangeRequests"));
        assertNull("'paymentsProcessed' should be correct.", TestsHelper.getField(instance, "paymentsProcessed"));
        assertNull("'initialBillsProcessed' should be correct.",
            TestsHelper.getField(instance, "initialBillsProcessed"));
        assertNull("'reversedProcessed' should be correct.", TestsHelper.getField(instance, "reversedProcessed"));
        assertNull("'achStopLetters' should be correct.", TestsHelper.getField(instance, "achStopLetters"));
        assertNull("'refundMemos' should be correct.", TestsHelper.getField(instance, "refundMemos"));
        assertNull("'errorCountProcessing' should be correct.", TestsHelper.getField(instance, "errorCountProcessing"));
        assertNull("'userKey' should be correct.", TestsHelper.getField(instance, "userKey"));
        assertNull("'batchTime' should be correct.", TestsHelper.getField(instance, "batchTime"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getEventYear()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getEventYear() {
        Integer value = 1;
        instance.setEventYear(value);

        assertEquals("'getEventYear' should be correct.",
            value, instance.getEventYear());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setEventYear(Integer eventYear)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setEventYear() {
        Integer value = 1;
        instance.setEventYear(value);

        assertEquals("'setEventYear' should be correct.",
            value, TestsHelper.getField(instance, "eventYear"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getEventMonth()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getEventMonth() {
        Integer value = 1;
        instance.setEventMonth(value);

        assertEquals("'getEventMonth' should be correct.",
            value, instance.getEventMonth());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setEventMonth(Integer eventMonth)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setEventMonth() {
        Integer value = 1;
        instance.setEventMonth(value);

        assertEquals("'setEventMonth' should be correct.",
            value, TestsHelper.getField(instance, "eventMonth"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getEventDay()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getEventDay() {
        Integer value = 1;
        instance.setEventDay(value);

        assertEquals("'getEventDay' should be correct.",
            value, instance.getEventDay());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setEventDay(Integer eventDay)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setEventDay() {
        Integer value = 1;
        instance.setEventDay(value);

        assertEquals("'setEventDay' should be correct.",
            value, TestsHelper.getField(instance, "eventDay"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFileReceived()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getFileReceived() {
        Boolean value = true;
        instance.setFileReceived(value);

        assertEquals("'getFileReceived' should be correct.",
            value, instance.getFileReceived());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFileReceived(Boolean fileReceived)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFileReceived() {
        Boolean value = true;
        instance.setFileReceived(value);

        assertEquals("'setFileReceived' should be correct.",
            value, TestsHelper.getField(instance, "fileReceived"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDailyAction()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDailyAction() {
        Boolean value = true;
        instance.setDailyAction(value);

        assertEquals("'getDailyAction' should be correct.",
            value, instance.getDailyAction());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDailyAction(Boolean dailyAction)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDailyAction() {
        Boolean value = true;
        instance.setDailyAction(value);

        assertEquals("'setDailyAction' should be correct.",
            value, TestsHelper.getField(instance, "dailyAction"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getManualBatch()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getManualBatch() {
        Boolean value = true;
        instance.setManualBatch(value);

        assertEquals("'getManualBatch' should be correct.",
            value, instance.getManualBatch());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setManualBatch(Boolean manualBatch)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setManualBatch() {
        Boolean value = true;
        instance.setManualBatch(value);

        assertEquals("'setManualBatch' should be correct.",
            value, TestsHelper.getField(instance, "manualBatch"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getErrorImporting()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getErrorImporting() {
        Boolean value = true;
        instance.setErrorImporting(value);

        assertEquals("'getErrorImporting' should be correct.",
            value, instance.getErrorImporting());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setErrorImporting(Boolean errorImporting)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setErrorImporting() {
        Boolean value = true;
        instance.setErrorImporting(value);

        assertEquals("'setErrorImporting' should be correct.",
            value, TestsHelper.getField(instance, "errorImporting"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getErrorProcessing()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getErrorProcessing() {
        Boolean value = true;
        instance.setErrorProcessing(value);

        assertEquals("'getErrorProcessing' should be correct.",
            value, instance.getErrorProcessing());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setErrorProcessing(Boolean errorProcessing)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setErrorProcessing() {
        Boolean value = true;
        instance.setErrorProcessing(value);

        assertEquals("'setErrorProcessing' should be correct.",
            value, TestsHelper.getField(instance, "errorProcessing"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLatestBatch()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLatestBatch() {
        Boolean value = true;
        instance.setLatestBatch(value);

        assertEquals("'getLatestBatch' should be correct.",
            value, instance.getLatestBatch());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLatestBatch(Boolean latestBatch)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLatestBatch() {
        Boolean value = true;
        instance.setLatestBatch(value);

        assertEquals("'setLatestBatch' should be correct.",
            value, TestsHelper.getField(instance, "latestBatch"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAmountImported()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAmountImported() {
        BigDecimal value = new BigDecimal(1);
        instance.setAmountImported(value);

        assertSame("'getAmountImported' should be correct.",
            value, instance.getAmountImported());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAmountImported(BigDecimal amountImported)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAmountImported() {
        BigDecimal value = new BigDecimal(1);
        instance.setAmountImported(value);

        assertSame("'setAmountImported' should be correct.",
            value, TestsHelper.getField(instance, "amountImported"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAmountProcessed()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAmountProcessed() {
        BigDecimal value = new BigDecimal(1);
        instance.setAmountProcessed(value);

        assertSame("'getAmountProcessed' should be correct.",
            value, instance.getAmountProcessed());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAmountProcessed(BigDecimal amountProcessed)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAmountProcessed() {
        BigDecimal value = new BigDecimal(1);
        instance.setAmountProcessed(value);

        assertSame("'setAmountProcessed' should be correct.",
            value, TestsHelper.getField(instance, "amountProcessed"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumberAccepted()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumberAccepted() {
        Integer value = 1;
        instance.setNumberAccepted(value);

        assertEquals("'getNumberAccepted' should be correct.",
            value, instance.getNumberAccepted());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumberAccepted(Integer numberAccepted)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumberAccepted() {
        Integer value = 1;
        instance.setNumberAccepted(value);

        assertEquals("'setNumberAccepted' should be correct.",
            value, TestsHelper.getField(instance, "numberAccepted"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumberUnresolved()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumberUnresolved() {
        Integer value = 1;
        instance.setNumberUnresolved(value);

        assertEquals("'getNumberUnresolved' should be correct.",
            value, instance.getNumberUnresolved());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumberUnresolved(Integer numberUnresolved)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumberUnresolved() {
        Integer value = 1;
        instance.setNumberUnresolved(value);

        assertEquals("'setNumberUnresolved' should be correct.",
            value, TestsHelper.getField(instance, "numberUnresolved"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumberSuspended()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumberSuspended() {
        Integer value = 1;
        instance.setNumberSuspended(value);

        assertEquals("'getNumberSuspended' should be correct.",
            value, instance.getNumberSuspended());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumberSuspended(Integer numberSuspended)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumberSuspended() {
        Integer value = 1;
        instance.setNumberSuspended(value);

        assertEquals("'setNumberSuspended' should be correct.",
            value, TestsHelper.getField(instance, "numberSuspended"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumberAchAccepted()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumberAchAccepted() {
        Integer value = 1;
        instance.setNumberAchAccepted(value);

        assertEquals("'getNumberAchAccepted' should be correct.",
            value, instance.getNumberAchAccepted());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumberAchAccepted(Integer numberAchAccepted)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumberAchAccepted() {
        Integer value = 1;
        instance.setNumberAchAccepted(value);

        assertEquals("'setNumberAchAccepted' should be correct.",
            value, TestsHelper.getField(instance, "numberAchAccepted"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumberAchUnresolved()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumberAchUnresolved() {
        Integer value = 1;
        instance.setNumberAchUnresolved(value);

        assertEquals("'getNumberAchUnresolved' should be correct.",
            value, instance.getNumberAchUnresolved());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumberAchUnresolved(Integer numberAchUnresolved)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumberAchUnresolved() {
        Integer value = 1;
        instance.setNumberAchUnresolved(value);

        assertEquals("'setNumberAchUnresolved' should be correct.",
            value, TestsHelper.getField(instance, "numberAchUnresolved"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumberAchSuspended()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumberAchSuspended() {
        Integer value = 1;
        instance.setNumberAchSuspended(value);

        assertEquals("'getNumberAchSuspended' should be correct.",
            value, instance.getNumberAchSuspended());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumberAchSuspended(Integer numberAchSuspended)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumberAchSuspended() {
        Integer value = 1;
        instance.setNumberAchSuspended(value);

        assertEquals("'setNumberAchSuspended' should be correct.",
            value, TestsHelper.getField(instance, "numberAchSuspended"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNumberChangeRequests()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNumberChangeRequests() {
        Integer value = 1;
        instance.setNumberChangeRequests(value);

        assertEquals("'getNumberChangeRequests' should be correct.",
            value, instance.getNumberChangeRequests());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNumberChangeRequests(Integer numberChangeRequests)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNumberChangeRequests() {
        Integer value = 1;
        instance.setNumberChangeRequests(value);

        assertEquals("'setNumberChangeRequests' should be correct.",
            value, TestsHelper.getField(instance, "numberChangeRequests"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPaymentsProcessed()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPaymentsProcessed() {
        Integer value = 1;
        instance.setPaymentsProcessed(value);

        assertEquals("'getPaymentsProcessed' should be correct.",
            value, instance.getPaymentsProcessed());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPaymentsProcessed(Integer paymentsProcessed)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPaymentsProcessed() {
        Integer value = 1;
        instance.setPaymentsProcessed(value);

        assertEquals("'setPaymentsProcessed' should be correct.",
            value, TestsHelper.getField(instance, "paymentsProcessed"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getInitialBillsProcessed()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getInitialBillsProcessed() {
        Integer value = 1;
        instance.setInitialBillsProcessed(value);

        assertEquals("'getInitialBillsProcessed' should be correct.",
            value, instance.getInitialBillsProcessed());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setInitialBillsProcessed(Integer initialBillsProcessed)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setInitialBillsProcessed() {
        Integer value = 1;
        instance.setInitialBillsProcessed(value);

        assertEquals("'setInitialBillsProcessed' should be correct.",
            value, TestsHelper.getField(instance, "initialBillsProcessed"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReversedProcessed()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getReversedProcessed() {
        Integer value = 1;
        instance.setReversedProcessed(value);

        assertEquals("'getReversedProcessed' should be correct.",
            value, instance.getReversedProcessed());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setReversedProcessed(Integer reversedProcessed)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setReversedProcessed() {
        Integer value = 1;
        instance.setReversedProcessed(value);

        assertEquals("'setReversedProcessed' should be correct.",
            value, TestsHelper.getField(instance, "reversedProcessed"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAchStopLetters()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAchStopLetters() {
        Integer value = 1;
        instance.setAchStopLetters(value);

        assertEquals("'getAchStopLetters' should be correct.",
            value, instance.getAchStopLetters());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAchStopLetters(Integer achStopLetters)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAchStopLetters() {
        Integer value = 1;
        instance.setAchStopLetters(value);

        assertEquals("'setAchStopLetters' should be correct.",
            value, TestsHelper.getField(instance, "achStopLetters"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getRefundMemos()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getRefundMemos() {
        Integer value = 1;
        instance.setRefundMemos(value);

        assertEquals("'getRefundMemos' should be correct.",
            value, instance.getRefundMemos());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setRefundMemos(Integer refundMemos)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setRefundMemos() {
        Integer value = 1;
        instance.setRefundMemos(value);

        assertEquals("'setRefundMemos' should be correct.",
            value, TestsHelper.getField(instance, "refundMemos"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getErrorCountProcessing()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getErrorCountProcessing() {
        Integer value = 1;
        instance.setErrorCountProcessing(value);

        assertEquals("'getErrorCountProcessing' should be correct.",
            value, instance.getErrorCountProcessing());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setErrorCountProcessing(Integer errorCountProcessing)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setErrorCountProcessing() {
        Integer value = 1;
        instance.setErrorCountProcessing(value);

        assertEquals("'setErrorCountProcessing' should be correct.",
            value, TestsHelper.getField(instance, "errorCountProcessing"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getUserKey()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getUserKey() {
        Long value = 1L;
        instance.setUserKey(value);

        assertEquals("'getUserKey' should be correct.",
            value, instance.getUserKey());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setUserKey(Long userKey)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setUserKey() {
        Long value = 1L;
        instance.setUserKey(value);

        assertEquals("'setUserKey' should be correct.",
            value, TestsHelper.getField(instance, "userKey"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBatchTime()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBatchTime() {
        Date value = new Date();
        instance.setBatchTime(value);

        assertSame("'getBatchTime' should be correct.",
            value, instance.getBatchTime());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBatchTime(Date batchTime)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBatchTime() {
        Date value = new Date();
        instance.setBatchTime(value);

        assertSame("'setBatchTime' should be correct.",
            value, TestsHelper.getField(instance, "batchTime"));
    }
}
