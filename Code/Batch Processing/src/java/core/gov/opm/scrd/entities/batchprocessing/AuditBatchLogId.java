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

import java.util.Date;

/**
 * <p>
 * This class is simply a POJO containing single audit batch log ID.
 * </p>
 *
 * <p>
 * Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe manner.
 * </p>
 *
 * @author AleaActaEst, TCSASSEMBLER
 * @version 1.0
 */
public class AuditBatchLogId {

    /**
     * Primary key.
     */
    private long id;

    /**
     * Log ID.
     */
    private String auditBatchLogId;

    /**
     * Batch Date.
     */
    private Date batchDate;

    /**
     * Batch Number.
     */
    private Integer batchNumber;

    /**
     * Default constructor.
     */
    public AuditBatchLogId() {
    }

    /**
     * Gets the value of the field <code>id</code>.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the field <code>id</code>.
     *
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the value of the field <code>auditBatchLogId</code>.
     *
     * @return the auditBatchLogId
     */
    public String getAuditBatchLogId() {
        return auditBatchLogId;
    }

    /**
     * Sets the value of the field <code>auditBatchLogId</code>.
     *
     * @param auditBatchLogId the auditBatchLogId to set
     */
    public void setAuditBatchLogId(String auditBatchLogId) {
        this.auditBatchLogId = auditBatchLogId;
    }

    /**
     * Gets the value of the field <code>batchDate</code>.
     *
     * @return the batchDate
     */
    public Date getBatchDate() {
        return batchDate;
    }

    /**
     * Sets the value of the field <code>batchDate</code>.
     *
     * @param batchDate the batchDate to set
     */
    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    /**
     * Gets the value of the field <code>batchNumber</code>.
     *
     * @return the batchNumber
     */
    public Integer getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the value of the field <code>batchNumber</code>.
     *
     * @param batchNumber the batchNumber to set
     */
    public void setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
    }
}
