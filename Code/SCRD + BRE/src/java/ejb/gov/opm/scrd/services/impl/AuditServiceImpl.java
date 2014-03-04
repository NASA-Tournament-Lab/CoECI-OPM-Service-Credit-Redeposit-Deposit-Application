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
import gov.opm.scrd.entities.application.AuditRecord;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.AuditService;
import gov.opm.scrd.services.OPMException;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;

/**
 * <p>
 * This class is the implementation of the AuditService. It utilizes JPA EntityManager for necessary operations.
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
@Local(AuditService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AuditServiceImpl extends BaseService implements AuditService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = AuditServiceImpl.class.getName();

    /**
     * Creates an instance of AuditServiceImpl.
     */
    public AuditServiceImpl() {
        // Empty
    }

    /**
     * Stores the audit record.
     *
     * @param entity
     *            the audit record to store.
     *
     * @throws IllegalArgumentException
     *             if entity is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void audit(AuditRecord entity) throws OPMException {
        String signature = CLASS_NAME + "#audit(AuditRecord entity)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"entity"},
            new Object[] {entity});

        Helper.checkNull(logger, signature, entity, "entity");

        try {
            getEntityManager().persist(entity);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }

        LoggingHelper.logExit(logger, signature, null);
    }
}
