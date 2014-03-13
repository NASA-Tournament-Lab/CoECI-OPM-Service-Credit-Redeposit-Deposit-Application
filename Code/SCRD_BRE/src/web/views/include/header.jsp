<%--
  - Author: sparemax,liuliquan,TCSASSEMBLER
  - Version: 1.2
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The page header.
  - Change log:
  -         1.1 Add the common viewAccountSearchFormWrapper DIV
  - Change log:
  -         1.2 Remove scriptlet and use JSTL tag instead
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <input id="context" type="hidden" name="context" value="${pageContext.request.contextPath}"/>
        <div id="header">
            <div class="banner">
              <h1><img src="<c:url value="/i/opm.png"/>" alt="United States Office of Personnel Management" width="130" height="129" />Service Credit Deposit/Redeposit System</h1>
                <div class="headerUserInfo aRight">
                  <p class="welcomeMsg">Welcome <span></span></p>
                    <p class="userMeta">
                        <label>Your Role:</label> <span class="roleDesc"> </span><br />
                        <!--Removed: <label>Last Login:</label> Monday, 13th May, 2013 15:00:00-->
                    </p>
                    <p class="notifications">
                        <label>Notifications:</label> You have <a href="javascript:;" class="jsShowNotifications"><span class="notificationNum"><span><span>0</span></span></span>New Notifications</a>
                    </p>
                </div>
            </div>
            <!-- .banner -->
            <div class="topNav">
                <ul>
                    <li class="${VIEW_ACCOUNT?'':'isHidden'}"><a href="javascript:;" class="jsShowSearchAccountPanel">View Account</a></li>
                    <li class="${CREATE_NEW_ACCOUNT?'':'isHidden'}"><a href="<c:url value="/account/viewCreate?step=createAccount&requireNew=true"/>">Create New Account</a></li>
                    <li class="${WORK_QUEUE?'':'isHidden'}"><a href="<c:url value="/workQueue/view"/>">Work Queue <span class="notificationNum"><span><span></span></span></span></a></li>
                    <li class="${REPORTS?'':'isHidden'}"><a href="<c:url value="/report/view"/>">Reports</a></li>
                    <li class="${SUSPENSE?'':'isHidden'}"><a href="<c:url value="/suspension/view"/>">Suspense <span class="notificationNum"><span><span></span></span></span></a></li>
                    <li class="${APPROVAL?'':'isHidden'}"><a href="<c:url value="/approval/view"/>">Approval <span class="notificationNum"><span><span></span></span></span></a></li>
                    <li class="${PAYMENTS?'':'isHidden'}"><a href="<c:url value="/payment/view"/>">Payments</a></li>
                    <li class="${TOOLS?'':'isHidden'}"><a href="<c:url value="/tools/view"/>">Tools</a></li>
                    <li class="${ADMIN?'':'isHidden'}"><a href="<c:url value="/admin/view"/>">Admin</a></li>
                    <li class="${NOTIFICATION_LOG?'':'isHidden'}"><a href="<c:url value="/notificationLog/view"/>">Notification Log</a></li>
                    <li class="last ${HELP?'':'isHidden'}"><a href="<c:url value="/help/view"/>">Help</a></li>
                </ul>
            </div>
            <!-- .topNav -->
        </div>
        <!-- #header -->
        <div class="viewAccountSearchFormWrapper isHidden"><div class="viewAccountSearchFormInner">
          <div class="viewAccountSearchForm">
            <a class="jsCloseViewAccount">View Account</a>
            <div class="fieldRow">
              <label for="claimNumberL"><p class="fieldLabel">Claim Number <span>:</span></p></label>
              <input name="claimNumber" type="text" id="claimNumberL" class="text filter" title="input the claim number"/><span class="orSep">OR</span>
            </div>
            <div class="fieldRow">
              <label for="ssn1L"><p class="fieldLabel">SSN <span>:</span></p></label>
              <input name="ssn1" title="Enter the ssn number in the format XXX-XX-XXXX" id="ssn1L" type="text" maxlength="3" class="text ssn searchSsn1" onkeypress='return  validateNumberInput(event);'/><span class="sep">-</span><input name="ssn2" title="Enter the ssn number in the format XXX-XX-XXXX" maxlength="2" type="text" class="text ssn searchSsn2" onkeypress='return  validateNumberInput(event);'/><span class="sep">-</span><input name="ssn3" title="Enter the ssn number in the format XXX-XX-XXXX" maxlength="4" type="text" class="text ssn searchSsn3" onkeypress='return  validateNumberInput(event);'/><span class="orSep">OR</span>
            </div>
            <div class="fieldRow">
              <label for="firstNameL"><p class="fieldLabel">First Name <span>:</span></p></label>
              <input name="firstName" id="firstNameL" type="text" title="input first name" class="text filter"/><span class="orSep">AND / OR</span><label for="middleNameL"><span class="birthLable">Middle Initial&nbsp;&nbsp;:</span></label><input name="middleName" id="middleNameL" type="text" class="text filter" title="input middle name"/><span class="orSep  filter">AND / OR</span>
            </div>
            <div class="fieldRow birthRow">
              <label for="lastNameL"><p class="fieldLabel">Last Name <span>:</span></p></label>
              <input name="lastName" id="lastNameL" type="text" class="text filter" title="input last name"/><span class="orSep">AND / OR</span><label for="bMonthL"><span class="birthLable">Birth Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</span></label><input name="bMonth" title="birth month" id="bMonthL" type="text" class="text dpMonth date" maxlength="2" onkeypress='return  validateNumberInput(event);'/><span class="sep birthSep">/</span><input title="birth day" name="bDay" type="text" class="text date dpDay" maxlength="2" onkeypress='return  validateNumberInput(event);'/><span class="sep birthSep">/</span><input title="birth year" name="bYear" type="text" id="bYearSearch" class="text date dpYear" maxlength="4" onkeypress='return  validateNumberInput(event);'/><span class="datePickerSep"><input title="Choose the birth date." name="dpInput" type="text" class="datePickerInput"/></span><span class="dateFieldNote">(mm/dd/yyyy)</span>
            </div>
            <div class="fieldRow checkRow">
              <input name="excludeHistory" id="excludeFromSearch" type="checkbox" value="excludeFromSearch" /> <label for="excludeFromSearch">Exclude History From Search</label>
            </div>
            <div class="noRecordMsg clear isHidden">No Record Found</div>
            <div class= "btnWrapper clear">
              <a href="javascript:;" class="priBtn jsSearchAccount"><span><span>Search</span></span></a>
              <a href="javascript:;" class="priBtn jsClearSearchAccount"><span><span>Clear Filter</span></span></a>
              <a href="javascript:;" class="priBtn jsCancelSearchAccount"><span><span>Cancel</span></span></a>
            </div>
          </div></div>
      </div>
      <!-- .viewAccountSearchFormWrapper -->
