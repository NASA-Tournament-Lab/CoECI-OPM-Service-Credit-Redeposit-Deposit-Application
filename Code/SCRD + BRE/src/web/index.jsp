<%--
  - Author: sparemax, liuliquan, TCSASSEMBLER
  - Version: 1.2
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The index page.
  - Change log:
  -         1.1 Add the loading overlay
  - Change log:
  -         1.2 Add VIEW_ACCOUNT, CREATE_NEW_ACCOUNT main tab configuration.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <!-- title -->
    <title>OPM - Home</title>

    <!-- metatags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- External CSS -->
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.3.custom.css"/>" media="all" />
    <link rel="stylesheet" href="<c:url value="/css/screen.css"/>" media="all" />

    <!-- JS lib/plugins-->
    <script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
    <script type='text/javascript' src='<c:url value="/js/jquery-ui-1.10.3.custom.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/js/sortable.js"/>'></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.zclip.js"/>"></script>

    <!-- css file for IE7 -->
 <!--[if IE 7]><link rel="stylesheet" href="<c:url value="/css/ie7.css"/>" media="all" type="text/css" /><![endif]-->
    <script type="text/javascript">
      $(function() {
        var context = $('#context').val();
        $.ajax({
          url: context + '/common/currentUser',
          //url: context + '/common/tab',
          type: 'GET',
          cache: false,
          async: false,
          success: function(user) {
            if (user.defaultTab == 'WORK_QUEUE') {
              window.location = context + "/workQueue/view";
            } else if (user.defaultTab == 'APPROVAL') {
              window.location = context + "/approval/view";
            } else if (user.defaultTab == 'VIEW_ACCOUNT') {
              window.location = context + "/account/" + user.defaultTabAccountId + "/detail";
            } else if (user.defaultTab == 'CREATE_NEW_ACCOUNT') {
              window.location = context + "/account/viewCreate?step=createAccount";
            } else if (user.defaultTab == 'TOOLS') {
              window.location = context + "/tools/view";
            } else if (user.defaultTab == 'ADMIN') {
              window.location = context + "/admin/view";
            } else if (user.defaultTab == 'NOTIFICATION_LOG') {
              window.location = context + "/notificationLog/view";
            } else if (user.defaultTab == 'HELP') {
              window.location = context + "/help/view";
            } else if(user.defaultTab=='SUSPENSE') {
              window.location = context + "/suspension/view";
            } else if(user.defaultTab=='PAYMENTS') {
              window.location = context + "/payment/view";
            } else {
              window.location = context + "/account/viewCreate?step=createAccount";
            }


          },
          error: function(request, status, error) {
            alert("Failed to get home tab page: " + request.responseText);
          }
        });
      });

    </script>

    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

  </head>
  <body>
    <input id="context" type="hidden" name="context" value="${pageContext.request.contextPath}"/>
  </body>
</html>