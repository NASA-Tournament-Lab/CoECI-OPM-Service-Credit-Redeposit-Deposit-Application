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

