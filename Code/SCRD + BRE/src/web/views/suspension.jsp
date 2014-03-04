<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The suspension page.
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
    <title>Service Credit Suspension</title>

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

    <script type="text/javascript">
	 var rootUrl = '${ctx}' + '/';
	 var accountId = ${accountId};
	</script>
    <!-- external main js -->
    <script type="text/javascript" src="${ctx}/js/script.js"></script>

    <script type="text/javascript" src="${ctx}/js/suspension.js"></script>


    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

</head>
<body>
    <div id="wrapper">
        <%@ include file="include/header.jsp"%>
        <!-- #header -->
        
        

        <div id="content">
        	<p class="breadcurmb"><span>Suspense</span></p>
            <div class="pageTitleArea">
	            <h2 class="pageTitle">Suspense</h2>
                <div class="setHomeLink">
                	<input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
                    <a href="javascript:;" class="jsShowTips">?</a>
                </div>
            </div>
            
            <div class="grayPanel suspensePanel" id="suspensePage">
                <div class="panelHeader">
                	<h3 class="panelTitle">Currently Selected Account</h3>
                    <div class="corner cornerTl"></div>
                	<div class="corner cornerTr"></div>
                </div>
                
                <div class="panelBody">
                	<div class="accountSummaryPanel group">
                        <div class="column column1">
                            <h4>Robert Taylor</h4>
                            <div class="fieldRow">
                                <p class="fieldTitle">CSD #<span>:</span></p>
                                <p class="fieldVal">1234574</p>
                            </div>
                            <div class="fieldRow">
                                <p class="fieldTitle">Birth Date<span>:</span></p>
                                <p class="fieldVal">12/18/1956</p>
                            </div>
                            <div class="fieldRow">
                                <p class="fieldTitle">SSN<span>:</span></p>
                                <p class="fieldVal">383-72-0234</p>
                            </div>
                            <div class="fieldRow">
                                <p class="fieldTitle">Plan Type<span>:</span></p>
                                <p class="fieldVal">CSRS</p>
                            </div>
                        </div>
                        <div class="column column2">
                            <h4>Status</h4>
                            <div class="fieldRow">
                                <p class="fieldTitle">Active(1)</p>
                            </div>
                            <div class="fieldRow">
                                <p class="fieldTitle">Last Action<span>:</span></p>
                                <p class="fieldVal">New receipt through St. Louis (6)</p>
                            </div>
                            <div class="fieldRow">
                                <p class="fieldTitle">Action Date<span>:</span></p>
                                <p class="fieldVal">1/6/2013</p>
                            </div>
                        </div>
                        <div class="column column3">
                            <h4>Payments (<span>5</span>)</h4>
                            <div class="fieldRow">
                                <p class="fieldTitle">Total<span>:</span></p>
                                <p class="fieldVal">$2,500.00</p>
                            </div>
                            <div class="fieldRow">
                                <p class="fieldTitle">Balance<span>:</span></p>
                                <p class="fieldVal">$326.98</p>
                            </div>
                        </div>
                        <div class="column column4">
                            <div class="fieldRow">
                                <p class="fieldTitle">Frozen<span>:</span></p>
                                <p class="fieldVal">YES</p>
                            </div>
                            <div class="fieldRow">
                                <p class="fieldTitle">Grace Period<span>:</span></p>
                                <p class="fieldVal">YES</p>
                            </div>
                        </div>
                    </div>
                    <div class="suspenseTblWrapper clear">
                        <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable" id="suspenseTbl" width="100%">
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
                                    <th class="unsortable checkBoxCell">Post</th>
                                    <th>Payment Status</th>
                                    <th class="defaultSortCol defaultSortDown sortArrowCloseToBorder">Deposit Date</th>
                                    <th>BatBlkSeq</th>
                                    <th>CSD #</th>
                                    <th class="unsortable checkBoxCell">ACH</th>
                                    <th class="sortArrowCloseToBorder">Amount</th>
                                    <th>Birth Date</th>
                                    <th class="nameCol">Name</th>
                                    <th>Balance</th>
                                    <th class="lastCol">Account</th>
                                </tr>
                            </thead>
                            <tfoot>
                           		<tr>
                                    <td class="blankCell firstCol">&nbsp;</td>
                                    <td class="checkBoxCell"><input name="postChkbx" type="checkbox" /></td>
                                    <td></td>
                                    <td class="dateCellDepositDate2"></td>
                                    <td></td>
                                    <td></td>
                                    <td class="checkBoxCell"><input name="achChkbx" type="checkbox" /></td>
                                    <td></td>
                                    <td class="dateCellBirthDate2"></td>
                                    <td></td>
                                    <td></td>
                                    <td class="lastCol"></td>
                                </tr>
                            </tfoot>
                            <tbody>
                               
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="suspenseTblBtnWrapper group">
                	<a href="javascript:;" class="priBtn fRight jsPrintSuspense"><span><span>Print</span></span></a>
                    <a href="javascript:;" class="priBtn priBtnDisabled fRight jsResetSelectedPaymentSuspense"><span><span>Reset Selected Payment</span></span></a>
                    <a href="javascript:;" class="priBtn fRight jsRefreshSuspense"><span><span>Refresh</span></span></a>
                    <a href="javascript:;" class="priBtn priBtnDisabled fRight jsPostCheckedPaymentsSuspense"><span><span>Post Checked Payments</span></span></a>
                </div>
            </div>
            <!-- .accountSummaryPanel -->
            
            <div class="accountPanymentsWrapper group">
            	<div class="leftBox">
                	<h3 class="boxTitle">Payment Transaction Details</h3>
                    <div class="boxInner group">
                    	<div class="halfCol">
                            <div class="halfRowField">
                                <p class="fieldLabel">Batch Number :</p>
                                <input type="text" class="text" name="batchNum" value="" readonly="readonly" title="input batch number"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">Block Number :</p>
                                <input type="text" class="text" name="blockNum" value="" readonly="readonly" title="input block number"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">Sequence Number :</p>
                                <input type="text" class="text" name="seqNum" value="" readonly="readonly" title="input sequence number"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">Payment CSD Number :</p>
                                <input type="text" class="text" name="csdNum" value="" readonly="readonly" title="input csd number"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">Payment Date of Birth :</p>
                                <input type="text" class="text" name="bDate" value="" readonly="readonly" title="input payment Date of Birth"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">Payment Status :</p>
                                <input type="text" class="text" name="pStatus" value="" readonly="readonly" title="input payment status"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">Payment Amount :</p>
                                <input type="text" class="text" name="pAmount" value="" readonly="readonly" title="input payment amount"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">Payment Transaction Date :</p>
                                <input type="text" class="text" name="tDate" value="" readonly="readonly" title="input payment transaction date"/>
                            </div>
                        </div>
                    	<div class="halfCol">
                        	<div class="halfRowField">
                                <p class="fieldLabel">SC Master Name :</p>
                                <input type="text" class="text" name="scmName" value="" readonly="readonly" title="input SC Master name"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">SC Master CSD Number :</p>
                                <input type="text" class="text" name="scmcsdNum" value="" readonly="readonly" title="input SC Master CSD number"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">SC Master Date of Birth :</p>
                                <input type="text" class="text" name="scmbDate" value="" readonly="readonly" title="input SC Master date of birth"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">SC Master Account Status :</p>
                                <input type="text" class="text" name="scmStatus" value="" readonly="readonly" title="input SC Master account status"/>
                            </div>
                            <div class="halfRowField">
                                <p class="fieldLabel">Master Account Balance :</p>
                                <input type="text" class="text" name="maBalance" value="" readonly="readonly" title="input master account balance"/>
                            </div>
                        </div>
                    </div>
                    <div class="boxBtnWrapper clear">
                    	<a href="javascript:;" class="priHighBtn priHighBtnTwoRow jsLinkPaymentToCurrent"><span><span>Link Payment to<br />Current Employee</span></span></a>
                    	<a href="javascript:;" class="priHighBtn priHighBtnTwoRow jsMakeThisEmpCur"><span><span>Make This<br />Employee Current</span></span></a>
                        <a href="javascript:;" class="priHighBtn priHighBtnOneRow jsTransferSuspense"><span><span>Transfer</span></span></a>
                    	<a href="javascript:;" class="priHighBtn priHighBtnOneRow jsHistorySuspense"><span><span>History</span></span></a>
                    </div>
            	</div>
                
                <div class="rightBox">
                	<h3 class="boxTitle">Payment Notes</h3>
                    <form action="#">
                    <div class="boxInner">
                    	<div class="textareaWrapper"><textarea class="paymentNotes" name="paymentNotes" cols="1" rows="1"></textarea><span class="placeholderTxt isHidden">Payment notes here</span></div>
                    </div>
                    <div class="boxBtnWrapper clear">
                    	<a href="javascript:;" class="priHighBtn priHighBtnOneRow jsSaveNoteSuspense"><span><span>Save Note</span></span></a>
                    	<a href="javascript:;" class="priHighBtn priHighBtnOneRow jsDiscardNoteChangesSuspense"><span><span>Discard Changes</span></span></a>
                    	<input name="" type="reset" class="isHidden" />
                    </div>
                    </form>
            	</div>
            </div>
            <!-- .accountPanymentsWrapper -->
            
            <div class="statusInfoBar">
            	<a href="javascript:;" class="jsShowStatusInfoPopup"><span class="jsSuspensionNum"></span> unresolved &amp; suspense payments</a>
                <div class="corner cornerTl"></div>
                <div class="corner cornerTr"></div>
                <div class="corner cornerBl"></div>
                <div class="corner cornerBr"></div>
            </div>
            
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
                        <tr>
                            <td><span class="rowNoti"></span>05/06/2013</td>
                            <td>08:00:00 AM</td>
                            <td class="lastCol">Notification from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                        </tr>
                        <tr>
                            <td><span class="rowInfo"></span>05/06/2013</td>
                            <td>08:00:00 AM</td>
                            <td class="lastCol">Information from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                        </tr>
                        <tr>
                            <td><span class="rowErr"></span>05/06/2013</td>
                            <td>08:00:00 AM</td>
                            <td class="lastCol">Error from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                        </tr>
                        <tr>
                            <td><span class="rowInfo"></span>05/06/2013</td>
                            <td>08:00:00 AM</td>
                            <td class="lastCol">Information from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                        </tr>
                        <tr>
                            <td><span class="rowInfo"></span>05/06/2013</td>
                            <td>08:00:00 AM</td>
                            <td class="lastCol">Information from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                        </tr>
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
    
    <div class="popup transferSuspensePopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
                <h4 class="popupTitle">Transfer Payment</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
			<div class="tranferTypeWrapper">
            	<h5>Choose Transfer Type</h5>
                <ul>
                	<li><input name="tpType" type="radio" value="tpType1" id="tpType1" checked="checked"/> <label for="tpType1">Refund Payment</label></li>
                    <li><input name="tpType" type="radio" value="tpType2" id="tpType2" /> <label for="tpType2">Direct Pay Life Insurance</label></li>
                    <li><input name="tpType" type="radio" value="tpType3" id="tpType3" /> <label for="tpType3">Voluntary Contributions</label></li>
                    <li><input name="tpType" type="radio" value="tpType4" id="tpType4" /> <label for="tpType4">Debit Voucher</label></li>
                    <li><input name="tpType" type="radio" value="tpType5" id="tpType5" /> <label for="tpType5">Annuity</label></li>
                </ul>
            </div>
            <div class="popupBtnWrapper">
                <a class="priBtn jsDoTransferSuspense"><span><span>OK</span></span></a>
                <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .transferSuspensePopup -->
    
    <div class="popup refundCreditBalancePopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Refund Credit Balance</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Do you want to refund the credit balance as well as the payment?<br />Make sure no other Suspense Refund will refund the balance!</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsAlertOpenDoc"><span><span>Yes</span></span></a>
                <a class="priBtn jsAlertOpenDoc"><span><span>No</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .refundCreditBalancePopup -->
    
    <div class="popup invalidCSDPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
                <h4 class="popupTitle">Invalid CSD</h4>
                <a class="popupClose popupCloseX jsClosePopup" href="javascript:;">Close</a>
            </div>
            <div class="popupPadding">
            	<p><img src="i/icon-error.png" alt="" width="22" height="22" class="vMiddle" /> 8888888 is not a valid Claimant Account Number.</p>
            </div>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .invalidCSDPopup -->
    
    <div class="popup currentSuspensePrintPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
        	    <h4 class="popupTitle">Suspense</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea currentSuspensePrintPreview">
                    <div class="reportHeader">
                        <span class="reportDate">Thursday, June 27, 2013</span>
                        <h1>Current Suspense</h1>
                    </div>
                    
                    <div class="reportTblWrapper">
                        <p><strong>Checks from the Lockbox Bank</strong></p>
                        <table cellpadding="0" cellspacing="0" border="0" class="suspenseReportTbl" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                                <col class="col3"/>
                                <col class="col4"/>
                                <col class="col5"/>
                                <col class="col6"/>
                                <col class="col7"/>
                                <col class="col8"/>
                                <col class="col9"/>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>Payment Status</th>
                                    <th>Date</th>
                                    <th>BatBlkSeq</th>
                                    <th>CSD #</th>
                                    <th class="aRight amountCell">Amount</th>
                                    <th>Birth Date</th>
                                    <th>Claimant Name</th>
                                    <th class="aRight">Acct Balance</th>
                                    <th class="aCenter">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                              
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="printPopupBtnWrapper">
            	<div class="fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    <a href="javascript:;" class="printLink jsDoPrintCurrentSuspense">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .currentSuspensePrintPopup -->
    
	<div class="popup suspenseHistoryPrintPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
        	    <h4 class="popupTitle">Suspense History</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea suspenseHistoryPrintPreview">
                    <div class="reportHeader">
                        <h1>CSD  7058683   HAFXXXX R IGIDXXXX</h1>
                        <div class="group">
                            <div class="leftCol"><strong>007  - 01  - 34</strong><br />Payment DatabaseID #735817<br />Imported Through Lockbox Bank<br />ACH Payment<br />This payment type cannot enter the GL</div>
                            <div class="midCol aCenter">Fantasy Towers<br />Apartment 1-A<br />123 Main Street<br />Anytown, DC 55555-4444</div>
                            <div class="rightCol">Printed on 06/07/2013</div>
                        </div>
                        <div class="suspenseStatus">Resolved Suspense</div>
                    </div>
                    
                    <div class="reportTblWrapper">
                        <p>Payment Audit</p>
                        <table cellpadding="0" cellspacing="0" border="0" class="suspenseReportTbl" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                                <col class="col3"/>
                                <col class="col4"/>
                                <col class="col5"/>
                                <col class="col6"/>
                                <col class="col7"/>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>UserName</th>
                                    <th class="aCenter">Change Time</th>
                                    <th>CSD #</th>
                                    <th class="aCenter">Birth Date</th>
                                    <th class="aRight">Amount</th>
                                    <th class="aCenter">Deposit Date</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td>X XXX42</td>
                                <td class="aCenter">1/7/13 11:29 am</td>
                                <td>7058683</td>
                                <td class="aCenter">01/01/1940</td>
                                <td class="aRight">$250.00</td>
                                <td class="aCenter">01/07/2013</td>
                                <td>Imported from Lockbox</td>
                              </tr>
                              <tr>
                                <td>X XXX42</td>
                                <td class="aCenter">1/7/13 11:29 am</td>
                                <td>7058683</td>
                                <td class="blackCell aCenter">01/01/1800</td>
                                <td class="aRight">$250.00</td>
                                <td class="aCenter">01/07/2013</td>
                                <td><strong>Unresolved</strong></td>
                              </tr>
                      
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="printPopupBtnWrapper">
            	<div class="fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    <a href="javascript:;" class="printLink jsDoPrintSuspenseHistory">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .suspenseHistoryPrintPopup -->
    
    <div class="popup savePaymentNotesSuccessPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Success</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">The payment notes have been saved successfully.</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .saveReportSuccessPopup -->
    
</body>
</html>