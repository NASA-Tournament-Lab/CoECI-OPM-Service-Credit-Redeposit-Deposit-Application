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

package gov.opm.scrd.migration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

/**
 * <p>
 * Unit tests for <code>{@link OPMMigrationException}</code> class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 * @since OPM - Data Migration - Processor Module Assembly 1.0
 */
public class OPMMigrationExceptionUnitTests {
    /**
     * <p>
     * Represents a detail message.
     * </p>
     */
    private static final String DETAIL_MESSAGE = "detail";

    /**
     * <p>
     * Represents an error cause.
     * </p>
     */
    private static final Throwable CAUSE = new Exception("UnitTests");

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(OPMMigrationExceptionUnitTests.class);
    }

    /**
     * <p>
     * <code>OPMMigrationException</code> should be subclass of <code>Exception</code>.
     * </p>
     */
    @Test
    public void testInheritance() {
        assertTrue("OPMMigrationException should be subclass of Exception.",
            OPMMigrationException.class.getSuperclass() == Exception.class);
    }

    /**
     * <p>
     * Tests accuracy of <code>OPMMigrationException()</code> constructor.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor1() {
        OPMMigrationException exception = new OPMMigrationException();

        // Verify the error message
        assertNull("Exception message should be null.", exception.getMessage());
        // Verify the error cause
        assertNull("Error cause should be null.", exception.getCause());
    }

    /**
     * <p>
     * Tests accuracy of <code>OPMMigrationException(String)</code> constructor.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor2() {
        OPMMigrationException exception =
            new OPMMigrationException(DETAIL_MESSAGE);

        // Verify the error message
        assertEquals("Exception message should be correct.", DETAIL_MESSAGE, exception.getMessage());
    }

    /**
     * <p>
     * Tests accuracy of <code>OPMMigrationException(Throwable)</code> constructor.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor3() {
        OPMMigrationException exception = new OPMMigrationException(CAUSE);

        // Verify the error cause
        assertSame("Error cause should be correct.", CAUSE, exception.getCause());
    }

    /**
     * <p>
     * Tests accuracy of <code>OPMMigrationException(String, Throwable)</code> constructor.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor4() {
        OPMMigrationException exception =
            new OPMMigrationException(DETAIL_MESSAGE, CAUSE);

        // Verify the error message
        assertEquals("Error message should be correct.", DETAIL_MESSAGE, exception.getMessage());
        // Verify the error cause
        assertSame("Error cause should be correct.", CAUSE, exception.getCause());
    }
}
