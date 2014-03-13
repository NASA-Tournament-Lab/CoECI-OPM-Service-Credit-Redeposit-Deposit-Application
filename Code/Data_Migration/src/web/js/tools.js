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
            url : 'tools/serviceCreditPreferences',
            type : 'GET',
            cache : false,
            async : true,
            success : function(preference) {
                storedPreference = preference;
                renderPreferenceTab();
            },
            error : function(request, status, error) {
                alert('Failed to get service credit preference: ' + request.responseText);
            }
        });
    }

    // Show preference tab on page load
    showPreferenceTab();

    // Reset preference button click
    $('.jsResetPreference').click(function(){
        renderPreferenceTab();
        $('#useAgents').prop('checked', false);
        $('#useStatusBar').prop('checked', false);
        $('#useMessageBox').prop('checked', false);
        $('#otherSetting').val("");
    });

    // Reset preference button click
    $('.jsCancelPreference').click(function(){
        renderPreferenceTab();
    });

    $('.toolsForm input').keypress(function(e){
        if ( e.which == 13 ) return false;
    });

    // Save preference button click
    $('.jsSavePreference').click(function(){
        var newPreference = {};
        newPreference.useAgents = $('#useAgents').prop('checked');
        newPreference.useStatusBar = $('#useStatusBar').prop('checked');
        newPreference.useMessageBox = $('#useMessageBox').prop('checked');
        newPreference.otherSetting = $('#otherSetting').val();

        if (newPreference.otherSetting && newPreference.otherSetting.length > 128) {
            alert('Other setting max supports 128 chars.');
            return false;
        } else if($.trim(newPreference.otherSetting) == ''){
            alert('Enter a non empty Other setting.');
            return false;
        }

        $.ajax({
            url : 'tools/serviceCreditPreferences',
            type : 'POST',
            cache : false,
            async : true,
            contentType : 'application/json',
            data : JSON.stringify(newPreference),
            success : function() {
                storedPreference = newPreference;
                showPopup('.savePreferenceSuccessPopup');
            },
            error : function(request, status, error) {
                alert('Failed to save service credit preference: ' + request.responseText);
            }
        });
    });

    /** Following are for printing out tab **/

    // Then function to be called when batch printout archive tab is shown
    function showBatchPrintoutArchiveTab() {

        $.ajax({
            url : 'tools/printouts',
            type : 'GET',
            cache : false,
            async : true,
            success : function(printouts) {
                $('#batchPrintoutArchiveTbl tbody').text('');
                for ( var i in printouts) {
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
            error : function(request, status, error) {
                alert('Failed to get print outs: ' + request.responseText);
            }
        });
    }

    $('#batchPrintoutArchiveTbl').delegate('tbody td', 'mouseenter', function(){
        $(this).parent().addClass('hovered');
    }).delegate('tbody td', 'mouseleave', function(){
        $(this).parent().removeClass('hovered');
    }).delegate('tbody td', 'click', function(){
        $('#printoutName').val($(this).parent().find('td:eq(0)').text());
        $('#printoutForm').trigger('submit');
    });

    /*** Following are for Printing Tools tab ( which is not in scope of this assembly ) ***/


    // Then function to be called when printing tools tab is shown
    function showPrintingToolsTab() {
    }

    $('.jsShowInitStatement').click(function(){
        showPopup('.initStatementPopup');
    });
    $('.jsDoPrintSampleInitialStatement').click(function(){
        return false;
    });

    $('.jsPrintChargeCard').click(function(){
        showPopup('.chargeCardPopup');
    });
    $('.jsDoPrintChargeCard').click(function(){
        return false;
    });

    // Tab switch
    switchTab([showPreferenceTab, showPrintingToolsTab, showBatchPrintoutArchiveTab]);
});
