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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link AuditRecord} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class AuditRecordUnitTests {
    /**
     * <p>
     * Represents the <code>AuditRecord</code> instance used in tests.
     * </p>
     */
    private AuditRecord instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AuditRecordUnitTests.class);
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
        instance = new AuditRecord();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AuditRecord()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AuditRecord();

        assertNull("'username' should be correct.", TestsHelper.getField(instance, "username"));
        assertNull("'ipAddress' should be correct.", TestsHelper.getField(instance, "ipAddress"));
        assertNull("'actionName' should be correct.", TestsHelper.getField(instance, "actionName"));
        assertNull("'date' should be correct.", TestsHelper.getField(instance, "date"));
        assertNull("'parameters' should be correct.", TestsHelper.getField(instance, "parameters"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getUsername()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getUsername() {
        String value = "new_value";
        instance.setUsername(value);

        assertEquals("'getUsername' should be correct.",
            value, instance.getUsername());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setUsername(String username)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setUsername() {
        String value = "new_value";
        instance.setUsername(value);

        assertEquals("'setUsername' should be correct.",
            value, TestsHelper.getField(instance, "username"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getIpAddress()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getIpAddress() {
        String value = "new_value";
        instance.setIpAddress(value);

        assertEquals("'getIpAddress' should be correct.",
            value, instance.getIpAddress());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setIpAddress(String ipAddress)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setIpAddress() {
        String value = "new_value";
        instance.setIpAddress(value);

        assertEquals("'setIpAddress' should be correct.",
            value, TestsHelper.getField(instance, "ipAddress"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getActionName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getActionName() {
        String value = "new_value";
        instance.setActionName(value);

        assertEquals("'getActionName' should be correct.",
            value, instance.getActionName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setActionName(String actionName)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setActionName() {
        String value = "new_value";
        instance.setActionName(value);

        assertEquals("'setActionName' should be correct.",
            value, TestsHelper.getField(instance, "actionName"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDate() {
        Date value = new Date();
        instance.setDate(value);

        assertSame("'getDate' should be correct.",
            value, instance.getDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDate(Date date)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDate() {
        Date value = new Date();
        instance.setDate(value);

        assertSame("'setDate' should be correct.",
            value, TestsHelper.getField(instance, "date"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getParameters()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getParameters() {
        List<AuditParameterRecord> value = new ArrayList<AuditParameterRecord>();
        instance.setParameters(value);

        assertSame("'getParameters' should be correct.",
            value, instance.getParameters());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setParameters(List&lt;AuditParameterRecord&gt; parameters)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setParameters() {
        List<AuditParameterRecord> value = new ArrayList<AuditParameterRecord>();
        instance.setParameters(value);

        assertSame("'setParameters' should be correct.",
            value, TestsHelper.getField(instance, "parameters"));
    }
}
