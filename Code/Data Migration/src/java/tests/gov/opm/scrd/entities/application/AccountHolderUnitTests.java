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

package gov.opm.scrd.entities.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import gov.opm.scrd.TestsHelper;
import gov.opm.scrd.entities.lookup.State;
import gov.opm.scrd.entities.lookup.Suffix;

import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link AccountHolder} class.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0):</em>
 * <ul>
 * <li>Added test cases for fields: position, agencyCode</li>
 * </ul>
 * </p>
 *
 * @author sparemax
 * @version 1.1
 */
public class AccountHolderUnitTests {
    /**
     * <p>
     * Represents the <code>AccountHolder</code> instance used in tests.
     * </p>
     */
    private AccountHolder instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AccountHolderUnitTests.class);
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
        instance = new AccountHolder();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>AccountHolder()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new AccountHolder();

        assertNull("'lastName' should be correct.", TestsHelper.getField(instance, "lastName"));
        assertNull("'firstName' should be correct.", TestsHelper.getField(instance, "firstName"));
        assertNull("'middleInitial' should be correct.", TestsHelper.getField(instance, "middleInitial"));
        assertNull("'suffix' should be correct.", TestsHelper.getField(instance, "suffix"));
        assertNull("'birthDate' should be correct.", TestsHelper.getField(instance, "birthDate"));
        assertNull("'ssn' should be correct.", TestsHelper.getField(instance, "ssn"));
        assertNull("'telephone' should be correct.", TestsHelper.getField(instance, "telephone"));
        assertNull("'email' should be correct.", TestsHelper.getField(instance, "email"));
        assertNull("'title' should be correct.", TestsHelper.getField(instance, "title"));
        assertNull("'departmentCode' should be correct.", TestsHelper.getField(instance, "departmentCode"));
        assertNull("'address' should be correct.", TestsHelper.getField(instance, "address"));
        assertNull("'geoCode' should be correct.", TestsHelper.getField(instance, "geoCode"));
        assertNull("'cityOfEmployment' should be correct.", TestsHelper.getField(instance, "cityOfEmployment"));
        assertNull("'stateOfEmployment' should be correct.", TestsHelper.getField(instance, "stateOfEmployment"));
        assertNull("'position' should be correct.", TestsHelper.getField(instance, "position"));
        assertNull("'agencyCode' should be correct.", TestsHelper.getField(instance, "agencyCode"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getLastName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getLastName() {
        String value = "new_value";
        instance.setLastName(value);

        assertEquals("'getLastName' should be correct.",
            value, instance.getLastName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setLastName(String lastName)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setLastName() {
        String value = "new_value";
        instance.setLastName(value);

        assertEquals("'setLastName' should be correct.",
            value, TestsHelper.getField(instance, "lastName"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getFirstName()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getFirstName() {
        String value = "new_value";
        instance.setFirstName(value);

        assertEquals("'getFirstName' should be correct.",
            value, instance.getFirstName());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setFirstName(String firstName)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setFirstName() {
        String value = "new_value";
        instance.setFirstName(value);

        assertEquals("'setFirstName' should be correct.",
            value, TestsHelper.getField(instance, "firstName"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getMiddleInitial()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getMiddleInitial() {
        String value = "new_value";
        instance.setMiddleInitial(value);

        assertEquals("'getMiddleInitial' should be correct.",
            value, instance.getMiddleInitial());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setMiddleInitial(String middleInitial)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setMiddleInitial() {
        String value = "new_value";
        instance.setMiddleInitial(value);

        assertEquals("'setMiddleInitial' should be correct.",
            value, TestsHelper.getField(instance, "middleInitial"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSuffix()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSuffix() {
        Suffix value = new Suffix();
        instance.setSuffix(value);

        assertSame("'getSuffix' should be correct.",
            value, instance.getSuffix());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSuffix(Suffix suffix)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSuffix() {
        Suffix value = new Suffix();
        instance.setSuffix(value);

        assertSame("'setSuffix' should be correct.",
            value, TestsHelper.getField(instance, "suffix"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getBirthDate()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getBirthDate() {
        Date value = new Date();
        instance.setBirthDate(value);

        assertSame("'getBirthDate' should be correct.",
            value, instance.getBirthDate());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setBirthDate(Date birthDate)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setBirthDate() {
        Date value = new Date();
        instance.setBirthDate(value);

        assertSame("'setBirthDate' should be correct.",
            value, TestsHelper.getField(instance, "birthDate"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getSsn()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getSsn() {
        String value = "new_value";
        instance.setSsn(value);

        assertEquals("'getSsn' should be correct.",
            value, instance.getSsn());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setSsn(String ssn)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setSsn() {
        String value = "new_value";
        instance.setSsn(value);

        assertEquals("'setSsn' should be correct.",
            value, TestsHelper.getField(instance, "ssn"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTelephone()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTelephone() {
        String value = "new_value";
        instance.setTelephone(value);

        assertEquals("'getTelephone' should be correct.",
            value, instance.getTelephone());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTelephone(String telephone)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTelephone() {
        String value = "new_value";
        instance.setTelephone(value);

        assertEquals("'setTelephone' should be correct.",
            value, TestsHelper.getField(instance, "telephone"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getEmail()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getEmail() {
        String value = "new_value";
        instance.setEmail(value);

        assertEquals("'getEmail' should be correct.",
            value, instance.getEmail());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setEmail(String email)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setEmail() {
        String value = "new_value";
        instance.setEmail(value);

        assertEquals("'setEmail' should be correct.",
            value, TestsHelper.getField(instance, "email"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getTitle()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getTitle() {
        String value = "new_value";
        instance.setTitle(value);

        assertEquals("'getTitle' should be correct.",
            value, instance.getTitle());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setTitle(String title)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setTitle() {
        String value = "new_value";
        instance.setTitle(value);

        assertEquals("'setTitle' should be correct.",
            value, TestsHelper.getField(instance, "title"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getDepartmentCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getDepartmentCode() {
        String value = "new_value";
        instance.setDepartmentCode(value);

        assertEquals("'getDepartmentCode' should be correct.",
            value, instance.getDepartmentCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setDepartmentCode(String departmentCode)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setDepartmentCode() {
        String value = "new_value";
        instance.setDepartmentCode(value);

        assertEquals("'setDepartmentCode' should be correct.",
            value, TestsHelper.getField(instance, "departmentCode"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAddress()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getAddress() {
        Address value = new Address();
        instance.setAddress(value);

        assertSame("'getAddress' should be correct.",
            value, instance.getAddress());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAddress(Address address)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setAddress() {
        Address value = new Address();
        instance.setAddress(value);

        assertSame("'setAddress' should be correct.",
            value, TestsHelper.getField(instance, "address"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getGeoCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getGeoCode() {
        String value = "new_value";
        instance.setGeoCode(value);

        assertEquals("'getGeoCode' should be correct.",
            value, instance.getGeoCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setGeoCode(String geoCode)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setGeoCode() {
        String value = "new_value";
        instance.setGeoCode(value);

        assertEquals("'setGeoCode' should be correct.",
            value, TestsHelper.getField(instance, "geoCode"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCityOfEmployment()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCityOfEmployment() {
        String value = "new_value";
        instance.setCityOfEmployment(value);

        assertEquals("'getCityOfEmployment' should be correct.",
            value, instance.getCityOfEmployment());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCityOfEmployment(String cityOfEmployment)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCityOfEmployment() {
        String value = "new_value";
        instance.setCityOfEmployment(value);

        assertEquals("'setCityOfEmployment' should be correct.",
            value, TestsHelper.getField(instance, "cityOfEmployment"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStateOfEmployment()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStateOfEmployment() {
        State value = new State();
        instance.setStateOfEmployment(value);

        assertSame("'getStateOfEmployment' should be correct.",
            value, instance.getStateOfEmployment());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStateOfEmployment(State stateOfEmployment)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStateOfEmployment() {
        State value = new State();
        instance.setStateOfEmployment(value);

        assertSame("'setStateOfEmployment' should be correct.",
            value, TestsHelper.getField(instance, "stateOfEmployment"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getPosition()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getPosition() {
        String value = "new_value";
        instance.setPosition(value);

        assertEquals("'getPosition' should be correct.",
            value, instance.getPosition());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setPosition(String position)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setPosition() {
        String value = "new_value";
        instance.setPosition(value);

        assertEquals("'setPosition' should be correct.",
            value, TestsHelper.getField(instance, "position"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getAgencyCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_getAgencyCode() {
        String value = "new_value";
        instance.setAgencyCode(value);

        assertEquals("'getAgencyCode' should be correct.",
            value, instance.getAgencyCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setAgencyCode(String agencyCode)</code>.<br>
     * The value should be properly set.
     * </p>
     *
     * @since 1.1 (OPM - Data Migration - Entities Update Module Assembly 1.0)
     */
    @Test
    public void test_setAgencyCode() {
        String value = "new_value";
        instance.setAgencyCode(value);

        assertEquals("'setAgencyCode' should be correct.",
            value, TestsHelper.getField(instance, "agencyCode"));
    }
}
