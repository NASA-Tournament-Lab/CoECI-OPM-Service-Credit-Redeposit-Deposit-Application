<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2014 TopCoder Inc., All Rights Reserved.
  -
  - Description: The create account page, used to check the creation result.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
  <head>
    <base href="<%=basePath%>"/>
    <!-- title -->
    <title>OPM - Finish Account Creation</title>
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

    <!-- external main js -->
    <script type="text/javascript" src="<c:url value="/js/script.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/createAccount.js"/>"></script>

    <!-- css file for IE7 -->
 <!--[if IE 7]><link rel="stylesheet" href="<c:url value="/css/ie7.css"/>" media="all" type="text/css" /><![endif]-->
    <script></script>

  </head>
  <body>
    <input id="accountId" type="hidden" value="${sessionScope.createdAccountId}"/>
    <div id="wrapper">
      <%@ include file="include/header.jsp"%>

      <div id="content">
        <p class="breadcurmb"><span>Create New Account</span></p>
        <div class="pageTitleArea">
          <h2 class="pageTitle">Create New Account</h2>
          <div class="setHomeLink">
            <input name="setHome" id="setHome" type="checkbox" value="setHome" /> <label for="setHome">Make this tab my home page</label>
            <a href="javascript:;" class="jsShowTips">?</a>
          </div>
        </div>

        <div class="progressStepsBar">
          <ul>
            <li class="firstStep finishedStep"><a href="<c:url value="/account/viewCreate?step=createAccount"/>">Basic Information</a></li>
            <li class="step2 finishedStep"><a href="<c:url value="/account/viewCreate?step=createServiceHistory"/>">Service History</a></li>
            <li class="step3 finishedStep justFinishedStep"><a href="<c:url value="/account/viewCreate?step=createAccountNote"/>">Notes</a></li>
            <li class="lastStep currentStep">Finish</li>
          </ul>
          <div class="corner cornerTl"></div>
          <div class="corner cornerTr"></div>
        </div>
        <div class="finishPanel">
          <div class="basicInfoPanel">
            <div class="stepBlockTitle">
              <h3>Basic Information</h3>
              <a href="<c:url value="/account/viewCreate?step=createAccount"/>" class="priBtn fRight editBtn"><span><span>Edit</span></span></a>
            </div>
            <div class="halfCol">
              <div class="halfRowField">
                <p class="fieldLabel">Claim Number :</p>
                <span class="fieldVal">${account.claimNumber}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Form Submitted :</p>
                <span class="fieldVal">${account.formType.name}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Last Name :</p>
                <span class="fieldVal">${account.holder.lastName}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">First Name :</p>
                <span class="fieldVal">${account.holder.firstName}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">MI :</p>
                <span class="fieldVal">${account.holder.middleInitial}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Suffix :</p>
                <span class="fieldVal">${account.holder.suffix.name}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Birthdate :</p>
                <span class="fieldVal"><fmt:formatDate pattern="MM/dd/yyyy" value="${account.holder.birthDate}"/></span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">SSN :</p>
                <span class="fieldVal">${account.holder.ssn}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Telephone :</p>
                <span class="fieldVal">${account.holder.telephone}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Email :</p>
                <span class="fieldVal">${account.holder.email}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Title :</p>
                <span class="fieldVal">${account.holder.title}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Agency / Dept. Code :</p>
                <span class="fieldVal">${account.holder.departmentCode}</span>
              </div>
            </div>
            <div class="halfCol rightHalfCol">
              <div class="halfRowField">
                <p class="fieldLabel">Address #1 :</p>
                <span class="fieldVal">${account.holder.address.street1}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Address #2 :</p>
                <span class="fieldVal">${account.holder.address.street2}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Address #3 :</p>
                <span class="fieldVal">${account.holder.address.street3}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Address #4 :</p>
                <span class="fieldVal">${account.holder.address.street4}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Address #5 :</p>
                <span class="fieldVal">${account.holder.address.street5}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">City :</p>
                <span class="fieldVal">${account.holder.address.city}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">State/ Province/ Region :</p>
                <span class="fieldVal">${account.holder.address.state.name}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">ZIP/Postal Code :</p>
                <span class="fieldVal">${account.holder.address.zipCode}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">Country :</p>
                <span class="fieldVal">${account.holder.address.country.name}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">GEO code :</p>
                <span class="fieldVal">${account.holder.geoCode}</span>
              </div>
              <p class="sectionTitle">Location of Employment</p>
              <div class="halfRowField">
                <p class="fieldLabel">City :</p>
                <span class="fieldVal">${account.holder.cityOfEmployment}</span>
              </div>
              <div class="halfRowField">
                <p class="fieldLabel">State :</p>
                <span class="fieldVal">${account.holder.stateOfEmployment.name}</span>
              </div>
            </div>
            <div class="clear"></div>
          </div>
          <!-- .basicInfoPanel -->

          <div class="serviceHistoryPanel tabsArea">

            <div class="serviceHistoryTabs tabsBar">
              <ul>
                <li><a href="javascript:;" class="current" id="depositTab">FERS Deposit</a></li>
                <li><a href="javascript:;" id="redepositTab">FERS Redeposit</a></li>
              </ul>
            </div>

            <div class="tabsBlock depositTab">
              <div class="versionBar">
                <label for="calcVersionDepF"><span class="fLeft bold grayTxt">Calculation Version</span></label>
                <select class="fLeft" id="calcVersionDepF">
                </select>
                <a href="<c:url value="/account/viewCreate?step=createServiceHistory"/>" class="priBtn fRight editBtn"><span><span>Edit</span></span></a>
              </div>
              <div class="scrollTblArea">
                <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable resultTbl periodTable" id="depTbl" width="100%">
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
                  </colgroup>
                  <thead>
                    <tr>
                      <th class="unsortable firstCol">&nbsp;&nbsp;&nbsp;&nbsp;</th>
                      <th>Begin Date</th>
                      <th>End Date</th>
                      <th>Retire Type</th>
                      <th>Period Type</th>
                      <th>Appointment Type</th>
                      <th>Service Type</th>
                      <th>Amount</th>
                      <th>Pay Type</th>
                      <th>Withholding Rate</th>
                      <th>Interest Waved</th>
                      <th class="lastCol">Interest Begins</th>
                    </tr>
                  </thead>
                  <tbody>
                    
                  </tbody>
                </table>
              </div>
              <div class="chartCalArea">
                <div class="chartCalAreaBox4">
                  <p class="tableTitle">Redeposit</p>
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl" width="100%">
                    <thead>
                      <tr>
                        <th>&nbsp;</th>
                        <th>Deposit</th>
                        <th>Interest</th>
                        <th>Total</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td class="rowTitle">Post 10/82</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Pre 10/82</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="chartCalAreaBox5">
                  <p class="tableTitle">Deposit</p>
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl" width="100%">
                    <thead>
                      <tr>
                        <th>&nbsp;</th>
                        <th>Deposit</th>
                        <th>Interest</th>
                        <th>Total</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td class="rowTitle twoLineRowTitle">Post 10/82<br/><span>( Incl. FERS )</span></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Pre 10/82</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="chartCalAreaBox6">
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl" width="100%">
                    <colgroup>
                      <col class="col1"/>
                      <col class="col2"/>
                    </colgroup>
                    <tbody>
                      <tr>
                        <td class="rowTitle">Total Initial Payment</td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Total Initial Interest</td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Total Payments Applied</td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Total Balance</td>
                        <td><strong class="dollar"></strong></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="clear"></div>
              </div>
            </div>
            <!-- .depositTab -->

            <div class="tabsBlock redepositTab isHidden">
              <div class="versionBar">
                <label for="calcVersionRepF"><span class="fLeft bold grayTxt">Calculation Version</span></label>
                <select class="fLeft" id="calcVersionRepF">
                </select>
                <a href="<c:url value="/account/viewCreate?step=createServiceHistory"/>" class="priBtn fRight editBtn"><span><span>Edit</span></span></a>
              </div>
              <div class="scrollTblArea">
                <table cellpadding="0" cellspacing="0" border="0" class="stdTbl sortable resultTbl periodTable" id="depTbl" width="100%">
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
                  </colgroup>
                  <thead>
                    <tr>
                      <th class="unsortable firstCol">&nbsp;&nbsp;&nbsp;&nbsp;</th>
                      <th>Begin Date</th>
                      <th>End Date</th>
                      <th>Retire Type</th>
                      <th>Period Type</th>
                      <th>Appointment Type</th>
                      <th>Service Type</th>
                      <th>Amount</th>
                      <th>Pay Type</th>
                      <th>Withholding Rate</th>
                      <th>Interest Waved</th>
                      <th class="lastCol">Interest Begins</th>
                    </tr>
                  </thead>
                  <tbody>
                  </tbody>
                </table>
              </div>
              <div class="chartCalArea">
                <div class="chartCalAreaBox4">
                  <p class="tableTitle">Redeposit</p>
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl" width="100%">
                    <thead>
                      <tr>
                        <th>&nbsp;</th>
                        <th>Deposit</th>
                        <th>Interest</th>
                        <th>Total</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td class="rowTitle">Post 10/82</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Pre 10/82</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="chartCalAreaBox5">
                  <p class="tableTitle">Deposit</p>
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl" width="100%">
                    <thead>
                      <tr>
                        <th>&nbsp;</th>
                        <th>Deposit</th>
                        <th>Interest</th>
                        <th>Total</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td class="rowTitle twoLineRowTitle">Post 10/82<br/><span>( Incl. FERS )</span></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Pre 10/82</td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                        <td class="dollar"></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="chartCalAreaBox6">
                  <table cellpadding="0" cellspacing="0" border="0" class="stdTbl" width="100%">
                    <colgroup>
                      <col class="col1"/>
                      <col class="col2"/>
                    </colgroup>
                    <tbody>
                      <tr>
                        <td class="rowTitle">Total Initial Payment</td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Total Initial Interest</td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Total Payments Applied</td>
                        <td class="dollar"></td>
                      </tr>
                      <tr>
                        <td class="rowTitle">Total Balance</td>
                        <td><strong class="dollar"></strong></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="clear"></div>
              </div>
            </div>
            <!-- .redepositTab -->
          </div>
          <!-- .serviceHistoryPanel -->

          <div class="notesPanel">
            <div class="stepBlockTitle">
              <h3>Account Notes</h3>
              <a href="<c:url value="/account/viewCreate?step=createAccountNote"/>" class="priBtn fRight editBtn"><span><span>Edit</span></span></a>
            </div>
            <p class ="notes">${notes[0].text}</p>
          </div>
          <!-- .notesPanel -->

          <div class="corner cornerBl"></div>
          <div class="corner cornerBr"></div>
        </div>
        <div class="stepsBtnWrapper">
          <a href="<c:url value="/account/viewCreate?step=createAccountNote&requireNew=true"/>" class="priBtn fLeft jsCancelCreateAccount"><span><span>Cancel</span></span></a>
          <a href="<c:url value="/account/${sessionScope.createdAccountId}/detail"/>" class="priBtn fRight"><span><span>Finish</span></span></a>
          <a href="<c:url value="/account/viewCreate?step=createAccountNote"/>" class="priBtn fRight"><span><span>Previous</span></span></a>
        </div>

       

      </div>
      <!-- #content --> 

      <jsp:include page="include/footer.jsp"></jsp:include>
      <!-- #footer -->
    </div>
    <!-- #wrapper -->

    <div class="alpha"></div>
    <div class="popup notificationPopup isHidden">
      <div class="popupArrow"></div>
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Notifications</h4>
              <div class="popupLinksWrapper">
                <a href="javascript:;" class="popupLink">View All</a>
                <a href="javascript:;" class="popupLink jsClosePopup">Hide</a>
              </div>
            </div>
            <table border="0" cellpadding="0" cellspacing="0" id="notificationsTbl" width="100%">
              <colgroup>
                <col class="col1"/>
                <col class="col2"/>
                <col class="col3"/>
              </colgroup>
              <thead>
                <tr>
                  <th>Date/Time</th>
                  <th>Sent by</th>
                  <th>Notification</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>05/06/2013<br/>08:00:00 AM</td>
                  <td>System</td>
                  <td>- You have 1 Record in <a href="workQueue/view">Work Queue.</a><br/>- You have 7 <a href="suspension/view">Payments in Suspense</a>.</td>
                </tr>
                <tr>
                  <td>05/06/2013<br/>08:00:00 AM</td>
                  <td>John Smith<br/>(Supervisor)</td>
                  <td>I reviewed the Account for John Doe and found an error in Billing Summary. Please fix. <a href="account/2002/detail">Click here to view the account</a>.</td>
                </tr>
              </tbody>
            </table>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .notificationPopup -->

    <div class="popup infoNotiPopup isHidden">
      <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
      <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
              <h4 class="popupTitle">Information &amp; Notifications</h4>
              <a href="javascript:;" class="popupClose jsClosePopup">Close</a>
            </div>
            <div class="infoTblWrapper">
              <table border="0" cellpadding="0" cellspacing="0" id="infoTbl" width="100%">
                <colgroup>
                  <col class="col1"/>
                  <col class="col2"/>
                  <col class="col3"/>
                </colgroup>
                <tbody>
                  <tr>
                    <td><span class="rowNoti"></span>05/06/2013</td>
                    <td>08:00:00 AM</td>
                    <td class="lastCol">Notification from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                  </tr>
                  <tr>
                    <td><span class="rowInfo"></span>05/06/2013</td>
                    <td>08:00:00 AM</td>
                    <td class="lastCol">Information from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                  </tr>
                  <tr>
                    <td><span class="rowErr"></span>05/06/2013</td>
                    <td>08:00:00 AM</td>
                    <td class="lastCol">Error from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                  </tr>
                  <tr>
                    <td><span class="rowInfo"></span>05/06/2013</td>
                    <td>08:00:00 AM</td>
                    <td class="lastCol">Information from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                  </tr>
                  <tr>
                    <td><span class="rowInfo"></span>05/06/2013</td>
                    <td>08:00:00 AM</td>
                    <td class="lastCol">Information from System: You have 2 records in <br/><a href="workQueue/view">Work Queue.</a></td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="filerTab">
              <ul>
                <li><a href="javascript:;" class="current" id="rowAll">All</a></li>
                <li><a href="javascript:;" id="rowNoti">Notifications</a></li>
                <li><a href="javascript:;" id="rowErr">Errors</a></li>
                <li><a href="javascript:;" id="rowInfo">Info</a></li>
              </ul>
              <div class="corner cornerBl"></div>
              <div class="corner cornerBr"></div>
            </div>
          </div></div></div>
      <div class="popupFooter"><div class="popupFooterRight"><div class="popupFooterInner"></div></div></div>
    </div>
    <!-- .infoNotiPopup -->
    <div class="isHidden">
      <table class="isHidden" id="versionTableTemplate">
        <tbody>
          <tr>
            <td class="blankCell firstCol">&nbsp;</td>
            <td></td>
            <td></td>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td class="dollar">20000.00</td>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <td></td>
            <td class="lastCol"></td>
          </tr>
        </tbody>
      </table>
      <table class="isHidden" id="versionEditTemplate">
        <tbody>
          <tr class="entriesEditRow">
            <td class="blankCell firstCol">&nbsp;</td>
            <td><input name="bDate" type="text" class="text bDate" value="01/01/2001"/></td>
            <td><input name="eDate" type="text" class="text eDate" value="01/12/2012"/></td>
            <td><select class="rType" typeName="retirementTypes">
              </select></td>
            <td><select typeName="periodTypes" class="pType">
              </select></td>
            <td><select class="aType" typeName="appointmentTypes">
              </select></td>
            <td><select class="sType" typeName="serviceTypes" >
              </select></td>
            <td><input name="amount" type="text" class="text amount" value="$ 20,000.00"/></td>
            <td><select class="payType" typeName="payTypes">
              </select></td>
      <td>
        <select>
          <option selected></option>
          <option>7</option>
          <option>1.3</option>
          <option>5.7</option>
        </select>
      </td>
      <td>
        <select>
          <option>YES</option>
          <option selected>NO</option>
        </select>
      </td>
      <td class="lastCol">
        <input name="iDate" type="text" class="text iDate"/>
      </td>
          </tr>
        </tbody>
      </table>

      <table class="isHidden" id="versionNewTemplate">
        <tbody>
          <tr class="even newEntryRow">
            <td class="blankCell firstCol">&nbsp;</td>
            <td></td>
            <td></td>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <input type="hidden"/>
            <td class="dollar"></td>
            <td></td>
            <input type="hidden"/>
            <td></td>
            <td></td>
            <td class="lastCol"></td>
          </tr>
        </tbody>
      </table>

      <table class="isHidden" id="resultItemTemplate">
        <tbody>
          <tr>
            <td class="blankCell firstCol">&nbsp;</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td class="dollar"></td>
            <td class="dollar"></td>
            <td class="dollar"></td>
            <td class="lastCol dollar"></td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="isHidden depositVersionTbodyArea">

    </div>

    <div class="isHidden redepositVersionTbodyArea">

    </div>
  </body>
</html>