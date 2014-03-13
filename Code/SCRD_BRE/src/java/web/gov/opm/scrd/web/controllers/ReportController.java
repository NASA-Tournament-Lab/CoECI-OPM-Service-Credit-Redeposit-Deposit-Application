/*
 * Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
 */
package gov.opm.scrd.web.controllers;

import gov.opm.scrd.LoggingHelper;
import gov.opm.scrd.entities.application.ReportRequest;
import gov.opm.scrd.entities.application.ReportResponse;
import gov.opm.scrd.entities.common.Helper;
import gov.opm.scrd.services.ExportType;
import gov.opm.scrd.services.OPMConfigurationException;
import gov.opm.scrd.services.OPMException;
import gov.opm.scrd.services.ReportService;
import gov.opm.scrd.services.impl.BaseReportService;
import gov.opm.scrd.services.impl.reporting.AccountBalanceReportRequest;
import gov.opm.scrd.services.impl.reporting.AccountBalanceReportService;
import gov.opm.scrd.services.impl.reporting.AccountStatisticsReportRequest;
import gov.opm.scrd.services.impl.reporting.AccountStatisticsReportService;
import gov.opm.scrd.services.impl.reporting.AccountSummaryReportRequest;
import gov.opm.scrd.services.impl.reporting.AccountSummaryReportService;
import gov.opm.scrd.services.impl.reporting.AccountTypeTotalsReportRequest;
import gov.opm.scrd.services.impl.reporting.AccountTypeTotalsReportService;
import gov.opm.scrd.services.impl.reporting.ActiveCreditBalancesReportRequest;
import gov.opm.scrd.services.impl.reporting.ActiveCreditBalancesReportService;
import gov.opm.scrd.services.impl.reporting.BalancedLockboxReportRequest;
import gov.opm.scrd.services.impl.reporting.BalancedLockboxReportService;
import gov.opm.scrd.services.impl.reporting.BalancedScorecardAccountReportRequest;
import gov.opm.scrd.services.impl.reporting.BalancedScorecardAccountReportService;
import gov.opm.scrd.services.impl.reporting.BalancedScorecardPaymentReportRequest;
import gov.opm.scrd.services.impl.reporting.BalancedScorecardPaymentReportService;
import gov.opm.scrd.services.impl.reporting.CalculationAuditTrailReportRequest;
import gov.opm.scrd.services.impl.reporting.CalculationAuditTrailReportService;
import gov.opm.scrd.services.impl.reporting.ChangeHistoryReportRequest;
import gov.opm.scrd.services.impl.reporting.ChangeHistoryReportService;
import gov.opm.scrd.services.impl.reporting.ClosedAccountReportRequest;
import gov.opm.scrd.services.impl.reporting.ClosedAccountReportService;
import gov.opm.scrd.services.impl.reporting.CurrentSuspenseReportRequest;
import gov.opm.scrd.services.impl.reporting.CurrentSuspenseReportService;
import gov.opm.scrd.services.impl.reporting.DailyCashflowReportRequest;
import gov.opm.scrd.services.impl.reporting.DailyCashflowReportService;
import gov.opm.scrd.services.impl.reporting.DailyReconciliationReportRequest;
import gov.opm.scrd.services.impl.reporting.DailyReconciliationReportService;
import gov.opm.scrd.services.impl.reporting.DailySuspenseReportRequest;
import gov.opm.scrd.services.impl.reporting.DailySuspenseReportService;
import gov.opm.scrd.services.impl.reporting.DeductionRatesReportRequest;
import gov.opm.scrd.services.impl.reporting.DeductionRatesReportService;
import gov.opm.scrd.services.impl.reporting.GeneralLedgerReportRequest;
import gov.opm.scrd.services.impl.reporting.GeneralLedgerReportService;
import gov.opm.scrd.services.impl.reporting.LockboxFileImportReportRequest;
import gov.opm.scrd.services.impl.reporting.LockboxFileImportReportService;
import gov.opm.scrd.services.impl.reporting.LockboxImportErrorsReportRequest;
import gov.opm.scrd.services.impl.reporting.LockboxImportErrorsReportService;
import gov.opm.scrd.services.impl.reporting.ManualPaymentReportRequest;
import gov.opm.scrd.services.impl.reporting.ManualPaymentReportService;
import gov.opm.scrd.services.impl.reporting.MonthlyAdjustmentReportRequest;
import gov.opm.scrd.services.impl.reporting.MonthlyAdjustmentReportService;
import gov.opm.scrd.services.impl.reporting.MonthlySuspenseListReportRequest;
import gov.opm.scrd.services.impl.reporting.MonthlySuspenseListReportService;
import gov.opm.scrd.services.impl.reporting.PaymentByTypeRangeReportRequest;
import gov.opm.scrd.services.impl.reporting.PaymentByTypeRangeReportService;
import gov.opm.scrd.services.impl.reporting.PaymentHistoryReportRequest;
import gov.opm.scrd.services.impl.reporting.PaymentHistoryReportService;
import gov.opm.scrd.services.impl.reporting.PaymentPendingApprovalReportRequest;
import gov.opm.scrd.services.impl.reporting.PaymentPendingApprovalReportService;
import gov.opm.scrd.services.impl.reporting.RefundSectionAccountAssignmentsReportRequest;
import gov.opm.scrd.services.impl.reporting.RefundSectionAccountAssignmentsReportService;
import gov.opm.scrd.services.impl.reporting.ResolvedSuspenseHistoryReportRequest;
import gov.opm.scrd.services.impl.reporting.ResolvedSuspenseHistoryReportService;
import gov.opm.scrd.services.impl.reporting.SuspenseResolutionReportRequest;
import gov.opm.scrd.services.impl.reporting.SuspenseResolutionReportService;
import gov.opm.scrd.services.impl.reporting.TotalPaymentSummaryReportRequest;
import gov.opm.scrd.services.impl.reporting.TotalPaymentSummaryReportService;
import gov.opm.scrd.services.impl.reporting.TransactionRegisterNewReceiptsReportRequest;
import gov.opm.scrd.services.impl.reporting.TransactionRegisterNewReceiptsReportService;
import gov.opm.scrd.services.impl.reporting.UserAuditTrailReportRequest;
import gov.opm.scrd.services.impl.reporting.UserAuditTrailReportService;
import gov.opm.scrd.services.impl.reporting.UserPermissionsRolesReportRequest;
import gov.opm.scrd.services.impl.reporting.UserPermissionsRolesReportService;
import gov.opm.scrd.services.impl.reporting.UserRolePermissionsReportRequest;
import gov.opm.scrd.services.impl.reporting.UserRolePermissionsReportService;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * This class represents the controller used to perform report functionality. 
 * The reports can be generated from the application data and exported.
 * 
 * It's mutable but the spring mvc framework will guarantee that it's used in the thread safe model.
 * </p>
 * 
 * @author faeton, TCSASSEMBLER
 * @version 1.0
 */
@Controller
public class ReportController extends BaseController {
    
    /**
     * Represents the report controller class name for logging.
     */
    private static final String CLASS_NAME = ReportController.class.getName();

    /**
     * <p>
     * Represents the map specifying the ReportService instances used to
     * generate and export reports. The key is the report name, the value is the
     * instance of ReportService to perform reporting functionality for the
     * given name.
     * </p>
     * <p>
     * It is modified by setter.
     * </p>
     * <p>
     * It is injected by Spring.
     * </p>
     * <p>
     * It can not be null after injected.
     * </p>
     */
    @Resource(name = "reportServices")
    private Map<String, ReportService<ReportRequest, ReportResponse>> reportServices;

    /**
     * <p>
     * Represents the map specifying the download filenames for reports. The key
     * is the report name, the value is the download file name for this report
     * name.
     * </p>
     * <p>
     * It is modified by setter.
     * </p>
     * <p>
     * It is injected by Spring.
     * </p>
     * <p>
     * It can not be null after injected.
     * </p>
     */
    @Resource(name = "reportDownloadFilenames")
    private Map<String, String> reportDownloadFilenames;

    /**
     * <p>
     * Represents the map specifying the content types for exported reports. 
     * The key is the name of the export type (PDF, DOC, RTF), the value is the respective content type.
     * </p>
     * <p>
     * It is modified by setter. It is injected by Spring. It can not be null after injected.
     * </p>
     */
    @Resource(name = "reportContentTypes")
    private Map<String, String> reportContentTypes;

    /**
     * This is the constructor of this class.
     */
    public ReportController() {
        super();
    }

    /**
     * This method is responsible for viewing the page of the controller.
     *
     * @param session
     *            current http session.
     *
     * @return The populated ModelAndView instance.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "report/view", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView view(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        ModelAndView modelAndView = populateModelAndView(session, new ModelAndView("report"));

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }

    /**
     * This method is responsible for viewing the page of the controller.
     *
     * @param session
     *            current http session.
     *
     * @return The populated ModelAndView instance.
     *
     * @throws OPMException
     *             if there is any problem when executing the method.
     */
    @RequestMapping(value = "report/preview", method = RequestMethod.GET)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ModelAndView preView(HttpSession session) throws OPMException {
        String signature = CLASS_NAME + "#view(HttpSession session)";
        Logger logger = getLogger();

        LoggingHelper.logEntrance(logger, signature,
            new String[] {"session"},
            new Object[] {session});

        ModelAndView modelAndView = populateModelAndView(session, new ModelAndView("reportPreview"));

        LoggingHelper.logExit(logger, signature, new Object[] {modelAndView});
        return modelAndView;
    }
    
    /**
     * <p>
     * Generates the report for the given request. 
     * The concrete report to generate is identified by reportName parameter.
     * </p>
     * 
     * @param reportName the name of report to export.
     * @param requestJSON the request data to generate report.
     * @param httpServletResponse the http servlet response
     * 
     * @return the response in json format.
     * @throws OPMException if there are any error occurs.
     * @throws ValidationException if report name is null or empty or the request is null
     */
    @RequestMapping(value = "report/get", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ReportResponse getReport(@RequestParam String reportName, @RequestParam String requestJSON, 
            HttpServletResponse httpServletResponse) 
            throws OPMException, ValidationException {
        final String signature = CLASS_NAME + "#getReport(String reportName, ReportRequest request, " +
                "HttpServletResponse httpServletResponse)";
        
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[] { "reportName", "requestJSON", "httpServletResponse"}, 
                new Object[] {reportName, requestJSON, httpServletResponse});
        
        // validate the parameters
        WebHelper.checkRequest(logger, signature,
                new boolean[]{reportName == null || reportName.trim().length() == 0, 
                requestJSON == null || requestJSON.trim().length() == 0},
                new String[]{"'reportName can't be null or empty.",
                "requestJSON cannot not be null or empty"}, 
                "Failed to export for invalid parameters.");
        
        // get the reportService
        ReportService<ReportRequest, ReportResponse> reportSerivce = reportServices.get(reportName);
        if (reportSerivce == null) {
            throw LoggingHelper.logException(logger, signature, 
                    new OPMException("Failed to get the report for:" + reportName));
        }
        
        
        // generate the report
        ReportResponse response;
        try {
            ReportRequest request = populateRequest(reportSerivce, requestJSON);
            response = reportSerivce.getReport(request);
        } catch (OPMException e) {
            // log and re-throw the exception
            throw LoggingHelper.logException(logger, signature, e);
        }
        
        // log the exit
        LoggingHelper.logExit(logger, signature, new Object[] {response});
        
        return response;
    }
    
    /**
     * <p>
     * Constructs the report request with the given json.
     * </p>
     * @param reportSerivice the service type
     * @param requestJSON the json string.
     * @return the report request
     * @throws OPMException if there are any error. 
     */
    private ReportRequest populateRequest(
        ReportService<ReportRequest, ReportResponse> reportService, String requestJSON) 
        throws OPMException {
        Class<? extends ReportRequest> theClass = null;
        BaseReportService baseReportService = (BaseReportService) reportService;
        if (baseReportService instanceof AccountBalanceReportService) {
            theClass = AccountBalanceReportRequest.class;
        } else if (baseReportService instanceof UserAuditTrailReportService) {
            theClass = UserAuditTrailReportRequest.class;
        } else if (baseReportService instanceof AccountTypeTotalsReportService) {
            theClass = AccountTypeTotalsReportRequest.class;
        } else if (baseReportService instanceof ActiveCreditBalancesReportService) {
            theClass = ActiveCreditBalancesReportRequest.class;
        } else if (baseReportService instanceof BalancedScorecardAccountReportService) {
            theClass = BalancedScorecardAccountReportRequest.class;
        } else if (baseReportService instanceof BalancedLockboxReportService) {
            theClass = BalancedLockboxReportRequest.class;
        } else if (baseReportService instanceof BalancedScorecardPaymentReportService) {
            theClass = BalancedScorecardPaymentReportRequest.class;
        } else if (baseReportService instanceof CalculationAuditTrailReportService) {
            theClass = CalculationAuditTrailReportRequest.class;
        } else if (baseReportService instanceof CurrentSuspenseReportService) {
            theClass = CurrentSuspenseReportRequest.class;
        } else if (baseReportService instanceof DailyCashflowReportService) {
            theClass = DailyCashflowReportRequest.class;
        } else if (baseReportService instanceof DailyReconciliationReportService) {
            theClass = DailyReconciliationReportRequest.class;
        } else if (baseReportService instanceof DailySuspenseReportService) {
            theClass = DailySuspenseReportRequest.class;
        } else if (baseReportService instanceof DeductionRatesReportService) {
            theClass = DeductionRatesReportRequest.class;
        } else if (baseReportService instanceof LockboxImportErrorsReportService) {
            // Lock Box Import Errors
            theClass = LockboxImportErrorsReportRequest.class;
        } else if (baseReportService instanceof LockboxFileImportReportService) {
            // Lockbox Bank File Import Totals
            theClass = LockboxFileImportReportRequest.class;
        } else if (baseReportService instanceof ManualPaymentReportService) {
            // Manual Payments
            theClass = ManualPaymentReportRequest.class;
        } else if (baseReportService instanceof MonthlySuspenseListReportService) {
            // Suspense Report
            theClass = MonthlySuspenseListReportRequest.class;
        } else if (baseReportService instanceof PaymentHistoryReportService) {
            // Payment Transaction History
            theClass = PaymentHistoryReportRequest.class;
        } else if (baseReportService instanceof PaymentByTypeRangeReportService) {
            // Payments
            theClass = PaymentByTypeRangeReportRequest.class;
        } else if (baseReportService instanceof PaymentPendingApprovalReportService) {
            // Payments Pending Supervisor Approval
            theClass = PaymentPendingApprovalReportRequest.class;
        } else if (baseReportService instanceof RefundSectionAccountAssignmentsReportService) {
            // Refund Section Account Assignments
            theClass = RefundSectionAccountAssignmentsReportRequest.class;
        } else if (baseReportService instanceof ResolvedSuspenseHistoryReportService) {
            // Resolved Suspense History
            theClass = ResolvedSuspenseHistoryReportRequest.class;
        } else if (baseReportService instanceof ClosedAccountReportService) {
            //CLOSED ACCOUNT STATEMENT
            theClass = ClosedAccountReportRequest.class;
        } else if (baseReportService instanceof TotalPaymentSummaryReportService) {
            // SC-007-001B - Summary of Total Payments
            theClass = TotalPaymentSummaryReportRequest.class;
        } else if (baseReportService instanceof MonthlyAdjustmentReportService) {
            // Adjustments Report
            theClass = MonthlyAdjustmentReportRequest.class;
        }  else if (baseReportService instanceof ChangeHistoryReportService) {
            // Service Credit Change History Report
            theClass = ChangeHistoryReportRequest.class;
        } else if (baseReportService instanceof GeneralLedgerReportService) {
            // SYBAC Payments to General Ledger Report (detailed)
            theClass = GeneralLedgerReportRequest.class;
        } else if (baseReportService instanceof GeneralLedgerReportService) {
            // SYBAC Payments to General Ledger Report (non-detailed), to confirm
            theClass = GeneralLedgerReportRequest.class;
        } else if (baseReportService instanceof TransactionRegisterNewReceiptsReportService) {
            // SYBAC Transaction Register
            theClass = TransactionRegisterNewReceiptsReportRequest.class;
        } else if (baseReportService instanceof SuspenseResolutionReportService) {
            // Suspense Resolution Report
            theClass = SuspenseResolutionReportRequest.class;
        } else if (baseReportService instanceof AccountStatisticsReportService) {
            // Account Statistics
            theClass = AccountStatisticsReportRequest.class;
        } else if (baseReportService instanceof AccountSummaryReportService) {
            // Accounting Summary Report
            theClass = AccountSummaryReportRequest.class;
        } else if (baseReportService instanceof UserRolePermissionsReportService) {
            // User Permission Roles in Service Credit 
            // User and Role List
            theClass = UserRolePermissionsReportRequest.class;
        } else if (baseReportService instanceof UserPermissionsRolesReportService) {
            // Service Credit Role Permissions 
            // User Role Permissions
            theClass = UserPermissionsRolesReportRequest.class;
        } 
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            ReportRequest request = mapper.readValue(requestJSON, theClass);
            return request;
        } catch (JsonParseException e) {
            throw new OPMException("failed to parse the given json.", e);
        } catch (JsonMappingException e) {
            throw new OPMException("failed to parse the given json.", e);
        } catch (IOException e) {
            throw new OPMException("failed to parse the given json.", e);
        }
    }

    
    
    /**
     * <p>
     * Exports the report for the given response. 
     * The concrete report to generate is identified by reportName parameter. 
     * The contents are written to the output stream of response.
     * </p> 
     * @param reportName the name of report to export.
     * @param response the response data to export report.
     * @param exportType the type of the report data to generate.
     * @param httpServletResponse the HttpSesrvletResponse 
     * instance to write the contests of the exported report.
     * 
     * @throws IOException if failed to write the report to the http response body.
     * @throws ValidationException if reportName is null or empty 
     * or the response is null or the exportType is null.
     * @throws OPMException if any error occurs when generating the error.
     */
    @RequestMapping(value="report/export", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void exportReport(@RequestParam String reportName, @RequestBody ReportResponse response, 
        @RequestParam ExportType exportType, HttpServletResponse httpServletResponse) 
        throws IOException, ValidationException, OPMException {
        
        final String signature = CLASS_NAME + "#exportReport(String reportName, " +
                "ReportResponse response, ExportType exportType, HttpServletResponse httpServletResponse)";
        
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature, 
                new String[] {"reportName", "response", "exportType", "httpServletResponse"}, 
                new Object[] {reportName, response, exportType, httpServletResponse});
        
        // validate the parameters
        WebHelper.checkRequest(logger, signature,
                new boolean[]{reportName == null 
                || reportName.trim().length() == 0, response == null, exportType == null},
                new String[]{"'reportName can't be null or empty.",
                "response cannot not be null", "exportType cannot be null"}, 
                "Failed to export for invalid parameters.");
        // get the reportService
        ReportService<ReportRequest, ReportResponse> reportSerivce = reportServices.get(reportName);
        if (reportSerivce == null) {
            throw LoggingHelper.logException(logger, signature, 
                    new OPMException("Failed to get the report service for:" + reportName));
        }
        
        convertReponseToFile(
                logger, signature, reportSerivce, reportName, response, exportType, httpServletResponse);
        
        // log and exit
        LoggingHelper.logExit(logger, signature, null);
    }

    /**
     * Converts the response to file.
     * @param logger the logger for logging.
     * @param signature the signature for logging.
     * @param reportSerivce the report service.
     * @param reportName the report name.
     * @param response the response.
     * @param exportType the export type.
     * @param httpServletResponse the http servlet response
     * @throws OPMException if there are any errors.
     * @throws IOException if there are IO errors.
     */
    private void convertReponseToFile(
            Logger logger, final String signature, ReportService<ReportRequest, ReportResponse> reportSerivce,
            String reportName, ReportResponse response, ExportType exportType,
            HttpServletResponse httpServletResponse) throws OPMException, IOException {
        // get the download file name
        String reportDownloadFileName = reportDownloadFilenames.get(reportName);
        if (reportDownloadFileName == null) {
            throw LoggingHelper.logException(logger, signature, 
                    new OPMException("Failed to get the report download name for:" + reportName));
        }
        
        String reportContentType = reportContentTypes.get(exportType.name().toLowerCase());
        if (reportContentType == null) {
            throw LoggingHelper.logException(logger, signature, 
                    new OPMException("Failed to get the report content type for:" + reportName));
        }
        
        byte[] exportContents;
        try {
            exportContents = reportSerivce.exportReport(response, exportType);
        } catch (OPMException e) {
            // wrap and re-throw the exception
            throw LoggingHelper.logException(logger, signature, e);
        }

        // set the headers
        httpServletResponse.setHeader(
                "Content-Disposition", 
                "attachment;filename=" + reportDownloadFileName + "." + exportType.toString().toLowerCase());
        httpServletResponse.setHeader(
                "Content-Type", reportContentType);
        
        // write to the response body
        try {
            httpServletResponse.getOutputStream().write(exportContents);
        } catch (IOException e) {
            // wrap and re-throw the exception
            throw LoggingHelper.logException(logger, signature, e);
        }

    }

    /**
     * <p>
     * Generates the report for the given request. 
     * The concrete report to generate is identified by reportName parameter.
     * </p>
     * 
     * @param reportName the name of report to export.
     * @param requestJSON the request data to generate report.
     * @param exportType the type of the file.
     * @param httpServletResponse the http servlet response
     * 
     * @return the response in json format.
     * @throws OPMException if there are any error occurs.
     * @throws ValidationException if report name is null or empty or the request is null
     * @throws IOException 
     */
    @RequestMapping(value = "report/download", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void downloadReport(@RequestParam String reportName, 
            @RequestParam String requestJSON,  @RequestParam ExportType exportType,
            HttpServletResponse httpServletResponse) 
            throws OPMException, ValidationException, IOException {
        final String signature = CLASS_NAME + "#downloadReport(String reportName, ReportRequest request, " +
                "HttpServletResponse httpServletResponse)";
        
        Logger logger = getLogger();
        LoggingHelper.logEntrance(
                logger, signature, new String[] { "reportName", "requestJSON", "httpServletResponse"}, 
                new Object[] {reportName, requestJSON, httpServletResponse});
        
        // validate the parameters
        WebHelper.checkRequest(logger, signature,
                new boolean[]{reportName == null || reportName.trim().length() == 0, 
                requestJSON == null || requestJSON.trim().length() == 0},
                new String[]{"'reportName can't be null or empty.",
                "requestJSON cannot not be null or empty"}, 
                "Failed to export for invalid parameters.");
        
        // get the reportService
        ReportService<ReportRequest, ReportResponse> reportSerivce = reportServices.get(reportName);
        if (reportSerivce == null) {
            throw LoggingHelper.logException(logger, signature, 
                    new OPMException("Failed to get the report for:" + reportName));
        }
        
        
        // generate the report
        ReportResponse response;
        try {
            ReportRequest request = populateRequest(reportSerivce, requestJSON);
            response = reportSerivce.getReport(request);
            // convert it to the file
            convertReponseToFile(logger, signature, reportSerivce, reportName, response, 
                    exportType, httpServletResponse);
        } catch (OPMException e) {
            // log and re-throw the exception
            throw LoggingHelper.logException(logger, signature, e);
        }
        
        // log the exit
        LoggingHelper.logExit(logger, signature, null);
    }
    
    /**
     * Gets the chart image.
     * @param reportName the name of the report.
     * @param requestJSON the request of json.
     * @param httpServletResponse the response
     * @throws ValidationException if the arguments are invalid.
     * @throws OPMException if there are any other erros.
     * @throws IOException if there are IO errors.
     */
    @RequestMapping(value="report/chart", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void getChart(@RequestParam String reportName,
            @RequestParam String requestJSON, HttpServletResponse httpServletResponse) 
         throws ValidationException, OPMException, IOException {
        final String signature = CLASS_NAME + "#getChart(String reportName, requestJSON, " +
                "HttpServletResponse httpServletResponse)";
        
        Logger logger = getLogger();
        LoggingHelper.logEntrance(logger, signature,
                new String[] { "reportName", "requestJSON", "httpServletResponse"}, 
                new Object[] {reportName, requestJSON, httpServletResponse});
        
        // validate the parameters
        WebHelper.checkRequest(logger, signature,
                new boolean[]{reportName == null || reportName.trim().length() == 0, 
                requestJSON == null || requestJSON.trim().length() == 0},
                new String[]{"'reportName can't be null or empty.",
                "requestJSON cannot not be null or empty"}, 
                "Failed to export for invalid parameters.");
        
        // get the reportService
        ReportService<ReportRequest, ReportResponse> reportSerivce = reportServices.get(reportName);
        if (reportSerivce == null) {
            throw LoggingHelper.logException(logger, signature, 
                    new OPMException("Failed to get the report for:" + reportName));
        }
        
        
        // generate the report
        ReportResponse response;
        try {
            ReportRequest request = populateRequest(reportSerivce, requestJSON);
            response = reportSerivce.getReport(request);
            byte[] imageContent = reportSerivce.renderChart(response);
            if (imageContent == null) {
                // wrap and re-throw the exception
                throw LoggingHelper.logException(logger, signature, 
                        new IllegalStateException("No image for report:" + reportName)); 
            }
            
            // set the headers
            httpServletResponse.setHeader(
                    "Content-Type", "image/png");
            
            // write to the response body
            try {
                httpServletResponse.getOutputStream().write(imageContent);
            } catch (IOException e) {
                // wrap and re-throw the exception
                throw LoggingHelper.logException(logger, signature, e);
            }
        } catch (OPMException e) {
            // log and re-throw the exception
            throw LoggingHelper.logException(logger, signature, e);
        }
        
        // log the exit
        LoggingHelper.logExit(logger, signature, null);
    }
    
    /**
     * <p>
     * This method checks whether the instance of the class was initialized properly.
     * </p>
     * @throws OPMConfigurationException if the instance was not initialized properly.
     */
    @PostConstruct
    @Override
    protected void checkInit() {
        super.checkInit();
        Helper.checkState(reportServices == null, "reportServices is not injected");
        Helper.checkState(reportDownloadFilenames == null, "reportDownloadFilenames is not injected");
        Helper.checkState(reportContentTypes == null, "reportContentTypes is not injected");
        for (String key : reportServices.keySet()) {
            ReportService<ReportRequest, ReportResponse> service = reportServices.get(key);
            Helper.checkState(key == null || key.trim().length() == 0, 
                    "reportServices cannot contains null or empty keys.");
            Helper.checkState(service == null, "reportServices cannot contains null element.");
        }
        for (String key : reportDownloadFilenames.keySet()) {
            String fileName = reportDownloadFilenames.get(key);
            Helper.checkState(key == null || key.trim().length() == 0, 
                    "reportDownloadFilenames cannot contains null or empty keys.");
            Helper.checkState(fileName == null || fileName.trim().length() == 0, 
                    "reportDownloadFilenames cannot contains null or empty element.");
        }
        for (String key : reportContentTypes.keySet()) {
            String typeName = reportContentTypes.get(key);
            Helper.checkState(key == null || key.trim().length() == 0, 
                    "reportContentTypes cannot contains null or empty keys.");
            Helper.checkState(typeName == null || typeName.trim().length() == 0, 
                    "reportContentTypes cannot contains null or empty element.");
        }
    }
}

