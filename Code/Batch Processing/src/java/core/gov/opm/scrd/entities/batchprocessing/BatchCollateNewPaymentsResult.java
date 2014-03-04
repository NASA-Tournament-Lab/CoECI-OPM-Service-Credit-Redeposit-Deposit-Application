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

/**
 * <p>This class is simply a POJO containing result of batch collate operation.</p>
 *  <p>Thread - safety. The class is mutable and not thread - safe, but is expected to be used in a thread - safe
 * manner.</p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
public class BatchCollateNewPaymentsResult {
    /**
     * Represents the accepted count. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Integer acceptedCount;

    /**
     * Represents the unresolved count. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Integer unresolvedCount;

    /**
     * Represents the suspended count. It is accessible by getter and modified by setter. It can be any value.
     * The default value is null.
     */
    private Integer suspendedCount;

    /**
     * Represents the accepted ach count. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer acceptedAchCount;

    /**
     * Represents the unresolved ach count. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer unresolvedAchCount;

    /**
     * Represents the suspended ach count. It is accessible by getter and modified by setter. It can be any
     * value. The default value is null.
     */
    private Integer suspendedAchCount;

    /**
     * Instantiates a new batch collate new payments result.
     */
    public BatchCollateNewPaymentsResult() {
    }

    /**
     * Gets the accepted count.
     *
     * @return the accepted count
     */
    public Integer getAcceptedCount() {
        return acceptedCount;
    }

    /**
     * Sets the accepted count.
     *
     * @param acceptedCount the new accepted count
     */
    public void setAcceptedCount(Integer acceptedCount) {
        this.acceptedCount = acceptedCount;
    }

    /**
     * Gets the unresolved count.
     *
     * @return the unresolved count
     */
    public Integer getUnresolvedCount() {
        return unresolvedCount;
    }

    /**
     * Sets the unresolved count.
     *
     * @param unresolvedCount the new unresolved count
     */
    public void setUnresolvedCount(Integer unresolvedCount) {
        this.unresolvedCount = unresolvedCount;
    }

    /**
     * Gets the suspended count.
     *
     * @return the suspended count
     */
    public Integer getSuspendedCount() {
        return suspendedCount;
    }

    /**
     * Sets the suspended count.
     *
     * @param suspendedCount the new suspended count
     */
    public void setSuspendedCount(Integer suspendedCount) {
        this.suspendedCount = suspendedCount;
    }

    /**
     * Gets the accepted ach count.
     *
     * @return the accepted ach count
     */
    public Integer getAcceptedAchCount() {
        return acceptedAchCount;
    }

    /**
     * Sets the accepted ach count.
     *
     * @param acceptedAchCount the new accepted ach count
     */
    public void setAcceptedAchCount(Integer acceptedAchCount) {
        this.acceptedAchCount = acceptedAchCount;
    }

    /**
     * Gets the unresolved ach count.
     *
     * @return the unresolved ach count
     */
    public Integer getUnresolvedAchCount() {
        return unresolvedAchCount;
    }

    /**
     * Sets the unresolved ach count.
     *
     * @param unresolvedAchCount the new unresolved ach count
     */
    public void setUnresolvedAchCount(Integer unresolvedAchCount) {
        this.unresolvedAchCount = unresolvedAchCount;
    }

    /**
     * Gets the suspended ach count.
     *
     * @return the suspended ach count
     */
    public Integer getSuspendedAchCount() {
        return suspendedAchCount;
    }

    /**
     * Sets the suspended ach count.
     *
     * @param suspendedAchCount the new suspended ach count
     */
    public void setSuspendedAchCount(Integer suspendedAchCount) {
        this.suspendedAchCount = suspendedAchCount;
    }
}
