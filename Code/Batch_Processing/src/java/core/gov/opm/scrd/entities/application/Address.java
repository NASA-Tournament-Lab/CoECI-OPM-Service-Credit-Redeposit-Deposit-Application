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
import gov.opm.scrd.entities.lookup.Country;
import gov.opm.scrd.entities.lookup.State;

/**
 * <p>
 * This is the class representing the physical address of the holder of the account.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class Address extends IdentifiableEntity {
    /**
     * <p>
     * Represents the first line of street. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String street1;
    /**
     * <p>
     * Represents the second line of street. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String street2;
    /**
     * <p>
     * Represents the third line of street. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String street3;
    /**
     * <p>
     * Represents the forth line of street. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String street4;
    /**
     * <p>
     * Represents the fifth line of street. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String street5;
    /**
     * <p>
     * Represents the city of address. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String city;
    /**
     * <p>
     * Represents the state of address. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private State state;
    /**
     * <p>
     * Represents the ZIP code of address. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String zipCode;
    /**
     * <p>
     * Represents the country of address. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Country country;

    /**
     * Creates an instance of Address.
     */
    public Address() {
        // Empty
    }

    /**
     * Gets the first line of street.
     *
     * @return the first line of street.
     */
    public String getStreet1() {
        return street1;
    }

    /**
     * Sets the first line of street.
     *
     * @param street1
     *            the first line of street.
     */
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    /**
     * Gets the second line of street.
     *
     * @return the second line of street.
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * Sets the second line of street.
     *
     * @param street2
     *            the second line of street.
     */
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    /**
     * Gets the third line of street.
     *
     * @return the third line of street.
     */
    public String getStreet3() {
        return street3;
    }

    /**
     * Sets the third line of street.
     *
     * @param street3
     *            the third line of street.
     */
    public void setStreet3(String street3) {
        this.street3 = street3;
    }

    /**
     * Gets the forth line of street.
     *
     * @return the forth line of street.
     */
    public String getStreet4() {
        return street4;
    }

    /**
     * Sets the forth line of street.
     *
     * @param street4
     *            the forth line of street.
     */
    public void setStreet4(String street4) {
        this.street4 = street4;
    }

    /**
     * Gets the fifth line of street.
     *
     * @return the fifth line of street.
     */
    public String getStreet5() {
        return street5;
    }

    /**
     * Sets the fifth line of street.
     *
     * @param street5
     *            the fifth line of street.
     */
    public void setStreet5(String street5) {
        this.street5 = street5;
    }

    /**
     * Gets the city of address.
     *
     * @return the city of address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of address.
     *
     * @param city
     *            the city of address.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state of address.
     *
     * @return the state of address.
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of address.
     *
     * @param state
     *            the state of address.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets the ZIP code of address.
     *
     * @return the ZIP code of address.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the ZIP code of address.
     *
     * @param zipCode
     *            the ZIP code of address.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the country of address.
     *
     * @return the country of address.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the country of address.
     *
     * @param country
     *            the country of address.
     */
    public void setCountry(Country country) {
        this.country = country;
    }
}
