/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import gov.opm.scrd.TestsHelper;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link Letter} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 */
public class LetterUnitTests {
    /**
     * <p>
     * Represents the <code>Letter</code> instance used in tests.
     * </p>
     */
    private Letter instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(LetterUnitTests.class);
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
        instance = new Letter();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Letter()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Letter();

        assertNull("'content' should be correct.", TestsHelper.getField(instance, "content"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getContent()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getContent() {
        String value = "new_value";
        instance.setContent(value);

        assertEquals("'getContent' should be correct.",
            value, instance.getContent());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setContent(String content)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setContent() {
        String value = "new_value";
        instance.setContent(value);

        assertEquals("'setContent' should be correct.",
            value, TestsHelper.getField(instance, "content"));
    }
}