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

import gov.opm.scrd.entities.application.ApprovalItemSummary;
import gov.opm.scrd.entities.application.InterestAdjustment;
import gov.opm.scrd.entities.application.PaymentMove;
import gov.opm.scrd.entities.application.PendingPayment;
import gov.opm.scrd.entities.lookup.ApprovalStatus;

import java.util.List;

/**
 * <p>
 * This interface defines a contract for managing approvals of different type of payments.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public interface ApprovalService {
    /**
     * Gets the information about approval summary for the user.
     *
     * @param approver
     *            the name of user to get approval summary for.
     *
     * @return ApprovalItemSummary instance containing summary information, can not be null.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public ApprovalItemSummary getApprovalSummary(String approver) throws OPMException;

    /**
     * Gets the pending payments for the user.
     *
     * @param approver
     *            the name of user to get pending payments.
     *
     * @return List of pending payments, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<PendingPayment> getPendingPayments(String approver) throws OPMException;

    /**
     * Approves pending payments.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param pendingPaymentIds
     *            the ids of payments to approve.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, pendingPaymentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void approvePendingPayments(String approver, long[] pendingPaymentIds) throws OPMException;

    /**
     * Disapproves pending payments.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param pendingPaymentIds
     *            the ids of payments to disapprove.
     * @param reason
     *            the reason
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, pendingPaymentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void disapprovePendingPayments(String approver, long[] pendingPaymentIds, String reason) throws OPMException;

    /**
     * Gets the interest adjustments for the user.
     *
     * @param approver
     *            the name of user to get interest adjustments.
     * @param approvalStatus
     *            the approval status of the interest adjustments.
     *
     * @return List of interest adjustments, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, approvalStatus is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<InterestAdjustment> getInterestAdjustments(String approver, ApprovalStatus approvalStatus)
        throws OPMException;

    /**
     * Approves interest adjustments.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param interestAdjustmentIds
     *            the ids of interest adjustments to approve.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, interestAdjustmentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void approveInterestAdjustments(String approver, long[] interestAdjustmentIds) throws OPMException;

    /**
     * Disapproves interest adjustments.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param interestAdjustmentIds
     *            the ids of interest adjustments to disapprove.
     * @param reason
     *            the reason
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, interestAdjustmentIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void disapproveInterestAdjustments(String approver, long[] interestAdjustmentIds, String reason)
        throws OPMException;

    /**
     * Gets the payment moves for the user.
     *
     * @param approver
     *            the name of user to get payment moves.
     * @param approvalStatus
     *            the approval status of the payment moves.
     *
     * @return List of payment moves, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, approvalStatus is null.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<PaymentMove> getPaymentMoves(String approver, ApprovalStatus approvalStatus) throws OPMException;

    /**
     * Approves payment moves.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param paymentMoveIds
     *            the ids of payment moves to approve.
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, paymentMoveIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void approvePaymentMoves(String approver, long[] paymentMoveIds) throws OPMException;

    /**
     * Disapproves payment moves.
     *
     * @param approver
     *            the name of the user performing operation.
     * @param paymentMoveIds
     *            the ids of payment moves to disapprove.
     * @param reason
     *            the reason
     *
     * @throws IllegalArgumentException
     *             if approver is null/empty, paymentMoveIds is null.
     * @throws EntityNotFoundException
     *             if there is any entity missing for any id.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public void disapprovePaymentMoves(String approver, long[] paymentMoveIds, String reason) throws OPMException;
}
