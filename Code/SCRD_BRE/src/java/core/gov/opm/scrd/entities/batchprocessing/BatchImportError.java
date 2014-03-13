/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.batchprocessing;

/**
 * <p>Represents the type of the import error.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public enum BatchImportError {

    /** Represents the error for invalid transaction code.*/
    TRANSACTION_CODE_MUST_BE_C_OR_R,

    /** Represents the error when field number is not in rang 01 to 10.*/
    FIELD_NO_MUST_BE_IN_RANGE_01_10,

    /** Represents the error for claim number is not 7 digits.*/
    CLAIM_NO_MUST_BE_7_DIGITS,

    /** Represents the error for invalid date of birth.*/
    DOB_MUST_BE_VALID,

    /** Represents the error for missing corrected data.*/
    CORRECTED_DATA_MUST_BE_PRESENT,

    /** Represents the error for none 7 digits' field 08.*/
    FIELD_08_CHANGE_IN_CSD_MUST_BE_7_DIGIT,

    /** Represents the error for invalid date of birth for field 09.*/
    FIELD_09_CHANGE_IN_DOB_MUST_BE_VALID_DATE,

    /** Represents the error for invalid ssn for field 10.*/
    FIELD_10_CHANGE_IN_SSN_MUST_BE_VALID_SSN,

    /** Represents the error for invalid r record.*/
    R_RECORD_MUST_BE_ACH_OR_CHECK,

    /** Represents the error for invalid amount.*/
    AMOUNT_MUST_BE_VALID_DECIMAL_NUMBER,

    /** Represents there is no error.*/
    NO_ERROR,

    /** Represents there is untrapped error.*/
    UNTRAPPED_ERROR;
}

