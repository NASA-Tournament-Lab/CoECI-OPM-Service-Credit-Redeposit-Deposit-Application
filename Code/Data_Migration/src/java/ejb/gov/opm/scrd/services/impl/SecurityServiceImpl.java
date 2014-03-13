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
import gov.opm.scrd.entities.application.RolePermission;
import gov.opm.scrd.entities.application.UserPermission;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.AuthorizationException;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.SecurityService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * This class is the implementation of the SecurityService. It utilizes JPA EntityManager for necessary operations.
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
 * <li>Use postgres reg match to authorize the action.</li>
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
@Local(SecurityService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SecurityServiceImpl extends BaseService implements SecurityService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = SecurityServiceImpl.class.getName();

    /**
     * <p>
     * Represents the JPQL to query count of UserPermission.
     * </p>
     */
    private static final String JPQL_QUERY_USER_PERMISSION = "SELECT COUNT(*) FROM opm.user_permission e"
        + " WHERE e.deleted = false AND e.username = :username AND :action ~ e.action";

    /**
     * <p>
     * Represents the JPQL to query count of RolePermission.
     * </p>
     */
    private static final String JPQL_QUERY_ROLE_PERMISSION = "SELECT COUNT(*) FROM opm.role_permission e"
        + " WHERE e.deleted = false AND :action ~ e.action AND e.rolename IN :roles";

    /**
     * Represents L2 JPA cache which is used to evict cached instances when necessary. It is injected by Spring. It can
     * not be null after injected.
     */
    @Autowired
    private Cache cache;

    /**
     * Creates an instance of SecurityServiceImpl.
     */
    public SecurityServiceImpl() {
        // Empty
    }

    /**
     * Checks whether user is authorized to perform a particular action or access a given widget.
     *
     * @param username
     *            the name of the user performing the operation.
     * @param roles
     *            the list of roles associated with user.
     * @param action
     *            the name of the action or the widget user is accessing.
     *
     * @throws IllegalArgumentException
     *             if username or action is null/empty, roles is null or contain null/empty elements.
     * @throws AuthorizationException
     *             if authorization fails.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void authorize(String username, List<String> roles, String action) throws OPMException {
        String signature = CLASS_NAME + "#authorize(String username, List<String> roles, String action)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"username", "roles", "action"},
            new Object[] {username, roles, action});

        Helper.checkNullOrEmpty(logger, signature, username, "username");

        Helper.checkNull(logger, signature, roles, "roles");
        int index = 0;
        for (String role : roles) {
            Helper.checkNullOrEmpty(logger, signature, role, "roles[" + index + "]");
            ++index;
        }

        Helper.checkNullOrEmpty(logger, signature, action, "action");

        try {
            EntityManager entityManager = getEntityManager();

            boolean authorized = (((Number) entityManager.createNativeQuery(JPQL_QUERY_USER_PERMISSION)
                    .setParameter("username", username)
                    .setParameter("action", action)
                    .getSingleResult()).longValue() > 0);

            if (!authorized) {
                if (!roles.isEmpty()) {
                    authorized = (((Number) entityManager.createNativeQuery(JPQL_QUERY_ROLE_PERMISSION)
                            .setParameter("action", action)
                            .setParameter("roles", roles)
                            .getSingleResult()).longValue() > 0);
                }

                if (!authorized) {
                    throw LoggingHelper.logException(logger, signature, new AuthorizationException(
                        "The authorization failed."));
                }
            }

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
     * Clears the cache security data.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void clearSecurityCacheData() {
        String signature = CLASS_NAME + "#clearSecurityCacheData()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        cache.evict(UserPermission.class);
        cache.evict(RolePermission.class);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (entityManager or cache is null).
     */
    @PostConstruct
    @Override
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(cache == null, "'cache' can't be null.");
    }
}
