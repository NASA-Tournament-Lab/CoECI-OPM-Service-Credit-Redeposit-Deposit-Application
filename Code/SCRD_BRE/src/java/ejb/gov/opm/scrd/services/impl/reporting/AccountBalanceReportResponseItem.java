/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class represents the response item for the namesake report.
 * <p>
 * <strong>Thread-safety:</strong> This class is mutable and not thread - safe.
 * </p>
 * 
 * @author AleaActaEst, RaitoShum
 * @version 1.1
 */
public class AccountBalanceReportResponseItem {
	/**
	 * Represents audit time field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private Date auditTime;

	/**
	 * Represents audit user field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private String auditUser;

	/**
	 * Represents pay code field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private String payCode;

	/**
	 * Represents status field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private String status;

	/**
	 * Represents last pay date field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private Date lastPay;

	/**
	 * Represents deposit field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private BigDecimal deposit;

	/**
	 * Represents additional interest field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private BigDecimal additionalInterest;

	/**
	 * Represents payment field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private BigDecimal payment;

	/**
	 * Represents balance field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private BigDecimal balance;

	/**
	 * Represents sub totals field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private BigDecimal subTotals;

	/**
	 * Represents grand totals field. It is accessible by getter and
	 * modified by setter. It can be any value. The default value is null.
	 */
	private BigDecimal grandTotal;

	/**
	 * Creates a new instance of the {@link AccountBalanceReportResponseItem}
	 * class.
	 */
	public AccountBalanceReportResponseItem() {
	}

	/**
	 * Gets the audit time field.
	 * 
	 * @return the field value.
	 */
	public Date getAuditTime() {
		return auditTime;
	}

	/**
	 * Sets the audit time field.
	 * 
	 * @param auditTime
	 *            the field value.
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * Gets the audit user field.
	 * 
	 * @return the field value.
	 */
	public String getAuditUser() {
		return auditUser;
	}

	/**
	 * Sets the audit user field.
	 * 
	 * @param auditUser
	 *            the field value.
	 */
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	/**
	 * Gets the pay code field.
	 * 
	 * @return the field value.
	 */
	public String getPayCode() {
		return payCode;
	}

	/**
	 * Sets the pay code field.
	 * 
	 * @param payCode
	 *            the field value.
	 */
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	/**
	 * Gets the status field.
	 * 
	 * @return the field value.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status field.
	 * 
	 * @param status
	 *            the field value.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the last pay date field.
	 * 
	 * @return the field value.
	 */
	public Date getLastPay() {
		return lastPay;
	}

	/**
	 * Sets the last pay date field.
	 * 
	 * @param lastPay
	 *            the field value.
	 */
	public void setLastPay(Date lastPay) {
		this.lastPay = lastPay;
	}

	/**
	 * Gets the deposit field.
	 * 
	 * @return the field value.
	 */
	public BigDecimal getDeposit() {
		return deposit;
	}

	/**
	 * Sets the deposit field.
	 * 
	 * @param deposit
	 *            the field value.
	 */
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	/**
	 * Gets the additional interest field.
	 * 
	 * @return the field value.
	 */
	public BigDecimal getAdditionalInterest() {
		return additionalInterest;
	}

	/**
	 * Sets the additional interest field.
	 * 
	 * @param additionalInterest
	 *            the field value.
	 */
	public void setAdditionalInterest(BigDecimal additionalInterest) {
		this.additionalInterest = additionalInterest;
	}

	/**
	 * Gets the payment field.
	 * 
	 * @return the field value.
	 */
	public BigDecimal getPayment() {
		return payment;
	}

	/**
	 * Sets the payment field.
	 * 
	 * @param payment
	 *            the field value.
	 */
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	/**
	 * Gets the balance field.
	 * 
	 * @return the field value.
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Sets the balance field.
	 * 
	 * @param balance
	 *            the field value.
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * Gets the sub totals field.
	 * 
	 * @return the field value.
	 */
	public BigDecimal getSubTotals() {
		return subTotals;
	}

	/**
	 * Sets the sub totals field.
	 * 
	 * @param subTotals
	 *            the field value.
	 */
	public void setSubTotals(BigDecimal subTotals) {
		this.subTotals = subTotals;
	}

	/**
	 * Gets the grand totals field.
	 * 
	 * @return the field value.
	 */
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	/**
	 * Sets the grand totals field.
	 * 
	 * @param grandTotal
	 *            the field value.
	 */
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
}
