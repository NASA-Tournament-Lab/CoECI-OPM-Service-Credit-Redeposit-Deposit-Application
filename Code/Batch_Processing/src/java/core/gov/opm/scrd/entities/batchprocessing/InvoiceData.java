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

import gov.opm.scrd.entities.common.IdentifiableEntity;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>This class is simply a POJO containing invoice data.</p>
 * <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 * <p>
 * <em>Changes in 1.1 (OPM - Reporting - Lockbox and Miscellaneous Module Assembly):</em>
 * <ol>
 * <li>Added account note type</li>
 * </ol>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Reporting - Balanced and Daily Assembly):</em>
 * <ol>
 * <li>Added fersTotalPayment, paymentApplicationOrder, pre1082DepositTotalPayment, pre1082RedepositTotalPayment,
 * post1082DepositTotalPayment, post1082RedepositTotalPayment, retirementTypeCode, retirementTypeDescription</li>
 * </ol>
 * </p>
 *
 * @author faeton, j3_guile, RaitoShum
 * @version 1.2
 */
public class InvoiceData extends IdentifiableEntity {

    /**
     * Represents the audit batch id. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Long auditBatchId;

    /**
     * Represents the pay transaction key. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Long payTransactionKey;

    /**
     * Represents the pay trans status code. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Long payTransStatusCode;

    /**
     * Represents the pay trans status description. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private String payTransStatusDescription;

    /**
     * Represents the pay trans transaction date. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Date payTransTransactionDate;

    /**
     * Represents the pay trans payment amount. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private BigDecimal payTransPaymentAmount;

    /**
     * Represents the over payment amount. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal overPaymentAmount;

    /**
     * Represents the scm claimnumber. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String scmClaimNumber;

    /**
     * Represents the scm date of birth. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Date scmDateOfBirth;

    /**
     * Represents the scm name. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String scmName;

    /**
     * Represents the account status. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Long accountStatus;

    /**
     * Represents the account status description. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private String accountStatusDescription;

    /**
     * Represents the account balance. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private BigDecimal accountBalance;

    /**
     * Represents the number payments today. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer numberPaymentsToday;

    /**
     * Represents the todays payment total. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal todaysPaymentTotal;

    /**
     * Represents the ach payment. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Boolean achPayment;

    /**
     * Represents if print initial bill or not. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Boolean printInitialBill;

    /**
     * Represents if it is the ach stop letter. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Boolean achStopLetter;

    /**
     * Represents if it is the reversed payment. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Boolean reversedPayment;

    /**
     * Represents if print invoice or not. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Boolean printInvoice;

    /**
     * Represents if refund required or not. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Boolean refundRequired;

    /**
     * Represents if update to completed status or not. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private Boolean updateToCompleted;

    /**
     * Represents the error processing flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private Boolean errorProcessing;

    /**
     * Represents the over the payment amount. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal overThePaymentAmount;

    /**
     * Represents the account payment total. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal accountPaymentTotal;

    /**
     * Represents the new account balance. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal accountBalanceNew;

    /**
     * Represents the note. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String note;

    /**
     * The account note type.
     * http://apps.topcoder.com/forums/?module=Thread&threadID=808983&start=0
     * @since 1.1
     */
    private AccountNoteType accountNoteType;

    /**
     * Represents namesake field value. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     * @since 1.2
     */
    private BigDecimal fersTotalPayment;

    /**
     * Represents namesake field value. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     * @since 1.2
     */
    private LookUpPaymentApplicationOrder paymentApplicationOrder;

    /**
     * Represents namesake field value. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     * @since 1.2
     */
    private BigDecimal pre1082DepositTotalPayment;

    /**
     * Represents namesake field value. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     * @since 1.2
     */
    private BigDecimal pre1082RedepositTotalPayment;

    /**
     * Represents namesake field value. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     * @since 1.2
     */
    private BigDecimal post1082DepositTotalPayment;

    /**
     * Represents namesake field value. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     * @since 1.2
     */
    private BigDecimal post1082RedepositTotalPayment;

    /**
     * Represents namesake field value. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     * @since 1.2
     */
    private Integer retirementTypeCode;

    /**
     * Represents namesake field value. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     * @since 1.2
     */
    private String retirementTypeDescription;

    /**
     * Instantiates a new invoice data.
     */
    public InvoiceData() {
    }

    /**
     * Constructor with fields.
     */
    public InvoiceData(Long auditBatchId, Long payTransactionKey, Long payTransStatusCode,
            String payTransStatusDescription, Date payTransTransactionDate, BigDecimal payTransPaymentAmount,
            BigDecimal overPaymentAmount, String scmClaimNumber, Date scmDateOfBirth, String scmName,
            Long accountStatus, String accountStatusDescription, BigDecimal accountBalance,
            Integer numberPaymentsToday, BigDecimal todaysPaymentTotal, Boolean achPayment, Boolean printInitialBill,
            Boolean achStopLetter, Boolean reversedPayment, Boolean printInvoice, Boolean refundRequired,
            Boolean updateToCompleted, Boolean errorProcessing) {
        this.auditBatchId = auditBatchId;
        this.payTransactionKey = payTransactionKey;
        this.payTransStatusCode = payTransStatusCode;
        this.payTransStatusDescription = payTransStatusDescription;
        this.payTransTransactionDate = payTransTransactionDate;
        this.payTransPaymentAmount = payTransPaymentAmount;
        this.overPaymentAmount = overPaymentAmount;
        this.scmClaimNumber = scmClaimNumber;
        this.scmDateOfBirth = scmDateOfBirth;
        this.scmName = scmName;
        this.accountStatus = accountStatus;
        this.accountStatusDescription = accountStatusDescription;
        this.accountBalance = accountBalance;
        this.numberPaymentsToday = numberPaymentsToday;
        this.todaysPaymentTotal = todaysPaymentTotal;
        this.achPayment = achPayment;
        this.printInitialBill = printInitialBill;
        this.achStopLetter = achStopLetter;
        this.reversedPayment = reversedPayment;
        this.printInvoice = printInvoice;
        this.refundRequired = refundRequired;
        this.updateToCompleted = updateToCompleted;
        this.errorProcessing = errorProcessing;
    }

    /**
     * Gets the pay transaction key.
     *
     * @return the pay transaction key
     */
    public Long getPayTransactionKey() {
        return payTransactionKey;
    }

    /**
     * Sets the pay transaction key.
     *
     * @param payTransactionKey the new pay transaction key
     */
    public void setPayTransactionKey(Long payTransactionKey) {
        this.payTransactionKey = payTransactionKey;
    }

    /**
     * Gets the scm claimnumber.
     *
     * @return the scm claimnumber
     */
    public String getScmClaimNumber() {
        return scmClaimNumber;
    }

    /**
     * Sets the scm claimnumber.
     *
     * @param scmClaimNumber the new scm claimnumber
     */
    public void setScmClaimNumber(String scmClaimNumber) {
        this.scmClaimNumber = scmClaimNumber;
    }

    /**
     * Gets the scm date of birth.
     *
     * @return the scm date of birth
     */
    public Date getScmDateOfBirth() {
        return scmDateOfBirth;
    }

    /**
     * Sets the scm date of birth.
     *
     * @param scmDateOfBirth the new scm date of birth
     */
    public void setScmDateOfBirth(Date scmDateOfBirth) {
        this.scmDateOfBirth = scmDateOfBirth;
    }

    /**
     * Gets the scm name.
     *
     * @return the scm name
     */
    public String getScmName() {
        return scmName;
    }

    /**
     * Sets the scm name.
     *
     * @param scmName the new scm name
     */
    public void setScmName(String scmName) {
        this.scmName = scmName;
    }

    /**
     * Gets the account status description.
     *
     * @return the account status description
     */
    public String getAccountStatusDescription() {
        return accountStatusDescription;
    }

    /**
     * Sets the account status description.
     *
     * @param accountStatusDescription the new account status description
     */
    public void setAccountStatusDescription(String accountStatusDescription) {
        this.accountStatusDescription = accountStatusDescription;
    }

    /**
     * Gets the account balance.
     *
     * @return the account balance
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the account balance.
     *
     * @param accountBalance the new account balance
     */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * Gets the total account payment.
     *
     * @return the total account payment
     */
    public BigDecimal getAccountPaymentTotal() {
        return accountPaymentTotal;
    }

    /**
     * Sets the total account payment.
     *
     * @param accountPaymentTotal the total account payment
     */
    public void setAccountPaymentTotal(BigDecimal accountPaymentTotal) {
        this.accountPaymentTotal = accountPaymentTotal;
    }

    /**
     * Gets the new account balance.
     *
     * @return the new account balance
     */
    public BigDecimal getAccountBalanceNew() {
        return accountBalanceNew;
    }

    /**
     * Sets the new account balance.
     *
     * @param accountBalanceNew the new account balance
     */
    public void setAccountBalanceNew(BigDecimal accountBalanceNew) {
        this.accountBalanceNew = accountBalanceNew;
    }

    /**
     * Gets the todays payment total.
     *
     * @return the todays payment total
     */
    public BigDecimal getTodaysPaymentTotal() {
        return todaysPaymentTotal;
    }

    /**
     * Sets the todays payment total.
     *
     * @param todaysPaymentTotal the todays payment total
     */
    public void setTodaysPaymentTotal(BigDecimal todaysPaymentTotal) {
        this.todaysPaymentTotal = todaysPaymentTotal;
    }

    /**
     * Gets the pay trans status code.
     *
     * @return the pay trans status code
     */
    public Long getPayTransStatusCode() {
        return payTransStatusCode;
    }

    /**
     * Sets the pay trans status code.
     *
     * @param payTransStatusCode the new pay trans status code
     */
    public void setPayTransStatusCode(Long payTransStatusCode) {
        this.payTransStatusCode = payTransStatusCode;
    }

    /**
     * Gets the pay trans status description.
     *
     * @return the pay trans status description
     */
    public String getPayTransStatusDescription() {
        return payTransStatusDescription;
    }

    /**
     * Sets the pay transaction status description.
     *
     * @param payTransStatusDescription the new pay transaction status description
     */
    public void setPayTransStatusDescription(String payTransStatusDescription) {
        this.payTransStatusDescription = payTransStatusDescription;
    }

    /**
     * Gets the pay trans payment amount.
     *
     * @return the pay trans payment amount
     */
    public BigDecimal getPayTransPaymentAmount() {
        return payTransPaymentAmount;
    }

    /**
     * Sets the pay transaction payment amount.
     *
     * @param payTransPaymentAmount the new pay transaction payment amount
     */
    public void setPayTransPaymentAmount(BigDecimal payTransPaymentAmount) {
        this.payTransPaymentAmount = payTransPaymentAmount;
    }

    /**
     * Gets the over payment amount.
     *
     * @return the over payment amount
     */
    public BigDecimal getOverPaymentAmount() {
        return overPaymentAmount;
    }

    /**
     * Sets the over payment amount.
     *
     * @param overPaymentAmount the new over payment amount
     */
    public void setOverPaymentAmount(BigDecimal overPaymentAmount) {
        this.overPaymentAmount = overPaymentAmount;
    }

    /**
     * Gets the pay trans transaction date.
     *
     * @return the pay trans transaction date
     */
    public Date getPayTransTransactionDate() {
        return payTransTransactionDate;
    }

    /**
     * Sets the pay trans transaction date.
     *
     * @param payTransTransactionDate the new pay trans transaction date
     */
    public void setPayTransTransactionDate(Date payTransTransactionDate) {
        this.payTransTransactionDate = payTransTransactionDate;
    }

    /**
     * Gets the number payments today.
     *
     * @return the number payments today
     */
    public Integer getNumberPaymentsToday() {
        return numberPaymentsToday;
    }

    /**
     * Sets the number payments today.
     *
     * @param numberPaymentsToday the new number payments today
     */
    public void setNumberPaymentsToday(Integer numberPaymentsToday) {
        this.numberPaymentsToday = numberPaymentsToday;
    }

    /**
     * Gets the flag specifying whether it is ach payment.
     *
     * @return the flag specifying whether it is ach payment
     */
    public Boolean getAchPayment() {
        return achPayment;
    }

    /**
     * Sets the flag specifying whether it is ach payment.
     *
     * @param achPayment the flag specifying whether it is ach payment.
     */
    public void setAchPayment(Boolean achPayment) {
        this.achPayment = achPayment;
    }

    /**
     * Gets the note.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note.
     *
     * @param note the new note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets the flag specifying whether it is ach stop letter.
     *
     * @return the flag specifying whether it is ach stop letter
     */
    public Boolean getAchStopLetter() {
        return achStopLetter;
    }

    /**
     * Sets the flag specifying whether it is ach stop letter.
     *
     * @param achStopLetter the flag specifying whether it is ach stop letter
     */
    public void setAchStopLetter(Boolean achStopLetter) {
        this.achStopLetter = achStopLetter;
    }

    /**
     * Gets the flag specifying whether prints the initial bill.
     *
     * @return the flag specifying whether prints the initial bill
     */
    public Boolean getPrintInitialBill() {
        return printInitialBill;
    }

    /**
     * Sets the flag specifying whether prints the initial bill.
     *
     * @param printInitialBill the flag specifying whether prints the initial bill
     */
    public void setPrintInitialBill(Boolean printInitialBill) {
        this.printInitialBill = printInitialBill;
    }

    /**
     * Gets the account status.
     *
     * @return the account status
     */
    public Long getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the account status.
     *
     * @param accountStatus the new account status
     */
    public void setAccountStatus(Long accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Gets the flag specifying whether it is reversed payment.
     *
     * @return the flag specifying whether it is reversed payment
     */
    public Boolean getReversedPayment() {
        return reversedPayment;
    }

    /**
     * Sets the flag specifying whether it is reversed payment.
     *
     * @param reversedPayment the flag specifying whether it is reversed payment
     */
    public void setReversedPayment(Boolean reversedPayment) {
        this.reversedPayment = reversedPayment;
    }

    /**
     * Gets the flag specifying whether prints the invoice.
     *
     * @return the flag specifying whether prints the invoice
     */
    public Boolean getPrintInvoice() {
        return printInvoice;
    }

    /**
     * Sets the flag specifying whether prints the invoice.
     *
     * @param printInvoice the flag specifying whether prints the invoice
     */
    public void setPrintInvoice(Boolean printInvoice) {
        this.printInvoice = printInvoice;
    }

    /**
     * Gets the flag specifying whether refund is required.
     *
     * @return the flag specifying whether refund is required
     */
    public Boolean getRefundRequired() {
        return refundRequired;
    }

    /**
     * Sets the flag specifying whether refund is required.
     *
     * @param refundRequired the flag specifying whether refund is required
     */
    public void setRefundRequired(Boolean refundRequired) {
        this.refundRequired = refundRequired;
    }

    /**
     * Gets the flag specifying whether it is update to completed.
     *
     * @return the flag specifying whether it is update to completed
     */
    public Boolean getUpdateToCompleted() {
        return updateToCompleted;
    }

    /**
     * Sets the flag specifying whether it is update to completed.
     *
     * @param updateToCompleted the flag specifying whether it is update to completed
     */
    public void setUpdateToCompleted(Boolean updateToCompleted) {
        this.updateToCompleted = updateToCompleted;
    }

    /**
     * Gets the over the payment amount.
     *
     * @return the over the payment amount
     */
    public BigDecimal getOverThePaymentAmount() {
        return overThePaymentAmount;
    }

    /**
     * Sets the over the payment amount.
     *
     * @param overThePaymentAmount the new over the payment amount
     */
    public void setOverThePaymentAmount(BigDecimal overThePaymentAmount) {
        this.overThePaymentAmount = overThePaymentAmount;
    }

    /**
     * Getter method for property <tt>errorProcessing</tt>.
     * @return property value of errorProcessing
     */
    public Boolean getErrorProcessing() {
        return errorProcessing;
    }

    /**
     * Setter method for property <tt>errorProcessing</tt>.
     * @param errorProcessing value to be assigned to property errorProcessing
     */
    public void setErrorProcessing(Boolean errorProcessing) {
        this.errorProcessing = errorProcessing;
    }

    /**
     * Getter method for property <tt>auditBatchId</tt>.
     * @return property value of auditBatchId
     */
    public Long getAuditBatchId() {
        return auditBatchId;
    }

    /**
     * Setter method for property <tt>auditBatchId</tt>.
     * @param auditBatchId value to be assigned to property auditBatchId
     */
    public void setAuditBatchId(Long auditBatchId) {
        this.auditBatchId = auditBatchId;
    }

    /**
     * Gets the value of the field <code>accountNoteType</code>.
     * @return the accountNoteType
     */
    public AccountNoteType getAccountNoteType() {
        return accountNoteType;
    }

    /**
     * Sets the value of the field <code>accountNoteType</code>.
     * @param accountNoteType the accountNoteType to set
     */
    public void setAccountNoteType(AccountNoteType accountNoteType) {
        this.accountNoteType = accountNoteType;
    }

    /**
     * Gets the namesake field value.
     *
     * @return the namesake field value.
     */
    public BigDecimal getFersTotalPayment() {
        return fersTotalPayment;
    }

    /**
     * Sets the namesake field value.
     *
     * @param fersTotalPayment
     *         the namesake field value.
     */
    public void setFersTotalPayment(BigDecimal fersTotalPayment) {
        this.fersTotalPayment = fersTotalPayment;
    }

    /**
     * Gets the namesake field value.
     *
     * @return the namesake field value.
     */
    public LookUpPaymentApplicationOrder getPaymentApplicationOrder() {
        return paymentApplicationOrder;
    }

    /**
     * Sets the namesake field value.
     *
     * @param paymentApplicationOrder
     *         the namesake field value.
     */
    public void setPaymentApplicationOrder(LookUpPaymentApplicationOrder paymentApplicationOrder) {
        this.paymentApplicationOrder = paymentApplicationOrder;
    }

    /**
     * Gets the namesake field value.
     *
     * @return the namesake field value.
     */
    public BigDecimal getPre1082DepositTotalPayment() {
        return pre1082DepositTotalPayment;
    }

    /**
     * Sets the namesake field value.
     *
     * @param pre1082DepositTotalPayment
     *         the namesake field value.
     */
    public void setPre1082DepositTotalPayment(BigDecimal pre1082DepositTotalPayment) {
        this.pre1082DepositTotalPayment = pre1082DepositTotalPayment;
    }

    /**
     * Gets the namesake field value.
     *
     * @return the namesake field value.
     */
    public BigDecimal getPre1082RedepositTotalPayment() {
        return pre1082RedepositTotalPayment;
    }

    /**
     * Sets the namesake field value.
     *
     * @param pre1082RedepositTotalPayment
     *         the namesake field value.
     */
    public void setPre1082RedepositTotalPayment(BigDecimal pre1082RedepositTotalPayment) {
        this.pre1082RedepositTotalPayment = pre1082RedepositTotalPayment;
    }

    /**
     * Gets the namesake field value.
     *
     * @return the namesake field value.
     */
    public BigDecimal getPost1082DepositTotalPayment() {
        return post1082DepositTotalPayment;
    }

    /**
     * Sets the namesake field value.
     *
     * @param post1082DepositTotalPayment
     *         the namesake field value.
     */
    public void setPost1082DepositTotalPayment(BigDecimal post1082DepositTotalPayment) {
        this.post1082DepositTotalPayment = post1082DepositTotalPayment;
    }

    /**
     * Gets the namesake field value.
     *
     * @return the namesake field value.
     */
    public BigDecimal getPost1082RedepositTotalPayment() {
        return post1082RedepositTotalPayment;
    }

    /**
     * Sets the namesake field value.
     *
     * @param post1082RedepositTotalPayment
     *         the namesake field value.
     */
    public void setPost1082RedepositTotalPayment(BigDecimal post1082RedepositTotalPayment) {
        this.post1082RedepositTotalPayment = post1082RedepositTotalPayment;
    }

    /**
     * Gets the namesake field value.
     *
     * @return the namesake field value.
     */
    public Integer getRetirementTypeCode() {
        return retirementTypeCode;
    }

    /**
     * Sets the namesake field value.
     *
     * @param retirementTypeCode
     *         the namesake field value.
     */
    public void setRetirementTypeCode(Integer retirementTypeCode) {
        this.retirementTypeCode = retirementTypeCode;
    }

    /**
     * Gets the namesake field value.
     *
     * @return the namesake field value.
     */
    public String getRetirementTypeDescription() {
        return retirementTypeDescription;
    }

    /**
     * Sets the namesake field value.
     *
     * @param retirementTypeDescription
     *         the namesake field value.
     */
    public void setRetirementTypeDescription(String retirementTypeDescription) {
        this.retirementTypeDescription = retirementTypeDescription;
    }
}
