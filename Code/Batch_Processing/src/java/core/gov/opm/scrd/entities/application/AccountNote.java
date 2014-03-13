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
 * This is the class representing the notes associated with the account. There can be many notes associated with
 * account.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added fields: priority</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.1
 */
public class AccountNote extends IdentifiableEntity {
    /**
     * <p>
     * Represents the date when the note was created. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private Date date;
    /**
     * <p>
     * Represents the name of the user who created the note. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String writer;
    /**
     * <p>
     * Represents the content of the note. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String text;
    /**
     * <p>
     * Represents the account associated with the note. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private long accountId;
    /**
     * <p>
     * Represents the priority of the note. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private Integer priority;

    /**
     * Creates an instance of AccountNote.
     */
    public AccountNote() {
        // Empty
    }

    /**
     * Gets the date when the note was created.
     *
     * @return the date when the note was created.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date when the note was created.
     *
     * @param date
     *            the date when the note was created.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the name of the user who created the note.
     *
     * @return the name of the user who created the note.
     */
    public String getWriter() {
        return writer;
    }

    /**
     * Sets the name of the user who created the note.
     *
     * @param writer
     *            the name of the user who created the note.
     */
    public void setWriter(String writer) {
        this.writer = writer;
    }

    /**
     * Gets the content of the note.
     *
     * @return the content of the note.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the content of the note.
     *
     * @param text
     *            the content of the note.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the account associated with the note.
     *
     * @return the account associated with the note.
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * Sets the account associated with the note.
     *
     * @param accountId
     *            the account associated with the note.
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the priority of the note.
     *
     * @return the priority of the note.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the note.
     *
     * @param priority
     *            the priority of the note.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
