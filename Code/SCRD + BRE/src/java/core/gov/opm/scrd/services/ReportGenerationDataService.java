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

package gov.opm.scrd.services;

import gov.opm.scrd.entities.application.ReportGenerationData;


/**
 * <p>
 * This interface defines a contract for managing report generation data.
 * It provides single method for retrieving the data.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 * @since OPM - Frontend - Miscellaneous Module Assembly
 */
public interface ReportGenerationDataService {

    /**
     * Returns the report generation data used in the application.
     *
     * @return the report generation data used in the application or null if they it is not set yet.
     *
     * @throws OPMException if there is any problem when executing the method.
     */
    public ReportGenerationData getReportGenerationData() throws OPMException;
}
