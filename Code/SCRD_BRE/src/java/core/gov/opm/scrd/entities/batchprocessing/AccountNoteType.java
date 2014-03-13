/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.batchprocessing;

/**
 * <p>Represents the type of the account note.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public enum AccountNoteType {

    /** Represents the note type for ach over payment. */
    ACH_OVER_PAYMENT,

    /** Represents the note type for credit balance. */
    CREDIT_BALANCE,

    /** Represents the note type for refund memo. */
    REFUND_MEMO,

    /** Represents the note type for stop ach letter. */
    STOP_ACH_LETTER,

    /** Represents the note type for payment is before initial bill. */
    PAYMENT_BEFORE_INITIAL_BILL,

    /** Represents the note type for account is set to history. */
    ACCOUNT_SET_TO_HISTORY;
}

