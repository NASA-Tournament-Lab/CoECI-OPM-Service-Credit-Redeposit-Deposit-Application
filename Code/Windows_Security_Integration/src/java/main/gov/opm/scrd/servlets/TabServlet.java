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
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

/**
 * This is the servlet class to mock send tab content by identifier.
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * 
 * @author TCSASSEMBLER
 * @version 1.0
 */
@WebServlet("/Tab")
public class TabServlet extends HttpServlet {
    /**
     * Represents the serial version uid.
     */
    private static final long serialVersionUID = 8559103513166906029L;

    /**
     * Represents the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TabServlet.class);

    /**
     * Represents the error message when no identifier.
     */
    private static final String INPUT_ERROR = "You must provide identifier to show tab.";

    /**
     * Represents the template for tab.
     */
    private static final String TAB_TEMPLATE = 
            "This tab with identifier %1$s performed at %2$tY-%2$tm-%2$td %2$tH:%2$tM:%2$tS.";

    /**
     * Read identifier from request and return mock tab content.
     * 
     * @param req
     *            the http servlet request.
     * @param resp
     *            the http servlet response.
     * @throws IOException
     *             throws if any io error happen.
     * @throws IOException
     *             throws if any io error happen.
     * @throws ServletException
     *             throw if any servlet error happen
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String signature = TabServlet.class.getName()
                + "#doGet(HttpServletRequest req, HttpServletResponse resp)";
        Helper.logEntrance(LOGGER, signature, new String[] { "req", "resp" },
                new Object[] { req, resp });
        String id = req.getParameter(Helper.IDENTIFIER_NAME);
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        if (Helper.isNullOrEmpty(id)) {
            writer.println(INPUT_ERROR);
        } else {
            writer.println(String.format(TAB_TEMPLATE, id, new Date()));
        }
        writer.close();
        Helper.logExit(LOGGER, signature, null);
    }
}
