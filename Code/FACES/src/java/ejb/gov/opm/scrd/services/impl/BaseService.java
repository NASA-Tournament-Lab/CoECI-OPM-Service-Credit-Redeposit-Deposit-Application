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

import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.OPMConfigurationException;

import javax.annotation.PostConstruct;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 * <p>
 * This class is the base class for all services the use persistence. It simply aggregates JPA EntityManager and the
 * logger of the application.
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
@Interceptors(SpringBeanAutowiringInterceptor.class)
public abstract class BaseService {
    /**
     * Represents logger used for logging. It is injected by Spring. It can be null if logging is turned off.
     */
    @Autowired
    private Logger logger;

    /**
     * Represents the EntityManager instance for managing data in the persistence. It is injected by Spring. It can not
     * be null after injected.
     */
    @PersistenceContext(unitName = "opmUnitName")
    private EntityManager entityManager;

    /**
     * Creates an instance of BaseService.
     */
    protected BaseService() {
        // Empty
    }

    /**
     * Gets the logger used for logging.
     *
     * @return the logger used for logging.
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Gets the EntityManager instance for managing data in the persistence.
     *
     * @return the EntityManager instance for managing data in the persistence.
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (entityManager is null).
     */
    @PostConstruct
    protected void checkInit() {
        Helper.checkState(entityManager == null, "'entityManager' can't be null.");
    }
}
