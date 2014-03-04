<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
    
    This is the demo page for the system assembly. Shows the update page.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <title>Account Update</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <div id="container">
            <div id="content">
                <h1>Account:</h1>
                
                <h3><c:out value="${msg}" /></h3>

                <div>
                <span style="color:red">Update an Account:</span>
                <form:form action="update" method="POST" modelAttribute="account">
                <form:hidden path="id"/>
                <form:hidden path="deleted"/>
                <ul>
                    <li><form:label path="id">ID:</form:label><c:out value="${account.id}" /></li>
                    <li><form:label path="claimNumber">Claim Number:</form:label><form:input path="claimNumber" /></li>
                    <li><form:label path="claimOfficer">Claim Officer:</form:label><form:input path="claimOfficer" maxlength="200"/></li>
                    <li><form:label path="planType">Plan Type:</form:label><form:input path="planType" /></li>
                    <li><form:label path="formType.id">Form Type:</form:label>
                    <form:select path="formType.id">
                        <form:option value="1">Form Type 1</form:option>
                        <form:option value="2">Form Type 2</form:option>
                        <form:option value="3">Form Type 3</form:option>
                        <form:option value="4">Form Type 4</form:option>
                        <form:option value="5">Form Type 5</form:option>
                    </form:select>
                    </li>
                    <li><form:label path="status.id">Account Status:</form:label>
                    <form:select path="status.id">
                        <form:option value="1">Account Status 1</form:option>
                        <form:option value="2">Account Status 2</form:option>
                        <form:option value="3">Account Status 3</form:option>
                        <form:option value="4">Account Status 4</form:option>
                        <form:option value="5">Account Status 5</form:option>
                    </form:select>
                    </li>
                    <li><form:label path="holder.id">Account Holder:</form:label>
                    <form:select path="holder.id">
                        <form:option value="1">Account Holder 1</form:option>
                        <form:option value="2">Account Holder 2</form:option>
                    </form:select>
                    </li>
                    <li><form:label path="returnedFromRecordsDate">Returned From Records Date (MM/DD/YYYY):</form:label><form:input path="returnedFromRecordsDate" /></li>
                    <li>Deleted: <c:out value="${account.deleted}" /></li>
                    <form:button value="Submit">Submit</form:button>
                </ul>
                </form:form>
                </div>

            </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>
