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

package gov.opm.scrd.entities.lookup;

/**
 * <p>
 * Represents the enumeration specifying the type of the payment. This enumeration is used to differentiate the payments
 * and use proper payment view when showing the item.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This enumeration is immutable and thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public enum PaymentType {
    /**
     * Represents ordinary payment status. Payment with such status will be returned as Payment entity.
     */
    ORDINARY,

    /**
     * Represents suspended payment status. Payment with such status will be returned as SuspendedPayment view of the
     * Payment entity.
     */
    SUSPENDED_PAYMENT,

    /**
     * Represents payment move status. Payment with such status will be returned as PaymentMove view of the Payment
     * entity.
     */
    PAYMENT_MOVE,

    /**
     * Represents interest adjustment payment status. Payment with such status will be returned as InterestAdjustment
     * view of the Payment entity.
     */
    INTEREST_ADJUSTMENT,

    /**
     * Represents pending payment status. Payment with such status will be returned as PendingPayment view of the
     * Payment entity.
     */
    PENDING_PAYMENT
}
