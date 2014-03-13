<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The approval page.
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
    <title>Service Credit Approval</title>

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

    <!-- external main js -->
    <script type="text/javascript" src="${ctx}/js/script.js"></script>

    <script type="text/javascript" src="${ctx}/js/approval.js"></script>

    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

</head>
<body>
    <div id="wrapper">
        <%@ include file="include/header.jsp"%>

        <div id="content">
            <p class="breadcurmb"><a href="${ctx}/approval/view">Approval</a><span>Pending Payments</span></p>
            <div class="pageTitleArea">
                <h2 class="pageTitle">Pending Payments</h2>
                <div class="setHomeLink">
                    <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
                    <a href="javascript:;" class="jsShowTips">?</a>
                </div>
            </div>

            <div class="tabsArea approvalTab" id="approvalPage">
                <div class="tabsBar whiteTabsBar">
                    <ul>
                        <li><a class="current" href="javascript:;" id="pendingPaymentTab">Pending Payments <span class="notificationNum pendingPaymentCount"><span><span></span></span></span></a></li>
                        <li><a href="javascript:;" id="interestAdjustmentTab">Interest Adjustments <span class="notificationNum interestAdjustmentCount"><span><span></span></span></span></a></li>
                        <li><a href="javascript:;" id="paymentMoveTab">Payment Moves <span class="notificationNum paymentMoveCount"><span><span></span></span></span></a></li>
                    </ul>
                </div>

                <div class="tabsBlock pendingPaymentTab">
                    <div class="approvalPageBtnWrapper group">
                        <div class="fRight">
                            <a href="javascript:;" class="priBtn priBtnDisabled pendingApproveBtn jsPendingPaymentsApproveSelected"><span><span>Approve Selected</span></span></a>
                            <a href="javascript:;" class="priBtn priBtnDisabled pendingDisapproveBtn jsPendingPaymentsDisapproveSelected"><span><span>Disapprove Selected</span></span></a>
                            <a href="javascript:;" class="priBtn priBtnDisabled pendingVATBtn jsPendingPaymentsViewAuditTrail"><span><span>View Audit Trail</span></span></a>
                        </div>
                    </div>

                    <div class="tblWrapper">
                        <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable" id="pendingPaymentsTbl" width="100%">
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
                                    <th class="unsortable aCenter"><input type="checkbox" name="selectAllCheckbox"/></th>
                                    <th class="aCenter asString">Payment Status</th>
                                    <th class="defaultSortCol defaultSortDown sortArrowCloseToBorder asString">BatBlkSeq</th>
                                    <th class="sortArrowCloseToBorder asDate">Trans Date</th>
                                    <th class="asDate">Status Date</th>
                                    <th class="asNumeric">Amount</th>
                                    <th class="unsortable aCenter">GL</th>
                                    <th class="asString">CSD #</th>
                                    <th class="asDate">Birth Date</th>
                                    <th class="sortArrowCloseToBorder asString">Account</th>
                                    <th class="lastCol asString">User</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <!-- Bug Fix 2 -->
                        <div class="noRecordTxt isHidden"></div>
                    </div>
                    <!-- .tblWrapper -->
                </div>
                <!-- .pendingPaymentTab -->

                <div class="tabsBlock interestAdjustmentTab isHidden">
                    <div class="approvalPageBtnWrapper group">
                        <a href="javascript:;" class="priBtn priBtnDisabled fRight pendingVATBtn jsInterestAdjustmentViewAuditTrail"><span><span>View Audit Trail</span></span></a>
                        <a href="javascript:;" class="priBtn priBtnDisabled fRight pendingDisapproveBtn jsInterestAdjustmentDisapproveSelected"><span><span>Disapprove Selected</span></span></a>
                        <a href="javascript:;" class="priBtn priBtnDisabled fRight pendingApproveBtn jsInterestAdjustmentApproveSelected"><span><span>Approve Selected</span></span></a>
                        <select class="viewFilter fRight">
                            <option value="PENDING" selected="selected">Unapproved</option>
                            <option value="APPROVED">Approved</option>
                            <option value="DISAPPROVED">Disapproved</option>
                        </select>
                        <span class="viewLabel fRight">View</span>
                    </div>
                    <div class="tblWrapper">
                        <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable" id="interestAdjustementTbl" width="100%">
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
                                    <th class="unsortable aCenter checkBoxCol"><input type="checkbox" name="selectAllCheckbox" /></th>
                                    <th class="defaultSortCol defaultSortDown asString">CSD #</th>
                                    <th class="asNumeric">Pre Dep</th>
                                    <th class="asNumeric">Pre Redep</th>
                                    <th class="asNumeric">Post Dep</th>
                                    <th class="asNumeric">Post Redep</th>
                                    <th class="asNumeric">FERS</th>
                                    <th class="asDate">Adjusted</th>
                                    <th class="asString">Acct Status</th>
                                    <th class="asString">User Name</th>
                                    <th class="lastCol asString">User Status</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <!-- Bug Fix 2 -->
                        <div class="noRecordTxt isHidden"></div>
                    </div>
                </div>
                <!-- .interestAdjustmentTab -->

                <div class="tabsBlock paymentMoveTab isHidden">
                    <div class="approvalPageBtnWrapper group">
                        <a href="javascript:;" class="priBtn priBtnDisabled fRight pendingVATBtn jsPaymentMovesViewAuditTrail"><span><span>View Audit Trail</span></span></a>
                        <a href="javascript:;" class="priBtn priBtnDisabled fRight pendingDisapproveBtn jsPaymentMovesDisapproveSelected"><span><span>Disapprove Selected</span></span></a>
                        <a href="javascript:;" class="priBtn priBtnDisabled fRight pendingApproveBtn jsPaymentMovesApproveSelected"><span><span>Approve Selected</span></span></a>
                        <select class="viewFilter fRight">
                            <option value="PENDING" selected="selected">Unapproved</option>
                            <option value="APPROVED">Approved</option>
                            <option value="DISAPPROVED">Disapproved</option>
                        </select>
                        <span class="viewLabel fRight">View</span>
                    </div>
                    <div class="tblWrapper">
                        <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable" id="paymentMovesTbl" width="100%">
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
                                    <th class="unsortable aCenter checkBoxCol"><input type="checkbox" name="selectAllCheckbox" /></th>
                                    <th class="defaultSortCol defaultSortDown asString">CSD #</th>
                                    <th class="asNumeric">Pre Dep</th>
                                    <th class="asNumeric">Pre Redep</th>
                                    <th class="asNumeric">Post Dep</th>
                                    <th class="asNumeric">Post Redep</th>
                                    <th class="asNumeric">FERS</th>
                                    <th class="asDate">Adjusted</th>
                                    <th class="asString">Acct Status</th>
                                    <th class="asString">User Name</th>
                                    <th class="lastCol asString">User Status</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <!-- Bug Fix 2 -->
                        <div class="noRecordTxt isHidden"></div>
                    </div>
                </div>
                <!-- .paymentMoveTab -->

                <div class="csdNoteArea csdNoteAareaNoSelection">
                    <h3 class="noteAreaTitle">Note <span class="hasNotes">for <strong>CSD# 8888888</strong></span></h3>
                    <form action="#" id="csdNoteForm">
                        <div class="noteWrapper">
                            <textarea name="csdNote" cols="1" rows="1"></textarea>
                        </div>
                        <div class="noteAreaBtnWrapper group">
                            <div class="fRight">
                                <a href="javascript:;" class="priBtn jsCSDNoteSave"><span><span>Save Note</span></span></a>
                                <a href="javascript:;" class="priBtn priBtnDisabled jsDiscardCSDNoteChanges"><span><span>Discard Changes</span></span></a>
                                <input name="" type="reset" class="isHidden" />
                            </div>
                        </div>
                    </form>
                    <div class="noRecordTxt isHidden">Select a record to view its notes.</div>
                </div>
            </div>

            <div class="statusInfoBar">
                <a href="javascript:;" class="jsShowStatusInfoPopup">7 payments needing approval</a>
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

    <div class="popup pendingPaymentDisapprovePopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Disapprove Payment Request</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
                <h5>Reason for disapproval:</h5>
                <div class="textaareaWrapper"><textarea rows="1" cols="1"></textarea></div>
                <p class="paymentNoteHint">Type your note and click "Disapprove" or "Cancel"</p>
            </div>
            <div class="popupBtnWrapper">
                <a class="priBtn jsDoDisapprovePendingPayment"><span><span>Disapprove</span></span></a>
                <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .pendingPaymentDisapprovePopup -->

    <div class="popup interestAdjustmentsDisapprovePopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Disapprove Interest Adjustment</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
                <h5>Reason for disapproval:</h5>
                <div class="textaareaWrapper"><textarea rows="1" cols="1"></textarea></div>
                <p class="paymentNoteHint">Type your note and click "Disapprove" or "Cancel"</p>
            </div>
            <div class="popupBtnWrapper">
                <a class="priBtn jsDoDisapproveInterestAdjustment"><span><span>Disapprove</span></span></a>
                <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .interestAdjustmentsDisapprovePopup -->

    <div class="popup paymentMovesDisapprovePopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Disapprove Payment Request</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
                <h5>Reason for disapproval:</h5>
                <div class="textaareaWrapper"><textarea rows="1" cols="1"></textarea></div>
                <p class="paymentNoteHint">Type your note and click "Disapprove" or "Cancel"</p>
            </div>
            <div class="popupBtnWrapper">
                <a class="priBtn jsDoDisapprovePaymentMove"><span><span>Disapprove</span></span></a>
                <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .paymentMovesDisapprovePopup -->

    <div class="popup approveSeletedSuccessPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Success</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <!-- Bug Fix 2 -->
            <p class="popMsg">Selected payment(s) have been approved.</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .approveSeletedSuccessPopup -->

    <div class="popup disapproveSeletedSuccessPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Success</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <!-- Bug Fix 2 -->
            <p class="popMsg">Selected payment request(s) have been disapproved.</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .disapproveSeletedSuccessPopup -->

    <div class="popup savePaymentNotesSuccessPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Success</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <!-- Bug Fix 2 -->
            <p class="popMsg">The payment note has been saved successfully.</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .savePaymentNotesSuccessPopup -->

    <div class="popup apViewAuditTrailPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">View Audit Trail</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea viewAuditTrailPrintPreview">
                    <div class="reportHeader">
                        <span class="reportSCNo">SC-007-006</span>
                        <h1>Service Credit Change History Report</h1>
                        <span class="reportDate">6/27/2013</span>
                    </div>
                    <div class="reportTblWrapper">
                        <table cellpadding="0" cellspacing="0" border="0" class="csdInfoTbl" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                                <col class="col3"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>CSD #9155865</td>
                                    <td>01/01/1940</td>
                                    <td>JBFXXXX FIGFXXXX</td>
                                </tr>
                            </tbody>
                        </table>
                        <table cellpadding="0" cellspacing="0" border="0" class="auditTrailPrintTbl" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                            </colgroup>
                            <tbody>
                              <tr>
                                <th>December 12, 2011 <span class="changeTime">11:32 am</span></th>
                                <th>X XXX175</th>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Mar 1 2011 11:23AM to Dec 12 2011 11:32AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Code Changed from A(NewAccount) to C(Changes)</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Account Status Changed from New Account to Processing Complete - Rady For Review</td>
                              </tr>
                              <tr>
                                <th>January 6, 2012 <span class="changeTime">1:05 pm</span></th>
                                <th><span class="pl18">X XXX20</span></th>
                              </tr>
                              <tr>
                                <td colspan="2">Master: FERS Deposit Changed from 0.00 to 54.15</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: First Billing Date Changed from Jan  1 1800 12:00AM to Jan  6 2012 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Dec 12 2011  11:32AM to Jan  6 2012  1:05PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Payment Date Changed from Jan  1 1800 12:00AM to Jan  6 2012 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Action Code Changed from C(Changes) to B(Adjustment)</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Computatuin Date Changed from Jan  1 1800 12:00AM to Jan  6 2012 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2" class="yellowCell">Master: Account Status Changed from Processing Complete -  Ready For Review to Initial Bill Triggered</td>
                              </tr>
                              <tr>
                                <th>January 6, 2012 <span class="changeTime">8:04 pm</span></th>
                                <th>X XXX42</th>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jan  6 2012  1:05PM to Jan  6 2012  1:05PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Account Status Changed from Initial Bill Triggered to Active</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jan  6 2012  1:05PM to Jan  6 2012  8:04PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Action Code Changed from B(Adjustment) to A(New Account)</td>
                              </tr>
                              <tr>
                                <th>January 22, 2013 <span class="changeTime">9:31 am</span></th>
                                <th>X XXX20</th>
                              </tr>
                              <tr>
                                <td colspan="2" class="greyCell">Master: FERS Interest Changer  from 0.00 to 1.22. Int Total=1.22. Balance=55.37.</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jan  6 2012  8:04PM to Jan 22 2013  9:31AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Payment Date Changer  from Jan  6 2012 12:00AM to Jan 22 2013 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Action Code Changed from A(New Account) to B(Adjustment)</td>
                              </tr>
                              <tr>
                                <th>February 25, 2013 <span class="changeTime">6:40  pm</span></th>
                                <th>X XXX42</th>
                              </tr>
                              <tr>
                                <td colspan="2">Payment #741417 02/25/2013 $55.37 : Lockbox Payment Entered</td>
                              </tr>
                              <tr>
                                <td colspan="2">Payment #741417 02/25/2013 $55.37 : Status Changed from Pending to Posted - Pending</td>
                              </tr>
                              <tr>
                                <th>February 25, 2013 <span class="changeTime">8:11 pm</span></th>
                                <th>X XXX42</th>
                              </tr>
                              <tr>
                                <td colspan="2" class="greyCell">Master: FERS Interest Changed from 1.22 to 1.36. Int Total=1.36.  Balance=0.14.</td>
                              </tr>
                              <tr>
                                <td colspan="2" class="greenCell">Master: FERS Payments Changed from 0.00 to 55.37. Pay Total=55.37. Balance=0.14.</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jan 22 2013  9:31AM to Feb 25 2013  8:11PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Payment Date Changed from Jan 22 2013 12:00AM to Feb 25 2013 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Payment #741417 02/25/2013 $55.37 : Status Changed from Posted - Pending to Posted - Complete</td>
                              </tr>
                              <tr>
                                <td colspan="2">Payment #741417 02/25/2013 $55.37 : Status Date Changed from  Feb 25 2013 6:40 PM to Feb 25 2013  8:11PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Action Code Changed from B(Adjustment) to 6(New Receipt through St. Louis)</td>
                              </tr>
                              <tr>
                                <th>March 1, 2013 <span class="changeTime">4:19  pm</span></th>
                                <th>X XXX692</th>
                              </tr>
                              <tr>
                                <td colspan="2">Interest #1641: Requested: $-0.14 subtracted from FERS interest.</td>
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
                    <a href="javascript:;" class="printLink jsDoPrintAPAuditTrail">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .apViewAuditTrailPopup -->

    <div class="popup iaViewAuditTrailPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">View Audit Trail</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea viewAuditTrailPrintPreview">
                    <div class="reportHeader">
                        <span class="reportSCNo">SC-007-006</span>
                        <h1>Service Credit Change History Report</h1>
                        <span class="reportDate">6/27/2013</span>
                    </div>
                    <div class="reportTblWrapper">
                        <table cellpadding="0" cellspacing="0" border="0" class="csdInfoTbl" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                                <col class="col3"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>CSD #9155865</td>
                                    <td>01/01/1940</td>
                                    <td>JBFXXXX FIGFXXXX</td>
                                </tr>
                            </tbody>
                        </table>
                        <table cellpadding="0" cellspacing="0" border="0" class="auditTrailPrintTbl" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                            </colgroup>
                            <tbody>
                              <tr>
                                <th>December 12, 2011 <span class="changeTime">11:32 am</span></th>
                                <th>X XXX175</th>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Mar 1 2011 11:23AM to Dec 12 2011 11:32AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Code Changed from A(NewAccount) to C(Changes)</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Account Status Changed from New Account to Processing Complete - Rady For Review</td>
                              </tr>
                              <tr>
                                <th>January 6, 2012 <span class="changeTime">1:05 pm</span></th>
                                <th><span class="pl18">X XXX20</span></th>
                              </tr>
                              <tr>
                                <td colspan="2">Master: FERS Deposit Changed from 0.00 to 54.15</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: First Billing Date Changed from Jan  1 1800 12:00AM to Jan  6 2012 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Dec 12 2011  11:32AM to Jan  6 2012  1:05PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Payment Date Changed from Jan  1 1800 12:00AM to Jan  6 2012 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Action Code Changed from C(Changes) to B(Adjustment)</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Computatuin Date Changed from Jan  1 1800 12:00AM to Jan  6 2012 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2" class="yellowCell">Master: Account Status Changed from Processing Complete -  Ready For Review to Initial Bill Triggered</td>
                              </tr>
                              <tr>
                                <th>January 6, 2012 <span class="changeTime">8:04 pm</span></th>
                                <th>X XXX42</th>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jan  6 2012  1:05PM to Jan  6 2012  1:05PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Account Status Changed from Initial Bill Triggered to Active</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jan  6 2012  1:05PM to Jan  6 2012  8:04PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Action Code Changed from B(Adjustment) to A(New Account)</td>
                              </tr>
                              <tr>
                                <th>January 22, 2013 <span class="changeTime">9:31 am</span></th>
                                <th>X XXX20</th>
                              </tr>
                              <tr>
                                <td colspan="2" class="greyCell">Master: FERS Interest Changer  from 0.00 to 1.22. Int Total=1.22. Balance=55.37.</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jan  6 2012  8:04PM to Jan 22 2013  9:31AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Payment Date Changer  from Jan  6 2012 12:00AM to Jan 22 2013 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Action Code Changed from A(New Account) to B(Adjustment)</td>
                              </tr>
                              <tr>
                                <th>February 25, 2013 <span class="changeTime">6:40  pm</span></th>
                                <th>X XXX42</th>
                              </tr>
                              <tr>
                                <td colspan="2">Payment #741417 02/25/2013 $55.37 : Lockbox Payment Entered</td>
                              </tr>
                              <tr>
                                <td colspan="2">Payment #741417 02/25/2013 $55.37 : Status Changed from Pending to Posted - Pending</td>
                              </tr>
                              <tr>
                                <th>February 25, 2013 <span class="changeTime">8:11 pm</span></th>
                                <th>X XXX42</th>
                              </tr>
                              <tr>
                                <td colspan="2" class="greyCell">Master: FERS Interest Changed from 1.22 to 1.36. Int Total=1.36.  Balance=0.14.</td>
                              </tr>
                              <tr>
                                <td colspan="2" class="greenCell">Master: FERS Payments Changed from 0.00 to 55.37. Pay Total=55.37. Balance=0.14.</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jan 22 2013  9:31AM to Feb 25 2013  8:11PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Payment Date Changed from Jan 22 2013 12:00AM to Feb 25 2013 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Payment #741417 02/25/2013 $55.37 : Status Changed from Posted - Pending to Posted - Complete</td>
                              </tr>
                              <tr>
                                <td colspan="2">Payment #741417 02/25/2013 $55.37 : Status Date Changed from  Feb 25 2013 6:40 PM to Feb 25 2013  8:11PM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Action Code Changed from B(Adjustment) to 6(New Receipt through St. Louis)</td>
                              </tr>
                              <tr>
                                <th>March 1, 2013 <span class="changeTime">4:19  pm</span></th>
                                <th>X XXX692</th>
                              </tr>
                              <tr>
                                <td colspan="2">Interest #1641: Requested: $-0.14 subtracted from FERS interest.</td>
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
                    <a href="javascript:;" class="printLink jsDoPrintIAAuditTrail">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .iaViewAuditTrailPopup -->

    <div class="popup pmViewAuditTrailPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">View Audit Trail</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea viewAuditTrailPrintPreview">
                    <div class="reportHeader">
                        <span class="reportSCNo">SC-007-006</span>
                        <h1>Service Credit Change History Report</h1>
                        <span class="reportDate">6/27/2013</span>
                    </div>
                    <div class="reportTblWrapper">
                        <table cellpadding="0" cellspacing="0" border="0" class="csdInfoTbl" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                                <col class="col3"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>CSD #1166988</td>
                                    <td>06/18/1951</td>
                                    <td>Donald L Hinman</td>
                                </tr>
                            </tbody>
                        </table>
                        <table cellpadding="0" cellspacing="0" border="0" class="auditTrailPrintTbl" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                            </colgroup>
                            <tbody>
                              <tr>
                                <th>June 9, 2008 <span class="changeTime">10:42 am</span></th>
                                <th><span class="pl25">Pam Tuck</span></th>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Address 1 Changed from 2991 HUNTINGTON GROV to 2991 HUNTINGTON GROVE SQUARE</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Address 2 Changed from E SQUARE to</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Address 3 Changed from ALEXANDRIA VA 22306 to</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: City Changed from  to ALEXANDRIA</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: ZIP Changed from  to 22306</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jun 9 2004 12:00AM to Jun 9 2008 10:42AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Code Changed from B(Adjustment) to C(Changes)</td>
                              </tr>
                              <tr>
                                <td colspan="2" class="greyCell">Master: Pre 10/82 Deposit Interest Changed from 0.00 to 156.05. Int Total=1209.00. Balance=10824.99.</td>
                              </tr>
                              <tr>
                                <td colspan="2" class="greyCell">Master: Pre 10/82 Redeposit Interest Changed from 0.00 to 1053.94. Int Total=1209.00. Balance=10824.99.</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jun 9 2008 10:42AM to Jun 9 2008 10:42AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Payment Date Changed from Jun 8 2004 12:00AM to Jun 9 2008 12:00AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Action Code Changed from C(Changes) to B(Adjustment)</td>
                              </tr>
                              <tr>
                                <th>December 15, 2010 <span class="changeTime">8:46 am</span></th>
                                <th><span class="pl25">Jenn-Rong Chen</span></th>
                              </tr>
                              <tr>
                                <td colspan="2" class="greyCell">Master: Pre 10/82 Deposit Interest Changed from 156.05 to 263.72. Int Total=2044.90. Balance=11659.90.</td>
                              </tr>
                              <tr>
                                <td colspan="2" class="greyCell">Master: Pre 10/82 Redeposit Interest Changed from 1053.94 to 1781.18. Int Total=2044.90. Balance=11659.90.</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Activity Date Changed from Jun 9 2008  10:42AM to Dec 15 2010  8:46AM</td>
                              </tr>
                              <tr>
                                <td colspan="2">Master: Last Payment Date Changed from Jun 9 2008  12:00AM to Dec 15 2010  12:00AM</td>
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
                    <a href="javascript:;" class="printLink jsDoPrintPMAuditTrail">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .pmViewAuditTrailPopup -->

    <%@ include file="include/notificationsPopup.jsp"%>
</body>
</html>