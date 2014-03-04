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

import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.lookup.ActionTab;

import java.util.List;

/**
 * <p>
 * This interface defines a contract for managing users. It provides simple retrieval and update operations for User
 * entity.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public interface UserService {
    /**
     * Retrieves all available users.
     *
     * @return List of available users, can not be null/contain null elements.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<User> getAll() throws OPMException;

    /**
     * Retrieves all available supervisors.
     *
     * @return List of available users with supervisor role, can not be null/contain null elements.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<User> getSupervisors() throws OPMException;

    /**
     * Retrieves user by id.
     *
     * @param userId
     *            the id of user to retrieve.
     *
     * @return User for the id or null if there is no such user.
     *
     * @throws IllegalArgumentException
     *             if userId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public User get(long userId) throws OPMException;

    /**
     * Retrieves user by username.
     *
     * @param username
     *            the name of user to retrieve.
     *
     * @return User for the name or null if there is no such user.
     *
     * @throws IllegalArgumentException
     *             if username is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public User getByUsername(String username) throws OPMException;

    /**
     * Updates user.
     *
     * @param user
     *            the user to update.
     *
     * @throws IllegalArgumentException
     *             if user is null.
     * @throws EntityNotFoundException
     *             if there is no such user to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void update(User user) throws OPMException;

    /**
     * Sets the default tab for user.
     *
     * @param userId
     *            the id of user to set default tab.
     * @param tab
     *            the tab to set for default.
     *
     * @throws IllegalArgumentException
     *             if tab is null or userId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such user to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void setDefaultTab(long userId, ActionTab tab) throws OPMException;
}
