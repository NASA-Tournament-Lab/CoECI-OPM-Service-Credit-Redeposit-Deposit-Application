<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The account detail page.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <!-- title -->
    <title>OPM - Account Detail</title>
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
    <script type='text/javascript' src='<c:url value="/js/sortable.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery.zclip.js"/>'></script>

    <!-- external main js -->
    <script type="text/javascript" src="<c:url value="/js/script.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/accountDetail.js"/>"></script>

    <!-- css file for IE7 -->
 <!--[if IE 7]><link rel="stylesheet" href="<c:url value="/css/ie7.css"/>" media="all" type="text/css" /><![endif]-->
    <script></script>

  </head>
  <body>
    <input id="accountId" type="hidden" value="${a.id}"/>
    <div id="wrapper">
      <%@ include file="include/header.jsp"%>

      <div id="content">
        <p class="breadcurmb"><a href="<c:url value="/faces/view"/>">Account Search Results</a><span>Account Details</span></p>
        <div class="pageTitleArea">
          <h2 class="pageTitle">Account Details</h2>
          <div class="setHomeLink">
            <div class="accountBtns">
              <a href="javascript:;" class="priBtn fRight jsRefreshAccountDetails"><span><span>Refresh</span></span></a>
              <a href="javascript:;" class="priBtn fRight isHidden jsCloseAccount"><span><span>Close</span></span></a>
              <a href="javascript:;" class="priBtn fRight isHidden jsReopenAccount"><span><span>Reopen</span></span></a>
            </div>
            <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab as my home page</label>
            <a href="javascript:;" class="jsShowTips">?</a>
          </div>
        </div>

        <div class="roundedGrayPanel accountSummaryPanel">
          <h3 class="panelHeader jsTogglePanel panelExpanded">Account Summary</h3>
          <div class="panelBody">
            <div class="column column1">
              <h4 class="name"></h4>
              <div class="fieldRow">
                <p class="fieldTitle">Claim #<span>:</span></p>
                <p class="fieldVal csd"></p>
              </div>
              <div class="fieldRow">
                <p class="fieldTitle">DOB<span>:</span></p>
                <p class="fieldVal birthDate"></p>
              </div>
              <div class="fieldRow">
                <p class="fieldTitle">SSN<span>:</span></p>
                <p class="fieldVal ssn"></p>
              </div>
              <div class="fieldRow">
                <p class="fieldTitle">Plan Type<span>:</span></p>
                <p class="fieldVal formType"></p>
              </div>
            </div>
            <div class="column column2">
              <h4>Status</h4>
              <div class="fieldRow">
                <p class="fieldTitle status"></p>
              </div>
              <div class="fieldRow">
                <p class="fieldTitle">Last Action<span>:</span></p>
                <p class="fieldVal lastAction"></p>
              </div>
              <div class="fieldRow">
                <p class="fieldTitle">Action Date<span>:</span></p>
                <p class="fieldVal lastActionDate"></p>
              </div>
            </div>
            <div class="column column3">
              <h4 class="paymentInfo"></h4>
              <div class="fieldRow">
                <p class="fieldTitle">Total<span>:</span></p>
                <p class="fieldVal total dollar"></p>
              </div>
              <div class="fieldRow">
                <p class="fieldTitle">Balance<span>:</span></p>
                <p class="fieldVal balance dollar"></p>
              </div>
            </div>
            <div class="column column4">
              <div class="fieldRow">
                <p class="fieldTitle">Grace Period<span>:</span></p>
                <p class="fieldVal grace"></p>
              </div>
            </div>
          </div>
          <div class="corner cornerTl"></div>
          <div class="corner cornerTr"></div>
        </div>
        <!-- .accountSummaryPanel -->

        <div class="roundedGrayPanel accountNotesPanel">
          <h3 class="panelHeader jsTogglePanel panelExpanded">Account Notes</h3>
          <div class="panelBtnWrapper">
            <a href="javascript:;" class="priBtn jsAddNote"><span><span>Add</span></span></a>
            <a href="javascript:;" class="priBtn jsEditNote"><span><span>Edit</span></span></a>
            <a href="javascript:;" class="priBtn jsDelNote"><span><span>Delete</span></span></a>
          </div>
          <div class="panelBody">
            <table cellpadding="0" cellspacing="0" border="0" class="stdTbl checkGroup" id="accountNoteTbl" width="100%">
              <colgroup>
                <col class="checkCol"/>
                <col class="col1"/>
                <col class="col2"/>
                <col class="col3"/>
              </colgroup>
              <thead>
                <tr>
                  <th class="checkCell"><input name="checkAllRow" type="checkbox" value="checkAll" class="checkAllRow"/></th>
                  <th>Date</th>
                  <th>Account Notes</th>
                  <th>Account Name</th>
                </tr>
              </thead>
              <tbody class="note-tbody">
                <c:forEach items="${notes}" var="note" varStatus="status">
                  <tr>
                    <input type="hidden" value="${note.id}"/>
                    <td class="checkCell"><input name="checkRow" type="checkbox" value="checkRow${status.index + 1}" class="checkRow"/></td>
                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${note.date}"/></td>
                    <td class="aLeft">${note.text}</td>
                    <td>${note.writer}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <div class="corner cornerTl"></div>
          <div class="corner cornerTr"></div>
        </div>
        <!-- .accountNotesPanel -->

        <div class="tabsArea accountDetailsTabsArea">
          <div class="accountDetailsTabs tabsBar">
            <ul>
              <li><a href="javascript:;" class="current" id="employeeTab">Employee</a></li>
              <li><a href="javascript:;" id="billingSummaryTab">Billing Summary</a></li>
              <li><a href="javascript:;" id="serviceHistoryTab">Service History</a></li>
            </ul>
          </div>
          <div class="tabsBlock employeeTab">
            <div class="accountBasicInfoPanel">
              <div class="halfCol">
                <div class="titleRow">
                  <h3>Basic Information</h3>
                  <span class="claimNumber">Claim Number: </span>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Status :</p>
                  <span class="fieldVal status"></span>
                  <i name="check1" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="titleRow mb3">
                  <h3>Account Holder</h3>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Last Name :</p>
                  <span class="fieldVal lastName"></span>
                  <i name="check2" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">First Name :</p>
                  <span class="fieldVal firstName"></span>
                  <i name="check3" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Middle Name :</p>
                  <span class="fieldVal middleInitial"></span>
                  <i name="check4" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Suffix :</p>
                  <span class="fieldVal suffix"></span>
                  <i name="check5" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Birthdate :</p>
                  <span class="fieldVal birthDate"></span>
                  <i name="check6" class="rowCheckStatus rowCheckFailed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">SSN :</p>
                  <span class="fieldVal ssn"></span>
                  <i name="check7" class="rowCheckStatus rowCheckFailed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Telephone :</p>
                  <span class="fieldVal telephone"></span>
                  <i name="check8" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Email :</p>
                  <span class="fieldVal email"></span>
                  <i name="check9" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Title :</p>
                  <span class="fieldVal title"></span>
                  <i name="check10" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Agency / Dept. Code :</p>
                  <span class="fieldVal departmentCode"></span>
                  <i name="check11" class="rowCheckStatus rowCheckPassed"></i>
                </div>
              </div>
              <div class="halfCol rightHalfCol">
                <div class="titleRow">
                  <h3>Data Check Status: <span class="jsCheckStatus">Unchecked</span></h3>
                  <span>Checked By: <span class="checker">-</span></span>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Form Submitted :</p>
                  <span class="fieldVal formType"></span>
                  <i name="check12" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="titleRow mb3">
                  <h3>Address</h3>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #1 :</p>
                  <span class="fieldVal street1"></span>
                  <i name="check13" class="rowCheckStatus rowCheckFailed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #2 :</p>
                  <span class="fieldVal street2"></span>
                  <i name="check14" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #3 :</p>
                  <span class="fieldVal street3"></span>
                  <i name="check15" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #4 :</p>
                  <span class="fieldVal street4"></span>
                  <i name="check16" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #5 :</p>
                  <span class="fieldVal street5"></span>
                  <i name="check17" class="rowCheckStatus rowCheckFailed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">City :</p>
                  <span class="fieldVal city"></span>
                  <i name="check18" class="rowCheckStatus rowCheckFailed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">State/Province/Region :</p>
                  <span class="fieldVal state"></span>
                  <i name="check19" class="rowCheckStatus rowCheckFailed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">ZIP/Postal Code :</p>
                  <span class="fieldVal zipCode"></span>
                  <i name="check20" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Country :</p>
                  <span class="fieldVal country"></span>
                  <i name="check21" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">GEO code :</p>
                  <span class="fieldVal geoCode"></span>
                  <i name="check22" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="titleRow locationTitleRow ">
                  <h3>Location of Employment</h3>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">City :</p>
                  <span class="fieldVal cityOfEmployment"></span>
                  <i name="check23" class="rowCheckStatus rowCheckPassed"></i>
                </div>
                <div class="halfRowField mb3">
                  <p class="fieldLabel">State :</p>
                  <span class="fieldVal stateOfEmployment"></span>
                  <i name="check24" class="rowCheckStatus rowCheckPassed"></i>
                </div>
              </div>
              <div class="clear tabBtnsWrapper">
                <a href="javascript:;" class="priBtn fRight jsEditBasicInfo"><span><span>Edit Basic Info</span></span></a>
              </div>
            </div>
            <!-- .accountBasicInfoPanel -->

            <div class="accountBasicInfoPanelEdit isHidden">
              <div class="halfCol">
                <div class="titleRow">
                  <h3>Basic Information</h3>
                  <span class="claimNumber">Claim Number: </span>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Status <span class="reqMark">*</span>:</p>
                  <select name="status" class="accountStatus" typeName="accountStatuses">

                  </select>
                </div>
                <div class="titleRow mb3">
                  <h3>Account Holder</h3>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Last Name <span class="reqMark">*</span>  :</p>
                  <input name="lastName" type="text" class="text holder" value="" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">First Name <span class="reqMark">*</span> :</p>
                  <input name="firstName" type="text" class="text holder" value="" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Middle Name :</p>
                  <input name="middleInitial" type="text" class="text midLength holder" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Suffix :</p>
                  <select name="suffix" class="midLength holder"  typeName="suffixes">

                  </select>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Birthdate <span class="reqMark">*</span> :</p>
                  <input name="birthDate" type="text" class="text midLength datePicker holder"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">SSN <span class="reqMark">*</span> :</p>
                  <input name="ssn" type="text" class="text holder" maxlength="128"/>
                </div>
                <div class="halfRowField phoneField">
                  <p class="fieldLabel">Telephone :</p>
                  <input name="phoneInput" type="text" class="text phoneInput phone1 phone" maxlength="20" onkeypress='return  validatePhoneInput(event);'/><span class="sep">-</span><input name="phoneExt" type="text" class="text phoneExt phone2 phone" onkeypress='return  validatePhoneInput(event);'/>
                  <span class="extNote">Ext</span>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Email :</p>
                  <input name="email" type="text" class="text holder" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Title :</p>
                  <input name="title" type="text" class="text holder" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Agency / Dept. Code :</p>
                  <input name="departmentCode" type="text" class="text midLength holder" maxlength="128"/>
                </div>
              </div>
              <div class="halfCol rightHalfCol">
                <div class="titleRow">
                  <h3>Data Check Status: <span class="jsCheckStatus">Unchecked</span></h3>
                  <span>Checked By: <span class="checker">-</span></span>
                </div>
                <div class="halfRowField radioRow formTypeDiv">
                  <p class="fieldLabel">Form submitted:</p>
                  <input name="formType" type="radio" value="2" id="formCsrs1" />
                  <label class="radioLabel" for="formCsrs1">2803 (CSRS)</label>
                  <input name="formType" type="radio" id="formFers1" value="1" checked="checked"/>
                  <label class="radioLabel" for="formFers1">3108 (FERS)</label>
                </div>
                <div class="titleRow mb3">
                  <h3>Address</h3>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #1 <span class="reqMark">*</span> :</p>
                  <input name="street1" type="text" class="text address" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #2 :</p>
                  <input name="street2" type="text" class="text address" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #3 :</p>
                  <input name="street3" type="text" class="text address" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #4 :</p>
                  <input name="street4" type="text" class="text address" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Line #5 :</p>
                  <input name="street5" type="text" class="text address" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">City <span class="reqMark">*</span> :</p>
                  <input name="city" type="text" class="text midLength address" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">State/Province/Region <span class="reqMark">*</span> :</p>
                  <select name="state" class="midLength address" typeName="states">

                  </select>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">ZIP/Postal Code <span class="reqMark">*</span> :</p>
                  <input name="zipCode" type="text" class="text midLength address" maxlength="128"/>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">Country :</p>
                  <select name="country" class="midLength2 address" typeName="countries">

                  </select>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">GEO code :</p>
                  <input name="geoCode" type="text" class="text midLength holder" maxlength="128"/>
                </div>
                <div class="titleRow locationTitleRow">
                  <h3>Location of Employment</h3>
                </div>
                <div class="halfRowField">
                  <p class="fieldLabel">City :</p>
                  <input name="cityOfEmployment" type="text" class="text midLength holder" maxlength="128" />
                </div>
                <div class="halfRowField  mb3">
                  <p class="fieldLabel">State :</p>
                  <select name="stateOfEmployment" class="midLength holder" typeName="states">

                  </select>
                </div>
              </div>
              <div class="clear tabBtnsWrapper">
                <a href="javascript:;" class="priBtn fRight editEmployeeBtn jsCancelEditBasicInfo"><span><span>Cancel</span></span></a>
                <a href="javascript:;" class="priBtn fRight editEmployeeBtn jsSaveEditBasicInfo"><span><span>Save</span></span></a>
              </div>
            </div>
            <!-- .accountBasicInfoPanelEdit -->

          </div>
          <div class="tabsBlock  billingSummaryTab isHidden">

            <div class="billingSummaryPanel">
              <div class="titleRow">
                <h3>Billing Summary</h3>
              </div>
              <div class="billingFields">
                <div class="billingField">
                  <p class="fieldLabel">Computed Date Code :</p>
                  <p class="fieldVal"></p>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">1st Billing Date :</p>
                  <p class="fieldVal"></p>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">Transaction :</p>
                  <p class="fieldVal"></p>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">Last Deposit Date :</p>
                  <p class="fieldVal"></p>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">Last Interest Calc :</p>
                  <p class="fieldVal"></p>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">Last Transaction Date :</p>
                  <p class="fieldVal"></p>
                </div>
              </div>
              <div class="tblWrapper">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="stdTbl billingTbl">
                  <colgroup>
                    <col class="titleCol"/>
                    <col class="col1"/>
                    <col class="col2"/>
                    <col class="col3"/>
                    <col class="col3"/>
                    <col class="col3"/>
                  </colgroup>
                  <thead>
                    <tr>
                      <th>&nbsp;</th>
                      <th>Initial Billing</th>
                      <th>Additional Interest</th>
                      <th>Total Payments</th>
                      <th>Balance</th>
                      <th>Payment Order</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr class="totalRow">
                      <td class="titleCol">Account Totals</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td>
                        <input name="achStop" id="achStop" type="checkbox" disabled="disabled" value="achStop" class="checkboxInput checkboxDisabled"/>
                        <label for="achStop" class="checkboxDisabled">Stop ACH Payments</label>
                      </td>
                    </tr>
                  </tfoot>
                  <tbody>
                    <tr>
                      <input type="hidden" value="FERS_DEPOSIT"/>
                      <td class="titleCol">FERS Deposit</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td></td>
                    </tr>
                    <tr>
                      <input type="hidden" value="FERS_REDEPOSIT"/>
                      <td class="titleCol">FERS Redeposit</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td></td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_POST_3_91_REDEPOSIT"/>
                      <td class="titleCol">CSRS Post 3/91 Redeposit</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td></td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_POST_82_PRE_91_REDEPOSIT"/>
                      <td class="titleCol">CSRS Post82/Pre91 Redeposit</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td></td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_PRE_10_82_REDEPOSIT"/>
                      <td class="titleCol">CSRS Pre 10/82 Redeposit</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td></td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_POST_10_82_DEPOSIT"/>
                      <td class="titleCol">CSRS Post 10/82 Deposit</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td></td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_PRE_10_82_DEPOSIT"/>
                      <td class="titleCol">CSRS Pre 10/82 Deposit</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td></td>
                    </tr>
                    <tr>
                      <input type="hidden" value="FERS_PEACE_CORPS"/>
                      <td class="titleCol">FERS Peace Corps</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td></td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_PEACE_CORPS"/>
                      <td class="titleCol">CSRS Peace Corps</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td></td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="clear tabBtnsWrapper">
                <a href="javascript:;" class="priBtn fRight jsUpdateInterest"><span><span>Update Interest</span></span></a>
                <a href="javascript:;" class="priBtn fRight isHidden jsLastUpdate"><span><span>Last update</span></span></a>
                <a href="javascript:;" class="priBtn fRight jsEditBillingInfo"><span><span>Edit Billing Info</span></span></a>
              </div>
            </div>
            <!-- .billingSummaryPanel -->

            <div class="billingSummaryPanelEdit isHidden">
              <div class="titleRow">
                <h3>Billing Summary</h3>
              </div>
              <div class="billingFields">
                <div class="billingField">
                  <p class="fieldLabel">Computed Date Code :</p>
                  <input name="cDate" type="text" class="text datePicker" value=""/>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">1st Billing Date :</p>
                  <input name="billingDate" type="text" class="text datePicker" value=""/>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">Transaction :</p>
                  <p class="fieldVal"></p>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">Last Deposit Date :</p>
                  <p class="fieldVal"></p>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">Last Interest Calc :</p>
                  <input name="lastCalcDate" type="text" class="text datePicker" value=""/>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">Last Transaction Date :</p>
                  <p class="fieldVal"></p>
                </div>
              </div>
              <div class="tblWrapper">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="stdTbl billingTbl">
                  <colgroup>
                    <col class="titleCol"/>
                    <col class="col1"/>
                    <col class="col2"/>
                    <col class="col3"/>
                    <col class="col3"/>
                    <col class="col3"/>
                  </colgroup>
                  <thead>
                    <tr>
                      <th>&nbsp;</th>
                      <th>Initial Billing</th>
                      <th>Additional Interest</th>
                      <th>Total Payments</th>
                      <th>Balance</th>
                      <th>Payment Order</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr class="totalRow">
                      <td class="titleCol">Account Totals</td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td class="dollar"></td>
                      <td>
                        <input name="achStop1" id="achStop1" type="checkbox" disabled="disabled" value="achStop" class="checkboxInput checkboxDisabled"/>
                        <label for="achStop1" class="checkboxDisabled">Stop ACH Payments</label>
                      </td>
                    </tr>
                  </tfoot>
                  <tbody class="summary-tbody">
                    <tr>
                      <input type="hidden" value="FERS_DEPOSIT"/>
                      <td class="titleCol">FERS Deposit</td>
                      <td class="dollar">$2,824.98</td>
                      <td><input name="cpostr2" type="text" class="text moneyField" value="2.00"/></td>
                      <td><input name="cpostr3" type="text" class="text moneyField" value="2,500.00"/></td>
                      <td><input name="cpostr4" type="text" class="text moneyField" value="326.98"/></td>
                      <td>
                        <select name="pOrder1" class="pOrder">
                          <option selected="selected">1</option>
                          <option>2</option>
                          <option>3</option>
                          <option>4</option>
                          <option>5</option>
                          <option>6</option>
                          <option>7</option>
                          <option>8</option>
                          <option>9</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <input type="hidden" value="FERS_REDEPOSIT"/>
                      <td class="titleCol">FERS Redeposit</td>
                      <td class="dollar">$0.00</td>
                      <td><input name="fd2" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="fd3" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="fd4" type="text" class="text moneyField" value="0.00"/></td>
                      <td>
                        <select name="pOrder2" class="pOrder">
                          <option>1</option>
                          <option selected="selected">2</option>
                          <option>3</option>
                          <option>4</option>
                          <option>5</option>
                          <option>6</option>
                          <option>7</option>
                          <option>8</option>
                          <option>9</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_POST_3_91_REDEPOSIT"/>
                      <td class="titleCol">CSRS Post 3/91 Redeposit</td>
                      <td class="dollar">$0.00</td>
                      <td><input name="fd2" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="fd3" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="fd4" type="text" class="text moneyField" value="0.00"/></td>
                      <td>
                        <select name="pOrder3" class="pOrder">
                          <option>1</option>
                          <option>2</option>
                          <option selected="selected">3</option>
                          <option>4</option>
                          <option>5</option>
                          <option>6</option>
                          <option>7</option>
                          <option>8</option>
                          <option>9</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_POST_82_PRE_91_REDEPOSIT"/>
                      <td class="titleCol">CSRS Post82/Pre91 Redeposit</td>
                      <td class="dollar">$0.00</td>
                      <td><input name="cprer2" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cprer3" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cprer4" type="text" class="text moneyField" value="0.00"/></td>
                      <td>
                        <select name="pOrder4" class="pOrder">
                          <option>1</option>
                          <option>2</option>
                          <option>3</option>
                          <option selected="selected">4</option>
                          <option>5</option>
                          <option>6</option>
                          <option>7</option>
                          <option>8</option>
                          <option>9</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_PRE_10_82_REDEPOSIT"/>
                      <td class="titleCol">CSRS Pre 10/82 Redeposit</td>
                      <td class="dollar">$0.00</td>
                      <td><input name="cpostd2" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpostd3" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpostd4" type="text" class="text moneyField" value="0.00"/></td>
                      <td>
                        <select name="pOrder5" class="pOrder">
                          <option>1</option>
                          <option>2</option>
                          <option>3</option>
                          <option>4</option>
                          <option selected="selected">5</option>
                          <option>6</option>
                          <option>7</option>
                          <option>8</option>
                          <option>9</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_POST_10_82_DEPOSIT"/>
                      <td class="titleCol">CSRS Post 10/82 Deposit</td>
                      <td class="dollar">$0.00</td>
                      <td><input name="cpostd2" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpostd3" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpostd4" type="text" class="text moneyField" value="0.00"/></td>
                      <td>
                        <select name="pOrder6" class="pOrder">
                          <option>1</option>
                          <option>2</option>
                          <option>3</option>
                          <option>4</option>
                          <option>5</option>
                          <option selected="selected">6</option>
                          <option>7</option>
                          <option>8</option>
                          <option>9</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_PRE_10_82_DEPOSIT"/>
                      <td class="titleCol">CSRS Pre 10/82 Deposit</td>
                      <td class="dollar">$0.00</td>
                      <td><input name="cpred2" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpred3" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpred4" type="text" class="text moneyField" value="0.00"/></td>
                      <td>
                        <select name="pOrder7" class="pOrder">
                          <option>1</option>
                          <option>2</option>
                          <option>3</option>
                          <option>4</option>
                          <option>5</option>
                          <option>6</option>
                          <option selected="selected">7</option>
                          <option>8</option>
                          <option>9</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <input type="hidden" value="FERS_PEACE_CORPS"/>
                      <td class="titleCol">FERS Peace Corps</td>
                      <td class="dollar">$0.00</td>
                      <td><input name="cpred2" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpred3" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpred4" type="text" class="text moneyField" value="0.00"/></td>
                      <td>
                        <select name="pOrder8" class="pOrder">
                          <option>1</option>
                          <option>2</option>
                          <option>3</option>
                          <option>4</option>
                          <option>5</option>
                          <option>6</option>
                          <option>7</option>
                          <option selected="selected">8</option>
                          <option>9</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <input type="hidden" value="CSRS_PEACE_CORPS"/>
                      <td class="titleCol">CSRS Peace Corps</td>
                      <td class="dollar">$0.00</td>
                      <td><input name="cpred2" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpred3" type="text" class="text moneyField" value="0.00"/></td>
                      <td><input name="cpred4" type="text" class="text moneyField" value="0.00"/></td>
                      <td>
                        <select name="pOrder9" class="pOrder">
                          <option>1</option>
                          <option>2</option>
                          <option>3</option>
                          <option>4</option>
                          <option>5</option>
                          <option>6</option>
                          <option>7</option>
                          <option>8</option>
                          <option selected="selected">9</option>
                        </select>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="clear tabBtnsWrapper">
                <a href="javascript:;" class="priBtn fRight jsCancelBillingSummaryEdit"><span><span>Cancel</span></span></a>
                <a href="javascript:;" class="priBtn fRight jsSavelBillingSummaryEdit"><span><span>Save</span></span></a>
              </div>
            </div>
            <!-- .billingSummaryPanelEdit -->
          </div>
          <div class="tabsBlock  serviceHistoryTab isHidden">
            <div class="serviceHistoryPanel roundedGrayPanel">

              <div class="tabsBlock depositTab">
                <div class="versionBar">
                  <span class="fLeft bold grayTxt">Calculation Version</span>
                  <select class="fLeft">

                  </select>
                  <a href="javascript:;" class="priBtn jsDelVersionBtn fRight"><span><span>Delete Current</span></span></a>
                </div>
                <div class="scrollTblArea">
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl entriesTbl sortable" id="depTbl" width="100%">
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
                    </colgroup>
                    <thead>
                      <tr>
                        <th class="unsortable firstCol"><div class="blankCellPlaceholder"></div></th>
                        <th>Begin Date</th>
                        <th>End Date</th>
                        <th title="Refund Date/IAD">Ref. Date/IAD</th>
                        <th title="Retire Type">Ret. Type</th>
                        <th title="Period Type">Per. Type</th>
                        <th title="Appointment Type">Appt. Type</th>
                        <th>Service Type</th>
                        <th>Amount</th>
                        <th title="Interest Rate">Int. Rate</th>
                        <th class="lastCol">Pay Type</th>
                      </tr>
                    </thead>
                    <tfoot class="isHidden">
                      <tr><td class="blankCell firstCol">&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td class="lastCol"></td></tr>
                    </tfoot>
                  </table>
                </div>
                <div class="funtionArea">
                  <p>
                    <a href="javascript:;" class="priBtn jsRefreshGrid fRight"><span><span>Refresh Grid</span></span></a>
                  </p>
                </div>
                <div class="validationStatusBar">
                  <span class="fieldLabel fLeft">Calculation Results :</span>
                  <span class="fieldVal resultsVal fLeft">Unknown</span>
                  <span class="fieldVal dateVal fRight calculateDate"></span>
                  <span class="fieldLabel fRight">Calculate as of :</span>
                </div>
                <div class="scrollTblArea">
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl validateResultTbl sortable" id="depValTbl" width="113%">
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
                        <th>Begin Date</th>
                        <th>End Date</th>
                        <th>Mid Point</th>
                        <th title="Refund Date/IAD">Ref. Date/IAD</th>
                        <th title="Period Type">Per. Type</th>
                        <th>Deduction Amount</th>
                        <th>Total Interest</th>
                        <th>Payments Applied</th>
                        <th class="lastCol">Balance</th>
                      </tr>
                    </thead>
                    <tfoot>
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
                <div class="noValidMask"></div>
                <div class="chartCalArea">
                  <h3 class="panelHeader jsTogglePanel panelExpanded">Account Summary</h3>
                  <div class="panelBody">
                    <div class="chartCalAreaBox1">
                      <p class="boxTitle">Chart: </p>
                      <select>
                        <option value="HOUR_2000" selected="selected">2000 Hour Chart - for Postal employees 07/25/85 to present</option>
                        <option value="HOUR_2008">2008 Hour Chart - for Postal employees 7/20/71 to 07/24/85</option>
                        <option value="HOUR_2016">2016 Hour Chart for Postal employees 12/3/55 - 7/19/71</option>
                        <option value="HOUR_2024">2024 Hour Chart for Postal employees 7/1/45 - 12/2/55</option>
                        <option value="HOUR_2080">2080 Hour Chart for Postal employees 7/1/45 - 12/2/55</option>
                        <option value="HOUR_2087">2087 Hour Chart effective on or after 3/1/86</option>
                        <option value="DAY_260">260 Day Chart effective 7/1/45</option>
                      </select>
                    </div>
                    <div class="chartCalAreaBox2">
                      <p class="boxTitle">Enter Hours: </p>
                      <input name="hoursInput" type="text" class="text hourField"/><a href="javascript:;" class="priBtn jsSubmitHour"><span><span>Submit</span></span></a>
                    </div>
                    <div class="chartCalAreaBox3">Enter Date : <strong></strong></div>
                    <div class="chartCalAreaBox4 chartCalAreaBoxEq">
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
                    <div class="chartCalAreaBox5 chartCalAreaBoxEq">
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
                    <div class="chartCalAreaBox6 chartCalAreaBoxEq">
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
                  </div>
                </div>
              </div>
              <!-- .depositTab -->
            </div>
          </div>         
        </div>
        <!-- .tabsArea -->

        <div class="statusInfoBar">
          <a href="javascript:;" class="jsShowStatusInfoPopup">Status Information: Lorem Ipsum dolor sit amet...</a>
          <div class="corner cornerTl"></div>
          <div class="corner cornerTr"></div>
          <div class="corner cornerBl"></div>
          <div class="corner cornerBr"></div>
        </div>

      </div>
      <!-- #content --> 

      <div id="footer">
        <div class="footerInner">
          <strong>&copy; Copyright OPM</strong>
          <ul>
            <li><a href="javascript:;">Contact us</a></li>
            <li><a href="javascript:;">Privacy Policy</a></li>
            <li><a href="javascript:;">Terms &amp; Conditions</a></li>
          </ul>
        </div>
      </div>
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
                  <td>- You have 1 Record in <a href="WorkQueue.html">Work Queue.</a><br/>- You have 7 <a href="Suspense.html">Payments in Suspense</a>.</td>
                </tr>
                <tr>
                  <td>05/06/2013<br/>08:00:00 AM</td>
                  <td>John Smith<br/>(Supervisor)</td>
                  <td>I reviewed the Account for John Doe and found an error in Billing Summary. Please fix. <a href="AccountDetails.html">Click here to view the account</a>.</td>
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
                    <td class="lastCol">Notification from System: You have 2 records in <br/><a href="WorkQueue.html">Work Queue.</a></td>
                  </tr>
                  <tr>
                    <td><span class="rowInfo"></span>05/06/2013</td>
                    <td>08:00:00 AM</td>
                    <td class="lastCol">Information from System: You have 2 records in <br/><a href="WorkQueue.html">Work Queue.</a></td>
                  </tr>
                  <tr>
                    <td><span class="rowErr"></span>05/06/2013</td>
                    <td>08:00:00 AM</td>
                    <td class="lastCol">Error from System: You have 2 records in <br/><a href="WorkQueue.html">Work Queue.</a></td>
                  </tr>
                  <tr>
                    <td><span class="rowInfo"></span>05/06/2013</td>
                    <td>08:00:00 AM</td>
                    <td class="lastCol">Information from System: You have 2 records in <br/><a href="WorkQueue.html">Work Queue.</a></td>
                  </tr>
                  <tr>
                    <td><span class="rowInfo"></span>05/06/2013</td>
                    <td>08:00:00 AM</td>
                    <td class="lastCol">Information from System: You have 2 records in <br/><a href="WorkQueue.html">Work Queue.</a></td>
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

    <div class="popup accountNotesPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Account Notes</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
              <h5>Notes:</h5>
              <div class="viewNotesArea">
                <p>
                  Interest Change approved.<br/>
                  <span class="col1">Type</span>	<span class="col2">Old</span>	<span class="col3">New</span><br/>
                  <span class="col1">FERS</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                  <span class="col1">Pre Deposit</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                  <span class="col1">Post Deposit</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                  <span class="col1">Pre Redeposit</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                  <span class="col1">Post Redeposit</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                  <span class="col1">FERS</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                  <span class="col1">Pre Deposit</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                  <span class="col1">Post Deposit</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                  <span class="col1">Pre Redeposit</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                  <span class="col1">Post Redeposit</span>	<span class="col2">0.00</span>	<span class="col3">0.00</span><br/>
                </p>
              </div>
            </div>
            <div class="notesNav">
              <a href="javascript:;" class="priBtn priBtnDisabled notesToFirst notesToFirstDisabled"><span><span><i></i></span></span></a>
              <a href="javascript:;" class="priBtn priBtnDisabled notesToPrev notesToPrevDisabled"><span><span><i></i></span></span></a>
              <a href="javascript:;" class="priBtn notesToNext"><span><span><i></i></span></span></a>
              <a href="javascript:;" class="priBtn notesToLast"><span><span><i></i></span></span></a>
            </div>
            <div class="notesPopupBtnWrapper">
              <a href="javascript:;" class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
              <a href="javascript:;" class="priBtn jsEditNotePopup"><span><span>Edit</span></span></a>
              <a href="javascript:;" class="priBtn priBtnDisabled"><span><span>Save</span></span></a>
              <a href="javascript:;" class="priBtn jsDelNotePopup"><span><span>Delete</span></span></a>
              <a href="javascript:;" class="priBtn jsClipboardCopy"><span><span>Clipboard Copy</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .accountNotesPopup -->

    <div class="popup accountNotesEditPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Account Notes Edit</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
              <h5>Notes:</h5>
              <div class="textaareaWrapper">
                <textarea rows="1" cols="1" id="noteEdit"></textarea>
              </div>
            </div>
            <div class="notesNav">
              <a href="javascript:;" class="priBtn priBtnDisabled notesToFirst notesToFirstDisabled"><span><span><i></i></span></span></a>
              <a href="javascript:;" class="priBtn priBtnDisabled notesToPrev notesToPrevDisabled"><span><span><i></i></span></span></a>
              <a href="javascript:;" class="priBtn notesToNext"><span><span><i></i></span></span></a>
              <a href="javascript:;" class="priBtn notesToLast"><span><span><i></i></span></span></a>
            </div>
            <div class="notesPopupBtnWrapper">
              <a href="javascript:;" class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
              <a href="javascript:;" class="priBtn priBtnDisabled"><span><span>Edit</span></span></a>
              <a href="javascript:;" class="priBtn jsUpdateNotePopup"><span><span>Save</span></span></a>
              <a href="javascript:;" class="priBtn jsDelNotePopup"><span><span>Delete</span></span></a>
              <a href="javascript:;" class="priBtn jsClipboardCopy"><span><span>Clipboard Copy</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .accountNotesEditPopup -->

    <div class="popup accountNotesAddPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Add Account Notes</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
              <h5>Notes:</h5>
              <div class="textaareaWrapper newnote"><textarea rows="1" cols="1"></textarea></div>
            </div>
            <div class="notesPopupBtnWrapper">
              <a href="javascript:;" class="priBtn jsSaveNotePopup"><span><span>Save</span></span></a>
              <a href="javascript:;" class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
              <a href="javascript:;" class="priBtn jsClipboardCopy"><span><span>Clipboard Copy</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .accountNotesAddPopup -->

    <div class="popup changeAccountInterestNotesPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Account Notes</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
              <h5>You are making changes to the interest, please enter the reason:</h5>
              <div class="textaareaWrapper"><textarea rows="1" cols="1"></textarea></div>
            </div>
            <div class="notesPopupBtnWrapper">
              <a href="javascript:;" class="priBtn jsSaveAccountInterestNotePopup"><span><span>Save</span></span></a>
              <a href="javascript:;" class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .changeAccountInterestNotesPopup -->

    <div class="popup delAccountNotePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Deleting Account Note</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Do you want to delete this note?</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsDoDelAccountNote"><span><span>OK</span></span></a>
              <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <div class="popup delAllAccountNotePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Deleting Account Note</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Do you want to delete this note?</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsDoDelAllAccountNote"><span><span>OK</span></span></a>
              <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .delAccountNotePopup -->

    <div class="popup noAccountNoteSelectedPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Deleting Account Note</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">Please select a note to delete.</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .noAccountNoteSelectedPopup -->

    <div class="popup noAccountNoteSelectedEditPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Edit Account Note</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">Please select a note to edit.</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .noAccountNoteSelectedEditPopup -->
    <div class="popup basicInfoValidationPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Validation Error</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg basicValidationError"></p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup validationErrorOk"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <div class="popup noEndDatePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">End Date Calculation</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg endDateError"></p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <div class="popup emptyNotePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Empty Note</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">The account note should not be empty.</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup emptyNewNoteOk"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <div class="popup billingInfoValidationPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Validation Error</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg billingValidationError"></p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <div class="popup noteEmptyPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Empty Note</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">The note should not be empty.</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup emptyNoteOk"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .noAccountNoteSelectedEditPopup -->



    <div class="alpha2"></div>
    <div class="popup popup2 accountNoteCopyPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Success</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">Copy to Clipboard Completed Successfully.</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup2"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .accountNoteCopyPopup -->

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

    <div class="popup confirmSaveEmployeePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Success</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Basic information successfully updated!</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .confirmSaveEmployeePopup -->



    <div class="popup updateInterestReportPopup isHidden">
      <div class="popupArrow"></div>
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
    <!-- .updateInterestReportPopup -->

    <div class="popup lastUpdatePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Update Interest On Account</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <div class="popupPadding">
              <p>The current account has been calculated as of today.<br />The invoice is a reprint</p>
            </div>
            <div class="popupBtnWrapper">
              <a class="priBtn jsShowLastUpdateReport"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .lastUpdatePopup -->

    <div class="popup confirmDatePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Refund / Lump SUm</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <div class="popupPadding">
              <p>Please confirm the date that the actual payment was issued (not the service period end date).<br /><br /><input type="text" class="text datePicker" value="10/22/1981" /></p>
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
              <p><img src="<c:url value="/i/icon-error.png"/>" alt="" width="22" height="22" class="vMiddle" />
                The effective date for the refund is 10/22/1981</p>
            </div>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .periodErrorPopup -->

    <div class="popup updateInterestPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Update Interest On Account</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="popupPadding">
              <p>The balance due on the current account will be recalculated through today's date. This is an irrevocable account adjustment. This adjustment will generate a revised statement that you may mail to the claimant. It will not be mailed from Production Control. Are you sure you want to update the interest?
              </p>
            </div>
            <div class="popupBtnWrapper">
              <a class="priBtn jsShowLastUpdateBtn"><span><span>Yes</span></span></a>
              <a class="priBtn jsClosePopup"><span><span>No</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .updateInterestPopup -->

    <div class="isHidden">
      <table class="isHidden" id="versionTableTemplate">
        <tbody>
          <tr>
            <td class="blankCell firstCol">&nbsp;</td>
            <td></td>
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
            <td class="lastCol"></td>
            <input type="hidden"/>
          </tr>
        </tbody>
      </table>
      <table class="isHidden" id="versionEditTemplate">
        <tbody>
          <tr class="entriesEditRow">
            <td class="blankCell firstCol">&nbsp;</td>
            <td class="bDate"><input name="bDate" type="text" class="text bDate" value="01/01/2001"/></td>
            <td class="eDate"><input name="eDate" type="text" class="text eDate" value="01/12/2012"/></td>
            <td class="rDate"><input name="rDate" type="text" class="text rDate" value="01/12/2012"/></td>
            <td><select class="rType" typeName="retirementTypes">
              </select></td>
            <td><select typeName="periodTypes" class="pType">
              </select></td>
            <td><select class="aType" typeName="appointmentTypes">
              </select></td>
            <td><select class="sType" typeName="serviceTypes" >
              </select></td>
            <td><input name="amount" type="text" class="text amount" value="$ 20,000.00"/></td>
            <td><input name="intRate" type="text" class="text intRate" value="Default"/></td>
            <td class="lastCol"><select class="payType" typeName="payTypes">
              </select></td>
          </tr>
        </tbody>
      </table>

      <table class="isHidden" id="versionNewTemplate">
        <tbody>
          <tr class="even newEntryRow">
            <td class="blankCell firstCol">&nbsp;</td>
            <td></td>
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
            <td class="lastCol"></td>
            <input type="hidden"/>
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
            <input type="hidden"/>
            <td class="dollar"></td>
            <td class="dollar"></td>
            <td class="dollar"></td>
            <td class="lastCol dollar"></td>
          </tr>
        </tbody>
      </table>
      
      <table class="isHidden" id="resultItemEditTemplate">
        <tbody>
          <tr class="entriesEditRow">
            <td class="blankCell firstCol">&nbsp;</td>
            <td class="bDate"><input name="bDate" type="text" class="text bDate" value="01/01/2001"/></td>
            <td class="eDate"><input name="eDate" type="text" class="text eDate" value="01/12/2012"/></td>
            <td class="rDate"><input name="mDate" type="text" class="text mDate" value="01/12/2012"/></td>
            <td class="rDate"><input name="rDate" type="text" class="text rDate" value="01/12/2012"/></td>
            <td><select typeName="periodTypes" class="pType">
              </select></td>
            <td><input name="amount" type="text" class="text amount" value="$20,000.00"/></td>
            <td><input name="interest" type="text" class="text amount" value="$20,000.00"/></td>
            <td><input name="payment" type="text" class="text amount" value="$20,000.00"/></td>
            <td><input name="balance" type="text" class="text amount" value="$20,000.00"/></td>
            
          </tr>
        </tbody>
      </table>
      
      <table class="isHidden" id="resultItemNewTemplate">
        <tbody>
          <tr class="newEntryRow">
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


  </body>
</html
