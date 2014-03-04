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

package gov.opm.scrd.entities.common;

import java.util.List;

/**
 * <p>
 * Represents the sortable and paged search results.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 *
 * @param <T>
 *            the data type
 */
public class SearchResult<T> extends BasePagedSearchParameters {
    /**
     * <p>
     * Represents the total count of records. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private int total;
    /**
     * <p>
     * Represents the total count of pages. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private int totalPageCount;
    /**
     * <p>
     * Represents the retrieved records. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private List<T> items;

    /**
     * Creates an instance of SearchResult.
     */
    public SearchResult() {
        // Empty
    }

    /**
     * Gets the total count of records.
     *
     * @return the total count of records.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the total count of records.
     *
     * @param total
     *            the total count of records.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Gets the total count of pages.
     *
     * @return the total count of pages.
     */
    public int getTotalPageCount() {
        return totalPageCount;
    }

    /**
     * Sets the total count of pages.
     *
     * @param totalPageCount
     *            the total count of pages.
     */
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    /**
     * Gets the retrieved records.
     *
     * @return the retrieved records.
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * Sets the retrieved records.
     *
     * @param items
     *            the retrieved records.
     */
    public void setItems(List<T> items) {
        this.items = items;
    }
}
