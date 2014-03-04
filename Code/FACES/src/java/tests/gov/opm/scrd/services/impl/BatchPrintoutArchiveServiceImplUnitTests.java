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

package gov.opm.scrd.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.Printout;
import gov.opm.scrd.services.OPMException;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link BatchPrintoutArchiveServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class BatchPrintoutArchiveServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>BatchPrintoutArchiveServiceImpl</code> instance used in tests.
     * </p>
     */
    private BatchPrintoutArchiveServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the name used in tests.
     * </p>
     */
    private String name = "name1";

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BatchPrintoutArchiveServiceImplUnitTests.class);
    }

    /**
     * <p>
     * Sets up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        entityManager = getEntityManager();

        logger = Logger.getLogger(getClass());

        instance = new BatchPrintoutArchiveServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>BatchPrintoutArchiveServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new BatchPrintoutArchiveServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAvailablePrintouts()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getAvailablePrintouts_1() throws Exception {
        clearDB();

        List<Printout> res = instance.getAvailablePrintouts();

        assertEquals("'getAvailablePrintouts' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAvailablePrintouts()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getAvailablePrintouts_2() throws Exception {
        Printout printout1 = new Printout();
        printout1.setName("name1");
        printout1.setPrintDate(new Date());
        printout1.setContent("content1".getBytes());
        create(printout1);

        Printout printout2 = new Printout();
        printout2.setName("name2");
        printout2.setPrintDate(new Date());
        printout2.setContent("content2".getBytes());
        create(printout2);

        Printout printout3 = new Printout();
        printout3.setDeleted(true);
        printout3.setName("name2");
        printout3.setPrintDate(new Date());
        printout3.setContent("content2".getBytes());
        create(printout3);

        List<Printout> res = instance.getAvailablePrintouts();

        assertEquals("'getAvailablePrintouts' should be correct.", 2, res.size());

        Printout retrievedPrintout1 = res.get(0);
        Printout retrievedPrintout2 = res.get(1);
        if (retrievedPrintout1.getName().equals(printout2.getName())) {
            retrievedPrintout1 = res.get(1);
            retrievedPrintout2 = res.get(0);
        }

        assertFalse("'getAvailablePrintouts' should be correct.", retrievedPrintout1.isDeleted());
        assertEquals("'getAvailablePrintouts' should be correct.", printout1.getName(), retrievedPrintout1.getName());
        assertNotNull("'getAvailablePrintouts' should be correct.", retrievedPrintout1.getPrintDate());
        assertNull("'getAvailablePrintouts' should be correct.", retrievedPrintout1.getContent());

        assertFalse("'getAvailablePrintouts' should be correct.", retrievedPrintout2.isDeleted());
        assertEquals("'getAvailablePrintouts' should be correct.", printout2.getName(), retrievedPrintout2.getName());
        assertNotNull("'getAvailablePrintouts' should be correct.", retrievedPrintout2.getPrintDate());
        assertNull("'getAvailablePrintouts' should be correct.", retrievedPrintout2.getContent());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPrintout(String name)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getPrintout_1() throws Exception {
        Printout printout1 = new Printout();
        printout1.setName("name1");
        printout1.setPrintDate(new Date());
        printout1.setContent("content1".getBytes());
        create(printout1);

        byte[] res = instance.getPrintout(name);

        assertEquals("'getPrintout' should be correct.", new String(printout1.getContent()), new String(res));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPrintout(String name)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getPrintout_2() throws Exception {
        Printout printout1 = new Printout();
        printout1.setName("name1");
        printout1.setPrintDate(new Date());
        printout1.setContent(null);
        create(printout1);

        byte[] res = instance.getPrintout(name);

        assertNull("'getPrintout' should be correct.", res);
    }

    /**
     * <p>
     * Failure test for the method <code>getPrintout(String name)</code>
     * with lookupType is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPrintout_lookupTypeNull() throws Exception {
        instance.getPrintout(null);
    }

    /**
     * <p>
     * Failure test for the method <code>getPrintout(String name)</code>
     * with lookupType is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPrintout_lookupTypeEmpty() throws Exception {
        instance.getPrintout(TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Failure test for the method <code>getPrintout(String name)</code>
     * with no printout is found.<br>
     * <code>OPMException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMException.class)
    public void test_getPrintout_NoPrintout1() throws Exception {
        clearDB();

        instance.getPrintout("not_exist");
    }

    /**
     * <p>
     * Failure test for the method <code>getPrintout(String name)</code>
     * with no printout is found.<br>
     * <code>OPMException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMException.class)
    public void test_getPrintout_NoPrintout2() throws Exception {
        Printout printout1 = new Printout();
        printout1.setDeleted(true);
        printout1.setName("name1");
        printout1.setPrintDate(new Date());
        printout1.setContent("content1".getBytes());
        create(printout1);

        instance.getPrintout(printout1.getName());
    }
}
