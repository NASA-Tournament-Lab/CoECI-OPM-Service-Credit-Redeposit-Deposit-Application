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
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
  <head>
    <base href="<%=basePath%>"/>
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
    <script type='text/javascript' src='<c:url value="/js/jquery-dateFormat.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/merge-sort.js"/>'></script>
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
    <input id="accountCsd" type="hidden" value="${a.claimNumber}"/>
    <div id="wrapper">
      <%@ include file="include/header.jsp"%>

      <div id="content">
        <p class="breadcurmb"><a href="<c:url value="/account/view"/>">Account Search Results</a><span>Account Details</span></p>
        <div class="pageTitleArea">
          <h2 class="pageTitle">Account Details</h2>
          <div class="setHomeLink">
            <div class="accountBtns">
              <div class="fRight">
              <a href="javascript:;" class="priBtn jsRefreshAccountDetails"><span><span>Refresh</span></span></a>
              <a href="javascript:;" class="priBtn jsPrintAccountSummary"><span><span>Print Summary</span></span></a>
              <a href="javascript:;" class="priBtn jsPrintStatement"><span><span>Print Statement</span></span></a>
              </div>
            </div>
            <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
            <a href="javascript:;" class="jsShowTips">?</a>
          </div>
        </div>

        <div class="roundedGrayPanel accountSummaryPanel">
          <h3 class="panelHeader jsTogglePanel panelExpanded" tabindex="0">Account Summary</h3>
          <div class="panelBody">
            <div class="column column1">
              <h4 class="name"></h4>
              <div class="fieldRow">
                <p class="fieldTitle">CSD #<span>:</span></p>
                <p class="fieldVal csd"></p>
              </div>
              <div class="fieldRow">
                <p class="fieldTitle">Birth Date<span>:</span></p>
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
                <p class="fieldTitle">Frozen<span>:</span></p>
                <p class="fieldVal frozen"></p>
              </div>
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
          <h3 class="panelHeader jsTogglePanel panelExpanded" tabindex="0">Account Notes</h3>
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
              <li><a href="javascript:;" id="billingSummaryTab" style="display: none;">Billing Summary</a></li>
              <li><a href="javascript:;" id="serviceHistoryTab">Service History</a></li>
              <li><a href="javascript:;" id="paymentHistoryTab">Payment History</a></li>
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
                  <p class="fieldLabel">Birth Date :</p>
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
                <div class="fRight">
                <a href="javascript:;" class="priBtn jsEditBasicInfo"><span><span>Edit Basic Info</span></span></a>
                <a href="javascript:;" class="priBtn jsConfirmNewAccountValidity"><span><span>Confirm New Account Validity</span></span></a>
                </div>
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
                  <label for="statusD"><p class="fieldLabel">Status <span class="reqMark">*</span>:</p></label>
                  <select name="status" id="statusD" class="accountStatus" typeName="accountStatuses">

                  </select>
                  <input name="check1" id="check1" type="checkbox" value="check1" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="titleRow mb3">
                  <h3>Account Holder</h3>
                </div>
                <div class="halfRowField">
                  <label for="lastNameD"><p class="fieldLabel">Last Name <span class="reqMark">*</span>  :</p></label>
                  <input name="lastName" type="text" id="lastNameD" class="text holder" value="" maxlength="128"/>
                  <input name="check2" id="check2" type="checkbox" value="check2" class="checkboxInput checkValidateInputBox"/>
                </div>
                <div class="halfRowField">
                  <label for="firstNameD"><p class="fieldLabel">First Name <span class="reqMark">*</span> :</p></label>
                  <input name="firstName" type="text" id="firstNameD" class="text holder" value="" maxlength="128"/>
                  <input name="check3" id="check3" type="checkbox" value="check3" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="middleInitialD"><p class="fieldLabel">Middle Name :</p></label>
                  <input name="middleInitial" type="text" id="middleInitialD" class="text midLength holder" maxlength="128"/>
                  <input name="check4" id="check4" type="checkbox" value="check4" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="suffixD"><p class="fieldLabel">Suffix :</p></label>
                  <select name="suffix" id="suffixD" class="midLength holder"  typeName="suffixes">

                  </select>
                  <input name="check5" id="check5" type="checkbox" value="check5" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="birthDateD"><p class="fieldLabel">Birth Date <span class="reqMark">*</span> :</p></label>
                  <input name="birthDate" type="text" id="birthDateD" class="text midLength datePicker holder" title="Choose the birth date"/>
                  <input name="check6" id="check6" type="checkbox" value="check6" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="ssnD"><p class="fieldLabel">SSN <span class="reqMark">*</span> :</p></label>
                  <input name="ssn1B" id="ssnD" type="text" maxlength="3" class="text ssnB searchSsn1B" title="Enter the ssn number in the format XXX-XX-XXXX" onkeypress='return  validateNumberInput(event);'/><span class="sep">-</span><input name="ssn2B" title="Enter the ssn number in the format XXX-XX-XXXX" maxlength="2" type="text" class="text ssnB searchSsn2B" onkeypress='return  validateNumberInput(event);'/><span class="sep">-</span><input name="ssn3B" title="Enter the ssn number in the format XXX-XX-XXXX" maxlength="4" type="text" class="text ssnB searchSsn3B" onkeypress='return  validateNumberInput(event);' title="input the phone extension"/>
                  <!--<input name="ssn" type="text" id="ssnD" class="text holder" maxlength="128"/>-->
                  <input name="check7" id="check7" type="checkbox" value="check7" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField phoneField">
                  <label for="phoneInputD"><p class="fieldLabel">Telephone :</p></label>
                  <input name="phoneInput" type="text" id="phoneInputD" class="text phoneInput phone1 phone" maxlength="20" onkeypress='return  validatePhoneInput(event);'/><span class="sep">-</span><input name="phoneExt" type="text" class="text phoneExt phone2 phone" onkeypress='return  validatePhoneInput(event);'/>
                  <span class="extNote">Ext</span>
                  <input name="check8" id="check8" type="checkbox" value="check8" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="emailD"><p class="fieldLabel">Email :</p></label>
                  <input name="email" type="text" id="emailD" class="text holder" maxlength="128"/>
                  <input name="check9" id="check9" type="checkbox" value="check9" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="titleD"><p class="fieldLabel">Title :</p></label>
                  <input name="title" type="text" id="titleD" class="text holder" maxlength="128"/>
                  <input name="check10" id="check10" type="checkbox" value="check10" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="departmentCodeD"><p class="fieldLabel">Agency / Dept. Code :</p></label>
                  <input name="departmentCode" type="text" id="departmentCodeD" class="text midLength holder" maxlength="2"/>
                  <input name="check11" id="check11" type="checkbox" value="check11" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
              </div>
              <div class="halfCol rightHalfCol">
                <div class="titleRow">
                  <h3>Data Check Status: <span class="jsCheckStatus">Unchecked</span></h3>
                  <span>Checked By: <span class="checker">-</span></span>
                </div>
                <div class="halfRowField radioRow formTypeDiv">
                  <p class="fieldLabel">Form Submitted:</p>
                  <input name="formType" type="radio" value="2" id="formCsrs1" />
                  <label class="radioLabel" for="formCsrs1">2803 (CSRS)</label>
                  <input name="formType" type="radio" id="formFers1" value="1" checked="checked"/>
                  <label class="radioLabel" for="formFers1">3108 (FERS)</label>
                  <input name="check12" id="check12" type="checkbox" value="check12" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="titleRow mb3">
                  <h3>Address</h3>
                </div>
                <div class="halfRowField">
                  <label for="street1D"><p class="fieldLabel">Line #1 <span class="reqMark">*</span> :</p></label>
                  <input name="street1" type="text" id="street1D" class="text address" maxlength="40"/>
                  <input name="check13" id="check13" type="checkbox" value="check13" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="street2D"><p class="fieldLabel">Line #2 :</p></label>
                  <input name="street2" type="text" id="street2D" class="text address" maxlength="40"/>
                  <input name="check14" id="check14" type="checkbox" value="check14" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="street3D"><p class="fieldLabel">Line #3 :</p></label>
                  <input name="street3" type="text" id="street3D" class="text address" maxlength="40"/>
                  <input name="check15" id="check15" type="checkbox" value="check15" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="street4D"><p class="fieldLabel">Line #4 :</p></label>
                  <input name="street4" type="text" id="street4D" class="text address" maxlength="40"/>
                  <input name="check16" id="check16" type="checkbox" value="check16" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="street5D"><p class="fieldLabel">Line #5 :</p></label>
                  <input name="street5" type="text" id="street5D" class="text address" maxlength="40"/>
                  <input name="check17" id="check17" type="checkbox" value="check17" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="cityD"><p class="fieldLabel">City <span class="reqMark">*</span> :</p></label>
                  <input name="city" type="text" id="cityD" class="text midLength address" maxlength="20"/>
                  <input name="check18" id="check18" type="checkbox" value="check18" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="stateD"><p class="fieldLabel">State/Province/Region <span class="reqMark">*</span> :</p></label>
                  <select name="state" id="stateD" class="midLength2 address" typeName="states">

                  </select>
                  <input name="check19" id="check19" type="checkbox" value="check19" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="zipCodeD"><p class="fieldLabel">ZIP/Postal Code <span class="reqMark">*</span> :</p></label>
                  <input name="zipCode" type="text" id="zipCodeD" class="text midLength address" maxlength="10"/>
                  <input name="check20" id="check20" type="checkbox" value="check20" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="countryD"><p class="fieldLabel">Country :</p></label>
                  <select name="country" id="countryD" class="midLength2 address" typeName="countries">

                  </select>
                  <input name="check21" id="check21" type="checkbox" value="check21" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField">
                  <label for="geoCodeD"><p class="fieldLabel">GEO code :</p></label>
                  <input name="geoCode" id="geoCodeD" type="text" class="text midLength holder" maxlength="10"/>
                  <input name="check22" id="check22" type="checkbox" value="check22" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="titleRow locationTitleRow">
                  <h3>Location of Employment</h3>
                </div>
                <div class="halfRowField">
                  <label for="cityOfEmploymentD"><p class="fieldLabel">City :</p></label>
                  <input name="cityOfEmployment" id="cityOfEmploymentD" type="text" class="text midLength holder" maxlength="128" />
                  <input name="check23" id="check23" type="checkbox" value="check23" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
                <div class="halfRowField  mb3">
                  <label for="stateOfEmploymentD"><p class="fieldLabel">State :</p></label>
                  <select name="stateOfEmployment" id="stateOfEmploymentD" class="midLength holder" typeName="states">

                  </select>
                  <input name="check24" id="check24" type="checkbox" value="check24" class="checkboxInput checkValidateInputBox" checked="checked"/>
                </div>
              </div>
              <div class="clear tabBtnsWrapper">
                <a href="javascript:;" class="priBtn fRight editEmployeeBtn jsCancelEditBasicInfo"><span><span>Cancel</span></span></a>
                <a href="javascript:;" class="priBtn fRight editEmployeeBtn jsSaveEditBasicInfo"><span><span>Save</span></span></a>

                <a href="javascript:;" class="priBtn fRight validEmployeeBtn jsCancelConfirmNewAccountValidity"><span><span>Cancel</span></span></a>
                <a href="javascript:;" class="priBtn fRight validEmployeeBtn jsRejectConfirmNewAccountValidity"><span><span>Reject</span></span></a>
                <a href="javascript:;" class="priBtn fRight validEmployeeBtn jsApproveConfirmNewAccountValidity"><span><span>Approve</span></span></a>
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
                  <p class="fieldLabel">Computed Date :</p>
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
                <a href="javascript:;" class="priBtn fRight jsPrintFinalStatement"><span><span>Print Final Statement</span></span></a>
                <a href="javascript:;" class="priBtn fRight jsReprintStatement"><span><span>Reprint Statement</span></span></a>
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
                  <input name="cDate" type="text" class="text datePicker" value="" title="choose the computed date"/>
                </div>
                <div class="billingField">
                  <p class="fieldLabel">1st Billing Date :</p>
                  <input name="billingDate" type="text" class="text datePicker" value="" title="choose the billing date"/>
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
                  <input name="lastCalcDate" type="text" class="text datePicker" value="" title="choose the interest calc date"/>
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
            <div class="serviceHistoryPanel tabsArea roundedGrayPanel">
              
              <div class="tabsBlock depositTab">
                <div class="versionBar">
                  <label for="calcVersionDepD"><span class="fLeft bold grayTxt">Calculation Version</span></label>
                  <select class="fLeft" id="calcVersionDepD">

                  </select>
                  <a href="javascript:;" class="priBtn jsNewVersionBtn fLeft"><span><span>New Version</span></span></a>
                  <input name="copyCurrent" id="copyCurrent" type="checkbox" value="copyCurrent" class="fLeft checkboxInput"/> <label for="copyCurrent" class="fLeft">Copy Current</label>
                  <a href="javascript:;" class="priBtn jsDelVersionBtn fRight"><span><span>Delete Current</span></span></a>
                </div>
                <div class="scrollTblArea">
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl entriesTbl  periodTable sortable" id="depTbl" width="100%">
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
                      <col class="col9 withHoldDisabled"/>
                      <col class="col10"/>
                      <col class="col11"/>
                    </colgroup>
                    <thead>
                      <tr>
                        <th class="unsortable firstCol">&nbsp;</th>
                        <th class="defaultSortCol defaultSortDown asDate">Begin Date</th>
                        <th class="asDate">End Date</th>
                        <th class="asString">Retire Type</th>
                        <th class="asString">Period Type</th>
                        <th class="asString">Appointment Type</th>
                        <th class="asString">Service Type</th>
                        <th class="asNumeric">Amount</th>
                        <th class="asString">Pay Type</th>
                        <th class="asNumeric withHoldDisabled unsortable">Withholding Rate</th>
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

                         <td class="withHoldDisabled">
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
                        <th class="asDate defaultSortCol defaultSortDown">Begin Date</th>
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
                  <h3 class="panelHeader jsTogglePanel panelExpanded">Account Summary</h3>
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
                      <label for="hoursInputL"><p class="boxTitle">Enter Hours: </p></label>
                      <input name="hoursInput" id="hoursInputL" type="text" class="text hourField hourFieldDep"/><a href="javascript:;" class="priBtn jsSubmitHour"><span><span>Submit</span></span></a>
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
              </div>
              <!-- .depositTab -->

            </div>
          </div>
          <div class="tabsBlock  paymentHistoryTab isHidden">
            <div class="paymentHistoryPanel">
              <div class="titleRow">
                <h3>Payment History Summary</h3>
              </div>
              <div class="paymentHistoryFields">
                <div class="paymentHistoryField">
                  <p class="fieldStatus">This payment may not be deleted</p>
                  <p class="fieldLabel">Status :</p>
                  <p class="fieldVal">Posted - Complete</p>
                </div>
                <div class="paymentHistoryField">
                  <p class="fieldStatus">This amount may not be edited</p>
                  <p class="fieldLabel">Status Date :</p>
                  <p class="fieldVal">Sunday, January 6, 2013 at 07:10:21 AM</p>
                </div>
                <div class="paymentHistoryField">
                  <p class="fieldStatus">ACH through bank</p>
                  <p class="fieldLabel">Technician ID :</p>
                  <p class="fieldVal">Wall-E ServiceCreditBatch () - Active user</p>
                </div>
              </div>
              <div class="paymentBtnsWrapper">
                <a href="javascript:;" class="priBtn fLeft jsAddPayment"><span><span>Add Payment</span></span></a>
                 <div class="fRight">
                <a href="javascript:;" class="priBtn jsPHReceipt"><span><span>Payment Receipt</span></span></a>
                <a href="javascript:;" class="priBtn jsShowPHNote"><span><span>Show Note</span></span></a>
                <a href="javascript:;" class="priBtn jsRefreshPaymentList"><span><span>Refresh List</span></span></a>
                </div>
              </div>
              <div class="paymentHistoryTblWrapper">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="stdTbl sortable" id="paymentHistoryTbl">
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
                  </colgroup>
                  <thead>
                    <tr>
                      <th class="unsortable">&nbsp;</th>
                      <th>Batch</th>
                      <th>Block</th>
                      <th>Seq</th>
                      <th>Deposit Date</th>
                      <th>Amount</th>
                      <th>Transaction Status</th>
                      <th>Applied To</th>
                      <th>User</th>
                      <th class="unsortable lastCol">GL</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <td class="blankCell starMark">&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                  </tfoot>
                  <tbody>
                    <tr>
                      <td class="blankCell jsShowRowAction">&nbsp;</td>
                      <td>161</td>
                      <td>51</td>
                      <td>60</td>
                      <td>01/05/2013</td>
                      <td>$500.00</td>
                      <td>Posted - Complete</td>
                      <td>Default User Order</td>
                      <td>Wall-E Service Credit</td>
                      <td class="lastCol"><input name="phRow1" type="checkbox" value="phRow1" checked="checked" class="checkboxInput"/></td>
                    </tr>
                    <tr>
                      <td class="blankCell jsShowRowAction">&nbsp;</td>
                      <td>161</td>
                      <td>51</td>
                      <td>60</td>
                      <td>01/05/2013</td>
                      <td>$500.00</td>
                      <td>Posted - Complete</td>
                      <td>Default User Order</td>
                      <td>Wall-E Service Credit</td>
                      <td class="lastCol"><input name="phRow2" type="checkbox" value="phRow1" checked="checked" class="checkboxInput"/></td>
                    </tr>
                    <tr>
                      <td class="blankCell jsShowRowAction">&nbsp;</td>
                      <td>161</td>
                      <td>51</td>
                      <td>60</td>
                      <td>01/05/2013</td>
                      <td>$500.00</td>
                      <td>Posted - Complete</td>
                      <td>Default User Order</td>
                      <td>Wall-E Service Credit</td>
                      <td class="lastCol"><input name="phRow3" type="checkbox" value="phRow1" checked="checked" class="checkboxInput"/></td>
                    </tr>
                    <tr>
                      <td class="blankCell jsShowRowAction">&nbsp;</td>
                      <td>161</td>
                      <td>51</td>
                      <td>60</td>
                      <td>01/05/2013</td>
                      <td>$500.00</td>
                      <td>Posted - Complete</td>
                      <td>Default User Order</td>
                      <td>Wall-E Service Credit</td>
                      <td class="lastCol"><input name="phRow4" type="checkbox" value="phRow1" checked="checked" class="checkboxInput"/></td>
                    </tr>
                    <tr>
                      <td class="blankCell jsShowRowAction">&nbsp;</td>
                      <td>161</td>
                      <td>51</td>
                      <td>60</td>
                      <td>01/05/2013</td>
                      <td>$500.00</td>
                      <td>Posted - Complete</td>
                      <td>Default User Order</td>
                      <td>Wall-E Service Credit</td>
                      <td class="lastCol"><input name="phRow5" type="checkbox" value="phRow1" checked="checked" class="checkboxInput"/></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <!-- .paymentHistoryPanel -->
            <div class="clear tabBtnsWrapper">
              <div class="fRight">
                <a href="javascript:;" class="priBtn jsSavePHChanges"><span><span>Save Changes</span></span></a>
                  <a href="javascript:;" class="priBtn jsPHReversePayment"><span><span>Reverse Payment</span></span></a>
              </div>
            </div>
          </div>           
        </div>
        <!-- .tabsArea -->

      </div>
      <!-- #content --> 

      <jsp:include page="include/footer.jsp"></jsp:include>
      <!-- #footer -->
      <div id="loadingOverlay2" class="isHidden">
        <img src="i/loader.gif" alt="Loading..." />
      </div>
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
              <label for="newnoteD"><h5>Notes:</h5></label>
              <div class="textaareaWrapper newnote"><textarea id="newnoteD" maxlength="2000" rows="1" cols="1"></textarea></div>
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
    <div class="popup emptyNotePopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Empty Note</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p class="popMsg">Account note should not be empty.</p>
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

    <div class="popup rejectAccountPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Reject Account</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
              <h5>Reason for rejection:</h5>
              <div class="textaareaWrapper"><textarea rows="1" cols="1" maxlength="128"></textarea></div>
            </div>
            <div class="popupBtnWrapper">
              <a class="priBtn jsDoRejectAccount"><span><span>Reject</span></span></a>
              <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .accountNotesEditPopup -->

    <div class="popup printStatementPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Statement</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
              <div class="printPreviewArea accountDetailsPrintPreview">
                <div class="printHeader">
                  <h1><span>UNITED STATES</span><br/>OFFICE OF PERSONNEL MANAGEMENT</h1>
                  <p>https://www.pay.gov</p>
                </div>
                <div class="printMetaRight">
                  <div class="printNum"><label>Claim Number</label><span>CSD# 9164949</span></div>
                  <div class="printBirth"><label>Date of Birth</label><span>01/01/1940</span></div>
                  <div class="printAcount">ENTER AMOUNT<br/> OF THIS PAYMENT <span>$</span></div>
                  <h2 class="printFormTitle">SERVICE CREDIT<br/> PAYMENT FORM</h2>
                </div>

                <div class="printAddress">
                  <p>Show any name or address change below</p>
                  <div class="printAddressBox">
                    <div class="printAddressHeader"></div>
                    <div class="printAddressBody">
                      JBGXXXX S EJEJXXXX<br/>
                      123 Test<br/>
                      Apertment 1-A<br/>
                      123 Main Street<br/>
                      Any Town, DC 55555-4444
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
                      <span>JBGXXXX W GAEFXXXX</span>
                    </div>
                    <div class="date">
                      <label>Date</label>
                      <span>06/14/2013</span>
                    </div>
                    <div class="coveredBy">
                      <label>Covered by</label>
                      <span>CSRS</span>
                    </div>
                    <div class="claimNum">
                      <label>Claim Number</label>
                      <span>CSD 9164949</span>
                    </div>
                  </div>
                  <div class="leftPart fLeft">
                    <p class="emLine"><label>Prior Balance Due</label><span class="priorBalanceDue">$1575.18</span></p>
                    <p class="emLine"><label>Plus Interest on Prior Balance</label><span class="plusInterestOnPriorBalance">$0.92</span></p>
                    <p class="emLine"><label>Balance Due Before Payment</label><span class="balanceDueBeforePayment">$1576.10</span></p>
                    <p class="emLine"><label>Amount of Payment</label><span class="amountOfPayment">$5.00</span></p>
                    <div class="detailsLine">
                      <p><label>FERS Deposit/Redeposit</label><span class="fersDeposit">$0.00</span></p>
                      <p><label>Post 9/30/82 Redeposit</label><span class="postRedeposit">$0.00</span></p>
                      <p><label>Post 9/30/82 Deposit</label><span class="postDeposit">$0.00</span></p>
                      <p><label>Pre 10/1/82 Redeposit</label><span class="preRedeposit">$0.00</span></p>
                      <p><label>Pre 10/1/82 Deposit</label><span class="preDeposit">$1,571.00</span></p>
                    </div>
                    <p class="emLine"><label>New Balance Due</label><span class="newBalanceDue">$1,571.00</span></p>
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
    <!-- .printStatementPopup -->

    <div class="popup printSummaryPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Summary</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
              <div class="printPreviewArea accountSummaryPrintPreview">
                <div class="printDate">Printed on 5/5/2013</div>
                <h3 class="accountSummaryPrintTitle">Account Summary</h3>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="accountSummaryPrintTbl">
                  <thead>
                    <tr>
                      <th>Field name</th>
                      <th>Field Vaule</th>
                      <th>Field Name</th>
                      <th>Field Value</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>
                    <tr>
                      <td>SCMClaimNumber</td>
                      <td>12345678</td>
                      <td>SCMAccIntMonDed</td>
                      <td>$0.00</td>
                    </tr>  
                  </tbody>
                </table>
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
    <!-- .printSummaryPopup -->

    <div class="popup printFinalStatementPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Final Statement</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
              <div class="printPreviewArea billingSummaryPrintPreview">
                <div class="printHeader">
                  <h1><span>UNITED STATES</span><br/>OFFICE OF PERSONNEL MANAGEMENT</h1>
                  <p>P.O. Box 979035<br />St. Louis, MO 631987-9000</p>
                </div>
                <div class="printMetaLeft">
                  <div class="printNum"><label>Claim Number</label>CSD# 9164949</div>
                  <div class="printBirth"><label>Date of Birth</label>01/01/1940</div>
                </div>
                <div class="printMetaRight">
                  <div class="printAcount">ENTER AMOUNT<br/> OF THIS PAYMENT $</div>
                  <h2 class="printFormTitle">CIVIL SERVICE DEPOSIT<br />ACCOUNT STATEMENT</h2>
                  <p class="printFormTitleNote">NOTE: IF NAME OR ADDRESS IS<br />INCORRECTLY PRINTED, PLEASE CORRECT IT.</p>
                </div>

                <div class="printAddress">
                  <div class="printAddressBox">
                    <div class="printAddressHeader"></div>
                    <div class="printAddressBody">
                      JBGXXXX S EJEJXXXX<br/>
                      123 Test<br/>
                      Apertment 1-A<br/>
                      123 Main Street<br/>
                      Any Town, DC 55555-4444
                    </div>
                    <div class="printAddressFooter"></div>
                  </div>
                </div>
                <div class="printDetachLine printDetachLineAbove clear">Please detach and return this portion with your payment; see the other side for payment instructions</div>
                <div class="printDataArea">
                  <h3>STATEMENT OF ACCOUNT - KEEP FOR YOUR RECORDS</h3>
                  <div class="printPersonalData">
                    <div class="name">
                      <label>Name</label>
                      JBGXXXX S EJEJXXXX
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
                      CSD 9164949
                    </div>
                  </div>
                  <div class="billingLeftPart fLeft ">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="printTbl">
                      <colgroup>
                        <col class="col1"/>
                        <col class="col2"/>
                      </colgroup>
                      <tbody>
                        <tr>
                          <td colspan="2" class="title Row">Amount Due</td>
                        </tr>
                        <tr>
                          <td>FERS Deposit</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr>
                          <td>Interest *</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr>
                          <td>Additional Interest *</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr class="subtotalRow">
                          <td>Subtotal</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr>
                          <td>Post 9/30/82 Redeposit</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr>
                          <td>Interest *</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr>
                          <td>Additional Interest *</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr class="subtotalRow">
                          <td>Subtotal</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr>
                          <td>Post 9/30/82 Redeposit</td>
                          <td class="aRight">$5,818.79</td>
                        </tr>
                        <tr>
                          <td>Interest *</td>
                          <td class="aRight">$26,304.32</td>
                        </tr>
                        <tr>
                          <td>Additional Interest *</td>
                          <td class="aRight">$722.77</td>
                        </tr>
                        <tr class="subtotalRow">
                          <td>Subtotal</td>
                          <td class="aRight">$32,845.88</td>
                        </tr>
                        <tr>

                          <td>Pre 10/1/82 Redeposit</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr>
                          <td>Interest *</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr>
                          <td>Additional Interest *</td>
                          <td class="aRight">$0.00</td>
                        </tr>
                        <tr class="subtotalRow">
                          <td>&nbsp;</td>
                          <td class="aRight">&nbsp;</td>
                        </tr>
                        <tr>
                          <td>Pre 10/1/82 Redeposit</td>
                          <td class="aRight">$799.81</td>
                        </tr>
                        <tr>
                          <td>Interest *</td>
                          <td class="aRight">$1,149.75</td>
                        </tr>
                        <tr>
                          <td>Additional Interest *</td>
                          <td class="aRight">$60.63</td>
                        </tr>
                        <tr class="subtotalRow">
                          <td>Subtotal</td>
                          <td class="aRight">$2,010.19</td>
                        </tr>
                        <tr class="totalArea">
                          <td>Total</td>
                          <td class="aRight">$34,856.07</td>
                        </tr>
                        <tr class="totalArea">
                          <td>Less Payments</td>
                          <td class="aRight">$5.00</td>
                        </tr>
                        <tr class="totalArea subtotalRow">
                          <td>Balance Due</td>
                          <td class="aRight">$34,851.07</td>
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
                            <td>11/02/1981</td>
                            <td>09/30/1982</td>
                            <td>D</td>
                            <td>10/1/1982</td>
                            <td>04/11/1987</td>
                            <td>D</td>
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
                <a href="javascript:;" class="printLink jsDoPrintFinalStatement">Print</a>
              </div>
              <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .printFinalStatementPopup -->

    <div class="popup printPaymentReceiptPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Payment Receipt</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="printScrollArea">
              <div class="printPreviewArea accountDetailsPrintPreview">
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
                      JBGXXXX S EJEJXXXX<br/>
                      123 Test<br/>
                      Apertment 1-A<br/>
                      123 Main Street<br/>
                      Any Town, DC 55555-4444
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
                      05/22/2013
                    </div>
                    <div class="coveredBy">
                      <label>Covered by</label>
                      CSRS
                    </div>
                    <div class="claimNum">
                      <label>Claim Number</label>
                      CSD 9164949
                    </div>
                  </div>
                  <div class="leftPart fLeft">
                    <p class="emLine"><label>Prior Balance Due</label><span>$34,072.67</span></p>
                    <p class="emLine"><label>Plus Interest on Prior Balance</label><span>$0.00</span></p>
                    <p class="emLine"><label>Balance Due Before Payment</label><span>$34,072.67</span></p>
                    <p class="emLine"><label>Amount of Payment</label><span>$34,072.67</span></p>
                    <div class="detailsLine">
                      <p><label>FERS Deposit/Redeposit</label><span>$0.00</span></p>
                      <p><label>Post 9/30/82 Redeposit</label><span>$0.00</span></p>
                      <p><label>Post 9/30/82 Deposit</label><span>$32,123.11</span></p>
                      <p><label>Pre 10/1/82 Redeposit</label><span>$0.00</span></p>
                      <p><label>Pre 10/1/82 Deposit</label><span>$1,949.56</span></p>
                    </div>
                    <p class="emLine"><label>New Balance Due</label><span>$34,072.67</span></p>
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
                    <p class="paymentNote">No Payment received</p>
                  </div>
                  <div class="clear"></div>
                </div>
                <div class="printFooter group">
                  <div class="fLeft leftNote">RI 3623<br/>Previous editions are<br/>not usable</div>
                  <div class="fLeft middleNote">Please check this statement with your records. If they differ,<br/>contact OPM promptly at sc_receipts@opm.gov and give us full details about the difference.<br/>
                    <strong>SEE OTHER SIDE FOR PAYMENT INSTRUCTIONS AND EXPLAINATION</strong>
                  </div>
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
                <a href="javascript:;" class="printLink jsDoPrintPaymentReceipt">Print</a>
              </div>
              <a class="priBtn jsClosePopup fRight"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .printPaymentReceiptPopup -->

    <div class="popup paymentNotePopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Payment Note</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
              <h5>Notes:</h5>
              <div class="textaareaWrapper"><textarea rows="1" cols="1" class="paymentNoteTextArea"></textarea></div>
              <p class="paymentNoteHint">Type your note and click "Save" or "Cancel"</p>
            </div>
            <div class="popupBtnWrapper">
              <a class="priBtn jsDoSavePHNote"><span><span>Save</span></span></a>
              <a class="priBtn jsDoCancelPHNote"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .paymentNotePopup -->


    <div class="popup paymentNoteCopyClipboardPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Payment Note</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="notesRecord">
              <h5>Notes:</h5>
              <div class="textaareaWrapper"><textarea rows="1" cols="1"></textarea></div>
              <p class="paymentNoteHint">Type your note and click "save" or "Cancel"</p>
            </div>
            <div class="popupBtnWrapper">
              <a class="priBtn jsDoSavePHNote"><span><span>Save</span></span></a>
              <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .paymentNotePopup -->

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
                  <p class="fieldLabel">CSD# :</p>
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
              <p class="fieldLabel">Batch Number :</p>
              <input name="batchNum" type="text" class="text" value="166"/>
            </div>
            <div class="paymentField">
              <p class="fieldLabel">Block Number :</p>
              <input name="blockNum" type="text" class="text" value="1"/>
            </div>
            <div class="paymentField">
              <p class="fieldLabel">Sequence Number :</p>
              <input name="seqNum" type="text" class="text"/>
            </div>
            <div class="paymentField">
              <p class="fieldLabel">&nbsp;</p>
              <input name="glApply" id="glApply" type="checkbox" value="glApply" checked="checked" class="checkboxInput" disabled="disabled"/>
              <label for="glApply">Apply to GL</label>
            </div>
            <div class="paymentField">
              <p class="fieldLabel">Amount :</p>
              <input name="paymentAmount" type="text" class="text"/>
            </div>
            <div class="paymentField paymentDateField">
              <p class="fieldLabel">Date of Deposit :</p>
              <input name="depositDate" type="text" class="text depositDate2"/>
              <span class="datenote">Julian Date = 035<br/>Today is Monday, May 06, 2013</span>
            </div>
            <div class="paymentField">
              <p class="fieldLabel">Apply to :</p>
              <select name="paymentApplyTo" class="paymentApplyTo" id="applicationDesignations">
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

    <div class="popup confirmPaymentDeletionPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Confirmation</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Are you sure you want to delete this payment?</p>
            <div class="popupBtnWrapper">
              <a class="priBtn jsClosePopup jsDeletePayment"><span><span>OK</span></span></a>
              <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .confirmPaymentDeletionPopup -->

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
              <h4 class="popupTitle">Refund / Lump Sum</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <div class="popupPadding">
              <p>Please confirm the date that the actual payment was issued (not the service period end date).<br /><br /><input maxlength="10" type="text" class="text actualPayment2" value="10/22/1981" /></p>
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
              <p class="periodErrorPopupMsg"><img src="<c:url value="/i/icon-error.png"/>" alt="error" width="22" height="22" class="vMiddle" />
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

    <div class="popup reversePayementPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Reverse a Check</h4>
              <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="popupPadding">
              <p>Pick the reason for this reverse payment and click the "Reverse" button. Microsoft Word will start after clicking the "Reverse" button.<br />
                <select typeName="paymentReversalReasons" id="paymentReversalReasons">
                </select>
              </p>
              <p><input type="checkbox" checked="checked" value="checkbox" class="vMiddle" id="applyReversalTo"/><label for="applyReversalTo" class="vMiddle">Apply Reversal to the General Ledger</label></p>
            </div>
            <div class="popupBtnWrapper">
              <a class="priBtn jsReversePayment"><span><span>Reverse</span></span></a>
              <a class="priBtn jsClosePopup"><span><span>Cancel</span></span></a>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .reversePayementPopup -->
	<%@ include file="report-template/AccountingSummaryReport.jsp" %>
	<%@ include file="report-template/PaymentHistory.jsp" %>

    <div class="rowActions isHidden">
      <div class="rowActionHeader"><div class="rowActionHeaderRight"><div class="rowActionHeaderInner"></div></div></div>
      <div class="rowActionBody"><div class="rowActionBodyRight"><div class="rowActionBodyInner">
            <ul>
              <li><a href="javascript:;" class="jsShowPHNote">Show Note</a></li>
              <li><a href="javascript:;" class="jsPHAuditHistory">Audit History</a></li>
              <li><a href="javascript:;" class="jsPHReceipt">Payment Receipt</a></li>
              <li><a href="javascript:;" class="jsPHReversePayment">Reverse Payment</a></li>
              <li><a href="javascript:;" class="jsPHDelPayment">Delete Payment</a></li>
              <li><a href="javascript:;" class="jsRefreshPaymentList">Refresh List</a></li>
            </ul>
          </div></div></div>
      <div class="rowActionFooter"><div class="rowActionFooterRight"><div class="rowActionFooterInner"></div></div></div>
    </div>

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
            <td class="withHoldDisabled"></td>
            <td></td>
            <td class="lastCol"></td>
          </tr>
        </tbody>
      </table>
      <table class="isHidden" id="versionEditTemplate">
        <tbody>
          <tr class="entriesEditRow">
            <td class="blankCell firstCol">&nbsp;</td>
            <td><input name="bDate" type="text" class="text bDate" value=""/></td>
            <td><input name="eDate" type="text" class="text eDate" value=""/></td>
            <td><select class="rType" typeName="retirementTypes">
              </select></td>
            <td><select typeName="periodTypes" class="pType">
              </select></td>
            <td><select class="aType" typeName="appointmentTypes">
              </select></td>
            <td><select class="sType" typeName="serviceTypes" >
              </select></td>
            <td><input name="amount" type="text" class="text amount" value=""/></td>
            <td><select class="payType" typeName="payTypes">
              </select></td>
      <td class="withHoldDisabled">
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
            <td class="withHoldDisabled"></td>
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
