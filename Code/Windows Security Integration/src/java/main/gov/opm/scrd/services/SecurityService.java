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

import javax.ejb.Local;

/**
 * The Security Service is used to check authorization. <strong>Thread Safety:
 * </strong>Implementations need to be effectively thread safe.
 * 
 * @author argolite,TCSASSEMBLER
 * @version 1.0
 */
@Local
public interface SecurityService {

    /**
     * Check authorization by user name,roles and action
     * 
     * @param username
     *            the user name
     * @param roles
     *            the roles
     * @param action
     *            the action name
     * @return if authorized return true
     */
    public boolean authorize(String username, List<String> roles, String action);
}
