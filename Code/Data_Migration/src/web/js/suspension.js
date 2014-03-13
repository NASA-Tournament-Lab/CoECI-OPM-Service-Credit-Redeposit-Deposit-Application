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

String.prototype.replaceAll = replaceAll;
function replaceAll(a,b){
    var raRegExp = new RegExp(a,"g");
    return this.replace(raRegExp,b)
}

// Bug Fix 2

function shortenVal(value, maxlength){

	if(!maxlength){
		maxlength = 15;
	}

	if(value && value.length > maxlength){
		return value.substr(0, maxlength)+' ...';
	} else{
		return value;
	}
}

function updateSuspendedPayments(payments) {
    $.ajax({
    url: rootUrl + "payment?resourceName=link",
    data: JSON.stringify(payments),
    async:false,
    contentType: 'application/json',
    //headers: { 'Accept': 'application/json'},
    type: "PUT",
    cache : false,
    success: function(data) {
        //result = data;
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to update the suspended payment.");
    }
    }
    );
    
}

function createSuspendedPayment(payment, accountId) {
    $.ajax({
    url: rootUrl + "payment?resourceName=link&accountId=" + accountId,
    data: JSON.stringify(payment),
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "POST",
    cache : false,
    success: function(data) {
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to create suspended payment.");
    }
    }
    );
    
}

function populateSuspendedPayment(row) {
	var validFlag = true;
	if (!$(row).hasClass("editing")) {
		return null;
	}
	var item = $(row).data("data");
	if (item == null) {
		item = {};
	} else {
		item = $.extend({}, $(row).data("data"));
	}
	
	var tds = $(row).find("td input,select");
	var paymentStatus = tds.eq(1).val();
	var ps = $(document.body).data("paymentStatuses");
	$(ps).each(function(index, it){
		if (it.name == paymentStatus) {
			item.paymentStatus = it;
		}
	});
	
	var accountStatus = tds.eq(10).val();
	var as = $(document.body).data("accountStatuses");
	$(as).each(function(index, it){
		if (it.name == accountStatus) {
			item.masterAccountStatus = it;
		}
	});
	
    
	var depositDate = parseDateMMddyyyy(tds.eq(2).val());
	if (depositDate == null || depositDate == 'Invalid Date') {
		
		$(".accountPanymentsWrapper input").removeClass("error");
		$(".accountPanymentsWrapper input").next().remove();
		alert("The deposit date must be 'mm/dd/yyyy'.");
		return null;
	} else if(depositDate > new Date()){
		alert("The deposit date can not be in the future.");
		return null;
	}
	item.depositDate = depositDate;
	
	var bbs = tds.eq(3).val();
	var bbsRegex = /^\d\d*[-]\d\d*[-]\d\d*$/;
	if (!bbsRegex.test(bbs)) {
		
		if (!$(".accountPanymentsWrapper input[name='batchNum']").hasClass("error")) {
			$(".accountPanymentsWrapper input[name='batchNum']").addClass("error").after('<span class="errorIcon"></span>');
			$(".accountPanymentsWrapper input[name='blockNum']").addClass("error").after('<span class="errorIcon"></span>');
			$(".accountPanymentsWrapper input[name='seqNum']").addClass("error").after('<span class="errorIcon"></span>');
			$(".accountPanymentsWrapper input[name='batchNum']").val("");
			$(".accountPanymentsWrapper input[name='blockNum']").val("");
			$(".accountPanymentsWrapper input[name='seqNum']").val("");
		}
		alert("The BltBlkSeq must be the digit format of X-Y-Z where X,Y,Z are number string. For example, 14-15-178.");
		return null;
	}
	item.batchNumber = bbs.split("-")[0];
	item.blockNumber = bbs.split("-")[1];
	item.sequenceNumber = bbs.split("-")[2];
	if ($(".accountPanymentsWrapper input[name='batchNum']").hasClass("error")) {
		$(".accountPanymentsWrapper input[name='batchNum']").removeClass("error");
		$(".accountPanymentsWrapper input[name='batchNum']").next().remove();
		$(".accountPanymentsWrapper input[name='blockNum']").removeClass("error");
		$(".accountPanymentsWrapper input[name='blockNum']").next().remove();
		$(".accountPanymentsWrapper input[name='seqNum']").removeClass("error");
		$(".accountPanymentsWrapper input[name='seqNum']").next().remove();
	}
	
	var csd = tds.eq(4).val();
	if (csd.trim().length >= 0 && isNaN(csd)) {
		
		if (!$(".accountPanymentsWrapper input[name='csdNum']").hasClass("error")) {
			$(".accountPanymentsWrapper input[name='csdNum']").addClass("error").after('<span class="errorIcon"></span>');
			$(".accountPanymentsWrapper input[name='csdNum']").val(shortenVal(csd));
		}
		alert("The CSD must be a number string.");
		return null;
	}
	item.claimNumber = csd;
	if ($(".accountPanymentsWrapper input[name='csdNum']").hasClass("error")) {
		$(".accountPanymentsWrapper input[name='csdNum']").removeClass("error");
		$(".accountPanymentsWrapper span.errorIcon").remove();
	}
	
	var ach = tds.eq(5).prop('checked');
	item.ach = ach;
	
	var numberRegex = /^[$]?\d\d*[.]?\d*$/;
	
	var amount = tds.eq(6).val();
	
	if (!numberRegex.test(amount)) {
		if (!$(".accountPanymentsWrapper input[name='pAmount']").hasClass("error")) {
			$(".accountPanymentsWrapper input[name='pAmount']").addClass("error").after('<span class="errorIcon"></span>');
			$(".accountPanymentsWrapper input[name='pAmount']").val(shortenVal(amount));
		}
		alert("The amount must be a non-negative number.");
		return null;
	}
	if (amount.length > 1 && amount.substring(0, 1) == "$") {
		item.amount = parseFloat(amount.substring(1, amount.length));
	} else {
		item.amount = parseFloat(amount);
	}
	
	if ($(".accountPanymentsWrapper input[name='pAmount']").hasClass("error")) {
		$(".accountPanymentsWrapper input[name='pAmount']").removeClass("error");
		$(".accountPanymentsWrapper input[name='pAmount']").next().remove();
	}
	
	
	var birthDate = parseDateMMddyyyy(tds.eq(7).val());
	if (tds.eq(7).val().length > 0 && (birthDate == null || birthDate == 'Invalid Date')) {
		
		if (!$(".accountPanymentsWrapper input[name='bDate']").hasClass("error")) {
			$(".accountPanymentsWrapper input[name='bDate']").addClass("error").after('<span class="errorIcon"></span>');
			$(".accountPanymentsWrapper input[name='bDate']").val(shortenVal(tds.eq(7).val()));
		}
		alert("The birth date must be 'mm/dd/yyyy'.");
		return null;
	} else if(birthDate > new Date()){
		alert("Nobody can be born in the future.");
		return null;
	}
	item.claimantBirthdate = birthDate;
	if ($(".accountPanymentsWrapper input[name='bDate']").hasClass("error")) {
		$(".accountPanymentsWrapper input[name='bDate']").removeClass("error");
		$(".accountPanymentsWrapper input[name='bDate']").next().remove();
	}
	
	var name = tds.eq(8).val();
	if (name.length == 0) {
		$(".accountPanymentsWrapper input").removeClass("error");
		$(".accountPanymentsWrapper span.errorIncon").remove();
		alert("Please input claimant name.");
		return null;
	}
	item.claimant = name;
	
	var balance = tds.eq(9).val();
	if (!numberRegex.test(balance)) {
		$(".accountPanymentsWrapper input").removeClass("error");
		$(".accountPanymentsWrapper input").next().remove();
		alert("The balance must be a non-negative number.");
		return null;
	}
	item.accountBalance = parseFloat(balance.substring(1, balance.length));
	if (balance.length > 1 && balance.substring(0, 1) == "$") {
		item.accountBalance = parseFloat(balance.substring(1, balance.length));
	} else {
		item.accountBalance = parseFloat(balance);
	}
	
	if ($(".accountPanymentsWrapper input[name='maBalance']").hasClass("error")) {
		$(".accountPanymentsWrapper input[name='maBalance']").removeClass("error");
		$(".accountPanymentsWrapper input[name='maBalance']").next().remove();
	}
	
	
    
	
	var payment = {};
	payment.id = item.paymentId;
	payment.paymentStatus = item.paymentStatus;
	if (payment.paymentStatus == null) {
		payment.paymentStatus = UNRESOLVED_PAYMENT_STATUS;
	}
	payment.transactionDate = item.transactionDate;
	payment.depositDate = item.depositDate;
	payment.batchNumber = item.batchNumber;
	payment.blockNumber = item.blockNumber;
	payment.sequenceNumber = item.sequenceNumber;
	payment.claimNumber = item.claimNumber;
	payment.ach = item.ach;
	payment.amount = item.amount;
	payment.claimantBirthdate = item.claimantBirthdate;
	payment.claimant = item.claimant;
	payment.accountBalance = item.accountBalance;
	payment.accountStatus = item.accountStatus;
	if (payment.accountStatus == null) {
		payment.accountStatus = ACTIVE_ACCOUNT_STATUS;
	}
	
	
	payment.masterClaimNumber = item.masterClaimNumber;
	payment.masterClaimantBirthdate = item.masterClaimantBirthdate;
	payment.masterAccountStatus = item.masterAccountStatus;
	payment.masterAccountBalance = item.masterAccountBalance;
	payment.note = item.paymentNote;
	
	payment.accountId = accountId;
	payment.paymentType = 'SUSPENDED_PAYMENT';
	payment.approvalStatus = "PENDING";
	
	if (payment.id == null) {
		var acc = $(document.body).data("data");
		payment.masterClaimNumber = acc.claimNumber;
	    payment.masterClaimantBirthdate = acc.claimantBirthdate;
	    payment.masterAccountStatus = acc.status;
	    payment.masterAccountBalance = acc.balance;
	    payment.note = item.paymentNote;
	    payment.accountStatus = acc.status;
	}
	
	$(row).data("data-new", item);
	return payment;
}

function getSuspendedPayments(accountId) {
	var url = rootUrl + "suspension?resourceName=link";
	if (accountId) {
		url = rootUrl + "suspension/" + accountId + "?resourceName=link";
	}
    $.ajax({
    url: url,
    async:true,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "GET",
    cache : false,
    success: function(data) {
        var parent = $("#suspenseTbl tbody");
    	parent.html("");
    	var rows = "";
    	var rowTemp = '<tr class="">' +
    			'<td class="blankCell firstCol">{0}</td>' +
    			'<td class="checkBoxCell"><input name="postChkbx" id="postChkbx{n}" type="checkbox" value="postChkbx{n}" disabled="disabled"></td>' +
    			'<td>{2}</td>' +
    			'<td class="dateCellDepositDate2">{3}</td>' +
    			'<td>{4}</td>' +
    			'<td>{5}</td>' +
    			'<td class="checkBoxCell"><input name="achChkbx" id="achChkbx{n}" type="checkbox" value="achChkbx{n}" disabled="disabled"></td>' +
    			'<td>{7}</td>' +
    			'<td class="dateCellBirthDate2 hasError">{8}</td>' +
    			'<td>{9}</td>' +
    			'<td>{10}</td>' +
    			'<td class="lastCol">{11}</td></tr>';
        $.each(data, function(index, item) {
        	var row = rowTemp
        	.replace("{0}", "&nbsp;")
        	//.replace("{1}", item.paymentType)
        	.replace("{2}", item.paymentStatus.name)
        	.replace("{3}", formatNotificationDate(item.depositDate))
        	.replace("{4}", item.batchNumber + "-" + item.blockNumber + "-" + item.sequenceNumber)
        	.replace("{5}", item.claimNumber?item.claimNumber:"")
        	//.replace("{6}", item.accountId)
        	.replace("{7}", numFormat(item.amount))
        	.replace("{8}", formatNotificationDate(item.claimantBirthdate))
        	.replace("{9}", item.claimant)
        	.replace("{10}", numFormat(item.accountBalance))
        	.replace("{11}", item.masterAccountStatus.name)
        	.replaceAll("{n}", (index + 1));
        	
        	if (item.paymentStatus.name == "Unresolved" || item.paymentStatus.name == 'Suspended') {
        		row = row.replace('disabled="disabled"', "");
        	}
        	rows += row;
        });
        
        $(rows).appendTo(parent);
        
        parent.find("tr").each(function(index, item) {
        	$(item).find('input[name="achChkbx"]').prop("checked", data[index].ach);
        	$(item).data("data", data[index]);
        	
        	
        	$(item).on("click", function() {
        		
        	    var data = $(this).data('data');
        	    if (data.claimNumber == null) {
        	    	$(".jsMakeThisEmpCur").addClass("priHighBtnDisabled");
        	    }
        	    
        	    if (data.paymentStatus.name == 'Unresolved' || data.paymentStatus.name == 'Suspended') {
        	    	$(".jsResetSelectedPaymentSuspense").addClass("priBtnDisabled");
        	    	$(".jsLinkPaymentToCurrent").removeClass("priHighBtnDisabled");
        	    	$(".jsTransferSuspense").removeClass("priHighBtnDisabled");
        	    } else {
        	    	$(".jsResetSelectedPaymentSuspense").removeClass("priBtnDisabled");
        	    	
        	    	$(".jsLinkPaymentToCurrent").addClass("priHighBtnDisabled");
        	    	$(".jsTransferSuspense").addClass("priHighBtnDisabled");
        	    }
        	    
        	    $("textarea.paymentNotes").val(data.paymentNote);
        	    
        	    var account = $(document.body).data("data");
        	    //setPaymentTransactionDetails(data, account);
        	    loadPaymentTransactionDetailsForSuspension($(this));
        	    
        	    var tbl = $(this).parents("table").eq(0);
        	    if (!$(this).hasClass("editing")){
        	    	if ($("#suspenseTbl tr:last").hasClass("editing") && $("#suspenseTbl tbody tr:last").hasClass("newRow")) {
		    	    	$("#suspenseTbl tr:last").remove();
		    	    } else {
		    	    	var editRow = tbl.find("tr.editing");
	                	var paymentItem = populateSuspendedPayment(editRow);
	                	if (paymentItem != null) {
		                	if (paymentItem.id != null) {
		                		updateSuspendedPayments([paymentItem]);
		                		saveSuspensePaymentRow(editRow);
		                		setAccountInfo(getAccountById(accountId));
		                	} else {
		                		createSuspendedPayment(paymentItem, accountId);
		                		saveSuspensePaymentRow(editRow);
		                		setAccountInfo(getAccountById(accountId));
		                	}
		                	editRow.data("data", editRow.data("data-new"));
		                	editRow.data("validPayment", "true");
	                	} else if (editRow.length > 0 && paymentItem == null) {
	                		editRow.data("validPayment", "false");
	                	}
		    	    }

                }
        	});
        });
        
        var firstRow = parent.find("tr:first");
        firstRow.addClass('highlighted');
        loadPaymentTransactionDetailsForSuspension(firstRow);
        
        var account = $(document.body).data("data");
        if (firstRow.length > 0) {
	        setPaymentTransactionDetails(firstRow.data('data'), account);
	        
	        $("textarea.paymentNotes").val(firstRow.data('data').paymentNote);
	        if (firstRow.find('td').eq(2).text() != 'Suspended') {
	        	$(".jsResetSelectedPaymentSuspense").removeClass("priBtnDisabled");
	        }
        }

        manualSort($('#suspenseTbl'));
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to get suspended payments.");
    }
    }
    );
}

function postPayments(paymentIds) {
	var result;
    $.ajax({
    url: rootUrl + "suspension/post?resourceName=link&paymentIds=" + paymentIds,
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "POST",
    cache : false,
    success: function(data) {
        result = data;
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to post payments.");
    }
    }
    );
    
    return result;
}

function resetPayment(paymentId) {
	var res;
    $.ajax({
    url: rootUrl + "suspension/" + paymentId + "/reset?resourceName=link",
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "POST",
    cache : false,
    success: function(data) {
        res = data;
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to reset payments.");
    }
    }
    );
    
    return res;
}

function getAllTransferTypes() {
    $.ajax({
    url: rootUrl + "lookup/transferTypes?resourceName=link",
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "GET",
    cache : false,
    success: function(data) {
    	var ul = $(".tranferTypeWrapper ul");
        var temp = '<li><input name="tpType" type="radio" value="tpType{n}" id="tpType{n}"> ' +
        		'<label for="tpType{n}">{name}</label></li>';
        		
        ul.html("");
        var li = "";
        $.each(data, function(index, item){
        	$(temp.replace("{name}", item.name).replaceAll("{n}", index + 1)).appendTo(ul);
        	//ul.last("li").find("input").data("data", item);
        });
        ul.find("input").each(function(index, item) {
            $(item).data("data", data[index]);
        });
        ul.find("input:first").prop("checked", true);
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to get transfer types.");
    }
    }
    );
}

function transferPayment(paymentId, transferTypeId, refund) {
    $.ajax({
    url: rootUrl + "suspension/" + paymentId + "/transferPayment?resourceName=link&transferTypeId=" + transferTypeId + "&refund=" +refund,
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "POST",
    cache : false,
    success: function(data) {
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to transfer payment.");
    }
    }
    );
}

function getAccountById(accountId) {
	var account;
    $.ajax({
    url: rootUrl + "suspension/getAccountById?resourceName=link&accountId=" + accountId,
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "GET",
    cache : false,
    success: function(data) {
        account = data;
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to get account.");
    }
    }
    );
    
    return account;
}

function setCurrentAccountId(accountId) {
    $.ajax({
    url: rootUrl + "payment/setAccountId?resourceName=link&accountId=" + accountId,
    async:false,
    type: "GET",
    cache : false,
    success: function(data) {
        
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to set the account.");
    }
    }
    );
}

function setAccountInfo(account) {
	var entity = account;
	if (entity == null) {
		return;
	}
	var total = 0.00;
	var suspendedPaymentAccount = 0;
	if (entity.paymentHistory) {
		for (var i = 0; i < entity.paymentHistory.length; ++i) {
			if (entity.paymentHistory[i].amount && entity.paymentHistory[i].paymentType == 'SUSPENDED_PAYMENT') {
				total += entity.paymentHistory[i].amount;
				suspendedPaymentAccount++;
			}
		}
	}
	total = total.toFixed(2);
	
	$('div.accountSummaryPanel.group div.column.column1 div.fieldRow p.fieldVal').eq(0).text(entity.claimNumber);
	$('div.accountSummaryPanel.group div.column.column1 div.fieldRow p.fieldVal').eq(1).text(formatNotificationDate(entity.claimantBirthdate));
	$('div.accountSummaryPanel.group div.column.column1 div.fieldRow p.fieldVal').eq(2).text(entity.holder?entity.holder.ssn:"");
	$('div.accountSummaryPanel.group div.column.column1 div.fieldRow p.fieldVal').eq(3).text(entity.planType);
	$('div.accountSummaryPanel.group div.column.column2 div.fieldRow p.fieldTitle').eq(0).text(entity.status?entity.status.name:"Unknown");
	$('div.accountSummaryPanel.group div.column.column2 div.fieldRow p.fieldVal').eq(0).text(entity.lastAction?formatNotificationDate(entity.lastAction):"");// last action name here
	$('div.accountSummaryPanel.group div.column.column2 div.fieldRow p.fieldVal').eq(1).text(entity.lastActionDate?formatNotificationDate(entity.lastActionDate):"");
	$('div.accountSummaryPanel.group div.column.column3 div.fieldRow p.fieldVal').eq(0).text("$" + total);
	$('div.accountSummaryPanel.group div.column.column3 div.fieldRow p.fieldVal').eq(1).text("$" + (entity.balance?entity.balance:0));
	$('div.accountSummaryPanel.group div.column.column4 div.fieldRow p.fieldVal').eq(0).text(entity.frozen?"Yes":"No");
	$('div.accountSummaryPanel.group div.column.column4 div.fieldRow p.fieldVal').eq(1).text(entity.grace?"Yes":"No");
	$('div.accountSummaryPanel.group div.column.column3 h4 span').text(suspendedPaymentAccount);
	
	$('div.panelBody div.accountSummaryPanel.group div.column.column1 h4').text(entity.holder?(entity.holder.firstName + " " + entity.holder.lastName):"No holder");
	
}

function setPaymentTransactionDetails(payment, account) {
	if (payment == null) {
		return;
	}
	$('div.accountPanymentsWrapper input[name="csdNum"]').val(shortenVal(payment.claimNumber));

	$('div.accountPanymentsWrapper input[name="bDate"]').val(shortenVal(payment.claimantBirthdate?formatNotificationDate(payment.claimantBirthdate):""));

	$('div.accountPanymentsWrapper input[name="tDate"]').val(shortenVal(formatNotificationDate(payment.transactionDate)));
	
	
	$('div.accountPanymentsWrapper input[name="scmName"]').val(shortenVal(account.holder.firstName + " " + account.holder.lastName));

	$('div.accountPanymentsWrapper input[name="pStatus"]').val(shortenVal(payment.paymentStatus.name));

	$('div.accountPanymentsWrapper input[name="scmcsdNum"]').val(shortenVal(payment.masterClaimNumber));

	$('div.accountPanymentsWrapper input[name="scmbDate"]').val(shortenVal(formatNotificationDate(payment.masterClaimantBirthdate)));

	$('div.accountPanymentsWrapper input[name="scmStatus"]').val(shortenVal(payment.masterAccountStatus?payment.masterAccountStatus.name:"Unknown"));

	$('div.accountPanymentsWrapper input[name="maBalance"]').val(shortenVal(payment.masterAccountBalance?"$" + parseFloat(payment.masterAccountBalance).toFixed(2):"$0.00"));

	$('div.accountPanymentsWrapper input[name="pAmount"]').val(shortenVal(payment.amount?"$" + parseFloat(payment.amount).toFixed(2):"$0.00"));
	
	$(".accountPanymentsWrapper input[name='batchNum']").val(shortenVal(payment.batchNumber));

    $(".accountPanymentsWrapper input[name='blockNum']").val(shortenVal(payment.blockNumber));

    $(".accountPanymentsWrapper input[name='seqNum']").val(shortenVal(payment.sequenceNumber));
}

function savePaymentNote(paymentId, note) {
    $.ajax({
    url: rootUrl + "payment/" + paymentId + "/note?resourceName=link&note=" + note,
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "POST",
    cache : false,
    success: function(data) {
        
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to save payment note.");
    }
    }
    );
}


function linkPaymentToEmployee(paymentId, accountId) {
    $.ajax({
    url: rootUrl + "suspension/" + paymentId + "/linkToEmployee?resourceName=link&accountId=" + accountId,
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "POST",
    cache : false,
    success: function(data) {
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to link payment to employee.");
    }
    }
    );
}


function getAccountByCsd(claimNumber) {
	var account = null;
    $.ajax({
    url: rootUrl + "suspension/getAccountByCsd?resourceName=link&claimNumber=" + claimNumber,
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "GET",
    cache : false,
    success: function(data) {
        account = data;
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to get account by CSD:" + claimNumber+'.');
    }
    }
    );
    
    return account;
}

function getAllAccountStatuses() {
    $.ajax({
    url: rootUrl + "lookup/accountStatuses?resourceName=link",
    async:false,
    contentType: 'application/json',
    headers: { 'Accept': 'application/json'},
    type: "GET",
    cache : false,
    success: function(data) {
    	$(document.body).data("accountStatuses", data);
    	
        var select = '<select name="accountStatuses">';
        $.each(data, function(index, item) {
        	select += '<option value="' + item.name + '">' + item.name + '</option>';
        });
        select += "</select>";
        $(document.body).data("accountStatusSelect", select);
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to get account statuses.");
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
    success: function(data) {
        $(document.body).data("paymentStatuses", data);
    	
        var select = '<select name="paymentStatuses">';
        $.each(data, function(index, item) {
        	select += '<option value="' + item.name + '">' + item.name + '</option>';
        });
        select += "</select>";
        $(document.body).data("paymentStatusSelect", select);
    },

    error:function(jqXHR, textStatus,errorThrow){
        alert("Failed to get payment statuses.");
    }
    }
    );
}

var SUSPENDED_PAYMENT_STATUS = {id:31,name:'Suspended'};
var UNRESOLVED_PAYMENT_STATUS = {id:32, name:'Unresolved'};
var ACTIVE_ACCOUNT_STATUS = {id:1, name:'Active'};
$(document).ready(function() {
	// Setup page
    setupPage('SUSPENSE', 4);
    
	if (accountId == -1) {
		//$("#suspensePage").hide();
		// Bug Fix 2 No Account Selected
		$("#suspensePage .panelHeader h3.panelTitle").text("No Account is selected. Please select an account in Payments tab first. Then visit this tab.");
		$(".suspensePage .panelHeader").show();
		$("#suspensePage .panelBody").hide();
		
		
		$("#suspensePage .suspenseTblBtnWrapper").hide();
		$(".accountPanymentsWrapper").hide();
		return;
		
	}
	var account = getAccountById(accountId);
	
	$(document.body).data("data", account);
	getAllPaymentStatuses();
	getAllAccountStatuses();
    setAccountInfo(account);
    getSuspendedPayments(accountId);
    getAllTransferTypes();
    
	$("a.jsRefreshSuspense").on("click", function(event) {
	    getSuspendedPayments(accountId);
	});

	
	$("a.jsPostCheckedPaymentsSuspense").on("click", function(event) {
		var ids = [];
		$('#suspenseTbl tr input[name="postChkbx"]:checked').each(function(index, item) {
			if ($(item).attr("id") != null) {
			    var data = $(item).closest("tr").data("data");
		        ids[index] = data.paymentId;
			}
		});
	    
		var result = postPayments(ids);
		
		$('#suspenseTbl tr input[name="postChkbx"]:checked').each(function(index, item) {
			if ($(item).attr("id") != null) {
			    var data = $(item).closest("tr").data("data", result[index]);
			}
		});
		
		$('#suspenseTbl tr input[name="postChkbx"]:checked').prop('checked', false).attr("disabled", true);
		$(".jsResetSelectedPaymentSuspense").removeClass("priBtnDisabled");
	});
	
	
	$(".jsResetSelectedPaymentSuspense").off("click");
	$("a.jsResetSelectedPaymentSuspense").on("click", function(event) {
		if ($("#suspenseTbl tr.editing").length > 0) {
			$(this).addClass("priBtnDisabled");
			return;
		}
		if (!($(this).hasClass("priBtnDisabled"))){
			var data = $("#suspenseTbl tr.highlighted").data('data');
			var result = resetPayment(data.paymentId);
			setTableRowForSuspension($("#suspenseTbl tr.highlighted"), result);
			$("#suspenseTbl tr.highlighted").data("data", result);
		    $('#suspenseTbl tr input[name="postChkbx"]:checked').prop('checked', false).attr("disabled", false);
		    
		    $(".jsLinkPaymentToCurrent").removeClass("priHighBtnDisabled");
		    $(".jsTransferSuspense").removeClass("priHighBtnDisabled");
	    
            $(this).addClass("priBtnDisabled");
            var row = $("#suspenseTbl tbody tr.highlighted");
            $("input[name='postChkbx']", row).prop('checked', false).attr("disabled", false);
        }
	});
	
	
	$("a.jsDoTransferSuspense").on("click", function(event) {
	     var ul = $(".tranferTypeWrapper ul");
	     var transfer = ul.find("input:checked").data("data");
	     var data = $("#suspenseTbl tr.highlighted").data('data');
	     if (transfer.id != 1) {
	         transferPayment(data.paymentId, transfer.id, false);
	     }
	});

    
	$("a.jsAlertOpenDoc").on("click", function(event) {
		var ul = $(".tranferTypeWrapper ul");
	    var transfer = ul.find("input:checked").data("data");
	    var data = $("#suspenseTbl tr.highlighted").data('data');
	    if (data.claimNumber == null) {
	    	alert("The payment's claimNumber is reset, can not do the transfer for the refund.");
	    	return;
	    }
	    if ($(this).text() == 'Yes') {
	    	transferPayment(data.paymentId, transfer.id, true);
	    } else {
	    	transferPayment(data.paymentId, transfer.id, false);
	    }
	});
	
	
	$(".jsSaveNoteSuspense").off('click');
	$("a.jsSaveNoteSuspense").on("click", function(event) {
		var data = $("#suspenseTbl tr.highlighted").data('data');
		var note = $("textarea.paymentNotes").val();
		if (note != null && note.length > 255) {
			alert("The note length must be < = 255.");
			return;
		} else if($.trim(note) == ''){
			alert("Enter a non empty note.");
			return;
		}
		if (data == null) {
			alert("Select an non-editing payment to save note.");
			return;
		}
	    savePaymentNote(data.paymentId, note);
	    data.paymentNote = note;
	    showPopup(".savePaymentNotesSuccessPopup");
	});
	
	
	$("a.jsDiscardNoteChangesSuspense").on("click", function(event) {
	     var data = $("#suspenseTbl tr.highlighted").data('data');
	     if (data == null) {
			return;
		}
	     $("textarea.paymentNotes").val(data.paymentNote);
	});
	
    $("a.jsLinkPaymentToCurrent").off('click');
	$("a.jsLinkPaymentToCurrent").on("click", function(event) {
		if (!($(this).hasClass("priHighBtnDisabled"))){
			var payment = $("#suspenseTbl tr.highlighted").data('data');
			if (account.id == null) {
				alert("Can not link to the current account.");
				return;
			}
			linkPaymentToEmployee(payment.paymentId, account.id);
			
			payment.claimNumber = account.claimNumber;
			payment.claimantBirthdate = account.claimantBirthdate;
			payment.masterClaimNumber = account.claimNumber;
			payment.masterClaimantBirthdate = account.claimantBirthdate;
			payment.masterAccountStatus = account.status;
			payment.masterAccountBalance = account.balance;
			
			setPaymentTransactionDetails(payment, account);
			setTableRowForSuspension($("#suspenseTbl tr.highlighted"), payment);
			setAccountInfo(account);
			
			//$("#suspenseTbl tr.highlighted td").eq(2).text("Ready to Link to" + payment.claimNumber);
			$("#suspenseTbl tr.highlighted td").eq(5).text(payment.claimNumber);
			$("#suspenseTbl tr.highlighted td").eq(8).text(formatNotificationDate(payment.claimantBirthdate));
			$("#suspenseTbl tr.highlighted td").eq(9).text(payment.claimant);
			$("#suspenseTbl tr.highlighted td").eq(10).text(payment.accountBalance?"$" + payment.accountBalance.toFixed(2):"$0.00");
			$("#suspenseTbl tr.highlighted td").eq(11).text(payment.masterAccountStatus.name);
			
			var selectedRow = $("#suspenseTbl tr.highlighted");
            $("input[name='postChkbx']", selectedRow).prop('checked', true);
            $(".jsPostCheckedPaymentsSuspense").removeClass("priBtnDisabled");
		}
	});
	
	
	$("a.jsMakeThisEmpCur").on("click", function(event) {
	    var csd = $('div.accountPanymentsWrapper input[name="csdNum"]').val();
	    if (csd.trim().length == 0) {
	    	alert("Can not make the current account for empty csd number.");
	    	return;
	    }
	    account = getAccountByCsd(csd);
	    if (account == null || account.id == null) {
	    	alert("No account found for the claimNumber:" + csd+'.');
	    	return;
	    }
	    setCurrentAccountId(account.id);
	    setAccountInfo(account);
	    
	});

	$("#suspenseTbl tfoot td").off('click');
	$("#suspenseTbl tfoot td").click(function(){
    	var tbl = $(this).parents("table").eq(0);
    	var editRow = tbl.find("tr.editing");
    	if (editRow.length > 0) {
    		var paymentItem =  populateSuspendedPayment(editRow);
    		if (paymentItem == null) {
    			return;
    		} else{
    			if (editRow.hasClass("newRow")) {
    				createSuspendedPayment(paymentItem, accountId);
    				setAccountInfo(getAccountById(accountId));
    			} else {
    				updateSuspendedPayments([paymentItem]);
    				setAccountInfo(getAccountById(accountId));
    			}
    			
		        saveSuspensePaymentRow(editRow);
		        
		        var newItem = editRow.data("data-new");
		        editRow.data("data", newItem);
    		}
    	}
    	manualSort($('#suspenseTbl'));
	    
        var newRow = $(this).parent().clone();
        newRow.find("td").eq(2).html($(document.body).data("paymentStatusSelect"));
        newRow.find("td").eq(2).find('select').val("Unresolved");
        newRow.find("td").eq(11).html($(document.body).data("accountStatusSelect"));
        newRow.find("td").eq(3).text("02/05/2014");
		newRow.find("td").eq(8).text("02/07/2014");
        
        newRow.addClass("newRow");
        var tbl = $(this).parents("table").eq(0);
        $("tbody", tbl).append(newRow);
        loadPaymentTransactionDetailsForSuspension(newRow);
        $("textarea.paymentNotes").val("");
        
        $(".jsMakeThisEmpCur").addClass("priHighBtnDisabled");
        editSuspensePaymentRow(newRow);
        $(".jsLinkPaymentToCurrent, .jsTransferSuspense, .jsLinkPaymentToCurrent").addClass("priHighBtnDisabled");
    });
    
    $("#suspenseTbl tbody td").off('click');
    $("#suspenseTbl").delegate("tbody td", "click", function(){
    	    if ($("#suspenseTbl tr:last").hasClass("editing") && $("#suspenseTbl tr:last").hasClass("newRow")) {
    	    	if (!$(this).parents("tr").hasClass("editing")) {
    	    	    $("#suspenseTbl tr:last").remove();
    	    	}
    	    }
    		if ($("#suspenseTbl tr.editing").data("validPayment") == 'false') {
			    return;
		    }
		
            var row = $(this).parent();
            
            if (!($(this).parent().hasClass("editing"))){
            	
                var tbl = $(this).parents("table").eq(0);
                
                if (! row.hasClass("editing")){
                    //saveSuspenseRow($(".editing", tbl));
                	//saveSuspensePaymentRow(row);
                }
                if (row.hasClass("highlighted") && (!$(this).hasClass("checkBoxCell")) ){
                    editSuspensePaymentRow(row);
                }else{
                    $("#suspenseTbl tr").removeClass("highlighted");
                    row.addClass("highlighted");
                    loadPaymentTransactionDetails(row);
                    manualSort($('#suspenseTbl'));
                }
            }
            if ($("input[name='postChkbx']:disabled", row).is(':disabled')){
                $(".jsResetSelectedPaymentSuspense").removeClass("priBtnDisabled");
            }else{
                //$(".jsResetSelectedPaymentSuspense").addClass("priBtnDisabled");
            }

            
    });
        
	$(".statusInfoBar .jsShowStatusInfoPopup").off('click');
});

function setTableRowForSuspension(row, item) {
	$(row).find("td").eq(2).text(item.paymentStatus.name);
	$(row).find("td").eq(3).text(formatNotificationDate(item.depositDate));
	$(row).find("td").eq(4).text(item.batchNumber + "-" + item.blockNumber + "-" + item.sequenceNumber);
	$(row).find("td").eq(5).text(item.claimNumber?item.claimNumber:"");
	$(row).find("td").eq(6).find("input").prop("checked", item.ach);
	$(row).find("td").eq(7).text(item.amount?"$" + item.amount.toFixed(2):"$0.00");
	$(row).find("td").eq(8).text(formatNotificationDate(item.claimantBirthdate));
	$(row).find("td").eq(9).text(item.claimant);
	$(row).find("td").eq(10).text(item.accountBalance? "$" + item.accountBalance.toFixed(2):"$0.00");
	$(row).find("td").eq(11).text(item.masterAccountStatus.name);
}


function loadPaymentTransactionDetailsForSuspension(row) {
	var cells = $(row).find("td");
    var pStatus = cells.eq(2).find("select").val();
    if (cells.eq(2).find("select").length <= 0) {
    	pStatus = cells.eq(2).text();
    }

    var tDate = cells.eq(3).text();
    var batBlkSeq = cells.eq(4).text();
    var tmp = batBlkSeq.split("-");
    var bat = tmp[0];
    var blk = tmp[1];
    var seq = tmp[2];
    var csd = cells.eq(5).text();
    var pAmount = cells.eq(7).text();
    var bDate = cells.eq(8).text();
    var scmName = cells.eq(9).text();
    var bAmount = cells.eq(10).text();
    var aStatus = cells.eq(11).find("select").val();
    if (cells.eq(11).find("select").length <= 0) {
    	aStatus = cells.eq(11).text();
    }
    
    $(".accountPanymentsWrapper input[name='batchNum']").val(shortenVal(bat));

    $(".accountPanymentsWrapper input[name='blockNum']").val(shortenVal(blk));

    $(".accountPanymentsWrapper input[name='seqNum']").val(shortenVal(seq));
    
    $(".accountPanymentsWrapper input[name='csdNum']").val(shortenVal(csd));

    $(".accountPanymentsWrapper input[name='bDate']").val(shortenVal(bDate));

    $(".accountPanymentsWrapper input[name='pStatus']").val(shortenVal(pStatus));

    $(".accountPanymentsWrapper input[name='pAmount']").val(shortenVal(pAmount));
    $(".accountPanymentsWrapper input[name='tDate']").val(shortenVal(tDate));
    $(".accountPanymentsWrapper input[name='scmName']").val(shortenVal(scmName));
    
    
    $(".accountPanymentsWrapper input[name='scmStatus']").val(shortenVal(aStatus));
    $(".accountPanymentsWrapper input[name='maBalance']").val(shortenVal(bAmount));
    
    var payment = $(row).data("data");
    if (payment != null) {
	    $(".accountPanymentsWrapper input[name='scmcsdNum']").val(shortenVal(payment.masterClaimNumber));
	    $(".accountPanymentsWrapper input[name='scmbDate']").val(shortenVal(formatNotificationDate(payment.masterClaimantBirthdate)));
    } else {
    	$(".accountPanymentsWrapper input[name='scmcsdNum']").val("");
	    $(".accountPanymentsWrapper input[name='scmbDate']").val("");
    }
    
    if ( pStatus != null && pStatus.indexOf('Pending Approval') > -1 ){
        $(".jsTransferSuspense, .jsLinkPaymentToCurrent").addClass("priHighBtnDisabled");
    }
    if  ( pStatus === "" ){
        $(".jsLinkPaymentToCurrent, .jsMakeThisEmpCur, .jsTransferSuspense").addClass("priHighBtnDisabled");
    }
}

function numFormat(val) {
	if (val == null) {
		return "$0.00";
	}
	var value = val + "";
	if (value.trim().length == 0) {
		return "$0.00";
	}
	
	if (value.substring(0, 1) == "$") {
		value = value.substring(1, value.length);
	}
	
	return "$" + parseFloat(value).toFixed(2);
}
function saveSuspensePaymentRow(row){
	var i = 0;
    $("td", row).each(function() {
        if (row.hasClass("newRow")){
            var cells = row.find("td");
            cells.eq(2).html(cells.eq(2).find("select").val());
            cells.eq(3).html(cells.eq(3).find("input").val());
            cells.eq(4).html(cells.eq(4).find("input").val());
            cells.eq(5).html(cells.eq(5).find("input").val());
            cells.eq(7).html(numFormat(cells.eq(7).find("input").val()));
            cells.eq(8).html(cells.eq(8).find("input").val());
            cells.eq(9).html(cells.eq(9).find("input").val());
            cells.eq(10).html(numFormat(cells.eq(10).find("input").val()));
            cells.eq(11).html(cells.eq(11).find("select").val());
            row.removeClass("newRow");
            $("input[type='checkbox']", row).attr("disabled", false);
            $(".jsPostCheckedPaymentsSuspense").removeClass("priBtnDisabled");
        }else{
            if ((!$(this).hasClass("checkBoxCell")) && (!$(this).hasClass("blankCell"))){
            	if ($(this).find("input").length > 0 ) {
	                var cellVal = $("input.text", $(this)).val();
	                if (i == 7 || i == 10) {
	                	$(this).html(numFormat(cellVal));
	                } else {
	                	$(this).html(cellVal);
	                }
	                
            	} else if ($(this).find("select").length > 0) {
            		 var cellVal = $("select", $(this)).val();
	                $(this).html(cellVal);
            	}
            }
        }
        
        if (i == 6) {
        	$(this).find("input").attr("disabled", "disabled");
        }
        i++;
    });
    row.removeClass("editing");
    
}

function editSuspensePaymentRow(row){
    var tbl = row.parents("table").eq(0);
    $("tr", tbl).removeClass("highlighted").removeClass("hovered");;
    var currentEditingRow = tbl.find(".editing");
    saveSuspensePaymentRow(currentEditingRow);
    row.addClass("editing");
    var i = 0;
    $("td", row).each(function() {
        var w = parseInt($(this).width(), 10) -16;
        if ((!$(this).hasClass("checkBoxCell")) && (!$(this).hasClass("blankCell"))){
            var cellVal = $(this).text();
            if ($(this).hasClass("dateCellBirthDate2")){
                $(this).html('<input type="text" value="' + cellVal + '" class="text birthDate2" style="width:' + (w-6) +  'px"/>');
            }else if ($(this).hasClass("dateCellDepositDate2")){
                $(this).html('<input type="text" value="' + cellVal + '" class="text depositDate2" style="width:' + (w-6) +  'px"/>');
            }else{

            	var data = $(row).data("data");
            	var as;
            	var ps;
            	if (data != null) {
            		as = data.masterAccountStatus;
            		ps = data.paymentStatus;
            	}
            	if ($(this).hasClass("lastCol")) {
            		var select = $(document.body).data("accountStatusSelect");
            		$(this).html(select);
            		if (as != null) {
            		    $(this).find("select").val(as.name);
            		}
            	} else if (i == 2) {
            		var select = $(document.body).data("paymentStatusSelect");
            		$(this).html(select);
            		if (ps != null) {
            			$(this).find("select").val(ps.name);
            		} else {
            			$(this).find("select").val("Unresolved");
            		}
            	} else {
            	    $(this).html('<input type="text" value="' + cellVal + '" class="text" style="width:' + w +  'px"/>');
            	}
            }
        }
        if (i == 6) {
        	var subHtml = $(this).html();
        	var data = $(this).parent("tr").data("data");
        	$(this).html(subHtml.replace('disabled="disabled"', ""));
        	if (data != null) {
        	    $(this).find("intput").prop("checked", data.ach);
        	} else {
        		 $(this).find("intput").prop("checked", false);
        	}
        }
        i++;
    });
    if (row.hasClass("newRow")){
        $("input[type='checkbox']", row).prop('checked', true).attr("disabled", true);
    }
    $(".datePicker", row).datepicker({
        showOn: "button",
        buttonImage: "i/calendar.png",
        changeMonth: true,
        changeYear: true,yearRange: "-100:+1",
        buttonImageOnly: true
    });

    //datepicker
    $(".birthDate2", row).datepicker({
        showOn: "button",
        buttonImage: "i/calendar.png",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true, yearRange: "-100:+1",
        buttonText: "Enter the date of birth"
    });

    //datepicker
    $(".depositDate2", row).datepicker({
        showOn: "button",
        buttonImage: "i/calendar.png",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true, yearRange: "-100:+1",
        buttonText: "Enter the date of deposit"
    });
    
    
}



// parse the format MM/dd/yyyy and return Date type
function parseDateMMddyyyy(format) {
	var dateReg = /^((0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)?[0-9]{2})*$/;
	if (dateReg.test(format)) {
		var date = new Date();
		date.setFullYear(format.split("/")[2]);
		date.setMonth(parseInt(format.split("/")[0]) - 1);
		date.setDate(format.split("/")[1]);
		date.setHours(0);
		date.setMinutes(0)
		date.setSeconds(0)
		date.setMilliseconds(1);
		
		//console.log(formatNotificationDate(date));
		return date;
	}
	
	return null;
}
