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
 * This exception is thrown by application methods if an entity for some id was not found.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Exception is not thread safe because its base class is not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class EntityNotFoundException extends OPMException {
    /**
     * <p>
     * The serial version id.
     * </p>
     */
    private static final long serialVersionUID = 8429655244321454930L;

    /**
     * <p>
     * Constructs a new <code>EntityNotFoundException</code> instance.
     * </p>
     */
    public EntityNotFoundException() {
        // empty
    }

    /**
     * <p>
     * Constructs a new <code>EntityNotFoundException</code> instance with error message.
     * </p>
     *
     * @param message
     *            the error message.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * <p>
     * Constructs a new <code>EntityNotFoundException</code> instance with inner cause.
     * </p>
     *
     * @param cause
     *            the inner cause.
     */
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * <p>
     * Constructs a new <code>EntityNotFoundException</code> instance with error message and inner cause.
     * </p>
     *
     * @param message
     *            the error message.
     * @param cause
     *            the inner cause.
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
