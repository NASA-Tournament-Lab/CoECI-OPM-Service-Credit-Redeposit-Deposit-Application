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

package gov.opm.scrd.services;


/**
 * <p>
 * This exception indicates the account has zero balance.
 * </p>
 *
 * <p>
 * <b>Thread Safety:</b> This exception is not thread-safe because its super class is mutable.
 * </p>
 * @author albertwang, TCSASSEMBLER
 * @version 1.0
 * @since OPM Interest Update Assembly v1.0
 */
public class ZeroBalanceInAccountException extends OPMException {
    /**
     * Default serial ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception instance with this error message.
     *
     * @param message - the error message
     */
    public ZeroBalanceInAccountException(String message) {
        super(message);
    }

    /**
     * Creates a new exception instance with an error message and the given cause of error.
     *
     * @param message - the error message
     * @param cause - the cause of error
     */
    public ZeroBalanceInAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}

