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
import gov.opm.scrd.entities.lookup.ApprovalStatus;

import java.util.List;

/**
 * <p>
 * This is the class representing the information about the validation of the account confirmation. It contains the
 * overall validation data and status and reference to list of single field items of the validation.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public class AccountConfirmationValidation extends IdentifiableEntity {
    /**
     * <p>
     * Represents the account id of validation data. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private long accountId;
    /**
     * <p>
     * Represents the data check status of validation data. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private ApprovalStatus dataCheckStatus;
    /**
     * <p>
     * Represents the name of the user performed data check status of validation data. It is managed with a getter and
     * setter. It may have any value. It is fully mutable.
     * </p>
     */
    private String dataCheckStatusValidator;
    /**
     * <p>
     * Represents the data check status reason of validation data. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private String dataCheckStatusReason;
    /**
     * <p>
     * Represents the list of individual field entries of validation data. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private List<AccountConfirmationValidationEntry> entries;

    /**
     * Creates an instance of AccountConfirmationValidation.
     */
    public AccountConfirmationValidation() {
        // Empty
    }

    /**
     * Gets the account id of validation data.
     *
     * @return the account id of validation data.
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * Sets the account id of validation data.
     *
     * @param accountId
     *            the account id of validation data.
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the data check status of validation data.
     *
     * @return the data check status of validation data.
     */
    public ApprovalStatus getDataCheckStatus() {
        return dataCheckStatus;
    }

    /**
     * Sets the data check status of validation data.
     *
     * @param dataCheckStatus
     *            the data check status of validation data.
     */
    public void setDataCheckStatus(ApprovalStatus dataCheckStatus) {
        this.dataCheckStatus = dataCheckStatus;
    }

    /**
     * Gets the name of the user performed data check status of validation data.
     *
     * @return the name of the user performed data check status of validation data.
     */
    public String getDataCheckStatusValidator() {
        return dataCheckStatusValidator;
    }

    /**
     * Sets the name of the user performed data check status of validation data.
     *
     * @param dataCheckStatusValidator
     *            the name of the user performed data check status of validation data.
     */
    public void setDataCheckStatusValidator(String dataCheckStatusValidator) {
        this.dataCheckStatusValidator = dataCheckStatusValidator;
    }

    /**
     * Gets the data check status reason of validation data.
     *
     * @return the data check status reason of validation data.
     */
    public String getDataCheckStatusReason() {
        return dataCheckStatusReason;
    }

    /**
     * Sets the data check status reason of validation data.
     *
     * @param dataCheckStatusReason
     *            the data check status reason of validation data.
     */
    public void setDataCheckStatusReason(String dataCheckStatusReason) {
        this.dataCheckStatusReason = dataCheckStatusReason;
    }

    /**
     * Gets the list of individual field entries of validation data.
     *
     * @return the list of individual field entries of validation data.
     */
    public List<AccountConfirmationValidationEntry> getEntries() {
        return entries;
    }

    /**
     * Sets the list of individual field entries of validation data.
     *
     * @param entries
     *            the list of individual field entries of validation data.
     */
    public void setEntries(List<AccountConfirmationValidationEntry> entries) {
        this.entries = entries;
    }
}
