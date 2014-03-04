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

import gov.opm.scrd.entities.common.IdentifiableEntity;
import gov.opm.scrd.entities.lookup.TransferType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This is the class representing the information about the payment refund, which should be done once payment is reset.
 * It is created and persisted in reset() method of SuspensionService.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class RefundTransaction extends IdentifiableEntity {
    /**
     * <p>
     * Represents the transaction key of refund transaction. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String transactionKey;
    /**
     * <p>
     * Represents the amount of refund transaction. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private BigDecimal amount;
    /**
     * <p>
     * Represents the claim number of refund transaction. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private String claimNumber;
    /**
     * <p>
     * Represents the timestamp of refund transaction. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private Date refundDate;
    /**
     * <p>
     * Represents the name of the user who requested refund transaction. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private String refundUsername;
    /**
     * <p>
     * Represents the transfer type of refund transaction. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private TransferType transferType;

    /**
     * Creates an instance of RefundTransaction.
     */
    public RefundTransaction() {
        // Empty
    }

    /**
     * Gets the transaction key of refund transaction.
     *
     * @return the transaction key of refund transaction.
     */
    public String getTransactionKey() {
        return transactionKey;
    }

    /**
     * Sets the transaction key of refund transaction.
     *
     * @param transactionKey
     *            the transaction key of refund transaction.
     */
    public void setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
    }

    /**
     * Gets the amount of refund transaction.
     *
     * @return the amount of refund transaction.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of refund transaction.
     *
     * @param amount
     *            the amount of refund transaction.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the claim number of refund transaction.
     *
     * @return the claim number of refund transaction.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number of refund transaction.
     *
     * @param claimNumber
     *            the claim number of refund transaction.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the timestamp of refund transaction.
     *
     * @return the timestamp of refund transaction.
     */
    public Date getRefundDate() {
        return refundDate;
    }

    /**
     * Sets the timestamp of refund transaction.
     *
     * @param refundDate
     *            the timestamp of refund transaction.
     */
    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    /**
     * Gets the name of the user who requested refund transaction.
     *
     * @return the name of the user who requested refund transaction.
     */
    public String getRefundUsername() {
        return refundUsername;
    }

    /**
     * Sets the name of the user who requested refund transaction.
     *
     * @param refundUsername
     *            the name of the user who requested refund transaction.
     */
    public void setRefundUsername(String refundUsername) {
        this.refundUsername = refundUsername;
    }

    /**
     * Gets the transfer type of refund transaction.
     *
     * @return the transfer type of refund transaction.
     */
    public TransferType getTransferType() {
        return transferType;
    }

    /**
     * Sets the transfer type of refund transaction.
     *
     * @param transferType
     *            the transfer type of refund transaction.
     */
    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }
}
