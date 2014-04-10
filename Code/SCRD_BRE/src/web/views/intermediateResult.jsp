<%--
  - Author: yedtoss
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
        <title>OPM - Account Detail - Show Calculation</title>
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
                    <col class="col9"/>
                    <col class="col10"/>
                    <col class="col11"/>
                    <col class="col12"/>
                    <col class="col13"/>
                    <col class="col14"/>
                    <col class="col15"/>
                    <col class="col16"/>
                    <col class="col17"/>
                    <col class="col18"/>
                    <col class="col19"/>
                </colgroup>
                <thead>
                    <tr>
                        <th class="unsortable firstCol">&nbsp;</th>
                        <th class="defaultSortCol defaultSortDown asDate">Period Begin Date</th>
                        <th class="asDate">Period End Date</th>
                        <th class="asDate">Interest Begin Date</th>
                        <th class="asDate">Interest End Date</th>
                        <th class="asDate">Interest Calculated To Date</th>
                        <th class="asDate">Interest Accrual Date</th>
                        <th class="asString">Period Type</th>
                        <th class="asString">Retirement Type</th>
                        <th class="asNumeric">Interest Calculation Year</th>
                        <th class="asNumeric">Begin Balance With Interest</th>

                        <th class="asNumeric">Total Interest</th>
                        <th class="asNumeric">Interest Rate</th>
                        <th class="asNumeric">Interest Rate 2</th>
                        <th class="asNumeric">Composite Rate 1</th>
                        <th class="asNumeric">Composite Rate 2</th>
                        <th class="asNumeric">Number of days in period</th>
                        <th class="asNumeric">Number of days in period 2</th>
                        <th class="asNumeric">Start Year Factor</th>
                        <th class="lastCol asNumeric">Final Balance With Interest</th>
                    </tr>
                </thead>
                
                <tbody id="depTblBody">
                    
                </tbody>
            </table>
        </div>
    </body>
</html>