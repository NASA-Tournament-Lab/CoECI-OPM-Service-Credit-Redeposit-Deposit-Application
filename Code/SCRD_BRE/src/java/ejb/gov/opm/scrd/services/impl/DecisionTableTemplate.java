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

package gov.opm.scrd.services.impl;

/**
 * This class represents a decision table template.
 * @author albertwang, TCSASSEMBLER
 * @version 1.0
 * @since OPM - Implement Business Rules Engine Deduction Calculation Assembly
 *        v1.0
 *
 */
public class DecisionTableTemplate {
    /**
     * Represents the template file.
     */
    private String templateFile;
    /**
     * Represents the decision table file to generate.
     */
    private String decisionTableFile;
    /**
     * Represents the start cell row. 
     */
    private int startCellRow;
    /**
     * Represents the start cell column. 
     */
    private int startCellColumn;
    
    /**
     * Constructor.
     */
    public DecisionTableTemplate() {
    }
    
    /**
     * Getter of corresponding field.
     * @return corresponding field
     */
    public String getTemplateFile() {
        return templateFile;
    }

    /**
     * Setter of corresponding field.
     * @param templateFile the value to set
     */
    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    /**
     * Getter of corresponding field.
     * @return corresponding field
     */
    public int getStartCellRow() {
        return startCellRow;
    }

    /**
     * Setter of corresponding field.
     * @param startCellRow the value to set
     */
    public void setStartCellRow(int startCellRow) {
        this.startCellRow = startCellRow;
    }

    /**
     * Getter of corresponding field.
     * @return corresponding field
     */
    public int getStartCellColumn() {
        return startCellColumn;
    }

    /**
     * Setter of corresponding field.
     * @param startCellColumn the value to set
     */
    public void setStartCellColumn(int startCellColumn) {
        this.startCellColumn = startCellColumn;
    }


    /**
     * Getter of corresponding field.
     * @return corresponding field
     */
    public String getDecisionTableFile() {
        return decisionTableFile;
    }
    /**
     * Setter of corresponding field.
     * @param decisionTableFile the value to set
     */
    public void setDecisionTableFile(String decisionTableFile) {
        this.decisionTableFile = decisionTableFile;
    }
}
