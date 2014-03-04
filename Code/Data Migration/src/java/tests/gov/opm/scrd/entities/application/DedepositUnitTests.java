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
import gov.opm.scrd.entities.lookup.DepositType;

import java.math.BigDecimal;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link Dedeposit} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class DedepositUnitTests {
    /**
     * <p>
     * Represents the <code>Dedeposit</code> instance used in tests.
     * </p>
     */
    private Dedeposit instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(DedepositUnitTests.class);
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
        instance = new Dedeposit();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Dedeposit()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Dedeposit();

        assertNull("'depositType' should be correct.", TestsHelper.getField(instance, "depositType"));
        assertNull("'deposit' should be correct.", TestsHelper.getField(instance, "deposit"));
        assertNull("'interest' should be correct.", TestsHelper.getField(instance, "interest"));
        assertNull("'total' should be correct.", TestsHelper.getField(instance, "total"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getDepositType()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDepositType() {
        DepositType value = DepositType.POST_10_82;
        instance.setDepositType(value);

        assertEquals("'getDepositType' should be correct.",
            value, instance.getDepositType());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDepositType(DepositType depositType)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDepositType() {
        DepositType value = DepositType.POST_10_82;
        instance.setDepositType(value);

        assertEquals("'setDepositType' should be correct.",
            value, TestsHelper.getField(instance, "depositType"));
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
     * Accuracy test for the method <code>getInterest()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setInterest(value);

        assertSame("'getInterest' should be correct.",
            value, instance.getInterest());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setInterest(BigDecimal interest)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setInterest() {
        BigDecimal value = new BigDecimal(1);
        instance.setInterest(value);

        assertSame("'setInterest' should be correct.",
            value, TestsHelper.getField(instance, "interest"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTotal()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTotal() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotal(value);

        assertSame("'getTotal' should be correct.",
            value, instance.getTotal());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTotal(BigDecimal total)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTotal() {
        BigDecimal value = new BigDecimal(1);
        instance.setTotal(value);

        assertSame("'setTotal' should be correct.",
            value, TestsHelper.getField(instance, "total"));
    }
}
