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
 * Represents base search parameters that add paging.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public abstract class BasePagedSearchParameters extends BaseSearchParameters {
    /**
     * <p>
     * Represents the page number (1-based). It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private int pageNumber;
    /**
     * <p>
     * Represents the page size. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private int pageSize;

    /**
     * Creates an instance of BasePagedSearchParameters.
     */
    protected BasePagedSearchParameters() {
        // Empty
    }

    /**
     * Gets the page number (1-based).
     *
     * @return the page number (1-based).
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the page number (1-based).
     *
     * @param pageNumber
     *            the page number (1-based).
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Gets the page size.
     *
     * @return the page size.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize
     *            the page size.
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
