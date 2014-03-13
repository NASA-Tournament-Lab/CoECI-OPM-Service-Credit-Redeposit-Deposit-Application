/*
 * Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl.reporting;

/**
 * <p>
 * This class serves as the response item object that contains result details
 * to be used to generate reports in {@link ResolvedSuspenseHistoryReportService} service.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, AleaActaEst, j3_guile
 * @version 1.0
 */
public class ResolvedSuspenseHistoryReportHistogramItem {
    /**
     * Name.
     */
    private String name;
    /**
     * The count.
     */
    private Integer count;

    /**
     * Default constructor.
     */
    public ResolvedSuspenseHistoryReportHistogramItem() {
    }

    /**
     * Gets the value of the field <code>name</code>.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the field <code>name</code>.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of the field <code>count</code>.
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets the value of the field <code>count</code>.
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}
