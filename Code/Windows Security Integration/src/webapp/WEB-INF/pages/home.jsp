<%--
	Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
	
	@author TCSASSEMBLER
	@version 1.0
	
	This file contains the home page.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SPNEGO Test Page</title>
<script type="text/javascript" src="./js/jquery.tools.min.js"></script>
<link type="text/css" href="./css/style.css" rel="stylesheet" />
</head>
<body>
   <h1>Welcome  ${username}</h1>
   <p>AuthType:${authType}</p>
   <p>UserName:${username}</p>
   <p>Roles:</p>
   <ul>
   <c:forEach items="${roles}" var="role">
   	<li>${role}</li>
   </c:forEach>
   </ul>
   <p>only Batch Process can view tab1</p>
   <p>only Service Credit Reviewer can view tab2</p>
   <p>only Receivables Supervisor can view tab3</p>
	<!-- the tabs -->
	<ul class="css-tabs">
		<li><a href="./Tab?identifier=tab1">Tab 1</a></li>
		<li><a href="./Tab?identifier=tab2">Tab 2</a></li>
		<li><a href="./Tab?identifier=tab3">Tab 3</a></li>
	</ul>
	<!-- tab "panes" -->
	<div class="css-panes">
		<div style="display: block"></div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$("ul.css-tabs").tabs("div.css-panes > div",  {effect: 'ajax'});
		$("div.css-panes > div").load($("ul.css-tabs>li:first>a").prop("href"));
	});
</script>
</html>