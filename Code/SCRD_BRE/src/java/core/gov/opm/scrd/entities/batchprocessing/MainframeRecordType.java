/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.batchprocessing;


/**
 * <p>Represents the type of the mainframe record.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public enum MainframeRecordType {

    /** Represents the valid "R" transaction type.*/
    VALID_R_TRANSACTION,

    /** Represents the valid "C" transaction type.*/
    VALID_C_TRANSACTION,

    /** Represents the valid "C" transaction type without any change.*/
    VALID_C_NO_CHANGE_OCCURRED,

    /** Represents the type of bad "R" transaction.*/
    BAD_R_TRANSACTION,

    /** Represents the type of bad "C" transaction.*/
    BAD_C_TRANSACTION,

    /** Represents the type of valid summing "R" transaction.*/
    SUM_R_VALID,

    /** Represents the type of corrupted summing "R" transaction.*/
    SUM_R_CORRUPT,

    /** Represents the type for not being a record.*/
    NOT_A_RECORD,

    /** Represents the type for duplicated record.*/
    DUPLICATE_RECORD,

    /** Represents the type for disallowed user.*/
    USER_NOT_ALLOWED;
}

