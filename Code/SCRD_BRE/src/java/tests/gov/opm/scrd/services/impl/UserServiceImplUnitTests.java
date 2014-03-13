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
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.lookup.ActionTab;
import gov.opm.scrd.entities.lookup.Role;
import gov.opm.scrd.entities.lookup.UserStatus;
import gov.opm.scrd.services.EntityNotFoundException;

import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link UserServiceImpl} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Modify setUp() method to setup the adminRoleName</li>
 * </ul>
 * </p>
 *
 * @author sparemax, liuliquan
 * @version 1.1
 */
public class UserServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>UserServiceImpl</code> instance used in tests.
     * </p>
     */
    private UserServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the supervisor role name used in tests.
     * </p>
     */
    private String supervisorRoleName = "role1";

    /**
     * <p>
     * Represents the admin role name used in tests.
     * </p>
     */
    private String adminRoleName = "role2";

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(UserServiceImplUnitTests.class);
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

        instance = new UserServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
        TestsHelper.setField(instance, "supervisorRoleName", supervisorRoleName);
        TestsHelper.setField(instance, "adminRoleName", adminRoleName);
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>UserServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new UserServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
        assertNull("'supervisorRoleName' should be correct.",
            TestsHelper.getField(instance, "supervisorRoleName"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAll()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getAll_1() throws Exception {
        clearDB();

        List<User> res = instance.getAll();

        assertEquals("'getAll' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAll()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getAll_2() throws Exception {
        User user1 = createUser(1, false);
        User user2 = createUser(2, false);
        createUser(3, true);

        List<User> res = instance.getAll();

        assertEquals("'getAll' should be correct.", 2, res.size());

        User entity1 = res.get(0);
        User entity2 = res.get(1);
        if (entity1.getUsername().equals(user2.getUsername())) {
            entity1 = res.get(1);
            entity2 = res.get(0);
        }

        assertEquals("'getAll' should be correct.", user1.getUsername(), entity1.getUsername());
        assertEquals("'getAll' should be correct.", user1.getDefaultTab(), entity1.getDefaultTab());
        assertEquals("'getAll' should be correct.", user1.getNetworkId(), entity1.getNetworkId());
        assertEquals("'getAll' should be correct.", user1.getRole().getId(), entity1.getRole().getId());
        assertEquals("'getAll' should be correct.", user1.getFirstName(), entity1.getFirstName());
        assertEquals("'getAll' should be correct.", user1.getLastName(), entity1.getLastName());
        assertEquals("'getAll' should be correct.", user1.getEmail(), entity1.getEmail());
        assertEquals("'getAll' should be correct.", user1.getTelephone(), entity1.getTelephone());
        assertEquals("'getAll' should be correct.", user1.getStatus().getId(), entity1.getStatus().getId());

        assertEquals("'getAll' should be correct.", user2.getUsername(), entity2.getUsername());
        assertEquals("'getAll' should be correct.", user2.getDefaultTab(), entity2.getDefaultTab());
        assertEquals("'getAll' should be correct.", user2.getNetworkId(), entity2.getNetworkId());
        assertEquals("'getAll' should be correct.", user2.getRole().getId(), entity2.getRole().getId());
        assertEquals("'getAll' should be correct.", user2.getFirstName(), entity2.getFirstName());
        assertEquals("'getAll' should be correct.", user2.getLastName(), entity2.getLastName());
        assertEquals("'getAll' should be correct.", user2.getEmail(), entity2.getEmail());
        assertEquals("'getAll' should be correct.", user2.getTelephone(), entity2.getTelephone());
        assertEquals("'getAll' should be correct.", user2.getStatus().getId(), entity2.getStatus().getId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSupervisors()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getSupervisors_1() throws Exception {
        clearDB();

        List<User> res = instance.getSupervisors();

        assertEquals("'getSupervisors' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSupervisors()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getSupervisors_2() throws Exception {
        User user1 = createUser(1, false);
        createUser(2, false);
        createUser(3, true);

        List<User> res = instance.getSupervisors();

        assertEquals("'getSupervisors' should be correct.", 1, res.size());

        User entity1 = res.get(0);

        assertEquals("'getSupervisors' should be correct.", user1.getUsername(), entity1.getUsername());
        assertEquals("'getSupervisors' should be correct.", user1.getDefaultTab(), entity1.getDefaultTab());
        assertEquals("'getSupervisors' should be correct.", user1.getNetworkId(), entity1.getNetworkId());
        assertEquals("'getSupervisors' should be correct.", user1.getRole().getId(), entity1.getRole().getId());
        assertEquals("'getSupervisors' should be correct.", user1.getFirstName(), entity1.getFirstName());
        assertEquals("'getSupervisors' should be correct.", user1.getLastName(), entity1.getLastName());
        assertEquals("'getSupervisors' should be correct.", user1.getEmail(), entity1.getEmail());
        assertEquals("'getSupervisors' should be correct.", user1.getTelephone(), entity1.getTelephone());
        assertEquals("'getSupervisors' should be correct.", user1.getStatus().getId(), entity1.getStatus().getId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long userId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_1() throws Exception {
        clearDB();

        User res = instance.get(Long.MAX_VALUE);

        assertNull("'get' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long userId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_2() throws Exception {
        User user1 = createUser(1, true);

        User res = instance.get(user1.getId());

        assertNull("'get' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long userId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_3() throws Exception {
        User user1 = createUser(1, false);

        User res = instance.get(user1.getId());

        assertEquals("'get' should be correct.", user1.getUsername(), res.getUsername());
        assertEquals("'get' should be correct.", user1.getDefaultTab(), res.getDefaultTab());
        assertEquals("'get' should be correct.", user1.getNetworkId(), res.getNetworkId());
        assertEquals("'get' should be correct.", user1.getRole().getId(), res.getRole().getId());
        assertEquals("'get' should be correct.", user1.getFirstName(), res.getFirstName());
        assertEquals("'get' should be correct.", user1.getLastName(), res.getLastName());
        assertEquals("'get' should be correct.", user1.getEmail(), res.getEmail());
        assertEquals("'get' should be correct.", user1.getTelephone(), res.getTelephone());
        assertEquals("'get' should be correct.", user1.getStatus().getId(), res.getStatus().getId());
    }

    /**
     * <p>
     * Failure test for the method <code>get(long userId)</code>
     * with userId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_get_userIdNegative() throws Exception {
        instance.get(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>get(long userId)</code>
     * with userId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_get_userIdZero() throws Exception {
        instance.get(0);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getByUsername(String username)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getByUsername_1() throws Exception {
        clearDB();

        User res = instance.getByUsername("not_exist");

        assertNull("'getByUsername' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getByUsername(String username)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getByUsername_2() throws Exception {
        User user1 = createUser(1, true);

        User res = instance.getByUsername(user1.getUsername());

        assertNull("'getByUsername' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getByUsername(String username)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getByUsername_3() throws Exception {
        User user1 = createUser(1, false);

        User res = instance.getByUsername(user1.getUsername());

        assertEquals("'getByUsername' should be correct.", user1.getUsername(), res.getUsername());
        assertEquals("'getByUsername' should be correct.", user1.getDefaultTab(), res.getDefaultTab());
        assertEquals("'getByUsername' should be correct.", user1.getNetworkId(), res.getNetworkId());
        assertEquals("'getByUsername' should be correct.", user1.getRole().getId(), res.getRole().getId());
        assertEquals("'getByUsername' should be correct.", user1.getFirstName(), res.getFirstName());
        assertEquals("'getByUsername' should be correct.", user1.getLastName(), res.getLastName());
        assertEquals("'getByUsername' should be correct.", user1.getEmail(), res.getEmail());
        assertEquals("'getByUsername' should be correct.", user1.getTelephone(), res.getTelephone());
        assertEquals("'getByUsername' should be correct.", user1.getStatus().getId(), res.getStatus().getId());
    }

    /**
     * <p>
     * Failure test for the method <code>getByUsername(String username)</code>
     * with username is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getByUsername_usernameNull() throws Exception {
        instance.getByUsername(null);
    }

    /**
     * <p>
     * Failure test for the method <code>getByUsername(String username)</code>
     * with username is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getByUsername_usernameEmpty() throws Exception {
        instance.getByUsername(TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Accuracy test for the method <code>update(User user)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_update() throws Exception {
        User user1 = createUser(1, false);

        user1.setUsername("new");

        entityManager.getTransaction().begin();
        instance.update(user1);
        entityManager.getTransaction().commit();
        entityManager.clear();

        User retrievedUser = instance.get(user1.getId());

        assertEquals("'update' should be correct.", user1.getUsername(), retrievedUser.getUsername());
    }

    /**
     * <p>
     * Failure test for the method <code>update(User user)</code>
     * with user is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_update_userNull() throws Exception {
        instance.update(null);
    }

    /**
     * <p>
     * Failure test for the method <code>update(User user)</code>
     * with user is not found.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_update_NotFound1() throws Exception {
        User user1 = createUser(1, false);

        clearDB();

        user1.setUsername("new");
        instance.update(user1);
    }

    /**
     * <p>
     * Failure test for the method <code>update(User user)</code>
     * with user is not found.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_update_NotFound2() throws Exception {
        User user1 = createUser(1, true);

        user1.setUsername("new");
        instance.update(user1);
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDefaultTab(long userId, ActionTab tab)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_setDefaultTab() throws Exception {
        User user1 = createUser(1, false);

        entityManager.getTransaction().begin();
        instance.setDefaultTab(user1.getId(), ActionTab.TOOLS);
        entityManager.getTransaction().commit();
        entityManager.clear();

        User retrievedUser = instance.get(user1.getId());

        assertEquals("'setDefaultTab' should be correct.", ActionTab.TOOLS, retrievedUser.getDefaultTab());
    }

    /**
     * <p>
     * Failure test for the method <code>setDefaultTab(long userId, ActionTab tab)</code>
     * with userId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setDefaultTab_userIdNegative() throws Exception {
        instance.setDefaultTab(-1, ActionTab.TOOLS);
    }

    /**
     * <p>
     * Failure test for the method <code>setDefaultTab(long userId, ActionTab tab)</code>
     * with userId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setDefaultTab_userIdZero() throws Exception {
        instance.setDefaultTab(0, ActionTab.TOOLS);
    }

    /**
     * <p>
     * Failure test for the method <code>setDefaultTab(long userId, ActionTab tab)</code>
     * with tab is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setDefaultTab_tabNull() throws Exception {
        User user1 = createUser(1, false);

        instance.setDefaultTab(user1.getId(), null);
    }

    /**
     * <p>
     * Failure test for the method <code>setDefaultTab(long userId, ActionTab tab)</code>
     * with user is not found.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_setDefaultTab_NotFound1() throws Exception {
        User user1 = createUser(1, false);

        clearDB();

        instance.setDefaultTab(user1.getId(), ActionTab.TOOLS);
    }

    /**
     * <p>
     * Failure test for the method <code>setDefaultTab(long userId, ActionTab tab)</code>
     * with user is not found.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_setDefaultTab_NotFound2() throws Exception {
        User user1 = createUser(1, true);

        instance.setDefaultTab(user1.getId(), ActionTab.TOOLS);
    }

    /**
     * Creates an instance of User.
     *
     * @param index
     *            the index
     * @param deleted
     *            the deleted flag
     *
     * @return the User instance.
     */
    private User createUser(int index, boolean deleted) {
        User entity = new User();

        entity.setDeleted(deleted);
        entity.setUsername("username" + index);
        entity.setDefaultTab(ActionTab.ADMIN);
        entity.setNetworkId("networkId" + index);

        Role role = new Role();
        role.setName("role" + index);
        entity.setRole(role);
        create(entity.getRole());

        entity.setFirstName("firstName" + index);
        entity.setLastName("lastName" + index);
        entity.setEmail("email" + index);
        entity.setTelephone("telephone" + index);

        UserStatus userStatus = new UserStatus();
        userStatus.setName("userStatus" + index);
        entity.setStatus(userStatus);
        create(entity.getStatus());

        create(entity);

        return entity;
    }
}
