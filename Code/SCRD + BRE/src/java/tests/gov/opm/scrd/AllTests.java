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

package gov.opm.scrd;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * <p>
 * This test case aggregates all test cases.
 * </p>
 *
 * <p>
 * <em>Changes in version 1.1</em>
 * <ol>
 * <li>Added test suites for Validation, Deduction, Interest and integration tests for Business rules engine</li>
 * </ol>
 * </p>
 * @author sparemax, Schpotsky
 * @version 1.1
 */
public class AllTests extends TestCase {
    /**
     * <p>
     * All test cases.
     * </p>
     *
     * @return The test suite.
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite();

        suite.addTest(DeductionValidationTest.suite());
        suite.addTest(DeductionTest.suite());
        suite.addTest(InterestTest.suite());
        suite.addTest(IntegrationTest.suite());
        
        // unit tests
        suite.addTest(UnitTests.suite());

        return suite;
    }

}
