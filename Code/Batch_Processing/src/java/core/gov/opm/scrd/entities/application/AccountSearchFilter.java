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

import java.util.Date;

/**
 * <p>
 * Represents search filter used for searching accounts.
 * </p>
 *
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Changed to extend BasicPagedSearchFilter.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class AccountSearchFilter extends BasicPagedSearchFilter {
    /**
     * <p>
     * Represents the claim number of the account in filter. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String claimNumber;
    /**
     * <p>
     * Represents the ssn of the account holder of account in filter. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private String ssn;
    /**
     * <p>
     * Represents the first name of the account holder of account in filter. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private String firstName;
    /**
     * <p>
     * Represents the middle name of the account holder of account in filter. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private String middleName;
    /**
     * <p>
     * Represents the last name of the account holder of account in filter. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private String lastName;
    /**
     * <p>
     * Represents the birth date of the account holder of account in filter. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private Date birthDate;
    /**
     * <p>
     * Represents the flag specifying whether payment history should be excluded when account is searched. It is managed
     * with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean excludeHistory;
    /**
     * <p>
     * Represents the flag specifying whether account is assigned to the claim officer. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Boolean assigned;

    /**
     * Creates an instance of AccountSearchFilter.
     */
    public AccountSearchFilter() {
        // Empty
    }

    /**
     * Gets the claim number of the account in filter.
     *
     * @return the claim number of the account in filter.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number of the account in filter.
     *
     * @param claimNumber
     *            the claim number of the account in filter.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the ssn of the account holder of account in filter.
     *
     * @return the ssn of the account holder of account in filter.
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the ssn of the account holder of account in filter.
     *
     * @param ssn
     *            the ssn of the account holder of account in filter.
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * Gets the first name of the account holder of account in filter.
     *
     * @return the first name of the account holder of account in filter.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the account holder of account in filter.
     *
     * @param firstName
     *            the first name of the account holder of account in filter.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the middle name of the account holder of account in filter.
     *
     * @return the middle name of the account holder of account in filter.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name of the account holder of account in filter.
     *
     * @param middleName
     *            the middle name of the account holder of account in filter.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the last name of the account holder of account in filter.
     *
     * @return the last name of the account holder of account in filter.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the account holder of account in filter.
     *
     * @param lastName
     *            the last name of the account holder of account in filter.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the birth date of the account holder of account in filter.
     *
     * @return the birth date of the account holder of account in filter.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of the account holder of account in filter.
     *
     * @param birthDate
     *            the birth date of the account holder of account in filter.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the flag specifying whether payment history should be excluded when account is searched.
     *
     * @return the flag specifying whether payment history should be excluded when account is searched.
     */
    public boolean isExcludeHistory() {
        return excludeHistory;
    }

    /**
     * Sets the flag specifying whether payment history should be excluded when account is searched.
     *
     * @param excludeHistory
     *            the flag specifying whether payment history should be excluded when account is searched.
     */
    public void setExcludeHistory(boolean excludeHistory) {
        this.excludeHistory = excludeHistory;
    }

    /**
     * Gets the flag specifying whether account is assigned to the claim officer.
     *
     * @return the flag specifying whether account is assigned to the claim officer.
     */
    public Boolean getAssigned() {
        return assigned;
    }

    /**
     * Sets the flag specifying whether account is assigned to the claim officer.
     *
     * @param assigned
     *            the flag specifying whether account is assigned to the claim officer.
     */
    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }
}
