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

package gov.opm.scrd.services;

import gov.opm.scrd.entities.application.HelpItem;

import java.util.List;

/**
 * <p>
 * This interface defines a contract for retrieving help data.
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
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, sparemax, liuliquan
 * @version 1.1
 */
public interface HelpService {
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
    public List<HelpItem> search(String term) throws OPMException;

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
    public HelpItem get(long helpItemId) throws OPMException;

    /**
     * Retrieves all help items.
     *
     * @return All help items.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    public List<HelpItem> getAll() throws OPMException;
}
