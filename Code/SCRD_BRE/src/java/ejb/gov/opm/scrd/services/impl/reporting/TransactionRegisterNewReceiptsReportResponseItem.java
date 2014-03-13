/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details to be used to generate reports in
 * {@link TransactionRegisterNewReceiptsReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class TransactionRegisterNewReceiptsReportResponseItem {
    /**
     * Payment type.
     */
    private String paymentType;
    /**
     * Date.
     */
    private Date date;

    /**
     * Claimant name.
     */
    private String claimantName;

    /**
     * CSD.
     */
    private String csd;

    /**
     * Retirement type.
     */
    private String retirementType;

    /**
     * Action type.
     */
    private String actionType;

    /**
     * Action time.
     */
    private Date actionTime;

    /**
     * FERS.
     */
    private BigDecimal fers;

    /**
     * Redeposits Post 982.
     */
    private BigDecimal redepositsPost982;

    /**
     * Redeposits Pre 1082.
     */
    private BigDecimal redepositsPre1082;

    /**
     * Deposits Post 982.
     */
    private BigDecimal depositsPost982;

    /**
     * Deposits Pre 1082.
     */
    private BigDecimal depositsPre1082;

    /**
     * Amount.
     */
    private BigDecimal amount;

    /**
     * Default constructor.
     */
    public TransactionRegisterNewReceiptsReportResponseItem() {
    }

    /**
     * Gets the value of the field <code>date</code>.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the value of the field <code>date</code>.
     *
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the value of the field <code>claimantName</code>.
     *
     * @return the claimantName
     */
    public String getClaimantName() {
        return claimantName;
    }

    /**
     * Sets the value of the field <code>claimantName</code>.
     *
     * @param claimantName the claimantName to set
     */
    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    /**
     * Gets the value of the field <code>csd</code>.
     *
     * @return the csd
     */
    public String getCsd() {
        return csd;
    }

    /**
     * Sets the value of the field <code>csd</code>.
     *
     * @param csd the csd to set
     */
    public void setCsd(String csd) {
        this.csd = csd;
    }

    /**
     * Gets the value of the field <code>retirementType</code>.
     *
     * @return the retirementType
     */
    public String getRetirementType() {
        return retirementType;
    }

    /**
     * Sets the value of the field <code>retirementType</code>.
     *
     * @param retirementType the retirementType to set
     */
    public void setRetirementType(String retirementType) {
        this.retirementType = retirementType;
    }

    /**
     * Gets the value of the field <code>actionType</code>.
     *
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Sets the value of the field <code>actionType</code>.
     *
     * @param actionType the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * Gets the value of the field <code>actionTime</code>.
     *
     * @return the actionTime
     */
    public Date getActionTime() {
        return actionTime;
    }

    /**
     * Sets the value of the field <code>actionTime</code>.
     *
     * @param actionTime the actionTime to set
     */
    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    /**
     * Gets the value of the field <code>fers</code>.
     *
     * @return the fers
     */
    public BigDecimal getFers() {
        return fers;
    }

    /**
     * Sets the value of the field <code>fers</code>.
     *
     * @param fers the fers to set
     */
    public void setFers(BigDecimal fers) {
        this.fers = fers;
    }

    /**
     * Gets the value of the field <code>redepositsPost982</code>.
     *
     * @return the redepositsPost982
     */
    public BigDecimal getRedepositsPost982() {
        return redepositsPost982;
    }

    /**
     * Sets the value of the field <code>redepositsPost982</code>.
     *
     * @param redepositsPost982 the redepositsPost982 to set
     */
    public void setRedepositsPost982(BigDecimal redepositsPost982) {
        this.redepositsPost982 = redepositsPost982;
    }

    /**
     * Gets the value of the field <code>redepositsPre1082</code>.
     *
     * @return the redepositsPre1082
     */
    public BigDecimal getRedepositsPre1082() {
        return redepositsPre1082;
    }

    /**
     * Sets the value of the field <code>redepositsPre1082</code>.
     *
     * @param redepositsPre1082 the redepositsPre1082 to set
     */
    public void setRedepositsPre1082(BigDecimal redepositsPre1082) {
        this.redepositsPre1082 = redepositsPre1082;
    }

    /**
     * Gets the value of the field <code>depositsPost982</code>.
     *
     * @return the depositsPost982
     */
    public BigDecimal getDepositsPost982() {
        return depositsPost982;
    }

    /**
     * Sets the value of the field <code>depositsPost982</code>.
     *
     * @param depositsPost982 the depositsPost982 to set
     */
    public void setDepositsPost982(BigDecimal depositsPost982) {
        this.depositsPost982 = depositsPost982;
    }

    /**
     * Gets the value of the field <code>depositsPre1082</code>.
     *
     * @return the depositsPre1082
     */
    public BigDecimal getDepositsPre1082() {
        return depositsPre1082;
    }

    /**
     * Sets the value of the field <code>depositsPre1082</code>.
     *
     * @param depositsPre1082 the depositsPre1082 to set
     */
    public void setDepositsPre1082(BigDecimal depositsPre1082) {
        this.depositsPre1082 = depositsPre1082;
    }

    /**
     * Gets the value of the field <code>amount</code>.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the field <code>amount</code>.
     *
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the value of the field <code>paymentType</code>.
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the field <code>paymentType</code>.
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
