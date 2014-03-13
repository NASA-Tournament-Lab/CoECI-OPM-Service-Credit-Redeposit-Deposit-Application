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

/**
 * <p>
 * This is the class representing the report generation data.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 * @since OPM - Frontend - Miscellaneous Module Assembly
 */
public class ReportGenerationData extends IdentifiableEntity {

    /**
     * <p>
     * The number of payment invoices processed. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Integer paymentInvoicesProcessed;

    /**
     * <p>
     * The number of bills printed. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Integer billsPrinted;

    /**
     * <p>
     * The number of reveals printed. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer revealsPrinted;

    /**
     * <p>
     * The number of letters printed. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer lettersPrinted;

    /**
     * <p>
     * The number of refunds printed. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer refundsPrinted;

    /**
     * Empty constructor.
     */
    public ReportGenerationData() {
        // Empty
    }

    /**
     * Getter method for property <tt>paymentInvoicesProcessed</tt>.
     * @return property value of paymentInvoicesProcessed
     */
    public Integer getPaymentInvoicesProcessed() {
        return paymentInvoicesProcessed;
    }

    /**
     * Setter method for property <tt>paymentInvoicesProcessed</tt>.
     * @param paymentInvoicesProcessed value to be assigned to property paymentInvoicesProcessed
     */
    public void setPaymentInvoicesProcessed(Integer paymentInvoicesProcessed) {
        this.paymentInvoicesProcessed = paymentInvoicesProcessed;
    }

    /**
     * Getter method for property <tt>billsPrinted</tt>.
     * @return property value of billsPrinted
     */
    public Integer getBillsPrinted() {
        return billsPrinted;
    }

    /**
     * Setter method for property <tt>billsPrinted</tt>.
     * @param billsPrinted value to be assigned to property billsPrinted
     */
    public void setBillsPrinted(Integer billsPrinted) {
        this.billsPrinted = billsPrinted;
    }

    /**
     * Getter method for property <tt>revealsPrinted</tt>.
     * @return property value of revealsPrinted
     */
    public Integer getRevealsPrinted() {
        return revealsPrinted;
    }

    /**
     * Setter method for property <tt>revealsPrinted</tt>.
     * @param revealsPrinted value to be assigned to property revealsPrinted
     */
    public void setRevealsPrinted(Integer revealsPrinted) {
        this.revealsPrinted = revealsPrinted;
    }

    /**
     * Getter method for property <tt>lettersPrinted</tt>.
     * @return property value of lettersPrinted
     */
    public Integer getLettersPrinted() {
        return lettersPrinted;
    }

    /**
     * Setter method for property <tt>lettersPrinted</tt>.
     * @param lettersPrinted value to be assigned to property lettersPrinted
     */
    public void setLettersPrinted(Integer lettersPrinted) {
        this.lettersPrinted = lettersPrinted;
    }

    /**
     * Getter method for property <tt>refundsPrinted</tt>.
     * @return property value of refundsPrinted
     */
    public Integer getRefundsPrinted() {
        return refundsPrinted;
    }

    /**
     * Setter method for property <tt>refundsPrinted</tt>.
     * @param refundsPrinted value to be assigned to property refundsPrinted
     */
    public void setRefundsPrinted(Integer refundsPrinted) {
        this.refundsPrinted = refundsPrinted;
    }
}
