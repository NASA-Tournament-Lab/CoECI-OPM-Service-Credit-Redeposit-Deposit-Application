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
    setupPage('TOOLS', 7);

    /** Following are for preference tab **/
    // The local stored preference
    var storedPreference = {};

    // Function to render preference tab
    function renderPreferenceTab() {
        $('#useAgents').prop('checked', storedPreference.useAgents);
        $('#useStatusBar').prop('checked', storedPreference.useStatusBar);
        $('#useMessageBox').prop('checked', storedPreference.useMessageBox);
        $('#otherSetting').val(storedPreference.otherSetting);
    }

    // Then function to be called when preference tab is shown
    function showPreferenceTab() {

        $.ajax({
            url: 'tools/serviceCreditPreferences',
            type: 'GET',
            cache: false,
            async: true,
            success: function(preference) {
                storedPreference = preference;
                renderPreferenceTab();
            },
            error: function(request, status, error) {
                alert('Failed to get service credit preference: ' + request.responseText);
            }
        });
    }

    // Show preference tab on page load
    showPreferenceTab();

    // Reset preference button click
    $('.jsResetPreference').click(function() {
        renderPreferenceTab();
        $('#useAgents').prop('checked', false);
        $('#useStatusBar').prop('checked', false);
        $('#useMessageBox').prop('checked', false);
        $('#otherSetting').val("");
    });

    // Reset preference button click
    $('.jsCancelPreference').click(function() {
        renderPreferenceTab();
    });

    $('.toolsForm input').keypress(function(e) {
        if (e.which == 13)
            return false;
    });

    // Save preference button click
    $('.jsSavePreference').click(function() {
        var newPreference = {};
        newPreference.useAgents = $('#useAgents').prop('checked');
        newPreference.useStatusBar = $('#useStatusBar').prop('checked');
        newPreference.useMessageBox = $('#useMessageBox').prop('checked');
        newPreference.otherSetting = $('#otherSetting').val();

        if (newPreference.otherSetting && newPreference.otherSetting.length > 128) {
            alert('Other setting max supports 128 chars.');
            return false;
        } else if ($.trim(newPreference.otherSetting) == '') {
            alert('Enter a non empty Other setting.');
            return false;
        }

        $.ajax({
            url: 'tools/serviceCreditPreferences',
            type: 'POST',
            cache: false,
            async: true,
            contentType: 'application/json',
            data: JSON.stringify(newPreference),
            success: function() {
                storedPreference = newPreference;
                showPopup('.savePreferenceSuccessPopup');
            },
            error: function(request, status, error) {
                alert('Failed to save service credit preference: ' + request.responseText);
            }
        });
    });

    /** Following are for printing out tab **/

    // Then function to be called when batch printout archive tab is shown
    function showBatchPrintoutArchiveTab() {

        $.ajax({
            url: 'tools/printouts',
            type: 'GET',
            cache: false,
            async: true,
            success: function(printouts) {
                $('#batchPrintoutArchiveTbl tbody').text('');
                for (var i in printouts) {
                    var printout = printouts[i];
                    var printoutRow = ['<tr>'];
                    printoutRow = printoutRow.concat(['<td>', printout.name, '</td>']);
                    printoutRow = printoutRow.concat(['<td>', formatCaseDate(printout.printDate), '</td>']);
                    printoutRow.push('</tr>');
                    $('#batchPrintoutArchiveTbl tbody').append($(printoutRow.join('')));
                }
                $('.batchPrintoutArchiveTab .sortable th.defaultSortCol .sortarrow').attr('sortdir', 'down');
                $('.batchPrintoutArchiveTab .sortable th.defaultSortCol').trigger('click');
            },
            error: function(request, status, error) {
                alert('Failed to get print outs: ' + request.responseText);
            }
        });
    }

    $('#batchPrintoutArchiveTbl').delegate('tbody td', 'mouseenter', function() {
        $(this).parent().addClass('hovered');
    }).delegate('tbody td', 'mouseleave', function() {
        $(this).parent().removeClass('hovered');
    }).delegate('tbody td', 'click', function() {
        $('#printoutName').val($(this).parent().find('td:eq(0)').text());
        $('#printoutForm').trigger('submit');
    });

    /*** Following are for Printing Tools tab ( which is not in scope of this assembly ) ***/


    // Then function to be called when printing tools tab is shown
    function showPrintingToolsTab() {
    }

    $('.jsShowInitStatement').click(function() {
        var popup = $('.initStatementPopup');
        var context = $('#context').val();
        var accountId = $('#selectedAccountId').val();
        var account = getAccount(context, accountId);
        var holder = account.holder;
        var version = getOfficialVersion(account.calculationVersions);
        // basic information
        $('.report-csd', popup).html(account.claimNumber);
        $('.report-birthday', popup).html(parseDateToString(holder.birthDate));
        $('.printAddressBody', popup).html(buildAddrerssString(holder.address));
        $('.report-name', popup).html(holder.firstName + ' ' + holder.middleInitial + ' ' + holder.lastName);
        $('.report-date', popup).html(parseDateToString(version.calculationDate));
        $('.coverby', popup).html(account.planType);
        // billing information
        var billings = account.billingSummary.billings;
        var trs = $('.billing1-tbody', popup).find('tr');
        var total = 0;
        $.each(billings, function() {
            var i = mapping[this.name];
            trs.eq(i * 3 + 1).find('td').eq(1).html(this.initialBilling);
            trs.eq(i * 3 + 2).find('td').eq(1).html(this.additionalInterest);
            trs.eq(i + 31).find('td').eq(1).html(this.initialBilling + this.totalPayments + this.additionalInterest);
            total += this.initialBilling + this.totalPayments + this.additionalInterest;
        });
        $('.billing-total', popup).html(total);
        // calculate less payments
        var lessPayment = 0;
        $.each(account.paymentHistory, function() {
            if (this.approvalStatus == 'APPROVED') {
                lessPayment += this.amount;
            }
        });
        $('.less-payments', popup).html(lessPayment);
        $('.balance-due', popup).html(account.balance);
        var items = version.calculationResult.items;
        var html = "";
        for (var i = 0; i < items.length / 2; i++) {
            html += '<tr>';
            html += '<td>' + parseDateToString(items[i * 2].startDate) + '</td>';
            html += '<td>' + parseDateToString(items[i * 2].endDate) + '</td>';
            var typeStr = 'F';
            if (items[i * 2].retirementType == 'CSRS') {
                if (items[i * 2].periodType.name == 'DEPOSIT') {
                    typeStr = 'D';
                } else {
                    typeStr = 'R';
                }
            }
            html += '<td>' + typeStr + '</td>';
            if (i * 2 + 1 < items.length) {
                html += '<td>' + parseDateToString(items[i * 2 + 1].startDate) + '</td>';
                html += '<td>' + parseDateToString(items[i * 2 + 1].endDate) + '</td>';
                var typeStr = 'F';
                if (items[i * 2 + 1].retirementType == 'CSRS') {
                    if (items[i * 2 + 1].periodType.name == 'DEPOSIT') {
                        typeStr = 'D';
                    } else {
                        typeStr = 'R';
                    }
                }
                html += '<td>' + typeStr + '</td>';
            } else {
                html += '<td></td><td></td><td></td>';
            }
            html += '</tr>';
        }
        $('.calculationResult-tbody').html(html);
        $('.dollar').formatCurrency({
            negativeFormat: '%s-%n'
        });
        showPopup(".initStatementPopup");
    });
    $('.jsDoPrintSampleInitialStatement').click(function() {
        return false;
    });

    $('.jsPrintChargeCard').click(function() {
        showPopup('.chargeCardPopup');
    });
    $('.jsDoPrintChargeCard').click(function() {
        return false;
    });

    // Tab switch
    switchTab([showPreferenceTab, showPrintingToolsTab, showBatchPrintoutArchiveTab]);
});
