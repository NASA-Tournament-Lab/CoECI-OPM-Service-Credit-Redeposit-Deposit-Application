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
import gov.opm.scrd.entities.application.ApprovalItemSummary;
import gov.opm.scrd.entities.application.Error;
import gov.opm.scrd.entities.application.Info;
import gov.opm.scrd.entities.application.Notification;
import gov.opm.scrd.entities.application.NotificationSearchFilter;
import gov.opm.scrd.entities.application.ServiceAnnouncement;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.BasicPagedSearchFilter;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.lookup.ActionTab;
import gov.opm.scrd.services.AccountService;
import gov.opm.scrd.services.ApprovalService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.ServiceAnnouncementService;
import gov.opm.scrd.services.SuspensionService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * This class represents the controller used to manage common tasks. It is responsible for retrieving currently logged
 * user, selecting tab as homepage and retrieving general data used to show on all pages.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Add welcome() method for viewing welcome page</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - SCRD - Frontend Account Module Assembly 1.0):</em>
 * <ul>
 * <li>Add implementatio of default account id and corresponding audit logic</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the injected services are
 * not modified in controllers by methods after configuration and configuration will be done in a thread safe manner by
 * Spring IoC framework.
 * </p>
 *
 * @author faeton, sparemax, liuliquan, TCSASSEMBLER
 * @version 1.2
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
@Controller
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CommonActivityController extends BaseAuditController {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = CommonActivityController.class.getName();

    /**
     * Represents the SuspensionService instance for retrieving suspension items count. It is modified by setter. It is
     * injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.SUSPENSION_SERVICE_JNDI)
    private SuspensionService suspensionService;

    /**
     * Represents the ApprovalService instance for approval items count. It is modified by setter. It is injected by
     * Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.APPROVAL_SERVICE_JNDI)
    private ApprovalService approvalService;

    /**
     * Represents the ServiceAnnouncementService instance for notifications retrieval. It is modified by setter. It is
     * injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.SERVICE_ANNOUNCEMENT_SERVICE_JNDI)
    private ServiceAnnouncementService serviceAnnouncementService;

    /**
     * Represents the AccountService instance for counting accounts assigned to user. It is modified by setter. It is
     * injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.ACCOUNT_SERVICE_JNDI)
    private AccountService accountService;

    /**
     * Creates an instance of CommonActivityController.
     */
    public CommonActivityController() {
        // Empty
    }

    /**
     * This method is responsible for viewing the welcome page of the controller.
     *
     * @param session
     *            current http session.
     *
     * @return The populated ModelAndView instance.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @RequestMapping(value = "common/welcome", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView welcome(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#welcome(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[] {"session"},
                new Object[] {session});

        ModelAndView modelAndView = populateModelAndView(session, new ModelAndView("welcome"));

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }
    
    /**
     * Sets the tab as a home page for the user.
     * 
     * @param request
     *            http request.
     * @param tab
     *            the tab to set a home page.
     * 
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/tab", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void makeTabHomePage(HttpServletRequest request, @RequestBody ActionTab tab) throws OPMException {
        String signature = CLASS_NAME + "#makeTabHomePage(HttpServletRequest request, ActionTab tab)";
        Logger logger = getLogger();

        LoggingHelper
                .logEntrance(logger, signature, new String[] { "request", "tab" }, new Object[] { request, tab });

        User user = getLoggedUser(logger, signature, request.getSession());

        ActionTab previousDefaultTab = user.getDefaultTab();

        user.setDefaultTab(tab);
        getUserService().update(user);

        WebHelper.audit(request, getAuditService(), user, "makeTabHomePage", new Object[] { user.getId(), "User",
                "defaultTab", previousDefaultTab, tab });

        LoggingHelper.logExit(logger, signature, null);
    }
    

    /**
     * Sets the tab as a home page for the user.
     *
     * @param request
     *            http request.
     * @param tab
     *            the tab to set a home page.
     * @param defaultTabAccountId
     *            the default account id used to show details when view account tab is selected
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/tab", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void makeTabHomePage(HttpServletRequest request, @RequestParam(required = false) ActionTab tab,
        @RequestParam(required = false) Long defaultTabAccountId) throws OPMException {
        String signature = CLASS_NAME + "#makeTabHomePage(HttpServletRequest request, ActionTab tab, "
                + "long defaultTabAccountId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"request", "tab", "defaultTabAccountId"},
            new Object[] {request, tab, defaultTabAccountId});

        User user = getLoggedUser(logger, signature, request.getSession());

        ActionTab previousDefaultTab = user.getDefaultTab();
        Long previousId = user.getDefaultTabAccountId();

        user.setDefaultTab(tab);
        user.setDefaultTabAccountId(defaultTabAccountId);
        getUserService().update(user);

        WebHelper.audit(request, getAuditService(), user, "makeTabHomePage",
            new Object[] {user.getId(), "User", "defaultTab", previousDefaultTab, tab});
        WebHelper.audit(request, getAuditService(), user, "makeTabHomePage",
            new Object[] {user.getId(), "User", "defaultTabAccountId", previousId, defaultTabAccountId});

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Returns the home tab of the current user.
     *
     * @param session
     *            http session holding currently logged username.
     *
     * @return The serialized home tab information.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/tab", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ActionTab getHomeTabPage(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#getHomeTabPage(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        User user = getLoggedUser(logger, signature, session);

        ActionTab result = user.getDefaultTab();

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Counts the accounts assigned to the user.
     *
     * @param session
     *            http session.
     *
     * @return the accounts assigned to the user.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/countAccounts", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Integer countAccountsAssignedToUser(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#countAccountsAssignedToUser(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        User user = getLoggedUser(logger, signature, session);

        int result = accountService.countAccountsAssignedToUser(user.getUsername());

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Counts the number of suspended payments for the user.
     *
     * @param session
     *            http session.
     *
     * @return the number of suspended payments for the user.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/countSuspensions", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Integer getSuspensionCount(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#getSuspensionCount(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        User user = getLoggedUser(logger, signature, session);

        int result = suspensionService.getSuspensionCount(user.getUsername());

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Returns the approval summary for the approver.
     *
     * @param session
     *            http session.
     *
     * @return The summary data of the approval
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/approvalSummary", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ApprovalItemSummary getApprovalSummary(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#getApprovalSummary(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        User user = getLoggedUser(logger, signature, session);

        ApprovalItemSummary result = approvalService.getApprovalSummary(user.getUsername());

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Returns the announcements for the given filter.
     *
     * @param filter
     *            the search filter.
     *
     * @return the announcements for the given filter.
     *
     * @throws ValidationException
     *             if filter is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/announcements", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ServiceAnnouncement> getAnnouncements(@RequestBody NotificationSearchFilter filter)
        throws ValidationException, OPMException {
        String signature = CLASS_NAME + "#getAnnouncements(NotificationSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"filter"},
            new Object[] {filter});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {filter == null},
            new String[] {"'filter' can't be null."},
            "Failed to get the announcements for the given filter.");

        List<ServiceAnnouncement> result = serviceAnnouncementService.searchAnnouncements(filter).getItems();

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Returns the notifications for the given filter.
     *
     * @param filter
     *            the search filter.
     *
     * @return the notifications for the given filter.
     *
     * @throws ValidationException
     *             if filter is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/notifications", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Notification> getNotifications(@RequestBody NotificationSearchFilter filter)
        throws ValidationException, OPMException {
        String signature = CLASS_NAME + "#getNotifications(NotificationSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"filter"},
            new Object[] {filter});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {filter == null},
            new String[] {"'filter' can't be null."},
            "Failed to get the notifications for the given filter.");

        List<Notification> result = serviceAnnouncementService.searchNotifications(filter).getItems();

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Returns the errors for the given filter.
     *
     * @param filter
     *            the search filter.
     *
     * @return the errors for the given filter.
     *
     * @throws ValidationException
     *             if filter is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/errors", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Error> getErrors(@RequestBody BasicPagedSearchFilter filter)
        throws ValidationException, OPMException {
        String signature = CLASS_NAME + "#getErrors(BasicPagedSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"filter"},
            new Object[] {filter});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {filter == null},
            new String[] {"'filter' can't be null."},
            "Failed to get the errors for the given filter.");

        List<Error> result = serviceAnnouncementService.searchErrors(filter).getItems();

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Returns the infos for the given filter.
     *
     * @param filter
     *            the search filter.
     *
     * @return the infos for the given filter.
     *
     * @throws ValidationException
     *             if filter is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/infos", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Info> getInfos(@RequestBody BasicPagedSearchFilter filter)
        throws ValidationException, OPMException {
        String signature = CLASS_NAME + "#getInfos(BasicPagedSearchFilter filter)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"filter"},
            new Object[] {filter});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {filter == null},
            new String[] {"'filter' can't be null."},
            "Failed to get the infos for the given filter.");

        List<Info> result = serviceAnnouncementService.searchInfos(filter).getItems();

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Returns the current user of the application.
     *
     * @param session
     *            http session holding currently logged username.
     *
     * @return The serialized details about current user.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "common/currentUser", method = RequestMethod.GET)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User getCurrentUser(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#getCurrentUser(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        User result = getLoggedUser(logger, signature, session);

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Sets the SuspensionService instance for retrieving suspension items count.
     *
     * @param suspensionService
     *            the SuspensionService instance for retrieving suspension items count.
     */
    public void setSuspensionService(SuspensionService suspensionService) {
        this.suspensionService = suspensionService;
    }

    /**
     * Sets the ApprovalService instance for approval items count.
     *
     * @param approvalService
     *            the ApprovalService instance for approval items count.
     */
    public void setApprovalService(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    /**
     * Sets the ServiceAnnouncementService instance for notifications retrieval.
     *
     * @param serviceAnnouncementService
     *            the ServiceAnnouncementService instance for notifications retrieval.
     */
    public void setServiceAnnouncementService(ServiceAnnouncementService serviceAnnouncementService) {
        this.serviceAnnouncementService = serviceAnnouncementService;
    }

    /**
     * Sets the AccountService instance for counting accounts assigned to user.
     *
     * @param accountService
     *            the AccountService instance for counting accounts assigned to user.
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService, userService, auditService, suspensionService, approvalService,
     *             serviceAnnouncementService or accountService is null; userSessionKey is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(suspensionService == null, "'suspensionService' can't be null.");
        Helper.checkState(approvalService == null, "'approvalService' can't be null.");
        Helper.checkState(serviceAnnouncementService == null, "'serviceAnnouncementService' can't be null.");
        Helper.checkState(accountService == null, "'accountService' can't be null.");
    }

    /**
     * Gets the logged user.
     *
     * @param logger
     *            the logger
     * @param signature
     *            the signature
     * @param session
     *            the session
     *
     * @return the logged user.
     *
     * @throws OPMException
     *             if any error occurs
     */
    private User getLoggedUser(Logger logger, String signature, HttpSession session) throws OPMException {
        User user = super.getCurrentUser(session);
        if (user == null) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The user is not logged in."));
        }

        return user;
    }
}
