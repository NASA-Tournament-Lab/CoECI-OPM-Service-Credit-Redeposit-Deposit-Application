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

package gov.opm.scrd.entities.lookup;

/**
 * <p>
 * Represents the enumeration specifying the home page for the user of the application.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This enumeration is immutable and thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public enum ActionTab {
    /**
     * Represents 'view account' action tab.
     */
    VIEW_ACCOUNT,

    /**
     * Represents 'create new account' action tab.
     */
    CREATE_NEW_ACCOUNT,

    /**
     * Represents 'disapproved' action tab.
     */
    DISAPPROVED,

    /**
     * Represents 'work queue' action tab.
     */
    WORK_QUEUE,

    /**
     * Represents 'reports' action tab.
     */
    REPORTS,

    /**
     * Represents 'suspense' action tab.
     */
    SUSPENSE,

    /**
     * Represents 'approval' action tab.
     */
    APPROVAL,

    /**
     * Represents 'payments' action tab.
     */
    PAYMENTS,

    /**
     * Represents 'tools' action tab.
     */
    TOOLS,

    /**
     * Represents 'admin' action tab.
     */
    ADMIN,

    /**
     * Represents 'notification log' action tab.
     */
    NOTIFICATION_LOG,

    /**
     * Represents 'help' action tab.
     */
    HELP
}
