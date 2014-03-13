/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link ActiveCreditBalancesReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class ActiveCreditBalancesReportResponseItem {
    /**
     * CSD.
     */
    private String csd;

    /**
     * Credit balance.
     */
    private BigDecimal creditBalance;

    /**
     * Date of over payment.
     */
    private Date dateOfOverPayment;

    /**
     * Default constructor.
     */
    public ActiveCreditBalancesReportResponseItem() {
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
     * Gets the value of the field <code>creditBalance</code>.
     * @return the creditBalance
     */
    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    /**
     * Sets the value of the field <code>creditBalance</code>.
     * @param creditBalance the creditBalance to set
     */
    public void setCreditBalance(BigDecimal creditBalance) {
        this.creditBalance = creditBalance;
    }

    /**
     * Gets the value of the field <code>dateOfOverPayment</code>.
     * @return the dateOfOverPayment
     */
    public Date getDateOfOverPayment() {
        return dateOfOverPayment;
    }

    /**
     * Sets the value of the field <code>dateOfOverPayment</code>.
     * @param dateOfOverPayment the dateOfOverPayment to set
     */
    public void setDateOfOverPayment(Date dateOfOverPayment) {
        this.dateOfOverPayment = dateOfOverPayment;
    }
}

