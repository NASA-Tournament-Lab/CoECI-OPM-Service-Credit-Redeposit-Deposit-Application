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

package gov.opm.scrd.entities.application;

import gov.opm.scrd.entities.common.IdentifiableEntity;
import java.util.Date;

/**
 * <p>
 * This is the class representing the payment statement print of nightly batch processing job.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, bannie2492
 * @version 1.0
 */
public class PaymentStatementPrint extends IdentifiableEntity {

    /**
     * Represents the message content of print. it is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     */
    private String message;
    /**
     * Represents the date when print was generated. it is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     */
    private Date date;

    /**
     * Creates an instance of PaymentStatementPrint.
     */
    public PaymentStatementPrint() {
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message
     *            the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date
     *            the date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
