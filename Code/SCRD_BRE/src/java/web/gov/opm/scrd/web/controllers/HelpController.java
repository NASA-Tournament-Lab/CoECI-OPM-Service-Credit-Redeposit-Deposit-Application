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
import gov.opm.scrd.entities.application.HelpItem;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.HelpService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * This class represents the controller used to show help data.
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
public class HelpController extends BaseAuditController {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = HelpController.class.getName();

    /**
     * Represents the HelpService instance for retrieving data. It is modified by setter. It is injected by Spring. It
     * can not be null after injected.
     */
    @EJB(mappedName = WebHelper.HELP_SERVICE_JNDI)
    private HelpService helpService;

    /**
     * Creates an instance of HelpController.
     */
    public HelpController() {
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
    @RequestMapping(value = "help/view", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView view(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        ModelAndView modelAndView = populateModelAndView(session, new ModelAndView("help"));

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }

    /**
     * Searches help contents for the given term.
     *
     * @param term
     *            the term to search.
     * @return help items for the given term.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "help/search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<HelpItem> search(String term) throws OPMException {
        String signature = CLASS_NAME + "#search(String term)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"term"},
            new Object[] {term});

        List<HelpItem> items;
        if (WebHelper.isNullOrEmpty(term)) {
            // Get all help items
            items = helpService.getAll();
        } else {
            // Perform search
            items = helpService.search(term);
        }

        LoggingHelper.logExit(logger, signature, new Object[] {items});
        return items;
    }

    /**
     * Returns the help item details for the given id.
     *
     * @param helpItemId
     *            the id of help item.
     *
     * @return HelpItem entity for the id.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "help/{helpItemId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public HelpItem get(@PathVariable long helpItemId) throws OPMException {
        String signature = CLASS_NAME + "#get(long helpItemId)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"helpItemId"},
            new Object[] {helpItemId});

        HelpItem item = helpService.get(helpItemId);

        // The HelpItem#related collection is lazy loaded
        // So need load it here
        if (item != null && item.getRelated() != null) {
            item.getRelated().size();
        }

        LoggingHelper.logExit(logger, signature, new Object[] {item});
        return item;
    }

    /**
     * Setter method for property <tt>helpService</tt>.
     * @param helpService value to be assigned to property helpService
     */
    public void setHelpService(HelpService helpService) {
        this.helpService = helpService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService, userService, auditService or helpService is null; userSessionKey is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(helpService == null, "'helpService' can't be null.");
    }
}

