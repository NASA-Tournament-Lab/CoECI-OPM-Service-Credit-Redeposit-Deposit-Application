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

import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link SCMFirstInsert} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class SCMFirstInsertUnitTests {
    /**
     * <p>
     * Represents the <code>SCMFirstInsert</code> instance used in tests.
     * </p>
     */
    private SCMFirstInsert instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(SCMFirstInsertUnitTests.class);
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
        instance = new SCMFirstInsert();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>SCMFirstInsert()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new SCMFirstInsert();

        assertNull("'claimNumber' should be correct.", TestsHelper.getField(instance, "claimNumber"));
        assertNull("'lastAction' should be correct.", TestsHelper.getField(instance, "lastAction"));
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
     * Accuracy test for the method <code>getLastAction()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastAction() {
        Date value = new Date();
        instance.setLastAction(value);

        assertSame("'getLastAction' should be correct.",
            value, instance.getLastAction());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastAction(Date lastAction)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastAction() {
        Date value = new Date();
        instance.setLastAction(value);

        assertSame("'setLastAction' should be correct.",
            value, TestsHelper.getField(instance, "lastAction"));
    }
}
