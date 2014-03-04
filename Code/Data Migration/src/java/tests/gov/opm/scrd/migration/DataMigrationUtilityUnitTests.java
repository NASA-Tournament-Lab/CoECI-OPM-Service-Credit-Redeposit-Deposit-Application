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

import gov.opm.scrd.BasePersistenceTests;

import java.sql.Connection;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link DataMigrationUtility} class.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 * @since OPM - Data Migration - Processor Module Assembly 1.0
 */
public class DataMigrationUtilityUnitTests extends BasePersistenceTests {
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
        return new JUnit4TestAdapter(DataMigrationUtilityUnitTests.class);
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
     * Accuracy test for the method <code>main()</code>.<br>
     * The result should be correct.
     * </p>
     *
     * @throws Exception
     *             to JUnit.
     */
    @Test
    public void test_main() throws Exception {
        clearSourceDB(connection);
        loadSourceData(connection);

        DataMigrationUtility.main(new String[0]);

        checkLookupData("main");

        checkMiscellaneousData("main");

        checkUserData("main");

        checkAccountData("main");

        checkPaymentData("main");

        checkBatchData("main");
    }
}
