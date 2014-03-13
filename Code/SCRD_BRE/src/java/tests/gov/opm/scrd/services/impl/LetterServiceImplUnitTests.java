/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.Letter;
import gov.opm.scrd.services.EntityNotFoundException;

import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link LetterServiceImpl} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 * @since OPM - SCRD - Reporting Initial Module Assembly 1.0
 */
public class LetterServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>LetterServiceImpl</code> instance used in tests.
     * </p>
     */
    private LetterServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the letter used in tests.
     * </p>
     */
    private Letter letter;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(LetterServiceImplUnitTests.class);
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

        instance = new LetterServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);

        letter = getLetter();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>LetterServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new LetterServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>create(Letter letter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_create() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(letter);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertTrue("'create' should be correct.", letter.getId() > 0);

        Letter retrievedLetter = entityManager.find(Letter.class, letter.getId());

        assertFalse("'create' should be correct.", retrievedLetter.isDeleted());
        assertEquals("'create' should be correct.", letter.getName(), retrievedLetter.getName());
        assertEquals("'create' should be correct.", letter.getContent(), retrievedLetter.getContent());
    }

    /**
     * <p>
     * Failure test for the method <code>create(Letter letter)</code> with letter is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_create_letterNull() throws Exception {
        instance.create(null);
    }

    /**
     * <p>
     * Accuracy test for the method <code>update(Letter letter)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_update() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(letter);
        entityManager.getTransaction().commit();
        entityManager.clear();

        letter.setName("new");
        entityManager.getTransaction().begin();
        instance.update(letter);
        entityManager.getTransaction().commit();

        Letter retrievedLetter = entityManager.find(Letter.class, letter.getId());

        assertEquals("'update' should be correct.", letter.getName(), retrievedLetter.getName());
    }

    /**
     * <p>
     * Failure test for the method <code>update(Letter letter)</code> with letter is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_update_letterNull() throws Exception {
        instance.update(null);
    }

    /**
     * <p>
     * Failure test for the method <code>update(Letter letter)</code> with letter doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_update_letterNotExist() throws Exception {
        letter.setId(Long.MAX_VALUE);

        instance.update(letter);
    }

    /**
     * <p>
     * Accuracy test for the method <code>delete(long letterId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_delete() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(letter);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        instance.delete(letter.getId());
        entityManager.getTransaction().commit();

        Letter retrievedLetter = entityManager.find(Letter.class, letter.getId());

        assertTrue("'delete' should be correct.", retrievedLetter.isDeleted());
    }

    /**
     * <p>
     * Failure test for the method <code>delete(long letterId)</code> with letterId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_delete_letterIdNegative() throws Exception {
        instance.delete(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>delete(long letterId)</code> with letterId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_delete_letterIdZero() throws Exception {
        instance.delete(0);
    }

    /**
     * <p>
     * Failure test for the method <code>delete(long letterId)</code> with letter doesn't exist.<br>
     * <code>EntityNotFoundException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = EntityNotFoundException.class)
    public void test_delete_letterNotExist() throws Exception {
        instance.delete(Long.MAX_VALUE);
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long letterId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_1() throws Exception {
        Letter res = instance.get(Long.MAX_VALUE);

        assertNull("'get' should be correct.", res);
    }

    /**
     * <p>
     * Accuracy test for the method <code>get(long letterId)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_get_2() throws Exception {
        entityManager.getTransaction().begin();
        instance.create(letter);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Letter res = instance.get(letter.getId());

        assertFalse("'get' should be correct.", res.isDeleted());
        assertEquals("'get' should be correct.", letter.getName(), res.getName());
        assertEquals("'get' should be correct.", letter.getContent(), res.getContent());
    }

    /**
     * <p>
     * Failure test for the method <code>get(long letterId)</code> with letterId is negative.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_get_letterIdNegative() throws Exception {
        instance.get(-1);
    }

    /**
     * <p>
     * Failure test for the method <code>get(long letterId)</code> with letterId is zero.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_get_letterIdZero() throws Exception {
        instance.get(0);
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAll()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getAll_1() throws Exception {
        clearDB();

        List<Letter> res = instance.getAll();

        assertEquals("'getAll' should be correct.", 0, res.size());
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAll()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_getAll_2() throws Exception {
        Letter letter2 = new Letter();
        letter2.setName("name2");
        letter2.setContent("content2");

        Letter letter3 = new Letter();
        letter3.setDeleted(true);
        letter3.setName("name2");
        letter3.setContent("content2");

        entityManager.getTransaction().begin();
        instance.create(letter);
        instance.create(letter2);
        instance.create(letter3);
        entityManager.getTransaction().commit();
        entityManager.clear();

        List<Letter> res = instance.getAll();

        assertEquals("'getAll' should be correct.", 2, res.size());

        Letter retrievedLetter1 = res.get(0);
        Letter retrievedLetter2 = res.get(1);
        if ("name2".equals(retrievedLetter1.getName())) {
            retrievedLetter1 = res.get(1);
            retrievedLetter2 = res.get(0);
        }

        assertFalse("'getAll' should be correct.", retrievedLetter1.isDeleted());
        assertEquals("'getAll' should be correct.", letter.getName(), retrievedLetter1.getName());
        assertNull("'getAll' should be correct.", retrievedLetter1.getContent());

        assertFalse("'getAll' should be correct.", retrievedLetter2.isDeleted());
        assertEquals("'getAll' should be correct.", letter2.getName(), retrievedLetter2.getName());
        assertNull("'getAll' should be correct.", retrievedLetter2.getContent());
    }
}