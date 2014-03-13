<%--
    Copyright (C) 2013 TopCoder Inc., All Rights Reserved.

    This is the demo page for testing controllers of the OPM - SCRD - Frontend Initial Module Assembly.
  - Change:
  - V1.1 multiple bug fixes for OPM - Bug Fix Part 1 Assembly
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>"/>
        <title>OPM - SCRD - Reporting Initial Module Assembly</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <div id="container">
            <div id="content">
                <h1>Lookup Controller Test</h1>
                <div>
                  <a href="lookup/paymentReversalReasons">Get All Payment Reversal Reasons</a> <br>
                  <a href="lookup/suffixes">Get All Suffixes</a> <br>
                  <a href="lookup/applicationDesignations">Get All Application Designations</a> <br>
                  <a href="lookup/states">Get All States</a> <br>
                  <a href="lookup/paymentStatuses">Get All Payment Statuses</a> <br>
                  <a href="lookup/serviceTypes">Get All Service Types</a> <br>
                  <a href="lookup/formTypes">Get All Form Types</a> <br>
                  <a href="lookup/claimOfficers">Get All Claim Officers</a> <br>
                  <a href="lookup/payTypes">Get All Pay Types</a> <br>
                  <a href="lookup/accountStatuses">Get All Account Statuses</a> <br>
                  <a href="lookup/roles">Get All Roles</a> <br>
                  <a href="lookup/retirementTypes">Get All Retirement Types</a> <br>
                  <a href="lookup/appointmentTypes">Get All Appointment Types</a> <br>
                  <a href="lookup/transferTypes">Get All Transfer Types</a> <br>
                  <a href="lookup/periodTypes">Get All Period Types</a> <br>
                  <a href="lookup/userStatuses">Get All User Statuses</a> <br>
                  <a href="lookup/countries">Get All Countries</a> <br>
                </div>
            <div id="footer">
                <p>&nbsp;</p>
            </div>
        </div>
    </body>
</html>