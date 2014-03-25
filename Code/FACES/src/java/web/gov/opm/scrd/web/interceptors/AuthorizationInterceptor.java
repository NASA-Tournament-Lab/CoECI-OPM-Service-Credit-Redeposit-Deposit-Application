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

package gov.opm.scrd.web.interceptors;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.SecurityService;
import gov.opm.scrd.services.UserService;
import gov.opm.scrd.web.controllers.WebHelper;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UrlPathHelper;

/**
 * <p>
 * This class intercepts the user request and checks whether the user is authorized to perform the given action. Note
 * that it should be invoked after UserDataInterceptor.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Use the request path to determine the action.</li>
 * <li>Propagate the exception instead of sending error response. The exception will be handled
 * by AJAXExceptionResolver.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the injected services are
 * not modified in controllers by methods after configuration and configuration will be done in a thread safe manner by
 * Spring IoC framework.
 * </p>
 *
 * @author faeton, sparemax, liuliquan
 * @version 1.1
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
public class AuthorizationInterceptor extends BaseInterceptor {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = AuthorizationInterceptor.class.getName();

    /**
     * Represents the SecurityService instance for checking whether user is authorized to perform action. It is modified
     * by setter. It is injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.SECURITY_SERVICE_JNDI)
    private SecurityService securityService;

    /**
     * Represents the UserService instance for retrieving currently logged user. It is modified by setter. It is
     * injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.USER_SERVICE_JNDI)
    private UserService userService;

    /**
     * The URL path helper.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    @Autowired
    private UrlPathHelper urlPathHelper;

    /**
     * Creates an instance of AuthorizationInterceptor.
     */
    public AuthorizationInterceptor() {
        // Empty
    }

    /**
     * Intercepts the user request and checks whether the user is authorized to perform the given action.
     *
     * @param request
     *            http servlet request
     * @param response
     *            http servlet respnse
     * @param handler
     *            handler object
     *
     * @return True, if user is authorized to perform request, false otherwise.
     *
     * @throws Exception
     *             if any error occurs
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String signature = CLASS_NAME
            + "#preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"request", "response", "handler"},
            new Object[] {request, response, handler});

        try {
            String loggedUsername = (String) request.getSession().getAttribute(getUserSessionKey());
            User user = userService.getByUsername(loggedUsername);

            if (user == null) {
                // redirect to the login page
                response.sendRedirect(request.getContextPath() + "/login.html");
                LoggingHelper.logExit(logger, signature, new Object[] {false});
                return false;
            } else {
                // Note: the request parameter can be faked by client user with arbitrary value
                // So should use the request path to determine the action
                String action = urlPathHelper.getPathWithinApplication(request);

                // Prepend the HTTP method for fine-grained control
                action = request.getMethod().toUpperCase() + " " + action;

                // Authorize
                securityService.authorize(user.getUsername(), Arrays.asList(user.getRole().getName()), action);
            }

        } catch (ClassCastException e) {
            throw LoggingHelper.logException(logger, signature, e);
        }

        LoggingHelper.logExit(logger, signature, new Object[] {true});
        return true;
    }

    /**
     * Sets the SecurityService instance for checking whether user is authorized to perform action.
     *
     * @param securityService
     *            the SecurityService instance for checking whether user is authorized to perform action.
     */
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * Sets the UserService instance for retrieving currently logged user.
     *
     * @param userService
     *            the UserService instance for retrieving currently logged user.
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (userSessionKey is null/empty; securityService or
     *             userService is null).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(securityService == null, "'securityService' can't be null.");
        Helper.checkState(userService == null, "'userService' can't be null.");
    }
}
