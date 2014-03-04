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
    setupPage('ADMIN', 8);

    // Then function to be called when regenerate reports tab is shown
    function showRegenerateReportsTab() {

        $.ajax({
            url : 'admin/reportGenerationData',
            type : 'GET',
            cache : false,
            async : true,
            success : function(reportGenerationData) {
                $('#latestReportsTbl tbody tr:eq(0) td:eq(1)').text(reportGenerationData.paymentInvoicesProcessed
                        ? reportGenerationData.paymentInvoicesProcessed + ' payments processed.' : 'None payments processed.');

                $('#latestReportsTbl tbody tr:eq(1) td:eq(1)').text(reportGenerationData.billsPrinted
                        ? reportGenerationData.billsPrinted + ' bills printed.' : 'None bills printed.');

                $('#latestReportsTbl tbody tr:eq(2) td:eq(1)').text(reportGenerationData.revealsPrinted
                        ? reportGenerationData.revealsPrinted + ' reversals printed.' : 'None reversals printed.');

                $('#latestReportsTbl tbody tr:eq(3) td:eq(1)').text(reportGenerationData.lettersPrinted
                        ? reportGenerationData.lettersPrinted + ' letters printed.' : 'None letters printed.');

                $('#latestReportsTbl tbody tr:eq(4) td:eq(1)').text(reportGenerationData.refundsPrinted
                        ? reportGenerationData.refundsPrinted + ' refunds printed.' : 'None refunds printed.');

                $('.priBtn').removeClass('priBtnDisabled');
                if (!reportGenerationData.paymentInvoicesProcessed) {
                    $('.jsRegeneratePaymentInvoices').addClass('priBtnDisabled');
                }
                if (!reportGenerationData.billsPrinted) {
                    $('.jsRegenerateInitialBillInvoices').addClass('priBtnDisabled');
                }
                if (!reportGenerationData.revealsPrinted) {
                    $('.jsRegenerateReversalInvoices').addClass('priBtnDisabled');
                }
                if (!reportGenerationData.lettersPrinted) {
                    $('.jsRegenerateStopACHLetters').addClass('priBtnDisabled');
                }
                if (!reportGenerationData.refundsPrinted) {
                    $('.jsRegenerateRefundMemos').addClass('priBtnDisabled');
                }
            },
            error : function(request, status, error) {
                alert('Failed to get report generation data: ' + request.responseText);
            }
        });
    }

    // Show regenerate reports tab on page load
    showRegenerateReportsTab();

    $('.jsRegeneratePaymentInvoices').click(function(){
        if ($(this).hasClass('priBtnDisabled')) {
            return;
        }
        showPopup('.paymentInvoicesPopup');
    });
    $('.jsDoPrintPaymentInvoices').click(function(){
        return false;
    });

    $('.jsRegenerateInitialBillInvoices').click(function(){
        if ($(this).hasClass('priBtnDisabled')) {
            return;
        }
        showPopup('.initialBillInvoicesPopup');
    });
    $('.jsDoPrintInitialBillInvoices').click(function(){
        return false;
    });

    $('.jsRegenerateReversalInvoices').click(function(){
        if ($(this).hasClass('priBtnDisabled')) {
            return;
        }
        showPopup('.reversalInvoicesPopup');
    });
    $('.jsDoPrintReversalInvoices').click(function(){
        return false;
    });

    $('.jsRegenerateStopACHLetters').click(function(){
        if ($(this).hasClass('priBtnDisabled')) {
            return;
        }
        showPopup('.stopACHPopup');
    });
    $('.jsDoPrintStopACHLetters').click(function(){
        return false;
    });

    $('.jsRegenerateRefundMemos').click(function(){
        if ($(this).hasClass('priBtnDisabled')) {
            return;
        }
        showPopup('.refundMemosPopup');
    });
    $('.jsDoPrintRefundMemos').click(function(){
        return false;
    });


    /** Followings are for user permissions **/

    // The function to build user row
    function buildUserRow(user) {
        var userRow = ['<tr>'];
        userRow = userRow.concat(['<td class="firstCol">', user.networkId, '</td>']);
        userRow = userRow.concat(['<td>', user.firstName, '</td>']);
        userRow = userRow.concat(['<td>', user.lastName, '</td>']);
        userRow = userRow.concat(['<td class="lastCol">', isNull(user.role)?'':user.role.description, '</td>']);
        userRow.push('</tr>');
        userRow = $(userRow.join(''));
        userRow.data('userId', user.id);
        return userRow;
    }

    // The function to be called when user permissions tab is shown
    function showUserPermissionsTab() {

        // Load all users
        $.ajax({
            url : 'admin/user/list',
            type : 'GET',
            cache : false,
            async : true,
            success : function(users) {
                $('#userPermissionsTbl tbody').text('');
                for ( var i = 0; i < users.length; i++) {
                    var user = users[i];
                    $('#userPermissionsTbl tbody').append(buildUserRow(user));
                }
                // Sort up by default column
                $('#userPermissionsTbl th.defaultSortCol .sortarrow').attr('sortdir', 'up');
                $('#userPermissionsTbl th.defaultSortCol').trigger('click');
            },
            error : function(request, status, error) {
                alert('Failed to get users: ' + request.responseText);
            }
        });

        // Load all user statuses
        loadLookup('userStatuses', false);

        // Load all user roles
        loadLookup('roles', true);

    }

    $('#userPermissionsTbl').delegate('tbody td', 'mouseenter', function(){
        $(this).parent().addClass('hovered');
    }).delegate('tbody td', 'mouseleave', function(){
        $(this).parent().removeClass('hovered');
    }).delegate('tbody td', 'click', function(){
        if (($(this).parent().hasClass('highlighted'))){
            return false;
        }else{
            $('#userPermissionsTbl tr').removeClass('highlighted');
            var row = $(this).parent();
            row.addClass('highlighted');
        }
        return false;
    }).delegate('tbody td', 'dblclick', function(){
        $('#userPermissionsTbl tr').removeClass('highlighted');
        var row = $(this).parent();
        row.addClass('highlighted');

        // Load all supervisors
        $.ajax({
            url : 'admin/user/supervisors',
            type : 'GET',
            cache : false,
            async : true,
            success : function(supervisors) {
                var superVisorIds = [];
                $('#supervisors').text('');
                $('#supervisors').append('<option value="-1">Select</option>');
                for ( var i in supervisors) {
                    var supervisor = supervisors[i];
                    var supervisorOption = '<option>' + supervisor.firstName + ' ' + supervisor.lastName + '</option>';
                    supervisorOption = $(supervisorOption);
                    supervisorOption.val(supervisor.id);
                    superVisorIds.push(supervisor.id);
                    $('#supervisors').append(supervisorOption);
                }

                // On double click, show user edit popup
                $.ajax({
                    url : 'admin/user/' + row.data('userId'),
                    type : 'GET',
                    cache : false,
                    async : true,
                    success : function(user) {

                        // Render the popup
                        $('#userStatuses').val(user.status.id);
                        $('#roles').val(user.role.id);
                        $('#fName').val(user.firstName);
                        $('#lName').val(user.lastName);
                        $('#eMail').val(user.email);
                        $('#phone').val(user.telephone);
                        if (user.supervisorId && $.inArray(user.supervisorId, superVisorIds) > -1) {
                            // User may not have supervisor
                            $('#supervisors').val(user.supervisorId);
                        } else {
                            $('#supervisors').val(-1);
                        }

                        $('.editUserPermissionsPopup').data('editingUser', user);
                        $('.editUserPermissionsPopup').data('editingUserRow', row);

                        // Show popup
                        showPopup('.editUserPermissionsPopup');
                    },
                    error : function(request, status, error) {
                        alert('Failed to get user: ' + request.responseText);
                    }
                });
            },
            error : function(request, status, error) {
                alert('Failed to get supervisors: ' + request.responseText);
            }
        });


        return false;
    });

    // Save user
    $('.jsSaveUserPermission').click(function(){

        var editingUser = $('.editUserPermissionsPopup').data('editingUser');
        var editingUserRow = $('.editUserPermissionsPopup').data('editingUserRow');

        // User can not select himself as supervisor:
        if ($('#supervisors').val() == editingUser.id) {
            alert('User can not select himself as supervisor.');
            return false;
        }

        // Validate first name
        var firstName = $('#fName').val();
        if (!firstName) {
            alert('Field First Name is required.');
            return false;
        }
        if (firstName.length > 25) {
            alert('Field First Name max supports 25 chars.');
            return false;
        }
        if (firstName.match(/[\"\'<>]/)) {
            alert('Field First Name has invalid characters.');
            return false;
        }

        // Validate last name
        var lastName = $('#lName').val();
        if (!lastName) {
            alert('Field Last Name is required.');
            return false;
        }
        if (lastName.length > 50) {
            alert('Field Last Name max supports 50 chars.');
            return false;
        }
        if (lastName.match(/[\"\'<>]/)) {
            alert('Field Last Name has invalid characters.');
            return false;
        }

        // Validate email
        var email = $('#eMail').val();
        if (!email) {
            alert('Field Email Address is required.');
            return false;
        }
        if (email.length > 128) {
            alert('Email max supports 128 chars.');
            return false;
        }
        if (/^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$/g.test($.trim(email)) == false) {
            alert('Field Email Address value is invalid.');
            return false;
        }

        // Validate telephone
        var phone = $('#phone').val();
        if (!phone) {
            alert('Field Telephone is required.');
            return false;
        }
        phone = phone.replace(/\s+/g, "");
        if (phone.length < 10 || !phone.match(/^(\+?1-?)?(\([2-9]\d{2}\)|[2-9]\d{2})-?[2-9]\d{2}-?\d{4}$/)) {
            alert('Field Telephone value is invalid.');
            return false;
        }

        editingUser.status.id = $('#userStatuses').val();
        editingUser.status.name = $('#userStatuses option:selected').text();
        editingUser.role.id = $('#roles').val();
        editingUser.role.name = $('#roles option:selected').data('name');
        editingUser.role.description = $('#roles option:selected').text();
        editingUser.firstName = firstName;
        editingUser.lastName = lastName;
        editingUser.email = email;
        editingUser.telephone = $('#phone').val();
        if ($('#supervisors').val() > 0) {
            editingUser.supervisorId = $('#supervisors').val();
        } else {
            delete editingUser.supervisorId;
        }

        $.ajax({
            url : 'admin/user/update',
            type : 'PUT',
            cache : false,
            async : true,
            contentType : 'application/json',
            data : JSON.stringify(editingUser),
            success : function() {
                if (editingUser.id == window.globalUserId) {
                    window.globalUserRoleId = editingUser.role.id;
                    window.globalUserRoleName = editingUser.role.name;

                    $('.welcomeMsg > span').text(editingUser.firstName + ' ' + editingUser.lastName);
                    $('.roleDesc').text(editingUser.role.description);
                }

                editingUserRow.replaceWith(buildUserRow(editingUser));
                closePopup();
                showPopup('.saveUserSuccessPopup');
            },
            error : function(request, status, error) {
                alert('Failed to save user: ' + request.responseText);
            }
        });
    });

    // Tab switch
    switchTab([showRegenerateReportsTab, showUserPermissionsTab]);
});
