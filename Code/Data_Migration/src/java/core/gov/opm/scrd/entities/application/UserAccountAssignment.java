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
 * Represents the entity specifying assignment of accounts to user.
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
public class UserAccountAssignment extends IdentifiableEntity {
    /**
     * <p>
     * Represents the user assigned to account. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private User user;
    /**
     * <p>
     * Represents the assigned account. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Account account;
    /**
     * <p>
     * Represents the assignment date. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Date assignmentDate;

    /**
     * Creates an instance of UserAccountAssignment.
     */
    public UserAccountAssignment() {
        // Empty
    }

    /**
     * Gets the user assigned to account.
     *
     * @return the user assigned to account.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user assigned to account.
     *
     * @param user
     *            the user assigned to account.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the assigned account.
     *
     * @return the assigned account.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the assigned account.
     *
     * @param account
     *            the assigned account.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the assignment date.
     *
     * @return the assignment date.
     */
    public Date getAssignmentDate() {
        return assignmentDate;
    }

    /**
     * Sets the assignment date.
     *
     * @param assignmentDate
     *            the assignment date.
     */
    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
