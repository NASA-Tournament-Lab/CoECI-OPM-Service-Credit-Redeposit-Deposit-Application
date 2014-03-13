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
import gov.opm.scrd.entities.application.Error;
import gov.opm.scrd.entities.application.Info;
import gov.opm.scrd.entities.application.Notification;
import gov.opm.scrd.entities.application.NotificationSearchFilter;
import gov.opm.scrd.entities.application.ServiceAnnouncement;
import gov.opm.scrd.entities.common.BasicPagedSearchFilter;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.entities.common.SortOrder;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.ServiceAnnouncementService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

/**
 * <p>
 * This class is the implementation of the ServiceAnnouncementService. It utilizes JPA EntityManager for necessary
 * operations.
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
@Local(ServiceAnnouncementService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServiceAnnouncementServiceImpl extends BaseService implements ServiceAnnouncementService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ServiceAnnouncementServiceImpl.class.getName();

    /**
     * <p>
     * Represents the SQL to query entity.
     * </p>
     */
    private static final String SQL_QUERY_ENTITY = "SELECT e FROM  %1$s e WHERE e.deleted = false";

    /**
     * <p>
     * Represents the SQL to query entity count.
     * </p>
     */
    private static final String SQL_QUERY_ENTITY_COUNT = "SELECT COUNT(e) FROM  %1$s e WHERE e.deleted = false";

    /**
     * <p>
     * Represents the ServiceAnnouncement types.
     * </p>
     */
    private static final Class<?>[] ANNOUNCEMENT_TYPES = new Class<?>[] {Notification.class, Error.class, Info.class};

    /**
     * <p>
     * Represents the ServiceAnnouncement fields.
     * </p>
     */
    private static final List<String> SERVICE_ANNOUNCEMENT_FIELDS = Arrays.asList("id", "date", "details");

    /**
     * Creates an instance of ServiceAnnouncementServiceImpl.
     */
    public ServiceAnnouncementServiceImpl() {
        // Empty
    }

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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addNotification(Notification notification) throws OPMException {
        String signature = CLASS_NAME + "#addNotification(Notification notification)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"notification"},
            new Object[] {notification});

        Helper.checkNull(logger, signature, notification, "notification");

        try {
            getEntityManager().persist(notification);

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addError(Error error) throws OPMException {
        String signature = CLASS_NAME + "#addError(Error error)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"error"},
            new Object[] {error});

        Helper.checkNull(logger, signature, error, "error");

        try {
            getEntityManager().persist(error);

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addInfo(Info info) throws OPMException {
        String signature = CLASS_NAME + "#addInfo(Info info)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"info"},
            new Object[] {info});

        Helper.checkNull(logger, signature, info, "info");

        try {
            getEntityManager().persist(info);

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void setNotificationAsRead(long notificationId, String username) throws OPMException {
        String signature = CLASS_NAME + "#setNotificationAsRead(long notificationId, String username)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"notificationId", "username"},
            new Object[] {notificationId, username});

        Helper.checkPositive(logger, signature, notificationId, "notificationId");
        Helper.checkNullOrEmpty(logger, signature, username, "username");

        EntityManager entityManager = getEntityManager();

        try {
            // Get the Notification
            Notification notification = Helper.getEntityById(entityManager, logger, signature, Notification.class,
                notificationId, true);

            List<String> readBy = notification.getReadBy();
            if (!readBy.contains(username)) {
                readBy.add(username);
                notification.setRead(true);
                getEntityManager().merge(notification);
            }

            LoggingHelper.logExit(logger, signature, null);
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

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
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public int getNotificationCount(NotificationSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#getNotificationCount(NotificationSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"filter"},
            new Object[] {filter});

        Helper.checkNull(logger, signature, filter, "filter");

        try {
            boolean notificationFilterSet = (filter.getSender() != null) || (filter.getRead() != null)
                || (filter.getRecipient() != null);

            EntityManager entityManager = getEntityManager();

            int result = 0;
            if (notificationFilterSet) {
                // "Notification" only

                List<String> paramNames = new ArrayList<String>();
                List<Object> paramValues = new ArrayList<Object>();
                String whereClause = buildWhere(filter, paramNames, paramValues);

                // Create query
                TypedQuery<Number> countQuery = entityManager.createQuery(
                    String.format(SQL_QUERY_ENTITY_COUNT, ANNOUNCEMENT_TYPES[0].getSimpleName()) + whereClause,
                    Number.class);
                Helper.setParameters(countQuery, paramNames, paramValues);

                result = countQuery.getSingleResult().intValue();
            } else {
                // "Error", "Info", "Notification"

                for (Class<?> entityType : ANNOUNCEMENT_TYPES) {
                    result += entityManager
                        .createQuery(String.format(SQL_QUERY_ENTITY_COUNT, entityType.getSimpleName()), Number.class)
                        .getSingleResult().intValue();
                }
            }

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

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
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<ServiceAnnouncement> searchAnnouncements(NotificationSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#searchAnnouncements(NotificationSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"filter"},
            new Object[] {filter});

        Helper.checkNull(logger, signature, filter, "filter");

        try {
            boolean notificationFilterSet = (filter.getSender() != null) || (filter.getRead() != null)
                || (filter.getRecipient() != null);

            EntityManager entityManager = getEntityManager();

            SearchResult<ServiceAnnouncement> result;
            if (notificationFilterSet) {
                // "Notification" only

                result = searchNotifications(filter, ServiceAnnouncement.class);
            } else {
                // "Error", "Info", "Notification"

                result = new SearchResult<ServiceAnnouncement>();
                List<ServiceAnnouncement> records = new ArrayList<ServiceAnnouncement>();

                for (Class<?> entityType : ANNOUNCEMENT_TYPES) {
                    records.addAll(entityManager.createQuery(
                        String.format(SQL_QUERY_ENTITY, entityType.getSimpleName()), ServiceAnnouncement.class)
                        .getResultList());
                }

                // Sort the records
                sortRecords(logger, signature, filter, records);

                int total = records.size();
                result.setTotal(total);

                int pageNumber = filter.getPageNumber();
                if (pageNumber > 0) {
                    int pageSize = filter.getPageSize();
                    result.setTotalPageCount((total + pageSize - 1) / pageSize);

                    int firstResult = Math.min((pageNumber - 1) * pageSize, total);
                    int lastResult = Math.min(firstResult + pageSize, total);

                    result.setItems(new ArrayList<ServiceAnnouncement>(records.subList(firstResult, lastResult)));
                } else {
                    result.setItems(records);
                    result.setTotalPageCount(records.isEmpty() ? 0 : 1);
                }
            }

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

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
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Notification> searchNotifications(NotificationSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#searchNotifications(NotificationSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"filter"},
            new Object[] {filter});

        Helper.checkNull(logger, signature, filter, "filter");

        try {
            SearchResult<Notification> result = searchNotifications(filter, Notification.class);

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

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
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Error> searchErrors(BasicPagedSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#searchErrors(BasicPagedSearchFilter filter)";

        return searchEntities(signature, Error.class, filter);
    }

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
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Info> searchInfos(BasicPagedSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#searchInfos(BasicPagedSearchFilter filter)";

        return searchEntities(signature, Info.class, filter);
    }

    /**
     * Sorts the records.
     *
     * @param logger
     *            the logger
     * @param signature
     *            the signature
     * @param filter
     *            the filter
     * @param records
     *            the records
     *
     * @throws OPMException
     *             if any error occurs
     */
    private static void sortRecords(Logger logger, String signature, NotificationSearchFilter filter,
        List<ServiceAnnouncement> records) throws OPMException {
        String sortColumn = filter.getSortColumn();
        if (sortColumn == null) {
            sortColumn = "id";
        }

        final int sortColumnIndex = SERVICE_ANNOUNCEMENT_FIELDS.indexOf(sortColumn.toLowerCase());
        if (sortColumnIndex == -1) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The sort column '" + sortColumn
                + "' is invalid."));
        }

        final SortOrder sortOrder;
        if (filter.getSortOrder() == null) {
            sortOrder = SortOrder.DESC;
        } else {
            sortOrder = filter.getSortOrder();
        }

        Collections.sort(records,
            /**
             * The ServiceAnnouncement comparator.
             *
             * @author sparemax
             * @version 1.0
             */
            new Comparator<ServiceAnnouncement>() {
                /**
                 * Compares the ServiceAnnouncement objects.
                 *
                 * @param o1
                 *            the first object to be compared.
                 * @param o2
                 *            the second object to be compared.
                 *
                 * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to,
                 *          or greater than the second (ASC order).
                 */
                public int compare(ServiceAnnouncement o1, ServiceAnnouncement o2) {
                    int result;

                    if (sortColumnIndex == 0) {
                        // "id"
                        result = ((Long) o1.getId()).compareTo(o2.getId());
                    } else if (sortColumnIndex == 1) {
                        // "date"
                        result = ((Date) o1.getDate()).compareTo(o2.getDate());
                    } else {
                        // "details"
                        result = ((String) o1.getDetails()).compareTo(o2.getDetails());
                    }

                    if (sortOrder == SortOrder.DESC) {
                        // Descend order
                        result = -result;
                    }

                    return result;
                }
            });
    }

    /**
     * Searches notification service announcements based on the filter.
     *
     * @param <T>
     *            the entity type.
     * @param filter
     *            the filter to search notification service announcements.
     * @param entityType
     *            the entity type.
     *
     * @return SearchResult&lt;T&gt; instance holding information about search result.
     *
     * @throws IllegalStateException
     *             if the entity manager has been closed.
     * @throws PersistenceException
     *             if any other error occurs.
     */
    private <T> SearchResult<T> searchNotifications(NotificationSearchFilter filter, Class<T> entityType) {
        String orderByClause = Helper.buildOrderBy(filter);

        List<String> paramNames = new ArrayList<String>();
        List<Object> paramValues = new ArrayList<Object>();
        String whereClause = buildWhere(filter, paramNames, paramValues);

        EntityManager entityManager = getEntityManager();

        // Create query
        TypedQuery<T> query = entityManager.createQuery(
            String.format(SQL_QUERY_ENTITY, ANNOUNCEMENT_TYPES[0].getSimpleName()) + whereClause + orderByClause,
            entityType);
        Helper.setParameters(query, paramNames, paramValues);

        int pageNumber = filter.getPageNumber();
        int pageSize = filter.getPageSize();
        // Set paging
        if (pageNumber > 0) {
            query.setMaxResults(pageSize);
            query.setFirstResult((pageNumber - 1) * pageSize);
        }

        List<T> records = query.getResultList();

        SearchResult<T> result = new SearchResult<T>();
        result.setItems(records);

        if (pageNumber > 0) {
            // Create query
            TypedQuery<Number> countQuery = entityManager.createQuery(
                String.format(SQL_QUERY_ENTITY_COUNT, ANNOUNCEMENT_TYPES[0].getSimpleName()) + whereClause,
                Number.class);
            Helper.setParameters(countQuery, paramNames, paramValues);

            int totalCount = countQuery.getSingleResult().intValue();
            result.setTotal(totalCount);
            result.setTotalPageCount((totalCount + pageSize - 1) / pageSize);
        } else {
            result.setTotal(records.size());
            result.setTotalPageCount(records.isEmpty() ? 0 : 1);
        }

        return result;
    }

    /**
     * Searches entities based on the filter.
     *
     * @param <T>
     *            the entity type
     * @param signature
     *            the signature
     * @param entityType
     *            the entity type
     * @param filter
     *            the filter to search entities
     *
     * @return SearchResult&lt;T&gt; instance holding information about search result.
     *
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    private <T> SearchResult<T> searchEntities(String signature, Class<T> entityType, BasicPagedSearchFilter filter)
        throws OPMException {
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"filter"}, new Object[] {filter});

        Helper.checkNull(logger, signature, filter, "filter");

        try {
            String orderByClause = Helper.buildOrderBy(filter);

            EntityManager entityManager = getEntityManager();

            String entityTypeName = entityType.getSimpleName();

            // Create query
            TypedQuery<T> query = entityManager.createQuery(String.format(SQL_QUERY_ENTITY, entityTypeName)
                + orderByClause, entityType);

            int pageNumber = filter.getPageNumber();
            int pageSize = filter.getPageSize();
            // Set paging
            if (pageNumber > 0) {
                query.setMaxResults(pageSize);
                query.setFirstResult((pageNumber - 1) * pageSize);
            }

            List<T> records = query.getResultList();

            SearchResult<T> result = new SearchResult<T>();
            result.setItems(records);

            if (pageNumber > 0) {
                // Create query
                TypedQuery<Number> countQuery = entityManager.createQuery(
                    String.format(SQL_QUERY_ENTITY_COUNT, entityTypeName), Number.class);

                int totalCount = countQuery.getSingleResult().intValue();
                result.setTotal(totalCount);
                result.setTotalPageCount((totalCount + pageSize - 1) / pageSize);
            } else {
                result.setTotal(records.size());
                result.setTotalPageCount(records.isEmpty() ? 0 : 1);
            }

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * Builds the WHERE string.
     *
     * @param filter
     *            the filter
     * @param paramNames
     *            the parameter name
     * @param paramValues
     *            the parameter values
     *
     * @return the WHERE string.
     */
    private static String buildWhere(NotificationSearchFilter filter, List<String> paramNames,
        List<Object> paramValues) {
        StringBuilder sb = new StringBuilder();

        Helper.appendCondition(sb, "e.sender LIKE :sender", filter.getSender(), "sender", paramNames, paramValues);
        Helper.appendCondition(sb, "e.read = :read", filter.getRead(), "read", paramNames, paramValues);
        Helper.appendCondition(sb, "e.recipient LIKE :recipient", filter.getRecipient(), "recipient", paramNames,
            paramValues);
        if (filter.getRecipientRole() != null) {
            Helper.appendCondition(sb, "e.role.name = :recipientRole", filter.getRecipientRole().getName(),
                "recipientRole", paramNames, paramValues);
        }

        return sb.toString();
    }
}
