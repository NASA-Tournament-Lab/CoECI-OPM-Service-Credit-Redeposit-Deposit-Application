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

import gov.opm.scrd.entities.common.IdentifiableEntity;

/**
 * <p>
 * Represents the service credit preferences used in the application. This entity basically holds a list of properties
 * that are application-wide preferences of the application.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class ServiceCreditPreference extends IdentifiableEntity {
    /**
     * <p>
     * Represents the flag specifying whether agents should be used in the application. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean useAgents;
    /**
     * <p>
     * Represents the flag specifying whether status bar should be used in the application. It is managed with a getter
     * and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean useStatusBar;
    /**
     * <p>
     * Represents the flag specifying whether message box should be used in the application. It is managed with a getter
     * and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private boolean useMessageBox;
    /**
     * <p>
     * Represents the other setting(to be defined) used in the application. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private String otherSetting;

    /**
     * Creates an instance of ServiceCreditPreference.
     */
    public ServiceCreditPreference() {
        // Empty
    }

    /**
     * Gets the flag specifying whether agents should be used in the application.
     *
     * @return the flag specifying whether agents should be used in the application.
     */
    public boolean isUseAgents() {
        return useAgents;
    }

    /**
     * Sets the flag specifying whether agents should be used in the application.
     *
     * @param useAgents
     *            the flag specifying whether agents should be used in the application.
     */
    public void setUseAgents(boolean useAgents) {
        this.useAgents = useAgents;
    }

    /**
     * Gets the flag specifying whether status bar should be used in the application.
     *
     * @return the flag specifying whether status bar should be used in the application.
     */
    public boolean isUseStatusBar() {
        return useStatusBar;
    }

    /**
     * Sets the flag specifying whether status bar should be used in the application.
     *
     * @param useStatusBar
     *            the flag specifying whether status bar should be used in the application.
     */
    public void setUseStatusBar(boolean useStatusBar) {
        this.useStatusBar = useStatusBar;
    }

    /**
     * Gets the flag specifying whether message box should be used in the application.
     *
     * @return the flag specifying whether message box should be used in the application.
     */
    public boolean isUseMessageBox() {
        return useMessageBox;
    }

    /**
     * Sets the flag specifying whether message box should be used in the application.
     *
     * @param useMessageBox
     *            the flag specifying whether message box should be used in the application.
     */
    public void setUseMessageBox(boolean useMessageBox) {
        this.useMessageBox = useMessageBox;
    }

    /**
     * Gets the other setting(to be defined) used in the application.
     *
     * @return the other setting(to be defined) used in the application.
     */
    public String getOtherSetting() {
        return otherSetting;
    }

    /**
     * Sets the other setting(to be defined) used in the application.
     *
     * @param otherSetting
     *            the other setting(to be defined) used in the application.
     */
    public void setOtherSetting(String otherSetting) {
        this.otherSetting = otherSetting;
    }
}
