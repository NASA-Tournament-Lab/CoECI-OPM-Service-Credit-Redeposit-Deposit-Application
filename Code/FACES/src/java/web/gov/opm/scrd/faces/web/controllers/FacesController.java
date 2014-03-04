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

package gov.opm.scrd.faces.web.controllers;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountNote;
import gov.opm.scrd.entities.application.AccountSearchFilter;
import gov.opm.scrd.entities.application.BillingSummary;
import gov.opm.scrd.entities.application.Calculation;
import gov.opm.scrd.entities.application.CalculationResultItem;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.PaymentStatementPrint;
import gov.opm.scrd.entities.application.User;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.entities.lookup.CalculationEndDateCalculationType;
import gov.opm.scrd.services.AccountService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.PaymentStatementPrintService;
import gov.opm.scrd.web.controllers.BaseAuditController;
import gov.opm.scrd.web.controllers.ValidationException;
import gov.opm.scrd.web.controllers.WebHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * This class represents the controller used to manage account data for FACES users.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong>This class is effectively thread safe after configuration, the injected services are
 * not modified in controllers by methods after configuration and configuration will be done in a thread safe manner by
 * Spring IoC framework.
 * </p>
 *
 * @author faeton, bannie2492
 * @version 1.0
 */
@Controller
@RequestMapping(value = "faces/")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FacesController extends BaseAuditController {

    /**
     * Represents the class name.
     */
    private static final String CLASS_NAME = FacesController.class.getName();
    /**
     * JNDI binding for the account service.
     */
    private static final String ACCOUNT_SERVICE_JNDI = "java:app/opm-faces-ejb/AccountServiceImpl!"
            + "gov.opm.scrd.services.impl.AccountServiceImpl";
    /**
     * JNDI binding for the payment statement print service.
     */
    private static final String PAYMENT_STATEMENT_PRINT_SERVICE_JNDI = "java:app/opm-faces-ejb/"
            + "PaymentStatementPrintServiceImpl!gov.opm.scrd.services.impl.PaymentStatementPrintServiceImpl";
    /**
     * Represents the AccountService instance for managing account data. It is modified by setter. It is injected by
     * Spring. It can not be null after injected.
     */
    @EJB(mappedName = ACCOUNT_SERVICE_JNDI)
    private AccountService accountService;

    /**
     * Represents the PaymentStatementPrintService instance for managing account data. It is modified by setter. It is
     * injected by Spring. It can not be null after injected.
     */
    @EJB(mappedName = PAYMENT_STATEMENT_PRINT_SERVICE_JNDI)
    private PaymentStatementPrintService paymentStatementPrintService;
    /**
     * Represents the key of the search filter. The value for the key in the session is the AccountSearchFilter
     * instance. It is modified by setter. It is injected by Spring. It can not be null/empty after injected.
     */
    @Autowired
    private String searchHistorySessionKey;
    /**
     * Represents the DateFormat instance used for date formatting for chart generation. It is modified by setter. It is
     * injected by Spring. It can not be null after injected.
     */
    @Autowired
    private DateFormat dateFormat;

    /**
     * This is the constructor of this class.
     */
    public FacesController() {
    }

    /**
     * This method is responsible for viewing the page of the controller.
     *
     * @param session the http session
     * @return the populated ModelAndView instance
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "view")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView viewSearch(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#viewSearch(HttpSession session)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"session"},
                new Object[]{session});

        session.removeAttribute("createdAccountId");
        ModelAndView result = populateModelAndView(session, new ModelAndView("accountSearchResult"));

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }

    /**
     * Searches the accounts based on the filter.
     *
     * @param filter the search filter
     * @param session the http session
     * @return serialized JSON representation of the SearchResult
     * @throws OPMException if there is any error from the backend
     * @throws ValidationException if filter is null
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public SearchResult<Account> search(@RequestBody AccountSearchFilter filter, HttpSession session)
            throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#search(AccountSearchFilter filter, HttpSession session)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"filter", "session"},
                new Object[]{filter, session});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{filter == null},
                new String[]{"'filter' can't be null."},
                "Failed to get the accounts for the given filter.");

        session.setAttribute(searchHistorySessionKey, filter);
        SearchResult<Account> result = accountService.search(filter);

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;

    }

    /**
     * Retrieves the search history of accounts.
     *
     * @param session the http session
     * @return Serialized JSON representation of the AccountSearchFilter;
     */
    @RequestMapping(value = "search/history")
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public AccountSearchFilter getSearchHistory(HttpSession session) {
        String signature = CLASS_NAME + "#getSearchHistory(HttpSession session)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"session"},
                new Object[]{session});

        AccountSearchFilter result = null;
        if (session.getAttribute(searchHistorySessionKey) != null) {
            result = (AccountSearchFilter) session.getAttribute(searchHistorySessionKey);
        }

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;

    }

    /**
     * Clears the account search history.
     *
     * @param session the http session
     */
    @RequestMapping(value = "search/history/clear", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void clearSearchHistory(HttpSession session) {
        String signature = CLASS_NAME + "#clearSearchHistory(HttpSession session)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"session"},
                new Object[]{session});

        session.removeAttribute(searchHistorySessionKey);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Returns the full account details for the id.
     *
     * @param accountId the id of account
     * @return the account for the id
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "{accountId}")
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Account getAccount(@PathVariable long accountId) throws OPMException {
        String signature = CLASS_NAME + "#getAccount(long accountId)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"accountId"},
                new Object[]{accountId});

        Account account = accountService.get(accountId);

        LoggingHelper.logExit(logger, signature, new Object[]{account});
        return account;
    }

    /**
     * Saves the calculation version for the account.
     *
     * @param accountId the account id to save
     * @param calculationVersion the calculation version
     * @param request the http servlet request
     * @return the calculation id and its name
     * @throws OPMException if there is any error from the backend
     * @throws ValidationException if calculationVersion is null
     */
    @RequestMapping(value = "{accountId}/calculation/save", method = RequestMethod.PUT)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String saveCalculationVersion(@RequestBody CalculationVersion calculationVersion,
            @PathVariable long accountId, HttpServletRequest request) throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#saveCalculationVersion(CalculationVersion calculationVersion,"
                + "long accountId, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"calculationVersion", "accountId", "request"},
                new Object[]{calculationVersion, accountId, request});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{calculationVersion == null},
                new String[]{"'calculationVersion' can't be null."},
                "Failed to save calculation because of null input.");
        // check if the version exists in the database
        CalculationVersion previousVersion = null;
        if (calculationVersion.getId() > 0) {
            List<CalculationVersion> versions = accountService.get(accountId).getCalculationVersions();
            for (CalculationVersion c : versions) {
                if (c.getId() == calculationVersion.getId()) {
                    previousVersion = c;
                    break;
                }
            }
        }

        calculationVersion.setName(getCalculationName(request.getSession()));
        accountService.saveCalculationVersion(accountId, calculationVersion);
        // auditing
        auditCalculationVersion(request, calculationVersion, previousVersion);
        String result = calculationVersion.getId() + "-" + calculationVersion.getName();
        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Deletes the calculation version for the account.
     *
     * @param calculationVersionId the calculation version id to delete
     * @param request the http servlet request
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "calculation/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCalculationVersion(@RequestParam long calculationVersionId,
            HttpServletRequest request) throws OPMException {
        String signature = CLASS_NAME + "#deleteCalculationVersion(long calculationVersionId,"
                + " HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"calculationVersionId", "request"},
                new Object[]{calculationVersionId, request});

        accountService.deleteCalculationVersion(calculationVersionId);

        // auditing
        User user = getCurrentUser(request.getSession());
        String itemName = "CalculationVersion";
        WebHelper.audit(request, getAuditService(), user, "deleteCalculationVersion",
                new Object[]{calculationVersionId, itemName, "deleted", false, true});

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Gets payments information for an account.
     *
     * @param accountId the account id
     * @return the payment list of the account
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "{accountId}/payments")
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Payment> getPayments(@PathVariable long accountId) throws OPMException {
        String signature = CLASS_NAME + "#getPayments(long accountId)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"accountId"},
                new Object[]{accountId});

        List<Payment> result = accountService.getPayments(accountId);

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }

    /**
     * Update the interest data of the account.
     *
     * @param accountId the id of account
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "{accountId}/updateInterest", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void updateInterest(@PathVariable long accountId) throws OPMException {
        String signature = CLASS_NAME + "#updateInterest(long accountId)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"accountId"},
                new Object[]{accountId});

        accountService.updateInterest(accountId);

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Returns the notes associated with the account.
     *
     * @param accountId the id of account
     * @return the account note list belonging to the specific account
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "{accountId}/notes")
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<AccountNote> getNotes(@PathVariable long accountId) throws OPMException {
        String signature = CLASS_NAME + "#getNotes(long accountId)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"accountId"},
                new Object[]{accountId});

        List<AccountNote> result = accountService.getNotes(accountId);

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }

    /**
     * Add the note associated with the account.
     *
     * @param accountId the id of account
     * @param note the account note to add
     * @param request the http servlet request
     * @throws OPMException if there is any error from the backend
     * @throws ValidationException if note is null
     * @return the note id
     */
    @RequestMapping(value = "{accountId}/notes", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long addNote(@PathVariable long accountId, @RequestBody AccountNote note, HttpServletRequest request)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#addNote(long accountId, AccountNote note, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"accountId", "note", "request"},
                new Object[]{accountId, note, request});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{note == null},
                new String[]{"'note' should not be null."},
                "Failed to create account note because of null input.");

        long result = accountService.addNote(accountId, note);

        // auditing
        User user = getCurrentUser(request.getSession());
        String itemName = "AccountNote";
        long itemId = 0l;
        WebHelper.audit(request, getAuditService(), user, "addNote",
                new Object[]{itemId, itemName, "date", null, note.getDate()},
                new Object[]{itemId, itemName, "writer", null, note.getWriter()},
                new Object[]{itemId, itemName, "text", null, note.getText()},
                new Object[]{itemId, itemName, "accountId", null, accountId});

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }

    /**
     * Updates the note associated with the account.
     *
     * @param note the account note to update
     * @param accountId the account id
     * @param request the http servlet request
     * @throws OPMException if there is any error from the backend
     * @throws ValidationException if note is null
     */
    @RequestMapping(value = "{accountId}/notes", method = RequestMethod.PUT)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateNote(@RequestBody AccountNote note, @PathVariable long accountId, HttpServletRequest request)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#updateNote(AccountNote note, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"note", "request"},
                new Object[]{note, request});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{note == null},
                new String[]{"'note' should not be null."},
                "Failed to update account note because of null input.");

        WebHelper.checkRequest(logger, signature,
                new boolean[]{note.getId() <= 0},
                new String[]{"'note' should have been saved in the database before."},
                "Failed to update account note because the input has not been save in the database.");
        AccountNote previousNote = null;
        List<AccountNote> notes = accountService.getNotes(accountId);
        for (AccountNote accountNote : notes) {
            if (note.getId() == accountNote.getId()) {
                previousNote = accountNote;
                break;
            }
        }
        accountService.updateNote(note);

        // auditing
        User user = getCurrentUser(request.getSession());
        String itemName = "AccountNote";
        long itemId = note.getId();
        WebHelper.audit(request, getAuditService(), user, "updateNote",
                new Object[]{itemId, itemName, "date", previousNote.getDate(), note.getDate()},
                new Object[]{itemId, itemName, "writer", previousNote.getWriter(), note.getWriter()},
                new Object[]{itemId, itemName, "text", previousNote.getText(), note.getText()});

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Deletes the note associated with the account.
     *
     * @param note the account note to delete
     * @param request the http servlet request
     * @throws OPMException if there is any error from the backend
     * @throws ValidationException if note is null
     */
    @RequestMapping(value = "notes", method = RequestMethod.DELETE)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteNote(@RequestBody AccountNote note, HttpServletRequest request)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#deleteNote(AccountNote note, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"note", "request"},
                new Object[]{note, request});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{note == null},
                new String[]{"'note' should not be null."},
                "Failed to delete account note because of null input.");

        WebHelper.checkRequest(logger, signature,
                new boolean[]{note.getId() <= 0},
                new String[]{"'note' should have been saved in the database before."},
                "Failed to delete account note because the input has not been save in the database..");

        accountService.deleteNote(note);

        // auditing
        User user = getCurrentUser(request.getSession());
        String itemName = "AccountNote";
        long itemId = note.getId();
        WebHelper.audit(request, getAuditService(), user, "deleteNote",
                new Object[]{itemId, itemName, "deleted", false, true});

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Saves the basic employee account data.
     *
     * @param account the account that the employee information is updated
     * @param request the http servlet request
     * @throws OPMException if there is any error from the backend
     * @throws ValidationException if account is null or holder is null
     */
    @RequestMapping(value = "saveEmployee", method = RequestMethod.PUT)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void updateEmployee(@RequestBody Account account, HttpServletRequest request)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#updateEmployee(Account account, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"account"},
                new Object[]{account});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{account == null || account.getHolder() == null},
                new String[]{"'account or holder' should not be null."},
                "Failed to update employee because of null input.");

        Account oldAccount = accountService.get(account.getId());

        accountService.updateEmployee(account);

        // auditing
        User user = getCurrentUser(request.getSession());
        String itemName = "Account";
        long itemId = account.getId();
        WebHelper.audit(request, getAuditService(), user, "updateEmployee",
                new Object[]{itemId, itemName, "formType", oldAccount.getFormType(), account.getFormType()},
                new Object[]{itemId, itemName, "status", oldAccount.getStatus(), account.getStatus()},
                new Object[]{itemId, itemName, "holder", oldAccount.getHolder(), account.getHolder()});

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Calculates the end date for chart generation.
     *
     * @param value the hours for the date
     * @param type the calculation type
     * @return the end date string
     * @throws OPMException if there is any error from the backend
     * @throws ValidationException if value is negative
     */
    @RequestMapping(value = "calculateEndDate")
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public String calculateEndDate(@RequestParam Integer value, @RequestParam CalculationEndDateCalculationType type)
        throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#calculateEndDate(Integer value, CalculationEndDateCalculationType type)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"value", "type"},
                new Object[]{value, type});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{value <= 0},
                new String[]{"'value' should be positive."},
                "Failed to calculate end date because of negative input.");

        Date date = accountService.calculateEndDate(value, type);
        String result = dateFormat.format(date);

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }

    /**
     * Directs to the account detail page.
     *
     * @param accountId the account id
     * @param session the http session
     * @return the model and view object
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "{accountId}/detail")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView viewDetail(@PathVariable long accountId, HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#viewDetail(long accountId, HttpSession session)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"accountId", "session"},
                new Object[]{accountId, session});

        session.removeAttribute("createdAccountId");
        session.setAttribute("currentAccountId", accountId);
        Account account = accountService.get(accountId);
        ModelAndView result = populateModelAndView(session, new ModelAndView("accountDetail"));
        result.addObject("a", account);
        result.addObject("notes", accountService.getNotes(accountId));

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }

    /**
     * Save and update the billing summary information of the account.
     *
     * @param accountId the account id
     * @param summary the billing summary object
     * @param request http servlet request
     * @throws OPMException if there is any error from the backend
     * @throws ValidationException if summary is null
     */
    @RequestMapping(value = "{accountId}/summary", method = RequestMethod.PUT)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void saveBillingSummary(@RequestBody BillingSummary summary, @PathVariable long accountId,
        HttpServletRequest request) throws OPMException, ValidationException {
        String signature = CLASS_NAME + "#saveBillingSummary(BillingSummary summary, long accountId, "
            + "HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"summary", "accountId", "request"},
                new Object[]{summary, accountId, request});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{summary == null},
                new String[]{"'summary' should not be null."},
                "Failed to save billing summary because of null input.");

        BillingSummary oldValue = accountService.get(accountId).getBillingSummary();

        accountService.updateBillingSummary(summary);

        // auditing
        User user = getCurrentUser(request.getSession());
        String itemName = "BillingSummary";
        long itemId = summary.getId();
        WebHelper.audit(request, getAuditService(), user, "saveBillingSummary",
                new Object[]{itemId, itemName, "billings", oldValue.getBillings(), summary.getBillings()},
                new Object[]{itemId, itemName, "computedDate", oldValue.getComputedDate(), summary.getComputedDate()},
                new Object[]{itemId, itemName, "firstBillingDate", oldValue.getFirstBillingDate(),
                    summary.getFirstBillingDate()},
                new Object[]{itemId, itemName, "lastInterestCalculation", oldValue.getLastInterestCalculation(),
                    summary.getLastInterestCalculation()});

        LoggingHelper.logExit(logger, signature, null);
    }
    /**
     * Close the account.
     * @param accountId the account id
     * @param request the http servlet request
     * @throws ValidationException if the account id is not positive
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "close", method = RequestMethod.PUT)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void close(@RequestBody long accountId, HttpServletRequest request) throws ValidationException,
        OPMException {
        String signature = CLASS_NAME + "#close(long accountId, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"accountId", "request"},
                new Object[]{accountId, request});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{accountId <= 0},
                new String[]{"'accountId' should be positive."},
                "Failed to close account because the account id is not positive.");

        Account oldAccount = getAccount(accountId);
        accountService.close(accountId);
        Account newAccount = getAccount(accountId);
        // auditing
        User user = getCurrentUser(request.getSession());
        String itemName = "Account";
        WebHelper.audit(request, getAuditService(), user, "close",
                new Object[]{accountId, itemName, "accountStatus", oldAccount.getStatus(), newAccount.getStatus()});

        LoggingHelper.logExit(logger, signature, null);
    }
    /**
     * Reopen the account.
     * @param accountId the account id
     * @param request the http servlet request
     * @throws ValidationException if the account id is not positive
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "reopen", method = RequestMethod.PUT)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void reopen(@RequestBody long accountId, HttpServletRequest request) throws ValidationException,
        OPMException {
        String signature = CLASS_NAME + "#reopen(long accountId, HttpServletRequest request)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"accountId", "request"},
                new Object[]{accountId, request});

        WebHelper.checkRequest(logger, signature,
                new boolean[]{accountId <= 0},
                new String[]{"'accountId' should be positive."},
                "Failed to reopen account because the account id is not positive.");

        Account oldAccount = getAccount(accountId);
        accountService.reopen(accountId);
        Account newAccount = getAccount(accountId);
        // auditing
        User user = getCurrentUser(request.getSession());
        String itemName = "Account";
        WebHelper.audit(request, getAuditService(), user, "reopen",
                new Object[]{accountId, itemName, "accountStatus", oldAccount.getStatus(), newAccount.getStatus()});

        LoggingHelper.logExit(logger, signature, null);
    }
    /**
     * Get the PaymentStatementPrint entity.
     * @return the entity
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "requestPaymentStatementPrint")
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public PaymentStatementPrint requestPaymentStatementPrint() throws OPMException {
        String signature = CLASS_NAME + "#requestPaymentStatementPrint()";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{},
                new Object[]{});

        PaymentStatementPrint result = paymentStatementPrintService.requestPaymentStatementPrint();

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }
    /**
     * Update a specific calculation.
     * @param versionId the calculation version id
     * @param calculation the calculation
     * @return the id of the updated calculation
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "calculationVersion/{versionId}/calculation", method = RequestMethod.PUT)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long updateCalculation(@PathVariable long versionId, @RequestBody Calculation calculation)
        throws OPMException {
        String signature = CLASS_NAME + "#updateCalculation(long versionId, Calculation calculation)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"versionId", "calculation"},
                new Object[]{versionId, calculation});

        long result = accountService.updateCalculation(versionId, calculation);

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }
    /**
     * Update a specific calculation result item.
     * @param resultId the id of calculation result
     * @param calculationResultItem the calculation result item
     * @return the id of the updated calculation result item
     * @throws OPMException if there is any error from the backend
     */
    @RequestMapping(value = "calculationResult/{resultId}/calculationResultItem", method = RequestMethod.PUT)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long updateCalculationResultItem(@PathVariable long resultId,
        @RequestBody CalculationResultItem calculationResultItem) throws OPMException {
        String signature = CLASS_NAME + "#updateCalculation(long resultId, "
                + "CalculationResultItem calculationResultItem)";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{"resultId", "calculationResultItem"},
                new Object[]{resultId, calculationResultItem});

        long result = accountService.updateCalculationResultItem(resultId, calculationResultItem);

        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }
    /**
     * Get the page view of payment statement print.
     * @return the view name
     */
    @RequestMapping(value = "viewPaymentStatementPrint")
    public String viewPaymentStatementPrint() {
        String signature = CLASS_NAME + "#viewPaymentStatementPrint()";
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[]{},
                new Object[]{});
        String result = "paymentStatementPrint";
        LoggingHelper.logExit(logger, signature, new Object[]{result});
        return result;
    }

    /**
     * Sets the AccountService instance for account manipulation.
     *
     * @param accountService the AccountService instance for account manipulation.
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Sets the search history sessionKey.
     *
     * @param searchHistorySessionKey the search history sessionKey.
     */
    public void setSearchHistorySessionKey(String searchHistorySessionKey) {
        this.searchHistorySessionKey = searchHistorySessionKey;
    }

    /**
     * Sets the DateFormat instance to generate end date.
     *
     * @param dateFormat the DateFormat instance to generate end date.
     */
    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException if accountService, calculationExecutionService is null, or
     * searchHistorySessionKey is null/empty, paymentStatementPrintService is null
     */
    @PostConstruct
    protected void checkInit() {
        super.checkInit();
        Helper.checkState(accountService == null, "accountService should not be null.");
        Helper.checkState(paymentStatementPrintService == null, "paymentStatementPrintService should not be null.");
        Helper.checkState(dateFormat == null, "dateFormat should not be null.");
        Helper.checkState(searchHistorySessionKey == null, "searchHistorySessionKey should not be null.");
        Helper.checkState(searchHistorySessionKey.trim().isEmpty(), "searchHistorySessionKey should not be empty.");
    }

    /**
     * Returns the version name for calculation versions.
     *
     * @param session the http session
     * @return the version name
     * @throws OPMException if there is any error from the backend
     */
    private String getCalculationName(HttpSession session) throws OPMException {
        DateFormat format = new SimpleDateFormat("MMM dd yyyy hh:mm a", Locale.US);
        return this.getCurrentUser(session).getUsername() + " " + format.format(new Date());
    }

    /**
     * Audit calculation versions.
     * @param request the http request
     * @param calculationVersion the latest calculation version
     * @param previousVersion the previous calculation version
     * @throws OPMException if there is any error from the backend
     */
    private void auditCalculationVersion(HttpServletRequest request, CalculationVersion calculationVersion,
        CalculationVersion previousVersion) throws OPMException {
        User user = getCurrentUser(request.getSession());
        String itemName = "CalculationVersion";
        long itemId = calculationVersion.getId();
        WebHelper.audit(request, getAuditService(), user, "saveCalculationVersion",
                new Object[]{itemId, itemName, "name", previousVersion == null ? null : previousVersion.getName(),
                    calculationVersion.getName()},
                new Object[]{itemId, itemName, "calculations",
                    previousVersion == null ? null : previousVersion.getCalculations(),
                    calculationVersion.getCalculations()},
                new Object[]{itemId, itemName, "calculationResult",
                    previousVersion == null ? null : previousVersion.getCalculationResult(),
                    calculationVersion.getCalculationResult()},
                new Object[]{itemId, itemName, "calculationDate",
                    previousVersion == null ? null : previousVersion.getCalculationDate(),
                    calculationVersion.getCalculationDate()});
    }
}
