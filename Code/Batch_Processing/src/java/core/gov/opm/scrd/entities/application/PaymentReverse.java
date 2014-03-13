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
import gov.opm.scrd.entities.lookup.PaymentReversalReason;

/**
 * <p>
 * This is the class representing the information about the payment reversal. It is created and persisted in reverse()
 * method of PaymentService.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class PaymentReverse extends IdentifiableEntity {
    /**
     * <p>
     * Represents the id of payment of payment reverse. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private long paymentId;
    /**
     * <p>
     * Represents the reason of payment reverse. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private PaymentReversalReason reason;
    /**
     * <p>
     * Represents the flag specifying whether reverse should be applied to GL file. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean applyToGL;
    /**
     * <p>
     * Represents the name of the user performed payment reverse. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private String reverser;

    /**
     * Creates an instance of PaymentReverse.
     */
    public PaymentReverse() {
        // Empty
    }

    /**
     * Gets the id of payment of payment reverse.
     *
     * @return the id of payment of payment reverse.
     */
    public long getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the id of payment of payment reverse.
     *
     * @param paymentId
     *            the id of payment of payment reverse.
     */
    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Gets the reason of payment reverse.
     *
     * @return the reason of payment reverse.
     */
    public PaymentReversalReason getReason() {
        return reason;
    }

    /**
     * Sets the reason of payment reverse.
     *
     * @param reason
     *            the reason of payment reverse.
     */
    public void setReason(PaymentReversalReason reason) {
        this.reason = reason;
    }

    /**
     * Gets the flag specifying whether reverse should be applied to GL file.
     *
     * @return the flag specifying whether reverse should be applied to GL file.
     */
    public boolean isApplyToGL() {
        return applyToGL;
    }

    /**
     * Sets the flag specifying whether reverse should be applied to GL file.
     *
     * @param applyToGL
     *            the flag specifying whether reverse should be applied to GL file.
     */
    public void setApplyToGL(boolean applyToGL) {
        this.applyToGL = applyToGL;
    }

    /**
     * Gets the name of the user performed payment reverse.
     *
     * @return the name of the user performed payment reverse.
     */
    public String getReverser() {
        return reverser;
    }

    /**
     * Sets the name of the user performed payment reverse.
     *
     * @param reverser
     *            the name of the user performed payment reverse.
     */
    public void setReverser(String reverser) {
        this.reverser = reverser;
    }
}
