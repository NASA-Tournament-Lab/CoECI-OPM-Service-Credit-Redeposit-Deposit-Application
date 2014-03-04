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
 * <p>Represents the type of the lookup payment application order.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public enum LookUpPaymentApplicationOrder {

    /** Represents the default user order.*/
    DEFAULT_USER_ORDER,

    /** Represents the fers order.*/
    FERS,

    /** Represents the order of post 982 redeposit.*/
    POST_9_82_REDEPOSIT,

    /** Represents the order of pre 1082 redeposit.*/
    PRE_10_82_REDEPOSIT,

    /** Represents the order of post 982 deposit.*/
    POST_9_82_DEPOSIT,

    /** Represents the order of pre 1082 deposit.*/
    PRE_10_82_DEPOSIT;
}

