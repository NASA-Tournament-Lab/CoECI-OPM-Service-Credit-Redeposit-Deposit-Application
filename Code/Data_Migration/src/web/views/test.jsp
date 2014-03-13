<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
    
    This is the demo page for the OPM - Data Services - Account and Payment Services Assembly.
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
                
            	  <h1>Payment Service Test</h1>
                <div>
                <span style="color:red">Create Payment:</span>
                <form:form action="createPayment" method="POST" modelAttribute="payment">
                <ul>
                    <li><form:label path="accountId">AccountId:</form:label><form:input path="accountId" /></li>
                    <li><form:label path="batchNumber">Batch Number:</form:label><form:input path="batchNumber" /></li>
                    <li><form:label path="blockNumber">Block Number:</form:label><form:input path="blockNumber" /></li>
                    <li><form:label path="sequenceNumber">Sequence Number:</form:label><form:input path="sequenceNumber" /></li>
                    <li><form:label path="paymentStatus.id">Payment Status:</form:label>
                    <form:select path="paymentStatus.id">
                        <form:option value="1">Payment Status 1</form:option>
                        <form:option value="2">Payment Status 2</form:option>
                        <form:option value="3">Payment Status 3</form:option>
                    </form:select>
                    </li>
                    <li><form:label path="claimNumber">Claim Number:</form:label><form:input path="claimNumber" /></li>
                    <li><form:label path="depositDate">Deposit Date (MM/DD/YYYY):</form:label><form:input path="depositDate" /></li>
                    <li><form:label path="amount">Amount:</form:label><form:input path="amount" /></li>
                    <li><form:label path="ssn">Ssn:</form:label><form:input path="ssn" /></li>
                    <li><form:label path="claimant">Claimant:</form:label><form:input path="claimant" /></li>
                    <li><form:label path="importId">Import Id:</form:label><form:input path="importId" /></li>
                    <li><form:label path="sequence">Sequence:</form:label><form:input path="sequence" /></li>
                    <li><form:label path="applyTo.id">Apply To:</form:label>
                    <form:select path="applyTo.id">
                        <form:option value="1">Application Designation 1</form:option>
                        <form:option value="2">Application Designation 2</form:option>
                        <form:option value="3">Application Designation 3</form:option>
                    </form:select>
                    </li>
                    <li><form:label path="applyToGL">Apply To GL (true/false):</form:label><form:input path="applyToGL" /></li>
                    <li><form:label path="note">Note:</form:label><form:input path="note" /></li>
                    <li><form:label path="masterAccountStatus.id">Master Account Status:</form:label>
                    <form:select path="masterAccountStatus.id">
                        <form:option value="1">Account Status 1</form:option>
                        <form:option value="2">Account Status 2</form:option>
                        <form:option value="3">Account Status 3</form:option>
                    </form:select>
                    </li>
                    <li><form:label path="transactionKey">Transaction Key:</form:label><form:input path="transactionKey" /></li>
                    <li><form:label path="approvalUser">Approval User:</form:label><form:input path="approvalUser" /></li>
                    <li><form:label path="approvalStatus">Approval Status:</form:label><form:input path="approvalStatus" /></li>
                    <li><form:label path="approvalReason">Approval Reason:</form:label><form:input path="approvalReason" /></li>
                    <li><form:label path="paymentType">Payment Type:</form:label><form:input path="paymentType" /></li>
                    	
                    <input type="submit" value="Create Payment"/>
                </ul>
                </form:form>
                </div>
                
            	  <h1>Service Announcement Service Test</h1>
                <div>
                <span style="color:red">Add Notification:</span>
                <form:form action="addNotification" method="POST" modelAttribute="notification">
                <ul>
                    <li><form:label path="date">Date (MM/DD/YYYY):</form:label><form:input path="date" /></li>
                    <li><form:label path="details">Details:</form:label><form:input path="details"/></li>
                    <li><form:label path="sender">Sender:</form:label><form:input path="sender" /></li>
                    <li><form:label path="recipient">Recipient:</form:label><form:input path="recipient" /></li>
                    <li><form:label path="role.id">Role:</form:label>
                    <form:select path="role.id">
                        <form:option value="1">Role 1</form:option>
                        <form:option value="2">Role 2</form:option>
                        <form:option value="3">Role 3</form:option>
                    </form:select>
                    </li>
                    	
                    <input type="submit" value="Add Notification"/>
                </ul>
                </form:form>
                </div>
                
            	  <h1>Approval Service Test</h1>
                <div>
                <span style="color:red">Get Approval Summary:</span>
                <form action="getApprovalSummary" method="GET">
                <ul>
                    <li>Approver: <input type="text" name="approver"/></li>
                    <input type="submit" value="Retrieve"/>
                </ul>
                </form>
                </div>
                
            	  <h1>Suspension Service Test</h1>
                <div>
                <span style="color:red">Get Suspension Count:</span>
                <form action="getSuspensionCount" method="GET">
                <ul>
                    <li>Suspender: <input type="text" name="suspender"/></li>
                    <input type="submit" value="Retrieve"/>
                </ul>
                </form>
                </div>
                
            	  <h1>Calculation Execution Service Test</h1>
                <div>
                <span style="color:red">Run Calculation:</span>
                <form action="runCalculation" method="GET">
                <ul>
                    <input type="submit" value="Run Calculation"/>
                </ul>
                </form>
                </div>
            </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>
