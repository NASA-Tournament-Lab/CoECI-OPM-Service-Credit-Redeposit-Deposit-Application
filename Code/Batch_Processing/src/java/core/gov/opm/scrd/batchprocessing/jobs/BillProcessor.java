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

/**
 * <p>This interface defines the contract for processing bills.</p>
 *
 * <p>Thread - safety. The implementation should be effectively thread - safe.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public interface BillProcessor {
    /**
     * Processes the bills data.
     *
     * @param auditBatchID the id of audit batch log.
     * @param procMessage logging message used to collect information about process.
     * @param invoiceCount general information counter for invoices.
     * @param achStopCount general information counter for ACH stop markers.
     * @param refundMemoCount general information counter for refund memos.
     * @param reversalCount general information counter for reversal payments.
     * @param initialBillCount general information counter for initial biils.
     *
     * @throws BillProcessingException if there is any problem executing the method.
     */
    public void processBills(Long auditBatchID, StringBuilder procMessage, Integer invoiceCount,
        Integer achStopCount, Integer refundMemoCount, Integer reversalCount, Integer initialBillCount)
        throws BillProcessingException;
}
