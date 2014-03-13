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
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.NamedEntity;
import gov.opm.scrd.services.LookupService;
import gov.opm.scrd.services.OPMException;

import java.util.ArrayList;
import java.util.List;

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
 * This class is the implementation of the LookupService. It utilizes JPA EntityManager for necessary operations.
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
@Local(LookupService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LookupServiceImpl extends BaseService implements LookupService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = LookupServiceImpl.class.getName();

    /**
     * <p>
     * Represents the JPQL to query entity.
     * </p>
     */
    private static final String JPQL_QUERY_ENTITY = "SELECT e FROM %1$s e WHERE e.deleted = false";

    /**
     * <p>
     * Represents the package of lookup entities.
     * </p>
     */
    private static final String LOOKUP_PACKAGE = "gov.opm.scrd.entities.lookup.";

    /**
     * Creates an instance of LookupServiceImpl.
     */
    public LookupServiceImpl() {
        // Empty
    }

    /**
     * Returns the list of entities for the given lookup type.
     *
     * @param lookupType
     *            the lookup type to retrieve.
     *
     * @return The list of lookup entities for the given type, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if lookupType is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<NamedEntity> getLookups(String lookupType) throws OPMException {
        String signature = CLASS_NAME + "#getLookups(String lookupType)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"lookupType"},
            new Object[] {lookupType});

        Helper.checkNullOrEmpty(logger, signature, lookupType, "lookupType");

        try {
            Class<?> lookupClass = Class.forName(LOOKUP_PACKAGE + lookupType);

            List<?> list = getEntityManager().createQuery(String.format(JPQL_QUERY_ENTITY, lookupType), lookupClass)
                .getResultList();

            List<NamedEntity> result = new ArrayList<NamedEntity>();
            for (Object obj : list) {
                result.add((NamedEntity) obj);
            }

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (ClassNotFoundException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The class can't be found: "
                + lookupType, e));
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }
}
