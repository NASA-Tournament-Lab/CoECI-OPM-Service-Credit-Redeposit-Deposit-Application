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
import gov.opm.scrd.entities.application.HelpItem;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.HelpService;
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
 * This class is the implementation of the HelpService. It utilizes JPA EntityManager for necessary operations.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Add getAll() method to get all help items</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, sparemax, liuliquan
 * @version 1.1
 */
@Stateless
@Local(HelpService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class HelpServiceImpl extends BaseService implements HelpService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = HelpServiceImpl.class.getName();

    /**
     * <p>
     * Represents the string '%'.
     * </p>
     */
    private static final String PERCENT_SIGN = "%";

    /**
     * <p>
     * Represents the JPQL to query all HelpItems.
     * </p>
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    private static final String JPQL_QUERY_ALL_HELP_ITEMS = "SELECT e.id, e.title, e.summary FROM HelpItem e"
        + " WHERE e.deleted = false ORDER BY e.id";

    /**
     * <p>
     * Represents the JPQL to query HelpItem by id.
     * </p>
     */
    private static final String JPQL_QUERY_HELP_ITEM_BY_ID = "SELECT e FROM HelpItem e"
        + " WHERE e.deleted = false AND e.id = :id";

    /**
     * <p>
     * Represents the JPQL to query HelpItem by term.
     * </p>
     */
    private static final String JPQL_QUERY_HELP_ITEM_BY_TERM = "SELECT e.id, e.title, e.summary FROM HelpItem e"
        + " WHERE e.deleted = false AND (e.title LIKE :term OR e.summary LIKE :term OR e.content LIKE :term) ORDER BY e.id";

    /**
     * Creates an instance of HelpServiceImpl.
     */
    public HelpServiceImpl() {
        // Empty
    }

    /**
     * Searches for the help items based on the search term.
     *
     * @param term
     *            the search term to appear in the help item.
     *
     * @return The list of HelpItem instances containing help information, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if term is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<HelpItem> search(String term) throws OPMException {
        String signature = CLASS_NAME + "#search(String term)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"term"},
            new Object[] {term});

        Helper.checkNullOrEmpty(logger, signature, term, "term");

        // Escape wildcard characters
        term = term.replaceAll("%", "\\\\%");
        term = term.replaceAll("_", "\\\\_");

        try {
            // HelpItem.content should not be retrieved
            List<Object[]> data = getEntityManager().createQuery(JPQL_QUERY_HELP_ITEM_BY_TERM)
                .setParameter("term", PERCENT_SIGN + term + PERCENT_SIGN)
                .getResultList();

            List<HelpItem> result = popuateHelpItems(data);

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
     * Retrieves the help item by id.
     *
     * @param helpItemId
     *            the id of help item to retrieve.
     *
     * @return The help item for id or null if it can not be found.
     *
     * @throws IllegalArgumentException
     *             if helpItemId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public HelpItem get(long helpItemId) throws OPMException {
        String signature = CLASS_NAME + "#get(long helpItemId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"helpItemId"},
            new Object[] {helpItemId});

        Helper.checkPositive(logger, signature, helpItemId, "helpItemId");

        try {
            List<HelpItem> list = getEntityManager().createQuery(JPQL_QUERY_HELP_ITEM_BY_ID, HelpItem.class)
                .setParameter("id", helpItemId)
                .getResultList();

            HelpItem result = list.isEmpty() ? null : list.get(0);

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
     * Retrieves all help items.
     *
     * @return All help items.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<HelpItem> getAll() throws OPMException {
        String signature = CLASS_NAME + "#getAll()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        try {
            List<Object[]> data = getEntityManager().createQuery(JPQL_QUERY_ALL_HELP_ITEMS)
                .getResultList();

            List<HelpItem> result = popuateHelpItems(data);

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
     * Populates help items.
     *
     * @param data list of objects.
     *
     * @return list of help items.
     */
    private static List<HelpItem> popuateHelpItems(List<Object[]> data) {

        List<HelpItem> result = new ArrayList<HelpItem>();
        for (Object[] row : data) {
            HelpItem helpItem = new HelpItem();

            helpItem.setId((Long) row[0]);
            helpItem.setTitle((String) row[1]);
            helpItem.setSummary((String) row[2]);

            result.add(helpItem);
        }
        return result;
    }
}
