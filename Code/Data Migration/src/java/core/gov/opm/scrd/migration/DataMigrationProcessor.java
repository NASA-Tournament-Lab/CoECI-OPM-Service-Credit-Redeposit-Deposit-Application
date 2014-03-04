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

/**
 * <p>
 * This interface defines the contract for performing data migration. The data migration should be done from source MS
 * SQL database to target PosgreSQL database. The data will be read with JDBC and stored with JPA.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> The implementation is required to be effectively thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - Data Migration - Processor Module Assembly 1.0
 */
public interface DataMigrationProcessor {
    /**
     * Performs the data migration. The method should read the existing data and store it in the new database.
     *
     * @throws OPMMigrationException
     *             if there is any problem performing migration.
     */
    public void migrate() throws OPMMigrationException;
}
