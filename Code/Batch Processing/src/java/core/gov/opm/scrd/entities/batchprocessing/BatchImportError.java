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
 * <p>Represents the type of the import error.</p>
 *
 * @author faeton, liuliquan
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

    /** Represents the error for invalid deposit date.*/
    CD_DATE_MUST_BE_VALID,

    /** Represents the error for invalid CD number.*/
    CD_NUMBER_MUST_BE_VALID,

    /** Represents the error for invalid block number.*/
    BLOCK_NUMBER_MUST_BE_VALID,

    /** Represents the error for invalid sequence number.*/
    SEQ_NUMBER_MUST_BE_VALID,

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

    /** Represents the EOF line.*/
    EOF_ERROR,

    /** Represents there is no error.*/
    NO_ERROR,

    /** Represents there is untrapped error.*/
    UNTRAPPED_ERROR;
}

