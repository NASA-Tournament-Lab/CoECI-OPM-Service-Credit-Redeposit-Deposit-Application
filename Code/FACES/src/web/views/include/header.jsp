<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The page header.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <input id="context" type="hidden" name="context" value="${pageContext.request.contextPath}"/>
        <div id="header">
            <div class="banner">
              <h1><img src="<c:url value="/i/opm.png"/>" alt="United States Office of Personnel Management" width="130" height="129" />Service Credit Deposit/Redeposit System</h1>
                <div class="headerUserInfo aRight">
                  <p class="welcomeMsg">Welcome <a href="javascript:;"> </a></p>
                    <p class="userMeta">
                        <label>Your Role:</label> <span class="roleDesc"> </span><br />
                    </p>
                    <p class="notifications">
                        <label>Notifications:</label> You have <a href="javascript:;" class="jsShowNotifications"><span class="notificationNum"><span><span>0</span></span></span>New Notifications</a>
                    </p>
                </div>
            </div>
            <!-- .banner -->
            <div class="topNav">
                <ul>
                    <li><a href="javascript:;" class="jsShowSearchAccountPanel">View Account</a></li>
                    <li><a href="<c:url value="/faces/viewPaymentStatementPrint"/>">Payment Statement Print</a></li>
                </ul>
            </div>
            <!-- .topNav -->
        </div>
        <!-- #header -->
        <div class="viewAccountSearchFormWrapper isHidden"><div class="viewAccountSearchFormInner">
          <div class="viewAccountSearchForm">
            <a class="jsCloseViewAccount">View Account</a>
            <div class="fieldRow">
              <p class="fieldLabel">Claim Number <span>:</span></p>
              <input name="claimNumber" type="text" class="text filter"/><span class="orSep">OR</span>
            </div>
            <div class="fieldRow">
              <p class="fieldLabel">SSN <span>:</span></p>
              <input name="ssn1" type="text" class="text ssn searchSsn1" onkeypress='return  validateNumberInput(event);'/><span class="sep">-</span><input name="ssn2" type="text" class="text ssn searchSsn2" onkeypress='return  validateNumberInput(event);'/><span class="sep">-</span><input name="ssn3" type="text" class="text ssn searchSsn3" onkeypress='return  validateNumberInput(event);'/><span class="orSep">OR</span>
            </div>
            <div class="fieldRow">
              <p class="fieldLabel">First Name <span>:</span></p>
              <input name="firstName" type="text" class="text filter"/><span class="orSep">AND / OR</span><span class="birthLable">Middle Initial&nbsp;&nbsp;:</span><input name="middleName" type="text" class="text filter"/><span class="orSep  filter">AND / OR</span>
            </div>
            <div class="fieldRow birthRow">
              <p class="fieldLabel">Last Name <span>:</span></p>
              <input name="lastName" type="text" class="text filter"/><span class="orSep">AND / OR</span><span class="birthLable">Birthdate&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</span><input name="birthDate" type="text" class="text midLength datePicker"/>
            </div>
            <div class="fieldRow checkRow">
              <input name="excludeHistory" id="excludeFromSearch" type="checkbox" value="excludeFromSearch" /> <label for="excludeFromSearch">Exclude History From Search</label>
            </div>
            <div class="noRecordMsg clear isHidden">No Record Found</div>
            <div class= "btnWrapper clear">
              <a href="javascript:;" class="priBtn jsSearchAccount"><span><span>Search</span></span></a>
              <a href="javascript:;" class="priBtn jsClearSearchAccount"><span><span>Clear History</span></span></a>
              <a href="javascript:;" class="priBtn jsCancelSearchAccount"><span><span>Cancel</span></span></a>
            </div>
          </div></div>
      </div>
      <!-- .viewAccountSearchFormWrapper -->