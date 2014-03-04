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

import static org.junit.Assert.assertEquals;
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link BasePagedSearchParameters} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class BasePagedSearchParametersUnitTests {
    /**
     * <p>
     * Represents the <code>BasePagedSearchParameters</code> instance used in tests.
     * </p>
     */
    private BasePagedSearchParameters instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BasePagedSearchParametersUnitTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Before
    public void setUp() throws Exception {
        instance = new MockBasePagedSearchParameters();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>BasePagedSearchParameters()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new MockBasePagedSearchParameters();

        assertEquals("'pageNumber' should be correct.", 0, TestsHelper.getField(instance, "pageNumber"));
        assertEquals("'pageSize' should be correct.", 0, TestsHelper.getField(instance, "pageSize"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getPageNumber()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPageNumber() {
        int value = 1;
        instance.setPageNumber(value);

        assertEquals("'getPageNumber' should be correct.",
            value, instance.getPageNumber());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPageNumber(int pageNumber)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPageNumber() {
        int value = 1;
        instance.setPageNumber(value);

        assertEquals("'setPageNumber' should be correct.",
            value, TestsHelper.getField(instance, "pageNumber"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPageSize()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getPageSize() {
        int value = 1;
        instance.setPageSize(value);

        assertEquals("'getPageSize' should be correct.",
            value, instance.getPageSize());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPageSize(int pageSize)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setPageSize() {
        int value = 1;
        instance.setPageSize(value);

        assertEquals("'setPageSize' should be correct.",
            value, TestsHelper.getField(instance, "pageSize"));
    }
}
