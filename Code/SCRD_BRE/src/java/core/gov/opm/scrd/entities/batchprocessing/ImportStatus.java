/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.batchprocessing;

import java.math.BigDecimal;


/**
 * <p>This class is simply a POJO containing status about the currently processed importing.</p>
 *  <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Reporting - Lockbox and Miscellaneous Module Assembly):</em>
 * <ol>
 * <li>Included comment from reviewer</li>
 * </ol>
 * </p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.1
 */
public class ImportStatus {
    /**
     * In ImportStatus#batchDailyPayments attribute document, it says "Represents the audit batch id field".
     * But it seems it's not for audit batch id. 
     * 
     * This entity is part of the bills processing assembly. Please ask reviewer flytoj2ee for what this field is for. 
     * It seems he knows better than the winner of that assembly but does not want to share what the actual
     * information this actually represents.
     */
    private Long batchDailyPayments;

    /**
     * Represents the value to show if the import is successful. It is accessible by getter and modified by
     * setter. It can be any value. The default value is null.
     */
    private Boolean successful;

    /**
     * Represents the value to show if the import status is permission denied. It is accessible by getter and
     * modified by setter. It can be any value. The default value is null.
     */
    private Boolean permissionDenied;

    /**
     * Represents the value to show if the payments are good. It is accessible by getter and modified by
     * setter. It can be any value. The default value is null.
     */
    private Boolean paymentsGood;

    /**
     * Represents the value to show if the change records good. It is accessible by getter and modified by
     * setter. It can be any value. The default value is null.
     */
    private Boolean changeRecordsGood;

    /**
     * Represents the number of change records. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Integer numberChangeRecords;

    /**
     * Represents the number of good change records. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private Integer numberGoodChangeRecords;

    /**
     * Represents the number of bad change records. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private Integer numberBadChangeRecords;

    /**
     * Represents the number of accepted ach payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private Integer numberAcceptedAchPayments;

    /**
     * Represents the number of unresolved ach payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private Integer numberUnresolvedAchPayments;

    /**
     * Represents the number of suspended ach payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private Integer numberSuspendedAchPayments;

    /**
     * Represents the number of accepted check payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private Integer numberAcceptedCheckPayments;

    /**
     * Represents the number of unresolved check payments. It is accessible by getter and modified by setter.
     * It can be any value. The default value is null.
     */
    private Integer numberUnresolvedCheckPayments;

    /**
     * Represents the number of suspended check payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private Integer numberSuspendedCheckPayments;

    /**
     * Represents the total ach payments. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal totalACHPayments;

    /**
     * Represents the total check payments. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal totalCheckPayments;

    /**
     * Represents the total skipped payments. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal totalSkippedPayments;

    /**
     * Represents the total dupe payments. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal totalDupePayments;

    /**
     * Represents the file summary total. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BigDecimal fileSummaryTotal;

    /**
     * Represents the number of ach payments. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer numberAchPayments;

    /**
     * Represents the number of good ach payments. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Integer numberGoodAchPayments;

    /**
     * Represents the number of bad ach payments. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Integer numberBadAchPayments;

    /**
     * Represents the number of dupe ach payments. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Integer numberDupeAchPayments;

    /**
     * Represents the number of check payments. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Integer numberCheckPayments;

    /**
     * Represents the number of good check payments. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private Integer numberGoodCheckPayments;

    /**
     * Represents the number of bad check payments. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private Integer numberBadCheckPayments;

    /**
     * Represents the number of dupe check payments. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private Integer numberDupeCheckPayments;

    /**
     * Represents the number of good summary records. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private Integer numberGoodSummaryRecords;

    /**
     * Represents the number of bad summary records. It is accessible by getter and modified by setter. It can
     * be any value. The default value is null.
     */
    private Integer numberBadSummaryRecords;

    /**
     * Represents the number of bad data records. It is accessible by getter and modified by setter. It can be
     * any value. The default value is null.
     */
    private Integer numberBadDataRecords;

    /**
     * Represents the number of lines in file. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer numberLinesInFile;

    /**
     * Represents the number of eof markers. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer numberEOFMarkers;

    /**
     * Represents the input name. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String inputName;

    /**
     * Represents the output name. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String outputName;

    /**
     * Instantiates a new import status.
     */
    public ImportStatus() {
    }

    /**
     * Gets the audit batch id.
     *
     * @return the audit batch id
     */
    public Long getBatchDailyPayments() {
        return batchDailyPayments;
    }

    /**
     * Sets the audit batch log id.
     *
     * @param batchDailyPayments the new audit batch log id to set
     */
    public void setBatchDailyPayments(Long batchDailyPayments) {
        this.batchDailyPayments = batchDailyPayments;
    }

    /**
     * Gets the successful.
     *
     * @return the successful
     */
    public Boolean getSuccessful() {
        return successful;
    }

    /**
     * Sets the successful.
     *
     * @param successful the new successful
     */
    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    /**
     * Gets the permission denied.
     *
     * @return the permission denied
     */
    public Boolean getPermissionDenied() {
        return permissionDenied;
    }

    /**
     * Sets the permission denied.
     *
     * @param permissionDenied the new permission denied
     */
    public void setPermissionDenied(Boolean permissionDenied) {
        this.permissionDenied = permissionDenied;
    }

    /**
     * Gets the payments good.
     *
     * @return the payments good
     */
    public Boolean getPaymentsGood() {
        return paymentsGood;
    }

    /**
     * Sets the payments good.
     *
     * @param paymentsGood the new payments good
     */
    public void setPaymentsGood(Boolean paymentsGood) {
        this.paymentsGood = paymentsGood;
    }

    /**
     * Gets the change records good.
     *
     * @return the change records good
     */
    public Boolean getChangeRecordsGood() {
        return changeRecordsGood;
    }

    /**
     * Sets the change records good.
     *
     * @param changeRecordsGood the new change records good
     */
    public void setChangeRecordsGood(Boolean changeRecordsGood) {
        this.changeRecordsGood = changeRecordsGood;
    }

    /**
     * Gets the number change records.
     *
     * @return the number change records
     */
    public Integer getNumberChangeRecords() {
        return numberChangeRecords;
    }

    /**
     * Sets the number change records.
     *
     * @param numberChangeRecords the new number change records
     */
    public void setNumberChangeRecords(Integer numberChangeRecords) {
        this.numberChangeRecords = numberChangeRecords;
    }

    /**
     * Gets the number good change records.
     *
     * @return the number good change records
     */
    public Integer getNumberGoodChangeRecords() {
        return numberGoodChangeRecords;
    }

    /**
     * Sets the number good change records.
     *
     * @param numberGoodChangeRecords the new number good change records
     */
    public void setNumberGoodChangeRecords(Integer numberGoodChangeRecords) {
        this.numberGoodChangeRecords = numberGoodChangeRecords;
    }

    /**
     * Gets the number bad change records.
     *
     * @return the number bad change records
     */
    public Integer getNumberBadChangeRecords() {
        return numberBadChangeRecords;
    }

    /**
     * Sets the number bad change records.
     *
     * @param numberBadChangeRecords the new number bad change records
     */
    public void setNumberBadChangeRecords(Integer numberBadChangeRecords) {
        this.numberBadChangeRecords = numberBadChangeRecords;
    }

    /**
     * Gets the number accepted ach payments.
     *
     * @return the number accepted ach payments
     */
    public Integer getNumberAcceptedAchPayments() {
        return numberAcceptedAchPayments;
    }

    /**
     * Sets the number accepted ach payments.
     *
     * @param numberAcceptedAchPayments the new number accepted ach payments
     */
    public void setNumberAcceptedAchPayments(Integer numberAcceptedAchPayments) {
        this.numberAcceptedAchPayments = numberAcceptedAchPayments;
    }

    /**
     * Gets the number unresolved ach payments.
     *
     * @return the number unresolved ach payments
     */
    public Integer getNumberUnresolvedAchPayments() {
        return numberUnresolvedAchPayments;
    }

    /**
     * Sets the number unresolved ach payments.
     *
     * @param numberUnresolvedAchPayments the new number unresolved ach payments
     */
    public void setNumberUnresolvedAchPayments(Integer numberUnresolvedAchPayments) {
        this.numberUnresolvedAchPayments = numberUnresolvedAchPayments;
    }

    /**
     * Gets the number suspended ach payments.
     *
     * @return the number suspended ach payments
     */
    public Integer getNumberSuspendedAchPayments() {
        return numberSuspendedAchPayments;
    }

    /**
     * Sets the number suspended ach payments.
     *
     * @param numberSuspendedAchPayments the new number suspended ach payments
     */
    public void setNumberSuspendedAchPayments(Integer numberSuspendedAchPayments) {
        this.numberSuspendedAchPayments = numberSuspendedAchPayments;
    }

    /**
     * Gets the number accepted check payments.
     *
     * @return the number accepted check payments
     */
    public Integer getNumberAcceptedCheckPayments() {
        return numberAcceptedCheckPayments;
    }

    /**
     * Sets the number accepted check payments.
     *
     * @param numberAcceptedCheckPayments the new number accepted check payments
     */
    public void setNumberAcceptedCheckPayments(Integer numberAcceptedCheckPayments) {
        this.numberAcceptedCheckPayments = numberAcceptedCheckPayments;
    }

    /**
     * Gets the number unresolved check payments.
     *
     * @return the number unresolved check payments
     */
    public Integer getNumberUnresolvedCheckPayments() {
        return numberUnresolvedCheckPayments;
    }

    /**
     * Sets the number unresolved check payments.
     *
     * @param numberUnresolvedCheckPayments the new number unresolved check payments
     */
    public void setNumberUnresolvedCheckPayments(Integer numberUnresolvedCheckPayments) {
        this.numberUnresolvedCheckPayments = numberUnresolvedCheckPayments;
    }

    /**
     * Gets the number suspended check payments.
     *
     * @return the number suspended check payments
     */
    public Integer getNumberSuspendedCheckPayments() {
        return numberSuspendedCheckPayments;
    }

    /**
     * Sets the number suspended check payments.
     *
     * @param numberSuspendedCheckPayments the new number suspended check payments
     */
    public void setNumberSuspendedCheckPayments(Integer numberSuspendedCheckPayments) {
        this.numberSuspendedCheckPayments = numberSuspendedCheckPayments;
    }

    /**
     * Gets the total ach payments.
     *
     * @return the total ach payments
     */
    public BigDecimal getTotalACHPayments() {
        return totalACHPayments;
    }

    /**
     * Sets the total ach payments.
     *
     * @param totalACHPayments the new total ach payments
     */
    public void setTotalACHPayments(BigDecimal totalACHPayments) {
        this.totalACHPayments = totalACHPayments;
    }

    /**
     * Gets the total check payments.
     *
     * @return the total check payments
     */
    public BigDecimal getTotalCheckPayments() {
        return totalCheckPayments;
    }

    /**
     * Sets the total check payments.
     *
     * @param totalCheckPayments the new total check payments
     */
    public void setTotalCheckPayments(BigDecimal totalCheckPayments) {
        this.totalCheckPayments = totalCheckPayments;
    }

    /**
     * Gets the total skipped payments.
     *
     * @return the total skipped payments
     */
    public BigDecimal getTotalSkippedPayments() {
        return totalSkippedPayments;
    }

    /**
     * Sets the total skipped payments.
     *
     * @param totalSkippedPayments the new total skipped payments
     */
    public void setTotalSkippedPayments(BigDecimal totalSkippedPayments) {
        this.totalSkippedPayments = totalSkippedPayments;
    }

    /**
     * Gets the total dupe payments.
     *
     * @return the total dupe payments
     */
    public BigDecimal getTotalDupePayments() {
        return totalDupePayments;
    }

    /**
     * Sets the total dupe payments.
     *
     * @param totalDupePayments the new total dupe payments
     */
    public void setTotalDupePayments(BigDecimal totalDupePayments) {
        this.totalDupePayments = totalDupePayments;
    }

    /**
     * Gets the file summary total.
     *
     * @return the file summary total
     */
    public BigDecimal getFileSummaryTotal() {
        return fileSummaryTotal;
    }

    /**
     * Sets the file summary total.
     *
     * @param fileSummaryTotal the new file summary total
     */
    public void setFileSummaryTotal(BigDecimal fileSummaryTotal) {
        this.fileSummaryTotal = fileSummaryTotal;
    }

    /**
     * Gets the number ach payments.
     *
     * @return the number ach payments
     */
    public Integer getNumberAchPayments() {
        return numberAchPayments;
    }

    /**
     * Sets the number ach payments.
     *
     * @param numberAchPayments the new number ach payments
     */
    public void setNumberAchPayments(Integer numberAchPayments) {
        this.numberAchPayments = numberAchPayments;
    }

    /**
     * Gets the number good ach payments.
     *
     * @return the number good ach payments
     */
    public Integer getNumberGoodAchPayments() {
        return numberGoodAchPayments;
    }

    /**
     * Sets the number good ach payments.
     *
     * @param numberGoodAchPayments the new number good ach payments
     */
    public void setNumberGoodAchPayments(Integer numberGoodAchPayments) {
        this.numberGoodAchPayments = numberGoodAchPayments;
    }

    /**
     * Gets the number bad ach payments.
     *
     * @return the number bad ach payments
     */
    public Integer getNumberBadAchPayments() {
        return numberBadAchPayments;
    }

    /**
     * Sets the number bad ach payments.
     *
     * @param numberBadAchPayments the new number bad ach payments
     */
    public void setNumberBadAchPayments(Integer numberBadAchPayments) {
        this.numberBadAchPayments = numberBadAchPayments;
    }

    /**
     * Gets the number dupe ach payments.
     *
     * @return the number dupe ach payments
     */
    public Integer getNumberDupeAchPayments() {
        return numberDupeAchPayments;
    }

    /**
     * Sets the number dupe ach payments.
     *
     * @param numberDupeAchPayments the new number dupe ach payments
     */
    public void setNumberDupeAchPayments(Integer numberDupeAchPayments) {
        this.numberDupeAchPayments = numberDupeAchPayments;
    }

    /**
     * Gets the number check payments.
     *
     * @return the number check payments
     */
    public Integer getNumberCheckPayments() {
        return numberCheckPayments;
    }

    /**
     * Sets the number check payments.
     *
     * @param numberCheckPayments the new number check payments
     */
    public void setNumberCheckPayments(Integer numberCheckPayments) {
        this.numberCheckPayments = numberCheckPayments;
    }

    /**
     * Gets the number good check payments.
     *
     * @return the number good check payments
     */
    public Integer getNumberGoodCheckPayments() {
        return numberGoodCheckPayments;
    }

    /**
     * Sets the number good check payments.
     *
     * @param numberGoodCheckPayments the new number good check payments
     */
    public void setNumberGoodCheckPayments(Integer numberGoodCheckPayments) {
        this.numberGoodCheckPayments = numberGoodCheckPayments;
    }

    /**
     * Gets the number bad check payments.
     *
     * @return the number bad check payments
     */
    public Integer getNumberBadCheckPayments() {
        return numberBadCheckPayments;
    }

    /**
     * Sets the number bad check payments.
     *
     * @param numberBadCheckPayments the new number bad check payments
     */
    public void setNumberBadCheckPayments(Integer numberBadCheckPayments) {
        this.numberBadCheckPayments = numberBadCheckPayments;
    }

    /**
     * Gets the number dupe check payments.
     *
     * @return the number dupe check payments
     */
    public Integer getNumberDupeCheckPayments() {
        return numberDupeCheckPayments;
    }

    /**
     * Sets the number dupe check payments.
     *
     * @param numberDupeCheckPayments the new number dupe check payments
     */
    public void setNumberDupeCheckPayments(Integer numberDupeCheckPayments) {
        this.numberDupeCheckPayments = numberDupeCheckPayments;
    }

    /**
     * Gets the number good summary records.
     *
     * @return the number good summary records
     */
    public Integer getNumberGoodSummaryRecords() {
        return numberGoodSummaryRecords;
    }

    /**
     * Sets the number good summary records.
     *
     * @param numberGoodSummaryRecords the new number good summary records
     */
    public void setNumberGoodSummaryRecords(Integer numberGoodSummaryRecords) {
        this.numberGoodSummaryRecords = numberGoodSummaryRecords;
    }

    /**
     * Gets the number bad summary records.
     *
     * @return the number bad summary records
     */
    public Integer getNumberBadSummaryRecords() {
        return numberBadSummaryRecords;
    }

    /**
     * Sets the number bad summary records.
     *
     * @param numberBadSummaryRecords the new number bad summary records
     */
    public void setNumberBadSummaryRecords(Integer numberBadSummaryRecords) {
        this.numberBadSummaryRecords = numberBadSummaryRecords;
    }

    /**
     * Gets the number bad data records.
     *
     * @return the number bad data records
     */
    public Integer getNumberBadDataRecords() {
        return numberBadDataRecords;
    }

    /**
     * Sets the number bad data records.
     *
     * @param numberBadDataRecords the new number bad data records
     */
    public void setNumberBadDataRecords(Integer numberBadDataRecords) {
        this.numberBadDataRecords = numberBadDataRecords;
    }

    /**
     * Gets the number lines in file.
     *
     * @return the number lines in file
     */
    public Integer getNumberLinesInFile() {
        return numberLinesInFile;
    }

    /**
     * Sets the number lines in file.
     *
     * @param numberLinesInFile the new number lines in file
     */
    public void setNumberLinesInFile(Integer numberLinesInFile) {
        this.numberLinesInFile = numberLinesInFile;
    }

    /**
     * Gets the number eof markers.
     *
     * @return the number eof markers
     */
    public Integer getNumberEOFMarkers() {
        return numberEOFMarkers;
    }

    /**
     * Sets the number eof markers.
     *
     * @param numberEOFMarkers the new number eof markers
     */
    public void setNumberEOFMarkers(Integer numberEOFMarkers) {
        this.numberEOFMarkers = numberEOFMarkers;
    }

    /**
     * Gets the input name.
     *
     * @return the input name
     */
    public String getInputName() {
        return inputName;
    }

    /**
     * Sets the input name.
     *
     * @param inputName the new input name
     */
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    /**
     * Gets the output name.
     *
     * @return the output name
     */
    public String getOutputName() {
        return outputName;
    }

    /**
     * Sets the output name.
     *
     * @param outputName the new output name
     */
    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }
}
