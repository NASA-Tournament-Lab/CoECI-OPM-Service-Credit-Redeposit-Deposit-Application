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
 * Unit tests for {@link Printout} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class PrintoutUnitTests {
    /**
     * <p>
     * Represents the <code>Printout</code> instance used in tests.
     * </p>
     */
    private Printout instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PrintoutUnitTests.class);
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
        instance = new Printout();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Printout()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Printout();

        assertNull("'name' should be correct.", TestsHelper.getField(instance, "name"));
        assertNull("'printDate' should be correct.", TestsHelper.getField(instance, "printDate"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getName() {
        String value = "new_value";
        instance.setName(value);

        assertEquals("'getName' should be correct.",
            value, instance.getName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setName(String name)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setName() {
        String value = "new_value";
        instance.setName(value);

        assertEquals("'setName' should be correct.",
            value, TestsHelper.getField(instance, "name"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPrintDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPrintDate() {
        Date value = new Date();
        instance.setPrintDate(value);

        assertSame("'getPrintDate' should be correct.",
            value, instance.getPrintDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPrintDate(Date printDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPrintDate() {
        Date value = new Date();
        instance.setPrintDate(value);

        assertSame("'setPrintDate' should be correct.",
            value, TestsHelper.getField(instance, "printDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getContent()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getContent() {
        byte[] value = new byte[0];
        instance.setContent(value);

        assertSame("'getContent' should be correct.",
            value, instance.getContent());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setContent(byte[] content)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setContent() {
        byte[] value = new byte[0];
        instance.setContent(value);

        assertSame("'setContent' should be correct.",
            value, TestsHelper.getField(instance, "content"));
    }
}
