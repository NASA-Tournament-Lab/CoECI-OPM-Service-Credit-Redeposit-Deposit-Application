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

import gov.opm.scrd.entities.application.SuspendedPayment;

import java.util.List;

/**
 * <p>
 * This interface defines a contract for managing suspension related functionality of payments.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 * 
 * Changed in the OPM - Frontend - Payments Module Assembly:
 * Add List<SuspendedPayment> getSuspendedPayments(long account) method.
 *
 * @author faeton, sparemax, woodjhon
 * @version 1.1
 */
public interface SuspensionService {
    /**
     * Gets the number of suspensions for the given user.
     *
     * @param suspender
     *            the name of user to get number of suspensions.
     *
     * @return Number of suspensions for user, can be 0 or positive integer.
     *
     * @throws IllegalArgumentException
     *             if suspender is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public int getSuspensionCount(String suspender) throws OPMException;

    /**
     * Gets the suspended payments for the user.
     *
     * @param suspender
     *            the name of user to get suspended payments.
     *
     * @return List of suspended payments, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if suspender is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<SuspendedPayment> getSuspendedPayments(String suspender) throws OPMException;
    
    /**
     * Gets the suspended payments for the accound id.
     *
     * @param accoundId
     *            the account id to get suspended payments.
     *
     * @return List of suspended payments, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if account id is not positive.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<SuspendedPayment> getSuspendedPayments(long accountId) throws OPMException;

    /**
     * Reset the the payment by id and return the modified payment.
     *
     * @param paymentId
     *            the payment id to reset.
     *
     * @return The reset payment for the id, can not be null.
     *
     * @throws IllegalArgumentException
     *             if paymentId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such payment to reset.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public SuspendedPayment resetPayment(long paymentId) throws OPMException;

    /**
     * Post payments for the user.
     *
     * @param paymentIds
     *            the payment ids to post.
     *
     * @return The posted payments for the ids, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if paymentIds is null.
     * @throws EntityNotFoundException
     *             if there is no such payment for any payment id in the list.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<SuspendedPayment> postPayments(long[] paymentIds) throws OPMException;

    /**
     * Link the payment to the account.
     *
     * @param paymentId
     *            the payment id to link.
     * @param accountId
     *            the accountId to link.
     *
     * @return The linked payment for the id, can not be null.
     *
     * @throws IllegalArgumentException
     *             if paymentId or accountId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such payment/account for the id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public SuspendedPayment linkPaymentToAccount(long paymentId, long accountId) throws OPMException;

    /**
     * Transfers the payment given the type and refund option.
     *
     * @param paymentId
     *            the payment id to transfer.
     * @param transferTypeId
     *            the id of the transfer type.
     * @param refund
     *            the flag specifying whether transfer should be refunded.
     *
     * @return The transferred payment for the id, can not be null.
     *
     * @throws IllegalArgumentException
     *             if paymentId or transferTypeId is not positive.
     * @throws EntityNotFoundException
     *             if there is no such payment/transfer type for the id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public SuspendedPayment transferPayment(long paymentId, long transferTypeId, Boolean refund) throws OPMException;
}
