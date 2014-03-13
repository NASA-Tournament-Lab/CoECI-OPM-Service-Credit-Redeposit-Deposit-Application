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
 * Unit tests for {@link ServiceAnnouncement} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class ServiceAnnouncementUnitTests {
    /**
     * <p>
     * Represents the <code>ServiceAnnouncement</code> instance used in tests.
     * </p>
     */
    private ServiceAnnouncement instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ServiceAnnouncementUnitTests.class);
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
        instance = new ServiceAnnouncement();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>ServiceAnnouncement()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new ServiceAnnouncement();

        assertNull("'date' should be correct.", TestsHelper.getField(instance, "date"));
        assertNull("'details' should be correct.", TestsHelper.getField(instance, "details"));
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
     * Accuracy test for the method <code>getDetails()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDetails() {
        String value = "new_value";
        instance.setDetails(value);

        assertEquals("'getDetails' should be correct.",
            value, instance.getDetails());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDetails(String details)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDetails() {
        String value = "new_value";
        instance.setDetails(value);

        assertEquals("'setDetails' should be correct.",
            value, TestsHelper.getField(instance, "details"));
    }
}
