<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The reports page
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
    <title>Report Printing Preview</title>
    <link rel="shortcut icon" href="<c:url value="/i/logo-opm-gray.png"/>" type="image/x-icon" />

    <!-- metatags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- External CSS -->
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.3.custom.css"/>" media="all" />
    <link rel="stylesheet" href="<c:url value="/css/jquery.treeview.css"/>" media="all" />
    <link rel="stylesheet" href="<c:url value="/css/screen.css"/>" media="all" />


	<script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>

    <!-- css file for IE7 -->
 <!--[if IE 7]><link rel="stylesheet" href="<c:url value="/css/ie7.css"/>" media="all" type="text/css" /><![endif]-->
    <script>
    	var dataInit = false;
    	var printed = false;
    	function initPreview(content) {
    		if (dataInit) {
    			return;
    		}
    		$('body').html(content);
    		dataInit = true;
    	}
    	$(document).ready(function() {
    		setInterval(function() {
    			if (dataInit && !printed) {
    				printed = true;
    				window.focus();
    				window.print();

    				if (window.opener && window.opener.openerCleanup) {
    					window.opener.openerCleanup();
    				}
    				
    				
    				window.close();
    				
    			}
    		}, 1000);
    	});
    </script>

  </head>
  <body>
  </body>
</html>
