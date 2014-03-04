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

package gov.opm.scrd.filters;

import gov.opm.scrd.Helper;
import gov.opm.scrd.services.SecurityService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.jboss.security.SecurityContextAssociation;
import org.jboss.security.SubjectInfo;

/**
 * This is the filter class to check authorization.
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * 
 * @author TCSASSEMBLER
 * @version 1.0
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    /**
     * Represents the logger.
     */
    private static final Logger LOGGER = Logger
            .getLogger(AuthorizationFilter.class);

    /**
     * Represents the authorization error message.
     */
    private static final String AUTHORIZATION_ERROR = 
            "You are not authorizd to perform this action with identifier %s.";

    /**
     * Represents the file extension names that not check authorization.
     */
    private static final List<String> NOTCHECKS = Arrays.asList(".js", ".css");

    /**
     * Represents the security service.
     */
    @Inject
    private SecurityService securityService;

    /**
     * The destroy method for filter.
     */
    @Override
    public void destroy() {
        final String signature = AuthorizationFilter.class.getName()
                + "#destroy()";
        Helper.logEntrance(LOGGER, signature, null, null);
        Helper.logExit(LOGGER, signature, null);
    }

    /**
     * Check authorization when filter,it will use identifier or path info as
     * action.
     * 
     * @param req
     *            the servlet request.
     * @param res
     *            the servlet response.
     * @param chain
     *            the filter chain.
     * @throws IOException
     *             throws if any io error happen.
     * @throws ServletException
     *             throw if any servlet error happen
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        final String signature = AuthorizationFilter.class.getName()
                + "#doFilter(ServletRequest req, ServletResponse res,FilterChain chain)";
        Helper.logEntrance(LOGGER, signature, new String[] { "req", "res",
                "chain" }, new Object[] { req, res, chain });
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;

        String action = req.getParameter(Helper.IDENTIFIER_NAME);
        // if not found identifier will use path info as identifier
        if (Helper.isNullOrEmpty(action)) {
            action = httpReq.getRequestURI().substring(
                    httpReq.getContextPath().length());
        }
        action = action.toLowerCase().trim();
        SubjectInfo info = SecurityContextAssociation.getSecurityContext()
                .getSubjectInfo();
        String username = httpReq.getUserPrincipal().getName();
        List<String> roleNames = Helper.GetRoles(info);
        boolean notCheck = false;
        for (String suffix : NOTCHECKS) {
            if (action.endsWith(suffix)) {
                notCheck = true;
                break;
            }
        }
        if (notCheck || securityService.authorize(username, roleNames, action)) {
            chain.doFilter(req, res);
            Helper.logExit(LOGGER, signature, null);
        } else {
            httpRes.getWriter().println(
                    String.format(AUTHORIZATION_ERROR, action));
            httpRes.getWriter().close();
            Helper.logExit(LOGGER, signature, null);
        }
    }

    /**
     * The init method for filter.
     * 
     * @param config
     *            the filter config.
     * @throws ServletException
     *             throw if any servlet error happen
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        final String signature = AuthorizationFilter.class.getName()
                + "#init(FilterConfig config)";
        Helper.logEntrance(LOGGER, signature, new String[] { "config" },
                new Object[] { config });
        Helper.logExit(LOGGER, signature, null);
    }
}
