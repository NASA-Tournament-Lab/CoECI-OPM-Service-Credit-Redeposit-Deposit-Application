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

