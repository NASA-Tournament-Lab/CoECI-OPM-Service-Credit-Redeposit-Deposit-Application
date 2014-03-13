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

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.Calculation;
import gov.opm.scrd.entities.application.Notification;
import gov.opm.scrd.entities.application.Payment;
import gov.opm.scrd.services.ApprovalService;
import gov.opm.scrd.services.CalculationExecutionService;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.PaymentService;
import gov.opm.scrd.services.ServiceAnnouncementService;
import gov.opm.scrd.services.SuspensionService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.logging.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Performs demonstration functionality for this assembly.
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
@Controller
public class TestController {
    /**
     * JNDI binding for the approval service.
     */
    private static final String APPROVAL_SERVICE_JNDI = "java:app/opm-scrd-ejb/ApprovalServiceImpl!"
        + "gov.opm.scrd.services.impl.ApprovalServiceImpl";

    /**
     * JNDI binding for the payment service.
     */
    private static final String PAYMENT_SERVICE_JNDI = "java:app/opm-scrd-ejb/PaymentServiceImpl!"
        + "gov.opm.scrd.services.impl.PaymentServiceImpl";
    /**
     * JNDI binding for the service announcement service.
     */
    private static final String ANNOUNCEMENT_SERVICE_JNDI = "java:app/opm-scrd-ejb/ServiceAnnouncementServiceImpl!"
        + "gov.opm.scrd.services.impl.ServiceAnnouncementServiceImpl";
    /**
     * JNDI binding for the suspension service.
     */
    private static final String SUSPENSION_SERVICE_JNDI = "java:app/opm-scrd-ejb/SuspensionServiceImpl!"
        + "gov.opm.scrd.services.impl.SuspensionServiceImpl";
    /**
     * JNDI binding for the calculation execution service.
     */
    private static final String CALCULATION_EXECUTION_SERVICE_JNDI =
        "java:app/opm-scrd-ejb/CalculationExecutionServiceImpl!"
            + "gov.opm.scrd.services.impl.CalculationExecutionServiceImpl";

    /**
     * Class logger.
     */
    private static final Logger LOG = Logger.getLogger(TestController.class);

    /**
     * The approval service.
     */
    @EJB(mappedName = APPROVAL_SERVICE_JNDI)
    private ApprovalService approvalService;

    /**
     * The payment service.
     */
    @EJB(mappedName = PAYMENT_SERVICE_JNDI)
    private PaymentService paymentService;

    /**
     * The service announcement service.
     */
    @EJB(mappedName = ANNOUNCEMENT_SERVICE_JNDI)
    private ServiceAnnouncementService serviceAnnouncementService;

    /**
     * The suspension service.
     */
    @EJB(mappedName = SUSPENSION_SERVICE_JNDI)
    private SuspensionService suspensionService;

    /**
     * The calculation execution service.
     */
    @EJB(mappedName = CALCULATION_EXECUTION_SERVICE_JNDI)
    private CalculationExecutionService calculationExecutionService;

    /**
     * Sets up custom formatters and binders.
     *
     * @param binder
     *            the web binder from the spring MVC framework
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * Shows the demonstration screen.
     *
     * @return the model and view.
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping("/test")
    public ModelAndView view() throws OPMException {
        String signature = AccountController.class.getName() + "#view()";
        LoggingHelper.logEntrance(LOG, signature, null, null);

        ModelAndView mv = new ModelAndView("test");

        mv.addObject("payment", new Payment());

        mv.addObject("notification", new Notification());

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }

    /**
     * Retrieves approval summary for the user.
     *
     * @param approver
     *            the approver
     *
     * @return the model and view.
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping(value = "/getApprovalSummary", method = RequestMethod.GET)
    public ModelAndView getApprovalSummary(@RequestParam String approver) throws OPMException {
        String signature = TestController.class.getName() + "#getApprovalSummary(String approver)";

        LoggingHelper.logEntrance(LOG, signature, new String[] {"approver"}, new Object[] {approver});

        ModelAndView mv = new ModelAndView("approvalSummaryResult");

        mv.addObject("approvalSummary", approvalService.getApprovalSummary(approver));

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }

    /**
     * Creates payment.
     *
     * @param accountId
     *            the account id
     * @param payment
     *            the payment
     *
     * @return the model and view.
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping(value = "/createPayment", method = RequestMethod.POST)
    public ModelAndView createPayment(@RequestParam long accountId, @ModelAttribute Payment payment)
        throws OPMException {
        String signature = TestController.class.getName() + "#createPayment(long accountId, Payment payment)";

        LoggingHelper.logEntrance(LOG, signature, new String[] {"accountId", "payment"}, new Object[] {accountId,
            payment});

        ModelAndView mv = new ModelAndView("paymentResult");

        paymentService.create(accountId, payment);
        mv.addObject("payment", payment);

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }

    /**
     * Creates notification.
     *
     * @param notification
     *            the notification
     *
     * @return the model and view.
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping(value = "/addNotification", method = RequestMethod.POST)
    public ModelAndView addNotification(@ModelAttribute Notification notification) throws OPMException {
        String signature = TestController.class.getName() + "#addNotification(Notification notification)";

        LoggingHelper.logEntrance(LOG, signature, new String[] {"notification"}, new Object[] {notification});

        ModelAndView mv = new ModelAndView("notificationResult");

        serviceAnnouncementService.addNotification(notification);
        mv.addObject("notification", notification);

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }

    /**
     * Gets suspension count.
     *
     * @param suspender
     *            the suspender
     *
     * @return the model and view.
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping(value = "/getSuspensionCount", method = RequestMethod.GET)
    public ModelAndView getSuspensionCount(@RequestParam String suspender) throws OPMException {
        String signature = TestController.class.getName() + "#getSuspensionCount(String suspender)";

        LoggingHelper.logEntrance(LOG, signature, new String[] {"suspender"}, new Object[] {suspender});

        ModelAndView mv = new ModelAndView("suspensionCountResult");

        mv.addObject("suspensionCount", suspensionService.getSuspensionCount(suspender));

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }

    /**
     * Runs calculation.
     *
     * @return the model and view.
     * @throws OPMException
     *             for any errors encountered, expected to be handled by exception resolvers future iterations.
     */
    @RequestMapping(value = "/runCalculation", method = RequestMethod.GET)
    public ModelAndView runCalculation() throws OPMException {
        String signature = TestController.class.getName() + "#runCalculation()";

        LoggingHelper.logEntrance(LOG, signature, null, null);

        ModelAndView mv = new ModelAndView("calculationResult");

        List<Calculation> calculations = new ArrayList<Calculation>();
        mv.addObject("calculationResult", calculationExecutionService.runCalculation(calculations));

        // exceptions are expected to be logged by the exception resolver
        LoggingHelper.logExit(LOG, signature, new Object[] {mv});
        return mv;
    }
}
