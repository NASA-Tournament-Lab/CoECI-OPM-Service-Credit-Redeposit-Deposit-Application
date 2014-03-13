<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
    
    This is the payment result page for the OPM - Data Services - Account and Payment Services Assembly.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <title>Payment Service Test</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <div id="container">
            <div id="content">
                <h1>The Payment:</h1>
                <ul>
                    <li>ID: <c:out value="${payment.id}" /></li>
                    <li>Account Id: <c:out value="${payment.accountId}" /></li>
                    <li>Batch Number: <c:out value="${payment.batchNumber}" /></li>
                    <li>Block Number: <c:out value="${payment.blockNumber}" /></li>
                    <li>Sequence Number: <c:out value="${payment.sequenceNumber}" /></li>
                    <li>Payment Status: <c:out value="${payment.paymentStatus.name}" /></li>
                    <li>Claim Number: <c:out value="${payment.claimNumber}" /></li>
                    <li>Deposit Date: <c:out value="${payment.depositDate}" /></li>
                    <li>Amount: <c:out value="${payment.amount}" /></li>
                    <li>Ssn: <c:out value="${payment.ssn}" /></li>
                    <li>Claimant: <c:out value="${payment.claimant}" /></li>
                    <li>Import Id: <c:out value="${payment.importId}" /></li>
                    <li>Sequence: <c:out value="${payment.sequence}" /></li>
                    <li>Apply To: <c:out value="${payment.applyTo.name}" /></li>
                    <li>Apply To GL: <c:out value="${payment.applyToGL}" /></li>
                    <li>Note: <c:out value="${payment.note}" /></li>
                    <li>Master Account Status: <c:out value="${payment.masterAccountStatus.name}" /></li>
                    <li>Transaction Key: <c:out value="${payment.transactionKey}" /></li>
                    <li>Approval User: <c:out value="${payment.approvalUser}" /></li>
                    <li>Approval Status: <c:out value="${payment.approvalStatus}" /></li>
                    <li>Approval Reason: <c:out value="${payment.approvalReason}" /></li>
                    <li>Payment Type: <c:out value="${payment.paymentType}" /></li>
                </ul>
            </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>
