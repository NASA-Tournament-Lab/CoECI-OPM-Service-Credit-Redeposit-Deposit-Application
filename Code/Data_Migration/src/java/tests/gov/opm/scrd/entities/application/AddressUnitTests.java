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
import gov.opm.scrd.entities.lookup.Country;
import gov.opm.scrd.entities.lookup.State;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link Address} class.
 * </p>
 *
 * @author sparemax
 * @version 1.0
 */
public class AddressUnitTests {
    /**
     * <p>
     * Represents the <code>Address</code> instance used in tests.
     * </p>
     */
    private Address instance;

    /**
     * <p>
     * Adapter for earlier versions of JUnit.
     * </p>
     *
     * @return a test suite.
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AddressUnitTests.class);
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
        instance = new Address();
    }

    /**
     * <p>
     * Accuracy test for the constructor <code>Address()</code>.<br>
     * Instance should be correctly created.
     * </p>
     */
    @Test
    public void testCtor() {
        instance = new Address();

        assertNull("'street1' should be correct.", TestsHelper.getField(instance, "street1"));
        assertNull("'street2' should be correct.", TestsHelper.getField(instance, "street2"));
        assertNull("'street3' should be correct.", TestsHelper.getField(instance, "street3"));
        assertNull("'street4' should be correct.", TestsHelper.getField(instance, "street4"));
        assertNull("'street5' should be correct.", TestsHelper.getField(instance, "street5"));
        assertNull("'city' should be correct.", TestsHelper.getField(instance, "city"));
        assertNull("'state' should be correct.", TestsHelper.getField(instance, "state"));
        assertNull("'zipCode' should be correct.", TestsHelper.getField(instance, "zipCode"));
        assertNull("'country' should be correct.", TestsHelper.getField(instance, "country"));
    }


    /**
     * <p>
     * Accuracy test for the method <code>getStreet1()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStreet1() {
        String value = "new_value";
        instance.setStreet1(value);

        assertEquals("'getStreet1' should be correct.",
            value, instance.getStreet1());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStreet1(String street1)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStreet1() {
        String value = "new_value";
        instance.setStreet1(value);

        assertEquals("'setStreet1' should be correct.",
            value, TestsHelper.getField(instance, "street1"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStreet2()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStreet2() {
        String value = "new_value";
        instance.setStreet2(value);

        assertEquals("'getStreet2' should be correct.",
            value, instance.getStreet2());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStreet2(String street2)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStreet2() {
        String value = "new_value";
        instance.setStreet2(value);

        assertEquals("'setStreet2' should be correct.",
            value, TestsHelper.getField(instance, "street2"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStreet3()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStreet3() {
        String value = "new_value";
        instance.setStreet3(value);

        assertEquals("'getStreet3' should be correct.",
            value, instance.getStreet3());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStreet3(String street3)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStreet3() {
        String value = "new_value";
        instance.setStreet3(value);

        assertEquals("'setStreet3' should be correct.",
            value, TestsHelper.getField(instance, "street3"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStreet4()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStreet4() {
        String value = "new_value";
        instance.setStreet4(value);

        assertEquals("'getStreet4' should be correct.",
            value, instance.getStreet4());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStreet4(String street4)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStreet4() {
        String value = "new_value";
        instance.setStreet4(value);

        assertEquals("'setStreet4' should be correct.",
            value, TestsHelper.getField(instance, "street4"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getStreet5()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getStreet5() {
        String value = "new_value";
        instance.setStreet5(value);

        assertEquals("'getStreet5' should be correct.",
            value, instance.getStreet5());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setStreet5(String street5)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setStreet5() {
        String value = "new_value";
        instance.setStreet5(value);

        assertEquals("'setStreet5' should be correct.",
            value, TestsHelper.getField(instance, "street5"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCity()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCity() {
        String value = "new_value";
        instance.setCity(value);

        assertEquals("'getCity' should be correct.",
            value, instance.getCity());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCity(String city)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCity() {
        String value = "new_value";
        instance.setCity(value);

        assertEquals("'setCity' should be correct.",
            value, TestsHelper.getField(instance, "city"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getState()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getState() {
        State value = new State();
        instance.setState(value);

        assertSame("'getState' should be correct.",
            value, instance.getState());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setState(State state)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setState() {
        State value = new State();
        instance.setState(value);

        assertSame("'setState' should be correct.",
            value, TestsHelper.getField(instance, "state"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getZipCode()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getZipCode() {
        String value = "new_value";
        instance.setZipCode(value);

        assertEquals("'getZipCode' should be correct.",
            value, instance.getZipCode());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setZipCode(String zipCode)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setZipCode() {
        String value = "new_value";
        instance.setZipCode(value);

        assertEquals("'setZipCode' should be correct.",
            value, TestsHelper.getField(instance, "zipCode"));
    }

    /**
     * <p>
     * Accuracy test for the method <code>getCountry()</code>.<br>
     * The value should be properly retrieved.
     * </p>
     */
    @Test
    public void test_getCountry() {
        Country value = new Country();
        instance.setCountry(value);

        assertSame("'getCountry' should be correct.",
            value, instance.getCountry());
    }

    /**
     * <p>
     * Accuracy test for the method <code>setCountry(Country country)</code>.<br>
     * The value should be properly set.
     * </p>
     */
    @Test
    public void test_setCountry() {
        Country value = new Country();
        instance.setCountry(value);

        assertSame("'setCountry' should be correct.",
            value, TestsHelper.getField(instance, "country"));
    }
}
