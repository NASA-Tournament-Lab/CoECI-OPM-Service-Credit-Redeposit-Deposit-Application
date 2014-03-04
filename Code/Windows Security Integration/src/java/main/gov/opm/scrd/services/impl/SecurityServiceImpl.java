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

import gov.opm.scrd.Helper;
import gov.opm.scrd.services.SecurityService;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.jboss.logging.Logger;

/**
 * This is the implementation of SecurityService. <strong>Thread Safety:
 * </strong> This class is immutable and thread safe.
 * 
 * @author argolite,TCSASSEMBLER
 * @version 1.0
 */
@Stateless
public class SecurityServiceImpl implements SecurityService {
    /**
     * Represents the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SecurityServiceImpl.class);

    /**
     * Represents the query to check authorization.
     */
    private static final String COUNT_QUERY = "select count(p.*) as count from Permissions " 
            + "p where p.userName=:userName and p.action=:action and p.id in "
            + "(select pr.Permissions_id from Permissions_Role pr join Role r on pr.roles_id=r.id"
            + " where r.name in (:roleNames))";

    /**
     * Represents the entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Check authorization by user name,roles and action
     * 
     * @param username
     *            the user name
     * @param roles
     *            the roles
     * @param action
     *            the action name
     * @return if authorized return true
     */
    @Override
    public boolean authorize(String username, List<String> roles, String action) {
        final String signature = SecurityServiceImpl.class.getName()
                + "#authorize(String username, List<String> roles, String action)";
        Helper.logEntrance(LOGGER, signature, new String[] { "username",
                "roles", "action" }, new Object[] { username, roles, action });
        try {
            Helper.checkNullOrEmpty("username", username);
            Helper.checkNull("roles", roles);
            Helper.checkNullOrEmpty("action", action);
            Session session = (Session) entityManager.getDelegate();
            Long count = (Long) session.createSQLQuery(COUNT_QUERY)
                    .addScalar("count", LongType.INSTANCE)
                    .setString("userName", username)
                    .setString("action", action)
                    .setParameterList("roleNames", roles).setCacheable(true)
                    .setCacheRegion("query.countCache").uniqueResult();
            boolean result = count > 0;
            Helper.logExit(LOGGER, signature, new Object[] { result });
            return result;
        } catch (IllegalArgumentException e) {
            Helper.logException(LOGGER, signature, e);
            return false;
        } catch (HibernateException e) {
            Helper.logException(LOGGER, signature, e);
            return false;
        }
    }
}
