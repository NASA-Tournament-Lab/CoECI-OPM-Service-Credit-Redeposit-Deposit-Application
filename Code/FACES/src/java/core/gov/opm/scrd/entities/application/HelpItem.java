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

import gov.opm.scrd.entities.common.IdentifiableEntity;

import java.util.List;

/**
 * <p>
 * Represents the help item in the application. The item will contain a help information and can be related to number of
 * other help items.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class HelpItem extends IdentifiableEntity {
    /**
     * <p>
     * Represents the title. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String title;
    /**
     * <p>
     * Represents the summary. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String summary;
    /**
     * <p>
     * Represents the content. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String content;
    /**
     * <p>
     * Represents the related help items. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private List<HelpItem> related;

    /**
     * Creates an instance of HelpItem.
     */
    public HelpItem() {
        // Empty
    }

    /**
     * Gets the title.
     *
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title
     *            the title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the summary.
     *
     * @return the summary.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary.
     *
     * @param summary
     *            the summary.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets the content.
     *
     * @return the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content.
     *
     * @param content
     *            the content.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the related help items.
     *
     * @return the related help items.
     */
    public List<HelpItem> getRelated() {
        return related;
    }

    /**
     * Sets the related help items.
     *
     * @param related
     *            the related help items.
     */
    public void setRelated(List<HelpItem> related) {
        this.related = related;
    }
}
