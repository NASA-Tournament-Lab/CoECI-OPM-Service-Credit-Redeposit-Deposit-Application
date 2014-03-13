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

package gov.opm.scrd.batchprocessing.jobs;

import gov.opm.scrd.entities.batchprocessing.BatchImportError;
import gov.opm.scrd.entities.batchprocessing.LockboxPaymentType;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * This class is simply a POJO providing capabilities to parse lockbox file//s record line.
 * </p>
 *
 * <p>
 * Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.
 * </p>
 *
 * @author faeton, liuliquan
 * @version 1.0
 */
@Component
public class RFile {

    /**
     * Represents the date format.
     */
    private static final DateFormat DF = new SimpleDateFormat("MMddyy");

    /**
     * Represents the minimum work age.
     * <p/>
     * Injected by Spring. It has a setter. The default value is 16.
     */
    @Autowired
    private Integer minWorkAge = 16;

    /**
     * Represents the file line. It is accessible by getter and modified by parseFileLine(). It can be any value. The
     * default value is null.
     */
    private String fileLine;

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
     * Represents the record import error. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private BatchImportError recordImportError;

    /**
     * Represents the error message. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private String errorMessage;

    /**
     * Represents the eof. It is accessible by getter and modified by setter. It can be any value. The default
     * value is null.
     */
    private Boolean eof;

    /**
     * Empty constructor.
     */
    public RFile() {
        // empty
    }

    /**
     * Parse common part.
     *
     * @return true if parse is successful; false otherwise.
     */
    private boolean parseCommon() {

        try {
            // Transaction code: 1 - 1
            recordImportError = BatchImportError.TRANSACTION_CODE_MUST_BE_C_OR_R;
            transactionCode = fileLine.substring(0, 1);

            // Claim number: 11 - 17
            recordImportError = BatchImportError.CLAIM_NO_MUST_BE_7_DIGITS;
            claimNumber = fileLine.substring(10, 17);

            // Date of birth: 18 - 23
            recordImportError = BatchImportError.DOB_MUST_BE_VALID;
            String dateStr = fileLine.substring(17, 23);

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(DF.parse(dateStr));

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date());

            while (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR) - minWorkAge) {
                cal1.add(Calendar.YEAR, -100);
            }

            dateOfBirth = cal1.getTime();
        } catch (IndexOutOfBoundsException e) {
            errorMessage = e.getMessage();
            return false;
        } catch (ParseException e) {
            errorMessage = e.getMessage();
            return false;
        }

        recordImportError = BatchImportError.NO_ERROR;
        return true;
    }

    /**
     * Parse C line.
     *
     * @return true if parse is successful; false otherwise.
     */
    private boolean parseCLine() {
        //Begin  End Length  Name    Values
        //1      1   1       Constant    "C"
        //2      2   1       Filler  Space
        //3      4   2       Change Type Index   Integer between 1 and 10
        //5      10  6       Filler  Spaces
        //11     17  7       CSD
        //18     23  6       Date of Birth   MMDDYY
        //24     25  2       Filler  Spaces
        //26     80  55      Change String   New name or address value

        try {
            // Change field index: 3 - 4
            recordImportError = BatchImportError.FIELD_NO_MUST_BE_IN_RANGE_01_10;
            indexOfAccountFieldToCorrect = Integer.parseInt(fileLine.substring(2, 4));

            // Change string
            recordImportError = BatchImportError.CORRECTED_DATA_MUST_BE_PRESENT;
            correctedData = fileLine.substring(23).trim();

        } catch (IndexOutOfBoundsException e) {
            errorMessage = e.getMessage();
            return false;
        } catch (NumberFormatException e) {
            errorMessage = e.getMessage();
            return false;
        }

        recordImportError = BatchImportError.NO_ERROR;
        return true;
    }

    /**
     * Parse R line.
     *
     * @return true if parse is successful; false otherwise.
     */
    private boolean parseRLine() {
        //Begin  End Length  Name    Values
        //1      1   1       Constant    "R"
        //2      2   1       Type    "6"
        //3      5   3       Batch
        //6      7   2       Block
        //8      9   2       Sequence
        //10     10  1       Filler  Space
        //11     17  7       CSD
        //18     23  6       Date of Birth   MMDDYY
        //24     30  7       Payment Amount  Zero-filled, Implicit Decimal
        //31     36  6       Deposit Date
        //37     37  1       Filler  Spaces
        //38     38  1       ACH Flag    1 if ACH, 0 if check
        //39     80  42      Filler  42 spaces

        try {
            // Sub transaction code: 2 - 2
            recordImportError = BatchImportError.TRANSACTION_CODE_MUST_BE_C_OR_R;
            subTransactionCode = Integer.parseInt(fileLine.substring(1, 2));

            // Batch number: 3 - 5
            recordImportError = BatchImportError.CD_NUMBER_MUST_BE_VALID;
            cdNumber = fileLine.substring(2, 5);

            // Block number: 6 - 7
            recordImportError = BatchImportError.BLOCK_NUMBER_MUST_BE_VALID;
            blockNumber = fileLine.substring(5, 7);

            // Sequence number: 8 - 9
            recordImportError = BatchImportError.SEQ_NUMBER_MUST_BE_VALID;
            sequenceNumber = fileLine.substring(7, 9);

            // Payment date: 31 - 36
            recordImportError = BatchImportError.CD_DATE_MUST_BE_VALID;
            cdDate = DF.parse(fileLine.substring(30, 36));

            // Payment amount: 24 - 30
            recordImportError = BatchImportError.AMOUNT_MUST_BE_VALID_DECIMAL_NUMBER;
            amount = new BigDecimal(fileLine.substring(23, 30)).divide(BatchProcessHelper.HUNDRED);
        } catch (IndexOutOfBoundsException e) {
            errorMessage = e.getMessage();
            return false;
        } catch (NumberFormatException e) {
            errorMessage = e.getMessage();
            return false;
        } catch (ParseException e) {
            errorMessage = e.getMessage();
            return false;
        }

        paymentType = LockboxPaymentType.UNKNOWN;
        if (fileLine.length() > 37) {
            String achFlag = fileLine.substring(37, 38);
            if ("0".equals(achFlag)) {
                paymentType = LockboxPaymentType.CHECK;
            } else if ("1".equals(achFlag)) {
                paymentType = LockboxPaymentType.ACH;
            }
        }

        recordImportError = BatchImportError.NO_ERROR;
        return true;
    }

    /**
     * Parses the file line.
     *
     * @param line to parse
     */
    public synchronized void parseFileLine(String line) {
        // Clear first
        clear();

        fileLine = line;

        if (fileLine == null) {
            eof = true;
            recordImportError = BatchImportError.EOF_ERROR;
            errorMessage = "Empty file line.";
        } else {
            eof = false;

            if (!parseCommon()) {
                return;
            }

            if ("C".equals(transactionCode)) {
                parseCLine();
            } else if ("R".equals(transactionCode)) {
                parseRLine();
            } else {
                recordImportError = BatchImportError.TRANSACTION_CODE_MUST_BE_C_OR_R;
                errorMessage = "Invalid transaction code.";
            }
        }
    }

    /**
     * Clear.
     */
    public void clear() {
        this.fileLine = null;
        this.eof = false;
        this.recordImportError = null;
        this.errorMessage = null;

        this.transactionCode = null;
        this.subTransactionCode = null;
        this.cdNumber = null;
        this.blockNumber = null;
        this.sequenceNumber = null;
        this.claimNumber = null;
        this.dateOfBirth = null;
        this.amount = null;
        this.cdDate = null;
        this.achPaymentFlag = null;
        this.paymentType = null;
        this.correctedData = null;
        this.indexOfAccountFieldToCorrect = null;
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
     * Gets the valid file record.
     *
     * @return the valid file record
     */
    public Boolean getValidFileRecord() {
        return recordImportError == BatchImportError.NO_ERROR;
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

    /**
     * Setter method for property <tt>minWorkAge</tt>.
     * @param minWorkAge value to be assigned to property minWorkAge
     */
    public void setMinWorkAge(int minWorkAge) {
        this.minWorkAge = minWorkAge;
    }
}
