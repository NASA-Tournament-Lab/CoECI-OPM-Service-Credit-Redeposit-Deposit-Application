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
 * Represents the entity specifying payment transaction status code.
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
public class PayTransStatusCode extends IdentifiableEntity {
    /**
     * <p>
     * Represents the description of code. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String description;
    /**
     * <p>
     * Represents the category of code. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private String category;
    /**
     * <p>
     * Represents the display order the of code. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Integer displayOrder;
    /**
     * <p>
     * Represents the next state link of code. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Integer nextStateLink;
    /**
     * <p>
     * Represents the batch processing order of code. It is managed with a getter and setter. It may have any value. It
     * is fully mutable.
     * </p>
     */
    private Integer batchProcessingOrder;
    /**
     * <p>
     * Represents the flag specifying whether code is in final state. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean finalState;
    /**
     * <p>
     * Represents the flag specifying whether code needs approval. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private Boolean needsApproval;
    /**
     * <p>
     * Represents the flag specifying whether code is shown on suspense. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean showOnSuspense;
    /**
     * <p>
     * Represents the flag specifying whether code is included in balance. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private Boolean includeInBalance;
    /**
     * <p>
     * Represents the flag specifying whether code relates to nightly batch. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private Boolean nightlyBatch;
    /**
     * <p>
     * Represents the flag specifying whether code is deletable. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Boolean deletable;
    /**
     * <p>
     * Represents the flag specifying whether code is reversable. It is managed with a getter and setter. It may have
     * any value. It is fully mutable.
     * </p>
     */
    private Boolean reversable;
    /**
     * <p>
     * Represents the flag specifying whether code is manually entered. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean manualEntered;
    /**
     * <p>
     * Represents the flag specifying whether code is suspense action. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean suspenseAction;
    /**
     * <p>
     * Represents the flag specifying whether code can hit GL. It is managed with a getter and setter. It may have any
     * value. It is fully mutable.
     * </p>
     */
    private Boolean canHitGl;
    /**
     * <p>
     * Represents the flag specifying whether code is of revering type. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean reversingType;
    /**
     * <p>
     * Represents the flag specifying whether code is balanced scorecard. It is managed with a getter and setter. It may
     * have any value. It is fully mutable.
     * </p>
     */
    private Boolean balancedScorecard;
    /**
     * <p>
     * Represents the flag specifying whether code should be sent to DBTS. It is managed with a getter and setter. It
     * may have any value. It is fully mutable.
     * </p>
     */
    private Boolean sendToDbts;

    /**
     * Creates an instance of PayTransStatusCode.
     */
    public PayTransStatusCode() {
        // Empty
    }

    /**
     * Gets the description of code.
     *
     * @return the description of code.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of code.
     *
     * @param description
     *            the description of code.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the category of code.
     *
     * @return the category of code.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of code.
     *
     * @param category
     *            the category of code.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the display order the of code.
     *
     * @return the display order the of code.
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Sets the display order the of code.
     *
     * @param displayOrder
     *            the display order the of code.
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * Gets the next state link of code.
     *
     * @return the next state link of code.
     */
    public Integer getNextStateLink() {
        return nextStateLink;
    }

    /**
     * Sets the next state link of code.
     *
     * @param nextStateLink
     *            the next state link of code.
     */
    public void setNextStateLink(Integer nextStateLink) {
        this.nextStateLink = nextStateLink;
    }

    /**
     * Gets the batch processing order of code.
     *
     * @return the batch processing order of code.
     */
    public Integer getBatchProcessingOrder() {
        return batchProcessingOrder;
    }

    /**
     * Sets the batch processing order of code.
     *
     * @param batchProcessingOrder
     *            the batch processing order of code.
     */
    public void setBatchProcessingOrder(Integer batchProcessingOrder) {
        this.batchProcessingOrder = batchProcessingOrder;
    }

    /**
     * Gets the flag specifying whether code is in final state.
     *
     * @return the flag specifying whether code is in final state.
     */
    public Boolean getFinalState() {
        return finalState;
    }

    /**
     * Sets the flag specifying whether code is in final state.
     *
     * @param finalState
     *            the flag specifying whether code is in final state.
     */
    public void setFinalState(Boolean finalState) {
        this.finalState = finalState;
    }

    /**
     * Gets the flag specifying whether code needs approval.
     *
     * @return the flag specifying whether code needs approval.
     */
    public Boolean getNeedsApproval() {
        return needsApproval;
    }

    /**
     * Sets the flag specifying whether code needs approval.
     *
     * @param needsApproval
     *            the flag specifying whether code needs approval.
     */
    public void setNeedsApproval(Boolean needsApproval) {
        this.needsApproval = needsApproval;
    }

    /**
     * Gets the flag specifying whether code is shown on suspense.
     *
     * @return the flag specifying whether code is shown on suspense.
     */
    public Boolean getShowOnSuspense() {
        return showOnSuspense;
    }

    /**
     * Sets the flag specifying whether code is shown on suspense.
     *
     * @param showOnSuspense
     *            the flag specifying whether code is shown on suspense.
     */
    public void setShowOnSuspense(Boolean showOnSuspense) {
        this.showOnSuspense = showOnSuspense;
    }

    /**
     * Gets the flag specifying whether code is included in balance.
     *
     * @return the flag specifying whether code is included in balance.
     */
    public Boolean getIncludeInBalance() {
        return includeInBalance;
    }

    /**
     * Sets the flag specifying whether code is included in balance.
     *
     * @param includeInBalance
     *            the flag specifying whether code is included in balance.
     */
    public void setIncludeInBalance(Boolean includeInBalance) {
        this.includeInBalance = includeInBalance;
    }

    /**
     * Gets the flag specifying whether code relates to nightly batch.
     *
     * @return the flag specifying whether code relates to nightly batch.
     */
    public Boolean getNightlyBatch() {
        return nightlyBatch;
    }

    /**
     * Sets the flag specifying whether code relates to nightly batch.
     *
     * @param nightlyBatch
     *            the flag specifying whether code relates to nightly batch.
     */
    public void setNightlyBatch(Boolean nightlyBatch) {
        this.nightlyBatch = nightlyBatch;
    }

    /**
     * Gets the flag specifying whether code is deletable.
     *
     * @return the flag specifying whether code is deletable.
     */
    public Boolean getDeletable() {
        return deletable;
    }

    /**
     * Sets the flag specifying whether code is deletable.
     *
     * @param deletable
     *            the flag specifying whether code is deletable.
     */
    public void setDeletable(Boolean deletable) {
        this.deletable = deletable;
    }

    /**
     * Gets the flag specifying whether code is reversable.
     *
     * @return the flag specifying whether code is reversable.
     */
    public Boolean getReversable() {
        return reversable;
    }

    /**
     * Sets the flag specifying whether code is reversable.
     *
     * @param reversable
     *            the flag specifying whether code is reversable.
     */
    public void setReversable(Boolean reversable) {
        this.reversable = reversable;
    }

    /**
     * Gets the flag specifying whether code is manually entered.
     *
     * @return the flag specifying whether code is manually entered.
     */
    public Boolean getManualEntered() {
        return manualEntered;
    }

    /**
     * Sets the flag specifying whether code is manually entered.
     *
     * @param manualEntered
     *            the flag specifying whether code is manually entered.
     */
    public void setManualEntered(Boolean manualEntered) {
        this.manualEntered = manualEntered;
    }

    /**
     * Gets the flag specifying whether code is suspense action.
     *
     * @return the flag specifying whether code is suspense action.
     */
    public Boolean getSuspenseAction() {
        return suspenseAction;
    }

    /**
     * Sets the flag specifying whether code is suspense action.
     *
     * @param suspenseAction
     *            the flag specifying whether code is suspense action.
     */
    public void setSuspenseAction(Boolean suspenseAction) {
        this.suspenseAction = suspenseAction;
    }

    /**
     * Gets the flag specifying whether code can hit GL.
     *
     * @return the flag specifying whether code can hit GL.
     */
    public Boolean getCanHitGl() {
        return canHitGl;
    }

    /**
     * Sets the flag specifying whether code can hit GL.
     *
     * @param canHitGl
     *            the flag specifying whether code can hit GL.
     */
    public void setCanHitGl(Boolean canHitGl) {
        this.canHitGl = canHitGl;
    }

    /**
     * Gets the flag specifying whether code is of revering type.
     *
     * @return the flag specifying whether code is of revering type.
     */
    public Boolean getReversingType() {
        return reversingType;
    }

    /**
     * Sets the flag specifying whether code is of revering type.
     *
     * @param reversingType
     *            the flag specifying whether code is of revering type.
     */
    public void setReversingType(Boolean reversingType) {
        this.reversingType = reversingType;
    }

    /**
     * Gets the flag specifying whether code is balanced scorecard.
     *
     * @return the flag specifying whether code is balanced scorecard.
     */
    public Boolean getBalancedScorecard() {
        return balancedScorecard;
    }

    /**
     * Sets the flag specifying whether code is balanced scorecard.
     *
     * @param balancedScorecard
     *            the flag specifying whether code is balanced scorecard.
     */
    public void setBalancedScorecard(Boolean balancedScorecard) {
        this.balancedScorecard = balancedScorecard;
    }

    /**
     * Gets the flag specifying whether code should be sent to DBTS.
     *
     * @return the flag specifying whether code should be sent to DBTS.
     */
    public Boolean getSendToDbts() {
        return sendToDbts;
    }

    /**
     * Sets the flag specifying whether code should be sent to DBTS.
     *
     * @param sendToDbts
     *            the flag specifying whether code should be sent to DBTS.
     */
    public void setSendToDbts(Boolean sendToDbts) {
        this.sendToDbts = sendToDbts;
    }
}
