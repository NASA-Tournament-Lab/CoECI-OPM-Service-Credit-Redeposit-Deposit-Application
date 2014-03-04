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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.RolePermission;
import gov.opm.scrd.entities.application.UserPermission;
import gov.opm.scrd.services.AuthorizationException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cache;
import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link SecurityServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class SecurityServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>SecurityServiceImpl</code> instance used in tests.
     * </p>
     */
    private SecurityServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the cache used in tests.
     * </p>
     */
    private Cache cache;

    /**
     * <p>
     * Represents the username used in tests.
     * </p>
     */
    private String username = "username1";

    /**
     * <p>
     * Represents the roles used in tests.
     * </p>
     */
    private List<String> roles;

    /**
     * <p>
     * Represents the action used in tests.
     * </p>
     */
    private String action = "action1";

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(SecurityServiceImplUnitTests.class);
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

        cache = entityManager.getEntityManagerFactory().getCache();

        instance = new SecurityServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "cache", cache);

        roles = new ArrayList<String>();
        roles.add("role1");
        roles.add("role2");
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>SecurityServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new SecurityServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
        assertNull("'cache' should be correct.", TestsHelper.getField(instance, "cache"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_authorize_1() throws Exception {
        UserPermission userPermission = new UserPermission();
        userPermission.setUsername("username1");
        userPermission.setAction("action1");
        create(userPermission);

        instance.authorize(username, roles, action);

        // Good
    }

    /**
     * <p>
     * Accuracy test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_authorize_2() throws Exception {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRole("role1");
        rolePermission.setAction("action1");
        create(rolePermission);

        instance.authorize(username, roles, action);

        // Good
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code> with username is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_authorize_usernameNull() throws Exception {
        instance.authorize(null, roles, action);
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code> with username is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_authorize_usernameEmpty() throws Exception {
        instance.authorize(TestsHelper.EMPTY_STRING, roles, action);
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code> with roles is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_authorize_rolesNull() throws Exception {
        instance.authorize(username, null, action);
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code> with roles contains null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_authorize_rolesContainsNull() throws Exception {
        roles.add(null);

        instance.authorize(username, roles, action);
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code> with roles contains empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_authorize_rolesContainsEmpty() throws Exception {
        roles.add(TestsHelper.EMPTY_STRING);

        instance.authorize(username, roles, action);
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code> with action is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_authorize_actionNull() throws Exception {
        instance.authorize(username, roles, null);
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code> with action is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_authorize_actionEmpty() throws Exception {
        instance.authorize(username, roles, TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles, String action)</code> with
     * authorization failed.<br>
     * <code>AuthorizationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = AuthorizationException.class)
    public void test_authorize_AuthorizationFailed1() throws Exception {
        clearDB();

        instance.authorize(username, roles, action);
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code> with authorization failed.<br>
     * <code>AuthorizationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = AuthorizationException.class)
    public void test_authorize_AuthorizationFailed2() throws Exception {
        UserPermission userPermission = new UserPermission();
        userPermission.setDeleted(true);
        userPermission.setUsername("username1");
        userPermission.setAction("action1");
        create(userPermission);

        instance.authorize(username, roles, action);
    }

    /**
     * <p>
     * Failure test for the method <code>authorize(String username, List&lt;String&gt; roles,
     * String action)</code> with authorization failed.<br>
     * <code>AuthorizationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = AuthorizationException.class)
    public void test_authorize_AuthorizationFailed3() throws Exception {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setDeleted(true);
        rolePermission.setRole("role1");
        rolePermission.setAction("action1");
        create(rolePermission);

        instance.authorize(username, roles, action);
    }

    /**
     * <p>
     * Accuracy test for the method <code>clearSecurityCacheData()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_clearSecurityCacheData() throws Exception {
        UserPermission userPermission = new UserPermission();
        userPermission.setUsername("username1");
        userPermission.setAction("action1");
        create(userPermission);

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRole("role1");
        rolePermission.setAction("action1");
        create(rolePermission);

        entityManager.find(UserPermission.class, userPermission.getId());
        assertTrue("'clearSecurityCacheData' should be correct.",
            cache.contains(UserPermission.class, userPermission.getId()));

        entityManager.find(RolePermission.class, rolePermission.getId());
        assertTrue("'clearSecurityCacheData' should be correct.",
            cache.contains(RolePermission.class, rolePermission.getId()));

        instance.clearSecurityCacheData();

        assertFalse("'clearSecurityCacheData' should be correct.",
            cache.contains(UserPermission.class, userPermission.getId()));
        assertFalse("'clearSecurityCacheData' should be correct.",
            cache.contains(RolePermission.class, rolePermission.getId()));
    }
}
