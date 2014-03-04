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

package gov.opm.scrd.entities.batchprocessing;

import java.math.BigDecimal;


/**
 * <p>This class is simply a POJO containing status about the currently processed importing.</p>
 *  <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public class ImportStatus {
    /**
     * Represents the audit batch id. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Long auditBatchId;

    /**
     * Represents the value to show if the import is successful. It is accessible by getter and modified by
     * setter. It can be any value. The default value is true.
     */
    private boolean successful = true;

    /**
     * Represents the value to show if the import status is permission denied. It is accessible by getter and
     * modified by setter. It can be any value. The default value is false.
     */
    private boolean permissionDenied = false;

    /**
     * Represents the value to show if the payments are good. It is accessible by getter and modified by
     * setter. It can be any value. The default value is true.
     */
    private boolean paymentsGood = true;

    /**
     * Represents the value to show if the change records good. It is accessible by getter and modified by
     * setter. It can be any value. The default value is true.
     */
    private boolean changeRecordsGood = true;

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
     * Represents the number of lines in file. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private int numberLinesInFile = 0;

    /**
     * Represents the number of discrete records. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private int numberDiscreteRecords = 0;

    /**
     * Represents the number of eof markers. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private int numberEOFMarkers = 0;

    /**
     * Represents the number of duplicate records. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private int numberDuplicateRecords = 0;

    /**
     * Represents the number of bad data records. It is accessible by getter and modified by setter. It can be
     * any value. The default value is 0.
     */
    private int numberBadDataRecords = 0;

    /**
     * Represents the number of good summary records. It is accessible by getter and modified by setter. It can
     * be any value. The default value is 0.
     */
    private int numberGoodSummaryRecords = 0;

    /**
     * Represents the number of bad summary records. It is accessible by getter and modified by setter. It can
     * be any value. The default value is 0.
     */
    private int numberBadSummaryRecords = 0;

    /**
     * Represents the number of good change records. It is accessible by getter and modified by setter. It can
     * be any value. The default value is 0.
     */
    private int numberGoodChangeRecords = 0;

    /**
     * Represents the number of bad change records. It is accessible by getter and modified by setter. It can
     * be any value. The default value is 0.
     */
    private int numberBadChangeRecords = 0;

    /**
     * Represents the number of no match change records. It is accessible by getter and modified by setter. It can
     * be any value. The default value is 0.
     */
    private int numberNoMatchChangeRecords = 0;

    /**
     * Represents the number of good ach payments. It is accessible by getter and modified by setter. It can be
     * any value. The default value is 0.
     */
    private int numberGoodAchPayments = 0;

    /**
     * Represents the number of bad ach payments. It is accessible by getter and modified by setter. It can be
     * any value. The default value is 0.
     */
    private int numberBadAchPayments = 0;

    /**
     * Represents the number of dupe ach payments. It is accessible by getter and modified by setter. It can be
     * any value. The default value is 0.
     */
    private int numberDupeAchPayments = 0;

    /**
     * Represents the number of good check payments. It is accessible by getter and modified by setter. It can
     * be any value. The default value is 0.
     */
    private int numberGoodCheckPayments = 0;

    /**
     * Represents the number of bad check payments. It is accessible by getter and modified by setter. It can
     * be any value. The default value is 0.
     */
    private int numberBadCheckPayments = 0;

    /**
     * Represents the number of dupe check payments. It is accessible by getter and modified by setter. It can
     * be any value. The default value is 0.
     */
    private int numberDupeCheckPayments = 0;

    /**
     * Represents the file summary total. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private BigDecimal fileSummaryTotal = BigDecimal.ZERO;

    /**
     * Represents the total summed transactions money. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private BigDecimal transactionsTotal = BigDecimal.ZERO;

    /**
     * Represents the total ach payments. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private BigDecimal totalACHPayments = BigDecimal.ZERO;

    /**
     * Represents the total check payments. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private BigDecimal totalCheckPayments = BigDecimal.ZERO;

    /**
     * Represents the total skipped payments. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private BigDecimal totalSkippedPayments = BigDecimal.ZERO;

    /**
     * Represents the total dupe payments. It is accessible by getter and modified by setter. It can be any
     * value. The default value is 0.
     */
    private BigDecimal totalDupePayments = BigDecimal.ZERO;

    /**
     * Represents the number of accepted ach payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is 0.
     */
    private int numberAcceptedAchPayments = 0;

    /**
     * Represents the number of unresolved ach payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is 0.
     */
    private int numberUnresolvedAchPayments = 0;

    /**
     * Represents the number of suspended ach payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is 0.
     */
    private int numberSuspendedAchPayments = 0;

    /**
     * Represents the number of accepted check payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is 0.
     */
    private int numberAcceptedCheckPayments = 0;

    /**
     * Represents the number of unresolved check payments. It is accessible by getter and modified by setter.
     * It can be any value. The default value is 0.
     */
    private int numberUnresolvedCheckPayments = 0;

    /**
     * Represents the number of suspended check payments. It is accessible by getter and modified by setter. It
     * can be any value. The default value is 0.
     */
    private int numberSuspendedCheckPayments = 0;

    /**
     * Instantiates a new import status.
     */
    public ImportStatus() {
    }

    /**
     * Gets the successful.
     *
     * @return the successful
     */
    public boolean getSuccessful() {
        return successful;
    }

    /**
     * Sets the successful.
     *
     * @param successful the new successful
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /**
     * Gets the permission denied.
     *
     * @return the permission denied
     */
    public boolean getPermissionDenied() {
        return permissionDenied;
    }

    /**
     * Sets the permission denied.
     *
     * @param permissionDenied the new permission denied
     */
    public void setPermissionDenied(boolean permissionDenied) {
        this.permissionDenied = permissionDenied;
    }

    /**
     * Gets the payments good.
     *
     * @return the payments good
     */
    public boolean getPaymentsGood() {
        return paymentsGood;
    }

    /**
     * Sets the payments good.
     *
     * @param paymentsGood the new payments good
     */
    public void setPaymentsGood(boolean paymentsGood) {
        this.paymentsGood = paymentsGood;
    }

    /**
     * Gets the change records good.
     *
     * @return the change records good
     */
    public boolean getChangeRecordsGood() {
        return changeRecordsGood;
    }

    /**
     * Sets the change records good.
     *
     * @param changeRecordsGood the new change records good
     */
    public void setChangeRecordsGood(boolean changeRecordsGood) {
        this.changeRecordsGood = changeRecordsGood;
    }

    /**
     * Gets the number good change records.
     *
     * @return the number good change records
     */
    public int getNumberGoodChangeRecords() {
        return numberGoodChangeRecords;
    }

    /**
     * Sets the number good change records.
     *
     * @param numberGoodChangeRecords the new number good change records
     */
    public void setNumberGoodChangeRecords(int numberGoodChangeRecords) {
        this.numberGoodChangeRecords = numberGoodChangeRecords;
    }

    /**
     * Gets the number bad change records.
     *
     * @return the number bad change records
     */
    public int getNumberBadChangeRecords() {
        return numberBadChangeRecords;
    }

    /**
     * Sets the number bad change records.
     *
     * @param numberBadChangeRecords the new number bad change records
     */
    public void setNumberBadChangeRecords(int numberBadChangeRecords) {
        this.numberBadChangeRecords = numberBadChangeRecords;
    }

    /**
     * Gets the number accepted ach payments.
     *
     * @return the number accepted ach payments
     */
    public int getNumberAcceptedAchPayments() {
        return numberAcceptedAchPayments;
    }

    /**
     * Sets the number accepted ach payments.
     *
     * @param numberAcceptedAchPayments the new number accepted ach payments
     */
    public void setNumberAcceptedAchPayments(int numberAcceptedAchPayments) {
        this.numberAcceptedAchPayments = numberAcceptedAchPayments;
    }

    /**
     * Gets the number unresolved ach payments.
     *
     * @return the number unresolved ach payments
     */
    public int getNumberUnresolvedAchPayments() {
        return numberUnresolvedAchPayments;
    }

    /**
     * Sets the number unresolved ach payments.
     *
     * @param numberUnresolvedAchPayments the new number unresolved ach payments
     */
    public void setNumberUnresolvedAchPayments(int numberUnresolvedAchPayments) {
        this.numberUnresolvedAchPayments = numberUnresolvedAchPayments;
    }

    /**
     * Gets the number suspended ach payments.
     *
     * @return the number suspended ach payments
     */
    public int getNumberSuspendedAchPayments() {
        return numberSuspendedAchPayments;
    }

    /**
     * Sets the number suspended ach payments.
     *
     * @param numberSuspendedAchPayments the new number suspended ach payments
     */
    public void setNumberSuspendedAchPayments(int numberSuspendedAchPayments) {
        this.numberSuspendedAchPayments = numberSuspendedAchPayments;
    }

    /**
     * Gets the number accepted check payments.
     *
     * @return the number accepted check payments
     */
    public int getNumberAcceptedCheckPayments() {
        return numberAcceptedCheckPayments;
    }

    /**
     * Sets the number accepted check payments.
     *
     * @param numberAcceptedCheckPayments the new number accepted check payments
     */
    public void setNumberAcceptedCheckPayments(int numberAcceptedCheckPayments) {
        this.numberAcceptedCheckPayments = numberAcceptedCheckPayments;
    }

    /**
     * Gets the number unresolved check payments.
     *
     * @return the number unresolved check payments
     */
    public int getNumberUnresolvedCheckPayments() {
        return numberUnresolvedCheckPayments;
    }

    /**
     * Sets the number unresolved check payments.
     *
     * @param numberUnresolvedCheckPayments the new number unresolved check payments
     */
    public void setNumberUnresolvedCheckPayments(int numberUnresolvedCheckPayments) {
        this.numberUnresolvedCheckPayments = numberUnresolvedCheckPayments;
    }

    /**
     * Gets the number suspended check payments.
     *
     * @return the number suspended check payments
     */
    public int getNumberSuspendedCheckPayments() {
        return numberSuspendedCheckPayments;
    }

    /**
     * Sets the number suspended check payments.
     *
     * @param numberSuspendedCheckPayments the new number suspended check payments
     */
    public void setNumberSuspendedCheckPayments(int numberSuspendedCheckPayments) {
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
     * Gets the number good ach payments.
     *
     * @return the number good ach payments
     */
    public int getNumberGoodAchPayments() {
        return numberGoodAchPayments;
    }

    /**
     * Sets the number good ach payments.
     *
     * @param numberGoodAchPayments the new number good ach payments
     */
    public void setNumberGoodAchPayments(int numberGoodAchPayments) {
        this.numberGoodAchPayments = numberGoodAchPayments;
    }

    /**
     * Gets the number bad ach payments.
     *
     * @return the number bad ach payments
     */
    public int getNumberBadAchPayments() {
        return numberBadAchPayments;
    }

    /**
     * Sets the number bad ach payments.
     *
     * @param numberBadAchPayments the new number bad ach payments
     */
    public void setNumberBadAchPayments(int numberBadAchPayments) {
        this.numberBadAchPayments = numberBadAchPayments;
    }

    /**
     * Gets the number dupe ach payments.
     *
     * @return the number dupe ach payments
     */
    public int getNumberDupeAchPayments() {
        return numberDupeAchPayments;
    }

    /**
     * Sets the number dupe ach payments.
     *
     * @param numberDupeAchPayments the new number dupe ach payments
     */
    public void setNumberDupeAchPayments(int numberDupeAchPayments) {
        this.numberDupeAchPayments = numberDupeAchPayments;
    }

    /**
     * Gets the number good check payments.
     *
     * @return the number good check payments
     */
    public int getNumberGoodCheckPayments() {
        return numberGoodCheckPayments;
    }

    /**
     * Sets the number good check payments.
     *
     * @param numberGoodCheckPayments the new number good check payments
     */
    public void setNumberGoodCheckPayments(int numberGoodCheckPayments) {
        this.numberGoodCheckPayments = numberGoodCheckPayments;
    }

    /**
     * Gets the number bad check payments.
     *
     * @return the number bad check payments
     */
    public int getNumberBadCheckPayments() {
        return numberBadCheckPayments;
    }

    /**
     * Sets the number bad check payments.
     *
     * @param numberBadCheckPayments the new number bad check payments
     */
    public void setNumberBadCheckPayments(int numberBadCheckPayments) {
        this.numberBadCheckPayments = numberBadCheckPayments;
    }

    /**
     * Gets the number dupe check payments.
     *
     * @return the number dupe check payments
     */
    public int getNumberDupeCheckPayments() {
        return numberDupeCheckPayments;
    }

    /**
     * Sets the number dupe check payments.
     *
     * @param numberDupeCheckPayments the new number dupe check payments
     */
    public void setNumberDupeCheckPayments(int numberDupeCheckPayments) {
        this.numberDupeCheckPayments = numberDupeCheckPayments;
    }

    /**
     * Gets the number good summary records.
     *
     * @return the number good summary records
     */
    public int getNumberGoodSummaryRecords() {
        return numberGoodSummaryRecords;
    }

    /**
     * Sets the number good summary records.
     *
     * @param numberGoodSummaryRecords the new number good summary records
     */
    public void setNumberGoodSummaryRecords(int numberGoodSummaryRecords) {
        this.numberGoodSummaryRecords = numberGoodSummaryRecords;
    }

    /**
     * Gets the number bad summary records.
     *
     * @return the number bad summary records
     */
    public int getNumberBadSummaryRecords() {
        return numberBadSummaryRecords;
    }

    /**
     * Sets the number bad summary records.
     *
     * @param numberBadSummaryRecords the new number bad summary records
     */
    public void setNumberBadSummaryRecords(int numberBadSummaryRecords) {
        this.numberBadSummaryRecords = numberBadSummaryRecords;
    }

    /**
     * Gets the number bad data records.
     *
     * @return the number bad data records
     */
    public int getNumberBadDataRecords() {
        return numberBadDataRecords;
    }

    /**
     * Sets the number bad data records.
     *
     * @param numberBadDataRecords the new number bad data records
     */
    public void setNumberBadDataRecords(int numberBadDataRecords) {
        this.numberBadDataRecords = numberBadDataRecords;
    }

    /**
     * Gets the number lines in file.
     *
     * @return the number lines in file
     */
    public int getNumberLinesInFile() {
        return numberLinesInFile;
    }

    /**
     * Sets the number lines in file.
     *
     * @param numberLinesInFile the new number lines in file
     */
    public void setNumberLinesInFile(int numberLinesInFile) {
        this.numberLinesInFile = numberLinesInFile;
    }

    /**
     * Gets the number eof markers.
     *
     * @return the number eof markers
     */
    public int getNumberEOFMarkers() {
        return numberEOFMarkers;
    }

    /**
     * Sets the number eof markers.
     *
     * @param numberEOFMarkers the new number eof markers
     */
    public void setNumberEOFMarkers(int numberEOFMarkers) {
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

    /**
     * Getter method for property <tt>transactionsTotal</tt>.
     * @return property value of transactionsTotal
     */
    public BigDecimal getTransactionsTotal() {
        return transactionsTotal;
    }

    /**
     * Setter method for property <tt>transactionsTotal</tt>.
     * @param transactionsTotal value to be assigned to property transactionsTotal
     */
    public void setTransactionsTotal(BigDecimal transactionsTotal) {
        this.transactionsTotal = transactionsTotal;
    }

    /**
     * Getter method for property <tt>numberNoMatchChangeRecords</tt>.
     * @return property value of numberNoMatchChangeRecords
     */
    public int getNumberNoMatchChangeRecords() {
        return numberNoMatchChangeRecords;
    }

    /**
     * Setter method for property <tt>numberNoMatchChangeRecords</tt>.
     * @param numberNoMatchChangeRecords value to be assigned to property numberNoMatchChangeRecords
     */
    public void setNumberNoMatchChangeRecords(int numberNoMatchChangeRecords) {
        this.numberNoMatchChangeRecords = numberNoMatchChangeRecords;
    }

    /**
     * Getter method for property <tt>numberDuplicateRecords</tt>.
     * @return property value of numberDuplicateRecords
     */
    public int getNumberDuplicateRecords() {
        return numberDuplicateRecords;
    }

    /**
     * Setter method for property <tt>numberDuplicateRecords</tt>.
     * @param numberDuplicateRecords value to be assigned to property numberDuplicateRecords
     */
    public void setNumberDuplicateRecords(int numberDuplicateRecords) {
        this.numberDuplicateRecords = numberDuplicateRecords;
    }

    /**
     * Getter method for property <tt>numberDiscreteRecords</tt>.
     * @return property value of numberDiscreteRecords
     */
    public int getNumberDiscreteRecords() {
        return numberDiscreteRecords;
    }

    /**
     * Setter method for property <tt>numberDiscreteRecords</tt>.
     * @param numberDiscreteRecords value to be assigned to property numberDiscreteRecords
     */
    public void setNumberDiscreteRecords(int numberDiscreteRecords) {
        this.numberDiscreteRecords = numberDiscreteRecords;
    }

    /**
     * Getter method for property <tt>auditBatchId</tt>.
     * @return property value of auditBatchId
     */
    public Long getAuditBatchId() {
        return auditBatchId;
    }

    /**
     * Setter method for property <tt>auditBatchId</tt>.
     * @param auditBatchId value to be assigned to property auditBatchId
     */
    public void setAuditBatchId(Long auditBatchId) {
        this.auditBatchId = auditBatchId;
    }

}
