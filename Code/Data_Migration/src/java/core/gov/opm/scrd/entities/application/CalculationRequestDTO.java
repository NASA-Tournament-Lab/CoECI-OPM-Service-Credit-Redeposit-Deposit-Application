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

package gov.opm.scrd.entities.application;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * This class is a wrapper of a calculation request sent from the front end pages to the Controller.
 * It wraps the list of calculations to be performed and the interestCalculatedToDate.
 * I provides setters and getters for each of its fields.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is mutable and not thread safe.
 * </p>
 * 
 * @author TCSASSEMBLER
 * @version 1.0 (OPM - Rules Engine - Integrate with Web App Assembly v1.0)
 */
public class CalculationRequestDTO {

    public CalculationRequestDTO() {
        // empty.
    }
    
    /**
     * <p>
     * The list of calculation to be performed. It is managed by getter and setter.
     * It can be any value. It's fully mutable.
     * </p>
     */
    private List<Calculation> calculations;
    
    /**
     * <p>
     * The date to which the interest would be calculated. It is managed by getter and setter.
     * It can be any value. It's fully mutable.
     * </p>
     */
    private Date interestCalculatedToDate;
    
    
    /**
     * Gets the list of the calculations to be performed.
     * 
     * @return the calculations
     */
    public List<Calculation> getCalculations() {
        return calculations;
    }

    /**
     * Sets the list of the calculations to be performed.
     * 
     * @param calculations the calculations to set
     */
    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    /**
     * Gets the date to which the interest would be calculated.
     * 
     * @return the interestCalculatedToDate
     */
    public Date getInterestCalculatedToDate() {
        return interestCalculatedToDate;
    }

    /**
     * Sets the date to which the interest would be calculated.
     * 
     * @param interestCalculatedToDate the interestCalculatedToDate to set
     */
    public void setInterestCalculatedToDate(Date interestCalculatedToDate) {
        this.interestCalculatedToDate = interestCalculatedToDate;
    }
}
