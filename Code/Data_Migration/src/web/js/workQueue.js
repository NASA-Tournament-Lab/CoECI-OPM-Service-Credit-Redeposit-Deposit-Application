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

$(document).ready(
        function() {
            // Setup page
            setupPage('WORK_QUEUE', 2);

            $.ajax({
                url : 'lookup/claimOfficers',
                type : 'GET',
                cache : false,
                async : true,
                global: false,
                success : function(claimOfficerObjs) {
                    var claimOfficersStr = '';
                    for ( var i in claimOfficerObjs) {
                        $('.accountAssignmentPopup ul').append('<li>' + claimOfficerObjs[i].name + '</li>');
                    }
                },
                error : function(request, status, error) {
                    alert("Failed to get claim officers: " + request.responseText);
                }
            });

            // Work Queue Page Filter click
            $('.caseOptions input:radio').eq(0).prop('checked', true);
            $('.caseOptions input:radio').change(function() {
                var currentVal = $('.caseOptions input[name=caseFilter]:checked').val();
                var panel = $(this).parents(".casesPanel").eq(0);
                $(".panelBody > div", panel).hide();
                $("." + currentVal + "Block", panel).show();
                $(".jsUnassignCase", panel).hide();
                if (currentVal === "showAssigned") {
                    $(".jsUnassignCase", panel).show();
                }
                $(".casesPanel tbody tr").removeClass("selectedRow");
            });

            // Select Case
            $('.casesPanel tbody tr').click(function() {
                $(this).toggleClass("selectedRow");
            });

            // Unassign Case
            $('.jsUnassignCase').click(function() {
                var currentVal = $('.caseOptions input[name=caseFilter]:checked').val();
                var currentTbl = $("." + currentVal + "Block table");
                if ($(".selectedRow", currentTbl).length < 1) {
                    showPopup(".wqUnassignmentPopup");
                } else {
                    $(".selectedRow", currentTbl).find(".assignee").parent().remove();
                    var rows = $(".selectedRow", currentTbl);
                    var pendingTbl = $("#allPendingCasesTbl tbody");
                    rows.each(function(index, element) {
                        var row = rows.eq(index);
                        $.ajax({
                            url : 'workQueue/unassign',
                            type : 'POST',
                            cache : false,
                            async : true,
                            contentType : 'application/json',
                            data : getAccountStr($(row).children("td:first").text()),
                            success : function() {
                                $(row).remove();

                                var count = $('.topNav > ul > li:eq(2) .notificationNum > span > span').text();
                                $('.topNav > ul > li:eq(2) .notificationNum > span > span').text(parseInt(count) + 1);
                            },
                            error : function(request, status, error) {
                                alert("Failed to unassign case: " + request.responseText);
                            }
                        });

                    });
                }
            });

            // Reassign Case
            $('.jsReassignCase').click(function() {
                var currentVal = $('.caseOptions input[name=caseFilter]:checked').val();
                var currentTbl = $("." + currentVal + "Block table");

                if ($(".selectedRow", currentTbl).length < 1) {
                    showPopup(".wqAssignmentPopup");
                } else {
                    showPopup(".accountAssignmentPopup");
                    $(".accountAssignmentPopup .accountList li").removeClass("selected").eq(0).addClass("selected");
                }
                $('.accountAssignmentPopup .leftAccountList .accountList').scrollTop(0);
                
            });

            $(".accountAssignmentPopup .accountList").delegate("li", "click", function() {
                $(".accountAssignmentPopup .accountList li").removeClass("selected");
                $(this).addClass("selected");
            });

            // Do Reassign Case
            $(".jsDoReassignCase").click(
                    function() {
                        var currentVal = $('.caseOptions input[name=caseFilter]:checked').val();
                        var assignedTbl = $("#assignedCasesTbl tbody");
                        var currentTbl = $("." + currentVal + "Block table");
                        var selectedAccount = $(this).parents(".popup").eq(0).find(".accountList li.selected");
                        if (selectedAccount.length > 0) {
                            accountName = selectedAccount.eq(0).text();
                            var rows = $(".selectedRow", currentTbl);
                            rows.each(function(index, element) {
                                var row = rows.eq(index);
                                $.ajax({
                                    url : 'workQueue/assign?claimOfficer=' + accountName,
                                    type : 'POST',
                                    cache : false,
                                    async : true,
                                    contentType : 'application/json',
                                    data : getAccountStr($(row).children("td:first").text()),
                                    success : function() {
                                        $(row).removeClass("selectedRow");
                                        if (currentVal === 'showAllPending') {
                                            $(row).remove();
                                        } else if (currentVal === 'showAssigned') {
                                            $("td", row).eq(6).text(accountName);
                                        }
                                        if (currentVal === 'showAllProcessed') {
                                            var caseObj = window['globalCase' + $(row).children("td:first").text()];
                                            if (!caseObj['claimOfficer']) {
                                                var count = $('.topNav > ul > li:eq(2) .notificationNum > span > span')
                                                        .text();
                                                $('.topNav > ul > li:eq(2) .notificationNum > span > span').text(
                                                        parseInt(count) - 1);
                                                caseObj['claimOfficer'] = accountName;
                                            }
                                        }
                                    },
                                    error : function(request, status, error) {
                                        alert("Failed to reassign case: " + request.responseText);
                                    }
                                });

                            });
                            closePopup();

                            if (currentVal === 'showAllPending') {
                                var count = $('.topNav > ul > li:eq(2) .notificationNum > span > span').text();
                                $('.topNav > ul > li:eq(2) .notificationNum > span > span').text(
                                        parseInt(count) - selectedAccount.length);
                            }
                        }
                    });

            // Cancel Reassign Case
            $(".jsCancelReassignCase").click(function() {
                closePopup();
            });

            // Assign to me
            $('.jsAssignToMe').click(
                    function() {
                        var currentVal = $('.caseOptions input[name=caseFilter]:checked').val();
                        var currentTbl = $("." + currentVal + "Block table");
                        var selectedAccount = $(".selectedRow", currentTbl).length;
                        if (selectedAccount < 1) {
                            showPopup(".wqAssignmentPopup");
                        } else {
                            var assignedTbl = $("#assignedCasesTbl tbody");

                            var rows = $(".selectedRow", currentTbl);
                            rows.each(function(index, element) {
                                var row = rows.eq(index);
                                $.ajax({
                                    url : 'workQueue/assign?claimOfficer=' + window.globalUsername,
                                    type : 'POST',
                                    cache : false,
                                    async : true,
                                    contentType : 'application/json',
                                    data : getAccountStr($(row).children("td:first").text()),
                                    success : function() {
                                        $(row).removeClass("selectedRow");

                                        if (currentVal === 'showAllPending') {
                                            $(row).remove();
                                        } else if (currentVal === 'showAssigned') {
                                            $("td", row).eq(6).text(window.globalUsername);
                                        }
                                        if (currentVal === 'showAllProcessed') {
                                            var caseObj = window['globalCase' + $(row).children("td:first").text()];
                                            if (!caseObj['claimOfficer']) {
                                                var count = $('.topNav > ul > li:eq(2) .notificationNum > span > span')
                                                        .text();
                                                $('.topNav > ul > li:eq(2) .notificationNum > span > span').text(
                                                        parseInt(count) - 1);
                                                caseObj['claimOfficer'] = window.globalUsername;
                                            }
                                        }
                                    },
                                    error : function(request, status, error) {
                                        alert("Failed to assign to me: " + request.responseText);
                                    }
                                });
                            });

                            if (currentVal === 'showAllPending') {
                                var count = $('.topNav > ul > li:eq(2) .notificationNum > span > span').text();
                                $('.topNav > ul > li:eq(2) .notificationNum > span > span').text(
                                        parseInt(count) - selectedAccount);
                            }
                        }
                    });

            // double click to open case
            $("#assignedCasesTbl tbody").delegate("tr", "dblclick", function() {
                // find the accountId
                var accountId = $(this).find("td:eq(0)").text();
                var context = $('#context').val();
                window.location = context + "/account/" + accountId + "/detail";
            });

            $(".popupLinksWrapper .notificationsViewAll").click(function() {
                window.location = "NotificationLog_Notifications.html";
            });

            // Get assigned cases
            $('#showAssigned').click(function() {
                getAssignedCases();
            });

            // Get pending cases
            $('#showAllPending').click(function() {
                getPendingCases();
            });

            // Initial pending cases
            $("#showAllPending").trigger("click");

            // Get processed cases
            $('#showAllProcessed').click(function() {
                getProcessedCases();
            });

            // Double click event handler for pending cases
            $("#allPendingCasesTbl tbody").delegate("tr", "dblclick", function() {
                var thisObj = this;
                $.ajax({
                    url : 'workQueue/assign?claimOfficer=' + window.globalUsername,
                    type : 'POST',
                    cache : false,
                    async : true,
                    contentType : 'application/json',
                    data : getAccountStr($(thisObj).children("td:first").text()),
                    success : function() {
                        $(thisObj).remove();

                        var count = $('.topNav > ul > li:eq(2) .notificationNum > span > span').text();
                        $('.topNav > ul > li:eq(2) .notificationNum > span > span').text(parseInt(count) - 1);
                    },
                    error : function(request, status, error) {
                        alert("Failed to assign to me: " + request.responseText);
                    }
                });
            });
            // Double click event handler for processed cases
            $("#allProcessedCasesTbl tbody").delegate("tr", "dblclick", function() {
                var thisObj = this;
                $.ajax({
                    url : 'workQueue/assign?claimOfficer=' + window.globalUsername,
                    type : 'POST',
                    cache : false,
                    async : true,
                    contentType : 'application/json',
                    data : getAccountStr($(thisObj).children("td:first").text()),
                    success : function() {
                        var caseObj = window['globalCase' + $(thisObj).children("td:first").text()];
                        if (!caseObj['claimOfficer']) {
                            var count = $('.topNav > ul > li:eq(2) .notificationNum > span > span').text();
                            $('.topNav > ul > li:eq(2) .notificationNum > span > span').text(parseInt(count) - 1);
                            caseObj['claimOfficer'] = window.globalUsername;
                        }
                    },
                    error : function(request, status, error) {
                        alert("Failed to assign to me: " + request.responseText);
                    }
                });
            });

            // Click event handler for cases
            $('.casesPanel tbody').delegate("tr", "click", function() {
                $(this).toggleClass("selectedRow");
            });



            //mockup pagination
    $(".paginationLabel").each(function(index, element) {
        var wrapper = $(this).parents(".pagination").eq(0);
        $(this).find(".totalCount").text($(".toPage", wrapper).length * 10);
        $(this).find(".startCount").text("1");
        $(this).find(".endCount").text("10");
    });


    $('.casesPanel').delegate('.toPrev', 'click', function() {
        var wrapper = $(this).parent().parent();
        if ((!$(this).hasClass("toPrevDisabled")) && ($('.toPage', wrapper).length > 1)){
            var prevItem = $('.current', wrapper).prev(".toPage");
            prevItem.trigger("click");
        }
    });

    $('.casesPanel').delegate('.toNext', 'click', function() {
        var wrapper = $(this).parent().parent();
        if ((!$(this).hasClass("toNextDisabled")) && ($('.toPage', wrapper).length > 1)){
            var nextItem = $('.current', wrapper).next(".toPage");
            nextItem.trigger("click");
        }
    });

    $('.casesPanel').delegate('.toPage', 'click', function() {
        var wrapper = $(this).parent().parent();
        if ((!$(this).hasClass("current")) && ($('.toPage', wrapper).length > 1)){
            $('.toPage', wrapper).removeClass("current");
            $(this).addClass("current");
            $('.toNext', wrapper).removeClass("toNextDisabled");
            $('.toPrev', wrapper).removeClass("toPrevDisabled");
            var currentIdx = parseInt($(this).text(), 10);
            var totalIdx =  parseInt($('.toPage', wrapper).length, 10);
            if (currentIdx == 1){
                $('.toPrev', wrapper).addClass("toPrevDisabled");
            }
            if (currentIdx == totalIdx ){
                $('.toNext', wrapper).addClass("toNextDisabled");
            }
        }
        var block = $(this).parents().eq(3);
        if(block.hasClass('showAssignedBlock')){

            getAssignedCases();

        } else if(block.hasClass('showAllPendingBlock')){
            getPendingCases();

        } else if(block.hasClass('showAllProcessedBlock')){
            getProcessedCases();

        }
        //var pageSize = parseInt($('.perPage select', wrapper).val(), 10 );
        //wrapper.find(".startCount").text( (parseInt($(".current", wrapper).text(),10)-1)*pageSize + 1 );
        //wrapper.find(".endCount").text(parseInt($(".current", wrapper).text(), 10)* pageSize  );
        //hidePagination($(this));
    });

    $('.casesPanel').delegate('.pagination .perPage select', 'change', function() {

        var block = $(this).parents().eq(3);
        if(block.hasClass('showAssignedBlock')){

            getAssignedCases();

        } else if(block.hasClass('showAllPendingBlock')){
            getPendingCases();

        } else if(block.hasClass('showAllProcessedBlock')){
            getProcessedCases();

        }

    });


    $(".pagination .current").each(function() {
        hidePagination($(this));
    });


        });

// Get processed cases
function getProcessedCases() {
    var filter = {};
    var perPage = $('.showAllProcessedBlock .pagination .perPage select');
    if(perPage.length <= 0 || perPage.val() == ""){
        filter.pageSize = 10;
    } else {
        filter.pageSize = parseInt(perPage.val(), 10);
    }

    var pageNumber = $('.showAllProcessedBlock .pagination .paginationLinks .current');
    if(pageNumber.length <= 0 || pageNumber.text() == ""){
        filter.pageNumber = 1;
    } else {
        filter.pageNumber = parseInt(pageNumber.text(), 10);
    }
    $.ajax({
        url : 'workQueue/processedCases',
        type : 'POST',
        cache : false,
        async : true,
        contentType: 'application/json',
        data : JSON.stringify(filter),
        success : function(data) {

            var casesArray = data.items;
            $('.showAllProcessedBlock tbody').text('');

            for ( var i in casesArray) {
                var caseObj = casesArray[i];
                var caseId = caseObj['id'];
                window['globalCase' + caseId] = caseObj;

                var middleInitial = caseObj['middleInitial'];

                var caseStr = '<tr><td class="isHidden">' + caseId + '</td><td class="blankCell">&nbsp;</td>' + '<td>'
                        + caseObj['claimNumber'] + '</td>' + '<td><span class="ssn">' + caseObj['ssn'] + '</span></td>'
                        + '<td>' + caseObj['firstName'] + ' ' + (middleInitial ? (middleInitial + ' ') : '')
                        + caseObj['lastName'] + '</td>' + '<td>' + formatCaseDate(caseObj['returnedFromRecordsDate'])
                        + '</td></tr>';

                $(".showAllProcessedBlock tbody").append(caseStr);
            }

            data.pageNumber = filter.pageNumber;
            data.pageSize = filter.pageSize;
            updatePagination(data, $('.showAllProcessedBlock'));
        },
        error : function(request, status, error) {
            alert("Failed to get processed cases: " + request.responseText);
        }
    });
}

// Get account string
function getAccountStr(id) {
    return '{"id":'
            + id
            + ', "claimNumber":null, "planType":null, "formType":null, "holder":null, "status":null,'
            + ' "grace":false, "frozen":false, "notes":null, "claimOfficer":null, "claimOfficerAssignmentDate":null, "claimantBirthdate":null,'
            + ' "balance":null, "returnedFromRecordsDate":null, "billingSummary":null, "calculationVersions":null,'
            + ' "paymentHistory":null, "validity":null}';
}

// Get pending cases
function getPendingCases() {
    var filter = {};
    var perPage = $('.showAllPendingBlock .pagination .perPage select');
    if(perPage.length <= 0 || perPage.val() == ""){
        filter.pageSize = 10;
    } else {
        filter.pageSize = parseInt(perPage.val(), 10);
    }

    var pageNumber = $('.showAllPendingBlock .pagination .paginationLinks .current');
    if(pageNumber.length <= 0 || pageNumber.text() == ""){
        filter.pageNumber = 1;
    } else {
        filter.pageNumber = parseInt(pageNumber.text(), 10);
    }
    $.ajax({
        url : 'workQueue/unassignedCases',
        type : 'POST',
        cache : false,
        async : true,
        contentType: 'application/json',
        data : JSON.stringify(filter),
        success : function(data) {
            $('.showAllPendingBlock tbody').text('');
            var casesArray = data.items;

            for ( var i in casesArray) {
                var caseObj = casesArray[i];
                var caseId = caseObj['id'];

                var middleInitial = caseObj['middleInitial'];

                var caseStr = '<tr><td class="isHidden">' + caseId + '</td><td class="blankCell">&nbsp;</td>' + '<td>'
                        + caseObj['claimNumber'] + '</td>' + '<td><span class="ssn">' + caseObj['ssn'] + '</span></td>'
                        + '<td>' + caseObj['firstName'] + ' ' + (middleInitial ? (middleInitial + ' ') : '')
                        + caseObj['lastName'] + '</td>' + '<td>' + formatCaseDate(caseObj['returnedFromRecordsDate'])
                        + '</td></tr>';

                $(".showAllPendingBlock tbody").append(caseStr);
            }

            data.pageNumber = filter.pageNumber;
            data.pageSize = filter.pageSize;
            updatePagination(data, $('.showAllPendingBlock'));
        },
        error : function(request, status, error) {
            alert("Failed to get unassigned cases: " + request.responseText);
        }
    });
}

// Get assigned cases
function getAssignedCases() {
    var filter = {};

    var perPage = $('.showAssignedBlock .pagination .perPage select');
    if(perPage.length <= 0 || perPage.val() == ""){
        filter.pageSize = 10;
    } else {
        filter.pageSize = parseInt(perPage.val(), 10);
    }

    var pageNumber = $('.showAssignedBlock .pagination .paginationLinks .current');
    if(pageNumber.length <= 0 || pageNumber.text() == ""){
        filter.pageNumber = 1;
    } else {
        filter.pageNumber = parseInt(pageNumber.text(), 10);
    }
    


    $.ajax({
        url : 'workQueue/assignedCases',
        type : 'POST',
        cache : false,
        async : true,
        contentType: 'application/json',
        data : JSON.stringify(filter),
        success : function(data) {
            $('.showAssignedBlock tbody').text('');
            var casesArray = data.items;

            for ( var i in casesArray) {
                var caseObj = casesArray[i];

                var caseId = caseObj['id'];

                var caseStr = '<tr><td class="isHidden">' + caseId + '</td><td class="blankCell">&nbsp;</td>' + '<td>'
                        + caseObj['claimNumber'] + '</td>' + '<td><span class="ssn">' + caseObj['ssn'] + '</span></td>'
                        + '<td>' + caseObj['firstName'] + ', ' + caseObj['lastName'] + '</td>' + '<td>'
                        + formatCaseDate(caseObj['claimOfficerAssignmentDate']) + '</td>' + '<td>'
                        + caseObj['claimOfficer'] + '</td></tr>';

                $(".showAssignedBlock tbody").append(caseStr);
            }

            data.pageNumber = filter.pageNumber;
            data.pageSize = filter.pageSize;
            updatePagination(data, $('.showAssignedBlock'));
        },
        error : function(request, status, error) {
            alert("Failed to get assigned cases: " + request.responseText);
        }
    });
}


function hidePagination(jqObj){
    var pWrapper = jqObj.parent();
    $(".toPage", pWrapper).show();
    $(".hidePage", pWrapper).remove();
    var currentNum = parseInt(jqObj.text(), 10);
    $(".toPage", pWrapper).each(function(){
        var pNum = parseInt($(this).text(), 10);
        if ( (( pNum - currentNum) > 5) || (( currentNum - pNum) > 5)){
            $(this).hide();
        }
        if ( (( pNum - currentNum) > 5) || (( currentNum - pNum) > 5)){
            $(this).hide();
        }
    });
    if ($(".toPage:visible", pWrapper).eq(0).text() !== "1"){
        $(".toPage:visible", pWrapper).eq(0).before("<span class='hidePage'>...</span>");
    }
    var idx1 = parseInt($(".toPage:visible", pWrapper).length, 10) - 1 ;
    var idx2 = parseInt($(".toPage", pWrapper).length, 10) - 1 ;
    if (($(".toPage:visible", pWrapper).eq(idx1).text()) !== ($(".toPage", pWrapper).eq(idx2).text())){
        $(".toPage:visible", pWrapper).eq(idx1).after("<span class='hidePage'>...</span>");
    }
    
}


// Update Pagination

function updatePagination(result, block){

    var html = '';

    if(result.pageNumber <= 1){
        html += '<a href="javascript:;" class="toPrev toPrevDisabled">Prev</a>';
    } else   {
        html += '<a href="javascript:;" class="toPrev">Prev</a>';
    }

    for(var i=1; i<= result.totalPageCount; i++){

        if(i==result.pageNumber){
            html += '<a href="javascript:;" class="toPage current">'+i+'</a>';
        } else {
            html += '<a href="javascript:;" class="toPage">'+i+'</a>';
        }

    }

    if(result.totalPageCount >= 2){
        html += '<a href="javascript:;" class="toNext">Next</a>';
    } else {
        html += '<a href="javascript:;" class="toNext toNextDisabled">Next</a>';
    }

    $('.pagination .paginationLinks', block).html(html);

    //$('.pagination .endCount', block).text($('.pagination .perPage select', block).val());
    var startCount = parseInt(result.pageSize, 10)*(result.pageNumber -1)+1;
    $('.pagination .startCount', block).text(startCount);
    var endCount = parseInt(result.pageSize, 10)*result.pageNumber;
    if(endCount > parseInt(result.total,10)){
        endCount = result.total;
    }
    $('.pagination .endCount', block).text(endCount);
    $('.pagination .totalCount', block).text(result.total);

    var currentPage = $('.pagination .paginationLinks .current', block);
    hidePagination(currentPage);



}


