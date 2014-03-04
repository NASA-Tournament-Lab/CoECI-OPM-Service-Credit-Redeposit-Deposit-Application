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

package gov.opm.scrd.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.ServiceCreditPreference;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link ServiceCreditPreferenceServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class ServiceCreditPreferenceServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>ServiceCreditPreferenceServiceImpl</code> instance used in tests.
     * </p>
     */
    private ServiceCreditPreferenceServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the default preference id used in tests.
     * </p>
     */
    private Long defaultPreferenceId;

    /**
     * <p>
     * Represents the preference used in tests.
     * </p>
     */
    private ServiceCreditPreference preference;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ServiceCreditPreferenceServiceImplUnitTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        entityManager = getEntityManager();

        logger = Logger.getLogger(getClass());

        preference = new ServiceCreditPreference();
        preference.setOtherSetting("otherSetting1");
        create(preference);

        defaultPreferenceId = preference.getId();

        instance = new ServiceCreditPreferenceServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "defaultPreferenceId", defaultPreferenceId);

        preference = new ServiceCreditPreference();
        preference.setUseAgents(true);
        preference.setUseMessageBox(true);
        preference.setUseStatusBar(true);
        preference.setOtherSetting("otherSetting2");
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>ServiceCreditPreferenceServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new ServiceCreditPreferenceServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));

        assertNull("'defaultPreferenceId' should be correct.", TestsHelper.getField(instance, "defaultPreferenceId"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getServiceCreditPreference()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getServiceCreditPreference_1() throws Exception {
        clearDB();

        ServiceCreditPreference res = instance.getServiceCreditPreference();

        assertNull("'getServiceCreditPreference' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getServiceCreditPreference()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getServiceCreditPreference_2() throws Exception {
        ServiceCreditPreference res = instance.getServiceCreditPreference();

        res.setDeleted(true);
        update(res);

        res = instance.getServiceCreditPreference();

        assertNull("'getServiceCreditPreference' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getServiceCreditPreference()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getServiceCreditPreference_3() throws Exception {
        ServiceCreditPreference res = instance.getServiceCreditPreference();

        assertFalse("'getServiceCreditPreference' should be correct.", res.isUseAgents());
        assertFalse("'getServiceCreditPreference' should be correct.", res.isUseStatusBar());
        assertFalse("'getServiceCreditPreference' should be correct.", res.isUseMessageBox());
        assertEquals("'getServiceCreditPreference' should be correct.", "otherSetting1", res.getOtherSetting());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setServiceCreditPreference(ServiceCreditPreference preference)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_setServiceCreditPreference() throws Exception {
        entityManager.getTransaction().begin();
        instance.setServiceCreditPreference(preference);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertEquals("'setServiceCreditPreference' should be correct.",
            defaultPreferenceId.longValue(), preference.getId());

        ServiceCreditPreference retrievedPreference =
            entityManager.find(ServiceCreditPreference.class, defaultPreferenceId);

        assertEquals("'setServiceCreditPreference' should be correct.",
            preference.isUseAgents(), retrievedPreference.isUseAgents());
        assertEquals("'setServiceCreditPreference' should be correct.",
            preference.isUseStatusBar(), retrievedPreference.isUseStatusBar());
        assertEquals("'setServiceCreditPreference' should be correct.",
            preference.isUseMessageBox(), retrievedPreference.isUseMessageBox());
        assertEquals("'setServiceCreditPreference' should be correct.",
            preference.getOtherSetting(), retrievedPreference.getOtherSetting());
    }

    /**
     * <p>
     * Failure test for the method <code>setServiceCreditPreference(ServiceCreditPreference preference)</code>
     * with preference is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setServiceCreditPreference_preferenceNull() throws Exception {
        instance.setServiceCreditPreference(null);
    }
}
