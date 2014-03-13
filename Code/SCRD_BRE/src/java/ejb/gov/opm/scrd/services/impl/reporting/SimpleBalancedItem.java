package gov.opm.scrd.services.impl.reporting;

import java.math.BigDecimal;

/**
 * This class represents the primitive response item for the namesake report. <p> <strong>Thread-safety:</strong> This
 * class is mutable and not thread - safe. </p>
 *
 * @author AleaActaEst, RaitoShum
 * @version 1.0
 */
public class SimpleBalancedItem {
    /**
     * Represents the name field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private String name;

    /**
     * Represents the number field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Integer number;

    /**
     * Represents the total field. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private BigDecimal total;

    /**
     * Represents the suspended field. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String suspended;

    /**
     * Creates a new instance of the {@link SimpleBalancedItem} class.
     */
    public SimpleBalancedItem() {
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
     * Gets the number field.
     *
     * @return the field value.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Sets the number field.
     *
     * @param number
     *         the field value.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Gets the total field.
     *
     * @return the field value.
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Sets the total field.
     *
     * @param total
     *         the field value.
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Gets the suspended field.
     *
     * @return the field value.
     */
    public String getSuspended() {
        return suspended;
    }

    /**
     * Sets the suspended field.
     *
     * @param suspended
     *         the field value.
     */
    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }
}
