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

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Represents the entity specifying adjustment transaction.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - Data Migration - Entities Update Module Assembly 1.0
 */
public class AdjustmentTransaction extends IdentifiableEntity {
    /**
     * <p>
     * Represents the transaction key of transaction. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private Long payTransactionKey;
    /**
     * <p>
     * Represents the claim number of transaction. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private String claimNumber;
    /**
     * <p>
     * Represents the namesake account value of transaction. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal accIntDep;
    /**
     * <p>
     * Represents the namesake account value of transaction. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal accIntRdep;
    /**
     * <p>
     * Represents the namesake account value of transaction. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal accIntNonDep;
    /**
     * <p>
     * Represents the namesake account value of transaction. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal accIntVarRdep;
    /**
     * <p>
     * Represents the namesake account value of transaction. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private BigDecimal accIntDepFers;
    /**
     * <p>
     * Represents the modification date of transaction. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private Date modificationDate;
    /**
     * <p>
     * Represents the approval date of transaction. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Date approvalDate;
    /**
     * <p>
     * Represents the processed date of transaction. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private Date processedDate;
    /**
     * <p>
     * Represents the key of the technician of transaction. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Long technicianUserKey;
    /**
     * <p>
     * Represents the key of the manager of transaction. It is managed with a getter and setter. It may have any value.
     * It is fully mutable.
     * </p>
     */
    private Long managerUserKey;
    /**
     * <p>
     * Represents the flag specifying whether transaction is approved. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean approved;
    /**
     * <p>
     * Represents the flag specifying whether transaction is disapproved. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean disapproved;
    /**
     * <p>
     * Represents the flag specifying whether transaction is modified. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean modified;
    /**
     * <p>
     * Represents the note of transaction. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String note;

    /**
     * Creates an instance of AdjustmentTransaction.
     */
    public AdjustmentTransaction() {
        // Empty
    }

    /**
     * Gets the transaction key of transaction.
     *
     * @return the transaction key of transaction.
     */
    public Long getPayTransactionKey() {
        return payTransactionKey;
    }

    /**
     * Sets the transaction key of transaction.
     *
     * @param payTransactionKey
     *            the transaction key of transaction.
     */
    public void setPayTransactionKey(Long payTransactionKey) {
        this.payTransactionKey = payTransactionKey;
    }

    /**
     * Gets the claim number of transaction.
     *
     * @return the claim number of transaction.
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number of transaction.
     *
     * @param claimNumber
     *            the claim number of transaction.
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the namesake account value of transaction.
     *
     * @return the namesake account value of transaction.
     */
    public BigDecimal getAccIntDep() {
        return accIntDep;
    }

    /**
     * Sets the namesake account value of transaction.
     *
     * @param accIntDep
     *            the namesake account value of transaction.
     */
    public void setAccIntDep(BigDecimal accIntDep) {
        this.accIntDep = accIntDep;
    }

    /**
     * Gets the namesake account value of transaction.
     *
     * @return the namesake account value of transaction.
     */
    public BigDecimal getAccIntRdep() {
        return accIntRdep;
    }

    /**
     * Sets the namesake account value of transaction.
     *
     * @param accIntRdep
     *            the namesake account value of transaction.
     */
    public void setAccIntRdep(BigDecimal accIntRdep) {
        this.accIntRdep = accIntRdep;
    }

    /**
     * Gets the namesake account value of transaction.
     *
     * @return the namesake account value of transaction.
     */
    public BigDecimal getAccIntNonDep() {
        return accIntNonDep;
    }

    /**
     * Sets the namesake account value of transaction.
     *
     * @param accIntNonDep
     *            the namesake account value of transaction.
     */
    public void setAccIntNonDep(BigDecimal accIntNonDep) {
        this.accIntNonDep = accIntNonDep;
    }

    /**
     * Gets the namesake account value of transaction.
     *
     * @return the namesake account value of transaction.
     */
    public BigDecimal getAccIntVarRdep() {
        return accIntVarRdep;
    }

    /**
     * Sets the namesake account value of transaction.
     *
     * @param accIntVarRdep
     *            the namesake account value of transaction.
     */
    public void setAccIntVarRdep(BigDecimal accIntVarRdep) {
        this.accIntVarRdep = accIntVarRdep;
    }

    /**
     * Gets the namesake account value of transaction.
     *
     * @return the namesake account value of transaction.
     */
    public BigDecimal getAccIntDepFers() {
        return accIntDepFers;
    }

    /**
     * Sets the namesake account value of transaction.
     *
     * @param accIntDepFers
     *            the namesake account value of transaction.
     */
    public void setAccIntDepFers(BigDecimal accIntDepFers) {
        this.accIntDepFers = accIntDepFers;
    }

    /**
     * Gets the modification date of transaction.
     *
     * @return the modification date of transaction.
     */
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * Sets the modification date of transaction.
     *
     * @param modificationDate
     *            the modification date of transaction.
     */
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * Gets the approval date of transaction.
     *
     * @return the approval date of transaction.
     */
    public Date getApprovalDate() {
        return approvalDate;
    }

    /**
     * Sets the approval date of transaction.
     *
     * @param approvalDate
     *            the approval date of transaction.
     */
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    /**
     * Gets the processed date of transaction.
     *
     * @return the processed date of transaction.
     */
    public Date getProcessedDate() {
        return processedDate;
    }

    /**
     * Sets the processed date of transaction.
     *
     * @param processedDate
     *            the processed date of transaction.
     */
    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }

    /**
     * Gets the key of the technician of transaction.
     *
     * @return the key of the technician of transaction.
     */
    public Long getTechnicianUserKey() {
        return technicianUserKey;
    }

    /**
     * Sets the key of the technician of transaction.
     *
     * @param technicianUserKey
     *            the key of the technician of transaction.
     */
    public void setTechnicianUserKey(Long technicianUserKey) {
        this.technicianUserKey = technicianUserKey;
    }

    /**
     * Gets the key of the manager of transaction.
     *
     * @return the key of the manager of transaction.
     */
    public Long getManagerUserKey() {
        return managerUserKey;
    }

    /**
     * Sets the key of the manager of transaction.
     *
     * @param managerUserKey
     *            the key of the manager of transaction.
     */
    public void setManagerUserKey(Long managerUserKey) {
        this.managerUserKey = managerUserKey;
    }

    /**
     * Gets the flag specifying whether transaction is approved.
     *
     * @return the flag specifying whether transaction is approved.
     */
    public Boolean getApproved() {
        return approved;
    }

    /**
     * Sets the flag specifying whether transaction is approved.
     *
     * @param approved
     *            the flag specifying whether transaction is approved.
     */
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    /**
     * Gets the flag specifying whether transaction is disapproved.
     *
     * @return the flag specifying whether transaction is disapproved.
     */
    public Boolean getDisapproved() {
        return disapproved;
    }

    /**
     * Sets the flag specifying whether transaction is disapproved.
     *
     * @param disapproved
     *            the flag specifying whether transaction is disapproved.
     */
    public void setDisapproved(Boolean disapproved) {
        this.disapproved = disapproved;
    }

    /**
     * Gets the flag specifying whether transaction is modified.
     *
     * @return the flag specifying whether transaction is modified.
     */
    public Boolean getModified() {
        return modified;
    }

    /**
     * Sets the flag specifying whether transaction is modified.
     *
     * @param modified
     *            the flag specifying whether transaction is modified.
     */
    public void setModified(Boolean modified) {
        this.modified = modified;
    }

    /**
     * Gets the note of transaction.
     *
     * @return the note of transaction.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note of transaction.
     *
     * @param note
     *            the note of transaction.
     */
    public void setNote(String note) {
        this.note = note;
    }
}
