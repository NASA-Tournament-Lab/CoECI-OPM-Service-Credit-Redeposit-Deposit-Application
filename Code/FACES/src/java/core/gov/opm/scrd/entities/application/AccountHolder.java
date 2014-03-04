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
import gov.opm.scrd.entities.lookup.State;
import gov.opm.scrd.entities.lookup.Suffix;

import java.util.Date;

/**
 * <p>
 * This is the class representing the person holding the account.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added fields: position, agencyCode</li>
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
public class AccountHolder extends IdentifiableEntity {
    /**
     * <p>
     * Represents the last name of account holder. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String lastName;
    /**
     * <p>
     * Represents the first name of account holder. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String firstName;
    /**
     * <p>
     * Represents the middle initial of account holder. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private String middleInitial;
    /**
     * <p>
     * Represents the suffix (Mr., Ms.) of the account holder. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Suffix suffix;
    /**
     * <p>
     * Represents the birth date of account holder. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date birthDate;
    /**
     * <p>
     * Represents the social security number of account holder. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String ssn;
    /**
     * <p>
     * Represents the telephone of account holder. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String telephone;
    /**
     * <p>
     * Represents the email of account holder. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String email;
    /**
     * <p>
     * Represents the title of account holder. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String title;
    /**
     * <p>
     * Represents the department code of account holder. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private String departmentCode;
    /**
     * <p>
     * Represents the address data of account holder. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private Address address;
    /**
     * <p>
     * Represents the geographical code of account holder. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String geoCode;
    /**
     * <p>
     * Represents the city of employment of account holder. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private String cityOfEmployment;
    /**
     * <p>
     * Represents the state of employment of account holder. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private State stateOfEmployment;
    /**
     * <p>
     * Represents the position of account holder. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String position;
    /**
     * <p>
     * Represents the agency code of account holder. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    private String agencyCode;

    /**
     * Creates an instance of AccountHolder.
     */
    public AccountHolder() {
        // Empty
    }

    /**
     * Gets the last name of account holder.
     *
     * @return the last name of account holder.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of account holder.
     *
     * @param lastName
     *            the last name of account holder.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first name of account holder.
     *
     * @return the first name of account holder.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of account holder.
     *
     * @param firstName
     *            the first name of account holder.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the middle initial of account holder.
     *
     * @return the middle initial of account holder.
     */
    public String getMiddleInitial() {
        return middleInitial;
    }

    /**
     * Sets the middle initial of account holder.
     *
     * @param middleInitial
     *            the middle initial of account holder.
     */
    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    /**
     * Gets the suffix (Mr.
     *
     * @return the suffix (Mr.
     */
    public Suffix getSuffix() {
        return suffix;
    }

    /**
     * Sets the suffix (Mr.
     *
     * @param suffix
     *            the suffix (Mr.
     */
    public void setSuffix(Suffix suffix) {
        this.suffix = suffix;
    }

    /**
     * Gets the birth date of account holder.
     *
     * @return the birth date of account holder.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of account holder.
     *
     * @param birthDate
     *            the birth date of account holder.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the social security number of account holder.
     *
     * @return the social security number of account holder.
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the social security number of account holder.
     *
     * @param ssn
     *            the social security number of account holder.
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * Gets the telephone of account holder.
     *
     * @return the telephone of account holder.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the telephone of account holder.
     *
     * @param telephone
     *            the telephone of account holder.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Gets the email of account holder.
     *
     * @return the email of account holder.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of account holder.
     *
     * @param email
     *            the email of account holder.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the title of account holder.
     *
     * @return the title of account holder.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of account holder.
     *
     * @param title
     *            the title of account holder.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the department code of account holder.
     *
     * @return the department code of account holder.
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * Sets the department code of account holder.
     *
     * @param departmentCode
     *            the department code of account holder.
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * Gets the address data of account holder.
     *
     * @return the address data of account holder.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address data of account holder.
     *
     * @param address
     *            the address data of account holder.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the geographical code of account holder.
     *
     * @return the geographical code of account holder.
     */
    public String getGeoCode() {
        return geoCode;
    }

    /**
     * Sets the geographical code of account holder.
     *
     * @param geoCode
     *            the geographical code of account holder.
     */
    public void setGeoCode(String geoCode) {
        this.geoCode = geoCode;
    }

    /**
     * Gets the city of employment of account holder.
     *
     * @return the city of employment of account holder.
     */
    public String getCityOfEmployment() {
        return cityOfEmployment;
    }

    /**
     * Sets the city of employment of account holder.
     *
     * @param cityOfEmployment
     *            the city of employment of account holder.
     */
    public void setCityOfEmployment(String cityOfEmployment) {
        this.cityOfEmployment = cityOfEmployment;
    }

    /**
     * Gets the state of employment of account holder.
     *
     * @return the state of employment of account holder.
     */
    public State getStateOfEmployment() {
        return stateOfEmployment;
    }

    /**
     * Sets the state of employment of account holder.
     *
     * @param stateOfEmployment
     *            the state of employment of account holder.
     */
    public void setStateOfEmployment(State stateOfEmployment) {
        this.stateOfEmployment = stateOfEmployment;
    }

    /**
     * Gets the position of account holder.
     *
     * @return the position of account holder.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the position of account holder.
     *
     * @param position
     *            the position of account holder.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets the agency code of account holder.
     *
     * @return the agency code of account holder.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public String getAgencyCode() {
        return agencyCode;
    }

    /**
     * Sets the agency code of account holder.
     *
     * @param agencyCode
     *            the agency code of account holder.
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }
}
