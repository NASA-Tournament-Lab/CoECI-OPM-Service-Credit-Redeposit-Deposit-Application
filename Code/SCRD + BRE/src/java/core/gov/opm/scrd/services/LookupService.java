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

import gov.opm.scrd.entities.common.NamedEntity;

import java.util.List;

/**
 * <p>
 * This interface defines a contract for retrieving lookup entities.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public interface LookupService {
    /**
     * Returns the list of entities for the given lookup type.
     *
     * @param lookupType
     *            the lookup type to retrieve.
     *
     * @return The list of lookup entities for the given type, can not be null/contain null elements.
     *
     * @throws IllegalArgumentException
     *             if lookupType is null/empty.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public List<NamedEntity> getLookups(String lookupType) throws OPMException;
}
