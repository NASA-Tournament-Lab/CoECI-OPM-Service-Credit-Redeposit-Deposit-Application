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
