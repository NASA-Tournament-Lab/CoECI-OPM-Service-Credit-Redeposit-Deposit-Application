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

package gov.opm.scrd.entities.batchprocessing;

/**
 * The Enum PaymentTransactionCodes.
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public enum PaymentTransactionCodes {
    /** Represents the code for pending status. */
    PENDING(0),
    /** Represents the code for accepted status. */
    ACCEPTED(1),
    /** Represents the code for unresolved status. */
    UNRESOLVED(2),
    /** Represents the code for suspended status. */
    SUSPENDED(3),
    /** Represents the code for posted pending status. */
    POSTED_PENDING(10),
    /** Represents the code for posted complete status. */
    POSTED_COMPLETE(11),
    /** Represents the code for reverse pending status. */
    REVERSE_PENDING(12),
    /** Represents the code for reverse complete status. */
    REVERSE_COMPLETE(13),
    /** Represents the code for prior payment record status. */
    PRIOR_PAYMENT_RECORD(15),
    /** Represents the code for annuity pending status. */
    ANNUITY_PENDING(18),
    /** Represents the code for annuity complete status. */
    ANNUITY_COMPLETE(19),
    /** Represents the code for voluntary contributions pending status. */
    VOLUNTARY_CONTRIBUTIONS_PENDING(20),
    /** Represents the code for voluntary contributions complete status. */
    VOLUNTARY_CONTRIBUTIONS_COMPLETE(21),
    /** Represents the code for direct pay life pending status. */
    DIRECT_PAY_LIFE_PENDING(22),
    /** Represents the code for direct pay life complete status. */
    DIRECT_PAY_LIFE_COMPLETE(23),
    /** Represents the code for suspense refund pending status. */
    SUSPENSE_REFUND_PENDING(24),
    /** Represents the code for suspense refund complete status. */
    SUSPENSE_REFUND_COMPLETE(25),
    /** Represents the code for debit voucher pending status. */
    DEBIT_VOUCHER_PENDING(26),
    /** Represents the code for debit voucher complete status. */
    DEBIT_VOUCHER_COMPLETE(27),
    /** Represents the code for reversed pending approval status. */
    REVERSED_PENDING_APPROVAL(30),
    /** Represents the code for posted pending approval status. */
    POSTED_PENDING_APPROVAL(31),
    /** Represents the code for voluntary contributions pending approval status. */
    VOLUNTARY_CONTRIBUTIONS_PENDING_APPROVAL(32),
    /** Represents the code for direct pay life pending approval status. */
    DIRECT_PAY_LIFE_PENDING_APPROVAL(33),
    /** Represents the code for suspense refund pending approval status. */
    SUSPENSE_REFUND_PENDING_APPROVAL(34),
    /** Represents the code for annuity pending approval status. */
    ANNUITY_PENDING_APPROVAL(35),
    /** Represents the code for debit voucher pending approval status. */
    DEBIT_VOUCHER_PENDING_APPROVAL(36),
    /** Represents the code for health benefits pending approval status. */
    HEALTH_BENEFITS_PENDING_APPROVAL(37),
    /** Represents the code for adjustment pending approval status. */
    ADJUSTMENT_PENDING_APPROVAL(38),
    /** Represents the code for write off pending approval status. */
    WRITE_OFF_PENDING_APPROVAL(39),
    /** Represents the code for health benefits pending status. */
    HEALTH_BENEFITS_PENDING(40),
    /** Represents the code for health benefits complete status. */
    HEALTH_BENEFITS_COMPLETE(41),
    /** Represents the code for resetto original pending status. */
    RESETTO_ORIGINAL_PENDING(50),
    /** Represents the code for batch auto refund status. */
    BATCH_AUTO_REFUND(51),
    /** Represents the code for transferred amount status. */
    TRANSFERRED_AMOUNT(52),
    /** Represents the code for write off pending status. */
    WRITE_OFF_PENDING(53),
    /** Represents the code for adjustment complete status. */
    ADJUSTMENT_COMPLETE(54),
    /** Represents the code for batch auto refund cancelled status. */
    BATCH_AUTO_REFUND_CANCELLED(55),
    /** Represents the code for write off complete status. */
    WRITE_OFF_COMPLETE(58),
    /** Represents the code for credit balance refund pending approval status. */
    CREDIT_BALANCE_REFUND_PENDING_APPROVAL(59),
    /** Represents the code for credit balance refund pending status. */
    CREDIT_BALANCE_REFUND_PENDING(60),
    /** Represents the code for credit balance refund complete status. */
    CREDIT_BALANCE_REFUND_COMPLETE(61),
    /** Represents the code for manual adjustment cancelled status. */
    MANUAL_ADJUSTMENT_CANCELLED(62),
    /**
     * Represents the code for batch auto refund cancelled pending approval
     * status.
     */
    BATCH_AUTO_REFUND_CANCELLED_PENDING_APPROVAL(63),
    /** Represents the code for batch auto refund cancelled pending status. */
    BATCH_AUTO_REFUND_CANCELLED_PENDING(64),
    /** Represents the code for manual adjustment cancelled pending status. */
    MANUAL_ADJUSTMENT_CANCELLED_PENDING(65),
    /**
     * Represents the code for manual adjustment cancelled pending approval
     * status.
     */
    MANUAL_ADJUSTMENT_CANCELLED_PENDING_APPROVAL(66),
    /** Represents the code for adjustment pending status. */
    ADJUSTMENT_PENDING(67),
    /** Represents the code for batch auto refund pending approval status. */
    BATCH_AUTO_REFUND_PENDING_APPROVAL(68),
    /** Represents the code for batch auto refund pending status. */
    BATCH_AUTO_REFUND_PENDING(69),
    /** Represents the code for lockbox bank error status. */
    LOCKBOX_BANK_ERROR(70),
    /** Represents the code for suspense debit voucher pending approval status. */
    SUSPENSE_DEBIT_VOUCHER_PENDING_APPROVAL(71),
    /** Represents the code for suspense debit voucher pending status. */
    SUSPENSE_DEBIT_VOUCHER_PENDING(72),
    /** Represents the code for suspense debit voucher status. */
    SUSPENSE_DEBIT_VOUCHER(73);
    /** Represents the code. */
    private final long code;

    /**
     * Instantiates a new payment transaction codes.
     *
     * @param code
     *            the code
     */
    private PaymentTransactionCodes(long code) {
        this.code = code;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public long getCode() {
        return code;
    }
}
