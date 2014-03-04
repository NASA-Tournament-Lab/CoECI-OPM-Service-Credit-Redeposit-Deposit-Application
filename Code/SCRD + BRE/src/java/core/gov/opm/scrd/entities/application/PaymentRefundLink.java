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

/**
 * <p>
 * Represents the entity specifying payment refund link.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class PaymentRefundLink extends IdentifiableEntity {
    /**
     * <p>
     * Represents the id of payment of link. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Long paymentNeedingRefund;
    /**
     * <p>
     * Represents the id of refund transaction of link. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private Long refundForPayment;

    /**
     * Creates an instance of PaymentRefundLink.
     */
    public PaymentRefundLink() {
        // Empty
    }

    /**
     * Gets the id of payment of link.
     *
     * @return the id of payment of link.
     */
    public Long getPaymentNeedingRefund() {
        return paymentNeedingRefund;
    }

    /**
     * Sets the id of payment of link.
     *
     * @param paymentNeedingRefund
     *            the id of payment of link.
     */
    public void setPaymentNeedingRefund(Long paymentNeedingRefund) {
        this.paymentNeedingRefund = paymentNeedingRefund;
    }

    /**
     * Gets the id of refund transaction of link.
     *
     * @return the id of refund transaction of link.
     */
    public Long getRefundForPayment() {
        return refundForPayment;
    }

    /**
     * Sets the id of refund transaction of link.
     *
     * @param refundForPayment
     *            the id of refund transaction of link.
     */
    public void setRefundForPayment(Long refundForPayment) {
        this.refundForPayment = refundForPayment;
    }
}
