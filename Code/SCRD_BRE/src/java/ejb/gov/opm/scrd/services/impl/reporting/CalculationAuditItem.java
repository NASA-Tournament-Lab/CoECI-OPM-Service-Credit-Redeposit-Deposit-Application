/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class represents the response item for the namesake report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class CalculationAuditItem {
    /**
     * Represents the value indicates if it is official. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Boolean official;

    /**
     * Represents version field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer version;

    /**
     * Represents line field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private Integer line;

    /**
     * Represents period field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String period;

    /**
     * Represents start date field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Date startDate;

    /**
     * Represents end date field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Date endDate;

    /**
     * Represents deduction field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal deduction;

    /**
     * Represents interest field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal interest;

    /**
     * Represents payments field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal payments;

    /**
     * Represents inserted field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Boolean inserted;

    /**
     * Represents updated field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Boolean updated;

    /**
     * Represents deleted field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Boolean deleted;

    /**
     * Creates a new instance of the {@link CalculationAuditItem} class.
     */
    public CalculationAuditItem() {
    }

    /**
     * Gets the value indicates if it is official.
     *
     * @return the field value.
     */
    public Boolean isOfficial() {
        return official;
    }

    /**
     * Sets the value indicates if it is official.
     *
     * @param official
     *         the field value.
     */
    public void setOfficial(Boolean official) {
        this.official = official;
    }

    /**
     * Gets the version field.
     *
     * @return the field value.
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the version field.
     *
     * @param version
     *         the field value.
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Gets the line field.
     *
     * @return the field value.
     */
    public Integer getLine() {
        return line;
    }

    /**
     * Sets the line field.
     *
     * @param line
     *         the field value.
     */
    public void setLine(Integer line) {
        this.line = line;
    }

    /**
     * Gets the period field.
     *
     * @return the field value.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets the period field.
     *
     * @param period
     *         the field value.
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Gets the start date field.
     *
     * @return the field value.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date field.
     *
     * @param startDate
     *         the field value.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date field.
     *
     * @return the field value.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date field.
     *
     * @param endDate
     *         the field value.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the deduction field.
     *
     * @return the field value.
     */
    public BigDecimal getDeduction() {
        return deduction;
    }

    /**
     * Sets the deduction field.
     *
     * @param deduction
     *         the field value.
     */
    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
    }

    /**
     * Gets the interest field.
     *
     * @return the field value.
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * Sets the interest field.
     *
     * @param interest
     *         the field value.
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * Gets the payments field.
     *
     * @return the field value.
     */
    public BigDecimal getPayments() {
        return payments;
    }

    /**
     * Sets the payments field.
     *
     * @param payments
     *         the field value.
     */
    public void setPayments(BigDecimal payments) {
        this.payments = payments;
    }

    /**
     * Gets the inserted flg.
     *
     * @return the field value.
     */
    public Boolean isInserted() {
        return inserted;
    }

    /**
     * Sets the inserted flag..
     *
     * @param inserted
     *         the field value.
     */
    public void setInserted(Boolean inserted) {
        this.inserted = inserted;
    }

    /**
     * Gets the updated flag..
     *
     * @return the field value.
     */
    public Boolean isUpdated() {
        return updated;
    }

    /**
     * Sets the updated flag.
     *
     * @param updated
     *         the field value.
     */
    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    /**
     * Gets the deleted flag..
     *
     * @return the field value.
     */
    public Boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted flag.
     *
     * @param deleted
     *         the field value.
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
