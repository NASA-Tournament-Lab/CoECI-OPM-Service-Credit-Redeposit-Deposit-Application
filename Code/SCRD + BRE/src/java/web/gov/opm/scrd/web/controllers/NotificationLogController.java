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

package gov.opm.scrd.web.controllers;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Error;
import gov.opm.scrd.entities.application.Info;
import gov.opm.scrd.entities.application.Notification;
import gov.opm.scrd.entities.application.NotificationSearchFilter;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.BasicPagedSearchFilter;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.ServiceAnnouncementService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * This class represents the controller used to show notification data.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, liuliquan
 * @version 1.0
 * @since OPM - Frontend - Miscellaneous Module Assembly
 */
@Controller
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NotificationLogController extends BaseController {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = NotificationLogController.class.getName();

    /**
     * Represents the ServiceAnnouncementService instance for notifications retrieval. It is modified by setter. It is
     * injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.SERVICE_ANNOUNCEMENT_SERVICE_JNDI)
    private ServiceAnnouncementService serviceAnnouncementService;

    /**
     * Creates an instance of NotificationLogController.
     */
    public NotificationLogController() {
        // Empty
    }

    /**
     * This method is responsible for viewing the page of the controller.
     *
     * @param session
     *            current http session.
     *
     * @return The populated ModelAndView instance.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "notificationLog/view", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView view(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        ModelAndView modelAndView = populateModelAndView(session, new ModelAndView("notificationLog"));

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }

    /**
     * Searches the notifications based on the filter.
     *
     * @param session
     *            current http session.
     * @param filter
     *            the search filter.
     *
     * @return Serialized JSON representation of search result.
     *
     * @throws ValidationException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "notificationLog/searchNotifications", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Notification> searchNotifications(
        HttpSession session, @RequestBody NotificationSearchFilter filter)
        throws ValidationException, OPMException {
        String signature = CLASS_NAME + "#searchNotifications(HttpSession session, NotificationSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session", "filter"},
            new Object[] {session, filter});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {filter == null},
            new String[] {"'filter' can't be null."},
            "Failed to search notifications.");

        // Search notification for user himself
        User currentUser = getCurrentUser(session);
        filter.setRecipient(currentUser.getUsername());
        filter.setRecipientRole(currentUser.getRole());

        SearchResult<Notification> result = serviceAnnouncementService.searchNotifications(filter);
        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Searches the errors based on the filter.
     *
     * @param filter
     *            the search filter.
     * @return Serialized JSON representation of search result.
     *
     * @throws ValidationException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "notificationLog/searchErrors", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Error> searchErrors(@RequestBody BasicPagedSearchFilter filter)
        throws ValidationException, OPMException {
        String signature = CLASS_NAME + "#searchErrors(BasicPagedSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"filter"}, new Object[] {filter});

        // Check request
        WebHelper.checkRequest(logger, signature, new boolean[] {filter == null},
                new String[] {"'filter' can't be null."}, "Failed to search errors.");

        SearchResult<Error> result = serviceAnnouncementService.searchErrors(filter);

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Searches the info data based on the filter.
     *
     * @param filter
     *            the search filter.
     * @return Serialized JSON representation of search result.
     *
     * @throws ValidationException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "notificationLog/searchInfos", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Info> searchInfos(@RequestBody BasicPagedSearchFilter filter)
        throws ValidationException, OPMException {
        String signature = CLASS_NAME + "#searchInfos(BasicPagedSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"filter"}, new Object[] {filter});

        // Check request
        WebHelper.checkRequest(logger, signature, new boolean[] {filter == null},
                new String[] {"'filter' can't be null."}, "Failed to search info data.");

        SearchResult<Info> result = serviceAnnouncementService.searchInfos(filter);

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }
    
    /**
     * <p>
     * Marks the notifications are read.
     * </p>
     * @param session the session.
     * @param notificationIds the notification ids.
     * @throws OPMException if any error occurs.
     */
    @RequestMapping(value = "notificationLog/markread", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void markNotificationsRead(HttpSession session, @RequestBody List<Long> notificationIds) throws OPMException {
        String signature = CLASS_NAME + "#markNotificationsRead(HttpSession session, List<Long> notificationIds)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, 
                new String[] {"notificationIds"}, new Object[] {notificationIds});
        User currentUser = getCurrentUser(session);
        String username = currentUser.getUsername();
        for (Long id : notificationIds) {
            serviceAnnouncementService.setNotificationAsRead(id, username);
        }
        LoggingHelper.logExit(logger, signature, null);
    }


    /**
     * Setter method for property <tt>serviceAnnouncementService</tt>.
     * @param serviceAnnouncementService value to be assigned to property serviceAnnouncementService
     */
    public void setServiceAnnouncementService(ServiceAnnouncementService serviceAnnouncementService) {
        this.serviceAnnouncementService = serviceAnnouncementService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService, userService, auditService or serviceAnnouncementService is null;
     *             userSessionKey is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(serviceAnnouncementService == null, "'serviceAnnouncementService' can't be null.");
    }
}
