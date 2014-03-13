<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2013-2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The account search result page.
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
    <title>OPM - Account Search Result</title>
    <link rel="shortcut icon" href='<c:url value="/i/logo-opm-gray.png"/>' type="image/x-icon" />

    <!-- metatags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- External CSS -->
    <link rel="stylesheet" href='<c:url value="/css/jquery-ui-1.10.3.custom.css"/>' media="all" />
    <link rel="stylesheet" href='<c:url value="/css/screen.css"/>' media="all" />

    <!-- JS lib/plugins-->
    <script type="text/javascript" src='<c:url value="/js/jquery-1.10.2.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery-ui-1.10.3.custom.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/merge-sort.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/sortable.js"/>'></script>
    <script type="text/javascript" src='<c:url value="/js/json2.js"/>'></script>

    <script type="text/javascript" src='<c:url value="/js/jquery.zclip.js"/>'></script>

    <!-- external main js -->
    <script type="text/javascript" src='<c:url value="/js/script.js"/>'></script>
    <script type="text/javascript" src='<c:url value="/js/accountSearchResult.js"/>'></script>

    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="<c:url value="/css/ie7.css"/>" media="all" type="text/css" /><![endif]-->
    <script></script>

  </head>
  <body>
    <div id="wrapper">
      <%@ include file="include/header.jsp"%>
      <div id="content">
        <p class="breadcurmb"><span>Account Search Results</span></p>
        <div class="pageTitleArea">
          <h2 class="pageTitle">Account Search</h2>
          <div class="setHomeLink">
            <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
            <a href="javascript:;" class="jsShowTips">?</a>
          </div>
        </div>

        <div class="accountSearchSummary">
          <span class="foundRecordCount"></span>
          <div class="corner cornerTl"></div>
          <div class="corner cornerTr"></div>
        </div>
        <div class="accountSearchResultsWrapper">            	
          <table border="0" cellpadding="0" cellspacing="0" id="accountSearchResultTbl" width="100%" class="stdTbl sortable" tabindex="0">
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
                <th class="firstCol">Claim Number</th>
                <th>First Name</th>
                <th>Middle Initial</th>
                <th>Last Name</th>
                <th>SSN</th>
                <th class="lastCol">Birth Date</th>
              </tr>
            </thead>
            <tbody class="result-tbody">
              
            </tbody>
          </table>
          <div class="pagination">
            <div class="perPage fLeft">
              <label for="showAccountResultR"><span>Show : </span></label> 
              <select id="showAccountResultR">
                <option value="5">5</option>
                <option value="10" selected>10</option>
                <option value="25">25</option>
                <option value="50">50</option>
                <option value="0">All</option>
              </select>
              <span>per page</span> 
            </div>
            <div class="paginationLinks fRight">
              <a href="javascript:;" class="toPrev toPrevDisabled">Prev</a>
              <a href="javascript:;" class="toPage current">1</a>
              <a href="javascript:;" class="toPage">2</a>
              <a href="javascript:;" class="toPage">3</a>
              <a href="javascript:;" class="toNext">Next</a>
            </div>
            <span class="paginationLabel fRight"><span class="startCount">1</span>-<span class="endCount">10</span> of <span class="totalCount">30</span></span>    
          </div>
        </div>


      </div>
      <!-- #content --> 
      <jsp:include page="include/footer.jsp"></jsp:include>

      <div id="loadingOverlay2" class="isHidden">
        <img src="i/loader.gif" alt="Loading..." />
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
  </body>
</html>