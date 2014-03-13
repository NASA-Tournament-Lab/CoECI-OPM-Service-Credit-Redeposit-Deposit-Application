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


var currentPaymentPageNumber = 1;

function search(filter) {
	//$("#loadingOverlay").show();
    $.ajax({
    url: rootUrl + "payments/search?resourceName=link",
    data: JSON.stringify(filter),
    async:true,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "POST",
    cache : false,
    success: function(data) {
    	if(data.total === 1 || data.total === 0){
    		$(".paymentSearchResultsSummary strong").html(data.total + " payment");
    	} else{
    		$(".paymentSearchResultsSummary strong").html(data.total + " payments");
    	}
    	
        var parent = $("#paymentSearchResultsTbl tbody");
    	parent.html("");
    	var rows = "";
    	var rowTemp = '<tr class="">' +
    			'<td class="blankCell firstCol">{0}</td><td>{1}</td><td>{2}</td>' +
    			'<td>{3}</td><td>{4}</td><td>{5}</td><td>{6}</td>' +
    			'<td>{7}</td><td>{8}</td><td>{9}</td><td>{10}</td>' +
    			'<td class="lastCol">{11}</td></tr>';
        $.each(data.items, function(index, item) {
        	rows += rowTemp
        	.replace("{0}", '&nbsp;')
        	.replace("{1}", item.id)
        	.replace("{2}", item.batchNumber + "-" + item.blockNumber + "-" + item.sequenceNumber)
        	.replace("{3}", item.claimNumber?item.claimNumber:"")
        	.replace("{4}", formatNotificationDate(item.accountHolderBirthdate))
        	.replace("{5}", "$" + item.amount.toFixed(2))
        	.replace("{6}", formatNotificationDate(item.depositDate))
        	.replace("{7}", item.paymentAppliance?item.paymentAppliance.name:"")
        	.replace("{8}", isNull(item.paymentStatus)?"":item.paymentStatus.name)
        	.replace("{9}", isNull(item.ssn)?"":item.ssn)
        	.replace("{10}", isNull(item.claimant)?"":item.claimant)
        	.replace("{11}", isNull(item.importId)?"":item.importId);
        });
        
        
        $(rows).appendTo(parent);
        
        parent.find("tr").each(function(index, item) {
        	$(item).data("data", data.items[index]);
        	$(item).on("click", function() {
        		clipboard();
        		$(this).closest("tbody").find("tr").removeClass("highlighted");
        		$(this).addClass("highlighted");
        		$(".paymentSearchResultsBtnWrapper a").removeClass("priBtnDisabled");
        	    $(".paymentSearchResultsBtnWrapper a").addClass("priBtnActive");
        	    
        	    if ($(this).data("data").accountId != null) {
        	        setCurrentAccountId($(this).data("data").accountId);
        	    } else {
        	    	setCurrentAccountId(-1);
        	    }
        	});
        });
        
        
        // Bug Fix 2
        //$(".paymentSearchResultsBtnWrapper a").addClass("priBtnDisabled");
        
        $("#paymentSearchResultsTbl thead tr th").eq(1).find("span").eq(1).addClass("sort-down");

        renderPagination(data.total);
        
        if (data.total > 0) {
        	$(".paymentSearchResultsWrapper > div").show();
        } else {
        	$(".paymentSearchResultsWrapper > div").hide();
        }
        
        $(".paymentSearchResultsWrapper .paymentSearchResultsSummary").show();

        manualSort($("#paymentSearchResultsTbl"));

        // clipboard(); Bug Fix 1 Disable
        
        // $(window).trigger("resize"); Bug Fix 1 Disable
        
        // Bug Fix 2
        //clipboard();
        //$("#loadingOverlay").hide();
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to search the payments.");
    }
    }
    );
}

function resetSearchFilter(){
	
	
	$('div.paymentSearchBox select[name="psStatus"]').prop('selectedIndex',0);
	$('div.paymentSearchBox select[name="psStatus"]').change();
	$('div.paymentSearchBox select').prop('selectedIndex',0);
	$('div.paymentSearchBox select').change();
	$('div.paymentSearchBox select[name="psAmountType"]').prop('selectedIndex',0);
	$('div.paymentSearchBox select[name="psAmountType"]').change();
	$('div.paymentSearchBox input[name="psAmount"]').val("0.00");
	$('div.paymentSearchBox select[name="psType"]').prop('selectedIndex',0);
	$('div.paymentSearchBox select[name="psType"]').change();
	$('div.paymentSearchBox input[name="depositedDate"]').val("");
	$('div.paymentSearchBox input[name="psCSD"]').val("");
	$('div.paymentSearchBox input[name="psBatch"]').val("");
	$('div.paymentSearchBox input[name="psBlock"]').val("");
	$('div.paymentSearchBox input[name="psSeq"]').val("");
	$("#psResolved").get(0).checked = false;
	$("#startDate").val("");
	$("#endDate").val("");
}

function populatePaymentSearchFilter() {
	var filter = {
		pageNumber:1,
		pageSize:10,
		sortOrder:"ASC",
		sortColumn:"id"
	};

	filter.pageSize = $('.perPage select').val();
	filter.pageNumber = currentPaymentPageNumber;
	if (filter.pageSize == 0) {
		filter.pageNumber = 0;
	}
	// trim all inputs
	$('div.paymentSearchBox input[type="text"]').each(function() {
		$(this).val($.trim($(this).val()));
	});
	filter.claimNumber = $('div.paymentSearchBox input[name="psCSD"]').val() == "" ? null : $('div.paymentSearchBox input[name="psCSD"]').val();
	
	filter.batchNumber = $('div.paymentSearchBox input[name="psBatch"]').val() == "" ? null : $('div.paymentSearchBox input[name="psBatch"]').val();
	filter.blockNumber = $('div.paymentSearchBox input[name="psBlock"]').val() == "" ? null : $('div.paymentSearchBox input[name="psBlock"]').val();
	filter.sequenceNumber = $('div.paymentSearchBox input[name="psSeq"]').val() == "" ? null : $('div.paymentSearchBox input[name="psSeq"]').val();
	filter.resolvedSuspense = $("#psResolved").get(0).checked;

	filter.amount = $('div.paymentSearchBox input[name="psAmount"]').val() == "" ? null : $('div.paymentSearchBox input[name="psAmount"]').val();

	if (filter.amount !== null && !filter.amount.match(/^\d+\.?\d+$/)) {
		alert("The amount should be numeric.");
		return null;
	}

	if (isNaN(filter.amount)) {
		filter.amount = -1;
	}
	
	//filter.paymentType = $('div.paymentSearchBox select[name="psType"]').find("option:selected").data("data");
    filter.paymentStatus = $('div.paymentSearchBox select[name="psStatus"]').find("option:selected").data("data");
    
    filter.depositComparisonType = $('div.paymentSearchBox select').find("option:selected").data("data");
    filter.amountComparisonType = $('div.paymentSearchBox select[name="psAmountType"]').find("option:selected").data("data");
    
    filter.paymentAppliance = $('div.paymentSearchBox select[name="psType"]').find("option:selected").data("data");
    
    filter.depositDate = $('div.paymentSearchBox input[name="depositedDate"]').val() == "" ? null : parseDateMMddyyyy($('div.paymentSearchBox input[name="depositedDate"]').val());
	filter.depositStartDate = $("#startDate").val() == "" ? null : parseDateMMddyyyy($("#startDate").val());
	filter.depositEndDate = $("#endDate").val() == "" ? null : parseDateMMddyyyy($("#endDate").val());
    filter.depositComparisonType = $('div.paymentSearchBox select').find("option:selected").data("data");
    if (filter.depositComparisonType == 'DEPOSITED_BETWEEN') {
        if ($("#startDate").val() != "" && parseDateMMddyyyy($("#startDate").val()) == null) {
        	alert("The start date format must be mm/dd/yyyy to find matching payments.");
        	return null;
        }
        if ($("#endDate").val() != "" && parseDateMMddyyyy($("#endDate").val()) == null) {
        	alert("The end date format must be mm/dd/yyyy to find matching payments.");
        	return null;
        }
    } else if ( filter.depositComparisonType != null && (
            $('div.paymentSearchBox input[name="depositedDate"]').val() == "" 
            || parseDateMMddyyyy( $('div.paymentSearchBox input[name="depositedDate"]').val()) == null)) {
    	alert("The date format must be mm/dd/yyyy to find matching payments.");
        return null;
    }
    
    if (filter.depositComparisonType == 'DEPOSITED_ON') {
    	filter.depositStartDate = shifftDate(filter.depositDate, 0);
    	filter.depositEndDate = shifftDate(filter.depositDate, 1);
    	filter.depositComparisonType = 'DEPOSITED_BETWEEN';
    	filter.depositDate = null;
    } else if (filter.depositComparisonType == 'DEPOSITED_AFTER') {
    	filter.depositDate = shifftDate(filter.depositDate, 1);
    } else if (filter.depositComparisonType == 'DEPOSITED_BEFORE_OR_ON') {
    	filter.depositDate = shifftDate(filter.depositDate, 1);
    } else if (filter.depositComparisonType == 'DEPOSITED_BETWEEN') {
    	if (filter.depositEndDate) {
    	    filter.depositEndDate = shifftDate(filter.depositEndDate, 1);
    	    filter.depositEndDate = new Date(filter.depositEndDate.getTime() - 1);
    	}
    } else if (filter.depositComparisonType == 'DEPOSITED_BEFORE') {
    	filter.depositDate = shifftDate(filter.depositDate, -1);
    } 
    return filter;
}

function initSelectForEnum() {
	$('.paymentSearchBox select.psDepositedType').find('option').each(function(index, item) {
	    if ($(item).attr("value") == "any") {
	        $(item).data("data", null);
	    }
	    if ($(item).attr("value") == "Deposited before") {
	        $(item).data("data", "DEPOSITED_BEFORE");
	    }
	    if ($(item).attr("value") == "Deposited before or on") {
	        $(item).data("data", "DEPOSITED_BEFORE_OR_ON");
	    }
	    if ($(item).attr("value") == "Deposited on") {
	        $(item).data("data", "DEPOSITED_ON");
	    }
	    if ($(item).attr("value") == "Deposited on or after") {
	        $(item).data("data", "DEPOSITED_ON_OR_AFTER");
	    }
	    if ($(item).attr("value") == "Deposited after") {
	        $(item).data("data", "DEPOSITED_AFTER");
	    }
	    if ($(item).attr("value") == "between") {
	        $(item).data("data", "DEPOSITED_BETWEEN");
	    }
    });
    
    
    $('.paymentSearchBox select[name="psAmountType"]').find('option').each(function(index, item) {
	    if ($(item).attr("value") == "any") {
	        $(item).data("data", null);
	    }
	    if ($(item).attr("value") == "Less than") {
	        $(item).data("data", "LESS_THAN");
	    }
	    if ($(item).attr("value") == "Less than or equal to") {
	        $(item).data("data", "LESS_THAN_OR_EQUAL");
	    }
	    if ($(item).attr("value") == "Equal to") {
	        $(item).data("data", "EQUAL");
	    }
	    if ($(item).attr("value") == "Greater than or equal to") {
	        $(item).data("data", "GREATER_THAN_OR_EQUAL");
	    }
	    if ($(item).attr("value") == "Greater than") {
	        $(item).data("data", "GREATER_THAN");
	    }
	    if ($(item).attr("value") == "Not equal to") {
	        $(item).data("data", "NET_EQUAL");
	    }
    });

}

function getAllApplicationDesignation() {
    $.ajax({
	    url: rootUrl + "lookup/applicationDesignations?resourceName=link",
	    async:false,
	    contentType: 'application/json',
	    headers: { 'Accept': 'application/json'},
	    type: "GET",
	    cache : false,
	    success: function(items) {
	        var select = $('div.addPaymentPopup select[name="paymentApplyTo"]');
			select.html('');
			$.each(items, function(index,item){
			    var optionTemp = "<option>" + item.name + "</option>";
			    $(optionTemp).appendTo(select);
			    //select.last().data("data", item);
			});
			select.find("option").each(function(index, item){
			    $(item).data("data", items[index]);
			});
	    },
	
	    error:function(jqXHR, textStatus,errorThrow){
	        alert("Failed to get the application designations.");
	    }
    }
    );
}

function setCurrentAccountId(accountId) {
    $.ajax({
    url: rootUrl + "payment/setAccountId?resourceName=link&accountId=" + accountId,
    async:false,
    //contentType: 'application/json',
    //headers: { 'Accept': 'application/json'},
    type: "GET",
    cache : false,
    success: function(data) {
        
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to set the current account id.");
    }
    }
    );
}

function getAllPaymentStatuses() {
    $.ajax({
	    url: rootUrl + "lookup/paymentStatuses?resourceName=link",
	    async:false,
	    contentType: 'application/json',
	    headers: { 'Accept': 'application/json'},
	    type: "GET",
	    cache : false,
	    success: function(items) {
	    	items.sort(predicatBy("name"));
	        var select = $('div.paymentSearchBox select[name="psStatus"]');
			select.html('');
			$('<option>Any Status</option>').appendTo(select);
			$.each(items, function(index,item){
			    var optionTemp = "<option>" + item.name + "</option>";
			    $(optionTemp).appendTo(select);
			    select.find("option:last").data("data", item);
			});
			
			
	    },
	
	    error:function(jqXHR, textStatus,errorThrow){
	        alert("Failed to get the payment status.");
	    }
    }
    );
}

function getAllPaymentAppliances() {
    $.ajax({
	    url: rootUrl + "lookup/paymentAppliances?resourceName=link",
	    async:false,
	    contentType: 'application/json',
	    headers: { 'Accept': 'application/json'},
	    type: "GET",
	    cache : false,
	    success: function(items) {
	        var select = $('div.paymentSearchBox select[name="psType"]');
			select.html('');
			$('<option>Any Type</option>').appendTo(select);
			$.each(items, function(index,item){
			    var optionTemp = "<option>" + item.name + "</option>";
			    $(optionTemp).appendTo(select);
			    select.find("option:last").data("data", item);
			});
			
			var parent = $(".radioField .radioWrapper");
			parent.html('');
			var inputLeft = '<input name="addPaymentType" type="radio" id="{0}" value="ppr">';
			var labelLeft = '<label class="radioLabel longLabel" for="{0}">{1}</label>';
			
			var inputRight = '<input name="addPaymentType" type="radio" id="{0}" value="wo">';
			var labelRight = '<label class="radioLabel" for="{0}">{1}</label>';
			
			var i = 1;
			$(".addPaymentNote").hide();
			$.each(items, function(index,item){
				var input;
				var label;
			    if (i %2 == 1) {
			    	input = inputLeft.replace("{0}", item.id + "ofID");
			    	label = labelLeft.replace("{1}", item.name).replace("{0}", item.id + "ofID");
			    } else {
			    	input = inputRight.replace("{0}", item.id + "ofID");
			    	label = labelRight.replace("{1}", item.name).replace("{0}", item.id + "ofID");
			    }
			    
			    if (item.name == 'Prior Payment Recorded') {
			    	input = input.replace(item.id + "ofID", "ppr");
			    	label = label.replace(item.id + "ofID", "ppr");
			    } else if (item.name =='Adjustment (no GL)') {
			    	input = input.replace(item.id + "ofID", "ang");
			    	label = label.replace(item.id + "ofID", "ang");
			    }
			    $(input).appendTo(parent);
			    $(label).appendTo(parent);
			    i++;
			});
			
			parent.find("input").each(function(index, item){
			    $(this).data("data", items[index]);
			});
			parent.find("label").on('click', function(){
			    $("p.addPaymentNote").hide();
			    if ($(this).attr("for") == "ppr") {
			    	$("p.addPaymentNote.addPaymentNote_ppr").show();
			    }
			    if ($(this).attr("for") == "ang") {
			    	$("p.addPaymentNote.addPaymentNote_ang").show();
			    }
			});
			
			parent.find("input").on('click', function(){
			    $("p.addPaymentNote").hide();
			    if ($(this).attr("id") == "ppr") {
			    	$("p.addPaymentNote.addPaymentNote_ppr").show();
			    }
			    if ($(this).attr("id") == "ang") {
			    	$("p.addPaymentNote.addPaymentNote_ang").show();
			    }
			});
	    },
	
	    error:function(jqXHR, textStatus,errorThrow){
	        alert("Failed to get the payment appliances.");
	    }
    }
    );
}
	


$(document).ready(function() {
	// Setup page
    setupPage('PAYMENTS', 6);
    initSelectForEnum();
    getAllPaymentStatuses();
    getAllPaymentAppliances();
    
    var parent = $("#paymentSearchResultsTbl tbody").html("");
    
    function seachThePayments() {
    	$(".paymentSearchResultsWrapper > div").hide();
    	var filter = populatePaymentSearchFilter();
    	if (filter == null) {
    		$("#paymentSearchResultsTbl tbody").html("");
    		return;
    	}
        search(filter);
    }
    $("a.jsSearchPayment").on("click", function(event) {
    	currentPaymentPageNumber = 1;
    	seachThePayments();
    });

    $("a.jsResetSearchFilter").on("click", function(event) {

    	resetSearchFilter();
    });
    
    getAllApplicationDesignation();
    
    
	$("a.jsCopyPaymentsToClipboard").on("click", function(event) {
	    if ($(this).hasClass("priBtnDisabled")) {
    		return;
    	}
	});

	$("input.psAmount").on("keyup", function() {
		var value = $(this).val();
		value = value.replace(/[^0-9\.]/g, '');
		$(this).val(value);
	});

    $("a.jsAddPayment").on("click", function(event) {
    	if ($(this).hasClass("priBtnDisabled")) {
    		return;
    	}
    	//var entity = $("tr.highlighteddisabled").data("data");

    	var currentDate = $.datepicker.formatDate( "DD, MM d, yy", new Date());
    	$("span.datenote").html('');
		$("span.datenote").html("Julian Date = 035<br>" + currentDate);

		var entity = $("tr.highlighted").data("data");
    	if (entity == null) {
    		return;
    	}


    	//console.log(entity.claimantNumber);
    	// set the non-editable fields
        $('div.currentAccountInfo div.fLeft div.accountField p.fieldVal').eq(0).text(entity.claimant);
        $('div.currentAccountInfo div.fLeft div.accountField p.fieldVal').eq(1).text(entity.claimNumber);
		$('div.currentAccountInfo div.fRight div.accountField p.fieldVal').eq(0).text(formatNotificationDate(entity.accountHolderBirthdate));
		$('div.currentAccountInfo div.fRight div.accountField p.fieldVal').eq(1).text("$" + (entity.accountBalance!=null?entity.accountBalance.toFixed(2):"0.00"));
		
		// set the editable fields
		$('div.addPaymentPopup input[name="batchNum"]').val(entity.batchNumber);
		$('div.addPaymentPopup input[name="blockNum"]').val(entity.blockNumber);
		$('div.addPaymentPopup input[name="seqNum"]').val(entity.sequenceNumber);
		$('div.addPaymentPopup input[name="paymentAmount"]').val(entity.amount?entity.amount:"0.00");
		$('div.addPaymentPopup input[name="depositDate"]').val(formatNotificationDate(entity.depositDate));
		$('div.addPaymentPopup select[name="paymentApplyTo"]').find("option").each(function(index, item){
		    var data = $(item).data("data");
		    if (entity.applyTo != null && entity.applyTo.name == $(this).text()) {
		        $(item).closest("select").get(0).selectedIndex=index;
		    }
		});
		
		if (entity.paymentAppliance != null) {
			$(".radioWrapper input:radio").each(function(index, item) {
			    if ($(this).next().text() == entity.paymentAppliance.name) {
			    	$(this).prop("checked", true);
			    }
			});
		}

		showPopup(".addPaymentPopup ");
    });
    
    
    
    $(".paymentSearchResultsBtnWrapper .jsViewAccountBtn").on("click", function(event) {
    	var context = $('#context').val();
    	var payment = $("tr.highlighted").data("data");
    	if (payment.accountId != null) {
    		window.location.href = context + "/account/" + payment.accountId + "/detail";
    	} else {
    		alert("The payment has no account id attached");
    	}
    });

    $(".perPage select").on("change", function() {
    	currentPaymentPageNumber = 1;
    	seachThePayments();
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
            currentPaymentPageNumber = currentIdx;
            seachThePayments();
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
            currentPaymentPageNumber = currentIdx;
            seachThePayments();
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
            currentPaymentPageNumber = currentIdx;
            seachThePayments();
        }
    });
    
    
});

function renderPagination(totalCount) {
	var pageSize = $('.perPage select').val();
	var pageNumber = currentPaymentPageNumber;
	if (pageSize == 0) {
		pageNumber = 1;
		pageSize = totalCount;
	}
	var totalPageCount = totalCount == 0 ? 0 : (Math.floor((totalCount - 1) / pageSize) + 1);
	    var startCount = totalCount == 0 ? 0 : (pageNumber - 1) * pageSize + 1;
	    var endCount = pageNumber * pageSize;
	    if (endCount > totalCount) {
	        endCount = totalCount;
	    }
	    $('.paginationLabel').html("<span class='startCount'>" + startCount + "</span>-<span class='endCount'>"
	            + endCount + "</span> of <span class='totalCount'>" + totalCount + "</span>");

	    var pageHtml = "";
	    pageHtml += "<a href='javascript:;' class='toPrev";
	    if (pageNumber === 1) {
	        pageHtml += " toPrevDisabled";
	    }
	    pageHtml += "'>Prev</a>";
	    var beginPage = pageNumber - 2 - 1;
	    if (beginPage < 0) {
	    	beginPage = 0;
	    }
	    endPage = beginPage + 5;
	    for (var i = beginPage; i < totalPageCount && i < endPage; i++) {
	        pageHtml += "<a href='javascript:;' class='toPage";
	        if (pageNumber === i + 1) {
	            pageHtml += " current";
	        }
	        pageHtml += "'>" + (i + 1) + "</a>";
	    }
	    pageHtml += "<a href='javascript:;' class='toNext";
	    if (pageNumber === totalPageCount) {
	        pageHtml += " toNextDisabled";
	    }
	    pageHtml += "'>Next</a>";

	    $('.paginationLinks').html(pageHtml);
}

function clipboard() {
	$("a.jsCopyPaymentsToClipboard").zclip({
			path:rootUrl + "js/ZeroClipboard.swf",
			copy:function(){
				var data = "";
				$("tr.highlighted td").each(function(index, item){
				    data += $(item).text() + " ";
				});
				return data.trim();
			}
	});
}

// parse the format MM/dd/yyyy and return Date type
function parseDateMMddyyyy(format) {
	//var dateReg = /^((0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)?[0-9]{2})*$/;
	var dateReg = /^((0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.][1-9]{1}[0-9]{3})*$/;
	if (dateReg.test(format)) {
		var date = new Date();
		date.setFullYear(format.split("/")[2]);
		date.setMonth(parseInt(format.split("/")[0]) - 1);
		date.setDate(format.split("/")[1]);
		date.setHours(0);
		date.setMinutes(0)
		date.setSeconds(0)
		date.setMilliseconds(0);
		//console.log(formatNotificationDate(date));
		return date;
	}
	
	return null;
}

function shifftDate(date, offset) {
	var newDate = new Date(date.getTime() + offset * 1000*60*60*24);
	return newDate;
}

