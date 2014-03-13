/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.batchprocessing;

import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>This class is simply a POJO record line in file. Additionally, it provides capabilities to read record line
 * data.</p>
 *  <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public class RFile {

    /**
     * Represents the transaction code. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String transactionCode;

    /**
     * Represents the sub transaction code. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer subTransactionCode;

    /**
     * Represents the cd number. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String cdNumber;

    /**
     * Represents the block number. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String blockNumber;

    /**
     * Represents the sequence number. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String sequenceNumber;

    /**
     * Represents the claim number. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String claimNumber;

    /**
     * Represents the date of birth. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Date dateOfBirth;

    /**
     * Represents the amount. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private BigDecimal amount;

    /**
     * Represents the cd date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date cdDate;

    /**
     * Represents the corrected data. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String correctedData;

    /**
     * Represents the index of account field to correct. It is accessible by getter and modified by setter. It
     * can be any value. The default value is null.
     */
    private Integer indexOfAccountFieldToCorrect;

    /**
     * Represents the ach payment flag. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Boolean achPaymentFlag;

    /**
     * Represents the payment type. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private LockboxPaymentType paymentType;

    /**
     * Represents the file line. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String fileLine;

    /**
     * Represents the valid file record. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Boolean validFileRecord;

    /**
     * Represents the error message. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String errorMessage;

    /**
     * Represents the record import error. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BatchImportError recordImportError;

    /**
     * Represents the eof. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Boolean eof;

    /**
     * Instantiates a new r file.
     */
    public RFile() {
    }

    /**
     * Parses the file line.
     *
     * @return the string
     */
    public String parseFileLine() {

        return fileLine;
    }

    /**
     * Clear.
     */
    public void clear() {
        this.achPaymentFlag = null;
        this.amount = null;
        this.blockNumber = null;
        this.cdDate = null;
        this.claimNumber = null;
        this.correctedData = null;
        this.dateOfBirth = null;
        this.eof = null;
        this.errorMessage = null;
        this.indexOfAccountFieldToCorrect = null;
        this.paymentType = null;
        this.recordImportError = null;
        this.sequenceNumber = null;
        this.subTransactionCode = null;
        this.transactionCode = null;
        this.validFileRecord = null;
    }

    /**
     * Gets the transaction code.
     *
     * @return the transaction code
     */
    public String getTransactionCode() {
        return transactionCode;
    }

    /**
     * Sets the transaction code.
     *
     * @param transactionCode the new transaction code
     */
    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    /**
     * Gets the sub transaction code.
     *
     * @return the sub transaction code
     */
    public Integer getSubTransactionCode() {
        return subTransactionCode;
    }

    /**
     * Sets the sub transaction code.
     *
     * @param subTransactionCode the new sub transaction code
     */
    public void setSubTransactionCode(Integer subTransactionCode) {
        this.subTransactionCode = subTransactionCode;
    }

    /**
     * Gets the cd number.
     *
     * @return the cd number
     */
    public String getCdNumber() {
        return cdNumber;
    }

    /**
     * Sets the cd number.
     *
     * @param cdNumber the new cd number
     */
    public void setCdNumber(String cdNumber) {
        this.cdNumber = cdNumber;
    }

    /**
     * Gets the block number.
     *
     * @return the block number
     */
    public String getBlockNumber() {
        return blockNumber;
    }

    /**
     * Sets the block number.
     *
     * @param blockNumber the new block number
     */
    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    /**
     * Gets the sequence number.
     *
     * @return the sequence number
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequence number.
     *
     * @param sequenceNumber the new sequence number
     */
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the claim number.
     *
     * @return the claim number
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    /**
     * Sets the claim number.
     *
     * @param claimNumber the new claim number
     */
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    /**
     * Gets the date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth.
     *
     * @param dateOfBirth the new date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the cd date.
     *
     * @return the cd date
     */
    public Date getCdDate() {
        return cdDate;
    }

    /**
     * Sets the cd date.
     *
     * @param cdDate the new cd date
     */
    public void setCdDate(Date cdDate) {
        this.cdDate = cdDate;
    }

    /**
     * Gets the corrected data.
     *
     * @return the corrected data
     */
    public String getCorrectedData() {
        return correctedData;
    }

    /**
     * Sets the corrected data.
     *
     * @param correctedData the new corrected data
     */
    public void setCorrectedData(String correctedData) {
        this.correctedData = correctedData;
    }

    /**
     * Gets the index of account field to correct.
     *
     * @return the index of account field to correct
     */
    public Integer getIndexOfAccountFieldToCorrect() {
        return indexOfAccountFieldToCorrect;
    }

    /**
     * Sets the index of account field to correct.
     *
     * @param indexOfAccountFieldToCorrect the new index of account field to correct
     */
    public void setIndexOfAccountFieldToCorrect(Integer indexOfAccountFieldToCorrect) {
        this.indexOfAccountFieldToCorrect = indexOfAccountFieldToCorrect;
    }

    /**
     * Gets the ach payment flag.
     *
     * @return the ach payment flag
     */
    public Boolean getAchPaymentFlag() {
        return achPaymentFlag;
    }

    /**
     * Sets the ach payment flag.
     *
     * @param achPaymentFlag the new ach payment flag
     */
    public void setAchPaymentFlag(Boolean achPaymentFlag) {
        this.achPaymentFlag = achPaymentFlag;
    }

    /**
     * Gets the payment type.
     *
     * @return the payment type
     */
    public LockboxPaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type.
     *
     * @param paymentType the new payment type
     */
    public void setPaymentType(LockboxPaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the file line.
     *
     * @return the file line
     */
    public String getFileLine() {
        return fileLine;
    }

    /**
     * Sets the file line.
     *
     * @param fileLine the new file line
     */
    public void setFileLine(String fileLine) {
        this.fileLine = fileLine;
    }

    /**
     * Gets the valid file record.
     *
     * @return the valid file record
     */
    public Boolean getValidFileRecord() {
        return validFileRecord;
    }

    /**
     * Sets the valid file record.
     *
     * @param validFileRecord the new valid file record
     */
    public void setValidFileRecord(Boolean validFileRecord) {
        this.validFileRecord = validFileRecord;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage the new error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the record import error.
     *
     * @return the record import error
     */
    public BatchImportError getRecordImportError() {
        return recordImportError;
    }

    /**
     * Sets the record import error.
     *
     * @param recordImportError the new record import error
     */
    public void setRecordImportError(BatchImportError recordImportError) {
        this.recordImportError = recordImportError;
    }

    /**
     * Gets the eof.
     *
     * @return the eof
     */
    public Boolean getEof() {
        return eof;
    }

    /**
     * Sets the eof.
     *
     * @param eof the new eof
     */
    public void setEof(Boolean eof) {
        this.eof = eof;
    }
}
