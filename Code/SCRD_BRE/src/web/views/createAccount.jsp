<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The create account page, used for the first step of creating basic information.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
  <head>
    <base href="<%=basePath%>"/>
    <!-- title -->
    <title>OPM - Create Basic Information</title>
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
    <script type="text/javascript" src="<c:url value="/js/big.min.js"/>"></script>
    <script type="text/javascript">
	 var rootUrl = '${ctx}' + '/';
	 
	</script>
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
            <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab as my home page</label>
            <a href="javascript:;" class="jsShowTips">?</a>
          </div>
        </div>

        <div class="progressStepsBar">
          <ul>
            <li class="firstStep currentStep">Basic Information</li>
            <li class="step2 step1-2"><a href="javascript:;">Service History</a></li>
            <li class="step3 step1-3"><a href="javascript:;">Notes</a></li>
            <li class="lastStep step1-4"><a href="javascript:;">Finish</a></li>
          </ul>
          <div class="corner cornerTl"></div>
          <div class="corner cornerTr"></div>
        </div>
        <div class="basicInfoPanel roundedGrayPanel">
          <div class="fullRowField">
            <p class="fieldLabel">Form submitted:</p>
            <input name="formType" type="radio" value="1" id="formCsrs" />
            <label class="radioLabel" for="formCsrs">2803 (CSRS)</label>
            <input name="formType" type="radio" id="formFers" value="2"/>
            <label class="radioLabel" for="formFers">3108 (FERS)</label>
          </div>
          <div class="halfCol">
            <div class="halfRowField">
              <label for="lastNameL"><p class="fieldLabel">Last Name <span class="reqMark">*</span> :</p></label>
              <input name="lastName" id="lastNameL" type="text" class="text holder" maxlength="128" id="lastNameL"/>
            </div>
            <div class="halfRowField">
              <label for="firstNameL"><p class="fieldLabel">First Name <span class="reqMark">*</span> :</p></label>
              <input name="firstName" id="firstNameL" type="text" class="text holder" maxlength="128" id="firstNameL"/>
            </div>
            <div class="halfRowField">
              <label for="middleInitialL"><p class="fieldLabel">Middle Name :</p></label>
              <input name="middleInitial" type="text" class="text midLength holder" maxlength="128" id="middleInitialL"/>
            </div>
            <div class="halfRowField">
              <label for="suffixL"><p class="fieldLabel">Suffix :</p></label>
              <span class="selectWrapper midLength"><select name="suffix" typeName="suffixes" id="suffixL" class="midLength holder">
                </select></span>
            </div>
            <div class="halfRowField">
              <label for="birthDateL"><p class="fieldLabel">Birth Date <span class="reqMark">*</span> :</p></label>
              <input name="birthDate" type="text" id="birthDateL" class="text midLength holder birthDate22"/>
            </div>
            <div class="halfRowField">
              <label for="ssn1L"><p class="fieldLabel">SSN <span class="reqMark">*</span> :</p></label>
              <input name="ssn1" id="ssn1L" type="text" class="text ssn1 ssn advanceSsn1" maxlength="3"/><span class="sep">-</span><input name="ssn2" type="text" class="text ssn2 ssn advanceSsn2" maxlength="2"/><span class="sep">-</span><input name="ssn3" type="text" class="text ssn3 ssn advanceSsn3" maxlength="4"/>
            </div>
            <div class="halfRowField phoneField">
              <label for="phoneInputL"><p class="fieldLabel">Telephone :</p></label>
              <input name="phoneInput" id="phoneInputL" type="text" class="text phoneInput phone" maxlength="20" onkeypress='return  validatePhoneInput(event);'/><span class="sep">-</span><input name="phoneExt" type="text" class="text phone phoneExt" onkeypress='return  validatePhoneInput(event);'/>
              <span class="extNote">Ext</span>
            </div>
            <div class="halfRowField">
              <label for="emailL"><p class="fieldLabel">Email :</p></label>
              <input name="email" id="emailL" type="text" class="text holder" maxlength="128"/>
            </div>
            <div class="halfRowField">
              <label for="titleL"><p class="fieldLabel">Title :</p></label>
              <input name="title" id="titleL" type="text" class="text holder" maxlength="20"/>
            </div>
            <div class="halfRowField">
              <label for="departmentCodeL"><p class="fieldLabel">Agency / Dept. Code :</p></label>
              <input name="departmentCode" id="departmentCodeL" type="text" class="text midLength holder" maxlength="2"/>
            </div>
          </div>
          <div class="halfCol rightHalfCol">
            <div class="halfRowField">
              <label for="street1L"><p class="fieldLabel">Address #1 <span class="reqMark">*</span> :</p></label>
              <input name="street1" type="text" id="street1L" class="text address" maxlength="40"/>
            </div>
            <div class="halfRowField">
              <label for="street2L"><p class="fieldLabel">Address #2 :</p></label>
              <input name="street2" type="text" id="street2L" class="text address" maxlength="40"/>
            </div>
            <div class="halfRowField">
              <label for="street3L"><p class="fieldLabel">Address #3 :</p></label>
              <input name="street3" type="text" id="street3L" class="text address" maxlength="40"/>
            </div>
            <div class="halfRowField">
              <label for="street4L"><p class="fieldLabel">Address #4 :</p></label>
              <input name="street4" type="text" id="street4L" class="text address" maxlength="40"/>
            </div>
            <div class="halfRowField">
              <label for="street5L"><p class="fieldLabel">Address #5 :</p></label>
              <input name="street5" type="text" id="street5L" class="text address" maxlength="40"/>
            </div>
            <div class="halfRowField">
              <label for="cityL"><p class="fieldLabel">City <span class="reqMark">*</span> :</p></label>
              <input name="city" type="text" id="cityL" class="text midLength address" maxlength="20"/>
            </div>
            <div class="halfRowField">
              <label for="stateL"><p class="fieldLabel">State/ Province/ Region <span class="reqMark">*</span> :</p></label>
              <span class="selectWrapper midLength"><select typeName="states" id="stateL" name="state" class="address addressSelect">
                </select></span>
            </div>
            <div class="halfRowField">
              <label for="zipCodeL"><p class="fieldLabel">ZIP/Postal Code <span class="reqMark">*</span> :</p></label>
              <input name="zipCode" type="text" id="zipCodeL" class="text midLength address" onkeypress='return  validateZipInput(event);' maxlength="10"/>
            </div>
            <div class="halfRowField">
              <label for="countryL"><p class="fieldLabel">Country :</p></label>
              <span class="selectWrapper"><select name="country" id="countryL" typeName="countries" class="address countriesSelect">
                </select></span>
            </div>
            <div class="halfRowField">
              <label for="geoCodeL"><p class="fieldLabel">GEO code :</p></label>
              <input name="geoCode" type="text" id="geoCodeL" class="text midLength holder" maxlength="10"/>
            </div>
            <p class="sectionTitle">Location of Employment</p>
            <div class="halfRowField">
              <label for="cityOfEmploymentL"><p class="fieldLabel">City :</p></label>
              <input name="cityOfEmployment" type="text" id="cityOfEmploymentL" class="text midLength holder" maxlength="20"/>
            </div>
            <div class="halfRowField">
              <label for="stateOfEmploymentL"><p class="fieldLabel">State :</p></label>
              <span class="selectWrapper midLength"><select typeName="states" id="stateOfEmploymentL" name="stateOfEmployment" class="holder stateOfEmployment">
                </select></span>
            </div>
          </div>
          <div class="clear"></div>
          <div class="corner cornerBl"></div>
          <div class="corner cornerBr"></div>
        </div>
        <div class="stepsBtnWrapper clear">
          <a href="javascript:;" class="priBtn fLeft jsCancelCreateAccount"><span><span>Cancel</span></span></a>
          <a href="javascript:;" class="priBtn fRight nextBtn jsStep1Next"><span><span>Next<i class="nextArrow"></i></span></span></a>
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
                  <td>- You have 1 Record in <a href="workQueue/view">Work Queue.</a><br/>- You have 791 <a href="Suspense.html">Payments in Suspense</a>.</td>
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
  </body>
</html>