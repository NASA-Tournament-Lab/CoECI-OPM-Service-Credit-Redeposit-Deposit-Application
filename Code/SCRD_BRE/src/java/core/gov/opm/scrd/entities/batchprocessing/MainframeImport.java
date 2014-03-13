/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.entities.batchprocessing;

import java.util.Date;


/**
 * <p>This class is simply a POJO containing single mainframe record to import.</p>
 *  <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 *
 * <p>
 * <em>Changes in 1.1 (OPM - Reporting - Lockbox and Miscellaneous Module Assembly):</em>
 * <ol>
 * <li>Added id, auditBatchLogId, payTransactionKey, paymentType</li>
 * </ol>
 * </p>
 *
 * @author faeton, TCSASSEMBLER, AleaActaEst, TCSASSEMBLER
 * @version 1.1
 */
public class MainframeImport {

    /**
     * Serves as the primary key.
     * @since 1.1
     */
    private long id;

    /**
     * Represents the record string. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String recordString;

    /**
     * Represents the import date. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Date importDate;

    /**
     * Represents the processing flag. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Boolean processingFlag;

    /**
     * Represents the error flag. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Boolean errorFlag;

    /**
     * Represents the ach flag. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private Boolean achFlag;

    /**
     * Represents the file name. It is accessible by getter and modified by setter. It can be any value. The
     * default value is null.
     */
    private String fileName;

    /**
     * Reference Log ID.
     * @since 1.1
     */
    private String auditBatchLogId;

    /**
     * Reference Payment Transaction key.
     * @since 1.1
     */
    private Integer payTransactionKey;

    /**
     * Reference Payment type.
     * @since 1.1
     */
    private LockboxPaymentType paymentType;

    /**
     * Represents the audit batch id field. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Long batchDailyPayments;

    /**
     * Instantiates a new mainframe import.
     */
    public MainframeImport() {
    }

    /**
     * Gets the record string.
     *
     * @return the record string
     */
    public String getRecordString() {
        return recordString;
    }

    /**
     * Sets the record string.
     *
     * @param recordString the new record string
     */
    public void setRecordString(String recordString) {
        this.recordString = recordString;
    }

    /**
     * Gets the import date.
     *
     * @return the import date
     */
    public Date getImportDate() {
        return importDate;
    }

    /**
     * Sets the import date.
     *
     * @param importDate the new import date
     */
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    /**
     * Gets the processing flag.
     *
     * @return the processing flag
     */
    public Boolean getProcessingFlag() {
        return processingFlag;
    }

    /**
     * Sets the processing flag.
     *
     * @param processingFlag the new processing flag
     */
    public void setProcessingFlag(Boolean processingFlag) {
        this.processingFlag = processingFlag;
    }

    /**
     * Gets the error flag.
     *
     * @return the error flag
     */
    public Boolean getErrorFlag() {
        return errorFlag;
    }

    /**
     * Sets the error flag.
     *
     * @param errorFlag the new error flag
     */
    public void setErrorFlag(Boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    /**
     * Gets the ach flag.
     *
     * @return the ach flag
     */
    public Boolean getAchFlag() {
        return achFlag;
    }

    /**
     * Sets the ach flag.
     *
     * @param achFlag the new ach flag
     */
    public void setAchFlag(Boolean achFlag) {
        this.achFlag = achFlag;
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the file name.
     *
     * @param fileName the new file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the audit batch id.
     *
     * @return the audit batch id log
     */
    public Long getBatchDailyPayments() {
        return batchDailyPayments;
    }

    /**
     * Sets the audit batch id.
     *
     * @param batchDailyPayments the new audit batch id to set
     */
    public void setBatchDailyPayments(Long batchDailyPayments) {
        this.batchDailyPayments = batchDailyPayments;
    }

    /**
     * Gets the value of the field <code>auditBatchLogId</code>.
     * @return the auditBatchLogId
     * @since 1.1
     */
    public String getAuditBatchLogId() {
        return auditBatchLogId;
    }

    /**
     * Sets the value of the field <code>auditBatchLogId</code>.
     * @param auditBatchLogId the auditBatchLogId to set
     * @since 1.1
     */
    public void setAuditBatchLogId(String auditBatchLogId) {
        this.auditBatchLogId = auditBatchLogId;
    }

    /**
     * Gets the value of the field <code>payTransactionKey</code>.
     * @return the payTransactionKey
     * @since 1.1
     */
    public Integer getPayTransactionKey() {
        return payTransactionKey;
    }

    /**
     * Sets the value of the field <code>payTransactionKey</code>.
     * @param payTransactionKey the payTransactionKey to set
     * @since 1.1
     */
    public void setPayTransactionKey(Integer payTransactionKey) {
        this.payTransactionKey = payTransactionKey;
    }

    /**
     * Gets the value of the field <code>paymentType</code>.
     * @return the paymentType
     * @since 1.1
     */
    public LockboxPaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the field <code>paymentType</code>.
     * @param paymentType the paymentType to set
     * @since 1.1
     */
    public void setPaymentType(LockboxPaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the value of the field <code>id</code>.
     * @return the id
     * @since 1.1
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the field <code>id</code>.
     * @param id the id to set
     * @since 1.1
     */
    public void setId(long id) {
        this.id = id;
    }
}
