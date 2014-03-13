<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
    
    This is the notification result page for the OPM - Data Services - Account and Payment Services Assembly.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <title>Service Announcement Service Test</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <div id="container">
            <div id="content">
                <h1>The Notification:</h1>
                <ul>
                    <li>ID: <c:out value="${notification.id}" /></li>
                    <li>Date: <c:out value="${notification.date}" /></li>
                    <li>Details: <c:out value="${notification.details}" /></li>
                    <li>Sender: <c:out value="${notification.sender}" /></li>
                    <li>Read: <c:out value="${notification.read}" /></li>
                    <li>Recipient: <c:out value="${notification.recipient}" /></li>
                    <li>Role: <c:out value="${notification.role.name}" /></li>
                </ul>
            </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>
