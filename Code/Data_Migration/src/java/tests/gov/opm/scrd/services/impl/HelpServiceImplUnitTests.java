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
import static org.junit.Assert.assertNull;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.HelpItem;

import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link HelpServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class HelpServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>HelpServiceImpl</code> instance used in tests.
     * </p>
     */
    private HelpServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the term used in tests.
     * </p>
     */
    private String term = "term1";

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(HelpServiceImplUnitTests.class);
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

        instance = new HelpServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>HelpServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new HelpServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(String term)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_1() throws Exception {
        clearDB();

        List<HelpItem> res = instance.search(term);

        assertEquals("'search' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(String term)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_2() throws Exception {
        HelpItem item1 = new HelpItem();
        item1.setTitle("title1 term1");
        item1.setSummary("summary1");
        item1.setContent("content1");
        create(item1);

        HelpItem item2 = new HelpItem();
        item2.setTitle("title2");
        item2.setSummary("summary2 term1");
        item2.setContent("content2");
        create(item2);

        List<HelpItem> res = instance.search(term);

        assertEquals("'search' should be correct.", 2, res.size());

        HelpItem entity1 = res.get(0);
        HelpItem entity2 = res.get(1);
        if (entity1.getTitle().equals(item2.getTitle())) {
            entity1 = res.get(1);
            entity2 = res.get(0);
        }

        assertEquals("'search' should be correct.", item1.getTitle(), entity1.getTitle());
        assertEquals("'search' should be correct.", item1.getSummary(), entity1.getSummary());
        assertNull("'search' should be correct.", entity1.getContent());

        assertEquals("'search' should be correct.", item2.getTitle(), entity2.getTitle());
        assertEquals("'search' should be correct.", item2.getSummary(), entity2.getSummary());
        assertNull("'search' should be correct.", entity2.getContent());
    }

    /**
     * <p>
     * Accuracy test for the method <code>search(String term)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_search_3() throws Exception {
        HelpItem item1 = new HelpItem();
        item1.setTitle("title1 term1");
        item1.setSummary("summary1");
        item1.setContent("content1");
        create(item1);

        HelpItem item2 = new HelpItem();
        item2.setDeleted(true);
        item2.setTitle("title2");
        item2.setSummary("summary2 term1");
        item2.setContent("content2");
        create(item2);

        List<HelpItem> res = instance.search(term);

        assertEquals("'search' should be correct.", 1, res.size());

        HelpItem entity1 = res.get(0);
        assertEquals("'search' should be correct.", item1.getTitle(), entity1.getTitle());
        assertEquals("'search' should be correct.", item1.getSummary(), entity1.getSummary());
        assertNull("'search' should be correct.", entity1.getContent());
    }

    /**
     * <p>
     * Failure test for the method <code>search(String term)</code>
     * with term is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_search_termNull() throws Exception {
        instance.search(null);
    }

    /**
     * <p>
     * Failure test for the method <code>search(String term)</code>
     * with term is empty.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_search_termEmpty() throws Exception {
        instance.search(TestsHelper.EMPTY_STRING);
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long helpItemId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_1() throws Exception {
        clearDB();

        HelpItem res = instance.get(Long.MAX_VALUE);

        assertNull("'get' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long helpItemId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_2() throws Exception {
        HelpItem item1 = new HelpItem();
        item1.setDeleted(true);
        item1.setTitle("title1 term1");
        item1.setSummary("summary1");
        item1.setContent("content1");
        create(item1);

        HelpItem res = instance.get(item1.getId());

        assertNull("'get' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long helpItemId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_3() throws Exception {
        HelpItem item1 = new HelpItem();
        item1.setTitle("title1 term1");
        item1.setSummary("summary1");
        item1.setContent("content1");
        create(item1);

        HelpItem res = instance.get(item1.getId());

        assertEquals("'get' should be correct.", item1.getTitle(), res.getTitle());
        assertEquals("'get' should be correct.", item1.getSummary(), res.getSummary());
        assertEquals("'get' should be correct.", item1.getContent(), res.getContent());
    }

    /**
     * <p>
     * Failure test for the method <code>get(long helpItemId)</code>
     * with helpItemId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_get_helpItemIdNegative() throws Exception {
        instance.get(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>get(long helpItemId)</code>
     * with helpItemId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_get_helpItemIdZero() throws Exception {
        instance.get(0);
    }
}
