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

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * This class represents command line utility that is used to run the data migration. It simply loads the
 * DataMigrationProcessor from Spring application context and executes the migration.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> The class is immutable and thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - Data Migration - Processor Module Assembly 1.0
 */
public class DataMigrationUtility {
    /**
     * The configuration file.
     */
    private static final String CONFIG_FILE = "applicationContext.xml";

    /**
     * The name of DataMigrationProcessor bean.
     */
    private static final String DATA_MIGRATION_PROCESSOR_BEAN_NAME = "dataMigrationProcessor";

    /**
     * Private constructor to prevent class instance from being instantiated.
     */
    private DataMigrationUtility() {
        // Empty
    }

    /**
     * This method gets is executed when the application is launched.
     *
     * @param args
     *            command line arguments, not used.
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONFIG_FILE);
            DataMigrationProcessor processor = (DataMigrationProcessor) applicationContext
                .getBean(DATA_MIGRATION_PROCESSOR_BEAN_NAME);

            processor.migrate();
        } catch (BeansException e) {
            handleException(e);
        } catch (ClassCastException e) {
            handleException(e);
        } catch (OPMMigrationException e) {
            handleException(e);
        }
    }

    /**
     * Handles the exception.
     *
     * @param e
     *            the exception.
     */
    private static void handleException(Exception e) {
        e.printStackTrace();
        System.exit(1);
    }
}
