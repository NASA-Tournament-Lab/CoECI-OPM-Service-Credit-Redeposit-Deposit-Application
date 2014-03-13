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
import gov.opm.scrd.entities.application.ReportGenerationData;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.ReportGenerationDataService;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * This class represents the controller used to show admin tab.
 * Specifically, it is used to manage user permissions and report generation data.
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
public class AdminController extends BaseAuditController {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = AdminController.class.getName();

    /**
     * Represents the ReportGenerationDataService instance for retrieve report generation data. It is modified by
     * setter. It is injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.REPORT_GENERATION_DATA_SERVICE_JNDI)
    private ReportGenerationDataService reportGenerationDataService;

    /**
     * Creates an instance of AdminController.
     */
    public AdminController() {
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
    @RequestMapping(value = "admin/view", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView view(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        ModelAndView modelAndView = populateModelAndView(session, new ModelAndView("admin"));

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }

    /**
     * Views the user table on the user permissions page.
     *
     * @return The serialized list of users.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "admin/user/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> list() throws OPMException {
        String signature = CLASS_NAME + "#list()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        List<User> users = getUserService().getAll();

        LoggingHelper.logExit(logger, signature, new Object[] {users});
        return users;
    }

    /**
     * Returns the user details for the given id.
     *
     * @param userId
     *            the id of user.
     *
     * @return User entity for the id.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "admin/user/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User getUser(@PathVariable long userId) throws OPMException {
        String signature = CLASS_NAME + "#getUser(long userId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"userId"},
            new Object[] {userId});

        User user = getUserService().get(userId);

        LoggingHelper.logExit(logger, signature, new Object[] {user});
        return user;
    }

    /**
     * Returns the possible user supervisors.
     *
     * @return List of user entities representing supervisors.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "admin/user/supervisors", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> getSupervisors() throws OPMException {
        String signature = CLASS_NAME + "#getSupervisors()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        List<User> supervisors = getUserService().getSupervisors();

        LoggingHelper.logExit(logger, signature, new Object[] {supervisors});
        return supervisors;
    }

    /**
     * Updates the user entity.
     *
     * @param request
     *            the http servlet request
     * @param user
     *            the user to update
     *
     * @throws ValidationException
     *             if user is null or does not exist, or if user's supervisor is himself
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "admin/user/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(HttpServletRequest request, @RequestBody User user) throws ValidationException, OPMException {
        String signature = CLASS_NAME + "#update(HttpServletRequest request, User user)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"request", "user"}, new Object[] {request, user});

        // Check request
        WebHelper.checkRequest(logger, signature, new boolean[] {user == null},
                new String[] {"'user' can't be null."}, "Failed to update user.");

        long userId = user.getId();

        // Get existing user
        User existingUser = getUserService().get(userId);

        // Check user exists
        WebHelper.checkRequest(logger, signature,
            new boolean[] {existingUser == null},
            new String[] {"User does not exist. Id = " + userId},
            "Failed to update user.");

        if (user.getSupervisorId() != null && user.getSupervisorId().longValue() == userId) {
            throw LoggingHelper.logException(logger, signature,
                    new ValidationException("Failed to update user.",
                            Arrays.asList("User can not select himself as supervisor")));
        }

        // Audit
        WebHelper.audit(request, getAuditService(), getCurrentUser(request.getSession()), "updateUser",
            new Object[] {userId, "User", "status", existingUser.getStatus(), user.getStatus()},
            new Object[] {userId, "User", "role", existingUser.getRole(), user.getRole()},
            new Object[] {userId, "User", "firstName", existingUser.getFirstName(), user.getFirstName()},
            new Object[] {userId, "User", "lastName", existingUser.getLastName(), user.getLastName()},
            new Object[] {userId, "User", "email", existingUser.getEmail(), user.getEmail()},
            new Object[] {userId, "User", "telephone", existingUser.getTelephone(), user.getTelephone()},
            new Object[] {userId, "User", "supervisorId", existingUser.getSupervisorId(), user.getSupervisorId()});

        // Safety update, do not change other fields (like username, defaultTab, etc...)
        user.setUsername(existingUser.getUsername());
        user.setDefaultTab(existingUser.getDefaultTab());
        user.setNetworkId(existingUser.getNetworkId());
        user.setDeleted(existingUser.isDeleted());

        // Update user
        getUserService().update(user);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Returns the report generation data used in the application.
     *
     * @return the report generation data used in the application or null if they it is not set yet.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "admin/reportGenerationData", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ReportGenerationData getReportGenerationData() throws OPMException {
        String signature = CLASS_NAME + "#getReportGenerationData()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        ReportGenerationData data = reportGenerationDataService.getReportGenerationData();

        LoggingHelper.logExit(logger, signature, new Object[] {data});
        return data;
    }

    /**
     * Setter method for property <tt>reportGenerationDataService</tt>.
     * @param reportGenerationDataService value to be assigned to property reportGenerationDataService
     */
    public void setReportGenerationDataService(ReportGenerationDataService reportGenerationDataService) {
        this.reportGenerationDataService = reportGenerationDataService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService, userService, auditService or reportGenerationDataService is null;
     *             userSessionKey is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(reportGenerationDataService == null, "'reportGenerationDataService' can't be null.");
    }
}

