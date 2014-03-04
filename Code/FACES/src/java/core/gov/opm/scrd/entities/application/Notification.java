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

import gov.opm.scrd.entities.lookup.Role;

import java.util.List;

/**
 * <p>
 * This is the class representing the notifications dedicated to a specific user or a group of users in the application.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class Notification extends ServiceAnnouncement {
    /**
     * <p>
     * Represents the name of the user that is the sender of this notification. It is managed with a getter and setter.
     * It may have any value. It is fully mutable.
     * </p>
     */
    private String sender;
    /**
     * <p>
     * Represents the flag specifying whether notification is read. If it is read, the flag is set to true and otherwise
     * it is false. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean read;
    /**
     * <p>
     * Represents the name of the user that is the recipient of this notification. Can be null if the notification if
     * notification is sent to a group of users based on their role. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private String recipient;
    /**
     * <p>
     * Represents the role of all users that are recipients of this notification. Can be null if the notification is
     * sent to a single user. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Role role;
    /**
     * <p>
     * Represents the list of user names, which have read this notification. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private List<String> readBy;

    /**
     * Creates an instance of Notification.
     */
    public Notification() {
        // Empty
    }

    /**
     * Gets the name of the user that is the sender of this notification.
     *
     * @return the name of the user that is the sender of this notification.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the name of the user that is the sender of this notification.
     *
     * @param sender
     *            the name of the user that is the sender of this notification.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Gets the flag specifying whether notification is read.
     *
     * @return the flag specifying whether notification is read.
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Sets the flag specifying whether notification is read.
     *
     * @param read
     *            the flag specifying whether notification is read.
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * Gets the name of the user that is the recipient of this notification.
     *
     * @return the name of the user that is the recipient of this notification.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets the name of the user that is the recipient of this notification.
     *
     * @param recipient
     *            the name of the user that is the recipient of this notification.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Gets the role of all users that are recipients of this notification.
     *
     * @return the role of all users that are recipients of this notification.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of all users that are recipients of this notification.
     *
     * @param role
     *            the role of all users that are recipients of this notification.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the list of user names, which have read this notification.
     *
     * @return the list of user names, which have read this notification.
     */
    public List<String> getReadBy() {
        return readBy;
    }

    /**
     * Sets the list of user names, which have read this notification.
     *
     * @param readBy
     *            the list of user names, which have read this notification.
     */
    public void setReadBy(List<String> readBy) {
        this.readBy = readBy;
    }
}
