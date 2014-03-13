<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The payment page.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>"/>

    <!-- title -->
    <title>Service Credit Payment</title>

    <!-- metatags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="${ctx}/i/logo-opm-gray.png"type="image/x-icon" />

    <!-- External CSS -->
    <link rel="stylesheet" href="${ctx}/css/jquery-ui-1.10.3.custom.css" media="all" />
    <link rel="stylesheet" href="${ctx}/css/screen.css" media="all" />

    <!-- JS lib/plugins-->
    <script type="text/javascript" src="${ctx}/js/jquery-1.10.2.min.js"></script>
    <script type='text/javascript' src='${ctx}/js/jquery-ui-1.10.3.custom.min.js'></script>
    <script type='text/javascript' src='${ctx}/js/merge-sort.js'></script>
    <script type='text/javascript' src='${ctx}/js/sortable.js'></script>
    <script type="text/javascript" src="${ctx}/js/json2.js"></script>
    
    <script type="text/javascript" src="${ctx}/js/jquery.zclip.js"></script>
    <script type="text/javascript">
	 var rootUrl = '${ctx}' + '/';
	 
	</script>
    <!-- external main js -->
    <script type="text/javascript" src="${ctx}/js/script.js"></script>

    <script type="text/javascript" src="${ctx}/js/payment.js"></script>
    
    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

</head>
<body>
    <div id="wrapper">
        <%@ include file="include/header.jsp"%>
        <!-- #header -->
        
        

        <div id="content">
        	<p class="breadcurmb"><span>Payments</span></p>
            <div class="pageTitleArea">
	            <h2 class="pageTitle">Payments</h2>
                <div class="setHomeLink">
                	<input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
                    <a href="javascript:;" class="jsShowTips">?</a>
                </div>
            </div>
            
            <div class="grayPanel paymentsPanel" id="paymentsPage">
                <div class="panelHeader">
                	<h3 class="panelTitle">Search Payments</h3>
                    <div class="corner cornerTl"></div>
                	<div class="corner cornerTr"></div>
                </div>
                <div class="panelBody">
                	<div class="paymentSearchBox group">
                    	<div class="halfCol leftCol">
                       		<div class="fieldRow">
                                <label for="psCSDP"><p class="fieldLabel">CSD #<span>:</span></p></label>
                                <input type="text" id="psCSDP" class="text psCSD" name="psCSD"  value=""/>
                            </div>
                            <div class="fieldRow">
                                <label for="psBatchP"><p class="fieldLabel">Batch <span>:</span></p></label>
                                <input type="text" id="psBatchP" class="text psBatch" name="psBatch"  value=""/>
                                <label for="psBlockP"><span class="fieldLabel halfFieldLabel">Block <span>:</span></span></label>
                                <input type="text" id="psBlockP" class="text psBlock" name="psBlock"  value=""/>
                            </div>
                        	<div class="fieldRow">
                                <label for="psSeqP"><p class="fieldLabel">Seq <span>:</span></p></label>
                                <input type="text" id="psSeqP" class="text psSeq" name="psSeq"  value=""/>
                                <input name="psResolved" id="psResolved" type="checkbox" value="psResolved" />
                                <label class="chkbxLabel" for="psResolved">Resolved Suspense</label>
                            </div>
                            <div class="fieldRow">
                                <label for="psDepositedTypeP"><p class="fieldLabel">Deposited <span>:</span></p></label>
                                <select class="psDepositedType" id="psDepositedTypeP" onchange="changeDepositedDate(this)">
                                    <option value="any" selected="selected">Any Time</option>
                                    <option value="Deposited before">Deposited before</option>
                                    <option value="Deposited before or on">Deposited on or before</option>
                                    <option value="Deposited on">Deposited on</option>
                                    <option value="Deposited on or after">Deposited on or after</option>
                                    <option value="Deposited after">Deposited after</option>
                                    <option value="between">Deposited between</option>
                                </select>
                                <span class="singleDate fieldDisabled"><input type="text" class="text depositedDate depositDate2" name="depositedDate"  value="" readonly/><span class="fieldMask"></span></span>
                                <span  class="betweenDate isHidden">
                                	<input type="text" class="text startDate" id="startDate" name="depositedDate"  value=""/>
                                    <label for="endDate"><span class="toLabel">to</span></label>
                                    <input type="text" class="text endDate" id="endDate" name="depositedDate"  value=""/>
                                </span>
                            </div>
                        </div>
                        <div class="halfCol">
                        	<div class="fieldRow">
                                <label for="psAmountTypeP"><p class="fieldLabel">Amount <span>:</span></p></label>
                                <select class="psAmountType" id="psAmountTypeP" name="psAmountType"  onchange="changeAmountType(this)">
                                    <option value="any" selected="selected">Any Amount</option>
                                    <option value="Less than">Less than</option>
                                    <option value="Less than or equal to">Less than or equal to</option>
                                    <option value="Equal to">Equal to</option>
                                    <option value="Greater than or equal to">Greater than or equal to</option>
                                    <option value="Greater than">Greater than</option>
                                    <option value="Not equal to">Not equal to</option>
                                </select>
                            </div>
                            <div class="fieldRow">
                                <p class="fieldLabel">&nbsp;</p>
                                <span class="amountInput fieldDisabled"><input type="text" class="text psAmount" name="psAmount"  value="0.00" readonly/><span class="fieldMask"></span></span>
                            </div>
                            <div class="fieldRow">
                                <label for="psStatusP"><p class="fieldLabel">Status <span>:</span></p></label>
                                <select class="psStatus" name="psStatus" id="psStatusP">
                                    <option value="Any Status" selected="selected">Any Status</option>
                                    <option value="Adjustment">Adjustment</option>
                                    <option value="Annuity">Annuity</option>
                                    <option value="Batch Auto Refund">Batch Auto Refund</option>
                                    <option value="Batch Auto Refund Cancelled">Batch Auto Refund Cancelled</option>
                                    <option value="Credit Balance Refund">Credit Balance Refund</option>
                                    <option value="Debit Voucher">Debit Voucher</option>
                                    <option value="Deleted Transactions">Deleted Transactions</option>
                                    <option value="Direct Pay Life">Direct Pay Life</option>
                                    <option value="Health Benefits">Health Benefits</option>
                                    <option value="Lockbox Bank Error">Lockbox Bank Error</option>
                                    <option value="Manual Adjustment Cancelled">Manual Adjustment Cancelled</option>
                                    <option value="Posted">Posted</option>
                                    <option value="Processed by Hewitts DBTS">Processed by Hewitts DBTS</option>
                                    <option value="Refund Cancelled">Refund Cancelled</option>
                                    <option value="Reversed">Reversed</option>
                                    <option value="Send to Hewitt DBTS">Send to Hewitt DBTS</option>
                                    <option value="Suspended">Suspended</option>
                                    <option value="Suspense Debit Voucher">Suspense Debit Voucher</option>
                                    <option value="Suspense Refund">Suspense Refund</option>
                                    <option value="Transferred Amount">Transferred Amount</option>
                                    <option value="Unknown State">Unknown State</option>
                                    <option value="Unresolved">Unresolved</option>
                                    <option value="Voluntary Contributions">Voluntary Contributions</option>
                                    <option value="Write Off">Write Off</option>
                                </select>
                            </div>
                            <div class="fieldRow">
                                <label for="psTypeP"><p class="fieldLabel">Type <span>:</span></p></label>
                                <select class="psType" name="psType" id="psTypeP">
                                    <option value="Any Type" selected="selected">Any Type</option>
                                    <option value="ACH">ACH</option>
                                    <option value="Lockbox Check">Lockbox Check</option>
                                    <option value="Manual Payment">Manual Payment</option>
                                </select>
                            </div>
                        </div>
                    	<div class="clear paymentSearchBtnWrapper group">
                            <a href="javascript:;" class="priBtn fLeft jsResetSearchFilter"><span><span>Reset Filter</span></span></a>
                        	<a href="javascript:;" class="priBtn fRight jsSearchPayment"><span><span>Find</span></span></a>
                        </div>
                    </div>
                    <!--paymentSearchBox-->
                    
                    <div class="paymentSearchResultsWrapper">
                    	<div class="paymentSearchResultsSummary isHidden"><strong>10 Payments</strong> found matching the criteria</div>
                    	<div class="paymentSearchResults isHidden">
                            <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable" id="paymentSearchResultsTbl" width="100%">
                                <colgroup>
                                    <col class="blankCol"/>
                                    <col class="col1"/>
                                    <col class="col2"/>
                                    <col class="col3"/>
                                    <col class="col4"/>
                                    <col class="col5"/>
                                    <col class="col6"/>
                                    <col class="col7"/>
                                    <col class="col8"/>
                                    <col class="col9"/>
                                    <col class="col10"/>
                                    <col class="col11"/>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th class="unsortable firstCol">&nbsp;</th>
                                        <th class="defaultSortCol defaultSortDown sortArrowCloseToBorder asNumeric">ID Num</th>
                                        <th class="asString">BatBlkSeq</th>
                                        <th class="asString">CSD #</th>
                                        <th class="asDate">Birth Date</th>
                                        <th class="asNumeric">Amount</th>
                                        <th class="asDate">Deposited</th>
                                        <th class="asString">Pay Type</th>
                                        <th class="asString">Status</th>
                                        <th class="asString">SSN</th>
                                        <th class="asString">Claimant</th>
                                        <th class="lastCol asString">Import<br/>Record</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                </tbody>
                            </table>
                        </div>

                        <div class="pagination isHidden">
                            <div class="perPage fLeft">
                              <span>Show : </span> 
                              <select>
                                <option value="5">5</option>
                                <option value="10" selected>10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="0">All</option>
                              </select>
                              <span>per page</span> 
                            </div>
                            <div class="paginationLinks fRight">
                              <a href="javascript:;" class="toPrev toPrevDisabled">Prev</a>
                              <a href="javascript:;" class="toPage current">1</a>
                              <a href="javascript:;" class="toPage">2</a>
                              <a href="javascript:;" class="toPage">3</a>
                              <a href="javascript:;" class="toNext">Next</a>
                            </div>
                            <span class="paginationLabel fRight"><span class="startCount">1</span>-<span class="endCount">10</span> of <span class="totalCount">30</span></span>    
                        </div>
                    
                    	<div class="isHidden clear paymentSearchResultsBtnWrapper group">
                        	<div class="fRight">
                                <a href="javascript:;" class="priBtn priBtnDisabled jsAddPayment"><span><span>Add Payment</span></span></a>
                                <a href="javascript:;" class="priBtn priBtnDisabled jsViewAccountBtn"><span><span>View Account</span></span></a>
                                <a href="javascript:;" class="priBtn priBtnDisabled jsCopyPaymentsToClipboard"><span><span>Copy to Clipboard</span></span></a>
                                <a href="javascript:;" class="priBtn priBtnDisabled jsShowAuditTrail"><span><span>Show Audit Trail</span></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- .paymentsPanel -->
            
            
            
        </div>
        <!-- #content --> 
<div id="loadingOverlay" class="isHidden" style="display: none;">
    <img src="i/loader.gif" alt="Loading...">
</div>
        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- #footer -->
	</div>
	<!-- #wrapper -->

	<div class="alpha"></div>
    <div class="popup notificationPopup isHidden">
        <div class="popupArrow"></div>
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
        	    <h4 class="popupTitle">Notifications</h4>
                <div class="popupLinksWrapper">
                	<a href="javascript:;" class="popupLink">View All</a>
                    <a href="javascript:;" class="popupLink jsClosePopup">Hide</a>
                </div>
            </div>
            <table border="0" cellpadding="0" cellspacing="0" id="notificationsTbl" width="100%">
                <colgroup>
                    <col class="col1"/>
                    <col class="col2"/>
                    <col class="col3"/>
                </colgroup>
                <thead>
                    <tr>
                        <th>Date/Time</th>
                        <th>Sent by</th>
                        <th>Notification</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>05/06/2013<br/>08:00:00 AM</td>
                        <td>System</td>
                        <td>- You have 1 Record in <a href="workQueue/view">Work Queue.</a><br/>- You have 7 <a href="suspension/view">Payments in Suspense</a>.</td>
                    </tr>
                    <tr>
                        <td>05/06/2013<br/>08:00:00 AM</td>
                        <td>John Smith<br/>(Supervisor)</td>
                        <td>I reviewed the Account for John Doe and found an error in Billing Summary. Please fix. <a href="account/2002/detail">Click here to view the account</a>.</td>
                    </tr>
                </tbody>
            </table>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .notificationPopup -->

    <div class="popup infoNotiPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
                <h4 class="popupTitle">Information &amp; Notifications</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <div class="infoTblWrapper">
                <table border="0" cellpadding="0" cellspacing="0" id="infoTbl" width="100%">
                    <colgroup>
                        <col class="col1"/>
                        <col class="col2"/>
                        <col class="col3"/>
                    </colgroup>
                    <tbody>
                        
                    </tbody>
                </table>
            </div>
            <div class="filerTab">
                <ul>
                    <li><a href="javascript:;" class="current" id="rowAll">All</a></li>
                    <li><a href="javascript:;" id="rowNoti">Notifications</a></li>
                    <li><a href="javascript:;" id="rowErr">Errors</a></li>
                    <li><a href="javascript:;" id="rowInfo">Info</a></li>
                </ul>
                <div class="corner cornerBl"></div>
                <div class="corner cornerBr"></div>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .infoNotiPopup -->
    
    <div class="popup sampleReportPlaceholderPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
        	    <h4 class="popupTitle">Report Viewer</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea accountDetailsPrintPreview">
                	<p class="placeholderPrintPreview">Placeholder</p>
                
                </div>
            </div>
            <div class="printPopupBtnWrapper">
            	<div class="fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    <a href="javascript:;" class="printLink">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .sampleReportPlaceholderPopup -->

    <div class="popup addPaymentPopup isHidden">
        <div class="popupArrow"></div>
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Manually Add Payment Transaction</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="currentAccountInfo">
                <div class="fLeft">
                    <div class="accountField">
                        <p class="fieldLabel">Current Employee :</p>
                        <p class="fieldVal">Robert Taylor</p>
                    </div>
                    <div class="accountField">
                        <p class="fieldLabel">CSD #:</p>
                        <p class="fieldVal">1234567</p>
                    </div>
                </div>
                <div class="fRight">
                    <div class="accountField">
                        <p class="fieldLabel">Birth Date :</p>
                        <p class="fieldVal">12/18/1956</p>
                    </div>
                    <div class="accountField">
                        <p class="fieldLabel">Balance :</p>
                        <p class="fieldVal">$2,824.98</p>
                    </div>
                </div>
            </div>
            <div class="paymentField">
                <label for="batchNumA"><p class="fieldLabel">Batch Number :</p></label>
                <input name="batchNum" id="batchNumA" onkeypress='return  validateNumberInput(event);' type="text" class="text" value="166"/>
            </div>
            <div class="paymentField">
                <label for="blockNumA"><p class="fieldLabel">Block Number :</p></label>
                <input name="blockNum" id="blockNumA" onkeypress='return  validateNumberInput(event);' type="text" class="text" value="1"/>
            </div>
            <div class="paymentField">
                <label for="seqNumA"><p class="fieldLabel">Sequence Number :</p></label>
                <input name="seqNum" id="seqNumA" type="text" onkeypress='return  validateNumberInput(event);' class="text"/>
            </div>
            <div class="paymentField">
                <p class="fieldLabel">&nbsp;</p>
                <input name="glApply" id="glApply" type="checkbox" value="glApply" checked="checked" class="checkboxInput" disabled="disabled"/>
                <label for="glApply" style="cursor: default;">Apply to GL</label>
            </div>
            <div class="paymentField">
                <label for="paymentAmountA"><p class="fieldLabel">Amount :</p></label>
                <input name="paymentAmount" id="paymentAmountA" type="text" class="text"/>
            </div>
            <div class="paymentField paymentDateField">
                <label for="depositDateA"><p class="fieldLabel">Date of Deposit :</p></label>
                <input name="depositDate" maxlength="10" id="depositDateA" type="text" class="text depositDate2"/>
                <span class="datenote">Julian Date = 035<br/>Today is Monday, May 06, 2013</span>
            </div>
            <div class="paymentField">
                <label for="applicationDesignations"><p class="fieldLabel">Apply to :</p></label>
                <select name="paymentApplyTo" id="applicationDesignations" class="paymentApplyTo">
                  <option>Default User Order</option>
                </select>
            </div>
            <div class="paymentField radioField">
                <p class="fieldLabel">Payment Appliance:</p>
                <p class="radioWrapper" id="paymentAppliance">
                    <input name="addPaymentType" type="radio" id="ppr" value="ppr" checked="checked"/>
                    <label class="radioLabel longLabel" for="ppr">Prior Payment Recorded</label>
                    <input name="addPaymentType" type="radio" id="wo" value="wo" />
                    <label class="radioLabel" for="wo">Write Off</label>
                    <input name="addPaymentType" type="radio" id="otcp" value="otcp" />
                    <label class="radioLabel longLabel" for="otcp">Over-the-Counter Payment</label>
                    <input name="addPaymentType" type="radio" id="rtc" value="rtc" />
                    <label class="radioLabel" for="rtc">Refund to Claimant</label>
                    <input name="addPaymentType" type="radio" id="ang" value="ang" />
                    <label class="radioLabel longLabel" for="ang">Adjustment (no GL)</label>
                    
                </p>
            </div>
            <p class="addPaymentNote addPaymentNote_ppr">This payment will be part of the historical record. It was previously applied to the current balance so this action will generate a new bill.</p>
            <p class="addPaymentNote addPaymentNote_ang isHidden">This Credit or debit will not enter the GL because it does not represent money coming into or out of the Treasury.</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsDoAddPHTransaction"><span><span>Add Transaction</span></span></a>
                <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .addPaymentPopup -->

</body>
</html>
