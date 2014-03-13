<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The reports page
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>"/>
    <!-- title -->
    <title>OPM - Reports</title>
    <link rel="shortcut icon" href="<c:url value="/i/logo-opm-gray.png"/>" type="image/x-icon" />

    <!-- metatags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- External CSS -->
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.3.custom.css"/>" media="all" />
    <link rel="stylesheet" href="<c:url value="/css/jquery.treeview.css"/>" media="all" />
    <link rel="stylesheet" href="<c:url value="/css/screen.css"/>" media="all" />

    <!-- JS lib/plugins-->
    <script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
    <script type='text/javascript' src='<c:url value="/js/jquery-ui-1.10.3.custom.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery.formatCurrency-1.4.0.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery-dateFormat.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/sortable.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery.treeview.js"/>'></script>
    <script>
    	var nicEditIcons = '<c:url value="/i/nicEditIcons.gif"/>';
    </script>
    <script type='text/javascript' src='<c:url value="/js/nicEdit.js"/>'></script>
	<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
	
    
    <!-- external main js -->
    <script type="text/javascript" src="<c:url value="/js/script.js"/>"></script>
 
    <script type="text/javascript" src="<c:url value="/js/report.js"/>"></script>

    <!-- css file for IE7 -->
 <!--[if IE 7]><link rel="stylesheet" href="<c:url value="/css/ie7.css"/>" media="all" type="text/css" /><![endif]-->
    <script></script>

  </head>
<body>
	<div id="wrapper">
        <%@ include file="include/header.jsp"%>
        <!-- #header -->
        
        <div id="content">
        	<p class="breadcurmb"><span>Reports</span></p>
            <div class="pageTitleArea">
	            <h2 class="pageTitle">Reports</h2>
                <div class="setHomeLink">
                	<input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab as my home page</label>
                    <a href="javascript:;" class="jsShowTips">?</a>
                </div>
            </div>
            
            
            <div class="tabsArea reportsTab" id="reportsPage">
            	<div class="tabsBar whiteTabsBar">
					<ul>
                    	<li><a href="javascript:;" class="current reportTabsBtn" data-tabid="reportsPage">Reports</a></li>
                        <li><a href="javascript:;" class="reportTabsBtn" data-tabid="correspondencePage">Correspondence</a></li>
                        <li><a href="javascript:;" class="reportTabsBtn" data-tabid="referencePage">Reference</a></li>
                    </ul>
                </div>
                <div class="reportsActionsWrapper">
                    <a href="javascript:;" class="priBtn fRight jsPrintReport"><span><span>Print</span></span></a>
                </div>
            	<div class="tabsBlock reportTabsBlock group">
                	<div class="leftTreeWrapper">
                        <ul class="filetree leftTree sampleTree isHidden">
                            <li><a href="javascript:;" data-report-name="ReportsLibrary" class="pageContentLink selected"><span class="file"><span class="nodeText">Reports Library</span></span></a>
                                <ul>
                                    <li><a href="javascript:;" data-report-name="AccountsBalanceHistory"><span class="file"><span class="nodeText">Accounts Balance History</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="AccountNotesByUser"><span class="file"><span class="nodeText">Account Notes By User</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="AccountTypeTotals"><span class="file"><span class="nodeText">Account Type Totals</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="ActiveCreditBalancesOver25Dollars"><span class="file"><span class="nodeText">Active Credit Balances Over Twenty Five Dollars</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="BalancedScorecard"><span class="file"><span class="nodeText">Balanced Scorecard Accounts</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="BalancedLockboxScorecard"><span class="file"><span class="nodeText">Balanced Scorecard Lockbox</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="BalancedScorecardPayment"><span class="file"><span class="nodeText">Balanced Scorecard Payments</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="CalculationAuditTrail"><span class="file"><span class="nodeText">Calculation Audit Trail</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="CurrentSuspense"><span class="file"><span class="nodeText">Current Suspense</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="DailyCashflowReport"><span class="file"><span class="nodeText">Daily Cashflow Report</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="DailyReconciliationReport"><span class="file"><span class="nodeText">Daily Reconciliation Report</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="DailySuspenseReport"><span class="file"><span class="nodeText">Daily Suspense Report</span></span></a></li>
                                    <li><a href="javascript:;" data-report-name="DeductionRates"><span class="file"><span class="nodeText">Deduction Rates</span></span></a></li>
                                    
                                    <li><a href="javascript:;" data-report-name="LockBoxImportErrors"><span class="file"><span class="nodeText">Lockbox Import Errors</span></span></a></li>
									<li><a href="javascript:;" data-report-name="LockboxBankFileImportTotals"><span class="file"><span class="nodeText">Lockbox Import Totals</span></span></a></li>
									<li><a href="javascript:;" data-report-name="ManualPayments"><span class="file"><span class="nodeText">Manual Payments</span></span></a></li>
									<li><a href="javascript:;" data-report-name="MonthlySuspenseList"><span class="file"><span class="nodeText">Monthly Suspense List</span></span></a></li>
									<li><a href="javascript:;" data-report-name="PaymentHistory"><span class="file"><span class="nodeText">Payment History</span></span></a></li>
									<li><a href="javascript:;" data-report-name="PaymentsbyTypeandDateRange"><span class="file"><span class="nodeText">Payments by Type and Date Range</span></span></a></li>
									<li><a href="javascript:;" data-report-name="PaymentsPendingApproval"><span class="file"><span class="nodeText">Payments Pending Approval</span></span></a></li>
									<li><a href="javascript:;" data-report-name="RefundSectionAccountAssignments"><span class="file"><span class="nodeText">Refund Section Account Assignments</span></span></a></li>
									<li><a href="javascript:;" data-report-name="ResolvedSuspenseHistory"><span class="file"><span class="nodeText">Resolved Suspense History</span></span></a></li>
									<li><a href="javascript:;" data-report-name="ClosedAccountReport"><span class="file"><span class="nodeText">SC-007-001A (Closed Account Report)</span></span></a></li>
									<li><a href="javascript:;" data-report-name="SummaryofTotalPayments"><span class="file"><span class="nodeText">SC-007-001B (Summary of Total Payments)</span></span></a></li>
									<li><a href="javascript:;" data-report-name="MonthlyAdjustmentsReports"><span class="file"><span class="nodeText">SC-007-005 (Monthly Adjustments Reports)</span></span></a></li>
									<li><a href="javascript:;" data-report-name="ChangeHistoryReport"><span class="file"><span class="nodeText">SC-007-006 (Change History Report)</span></span></a></li>
									<li><a href="javascript:;" data-report-name="GLReportDailyReport"><span class="file"><span class="nodeText">SC-007-008 (GL Daily Report)</span></span></a></li>
									<li><a href="javascript:;" data-report-name="ReceiptSuspenseItemsReport"><span class="file"><span class="nodeText">SC-007-014 (Receipt Suspense Items Report)</span></span></a></li>
									<li><a href="javascript:;" data-report-name="SuspenseResolutionReport"><span class="file"><span class="nodeText">SC-007-015 (Suspense Resolution Report)</span></span></a></li>
									<li><a href="javascript:;" data-report-name="AccountStatisticsReport"><span class="file"><span class="nodeText">SC-007-016 (Account Statistics Report)</span></span></a></li>
									<li><a href="javascript:;" data-report-name="AccountingSummaryReport"><span class="file"><span class="nodeText">SC-007-017 (Account Summary Report)</span></span></a></li>
									<li><a href="javascript:;" data-report-name="UserAndRoleList"><span class="file"><span class="nodeText">User and Role List</span></span></a></li>
									<li><a href="javascript:;" data-report-name="UserRolePermissions"><span class="file"><span class="nodeText">User Role Permissions</span></span></a></li>

                                </ul>
                            </li>
                        
                        </ul>
                	</div>
                    <div class="rightReportWrapper">
                    	<!-- Reports Template -->
                    	<%@ include file="report-template/ReportsLibrary.jsp" %>
						<%@ include file="report-template/AccountNotesByUser.jsp" %>
						<%@ include file="report-template/AccountsBalanceHistory.jsp" %>
						<%@ include file="report-template/AccountTypeTotals.jsp" %>
						<%@ include file="report-template/ActiveCreditBalancesOver25Dollars.jsp" %>
						<%@ include file="report-template/BalancedLockboxScorecard.jsp" %>
						<%@ include file="report-template/BalancedScorecard.jsp" %>
						<%@ include file="report-template/BalancedScorecardPayment.jsp" %>
						<%@ include file="report-template/CalculationAuditTrail.jsp" %>
						<%@ include file="report-template/CurrentSuspense.jsp" %>
						<%@ include file="report-template/DailyCashflowReport.jsp" %>
						<%@ include file="report-template/DailyReconciliationReport.jsp" %>
						<%@ include file="report-template/DailySuspenseReport.jsp" %>
						<%@ include file="report-template/DeductionRates.jsp" %>
						
						<%@ include file="report-template/LockBoxImportErrors.jsp" %>
						<%@ include file="report-template/LockboxBankFileImportTotals.jsp" %>
						<%@ include file="report-template/ManualPayments.jsp" %>
						<%@ include file="report-template/MonthlySuspenseList.jsp" %>
						<%@ include file="report-template/PaymentHistory.jsp" %>
						<%@ include file="report-template/PaymentsbyTypeandDateRange.jsp" %>
						<%@ include file="report-template/PaymentsPendingApproval.jsp" %>
						<%@ include file="report-template/RefundSectionAccountAssignments.jsp" %>
						<%@ include file="report-template/ResolvedSuspenseHistory.jsp" %>
						<%@ include file="report-template/ClosedAccountReport.jsp" %>
						<%@ include file="report-template/SummaryofTotalPayments.jsp" %>
						<%@ include file="report-template/MonthlyAdjustmentsReports.jsp" %>
						<%@ include file="report-template/ChangeHistoryReport.jsp" %>
						<%@ include file="report-template/GLReportDailyDetailed.jsp" %>
						<%@ include file="report-template/GLReportDailyReport.jsp" %>
						<%@ include file="report-template/ReceiptSuspenseItemsReport.jsp" %>
						<%@ include file="report-template/SuspenseResolutionReport.jsp" %>
						<%@ include file="report-template/AccountStatisticsReport.jsp" %>
						<%@ include file="report-template/AccountingSummaryReport.jsp" %>
						<%@ include file="report-template/UserAndRoleList.jsp" %>
						<%@ include file="report-template/UserRolePermissions.jsp" %>
						
						<!-- .Reports Template -->
                    </div>
                </div>    
            </div>
            
           	<div class="tabsArea reportsTab isHidden" id="correspondencePage">
            	<div class="tabsBar whiteTabsBar">
                	<ul>
                    	<li><a href="javascript:;" class="reportTabsBtn" data-tabid="reportsPage">Reports</a></li>
                        <li><a href="javascript:;" class="reportTabsBtn current" data-tabid="correspondencePage">Correspondence</a></li>
                        <li><a href="javascript:;" class="reportTabsBtn" data-tabid="referencePage">Reference</a></li>
                    </ul>
                </div>
                <div class="reportsActionsWrapper">
                	<a href="javascript:;" class="priBtn fRight jsPrintReport"><span><span>Print</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsDelReport isHidden"><span><span>Delete</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsCancelSaveReport isHidden"><span><span>Cancel</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsSaveReport isHidden"><span><span>Save</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsEditReport isHidden"><span><span>Edit</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsCreateReport"><span><span>Create</span></span></a>
                </div>
            	<div class="tabsBlock reportTabsBlock group">
                	<div class="leftTreeWrapper">
                        <ul class="filetree leftTree sampleTree isHidden">
                            <li><a href="javascript:;" data-report-name="LettersLibrary" class="selected pageContentLink"><span class="file"><span class="nodeText">Correspondence Library</span></span></a>
                                <ul>
                                    <li><a href="javascript:;"><span class="file"><span class="nodeText">Calculation Explanation</span></span></a></li>
                                </ul>
                            </li>
                        </ul>
                	</div>
                    <div class="rightReportWrapper">
                    	<%@ include file="report-template/LettersLibrary.jsp" %>
						<div class="viewReportWrapper isHidden" data-report-name="LetterReport">
							<div class="reportPageReportWrapper report-LetterReport">
	                            <h3 class="reportTitle"></h3>
	                            <div class="letterContent"></div>
							</div>
						</div>
                   
                    	<div class="editReportWrapper isHidden">
                        	<div class="richEditorWrapper">
                        		<div>
                        		<input type="text" class="titleInput"/>
                        		</div>
                                <textarea name="reportEditor" cols="1" rows="1" id="letterReportEditor">
                                </textarea>
                            </div>
						</div>
                    </div>
                </div>    
            </div>
          
            <div class="tabsArea reportsTab isHidden" id="referencePage">
            	<div class="tabsBar whiteTabsBar">
                	<ul>
                    	<li><a href="javascript:;" class="reportTabsBtn" data-tabid="reportsPage">Reports</a></li>
                        <li><a href="javascript:;" class="reportTabsBtn" data-tabid="correspondencePage">Correspondence</a></li>
                        <li><a href="javascript:;" class="reportTabsBtn current" data-tabid="referencePage">Reference</a></li>
                    </ul>
                </div>
                <div class="reportsActionsWrapper">
                   <a href="javascript:;" class="priBtn fRight jsPrintReport"><span><span>Print</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsDelReport isHidden"><span><span>Delete</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsCancelSaveReport isHidden"><span><span>Cancel</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsSaveReport isHidden"><span><span>Save</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsEditReport isHidden"><span><span>Edit</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsCreateReport"><span><span>Create</span></span></a>
                </div>
            	<div class="tabsBlock reportTabsBlock group">
                	<div class="leftTreeWrapper">
                        <ul class="filetree leftTree sampleTree isHidden">
                            <li><a href="javascript:;"  data-report-name="ReportsLibrary" class="pageContentLink selected"><span class="file"><span class="nodeText">Reference Documents</span></span></a>
                                <ul>
                                    <li><a href="javascript:;" data-report-name="Reference1" class="pageContentLink"><span class="file"><span class="nodeText">Calculation Explanation</span></span></a></li>
                                </ul>
                            </li>
                        </ul>
                	</div>
                    <div class="rightReportWrapper">
                    	<%@ include file="report-template/ReferenceLibrary.jsp" %>
						<div class="viewReportWrapper isHidden" data-report-name="LetterReport">
							<div class="reportPageReportWrapper report-LetterReport">
	                            <h3 class="reportTitle"></h3>
	                            <div class="letterContent"></div>
							</div>
						</div>
                   
                    	<div class="editReportWrapper isHidden">
                        	<div class="richEditorWrapper">
                        		<div>
                        		<input type="text" class="titleInput"/>
                        		</div>
                                <textarea name="reportEditor" cols="1" rows="1" id="referenceReportEditor">
                                </textarea>
                            </div>
						</div>
                    </div>
                </div>    
            </div>
          
            <div class="statusInfoBar">
            	<a href="javascript:;" class="jsShowStatusInfoPopup">Status Information: Lorem Ipsum dolor sit amet...</a>
                <div class="corner cornerTl"></div>
                <div class="corner cornerTr"></div>
                <div class="corner cornerBl"></div>
                <div class="corner cornerBr"></div>
            </div>
            
        </div>
        <!-- #content --> 


        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- #footer -->
	</div>
	<!-- #wrapper -->

	<div class="alpha"></div>
	<%@ include file="include/notificationsPopup.jsp"%>
	 
    <div class="popup saveReportSuccessPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Success</h4>
                <a href="javascript:;" class="popupClose jsClosePopup jsEditReportSaved">Close</a>
            </div>
            <p class="popMsg">The changes have been saved Successfully.</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsEditReportSaved"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .saveReportSuccessPopup -->
    
    <div class="popup sampleReportPlaceholderPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
        	    <h4 class="popupTitle">Report Viewer</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
            </div>
            <div class="printPopupBtnWrapper">
            	<div class="fLeft">
            		<div class="downloadLinks fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    </div>
                    <a href="javascript:;" class="printLink jsDoPrintReport fLeft">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .sampleReportPlaceholderPopup -->

    <div class="popup deleteReportConfirmationPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Confirmation</h4>
                <a href="javascript:;" class="popupClose jsClosePopup jsEditReportSaved">Close</a>
            </div>
            <p class="popMsg">Are you sure you want to delete this item?</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsDoDeleteReport jsClosePopup"><span><span>Yes</span></span></a>
                <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .deleteReportConfirmationPopup -->


</body>
</html>
