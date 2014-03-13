/*
    Copyright 2014 OPM.gov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

$(document).ready(function() {

    // Setup page
    setupPage('APPROVAL', 5);

    function setNoRecordsText(tab){
        var currFilter = $("select.viewFilter", tab).val();
        if(tab.hasClass('interestAdjustmentTab') || tab.hasClass('paymentMoveTab')){
            if(currFilter === 'PENDING'){
                $(".tblWrapper .noRecordTxt", tab).html("There are no payments to approve.");
            } else if(currFilter == 'APPROVED'){
                $(".tblWrapper .noRecordTxt", tab).html("There are no approved payments.");
            } else if(currFilter == 'DISAPPROVED'){
                $(".tblWrapper .noRecordTxt", tab).html("There are no disapproved payments.");
            }
        } else if(tab.hasClass('pendingPaymentTab')){
            $(".tblWrapper .noRecordTxt", tab).html("There are no payments to approve.");
        }
    }

    // This is the general function to be show tab
    function showTab(tab, loadUrl, loadParam, sortByDefaultColumn, buildRowFunction, callBackFunction, desc) {

        // Hide note area
        $(".csdNoteArea").addClass("csdNoteAareaNoSelection");

        // Disable approve/disapprove/viewAuditTrail buttons
        $(".approvalPageBtnWrapper .priBtn", tab).addClass("priBtnDisabled");

        // Load all data
        $.ajax({
            url : loadUrl,
            type : 'GET',
            cache : false,
            async : true,
            data : loadParam,
            success : function(results) {

                var tbody = $('.sortable tbody', tab);

                // Clear table
                tbody.text('');

                // No results
                if (!results || results.length == 0) {
                    $(".tblWrapper", tab).addClass("noApprovalRecords");
                    setNoRecordsText(tab);
                } else {
                    $(".tblWrapper", tab).removeClass("noApprovalRecords");

                    // Build & Append row
                    for ( var i in results) {
                        tbody.append(buildRowFunction(results[i]));
                    }

                    var currentSortarrow = $('.sortable th.current .sortarrow', tab);

                    // Sort up by default column
                    if (sortByDefaultColumn || currentSortarrow.length == 0) {
                        $('.sortable th.defaultSortCol .sortarrow', tab).attr('sortdir', 'up');
                        $('.sortable th.defaultSortCol', tab).trigger('click');
                    } else {
                        // Sort by current
                        if (currentSortarrow.attr('sortdir') == 'up') {
                            currentSortarrow.attr('sortdir', 'down');
                        } else {
                            currentSortarrow.attr('sortdir', 'up');
                        }
                        $('.sortable th.current', tab).trigger('click');
                    }
                }

                // Call back if any
                if (callBackFunction) {
                    callBackFunction(results);
                }
            },
            error : function(request, status, error) {
                alert('Failed to get ' + desc + ' : ' + request.responseText);
            }
        });
    }

    // This is the general function to collection selected payment ids
    function collectSelectedPaymentIds(tab) {

        var paymentIds = [];
        $('.sortable input[name="selectCheckbox"]:checked:enabled', tab).each(function() {
            paymentIds.push($(this).parents('tr').eq(0).data('paymentId'));
        });
        return paymentIds;
    }

    // This is the general function to approve
    function approve(approveButton, approveUrl, paymentIdsParamName, callBackFunction, desc) {
        if (!(approveButton.hasClass('priBtnDisabled'))) {

            var tab = approveButton.parents('div.tabsBlock').eq(0);

            var paymentIds = collectSelectedPaymentIds(tab);

            if (paymentIds.length == 0) {
                return;
            }

            var approveRequest = {};
            approveRequest[paymentIdsParamName] = paymentIds;

            // Approve
            $.ajax({
                url : approveUrl,
                type : 'POST',
                cache : false,
                async : true,
                data : $.param( approveRequest, true ),
                success : function() {
                    showPopup('.approveSeletedSuccessPopup');
                    renderAfterApproval(tab);
                    // Call back if any
                    if (callBackFunction) {
                        callBackFunction();
                    }
                },
                error : function(request, status, error) {
                    alert('Failed to approve ' + desc + ' : ' + request.responseText);
                }
            });
        }
    }

    // This is the general function to show disapprove popup
    function showDispprovePopup(disapproveButton, popupSelector) {
        if (!(disapproveButton.hasClass("priBtnDisabled"))){

            var tab = disapproveButton.parents('div.tabsBlock').eq(0);

            var paymentIds = collectSelectedPaymentIds(tab);

            if (paymentIds.length == 0) {
                return;
            }

            $(popupSelector + ' textarea').val('');
            $(popupSelector).data('paymentIds', paymentIds);

            showPopup(popupSelector);
        }
    }

    // This is the general function to disapprove
    function doDisapprove(popupSelector, disapproveUrl, paymentIdsParamName, callBackFunction, desc) {

        var reason = $(popupSelector + ' textarea').val();

        // Check reason
        if (!reason) {
            alert('Reason for disapproval field is required.');
            return false;
        }
        if (reason.length > 255) {
            alert('Reason for disapproval field max supports 255 chars.');
            return false;
        }

        var paymentIds = $(popupSelector).data('paymentIds');

        var disapproveRequest = {};
        disapproveRequest[paymentIdsParamName] = paymentIds;
        disapproveRequest['reason'] = reason;

        // Disapprove
        $.ajax({
            url : disapproveUrl,
            type : 'POST',
            cache : false,
            async : true,
            data : $.param( disapproveRequest, true ),
            success : function() {
                closePopup();
                renderAfterApproval($('.tabsBlock:visible'));
                showPopup(".disapproveSeletedSuccessPopup");
                if (callBackFunction) {
                    callBackFunction();
                }
            },
            error : function(request, status, error) {
                alert('Failed to disapprove ' + desc + ' : ' + request.responseText);
            }
        });
    }

    // This is the general function to update number
    function updateNumber(size, desc) {

        if (size > 1) {
            $('.statusInfoBar .jsShowStatusInfoPopup').text(size + ' ' + desc + 's needing approval');
        } else if (size == 1) {
            $('.statusInfoBar .jsShowStatusInfoPopup').text(size + ' ' + desc + ' needing approval');
        } else {
            size = 0;
            $('.statusInfoBar .jsShowStatusInfoPopup').text('No ' + desc + ' needing approval');
        }

        $('.tabsBar a.current .notificationNum > span > span').text(size);

        if (size == 0) {
            $('.tabsBar a.current .notificationNum').hide();
        }

        var totalNotiNum = 0;
        $('.tabsBar .notificationNum > span > span').each(function() {
            totalNotiNum = totalNotiNum + parseInt($(this).text(), 10);
        });
        $('.topNav > ul > li:eq(5) .notificationNum > span > span').text(totalNotiNum);
    }

    /** Pending Payments **/

    // The function to build row for pending payment
    function buildPendingPaymentRow(pendingPayment) {
        var row = ['<tr>'];
        row = row.concat(['<td class="blankCell firstCol">&nbsp;</td>']);
        row = row.concat(['<td class="checkBoxCell"><input name="selectCheckbox" type="checkbox"/></td>']);
        row = row.concat(['<td>', pendingPayment.paymentStatus ? pendingPayment.paymentStatus.name : '', '</td>']);
        row = row.concat(['<td>', pendingPayment.batchNumber + '-' + pendingPayment.blockNumber + '-' + pendingPayment.sequenceNumber, '</td>']);
        row = row.concat(['<td class="aCenter">', formatNotificationDate(pendingPayment.transactionDate), '</td>']);
        row = row.concat(['<td class="aCenter">', formatNotificationDate(pendingPayment.statusDate), '</td>']);
        row = row.concat(['<td>', '$' + numberFormat(pendingPayment.amount, 2), '</td>']);
        row = row.concat(['<td class="checkBoxCell"><input name="pendingPaymentGL" type="checkbox" disabled="disabled"', pendingPayment.applyToGL ? 'checked="checked"' : '' , '/></td>']);
        row = row.concat(['<td class="csdNum">', pendingPayment.claimNumber, '</td>']);
        row = row.concat(['<td class="aCenter">', formatNotificationDate(pendingPayment.accountHolderBirthdate), '</td>']);
        row = row.concat(['<td class="aCenter">', pendingPayment.accountStatus ? pendingPayment.accountStatus.name : '', '</td>']);
        row = row.concat(['<td class="lastCol">', pendingPayment.userName ? pendingPayment.userName + ' ' + pendingPayment.userRole.name : '', '</td>']);
        row.push('</tr>');
        row = $(row.join(''));
        row.addClass('UnapprovedRow');
        row.data('paymentId', pendingPayment.paymentId);
        return row;
    }

    // Then function to be called when pending payments tab is shown
    function showPendingPaymentTab() {
        showTab($('.pendingPaymentTab'),
                'approval/pendingPayments',
                null,
                false,
                buildPendingPaymentRow,
                function(results) {
                    updateNumber(results.length, 'payment');
                },
                'pending payments');
    }

    // Show pending payments tab on page load
    showPendingPaymentTab();

    // On approve pending payments clicked
    $(".jsPendingPaymentsApproveSelected").click(function() {
        approve($(this),
                'approval/pendingPayments/approve',
                'pendingPaymentIds',
                null,
                'pending payments');
    });

    // On disapprove pending payments clicked
    $(".jsPendingPaymentsDisapproveSelected").click(function(){
        showDispprovePopup($(this), '.pendingPaymentDisapprovePopup');
    });

    // Do disapprove pending payments
    $(".jsDoDisapprovePendingPayment").click(function(){
        doDisapprove('.pendingPaymentDisapprovePopup',
                'approval/pendingPayments/disapprove',
                'pendingPaymentIds',
                null,
                'pending payments');
    });

    $(".jsPendingPaymentsViewAuditTrail").click(function(){
        if (!($(this).hasClass("priBtnDisabled"))){
            showPopup(".apViewAuditTrailPopup");
        }
    });

    $(".jsDoPrintAPAuditTrail").click(function(){
        return false;
    });

    /** Interest Adjustments **/

    // The function to build row for interest adjustment
    function buildInterestAdjustmentRow(interestAdjustment) {
        var row = ['<tr>'];
        row = row.concat(['<td class="blankCell firstCol">&nbsp;</td>']);
        if (interestAdjustment.approvalStatus == 'PENDING') {
            row = row.concat(['<td class="checkBoxCell"><input name="selectCheckbox" type="checkbox"/></td>']);
        } else {
            row = row.concat(['<td class="isHidden"></td>']);
        }
        row = row.concat(['<td class="csdNum">', interestAdjustment.claimNumber, '</td>']);
        row = row.concat(['<td>', '$' + numberFormat(interestAdjustment.preDepositAmount, 2), '</td>']);
        row = row.concat(['<td>', '$' + numberFormat(interestAdjustment.preRedepositAmount, 2), '</td>']);
        row = row.concat(['<td>', '$' + numberFormat(interestAdjustment.postDepositAmount, 2), '</td>']);
        row = row.concat(['<td>', '$' + numberFormat(interestAdjustment.postRedepositAmount, 2), '</td>']);
        row = row.concat(['<td>', '$' + numberFormat(interestAdjustment.paymentTransactionAmount, 2), '</td>']);
        row = row.concat(['<td>', formatCaseDate(interestAdjustment.adjustmentDate), '</td>']);
        row = row.concat(['<td class="aCenter">', interestAdjustment.accountStatus ? interestAdjustment.accountStatus.name : '', '</td>']);
        row = row.concat(['<td>', interestAdjustment.userName ? interestAdjustment.userName + ' ' + interestAdjustment.userRole.name : '', '</td>']);
        row = row.concat(['<td class="lastCol">', interestAdjustment.userStatus ? interestAdjustment.userStatus.name : '', '</td>']);
        row.push('</tr>');
        row = $(row.join(''));
        if (interestAdjustment.approvalStatus == 'APPROVED') {
            row.addClass('ApprovedRow');
        } else if (interestAdjustment.approvalStatus == 'DISAPPROVED') {
            row.addClass('DisapprovedRow');
        } else {
            row.addClass('UnapprovedRow');
        }
        row.data('paymentId', interestAdjustment.paymentId);
        return row;
    }

    // Then function to be called when interest adjustments tab is shown
    function showInterestAdjustmentTab() {
        var tab = $('.interestAdjustmentTab');
        $('.viewFilter', tab).val('PENDING');
        changeFilterView(tab);

        showTab(tab,
                'approval/interestAdjustments',
                {approvalStatus: 'PENDING'},
                false,
                buildInterestAdjustmentRow,
                function() {
                    updateNumber($('.sortable tbody tr.UnapprovedRow', tab).length, 'record');
                },
                'interest adjustments');
    }

    // On approve interest adjustments clicked
    $(".jsInterestAdjustmentApproveSelected").click(function(){
        approve($(this),
                'approval/interestAdjustments/approve',
                'interestAdjustmentIds',
                null,
                'interest adjustments');
    });

    // On disapprove interest adjustments clicked
    $(".jsInterestAdjustmentDisapproveSelected").click(function(){
        showDispprovePopup($(this), '.interestAdjustmentsDisapprovePopup');
    });

    // Do disapprove interest adjustments
    $(".jsDoDisapproveInterestAdjustment").click(function(){
        doDisapprove('.interestAdjustmentsDisapprovePopup',
                'approval/interestAdjustments/disapprove',
                'interestAdjustmentIds',
                null,
                'interest adjustments');
    });

    // On filter view change
    $('.interestAdjustmentTab select.viewFilter').change(function() {
        var tab = $('.interestAdjustmentTab');
        changeFilterView(tab);

        var approvalStatus = $(this).val();

        showTab(tab,
                'approval/interestAdjustments',
                {approvalStatus: approvalStatus},
                false,
                buildInterestAdjustmentRow, // Use the same function to build row
                function() {
                    if (approvalStatus == 'PENDING') {
                        updateNumber($('.sortable tbody tr.UnapprovedRow', tab).length, 'record');
                    }
                },
                'interest adjustments');
    });

    $(".jsInterestAdjustmentViewAuditTrail").click(function(){
        if (!($(this).hasClass("priBtnDisabled"))){
            showPopup(".iaViewAuditTrailPopup");
        }
    });

    $(".jsDoPrintIAAuditTrail").click(function(){
        return false;
    });

    /** Payment Moves **/

    // Then function to be called when payment moves tab is shown
    function showPaymentMoveTab() {
        var tab = $('.paymentMoveTab');
        $('.viewFilter', tab).val('PENDING');
        changeFilterView(tab);

        showTab(tab,
                'approval/paymentMoves',
                {approvalStatus: 'PENDING'},
                false,
                buildInterestAdjustmentRow,
                function() {
                    updateNumber($('.sortable tbody tr.UnapprovedRow', tab).length, 'record');
                },
                'payment moves');
    }

    // On approve payment moves clicked
    $(".jsPaymentMovesApproveSelected").click(function(){
        approve($(this),
                'approval/paymentMoves/approve',
                'paymentMoveIds',
                null,
                'payment moves');
    });

    // On disapprove payment moves clicked
    $(".jsPaymentMovesDisapproveSelected").click(function(){
        showDispprovePopup($(this), '.paymentMovesDisapprovePopup');
    });

    // Do disapprove payment moves
    $(".jsDoDisapprovePaymentMove").click(function(){
        doDisapprove('.paymentMovesDisapprovePopup',
                'approval/paymentMoves/disapprove',
                'paymentMoveIds',
                null,
                'payment moves');
    });

    // On filter view change
    $('.paymentMoveTab select.viewFilter').change(function() {
        var tab = $('.paymentMoveTab');
        changeFilterView(tab);

        var approvalStatus = $(this).val();

        showTab(tab,
                'approval/paymentMoves',
                {approvalStatus: approvalStatus},
                false,
                buildInterestAdjustmentRow,
                function() {
                    if (approvalStatus == 'PENDING') {
                        updateNumber($('.sortable tbody tr.UnapprovedRow', tab).length, 'record');
                    }
                },
                'payment moves');
    });

    $(".jsPaymentMovesViewAuditTrail").click(function(){
        if (!($(this).hasClass("priBtnDisabled"))){
            showPopup(".pmViewAuditTrailPopup");
        }
    });

    $(".jsDoPrintPMAuditTrail").click(function(){
        return false;
    });

    // The function to render after approve/disapprove interestAdjustments/paymentMoves
    function renderAfterApproval(tab) {

        // Remove the selected rows
        $('.sortable tbody tr.UnapprovedRow', tab).each(function() {
            if ($(this).find('input[name="selectCheckbox"]:checked:enabled').length > 0) {
                $(this).remove();
            }
        });

        // Disable approve/disapprove buttons
        $(".pendingDisapproveBtn, .pendingApproveBtn", tab).addClass("priBtnDisabled");

        // Has any rows left?
        if ($(".sortable tbody tr:visible", tab).length < 1){
            $(".tblWrapper", tab).addClass("noApprovalRecords");
            setNoRecordsText(tab);
        }else{
            $(".tblWrapper", tab).removeClass("noApprovalRecords");
        }

        // Has any highlighted rows left?
        if ($(".sortable tbody tr.highlighted:visible", tab).length < 1){
            $(".csdNoteArea").addClass("csdNoteAareaNoSelection");
            $(".approvalPageBtnWrapper .priBtn", tab).addClass("priBtnDisabled");
        }

        updateNumber($('.sortable tbody tr.UnapprovedRow', tab).length,
                tab.hasClass('pendingPaymentTab') ? 'payment' : 'record');
    }

    //Filter view changes
    function changeFilterView(tab) {
        $('.sortable tbody', tab).text('');

        var currFilter = $("select.viewFilter", tab).val();

        if (currFilter === "PENDING"){
            $(".jsInterestAdjustmentDisapproveSelected, .jsInterestAdjustmentApproveSelected, .jsPaymentMovesApproveSelected, .jsPaymentMovesDisapproveSelected").show();
            $(".sortable thead .checkBoxCol", tab).css("display", "table-cell");
            if ($(".sortable colgroup col.col1", tab).length < 1){
                $(".sortable colgroup col.blankCol", tab).after("<col class='col1'>");
            }
        }else{
            $(".jsInterestAdjustmentDisapproveSelected, .jsInterestAdjustmentApproveSelected, .jsPaymentMovesApproveSelected, .jsPaymentMovesDisapproveSelected").hide();
            $(".sortable thead .checkBoxCol", tab).css("display", "none");
            $(".sortable colgroup col.col1", tab).remove();
        }
    }

    // Enable/disable approve/disapprove button on select check box clicked
    $(".approvalTab .sortable").delegate("input[name='selectCheckbox']", "click", function(){
        var tbl = $(this).parents("table").eq(0);
        var checkBox = $("input[name='selectCheckbox']:checked:enabled", tbl);
        var tabBlock = tbl.parents('div.tabsBlock').eq(0);
        var allCheckBoxNum = $("input[name='selectCheckbox']:enabled", tbl).length;
        if (checkBox.length > 0){
            $(".approvalPageBtnWrapper .pendingApproveBtn", tabBlock).removeClass("priBtnDisabled");
            $(".approvalPageBtnWrapper .pendingDisapproveBtn", tabBlock).removeClass("priBtnDisabled");
        }else{
            $(".approvalPageBtnWrapper .pendingApproveBtn", tabBlock).addClass("priBtnDisabled");
            $(".approvalPageBtnWrapper .pendingDisapproveBtn", tabBlock).addClass("priBtnDisabled");
        }
        if (checkBox.length == allCheckBoxNum) {
            $("input[name='selectAllCheckbox']", tbl).prop('checked', true);
        } else {
            $("input[name='selectAllCheckbox']", tbl).prop('checked', false);
        }
    });

    $(".sortable").delegate("input[name='selectAllCheckbox']", "click", function() {
        var isChecked = $(this).prop("checked");
        var table = $(this).closest("table");
        $("input[name='selectCheckbox']", table).each(function() {
            $(this).prop("checked", isChecked);
        });
        var tbl = table;
        var checkBox = $("input[name='selectCheckbox']:checked:enabled", tbl);
        var tabBlock = tbl.parents('div.tabsBlock').eq(0);
        if (checkBox.length > 0){
            $(".approvalPageBtnWrapper .pendingApproveBtn", tabBlock).removeClass("priBtnDisabled");
            $(".approvalPageBtnWrapper .pendingDisapproveBtn", tabBlock).removeClass("priBtnDisabled");
        }else{
            $(".approvalPageBtnWrapper .pendingApproveBtn", tabBlock).addClass("priBtnDisabled");
            $(".approvalPageBtnWrapper .pendingDisapproveBtn", tabBlock).addClass("priBtnDisabled");
        }
    });

    /** Payment note area **/

    $(".approvalTab .sortable").delegate("td", "mouseenter", function(){
        $(this).parent().addClass("hovered");
    }).delegate("td", "mouseleave", function(){
        $(this).parent().removeClass("hovered");
    }).delegate("tbody td", "click", function(){
            var row = $(this).parent();
            var tbl = $(this).parents("table").eq(0);
            $("tr", tbl).removeClass("highlighted");
            row.addClass("highlighted");

            var paymentId = row.data('paymentId');

            // The note of the payment is already shown
            if ($('#csdNoteForm textarea').is(':visible') && $('#csdNoteForm textarea').data('paymentId') == paymentId) {
                return;
            }

            // Get payment note
            $.ajax({
                url : 'approval/paymentNote/' + paymentId,
                type : 'GET',
                cache : false,
                async : true,
                success : function(note) {
                    $(".jsDiscardCSDNoteChanges").addClass("priBtnDisabled");
                    $('#csdNoteForm textarea').val(note);
                    $('#csdNoteForm textarea').data('note', note);
                    $('#csdNoteForm textarea').data('paymentId', paymentId);

                    var csdNum = $("td.csdNum", row).text();
                    $(".approvalPageBtnWrapper .pendingVATBtn").removeClass("priBtnDisabled");
                    $(".csdNoteArea").removeClass("csdNoteAareaNoSelection");
                    $(".csdNoteArea .noteAreaTitle strong").text("CSD #" + csdNum);
                },
                error : function(request, status, error) {
                    alert('Failed to get payment note: ' + request.responseText);
                }
            });
    });

    $('#csdNoteForm').delegate('textarea', 'change', function() {
        var originalData = $(this).data() ? $(this).data().note : "";
        if (originalData == $(this).val()) {
            // not changed
            $(".jsDiscardCSDNoteChanges").addClass("priBtnDisabled");
        } else {
            $(".jsDiscardCSDNoteChanges").removeClass("priBtnDisabled");
        }
    });
    $('#csdNoteForm').delegate('textarea', 'keyup', function() {
        console.log("text area is changed.");
        var originalData = $(this).data() ? $(this).data().note : "";
        if (originalData == $(this).val()) {
            // not changed
            $(".jsDiscardCSDNoteChanges").addClass("priBtnDisabled");
        } else {
            $(".jsDiscardCSDNoteChanges").removeClass("priBtnDisabled");
        }
    });

    $(".csdNoteArea textarea").resizable({
        minWidth: 913,
        maxWidth: 913,
        minHeight: 107
    }).parent().css({
        "padding": "0 0 1px 0",
        height: 107
    });

    // On note save button clicked
    $(".jsCSDNoteSave").click(function(e){
        var paymentId = $('#csdNoteForm textarea').data('paymentId');
        var note = $('#csdNoteForm textarea').val();

        if (!note) {
            alert('Notes field is required.');
            return false;
        }
        if (note.length > 2048) {
            alert('Notes field max supports 2048 chars.');
            return false;
        }

        $.ajax({
            url : 'approval/paymentNote/' + paymentId,
            type : 'PUT',
            cache : false,
            async : true,
            contentType : 'application/json',
            data : note,
            success : function() {
                $('#csdNoteForm textarea').data('note', note);
                showPopup(".savePaymentNotesSuccessPopup");
            },
            error : function(request, status, error) {
                alert('Failed to save payment note: ' + request.responseText);
            }
        });
    });

    // On note discard changes button clicked
    $(".jsDiscardCSDNoteChanges").click(function(){
        $('#csdNoteForm textarea').val($('#csdNoteForm textarea').data('note'));
        $(this).addClass("priBtnDisabled");
    });


    // Tab switch
    switchTab([showPendingPaymentTab, showInterestAdjustmentTab, showPaymentMoveTab]);
});
