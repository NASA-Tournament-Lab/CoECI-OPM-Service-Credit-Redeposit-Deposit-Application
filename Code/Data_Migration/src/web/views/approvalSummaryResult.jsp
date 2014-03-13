<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
    
    This is the demo page for the system assembly.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <title>OPM - Data Services - Account and Payment Services Assembly</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <div id="container">
            <div id="content">
                
            	  <h1>Approval Summary</h1>
                <div>
                <ul>
                    <li>Pending Payment Count: <c:out value="${approvalSummary.pendingPaymentCount}" /></li>
                    <li>Interest Adjustment Count: <c:out value="${approvalSummary.interestAdjustmentCount}" /></li>
                    <li>Payment MoveCount: <c:out value="${approvalSummary.paymentMoveCount}" /></li>
                    <li>Total Count: <c:out value="${approvalSummary.totalCount}" /></li>
                </ul>
                </div>
            </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>
