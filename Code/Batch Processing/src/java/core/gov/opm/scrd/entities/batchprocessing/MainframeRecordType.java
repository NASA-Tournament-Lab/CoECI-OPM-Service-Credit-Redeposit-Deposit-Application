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

    /** Represents the "C" transaction type without matching account.*/
    NOT_MATCH_C_TRANSACTION,

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
    //USER_NOT_ALLOWED;
}

