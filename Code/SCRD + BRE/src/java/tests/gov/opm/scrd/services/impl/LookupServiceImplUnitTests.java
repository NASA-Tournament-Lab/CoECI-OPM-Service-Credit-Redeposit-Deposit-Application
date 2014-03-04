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
import static org.junit.Assert.assertNull;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.common.NamedEntity;
import gov.opm.scrd.entities.lookup.Role;

import gov.opm.scrd.services.OPMException;

import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link LookupServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class LookupServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>LookupServiceImpl</code> instance used in tests.
     * </p>
     */
    private LookupServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the lookup type used in tests.
     * </p>
     */
    private String lookupType = "Role";

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(LookupServiceImplUnitTests.class);
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

        instance = new LookupServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>LookupServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new LookupServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLookups(String lookupType)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getLookups_1() throws Exception {
        clearDB();

        List<NamedEntity> res = instance.getLookups(lookupType);

        assertEquals("'getLookups' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLookups(String lookupType)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getLookups_2() throws Exception {
        Role role1 = new Role();
        role1.setName("name1");
        create(role1);

        Role role2 = new Role();
        role2.setName("name2");
        create(role2);

        entityManager.clear();

        List<NamedEntity> res = instance.getLookups(lookupType);

        assertEquals("'getLookups' should be correct.", 2, res.size());

        NamedEntity entity1 = res.get(0);
        NamedEntity entity2 = res.get(1);
        if (entity1.getName().equals(role2.getName())) {
            entity1 = res.get(1);
            entity2 = res.get(0);
        }

        assertEquals("'getLookups' should be correct.", role1.getName(), entity1.getName());
        assertEquals("'getLookups' should be correct.", role2.getName(), entity2.getName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getLookups(String lookupType)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getLookups_3() throws Exception {
        Role role1 = new Role();
        role1.setName("name1");
        create(role1);

        Role role2 = new Role();
        role2.setDeleted(true);
        role2.setName("name2");
        create(role2);

        entityManager.clear();

        List<NamedEntity> res = instance.getLookups(lookupType);

        assertEquals("'getLookups' should be correct.", 1, res.size());

        NamedEntity entity1 = res.get(0);

        assertEquals("'getLookups' should be correct.", role1.getName(), entity1.getName());
    }

    /**
     * <p>
     * Failure test for the method <code>getLookups(String lookupType)</code>
     * with lookupType is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getLookups_lookupTypeNull() throws Exception {
        instance.getLookups(null);
    }

    /**
     * <p>
     * Failure test for the method <code>getLookups(String lookupType)</code>
     * with lookupType is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getLookups_lookupTypeEmpty() throws Exception {
        instance.getLookups(TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Failure test for the method <code>getLookups(String lookupType)</code>
     * with lookupType is invalid.<br>
     * <code>OPMException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMException.class)
    public void test_getLookups_lookupTypeInvalid() throws Exception {
        instance.getLookups("invalid_\n_type");
    }
}
