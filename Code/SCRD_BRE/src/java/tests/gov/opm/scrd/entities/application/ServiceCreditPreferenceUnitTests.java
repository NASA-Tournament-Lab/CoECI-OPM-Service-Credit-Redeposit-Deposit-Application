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
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link ServiceCreditPreference} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class ServiceCreditPreferenceUnitTests {
    /**
     * <p>
     * Represents the <code>ServiceCreditPreference</code> instance used in tests.
     * </p>
     */
    private ServiceCreditPreference instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ServiceCreditPreferenceUnitTests.class);
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
        instance = new ServiceCreditPreference();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>ServiceCreditPreference()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new ServiceCreditPreference();

        assertFalse("'useAgents' should be correct.", (Boolean) TestsHelper.getField(instance, "useAgents"));
        assertFalse("'useStatusBar' should be correct.", (Boolean) TestsHelper.getField(instance, "useStatusBar"));
        assertFalse("'useMessageBox' should be correct.", (Boolean) TestsHelper.getField(instance, "useMessageBox"));
        assertNull("'otherSetting' should be correct.", TestsHelper.getField(instance, "otherSetting"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>isUseAgents()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isUseAgents() {
        boolean value = true;
        instance.setUseAgents(value);

        assertTrue("'isUseAgents' should be correct.", instance.isUseAgents());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setUseAgents(boolean useAgents)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setUseAgents() {
        boolean value = true;
        instance.setUseAgents(value);

        assertTrue("'setUseAgents' should be correct.",
            (Boolean) TestsHelper.getField(instance, "useAgents"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isUseStatusBar()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isUseStatusBar() {
        boolean value = true;
        instance.setUseStatusBar(value);

        assertTrue("'isUseStatusBar' should be correct.", instance.isUseStatusBar());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setUseStatusBar(boolean useStatusBar)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setUseStatusBar() {
        boolean value = true;
        instance.setUseStatusBar(value);

        assertTrue("'setUseStatusBar' should be correct.",
            (Boolean) TestsHelper.getField(instance, "useStatusBar"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>isUseMessageBox()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_isUseMessageBox() {
        boolean value = true;
        instance.setUseMessageBox(value);

        assertTrue("'isUseMessageBox' should be correct.", instance.isUseMessageBox());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setUseMessageBox(boolean useMessageBox)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setUseMessageBox() {
        boolean value = true;
        instance.setUseMessageBox(value);

        assertTrue("'setUseMessageBox' should be correct.",
            (Boolean) TestsHelper.getField(instance, "useMessageBox"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getOtherSetting()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getOtherSetting() {
        String value = "new_value";
        instance.setOtherSetting(value);

        assertEquals("'getOtherSetting' should be correct.",
            value, instance.getOtherSetting());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setOtherSetting(String otherSetting)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setOtherSetting() {
        String value = "new_value";
        instance.setOtherSetting(value);

        assertEquals("'setOtherSetting' should be correct.",
            value, TestsHelper.getField(instance, "otherSetting"));
    }
}
