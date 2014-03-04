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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 * This interceptor puts the currently logged username into user session from Spring Security. Note that it should be
 * invoked before AuthorizationInterceptor.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration.
 * </p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
public class UserDataInterceptor extends BaseInterceptor {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = UserDataInterceptor.class.getName();

    /**
     * Creates an instance of UserDataInterceptor.
     */
    public UserDataInterceptor() {
        // Empty
    }

    /**
     * Intercepts the user request and inserts currently logged username into session.
     *
     * @param request
     *            http servlet request
     * @param response
     *            http servlet respnse
     * @param handler
     *            handler object
     *
     * @return Always returns true.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String signature = CLASS_NAME
            + "#preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"request", "response", "handler"},
            new Object[] {request, response, handler});

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        request.getSession().setAttribute(getUserSessionKey(), username);

        boolean result = true;

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }
}
