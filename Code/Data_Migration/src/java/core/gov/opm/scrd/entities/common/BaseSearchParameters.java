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

/**
 * <p>
 * Represents the base search parameters for sorting.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public abstract class BaseSearchParameters {
    /**
     * <p>
     * Represents the column to sort on. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String sortColumn;
    /**
     * <p>
     * Represents the sort order. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private SortOrder sortOrder;

    /**
     * Creates an instance of BaseSearchParameters.
     */
    protected BaseSearchParameters() {
        // Empty
    }

    /**
     * Gets the column to sort on.
     *
     * @return the column to sort on.
     */
    public String getSortColumn() {
        return sortColumn;
    }

    /**
     * Sets the column to sort on.
     *
     * @param sortColumn
     *            the column to sort on.
     */
    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    /**
     * Gets the sort order.
     *
     * @return the sort order.
     */
    public SortOrder getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the sort order.
     *
     * @param sortOrder
     *            the sort order.
     */
    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Converts the entity to a string.
     *
     * @return the string with entity data.
     */
    @Override
    public String toString() {
        return Helper.toString(this);
    }
}
