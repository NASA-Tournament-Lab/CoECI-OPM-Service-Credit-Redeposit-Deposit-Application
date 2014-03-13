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

package gov.opm.scrd.web.controllers;

import java.util.List;

/**
 * <p>
 * This exception is thrown when there is any problem validating user request.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Exception is not thread safe because its base class is not thread safe.
 * </p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
public class ValidationException extends Exception {
    /**
     * <p>
     * The serial version id.
     * </p>
     */
    private static final long serialVersionUID = -6138773752101923496L;

    /**
     * Represents the validation error messages. Can not be null, contain null elements. It is initialized in
     * constructor and never changed afterwards.
     */
    private final List<String> details;

    /**
     * Constructor with message and details parameter.
     *
     * @param message
     *            the exception message
     * @param details
     *            the validation error messages.
     */
    public ValidationException(String message, List<String> details) {
        super(message);
        this.details = details;
    }

    /**
     * Gets the validation error messages.
     *
     * @return the validation error messages.
     */
    public List<String> getDetails() {
        return details;
    }
}
