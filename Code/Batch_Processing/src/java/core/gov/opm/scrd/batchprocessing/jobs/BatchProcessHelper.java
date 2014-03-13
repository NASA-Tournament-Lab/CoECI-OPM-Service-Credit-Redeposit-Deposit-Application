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

import java.math.BigDecimal;


/**
 * Batch process helper.
 *
 * @author liuliquan
 * @version 1.0
 */
public class BatchProcessHelper {

    /**
     * Represents CRLF characters.
     */
    public static final String CRLF = "\r\n";

    /**
     * Represents decimal 100.
     */
    public static final BigDecimal HUNDRED = new BigDecimal(100);

    /**
     * Private constructor to prevent class from being instantiated.
     */
    private BatchProcessHelper() {
    }

    /**
     * Convert null integer to zero.
     *
     * @param integer to convert.
     * @return zero if given integer is null; or given integer is returned if not null.
     */
    public static Integer nullToZero(Integer integer) {
        return integer == null ? 0 : integer;
    }

    /**
     * Convert null decimal to zero.
     *
     * @param decimal to convert.
     * @return zero if given decimal is null; or given decimal is returned if not null.
     */
    public static BigDecimal nullToZero(BigDecimal decimal) {
        return decimal == null ? BigDecimal.ZERO : decimal;
    }

    /**
     * Check integer is not null and positive.
     *
     * @param integer the integer to check
     * @param message the message
     *
     * @throws BatchProcessingConfigurationException if invalid
     */
    public static void checkInteger(Integer integer, String message) {
        if (integer == null || integer <= 0) {
            throw new BatchProcessingConfigurationException(message);
        }
    }

    /**
     * Check state.
     *
     * @param isInvalid the is invalid
     * @param message the message
     *
     * @throws BatchProcessingConfigurationException if invalid
     */
    public static void checkState(boolean isInvalid, String message) {
        if (isInvalid) {
            throw new BatchProcessingConfigurationException(message);
        }
    }
}
