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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.Error;
import gov.opm.scrd.entities.application.Info;
import gov.opm.scrd.entities.application.Notification;
import gov.opm.scrd.entities.application.NotificationSearchFilter;
import gov.opm.scrd.entities.application.ServiceAnnouncement;
import gov.opm.scrd.entities.common.BasicPagedSearchFilter;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.entities.common.SortOrder;
import gov.opm.scrd.services.EntityNotFoundException;

import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link ServiceAnnouncementServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class ServiceAnnouncementServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>ServiceAnnouncementServiceImpl</code> instance used in tests.
     * </p>
     */
    private ServiceAnnouncementServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the filter used in tests.
     * </p>
     */
    private NotificationSearchFilter filter;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ServiceAnnouncementServiceImplUnitTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        entityManager = getEntityManager();

        logger = Logger.getLogger(getClass());

        instance = new ServiceAnnouncementServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);

        Account account = getAccount();
        create(account);

        filter = new NotificationSearchFilter();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>ServiceAnnouncementServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new ServiceAnnouncementServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>addNotification(Notification notification)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_addNotification() throws Exception {
        Notification notification = getNotification();

        entityManager.getTransaction().begin();
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertTrue("'addNotification' should be correct.", notification.getId() > 0);

        Notification retrievedNotification = entityManager.find(Notification.class, notification.getId());

        assertFalse("'addNotification' should be correct.", retrievedNotification.isDeleted());
        assertNotNull("'addNotification' should be correct.", retrievedNotification.getDate());
        assertEquals("'addNotification' should be correct.",
            notification.getDetails(), retrievedNotification.getDetails());
        assertEquals("'addNotification' should be correct.",
            notification.getSender(), retrievedNotification.getSender());
        assertEquals("'addNotification' should be correct.",
            notification.isRead(), retrievedNotification.isRead());
        assertEquals("'addNotification' should be correct.",
            notification.getRecipient(), retrievedNotification.getRecipient());
        assertEquals("'addNotification' should be correct.",
            notification.getRole().getId(), retrievedNotification.getRole().getId());
        assertEquals("'addNotification' should be correct.",
            1, retrievedNotification.getReadBy().size());
    }

    /**
     * <p>
     * Failure test for the method <code>addNotification(Notification notification)</code> with notification is
     * null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addNotification_notificationNull() throws Exception {
        instance.addNotification(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>addError(Error error)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_addError() throws Exception {
        Error error = getError();

        entityManager.getTransaction().begin();
        instance.addError(error);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertTrue("'addError' should be correct.", error.getId() > 0);

        Error retrievedError = entityManager.find(Error.class, error.getId());

        assertFalse("'addError' should be correct.", retrievedError.isDeleted());
        assertNotNull("'addError' should be correct.", retrievedError.getDate());
        assertEquals("'addError' should be correct.", error.getDetails(), retrievedError.getDetails());
    }

    /**
     * <p>
     * Failure test for the method <code>addError(Error error)</code> with notification is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addError_notificationNull() throws Exception {
        instance.addError(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>addInfo(Info info)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_addInfo() throws Exception {
        Info info = getInfo();

        entityManager.getTransaction().begin();
        instance.addInfo(info);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertTrue("'addInfo' should be correct.", info.getId() > 0);

        Info retrievedInfo = entityManager.find(Info.class, info.getId());

        assertFalse("'addInfo' should be correct.", retrievedInfo.isDeleted());
        assertNotNull("'addInfo' should be correct.", retrievedInfo.getDate());
        assertEquals("'addInfo' should be correct.", info.getDetails(), retrievedInfo.getDetails());
    }

    /**
     * <p>
     * Failure test for the method <code>addInfo(Info info)</code> with info is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addInfo_infoNull() throws Exception {
        instance.addInfo(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNotificationAsRead(long notificationId, String username)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_setNotificationAsRead_1() throws Exception {
        Notification notification = getNotification();
        notification.setRead(false);

        entityManager.getTransaction().begin();
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.setNotificationAsRead(notification.getId(), "username1");
        entityManager.getTransaction().commit();

        Notification retrievedNotification = entityManager.find(Notification.class, notification.getId());

        assertEquals("'setNotificationAsRead' should be correct.", 2, retrievedNotification.getReadBy().size());
        assertTrue("'setNotificationAsRead' should be correct.",
            retrievedNotification.getReadBy().contains("username1"));
        assertTrue("'setNotificationAsRead' should be correct.",
            retrievedNotification.isRead());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setNotificationAsRead(long notificationId, String username)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_setNotificationAsRead_2() throws Exception {
        Notification notification = getNotification();
        notification.getReadBy().clear();
        notification.getReadBy().add("username1");

        entityManager.getTransaction().begin();
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.setNotificationAsRead(notification.getId(), "username1");
        entityManager.getTransaction().commit();

        Notification retrievedNotification = entityManager.find(Notification.class, notification.getId());

        assertEquals("'setNotificationAsRead' should be correct.", 1, retrievedNotification.getReadBy().size());
        assertTrue("'setNotificationAsRead' should be correct.",
            retrievedNotification.getReadBy().contains("username1"));
    }

    /**
     * <p>
     * Failure test for the method <code>setNotificationAsRead(long notificationId, String username)</code> with
     * notificationId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setNotificationAsRead_notificationIdNegative() throws Exception {
        instance.setNotificationAsRead(-1, "username");
    }

    /**
     * <p>
     * Failure test for the method <code>setNotificationAsRead(long notificationId, String username)</code> with
     * notificationId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setNotificationAsRead_notificationIdZero() throws Exception {
        instance.setNotificationAsRead(0, "username");
    }

    /**
     * <p>
     * Failure test for the method <code>setNotificationAsRead(long notificationId, String username)</code> with
     * username is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setNotificationAsRead_usernameNull() throws Exception {
        instance.setNotificationAsRead(1, null);
    }

    /**
     * <p>
     * Failure test for the method <code>setNotificationAsRead(long notificationId, String username)</code> with
     * username is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_setNotificationAsRead_usernameEmpty() throws Exception {
        instance.setNotificationAsRead(1, TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Failure test for the method <code>setNotificationAsRead(long notificationId, String username)</code> with
     * notification doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_setNotificationAsRead_notificationNotExist() throws Exception {
        instance.setNotificationAsRead(Long.MAX_VALUE, "username1");
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNotificationCount(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getNotificationCount_1() throws Exception {
        Error error = getError();
        Info info = getInfo();
        Notification notification = getNotification();

        entityManager.getTransaction().begin();
        instance.addError(error);
        instance.addInfo(info);
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        int res = instance.getNotificationCount(filter);

        assertEquals("'getNotificationCount' should be correct.", 3, res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNotificationCount(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getNotificationCount_2() throws Exception {
        Error error = getError();
        Info info = getInfo();
        Notification notification = getNotification();

        entityManager.getTransaction().begin();
        instance.addError(error);
        instance.addInfo(info);
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        filter.setSender(notification.getSender());
        filter.setRead(notification.isRead());
        filter.setRecipient(notification.getRecipient());
        filter.setRecipientRole(notification.getRole());

        int res = instance.getNotificationCount(filter);

        assertEquals("'getNotificationCount' should be correct.", 1, res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getNotificationCount(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getNotificationCount_3() throws Exception {
        Error error = getError();
        Info info = getInfo();
        Notification notification = getNotification();

        notification.setDeleted(true);

        entityManager.getTransaction().begin();
        instance.addError(error);
        instance.addInfo(info);
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        filter.setSender(notification.getSender());
        filter.setRead(notification.isRead());
        filter.setRecipient(notification.getRecipient());
        filter.setRecipientRole(notification.getRole());

        int res = instance.getNotificationCount(filter);

        assertEquals("'getNotificationCount' should be correct.", 0, res);
    }

    /**
     * <p>
     * Failure test for the method <code>getNotificationCount(NotificationSearchFilter filter)</code> with filter is
     * null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getNotificationCount_filterNull() throws Exception {
        instance.getNotificationCount(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchAnnouncements(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchAnnouncements_1() throws Exception {
        clearDB();

        SearchResult<ServiceAnnouncement> res = instance.searchAnnouncements(filter);

        assertEquals("'searchAnnouncements' should be correct.", 0, res.getTotal());
        assertEquals("'searchAnnouncements' should be correct.", 0, res.getTotalPageCount());

        List<ServiceAnnouncement> items = res.getItems();
        assertEquals("'searchAnnouncements' should be correct.", 0, items.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchAnnouncements(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchAnnouncements_2() throws Exception {
        Notification notification = getNotification();
        notification.setDeleted(true);

        entityManager.getTransaction().begin();
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        SearchResult<ServiceAnnouncement> res = instance.searchAnnouncements(filter);

        assertEquals("'searchAnnouncements' should be correct.", 0, res.getTotal());
        assertEquals("'searchAnnouncements' should be correct.", 0, res.getTotalPageCount());

        List<ServiceAnnouncement> searchAnnouncements = res.getItems();
        assertEquals("'searchAnnouncements' should be correct.", 0, searchAnnouncements.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchAnnouncements(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchAnnouncements_3() throws Exception {
        Error error = getError();
        error.setDetails("details1");
        Info info = getInfo();
        info.setDetails("details2");
        Notification notification = getNotification();
        notification.setDetails("details3");

        entityManager.getTransaction().begin();
        instance.addError(error);
        instance.addInfo(info);
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        filter.setSortColumn("details");
        filter.setSortOrder(SortOrder.ASC);
        SearchResult<ServiceAnnouncement> res = instance.searchAnnouncements(filter);

        assertEquals("'searchAnnouncements' should be correct.", 3, res.getTotal());
        assertEquals("'searchAnnouncements' should be correct.", 1, res.getTotalPageCount());

        List<ServiceAnnouncement> items = res.getItems();
        assertEquals("'searchAnnouncements' should be correct.", 3, items.size());
        ServiceAnnouncement item = items.get(0);
        assertEquals("'searchAnnouncements' should be correct.", error.getId(), item.getId());
        item = items.get(1);
        assertEquals("'searchAnnouncements' should be correct.", info.getId(), item.getId());
        item = items.get(2);
        assertEquals("'searchAnnouncements' should be correct.", notification.getId(), item.getId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchAnnouncements(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchAnnouncements_4() throws Exception {
        Error error = getError();
        error.setDetails("details1");
        Info info = getInfo();
        info.setDetails("details2");
        Notification notification = getNotification();
        notification.setDetails("details3");

        entityManager.getTransaction().begin();
        instance.addError(error);
        instance.addInfo(info);
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        filter.setSortColumn("details");
        filter.setPageNumber(2);
        filter.setPageSize(2);
        SearchResult<ServiceAnnouncement> res = instance.searchAnnouncements(filter);

        assertEquals("'searchAnnouncements' should be correct.", 3, res.getTotal());
        assertEquals("'searchAnnouncements' should be correct.", 2, res.getTotalPageCount());

        List<ServiceAnnouncement> items = res.getItems();
        assertEquals("'searchAnnouncements' should be correct.", 1, items.size());
        ServiceAnnouncement item = items.get(0);
        assertEquals("'searchAnnouncements' should be correct.", error.getId(), item.getId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchAnnouncements(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchAnnouncements_5() throws Exception {
        Error error = getError();
        Info info = getInfo();
        Notification notification = getNotification();

        entityManager.getTransaction().begin();
        instance.addError(error);
        instance.addInfo(info);
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        filter.setSender(notification.getSender());
        filter.setRead(notification.isRead());
        filter.setRecipient(notification.getRecipient());
        filter.setRecipientRole(notification.getRole());

        SearchResult<ServiceAnnouncement> res = instance.searchAnnouncements(filter);

        assertEquals("'searchAnnouncements' should be correct.", 1, res.getTotal());
        assertEquals("'searchAnnouncements' should be correct.", 1, res.getTotalPageCount());

        List<ServiceAnnouncement> items = res.getItems();
        assertEquals("'searchAnnouncements' should be correct.", 1, items.size());
        ServiceAnnouncement item = items.get(0);
        assertEquals("'searchAnnouncements' should be correct.", notification.getId(), item.getId());
    }

    /**
     * <p>
     * Failure test for the method <code>searchAnnouncements(NotificationSearchFilter filter)</code> with filter is
     * null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_searchAnnouncements_filterNull() throws Exception {
        instance.searchAnnouncements(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchNotifications(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchNotifications_1() throws Exception {
        clearDB();

        SearchResult<Notification> res = instance.searchNotifications(filter);

        assertEquals("'searchNotifications' should be correct.", 0, res.getTotal());
        assertEquals("'searchNotifications' should be correct.", 0, res.getTotalPageCount());

        List<Notification> items = res.getItems();
        assertEquals("'searchNotifications' should be correct.", 0, items.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchNotifications(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchNotifications_2() throws Exception {
        Notification notification = getNotification();
        notification.setDeleted(true);

        entityManager.getTransaction().begin();
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        SearchResult<Notification> res = instance.searchNotifications(filter);

        assertEquals("'searchNotifications' should be correct.", 0, res.getTotal());
        assertEquals("'searchNotifications' should be correct.", 0, res.getTotalPageCount());

        List<Notification> searchNotifications = res.getItems();
        assertEquals("'searchNotifications' should be correct.", 0, searchNotifications.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchNotifications(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchNotifications_3() throws Exception {
        Notification notification = getNotification();

        entityManager.getTransaction().begin();
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        SearchResult<Notification> res = instance.searchNotifications(filter);

        assertEquals("'searchNotifications' should be correct.", 1, res.getTotal());
        assertEquals("'searchNotifications' should be correct.", 1, res.getTotalPageCount());

        List<Notification> items = res.getItems();
        assertEquals("'searchNotifications' should be correct.", 1, items.size());
        Notification item = items.get(0);
        assertEquals("'searchNotifications' should be correct.", notification.getId(), item.getId());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchNotifications(NotificationSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchNotifications_4() throws Exception {
        Notification notification = getNotification();

        entityManager.getTransaction().begin();
        instance.addNotification(notification);
        entityManager.getTransaction().commit();
        entityManager.clear();

        filter.setSender(notification.getSender());
        filter.setRead(notification.isRead());
        filter.setRecipient(notification.getRecipient());
        filter.setRecipientRole(notification.getRole());

        SearchResult<Notification> res = instance.searchNotifications(filter);

        assertEquals("'searchNotifications' should be correct.", 1, res.getTotal());
        assertEquals("'searchNotifications' should be correct.", 1, res.getTotalPageCount());

        List<Notification> items = res.getItems();
        assertEquals("'searchNotifications' should be correct.", 1, items.size());
        Notification item = items.get(0);
        assertEquals("'searchNotifications' should be correct.", notification.getId(), item.getId());
    }

    /**
     * <p>
     * Failure test for the method <code>searchNotifications(NotificationSearchFilter filter)</code> with filter is
     * null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_searchNotifications_filterNull() throws Exception {
        instance.searchNotifications(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchErrors(BasicPagedSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchErrors_1() throws Exception {
        clearDB();

        BasicPagedSearchFilter basicPagedSearchFilter = new BasicPagedSearchFilter();
        SearchResult<Error> res = instance.searchErrors(basicPagedSearchFilter);

        assertEquals("'searchErrors' should be correct.", 0, res.getTotal());
        assertEquals("'searchErrors' should be correct.", 0, res.getTotalPageCount());

        List<Error> items = res.getItems();
        assertEquals("'searchErrors' should be correct.", 0, items.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchErrors(BasicPagedSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchErrors_2() throws Exception {
        Error error = getError();
        error.setDeleted(true);

        entityManager.getTransaction().begin();
        instance.addError(error);
        entityManager.getTransaction().commit();
        entityManager.clear();

        BasicPagedSearchFilter basicPagedSearchFilter = new BasicPagedSearchFilter();
        SearchResult<Error> res = instance.searchErrors(basicPagedSearchFilter);

        assertEquals("'searchErrors' should be correct.", 0, res.getTotal());
        assertEquals("'searchErrors' should be correct.", 0, res.getTotalPageCount());

        List<Error> searchErrors = res.getItems();
        assertEquals("'searchErrors' should be correct.", 0, searchErrors.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchErrors(BasicPagedSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchErrors_3() throws Exception {
        Error error = getError();

        entityManager.getTransaction().begin();
        instance.addError(error);
        entityManager.getTransaction().commit();
        entityManager.clear();

        SearchResult<Error> res = instance.searchErrors(filter);

        assertEquals("'searchErrors' should be correct.", 1, res.getTotal());
        assertEquals("'searchErrors' should be correct.", 1, res.getTotalPageCount());

        List<Error> items = res.getItems();
        assertEquals("'searchErrors' should be correct.", 1, items.size());
        Error item = items.get(0);
        assertEquals("'searchErrors' should be correct.", error.getId(), item.getId());
    }

    /**
     * <p>
     * Failure test for the method <code>searchErrors(BasicPagedSearchFilter filter)</code> with filter is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_searchErrors_filterNull() throws Exception {
        instance.searchErrors(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchInfos(BasicPagedSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchInfos_1() throws Exception {
        clearDB();

        BasicPagedSearchFilter basicPagedSearchFilter = new BasicPagedSearchFilter();
        SearchResult<Info> res = instance.searchInfos(basicPagedSearchFilter);

        assertEquals("'searchInfos' should be correct.", 0, res.getTotal());
        assertEquals("'searchInfos' should be correct.", 0, res.getTotalPageCount());

        List<Info> items = res.getItems();
        assertEquals("'searchInfos' should be correct.", 0, items.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchInfos(BasicPagedSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchInfos_2() throws Exception {
        Info info = getInfo();
        info.setDeleted(true);

        entityManager.getTransaction().begin();
        instance.addInfo(info);
        entityManager.getTransaction().commit();
        entityManager.clear();

        BasicPagedSearchFilter basicPagedSearchFilter = new BasicPagedSearchFilter();
        SearchResult<Info> res = instance.searchInfos(basicPagedSearchFilter);

        assertEquals("'searchInfos' should be correct.", 0, res.getTotal());
        assertEquals("'searchInfos' should be correct.", 0, res.getTotalPageCount());

        List<Info> searchInfos = res.getItems();
        assertEquals("'searchInfos' should be correct.", 0, searchInfos.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>searchInfos(BasicPagedSearchFilter filter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_searchInfos_3() throws Exception {
        Info info = getInfo();

        entityManager.getTransaction().begin();
        instance.addInfo(info);
        entityManager.getTransaction().commit();
        entityManager.clear();

        SearchResult<Info> res = instance.searchInfos(filter);

        assertEquals("'searchInfos' should be correct.", 1, res.getTotal());
        assertEquals("'searchInfos' should be correct.", 1, res.getTotalPageCount());

        List<Info> items = res.getItems();
        assertEquals("'searchInfos' should be correct.", 1, items.size());
        Info item = items.get(0);
        assertEquals("'searchInfos' should be correct.", info.getId(), item.getId());
    }

    /**
     * <p>
     * Failure test for the method <code>searchInfos(BasicPagedSearchFilter filter)</code> with filter is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_searchInfos_filterNull() throws Exception {
        instance.searchInfos(null);
    }
}
