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
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.AuthorizationException;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.SecurityService;
import gov.opm.scrd.services.UserService;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * This class is the base class of all controllers which provides functionality shared for all controllers of
 * application.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the injected services are
 * not modified in controllers by methods after configuration and configuration will be done in a thread safe manner by
 * Spring IoC framework.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
public abstract class BaseController {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = BaseController.class.getName();

    /**
     * This is the logger used for logging. It is initialized in constructor and never changed afterwards. It can not be
     * null.
     */
    private final Logger logger;

    /**
     * Represents the list of widget ids on the page for the controller. It is modified by setter. It is injected by
     * Spring. It can not be null or contain null/empty elements after injected.
     */
    @Resource(name = "widgetIds")
    private List<String> widgetIds;

    /**
     * Represents the SecurityService instance for retrieving enabled widgets data for the page. It is modified by
     * setter. It is injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.SECURITY_SERVICE_JNDI)
    private SecurityService securityService;

    /**
     * Represents the UserService instance for retrieving currently logged user and performing other actions. It is
     * modified by setter. It is injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.USER_SERVICE_JNDI)
    private UserService userService;

    /**
     * Represents the key of the currently logged user in the session. The value for the key in the session is the user
     * name. It is modified by setter. It is injected by Spring. It can not be null/empty after injected.
     */
    @Autowired
    private String userSessionKey;

    /**
     * Creates an instance of BaseController.
     */
    protected BaseController() {
        logger = Logger.getLogger(this.getClass());
    }

    /**
     * Gets the logger used for logging.
     *
     * @return the logger used for logging.
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * This method is responsible for populating the model and view with the data about enabled widgets.
     *
     * @param session
     *            current http session.
     * @param modelAndView
     *            the ModelAndView instance to populate
     *
     * @return The populated ModelAndView instance.
     *
     * @throws IllegalArgumentException
     *             if session or modelAndView instance is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    protected ModelAndView populateModelAndView(HttpSession session, ModelAndView modelAndView) throws OPMException {
        String signature = CLASS_NAME + "#populateModelAndView(HttpSession session, ModelAndView modelAndView)";

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session", "modelAndView"},
            new Object[] {session, modelAndView});

        Helper.checkNull(logger, signature, modelAndView, "modelAndView");

        User user = getCurrentUser(session);
        if (user == null) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The user is not logged in."));
        }
        for (String widgetId : widgetIds) {
            try {
                securityService.authorize(user.getUsername(), Arrays.asList(user.getRole().getName()), widgetId);
                modelAndView.addObject(widgetId, true);
            } catch (AuthorizationException e) {
                modelAndView.addObject(widgetId, false);
            }
        }

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }

    /**
     * Sets the list of widget ids on the page for the controller.
     *
     * @param widgetIds
     *            the list of widget ids on the page for the controller.
     */
    public void setWidgetIds(List<String> widgetIds) {
        this.widgetIds = widgetIds;
    }

    /**
     * Sets the SecurityService instance for retrieving enabled widgets data for the page.
     *
     * @param securityService
     *            the SecurityService instance for retrieving enabled widgets data for the page.
     */
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * Sets the UserService instance for retrieving currently logged user and performing other actions.
     *
     * @return the UserService instance for retrieving currently logged user and performing other actions.
     */
    protected UserService getUserService() {
        return userService;
    }

    /**
     * Sets the UserService instance for retrieving currently logged user and performing other actions.
     *
     * @param userService
     *            the UserService instance for retrieving currently logged user and performing other actions.
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves the current logged user based on the session key.
     *
     * @param session
     *            http session holding currently logged username.
     *
     * @return User instance representing currently logged user.
     *
     * @throws IllegalArgumentException
     *             if session is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    protected User getCurrentUser(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#getCurrentUser(HttpSession session)";

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        Helper.checkNull(logger, signature, session, "session");

        try {
            String loggedUsername = (String) session.getAttribute(userSessionKey);

            User result = userService.getByUsername(loggedUsername);

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalArgumentException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The attribute of key '"
                + userSessionKey + "' can't be null/empty.", e));
        } catch (ClassCastException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The attribute of key '"
                + userSessionKey + "' should be a string.", e));
        }
    }

    /**
     * Sets the key of the currently logged user in the session.
     *
     * @param userSessionKey
     *            the key of the currently logged user in the session.
     */
    public void setUserSessionKey(String userSessionKey) {
        this.userSessionKey = userSessionKey;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService or userService is null; userSessionKey is null/empty).
     */
    @PostConstruct
    protected void checkInit() {
        Helper.checkState(widgetIds == null, "'widgetIds' can't be null.");
        for (String widgetId : widgetIds) {
            Helper.checkState(WebHelper.isNullOrEmpty(widgetId), "'widgetIds' can't contain null/empty element.");
        }

        Helper.checkState(securityService == null, "'securityService' can't be null.");
        Helper.checkState(userService == null, "'userService' can't be null.");

        Helper.checkState(WebHelper.isNullOrEmpty(userSessionKey), "'userSessionKey' can't be null/empty.");
    }
}
