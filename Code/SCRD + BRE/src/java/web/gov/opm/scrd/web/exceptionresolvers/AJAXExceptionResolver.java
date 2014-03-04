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

package gov.opm.scrd.web.exceptionresolvers;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.services.AuthorizationException;
import gov.opm.scrd.web.controllers.ValidationException;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * <p>
 * This class is the exception resolver for the AJAX requests, which will set proper exception HTTP code, if necessary.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Modify resolveException() to write exception message to response for AJAX requests.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is immutable and thread safe.
 * </p>
 *
 * @author faeton, sparemax, liuliquan
 * @version 1.1
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
public class AJAXExceptionResolver extends SimpleMappingExceptionResolver {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = AJAXExceptionResolver.class.getName();

    /**
     * This is the logger used for logging. It is initialized in constructor and never changed afterwards. It can not be
     * null.
     */
    private final Logger logger;

    /**
     * Creates an instance of AJAXExceptionResolver.
     */
    public AJAXExceptionResolver() {
        logger = Logger.getLogger(this.getClass());
    }

    /**
     * Resolves the exception and sets proper http code.
     *
     * @param request
     *            the http servlet request
     * @param response
     *            the http servlet response
     * @param handler
     *            the handler object
     * @param exception
     *            the thrown exception
     *
     * @return Resulting ModelAndView to show to user
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception exception) {
        String signature = CLASS_NAME + "#resolveException(HttpServletRequest request, HttpServletResponse response,"
            + " Object handler, Exception exception)";

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"request", "response", "handler", "exception"},
            new Object[] {request, response, handler, exception});

        ModelAndView result;
        // Perform the check only for AJAX requests
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            try {
                String message = exception.getMessage();
                if (exception instanceof ValidationException) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else if (exception instanceof AuthorizationException) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                // Write exception message to response
                response.getWriter().print(message);
            } catch (IOException e) {
                // Log and ignore the exception
                LoggingHelper.logException(logger, signature, e);
            }
            result = new ModelAndView();
        } else {
            // Return error page
            result = new ModelAndView("error");
            result.addObject("exception", exception);
        }

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }
}
