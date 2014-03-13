<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The help page.
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
    <title>Service Credit Help</title>

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

    <script type="text/javascript" src="${ctx}/js/help.js"></script>

    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

</head>
<body>
    <div id="wrapper">
        <%@ include file="include/header.jsp"%>

        <div id="content">
            <p class="breadcurmb"><a href="javascript:;" id="breadcurmbLink">Help</a><span id="searchBreadcurmb" class="searchResult">Search Results</span><span class="articleTitle isHidden"></span></p>
            <div class="pageTitleArea">
                <h2 class="pageTitle">Help</h2>
                <div class="setHomeLink">
                    <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
                    <a href="javascript:;" class="jsShowTips">?</a>
                </div>
            </div>

            <div class="grayPanel helpPanel" id="helpPage">
                <div class="panelHeader">
                    <div class="helpSearchSummary searchResult fLeft isHidden">Showing Search Results for <strong>Lorem Ipsum</strong></div>
                    <div class="helpArticleTitle articleTitle fLeft isHidden"></div>
                    <div class="helpSearchBox fRight">
                        <input name="helpSearch" type="text" class="text helpSearchInput" value="" maxlength="30"/>
                        <span class="placeholderTxt isHidden">What do you need help with?</span>
                        <a href="javascript:;" class="priBtn jsSearchHelp"><span><span>Search</span></span></a>
                    </div>
                    <div class="corner cornerTl"></div>
                    <div class="corner cornerTr"></div>
                </div>

                <div class="panelBody roundedGrayPanel">
                    <ul class="helpItemList">
                    </ul>

                    <div class="helpArticleWrapper isHidden">

                    </div>
                    <br/>
                    <div class="relatedTopicsWrapper isHidden">
                        <p><strong>Related Topics</strong></p>
                        <p id="relatedTopics">
                        </p>
                    </div>

                    <div class="corner cornerBl"></div>
                    <div class="corner cornerBr"></div>
                </div>
            </div>
            <!-- .grayPanel -->
        </div>
        <!-- #content -->

        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- #footer -->
    </div>
    <!-- #wrapper -->

    <div class="alpha"></div>

    <%@ include file="include/notificationsPopup.jsp"%>
</body>
</html>