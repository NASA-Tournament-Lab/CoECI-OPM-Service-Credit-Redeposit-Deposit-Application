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

import gov.opm.scrd.entities.application.Error;
import gov.opm.scrd.entities.application.Info;
import gov.opm.scrd.entities.application.Notification;
import gov.opm.scrd.entities.application.NotificationSearchFilter;
import gov.opm.scrd.entities.application.ServiceAnnouncement;
import gov.opm.scrd.entities.common.BasicPagedSearchFilter;
import gov.opm.scrd.entities.common.SearchResult;

/**
 * <p>
 * This interface defines a contract for managing service announcements, including notifications, info and error data.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public interface ServiceAnnouncementService {
    /**
     * Adds the notification service announcement.
     *
     * @param notification
     *            the notification to add.
     *
     * @throws IllegalArgumentException
     *             if notification is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void addNotification(Notification notification) throws OPMException;

    /**
     * Adds the error service announcement.
     *
     * @param error
     *            the error to add.
     *
     * @throws IllegalArgumentException
     *             if error is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void addError(Error error) throws OPMException;

    /**
     * Adds the info service announcement.
     *
     * @param info
     *            the info to add.
     *
     * @throws IllegalArgumentException
     *             if info is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void addInfo(Info info) throws OPMException;

    /**
     * Set the notification as read for the user.
     *
     * @param notificationId
     *            the notification to set as read.
     * @param username
     *            the name of the user who read the notification.
     *
     * @throws IllegalArgumentException
     *             if notificationId is not positive or username is null/empty.
     * @throws EntityNotFoundException
     *             if there is no such notification for notificationId or user for the username.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void setNotificationAsRead(long notificationId, String username) throws OPMException;

    /**
     * Retrieves the total notification count.
     *
     * @param filter
     *            the filter to retrieve total notification count.
     *
     * @return Total notification count, can not be negative.
     *
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public int getNotificationCount(NotificationSearchFilter filter) throws OPMException;

    /**
     * Searches service announcements based on the filter.
     *
     * @param filter
     *            the filter to search service announcements.
     *
     * @return SearchResult&lt;ServiceAnnouncement&gt; instance holding information about search result.
     *
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public SearchResult<ServiceAnnouncement> searchAnnouncements(NotificationSearchFilter filter) throws OPMException;

    /**
     * Searches notification service announcements based on the filter.
     *
     * @param filter
     *            the filter to search notification service announcements.
     *
     * @return SearchResult&lt;Notification&gt; instance holding information about search result.
     *
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public SearchResult<Notification> searchNotifications(NotificationSearchFilter filter) throws OPMException;

    /**
     * Searches error service announcements based on the filter.
     *
     * @param filter
     *            the filter to search error service announcements.
     *
     * @return SearchResult&lt;Error&gt; instance holding information about search result.
     *
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public SearchResult<Error> searchErrors(BasicPagedSearchFilter filter) throws OPMException;

    /**
     * Searches info service announcements based on the filter.
     *
     * @param filter
     *            the filter to search info service announcements.
     *
     * @return SearchResult&lt;Info&gt; instance holding information about search result.
     *
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public SearchResult<Info> searchInfos(BasicPagedSearchFilter filter) throws OPMException;
}
