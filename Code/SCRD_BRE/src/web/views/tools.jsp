<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The tools page.
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
    <title>Service Credit Tools</title>

    <!-- metatags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="${ctx}/i/logo-opm-gray.png"type="image/x-icon" />

    <!-- External CSS -->
    <link rel="stylesheet" href="${ctx}/css/jquery-ui-1.10.3.custom.css" media="all" />
    <link rel="stylesheet" href="${ctx}/css/screen.css" media="all" />

    <!-- JS lib/plugins-->
    <script type="text/javascript" src="${ctx}/js/jquery-1.10.2.min.js"></script>
    <script type='text/javascript' src='${ctx}/js/jquery-ui-1.10.3.custom.min.js'></script>
    <script type='text/javascript' src='${ctx}/js/jquery.formatCurrency-1.4.0.min.js'></script>
    <script type='text/javascript' src='${ctx}/js/merge-sort.js'></script>
    <script type='text/javascript' src='${ctx}/js/sortable.js'></script>
    <script type="text/javascript" src="${ctx}/js/json2.js"></script>

    <!-- external main js -->
    <script type="text/javascript" src="${ctx}/js/script.js"></script>

    <script type="text/javascript" src="${ctx}/js/tools.js"></script>

    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

</head>
<body>
    <div id="wrapper">
        <%@ include file="include/header.jsp"%>
        <input type="hidden" id="selectedAccountId" value="${sessionScope.currentAccountId}"/>
        <div id="content">
            <p class="breadcurmb"><a href="${ctx}/tools/view">Tools</a><span>Service Credit Preferences</span></p>
            <div class="pageTitleArea">
                <h2 class="pageTitle">Service Credit Preferences</h2>
                <div class="setHomeLink">
                    <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
                    <a href="javascript:;" class="jsShowTips">?</a>
                </div>
            </div>

            <div class="tabsArea toolsTab" id="toolsPage">
                <div class="tabsBar whiteTabsBar">
                    <ul>
                        <li><a class="current" href="javascript:;" id="preferenceTab">Service Credit Preferences</a></li>
                        <li><a href="javascript:;" id="printingToolsTab">Printing Tools</a></li>
                        <li><a href="javascript:;" id="batchPrintoutArchiveTab">Batch Printout Archive</a></li>
                    </ul>
                </div>

                <div class="tabsBlock preferenceTab">
                    <div class="preferenceArea">
                        <form action="" class="toolsForm">
                            <div class="preferenceItemTitle">User Messages</div>
                            <ul class="userMsgOptions">
                                <li><input type="checkbox" id="useAgents" /> <label for="useAgents">Use Agents</label></li>
                                <li><input type="checkbox" id="useStatusBar" /> <label for="useStatusBar">Use Status Bar</label></li>
                                <li><input type="checkbox" id="useMessageBox" /> <label for="useMessageBox">Use Message Box</label></li>
                            </ul>
                            <div class="preferenceItemTitle"><label for="otherSetting"><span class="vMiddle">Some Other Setting :</span></label><input type="text" id="otherSetting" class="text settingInput" value="" maxlength="128"/></div>
                            <div class="boxBtnWrapper clear">
                                <a href="javascript:;" class="priBtn jsSavePreference"><span><span>Save</span></span></a>
                                <a href="javascript:;" class="priBtn jsResetPreference"><span><span>Reset</span></span></a>
                                <a href="javascript:;" class="priBtn jsCancelPreference"><span><span>Cancel</span></span></a>
                                <input name="" type="reset" class="isHidden" />
                            </div>
                        </form>
                    </div>
                </div>
                <!-- .preferenceTab -->

                <div class="tabsBlock printingToolsTab isHidden">
                    <ul class="printingToolsList">
                        <li><a  href="javascript:;" class="jsShowInitStatement">Show Sample Initial Statement for Current Account</a></li>
                        <li><a  href="javascript:;" class="jsPrintChargeCard">Print Charge Card for Records Search</a></li>
                    </ul>
                </div>
                <!-- .printingToolsTab -->

                <div class="tabsBlock batchPrintoutArchiveTab isHidden">
                    <div class="bpaTblWrapper">
                        <table border="0" cellpadding="0" cellspacing="0" id="batchPrintoutArchiveTbl" class="stdTbl sortable" width="100%">
                            <colgroup>
                                <col class="col1"/>
                                <col class="col2"/>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th class="defaultSortCol">Print Date</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <form action="tools/printoutByName" method="get" id="printoutForm">
                        <div><input name="name" type="hidden" id="printoutName" title="The print out name."></input></div>
                    </form>
                </div>
                <!-- .batchPrintoutArchiveTab -->
            </div>
            <!-- .tabsArea -->

            
        </div>
        <!-- #content -->

        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- #footer -->
    </div>
    <!-- #wrapper -->

    <div class="alpha"></div>

    <div class="popup savePreferenceSuccessPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Success</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">Preference Settings Saved Successfully.</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .savePreferenceSuccessPopup -->

    <div class="popup cancelPreferenceChangesPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Cancel</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">Preference Changes Cancelled.</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .cancelPreferenceChangesPopup -->

    <!-- Following are for Printing Tools popups, which are not in scope of this assembly -->
    <div class="popup initStatementPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
                <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
                            <div class="popupTitleWrapper">
                                <h4 class="popupTitle">Show Sample Initial Statement</h4>
                                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
                            </div>
                            <div class="printScrollArea">
                                <div class="printPreviewArea sampleInitialStatementPrintPreview group">
                                    <div class="printHeader">
                                        <h1><span>UNITED STATES</span><br/>OFFICE OF PERSONNEL MANAGEMENT</h1>
                                    </div>
                                    <div class="printMetaRight">
                                        <div class="printNum"><label>Claim Number</label>CSD# <span class="report-csd"></span></div>
                                        <div class="printBirth"><label>Date of Birth</label><span class="report-birthday"></span></div>
                                        <div class="printAcount">ENTER AMOUNT<br/> OF THIS PAYMENT $</div>
                                        <h2 class="printFormTitle">CIVIL SERVICE DEPOSIT<br />ACCOUNT STATEMENT</h2>
                                    </div>

                                    <div class="printAddress">
                                        <p>Show any name or address change below</p>
                                        <div class="printAddressBox">
                                            <div class="printAddressHeader"></div>
                                            <div class="printAddressBody">
                                            </div>
                                            <div class="printAddressFooter"></div>
                                        </div>
                                    </div>
                                    <div class="printDetachLine clear">Please detach and return this portion with your payment; see the other side for payment instructions</div>
                                    <div class="printDataArea">
                                        <h3>STATEMENT OF ACCOUNT - KEEP FOR YOUR RECORDS</h3>
                                        <div class="printPersonalData">
                                            <div class="name-report">
                                                <label>Name</label>
                                                <span class="report-name"></span>
                                            </div>
                                            <div class="date">
                                                <label>Date</label>
                                                <span class="report-date"></span>
                                            </div>
                                            <div class="coveredBy">
                                                <label>Covered by</label>
                                                <span class="coverby"></span>
                                            </div>
                                            <div class="claimNum">
                                                <label>Claim Number</label>
                                                CSD# <span class="report-csd"></span>
                                            </div>
                                        </div>
                                        <div class="billingLeftPart fLeft ">
                                            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="printTbl">
                                                <colgroup>
                                                    <col class="col1"/>
                                                    <col class="col2"/>
                                                </colgroup>
                                                <tbody class="billing1-tbody">
                                                    <tr class="totalArea">
                                                        <td colspan="2" class="Row">Amount Due</td>
                                                    </tr>
                                                    <tr>
                                                        <td>FERS Deposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Interest *</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>FERS Redeposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Interest *</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>CSRS Post 3/91 Redeposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Interest *</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>CSRS Post82/Pre91 Redeposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Interest *</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>CSRS Pre 10/82 Redeposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Interest *</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>CSRS Post 10/82 Deposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Interest *</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>CSRS Pre 10/82 Deposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Interest *</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>FERS Peace Corps</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Interest *</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>CSRS Peace Corps</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Interest *</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    
                                                    <tr class="totalArea">
                                                        <td>Less Payments</td>
                                                        <td class="aRight dollar less-payments">$0.00</td>
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
                                                        <td class="aRight dollar balance-due">$0.00</td>
                                                    </tr>
                                                    <tr class="heightRow">
                                                        <td>FERS Deposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr class="heightRow">
                                                        <td>FERS Redeposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr class="heightRow">
                                                        <td>CSRS Post 3/91 Redeposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr class="heightRow">
                                                        <td>CSRS Post82/Pre91 Redeposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr class="heightRow">
                                                        <td>CSRS Pre 10/82 Redeposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr class="heightRow">
                                                        <td>CSRS Post 10/82 Deposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr class="heightRow">
                                                        <td>CSRS Pre 10/82 Deposit</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr class="heightRow">
                                                        <td>FERS Peace Corps</td>
                                                        <td class="aRight dollar">$0.00</td>
                                                    </tr>
                                                    <tr class="heightRow">
                                                        <td>CSRS Peace Corps</td>
                                                        <td class="aRight dollar">$0.00</td>
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
                                                        <td class="aRight dollar billing-total">$0.0</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="billingRightPart fLeft">
                                            <div class="initial-billingTblWrapper">
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
                                                    <tbody class="calculationResult-tbody">
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="paymentExplanation">
                                                <p><strong>Explanation of How Payments Are Applied.</strong></p>
                                                <p>The payments you make are applied to -</p>
                                                <ol class="numberList">
                                                    <li>Pay for FERS Deposit or Redeposit, if any</li>
                                                    <li>Pay for CSRS Post-03/91 redeposit, if any</li>
                                                    <li>Pay for CSRS Post-09/82 redeposit, if any</li>
                                                    <li>Pay for CSRS Pre-03/91 redeposit, if any</li>
                                                    <li>Pay for CSRS Pre-10/82 redeposit, if any</li>
                                                    <li>Pay for CSRS Post-09/82 deposit, if any</li>
                                                    <li>Pay for CSRS Pre-10/82 deposit, if any</li>
                                                    <li>Pay for CERS Peace Corps deposit, if any</li>
                                                    <li>Pay for FERS Peace Corps deposit, if any</li>
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
                                        <div class="fLeft leftNote">RI 3623<br/>Previous editions are<br/>not usable</div>
                                        <div class="fRight aRight rightNote">RI 36-23<br/>Revised June 2006</div>
                                    </div>
                                </div>
                            </div>
                            <div class="printPopupBtnWrapper">
                                <div class="fLeft">
                                    <span>Download as : </span>
                                    <a href="javascript:;" class="pdfLink"></a>
                                    <a href="javascript:;" class="rtfLink"></a>
                                    <a href="javascript:;" class="docLink"></a>
                                    <a href="javascript:;" class="printLink jsDoPrintReport">Print</a>
                                </div>
                                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
                            </div>
                        </div></div></div>
                <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .initStatementPopup -->

    <div class="popup chargeCardPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Charge Card</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
                <div class="printPreviewArea chargeCardPrintPreview group">
                    <div class="chargeCardTitle">
                        <div class="leftCol">
                            <p class="address">EJEJXXXX, JBGXXXX S.</p>
                            <p>
                                <span class="date">1/1/1940</span>
                                <span class="phone">123-45-6789</span>
                            </p>
                            <p class="csdNo">CSD#: 9164949</p>
                        </div>
                        <div class="rightCol">
                            <h1>Service Credit</h1>
                            <h2>Initial Search</h2>
                            <h2>CSRS</h2>
                        </div>
                    </div>
                    <div class="chargeCardBody">
                        <div class="leftSideInfo">
                            <div>EJEJXXXX, JBGXXXX S.<br />1/1/1940</div>
                            <div>CSD#: 9164949<br />123-45-6789</div>
                        </div>
                        <div class="chargeCardContent group">
                            <p class="summaryTxt">Record Attached<br />Record Charged<br />NR - No Record</p>
                            <ul class="boxes fLeft">
                                <li></li>
                                <li></li>
                                <li></li>
                            </ul>
                            <div class="comments fLeft">
                                <h3>Comments</h3>
                                <p></p>
                                <p></p>
                                <p></p>
                                <p></p>
                                <p></p>
                                <p></p>
                                <p></p>
                            </div>
                        </div>
                    </div>
                    <div class="chargeCardFooter">For Official Use Only</div>
                </div>
            </div>
            <div class="printPopupBtnWrapper">
                <div class="fLeft">
                    <span>Download as : </span>
                    <a href="javascript:;" class="pdfLink"></a>
                    <a href="javascript:;" class="rtfLink"></a>
                    <a href="javascript:;" class="docLink"></a>
                    <a href="javascript:;" class="printLink jsDoPrintChargeCard">Print</a>
                </div>
                <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .chargeCardPopup -->

    <%@ include file="include/notificationsPopup.jsp"%>
</body>
</html>