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

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <p>
 * The base class for unit tests.
 * </p>
 *
 * <p>
 * <em>Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:</em>
 * <ol>
 * <li>Added EMPTY_STRING, DATE_FORMAT constants and setField method.</li>
 * </ol>
 * </p>
 *
 * @author sparemax, TCSASSEMBLER
 * @version 1.0
 */
public class TestsHelper {
    /**
     * <p>
     * Represents the empty string.
     * </p>
     */
    public static final String EMPTY_STRING = " \t ";

    /**
     * The date format 'MM/dd/yyyy'.
     */
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Creates an instance of TestsHelper.
     */
    private TestsHelper() {
        // Empty
    }

    /**
     * <p>
     * Gets value for field of given object.
     * </p>
     *
     * @param obj
     *            the given object.
     * @param field
     *            the field name.
     *
     * @return the field value.
     */
    public static Object getField(Object obj, String field) {
        Object value = null;
        try {
            Field declaredField = null;
            try {
                declaredField = obj.getClass().getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                // Ignore
            }

            try {
                if (declaredField == null) {
                    declaredField = obj.getClass().getSuperclass().getDeclaredField(field);
                }
            } catch (NoSuchFieldException e) {
                // Ignore
            }

            if (declaredField == null) {
                declaredField = obj.getClass().getSuperclass().getSuperclass().getDeclaredField(field);
            }

            declaredField.setAccessible(true);

            try {
                value = declaredField.get(obj);
            } finally {
                declaredField.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            // Ignore
        } catch (NoSuchFieldException e) {
            // Ignore
        }

        return value;
    }

    /**
     * <p>
     * Sets value for field of given object.
     * </p>
     *
     * @param obj
     *            the given object.
     * @param field
     *            the field name.
     * @param value
     *            the field value.
     */
    public static void setField(Object obj, String field, Object value) {
        try {
            Field declaredField = null;
            try {
                declaredField = obj.getClass().getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                // Ignore
            }

            try {
                if (declaredField == null) {
                    declaredField = obj.getClass().getSuperclass().getDeclaredField(field);
                }
            } catch (NoSuchFieldException e) {
                // Ignore
            }

            if (declaredField == null) {
                declaredField = obj.getClass().getSuperclass().getSuperclass().getDeclaredField(field);
            }

            declaredField.setAccessible(true);

            try {
                declaredField.set(obj, value);
            } finally {
                declaredField.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            // Ignore
        } catch (NoSuchFieldException e) {
            // Ignore
        }
    }
}
