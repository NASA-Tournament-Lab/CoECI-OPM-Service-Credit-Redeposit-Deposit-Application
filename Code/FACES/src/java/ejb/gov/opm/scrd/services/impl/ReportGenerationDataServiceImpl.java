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

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.ReportGenerationData;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.ReportGenerationDataService;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * This class is the implementation of the ReportGenerationDataService. It utilizes JPA EntityManager for necessary
 * operations.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread safe after configuration, the configuration is done
 * in a thread safe manner.
 * </p>
 *
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 * @since OPM - Frontend - Miscellaneous Module Assembly
 */
@Stateless
@Local(ReportGenerationDataService.class)
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReportGenerationDataServiceImpl extends BaseService implements ReportGenerationDataService {

    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = ReportGenerationDataServiceImpl.class.getName();

    /**
     * <p>
     * Represents the id of single ReportGenerationData instance used across the application. It is modified by setter.
     * It is injected by Spring. It can not be null after injected.
     * </p>
     */
    @Autowired
    private Long defaultDataId;

    /**
     * Creates an instance of ReportGenerationDataServiceImpl.
     */
    public ReportGenerationDataServiceImpl() {
        // Empty
    }

    /**
     * Retrieves report generation data used in the application.
     *
     * @return the report generation data used in the application or null if they it is not set yet.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ReportGenerationData getReportGenerationData() throws OPMException {
        String signature = CLASS_NAME + "#getReportGenerationData()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature, null, null);

        try {
            ReportGenerationData result = getEntityManager().find(ReportGenerationData.class, defaultDataId);

            LoggingHelper.logExit(logger, signature, new Object[] {result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature,
                new OPMException("The entity manager has been closed.", e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                "An error has occurred when accessing persistence.", e));
        }
    }

    /**
     * This method checks whether the instance of the class was initialized properly.
     *
     * @throws OPMConfigurationException
     *             if the instance was not initialized properly (entityManager or defaultDataId is null).
     */
    @Override
    @PostConstruct
    protected void checkInit() {
        super.checkInit();
        Helper.checkState(defaultDataId == null, "'defaultDataId' can't be null.");
    }
}
