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

// The interval to retrieve notifications in seconds
var notificationsInterval = 50;
// The auto search interval
var autoSearchInterval = 2;
// The last notifications in days
var notificationsInLastDays = 15;
// current logged in user object
window.user = null;
// calculation result cache
window.results = {};
// represent the search filter is triggered or not
var filterEnabled = false;

$(document).ready(function() {
    var context = $('#context').val();
    $(document).ajaxStart(function() {
        $("#loadingOverlay").show();
    });
    $(document).ajaxStop(function() {
        $("#loadingOverlay").hide();
    });

    $(document).ajaxComplete(function(event, xhr, ajaxOptions) {
        // For ajax calling, the server only returns 200 status code
        // and json content for success calling.
        // If 302 or text/html content is returned, assumes that login expired
        if (xhr.status === 302) {
            window.location.reload();
        } else if (xhr.status === 200) {
            var contentType = xhr.getResponseHeader("Content-Type");
            if (contentType && contentType.toLowerCase().indexOf("text/html") >= 0) {
                window.location.reload();
            }
        }
    });

    // Get current user
    $.ajax({
        url: context + '/common/currentUser',
        type: 'GET',
        cache: false,
        async: false,
        global: false,
        success: function(userObj) {
            $('.welcomeMsg > a').text(userObj['firstName'] + ' ' + userObj['lastName']);
            $('.roleDesc').text(userObj['role']['description']);

            window.globalUserRoleName = userObj['role']['name'];
            window.globalUserRoleId = userObj['role']['id'];
            window.globalUserId = userObj['id'];
            window.globalUsername = userObj['username'];
            user = userObj;
            // Get new notifications for the user
            getNotifications();
        },
        error: function(request, status, error) {
            alert("Failed to get current user: " + request.responseText);
        }
    });



    function getNotifications() {
        $.ajax({
            url: context + '/common/notifications',
            type: 'POST',
            cache: false,
            async: true,
            global: false,
            contentType: 'application/json',
            data: '{"sortColumn":"date","sortOrder":"DESC","pageNumber":0,"pageSize":0,"sender":null,"read":false,"recipient":"' + window.globalUsername + '","recipientRole":{"name":"' + window.globalUserRoleName + '"}}',
            success: function(notificationsArray) {
                $('.jsShowNotifications .notificationNum > span > span').text(notificationsArray.length);

                $('.notificationPopup tbody').text('');

                $('.infoTblWrapper tbody .rowNoti').parent().parent().remove();

                var beginDateTime = (new Date()).getTime() - notificationsInLastDays * 86400000;
                for (var i in notificationsArray) {
                    var notificationObj = notificationsArray[i];
                    var dateValue = notificationObj['date'];

                    var notificationStr = '<tr><td>' + formatNotificationDate(dateValue) + '<br/>' + formatNotificationTime(dateValue) + '</td>'
                            + '<td>' + notificationObj['sender'] + '</td>' + '<td>' + notificationObj['details'] + '</td></tr>';

                    $(".notificationPopup tbody").append(notificationStr);

                    if (($('.infoNotiPopup .filerTab li a:eq(0)').hasClass("current") || $('.infoNotiPopup .filerTab li a:eq(1)').hasClass("current")) && (new Date(dateValue).getTime() > beginDateTime)) {
                        var notificationStr2 = '<tr><td><span class="rowNoti"></span>' + formatNotificationDate(dateValue) + '</td>'
                                + '<td>' + formatNotificationTime(dateValue) + '</td>'
                                + '<td class="lastCol">Notification from ' + notificationObj['sender'] + ':' + notificationObj['details'] + '</td></tr>';
                        $(".infoTblWrapper tbody").append(notificationStr2);
                    }
                }
            },
            error: function(request, status, error) {
                alert("Failed to get notifications: " + request.responseText);
            }
        });

        // Call getNotifications repeatedly
        setTimeout(getNotifications, notificationsInterval * 1000);
    }

    function getErrors() {
        $.ajax({
            url: context + '/common/errors',
            type: 'POST',
            cache: false,
            async: true,
            global: false,
            contentType: 'application/json',
            data: '{"sortColumn":"date","sortOrder":"DESC","pageNumber":0,"pageSize":0}',
            success: function(errorsArray) {

                $('.infoTblWrapper tbody .rowErr').parent().parent().remove();

                var beginDateTime = (new Date()).getTime() - notificationsInLastDays * 86400000;
                for (var i in errorsArray) {
                    var errorObj = errorsArray[i];
                    var dateValue = errorObj['date'];

                    if (($('.infoNotiPopup .filerTab li a:eq(0)').hasClass("current") || $('.infoNotiPopup .filerTab li a:eq(2)').hasClass("current")) && (new Date(dateValue).getTime() > beginDateTime)) {
                        var errorStr = '<tr><td><span class="rowErr"></span>' + formatNotificationDate(dateValue) + '</td>' + '<td>' + formatNotificationTime(dateValue) + '</td>' + '<td class="lastCol">Error from System:' + errorObj['details'] + '</td></tr>';
                        $(".infoTblWrapper tbody").append(errorStr);
                    }
                }
            },
            error: function(request, status, error) {
                alert("Failed to get errors: " + request.responseText);
            }
        });

        // Call getErrors repeatedly
        setTimeout(getErrors, notificationsInterval * 1000);
    }


    // activate auto search
    setTimeout(autoSearch, autoSearchInterval * 1000);

    $('.viewAccountSearchForm input:text').click(function() {
        filterEnabled = true;
    });
    function autoSearch() {

        var filter = gatherAccountSearchFilterParam();
        if (filter.birthDate || filter.ssn || filter.firstName || filter.lastName || filter.middleName || filter.claimNumber) {
            if (filterEnabled == true) {
                $.ajax({
                    url: context + '/faces/search',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(filter),
                    success: function(data, text, xhr) {
                        window.location = context + '/faces/view';
                    },
                    error: function(a, b, c) {
                        alert('fail to search accounts, message:' + a.responseText);

                    }
                });
            }

        }
        setTimeout(autoSearch, autoSearchInterval * 1000);
    }
    function getInfos() {
        $.ajax({
            url: context + '/common/infos',
            type: 'POST',
            cache: false,
            async: true,
            global: false,
            contentType: 'application/json',
            data: '{"sortColumn":"date","sortOrder":"DESC","pageNumber":0,"pageSize":0}',
            success: function(infosArray) {
                $('.infoTblWrapper tbody .rowInfo').parent().parent().remove();

                var beginDateTime = (new Date()).getTime() - notificationsInLastDays * 86400000;
                for (var i in infosArray) {
                    var infoObj = infosArray[i];
                    var dateValue = infoObj['date'];

                    if (($('.infoNotiPopup .filerTab li a:eq(0)').hasClass("current") || $('.infoNotiPopup .filerTab li a:eq(3)').hasClass("current")) && (new Date(dateValue).getTime() > beginDateTime)) {
                        var infoStr = '<tr><td><span class="rowInfo"></span>' + formatNotificationDate(dateValue) + '</td>'
                                + '<td>' + formatNotificationTime(dateValue) + '</td>' + '<td class="lastCol">Info from System:' + infoObj['details'] + '</td></tr>';
                        $(".infoTblWrapper tbody").append(infoStr);
                    }
                }
            },
            error: function(request, status, error) {
                alert("Failed to get infos: " + request.responseText);
            }
        });

        // Call getInfos repeatedly
        setTimeout(getInfos, notificationsInterval * 1000);
    }

    // If the info bar does not present, then no need to get errors/infos repeatedly
    if ($('.statusInfoBar').length > 0) {

        // Get errors
        getErrors();

        // Get infos
        getInfos();
    }

    //Date Input Validation after enter
    $("body").delegate("input.datePicker, input.bDate, input.eDate, input.rDate", "blur keypress", function(e) {
        if (e.type === 'keypress' && e.keyCode !== 13)
            return;
        $(this).removeClass("error");
        var currVal = $(this).val();
        if (currVal == '') {
            //no value
            $(this).addClass("error");
            return false;
        }
        //Declare Regex 
        var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
        var rxDatePattern2 = /^(\d{2})(\d{2})(\d{4})$/;
        var dtArray = currVal.match(rxDatePattern); // is format OK?

        if (dtArray == null) {
            dtArray = currVal.match(rxDatePattern2); // is format2 OK?
            if (dtArray == null) {
                $(this).addClass("error");
                return false;
            } else {
                dtMonth = dtArray[1];
                dtDay = dtArray[2];
                dtYear = dtArray[3];
            }
        } else {
            dtMonth = dtArray[1];
            dtDay = dtArray[3];
            dtYear = dtArray[5];
        }

        if (dtMonth < 1 || dtMonth > 12) {
            //month not correct
            $(this).addClass("error");
        } else if (dtDay < 1 || dtDay > 31) {
            //day not correct
            $(this).addClass("error");
        } else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11) && dtDay == 31) {
            //no day#31 of this month
            $(this).addClass("error");
        } else if (dtMonth == 2) {
            var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
            if (dtDay > 29 || (dtDay == 29 && !isleap)) {
                //Check leap year, no day#29 of this Jan
                $(this).addClass("error");
            }
        }
        if ($(this).hasClass("error")) {
            return false;
        } else {
            var retValue = '';
            retValue = padding(dtMonth, 2) + "/" + padding(dtDay, 2) + "/" + dtYear;
            $(this).val(retValue);
        }
    });

    // calculate end date
    $('.jsSubmitHour').click(function() {
        var hour = $(this).prev('input').val();
        if (hour == '') {
            $('.endDateError').html('The value you provide is empty.');
            showPopup('.noEndDatePopup');
            return;
        }
        var type = $(this).parent('div').prev('div').children('select').val();
        var dateField = $(this).parent('div').next('div').children('strong');
        dateField.html('');
        $.ajax({
            url: context + '/faces/calculateEndDate?value=' + hour + '&type=' + type,
            success: function(data, text, xhr) {
                $(dateField).html(data);
            },
            error: function(a, b, c) {
                $('.endDateError').html(a.responseText);
                showPopup('.noEndDatePopup');
            }
        });
    });

    $('.chartCalAreaBox1 select').change(function() {
        var title = $('.chartCalAreaBox2 p').eq(0);
        var value = this.value;
        if (value == 'DAY_260') {
            title.html('Enter Days:');
        } else {
            title.html('Enter Hours:');
        }

    });
    //mockup pagination
    $('.jsGoPrev').click(function() {
        var wrapper = $(this).parent();
        if ((!$(this).hasClass("paginationBtnDisabled")) && ($('.jsGoPage', wrapper).length > 1)) {
            var prevItem = $('.currentPage', wrapper).prev(".jsGoPage");
            if (prevItem.length < 1) {
                var prevItem = $('.currentPage', wrapper).prev().prev(".jsGoPage");
            }
            $('.jsGoPage', wrapper).removeClass("currentPage");
            prevItem.addClass("currentPage");
            $('.jsGoNext', wrapper).removeClass("paginationBtnDisabled");
            var currentIdx = parseInt(prevItem.text(), 10);
            if (currentIdx == 1) {
                $('.jsGoPrev', wrapper).addClass("paginationBtnDisabled");
            }
            var totalCount = parseInt($(".totalNum").text(), 10);
            var startNum = (currentIdx - 1) * 10 + 1;
            var endNum = currentIdx * 10;
            endNum = Math.min(totalCount, endNum);
        }
        $(".startNum").text(startNum);
        $(".endNum").text(endNum);
        showPaginationDot(wrapper);
    });
    $('.jsGoNext').click(function() {
        var wrapper = $(this).parent();
        if ((!$(this).hasClass("paginationBtnDisabled")) && ($('.jsGoPage', wrapper).length > 1)) {
            var nextItem = $('.currentPage', wrapper).next(".jsGoPage");
            if (nextItem.length < 1) {
                var nextItem = $('.currentPage', wrapper).next().next(".jsGoPage");
            }
            $('.jsGoPage', wrapper).removeClass("currentPage");
            nextItem.addClass("currentPage");
            $('.jsGoPrev', wrapper).removeClass("paginationBtnDisabled");
            var currentIdx = parseInt(nextItem.text(), 10);
            var totalIdx = parseInt($('.jsGoPage:last', wrapper).text(), 10);

            if (currentIdx == totalIdx) {
                $('.jsGoNext', wrapper).addClass("paginationBtnDisabled");
            }

            var totalCount = parseInt($(".totalNum").text(), 10);
            var startNum = (currentIdx - 1) * 10 + 1;
            var endNum = currentIdx * 10;
            endNum = Math.min(totalCount, endNum);
        }
        $(".startNum").text(startNum);
        $(".endNum").text(endNum);
        showPaginationDot(wrapper);
    });
    $('.jsGoPage').click(function() {
        var wrapper = $(this).parent();
        if ((!$(this).hasClass("currentPage")) && ($('.jsGoPage', wrapper).length > 1)) {
            $('.jsGoPage', wrapper).removeClass("currentPage");
            $(this).addClass("currentPage");
            $('.jsGoNext', wrapper).removeClass("paginationBtnDisabled");
            $('.jsGoPrev', wrapper).removeClass("paginationBtnDisabled");
            var currentIdx = parseInt($(this).text(), 10);
            var totalIdx = parseInt($('.jsGoPage:last', wrapper).text(), 10);
            if (currentIdx == 1) {
                $('.jsGoPrev', wrapper).addClass("paginationBtnDisabled");
            }
            if (currentIdx == totalIdx) {
                $('.jsGoNext', wrapper).addClass("paginationBtnDisabled");
            }

            var totalCount = parseInt($(".totalNum").text(), 10);
            var startNum = (currentIdx - 1) * 10 + 1;
            var endNum = currentIdx * 10;
            endNum = Math.min(totalCount, endNum);
        }
        $(".startNum").text(startNum);
        $(".endNum").text(endNum);
        showPaginationDot(wrapper);
    });


    //top navigation hover
    $('.topNav li').mouseenter(function() {
        $(this).next("li").addClass("noSep");
    }).mouseleave(function() {
        $(this).next("li").removeClass("noSep");
        $('.topNav li.current').next("li").addClass("noSep");
    });
    $('.topNav li.current').next("li").addClass("noSep");

    //btn active style
    $("body").delegate(".priBtn", "mousedown", function() {
        if (!$(this).hasClass("priBtnDisabled")) {
            $(this).addClass("priBtnActive");
        }
    }).delegate(".priBtn", "mouseup mouseleave", function() {
        $(this).removeClass("priBtnActive");
    });

    $("body").delegate(".priHighBtn", "mousedown", function() {
        if (!$(this).hasClass("priHighBtnDisabled")) {
            $(this).addClass("priHighBtnActive");
        }
    }).delegate(".priHighBtn", "mouseup mouseleave", function() {
        $(this).removeClass("priHighBtnActive");
    });
    $("body").delegate(".paginationBtn", "mousedown", function() {
        if (!$(this).hasClass("paginationBtnDisabled")) {
            $(this).addClass("paginationBtnActive");
        }
    }).delegate(".paginationBtn", "mouseup mouseleave", function() {
        $(this).removeClass("paginationBtnActive");
    });

    //Input focus style
    $("body").delegate("input.text, textarea, select", "focus", function() {
        if (!$(this).is('[readonly]')) {
            $(this).addClass("focus").removeClass("error").parent(".selectWrapper").removeClass("selectWrapperError");
            var fieldRow = $(this).parents(".halfRowField");
            if ($(".error", fieldRow).length < 1) {
                $(".errorIcon", fieldRow).remove();
            }
        }

    }).delegate("input.text, textarea", "blur", function() {
        $(this).removeClass("focus");
    });


    //Close Popup
    $('.jsClosePopup').click(function() {
        closePopup();
    });

    //datepicker
    $("input.datePicker").datepicker({
        showOn: "button",
        buttonImage: context + "/i/calendar.png",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true, yearRange: "-100:+1",
        onSelect: function() {
            $(this).removeClass("error");
        }
    });

    //Show Status Info popup
    $('.jsShowStatusInfoPopup').click(function() {
        $(this).parent().hide();
        $('.infoNotiPopup .filerTab li a').eq(0).trigger("click");
        showPopup(".infoNotiPopup");
    });

    //Info popup filer click
    $('.infoNotiPopup .filerTab li a').click(function() {
        var popup = $(this).parents(".popup").eq(0);
        var filter = $(this).attr("id");
        if (filter == "rowAll") {
            $(".infoNotiPopup #infoTbl tr").show();
            $(".infoNotiPopup #infoTbl tr td").show();
        } else {
            $(".infoNotiPopup #infoTbl tr").hide();
            $(".infoNotiPopup #infoTbl tr td").hide();
            var rows = $("td > ." + filter, popup).parent().parent();
            rows.show().find("td").show();
        }
        $('.infoNotiPopup .filerTab li a').removeClass("current");
        $(this).addClass("current");
    });

    $('.infoNotiPopup .jsClosePopup').click(function() {
        $(".statusInfoBar").show();
    });
    //Show Notification
    $('.jsShowNotifications').click(function() {
        showNoti(".notificationPopup");
    });

    $(window).resize(function() {
        if ($(".notificationPopup:visible").length > 0) {
            positionNoti();
        }
        if ($(".noValidMask:visible").length > 0) {
            positionNoValidMask();
        }
        if ($(".printScrollArea:visible").length > 0) {
            var winHeight = parseInt($(window).height(), 10);
            $(".printScrollArea:visible").css({
                maxHeight: (winHeight - 100)
            });
            var popup = $(".printScrollArea:visible").parents(".popup").eq(0);
            popup.css('display', 'block').css('margin', -popup.height() / 2 + 'px 0 0 ' + (-popup.width() / 2) + 'px');
        }
    });



    //Show tooltips
    $(".jsShowTips").hover(function() {
        showTooltips("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ac euismod augue. Maecenas tristique sit amet leo ut ullamcorper. Donec aliquam elementum erat sit amet fringilla.", $(this));
    }, function() {
        hideTooltips();
    });



    //Manually updated date
    $('.dpYear, .dpMonth, .dpDay').change(function() {
        if (isNaN($(this).val())) {
            $(this).val("");
        }
        var wrapper = $(this).parent();
        var nYear = parseInt($('.dpYear', wrapper).val(), 10);
        var nMonth = parseInt($('.dpMonth', wrapper).val(), 10);
        var nDay = parseInt($('.dpDay', wrapper).val(), 10);
        var nDate = new Date(nYear, (nMonth - 1), nDay);

        if (isDate(nMonth + '/' + nDay + '/' + nYear)) {
            $('.datePickerInput', wrapper).datepicker('setDate', nDate);
        }
    });

    //Click top nav view account button
    $('.jsShowSearchAccountPanel').click(function() {
        $(".viewAccountSearchFormWrapper").show();
        $(".topNav").addClass("showingSearchAccountPanel");
        $(".topNav li.current").addClass("tCurrent").removeClass("current");
        $(".topNav li").removeClass("noSep");
    });


    //index page
    if ($(".jsShowViewAccountOverlay").length > 0) {
        $(".jsShowSearchAccountPanel").trigger("click");
    }

    //Hide Search Account Panel
    $('.jsCloseViewAccount, .jsCancelSearchAccount').click(function() {
        $(".viewAccountSearchFormWrapper").hide();
        $(".topNav").removeClass("showingSearchAccountPanel");
        $(".topNav li.tCurrent").addClass("current").removeClass("tCurrent");
        $(".topNav li.current").next("li").addClass("noSep");
    });

    //Search Account Btn click
    $('.jsSearchAccount').click(function() {
        var searchPanel = $(this).parents(".viewAccountSearchForm").eq(0);
        if ($('.viewAccountSearchForm .datePicker').hasClass('error')) {
            if ($.trim($('.viewAccountSearchForm .datePicker').val()) != '') {
                return false;
            }
        }
        $(".noRecordMsg", searchPanel).hide();
        var filter = gatherAccountSearchFilterParam();
        $.ajax({
            url: context + '/faces/search',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(filter),
            success: function(data) {
                window.location = context + '/faces/view';
            },
            error: function(a) {
                alert('fail to search account, message:' + a.responseText);
            }
        });
    });

    $(".viewAccountSearchForm input[type=text]").val("");
    $(".viewAccountSearchForm input[type=checkbox]").prop("checked", false);
    //Search Account Btn click
    $('.jsClearSearchAccount').click(function() {
        $.ajax({
            url: context + '/faces/search/history/clear',
            type: 'POST',
            success: function(data) {
                // good
            },
            error: function(a) {
                alert('fail to clear search history : ' + a.responseText);
            }
        });
        var searchPanel = $(this).parents(".viewAccountSearchForm").eq(0);
        $(".noRecordMsg", searchPanel).hide();
        $("input[type=text]", searchPanel).val("");
        $("input[type=checkbox]", searchPanel).prop("checked", false);
    });

    var emptyRowHTML = '<tr class="even newEntryRow"><td class="blankCell firstCol">&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td class="lastCol"></td></tr>';

    $("body").delegate(".entriesTbl tbody td", "click", function() {
        if (!$(this).parents("table").eq(0).hasClass("entriesTblUnedit")) {
            var tr = $(this).parent("tr");
            var select = tr.parents('.scrollTblArea').prev('div.versionBar').find('select').eq(0);
            if (($(this).hasClass("blankCell")) && (tr.hasClass("entriesEditRow"))) {
                tr.after($('#versionTableTemplate tbody tr').clone());
                var tds = $(tr).children('td');
                var newRow = $(tr).next();
                var ntds = $(newRow).children('td');
                $(ntds).eq(1).html($(tds).eq(1).children('input').val());
                $(ntds).eq(2).html($(tds).eq(2).children('input').val());
                $(ntds).eq(3).html($(tds).eq(3).children('input').val());
                $(ntds).eq(4).html($(tds).eq(4).children('select').get(0).options[$(tds).eq(4).children('select').get(0).selectedIndex].innerHTML);
                $(ntds).eq(4).next('input:hidden').val($(tds).eq(4).children('select').val());
                $(ntds).eq(5).html($(tds).eq(5).children('select').get(0).options[$(tds).eq(5).children('select').get(0).selectedIndex].innerHTML);
                $(ntds).eq(5).next('input:hidden').val($(tds).eq(5).children('select').val());
                $(ntds).eq(6).html($(tds).eq(6).children('select').get(0).options[$(tds).eq(6).children('select').get(0).selectedIndex].innerHTML);
                $(ntds).eq(6).next('input:hidden').val($(tds).eq(6).children('select').val());
                $(ntds).eq(7).html($(tds).eq(7).children('select').get(0).options[$(tds).eq(7).children('select').get(0).selectedIndex].innerHTML);
                $(ntds).eq(7).next('input:hidden').val($(tds).eq(7).children('select').val());
                $(ntds).eq(8).html($(tds).eq(8).children('input').val());
                var interestRate = $.trim($(tds).eq(9).children('input').val());
                if (interestRate == '') {
                    $(ntds).eq(9).html('Default');
                } else {
                    $(ntds).eq(9).html(interestRate);
                }

                $(ntds).eq(10).html($(tds).eq(10).children('select').get(0).options[$(tds).eq(10).children('select').get(0).selectedIndex].innerHTML);
                $(ntds).eq(10).next('input:hidden').val($(tds).eq(10).children('select').val());

                // create and save calculation
                var calculation = {
                    id: 0,
                    beginDate: Date.parse($(ntds).eq(1).html()),
                    endDate: Date.parse($(ntds).eq(2).html()),
                    refundDate: Date.parse($(ntds).eq(3).html()),
                    retirementType: {
                        id: $(ntds).eq(4).next('input:hidden').val()
                    },
                    periodType: {
                        id: $(ntds).eq(5).next('input:hidden').val()
                    },
                    appointmentType: {
                        id: $(ntds).eq(6).next('input:hidden').val()
                    },
                    serviceType: {
                        id: $(ntds).eq(7).next('input:hidden').val()
                    },
                    amount: $(ntds).eq(8).html(),
                    payType: {
                        id: $(ntds).eq(10).next('input:hidden').val()
                    }
                };
                if ($(ntds).eq(9).html() != 'Default') {
                    calculation.interestRate = $(ntds).eq(9).html();
                }
                var idString = tr.prop('id');
                if (idString.indexOf('cal-') != -1) {
                    calculation.id = idString.replace('cal-', '');
                }
                var versionId = select.val();

                $.ajax({
                    url: context + "/faces/calculationVersion/" + versionId + "/calculation",
                    async: false,
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(calculation),
                    success: function(data) {
                        newRow.prop('id', 'cal-' + data);
                    },
                    error: function(a, b, c) {
                        alert('fail to update calculation, message:' + a.responseText);
                    }
                });

                $('.dollar').formatCurrency({
                    negativeFormat: '%s-%n'
                });
            } else if (tr.hasClass("entriesEditRow")) {
                return;
            } else {
                tr.parent('tbody').children('tr.entriesEditRow').children('td').eq(0).trigger('click');
                var idString = tr.prop('id');

                var editRowHTML = $("#versionEditTemplate tbody tr").clone();
                if (idString.indexOf('cal-') != -1) {
                    editRowHTML.prop('id', idString);
                }
                var values = [];

                $.each($(tr).children('td'), function(i) {
                    if (i == 1 || i == 2 || i == 3 || i == 9) {
                        values.push($(this).html());
                    } else if (i == 8) {
                        values.push($(this).html().replace(/[^0-9\.]+/g, ""));
                    } else {
                        values.push($(this).next('input:hidden').val());
                    }


                });
                tr.after(editRowHTML);
                var editingTr = $(tr).next('tr');
                var tds = $(editingTr).children('td');

                if (!tr.hasClass('newEntryRow')) {
                    $(tds).eq(1).children('input').val(values[1]);
                    $(tds).eq(2).children('input').val(values[2]);
                    $(tds).eq(3).children('input').val(values[3]);
                    $(tds).eq(4).children('select').get(0).selectedIndex = values[4];
                    $(tds).eq(5).children('select').get(0).selectedIndex = values[5];
                    $(tds).eq(6).children('select').get(0).selectedIndex = values[6];
                    $(tds).eq(7).children('select').get(0).selectedIndex = values[7];
                    $(tds).eq(8).children('input').val(values[8]).keypress(checkDollar);
                    if (values[9] == 'Default') {
                        $(tds).eq(9).children('input').val('');
                    } else {
                        $(tds).eq(9).children('input').val(values[9]);
                    }
                    $(tds).eq(9).keypress(checkDollar);
                    $(tds).eq(10).children('select').get(0).selectedIndex = values[10];
                } else {
                    $(tds).eq(4).children('select').get(0).selectedIndex = 2;
                    $(tds).eq(5).children('select').get(0).selectedIndex = 1;
                    $(tds).eq(6).children('select').get(0).selectedIndex = 2;
                    $(tds).eq(7).children('select').get(0).selectedIndex = 4;
                    $(tds).eq(8).children('input').val(20000.00).keypress(checkDollar);
                    $(tds).eq(10).children('select').get(0).selectedIndex = 5;
                }
                $(tds).eq(1).children('input').datepicker({
                    showOn: "button",
                    buttonImage: context + "/i/calendar.png",
                    buttonImageOnly: true,
                    changeMonth: true,
                    changeYear: true, yearRange: "-100:+1",
                    onSelect: function() {
                        $(this).removeClass("error");
                    }
                });
                $(tds).eq(2).children('input').datepicker({
                    showOn: "button",
                    buttonImage: context + "/i/calendar.png",
                    buttonImageOnly: true,
                    changeMonth: true,
                    changeYear: true, yearRange: "-100:+1",
                    onSelect: function() {
                        $(this).removeClass("error");
                    }
                });
                $(tds).eq(3).children('input').datepicker({
                    showOn: "button",
                    buttonImage: context + "/i/calendar.png",
                    buttonImageOnly: true,
                    changeMonth: true,
                    changeYear: true, yearRange: "-100:+1",
                    onSelect: function() {
                        $(this).removeClass("error");
                    }
                });

                if (tr.hasClass("newEntryRow")) {
                    $(tr).parent('tbody').append(emptyRowHTML);
                }
            }
            var editRow = tr.next("tr");
            if (tr.hasClass("valErrorRow")) {
                editRow.addClass("valErrorRow");
            }
            if (tr.hasClass("valErrorRowBefore")) {
                editRow.addClass("valErrorRowBefore");
            }
            if (tr.hasClass("valErrorRowAfter")) {
                editRow.addClass("valErrorRowAfter");
            }
            tr.remove();

        }
    });

    $("body").delegate(".validateResultTbl tbody td", "click", function() {

        if (!$(this).parents("table").eq(0).hasClass("entriesTblUnedit")) {
            var tr = $(this).parent("tr");
            var resultId = tr.parents('table.validateResultTbl').prop('id').replace('result-', '');
            if (($(this).hasClass("blankCell")) && (tr.hasClass("entriesEditRow"))) {
                tr.after($('#resultItemTemplate tbody tr').clone());
                var tds = $(tr).children('td');
                var newRow = $(tr).next();
                var ntds = $(newRow).children('td');
                $(ntds).eq(1).html($(tds).eq(1).children('input').val());
                $(ntds).eq(2).html($(tds).eq(2).children('input').val());
                $(ntds).eq(3).html($(tds).eq(3).children('input').val());
                $(ntds).eq(4).html($(tds).eq(4).children('input').val());
                $(ntds).eq(5).html($(tds).eq(5).children('select').get(0).options[$(tds).eq(5).children('select').get(0).selectedIndex].innerHTML);
                $(ntds).eq(5).next('input:hidden').val($(tds).eq(5).children('select').val());
                $(ntds).eq(6).html($(tds).eq(6).children('input').val());
                $(ntds).eq(7).html($(tds).eq(7).children('input').val());
                $(ntds).eq(8).html($(tds).eq(8).children('input').val());
                $(ntds).eq(9).html($(tds).eq(9).children('input').val());

                // create and save calculation

                var resultItem = {
                    id: 0,
                    startDate: Date.parse($(ntds).eq(1).html()),
                    endDate: Date.parse($(ntds).eq(2).html()),
                    midDate: Date.parse($(ntds).eq(3).html()),
                    refundDate: Date.parse($(ntds).eq(4).html()),
                    periodType: {
                        id: $(ntds).eq(5).next('input:hidden').val()
                    },
                    deductionAmount: $(ntds).eq(6).html(),
                    totalInterest: $(ntds).eq(7).html(),
                    paymentsApplied: $(ntds).eq(8).html(),
                    balance: $(ntds).eq(9).html(),
                    retirementType: {
                        id: 1
                    },
                    serviceCategory: 'CSRS_POST_10_82_DEPOSIT'
                };
                if (tr.prop('id').indexOf('ri-') != -1) {
                    resultItem.id = tr.prop('id').replace('ri-', '');
                }
                
                $.ajax({
                    url: context + "/faces/calculationResult/" + resultId + "/calculationResultItem",
                    async: false,
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(resultItem),
                    success: function(data) {
                        newRow.prop('id', 'ri-' + data);
                    },
                    error: function(a, b, c) {
                        alert('fail to update calculation, message:' + a.responseText);
                    }
                });

                $('.dollar').formatCurrency({
                    negativeFormat: '%s-%n'
                });
            } else if (tr.hasClass("entriesEditRow")) {
                return;
            } else {
                tr.parent('tbody').children('tr.entriesEditRow').children('td').eq(0).trigger('click');
                var idString = tr.prop('id');

                var editRowHTML = $("#resultItemEditTemplate tbody tr").clone();
                if (idString.indexOf('ri-') != -1) {
                    editRowHTML.prop('id', idString);
                }
                var values = [];

                $.each($(tr).children('td'), function(i) {
                    if (i == 1 || i == 2 || i == 3 || i == 4) {
                        values.push($(this).html());
                    } else if (i == 6 || i == 7 || i == 8 || i == 9) {
                        values.push($(this).html().replace(/[^0-9\.]+/g, ""));
                    } else {
                        values.push($(this).next('input:hidden').val());
                    }


                });
                tr.after(editRowHTML);
                var editingTr = $(tr).next('tr');
                var tds = $(editingTr).children('td');

                if (!tr.hasClass('newEntryRow')) {
                    $(tds).eq(1).children('input').val(values[1]);
                    $(tds).eq(2).children('input').val(values[2]);
                    $(tds).eq(3).children('input').val(values[3]);
                    $(tds).eq(4).children('input').val(values[4]);
                    $(tds).eq(5).children('select').get(0).selectedIndex = values[5];
                    $(tds).eq(6).children('input').val(values[6]).keypress(checkDollar);
                    $(tds).eq(7).children('input').val(values[7]).keypress(checkDollar);
                    $(tds).eq(8).children('input').val(values[8]).keypress(checkDollar);
                    $(tds).eq(9).children('input').val(values[9]).keypress(checkDollar);
                } else {
                    $(tds).eq(5).children('select').get(0).selectedIndex = 1;
                    $(tds).eq(6).children('input').val(20000.00).keypress(checkDollar);
                    $(tds).eq(7).children('input').val(20000.00).keypress(checkDollar);
                    $(tds).eq(8).children('input').val(20000.00).keypress(checkDollar);
                    $(tds).eq(9).children('input').val(20000.00).keypress(checkDollar);

                }
                $(tds).eq(1).children('input').datepicker({
                    showOn: "button",
                    buttonImage: context + "/i/calendar.png",
                    buttonImageOnly: true,
                    changeMonth: true,
                    changeYear: true, yearRange: "-100:+1",
                    onSelect: function() {
                        $(this).removeClass("error");
                    }
                });
                $(tds).eq(2).children('input').datepicker({
                    showOn: "button",
                    buttonImage: context + "/i/calendar.png",
                    buttonImageOnly: true,
                    changeMonth: true,
                    changeYear: true, yearRange: "-100:+1",
                    onSelect: function() {
                        $(this).removeClass("error");
                    }
                });
                $(tds).eq(3).children('input').datepicker({
                    showOn: "button",
                    buttonImage: context + "/i/calendar.png",
                    buttonImageOnly: true,
                    changeMonth: true,
                    changeYear: true, yearRange: "-100:+1",
                    onSelect: function() {
                        $(this).removeClass("error");
                    }
                });
                $(tds).eq(4).children('input').datepicker({
                    showOn: "button",
                    buttonImage: context + "/i/calendar.png",
                    buttonImageOnly: true,
                    changeMonth: true,
                    changeYear: true, yearRange: "-100:+1",
                    onSelect: function() {
                        $(this).removeClass("error");
                    }
                });

                if (tr.hasClass("newEntryRow")) {
                    var newRow = $('#resultItemNewTemplate').find('tr').clone();
                    $(tr).parent('tbody').append(newRow);
                }
            }
            var editRow = tr.next("tr");
            if (tr.hasClass("valErrorRow")) {
                editRow.addClass("valErrorRow");
            }
            if (tr.hasClass("valErrorRowBefore")) {
                editRow.addClass("valErrorRowBefore");
            }
            if (tr.hasClass("valErrorRowAfter")) {
                editRow.addClass("valErrorRowAfter");
            }
            tr.remove();

        }
    });

    $("body").delegate(".entriesEditRow input, .entriesEditRow select, tr.editing input, tr.editing select", "focus", function() {
        $(this).parents("td").eq(0).addClass("editingCell");
    }).delegate(".entriesEditRow input, .entriesEditRow select, tr.editing input, tr.editing select", "blur", function() {
        $(this).parents("td").eq(0).removeClass("editingCell");
    });

    $("body").delegate(".editingCell", "keydown", function(e) {
        var keyCode = e.keyCode || e.which;
        if (keyCode == 9) {
            var tBody = $(this).parents("tbody").eq(0);
            var count = $("td", tBody).length - 1;
            var currentIdx = $("td", tBody).index($(this));
            if (currentIdx == count) {
                e.preventDefault();
                var editRowHTML = $(".entriesTbl tfoot").html();
                tBody.append(editRowHTML);
                var newRow = $("tr:last-child", tBody);
                $("input", newRow).val("");
                $(".bDate, .eDate", newRow).addClass("datePicker");
                $("input.datePicker", newRow).datepicker({
                    showOn: "button",
                    buttonImage: context + "/i/calendar.png",
                    buttonImageOnly: true,
                    changeMonth: true,
                    changeYear: true, yearRange: "-100:+1",
                    onSelect: function() {
                        $(this).removeClass("error");
                    }
                });
                $("select", newRow).each(function(index, element) {
                    $(this)[0].selectedIndex = 0;
                });
                newRow.addClass("newAddeeRow");
                $("td", newRow).addClass("newRowCellHidden").removeClass("editingCell").eq(1).removeClass("newRowCellHidden");
                // call custom function here
                $("input", newRow)[0].focus();
            }
            var nextCell = $(this).next("td");
            if (nextCell.hasClass("newRowCellHidden")) {
                nextCell.trigger("click");
            }
            if ($(".noValidMask:visible").length > 0) {
                positionNoValidMask();
            }
        }
    });
    $("body").delegate(".newRowCellHidden input", "focus", function() {
        $(this).parent().removeClass("newRowCellHidden");
    });
    $("body").delegate(".newRowCellHidden", "click", function(e) {
        $(this).removeClass("newRowCellHidden");
        if (e.hasOwnProperty('originalEvent')) {
            $("input", $(this)).show().focus();
            $("select", $(this)).show().focus();
        } else {
            if ($("input", $(this)).length > 0) {
                $("input", $(this)).show().get(0).click();
            } else if ($("select", $(this)).length > 0) {
                $("select", $(this)).show().get(0).click();
            }
        }
    });

    //Tabs click
    $(".tabsBar a").click(function() {
        var targetBlock = $(this).attr("id");
        //If tab has ID, switch tab display within same page
        if (!((typeof(targetBlock) == "undefined") || (targetBlock == ""))) {
            var bar = $(this).parents(".tabsBar").eq(0);
            $("a", bar).removeClass("current");
            $(this).addClass("current");
            var wrapper = bar.parent();
            $("> .tabsBlock", wrapper).hide();
            $("div." + targetBlock, wrapper).show();
            positionNoValidMask();
            $(".pageTitleArea .accountBtns .jsPrintStatement").hide();
            if (targetBlock === "employeeTab") {
                $(".pageTitleArea .accountBtns .jsPrintStatement").show();
            }
        }
    });

    //Expand/collapse Panel
    $(".jsTogglePanel").click(function() {
        $(this).toggleClass("panelExpanded");
        if ($(this).hasClass("panelExpanded")) {
            $(this).parent().find(".panelBody").show();
        } else {
            $(this).parent().find(".panelBody").hide();
        }
    });

    if ($(".noValidMask:visible").length > 0) {
        positionNoValidMask();
    }


    $("#accountSearchResultTbl tbody td").mouseenter(function() {
        $(this).parent("tr").addClass("rowHover");
    }).mouseleave(function() {
        $(this).parent("tr").removeClass("rowHover");
    });

    $('.accountNotesPanel tbody').delegate('td', 'mouseenter', function() {
        $(this).parent("tr").addClass("rowHover");
    });
    $('.accountNotesPanel tbody').delegate('td', 'mouseleave', function() {
        $(this).parent("tr").removeClass("rowHover");
    });


    //Delete Account version
    $(".jsDelVersionBtn").click(function() {
        if ($(this).hasClass('priBtnDisabled') == true) {
            return;
        }
        showPopup(".confirmDeletionPopup");
    });
    $(".jsConfirmDeletion").click(function() {
        closePopup();
        var select = $('.depositTab .versionBar select');
        var id = select.val();
        deleteVersion(context, id);
        select.find("option[value='" + id + "']").addClass('isHidden');
        var firstSee = -1;
        $.each(select.children('option'), function(i) {
            if ($(this).hasClass('isHidden') == false && firstSee == -1) {
                select.get(0).selectedIndex = i;
                firstSee = i;
                select.trigger('change');
            }
        });
        if (firstSee == -1) {
            select.text('');
            $(".depositTab .jsDelVersionBtn").addClass('priBtnDisabled');
            $(".depositTab .jsCancelFunction").trigger('click');
        }

    });

    $('.depositTab .jsRefreshGrid').click(function() {
        var accountId = $('#accountId').val();
        $('.depositVersionTbodyArea').html("");
        var account = getAccount(context, accountId);
        populateCalculationVersion(account);
        $('.depositTab .jsDelVersionBtn').removeClass('priBtnDisabled');
        $('.dollar').formatCurrency();
    });




    //Checkbox check all 
    $(".checkGroup").delegate('input[type=checkbox]', 'click', function(e) {
        e.stopPropagation();
        var checkGroup = $(this).parents(".checkGroup").eq(0);
        if ($(this).hasClass("checkAllRow")) {
            if ($(this).prop("checked")) {
                $(".checkRow", checkGroup).prop("checked", true);
            } else {
                $(".checkRow", checkGroup).prop("checked", false);
            }
        }
        if ($(this).hasClass("checkRow")) {
            var allCount = $(".checkRow", checkGroup).length;
            var checkedCount = $(".checkRow:checked", checkGroup).length;
            if (checkedCount == allCount) {
                $(".checkAllRow", checkGroup).prop("checked", true);
            } else {
                $(".checkAllRow", checkGroup).prop("checked", false);
            }
        }
    });


    //hide menu
    $("body").click(function(e) {
        if (e.target.className.indexOf("jsShowRowAction") < 0) {
            $(".jsShowRowAction").removeClass("jsShowRowActionCellFocus");
            $(".rowActions").hide();
        }
    });

    //Popups

    $(".jsAddNote").click(function(e) {
        closePopup();
        $('.newnote textarea').val('');
        showPopup(".accountNotesAddPopup");
        $('.accountNotesAddPopup a.jsClipboardCopy').zclip({
            path: context + '/js/ZeroClipboard.swf',
            copy: function() {
                showPopup(".accountNoteCopyPopup");
                return $('.accountNotesAddPopup textarea').val();
            }
        });
    });

    $(".jsUpdateInterest").click(function(e) {
        closePopup();
        showPopup(".updateInterestPopup");
    });


    $(".jsLastUpdate").click(function(e) {
        closePopup();
        showPopup(".lastUpdatePopup");
    });

    $(".hourField").keypress(function(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        return !(charCode > 31 && (charCode < 48 || charCode > 57));
    });



    $(".jsClosePopup2").click(function(e) {
        $(".alpha2, .popup2").hide();
    });


    $("body").delegate(".alphaTrans", "click", function() {
        closePopup();
    });

    //Notes popup textarea resize
    $(".popup .notesRecord textarea").resizable({
        minWidth: 476,
        maxWidth: 476,
        minHeight: 172,
        resize: function() {
            var popup = $(this).parents(".popup").eq(0);
            popup.css('display', 'block').css('margin', -popup.height() / 2 + 'px 0 0 ' + (-popup.width() / 2) + 'px');
        }
    }).parent().css({
        "padding": "0 0 1px 0",
        height: 172
    });


}); //End document.ready


// Load lookup entities and populate the select drop-down options
function loadLookup(entityType, displayDiscription) {

    $.ajax({
        url: 'lookup/' + entityType,
        type: 'GET',
        cache: false,
        async: true,
        global: false,
        success: function(entities) {
            $('#' + entityType).text('');
            for (var i in entities) {
                var entity = entities[i];
                var option = $('<option></option>');
                option.text(displayDiscription ? entity.description : entity.name);
                option.val(entity.id);
                option.data('name', entity.name);
                $('#' + entityType).append(option);
            }
        },
        error: function(request, status, error) {
            alert('Failed to get ' + entityType + ' : ' + request.responseText);
        }
    });
}

// Setup page
function setupPage(pageName, pageIndex) {

    // Set current page tab
    $('.topNav > ul > li:eq(' + pageIndex + ')').addClass('current');

    // Check home page
    checkHomePage(pageName);

    // Home page click handler
    $('#setHome').click(function() {
        setHomePage('' + pageName + '');
    });
}

// Switch tab (within page)
function switchTab(tabShowFunctions) {

    var tabLinks = $('.tabsBar a');
    tabLinks.click(function() {
        // Change breadcrumb title text when tab change

        var title = $(this).contents().filter(function() {
            return this.nodeType == 3;
        })[0].nodeValue;

        $('.breadcurmb span').text(title);
        $('.pageTitle').text(title);

        // If the tab is already shown
        if ($(this).data("tabShown")) {
            return;
        }

        // Set tab shown flag
        tabLinks.each(function() {
            $(this).data("tabShown", false);
        });
        $(this).data("tabShown", true);

        // Call tab show function
        tabShowFunctions[$(this).parent('li').index()]();
    });
}

//Check home page
function checkHomePage(page) {
    var context = $('#context').val();
    $.ajax({
        url: context + '/common/tab',
        type: 'GET',
        cache: false,
        async: true,
        global: false,
        success: function(homeTabPage) {
            if (homeTabPage == page) {
                $('#setHome').prop('checked', true);
            }
        },
        error: function(request, status, error) {
            alert('Failed to get home tab page: ' + request.responseText);
        }
    });
}

//Set home page
function setHomePage(homePage) {
    var setHomeData = $('#setHome').prop("checked") ? homePage : 'null';
    var url = 'common/tab';
    if (setHomeData != 'null') {
        url = 'common/tab?tab=' + setHomeData;
    }
    if (typeof rootUrl != 'undefined') {
        url = rootUrl + url;
    }
    $.ajax({
        url: url,
        type: 'POST',
        cache: false,
        async: true,
        global: false,
        contentType: 'application/json',
        data: setHomeData,
        success: function() {
            // Good
        },
        error: function(request, status, error) {
            alert('Failed to make this tab as home page: ' + request.responseText);
        }
    });
}

//Format the date as MM/DD/YY hh:mm AM|PM
function formatCaseDate(dateValue) {
    if (!dateValue) {
        return '';
    }
    var date = new Date(dateValue);
    var hours = date.getHours();
    var amOrPm = 'AM';
    if (hours > 12) {
        hours -= 12;
        amOrPm = 'PM';
    }
    return (date.getMonth() < 9 ? '0' : '') + (date.getMonth() + 1) + '/' + (date.getDate() < 10 ? '0' : '')
            + date.getDate() + '/' + date.getFullYear() + ' ' + (hours < 10 ? '0' : '') + hours + ':'
            + (date.getMinutes() < 10 ? '0' : '') + date.getMinutes() + ' ' + amOrPm;
}

// Format the date as MM/DD/YY
function formatNotificationDate(dateValue) {
    if (!dateValue) {
        return '';
    }
    var date = new Date(dateValue);

    return (date.getMonth() < 9 ? '0' : '') + (date.getMonth() + 1) + '/' + (date.getDate() < 10 ? '0' : '') + date.getDate() + '/' + date.getFullYear();
}
// Format the date as hh:mm:ss AM|PM
function formatNotificationTime(dateValue) {
    if (!dateValue) {
        return '';
    }
    var date = new Date(dateValue);

    var hours = date.getHours();
    var amOrPm = 'AM';
    if (hours > 12) {
        hours -= 12;
        amOrPm = 'PM';
    }
    return (hours < 10 ? '0' : '') + hours + ':' + (date.getMinutes() < 10 ? '0' : '') + date.getMinutes() + ':' + (date.getSeconds() < 10 ? '0' : '') + date.getSeconds() + ' ' + amOrPm;
}

function numberFormat(number, decimals, dec_point, thousands_sep) {
    var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
            s = '',
            toFixedFix = function(n, prec) {
        var k = Math.pow(10, prec);
        return '' + Math.round(n * k) / k;
    };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}
;

//Change amount type of deposite field on payment search
function changeAmountType(obj) {
    checkValue = $(obj).val();
    var searchBox = $(obj).parents(".paymentSearchBox").eq(0);
    if (checkValue === "any") {
        $(".amountInput ", searchBox).addClass("fieldDisabled");
    } else {
        $(".amountInput ", searchBox).removeClass("fieldDisabled");
    }
}

//Change deposited date type of deposite field on payment search
function changeDepositedDate(obj) {
    checkValue = $(obj).val();
    var filedRow = $(obj).parents(".fieldRow").eq(0);
    if (checkValue === "between") {
        $(".singleDate", filedRow).addClass("isHidden");
        $(".betweenDate", filedRow).removeClass("isHidden");
    } else {
        $(".singleDate", filedRow).removeClass("fieldDisabled")
        $(".singleDate", filedRow).removeClass("isHidden");
        $(".betweenDate", filedRow).addClass("isHidden");
        if (checkValue === "any") {
            $(".singleDate", filedRow).addClass("fieldDisabled")
        }
    }
    if ((obj.selectedIndex === 1) || (obj.selectedIndex === 2) || (obj.selectedIndex === 3) || (obj.selectedIndex === 4) || (obj.selectedIndex === 5)) {
        filedRow.addClass("longSelect");
    } else {
        filedRow.removeClass("longSelect");
    }
}

//Position No Validation Mask
function positionNoValidMask() {
    $(".noValidMask:visible").each(function(index, element) {
        var mask = $(this);
        var target = $(this).prev("div");
        var p = target.position();
        mask.css({
            left: p.left,
            top: p.top,
            height: target.height()
        });
    });
}

// popup
/**
 * show popup
 * selector - the jQuery selector of the popup
 */
function showPopup(selector) {
    var popup = $(selector);
    $('.alpha').css('display', 'block');
    var winHeight = parseInt($(window).height(), 10);
    if ($(".printScrollArea", popup).length > 0) {
        $(".printScrollArea", popup).css({
            maxHeight: (winHeight - 100)
        });
    }
    popup.css('display', 'block').css('margin', -popup.height() / 2 + 'px 0 0 ' + (-popup.width() / 2) + 'px');
}
;
function closePopup() {
    $('.alpha').css('display', 'none').removeClass("alphaTrans");
    $('.popup').css('display', 'none');
}
;
/**
 * show Notification Popup
 * selector - the jQuery selector of the popup
 */
function showNoti(selector) {
    var popup = $(selector);
    $('.alpha').css('display', 'block').addClass("alphaTrans");
    popup.css('display', 'block');
    positionNoti();
}
;
function positionNoti() {
    var popup = $(".notificationPopup");
    var target = $(".headerUserInfo .jsShowNotifications");
    var offset = target.offset();
    var h = parseInt(popup.height(), 10);
    var w = parseInt(popup.width(), 10);
    var tw = parseInt(target.width(), 10);
    popup.css('display', 'block').css({
        position: "absolute",
        top: ((parseInt(offset.top, 10) + 27) + 'px'),
        left: ((parseInt(offset.left, 10) - w + 3 + tw) + 'px')
    });
    $(".popupArrow", popup).css('display', 'block').css({
        position: "absolute",
        right: ((tw - 13) + 'px')
    });
}

//Show Tooltips
//@param str:
//    Tooltips string
//@target
//  Tooltips target object
function showTooltips(str, target) {
    $(".toolTips").remove();
    var offset = $(target).offset();
    var bd = $("body");
    bd.append("<div class='toolTips'>" + str + "<i></i></div>");
    var toolTips = $(".toolTips").eq(0);
    var h = toolTips.height();
    var w = toolTips.width();
    toolTips.css('top', (offset.top - h - 36) + 'px').css('left', (offset.left - w) + 'px');
}
function hideTooltips() {
    $(".toolTips").remove();
}


//Validate Date
function isDate(dateStr) {
    var datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
    var matchArray = dateStr.match(datePat); // is the format ok?

    //Check format
    if (matchArray == null) {
        return false;
    }

    // p@rse date into variables
    month = matchArray[1];
    day = matchArray[3];
    year = matchArray[5];

    // check month range
    if (month < 1 || month > 12) {
        alert("Month must be between 1 and 12.");
        return false;
    }

    // check day range
    if (day < 1 || day > 31) {
        alert("Day must be between 1 and 31.");
        return false;
    }

    // check if month have 31 days
    if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
        alert("Month " + month + " doesn`t have 31 days!")
        return false;
    }

    //check how much days for February of the year
    if (month == 2) {
        var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !isleap)) {
            alert("February " + year + " doesn`t have " + day + " days!");
            return false;
        }
    }

    // date is valid
    return true;
}

//Show pagination Dot
function showPaginationDot(wrapper) {
    var pageLinks = $(".jsGoPage", wrapper);
    $("span.fLeft", wrapper).remove();
    pageLinks.hide();
    totalCount = pageLinks.length;
    pageLinks.eq(0).show();
    pageLinks.eq(1).show();
    pageLinks.eq(2).show();
    pageLinks.eq(totalCount - 1).show();
    pageLinks.eq(totalCount - 2).show();
    pageLinks.eq(totalCount - 3).show();
    var currentPage = parseInt($(".currentPage", wrapper).eq(0).text(), 10);
    if ((currentPage > 2) && (currentPage < (totalCount - 1))) {
        pageLinks.eq(currentPage - 1).show();
        pageLinks.eq(currentPage - 2).show();
        pageLinks.eq(currentPage).show();
    }

    if ((currentPage < 3) && (totalCount > 6)) {
        pageLinks.eq(2).after('<span class="fLeft">...</span>');
    }
    if ((currentPage >= 3) && (currentPage <= 5) && (totalCount > 8)) {
        pageLinks.eq(currentPage).after('<span class="fLeft">...</span>');
    }
    if (currentPage > 5) {
        pageLinks.eq(2).after('<span class="fLeft">...</span>');
        if ((totalCount > 10) && (currentPage < totalCount - 4)) {
            pageLinks.eq(currentPage).after('<span class="fLeft">...</span>');
        }
    }
}


//input zipcode only
function validateZipInput(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode == 45) {
        return true;
    }
    return !(charCode > 31 && (charCode < 48 || charCode > 57));
}

//input phone only
function validatePhoneInput(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    
    return !(charCode > 31 && (charCode < 48 || charCode > 57));
}

// input number only
function validateNumberInput(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    return !(charCode > 31 && (charCode < 48 || charCode > 57));

}

//clear text selection
function clearSelection() {
    var sel;
    if (document.selection && document.selection.empty) {
        document.selection.empty();
    } else if (window.getSelection) {
        sel = window.getSelection();
        if (sel && sel.removeAllRanges)
            sel.removeAllRanges();
    }
}

//cookies
function setCookie(name, value) {
    var exp = new Date();
    exp.setTime(exp.getTime() + 30 * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}
function getCookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null)
        return unescape(arr[2]);
    return null;
}
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}
function gatherAccountSearchFilterParam() {
    var filter = {};
    $.each($('.viewAccountSearchForm input.filter:text').serializeArray(), function() {
        if ($.trim(this.value) !== '') {
            filter[this.name] = this.value.replace(/\%/g, '\\%');
        }

    });
    var ssn = $('.viewAccountSearchForm input.ssn:text').serializeArray();
    var ssnStr = $.trim(ssn[0].value) + '-' + $.trim(ssn[1].value) + '-' + $.trim(ssn[2].value);
    if (ssnStr !== '--') {
        filter.ssn = ssnStr;
    }
    var birthDate = $.trim($('.viewAccountSearchForm input.datePicker').val());
    if (birthDate != '') {
        filter['birthDate'] = Date.parse(birthDate);
    }
    filter.excludeHistory = $('#excludeFromSearch').prop('checked');
    filter.pageNumber = 1;
    filter.pageSize = 10;
    filter.assigned = null;
    return filter;
}

function populateNamedEntity(typeName, context, noSelect) {

    $.ajax({
        url: context + '/lookup/' + typeName,
        dataType: 'json',
        async: false,
        success: function(data, status) {
            var html = "";
            if (noSelect === undefined) {
                html += "<option value='0' selected='selected'>Select</option>";
            }
            for (i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].id + "'";
                if (i === 0 && noSelect) {
                    html += " selected='selected'";
                }
                html += '>' + data[i].name + "</option>";
            }
            $("select[typeName='" + typeName + "']").html(html);
            if (typeName == 'countries') {
                $("select[typeName='" + typeName + "']").val(197);
            }
        },
        error: function(a, b, c) {
            alert('fail to populate named entity [' + typeName + '] : ' + a.responseText);
        }
    });
}

function parseDateToString(item) {
    var date = new Date(item);
    return two(date.getMonth() + 1) + "/" + two(date.getDate()) + "/" + date.getFullYear();
}

function two(value) {
    if (value > 9) {
        return '' + value;
    }
    return '0' + value;
}

function refreshAccountSummary(account) {
    var context = $('#context').val();
    $.ajax({
        url: context + '/faces/' + account.id + '/payments',
        async: false,
        success: function(data) {
            var sum = 0;
            $.each(data, function() {
                sum = sum + this.amount;
            });
            $('.paymentInfo').html('Payments(' + data.length + ')');
            $('.total').html(sum);
        },
        error: function(error, b, c) {
            alert('fail to get payments : ' + error.responseText);
        }
    });
    $('.csd').html(account.claimNumber);
    $('.status').html(account.status.name + '(' + account.status.id + ')');
    $('.name').html(account.holder.firstName + ' ' + account.holder.lastName);
    $('.birthDate').html(parseDateToString(account.holder.birthDate));
    $('.ssn').html(account.holder.ssn);
    $('.formType').html(account.formType.name);
    $('.title').html(account.holder.title);
    $('.grace').html(account.grace === true ? 'YES' : 'NO');
    $('.balance').html(account.balance);
    $('.lastAction').html(account.lastAction);
    if (account.lastActionDate != null) {
        $('.lastActionDate').html(parseDateToString(account.lastActionDate));
    }
    if (account.status.id == 3) {
        $('.jsCloseAccount').addClass('isHidden');
        $('.jsReopenAccount').removeClass('isHidden');
    } else {
        $('.jsCloseAccount').removeClass('isHidden');
        $('.jsReopenAccount').addClass('isHidden');
    }
}

function getAccount(context, accountId) {
    var result = null;
    $.ajax({
        url: context + '/faces/' + accountId,
        async: false,
        cache: false,
        success: function(data, text, xhr) {
            result = data;
        },
        error: function(a, b, c) {
            alert('fail to get account, message:' + a.responseText);

        }
    });
    return result;
}




function makeHomeTab(context, tab, id) {
    var data = {
    };
    if (tab) {
        data.tab = tab;
    }
    if (id) {
        data.defaultTabAccountId = id;
    }
    $.ajax({
        url: context + '/common/tab' + '',
        type: 'POST',
        data: data,
        success: function(data, text, xhr) {
        },
        error: function(a, b, c) {
            alert('fail to make this tab your home tab, message:' + a.responseText);

        }
    });
}

function populateCalculationResult(result, tab) {
    var entity = {
        fersde: {
            deposit: 0,
            interest: 0,
            total: 0
        },
        fersre: {
            deposit: 0,
            interest: 0,
            total: 0
        },
        csrs_post391: {
            deposit: 0,
            interest: 0,
            total: 0
        },
        csrs_post82: {
            deposit: 0,
            interest: 0,
            total: 0
        },
        csrs_pre1082: {
            deposit: 0,
            interest: 0,
            total: 0
        },
        csrs_post1082_de: {
            deposit: 0,
            interest: 0,
            total: 0
        },
        csrs_pre1082_de: {
            deposit: 0,
            interest: 0,
            total: 0
        },
        fers_peace: {
            deposit: 0,
            interest: 0,
            total: 0
        },
        csrs_peace: {
            deposit: 0,
            interest: 0,
            total: 0
        }
    };
    $.each(result.dedeposits, function() {
        if (this.depositType == 'FERS_DEPOSIT') {
            entity.fersde.deposit += this.deposit;
            entity.fersde.interest += this.interest;
            entity.fersde.total += this.total;
        } else if (this.depositType == 'CSRS_POST_10_82_DEPOSIT') {
            entity.csrs_post1082_de.deposit += this.deposit;
            entity.csrs_post1082_de.interest += this.interest;
            entity.csrs_post1082_de.total += this.total;
        } else if (this.depositType == 'CSRS_PRE_10_82_DEPOSIT') {
            entity.csrs_pre1082_de.deposit += this.deposit;
            entity.csrs_pre1082_de.interest += this.interest;
            entity.csrs_pre1082_de.total += this.total;
        } else if (this.depositType == 'FERS_PEACE_CORPS') {
            entity.fers_peace.deposit += this.deposit;
            entity.fers_peace.interest += this.interest;
            entity.fers_peace.total += this.total;
        } else {
            entity.csrs_peace.deposit += this.deposit;
            entity.csrs_peace.interest += this.interest;
            entity.csrs_peace.total += this.total;
        }
    });
    $.each(result.redeposits, function() {
        if (this.depositType == 'FERS_REDEPOSIT') {
            entity.fersre.deposit += this.deposit;
            entity.fersre.interest += this.interest;
            entity.fersre.total += this.total;
        } else if (this.depositType == 'CSRS_POST_3_91_REDEPOSIT') {
            entity.csrs_post391.deposit += this.deposit;
            entity.csrs_post391.interest += this.interest;
            entity.csrs_post391.total += this.total;
        } else if (this.depositType == 'CSRS_POST_82_PRE_91_REDEPOSIT') {
            entity.csrs_post82.deposit += this.deposit;
            entity.csrs_post82.interest += this.interest;
            entity.csrs_post82.total += this.total;
        } else {
            entity.csrs_pre1082.deposit += this.deposit;
            entity.csrs_pre1082.interest += this.interest;
            entity.csrs_pre1082.total += this.total;
        }
    });

    var reTable = $('.chartCalAreaBox4 table tbody', tab);
    reTable.find('tr').eq(0).children('td').eq(1).html(entity.fersre.deposit);
    reTable.find('tr').eq(0).children('td').eq(2).html(entity.fersre.interest);
    reTable.find('tr').eq(0).children('td').eq(3).html(entity.fersre.total);
    reTable.find('tr').eq(1).children('td').eq(1).html(entity.csrs_post391.deposit);
    reTable.find('tr').eq(1).children('td').eq(2).html(entity.csrs_post391.interest);
    reTable.find('tr').eq(1).children('td').eq(3).html(entity.csrs_post391.total);
    reTable.find('tr').eq(2).children('td').eq(1).html(entity.csrs_post82.deposit);
    reTable.find('tr').eq(2).children('td').eq(2).html(entity.csrs_post82.interest);
    reTable.find('tr').eq(2).children('td').eq(3).html(entity.csrs_post82.total);
    reTable.find('tr').eq(3).children('td').eq(1).html(entity.csrs_pre1082.deposit);
    reTable.find('tr').eq(3).children('td').eq(2).html(entity.csrs_pre1082.interest);
    reTable.find('tr').eq(3).children('td').eq(3).html(entity.csrs_pre1082.total);

    var deTable = $('.chartCalAreaBox5 table tbody', tab);
    deTable.find('tr').eq(0).children('td').eq(1).html(entity.fersde.deposit);
    deTable.find('tr').eq(0).children('td').eq(2).html(entity.fersde.interest);
    deTable.find('tr').eq(0).children('td').eq(3).html(entity.fersde.total);
    deTable.find('tr').eq(1).children('td').eq(1).html(entity.csrs_post1082_de.deposit);
    deTable.find('tr').eq(1).children('td').eq(2).html(entity.csrs_post1082_de.interest);
    deTable.find('tr').eq(1).children('td').eq(3).html(entity.csrs_post1082_de.total);
    deTable.find('tr').eq(2).children('td').eq(1).html(entity.csrs_pre1082_de.deposit);
    deTable.find('tr').eq(2).children('td').eq(2).html(entity.csrs_pre1082_de.interest);
    deTable.find('tr').eq(2).children('td').eq(3).html(entity.csrs_pre1082_de.total);
    deTable.find('tr').eq(3).children('td').eq(1).html(entity.fers_peace.deposit);
    deTable.find('tr').eq(3).children('td').eq(2).html(entity.fers_peace.interest);
    deTable.find('tr').eq(3).children('td').eq(3).html(entity.fers_peace.total);
    deTable.find('tr').eq(4).children('td').eq(1).html(entity.csrs_peace.deposit);
    deTable.find('tr').eq(4).children('td').eq(2).html(entity.csrs_peace.interest);
    deTable.find('tr').eq(4).children('td').eq(3).html(entity.csrs_peace.total);

    $('.chartCalAreaBox6 .dollar', tab).eq(0).html(result.summary.totalPaymentsRequired);
    $('.chartCalAreaBox6 .dollar', tab).eq(1).html(result.summary.totalInitialInterest);
    $('.chartCalAreaBox6 .dollar', tab).eq(2).html(result.summary.totalPaymentsApplied);
    $('.chartCalAreaBox6 .dollar', tab).eq(3).html(result.summary.totalBalance);

}

function populateCalculationItem(data, tab) {
    $(".noValidMask", tab).hide();
    var validTbl = $(".validateResultTbl", tab);
    validTbl.attr('id', 'result-' + data.id);
    $("tbody", validTbl).find('tr').remove();
    $.each(data.items, function() {
        var template = $('#resultItemTemplate').find('tr').clone();
        template.prop('id', 'ri-' + this.id);
        template.find('td').eq(1).html(parseDateToString(this.startDate));
        template.find('td').eq(2).html(parseDateToString(this.endDate));
        template.find('td').eq(3).html(parseDateToString(this.midDate));
        template.find('td').eq(4).html(parseDateToString(this.refundDate));
        template.find('td').eq(5).html(this.periodType.name);
        template.find('td').eq(5).next('input:hidden').val(this.periodType.id);
        template.find('td').eq(6).html(this.deductionAmount);
        template.find('td').eq(7).html(this.totalInterest);
        template.find('td').eq(8).html(this.paymentsApplied);
        template.find('td').eq(9).html(this.balance);
        $("tbody", validTbl).append(template);
    });
    var newRow = $('#resultItemNewTemplate').find('tr').clone();
    $("tbody", validTbl).append(newRow);

}

function decorateCalculationResult(tab) {
    var result = $('.resultsVal', tab);
    var validation = $('.statusVal', tab);
    if (result.html() == 'Success' || result.html() == 'Status Calculation Saved' || result.html() == 'Status Calculation Triggered Pending') {
        result.addClass('successLabel');
        result.removeClass('failLabel');
    } else if (result.html() == 'Unknown') {
        result.removeClass('successLabel');
        result.removeClass('failLabel');
    } else {
        result.removeClass('successLabel');
        result.addClass('failLabel');
    }


    if (validation.html() == 'Success') {
        validation.addClass('successLabel');
        validation.removeClass('failLabel');
    } else if (validation.html() == 'Unknown') {
        validation.removeClass('successLabel');
        validation.removeClass('failLabel');
    } else {
        validation.removeClass('successLabel');
        validation.addClass('failLabel');
    }


}

function addNewVersion(button, copy) {
    // tab
    var tab = $(button).parents(".tabsBlock").eq(0);
    // calculate date
    var dateSpan = $('.calculateDate', tab);
    // status
    var statusSpan = $('.resultsVal', tab);
    // table
    var table = $('.entriesTbl', tab);
    // select
    var select = $('.versionBar select', tab);
    // delete button
    $('.jsDelVersionBtn', tab).removeClass('priBtnDisabled');

    var verSelector = $(".versionBar select", tab);
    var newIndex = 'new-' + (verSelector.find("option[value^='new']").length + 1);

    verSelector.append($('<option>', {
        value: newIndex,
        text: 'New Version'
    }));
    var idx = $("option", verSelector).length - 1;
    $(".versionBar select", tab)[0].selectedIndex = idx;
    var newRow = $('#versionNewTemplate tbody tr').clone();
    table.find('tbody').html(newRow);





    select.change(function() {
        var selectId = this.options[this.selectedIndex].value;
        var accountId = $('#accountId').val();
        var context = $('#context').val();
        var account = getAccount(context, accountId);
        var versions = account.calculationVersions;
        $.each(versions, function() {
            if (this.id == selectId) {
                var tbody = $('#versionTableTemplate tbody').clone();
                $.each(this.calculations, function() {
                    var rowTemplate = tbody.children('tr').eq(0).clone();
                    rowTemplate.prop('id', 'cal-' + this.id);
                    rowTemplate.children('td').eq(1).html(parseDateToString(new Date(this.beginDate)));
                    rowTemplate.children('td').eq(2).html(parseDateToString(new Date(this.endDate)));
                    rowTemplate.children('td').eq(3).html(parseDateToString(new Date(this.refundDate)));
                    rowTemplate.children('td').eq(4).html(this.retirementType.name);
                    rowTemplate.children('input:hidden').eq(0).val(this.retirementType.id);
                    rowTemplate.children('td').eq(5).html(this.periodType.name);
                    rowTemplate.children('input:hidden').eq(1).val(this.periodType.id);
                    rowTemplate.children('td').eq(6).html(this.appointmentType.name);
                    rowTemplate.children('input:hidden').eq(2).val(this.appointmentType.id);
                    rowTemplate.children('td').eq(7).html(this.serviceType.name);
                    rowTemplate.children('input:hidden').eq(3).val(this.serviceType.id);
                    rowTemplate.children('td').eq(8).html(this.amount);
                    if (this.interestRate == null) {
                        rowTemplate.children('td').eq(9).html('Default');
                    } else {
                        rowTemplate.children('td').eq(9).html(this.interestRate);
                    }
                    rowTemplate.children('td').eq(10).html(this.payType.name);
                    rowTemplate.children('input:hidden').eq(4).val(this.payType.id);
                    tbody.children('tr').last().after(rowTemplate);
                });
                // send information to the temp space
                tbody.children('tr').eq(0).remove();
                var newRow = $('#versionNewTemplate tbody tr').clone();
                tbody.append(newRow);
                table.find('tbody').remove();
                table.append(tbody);
                dateSpan.html(parseDateToString(this.calculationDate));
                statusSpan.html(this.calculationResult.calculationStatus.name);
            }
        });
        decorateCalculationResult(tab);


        $('.dollar').formatCurrency({
            negativeFormat: '%s-%n'
        });
    });
}

function deleteVersion(context, versionId) {
    $.ajax({
        url: context + '/faces/calculation/delete?calculationVersionId=' + versionId,
        type: 'DELETE',
        async: false,
        success: function(data, text, xhr) {
        },
        error: function(a, b, c) {
            alert('fail to delete calculation version, message:' + a.responseText);
        }
    });
}

function populateCalculationVersion(account) {

    // calculation version
    var dvs = account.calculationVersions;
    // the deposit tab
    var tab = $('.depositTab');
    // is this page the finish page?
    var finish = false;
    // the version drop down
    var select = $('.versionBar select', tab);
    // the table
    var table = $('.entriesTbl', tab);

    // calculate date
    var dateSpan = $('.calculateDate', tab);
    // status
    var statusSpan = $('.resultsVal', tab);


    select.html('');
    if (dvs.length == 0 && finish == false) {
        $('.jsDelVersionBtn').addClass('priBtnDisabled');
        return;
    }


    $.each(dvs, function(i) {
        var option = "<option value='" + this.id + "'";
        if (i == 0) {
            option += " selected='selected'";
        }
        option += ">" + (i + 1) + "-" + this.name + "</option>";
        select.append(option);

        if (i == 0) {
            var tbody = $('#versionTableTemplate tbody').clone();
            $.each(this.calculations, function() {
                var rowTemplate = tbody.children('tr').eq(0).clone();
                rowTemplate.prop('id', 'cal-' + this.id);
                rowTemplate.children('td').eq(1).html(parseDateToString(new Date(this.beginDate)));
                rowTemplate.children('td').eq(2).html(parseDateToString(new Date(this.endDate)));
                rowTemplate.children('td').eq(3).html(parseDateToString(new Date(this.refundDate)));
                rowTemplate.children('td').eq(4).html(this.retirementType.name);
                rowTemplate.children('input:hidden').eq(0).val(this.retirementType.id);
                rowTemplate.children('td').eq(5).html(this.periodType.name);
                rowTemplate.children('input:hidden').eq(1).val(this.periodType.id);
                rowTemplate.children('td').eq(6).html(this.appointmentType.name);
                rowTemplate.children('input:hidden').eq(2).val(this.appointmentType.id);
                rowTemplate.children('td').eq(7).html(this.serviceType.name);
                rowTemplate.children('input:hidden').eq(3).val(this.serviceType.id);
                rowTemplate.children('td').eq(8).html(this.amount);
                if (this.interestRate == null) {
                    rowTemplate.children('td').eq(9).html('Default');
                } else {
                    rowTemplate.children('td').eq(9).html(this.interestRate);
                }
                rowTemplate.children('td').eq(10).html(this.payType.name);
                rowTemplate.children('input:hidden').eq(4).val(this.payType.id);
                tbody.children('tr').last().after(rowTemplate);
            });
            // send information to the temp space
            tbody.children('tr').eq(0).remove();
            var newRow = $('#versionNewTemplate tbody tr').clone();
            tbody.append(newRow);
            table.find('tbody').remove();
            table.append(tbody);
            dateSpan.html(parseDateToString(this.calculationDate));
            statusSpan.html(this.calculationResult.calculationStatus.name);
            populateCalculationResult(this.calculationResult, tab);
            populateCalculationItem(this.calculationResult, tab);
            decorateCalculationResult(tab);
        }
    });


    select.change(function() {

        var selectId = this.options[this.selectedIndex].value;
        var accountId = $('#accountId').val();
        var context = $('#context').val();
        var account = getAccount(context, accountId);
        var versions = account.calculationVersions;
        $.each(versions, function() {
            if (this.id == selectId) {
                var tbody = $('#versionTableTemplate tbody').clone();
                $.each(this.calculations, function() {
                    var rowTemplate = tbody.children('tr').eq(0).clone();
                    rowTemplate.prop('id', 'cal-' + this.id);
                    rowTemplate.children('td').eq(1).html(parseDateToString(new Date(this.beginDate)));
                    rowTemplate.children('td').eq(2).html(parseDateToString(new Date(this.endDate)));
                    rowTemplate.children('td').eq(3).html(parseDateToString(new Date(this.refundDate)));
                    rowTemplate.children('td').eq(4).html(this.retirementType.name);
                    rowTemplate.children('input:hidden').eq(0).val(this.retirementType.id);
                    rowTemplate.children('td').eq(5).html(this.periodType.name);
                    rowTemplate.children('input:hidden').eq(1).val(this.periodType.id);
                    rowTemplate.children('td').eq(6).html(this.appointmentType.name);
                    rowTemplate.children('input:hidden').eq(2).val(this.appointmentType.id);
                    rowTemplate.children('td').eq(7).html(this.serviceType.name);
                    rowTemplate.children('input:hidden').eq(3).val(this.serviceType.id);
                    rowTemplate.children('td').eq(8).html(this.amount);
                    if (this.interestRate == null) {
                        rowTemplate.children('td').eq(9).html('Default');
                    } else {
                        rowTemplate.children('td').eq(9).html(this.interestRate);
                    }
                    rowTemplate.children('td').eq(10).html(this.payType.name);
                    rowTemplate.children('input:hidden').eq(4).val(this.payType.id);
                    tbody.children('tr').last().after(rowTemplate);
                });
                // send information to the temp space
                tbody.children('tr').eq(0).remove();
                var newRow = $('#versionNewTemplate tbody tr').clone();
                tbody.append(newRow);
                table.find('tbody').remove();
                table.append(tbody);
                dateSpan.html(parseDateToString(this.calculationDate));
                statusSpan.html(this.calculationResult.calculationStatus.name);
                populateCalculationResult(this.calculationResult, tab);
                populateCalculationItem(this.calculationResult, tab);
                decorateCalculationResult(tab);
            }
        });
        decorateCalculationResult(tab);


        $('.dollar').formatCurrency({
            negativeFormat: '%s-%n'
        });

    });
}
//Padding Month/Day 
function padding(value, length) {
    var paddingCount = length - String(value).length;
    for (var i = 0; i < paddingCount; i++) {
        value = '0' + value;
    }
    return value;
}

//check required fields of country
function checkRequiredFields(obj) {
    var selectedCountry = $("option:selected", $(obj)).text();
    var wrapper = $(obj).parents(".halfRowField").eq(0).parent();
    var cityRow = $("input[name='city']").parents(".halfRowField").eq(0);
    var stateRow = $("select[name='state']").parents(".halfRowField").eq(0);
    var zipRow = $("input[name='zipCode']").parents(".halfRowField").eq(0);
    if (selectedCountry != "United States") {
        $(".error", cityRow).removeClass("error");
        $(".error", zipRow).removeClass("error");
        $(".errorIcon", cityRow).hide();
        $(".errorIcon", zipRow).hide();
        $(".reqMark", cityRow).addClass("isHidden");
        $(".reqMark", stateRow).addClass("isHidden");
        $(".reqMark", zipRow).addClass("isHidden");
    } else {
        $(".reqMark", cityRow).removeClass("isHidden");
        $(".reqMark", stateRow).removeClass("isHidden");
        $(".reqMark", zipRow).removeClass("isHidden");
    }
}

var checkDollar = function(evt) {
    if (evt.which === 46) {
        if (/\./.test($(this).val()) === true) {
            evt.preventDefault();
        }
    } else if (evt.which > 31) {
        if (evt.which < 48 || evt.which > 57) {
            evt.preventDefault();
        } else {
            var value = $(this).val();
            var newVal = value + (evt.which - 48);
            if (parseInt(newVal) > 100000000) {
                evt.preventDefault();
            }
        }
    }
};
