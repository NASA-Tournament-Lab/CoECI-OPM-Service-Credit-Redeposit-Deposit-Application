<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
    
    This is the demo page for the system assembly. Shows the action result.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <title>Accounts operation result</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <div id="container">
            <div id="content">
                <h1>Account:</h1>
                
                <h3><c:out value="${msg}" /></h3>

                <c:if test="${not empty account}">
                <div>
                    <p>
                    <ul>
                        <li>ID: <c:out value="${account.id}" /></li>
                        <li>Claim Number: <c:out value="${account.claimNumber}" /></li>
                        <li>Claim Officer: <c:out value="${account.claimOfficer}" /></li>
                        <li>Plan Type: <c:out value="${account.planType}" /></li>
                        <li>Form Type: <c:out value="${account.formType.name}" /></li>
                        <li>Account Status: <c:out value="${account.status.name}" /></li>
                        <li>Account Holder: <c:out value="${account.holder.firstName}" /> <c:out value="${account.holder.lastName}" /></li>
                        <li>Returned From Records Date: <c:out value="${account.returnedFromRecordsDate}" /></li>
                        <li>Deleted: <c:out value="${account.deleted}" /></li>
                    </ul>
                    </p>
                </div>
                </c:if>

            </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>
