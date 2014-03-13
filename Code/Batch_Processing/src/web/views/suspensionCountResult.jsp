<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
    
    This is the suspension count result page for the OPM - Data Services - Account and Payment Services Assembly.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <title>Suspension Service Test</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <div id="container">
            <div id="content">
                <h1>Suspension Count:</h1>
                <h3><c:out value="${suspensionCount}" /></h3>
            </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>
