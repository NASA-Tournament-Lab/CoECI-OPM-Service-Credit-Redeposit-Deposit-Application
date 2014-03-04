<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
    
    Changes in OPM - Data Services - Account and Payment Services Assembly 1.0:
      Removed calculation test.
    
    This is the demo page for the system assembly.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <title>Spring MVC Starter Application</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <div id="container">
            <div id="content">
                <h1>Account Test</h1>
                <div>
                <span style="color:red">Create an Account:</span>
                <form:form action="create" method="POST" modelAttribute="account">
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
                    <li><form:label path="returnedFromRecordsDate">Returned From Records Date (MM/DD/YYYY):</form:label><form:input path="returnedFromRecordsDate" value="11/15/2013" /></li>
                    <form:button value="Submit">Create</form:button>
                </ul>
                </form:form>
                </div>

                <div>
                <span style="color:red">Retrieve an Account:</span>
                <form  action="retrieve" method="get">
                <ul>
                    <li>ID of account to be retrieved: <input type="text" name="id"/></li>
                </ul>
                <input type="hidden" name="forUpdate" value="false" />
                <input type="submit" value="Retrieve"/>
                </form>
                </div>

                <div>
                <span style="color:red">Update an Account:</span>
                <form  action="retrieve" method="get">
                <ul>
                    <li>ID of account to be updated: <input type="text" name="id"/></li>
                </ul>
                <input type="hidden" name="forUpdate" value="true" />
                <input type="submit" value="Retrieve for Update"/>
                </form>
                </div>


                <div>
                <span style="color:red">Delete an Account:</span>
                <form  action="delete" method="post">
                <ul>
                    <li>ID of account to be deleted: <input type="text" name="id"/></li>
                </ul>
                <input type="submit" value="Delete"/>
                </form>
                </div>

            </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>
