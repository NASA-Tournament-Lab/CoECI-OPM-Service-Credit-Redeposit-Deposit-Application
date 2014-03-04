<%--
  - Author: TCSASSEMBLER
  - Version: 1.0
  - Copyright (C) 2013 TopCoder Inc., All Rights Reserved.
  -
  - Description: The notifications popup page.
--%>
    <div class="popup notificationPopup isHidden">
        <div class="popupArrow"></div>
        <div class="popupHeader"><div class="popupHeaderRight"><div class="popupHeaderInner"></div></div></div>
        <div class="popupBody"><div class="popupBodyRight"><div class="popupBodyInner">
            <div class="popupTitleWrapper">
                <h4 class="popupTitle">Notifications</h4>
                <div class="popupLinksWrapper">
                    <a href="${ctx}/notificationLog/view" class="popupLink notificationsViewAll">View All</a>
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