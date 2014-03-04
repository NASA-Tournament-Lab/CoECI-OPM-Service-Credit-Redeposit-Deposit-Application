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

package gov.opm.scrd.migration.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.opm.scrd.BasePersistenceTests;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.migration.OPMMigrationException;
import gov.opm.scrd.services.OPMConfigurationException;

import java.sql.Connection;

import javax.persistence.EntityManager;

import junit.framework.JUnit4TestAdapter;

import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link DataMigrationProcessorImpl} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 * @since OPM - Data Migration - Processor Module Assembly 1.0
 */
public class DataMigrationProcessorImplUnitTests extends BasePersistenceTests {
    /**
     * <p>
     * Represents the entity manager used in tests.
     * </p>
     */
    private static EntityManager entityManager;

    /**
     * <p>
     * Represents the <code>DataMigrationProcessorImpl</code> instance used in tests.
     * </p>
     */
    private DataMigrationProcessorImpl instance;

    /**
     * <p>
     * Represents the logger used in tests.
     * </p>
     */
    private Logger logger;

    /**
     * <p>
     * Represents the country name used in tests.
     * </p>
     */
    private String countryName = "US";

    /**
     * <p>
     * Represents the redeposit period type name used in tests.
     * </p>
     */
    private String redepositPeriodTypeName = "R";

    /**
     * <p>
     * Represents the deposit period type name used in tests.
     * </p>
     */
    private String depositPeriodTypeName = "D";

    /**
     * <p>
     * Represents the source database driver name used in tests.
     * </p>
     */
    private String sourceDatabaseDriverName = TestsHelper.SOURCE_DB_DRIVER;

    /**
     * <p>
     * Represents the source database url used in tests.
     * </p>
     */
    private String sourceDatabaseUrl = TestsHelper.SOURCE_DB_URL;

    /**
     * <p>
     * Represents the source database username used in tests.
     * </p>
     */
    private String sourceDatabaseUsername = TestsHelper.SOURCE_DB_USERNAME;

    /**
     * <p>
     * Represents the source database password used in tests.
     * </p>
     */
    private String sourceDatabasePassword = TestsHelper.SOURCE_DB_PASSWORD;

    /**
     * <p>
     * Represents the connection used in tests.
     * </p>
     */
    private Connection connection;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(DataMigrationProcessorImplUnitTests.class);
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

        connection = createConnection();

        entityManager = getEntityManager();

        logger = Logger.getLogger(getClass());

        instance = new DataMigrationProcessorImpl();
        TestsHelper.setField(instance, "destinationEntityManager", entityManager);
        instance.setLogger(logger);
        instance.setCountryName(countryName);
        instance.setRedepositPeriodTypeName(redepositPeriodTypeName);
        instance.setDepositPeriodTypeName(depositPeriodTypeName);
        instance.setSourceDatabaseDriverName(sourceDatabaseDriverName);
        instance.setSourceDatabaseUrl(sourceDatabaseUrl);
        instance.setSourceDatabaseUsername(sourceDatabaseUsername);
        instance.setSourceDatabasePassword(sourceDatabasePassword);
    }

    /**
     * <p>
     * Cleans up the unit tests.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();

        clearSourceDB(connection);
        connection.close();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>DataMigrationProcessorImpl()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new DataMigrationProcessorImpl();

        assertNull("'logger' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNull("'destinationEntityManager' should be correct.",
            TestsHelper.getField(instance, "destinationEntityManager"));
        assertNull("'countryName' should be correct.",
            TestsHelper.getField(instance, "countryName"));
        assertNull("'redepositPeriodTypeName' should be correct.",
            TestsHelper.getField(instance, "redepositPeriodTypeName"));
        assertNull("'depositPeriodTypeName' should be correct.",
            TestsHelper.getField(instance, "depositPeriodTypeName"));
        assertNull("'sourceDatabaseDriverName' should be correct.",
            TestsHelper.getField(instance, "sourceDatabaseDriverName"));
        assertNull("'sourceDatabaseUrl' should be correct.", TestsHelper.getField(instance, "sourceDatabaseUrl"));
        assertNull("'sourceDatabaseUsername' should be correct.",
            TestsHelper.getField(instance, "sourceDatabaseUsername"));
        assertNull("'sourceDatabasePassword' should be correct.",
            TestsHelper.getField(instance, "sourceDatabasePassword"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>checkInit()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_checkInit_1() throws Exception {
        instance.checkInit();

        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "destinationEntityManager"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "countryName"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "redepositPeriodTypeName"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "depositPeriodTypeName"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "sourceDatabaseDriverName"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "sourceDatabaseUrl"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "sourceDatabaseUsername"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "sourceDatabasePassword"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>checkInit()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_checkInit_2() throws Exception {
        instance.setLogger(null);
        instance.checkInit();

        assertNull("'checkInit' should be correct.", TestsHelper.getField(instance, "logger"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "destinationEntityManager"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "countryName"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "redepositPeriodTypeName"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "depositPeriodTypeName"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "sourceDatabaseDriverName"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "sourceDatabaseUrl"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "sourceDatabaseUsername"));
        assertNotNull("'checkInit' should be correct.", TestsHelper.getField(instance, "sourceDatabasePassword"));
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with destinationEntityManager is null.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_destinationEntityManagerNull() throws Exception {
        TestsHelper.setField(instance, "destinationEntityManager", null);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with countryName is null.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_countryNameNull() throws Exception {
        instance.setCountryName(null);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with countryName is empty.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_countryNameEmpty() throws Exception {
        instance.setCountryName(TestsHelper.EMPTY_STRING);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with redepositPeriodTypeName is null.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_redepositPeriodTypeNameNull() throws Exception {
        instance.setRedepositPeriodTypeName(null);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with redepositPeriodTypeName is empty.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_redepositPeriodTypeNameEmpty() throws Exception {
        instance.setRedepositPeriodTypeName(TestsHelper.EMPTY_STRING);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with depositPeriodTypeName is null.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_depositPeriodTypeNameNull() throws Exception {
        instance.setDepositPeriodTypeName(null);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with depositPeriodTypeName is empty.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_depositPeriodTypeNameEmpty() throws Exception {
        instance.setDepositPeriodTypeName(TestsHelper.EMPTY_STRING);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with sourceDatabaseDriverName is null.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_sourceDatabaseDriverNameNull() throws Exception {
        instance.setSourceDatabaseDriverName(null);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with sourceDatabaseDriverName is empty.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_sourceDatabaseDriverNameEmpty() throws Exception {
        instance.setSourceDatabaseDriverName(TestsHelper.EMPTY_STRING);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with sourceDatabaseUrl is null.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_sourceDatabaseUrlNull() throws Exception {
        instance.setSourceDatabaseUrl(null);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with sourceDatabaseUrl is empty.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_sourceDatabaseUrlEmpty() throws Exception {
        instance.setSourceDatabaseUrl(TestsHelper.EMPTY_STRING);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with sourceDatabaseUsername is null.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_sourceDatabaseUsernameNull() throws Exception {
        instance.setSourceDatabaseUsername(null);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with sourceDatabaseUsername is empty.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_sourceDatabaseUsernameEmpty() throws Exception {
        instance.setSourceDatabaseUsername(TestsHelper.EMPTY_STRING);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with sourceDatabasePassword is null.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_sourceDatabasePasswordNull() throws Exception {
        instance.setSourceDatabasePassword(null);

        instance.checkInit();
    }

    /**
     * <p>
     * Failure test for the method <code>checkInit()</code> with sourceDatabasePassword is empty.<br>
     * <code>OPMConfigurationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMConfigurationException.class)
    public void test_checkInit_sourceDatabasePasswordEmpty() throws Exception {
        instance.setSourceDatabasePassword(TestsHelper.EMPTY_STRING);

        instance.checkInit();
    }

    /**
     * <p>
     * Accuracy test for the method <code>migrate()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_migrate() throws Exception {
        clearSourceDB(connection);
        loadSourceData(connection);

        instance.migrate();

        checkLookupData("migrate");

        checkMiscellaneousData("migrate");

        checkUserData("migrate");

        checkAccountData("migrate");

        checkPaymentData("migrate");

        checkBatchData("migrate");
    }

    /**
     * <p>
     * Failure test for the method <code>migrate()</code> with an error occurs.<br>
     * <code>OPMMigrationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMMigrationException.class)
    public void test_migrate_Error1() throws Exception {
        instance.setSourceDatabaseDriverName("invalid_\n_driver");

        instance.migrate();
    }

    /**
     * <p>
     * Failure test for the method <code>migrate()</code> with an error occurs.<br>
     * <code>OPMMigrationException</code> is expected.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test(expected = OPMMigrationException.class)
    public void test_migrate_Error2() throws Exception {
        instance.setSourceDatabaseUrl("invalid_\n_url");

        instance.migrate();
    }
}
