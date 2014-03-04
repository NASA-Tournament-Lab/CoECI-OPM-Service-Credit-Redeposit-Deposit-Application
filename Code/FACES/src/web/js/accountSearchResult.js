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

var currentFilter = null;
$(function() {
    var context = $('#context').val();
    // get the account search filter
    $.ajax({
        url: context + '/faces/search/history',
        async: false,
        success: function(data, text, xhr) {
            if (data) {
                currentFilter = data;
                populateFilterInfo(currentFilter);
                getSearchResult(context, currentFilter);
                $('.accountSearchSummary, .accountSearchResultsWrapper').show();
            } else {
                $('.accountSearchSummary, .accountSearchResultsWrapper').hide();
            }

        },
        error: function(a, b, c) {
            alert('fail to get search history, message:' + a.responseText);
        }
    });

    // go to the account detail page when clicking a row
    $('#accountSearchResultTbl').delegate('td', 'click', function() {
        var id = $(this).parents('tr').children('input:hidden').val();
        window.location = context + '/faces/' + id + '/detail';
    });

    // select per page
    $('.perPage select').change(function() {
        currentFilter.pageSize = $(this).val();
        currentFilter.pageNumber = 1;
        getSearchResult(context, currentFilter);
    });

    $('.paginationLinks').delegate('.toPrev', 'click', function() {
        var wrapper = $(this).parent().parent();
        if ((!$(this).hasClass("toPrevDisabled")) && ($('.toPage', wrapper).length > 1)) {
            var prevItem = $('.current', wrapper).prev(".toPage");
            $('.toPage', wrapper).removeClass("current");
            prevItem.addClass("current");
            $('.toNext', wrapper).removeClass("toNextDisabled");
            var currentIdx = parseInt(prevItem.text(), 10);
            if (currentIdx === 1) {
                $('.toPrev', wrapper).addClass("toPrevDisabled");
            }
            currentFilter.pageNumber = currentIdx;
            getSearchResult(context, currentFilter);
        }
    });
    $('.paginationLinks').delegate('.toNext', 'click', function() {
        var wrapper = $(this).parent().parent();
        if ((!$(this).hasClass("toNextDisabled")) && ($('.toPage', wrapper).length > 1)) {
            var nextItem = $('.current', wrapper).next(".toPage");
            $('.toPage', wrapper).removeClass("current");
            nextItem.addClass("current");
            $('.toPrev', wrapper).removeClass("toPrevDisabled");
            var currentIdx = parseInt(nextItem.text(), 10);
            var totalIdx = parseInt($('.toPage', wrapper).length, 10);
            if (currentIdx === totalIdx) {
                $('.toNext', wrapper).addClass("toNextDisabled");
            }
            currentFilter.pageNumber = currentIdx;
            getSearchResult(context, currentFilter);
        }
    });
    $('.paginationLinks').delegate('.toPage', 'click', function() {
        var wrapper = $(this).parent().parent();
        if ((!$(this).hasClass("current")) && ($('.toPage', wrapper).length > 1)) {
            $('.toPage', wrapper).removeClass("current");
            $(this).addClass("current");
            $('.toNext', wrapper).removeClass("toNextDisabled");
            $('.toPrev', wrapper).removeClass("toPrevDisabled");
            var currentIdx = parseInt($(this).text(), 10);
            var totalIdx = parseInt($('.toPage', wrapper).length, 10);
            if (currentIdx === 1) {
                $('.toPrev', wrapper).addClass("toPrevDisabled");
            }
            if (currentIdx === totalIdx) {
                $('.toNext', wrapper).addClass("toNextDisabled");
            }
            currentFilter.pageNumber = currentIdx;
            getSearchResult(context, currentFilter);
        }
    });
    
    
});

function getSearchResult(context, filter) {
    $.ajax({
        url: context + '/faces/search' ,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(filter),
        success: function(data, text, xhr) {
            $('.result-tbody').html(getResultHtml(data.items));
            $("#accountSearchResultTbl tbody td, .accountNotesPanel tbody td").mouseenter(function() {
                $(this).parent("tr").addClass("rowHover");
            }).mouseleave(function() {
                $(this).parent("tr").removeClass("rowHover");
            });
            updateFilterInfo(filter, data);
        },
        error: function(a, b, c) {
            alert('fail to search accounts, message:' + a.responseText);
        }
    });
}

function getResultHtml(items) {
    var html = "";
    for (var i = 0; i < items.length; i++) {
        var a = items[i];
        html += "<tr>";
        html += "<input type='hidden' value='" + a.id + "'/>";
        html += "<td class='firstCol'>" + a.claimNumber + "</td>";
        html += "<td>" + a.holder.firstName + "</td>";
        var miStr = a.holder.middleInitial != null ? a.holder.middleInitial : '';
        html += "<td>" + miStr + "</td>";
        html += "<td>" + a.holder.lastName + "</td>";
        html += "<td>" + a.holder.ssn + "</td>";
        html += "<td class='lastCol'>" + parseDateToString(a.holder.birthDate) + "</td></tr>";
    }
    return html;
}

function updateFilterInfo(filter, result) {
    $('.perPage select').val(filter.pageSize);
    var startCount = result.total == 0 ? 0 : (filter.pageNumber - 1) * filter.pageSize + 1;
    var endCount = filter.pageNumber * filter.pageSize;
    if (endCount > result.total) {
        endCount = result.total;
    }
    $('.paginationLabel').html("<span class='startCount'>" + startCount + "</span>-<span class='endCount'>"
            + endCount + "</span> of <span class='totalCount'>" + result.total + "</span>");

    var pageHtml = "";
    pageHtml += "<a href='javascript:;' class='toPrev";
    if (filter.pageNumber === 1) {
        pageHtml += " toPrevDisabled";
    }
    pageHtml += "'>Prev</a>";
    for (var i = 0; i < result.totalPageCount; i++) {
        pageHtml += "<a href='javascript:;' class='toPage";
        if (filter.pageNumber === i + 1) {
            pageHtml += " current";
        }
        pageHtml += "'>" + (i + 1) + "</a>";
    }
    pageHtml += "<a href='javascript:;' class='toNext";
    if (filter.pageNumber === result.totalPageCount) {
        pageHtml += " toNextDisabled";
    }
    pageHtml += "'>Next</a>";

    $('.paginationLinks').html(pageHtml);

    var desc = "";
    desc += result.total + " record";
    if (result.total !== 1) {
        desc += "s";
    }
    desc += " found using ";
    var begin = true;
    if (filter.claimNumber && filter.claimNumber !== '') {
        if (begin === false) {
            desc += ", ";
        }
        desc += "Claim Number: " + filter.claimNumber.replace(/\\/g, '') + " ";
        begin = false;
    }
    if (filter.ssn && filter.ssn !== '') {
        if (begin === false) {
            desc += ", ";
        }
        desc += "SSN: " + filter.ssn.replace(/\\/g, '') + " ";
        begin = false;
    }

    if (filter.middleName && filter.middleName !== '') {
        if (begin === false) {
            desc += ", ";
        }
        desc += "Middle Name: " + filter.middleName.replace(/\\/g, '') + " ";
        begin = false;
    }
    if (filter.firstName && filter.firstName !== '') {
        if (begin === false) {
            desc += ", ";
        }
        desc += "First Name: " + filter.firstName.replace(/\\/g, '') + " ";
        begin = false;
    }
    if (filter.lastName && filter.lastName !== '') {
        if (begin === false) {
            desc += ", ";
        }
        desc += "Last Name: " + filter.lastName.replace(/\\/g, '') + " ";
        begin = false;
    }
    if (filter.birthDate) {
        if (begin === false) {
            desc += ", ";
        }
        var date = new Date(filter.birthDate);
        desc += "birthDate: " + (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear() + " ";
        begin = false;
    }
    $('.foundRecordCount').html(desc);
    $(".pagination .current").each(function() {
		hidePagination($(this));
	});
}

function populateFilterInfo(filter) {
    var textboxes = ['claimNumber', 'firstName', 'lastName', 'middleName'];
    $.each(textboxes, function() {
        if (filter[this] != null) {
            $("input[name='" + this + "']").val(filter[this].replace(/\\/g, ''));
        }
    });
    if (filter.ssn != null) {
        var tokens = filter.ssn.split('-');
        var ssnFields = $('input.ssn');
        $.each(tokens, function(i) {
            ssnFields.eq(i).val(tokens[i]);
        });
    }
    
    if (filter.excludeHistory == true) {
        $('#excludeFromSearch').prop('checked', true);
    }
    
    if (filter.birthDate != null) {
        $('.datePicker').val(parseDateToString(filter.birthDate));
    }
}

function makeTwo(value) {
    if (value >= 10) {
        return value + '';
    }
    return '0' + value;
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
