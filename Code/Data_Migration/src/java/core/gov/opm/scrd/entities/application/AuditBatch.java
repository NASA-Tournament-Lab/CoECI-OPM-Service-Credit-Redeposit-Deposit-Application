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
 * This is the class representing the audit batch.
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
public class AuditBatch extends IdentifiableEntity {
    /**
     * <p>
     * Represents the event year of batch. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer eventYear;
    /**
     * <p>
     * Represents the event month of batch. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer eventMonth;
    /**
     * <p>
     * Represents the event day of batch. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer eventDay;
    /**
     * <p>
     * Represents the file received flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean fileReceived;
    /**
     * <p>
     * Represents the daily action flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean dailyAction;
    /**
     * <p>
     * Represents the manual batch flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean manualBatch;
    /**
     * <p>
     * Represents the error importing flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean errorImporting;
    /**
     * <p>
     * Represents the error processing flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean errorProcessing;
    /**
     * <p>
     * Represents the latest batch flag. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Boolean latestBatch;
    /**
     * <p>
     * Represents the amount imported. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal amountImported;
    /**
     * <p>
     * Represents the amount processed. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private BigDecimal amountProcessed;
    /**
     * <p>
     * Represents the number accepted. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer numberAccepted;
    /**
     * <p>
     * Represents the number unresolved. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer numberUnresolved;
    /**
     * <p>
     * Represents the number suspended. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer numberSuspended;
    /**
     * <p>
     * Represents the number ach accepted. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer numberAchAccepted;
    /**
     * <p>
     * Represents the number ach unresolved. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer numberAchUnresolved;
    /**
     * <p>
     * Represents the number ach suspended. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer numberAchSuspended;
    /**
     * <p>
     * Represents the number change requests. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer numberChangeRequests;
    /**
     * <p>
     * Represents the payments processed. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer paymentsProcessed;
    /**
     * <p>
     * Represents the initial bills processed. It is managed with a getter and setter. It may have any value. It is
     * fully mutable.
     * </p>
     */
    private Integer initialBillsProcessed;
    /**
     * <p>
     * Represents the reversed processed. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer reversedProcessed;
    /**
     * <p>
     * Represents the ach stop letters. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer achStopLetters;
    /**
     * <p>
     * Represents the refund memos. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Integer refundMemos;
    /**
     * <p>
     * Represents the error count processing. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Integer errorCountProcessing;
    /**
     * <p>
     * Represents the user key of batch. It is managed with a getter and setter. It may have any value. It is fully
     * mutable.
     * </p>
     */
    private Long userKey;
    /**
     * <p>
     * Represents the time of batch. It is managed with a getter and setter. It may have any value. It is fully mutable.
     * </p>
     */
    private Date batchTime;

    /**
     * Creates an instance of AuditBatch.
     */
    public AuditBatch() {
        // Empty
    }

    /**
     * Gets the event year of batch.
     *
     * @return the event year of batch.
     */
    public Integer getEventYear() {
        return eventYear;
    }

    /**
     * Sets the event year of batch.
     *
     * @param eventYear
     *            the event year of batch.
     */
    public void setEventYear(Integer eventYear) {
        this.eventYear = eventYear;
    }

    /**
     * Gets the event month of batch.
     *
     * @return the event month of batch.
     */
    public Integer getEventMonth() {
        return eventMonth;
    }

    /**
     * Sets the event month of batch.
     *
     * @param eventMonth
     *            the event month of batch.
     */
    public void setEventMonth(Integer eventMonth) {
        this.eventMonth = eventMonth;
    }

    /**
     * Gets the event day of batch.
     *
     * @return the event day of batch.
     */
    public Integer getEventDay() {
        return eventDay;
    }

    /**
     * Sets the event day of batch.
     *
     * @param eventDay
     *            the event day of batch.
     */
    public void setEventDay(Integer eventDay) {
        this.eventDay = eventDay;
    }

    /**
     * Gets the file received flag.
     *
     * @return the file received flag.
     */
    public Boolean getFileReceived() {
        return fileReceived;
    }

    /**
     * Sets the file received flag.
     *
     * @param fileReceived
     *            the file received flag.
     */
    public void setFileReceived(Boolean fileReceived) {
        this.fileReceived = fileReceived;
    }

    /**
     * Gets the daily action flag.
     *
     * @return the daily action flag.
     */
    public Boolean getDailyAction() {
        return dailyAction;
    }

    /**
     * Sets the daily action flag.
     *
     * @param dailyAction
     *            the daily action flag.
     */
    public void setDailyAction(Boolean dailyAction) {
        this.dailyAction = dailyAction;
    }

    /**
     * Gets the manual batch flag.
     *
     * @return the manual batch flag.
     */
    public Boolean getManualBatch() {
        return manualBatch;
    }

    /**
     * Sets the manual batch flag.
     *
     * @param manualBatch
     *            the manual batch flag.
     */
    public void setManualBatch(Boolean manualBatch) {
        this.manualBatch = manualBatch;
    }

    /**
     * Gets the error importing flag.
     *
     * @return the error importing flag.
     */
    public Boolean getErrorImporting() {
        return errorImporting;
    }

    /**
     * Sets the error importing flag.
     *
     * @param errorImporting
     *            the error importing flag.
     */
    public void setErrorImporting(Boolean errorImporting) {
        this.errorImporting = errorImporting;
    }

    /**
     * Gets the error processing flag.
     *
     * @return the error processing flag.
     */
    public Boolean getErrorProcessing() {
        return errorProcessing;
    }

    /**
     * Sets the error processing flag.
     *
     * @param errorProcessing
     *            the error processing flag.
     */
    public void setErrorProcessing(Boolean errorProcessing) {
        this.errorProcessing = errorProcessing;
    }

    /**
     * Gets the latest batch flag.
     *
     * @return the latest batch flag.
     */
    public Boolean getLatestBatch() {
        return latestBatch;
    }

    /**
     * Sets the latest batch flag.
     *
     * @param latestBatch
     *            the latest batch flag.
     */
    public void setLatestBatch(Boolean latestBatch) {
        this.latestBatch = latestBatch;
    }

    /**
     * Gets the amount imported.
     *
     * @return the amount imported.
     */
    public BigDecimal getAmountImported() {
        return amountImported;
    }

    /**
     * Sets the amount imported.
     *
     * @param amountImported
     *            the amount imported.
     */
    public void setAmountImported(BigDecimal amountImported) {
        this.amountImported = amountImported;
    }

    /**
     * Gets the amount processed.
     *
     * @return the amount processed.
     */
    public BigDecimal getAmountProcessed() {
        return amountProcessed;
    }

    /**
     * Sets the amount processed.
     *
     * @param amountProcessed
     *            the amount processed.
     */
    public void setAmountProcessed(BigDecimal amountProcessed) {
        this.amountProcessed = amountProcessed;
    }

    /**
     * Gets the number accepted.
     *
     * @return the number accepted.
     */
    public Integer getNumberAccepted() {
        return numberAccepted;
    }

    /**
     * Sets the number accepted.
     *
     * @param numberAccepted
     *            the number accepted.
     */
    public void setNumberAccepted(Integer numberAccepted) {
        this.numberAccepted = numberAccepted;
    }

    /**
     * Gets the number unresolved.
     *
     * @return the number unresolved.
     */
    public Integer getNumberUnresolved() {
        return numberUnresolved;
    }

    /**
     * Sets the number unresolved.
     *
     * @param numberUnresolved
     *            the number unresolved.
     */
    public void setNumberUnresolved(Integer numberUnresolved) {
        this.numberUnresolved = numberUnresolved;
    }

    /**
     * Gets the number suspended.
     *
     * @return the number suspended.
     */
    public Integer getNumberSuspended() {
        return numberSuspended;
    }

    /**
     * Sets the number suspended.
     *
     * @param numberSuspended
     *            the number suspended.
     */
    public void setNumberSuspended(Integer numberSuspended) {
        this.numberSuspended = numberSuspended;
    }

    /**
     * Gets the number ach accepted.
     *
     * @return the number ach accepted.
     */
    public Integer getNumberAchAccepted() {
        return numberAchAccepted;
    }

    /**
     * Sets the number ach accepted.
     *
     * @param numberAchAccepted
     *            the number ach accepted.
     */
    public void setNumberAchAccepted(Integer numberAchAccepted) {
        this.numberAchAccepted = numberAchAccepted;
    }

    /**
     * Gets the number ach unresolved.
     *
     * @return the number ach unresolved.
     */
    public Integer getNumberAchUnresolved() {
        return numberAchUnresolved;
    }

    /**
     * Sets the number ach unresolved.
     *
     * @param numberAchUnresolved
     *            the number ach unresolved.
     */
    public void setNumberAchUnresolved(Integer numberAchUnresolved) {
        this.numberAchUnresolved = numberAchUnresolved;
    }

    /**
     * Gets the number ach suspended.
     *
     * @return the number ach suspended.
     */
    public Integer getNumberAchSuspended() {
        return numberAchSuspended;
    }

    /**
     * Sets the number ach suspended.
     *
     * @param numberAchSuspended
     *            the number ach suspended.
     */
    public void setNumberAchSuspended(Integer numberAchSuspended) {
        this.numberAchSuspended = numberAchSuspended;
    }

    /**
     * Gets the number change requests.
     *
     * @return the number change requests.
     */
    public Integer getNumberChangeRequests() {
        return numberChangeRequests;
    }

    /**
     * Sets the number change requests.
     *
     * @param numberChangeRequests
     *            the number change requests.
     */
    public void setNumberChangeRequests(Integer numberChangeRequests) {
        this.numberChangeRequests = numberChangeRequests;
    }

    /**
     * Gets the payments processed.
     *
     * @return the payments processed.
     */
    public Integer getPaymentsProcessed() {
        return paymentsProcessed;
    }

    /**
     * Sets the payments processed.
     *
     * @param paymentsProcessed
     *            the payments processed.
     */
    public void setPaymentsProcessed(Integer paymentsProcessed) {
        this.paymentsProcessed = paymentsProcessed;
    }

    /**
     * Gets the initial bills processed.
     *
     * @return the initial bills processed.
     */
    public Integer getInitialBillsProcessed() {
        return initialBillsProcessed;
    }

    /**
     * Sets the initial bills processed.
     *
     * @param initialBillsProcessed
     *            the initial bills processed.
     */
    public void setInitialBillsProcessed(Integer initialBillsProcessed) {
        this.initialBillsProcessed = initialBillsProcessed;
    }

    /**
     * Gets the reversed processed.
     *
     * @return the reversed processed.
     */
    public Integer getReversedProcessed() {
        return reversedProcessed;
    }

    /**
     * Sets the reversed processed.
     *
     * @param reversedProcessed
     *            the reversed processed.
     */
    public void setReversedProcessed(Integer reversedProcessed) {
        this.reversedProcessed = reversedProcessed;
    }

    /**
     * Gets the ach stop letters.
     *
     * @return the ach stop letters.
     */
    public Integer getAchStopLetters() {
        return achStopLetters;
    }

    /**
     * Sets the ach stop letters.
     *
     * @param achStopLetters
     *            the ach stop letters.
     */
    public void setAchStopLetters(Integer achStopLetters) {
        this.achStopLetters = achStopLetters;
    }

    /**
     * Gets the refund memos.
     *
     * @return the refund memos.
     */
    public Integer getRefundMemos() {
        return refundMemos;
    }

    /**
     * Sets the refund memos.
     *
     * @param refundMemos
     *            the refund memos.
     */
    public void setRefundMemos(Integer refundMemos) {
        this.refundMemos = refundMemos;
    }

    /**
     * Gets the error count processing.
     *
     * @return the error count processing.
     */
    public Integer getErrorCountProcessing() {
        return errorCountProcessing;
    }

    /**
     * Sets the error count processing.
     *
     * @param errorCountProcessing
     *            the error count processing.
     */
    public void setErrorCountProcessing(Integer errorCountProcessing) {
        this.errorCountProcessing = errorCountProcessing;
    }

    /**
     * Gets the user key of batch.
     *
     * @return the user key of batch.
     */
    public Long getUserKey() {
        return userKey;
    }

    /**
     * Sets the user key of batch.
     *
     * @param userKey
     *            the user key of batch.
     */
    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    /**
     * Gets the time of batch.
     *
     * @return the time of batch.
     */
    public Date getBatchTime() {
        return batchTime;
    }

    /**
     * Sets the time of batch.
     *
     * @param batchTime
     *            the time of batch.
     */
    public void setBatchTime(Date batchTime) {
        this.batchTime = batchTime;
    }
}
