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
import gov.opm.scrd.entities.application.AccountHolder;
import gov.opm.scrd.entities.application.AccountSearchFilter;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.entities.common.IdentifiableEntity;
import gov.opm.scrd.entities.common.SortOrder;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.services.AccountService;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * This class represents the controller used to manage work queue and cases data.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Change getAssignedCases()/getUnassignedCases()/getAllProcessedCases() methods
 * to require transaction for lazy loading.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the injected services are
 * not modified in controllers by methods after configuration and configuration will be done in a thread safe manner by
 * Spring IoC framework.
 * </p>
 *
 * @author faeton, sparemax, liuliquan
 * @version 1.1
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
@Controller
@TransactionManagement(TransactionManagementType.CONTAINER)
public class WorkQueueController extends BaseAuditController {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = WorkQueueController.class.getName();

    /**
     * Represents the AccountService instance for managing account data. It is modified by setter. It is injected by
     * Spring. It can not be null after injected.
     */
    @EJB(mappedName = WebHelper.ACCOUNT_SERVICE_JNDI)
    private AccountService accountService;

    /**
     * Creates an instance of WorkQueueController.
     */
    public WorkQueueController() {
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
    @RequestMapping(value = "workQueue/view", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView view(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        ModelAndView modelAndView = populateModelAndView(session, new ModelAndView("workQueue"));

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }

    /**
     * Returns all assigned cases in the work queue.
     *
     * @param filter the search filter.
     *
     * @return List of cases, may be empty.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "workQueue/assignedCases", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SearchResult<AccountDto> getAssignedCases(@RequestBody AccountSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#getAssignedCases()";

        return getCases(getLogger(), signature, true, filter);
    }

    /**
     * Returns all unassigned cases in the work queue.
     *
     * @param filter the search filter.
     *
     * @return List of cases, may be empty.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "workQueue/unassignedCases", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SearchResult<AccountDto> getUnassignedCases(@RequestBody AccountSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#getUnassignedCases()";

        return getCases(getLogger(), signature, false, filter);
    }

    /**
     * Returns all processed cases in the work queue.
     *
     * @param filter the search filter.
     *
     * @return List of cases, may be empty.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "workQueue/processedCases", method = RequestMethod.POST)
    @ResponseBody
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SearchResult<AccountDto> getAllProcessedCases(@RequestBody AccountSearchFilter filter) throws OPMException {
        String signature = CLASS_NAME + "#getAllProcessedCases()";

        return getCases(getLogger(), signature, null, filter);
    }

    /**
     * Unassigns the case.
     *
     * @param request
     *            the http servlet request
     * @param account
     *            the case to unassign
     *
     * @throws ValidationException
     *             if account is null
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "workQueue/unassign", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void unassignCase(HttpServletRequest request, @RequestBody Account account) throws ValidationException,
        OPMException {
        String signature = CLASS_NAME + "#unassignCase(HttpServletRequest request, Account account)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"request", "account"},
            new Object[] {request, account});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {account == null},
            new String[] {"'account' can't be null."},
            "Failed to unassign the case.");

        updateCase(logger, signature, "unassignCase", request, account, null, null);
    }

    /**
     * Assigns the case.
     *
     * @param request
     *            the http servlet request
     * @param account
     *            the case to assign
     * @param claimOfficer
     *            the claim officer the case is assigned to
     *
     * @throws ValidationException
     *             if account is null, clainOfficer is null/empty
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "workQueue/assign", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void assignCase(HttpServletRequest request, @RequestBody Account account, @RequestParam String claimOfficer)
        throws ValidationException, OPMException {
        String signature = CLASS_NAME + "#assignCase(HttpServletRequest request, Account account, String claimOfficer)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"request", "account", "claimOfficer"},
            new Object[] {request, account, claimOfficer});

        // Check request
        WebHelper.checkRequest(logger, signature,
            new boolean[] {account == null, WebHelper.isNullOrEmpty(claimOfficer)},
            new String[] {"'account' can't be null.", "'clainOfficer' can't be null/empty."},
            "Failed to assign the case.");

        updateCase(logger, signature, "assignCase", request, account, claimOfficer, new Date());
    }

    /**
     * Sets the AccountService instance for managing account data.
     *
     * @param accountService
     *            the AccountService instance for managing account data.
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService, userService, auditService or accountService is null; userSessionKey is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(accountService == null, "'accountService' can't be null.");
    }

    /**
     * Update the case.
     *
     * @param logger
     *            the logger
     * @param signature
     *            the signature
     * @param actionName
     *            the action name
     * @param request
     *            the request
     * @param account
     *            the account
     * @param claimOfficer
     *            the claim officer
     * @param claimOfficerAssignmentDate
     *            the claim officer assignment date
     *
     * @throws OPMException
     *             if any error occurs
     */
    private void updateCase(Logger logger, String signature, String actionName, HttpServletRequest request,
        Account account, String claimOfficer, Date claimOfficerAssignmentDate) throws OPMException {

        account = accountService.get(account.getId());

        String previousClaimOfficer = account.getClaimOfficer();
        Date previousClaimOfficerAssignmentDate = account.getClaimOfficerAssignmentDate();

        account.setClaimOfficer(claimOfficer);
        account.setClaimOfficerAssignmentDate(claimOfficerAssignmentDate);
        accountService.update(account);

        long accountId = account.getId();

        WebHelper.audit(request, getAuditService(), getCurrentUser(request.getSession()), "unassignCase",
            new Object[] {accountId, "Account", "claimOfficer", previousClaimOfficer, account.getClaimOfficer()},
            new Object[] {accountId, "Account", "claimOfficerAssignmentDate", previousClaimOfficerAssignmentDate,
                account.getClaimOfficerAssignmentDate()});

        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Returns all cases in the work queue.
     *
     * @param logger
     *            the logger
     * @param signature
     *            the signature
     * @param assigned
     *            the assigned flag
     * @param filter
     *            the filter for searching
     *
     * @return List of cases, may be empty.
     *
     * @throws OPMException
     *             if any error occurs
     */
    private SearchResult<AccountDto> getCases(Logger logger, String signature, Boolean assigned, AccountSearchFilter filter) throws OPMException {
        LoggingHelper.logEntrance(logger, signature, null, null);

        //AccountSearchFilter filter = new AccountSearchFilter();
        filter.setAssigned(assigned);
        filter.setSortColumn("claimNumber");
        filter.setSortOrder(SortOrder.DESC);

        //List<Account> items = accountService.search(filter).getItems();
        //List<AccountDto> result = new ArrayList<AccountDto>();
        //for (Account item : items) {
            //result.add(convertAccount(item));
        //}

        SearchResult<Account> accountResult = accountService.search(filter);
        List<Account> accountItems = accountResult.getItems();
        List<AccountDto> items = new ArrayList<AccountDto>();
        for (Account item : accountItems) {
            items.add(convertAccount(item));
        }

        SearchResult<AccountDto> result = new SearchResult<AccountDto>();
        result.setItems(items);
        result.setTotal(accountResult.getTotal());
        result.setTotalPageCount(accountResult.getTotalPageCount());

        LoggingHelper.logExit(logger, signature, new Object[] {result});
        return result;
    }

    /**
     * Converts the Account to AccountDto.
     *
     * @param account
     *            the Account
     *
     * @return the AccountDto
     */
    private static AccountDto convertAccount(Account account) {
        AccountDto dto = new AccountDto();

        dto.setId(account.getId());
        dto.setClaimNumber(account.getClaimNumber());
        dto.setClaimOfficer(account.getClaimOfficer());
        dto.setClaimOfficerAssignmentDate(account.getClaimOfficerAssignmentDate());
        dto.setReturnedFromRecordsDate(account.getReturnedFromRecordsDate());

        AccountHolder holder = account.getHolder();
        dto.setSsn(holder.getSsn());
        dto.setFirstName(holder.getFirstName());
        dto.setMiddleInitial(holder.getMiddleInitial());
        dto.setLastName(holder.getLastName());

        return dto;
    }

    /**
     * <P>
     * The Account DTO.
     * </p>
     * <p>
     * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
     * </p>
     *
     * @author TCSASSEMBLER
     * @version 1.0
     */
    private static class AccountDto extends IdentifiableEntity {
        /**
         * The Account#claimNumber.
         */
        private String claimNumber;

        /**
         * The Account#claimOfficer.
         */
        private String claimOfficer;

        /**
         * The Account#claimOfficerAssignmentDate.
         */
        private Date claimOfficerAssignmentDate;

        /**
         * The Account#returnedFromRecordsDate.
         */
        private Date returnedFromRecordsDate;

        /**
         * The Account#holder#ssn.
         */
        private String ssn;

        /**
         * The Account#holder#firstName.
         */
        private String firstName;

        /**
         * The Account#holder#middleInitial.
         */
        private String middleInitial;

        /**
         * The Account#holder#lastName.
         */
        private String lastName;

        /**
         * Creates an instance of AccountDto.
         */
        public AccountDto() {
            // Empty
        }

        /**
         * Gets the claim number of account.
         *
         * @return the claim number of account.
         */
        @SuppressWarnings("unused")
        public String getClaimNumber() {
            return claimNumber;
        }

        /**
         * Sets the claim number of account.
         *
         * @param claimNumber
         *            the claim number of account.
         */
        public void setClaimNumber(String claimNumber) {
            this.claimNumber = claimNumber;
        }

        /**
         * Gets the claim officer assigned to the account.
         *
         * @return the claim officer assigned to the account.
         */
        @SuppressWarnings("unused")
        public String getClaimOfficer() {
            return claimOfficer;
        }

        /**
         * Sets the claim officer assigned to the account.
         *
         * @param claimOfficer
         *            the claim officer assigned to the account.
         */
        public void setClaimOfficer(String claimOfficer) {
            this.claimOfficer = claimOfficer;
        }

        /**
         * Gets the assigned date of account.
         *
         * @return the assigned date of account.
         */
        @SuppressWarnings("unused")
        public Date getClaimOfficerAssignmentDate() {
            return claimOfficerAssignmentDate;
        }

        /**
         * Sets the assigned date of account.
         *
         * @param claimOfficerAssignmentDate
         *            the assigned date of account.
         */
        public void setClaimOfficerAssignmentDate(Date claimOfficerAssignmentDate) {
            this.claimOfficerAssignmentDate = claimOfficerAssignmentDate;
        }

        /**
         * Gets the timestamp when account was returned from records.
         *
         * @return the timestamp when account was returned from records.
         */
        @SuppressWarnings("unused")
        public Date getReturnedFromRecordsDate() {
            return returnedFromRecordsDate;
        }

        /**
         * Sets the timestamp when account was returned from records.
         *
         * @param returnedFromRecordsDate
         *            the timestamp when account was returned from records.
         */
        public void setReturnedFromRecordsDate(Date returnedFromRecordsDate) {
            this.returnedFromRecordsDate = returnedFromRecordsDate;
        }

        /**
         * Gets the social security number of account holder.
         *
         * @return the social security number of account holder.
         */
        @SuppressWarnings("unused")
        public String getSsn() {
            return ssn;
        }

        /**
         * Sets the social security number of account holder.
         *
         * @param ssn
         *            the social security number of account holder.
         */
        public void setSsn(String ssn) {
            this.ssn = ssn;
        }

        /**
         * Gets the first name of account holder.
         *
         * @return the first name of account holder.
         */
        @SuppressWarnings("unused")
        public String getFirstName() {
            return firstName;
        }

        /**
         * Sets the first name of account holder.
         *
         * @param firstName
         *            the first name of account holder.
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * Gets the middle initial of account holder.
         *
         * @return the middle initial of account holder.
         */
        @SuppressWarnings("unused")
        public String getMiddleInitial() {
            return middleInitial;
        }

        /**
         * Sets the middle initial of account holder.
         *
         * @param middleInitial
         *            the middle initial of account holder.
         */
        public void setMiddleInitial(String middleInitial) {
            this.middleInitial = middleInitial;
        }

        /**
         * Gets the last name of account holder.
         *
         * @return the last name of account holder.
         */
        @SuppressWarnings("unused")
        public String getLastName() {
            return lastName;
        }

        /**
         * Sets the last name of account holder.
         *
         * @param lastName
         *            the last name of account holder.
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
