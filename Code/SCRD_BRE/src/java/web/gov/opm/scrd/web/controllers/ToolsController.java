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
import gov.opm.scrd.entities.application.Printout;
import gov.opm.scrd.entities.application.ServiceCreditPreference;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.BatchPrintoutArchiveService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.ServiceCreditPreferenceService;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * This class represents the controller used to show tools tab.
 * Specifically, it is used to manage service credit preferences and batch printout archive.
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
public class ToolsController extends BaseAuditController {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ToolsController.class.getName();

    /**
     * Represents the ServiceCreditPreferencesService instance to manage service credit preferences. It is modified by
     * setter. It is injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.SERVICE_CREDIT_PREFERENCE_SERVICE_JNDI)
    private ServiceCreditPreferenceService serviceCreditPreferencesService;

    /**
     * Represents the BatchPrintoutArchiveService instance to manage batch printout archive. It is modified by setter.
     * It is injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.BATCH_PRINT_OUT_ARCHIVE_SERVICE_JNDI)
    private BatchPrintoutArchiveService batchPrintoutArchiveService;

    /**
     * Creates an instance of ToolsController.
     */
    public ToolsController() {
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
    @RequestMapping(value = "tools/view", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView view(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        ModelAndView modelAndView = populateModelAndView(session, new ModelAndView("tools"));

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }

    /**
     * Returns service credit preferences data.
     *
     * @return ServiceCreditPreferences instance holding the data
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "tools/serviceCreditPreferences", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ServiceCreditPreference getServiceCreditPreference() throws OPMException {
        String signature = CLASS_NAME + "#getServiceCreditPreference()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        ServiceCreditPreference scp = serviceCreditPreferencesService.getServiceCreditPreference();

        LoggingHelper.logExit(logger, signature, new Object[] {scp});
        return scp;
    }

    /**
     * Saves service credit preferences data.
     *
     * @param request
     *            the http servlet request
     * @param preference
     *            ServiceCreditPreferences instance holding the data
     *
     * @throws ValidationException
     *             if preferences is null or does not exist
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "tools/serviceCreditPreferences", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveServiceCreditPreference(HttpServletRequest request, @RequestBody ServiceCreditPreference preference)
        throws ValidationException, OPMException {
        String signature = CLASS_NAME
            + "#saveServiceCreditPreference(HttpServletRequest request, ServiceCreditPreference preference)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"request", "preference"},
            new Object[] {request, preference});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {preference == null},
            new String[] {"'preference' can't be null."},
            "Failed to save service credit preference.");

        // Get existing preference
        ServiceCreditPreference existingSCP = serviceCreditPreferencesService.getServiceCreditPreference();

        // Check preference exists
        WebHelper.checkRequest(logger, signature,
            new boolean[] {existingSCP == null},
            new String[] {"Preference does not exist"},
            "Failed to save service credit preference.");

        long scpId = existingSCP.getId();

        // Audit
        WebHelper.audit(request, getAuditService(), getCurrentUser(request.getSession()), "saveServiceCreditPreference",
            new Object[] {scpId, "ServiceCreditPreference",
                "useAgents", existingSCP.isUseAgents(), preference.isUseAgents()},
            new Object[] {scpId, "ServiceCreditPreference",
                "useStatusBar", existingSCP.isUseStatusBar(), preference.isUseStatusBar()},
            new Object[] {scpId, "ServiceCreditPreference",
                "useMessageBox", existingSCP.isUseMessageBox(), preference.isUseMessageBox()},
            new Object[] {scpId, "ServiceCreditPreference",
                "otherSetting", existingSCP.getOtherSetting(), preference.getOtherSetting()});

        // Update preference
        serviceCreditPreferencesService.setServiceCreditPreference(preference);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Returns available printouts.
     *
     * @return List of Printout instances holding the data
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "tools/printouts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Printout> getAvailablePrintouts() throws OPMException {
        String signature = CLASS_NAME + "#getAvailablePrintouts()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        List<Printout> printouts = batchPrintoutArchiveService.getAvailablePrintouts();

        LoggingHelper.logExit(logger, signature, new Object[] {printouts});
        return printouts;
    }

    /**
     * Views the content of the printout.
     *
     * @param name
     *            the printout name to view.
     * @param response
     *            the http servlet response to write contents of the printout.
     *
     * @throws ValidationException
     *             if response is null, name is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "tools/printoutByName", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void viewPrintout(@RequestParam String name, HttpServletResponse response)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME
                + "#viewPrintout(String name, HttpServletResponse response)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, new String[] {"name", "response"}, new Object[] {name, response});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {response == null, WebHelper.isNullOrEmpty(name)},
            new String[] {"'response' can't be null.", "'name' can't be null/empty."},
            "Failed to view print out.");

        try {
            response.getOutputStream().write(batchPrintoutArchiveService.getPrintout(name));
        } catch (IOException ioe) {
            throw LoggingHelper.logException(logger, signature,
                    new OPMException("IO error occurs when viewing print out", ioe));
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        response.setHeader("Content-Type", "application/pdf");

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Setter method for property <tt>serviceCreditPreferencesService</tt>.
     * @param serviceCreditPreferencesService value to be assigned to property serviceCreditPreferencesService
     */
    public void setServiceCreditPreferencesService(ServiceCreditPreferenceService serviceCreditPreferencesService) {
        this.serviceCreditPreferencesService = serviceCreditPreferencesService;
    }

    /**
     * Setter method for property <tt>batchPrintoutArchiveService</tt>.
     * @param batchPrintoutArchiveService value to be assigned to property batchPrintoutArchiveService
     */
    public void setBatchPrintoutArchiveService(BatchPrintoutArchiveService batchPrintoutArchiveService) {
        this.batchPrintoutArchiveService = batchPrintoutArchiveService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService, userService, auditService, serviceCreditPreferencesService or
     *             batchPrintoutArchiveService is null; userSessionKey is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(serviceCreditPreferencesService == null, "'serviceCreditPreferencesService' can't be null.");
        Helper.checkState(batchPrintoutArchiveService == null, "'batchPrintoutArchiveService' can't be null.");
    }
}

