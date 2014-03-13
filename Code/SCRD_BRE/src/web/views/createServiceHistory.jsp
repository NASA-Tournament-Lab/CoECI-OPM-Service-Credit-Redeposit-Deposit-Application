<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The create account page, used to create service history.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
  <head>
    <base href="<%=basePath%>"/>
    <!-- title -->
    <title>OPM - Create Service History</title>
    <link rel="shortcut icon" href="<c:url value="/i/logo-opm-gray.png"/>" type="image/x-icon" />

    <!-- metatags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- External CSS -->
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.3.custom.css"/>" media="all" />
    <link rel="stylesheet" href="<c:url value="/css/screen.css"/>" media="all" />

    <!-- JS lib/plugins-->
    <script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
    <script type='text/javascript' src='<c:url value="/js/jquery-ui-1.10.3.custom.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery.formatCurrency-1.4.0.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/merge-sort.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/sortable.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery.zclip.js"/>'></script>

    <!-- external main js -->
    <script type="text/javascript" src="<c:url value="/js/script.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/createAccount.js"/>"></script>

    <!-- css file for IE7 -->
 <!--[if IE 7]><link rel="stylesheet" href="<c:url value="/css/ie7.css"/>" media="all" type="text/css" /><![endif]-->
    <script></script>

  </head>
  <body>
    <input id="accountId" type="hidden" value="${sessionScope.createdAccountId}"/>
    <div id="wrapper">
      <%@ include file="include/header.jsp"%>

      <div id="content">
        <p class="breadcurmb"><span>Create New Account</span></p>
        <div class="pageTitleArea">
          <h2 class="pageTitle">Create New Account</h2>
          <div class="setHomeLink">
            <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
            <a href="javascript:;" class="jsShowTips">?</a>
          </div>
        </div>

        <div class="progressStepsBar">
          <ul>
            <li class="firstStep finishedStep justFinishedStep"><a href="<c:url value="/account/viewCreate?step=createAccount"/>">Basic Information</a></li>
            <li class="step2 currentStep">Service History</li>
            <li class="step3"><a href="<c:url value="/account/viewCreate?step=createAccountNote"/>">Notes</a></li>
            <li class="lastStep"><a href="<c:url value="/account/viewCreate?step=createAccountFinish"/>">Finish</a></li>
          </ul>
          <div class="corner cornerTl"></div>
          <div class="corner cornerTr"></div>
        </div>
        <div class="serviceHistoryPanel tabsArea roundedGrayPanel">
          
          <div class="tabsBlock depositTab">
            <div class="versionBar">
              <label for="calcVersionDepS"><span class="fLeft bold grayTxt">Calculation Version</span></label>
              <select class="fLeft" id="calcVersionDepS">

              </select>
              <a href="javascript:;" class="priBtn jsNewVersionBtn fLeft"><span><span>New Version</span></span></a>
              <input name="copyCurrent" id="copyCurrent" type="checkbox" value="copyCurrent" class="fLeft checkboxInput"/> <label for="copyCurrent" class="fLeft">Copy Current</label>
              <a href="javascript:;" class="priBtn jsDelVersionBtn fRight"><span><span>Delete Current</span></span></a>
            </div>
            <div class="scrollTblArea">
              <table cellpadding="0" cellspacing="0" border="0" class="stdTbl entriesTbl sortable periodTable" id="depTbl" width="100%">
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
                    <th class="unsortable firstCol">&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th class="defaultSortCol defaultSortDown asDate">Begin Date</th>
                    <th class="asDate">End Date</th>
                    <th class="asString">Retire Type</th>
                    <th class="asString">Period Type</th>
                    <th class="asString">Appointment Type</th>
                    <th class="asString">Service Type</th>
                    <th class="asNumeric">Amount</th>
                    <th class="asString">Pay Type</th>
                    <th class="asNumeric">Withholding Rate</th>
                    <th class="asString">Interest Waved</th>
                    <th class="lastCol asDate">Interest Begins</th>
                  </tr>
                </thead>
                <tfoot class="isHidden">
                  <tr class="entriesEditRow">
                    <td class="blankCell firstCol">&nbsp;</td>
                    <td><input name="bDate" type="text" class="text bDate" value="01/01/2001"/></td>
                    <td><input name="eDate" type="text" class="text eDate" value="01/12/2012"/></td>
                    <td><select class="rType" typeName="retirementTypes">
                      </select></td>
                    <td><select typeName="periodTypes" class="pType">
                      </select></td>
                    <td><select class="aType" typeName="appointmentTypes">
                      </select></td>
                    <td><select class="sType" typeName="serviceTypes" >
                      </select></td>
                    <td><input name="amount" type="text" class="text amount" value="$ 20,000.00"/></td>
                    <td><select class="payType" typeName="payTypes">
              </select></td>
      <td>
        <select>
          <option selected></option>
          <option>7</option>
          <option>1.3</option>
          <option>5.7</option>
        </select>
      </td>
      <td>
        <select>
          <option selected></option>
          <option>YES</option>
          <option>NO</option>
        </select>
      </td>
      <td class="lastCol">
        <input name="iDate" type="text" class="text iDate" value="" title="Enter the Interest Begin Date"/>
      </td>
                  </tr>
                </tfoot>
              </table>
            </div>
            <div class="funtionArea">
              <p>
                <a href="javascript:;" class="priBtn jsValidateEntries fLeft"><span><span>Validate Entries</span></span></a>
                <a href="javascript:;" class="priBtn jsRunCalculation fLeft"><span><span>Run Calculation</span></span></a>
                <a href="javascript:;" class="priBtn jsSaveCalculation fLeft"><span><span>Save Calculation</span></span></a>
                <a href="javascript:;" class="priBtn jsCancelFunction fLeft isHidden"><span><span>Cancel</span></span></a>
                <a href="javascript:;" class="priBtn jsTriggerBill fLeft isHidden"><span><span>Trigger Bill</span></span></a>
                <a href="javascript:;" class="priBtn jsRefreshGrid fRight"><span><span>Refresh Grid</span></span></a>
                <a href="javascript:;" class="priBtn jsShowCalculation isHidden fRight"><span><span>Show Calculation</span></span></a>
              </p>
              <p>
                <input name="applyReal" id="applyReal" type="checkbox" value="applyReal" /> <label for="applyReal">Apply Real Payments</label>
              </p>
            </div>
            <div class="validationStatusBar">
              <span class="fieldLabel fLeft">Validation Status :</span>
              <span class="fieldVal statusVal fLeft">Unknown</span>
              <span class="fieldLabel fLeft">Calculation Results :</span>
              <span class="fieldVal resultsVal fLeft">Unknown</span>
              <a href="javascript:;" class="priBtn priBtnDisabled jsExpandCalculation fRight"><span><span>Expand Calculation</span></span></a>
              <span>
                <span class="fieldLabel">Calculate as of :</span>
                <span class="fieldVal dateVal">
                    <input name="depositInterestCalculatedToDate" type="text" class="text interestCalculatedToDate datePicker"/>
                </span>
              </span>
            </div>
            <div class="scrollTblArea">
              <table cellpadding="0" cellspacing="0" border="0" class="stdTbl validateResultTbl sortable" id="depTbl" width="113%">
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
                </colgroup>
                <thead>
                  <tr>
                    <th class="unsortable firstCol">&nbsp;</th>
                    <th class="defaultSortCol defaultSortDown asDate">Begin Date</th>
                    <th class="asDate">End Date</th>
                    <th class="asDate">Mid Point</th>
                    <th class="asDate">Effective Date</th>
                    <th class="asString">Period Type</th>
                    <th class="asNumeric">Deduction Amount</th>
                    <th class="asNumeric">Total Interest</th>
                    <th class="asNumeric">Payments Applied</th>
                    <th class="lastCol asNumeric">Balance</th>
                  </tr>
                </thead>
                <tfoot class="isHidden">
                </tfoot>
                <tbody>
                  <tr>
                    <td class="blankCell firstCol">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td class="lastCol">&nbsp;</td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="noValidMask2"></div>
            <div class="chartCalArea">
              <h3 class="panelHeader jsTogglePanel panelExpanded">Chart Calculation</h3>
              <div class="panelBody">
                <div class="chartCalAreaBox1">
                  <label for="chartValueDepL"><p class="boxTitle">Chart: </p></label>
                  <select class="chartValueDep" id="chartValueDepL">
                    <option value="HOUR_2000" selected="selected">2000 Hour Chart - for Postal employees 07/25/85 to present</option>
                    <option value="HOUR_2008">2008 Hour Chart - for Postal employees 7/20/71 to 07/24/85</option>
                    <option value="HOUR_2016">2016 Hour Chart - for Postal employees 12/3/55 - 7/19/71</option>
                    <option value="HOUR_2024">2024 Hour Chart - for Postal employees 7/1/45 - 12/2/55</option>
                    <option value="HOUR_2080">2080 Hour Chart - for Postal employees 7/1/45 - 12/2/55</option>
                    <option value="HOUR_2087">2087 Hour Chart - effective on or after 3/1/86</option>
                    <option value="DAY_260">260 Day Chart - effective 7/1/45</option>
                  </select>
                </div>
                <div class="chartCalAreaBox2">
                  <label for="hoursInputDepL"><p class="boxTitle">Enter Hours: </p></label>
                  <input name="hoursInput" id="hoursInputDepL" type="text" class="text hourField hourFieldDep"/><a href="javascript:;" class="priBtn jsSubmitHour"><span><span>Submit</span></span></a>
                </div>
                <div class="chartCalAreaBox3">End Date : <strong></strong></div>
                <div class="chartCalAreaBox4">
                  <p class="tableTitle">Redeposit</p>
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl" width="100%">
                    <thead>
                      <tr>
                        <th>&nbsp;</th>
                        <th>Deposit</th>
                        <th>Interest</th>
                        <th>Total</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td class="rowTitle">FERS Redeposit</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">CSRS Post 3/91 Redeposit</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">CSRS Post82/Pre91 Redeposit</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">CSRS Pre 10/82 Redeposit</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="chartCalAreaBox5">
                  <p class="tableTitle">Deposit</p>
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl" width="100%">
                    <thead>
                      <tr>
                        <th>&nbsp;</th>
                        <th>Deposit</th>
                        <th>Interest</th>
                        <th>Total</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td class="rowTitle">FERS Deposit</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">CSRS Post 10/82 Deposit</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">CSRS Pre 10/82 Deposit</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">FERS Peace Corps</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">CSRS Peace Corps</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="chartCalAreaBox6">
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl" width="100%">
                    <colgroup>
                      <col class="col1"/>
                      <col class="col2"/>
                    </colgroup>
                    <tbody>
                      <tr>
                        <td class="rowTitle">Total Initial Payment</td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Total Initial Interest</td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Total Payments Applied</td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Total Balance</td>
                        <td><strong class="dollar"></strong></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="clear showSampleBtnWrapper">
                  <a href="javascript:;" class="priBtn jsShowSample priBtnDisabled"><span><span>Show Sample Initial</span></span></a>
                </div>
              </div>
            </div>
            <div class="dateCalculator fLeft">
              <p class="blocktitle">Date Calculator</p>
              <div class="blockInner">
                <div class="fieldWrapper">
                  <label for="sDateL"><p class="fieldLabel">Starting Date</p></label>
                  <input name="sDate" id="sDateL" type="text" class="text sDateLC" value="01/03/2014" title="Choose the start date."/>
                </div>
                <div class="fieldWrapper">
                  <label for="eDateL"><p class="fieldLabel">Ending Date</p></label>
                  <input name="eDate" id="eDateL" type="text" class="text eDateLC" value="02/20/2014" title="Choose the end date."/>
                </div>
                <div class="btnWrapper">
                  <a href="javascript:;" class="priBtn jsCalBtn"><span><span>Calculate</span></span></a>
                </div>
                <div class="errorMask isHidden">
                  <span class="errorMark"></span><span class="errorMsg">Start Date is invalid</span><br/><a href="javascript:;" class="jsRetryCalc">Click here to retry</a>
                </div>
              </div>
            </div>
            <div class="calReuslts fLeft">
              <p class="blocktitle">Calculation Results</p>
              <div class="blockInner">
                <table border="0" cellpadding="0" cellspacing="0" id="calResultsTbl" width="100%">
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
                      <th>Years:</th>
                      <th>Months:</th>
                      <th>Days:</th>
                      <th>Decimal Years:</th>
                      <th>Total Days:</th>
                      <th>Midpoint:</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>-</td>
                      <td>-</td>
                      <td>-</td>
                      <td>-</td>
                      <td>-</td>
                      <td>-</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="clear"></div>
          </div>
          <!-- .depositTab -->


          <div class="corner cornerBl"></div>
          <div class="corner cornerBr"></div>
        </div>
        <div class="stepsBtnWrapper">
          <a href="javascript:;" class="priBtn fLeft jsCancelCreateAccount"><span><span>Cancel</span></span></a>
          <a href="<c:url value="/account/viewCreate?step=createAccountNote"/>" class="priBtn fRight nextBtn jsStep2Next"><span><span>Next<i class="nextArrow"></i></span></span></a>
          <a href="<c:url value="/account/viewCreate?step=createAccount"/>" class="priBtn fRight"><span><span>Previous</span></span></a>
        </div>



      </div>
      <!-- #content --> 

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


    <div class="popup confirmDeletionPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Confirm Deletion</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Are your sure you want to delete all Service History records in this version?</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsConfirmDeletion"><span><span>OK</span></span></a>
              <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .confirmDeletionPopup -->

    <div class="popup sampleInitialPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Sample Initial Calculation Report</h4>
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
    <!-- .printPaymentReceiptPopup -->
    
    <div class="popup noEndDatePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Enter Hours</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg endDateError"></p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>

    <div class="popup newVersionImpossiblePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Create New Version</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg newVersionImpossibleError"></p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>

    <div class="popup confirmDatePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Refund / Lump SUm</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <div class="popupPadding">
              <p>Please confirm the date that the actual payment was issued (not the service period end date).<br /><br /><input type="text" class="text actualPayment2" value="10/22/1981" /></p>
            </div>
            <div class="popupBtnWrapper">
              <a class="priBtn jsShowPeriodErrorPopup"><span><span>OK</span></span></a>
              <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .confirmDatePopup -->

    <div class="popup periodErrorPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Service Period Errors and Warnings</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="popupPadding">
              <p><img src="<c:url value="/i/icon-error.png"/>" alt="error" width="22" height="22" class="vMiddle" />
                The effective date for the refund is 10/22/1981</p>
            </div>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .periodErrorPopup -->

    <div class="isHidden">
      <table class="isHidden" id="versionTableTemplate">
        <tbody>
          <tr>
            <td class="blankCell firstCol">&nbsp;</td>
            <td></td>
            <td></td>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td class="dollar">20000.00</td>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <td></td>
            <td class="lastCol"></td>
          </tr>
        </tbody>
      </table>
      <table class="isHidden" id="versionEditTemplate">
        <tbody>
          <tr class="entriesEditRow">
            <td class="blankCell firstCol">&nbsp;</td>
            <td><input name="bDate" type="text" class="text bDate" value="01/01/2001"/></td>
            <td><input name="eDate" type="text" class="text eDate" value="01/12/2012"/></td>
            <td><select class="rType" typeName="retirementTypes">
              </select></td>
            <td><select typeName="periodTypes" class="pType">
              </select></td>
            <td><select class="aType" typeName="appointmentTypes">
              </select></td>
            <td><select class="sType" typeName="serviceTypes" >
              </select></td>
            <td><input name="amount" type="text" class="text amount" value="$ 20,000.00"/></td>
            <td><select class="payType" typeName="payTypes">
              </select></td>
      <td>
        <select>
          <option selected></option>
          <option>7</option>
          <option>1.3</option>
          <option>5.7</option>
        </select>
      </td>
      <td>
        <select>
          <option selected></option>
          <option>YES</option>
          <option>NO</option>
        </select>
      </td>
      <td class="lastCol">
        <input name="iDate" type="text" class="text iDate" value="" title="Enter the Interest Begin Date"/>
      </td>
          </tr>
        </tbody>
      </table>

      <table class="isHidden" id="versionNewTemplate">
        <tbody>
          <tr class="even2 newEntryRow unsortable">
            <td class="blankCell firstCol">&nbsp;</td>
            <td></td>
            <td></td>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td class="dollar"></td>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <td></td>
            <td class="lastCol"></td>
          </tr>
        </tbody>
      </table>

      <table class="isHidden" id="resultItemTemplate">
        <tbody>
          <tr>
            <td class="blankCell firstCol">&nbsp;</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td class="dollar"></td>
            <td class="dollar"></td>
            <td class="dollar"></td>
            <td class="lastCol dollar"></td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="isHidden depositVersionTbodyArea">

    </div>

  </body>
</html>