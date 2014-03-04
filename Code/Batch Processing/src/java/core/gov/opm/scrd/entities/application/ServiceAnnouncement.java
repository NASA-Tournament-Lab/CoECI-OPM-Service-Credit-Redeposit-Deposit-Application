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
 * This is the class representing the possible service announcements used in the application. Such announcements can be
 * dedicated to a specific user or a group of users (notification) or general system information(error, info).
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class ServiceAnnouncement extends IdentifiableEntity {
    /**
     * <p>
     * Represents the date of the announcement. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date date;
    /**
     * <p>
     * Represents the text with details of the announcement. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String details;

    /**
     * Creates an instance of ServiceAnnouncement.
     */
    public ServiceAnnouncement() {
        // Empty
    }

    /**
     * Gets the date of the announcement.
     *
     * @return the date of the announcement.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the announcement.
     *
     * @param date
     *            the date of the announcement.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the text with details of the announcement.
     *
     * @return the text with details of the announcement.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the text with details of the announcement.
     *
     * @param details
     *            the text with details of the announcement.
     */
    public void setDetails(String details) {
        this.details = details;
    }
}
