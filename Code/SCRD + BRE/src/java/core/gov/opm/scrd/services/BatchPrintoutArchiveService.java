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

import java.util.List;
import gov.opm.scrd.entities.application.Printout;

/**
 * <p>
 * This interface defines a contract for retrieving printout data.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public interface BatchPrintoutArchiveService {
    /**
     * Returns the list of available printouts in the application.
     *
     * @return List of available printouts, can not be null
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<Printout> getAvailablePrintouts() throws OPMException;

    /**
     * Return the content of the printout by name.
     *
     * @param name
     *            the printout name.
     *
     * @return Printout contents as byte array, can be null.
     *
     * @throws IllegalArgumentException
     *             if name is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public byte[] getPrintout(String name) throws OPMException;
}
