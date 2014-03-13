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

package gov.opm.scrd.entities.lookup;

/**
 * <p>
 * Represents the enumeration specifying the type of the chart to use for calculating the end date in chart calculation.
 * It is used in AccountService.calculateEndDate method.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This enumeration is immutable and thread safe.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 */
public enum CalculationEndDateCalculationType {
    /**
     * Represents HOUR_2000 type of the calculation chart.
     */
    HOUR_2000,

    /**
     * Represents HOUR_2008 type of the calculation chart.
     */
    HOUR_2008,

    /**
     * Represents HOUR_2016 type of the calculation chart.
     */
    HOUR_2016,

    /**
     * Represents HOUR_2024 type of the calculation chart.
     */
    HOUR_2024,

    /**
     * Represents HOUR_2080 type of the calculation chart.
     */
    HOUR_2080,

    /**
     * Represents HOUR_2087 type of the calculation chart.
     */
    HOUR_2087,

    /**
     * Represents DAY_260 type of the calculation chart.
     */
    DAY_260
}
