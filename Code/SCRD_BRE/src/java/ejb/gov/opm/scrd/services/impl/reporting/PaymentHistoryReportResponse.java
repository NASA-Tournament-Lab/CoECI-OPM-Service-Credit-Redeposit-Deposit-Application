/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import gov.opm.scrd.services.impl.BaseReportResponse;

import java.util.List;

/**
 * <p>
 * This class serves as the response object that contains result details
 * to be used to generate reports in {@link PaymentHistoryReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class PaymentHistoryReportResponse extends BaseReportResponse {
    /**
     * CSD #.
     */
    private String csd;
    /**
     * Username.
     */
    private String username;
    /**
     * Address line 1.
     */
    private String address1;
    /**
     * Address line 2.
     */
    private String address2;
    /**
     * City.
     */
    private String city;
    /**
     * State.
     */
    private String state;
    /**
     * Zip code.
     */
    private String zip;
    /**
     * Telephone number.
     */
    private String phone;
    /**
     * List of payment history items.
     */
    private List<PaymentHistoryReportResponseItem> items;

    /**
     * Default constructor.
     */
    public PaymentHistoryReportResponse() {
    }

    /**
     * Gets the value of the field <code>csd</code>.
     * @return the csd
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the value of the field <code>csd</code>.
     * @param csd the csd to set
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the value of the field <code>username</code>.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the field <code>username</code>.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the value of the field <code>address1</code>.
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the value of the field <code>address1</code>.
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * Gets the value of the field <code>address2</code>.
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the value of the field <code>address2</code>.
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Gets the value of the field <code>city</code>.
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the field <code>city</code>.
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the value of the field <code>state</code>.
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the field <code>state</code>.
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the value of the field <code>zip</code>.
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the field <code>zip</code>.
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets the value of the field <code>phone</code>.
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the field <code>phone</code>.
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the value of the field <code>items</code>.
     * @return the items
     */
    public List<PaymentHistoryReportResponseItem> getItems() {
        return items;
    }

    /**
     * Sets the value of the field <code>items</code>.
     * @param items the items to set
     */
    public void setItems(List<PaymentHistoryReportResponseItem> items) {
        this.items = items;
    }
}
