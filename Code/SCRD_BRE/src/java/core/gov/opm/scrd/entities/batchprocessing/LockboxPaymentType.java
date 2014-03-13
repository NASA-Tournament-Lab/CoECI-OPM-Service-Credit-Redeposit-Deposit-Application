/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.batchprocessing;

/**
 * <p>Represents the type of the lockbox payment.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public enum LockboxPaymentType {

    /** Represents the payment type check.*/
    CHECK,

    /** Represents the payment type ach.*/
    ACH,

    /** Represents the unknown payment type.*/
    UNKNOWN;
}

