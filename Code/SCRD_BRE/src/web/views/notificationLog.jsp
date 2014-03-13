<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The notification log page.
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
    <title>Service Credit Notifications</title>

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

    <script type="text/javascript" src="${ctx}/js/notificationLog.js"></script>

    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

</head>
<body>
    <div id="wrapper">
        <%@ include file="include/header.jsp"%>

        <div id="content">
            <p class="breadcurmb"><a href="${ctx}/notificationLog/view">Notification Log</a><span>Notifications</span></p>
            <div class="pageTitleArea">
                <h2 class="pageTitle">Notifications</h2>
                <div class="setHomeLink">
                    <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
                    <a href="javascript:;" class="jsShowTips">?</a>
                </div>
            </div>

            <div class="tabsArea notiLogTab" id="notiLogPage">
                <div class="tabsBar whiteTabsBar">
                    <ul>
                        <li><a class="current" href="javascript:;" id="notificationsTab">Notifications</a></li>
                        <li><a href="javascript:;" id="errorsTab" >Errors</a></li>
                        <li><a href="javascript:;" id="infoTab" >Info</a></li>
                    </ul>
                    <a href="javascript:;" class="priBtn jsPrintNoti fRight"><span><span><i class="printIcon"></i>Print</span></span></a>
                </div>

                <div class="tabsBlock notificationsTab">
                    <div class="notiLogTblSummary isHidden">Displaying <strong><span class="startNum">1</span> - <span class="endNum">10</span> of <span class="totalNum">99</span></strong> Notifications:</div>

                    <table border="0" cellpadding="0" cellspacing="0" id="notiTbl" class="stdTbl" width="100%">
                        <colgroup>
                            <col class="col1"/>
                            <col class="col2"/>
                            <col class="col3"/>
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="firstCol defaultSortCol aCenter">Date / Time</th>
                                <th class="aCenter">Sent by</th>
                                <th class="lastCol aCenter">Notification</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>

                    <div class="paginationWrapper">
                        <div class="fLeft">
                            <label for="notiPageN"><span>Show</span></label>
                            <select id="notiPageN">
                                <option value="10" selected="selected">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                                <option value="25">25</option>
                            </select>
                            <label for="notiPageN"><span>per page</span></label>
                        </div>
                        <div class="fRight">
                        </div>
                    </div>
                </div>
                <!-- .notificationsTab -->

                <div class="tabsBlock errorsTab isHidden">
                    <div class="notiLogTblSummary isHidden">Displaying <strong><span class="startNum">1</span> - <span class="endNum">10</span> of <span class="totalNum">29</span></strong> Errors:</div>

                    <table border="0" cellpadding="0" cellspacing="0" id="notiErrorsTbl" class="stdTbl" width="100%">
                        <colgroup>
                            <col class="col1"/>
                            <col class="col2"/>
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="firstCol defaultSortCol aCenter">Date / Time</th>
                                <th class="lastCol aCenter">Errors</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>

                    <div class="paginationWrapper">
                        <div class="fLeft">
                            <span>Show</span>
                            <select>
                                <option value="5">5</option>
                                <option value="10" selected="selected">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="All">All</option>
                            </select>
                            <span>per page</span>
                        </div>
                        <div class="fRight">
                        </div>
                    </div>
                </div>
                <!-- .errorsTab -->

                <div class="tabsBlock infoTab isHidden">
                    <div class="notiLogTblSummary isHidden">Displaying <strong><span class="startNum">1</span> - <span class="endNum">10</span> of <span class="totalNum">29</span></strong> Info:</div>

                    <table border="0" cellpadding="0" cellspacing="0" id="notiInfoTbl" class="stdTbl" width="100%">
                        <colgroup>
                            <col class="col1"/>
                            <col class="col2"/>
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="firstCol defaultSortCol aCenter">Date / Time</th>
                                <th class="lastCol aCenter">Info</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>

                    <div class="paginationWrapper">
                        <div class="fLeft">
                            <span>Show</span>
                            <select>
                                <option value="5">5</option>
                                <option value="10" selected="selected">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="All">All</option>
                            </select>
                            <span>per page</span>
                        </div>
                        <div class="fRight">
                        </div>
                    </div>
                </div>
                <!-- .infoTab -->

            </div>
            <!-- .tabsArea -->

        </div>
        <!-- #content -->

        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- #footer -->
    </div>
    <!-- #wrapper -->

    <div class="alpha"></div>

    <div class="popup reportPlaceHolderPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Print list</h4>
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
    <!-- .reportPlaceHolderPopup -->

    <%@ include file="include/notificationsPopup.jsp"%>
</body>
</html>