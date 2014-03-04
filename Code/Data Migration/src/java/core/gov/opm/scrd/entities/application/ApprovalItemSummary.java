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

package gov.opm.scrd.entities.application;

import gov.opm.scrd.entities.common.Helper;

/**
 * <p>
 * This is the class representing the summary of the payments in the application. This entity is not persisted. It is
 * used only to aggregate results in getApprovalSummary() method of ApprovalService.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class ApprovalItemSummary {
    /**
     * <p>
     * Represents the total number of pending payments of summary. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private int pendingPaymentCount;
    /**
     * <p>
     * Represents the total number of interest adjustments of summary. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private int interestAdjustmentCount;
    /**
     * <p>
     * Represents the total number payment of payment moves of summary. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private int paymentMoveCount;
    /**
     * <p>
     * Represents the total number of payments of summary. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private int totalCount;

    /**
     * Creates an instance of ApprovalItemSummary.
     */
    public ApprovalItemSummary() {
        // Empty
    }

    /**
     * Gets the total number of pending payments of summary.
     *
     * @return the total number of pending payments of summary.
     */
    public int getPendingPaymentCount() {
        return pendingPaymentCount;
    }

    /**
     * Sets the total number of pending payments of summary.
     *
     * @param pendingPaymentCount
     *            the total number of pending payments of summary.
     */
    public void setPendingPaymentCount(int pendingPaymentCount) {
        this.pendingPaymentCount = pendingPaymentCount;
    }

    /**
     * Gets the total number of interest adjustments of summary.
     *
     * @return the total number of interest adjustments of summary.
     */
    public int getInterestAdjustmentCount() {
        return interestAdjustmentCount;
    }

    /**
     * Sets the total number of interest adjustments of summary.
     *
     * @param interestAdjustmentCount
     *            the total number of interest adjustments of summary.
     */
    public void setInterestAdjustmentCount(int interestAdjustmentCount) {
        this.interestAdjustmentCount = interestAdjustmentCount;
    }

    /**
     * Gets the total number payment of payment moves of summary.
     *
     * @return the total number payment of payment moves of summary.
     */
    public int getPaymentMoveCount() {
        return paymentMoveCount;
    }

    /**
     * Sets the total number payment of payment moves of summary.
     *
     * @param paymentMoveCount
     *            the total number payment of payment moves of summary.
     */
    public void setPaymentMoveCount(int paymentMoveCount) {
        this.paymentMoveCount = paymentMoveCount;
    }

    /**
     * Gets the total number of payments of summary.
     *
     * @return the total number of payments of summary.
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Sets the total number of payments of summary.
     *
     * @param totalCount
     *            the total number of payments of summary.
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Converts the entity to a string.
     *
     * @return the string with entity data.
     */
    @Override
    public String toString() {
        return Helper.toString(this);
    }
}
