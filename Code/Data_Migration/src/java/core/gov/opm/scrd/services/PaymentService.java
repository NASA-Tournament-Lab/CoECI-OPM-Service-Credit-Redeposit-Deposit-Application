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

import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.entities.application.PaymentSearchFilter;
import gov.opm.scrd.entities.common.SearchResult;

import java.util.List;

/**
 * <p>
 * This interface defines a contract for managing payments.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - SCRD - Frontend Miscellaneous Module Assembly 1.0):</em>
 * <ul>
 * <li>Add get(long paymentId) method to get payment by Id</li>
 * <li>Add getPaymentNote(long paymentId) method to get payment note</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, sparemax, liuliquan
 * @version 1.1
 */
public interface PaymentService {

    /**
     * Gets the payment by id.
     *
     * @param paymentId
     *            the payment id to get.
     *
     * @return The payment for the id or null if it can not be found.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    public Payment get(long paymentId) throws OPMException;

    /**
     * Retrieves note of payment by id.
     *
     * @param paymentId
     *            the id of payment to retrieve its note.
     *
     * @return The note for the payment, can be null.
     *
     * @throws IllegalArgumentException
     *             if paymentId is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     * @since OPM - Frontend - Miscellaneous Module Assembly
     */
    public String getPaymentNote(long paymentId) throws OPMException;

    /**
     * Creates the payment for the account.
     *
     * @param accountId
     *            the id of account to associate payment.
     * @param payment
     *            payment to create.
     *
     * @return The id of the created payment instance.
     *
     * @throws IllegalArgumentException
     *             if accountId is not positive or payment is null.
     * @throws EntityNotFoundException
     *             if there is no such account.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public long create(long accountId, Payment payment) throws OPMException;

    /**
     * Updates payments.
     *
     * @param payments
     *            payments to update.
     *
     * @throws IllegalArgumentException
     *             if payment is null/contain null elements.
     * @throws EntityNotFoundException
     *             if there is no such payment for any element of payments array.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void update(List<Payment> payments) throws OPMException;

    /**
     * Deletes the payment by id.
     *
     * @param paymentId
     *            the payment id to update.
     *
     * @throws IllegalArgumentException
     *             if paymentId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such payment to delete.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void delete(long paymentId) throws OPMException;

    /**
     * Searches payments based on the filter.
     *
     * @param filter
     *            the filter to search payment.
     *
     * @return SearchResult&lt;Payment&t; instance holding information about search result.
     *
     * @throws IllegalArgumentException
     *             if filter is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public SearchResult<Payment> search(PaymentSearchFilter filter) throws OPMException;

    /**
     * Saves the note for the payment.
     *
     * @param paymentId
     *            the id of payment to add note.
     * @param note
     *            the payment note to add.
     *
     * @throws IllegalArgumentException
     *             if paymentId is not positive or note is null.
     * @throws EntityNotFoundException
     *             if there is no such payment to add note.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void savePaymentNote(long paymentId, String note) throws OPMException;

    /**
     * Reverses the payment.
     *
     * @param reverser
     *            the name of the user performing operation.
     * @param paymentId
     *            the id of payment to reverse
     * @param paymentReversalReasonId
     *            the id of reversal reason
     * @param applyReversalToGL
     *            the flag specifying whether payment reversal should be applied to GL
     *
     * @throws IllegalArgumentException
     *             if paymentId or paymentReversalReasonId is not positive, or reverser is null/empty.
     * @throws EntityNotFoundException
     *             if there is no such payment to reverse, no record for paymentReversalReasonId.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void reverse(String reverser, long paymentId, long paymentReversalReasonId, boolean applyReversalToGL)
        throws OPMException;
}
