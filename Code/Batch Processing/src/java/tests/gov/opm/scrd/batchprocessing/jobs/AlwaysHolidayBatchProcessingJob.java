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

import java.util.Date;


/**
 * A batch process job which consider every day to be holiday.
 *
 * @author liuliquan
 * @version 1.0
 */
public class AlwaysHolidayBatchProcessingJob extends BatchProcessingJob {

    /**
     * Empty constructor.
     */
    public AlwaysHolidayBatchProcessingJob() {
    }

    /**
     * Checks whether the current day is a holiday.
     *
     * @param now The current day.
     * @return true always.
     */
    @Override
    protected boolean isNowHoliday(Date now) {
        return true;
    }
}
