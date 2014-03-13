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

package gov.opm.scrd.servlets;

import gov.opm.scrd.Helper;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.jboss.security.SecurityContextAssociation;
import org.jboss.security.SubjectInfo;

/**
 * This is the servlet class to render home page.
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * 
 * @author TCSASSEMBLER
 * @version 1.0
 */
@WebServlet("/index.jsp")
public class HomeServlet extends HttpServlet {
    /**
     * Represents the serial version uid.
     */
    private static final long serialVersionUID = -7248124003317421300L;

    /**
     * Represents the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(HomeServlet.class);

    /**
     * Represents the request attribute name for auth type.
     */
    private static final String AUTHTYPE = "authType";

    /**
     * Represents the request attribute name for user name.
     */
    private static final String USERNAME = "username";

    /**
     * Represents the request attribute name for roles.
     */
    private static final String ROLES = "roles";

    /**
     * Represents the path for home page.
     */
    private static final String HOME_PAGE = "WEB-INF/pages/home.jsp";

    /**
     * Render home page with user name and roles information.
     * 
     * @param req
     *            the http servlet request.
     * @param resp
     *            the http servlet response.
     * @throws IOException
     *             throws if any io error happen.
     * @throws ServletException
     *             throw if any servlet error happen
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String signature = HomeServlet.class.getName()
                + "#doGet(HttpServletRequest req, HttpServletResponse resp)";
        Helper.logEntrance(LOGGER, signature, new String[] { "req", "resp" },
                new Object[] { req, resp });
        SubjectInfo info = SecurityContextAssociation.getSecurityContext()
                .getSubjectInfo();
        List<String> roles = Helper.GetRoles(info);
        req.setAttribute(AUTHTYPE, req.getAuthType());
        req.setAttribute(USERNAME, req.getUserPrincipal().getName());
        req.setAttribute(ROLES, roles);
        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
        Helper.logExit(LOGGER, signature, null);
    }
}
