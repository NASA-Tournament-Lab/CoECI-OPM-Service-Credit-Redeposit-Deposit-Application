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

import gov.opm.scrd.entities.common.BasicPagedSearchFilter;
import gov.opm.scrd.entities.lookup.Role;

/**
 * <p>
 * Represents search filter used for searching notifications.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class NotificationSearchFilter extends BasicPagedSearchFilter {
    /**
     * <p>
     * Represents the name of the user that is the sender of notifications in filter. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String sender;
    /**
     * <p>
     * Represents the flag specifying whether notification is read in filter. If it is read, the flag is set to true and
     * otherwise it is false and null if it can be any value. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Boolean read;
    /**
     * <p>
     * Represents the name of the user that is the recipient of this notification in filter. It is managed with a getter
     * and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String recipient;
    /**
     * <p>
     * Represents the role of all users that are recipients of this notification in filter. It is managed with a getter
     * and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Role recipientRole;

    /**
     * Creates an instance of NotificationSearchFilter.
     */
    public NotificationSearchFilter() {
        // Empty
    }

    /**
     * Gets the name of the user that is the sender of notifications in filter.
     *
     * @return the name of the user that is the sender of notifications in filter.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the name of the user that is the sender of notifications in filter.
     *
     * @param sender
     *            the name of the user that is the sender of notifications in filter.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Gets the flag specifying whether notification is read in filter.
     *
     * @return the flag specifying whether notification is read in filter.
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * Sets the flag specifying whether notification is read in filter.
     *
     * @param read
     *            the flag specifying whether notification is read in filter.
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     * Gets the name of the user that is the recipient of this notification in filter.
     *
     * @return the name of the user that is the recipient of this notification in filter.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets the name of the user that is the recipient of this notification in filter.
     *
     * @param recipient
     *            the name of the user that is the recipient of this notification in filter.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Gets the role of all users that are recipients of this notification in filter.
     *
     * @return the role of all users that are recipients of this notification in filter.
     */
    public Role getRecipientRole() {
        return recipientRole;
    }

    /**
     * Sets the role of all users that are recipients of this notification in filter.
     *
     * @param recipientRole
     *            the role of all users that are recipients of this notification in filter.
     */
    public void setRecipientRole(Role recipientRole) {
        this.recipientRole = recipientRole;
    }
}
