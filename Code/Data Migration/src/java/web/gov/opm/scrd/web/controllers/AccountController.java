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
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.CalculationRequest;
import gov.opm.scrd.services.AccountService;
import gov.opm.scrd.services.EntityNotFoundException;
import gov.opm.scrd.services.OPMException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;

import org.jboss.logging.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * Performs demonstration functionality for this system assembly.
 * </p>
 *
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Changed to used AccountServiceImpl.</li>
 * <li>Removed to "execute" method.</li>
 * <li>Changed logger to org.jboss.logging.Logger type.</li>
 * </ol>
 * </p>
 *
 * @author j3_guile, TCSASSEMBLER
 * @version 1.0
 */
@Controller
public class AccountController {
    /**
     * JNDI binding for the account service.
     */
    private static final String ACCOUNT_SERVICE_JNDI = "java:app/opm-scrd-ejb/AccountServiceImpl!"
        + "gov.opm.scrd.services.impl.AccountServiceImpl";

    /**
     * Class logger.
     */
    private static final Logger LOG = Logger.getLogger(AccountController.class);

    /**
     * The account service EJB.
     */
    @EJB(mappedName = ACCOUNT_SERVICE_JNDI)
    private AccountService accountService;

    /**
     * Sets up custom formatters and binders.
     *
     * @param binder
     *            the web binder from the spring MVC framework
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * Shows the initial demonstration screen.
     *
     * @return the model and view.
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping("/")
    public ModelAndView view() throws OPMException {
        String signature = AccountController.class.getName() + "#view()";
        LoggingHelper.logEntrance(LOG, signature, null, null);

        ModelAndView mv = new ModelAndView("account");

        mv.addObject("account", new Account());

        CalculationRequest calculationRequest = new CalculationRequest();
        calculationRequest.setBeginDate(new Date());
        calculationRequest.setEndDate(new Date());
        mv.addObject("calculationRequest", calculationRequest);

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }

    /**
     * Demonstrates a call to retrieve an entity.
     *
     * @param id
     *            the ID of the account to retrieve
     * @param forUpdate
     *            if true, the retrieval will show the update screen
     * @return the model and view
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
    public ModelAndView retrieve(@RequestParam long id, @RequestParam boolean forUpdate) throws OPMException {
        String signature = AccountController.class.getName() + "#retrieve(long id, boolean forUpdate)";
        LoggingHelper.logEntrance(LOG, signature, new String[] {"id", "forUpdate"}, new Object[] {id, forUpdate});

        ModelAndView mv = new ModelAndView();
        Account account = accountService.get(id);
        if (account == null) {
            mv.addObject("msg", "Account does not exists or was already deleted!");
            mv.setViewName("accountResult");
        } else {
            mv.addObject("account", account);
            mv.setViewName(forUpdate ? "accountUpdate" : "accountResult");
        }

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }

    /**
     * Demonstrates a call to create an entity.
     *
     * @param account
     *            the account create
     * @return the model and view
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Account account) throws OPMException {
        String signature = AccountController.class.getName() + "#create(Account account)";
        LoggingHelper.logEntrance(LOG, signature, new String[] {"account"}, new Object[] {account});

        ModelAndView mv = new ModelAndView("accountResult");
        accountService.create(account);
        // get the created account
        mv.addObject("account", accountService.get(account.getId()));

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }

    /**
     * Demonstrates a call to update an entity.
     *
     * @param account
     *            the account updates
     * @return the model and view
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute Account account) throws OPMException {
        String signature = AccountController.class.getName() + "#update(Account account)";
        LoggingHelper.logEntrance(LOG, signature, new String[] {"account"}, new Object[] {account});
        ModelAndView mv = new ModelAndView("accountResult");

        try {
            accountService.update(account);
            // get the updated account
            mv.addObject("account", accountService.get(account.getId()));
        } catch (EntityNotFoundException e) {
            mv.addObject("msg", "Account does not exists or was already deleted!");
        }

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }

    /**
     * Demonstrates a call to delete an entity.
     *
     * @param id
     *            the ID of the entity to delete
     * @return the model and view
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam long id) throws OPMException {
        String signature = AccountController.class.getName() + "#delete(long id)";
        LoggingHelper.logEntrance(LOG, signature, new String[] {"id"}, new Object[] {id});

        ModelAndView mv = new ModelAndView("accountResult");
        try {
            accountService.delete(id);
            mv.addObject("msg", "Account [ID=" + id + "] was successfully deleted");
        } catch (EntityNotFoundException e) {
            mv.addObject("msg", "Account does not exists or was already deleted!");
        }

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }
}
