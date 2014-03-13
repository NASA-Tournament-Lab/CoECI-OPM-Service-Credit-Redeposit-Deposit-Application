/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.application;

import gov.opm.scrd.entities.common.NamedEntity;

/**
 * <p>
 * This is a simple POJO class representing the letter entity used of the application.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 */
public class Letter extends NamedEntity {
    /**
     * Represents the content of the letter. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     */
    private String content;

    /**
     * Creates an instance of Letter.
     */
    public Letter() {
        // Empty
    }

    /**
     * Gets the content of the letter.
     *
     * @return the content of the letter.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the letter.
     *
     * @param content
     *            the content of the letter.
     */
    public void setContent(String content) {
        this.content = content;
    }
}
