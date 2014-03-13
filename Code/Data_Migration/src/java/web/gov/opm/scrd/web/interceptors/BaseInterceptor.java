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

package gov.opm.scrd.web.interceptors;

import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.web.controllers.WebHelper;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <p>
 * This class is the base interceptor for application interceptors. It provides logger and configured user session key.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration.
 * </p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
public abstract class BaseInterceptor extends HandlerInterceptorAdapter {
    /**
     * This is the logger used for logging. It is initialized in constructor and never changed afterwards. It can not be
     * null.
     */
    private final Logger logger;

    /**
     * Represents the key of the currently logged user in the session. The value for the key in the session is the user
     * name. It is modified by setter. It is injected by Spring. It can not be null/empty after injected.
     */
    @Autowired
    private String userSessionKey;

    /**
     * Creates an instance of BaseInterceptor.
     */
    protected BaseInterceptor() {
        logger = Logger.getLogger(this.getClass());
    }

    /**
     * Gets the logger used for logging.
     *
     * @return the logger used for logging.
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Gets the key of the currently logged user in the session.
     *
     * @return the key of the currently logged user in the session.
     */
    public String getUserSessionKey() {
        return userSessionKey;
    }

    /**
     * Sets the key of the currently logged user in the session.
     *
     * @param userSessionKey
     *            the key of the currently logged user in the session.
     */
    public void setUserSessionKey(String userSessionKey) {
        this.userSessionKey = userSessionKey;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (userSessionKey is null/empty).
     */
    @PostConstruct
    protected void checkInit() {
        Helper.checkState(WebHelper.isNullOrEmpty(userSessionKey), "'userSessionKey' can't be null/empty.");
    }
}
