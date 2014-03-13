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
import gov.opm.scrd.entities.application.Printout;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.BatchPrintoutArchiveService;
import gov.opm.scrd.services.OPMException;

import java.util.ArrayList;
import java.util.Date;
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
 * This class is the implementation of the BatchPrintoutArchiveService. It utilizes JPA EntityManager for necessary
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
@Local(BatchPrintoutArchiveService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BatchPrintoutArchiveServiceImpl extends BaseService implements BatchPrintoutArchiveService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = BatchPrintoutArchiveServiceImpl.class.getName();

    /**
     * <p>
     * Represents the JPQL to query Printout.
     * </p>
     */
    private static final String JPQL_QUERY_PRINTOUT = "SELECT e.id, e.name, e.printDate FROM Printout e"
        + " WHERE e.deleted = false";

    /**
     * <p>
     * Represents the JPQL to query printout data.
     * </p>
     */
    private static final String JPQL_QUERY_PRINTOUT_DATA = "SELECT e.content FROM Printout e"
        + " WHERE e.deleted = false AND e.name = :name";

    /**
     * Creates an instance of BatchPrintoutArchiveServiceImpl.
     */
    public BatchPrintoutArchiveServiceImpl() {
        // Empty
    }

    /**
     * Returns the list of available printouts in the application.
     *
     * @return List of available printouts, can not be null
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Printout> getAvailablePrintouts() throws OPMException {
        String signature = CLASS_NAME + "#getAvailablePrintouts()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        try {
            // Printout.content should not be retrieved
            List<Object[]> data = getEntityManager().createQuery(JPQL_QUERY_PRINTOUT).getResultList();

            List<Printout> result = new ArrayList<Printout>();

            for (Object[] row : data) {
                Printout printout = new Printout();

                printout.setId((Long) row[0]);
                printout.setName((String) row[1]);
                printout.setPrintDate((Date) row[2]);

                result.add(printout);
            }

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
     * Return the content of the printout by name.
     *
     * @param name
     *            the printout name.
     *
     * @return Printout contents as byte array, can be null.
     *
     * @throws IllegalArgumentException
     *             if name is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public byte[] getPrintout(String name) throws OPMException {
        String signature = CLASS_NAME + "#getPrintout(String name)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"name"},
            new Object[] {name});

        Helper.checkNullOrEmpty(logger, signature, name, "name");

        try {
            byte[] content = (byte[]) getEntityManager().createQuery(JPQL_QUERY_PRINTOUT_DATA)
                .setParameter("name", name)
                .getSingleResult();

            LoggingHelper.logExit(logger, signature, new Object[] {content});
            return content;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }
}
