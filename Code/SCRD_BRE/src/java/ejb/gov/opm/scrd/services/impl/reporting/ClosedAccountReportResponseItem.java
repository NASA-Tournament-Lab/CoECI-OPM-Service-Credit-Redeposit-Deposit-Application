/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;

/**
 * This class represents the response item for the namesake report. <p> <strong>Thread-safety:</strong> This class is
 * mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class ClosedAccountReportResponseItem {
    /**
     * Represents name field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private String name;

    /**
     * Represents FERS field. It is accessible by getter and modified by setter. It can be any value. The default value
     * is null.
     */
    private BigDecimal fers;

    /**
     * Represents redeposits post 982 field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal redepositsPost982;

    /**
     * Represents redeposits pre 1982 field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal redepositsPre1082;

    /**
     * Represents deposits post 982 field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal depositsPost982;

    /**
     * Represents deposits pre 1082 field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal depositsPre1082;

    /**
     * Creates a new instance of the {@link ClosedAccountReportResponseItem} class.
     */
    public ClosedAccountReportResponseItem() {
    }

    /**
     * Gets the name field.
     *
     * @return the field value.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name field.
     *
     * @param name
     *         the field value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the FERS field.
     *
     * @return the field value.
     */
    public BigDecimal getFers() {
        return fers;
    }

    /**
     * Sets the FERS field.
     *
     * @param fers
     *         the field value.
     */
    public void setFers(BigDecimal fers) {
        this.fers = fers;
    }

    /**
     * Gets the redeposits post 982 field.
     *
     * @return the field value.
     */
    public BigDecimal getRedepositsPost982() {
        return redepositsPost982;
    }

    /**
     * Sets the redeposits post 982 field.
     *
     * @param redepositsPost982
     *         the field value.
     */
    public void setRedepositsPost982(BigDecimal redepositsPost982) {
        this.redepositsPost982 = redepositsPost982;
    }

    /**
     * Gets the redeposits pre 1082 field.
     *
     * @return the field value.
     */
    public BigDecimal getRedepositsPre1082() {
        return redepositsPre1082;
    }

    /**
     * Sets the redeposits pre 1082 field.
     *
     * @param redepositsPre1082
     *         the field value.
     */
    public void setRedepositsPre1082(BigDecimal redepositsPre1082) {
        this.redepositsPre1082 = redepositsPre1082;
    }

    /**
     * Gets the deposits post 982 field.
     *
     * @return the field value.
     */
    public BigDecimal getDepositsPost982() {
        return depositsPost982;
    }

    /**
     * Sets the deposits post 982 field.
     *
     * @param depositsPost982
     *         the field value.
     */
    public void setDepositsPost982(BigDecimal depositsPost982) {
        this.depositsPost982 = depositsPost982;
    }

    /**
     * Gets the deposits pre 1082 field.
     *
     * @return the field value.
     */
    public BigDecimal getDepositsPre1082() {
        return depositsPre1082;
    }

    /**
     * Sets the deposits pre 1082 field.
     *
     * @param depositsPre1082
     *         the field value.
     */
    public void setDepositsPre1082(BigDecimal depositsPre1082) {
        this.depositsPre1082 = depositsPre1082;
    }
}
