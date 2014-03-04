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

package gov.opm.scrd.services;

import gov.opm.scrd.entities.application.Account;
import gov.opm.scrd.entities.application.AccountConfirmationValidation;
import gov.opm.scrd.entities.application.AccountNote;
import gov.opm.scrd.entities.application.AccountSearchFilter;
import gov.opm.scrd.entities.application.Billing;
import gov.opm.scrd.entities.application.BillingSummary;
import gov.opm.scrd.entities.application.CalculationVersion;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.common.SearchResult;
import gov.opm.scrd.entities.lookup.CalculationEndDateCalculationType;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * This interface defines a contract for managing account data. It provides method for creating/updating account and its
 * data, search and calculation related functionality.
 * </p>
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Account Module Assembly 1.0):</em>
 * <ul>
 * <li>#addNote: return the note id.</li>
 * <li>Add #getPayments, updateBillingSummary.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Release I Assembly 1.0):</em>
 * <ul>
 * <li>remove saveFERSDepositCalculationVersion, saveFERSRedepositCalculationVersion</li>
 * <li>add method saveCalculationVersion.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, sparemax, TCSASSEMBLER, bannie2492
 * @version 1.2
 */
public interface AccountService {
    /**
     * Creates the account.
     *
     * @param account
     *            the account to create.
     *
     * @return The id of the created account instance.
     *
     * @throws IllegalArgumentException
     *             if account is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public long create(Account account) throws OPMException;

    /**
     * Updates the account.
     *
     * @param account
     *            the account to update.
     *
     * @throws IllegalArgumentException
     *             if account is null.
     * @throws EntityNotFoundException
     *             if there is no such account to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void update(Account account) throws OPMException;

    /**
     * Updates the employee data of the account.
     *
     * @param account
     *            the account to update.
     *
     * @throws IllegalArgumentException
     *             if account is null.
     * @throws EntityNotFoundException
     *             if there is no such account to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void updateEmployee(Account account) throws OPMException;

    /**
     * Deletes the account by id.
     *
     * @param accountId
     *            the account id to update.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such account to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void delete(long accountId) throws OPMException;

    /**
     * Gets the account by id.
     *
     * @param accountId
     *            the account id to get.
     *
     * @return The account for the id or null if it can not be found.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public Account get(long accountId) throws OPMException;

    /**
     * Gets the account by claim number.
     *
     * @param claimNumber
     *            the claim number to get account.
     *
     * @return The account for the claim number or null if it can not be found.
     *
     * @throws IllegalArgumentException
     *             if claimNumber is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public Account getByClaimNumber(String claimNumber) throws OPMException;

    /**
     * Searches accounts based on the filter.
     *
     * @param filter
     *            the filter to search account.
     *
     * @return SearchResult&lt;Account&gt; instance holding information about search result.
     *
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public SearchResult<Account> search(AccountSearchFilter filter) throws OPMException;

    /**
     * Adds the note for the account.
     *
     * @param accountId
     *            the id of account to add note.
     * @param note
     *            the account note to add.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive or note is null.
     * @throws EntityNotFoundException
     *             if there is no such account to add note.
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @return the note id
     */
    public long addNote(long accountId, AccountNote note) throws OPMException;

    /**
     * Adds the note for the account giving account claim number.
     *
     * @param claimNumber
     *            the claimNumber of account to add note.
     * @param note
     *            the account note to add.
     *
     * @throws IllegalArgumentException
     *             if claimNumber is null/empty or note is null.
     * @throws EntityNotFoundException
     *             if there is no such account to add note.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void addNoteByClaimNumber(String claimNumber, AccountNote note) throws OPMException;

    /**
     * Updates account note.
     *
     * @param note
     *            the account note to update.
     *
     * @throws IllegalArgumentException
     *             if note is null.
     * @throws EntityNotFoundException
     *             if there is no such note to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void updateNote(AccountNote note) throws OPMException;

    /**
     * Deletes account note.
     *
     * @param note
     *            the account note to delete.
     *
     * @throws IllegalArgumentException
     *             if note is null.
     * @throws EntityNotFoundException
     *             if there is no such note to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void deleteNote(AccountNote note) throws OPMException;

    /**
     * Retrieves all notes for the account given account id.
     *
     * @param accountId
     *            the id of account to retrieve notes.
     *
     * @return List of account notes for the account, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<AccountNote> getNotes(long accountId) throws OPMException;

    /**
     * Saves the calculation version for the account.
     *
     * @param accountId
     *            the id of account to save calculation version.
     * @param calculationVersion
     *            the calculation version to save.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive or calculationVersion is null.
     * @throws EntityNotFoundException
     *             if there is no such account to save data.
     * @throws OPMException
     *             if there is any problem when executing the method.
     *
     * @since 1.2 (OPM - Release I Assembly 1.0)
     */
    public void saveCalculationVersion(long accountId, CalculationVersion calculationVersion)
        throws OPMException;

    /**
     * Deletes the calculation version for the id.
     *
     * @param calculationVersionId
     *            the id of calculation version to delete.
     *
     * @throws IllegalArgumentException
     *             if calculationVersionId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such calculation version to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void deleteCalculationVersion(long calculationVersionId) throws OPMException;

    /**
     * Saves the billings data.
     *
     * @param accountId
     *            the id of account to save billing data
     * @param billings
     *            the billings data to save.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive or billings is null or contains null elements.
     * @throws EntityNotFoundException
     *             if there is no such note to save billings.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void saveBillings(long accountId, List<Billing> billings) throws OPMException;

    /**
     * Approves the account.
     *
     * @param validity
     *            the information about account approval.
     *
     * @throws IllegalArgumentException
     *             if validity is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void approve(AccountConfirmationValidation validity) throws OPMException;

    /**
     * Rejects the account.
     *
     * @param validity
     *            the information about account rejection.
     *
     * @throws IllegalArgumentException
     *             if validity is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void reject(AccountConfirmationValidation validity) throws OPMException;

    /**
     * Updates account interest.
     *
     * @param accountId
     *            the id of account to update interest.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such account to update.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void updateInterest(long accountId) throws OPMException;

    /**
     * Retrieves the count of accounts assigned to the user.
     *
     * @param username
     *            the name of user to count accounts for.
     *
     * @return Number of accounts assigned to the user, can be 0 or positive integer.
     *
     * @throws IllegalArgumentException
     *             if username is null/empty
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public int countAccountsAssignedToUser(String username) throws OPMException;

    /**
     * Assigns account to the claim officer.
     *
     * @param accountId
     *            the id of account to assign claim officer.
     * @param claimOfficer
     *            the name of the claim officer to assign account.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive or claimOfficer is null/empty.
     * @throws EntityNotFoundException
     *             if there is no such account to assign.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void assignAccount(long accountId, String claimOfficer) throws OPMException;

    /**
     * Unassigns account from claim officer.
     *
     * @param accountId
     *            the id of account to unassign from claim officer.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such account to unassign.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void unassignAccount(long accountId) throws OPMException;

    /**
     * Calculates the end date for the given time value.
     *
     * @param value
     *            the value of the time to calculate.
     * @param type
     *            the calculation type of the operation.
     *
     * @return The end date for the given calculation type and value, can not be null.
     *
     * @throws IllegalArgumentException
     *             if value is null/negative integer, type is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     *
     * @since 1.1 (OPM - SCRD - Frontend Account Module Assembly 1.0)
     */
    public Date calculateEndDate(Integer value, CalculationEndDateCalculationType type) throws OPMException;
    /**
     * Get payments for the account.
     *
     * @param accountId the account id
     * @return payments records for a specific account
     * @throws OPMException if there is any problem when executing the method
     */
    public List<Payment> getPayments(long accountId) throws OPMException;
    /**
     * Update the billing summary for an account.
     *
     * @param summary the new billing summary
     * @throws OPMException if there is any problem when executing the method.
     *
     * @since 1.1 (OPM - SCRD - Frontend Account Module Assembly 1.0)
     */
    public void updateBillingSummary(BillingSummary summary) throws OPMException;
}
