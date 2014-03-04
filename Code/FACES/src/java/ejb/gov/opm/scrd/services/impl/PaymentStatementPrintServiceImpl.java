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
import gov.opm.scrd.entities.application.PaymentStatementPrint;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.PaymentStatementPrintService;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.jboss.logging.Logger;

/**
 * <p>
 * This class is the implementation of the PaymentStatementPrintService. It utilizes JPA EntityManager for necessary
 * operations.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class is effectively thread - safe after configuration, the configuration is
 * done in a thread - safe manner.
 * </p>
 *
 * @author faeton, bannie2492
 * @version 1.0
 */
@Stateless
@Local(PaymentStatementPrintService.class)
@LocalBean
public class PaymentStatementPrintServiceImpl extends BaseService implements PaymentStatementPrintService {
    /**
     * <p>
     * Represents the class name.
     * </p>
     */
    private static final String CLASS_NAME = AccountServiceImpl.class.getName();
    /**
     * <p>
     * Represents the JPQL to query Account by claim number.
     * </p>
     */
    private static final String JPQL_QUERY_PAYMENT_STATEMENT_PRINT = "SELECT p FROM PaymentStatementPrint "
            + "p ORDER BY p.date DESC";

    /**
     * Create an instance of PaymentStatementPrintServiceImpl.
     */
    public PaymentStatementPrintServiceImpl() {
    }

    /**
     * Requests the payment statement print from the lastly run nightly batch processing job.
     * @return PaymentStatementPrint instance containing print information data
     * @throws OPMException if there is any problem when executing the method
     */
    public PaymentStatementPrint requestPaymentStatementPrint() throws OPMException {
        String signature = CLASS_NAME + "#requestPaymentStatementPrint()";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
                new String[]{},
                new Object[]{});
        try {
            TypedQuery<PaymentStatementPrint> query = getEntityManager().createQuery(JPQL_QUERY_PAYMENT_STATEMENT_PRINT,
                    PaymentStatementPrint.class);
            query.setMaxResults(1);
            List<PaymentStatementPrint> list = query.getResultList();
            PaymentStatementPrint result = null;
            if (!list.isEmpty()) {
                result = list.get(0);
            }
            LoggingHelper.logExit(logger, signature, new Object[]{result});
            return result;
        } catch (IllegalStateException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException("The entity manager has been closed.",
                    e));
        } catch (PersistenceException e) {
            throw LoggingHelper.logException(logger, signature, new OPMException(
                    "An error has occurred when accessing persistence.", e));
        }

    }
}
