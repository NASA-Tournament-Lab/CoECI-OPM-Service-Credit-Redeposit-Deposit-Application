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
import gov.opm.scrd.entities.application.ServiceCreditPreference;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.ServiceCreditPreferenceService;

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
 * This class is the implementation of the ServiceCreditPreferenceService. It utilizes JPA EntityManager for necessary
 * operations.
 * </p>
 *
 * <p>
 * <em>Changes in OPM - SCRD - Frontend Initial Module Assembly 1.0:</em>
 * <ul>
 * <li>Changed to use LoggingHelper instead for logging</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
@Stateless
@Local(ServiceCreditPreferenceService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServiceCreditPreferenceServiceImpl extends BaseService implements ServiceCreditPreferenceService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ServiceCreditPreferenceServiceImpl.class.getName();

    /**
     * <p>
     * Represents the JPQL to query ServiceCreditPreference by id.
     * </p>
     */
    private static final String JPQL_QUERY_PREFERENCE_BY_ID = "SELECT e FROM ServiceCreditPreference e"
        + " WHERE e.deleted = false AND e.id = :id";

    /**
     * Represents the id of single ServiceCreditPreference instance used across the application. It is injected by
     * Spring. It can not be null after injected.
     */
    @Autowired
    private Long defaultPreferenceId;

    /**
     * Creates an instance of ServiceCreditPreferenceServiceImpl.
     */
    public ServiceCreditPreferenceServiceImpl() {
        // Empty
    }

    /**
     * Returns the service credit preference used in the application.
     *
     * @return the service credit preference used in the application or null if it is not set yet.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ServiceCreditPreference getServiceCreditPreference() throws OPMException {
        String signature = CLASS_NAME + "#getServiceCreditPreference()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        try {
            List<ServiceCreditPreference> list = getEntityManager()
                .createQuery(JPQL_QUERY_PREFERENCE_BY_ID, ServiceCreditPreference.class)
                .setParameter("id", defaultPreferenceId)
                .getResultList();

            ServiceCreditPreference result = list.isEmpty() ? null : list.get(0);

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
     * Saves the service credit preference used in the application.
     *
     * @param preference
     *            the service credit preference used in the application.
     *
     * @throws IllegalArgumentException
     *             if preference is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void setServiceCreditPreference(ServiceCreditPreference preference) throws OPMException {
        String signature = CLASS_NAME + "#setServiceCreditPreference(ServiceCreditPreference preference)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"preference"},
            new Object[] {preference});

        Helper.checkNull(logger, signature, preference, "preference");

        try {
            preference.setId(defaultPreferenceId);
            getEntityManager().merge(preference);

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
     *             if the instance was not initialized properly (entityManager or defaultPreferenceId is null).
     */
    @PostConstruct
    @Override
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(defaultPreferenceId == null, "'defaultPreferenceId' can't be null.");
    }
}
