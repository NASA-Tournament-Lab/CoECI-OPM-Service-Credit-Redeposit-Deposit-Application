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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <p>
 * This class provide helper methods in this package: gov.opm.scrd.services.impl
 * </p>
 * <p>
 * <b>Thread Safety:</b> This class is immutable and thread safe.
 * </p>
 * 
 * @author TCSASSEMBLER
 * @version 1.0
 * 
 * @since OPM - Implement Business Rules Engine Deduction Calculation Assembly
 *        v1.0
 * 
 */
final class ServiceHelper {
    /**
     * <p>
     * Creates the instance of ServiceHelper.
     * </p>
     * <p>
     * This constructor is set to private to avoid creating instances.
     * </p>
     */
    private ServiceHelper() {
        // does noting
    }
    /**
     * <p>
     * Checks if the parameter is null, if yes, IllegalArgumentException will be
     * thrown.
     * </p>
     * 
     * @param param
     *            the parameter to check if it is null.
     * @param name
     *            the name of the parameter.
     * 
     * @throws IllegalArgumentException
     *             if the parameter is null.
     */
    static void checkNull(Object param, String name) {
        if (param == null) {
            throw new IllegalArgumentException("The parameter: " + name + " cannot be null.");
        }
    }
    
    /**
     * Reads string from InputStream.
     * @param inputStream the input stream
     * @return the string
     * @throws IOException if IO error occurred
     */
    static String inputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}
