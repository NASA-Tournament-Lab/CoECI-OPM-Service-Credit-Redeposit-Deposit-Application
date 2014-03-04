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
import static org.junit.Assert.assertTrue;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.application.AuditParameterRecord;
import gov.opm.scrd.entities.application.AuditRecord;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link AuditServiceImpl} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class AuditServiceImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>AuditServiceImpl</code> instance used in tests.
     * </p>
     */
    private AuditServiceImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the entity used in tests.
     * </p>
     */
    private AuditRecord entity;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AuditServiceImplUnitTests.class);
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

        instance = new AuditServiceImpl();
        TestsHelper.setField(instance, "logger", logger);
        TestsHelper.setField(instance, "entityManager", entityManager);

        AuditParameterRecord auditParameterRecord = new AuditParameterRecord();
        auditParameterRecord.setItemId(1L);
        auditParameterRecord.setItemType("itemType1");
        auditParameterRecord.setPropertyName("propertyName1");
        auditParameterRecord.setPreviousValue("previousValue1");
        auditParameterRecord.setNewValue("newValue1");

        entity = new AuditRecord();
        entity.setUsername("username1");
        entity.setIpAddress("ipAddress1");
        entity.setActionName("actionName1");
        entity.setDate(new Date());
        entity.setParameters(Arrays.asList(auditParameterRecord));
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AuditServiceImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AuditServiceImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'entityManager' should be correct.", TestsHelper.getField(instance, "entityManager"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>audit(AuditRecord entity)</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_audit() throws Exception {
        entityManager.getTransaction().begin();
        instance.audit(entity);
        entityManager.getTransaction().commit();
        entityManager.clear();

        assertTrue("'audit' should be correct.", entity.getId() > 0);

        AuditRecord retrievedAuditRecord = entityManager.find(AuditRecord.class, entity.getId());

        assertFalse("'audit' should be correct.", retrievedAuditRecord.isDeleted());
        assertEquals("'audit' should be correct.", entity.getUsername(), retrievedAuditRecord.getUsername());
        assertEquals("'audit' should be correct.", entity.getIpAddress(), retrievedAuditRecord.getIpAddress());
        assertEquals("'audit' should be correct.", entity.getActionName(), retrievedAuditRecord.getActionName());
        assertNotNull("'audit' should be correct.", retrievedAuditRecord.getDate());
        assertEquals("'audit' should be correct.",
            entity.getParameters().size(), retrievedAuditRecord.getParameters().size());

        AuditParameterRecord parameter = entity.getParameters().get(0);
        AuditParameterRecord retrievedParameter = retrievedAuditRecord.getParameters().get(0);

        assertTrue("'audit' should be correct.", retrievedParameter.getId() > 0);

        assertFalse("'audit' should be correct.", retrievedParameter.isDeleted());
        assertEquals("'audit' should be correct.", parameter.getItemId(), retrievedParameter.getItemId());
        assertEquals("'audit' should be correct.", parameter.getItemType(), retrievedParameter.getItemType());
        assertEquals("'audit' should be correct.", parameter.getPropertyName(), retrievedParameter.getPropertyName());
        assertEquals("'audit' should be correct.", parameter.getPreviousValue(), retrievedParameter.getPreviousValue());
        assertEquals("'audit' should be correct.", parameter.getNewValue(), retrievedParameter.getNewValue());
    }

    /**
     * <p>
     * Failure test for the method <code>audit(AuditRecord entity)</code>
     * with entity is null.<br>
     * <code>IllegalArgumentException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_audit_entityNull() throws Exception {
        instance.audit(null);
    }
}
