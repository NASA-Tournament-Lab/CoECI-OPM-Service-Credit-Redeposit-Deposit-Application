<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The admin page.
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
    <title>Service Credit Admin</title>

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

    <script type="text/javascript" src="${ctx}/js/admin.js"></script>

    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

</head>
<body>
    <div id="wrapper">
        <%@ include file="include/header.jsp"%>

        <div id="content">
            <p class="breadcurmb"><a href="${ctx}/admin/view">Admin</a><span>Regenerate Latest Reports</span></p>
            <div class="pageTitleArea">
                <h2 class="pageTitle">Regenerate Latest Reports</h2>
                <div class="setHomeLink">
                    <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
                    <a href="javascript:;" class="jsShowTips">?</a>
                </div>
            </div>

            <div class="tabsArea adminTab" id="adminPage">
                <div class="tabsBar whiteTabsBar">
                    <ul>
                        <li><a class="current" href="javascript:;" id="regenerateReportsTab">Regenerate Latest Reports</a></li>
                        <li><a href="javascript:;" id="userPermissionsTab">User Permissions</a></li>
                        <!--li><a href="Admin_Holidays.html">Holidays</a></li-->
                    </ul>
                </div>

                <div class="tabsBlock regenerateReportsTab">
                    <div class="currentUserRights">
                        <p class="fLeft">You have permission to reprint the reports because you are a Administrator / Supervisor.</p>
                        <p class="fRight">Your role: <span class="roleDesc">Administrator</span> - <strong>all rights</strong></p>
                    </div>

                    <table border="0" cellpadding="0" cellspacing="0" id="latestReportsTbl" width="100%">
                        <colgroup>
                            <col class="col1"/>
                            <col class="col2"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <td class="firstCol"><a href="javascript:;" class="priBtn priBtnDisabled jsRegeneratePaymentInvoices"><span><span>Payment Invoices</span></span></a></td>
                                <td class="lastCol">None payments processed.</td>
                            </tr>
                            <tr>
                                <td class="firstCol"><a href="javascript:;" class="priBtn priBtnDisabled jsRegenerateInitialBillInvoices"><span><span>Initial Bill Invoices</span></span></a></td>
                                <td class="lastCol">None bills printed.</td>
                            </tr>
                            <tr>
                                <td class="firstCol"><a href="javascript:;" class="priBtn priBtnDisabled jsRegenerateReversalInvoices"><span><span>Reversal Invoices</span></span></a></td>
                                <td class="lastCol">None reversals printed.</td>
                            </tr>
                            <tr>
                                <td class="firstCol"><a href="javascript:;" class="priBtn priBtnDisabled jsRegenerateStopACHLetters"><span><span>Stop ACH Letters</span></span></a></td>
                                <td class="lastCol">None letters printed.</td>
                            </tr>
                            <tr>
                                <td class="firstCol"><a href="javascript:;" class="priBtn priBtnDisabled jsRegenerateRefundMemos"><span><span>Refund Memos</span></span></a></td>
                                <td class="lastCol">None refunds printed.</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- .regenerateReportsTab -->

                <div class="tabsBlock userPermissionsTab isHidden">
                    <div class="doubleClickHint">Double-click a row to edit the user's information and permission</div>

                    <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable" id="userPermissionsTbl" width="100%">
                        <colgroup>
                            <col class="col1"/>
                            <col class="col2"/>
                            <col class="col3"/>
                            <col class="col4"/>
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="firstCol defaultSortCol defaultSortDown aCenter">Network ID</th>
                                <th class="aCenter">First Name</th>
                                <th class="aCenter">Last Name</th>
                                <th class="aCenter lastCol">Role Assignment</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <!-- .userPermissionsTab -->
            </div>
            <!-- .tabsArea -->

        </div>
        <!-- #content -->

        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- #footer -->
    </div>
    <!-- #wrapper -->

    <div class="alpha"></div>

    <div class="popup editUserPermissionsPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Edit User Permissions</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="userPermissionWrapper">
                <h5>User Information</h5>
                <div class="fieldRow">
                    <div class="fieldLabel">Status :</div>
                    <select id="userStatuses">
                    </select>
                    <span class="reqMark">*</span>
                </div>
                <div class="fieldRow">
                    <div class="fieldLabel">Permission :</div>
                    <select id="roles">
                    </select>
                    <span class="reqMark">*</span>
                </div>
                <div class="fieldRow">
                    <div class="fieldLabel">First Name :</div>
                    <input name="fName" id="fName" type="text" class="text" value="" maxlength="25"/>
                    <span class="reqMark">*</span>
                </div>
                <div class="fieldRow">
                    <div class="fieldLabel">Last Name :</div>
                    <input name="lName" id="lName" type="text" class="text" value="" maxlength="50"/>
                    <span class="reqMark">*</span>
                </div>
                <div class="fieldRow">
                    <div class="fieldLabel">Email Address :</div>
                    <input name="eMail" id="eMail" type="text" class="text" value="" maxlength="128"/>
                    <span class="reqMark">*</span>
                    <span class="fieldNote">ex : JDOE@opm.gov</span>
                </div>
                <div class="fieldRow">
                    <div class="fieldLabel">Telephone :</div>
                    <input name="phone" id="phone" type="text" class="text" value="" maxlength="20"/>
                    <span class="reqMark">*</span>
                </div>
                <div class="fieldRow">
                    <div class="fieldLabel">Supervisor :</div>
                    <select id="supervisors">
                    </select>
                </div>
            </div>
            <div class="popupBtnWrapper">
                <a class="priBtn jsSaveUserPermission"><span><span>Save</span></span></a>
                <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .editUserPermissionsPopup -->

    <div class="popup saveUserSuccessPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Success</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">Save User Successfully.</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .saveUserSuccessPopup -->

    <div class="popup paymentInvoicesPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Payment Invoices</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea group">
                    <div class="printsubPage coverPagePrintPreview">
                        <div class="paymentInvoiceCover">
                            <h1>SERVICE CREDIT</h1>
                            <h2>PAYMENT/REVERSAL INVOICE</h2>
                            <div class="deliverInfo">DELIVER TO:<br /><span>Deposit Section, Boyers, PA</span></div>
                            <div class="printInfo">Printed:   6/27/2013</div>
                        </div>
                    </div>

                    <div class="printPageSepLine"></div>

                    <div class="printsubPage accountDetailsPrintPreview">
                        <div class="printHeader">
                            <h1><span>UNITED STATES</span><br/>OFFICE OF PERSONNEL MANAGEMENT</h1>
                            <p>https://www.pay.gov</p>
                        </div>
                        <div class="printMetaRight">
                            <div class="printNum"><label>Claim Number</label>CSD# 9164949</div>
                            <div class="printBirth"><label>Date of Birth</label>01/01/1940</div>
                            <div class="printAcount">ENTER AMOUNT<br/> OF THIS PAYMENT $</div>
                            <h2 class="printFormTitle">SERVICE CREDIT<br/>PAYMENT FORM</h2>
                        </div>

                        <div class="printAddress">
                            <p>Show any name or address change below</p>
                            <div class="printAddressBox">
                                <div class="printAddressHeader"></div>
                                <div class="printAddressBody">
                                    JBGXXXX S EJEJXXXX<br />
                                    123 Test<br />
                                    Apartment 1-Ab<br />
                                    123 Main Street<br />
                                    AnyTown, DC 55555-4444
                                </div>
                                <div class="printAddressFooter"></div>
                            </div>
                        </div>
                        <div class="printDetachLine clear">Please detach and return this portion with your payment; see the other side for payment instructions</div>
                        <div class="printDataArea">
                            <h3>STATEMENT OF ACCOUNT - KEEP FOR YOUR RECORDS</h3>
                            <div class="printPersonalData">
                                <div class="name">
                                    <label>Name</label>
                                    JBGXXXX S EJEJXXXX
                                </div>
                                <div class="date">
                                    <label>Date</label>
                                    06/14/2013
                                </div>
                                <div class="coveredBy">
                                    <label>Covered by</label>
                                    CSRS
                                </div>
                                <div class="claimNum">
                                    <label>Claim Number</label>
                                    CSD# 9164949
                                </div>
                            </div>
                            <div class="leftPart fLeft">
                                <p class="emLine"><label>Prior Balance Due</label><span>$34,851.90</span></p>
                                <p class="emLine"><label>Plus Interest on Prior Balance</label><span>$1.17</span></p>
                                <p class="emLine"><label>Balance Due Before Payment</label><span>$34,853.07</span></p>
                                <p class="emLine"><label>Amount of Payment</label><span>($2.00)</span></p>
                                <div class="detailsLine">
                                    <p><label>FERS Deposit/Redeposit</label><span>$0.00</span></p>
                                    <p><label>Post 9/30/82 Redeposit</label><span>$0.00</span></p>
                                    <p><label>Post 9/30/82 Deposit</label><span>$32,840.88</span></p>
                                    <p><label>Pre 10/1/82 Redeposit</label><span>$0.00</span></p>
                                    <p><label>Pre 10/1/82 Deposit</label><span>$2,010.19</span></p>
                                </div>
                                <p class="emLine"><label>New Balance Due</label><span>$34,851.07</span></p>
                            </div>
                            <div class="rightPart fRight">
                                <div class="paymentExplanation">
                                    <p><strong>Explanation of How Payments Are Applied.</strong></p>
                                    <p>The payments you make are applied to -</p>
                                    <ol class="numberList">
                                        <li>Pay for FERS Deposit or Redeposit, if any</li>
                                        <li>Pay for CSRS Post-9/30/82 redeposit, if any</li>
                                        <li>Pay for CSRS Pre-10/1/82 redeposit, if any</li>
                                        <li>Pay for CSRS Post-9/30/82 deposit, if any</li>
                                        <li>Pay for CSRS Pre-10/1/82 deposit, if any</li>
                                    </ol>
                                </div>
                                <p class="paymentNote">Invoice is for 1 payment</p>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="printFooter group">
                            <div class="fLeft leftNote">&nbsp;</div>
                            <div class="fLeft middleNote">Please check this statement with your records. If they differ,<br/>contact OPM promptly at sc_receipts@opm.gov and give us full details about the difference.<br/>
                                <strong>SEE OTHER SIDE FOR PAYMENT INSTRUCTIONS AND EXPLAINATION</strong>
                            </div>
                            <div class="fRight aRight rightNote">&nbsp;</div>
                        </div>
                    </div>

                    <div class="printPageSepLine"></div>

                    <div class="printsubPage paymentsInstructionsPrintPreview">
                        <h1>PAYMENT INSTRUCTIONS</h1>
                        <p>You may pay installments of $50 or more, but paying in full now will minimize further interest charges. After each
                payment we will send you updated account statement.</p>
                        <p>Make checks or money orders payable to U.S. Office of Personnel Management. Write your CSD# claim number and
                your date of birth on your check or money order, include the top portion of this form, and mail them to:</p>
                        <p class="indent">
                            U.S. Office of Personnel Management<br />
                            P.O. Box 979035<br />
                            St. Louis MO 63197-9000
                        </p>
                        <p>Your check will be converted to an electronic funds transfer. The debit from your account will usually occur within 24
                hour. <strong>Do not send corresondence with your payment. Do not send cash.</strong></p>
                        <p>To authorize deductions from a savings or checking account, fill out the Authorization for Direct Payments, RI 16-28,
                found at www.opm.gov and mail it to the St. Louis address shown above:</p>
                        <p>To make payment online, go to website www.pay.gov.</p>
                        <p>If you have questions about your account, contact OPM by calling (202) 606-5240, send email to screceipts@opm.gov,
                or write to:</p>
                        <p class="indent">
                            U.S. Office of Personnel Management<br />
                            1900 E Street NW, Room 3H30<br />
                            Washington DC 20415
                        </p>

                        <h1 class="notice">IMPORTANT NOTICE FROM THE OFFICE OF PERSONNEL MANAGEMENT<br />READ THIS BEFORE MAKING YOUR NEXT PAYMENT</h1>
                        <p>If you received a refund of CSRS retirement deductions for federal service ending before March 1, 1991, you need this information before making your redeposit payment. A redeposit is the repayment of retirement deductions that were withheld from your pay and later refunded to you, plus interest.</p>
                        <p>If you retire under CSRS with a regular (non-disablity) annuity, you can receive retirement credit for refunded service that ended before March 1991 without paying redeposit. If you do not make payment, your annuity will be reduced to reflect the retirement value of the redeposit and interest accumulated up to your retirement date. The reduction is based on actuarial factors; you can obtain these from your personnel office. For example, if a redeposit of $7,000 is due when you retire at age 55, your monthly annuity would be reduced by $33. The reduction goes up with retirement age. If you retire at age 61 and your redeposit is $7,000, the monthly reduction will be $38. Only your annuity will be reduced. Survivors annuities are not reduced.</p>
                        <p><strong>Why pay the redeposit?</strong> To get the maximum monthly CSRS benefit and to protect yourself in the event of disability or death in service. If you retire due to disability, you may have to pay the full amount of the redeposit in a lump sum to receive credit for the service. The same is true for your survivors should you die while employed with the federal government.</p>
                        <p><strong>I have paid part of the redeposit what will happen if I stop paying?</strong> Interest will accumulate on your unpaid balance until yoou retire, and the reduction in your monthly CSRS annuity will be based on the remaining amount due at that time. The additional interest will increase the amount of the redeposit and the amount of the reduction in your annuity.</p>
                        <p><strong>Do I have to decide now?</strong> No; you can make payments at any time before you retire. If you choose not to make payment before you retire, we will send you complete, personalized information about the amount of the redeposit and the reduction in your annuity when we process your retirement application. If you want to pay the redeposit when you retire, you must pay it in full in a lump sum.</p>
                        <p><strong>FERS Service</strong><br />Please note that if you received a refund of FERS retirement deductions, you must make a redeposit paid in full to obtain credit for that service in your annuity computation, regardless of when you took the refund.</p>

                        <div class="printFooter group">
                            <div class="fLeft leftNote">&nbsp;<br />Previous editions are not usable</div>
                            <div class="fRight aRight rightNote">RI 36-18R<br/>Revised February 2010</div>
                        </div>
                    </div>

                    <div class="printPageSepLine"></div>

                    <div class="printsubPage accountDetailsPrintPreview">
                        <div class="printHeader">
                            <h1><span>UNITED STATES</span><br/>OFFICE OF PERSONNEL MANAGEMENT</h1>
                            <p>https://www.pay.gov</p>
                        </div>
                        <div class="printMetaRight">
                            <div class="printNum"><label>Claim Number</label>CSD# 9166045</div>
                            <div class="printBirth"><label>Date of Birth</label>01/01/1940</div>
                            <div class="printAcount">ENTER AMOUNT<br/> OF THIS PAYMENT $</div>
                            <h2 class="printFormTitle">SERVICE CREDIT<br/>PAYMENT FORM</h2>
                        </div>

                        <div class="printAddress">
                            <p>Show any name or address change below</p>
                            <div class="printAddressBox">
                                <div class="printAddressHeader"></div>
                                <div class="printAddressBody">
                                    JBGXXXX W GAEFXXXX<br />
                                    Fantasy Towers<br />
                                    Apartment 1-A<br />
                                    123 Main Street<br />
                                    AnyTown, DC 55555-4444
                                </div>
                                <div class="printAddressFooter"></div>
                            </div>
                        </div>
                        <div class="printDetachLine clear">Please detach and return this portion with your payment; see the other side for payment instructions</div>
                        <div class="printDataArea">
                            <h3>STATEMENT OF ACCOUNT - KEEP FOR YOUR RECORDS</h3>
                            <div class="printPersonalData">
                                <div class="name">
                                    <label>Name</label>
                                    JBGXXXX W GAEFXXXX
                                </div>
                                <div class="date">
                                    <label>Date</label>
                                    06/14/2013
                                </div>
                                <div class="coveredBy">
                                    <label>Covered by</label>
                                    CSRS
                                </div>
                                <div class="claimNum">
                                    <label>Claim Number</label>
                                    CSD# 9166045
                                </div>
                            </div>
                            <div class="leftPart fLeft">
                                <p class="emLine"><label>Prior Balance Due</label><span>$1,575.17</span></p>
                                <p class="emLine"><label>Plus Interest on Prior Balance</label><span>$0.92</span></p>
                                <p class="emLine"><label>Balance Due Before Payment</label><span>$1,576.10</span></p>
                                <p class="emLine"><label>Amount of Payment</label><span>($5.00)</span></p>
                                <div class="detailsLine">
                                    <p><label>FERS Deposit/Redeposit</label><span>$0.00</span></p>
                                    <p><label>Post 9/30/82 Redeposit</label><span>$0.00</span></p>
                                    <p><label>Post 9/30/82 Deposit</label><span>$0.00</span></p>
                                    <p><label>Pre 10/1/82 Redeposit</label><span>$0.00</span></p>
                                    <p><label>Pre 10/1/82 Deposit</label><span>$1,571.10</span></p>
                                </div>
                                <p class="emLine"><label>New Balance Due</label><span>$1,571.10</span></p>
                            </div>
                            <div class="rightPart fRight">
                                <div class="paymentExplanation">
                                    <p><strong>Explanation of How Payments Are Applied.</strong></p>
                                    <p>The payments you make are applied to -</p>
                                    <ol class="numberList">
                                        <li>Pay for FERS Deposit or Redeposit, if any</li>
                                        <li>Pay for CSRS Post-9/30/82 redeposit, if any</li>
                                        <li>Pay for CSRS Pre-10/1/82 redeposit, if any</li>
                                        <li>Pay for CSRS Post-9/30/82 deposit, if any</li>
                                        <li>Pay for CSRS Pre-10/1/82 deposit, if any</li>
                                    </ol>
                                </div>
                                <p class="paymentNote">Invoice is for 1 payment</p>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="printFooter group">
                            <div class="fLeft leftNote">&nbsp;</div>
                            <div class="fLeft middleNote">Please check this statement with your records. If they differ,<br/>contact OPM promptly at sc_receipts@opm.gov and give us full details about the difference.<br/>
                                <strong>SEE OTHER SIDE FOR PAYMENT INSTRUCTIONS AND EXPLAINATION</strong>
                            </div>
                            <div class="fRight aRight rightNote">&nbsp;</div>
                        </div>
                    </div>

                    <div class="printPageSepLine"></div>

                    <div class="printsubPage paymentsInstructionsPrintPreview">
                        <h1>PAYMENT INSTRUCTIONS</h1>
                        <p>You may pay installments of $50 or more, but paying in full now will minimize further interest charges. After each
                payment we will send you updated account statement.</p>
                        <p>Make checks or money orders payable to U.S. Office of Personnel Management. Write your CSD# claim number and
                your date of birth on your check or money order, include the top portion of this form, and mail them to:</p>
                        <p class="indent">
                            U.S. Office of Personnel Management<br />
                            P.O. Box 979035<br />
                            St. Louis MO 63197-9000
                        </p>
                        <p>Your check will be converted to an electronic funds transfer. The debit from your account will usually occur within 24
                hour. <strong>Do not send corresondence with your payment. Do not send cash.</strong></p>
                        <p>To authorize deductions from a savings or checking account, fill out the Authorization for Direct Payments, RI 16-28,
                found at www.opm.gov and mail it to the St. Louis address shown above:</p>
                        <p>To make payment online, go to website www.pay.gov.</p>
                        <p>If you have questions about your account, contact OPM by calling (202) 606-5240, send email to screceipts@opm.gov,
                or write to:</p>
                        <p class="indent">
                            U.S. Office of Personnel Management<br />
                            1900 E Street NW, Room 3H30<br />
                            Washington DC 20415
                        </p>

                        <h1 class="notice">IMPORTANT NOTICE FROM THE OFFICE OF PERSONNEL MANAGEMENT<br />READ THIS BEFORE MAKING YOUR NEXT PAYMENT</h1>
                        <p>If you received a refund of CSRS retirement deductions for federal service ending before March 1, 1991, you need this information before making your redeposit payment. A redeposit is the repayment of retirement deductions that were withheld from your pay and later refunded to you, plus interest.</p>
                        <p>If you retire under CSRS with a regular (non-disablity) annuity, you can receive retirement credit for refunded service that ended before March 1991 without paying redeposit. If you do not make payment, your annuity will be reduced to reflect the retirement value of the redeposit and interest accumulated up to your retirement date. The reduction is based on actuarial factors; you can obtain these from your personnel office. For example, if a redeposit of $7,000 is due when you retire at age 55, your monthly annuity would be reduced by $33. The reduction goes up with retirement age. If you retire at age 61 and your redeposit is $7,000, the monthly reduction will be $38. Only your annuity will be reduced. Survivors annuities are not reduced.</p>
                        <p><strong>Why pay the redeposit?</strong> To get the maximum monthly CSRS benefit and to protect yourself in the event of disability or death in service. If you retire due to disability, you may have to pay the full amount of the redeposit in a lump sum to receive credit for the service. The same is true for your survivors should you die while employed with the federal government.</p>
                        <p><strong>I have paid part of the redeposit what will happen if I stop paying?</strong> Interest will accumulate on your unpaid balance until yoou retire, and the reduction in your monthly CSRS annuity will be based on the remaining amount due at that time. The additional interest will increase the amount of the redeposit and the amount of the reduction in your annuity.</p>
                        <p><strong>Do I have to decide now?</strong> No; you can make payments at any time before you retire. If you choose not to make payment before you retire, we will send you complete, personalized information about the amount of the redeposit and the reduction in your annuity when we process your retirement application. If you want to pay the redeposit when you retire, you must pay it in full in a lump sum.</p>
                        <p><strong>FERS Service</strong><br />Please note that if you received a refund of FERS retirement deductions, you must make a redeposit paid in full to obtain credit for that service in your annuity computation, regardless of when you took the refund.</p>

                        <div class="printFooter group">
                            <div class="fLeft leftNote">&nbsp;<br />Previous editions are not usable</div>
                            <div class="fRight aRight rightNote">RI 36-18R<br/>Revised February 2010</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="printPopupBtnWrapper">
                <div class="fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    <a href="javascript:;" class="printLink jsDoPrintPaymentInvoices">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .paymentInvoicesPopup -->

    <div class="popup initialBillInvoicesPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Initial Bill Invoice</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea group">
                    <div class="printsubPage coverPagePrintPreview">
                        <div class="initialInvoiceCover">
                            <h1>SERVICE CREDIT</h1>
                            <h2>INITIAL BILL INVOICE</h2>
                            <div class="deliverInfo">DELIVER TO:<br /><span>Deposit Section, Boyers, PA</span></div>
                            <div class="printInfo">Printed:   6/27/2013</div>
                        </div>
                    </div>

                    <div class="printPageSepLine"></div>

                    <div class="printsubPage sampleInitialStatementPrintPreview">
                        <div class="printHeader">
                            <h1><span>UNITED STATES</span><br/>OFFICE OF PERSONNEL MANAGEMENT</h1>
                        </div>
                        <div class="printMetaRight">
                            <div class="printNum"><label>Claim Number</label>CSD# 9175647</div>
                            <div class="printBirth"><label>Date of Birth</label>01/01/1940</div>
                            <div class="printAcount">ENTER AMOUNT<br/> OF THIS PAYMENT $</div>
                            <h2 class="printFormTitle">CIVIL SERVICE DEPOSIT<br />ACCOUNT STATEMENT</h2>
                        </div>

                        <div class="printAddress">
                            <p>Show any name or address change below</p>
                            <div class="printAddressBox">
                                <div class="printAddressHeader"></div>
                                <div class="printAddressBody">
                                    User Test<br/>
                                    Please do not mail<br/>
                                    OPM<br/>
                                    1900 E Street NW<br/>
                                    ATTN : BENESYS<br/>
                                    Washington, DC 20415
                                </div>
                                <div class="printAddressFooter"></div>
                            </div>
                        </div>
                        <div class="printDetachLine clear">Please detach and return this portion with your payment; see the other side for payment instructions</div>
                        <div class="printDataArea">
                            <h3>STATEMENT OF ACCOUNT - KEEP FOR YOUR RECORDS</h3>
                            <div class="printPersonalData">
                                <div class="name">
                                    <label>Name</label>
                                    User Test
                                </div>
                                <div class="date">
                                    <label>Date</label>
                                    06/14/2013
                                </div>
                                <div class="coveredBy">
                                    <label>Covered by</label>
                                    CSRS
                                </div>
                                <div class="claimNum">
                                    <label>Claim Number</label>
                                    CSD# 9175647
                                </div>
                            </div>
                            <div class="billingLeftPart fLeft ">
                                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="printTbl">
                                    <colgroup>
                                        <col class="col1"/>
                                        <col class="col2"/>
                                    </colgroup>
                                    <tbody>
                                        <tr class="totalArea">
                                            <td colspan="2" class="title Row">Amount Due</td>
                                        </tr>
                                        <tr>
                                            <td>FERS Deposit/Redeposit</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>Interest *</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>CSRS Post 9/30/82 Redeposit</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>Interest *</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>CSRS Post 9/30/82 Deposit</td>
                                            <td class="aRight">$37.00</td>
                                        </tr>
                                        <tr>
                                            <td>Interest *</td>
                                            <td class="aRight">$27.28</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>CSRS Pre 10/1/82 Redeposit</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>Interest *</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>CSRS Pre 10/1/82 Deposit</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>Interest *</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr class="totalArea">
                                            <td>Lest Payments</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr class="totalArea heightRow">
                                            <td>Balance Due</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr class="heightRow">
                                            <td>FERS Deposit</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr class="heightRow">
                                            <td>CSRS Post 9/30/82 Redeposit</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr class="heightRow">
                                            <td>CSRS Post 9/30/82 Deposit</td>
                                            <td class="aRight">$64.28</td>
                                        </tr>
                                        <tr class="heightRow">
                                            <td>CSRS Pre 10/1/82 Redeposit</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr class="heightRow">
                                            <td>CSRS Pre 10/1/82 Deposit</td>
                                            <td class="aRight">$0.00</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr class="totalArea">
                                            <td>Total</td>
                                            <td class="aRight">$64.28</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="billingRightPart fLeft">
                                <div class="billingTblWrapper">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="printListTbl">
                                        <thead>
                                            <tr>
                                                <th>From</th>
                                                <th>To</th>
                                                <th>Type</th>
                                                <th>From</th>
                                                <th>To</th>
                                                <th>Type</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>01/01/2000</td>
                                                <td>02/01/2000</td>
                                                <td>D</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="paymentExplanation">
                                    <p><strong>Explanation of How Payments Are Applied.</strong></p>
                                    <p>The payments you make are applied to -</p>
                                    <ol class="numberList">
                                        <li>Pay for FERS Deposit or Redeposit, if any</li>
                                        <li>Pay for CSRS Post-9/30/82 redeposit, if any</li>
                                        <li>Pay for CSRS Pre-10/1/82 redeposit, if any</li>
                                        <li>Pay for CSRS Post-9/30/82 deposit, if any</li>
                                        <li>Pay for CSRS Pre-10/1/82 deposit, if any</li>
                                    </ol>
                                    <p>If your want your payments applied in a different order, <br />Call 1-888-767-6738 or email retire@opm.gov</p>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="printFooter group">
                            <div class="legend clear"><span>R=Redeposit Period</span> <span>D=Deposit Period</span> <span>F=FERS</span></div>
                            <div class="clear footerNote">* The Internal Revenue Service has detemined that interest paid on deposits and redeposits may not be deducted for Federal income tax purposes as interest paid on indebtedness. This is because the interest is credited to your individual retirement record and is considered a part of your lump sum credit for tax purposes. (See IRS Publication 721 for further information)</div>
                            <div class="clear otherSideNote">SEE OTHER SIDE FOR PAYMENT INSTRUCTIONS AND EXPLAINATION</div>
                            <div class="fLeft leftNote">R3623R</div>
                            <div class="fRight aRight rightNote">Reverse of RI36-23<br/>Revised February 2010</div>
                        </div>
                    </div>

                   <div class="printPageSepLine"></div>

                   <div class="printsubPage paymentsInstructionsPrintPreview">
                        <h1>PAYMENT INSTRUCTIONS</h1>
                        <p>You may pay installments of $50 or more, but paying in full now will minimize further interest charges. After each
                payment we will send you updated account statement.</p>
                        <p>Make checks or money orders payable to U.S. Office of Personnel Management. Write your CSD# claim number and
                your date of birth on your check or money order, include the top portion of this form, and mail them to:</p>
                        <p class="indent">
                            U.S. Office of Personnel Management<br />
                            P.O. Box 979035<br />
                            St. Louis MO 63197-9000
                        </p>
                        <p>Your check will be converted to an electronic funds transfer. The debit from your account will usually occur within 24
                hour. <strong>Do not send corresondence with your payment. Do not send cash.</strong></p>
                        <p>To authorize deductions from a savings or checking account, fill out the Authorization for Direct Payments, RI 16-28,
                found at www.opm.gov and mail it to the St. Louis address shown above:</p>
                        <p>To make payment online, go to website www.pay.gov.</p>
                        <p>If you have questions about your account, contact OPM by calling (202) 606-5240, send email to screceipts@opm.gov,
                or write to:</p>
                        <p class="indent">
                            U.S. Office of Personnel Management<br />
                            1900 E Street NW, Room 3H30<br />
                            Washington DC 20415
                        </p>

                        <h1 class="notice">IMPORTANT NOTICE FROM THE OFFICE OF PERSONNEL MANAGEMENT<br />READ THIS BEFORE MAKING YOUR NEXT PAYMENT</h1>
                        <p>If you received a refund of CSRS retirement deductions for federal service ending before March 1, 1991, you need this information before making your redeposit payment. A redeposit is the repayment of retirement deductions that were withheld from your pay and later refunded to you, plus interest.</p>
                        <p>If you retire under CSRS with a regular (non-disablity) annuity, you can receive retirement credit for refunded service that ended before March 1991 without paying redeposit. If you do not make payment, your annuity will be reduced to reflect the retirement value of the redeposit and interest accumulated up to your retirement date. The reduction is based on actuarial factors; you can obtain these from your personnel office. For example, if a redeposit of $7,000 is due when you retire at age 55, your monthly annuity would be reduced by $33. The reduction goes up with retirement age. If you retire at age 61 and your redeposit is $7,000, the monthly reduction will be $38. Only your annuity will be reduced. Survivors annuities are not reduced.</p>
                        <p><strong>Why pay the redeposit?</strong> To get the maximum monthly CSRS benefit and to protect yourself in the event of disability or death in service. If you retire due to disability, you may have to pay the full amount of the redeposit in a lump sum to receive credit for the service. The same is true for your survivors should you die while employed with the federal government.</p>
                        <p><strong>I have paid part of the redeposit what will happen if I stop paying?</strong> Interest will accumulate on your unpaid balance until yoou retire, and the reduction in your monthly CSRS annuity will be based on the remaining amount due at that time. The additional interest will increase the amount of the redeposit and the amount of the reduction in your annuity.</p>
                        <p><strong>Do I have to decide now?</strong> No; you can make payments at any time before you retire. If you choose not to make payment before you retire, we will send you complete, personalized information about the amount of the redeposit and the reduction in your annuity when we process your retirement application. If you want to pay the redeposit when you retire, you must pay it in full in a lump sum.</p>
                        <p><strong>FERS Service</strong><br />Please note that if you received a refund of FERS retirement deductions, you must make a redeposit paid in full to obtain credit for that service in your annuity computation, regardless of when you took the refund.</p>

                        <div class="printFooter group">
                            <div class="fLeft leftNote">&nbsp;<br />Previous editions are not usable</div>
                            <div class="fRight aRight rightNote">RI 36-18R<br/>Revised February 2010</div>
                        </div>
                   </div>

                   <div class="printPageSepLine"></div>

                   <div class="printsubPage accountDetailsPrintPreview">
                        <div class="printHeader">
                            <h1><span>UNITED STATES</span><br/>OFFICE OF PERSONNEL MANAGEMENT</h1>
                            <p>https://www.pay.gov</p>
                        </div>
                        <div class="printMetaRight">
                            <div class="printNum"><label>Claim Number</label>CSD# 9166045</div>
                            <div class="printBirth"><label>Date of Birth</label>01/01/1940</div>
                            <div class="printAcount">ENTER AMOUNT<br/> OF THIS PAYMENT $</div>
                            <h2 class="printFormTitle">SERVICE CREDIT<br/>PAYMENT FORM</h2>
                        </div>

                        <div class="printAddress">
                            <p>Show any name or address change below</p>
                            <div class="printAddressBox">
                                <div class="printAddressHeader"></div>
                                <div class="printAddressBody">
                                    JBGXXXX W GAEFXXXX<br />
                                    Fantasy Towers<br />
                                    Apartment 1-A<br />
                                    123 Main Street<br />
                                    AnyTown, DC 55555-4444
                                </div>
                                <div class="printAddressFooter"></div>
                            </div>
                        </div>
                        <div class="printDetachLine clear">Please detach and return this portion with your payment; see the other side for payment instructions</div>
                        <div class="printDataArea">
                            <h3>STATEMENT OF ACCOUNT - KEEP FOR YOUR RECORDS</h3>
                            <div class="printPersonalData">
                                <div class="name">
                                    <label>Name</label>
                                    JBGXXXX W GAEFXXXX
                                </div>
                                <div class="date">
                                    <label>Date</label>
                                    05/22/2013
                                </div>
                                <div class="coveredBy">
                                    <label>Covered by</label>
                                    CSRS
                                </div>
                                <div class="claimNum">
                                    <label>Claim Number</label>
                                    CSD# 9166045
                                </div>
                            </div>
                            <div class="leftPart fLeft">
                                <p class="emLine"><label>Prior Balance Due</label><span>$1,575.17</span></p>
                                <p class="emLine"><label>Plus Interest on Prior Balance</label><span>$0.92</span></p>
                                <p class="emLine"><label>Balance Due Before Payment</label><span>$1,576.10</span></p>
                                <p class="emLine"><label>Amount of Payment</label><span>($5.00)</span></p>
                                <div class="detailsLine">
                                    <p><label>FERS Deposit/Redeposit</label><span>$0.00</span></p>
                                    <p><label>Post 9/30/82 Redeposit</label><span>$0.00</span></p>
                                    <p><label>Post 9/30/82 Deposit</label><span>$0.00</span></p>
                                    <p><label>Pre 10/1/82 Redeposit</label><span>$0.00</span></p>
                                    <p><label>Pre 10/1/82 Deposit</label><span>$1,571.10</span></p>
                                </div>
                                <p class="emLine"><label>New Balance Due</label><span>$1,571.10</span></p>
                            </div>
                            <div class="rightPart fRight">
                                <div class="paymentExplanation">
                                    <p><strong>Explanation of How Payments Are Applied.</strong></p>
                                    <p>The payments you make are applied to -</p>
                                    <ol class="numberList">
                                        <li>Pay for FERS Deposit or Redeposit, if any</li>
                                        <li>Pay for CSRS Post-9/30/82 redeposit, if any</li>
                                        <li>Pay for CSRS Pre-10/1/82 redeposit, if any</li>
                                        <li>Pay for CSRS Post-9/30/82 deposit, if any</li>
                                        <li>Pay for CSRS Pre-10/1/82 deposit, if any</li>
                                    </ol>
                                </div>
                                <p class="paymentNote">Invoice is for 1 payment</p>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="printFooter group">
                            <div class="fLeft leftNote">&nbsp;</div>
                            <div class="fLeft middleNote">Please check this statement with your records. If they differ,<br/>contact OPM promptly at sc_receipts@opm.gov and give us full details about the difference.<br/>
                                <strong>SEE OTHER SIDE FOR PAYMENT INSTRUCTIONS AND EXPLAINATION</strong>
                            </div>
                            <div class="fRight aRight rightNote">&nbsp;</div>
                        </div>
                    </div>

                   <div class="printPageSepLine"></div>

                   <div class="printsubPage paymentsInstructionsPrintPreview">
                        <h1>PAYMENT INSTRUCTIONS</h1>
                        <p>You may pay installments of $50 or more, but paying in full now will minimize further interest charges. After each
                payment we will send you updated account statement.</p>
                        <p>Make checks or money orders payable to U.S. Office of Personnel Management. Write your CSD# claim number and
                your date of birth on your check or money order, include the top portion of this form, and mail them to:</p>
                        <p class="indent">
                            U.S. Office of Personnel Management<br />
                            P.O. Box 979035<br />
                            St. Louis MO 63197-9000
                        </p>
                        <p>Your check will be converted to an electronic funds transfer. The debit from your account will usually occur within 24
                hour. <strong>Do not send corresondence with your payment. Do not send cash.</strong></p>
                        <p>To authorize deductions from a savings or checking account, fill out the Authorization for Direct Payments, RI 16-28,
                found at www.opm.gov and mail it to the St. Louis address shown above:</p>
                        <p>To make payment online, go to website www.pay.gov.</p>
                        <p>If you have questions about your account, contact OPM by calling (202) 606-5240, send email to screceipts@opm.gov,
                or write to:</p>
                        <p class="indent">
                            U.S. Office of Personnel Management<br />
                            1900 E Street NW, Room 3H30<br />
                            Washington DC 20415
                        </p>

                        <h1 class="notice">IMPORTANT NOTICE FROM THE OFFICE OF PERSONNEL MANAGEMENT<br />READ THIS BEFORE MAKING YOUR NEXT PAYMENT</h1>
                        <p>If you received a refund of CSRS retirement deductions for federal service ending before March 1, 1991, you need this information before making your redeposit payment. A redeposit is the repayment of retirement deductions that were withheld from your pay and later refunded to you, plus interest.</p>
                        <p>If you retire under CSRS with a regular (non-disablity) annuity, you can receive retirement credit for refunded service that ended before March 1991 without paying redeposit. If you do not make payment, your annuity will be reduced to reflect the retirement value of the redeposit and interest accumulated up to your retirement date. The reduction is based on actuarial factors; you can obtain these from your personnel office. For example, if a redeposit of $7,000 is due when you retire at age 55, your monthly annuity would be reduced by $33. The reduction goes up with retirement age. If you retire at age 61 and your redeposit is $7,000, the monthly reduction will be $38. Only your annuity will be reduced. Survivors annuities are not reduced.</p>
                        <p><strong>Why pay the redeposit?</strong> To get the maximum monthly CSRS benefit and to protect yourself in the event of disability or death in service. If you retire due to disability, you may have to pay the full amount of the redeposit in a lump sum to receive credit for the service. The same is true for your survivors should you die while employed with the federal government.</p>
                        <p><strong>I have paid part of the redeposit what will happen if I stop paying?</strong> Interest will accumulate on your unpaid balance until yoou retire, and the reduction in your monthly CSRS annuity will be based on the remaining amount due at that time. The additional interest will increase the amount of the redeposit and the amount of the reduction in your annuity.</p>
                        <p><strong>Do I have to decide now?</strong> No; you can make payments at any time before you retire. If you choose not to make payment before you retire, we will send you complete, personalized information about the amount of the redeposit and the reduction in your annuity when we process your retirement application. If you want to pay the redeposit when you retire, you must pay it in full in a lump sum.</p>
                        <p><strong>FERS Service</strong><br />Please note that if you received a refund of FERS retirement deductions, you must make a redeposit paid in full to obtain credit for that service in your annuity computation, regardless of when you took the refund.</p>

                        <div class="printFooter group">
                            <div class="fLeft leftNote">&nbsp;<br />Previous editions are not usable</div>
                            <div class="fRight aRight rightNote">RI 36-18R<br/>Revised February 2010</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="printPopupBtnWrapper">
                <div class="fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    <a href="javascript:;" class="printLink jsDoPrintInitialBillInvoices">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .initialBillInvoicesPopup -->

    <div class="popup reversalInvoicesPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Reversal Invoices</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea reversalInvoicesPrintPreview letterReport group">
                    <div class="reportTitle group">
                        <p class="date clear aRight">Friday, June 28, 2013</p>
                        <h1 class="fLeft"><span>UNITED STATES</span><br />OFFICE OF<br />PERSONNEL MANAGEMENT</h1>
                        <p class="address fLeft">Washington, DC 20415-0001</p>
                    </div>

                    <div class="letterArea">
                        <div class="letterTitle"><span class="subjectLabel">Subject:</span> Notice of Payment Reversal</div>
                    </div>
                </div>
            </div>
            <div class="printPopupBtnWrapper">
                <div class="fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    <a href="javascript:;" class="printLink jsDoPrintReversalInvoices">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .reversalInvoicesPopup -->

    <div class="popup stopACHPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Stop ACH Letter</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea stopACHLettersPrintPreview letterReport group">

                    <div class="printsubPage">
                        <div class="reportTitle group">
                            <h1 class="fLeft"><span>UNITED STATES</span><br />OFFICE OF<br />PERSONNEL MANAGEMENT</h1>
                            <p class="address fLeft">Washington, DC 20415-0001</p>
                            <p class="date clear aRight">June 28, 2013</p>
                        </div>

                        <div class="letterArea">
                            <div class="letterTitle">RE: DISCONTINUE NOTICE</div>
                            <div class="letterBody">Dear :<br />
                            <br />
                            This memo references your Services Credit account, claim number CSD# 0000000. Your last payment of dated
                            Wednesday, January 01, 1800 reduces your balance to $0.00.<br />
                            <br />
                            Please submit form RI 16-28 to discontinue your enrollment in our Direct Payment program. It could take up to
                            30 business days to process your discontinuation. Enclosed please find for your convinience RI Form 16-28.
                            This form should be faxed to (314) 418-4423 ATTN: Sue Boyd.<br />
                            <br />
                            If an overpayment occurs on your last payment, and it is over $25.00, OPM will process your refund. Please
                            allow 10 to 14 business days for this process.<br />
                            <br />
                            Receivable Management Branch<br />
                            Service Credit Unit<br />
                            Washington, D.C. 20415
                            </div>
                        </div>
                    </div>

                    <div class="printPageSepLine"></div>

                    <div class="printsubPage">
                        <div class="reportTitle group">
                            <h1 class="fLeft"><span>UNITED STATES</span><br />OFFICE OF<br />PERSONNEL MANAGEMENT</h1>
                            <p class="address fLeft">Washington, DC 20415-0001</p>
                            <p class="date clear aRight">Date: December 13, 2012</p>
                        </div>
                        <h2 class="stopACHTitle">Stop ACH payment Fax transmittal</h2>

                        <table cellpadding="0" cellspacing="0" border="0" class="faxTbl" width="554">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td colspan="2" class="tblTitle tblToTitle"><strong>To : Sue Boyd/ LaShonda Parlor</strong></td>
                                </tr>
                                <tr>
                                    <td class="firstCol"><strong>Fax number:</strong> 314-418-4423</td>
                                    <td class="lastCol"><strong>Phone number:</strong> 314-418-6635</td>
                                </tr>
                                <tr>
                                    <td class="firstCol"><strong>Company:</strong> USA Bank</td>
                                    <td class="lastCol"></td>
                                </tr>
                            </tbody>
                        </table>

                        <table cellpadding="0" cellspacing="0" border="0" class="faxTbl" width="554">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td colspan="2" class="tblTitle"><strong>From: Candace Gilliar</strong></td>
                                </tr>
                                <tr>
                                    <td class="firstCol"><strong>Fax number:</strong> 202-606-0661</td>
                                    <td class="lastCol"><strong>Phone number:</strong> 202-606-3522</td>
                                </tr>
                                <tr>
                                    <td class="firstCol"><strong>Office:</strong> Receivable Mgmt</td>
                                    <td class="lastCol"><strong>Room #:</strong> 3H30</td>
                                </tr>
                            </tbody>
                        </table>

                        <table cellpadding="0" cellspacing="0" border="0" class="achPaymentList" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                                <col class="col3"/>
                                <col class="col4"/>
                                <col class="col5"/>
                                <col class="col6"/>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>&nbsp;</th>
                                    <th class="aCenter">Name</th>
                                    <th>CSD#</th>
                                    <th>DOB</th>
                                    <th class="aCenter">Payment<br />Date</th>
                                    <th class="aCenter">Completion<br />Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="aCenter">1</td>
                                    <td>Thomas E. Anfinson</td>
                                    <td>9108968</td>
                                    <td>8/16/41</td>
                                    <td class="aCenter">15th</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="aCenter">2</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="aCenter"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="aCenter">3</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="aCenter"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="aCenter">4</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="aCenter"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="aCenter">5</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="aCenter"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="aCenter">6</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="aCenter"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="aCenter">7</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="aCenter"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="aCenter">8</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="aCenter"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="aCenter">9</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="aCenter"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="aCenter">10</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="aCenter"></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                        <p class="reportNote">* Please send verification that STOP has been completed for each customer. Thank you!</p>
                    </div>
                </div>
            </div>
            <div class="printPopupBtnWrapper">
                <div class="fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    <a href="javascript:;" class="printLink jsDoPrintStopACHLetters">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .stopACHPopup -->

    <div class="popup refundMemosPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Refund Memo</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea refundMemoPrintPreview letterReport group">
                    <div class="printsubPage">
                        <div class="reportTitle group">
                            <h1 class="fLeft"><span>UNITED STATES</span><br />OFFICE OF<br />PERSONNEL MANAGEMENT</h1>
                            <p class="address fLeft">Washington, DC 20415-0001</p>
                        </div>
                        <div class="reportBody">
                            <div class="reportSubject"><span>Subject:</span>Refund of Excess Recovery Payment</div>
                            <p class="reportNote">You are hereby authorized to prepare a Secure Payment System (SPS) payment as follows:</p>
                            <div class="leftFields fLeft">
                                <div class="field">
                                    <div class="fieldLabel">Amount:</div>
                                    <div class="fieldVal shortFieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">ABA No:</div>
                                    <div class="fieldVal shortFieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">Account No:</div>
                                    <div class="fieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">Payee:</div>
                                    <div class="fieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">Address:</div>
                                    <div class="fieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">&nbsp;</div>
                                    <div class="fieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">&nbsp;</div>
                                    <div class="fieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">&nbsp;</div>
                                    <div class="fieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">&nbsp;</div>
                                    <div class="fieldVal"></div>
                                </div>
                            </div>
                            <div class="rightFields fLeft">
                                <div class="field">
                                    <div class="fieldLabel">Retirement Plan:</div>
                                    <div class="fieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">OWCP No:</div>
                                    <div class="fieldVal"></div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">Reference:</div>
                                    <div class="fieldVal">CSD#0000000</div>
                                </div>
                                <div class="field">
                                    <div class="fieldLabel">SS#:</div>
                                    <div class="fieldVal"></div>
                                </div>
                            </div>
                            <p class="paymentIdField clear">Payment Identification Field: Ref*Cr*CSD 0000000 ***</p>
                            <p class="chargeAccountNote">Charge Account Symbol 24x8135. Please provide this office with a copy of the voucher.<br />Thank you.</p>
                            <div class="leftField"><div class="fieledLabel">Prepared by: Ruth Gardner</div></div>
                            <div class="leftField"><div class="fieledLabel">Credit balance certified by: Ruth Gardner</div></div>
                            <div class="rightField"><div class="fieledLabel">DATE: 6/28/2013</div></div>
                            <div class="sepRow">*********************************************************************************************************************</div>
                            <div class="leftField vDateField"><div class="fieledLabel">Voucher date:</div><div class="underline"></div></div>
                            <div class="rightField vNoField"><div class="fieledLabel">Voucher No.</div><div class="underline"></div></div>
                            <div class="fullField authorizedField"><div class="fieledLabel">Refund Authorized by:</div><div class="underline"></div></div>
                            <div class="leftField"></div>
                            <div class="rightField signatureField"><div class="fieledLabel">Signature</div></div>
                            <div class="approvalTblHeader">APPROVAL:</div>
                            <div class="approvalTblWrapper"><div class="approvalTblWrapperInner">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <colgroup>
                                        <col class="col1"/>
                                        <col class="col2"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>Office</th>
                                            <th class="aCenter">Signature/Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Receivables Management Branch</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>Funds Management Branch</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="printPopupBtnWrapper">
                <div class="fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    <a href="javascript:;" class="printLink jsDoPrintRefundMemos">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .refundMemosPopup -->

    <%@ include file="include/notificationsPopup.jsp"%>
</body>
</html>