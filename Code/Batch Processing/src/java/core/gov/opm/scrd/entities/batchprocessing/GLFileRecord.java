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

package gov.opm.scrd.entities.batchprocessing;

import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>This class is simply a POJO containing status representing one line of GL file record.</p>
 * <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 *
 * @author faeton, liuliquan
 * @version 1.0
 */
public class GLFileRecord {

    /**
     * Represents the payment date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String feederSystemId;

    /**
     * Represents the payment date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date paymentDate;

    /**
     * Represents the julian date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String julianDate;

    /**
     * Represents the gl filler. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String glFiller;

    /**
     * Represents the gl code. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String glCode;

    /**
     * Represents the fiscal year. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer fiscalYear;

    /**
     * Represents the gl accounting code. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String glAccountingCode;

    /**
     * Represents the recipient amount. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private BigDecimal recipientAmount;

    /**
     * Represents the revenue source code. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private String revenueSourceCode;

    /**
     * Represents the agency. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String agency;

    /**
     * Represents the print date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date printDate;

    /**
     * Represents the total non postal fers. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal totalNonPostalFers;

    /**
     * Represents the total postal fers. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal totalPostalFers;

    /**
     * Represents the total csrs. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal totalCsrs;

    /**
     * Represents the julian now. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String julianNow;

    /**
     * Represents the number of payments. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Integer numberOfPayments;

    /**
     * Empty constructor.
     */
    public GLFileRecord() {
    }

    /**
     * Constructor with fields.
     */
    public GLFileRecord(String feederSystemId, Date paymentDate, String julianDate, String glFiller, String glCode,
            Integer fiscalYear, String glAccountingCode, BigDecimal recipientAmount, String revenueSourceCode,
            Integer numberOfPayments, String agency, Date printDate, BigDecimal totalNonPostalFers, BigDecimal totalPostalFers,
            BigDecimal totalCsrs, String julianNow) {
        this.feederSystemId = feederSystemId;
        this.paymentDate = paymentDate;
        this.julianDate = julianDate;
        this.glFiller = glFiller;
        this.glCode = glCode;
        this.fiscalYear = fiscalYear;
        this.glAccountingCode = glAccountingCode;
        this.recipientAmount = recipientAmount;
        this.revenueSourceCode = revenueSourceCode;
        this.agency = agency;
        this.printDate = printDate;
        this.totalNonPostalFers = totalNonPostalFers;
        this.totalPostalFers = totalPostalFers;
        this.totalCsrs = totalCsrs;
        this.julianNow = julianNow;
        this.numberOfPayments = numberOfPayments;
    }

    /**
     * Getter method for property <tt>feederSystemId</tt>.
     * @return property value of feederSystemId
     */
    public String getFeederSystemId() {
        return feederSystemId;
    }

    /**
     * Setter method for property <tt>feederSystemId</tt>.
     * @param feederSystemId value to be assigned to property feederSystemId
     */
    public void setFeederSystemId(String feederSystemId) {
        this.feederSystemId = feederSystemId;
    }

    /**
     * Getter method for property <tt>paymentDate</tt>.
     * @return property value of paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Setter method for property <tt>paymentDate</tt>.
     * @param paymentDate value to be assigned to property paymentDate
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Getter method for property <tt>julianDate</tt>.
     * @return property value of julianDate
     */
    public String getJulianDate() {
        return julianDate;
    }

    /**
     * Setter method for property <tt>julianDate</tt>.
     * @param julianDate value to be assigned to property julianDate
     */
    public void setJulianDate(String julianDate) {
        this.julianDate = julianDate;
    }

    /**
     * Getter method for property <tt>glFiller</tt>.
     * @return property value of glFiller
     */
    public String getGlFiller() {
        return glFiller;
    }

    /**
     * Setter method for property <tt>glFiller</tt>.
     * @param glFiller value to be assigned to property glFiller
     */
    public void setGlFiller(String glFiller) {
        this.glFiller = glFiller;
    }

    /**
     * Getter method for property <tt>glCode</tt>.
     * @return property value of glCode
     */
    public String getGlCode() {
        return glCode;
    }

    /**
     * Setter method for property <tt>glCode</tt>.
     * @param glCode value to be assigned to property glCode
     */
    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    /**
     * Getter method for property <tt>fiscalYear</tt>.
     * @return property value of fiscalYear
     */
    public Integer getFiscalYear() {
        return fiscalYear;
    }

    /**
     * Setter method for property <tt>fiscalYear</tt>.
     * @param fiscalYear value to be assigned to property fiscalYear
     */
    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     * Getter method for property <tt>glAccountingCode</tt>.
     * @return property value of glAccountingCode
     */
    public String getGlAccountingCode() {
        return glAccountingCode;
    }

    /**
     * Setter method for property <tt>glAccountingCode</tt>.
     * @param glAccountingCode value to be assigned to property glAccountingCode
     */
    public void setGlAccountingCode(String glAccountingCode) {
        this.glAccountingCode = glAccountingCode;
    }

    /**
     * Getter method for property <tt>recipientAmount</tt>.
     * @return property value of recipientAmount
     */
    public BigDecimal getRecipientAmount() {
        return recipientAmount;
    }

    /**
     * Setter method for property <tt>recipientAmount</tt>.
     * @param recipientAmount value to be assigned to property recipientAmount
     */
    public void setRecipientAmount(BigDecimal recipientAmount) {
        this.recipientAmount = recipientAmount;
    }

    /**
     * Getter method for property <tt>revenueSourceCode</tt>.
     * @return property value of revenueSourceCode
     */
    public String getRevenueSourceCode() {
        return revenueSourceCode;
    }

    /**
     * Setter method for property <tt>revenueSourceCode</tt>.
     * @param revenueSourceCode value to be assigned to property revenueSourceCode
     */
    public void setRevenueSourceCode(String revenueSourceCode) {
        this.revenueSourceCode = revenueSourceCode;
    }

    /**
     * Getter method for property <tt>agency</tt>.
     * @return property value of agency
     */
    public String getAgency() {
        return agency;
    }

    /**
     * Setter method for property <tt>agency</tt>.
     * @param agency value to be assigned to property agency
     */
    public void setAgency(String agency) {
        this.agency = agency;
    }

    /**
     * Getter method for property <tt>printDate</tt>.
     * @return property value of printDate
     */
    public Date getPrintDate() {
        return printDate;
    }

    /**
     * Setter method for property <tt>printDate</tt>.
     * @param printDate value to be assigned to property printDate
     */
    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    /**
     * Getter method for property <tt>totalNonPostalFers</tt>.
     * @return property value of totalNonPostalFers
     */
    public BigDecimal getTotalNonPostalFers() {
        return totalNonPostalFers;
    }

    /**
     * Setter method for property <tt>totalNonPostalFers</tt>.
     * @param totalNonPostalFers value to be assigned to property totalNonPostalFers
     */
    public void setTotalNonPostalFers(BigDecimal totalNonPostalFers) {
        this.totalNonPostalFers = totalNonPostalFers;
    }

    /**
     * Getter method for property <tt>totalPostalFers</tt>.
     * @return property value of totalPostalFers
     */
    public BigDecimal getTotalPostalFers() {
        return totalPostalFers;
    }

    /**
     * Setter method for property <tt>totalPostalFers</tt>.
     * @param totalPostalFers value to be assigned to property totalPostalFers
     */
    public void setTotalPostalFers(BigDecimal totalPostalFers) {
        this.totalPostalFers = totalPostalFers;
    }

    /**
     * Getter method for property <tt>totalCsrs</tt>.
     * @return property value of totalCsrs
     */
    public BigDecimal getTotalCsrs() {
        return totalCsrs;
    }

    /**
     * Setter method for property <tt>totalCsrs</tt>.
     * @param totalCsrs value to be assigned to property totalCsrs
     */
    public void setTotalCsrs(BigDecimal totalCsrs) {
        this.totalCsrs = totalCsrs;
    }

    /**
     * Getter method for property <tt>julianNow</tt>.
     * @return property value of julianNow
     */
    public String getJulianNow() {
        return julianNow;
    }

    /**
     * Setter method for property <tt>julianNow</tt>.
     * @param julianNow value to be assigned to property julianNow
     */
    public void setJulianNow(String julianNow) {
        this.julianNow = julianNow;
    }

    /**
     * Getter method for property <tt>numberOfPayments</tt>.
     * @return property value of numberOfPayments
     */
    public Integer getNumberOfPayments() {
        return numberOfPayments;
    }

    /**
     * Setter method for property <tt>numberOfPayments</tt>.
     * @param numberOfPayments value to be assigned to property numberOfPayments
     */
    public void setNumberOfPayments(Integer numberOfPayments) {
        this.numberOfPayments = numberOfPayments;
    }
}
