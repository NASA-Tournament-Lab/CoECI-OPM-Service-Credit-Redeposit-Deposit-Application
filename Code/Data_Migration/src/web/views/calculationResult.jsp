<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The expanded calculation result page.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <!-- title -->
        <title>OPM - Account Detail</title>
        <link rel="shortcut icon" href="<c:url value="/i/logo-opm-gray.png"/>" type="image/x-icon" />

        <!-- metatags -->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <!-- External CSS -->
        <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.3.custom.css"/>" media="all" />
        <link rel="stylesheet" href="<c:url value="/css/screen.css"/>" media="all" />

        <!-- JS lib/plugins-->
        <script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
        <script type='text/javascript' src='<c:url value="/js/jquery-ui-1.10.3.custom.min.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/js/jquery.formatCurrency-1.4.0.min.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/js/merge-sort.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/js/sortable.js"/>'></script>

        <!-- css file for IE7 -->
     <!--[if IE 7]><link rel="stylesheet" href="<c:url value="/css/ie7.css"/>" media="all" type="text/css" /><![endif]-->
        <script type="text/javascript">
            $(function() {
                $('.dollar').formatCurrency();
            });
        </script>

    </head>
    <body class="expandCalScreen">
        <div class="scrollTblArea expandCalScreen">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" id="depTbl" class="stdTbl validateResultTbl sortable">
                <colgroup>
                    <col class="blankCol"/>
                    <col class="col1"/>
                    <col class="col2"/>
                    <col class="col3"/>
                    <col class="col4"/>
                    <col class="col5"/>
                    <col class="col6"/>
                    <col class="col7"/>
                    <col class="col8"/>
                </colgroup>
                <thead>
                    <tr>
                        <th class="unsortable firstCol">&nbsp;</th>
                        <th class="defaultSortCol defaultSortDown asDate">Begin Date</th>
                        <th class="asDate">End Date</th>
                        <th class="asDate">Mid Point</th>
                        <th class="asDate">Effective Date</th>
                        <th class="asString">Period Type</th>
                        <th class="asNumeric">Deduction Amount</th>
                        <th class="asNumeric">Total Interest</th>
                        <th class="asNumeric">Payments Applied</th>
                        <th class="lastCol asNumeric">Balance</th>
                    </tr>
                </thead>
                
                <tbody>
                    <c:forEach items="${items}" var="each">
                        <tr>
                            <td class="blankCell firstCol">&nbsp;</td>
                            <td><fmt:formatDate value="${each.startDate}" pattern="MM/dd/yyyy"/></td>
                            <td><fmt:formatDate value="${each.endDate}" pattern="MM/dd/yyyy"/></td>
                            <td><fmt:formatDate value="${each.midDate}" pattern="MM/dd/yyyy"/></td>
                            <td><fmt:formatDate value="${each.effectiveDate}" pattern="MM/dd/yyyy"/></td>
                            <td>${each.periodType.name}</td>
                            <td class="dollar">${each.deductionAmount}</td>
                            <td class="dollar">${each.totalInterest}</td>
                            <td class="dollar">${each.paymentsApplied}</td>
                            <td class="lastCol dollar">${each.balance}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
