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
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link PayTransStatusCode} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class PayTransStatusCodeUnitTests {
    /**
     * <p>
     * Represents the <code>PayTransStatusCode</code> instance used in tests.
     * </p>
     */
    private PayTransStatusCode instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PayTransStatusCodeUnitTests.class);
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
        instance = new PayTransStatusCode();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>PayTransStatusCode()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new PayTransStatusCode();

        assertNull("'description' should be correct.", TestsHelper.getField(instance, "description"));
        assertNull("'category' should be correct.", TestsHelper.getField(instance, "category"));
        assertNull("'displayOrder' should be correct.", TestsHelper.getField(instance, "displayOrder"));
        assertNull("'nextStateLink' should be correct.", TestsHelper.getField(instance, "nextStateLink"));
        assertNull("'batchProcessingOrder' should be correct.", TestsHelper.getField(instance, "batchProcessingOrder"));
        assertNull("'finalState' should be correct.", TestsHelper.getField(instance, "finalState"));
        assertNull("'needsApproval' should be correct.", TestsHelper.getField(instance, "needsApproval"));
        assertNull("'showOnSuspense' should be correct.", TestsHelper.getField(instance, "showOnSuspense"));
        assertNull("'includeInBalance' should be correct.", TestsHelper.getField(instance, "includeInBalance"));
        assertNull("'nightlyBatch' should be correct.", TestsHelper.getField(instance, "nightlyBatch"));
        assertNull("'deletable' should be correct.", TestsHelper.getField(instance, "deletable"));
        assertNull("'reversable' should be correct.", TestsHelper.getField(instance, "reversable"));
        assertNull("'manualEntered' should be correct.", TestsHelper.getField(instance, "manualEntered"));
        assertNull("'suspenseAction' should be correct.", TestsHelper.getField(instance, "suspenseAction"));
        assertNull("'canHitGl' should be correct.", TestsHelper.getField(instance, "canHitGl"));
        assertNull("'reversingType' should be correct.", TestsHelper.getField(instance, "reversingType"));
        assertNull("'balancedScorecard' should be correct.", TestsHelper.getField(instance, "balancedScorecard"));
        assertNull("'sendToDbts' should be correct.", TestsHelper.getField(instance, "sendToDbts"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getDescription()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDescription() {
        String value = "new_value";
        instance.setDescription(value);

        assertEquals("'getDescription' should be correct.",
            value, instance.getDescription());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDescription(String description)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDescription() {
        String value = "new_value";
        instance.setDescription(value);

        assertEquals("'setDescription' should be correct.",
            value, TestsHelper.getField(instance, "description"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCategory()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCategory() {
        String value = "new_value";
        instance.setCategory(value);

        assertEquals("'getCategory' should be correct.",
            value, instance.getCategory());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCategory(String category)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCategory() {
        String value = "new_value";
        instance.setCategory(value);

        assertEquals("'setCategory' should be correct.",
            value, TestsHelper.getField(instance, "category"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDisplayOrder()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDisplayOrder() {
        Integer value = 1;
        instance.setDisplayOrder(value);

        assertEquals("'getDisplayOrder' should be correct.",
            value, instance.getDisplayOrder());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDisplayOrder(Integer displayOrder)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDisplayOrder() {
        Integer value = 1;
        instance.setDisplayOrder(value);

        assertEquals("'setDisplayOrder' should be correct.",
            value, TestsHelper.getField(instance, "displayOrder"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNextStateLink()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNextStateLink() {
        Integer value = 1;
        instance.setNextStateLink(value);

        assertEquals("'getNextStateLink' should be correct.",
            value, instance.getNextStateLink());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNextStateLink(Integer nextStateLink)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNextStateLink() {
        Integer value = 1;
        instance.setNextStateLink(value);

        assertEquals("'setNextStateLink' should be correct.",
            value, TestsHelper.getField(instance, "nextStateLink"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBatchProcessingOrder()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBatchProcessingOrder() {
        Integer value = 1;
        instance.setBatchProcessingOrder(value);

        assertEquals("'getBatchProcessingOrder' should be correct.",
            value, instance.getBatchProcessingOrder());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBatchProcessingOrder(Integer batchProcessingOrder)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBatchProcessingOrder() {
        Integer value = 1;
        instance.setBatchProcessingOrder(value);

        assertEquals("'setBatchProcessingOrder' should be correct.",
            value, TestsHelper.getField(instance, "batchProcessingOrder"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFinalState()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getFinalState() {
        Boolean value = true;
        instance.setFinalState(value);

        assertEquals("'getFinalState' should be correct.",
            value, instance.getFinalState());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFinalState(Boolean finalState)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFinalState() {
        Boolean value = true;
        instance.setFinalState(value);

        assertEquals("'setFinalState' should be correct.",
            value, TestsHelper.getField(instance, "finalState"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNeedsApproval()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNeedsApproval() {
        Boolean value = true;
        instance.setNeedsApproval(value);

        assertEquals("'getNeedsApproval' should be correct.",
            value, instance.getNeedsApproval());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNeedsApproval(Boolean needsApproval)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNeedsApproval() {
        Boolean value = true;
        instance.setNeedsApproval(value);

        assertEquals("'setNeedsApproval' should be correct.",
            value, TestsHelper.getField(instance, "needsApproval"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getShowOnSuspense()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getShowOnSuspense() {
        Boolean value = true;
        instance.setShowOnSuspense(value);

        assertEquals("'getShowOnSuspense' should be correct.",
            value, instance.getShowOnSuspense());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setShowOnSuspense(Boolean showOnSuspense)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setShowOnSuspense() {
        Boolean value = true;
        instance.setShowOnSuspense(value);

        assertEquals("'setShowOnSuspense' should be correct.",
            value, TestsHelper.getField(instance, "showOnSuspense"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getIncludeInBalance()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getIncludeInBalance() {
        Boolean value = true;
        instance.setIncludeInBalance(value);

        assertEquals("'getIncludeInBalance' should be correct.",
            value, instance.getIncludeInBalance());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setIncludeInBalance(Boolean includeInBalance)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setIncludeInBalance() {
        Boolean value = true;
        instance.setIncludeInBalance(value);

        assertEquals("'setIncludeInBalance' should be correct.",
            value, TestsHelper.getField(instance, "includeInBalance"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNightlyBatch()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getNightlyBatch() {
        Boolean value = true;
        instance.setNightlyBatch(value);

        assertEquals("'getNightlyBatch' should be correct.",
            value, instance.getNightlyBatch());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNightlyBatch(Boolean nightlyBatch)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setNightlyBatch() {
        Boolean value = true;
        instance.setNightlyBatch(value);

        assertEquals("'setNightlyBatch' should be correct.",
            value, TestsHelper.getField(instance, "nightlyBatch"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDeletable()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDeletable() {
        Boolean value = true;
        instance.setDeletable(value);

        assertEquals("'getDeletable' should be correct.",
            value, instance.getDeletable());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDeletable(Boolean deletable)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDeletable() {
        Boolean value = true;
        instance.setDeletable(value);

        assertEquals("'setDeletable' should be correct.",
            value, TestsHelper.getField(instance, "deletable"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReversable()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getReversable() {
        Boolean value = true;
        instance.setReversable(value);

        assertEquals("'getReversable' should be correct.",
            value, instance.getReversable());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setReversable(Boolean reversable)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setReversable() {
        Boolean value = true;
        instance.setReversable(value);

        assertEquals("'setReversable' should be correct.",
            value, TestsHelper.getField(instance, "reversable"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getManualEntered()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getManualEntered() {
        Boolean value = true;
        instance.setManualEntered(value);

        assertEquals("'getManualEntered' should be correct.",
            value, instance.getManualEntered());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setManualEntered(Boolean manualEntered)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setManualEntered() {
        Boolean value = true;
        instance.setManualEntered(value);

        assertEquals("'setManualEntered' should be correct.",
            value, TestsHelper.getField(instance, "manualEntered"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSuspenseAction()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSuspenseAction() {
        Boolean value = true;
        instance.setSuspenseAction(value);

        assertEquals("'getSuspenseAction' should be correct.",
            value, instance.getSuspenseAction());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSuspenseAction(Boolean suspenseAction)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSuspenseAction() {
        Boolean value = true;
        instance.setSuspenseAction(value);

        assertEquals("'setSuspenseAction' should be correct.",
            value, TestsHelper.getField(instance, "suspenseAction"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCanHitGl()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCanHitGl() {
        Boolean value = true;
        instance.setCanHitGl(value);

        assertEquals("'getCanHitGl' should be correct.",
            value, instance.getCanHitGl());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCanHitGl(Boolean canHitGl)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCanHitGl() {
        Boolean value = true;
        instance.setCanHitGl(value);

        assertEquals("'setCanHitGl' should be correct.",
            value, TestsHelper.getField(instance, "canHitGl"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getReversingType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getReversingType() {
        Boolean value = true;
        instance.setReversingType(value);

        assertEquals("'getReversingType' should be correct.",
            value, instance.getReversingType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setReversingType(Boolean reversingType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setReversingType() {
        Boolean value = true;
        instance.setReversingType(value);

        assertEquals("'setReversingType' should be correct.",
            value, TestsHelper.getField(instance, "reversingType"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBalancedScorecard()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBalancedScorecard() {
        Boolean value = true;
        instance.setBalancedScorecard(value);

        assertEquals("'getBalancedScorecard' should be correct.",
            value, instance.getBalancedScorecard());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBalancedScorecard(Boolean balancedScorecard)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBalancedScorecard() {
        Boolean value = true;
        instance.setBalancedScorecard(value);

        assertEquals("'setBalancedScorecard' should be correct.",
            value, TestsHelper.getField(instance, "balancedScorecard"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSendToDbts()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSendToDbts() {
        Boolean value = true;
        instance.setSendToDbts(value);

        assertEquals("'getSendToDbts' should be correct.",
            value, instance.getSendToDbts());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSendToDbts(Boolean sendToDbts)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSendToDbts() {
        Boolean value = true;
        instance.setSendToDbts(value);

        assertEquals("'setSendToDbts' should be correct.",
            value, TestsHelper.getField(instance, "sendToDbts"));
    }
}
