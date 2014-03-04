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

package gov.opm.scrd.web.controllers;

import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.AuditService;
import gov.opm.scrd.services.OPMConfigurationException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 * <p>
 * This class is the base class of all controllers that require data audit. It holds a reference to AuditService
 * instance.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the injected services are
 * not modified in controllers by methods after configuration and configuration will be done in a thread safe manner by
 * Spring IoC framework.
 * </p>
 *
 * @author faeton, sparemax
 * @version 1.0
 * @since OPM - SCRD - Frontend Initial Module Assembly 1.0
 */
public abstract class BaseAuditController extends BaseController {
    /**
     * Represents the AuditService instance to manage audit. It is modified by setter. It is injected by Spring. It can
     * not be null after injected.
     */
    @EJB(mappedName = WebHelper.AUDIT_SERVICE_JNDI)
    private AuditService auditService;

    /**
     * Creates an instance of BaseAuditController.
     */
    protected BaseAuditController() {
        // Empty
    }

    /**
     * Gets the AuditService instance to manage audit.
     *
     * @return the AuditService instance to manage audit.
     */
    protected AuditService getAuditService() {
        return auditService;
    }

    /**
     * Sets the AuditService instance to manage audit.
     *
     * @param auditService
     *            the AuditService instance to manage audit.
     */
    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly(widgetIds is null or contains null/empty element;
     *             securityService, userService or auditService is null; userSessionKey is null/empty).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();

        Helper.checkState(auditService == null, "'auditService' can't be null.");
    }
}
