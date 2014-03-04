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

/**
 * <p>
 * Represents the entity specifying notes for payment transactions.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class PaymentTransactionNote extends IdentifiableEntity {
    /**
     * <p>
     * Represents the transaction key of note. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Long payTransactionKey;
    /**
     * <p>
     * Represents the note text. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String note;

    /**
     * Creates an instance of PaymentTransactionNote.
     */
    public PaymentTransactionNote() {
        // Empty
    }

    /**
     * Gets the transaction key of note.
     *
     * @return the transaction key of note.
     */
    public Long getPayTransactionKey() {
        return payTransactionKey;
    }

    /**
     * Sets the transaction key of note.
     *
     * @param payTransactionKey
     *            the transaction key of note.
     */
    public void setPayTransactionKey(Long payTransactionKey) {
        this.payTransactionKey = payTransactionKey;
    }

    /**
     * Gets the note text.
     *
     * @return the note text.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note text.
     *
     * @param note
     *            the note text.
     */
    public void setNote(String note) {
        this.note = note;
    }
}
