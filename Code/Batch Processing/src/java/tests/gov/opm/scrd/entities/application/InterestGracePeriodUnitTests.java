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
 * Unit tests for {@link InterestGracePeriod} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class InterestGracePeriodUnitTests {
    /**
     * <p>
     * Represents the <code>InterestGracePeriod</code> instance used in tests.
     * </p>
     */
    private InterestGracePeriod instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(InterestGracePeriodUnitTests.class);
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
        instance = new InterestGracePeriod();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>InterestGracePeriod()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new InterestGracePeriod();

        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'post982Redeposit' should be correct.", TestsHelper.getField(instance, "post982Redeposit"));
        assertNull("'pre1082Redeposit' should be correct.", TestsHelper.getField(instance, "pre1082Redeposit"));
        assertNull("'post982Deposit' should be correct.", TestsHelper.getField(instance, "post982Deposit"));
        assertNull("'pre1082Deposit' should be correct.", TestsHelper.getField(instance, "pre1082Deposit"));
        assertNull("'fersDeposit' should be correct.", TestsHelper.getField(instance, "fersDeposit"));
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
     * Accuracy test for the method <code>getPost982Redeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPost982Redeposit() {
        Boolean value = true;
        instance.setPost982Redeposit(value);

        assertEquals("'getPost982Redeposit' should be correct.",
            value, instance.getPost982Redeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPost982Redeposit(Boolean post982Redeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPost982Redeposit() {
        Boolean value = true;
        instance.setPost982Redeposit(value);

        assertEquals("'setPost982Redeposit' should be correct.",
            value, TestsHelper.getField(instance, "post982Redeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPre1082Redeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPre1082Redeposit() {
        Boolean value = true;
        instance.setPre1082Redeposit(value);

        assertEquals("'getPre1082Redeposit' should be correct.",
            value, instance.getPre1082Redeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPre1082Redeposit(Boolean pre1082Redeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPre1082Redeposit() {
        Boolean value = true;
        instance.setPre1082Redeposit(value);

        assertEquals("'setPre1082Redeposit' should be correct.",
            value, TestsHelper.getField(instance, "pre1082Redeposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPost982Deposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPost982Deposit() {
        Boolean value = true;
        instance.setPost982Deposit(value);

        assertEquals("'getPost982Deposit' should be correct.",
            value, instance.getPost982Deposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPost982Deposit(Boolean post982Deposit)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPost982Deposit() {
        Boolean value = true;
        instance.setPost982Deposit(value);

        assertEquals("'setPost982Deposit' should be correct.",
            value, TestsHelper.getField(instance, "post982Deposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPre1082Deposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPre1082Deposit() {
        Boolean value = true;
        instance.setPre1082Deposit(value);

        assertEquals("'getPre1082Deposit' should be correct.",
            value, instance.getPre1082Deposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPre1082Deposit(Boolean pre1082Deposit)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPre1082Deposit() {
        Boolean value = true;
        instance.setPre1082Deposit(value);

        assertEquals("'setPre1082Deposit' should be correct.",
            value, TestsHelper.getField(instance, "pre1082Deposit"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFersDeposit()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getFersDeposit() {
        Boolean value = true;
        instance.setFersDeposit(value);

        assertEquals("'getFersDeposit' should be correct.",
            value, instance.getFersDeposit());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFersDeposit(Boolean fersDeposit)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFersDeposit() {
        Boolean value = true;
        instance.setFersDeposit(value);

        assertEquals("'setFersDeposit' should be correct.",
            value, TestsHelper.getField(instance, "fersDeposit"));
    }
}
