<%--
  - Author: sparemax
  - Version: 1.1
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  - Change:
  - V1.1 add error title for OPM - Bug Fix Part 1 Assembly
  - Description: The error page.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" import="java.io.*" isELIgnored="false"%>
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
  <base href="<%=basePath%>">
	<!-- title -->
	<title>OPM - SCRD - Frontend Initial Module Assembly</title>
	
	<!-- metatags -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel="shortcut icon" href="${ctx}/i/logo-opm-gray.png"type="image/x-icon" />
	
	<!-- External CSS -->
  <link rel="stylesheet" href="${ctx}/css/jquery-ui-1.10.3.custom.css" media="all" />
	<link rel="stylesheet" href="${ctx}/css/screen.css" media="all" />
	
	<!-- JS lib/plugins-->
	<script type="text/javascript" src="${ctx}/js/jquery-1.10.2.min.js"></script>
  <script type='text/javascript' src='${ctx}/js/jquery-ui-1.10.3.custom.min.js'></script>
  <script type='text/javascript' src='${ctx}/js/sortable.js'></script>
    
  <!-- external main js -->
	<script type="text/javascript" src="${ctx}/js/script.js"></script>
	
	<!-- css file for IE7 -->
	<!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

</head>
<body>
	      <div id="wrapper">
        <%@ include file="views/include/header.jsp"%>

        <div class="viewAccountSearchFormWrapper isHidden"><div class="viewAccountSearchFormInner">
            <div class="viewAccountSearchForm">
            	<a class="jsCloseViewAccount">View Account</a>
                <div class="fieldRow">
                    <p class="fieldLabel">Claim Number <span>:</span></p>
                    <input name="searchCNum" type="text" class="text"/><span class="orSep">OR</span>
                </div>
                <div class="fieldRow">
                    <p class="fieldLabel">SSN <span>:</span></p>
                    <input name="searchSsn1" type="text" class="text searchSsn1"/><span class="sep">-</span><input name="searchSsn2" type="text" class="text searchSsn2"/><span class="sep">-</span><input name="searchSsn3" type="text" class="text searchSsn3"/><span class="orSep">OR</span>
                </div>
                <div class="fieldRow">
                    <p class="fieldLabel">First Name <span>:</span></p>
                    <input name="searchFName" type="text" class="text"/><span class="orSep">AND / OR</span><span class="birthLable">Middle Initial&nbsp;&nbsp;:</span><input name="searchMName" type="text" class="text searchMName"/><span class="orSep">AND / OR</span>
                </div>
                <div class="fieldRow birthRow">
                    <p class="fieldLabel">Last Name <span>:</span></p>
                    <input name="searchLName" type="text" class="text"/><span class="orSep">AND / OR</span><span class="birthLable">Birthdate&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</span><input name="bMonth" type="text" class="text dpMonth" maxlength="2"/><span class="sep birthSep">/</span><input name="bDay" type="text" class="text dpDay" maxlength="2"/><span class="sep birthSep">/</span><input name="bYear" type="text" class="text dpYear" maxlength="4"/><span class="datePickerSep"><input name="dpInput" type="text" class="datePickerInput" title="choose the birthdate"/></span><span class="dateFieldNote">(MM/DD/YYYY)</span>
                </div>
                <div class="fieldRow checkRow">
                    <input name="excludeFromSearch" id="excludeFromSearch" type="checkbox" value="excludeFromSearch" /> <label for="excludeFromSearch">Exclude History From Search</label>
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

        <div id="content">
                <br><h3>Error Details:</h3>
                <br>
                	<%
                	  StringWriter stringWriter = new StringWriter();
                	  PrintWriter printWriter = new PrintWriter(stringWriter);
                	  exception.printStackTrace(printWriter);
                	  out.println(stringWriter);
                	  %>
        </div>
        <!-- #content --> 

        <jsp:include page="views/include/footer.jsp"></jsp:include>
        <!-- #footer -->
	</div>
	<!-- #wrapper -->

	<div class="alpha"></div>
	<div class="popup wqAssignmentPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Work Queue Assignment</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Select one or more cases to assign</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .wqAssignmentPopup -->
    
    <div class="popup wqUnassignmentPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
                <h4 class="popupTitle">Work Queue Unassignment</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Select one or more cases to unassign</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .wqUnassignmentPopup -->
    
    <div class="popup accountAssignmentPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
                <h4 class="popupTitle">Account Assignment</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="leftAccountList fLeft">
                <ul class="accountList">
                </ul>
            </div>
            <div class="rightBtnWrapper fRight">
                <a href="javascript:;" class="priBtn jsDoReassignCase"><span><span>Assign Case</span></span></a>
                <a href="javascript:;" class="priBtn jsCancelReassignCase"><span><span>Cancel</span></span></a>
            </div>
            <div class="clear"></div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .accountAssignmentPopup -->
    
    <div class="popup notificationPopup isHidden">
        <div class="popupArrow"></div>
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
        	<div class="popupTitleWrapper">
        	    <h4 class="popupTitle">Notifications</h4>
                <div class="popupLinksWrapper">
                	<a href="javascript:;" class="popupLink notificationsViewAll">View All</a>
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