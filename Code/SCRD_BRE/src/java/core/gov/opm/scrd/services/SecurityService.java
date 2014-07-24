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

import java.util.List;

/**
 * <p>
 * This interface defines a contract for managing security related functionality, specifically authorization. It
 * provides a single method to check whether user given his list of roles is authorized to perform the given action.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 * @version 1.1 Added the API to get the permitted actions.
 * @author faeton, sparemax, TCSASSEMBLER
 * @version 1.1
 */
public interface SecurityService {
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
    public void authorize(String username, List<String> roles, String action) throws OPMException;

    /**
     * Clears the cache security data.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void clearSecurityCacheData() throws OPMException;
    
    /**
     * Gets the actions for a user or roles.
     *
     * @param username
     *            the name of the user performing the operation.
     * @param roles
     *            the list of roles associated with user.
     * @return the action lists
     * @throws IllegalArgumentException
     *             if username is null/empty, roles is null or contain null/empty elements.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<String> getPermittedActions(String username, List<String> roles) throws OPMException;
}
