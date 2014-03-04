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

import gov.opm.scrd.entities.application.Calculation;
import gov.opm.scrd.entities.application.CalculationResult;

import java.util.List;

/**
 * <p>
 * This interface defines a contract for running calculations.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> Implementations should be thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public interface CalculationExecutionService {
    /**
     * Runs the calculation on the account data.
     *
     * @param calculations
     *            the calculation data for performing calculation.
     *
     * @return The CalculationResult entity holding the data about result, can not be null.
     *
     * @throws IllegalArgumentException
     *             if calculations is null, contain null elements.
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    public CalculationResult runCalculation(List<Calculation> calculations) throws OPMException;
}
