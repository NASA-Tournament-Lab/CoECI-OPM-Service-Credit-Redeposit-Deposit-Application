<%--
  - Author: sparemax, liuliquan
  - Version: 1.1
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The work queue page.
  - Change log:
  -         1.1 Move viewAccountSearchFormWrapper DIV to include/header.jsp, move notifications popup to include/notificationsPopup.jsp
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>"/>
    <!-- title -->
    <title>Service Credit Working Queue</title>

    <!-- metatags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="${ctx}/i/logo-opm-gray.png"type="image/x-icon" />

    <!-- External CSS -->
    <link rel="stylesheet" href="${ctx}/css/jquery-ui-1.10.3.custom.css" media="all" />
    <link rel="stylesheet" href="${ctx}/css/screen.css" media="all" />

    <!-- JS lib/plugins-->
    <script type="text/javascript" src="${ctx}/js/jquery-1.10.2.min.js"></script>
    <script type='text/javascript' src='${ctx}/js/jquery-ui-1.10.3.custom.min.js'></script>
    <script type='text/javascript' src='${ctx}/js/merge-sort.js'></script>
    <script type='text/javascript' src='${ctx}/js/sortable.js'></script>

    <!-- external main js -->
    <script type="text/javascript" src="${ctx}/js/script.js"></script>

    <script type="text/javascript" src="${ctx}/js/workQueue.js"></script>

    <!-- css file for IE7 -->
    <!--[if IE 7]><link rel="stylesheet" href="./css/ie7.css" media="all" type="text/css" /><![endif]-->

</head>
<body>
    <div id="wrapper">
        <%@ include file="include/header.jsp"%>

        <div id="content">
            <p class="breadcurmb"><span>Work Queue</span></p>
            <div class="pageTitleArea">
                <h2 class="pageTitle">Work Queue</h2>
                <div class="setHomeLink">
                    <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
                    <a href="javascript:;" class="jsShowTips">?</a>
                </div>
            </div>

            <div class="grayPanel casesPanel">
                <div class="panelHeader">
                    <h3 class="panelTitle">Cases</h3>
                    <div class="corner cornerTl"></div>
                    <div class="corner cornerTr"></div>
                </div>
                <div class="caseOptions grayBorder">
                    <input name="caseFilter" type="radio" id="showAssigned" value="showAssigned" checked="checked" /> <label for="showAssigned">Show Assigned Cases</label>
                    <input name="caseFilter" type="radio" id="showAllPending" value="showAllPending" /> <label for="showAllPending">Show All Pending Cases</label>
                    <input name="caseFilter" type="radio" id="showAllProcessed" value="showAllProcessed" /> <label for="showAllProcessed">Show All Processed Cases</label>
                </div>
                <div class="panelBody grayBorder">
                    <div class="showAssignedBlock">
                        <p class="blockNote">Show Assigned Cases (double-click row to open case)</p>
                        <div class="tblWrapper">
                            <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable" id="assignedCasesTbl" width="100%">
                                <colgroup>
                                    <col class="blankCol"/>
                                    <col class="col1"/>
                                    <col class="col2"/>
                                    <col class="col3"/>
                                    <col class="col4"/>
                                    <col class="col5"/>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th class="unsortable isHidden">&nbsp;</th>
                                        <th class="unsortable">&nbsp;</th>
                                        <th>CSD #</th>
                                        <th>SSN</th>
                                        <th>Name</th>
                                        <th>Assigned On</th>
                                        <th>Assigned To</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <div class="pagination">
                                <div class="perPage fLeft">
                                    <span>Show : </span> 
                                    <select>
                                        <option>10</option>
                                        <option>20</option>
                                        <option>30</option>
                                        <option>40</option>
                                        <option>50</option>
                                        <option>100</option>
                                    </select>
                                    <span>per page</span> 
                                </div>

                                <div class="paginationLinks fRight">
                                </div>
                                <span class="paginationLabel fRight"><span class="startCount">1</span>-<span class="endCount">10</span> of <span class="totalCount">30</span></span>
                            </div>
                            <div class="tblWrapperBorder"></div>
                        </div>
                    </div>

                    <div class="showAllPendingBlock isHidden">
                        <p class="blockNote">Show All Pending Cases (double-click row to assign to me)</p>
                        <div class="tblWrapper">
                            <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable" id="allPendingCasesTbl" width="100%">
                                <colgroup>
                                    <col class="blankCol"/>
                                    <col class="col1"/>
                                    <col class="col2"/>
                                    <col class="col3"/>
                                    <col class="col4"/>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th class="unsortable isHidden">&nbsp;</th>
                                        <th class="unsortable">&nbsp;</th>
                                        <th>CSD #</th>
                                        <th>SSN</th>
                                        <th>Claimant Name</th>
                                        <th>Returned from Records</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <div class="pagination">
                                <div class="perPage fLeft">
                                    <span>Show : </span> 
                                    <select>
                                        <option>10</option>
                                        <option>20</option>
                                        <option>30</option>
                                        <option>40</option>
                                        <option>50</option>
                                        <option>100</option>
                                    </select>
                                    <span>per page</span> 
                                </div>

                                <div class="paginationLinks fRight">
                                </div>
                                <span class="paginationLabel fRight"><span class="startCount">1</span>-<span class="endCount">10</span> of <span class="totalCount">30</span></span>
                            </div>
                            <div class="tblWrapperBorder"></div>
                        </div>
                    </div>

                    <div class="showAllProcessedBlock isHidden">
                        <p class="blockNote">Show All Processed Cases (double-click row to assign to me)</p>
                        <div class="tblWrapper">
                            <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable" id="allProcessedCasesTbl" width="100%">
                                <colgroup>
                                    <col class="blankCol"/>
                                    <col class="col1"/>
                                    <col class="col2"/>
                                    <col class="col3"/>
                                    <col class="col4"/>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th class="unsortable isHidden">&nbsp;</th>
                                        <th class="unsortable">&nbsp;</th>
                                        <th>CSD #</th>
                                        <th>SSN</th>
                                        <th>Claimant Name</th>
                                        <th>Returned from Records</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <div class="pagination">
                                <div class="perPage fLeft">
                                    <span>Show : </span> 
                                    <select>
                                        <option>10</option>
                                        <option>20</option>
                                        <option>30</option>
                                        <option>40</option>
                                        <option>50</option>
                                        <option>100</option>
                                    </select>
                                    <span>per page</span> 
                                </div>

                                <div class="paginationLinks fRight">
                                </div>
                                <span class="paginationLabel fRight"><span class="startCount">1</span>-<span class="endCount">10</span> of <span class="totalCount">30</span></span>
                            </div>
                            <div class="tblWrapperBorder"></div>
                        </div>
                    </div>
                </div>
                <div class="panelBtnWrapper grayBorder">
                    <div class="fRight">
                        <a href="javascript:;" class="priBtn jsUnassignCase"><span><span>Unassign Case</span></span></a>
                        <a href="javascript:;" class="priBtn jsReassignCase"><span><span>Reassign Case</span></span></a>
                        <a href="javascript:;" class="priBtn jsAssignToMe"><span><span>Assign To Me</span></span></a>
                    </div>
                </div>
            </div>
            <!-- .grayPanel -->

            

        </div>
        <!-- #content -->

        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- #footer -->
    </div>
    <!-- #wrapper -->

    <div class="alpha"></div>
    <div class="popup wqAssignmentPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Work Queue Assignment</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Select one or more cases to assign</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .wqAssignmentPopup -->

    <div class="popup wqUnassignmentPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Work Queue Unassignment</h4>
                <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <p>Select one or more cases to unassign</p>
            <div class="popupBtnWrapper">
                <a class="priBtn jsClosePopup"><span><span>OK</span></span></a>
            </div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .wqUnassignmentPopup -->

    <div class="popup accountAssignmentPopup isHidden">
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Account Assignment</h4>
                <a href="javascript:;" class="popupClose popupCloseX jsClosePopup">Close</a>
            </div>
            <div class="leftAccountList fLeft">
                <ul class="accountList">
                </ul>
            </div>
            <div class="rightBtnWrapper fRight">
                <a href="javascript:;" class="priBtn jsDoReassignCase"><span><span>Assign Case</span></span></a>
                <a href="javascript:;" class="priBtn jsCancelReassignCase"><span><span>Cancel</span></span></a>
            </div>
            <div class="clear"></div>
        </div></div></div>
        <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .accountAssignmentPopup -->

    <%@ include file="include/notificationsPopup.jsp"%>
</body>
</html>
