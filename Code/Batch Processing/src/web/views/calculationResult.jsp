<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
    
    This is the calculation result page for the OPM - Data Services - Account and Payment Services Assembly. Shows the calculation result page.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <title>Calculation Execution Service Test</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <div id="container">
            <div id="content">
                <h1>Calculation Result:</h1>
                <ul>
                    <li>Calculation Status: <c:out value="${calculationResult.calculationStatus.name}" /></li>
                    <li>Official: <c:out value="${calculationResult.official}" /></li>
                    <li>Apply To Real Payments: <c:out value="${calculationResult.applyToRealPayments}" /></li>
                </ul>
            </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>
