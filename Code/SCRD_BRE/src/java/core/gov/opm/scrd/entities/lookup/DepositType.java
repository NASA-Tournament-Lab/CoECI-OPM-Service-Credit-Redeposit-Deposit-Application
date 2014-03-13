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

package gov.opm.scrd.entities.lookup;

/**
 * <p>
 * Represents the enumeration specifying the deposit type.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This enumeration is immutable and thread safe.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Interest Update Assembly 1.0):</em>
 * <ul>
 * <li>change names of enum and add new memebers.</li>
 * </ul>
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public enum DepositType {
    /**
     * Represents deposit type used after 10/82.
     */
    POST_10_82,

    /**
     * Represents deposit type used before 10/82. To be deleted
     */
    PRE_10_82,



    /**
     * Represents CSRS deposit type used after 10/82.
     *
     * It is renamed from POST_10_82. To be deleted
     */
    CSRS_POST_10_82_DEPOSIT,

    /**
     * Represents CSRS deposit type used before 10/82.
     *
     * It is renamed from PRE_10_82.
     */
    CSRS_PRE_10_82_DEPOSIT,

    /**
     * Represents the FERS Deposit type.
     *
     * @since OPM Interest Update Assembly 1.0
     */
    FERS_DEPOSIT,

    /**
     * Represents the FERS Redeposit type.
     *
     * @since OPM Interest Update Assembly 1.0
     */
    FERS_REDEPOSIT,

    /**
     * Represents the CSRS post-3/91 Redeposit type.
     *
     * @since OPM Interest Update Assembly 1.0
     */
    CSRS_POST_3_91_REDEPOSIT,

    /**
     * Represents the CSRS post-82/pre-91 Redeposit type.
     *
     * @since OPM Interest Update Assembly 1.0
     */
    CSRS_POST_82_PRE_91_REDEPOSIT,

    /**
     * Represents the CSRS post-10/82 Redeposit type.
     *
     * @since OPM Interest Update Assembly 1.0
     */
    CSRS_PRE_10_82_REDEPOSIT,

    /**
     * Represents the FERS Peace Corps type.
     *
     * @since OPM Interest Update Assembly 1.0
     */
    FERS_PEACE_CORPS,

    /**
     * Represents the CSRS Peace Corps type.
     *
     * @since OPM Interest Update Assembly 1.0
     */
    CSRS_PEACE_CORPS
}
