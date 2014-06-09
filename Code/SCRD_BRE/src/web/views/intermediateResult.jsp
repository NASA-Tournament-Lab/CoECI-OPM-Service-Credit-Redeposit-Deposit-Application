<%--
  - Author: yedtoss
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The expanded calculation result page.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <!-- title -->
        <title>OPM - Account Detail - Show Calculation</title>
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

        <!-- css file for IE7 -->
     <!--[if IE 7]><link rel="stylesheet" href="<c:url value="/css/ie7.css"/>" media="all" type="text/css" /><![endif]-->
        <script type="text/javascript">
            $(function() {
                $('.dollar').formatCurrency();
            });
        </script>

    </head>
    <body>

        <div id="depTblBody"> </div>
        
        
    </body>
</html>