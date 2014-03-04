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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * <p>
 * The base class for unit tests.
 * </p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Data Services - Account and Payment Services Assembly 1.0):</em>
 * <ol>
 * <li>Added EMPTY_STRING, DATE_FORMAT constants and setField method.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <em>Changes in 1.2 (OPM - Data Migration - Processor Module Assembly 1.0):</em>
 * <ul>
 * <li>Added constants: SOURCE_DB_DRIVER, SOURCE_DB_URL, SOURCE_DB_USERNAME, SOURCE_DB_PASSWORD, TEST_FILES</li>
 * </ul>
 * </p>
 *
 * @author sparemax, sparemax
 * @version 1.2
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
     * <p>
     * Represents the source database driver name.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Processor Module Assembly 1.0)
     */
    public static final String SOURCE_DB_DRIVER;

    /**
     * <p>
     * Represents the source database url.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Processor Module Assembly 1.0)
     */
    public static final String SOURCE_DB_URL;

    /**
     * <p>
     * Represents the source database username.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Processor Module Assembly 1.0)
     */
    public static final String SOURCE_DB_USERNAME;

    /**
     * <p>
     * Represents the source database password.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Processor Module Assembly 1.0)
     */
    public static final String SOURCE_DB_PASSWORD;

    /**
     * <p>
     * Represents the path of test files.
     * </p>
     *
     * @since 1.2 (OPM - Data Migration - Processor Module Assembly 1.0)
     */
    private static final String TEST_FILES = "test_files" + File.separator;

    /**
     * Initialization.
     *
     * @since 1.2 (OPM - Data Migration - Processor Module Assembly 1.0)
     */
    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(TEST_FILES + "test.properties");
            try {
                properties.load(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (Exception e) {
            // Ignore
        }
        SOURCE_DB_DRIVER = properties.getProperty("sourceDatabaseDriverName");
        SOURCE_DB_URL = properties.getProperty("sourceDatabaseUrl");
        SOURCE_DB_USERNAME = properties.getProperty("sourceDatabaseUsername");
        SOURCE_DB_PASSWORD = properties.getProperty("sourceDatabasePassword");
    }

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
