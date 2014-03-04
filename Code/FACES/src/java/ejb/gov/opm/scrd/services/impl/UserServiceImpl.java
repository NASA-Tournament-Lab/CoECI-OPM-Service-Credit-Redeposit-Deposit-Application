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

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.ActionTab;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.UserService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * This class is the implementation of the UserService. It utilizes JPA EntityManager for necessary operations.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Initial Module Assembly 1.0):</em>
 * <ul>
 * <li>Changed to use LoggingHelper instead for logging</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Modify update() method to check if there is admin user left</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, sparemax, liuliquan
 * @version 1.2
 */
@Stateless
@Local(UserService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserServiceImpl extends BaseService implements UserService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = UserServiceImpl.class.getName();

    /**
     * <p>
     * Represents the JPQL to query User.
     * </p>
     */
    private static final String JPQL_QUERY_USER = "SELECT e FROM User e WHERE e.deleted = false";

    /**
     * <p>
     * Represents the JPQL to query User by role name.
     * </p>
     */
    private static final String JPQL_QUERY_USER_BY_ROLE_NAME = "SELECT e FROM User e"
        + " WHERE e.deleted = false AND e.role.name = :supervisorRoleName";

    /**
     * <p>
     * Represents the JPQL to query User by username.
     * </p>
     */
    private static final String JPQL_QUERY_USER_BY_USERNAME = "SELECT e FROM User e"
        + " WHERE e.deleted = false AND e.username = :username";

    /**
     * <p>
     * Represents the JPQL to query User by id.
     * </p>
     */
    private static final String JPQL_QUERY_USER_BY_ID = "SELECT e FROM User e"
        + " WHERE e.deleted = false AND e.id = :id";

    /**
     * <p>
     * Represents the JPQL to count admin user.
     * </p>
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private static final String JPQL_COUNT_LEFT_ADMIN = "SELECT COUNT(u) FROM User u"
        + " WHERE u.role.name = :adminRoleName and u.id <> :userId";

    /**
     * Represents the name of Role instance which corresponds to supervisor role. It is injected by Spring. It can not
     * be null after injected.
     */
    @Autowired
    private String supervisorRoleName;

    /**
     * Represents the name of Role instance which corresponds to admin role. It is injected by Spring. It can not
     * be null after injected.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @Autowired
    private String adminRoleName;

    /**
     * Creates an instance of UserServiceImpl.
     */
    public UserServiceImpl() {
        // Empty
    }

    /**
     * Retrieves all available users.
     *
     * @return List of available users, can not be null/contain null elements.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> getAll() throws OPMException {
        String signature = CLASS_NAME + "#getAll()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        try {
            List<User> result = getEntityManager().createQuery(JPQL_QUERY_USER, User.class)
                .getResultList();

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Retrieves all available supervisors.
     *
     * @return List of available users with supervisor role, can not be null/contain null elements.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> getSupervisors() throws OPMException {
        String signature = CLASS_NAME + "#getSupervisors()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        try {
            List<User> result = getEntityManager().createQuery(JPQL_QUERY_USER_BY_ROLE_NAME, User.class)
                .setParameter("supervisorRoleName", supervisorRoleName)
                .getResultList();

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Retrieves user by id.
     *
     * @param userId
     *            the id of user to retrieve.
     *
     * @return User for the id or null if there is no such user.
     *
     * @throws IllegalArgumentException
     *             if userId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User get(long userId) throws OPMException {
        String signature = CLASS_NAME + "#get(long userId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"userId"},
            new Object[] {userId});

        Helper.checkPositive(logger, signature, userId, "userId");

        try {
            User result = getById(logger, signature, userId, false);

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Retrieves user by username.
     *
     * @param username
     *            the name of user to retrieve.
     *
     * @return User for the name or null if there is no such user.
     *
     * @throws IllegalArgumentException
     *             if username is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User getByUsername(String username) throws OPMException {
        String signature = CLASS_NAME + "#getByUsername(String username)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"username"},
            new Object[] {username});

        Helper.checkNullOrEmpty(logger, signature, username, "username");

        try {
            List<User> list = getEntityManager().createQuery(JPQL_QUERY_USER_BY_USERNAME, User.class)
                .setParameter("username", username)
                .getResultList();

            User result = list.isEmpty() ? null : list.get(0);

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Updates user.
     *
     * @param user
     *            the user to update.
     *
     * @throws IllegalArgumentException
     *             if user is null.
     * @throws EntityNotFoundException
     *             if there is no such user to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(User user) throws OPMException {
        String signature = CLASS_NAME + "#update(User user)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"user"},
            new Object[] {user});

        Helper.checkNull(logger, signature, user, "user");

        try {
            // Check user
            User existingUser = getById(logger, signature, user.getId(), true);

            // If user's role changes from admin to some other role,
            // check if there is any other admin user left
            if (adminRoleName.equals(existingUser.getRole().getName())
                    && !adminRoleName.equals(user.getRole().getName())) {
                int leftAdminCount = ((Number) getEntityManager().createQuery(JPQL_COUNT_LEFT_ADMIN)
                    .setParameter("adminRoleName", adminRoleName)
                    .setParameter("userId", user.getId())
                    .getSingleResult()).intValue();

                if (leftAdminCount < 1) {
                    throw LoggingHelper.logException(logger, signature,
                        new OPMException("Changing the role of given user causes no admin left."));
                }
            }

            getEntityManager().merge(user);

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Sets the default tab for user.
     *
     * @param userId
     *            the id of user to set default tab.
     * @param tab
     *            the tab to set for default.
     *
     * @throws IllegalArgumentException
     *             if tab is null or userId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such user to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void setDefaultTab(long userId, ActionTab tab) throws OPMException {
        String signature = CLASS_NAME + "#setDefaultTab(long userId, ActionTab tab)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"userId", "tab"},
            new Object[] {userId, tab});

        Helper.checkPositive(logger, signature, userId, "userId");
        Helper.checkNull(logger, signature, tab, "tab");

        try {
            // Get user
            User user = getById(logger, signature, userId, true);

            user.setDefaultTab(tab);
            getEntityManager().merge(user);

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (entityManager
     *             , adminRoleName or supervisorRoleName is null).
     */
    @PostConstruct
    @Override
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(supervisorRoleName == null, "'supervisorRoleName' can't be null.");
        Helper.checkState(adminRoleName == null, "'adminRoleName' can't be null.");
    }


    /**
     * Retrieves user by id.
     *
     * @param logger
     *            the logger object.
     * @param signature
     *            the signature of the method to log.
     * @param userId
     *            the id of user to retrieve.
     * @param checkIfExists
     *            true for checking if the user exists.
     *
     * @return User for the id or null if there is no such user when checkIfExists is false.
     *
     * @throws IllegalStateException
     *             if the entity manager has been closed.
     * @throws PersistenceException
     *             if there is any problem when executing the method.
     * @throws EntityNotFoundException
     *             if there is no such user when checkIfExists is true.
     */
    private User getById(Logger logger, String signature, long userId, boolean checkIfExists)
        throws EntityNotFoundException {
        List<User> list = getEntityManager().createQuery(JPQL_QUERY_USER_BY_ID, User.class)
            .setParameter("id", userId)
            .getResultList();

        if (list.isEmpty()) {
            if (checkIfExists) {
                throw LoggingHelper.logException(logger, signature, new EntityNotFoundException("User with id '"
                    + userId + "' is not found."));
            }

            return null;
        }

        return list.get(0);
    }
}
