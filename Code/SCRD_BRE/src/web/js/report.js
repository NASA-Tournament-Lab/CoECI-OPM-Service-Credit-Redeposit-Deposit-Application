var currentReportTab = "reportsPage";
var openerCleanup;
var gPreviousReportNames = {
		correspondencePage : 'LettersLibrary',
		referencePage : 'ReportsLibrary',
		reportsPage : 'ReportsLibrary'
};

function gotoPreviousReport() {
	gPreviousReportName = gPreviousReportNames[currentReportTab];
	var item = null;
	$(".leftTreeWrapper .displayTree li a", $('#' + currentReportTab)).each(function() {
		if ($(this).attr('data-report-name') == gPreviousReportName) {
			item = $(this);
		}
	});
	if (item == null) {
		item = $(".leftTreeWrapper .displayTree li a", $('#' + currentReportTab)).first();
	}
	item.click();
}
$(document).ready(function() {
	
	setupPage('REPORTS', 3);
	
	// create the tree
	createTreeView();
	
	//reports page edit button
	$(".jsEditReport").click(function(){
		if ($(this).hasClass("priBtnDisabled")) {
			return;
		}
		var panel = $(this).closest(".reportsTab");
		var view = $('.viewReportWrapper:visible', panel);
		$(".jsEditReport, .jsCreateReport, .jsDelReport, .jsPrintReport, .viewReportWrapper", panel).addClass("isHidden");
		$(".jsCancelSaveReport, .jsSaveReport, .editReportWrapper", panel).removeClass("isHidden");
		var editorId = 'letterReportEditor';
		if (currentReportTab == 'referencePage') {
			editorId = 'referenceReportEditor';
		}
		nicEditors.findEditor(editorId).setContent(view.find(".letterContent").html());
		$(".richEditorWrapper input.titleInput", panel).val(view.find(".reportTitle").text());
		// remove the textarea id
		var previousLetter = gPreviousReportNames[currentReportTab];
		$("textarea", panel).attr('data-letter-id', previousLetter);
		
		equalizeReportHeight();
	});
	
	$(".jsCancelSaveReport").click(function() {
		gotoPreviousReport();
	});
	
	$(".jsCreateReport").click(function() {
		if ($(this).hasClass("priBtnDisabled")) {
			return;
		}
		var panel = $(this).closest(".reportsTab");
		$(".jsEditReport, .jsCreateReport, .jsDelReport, .jsPrintReport, .viewReportWrapper", panel).addClass("isHidden")
		$(".jsCancelSaveReport, .jsSaveReport, .editReportWrapper", panel).removeClass("isHidden");
		// show the editor
		var editorId = 'letterReportEditor';
		if (currentReportTab == 'referencePage') {
			editorId = 'referenceReportEditor';
		}
		nicEditors.findEditor(editorId).setContent('');
		$(".richEditorWrapper input.titleInput", panel).val('');
		// remove the textarea id
		$("textarea", panel).removeAttr("data-letter-id");

		
	});
	
	//reports page cancel edit button
	$(".jsEditReportSaved").click(function(e){
		closePopup();
		if (currentReportTab != 'referencePage') {
			loadLetters();
		} else {
			loadReferences();
		}
		
		createTreeView();
		gotoPreviousReport();
	});
	
	//reports page save button
	$(".jsSaveReport").click(function(){
		// get the editor content
		var editorId = 'letterReportEditor';
		if (currentReportTab == 'referencePage') {
			editorId = 'referenceReportEditor';
		}
		var content = nicEditors.findEditor(editorId).getContent();
		var panel = $(this).closest(".reportsTab");
		var title = $(".richEditorWrapper input.titleInput", panel).val();
		if ($.trim(content).length == 0 || $.trim(title).length == 0) {
			alert("The content or title cannot be empty.");
			return;
		}
		var letterId = $("textarea", panel).attr("data-letter-id");
		var path = '/letter';
		if (currentReportTab == 'referencePage') {
			path = '/reference';
		}
		
		var letter;
		var method;
		if (!letterId) {
			// create
			letter = {
				name:title,
				content:content
			};
			method = "POST"
		} else {
			// edit
			letter = {
					name:title,
					content:content,
					id :letterId
			}
			method = "PUT";
		}
		var context = $("#context").val();
		$.ajax({
			url:context + path,
			type:method,
			async:true,
		    contentType: 'application/json',
		    headers: { 'Accept': 'application/json'},
			data:JSON.stringify(letter),
			success:function(response) {
				gPreviousReportNames[currentReportTab] = response.id;
				showPopup(".saveReportSuccessPopup");
			},
			error:function() {
				alert("failed to save the content");
			}
		});
		
	});
	
	//reports page print button
	$(".jsPrintReport").click(function(){
		var viewReport = $(this).closest('.reportsTab').find('.viewReportWrapper:visible');
		if (!viewReport || !viewReport.data('report-name')) {
			return;
		}
		$('.sampleReportPlaceholderPopup .printScrollArea').html('');
		var content = viewReport.find(".reportPageReportWrapper").clone();
		$('.sampleReportPlaceholderPopup .printScrollArea').append(content);
		// construct the download links
		if (currentReportTab == "reportsPage" && !viewReport.hasClass("noDownload")) {
			$('.sampleReportPlaceholderPopup .downloadLinks').removeClass("isHidden");
			var panel = viewReport.find('.reportParameterSetting');
			var request = populateReportRequest(panel);
			var reportName = viewReport.data('report-name');
			var context = $('#context').val();
			var theLink = context + '/report/download?requestJSON=' + JSON.stringify(request) + '&reportName=' + reportName + '&exportType=';
			var pdfLink = $('.sampleReportPlaceholderPopup .pdfLink');
			var docLink = $('.sampleReportPlaceholderPopup .docLink');
			var rtfLink = $('.sampleReportPlaceholderPopup .rtfLink');
			pdfLink.attr({
				href:theLink + 'PDF',
				target:'_blank'
			});
			docLink.attr({
				href:theLink + 'DOC',
				target:'_blank'
			});
			rtfLink.attr({
				href:theLink + 'RTF',
				target:'_blank'
			});
		} else {
			$('.sampleReportPlaceholderPopup .downloadLinks').addClass("isHidden");
		}
		
		showPopup(".sampleReportPlaceholderPopup");
	});

	$(".jsDoDeleteReport").click(function() {
		var id = gPreviousReportNames[currentReportTab];
		if (id == null) {
			return;
		}
		var path = '/letter/';
		if (currentReportTab == 'referencePage') {
			path = '/reference/';
		}
		
		var url = $('#context').val() + path + id;
		$.ajax({
			url:url,
			type:"DELETE",
			async:true,
			success:function() {				
				closePopup();
				if (currentReportTab != 'referencePage') {
					loadLetters();
				} else {
					loadReferences();
				}
				createTreeView();
				gotoPreviousReport();
			},
			error:function() {
				alert("failed to delete.")
			}
		});
	});

	//reports page delete button
	$(".jsDelReport").click(function(){
		if ($(this).hasClass("priBtnDisabled")) {
			return;
		}
		showPopup(".deleteReportConfirmationPopup");
		
	});	
	
	$(document).on("click", ".jsRunReport",  function() {
		var request = populateReportRequest($(this).closest('.reportParameterSetting'));
		if (request == 0) {
			// invalid request
			return;
		}
		var reportName = $(this).closest('.viewReportWrapper').data('report-name');
		sendReportingRequest(reportName, request);
	});
	$('.reportTabsBtn').click(function() {
		var pageId = $(this).data('tabid');
		$('.reportsTab').each(function() {
			if ($(this).prop('id') == pageId) {
				
				// go to this page
				$(this).removeClass("isHidden");
				currentReportTab = pageId;
				// set the page title
				if (pageId == 'reportsPage') {
					$('.pageTitle').text('Reports');
					$('.breadcurmb > span').text('Reports');
				} else if (pageId == 'correspondencePage') {
					$('.pageTitle').text('Correspondence');
					$('.breadcurmb > span').text('Correspondence');
					
					// load the letters
					loadLetters();
					
				} else if (pageId == 'referencePage') {
					$('.pageTitle').text('Reference');
					$('.breadcurmb > span').text('Reference');
					
					loadReferences();
				}
				createTreeView();
				
			} else {
				$(this).addClass("isHidden");
			}
		});
	});
	
});

function loadLetters() {
	var context = $('#context').val();
	$.ajax({
		url: context + "/letter/all",
		async:false,
		dataType:"json",
		type:"GET",
		cache:false,
		success:function(letters) {
			// copy the link
			var leftPanel = $("#correspondencePage .leftTreeWrapper");
			var treePanel = $('ul.sampleTree', leftPanel);
			var ul = treePanel.children("li").children("ul");
			ul.html('');
			for (var i = 0; i < letters.length; i++) {
				var link = $('.pageContentLink', leftPanel).first().clone();
				link.removeClass("selected")
				link.removeClass("pageContentLink");
				var letter = letters[i];
				link.attr('data-report-name', letter.id + '');
				link.find("span.nodeText").text(letter.name);
				ul.append($("<li></li>").append(link));
			}
		},
		error:function() {
			alert("failed to get letters");
		}
	});
}

function loadReferences() {
	var context = $('#context').val();
	$.ajax({
		url: context + "/reference/all",
		async:false,
		dataType:"json",
		type:"GET",
		cache:false,
		success:function(letters) {
			// copy the reference
			var leftPanel = $("#referencePage .leftTreeWrapper");
			var treePanel = $('ul.sampleTree', leftPanel);
			var ul = treePanel.children("li").children("ul");
			ul.html('');
			for (var i = 0; i < letters.length; i++) {
				var link = $('.pageContentLink', leftPanel).first().clone();
				link.removeClass("selected")
				link.removeClass("pageContentLink");
				var letter = letters[i];
				link.attr('data-report-name', letter.id + '');
				link.find("span.nodeText").text(letter.name);
				ul.append($("<li></li>").append(link));
			}
		},
		error:function() {
			alert("failed to get reference");
		}
	});
}





function renderReportUserRolePermissions(panel, response) {
	var roleTable = $(".roleTbl", panel);
	
	var colFirstCellStr = '<col class="col0" style="width:100px;"/>';
	var colCellStr = '<col class="colPermission" />';
	
	var headFirstCellStr = '<th class="roleCol">Role</th>';
	var headCellStr = '<th class="vertical"><div>Add New Account</div></th>';
	
	var dataFirstCellStr = '<td class="roleCol">Batch Process</td>';
	var dataCellStr = '<td class="vertical">X</td>';
	
	// cols
	var cols = $("colgroup", roleTable);
	cols.empty();
	cols.append($(colFirstCellStr));
	for (var i = 0; i < response.permissions.length; i++) {
		cols.append($(colCellStr));
	}
	
	// heads
	var heads = $("thead", roleTable);
	heads.empty();
	var headRow = $('<tr></tr>');
	heads.append(headRow);
	// add cells to the head
	headRow.append($(headFirstCellStr));
	for (var i = 0; i < response.permissions.length; i++) {
		var th = $(headCellStr);
		if (i % 2 == 1) {
			th.addClass("oddCol");
		}
		th.find("div").text(response.permissions[i]);
		headRow.append(th);
	}
	var tbody = $("tbody", roleTable);
	tbody.empty();
	
	for (var i = 0; i < response.roles.length; i++) {
		var dataRow = $('<tr></tr>');
		var role = response.roles[i];
		var allowed = response.allowedPermissions[role];
		var cell = $(dataFirstCellStr);
		cell.text(role);
		dataRow.append(cell);
		for (var j = 0; j < response.permissions.length; j++) {
			cell = $(dataCellStr);
			var permission = response.permissions[j];
			if ($.inArray(permission, allowed) < 0) {
				cell.text('');
			} else {
				cell.text('X');
			}
			if (j % 2 == 1) {
				cell.addClass("oddCol");
			}
			dataRow.append(cell);
		}
		tbody.append(dataRow);
	}
	
	var roleDesTbl = $('.roleDesTbl', panel);
	tbody = $("tbody", roleDesTbl);
	tbody.empty();
	for (var i = 0; i < response.roles.length; i++) {
		var row = $("<tr></tr>");
		var role = response.roles[i];
		var description = response.roleDescriptions[i];
		row.append("<td>" + role + "</td>");
		row.append("<td>" + description + "</td>");
		tbody.append(row);
	}
}

function renderReportUserAndRoleList(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate").text(formatDateTime(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tTitleRow = $("tr.tTitleRow", templateBody).clone();
	
	theTbody.html('');
	
	// group by 
	var groups = {};
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		if (groups[item.role] == null) {
			groups[item.role] = [];
		}
		groups[item.role].push(item);
	}
	
	for (var key in groups) {
		var list = groups[key];
		var titleRow = tTitleRow.clone();
		titleRow.find("td:eq(0)").text(key);
		theTbody.append(titleRow);
		theTbody.append(tHeadRow.clone());
		for (var i = 0; i < list.length; i++) {
			var item = list[i];
			var dataRow = tDataRow.clone();
			dataRow.find("td:eq(0)").text(item.userName);
			dataRow.find("td:eq(1)").text(item.opmName);
			dataRow.find("td:eq(2)").text(item.telephone);
			dataRow.find("td:eq(3)").text(item.email);
			dataRow.find("td:eq(4)").text(item.userStatus);
			dataRow.find("td:eq(5)").text(item.supervisor ? item.supervisor : '');
			theTbody.append(dataRow);
		}
	}
}

function renderReportAccountStatisticsReport(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName + ' As of ' + formatDateTime(response.reportGenerationDate));
	
	var tbody = $("table tbody", panel);
	tbody.find("tr:eq(0) td:eq(1)").text(numberWithCommas(response.totalActiveCsrsAccounts));
	tbody.find("tr:eq(1) td:eq(1)").text(numberWithCommas(response.totalActiveFersAccounts));
	tbody.find("tr:eq(2) td:eq(1)").text(numberWithCommas(response.totalHistoryCsrsAccounts));
	tbody.find("tr:eq(3) td:eq(1)").text(numberWithCommas(response.totalHistoryFersAccounts));
	tbody.find("tr:eq(4) td:eq(1)").text(numberWithCommas(response.totalCsrsAccounts));
	tbody.find("tr:eq(5) td:eq(1)").text(numberWithCommas(response.totalFersAccounts));
	tbody.find("tr:eq(6) td:eq(1)").text(numberWithCommas(response.totalOpenAccountsNoPostedPayments));
	tbody.find("tr:eq(7) td:eq(1)").text(numberWithCommas(response.totalHistoryClosedAccountsNoPostedPayment));
	tbody.find("tr:eq(8) td:eq(1)").text(numberWithCommas(response.totalAccountsNoPostedPayments));
	tbody.find("tr:eq(9) td:eq(1)").text(numberWithCommas(response.totalOpenAccountsLastYear));
	tbody.find("tr:eq(10) td:eq(1)").text(numberWithCommas(response.totalHistoryClosedAccountsLastYear));
	tbody.find("tr:eq(11) td:eq(1)").text(numberWithCommas(response.totalAccountsLastYear));
	
	
}

function renderReportSuspenseResolutionReport(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate span.fRight").text(formatDateTime(response.reportGenerationDate, "MMMM dd, yyyy"));
	// group by supervisor
	var groups = {};
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		if (groups[item.supervisor] == null) {
			groups[item.supervisor] = [];
		}
		groups[item.supervisor].push(item);
	}
	var contentDIV = $(".contentDIV", panel);
	contentDIV.empty();
	var minDate = -1;
	var sum = 0;
	for (var key in groups) {
		var list = groups[key];
		var content = $(".templateDIV", panel).clone();
		var tDataRow = content.find("tr.tDataRow").clone();
		content.removeClass("isHidden");
		content.removeClass("templateDIV");
		
		content.find("span.tTitleSpan").text(key);
		
		var preRow = content.find(".tDataRow");
		for (var i = 0; i < list.length; i++) {
			var item = list[i];
			var dataRow = tDataRow.clone();
			dataRow.removeClass("isHidden");
			dataRow.find("td:eq(0)").text(item.csd);
			dataRow.find("td:eq(1)").text(formatReportMoney(item.suspense));
			dataRow.find("td:eq(2)").text(formatReportMoney(item.resolved));
			dataRow.find("td:eq(3)").text(formatReportMoney(item.processed));
			dataRow.find("td:eq(4)").text(item.startedAs);
			dataRow.find("td:eq(5)").text(item.resolution);
			dataRow.find("td:eq(6)").text(item.payment ? item.payment : '');
			dataRow.find("td:eq(7)").text(formatReportMoney(item.account));
			if (item.transactionDate < minDate || minDate < 0) {
				minDate = item.transactionDate;
			}
			preRow.after(dataRow);
			preRow = dataRow;
			sum += item.resolved;
		}
		
		contentDIV.append(content);
	}
	
	$('.tFromSpan', panel).text(formatDateTime(response.startDate));
	$('.tToSpan', panel).text(formatDateTime(response.endDate));
	$('.tOldestSpan', panel).text(minDate > 0 ? formatDateTime(minDate) : '');
	$('.tTotalNumberSpan', panel).text(formatReportMoney(sum));
}
function renderReportReceiptSuspenseItemsReport(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".printDate").text(formatDateTime(response.reportGenerationDate, "MMMM dd, yyyy"));
	$(titleArea).find(".reportDate").text(formatDateTime(response.reportGenerationDate));
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tGroupRow = $("tr.tGroupRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tTotalRow = $("tr.tTotalRow", templateBody).clone();
	
	theTbody.html('');
	
	// group by paymentTye
	var groups = {};
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		if (groups[item.paymentType] == null) {
			groups[item.paymentType] = [];
		}
		groups[item.paymentType].push(item);
	}

	for (var key in groups) {
		var list = groups[key];
		var groupRow = tGroupRow.clone();
		groupRow.find("td:eq(0)").text(key);
		theTbody.append(groupRow);
		theTbody.append(tHeadRow.clone());
		var sum = 0;
		for (var i = 0; i < list.length; i++) {
			var item = list[i];
			var dataRow = tDataRow.clone();
			dataRow.find("td:eq(0)").text(item.claimantName);
			dataRow.find("td:eq(1)").text(item.csd);
			dataRow.find("td:eq(2)").text(item.retirementType ? item.retirementType : '');
			dataRow.find("td:eq(3)").text(formatReportMoney(item.fers));
			dataRow.find("td:eq(4)").text(item.actionType + ' ' + formatDateTime(item.actionTime, "MM/dd/yyyy HH:mm"));
			dataRow.find("td:eq(5)").text(formatReportMoney(item.depositsPre1082));
			dataRow.find("td:eq(6)").text(formatReportMoney(item.redepositsPre1082));
			dataRow.find("td:eq(7)").text(formatReportMoney(item.depositsPost982));
			dataRow.find("td:eq(8)").text(formatReportMoney(item.redepositsPost982));
			dataRow.find("td:eq(9)").text(formatReportMoney(item.amount));
			sum += item.amount;
			theTbody.append(dataRow);
		}
		var totalRow = tTotalRow.clone();
		totalRow.find("td:eq(0)").text(formatReportMoney(sum));
		theTbody.append(totalRow);
	}
}

function renderReportGLReportDailyReport(panel, response) {

	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate span.fRight").text(formatDateTime(response.reportGenerationDate, "MMMM dd, yyyy"));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tItemDataRow = $("tr.tItemDataRow", templateBody).clone();
	var tPaymentDataRow = $("tr.tPaymentDataRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tSeperateRow = $("tr.tSeperateRow", templateBody).clone();

	theTbody.html('');
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		theTbody.append(tHeadRow.clone());
		var payments = item.payments;
		var itemRow = tItemDataRow.clone();
		itemRow.find("td:eq(0)").text(item.plan);
		itemRow.find("td:eq(1)").text(item.agency);
		itemRow.find("td:eq(2)").text(item.type);
		itemRow.find("td:eq(3)").text(item.accountingCode);
		itemRow.find("td:eq(4)").text((item.glDate ? formatDateTime(item.glDate) : '') + item.glFiller + item.glCode);
		itemRow.find("td:eq(5)").text(item.sourceCode);
		itemRow.find("td:eq(6)").text(item.paymentsNumber);
		theTbody.append(itemRow.clone());
		for (var j = 0; j < payments.length; j++) {
			var payment = payments[j];
			var paymentRow = tPaymentDataRow.clone();
			// ID:762977	CSD:9167978 DOB: mm/dd/yyyy XXXXXXXXXX	06/09/2013	$50.00
			paymentRow.find("span:eq(0)").text(payment.id);
			paymentRow.find("span:eq(1)").text(payment.csd);
			paymentRow.find("span:eq(2)").text(formatDateTime(payment.claimantDateOfBirth));
			paymentRow.find("span:eq(3)").text(item.claimantName ? item.claimantName : '');
			paymentRow.find("span:eq(4)").text(formatDateTime(payment.paymentDate));
			paymentRow.find("span:eq(5)").text(formatReportMoney(item.paymentAmount));
			theTbody.append(paymentRow);
		}
		theTbody.append(tSeperateRow.clone());
	}
	
	
}
function renderReportChangeHistoryReport(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate span.fRight").text(formatDateTime(response.reportGenerationDate, "MMMM dd, yyyy"));
	
	var groups = {};
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		var key =item.date + ' ' + item.modified;
		if (groups[key] == null) {
			groups[key] = [];
		}
		groups[key].push(item);
	}
	
	// the meta
	var metas = $("table.reportMetaTable tbody", panel);
	metas.find("tr:eq(0) td:eq(0)").text('CSD #' + response.csd);
	metas.find("tr:eq(0) td:eq(1)").text(formatDateTime(response.birthDay));
	metas.find("tr:eq(0) td:eq(2)").text(response.claimName);
	
	var wrapper = $('.contentPlace', panel);
	wrapper.empty();
	for (var key in groups) {
		var list = groups[key];
		
		var content = $('table.reportTemplateTable', panel).clone();
		content.removeClass("reportTemplateTable");
		content.removeClass("isHidden");
		var tDataRow = content.find("tr.tDataRow");
		var prev = tDataRow;
		content.find(".tGroupRow td:eq(0)").text(formatDateTime(list[0].date, "MMMM dd, yyyy HH:mm p"));
		content.find(".tGroupRow td:eq(1)").text(list[0].modified);
		for (var j = 0; j < list.length; j++) {
			var item = list[j];
			var dataRow = tDataRow.clone();
			dataRow.removeClass("isHidden");
			dataRow.find("td:eq(0)").text(item.description);
			prev.after(dataRow);
			prev = dataRow;
		}
		
		wrapper.append(content);
	}
	
	
}

function renderReportMonthlyAdjustmentsReports(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate span.fRight").text(formatDateTime(response.reportGenerationDate, "MMMM dd, yyyy"));
	$(titleArea).find(".printDate").text('Printed ' + formatDateTime(response.reportGenerationDate));
	
	var groups = {};
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		var key = formatDateTime(item.date) + ' ' + item.modifier + ' ' + item.accountNumber;
		if (groups[key] == null) {
			groups[key] = [];
		}
		groups[key].push(item);
	}
	
	var wrapper = $('.closedAccountContent', panel).parent();
	wrapper.find("table:visible").each(function() {
		$(this).remove();
	});
	$("img", wrapper).remove();
	$("div", wrapper).remove();
	var changes = {};
	var accounts = {};
	for (var key in groups) {
		var group = groups[key];
		
		var content = $('.closedAccountContent', panel).clone();
		content.removeClass("closedAccountContent");
		content.removeClass("isHidden");
		
		var tDataRow = content.find("tr.tDataRow");
		var prev = tDataRow;
		
		content.find(".tHeadRow").find("td:eq(0)").text(formatDateTime(group[0].date));
		content.find(".tHeadRow").find("td:eq(1)").text(group[0].modifier + ' changed account #' + group[0].accountNumber);
		if (!changes[group[0].modifier]) {
			changes[group[0].modifier] = 0;
		}
		if (!accounts[group[0].modifier]) {
			accounts[group[0].modifier] = 0;
		}
		changes[group[0].modifier] += group.length;
		accounts[group[0].modifier] ++;
		for (var j = 0; j < group.length; j++) {
			var item = group[j];
			var dataRow = tDataRow.clone();
			dataRow.removeClass("isHidden");
			dataRow.find("td:eq(0)").text(formatDateTime(item.date, "HH:mm p"));
			dataRow.find("td:eq(1)").text(item.description);
			prev.after(dataRow);
			prev = dataRow;
		}
		wrapper.append(content);
	} 
	for (var key in changes) {
		var text = key + ' made ' + changes[key] + ' changes to ' + accounts[key] + ' accounts during reporting period.';
		wrapper.append($("<div></div>").text(text));
	}
	var img = $("<img />");
	
	var requestJSON = JSON.stringify({
		startDate:new Date(response.startDate),
		endDate:new Date(response.endDate)
	});
		
	var url = $('#context').val() + '/report/chart?reportName=MonthlyAdjustmentsReports&requestJSON=' + requestJSON;
	img.attr('src', url);
	wrapper.append(img);
}

function renderReportSummaryofTotalPayments(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate").text(formatDateTime(response.reportGenerationDate));
	
	var theTbody = $("table tbody", panel);
	theTbody.find("tr:eq(0) td:eq(1)").text(formatReportMoney(response.totalDepositsPre1082));
	theTbody.find("tr:eq(1) td:eq(1)").text(formatReportMoney(response.totalDepositsPost982));
	theTbody.find("tr:eq(2) td:eq(1)").text(formatReportMoney(response.csrsDepositsPost982));
	theTbody.find("tr:eq(3) td:eq(1)").text(formatReportMoney(response.totalDepositsPre1082));
	theTbody.find("tr:eq(4) td:eq(1)").text(formatReportMoney(response.fersDepositsPost982));
	theTbody.find("tr:eq(5) td:eq(1)").text(formatReportMoney(response.fersPostalDepositsPost982));
	theTbody.find("tr:eq(6) td:eq(1)").text(formatReportMoney(response.fersNonPostalDepositsPost982));
	theTbody.find("tr:eq(7) td:eq(1)").text(formatReportMoney(response.totalRedepositsPre1082));
	theTbody.find("tr:eq(8) td:eq(1)").text(formatReportMoney(response.totalPaymentsOnFile));
	theTbody.find("tr:eq(9) td:eq(1)").text(numberWithCommas(response.accountNumberOnFile));
	
	var url = $('#context').val() + "/report/chart?reportName=SummaryofTotalPayments&requestJSON={}";
	$("img", panel).attr("src", url);
}

function renderReportClosedAccountReport(panel, response) {
	var wrapper = $('.closedAccountContent', panel).parent();
	wrapper.find(".closedAccountContent:visible").each(function() {
		$(this).remove();
	});
	for (var i = 0; i < response.entries.length; i++) {
		var entry = response.entries[i];
		var content = $('.closedAccountContent', panel).clone();
		content.removeClass("closedAccountContent");
		content.removeClass("isHidden");
		var tDataRow = content.find("tr.tDataRow");
		var prev = tDataRow;
		var metas = $("table.accountMetaTbl tbody", content);
		metas.find("span:eq(0)").text(entry.name);
		metas.find("span:eq(1)").text(entry.claimNumber);
		metas.find("span:eq(2)").text(formatDateTime(entry.dateOfBirth));
		metas.find("span:eq(3)").text(entry.ssn);
		metas.find("span:eq(4)").text(formatDateTime(entry.closeDate));
		
		for (var j = 0; j < entry.items.length; j++) {
			var item = entry.items[j];
			var dataRow = tDataRow.clone();
			dataRow.removeClass("isHidden");
			dataRow.find("td:eq(0) span").text(formatDateTime(entry.billingDate));
			dataRow.find("td:eq(1)").text(formatReportMoney(item.redepositsPost982));
			dataRow.find("td:eq(2)").text(formatReportMoney(item.redepositsPre1082));
			dataRow.find("td:eq(3)").text(formatReportMoney(item.depositsPost982));
			dataRow.find("td:eq(4)").text(formatReportMoney(item.depositsPre1082));
			prev.after(dataRow);
			prev = dataRow;
		}
		
		var footer = $("table.closedAccountFooterTbl tbody", content);
		footer.find("tr:eq(0) td:eq(1)").text(entry.dateOfLastPayment ? formatDateTime(entry.dateOfLastPayment) : '');
		footer.find("tr:eq(1) td:eq(1)").text(entry.dateOfLastActivity ? formatDateTime(entry.dateOfLastActivity) : '');
		
		wrapper.append(content);
	}
	
	
}

function renderReportRefundSectionAccountAssignments(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate").text(formatDateTime(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	for (var i = 0; i < response.groups.length; i++) {
		var group = response.groups[i];
		var currentBody = templateBody.clone();
		var items = group.items;
		var prev = currentBody.find("tr.tDataRow");
		
		currentBody.find("tr:eq(0) span:eq(0)").text(group.user);
		currentBody.find("tr:eq(0) span:eq(1)").text(group.email);
		currentBody.find("tr:eq(0) span:eq(2)").text(group.telephone);
		
		currentBody.find("tr:eq(1) span:eq(0)").text(group.networkId);
		currentBody.find("tr:eq(1) span:eq(1)").text(group.role);
		currentBody.find("tr:eq(1) span:eq(2)").text(group.supervisingRole ? group.supervisingRole : '-');
		currentBody.find("tr.userSummarnyRow:eq(0) td:eq(0)").text(group.user);
		currentBody.find("tr.userSummarnyRow:eq(0) td:eq(2)").text(group.averageDays);
		currentBody.find("tr.userSummarnyRow:eq(1) td:eq(2)").text(group.maximumDays);
		for (var j = 0; j < items.length; j++) {
			var item = items[j];
			var dataRow = tDataRow.clone();
			dataRow.removeClass("isHidden");
			dataRow.find("td:eq(0)").text(item.csd);
			dataRow.find("td:eq(1)").text(formatDateTime(item.assignmentDate));
			dataRow.find("td:eq(2)").text(item.daysNumber);
			dataRow.find("td:eq(3)").text(item.accountStatusDescription);
			prev.after(dataRow);
			prev = dataRow;
		}
		theTbody.append($(currentBody.html()));
	}
	
}
function renderReportPaymentsPendingApproval(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate").text(formatDateTime(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tGroupRow = $("tr.tGroupRow", templateBody).clone();
	var tSeperateRow = $("tr.tSeperateRow", templateBody).clone();
	var tGrandTotal = $("tr.tGrandTotal", templateBody).clone();
	
	var groups = {};
	// group by names
	var items = response.items;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var name = item.approvalUser;
		if (groups[name] == null) {
			groups[name] = [];
		}
		groups[name].push(item);
	}
	
	theTbody.html('');
	for (var key in groups) {
		var groupRow = tGroupRow.clone();
		groupRow.find("td:eq(1)").text(key);
		theTbody.append(groupRow);
		theTbody.append(tHeadRow.clone());
		var items = groups[key];
		for (var i = 0; i < items.length; i++) {
			var item = items[i];
			var dataRow = tDataRow.clone();
			dataRow.find("td:eq(0)").text(item.batchNumber);
			dataRow.find("td:eq(1)").text(formatDateTime(item.depositDate));
			dataRow.find("td:eq(2)").text(formatDateTime(item.modifiedTime));
			dataRow.find("td:eq(3)").text(formatReportMoney(item.amount));
			dataRow.find("td:eq(4)").text(item.csd);
			dataRow.find("td:eq(5)").text(formatDateTime(item.dateOfBirth));
			dataRow.find("td:eq(6)").text(item.accountStatus);
			dataRow.find("td:eq(7)").text(item.paymentInfo);
			theTbody.append(dataRow);
		}
		theTbody.append(tSeperateRow.clone());
	}
	var techRow = $("tr.tTechRow", templateBody).clone();
	techRow.find("td:eq(2)").text(numberWithCommas(response.items.length));
	theTbody.append(techRow);
	var grandTotal = tGrandTotal.clone();
	grandTotal.find("td:eq(2)").text(numberWithCommas(response.items.length));
	theTbody.append(grandTotal);
}

function renderReportPaymentsbyTypeandDateRange(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(
			response.reportName + ' from ' + formatDateTime(response.startDate) + ' through ' + formatDateTime(response.endDate));
	$(titleArea).find(".printDate").text(formatDateTime(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	
	theTbody.html('');
	theTbody.append(tHeadRow.clone());
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.retirementType ? item.retirementType : '-');
		dataRow.find("td:eq(1)").text(item.csd);
		dataRow.find("td:eq(2)").text(formatReportMoney(item.paymentAmount));
		dataRow.find("td:eq(3)").text(formatDateTime(item.date));
		theTbody.append(dataRow);
	}
}

function renderReportPaymentHistory(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	
	var infoBlock = $("table.infoTable tbody tr:eq(0)", panel);
	infoBlock.find("span:eq(0)").text(response.username);
	infoBlock.find("span:eq(1)").text(response.city);
	infoBlock.find("span:eq(2)").text(response.address1);
	infoBlock.find("span:eq(3)").text(response.address2);
	infoBlock.find("span:eq(4)").text(response.state + ' ' + response.zip);
	infoBlock.find("span:eq(5)").text(response.csd);
	infoBlock.find("span:eq(6)").text(formatDateTime(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	theTbody.html('');
	
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(formatDateTime(item.dateOfPayment));
		dataRow.find("td:eq(1)").text(formatDateTime(item.processDate));
		dataRow.find("td:eq(2)").text(formatReportMoney(item.balanceDueBeforePayment));
		dataRow.find("td:eq(3)").text(formatReportMoney(item.interestOnPrior));
		dataRow.find("td:eq(4)").text(formatReportMoney(item.dueBeforePayment));
		dataRow.find("td:eq(5)").text(formatReportMoney(item.paymentAmount));
		dataRow.find("td:eq(6)").text(formatReportMoney(item.balanceDueAfterPayment));
		dataRow.find("td:eq(7)").text(formatReportMoney(item.totalOfPaymentsToDate));
		theTbody.append(dataRow);
	}
	
}
function renderReportMonthlySuspenseList(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate").text(formatDateTime(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tGrandTotalRow = $("tr.tGrandTotalRow", templateBody).clone();
	theTbody.html('');
	theTbody.append(tHeadRow.clone());
	var totals = 0;
	for (var i = 0; i < response.items.length;i++) {
		var item = response.items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.batchNumber);
		dataRow.find("td:eq(1)").text(item.type);
		dataRow.find("td:eq(2)").text(item.csd);
		dataRow.find("td:eq(3)").text('-');
		dataRow.find("td:eq(4)").text(formatDateTime(item.deposited));
		dataRow.find("td:eq(5)").text(formatReportMoney(item.amount));
		dataRow.find("td:eq(6)").text(formatDateTime(item.imported));
		dataRow.find("td:eq(7)").text(item.currentStatus ? item.currentStatus : '');
		totals += item.amount;
		theTbody.append(dataRow);
	}
	var grandTotalRow = tGrandTotalRow.clone();
	grandTotalRow.find("span").text(formatReportMoney(totals));
	theTbody.append(grandTotalRow);
	
}

function renderReportManualPayments(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find("h2").text(parseDateToString(response.reportGenerationDate));
	$(titleArea).find(".printDate").text($.format.date(response.reportGenerationDate, "ddd, MMMM d, yyyy"));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tGroupRow = $("tr.groupRow". templateBody).clone();
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tGroupRow = $("tr.groupRow", templateBody).clone();
	var tTotalRow = $("tr.tTotalRow", templateBody).clone();
	var tGrandTotalRow = $("tr.tGrandTotalRow", templateBody).clone();
	theTbody.html('');
	
	var groups = {};
	// group by claims
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		var csd = item.csd;
		var list = groups[csd];
		if (list == null) {
			groups[csd] = [];
			list = groups[csd];
		}
		list.push(item);
	}
	
	var grandNum = 0;
	var grandTotal = 0;
	for (var key in groups) {
		
		var groupRow = tGroupRow.clone();
		theTbody.append(groupRow);
		groupRow.find("td:eq(0)").text(key);
		theTbody.append(tHeadRow.clone());
		var items = groups[key];
		var sum = 0;
		for (var i = 0; i < items.length; i++) {
			var dataRow = tDataRow.clone();
			var item = items[i];
			dataRow.find("td:eq(0)").text(item.batchNumber);
			dataRow.find("td:eq(1)").text(item.csd);
			dataRow.find("td:eq(2)").text(formatDateTime(item.birthDate));
			dataRow.find("td:eq(3)").text(formatReportMoney(item.paymentAmount));
			dataRow.find("td:eq(4)").text(formatDateTime(item.payDate));
			dataRow.find("td:eq(5)").text(item.batchDate ? formatDateTime(item.batchDate) : '');
			dataRow.find("td:eq(6)").text(item.paymentStatusDescription ? item.paymentStatusDescription : '');
			sum += item.paymentAmount;
			theTbody.append(dataRow);
		}
		
		var totalRow = tTotalRow.clone();
		totalRow.find("span:eq(0)").text(items.length);
		totalRow.find("span:eq(1)").text(formatReportMoney(sum));
		grandNum += items.length;
		grandTotal += sum;
		theTbody.append(totalRow);
	}
	var grandRow = tGrandTotalRow.clone();
	grandRow.find("span:eq(0)").text(grandNum);
	grandRow.find("span:eq(1)").text(formatReportMoney(grandTotal));
	theTbody.append(grandRow);
}



function renderReportLockboxBankFileImportTotals(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate").text(parseDateToString(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	theTbody.html('');

	
	theTbody.append(tHeadRow.clone());
	var prev = null;
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(numberWithCommas(item.logId));
		dataRow.find("td:eq(1)").text(item.depositDate ? formatDateTime(item.depositDate) : '');
		dataRow.find("td:eq(2)").text(item.importDate ? formatDateTime(item.importDate) : '');
		dataRow.find("td:eq(3)").text(item.checkCount);
		dataRow.find("td:eq(4)").text(item.achCount);
		dataRow.find("td:eq(5)").text(item.checkTotal ? formatReportMoney(item.checkTotal) : '-');
		dataRow.find("td:eq(6)").text(item.achTotal ? formatReportMoney(item.achTotal) : '-');
		theTbody.append(dataRow);
		prev = dataRow;
	}
}

function renderReportAccountingSummaryReport(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".realReportDate").text(parseDateToString(response.reportGenerationDate));
	
	var theTbody = $("table.reportRealTable", panel);
	theTbody.html($("table.reportTemplateTable", panel).html());
	
	theTbody.find("tr:eq(1) td:eq(2)").text(formatReportMoney(response.receipts));
	theTbody.find("tr:eq(2) td:eq(2)").text(formatReportMoney(response.suspense));
	theTbody.find("tr:eq(3) td:eq(2)").text(formatReportMoney(response.totalReceipts));
	theTbody.find("tr:eq(4) td:eq(2)").text(formatReportMoney(response.replacedAccounts));
	theTbody.find("tr:eq(5) td:eq(2)").text(formatReportMoney(response.adjustmentPlus));
	theTbody.find("tr:eq(6) td:eq(2)").text(formatReportMoney(response.totalAdditions));
	theTbody.find("tr:eq(11) td:eq(2)").text(formatReportMoney(response.debitVouchers));
	theTbody.find("tr:eq(12) td:eq(2)").text(formatReportMoney(response.adjustmentMinus));
	theTbody.find("tr:eq(13) td:eq(2)").text(formatReportMoney(response.totalDeductions));
	theTbody.find("tr:eq(14) td:eq(1)").text(formatReportMoney(response.netChange));
	
}

function renderReportResolvedSuspenseHistory(panel, response) {
	// DailyReconciliationReport
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate").text(parseDateToString(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tGroupRow = $("tr.tGroupRow", templateBody).clone();
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tSeperateRow = $("tr.sepRow", templateBody).clone();
	
	theTbody.html('');
	
	
	
	function renderItems(items, name, type) {
		var groupRow = tGroupRow.clone();
		groupRow.find("td:eq(0)").text(name);
		theTbody.append(groupRow);
		theTbody.append(tHeadRow.clone());
		for (var i = 0; i < items.length; i++) {
			var item = items[i];
			if (item.statusCode != type) {
				continue;
			}
			// Bat	Blk	Seq	CSD	Birth Date	Amount	Deposited Date	Technian	Note
			var dataRow = tDataRow.clone();
			dataRow.find("td:eq(0)").text(item.batchNumber);
			dataRow.find("td:eq(1)").text(item.blkNumber);
			dataRow.find("td:eq(2)").text(item.sequenceNumber);
			dataRow.find("td:eq(3)").text(item.csd);
			dataRow.find("td:eq(4)").text(formatDateTime(item.birthday));
			dataRow.find("td:eq(5)").text(formatReportMoney(item.amount));
			dataRow.find("td:eq(6)").text(formatDateTime(item.depositDate));
			dataRow.find("td:eq(7)").text(item.technician);
			dataRow.find("td:eq(8)").text(item.note);
			
			theTbody.append(dataRow);
		}
		theTbody.append(tSeperateRow.clone());
	}
	
	renderItems(response.dataItems, 'ANNUITY COMPLETE', 19);
	renderItems(response.dataItems, 'DEBIT VOUCHER COMPLETE', 27);
	renderItems(response.dataItems, 'DIRECT PAY LIFE - COMPLETE', 23);
	renderItems(response.dataItems, 'SUSPENSE DEBIT VOUCHER', 73);
	renderItems(response.dataItems, 'SUSPENSE REFUND COMPLETE', 25);
	renderItems(response.dataItems, 'VOLUNTARY CONTRIBUTIONS COMPLETE', 21);
	
	// add the image
	var url = constructChartURL(panel);
	$("img.reportChart", panel).attr('src', url);
}

function constructChartURL(panel) {
	var context = $('#context').val();
	var request = populateReportRequest($(".reportParameterSetting", panel));
	var reportName = panel.closest('.viewReportWrapper').data('report-name');
	var url = context + '/report/chart?requestJSON=' + JSON.stringify(request) + '&reportName=' + reportName;
	return url;
}

function renderReportLockBoxImportErrors(panel, response) {
	// DailyReconciliationReport
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".printDate").text(parseDateToString(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tDataRow1 = $("tr.tDataRow1", templateBody).clone();
	var tDataRow2 = $("tr.tDataRow2", templateBody).clone();
	var tBigSepRow = $("tr.bigSepRow", templateBody).clone();
	var tSummaryRow = $("tr.summaryRow", templateBody).clone();
	
	theTbody.html('');
	
	var headRow = tHeadRow.clone();
	var headStr = 'Batch #' + numberWithCommas(response.batchNumber) + ' Imported on ' + formatDateTime(response.importDate);
	headRow.find("td:eq(0)").text(headStr);
	theTbody.append(headRow);
	
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		var dataRow = tDataRow1.clone();
		dataRow.find("td:eq(0)").text(item.errorCode);
		dataRow.find("td:eq(1)").text(item.errorMessage);
		theTbody.append(dataRow);
		
		
		dataRow = tDataRow2.clone();
		dataRow.find("td:eq(0)").text(item.description);
		theTbody.append(dataRow);
	}
	theTbody.append(tBigSepRow.clone());
	var summaryRow = tSummaryRow.clone();
	summaryRow.find("td:eq(0)").text('Number of Records in Error = ' + numberWithCommas(response.items.length));
	theTbody.append(summaryRow);
	
	
}
function renderReportDeductionRates(panel, response) {
	// DailyReconciliationReport
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).find(".reportDate").text(parseDateToString(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tTitleRow = $('tr.tTitleRow', templateBody).clone();
	var tSeperateRow = $('tr.tSeperateRow', templateBody).clone();
	
	theTbody.html('');
	
	for (var key in response.items) {
		var titleRow = tTitleRow.clone();
		titleRow.find("td:eq(1)").text(key);
		theTbody.append(titleRow);
		
		// insert the header
		theTbody.append(tHeadRow.clone());
		// render the table
		var items = response.items[key];
		for (var i = 0; i < items.length; i++) {
			var item = items[i];
			var dataRow = tDataRow.clone();
			dataRow.find("td:eq(1)").text(formatDateTime(item.startDate));
			dataRow.find("td:eq(2)").text(formatDateTime(item.endDate));
			dataRow.find("td:eq(3)").text(item.days);
			dataRow.find("td:eq(4)").text(item.rate + '%');
			theTbody.append(dataRow);
		}
		
		theTbody.append(tSeperateRow.clone());
	}
	
}



function renderReportDailySuspenseReport(panel, response) {
	
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).children("h2").text("Payments Imported on " + formatDateTime(response.reportGenerationDate, "MMMM dd, yyyy HH:mm p"));
	var wrapper = $('.reportTblWrapper', panel);
	wrapper.html('');
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		var table1 = $('.reportTemplateTable1', panel).clone();
		table1.removeClass("isHidden");
		table1.removeClass("reportTemplateTable1");
		table2 = $('.reportTemplateTable2', panel).clone();
		table2.removeClass("isHidden");
		table2.removeClass("reportTemplateTable2");
		wrapper.append(table1);
		wrapper.append(table2);
		var tds = [];
		table1.find(".tDataRow td").each(function() {
			tds.push($(this));
		});
		table2.find(".tDataRow td").each(function() {
			if ($(this).hasClass("tIgnoreCell")) {
				return;
			}
			tds.push($(this));
		});
		var values = [
		              item.batchNumber,
		              item.blkNumber,
		              item.sequenceNumber,
		              formatReportMoney(item.amount),
		              formatDateTime(item.payDate),
		              item.csd,
		              formatDateTime(item.birthDate),
		              item.technician,
		              item.paymentStatus,
		              formatDateTime(item.changedOn)
		];
		for (var j = 0; j < tds.length; j++) {
			tds[j].text(values[j]);
		}
	}
}

function renderReportDailyReconciliationReport(panel, response) {
	// DailyReconciliationReport
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1.reportTile").text(response.reportName);
	$(titleArea).find(".reportDate").text(parseDateToString(response.reportGenerationDate));
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tTotalRow = $('tr.tTotalRow', templateBody).clone();
	
	theTbody.html('');
	
	theTbody.append(tHeadRow.clone());
	var items = response.items;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.name);
		dataRow.find("td:eq(1)").text(item.items);
		dataRow.find("td:eq(2)").text(item.receipts);
		dataRow.find("td:eq(3)").text(item.processed);
		dataRow.find("td:eq(4)").text(item.suspended);
		dataRow.find("td:eq(5)").text(item.reversed);
		theTbody.append(dataRow);
	}
	
}

function renderReportDailyCashflowReport(panel, response) {	
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	
	
	// find all the rows template
	var theTbody = $("table.reportRealTable1 tbody", panel);
	var templateBody = $("table.reportTemplateTable1 tbody", panel);
	
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tTotalRow = $('tr.tTotalRow', templateBody).clone();
	
	theTbody.html('');
	
	var sums = {
			"achNumber":0,
			"achSum":0,
			"checkNumber":0,
			"checkSum":0,
			"allNumber":0,
			"allSum":0
	};
	theTbody.append(tHeadRow.clone());
	var items = response.lockboxItems;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		
		dataRow.find("td:eq(0)").text(item.lockboxBankImportFile);
		dataRow.find("td:eq(1)").text(item.achNumber);
		dataRow.find("td:eq(2)").text(formatReportMoney(item.achSum));
		dataRow.find("td:eq(3)").text(item.checkNumber);
		dataRow.find("td:eq(4)").text(formatReportMoney(item.checkSum));
		dataRow.find("td:eq(5)").text(item.allNumber);
		dataRow.find("td:eq(6)").text(formatReportMoney(item.allSum));
		theTbody.append(dataRow);
		
		sums.achNumber += item.achNumber;
		sums.achSum += item.achSum;
		sums.checkNumber += item.checkNumber;
		sums.checkSum += item.checkSum;
		sums.allNumber += item.allNumber;
		sums.allSum += item.allSum;
	}
	var totalRow = tTotalRow.clone();
	totalRow.find("td:eq(1)").text(sums.achNumber);
	totalRow.find("td:eq(2)").text(formatReportMoney(sums.achSum));
	totalRow.find("td:eq(3)").text(sums.checkNumber);
	totalRow.find("td:eq(4)").text(formatReportMoney(sums.checkSum));
	totalRow.find("td:eq(5)").text(sums.allNumber);
	totalRow.find("td:eq(6)").text(formatReportMoney(sums.allSum));
	theTbody.append(totalRow);
	
	// reader table 2
	
	theTbody = $("table.reportRealTable2 tbody", panel);
	templateBody = $("table.reportTemplateTable2 tbody", panel);
	
	tHeadRow = $("tr.tHeadRow", templateBody).clone();
	tDataRow = $("tr.tDataRow", templateBody).clone();
	tTotalRow = $('tr.tTotalRow', templateBody).clone();
	
	theTbody.html('');
	
	sums = {
			"number":0,
			"amount":0
	};
	theTbody.append(tHeadRow.clone());
	var items = response.paymentItems;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		
		dataRow.find("td:eq(0)").text(item.name);
		dataRow.find("td:eq(1)").text(item.number);
		dataRow.find("td:eq(2)").text(formatReportMoney(item.amount));
	
		theTbody.append(dataRow);
		
		sums.number += item.number;
		sums.amount += item.amount;
	}
	var totalRow = tTotalRow.clone();
	totalRow.find("td:eq(1)").text(sums.number);
	totalRow.find("td:eq(2)").text(formatReportMoney(sums.amount));
	theTbody.append(totalRow);	
}

function renderReportCurrentSuspense(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).find(".reportDate").text(parseDateToString(response.reportGenerationDate));
	$(titleArea).children("h1").text(response.reportName);
	
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tSeperateRow = $("tr.sepRow", templateBody).clone();
	var tTitleRow = $("tr.tTitleRow", templateBody).clone();
	var tTotalRow = $("tr.totalRow", templateBody).clone();
	
	theTbody.html('');
	
	var checks = [];
	var achs = [];
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		if (item.ach) {
			achs.push(item);
		} else {
			checks.push(item);
		}
	}
	
	// checks
	var titleRow = tTitleRow.clone();
	titleRow.find('td:eq(0)').text('Check Payments from Lockbox');
	theTbody.append(titleRow);
	
	var headRow = tHeadRow.clone();
	theTbody.append(headRow);
	var sum = 0;
	for (var i = 0; i < checks.length; i++) {
		var item = checks[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.paymentStatus ? item.paymentStatus : '');
		dataRow.find("td:eq(1)").text($.format.date(new Date(item.date).toString(), "MM/dd/yyyy"));
		dataRow.find("td:eq(2)").text(item.batchNumber);
		dataRow.find("td:eq(3)").text(item.csd);
		dataRow.find("td:eq(4)").text('$' + numberWithCommas(item.amount.toFixed(2)));
		dataRow.find("td:eq(5)").text($.format.date(new Date(item.birthDate).toString(), "MM/dd/yyyy"));
		dataRow.find("td:eq(6)").text(item.claimant);
		dataRow.find("td:eq(7)").text('$' + numberWithCommas(item.balance.toFixed(2)));
		dataRow.find("td:eq(8)").text(item.accountStatus);
		if (item.amount) {
			sum += item.amount;
		}
		theTbody.append(dataRow);
	}
	var totalRow = tTotalRow.clone();
	totalRow.find("td:eq(0)").text("Check Payments from Lockbox");
	totalRow.find("td:eq(1)").text('$' + numberWithCommas(sum.toFixed(2)));
	theTbody.append(totalRow);
	
	theTbody.append(tSeperateRow.clone());
	theTbody.append(tSeperateRow.clone());
	theTbody.append(tSeperateRow.clone());
	
	titleRow = tTitleRow.clone();
	titleRow.find('td:eq(0)').text('ACH Payments from Lockbox');
	theTbody.append(titleRow);
	
	headRow = tHeadRow.clone();
	theTbody.append(headRow);
	sum = 0;
	for (var i = 0; i < achs.length; i++) {
		var item = achs[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.paymentStatus ? item.paymentStatus : '');
		dataRow.find("td:eq(1)").text($.format.date(new Date(item.date).toString(), "MM/dd/yyyy"));
		dataRow.find("td:eq(2)").text(item.batchNumber);
		dataRow.find("td:eq(3)").text(item.csd);
		dataRow.find("td:eq(4)").text(item.amount ? '$' + numberWithCommas(item.amount.toFixed(2)) : '');
		dataRow.find("td:eq(5)").text($.format.date(new Date(item.birthDate).toString(), "MM/dd/yyyy"));
		dataRow.find("td:eq(6)").text(item.claimant);
		dataRow.find("td:eq(7)").text(item.balance ? '$' + numberWithCommas(item.balance.toFixed(2)) : '');
		dataRow.find("td:eq(8)").text(item.accountStatus);
		if (item.amount) {
			sum += item.amount;
		}
		theTbody.append(dataRow);
	}
	totalRow = tTotalRow.clone();
	totalRow.find("td:eq(0)").text("ACH Payments from Lockbox");
	totalRow.find("td:eq(1)").text('$' + numberWithCommas(sum.toFixed(2)));
	theTbody.append(totalRow);
	
	// grand total
	totalRow = tTotalRow.clone();
	totalRow.find("td:eq(0)").text("");
	totalRow.find("td:eq(1)").text('$' + numberWithCommas(response.totalPayment));
	theTbody.append(totalRow);
	
}

function renderReportCalculationAuditTrail(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).find(".reportDate").text(parseDateToString(response.reportGenerationDate));
	$(titleArea).children("h1").text(response.reportName + ' for CSD #' + response.csd);
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tSeperateRow = $("tr.sepRow", templateBody).clone();
	var tTitleRow = $("tr.tTitleRow", templateBody).clone();
	
	theTbody.html('');
	
	// render for each group
	for (var i = 0; i < response.groups.length; i++) {
		var group = response.groups[i];
		// 
		var titleRow = tTitleRow.clone();
		titleRow.find("td:eq(0)").text(group.action);
		titleRow.find("td:eq(1)").text(' ');
		titleRow.find("td:eq(3)").text("Cal as " + $.format.date(new Date(group.effective).toString(), "MM/dd/yyyy"));
		theTbody.append(titleRow);
		// render the table
		var headRow = tHeadRow.clone();
		theTbody.append(headRow);
		for (var j = 0; j < group.items.length; j++) {
			var item = group.items[j];
			var dataRow = tDataRow.clone();
			var td = dataRow.find("td:eq(0)");
			if (item.official) {
				td.text('X');
				td.addClass("underlineTxt");
			} else {
				td.text(' ');
				td.removeClass("underlineTxt");
			}
			
			dataRow.find("td:eq(1)").text(item.version);
			td = dataRow.find("td:eq(2)"); 
			td.text(item.line);
			td.removeClass("yellowCell");
			td.removeClass("greenCell");
			td.removeClass("redCell");
			if (item.inserted) {
				td.addClass("greenCell");
			} else if (item.updated) {
				td.addClass("yellowCell");
			} else if (item.deleted) {
				td.addClass("redCell");
			}
			dataRow.find("td:eq(3)").text(item.period);
			dataRow.find("td:eq(4)").text($.format.date(new Date(item.startDate).toString(), "MM/dd/yyyy"));
			dataRow.find("td:eq(5)").text($.format.date(new Date(item.endDate).toString(), "MM/dd/yyyy"));
			dataRow.find("td:eq(6)").text('$' + numberWithCommas(item.deduction));
			dataRow.find("td:eq(7)").text('$' + numberWithCommas(item.interest));
			dataRow.find("td:eq(8)").text('$' + numberWithCommas(item.payments));
			
			theTbody.append(dataRow);
		}
		theTbody.append(tSeperateRow.clone());
	}
	
	
}
function renderReportBalancedLockboxScorecard(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).children("h2").text('Fiscal Year ' + response.fiscalYear + ' - Quarter ' + response.quarter);
	var startDate = $.format.date(new Date(response.startDate), "MM/dd/yyyy");
	var endDate = $.format.date(new Date(response.endDate), "MM/dd/yyyy");
	$(titleArea).children("h3").text('Processed between ' + startDate + ' and ' + endDate);
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	var tHeadRow = $("tr.tHeadRow.t2Cols", templateBody).clone();
	var tDataRow = $("tr.tDataRow.t2Cols", templateBody).clone();
	var tSeperateRow = $("tr.tSeperateRow", templateBody).clone();
	var tTotalRow = $("tr.tTotalRow.t2Cols", templateBody).clone();
	var tTotalAllRow = $("tr.tTotalAllRow.t2Cols", templateBody).clone();
	
	// clear the tbody
	theTbody.html('');
	
	// changeRecordsItems
	theTbody.append(tHeadRow.clone());
	
	var items = response.changeRecordsItems;
	var sum = 0;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.importStatus);
		dataRow.find("td:eq(1)").text(numberWithCommas(item.itemNumber));
		sum += item.itemNumber;
		theTbody.append(dataRow);
	}
	var totalRow = tTotalRow.clone();
	totalRow.find("td:eq(0) strong").text('Change Record');
	totalRow.find("td:eq(1)").text(numberWithCommas(sum));
	theTbody.append(totalRow);
	
	theTbody.append(tSeperateRow.clone());
	
	var totalAllRow = tTotalAllRow.clone();
	totalAllRow.find("td:eq(1)").text(numberWithCommas(sum))
	theTbody.append(totalAllRow);
	theTbody.append(tSeperateRow.clone());
	
	tHeadRow = $("tr.tHeadRow.t3Cols", templateBody).clone();
	tDataRow = $("tr.tDataRow.t3Cols", templateBody).clone();
	tTotalRow = $("tr.tTotalRow.t3Cols", templateBody).clone();
	tTotalAllRow = $("tr.tTotalAllRow.t3Cols", templateBody).clone();
	
	// ACHs + Checks
	// 1.ACHs
	theTbody.append(tHeadRow.clone());
	items = response.achImportedItems;
	var sum1 = 0;
	var sum2 = 0;
	var totalSum1 = 0;
	var totalSum2 = 0;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.importStatus);
		dataRow.find("td:eq(1)").text(numberWithCommas(item.itemNumber));
		dataRow.find("td:eq(2)").text('$' + numberWithCommas(item.totalNumber.toFixed(2)));
		sum1 += item.itemNumber;
		sum2 += item.totalNumber;
		theTbody.append(dataRow);
	}
	totalRow = tTotalRow.clone();
	totalRow.find("td:eq(0) strong").text('ACHs');
	totalRow.find("td:eq(1)").text(numberWithCommas(sum1));
	totalRow.find("td:eq(2)").text('$' + numberWithCommas(sum2.toFixed(2)));
	theTbody.append(totalRow);
	theTbody.append(tSeperateRow.clone());
	totalSum1 += sum1;
	totalSum2 += sum2;
	
	// 2.Checks
	theTbody.append(tHeadRow.clone());
	items = response.checksImportedItems;
	sum1 = 0;
	sum2 = 0;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.importStatus);
		dataRow.find("td:eq(1)").text(numberWithCommas(item.itemNumber));
		dataRow.find("td:eq(2)").text('$' + numberWithCommas(item.totalNumber.toFixed(2)));
		sum1 += item.itemNumber;
		sum2 += item.totalNumber;
		theTbody.append(dataRow);
	}
	totalRow = tTotalRow.clone();
	totalRow.find("td:eq(0) strong").text('Checks');
	totalRow.find("td:eq(1)").text(numberWithCommas(sum1));
	totalRow.find("td:eq(2)").text('$' + numberWithCommas(sum2.toFixed(2)));
	theTbody.append(totalRow);
	theTbody.append(tSeperateRow.clone());
	
	totalSum1 += sum1;
	totalSum2 += sum2;
	// total all of ACHs + checks
	totalAllRow = tTotalAllRow.clone();
	totalAllRow.find("td:eq(1)").text(numberWithCommas(totalSum1));
	totalAllRow.find("td:eq(2)").text('$' + numberWithCommas(totalSum2.toFixed(2)));
	theTbody.append(totalAllRow);
	theTbody.append(tSeperateRow.clone());
	
	// achReversedItems
	totalSum1 = 0;
	totalSum2 = 0;
	theTbody.append(tHeadRow.clone());
	items = response.achReversedItems;
	sum1 = 0;
	sum2 = 0;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.importStatus);
		dataRow.find("td:eq(1)").text(numberWithCommas(item.itemNumber));
		dataRow.find("td:eq(2)").text('$(' + numberWithCommas(item.totalNumber.toFixed(2)) + ')');
		sum1 += item.itemNumber;
		sum2 += item.totalNumber;
		theTbody.append(dataRow);
	}
	totalRow = tTotalRow.clone();
	totalRow.find("td:eq(0) strong").text('ACHs');
	totalRow.find("td:eq(0) span").text('Reversed from Lockbox');
	totalRow.find("td:eq(1)").text(numberWithCommas(sum1));
	totalRow.find("td:eq(2)").text('($' + numberWithCommas(sum2.toFixed(2)) + ')');
	theTbody.append(totalRow);
	theTbody.append(tSeperateRow.clone());
	totalSum1 += sum1;
	totalSum2 += sum2;
	
	// Debit 
	theTbody.append(tHeadRow.clone());
	items = response.debitVouchersItems;
	sum1 = 0;
	sum2 = 0;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.importStatus);
		dataRow.find("td:eq(1)").text(numberWithCommas(item.itemNumber));
		dataRow.find("td:eq(2)").text('($' + numberWithCommas(item.totalNumber.toFixed(2)) + ')');
		sum1 += item.itemNumber;
		sum2 += item.totalNumber;
		theTbody.append(dataRow);
	}
	totalRow = tTotalRow.clone();
	totalRow.find("td:eq(0) strong").text('Debit Vouchers');
	totalRow.find("td:eq(0) span").text('from Lockbox');
	totalRow.find("td:eq(1)").text(numberWithCommas(sum1));
	totalRow.find("td:eq(2)").text('($' + numberWithCommas(sum2.toFixed(2)) + ')');
	theTbody.append(totalRow);
	theTbody.append(tSeperateRow.clone());
	
	totalSum1 += sum1;
	totalSum2 += sum2;
	
	// total all of ACHs reverse + Debit
	totalAllRow = tTotalAllRow.clone();
	totalAllRow.find("td:eq(0)").text('Total Reversed From This Quarter');
	totalAllRow.find("td:eq(1)").text(numberWithCommas(totalSum1));
	totalAllRow.find("td:eq(2)").text('($' + numberWithCommas(totalSum2.toFixed(2)) + ')');
	theTbody.append(totalAllRow);
	
	
}

function renderReportBalancedScorecardPayment(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).children("h2").text('Fiscal Year ' + response.fiscalYear + ' - Quarter ' + response.quarter);
	var startDate = $.format.date(new Date(response.startDate), "MM/dd/yyyy");
	var endDate = $.format.date(new Date(response.endDate), "MM/dd/yyyy");
	$(titleArea).children("h3").text('Processed between ' + startDate + ' and ' + endDate);
	
	
	// find all the rows template
	var theTbody = $("table.reportRealTable1 tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	
	// row types
	var t3HeadRow = $("tr.tHeadRow.t3Cols", templateBody).clone();
	var t3DataRow = $("tr.tDataRow.t3Cols", templateBody).clone();
	var t3TotalRow = $("tr.tTotalRow.t3Cols", templateBody).clone();
	
	var t4HeadRow = $("tr.tHeadRow.t4Cols", templateBody).clone();
	var t4DataRow = $("tr.tDataRow.t4Cols", templateBody).clone();
	var t4TotalRow = $("tr.tTotalRow.t4Cols", templateBody).clone();
	var t4HigherTotalRow = $("tr.tTotalHigherRow.t4Cols", templateBody).clone();
	
	var tDoubleLineRow = $("tr.doubleLineRow", templateBody).clone();
	var tMessageRow = $("tr.tMessageRow", templateBody).clone();
	var tSeperateRow = $("tr.tSeperateRow", templateBody).clone();
	var isUsingBracket = false;
	
	function wrapBracket(str) {
		if (isUsingBracket) {
			return '($' + str + ')';
		} else {
			return '$' + str;
		}
	}
	
	function addHeaderRow(colNumber, name) {
		var headRow = t3HeadRow.clone();
		if (colNumber == 4) {
			headRow = t4HeadRow.clone();
		}
		headRow.find("td:eq(0)").text(name);
		theTbody.append(headRow);
	}
	
	function addDataRow(colNumber, item) {
		var dataRow = t3DataRow.clone();
		if (colNumber == 4) {
			dataRow = t4DataRow.clone();
		}
		dataRow.find("td:eq(0)").text(item.name);
		dataRow.find("td:eq(1)").text(item.number);
		dataRow.find("td:eq(2)").text(wrapBracket(numberWithCommas(item.total)));
		if (colNumber == 4) {
			dataRow.find("td:eq(3)").text(item.suspended ? item.suspended : "");
		}
		theTbody.append(dataRow);
	}
	
	function addTotalRow(colNumber, name, sum1, sum2) {
		var totalRow = t3TotalRow.clone();
		if (colNumber == 4) {
			totalRow = t4TotalRow.clone();
		}
		totalRow.find("td:eq(0)").text(name);
		totalRow.find("td:eq(1)").text(sum1);
		totalRow.find("td:eq(2)").text(wrapBracket(numberWithCommas(sum2)));
		theTbody.append(totalRow);
	}
	
	// render table 1
	theTbody.html('');
	
	var finalTotal = 0;
	var items = response.finalCheckBatchProcessItem;
	isUsingBracket = true;
	addHeaderRow(3, "Final Check from Batch Process");
	addDataRow(3, items.batchProcessingTotal);
	addTotalRow(3, "Batch Process Subtotal", items.batchProcessingTotal.number, items.batchProcessSubTotal);
	finalTotal +=  items.batchProcessingTotal.number;
	theTbody.append(tSeperateRow.clone());
	isUsingBracket = false;
	
	items = response.finalAchLockboxItem;
	addHeaderRow(4, "Final ACH from Lockbox Bank");
	addDataRow(4, items.debitVoucher);
	addDataRow(4, items.directPayLife);
	addDataRow(4, items.postedCompleteResolved);
	addDataRow(4, items.postedComplete);
	addDataRow(4, items.suspenseRefundComplete);
	var sum = items.debitVoucher.number + items.directPayLife.number + items.postedCompleteResolved.number +  items.postedComplete.number + items.suspenseRefundComplete.number;
	addTotalRow(4, "ACH Subtotal", sum, items.achSubTotal);
	finalTotal += sum;
	theTbody.append(tSeperateRow.clone());
	
	items = response.finalCheckLockboxItem;
	addHeaderRow(4, "Final Check from Lockbox Bank");
	addDataRow(4, items.annuityComplete);
	addDataRow(4, items.directPayLife);
	addDataRow(4, items.postedCompleteResolved);
	addDataRow(4, items.postedComplete);
	addDataRow(4, items.suspenseRefund);
	addDataRow(4, items.voluntaryContributions);
	addTotalRow(4, items.checkSubTotal.name, items.checkSubTotal.number, items.checkSubTotal.total);
	sum = items.annuityComplete.number + items.directPayLife.number + 
	items.postedCompleteResolved.number + items.postedComplete.number 
	+ items.suspenseRefund.number + items.voluntaryContributions.number + items.checkSubTotal.number;
	addTotalRow(4, "Lockbox Bank Subtotal", items.checkSubTotal.number, items.checkSubTotal.total);
	finalTotal += sum;
	theTbody.append(tSeperateRow.clone());
	
	items = response.finalCheckOverTheCounterItem;
	addHeaderRow(3, "Final Check from Over-thecounter");
	addDataRow(3, items.postedComplete);
	addTotalRow(3, "Over-the-counter Subtotal", items.postedComplete.number, items.overTheCounterSubTotal);
	finalTotal += items.postedComplete.number;
	theTbody.append(tSeperateRow.clone());
	theTbody.append(tSeperateRow.clone());
	
	addTotalRow(3, "Final Payment Totals", finalTotal, response.finalPaymentTotal);
	
	
	// render table 2
	theTbody = $("table.reportRealTable2", panel);
	theTbody.html('');
	var finalTotal1 = finalTotal;
	finalTotal = 0;
	
	items = response.pendingAchLockboxItem;
	addHeaderRow(4, "Pending ACH from Lockbox Bank");
	addDataRow(4, items.directPayLife);
	addDataRow(4, items.postedPendingResolved);
	addDataRow(4, items.postedPending);
	addDataRow(4, items.suspended);
	addDataRow(4, items.suspendedRefund);
	addDataRow(4, items.unresolved);
	sum = items.directPayLife.number + items.postedPendingResolved.number + items.postedPending.number + items.suspended.number + items.suspendedRefund.number + items.unresolved.number;
	finalTotal += sum;
	addTotalRow(4, "ACH Subtotal", sum, items.achSubTotal);
	theTbody.append(tSeperateRow.clone());
	
	items = response.pendingAdjustmentLockboxItem;
	addHeaderRow(4, "Pending Adjustment from Lockbox Bank");
	addDataRow(4, items.postedPendingApproval);
	finalTotal += items.postedPendingApproval.number;
	addTotalRow(4, "Adjustment Subtotal", items.postedPendingApproval.number, items.adjustmentSubTotal);
	
	items = response.pendingCheckLockboxItem;
	addHeaderRow(4, "Pending Check from Lockbox Bank");
	addDataRow(4, items.postedPending);
	addDataRow(4, items.suspenseRefund);
	addDataRow(4, items.unresolved);
	sum = items.postedPending.number + items.suspenseRefund.number + items.unresolved.number;
	finalTotal += sum;
	addTotalRow(4, "Check Subtotal", sum, items.checkSubTotal);
	theTbody.append(tSeperateRow.clone());
	
	items = response.pendingCheckOverTheCounterItem;
	addHeaderRow(3, "Pending Check from Over-the-counter");
	addDataRow(3, items.postedPending);
	addDataRow(3, items.postedComplete);
	sum += items.postedPending.number + items.postedComplete.number;
	var tmpTotal = items.postedPending.total + items.postedComplete.total;
	finalTotal += sum;
	addTotalRow(3, "Over-the-counter Subtotal", sum, tmpTotal);
	finalTotal += items.postedComplete.number;
	theTbody.append(tSeperateRow.clone());
	theTbody.append(tSeperateRow.clone());
	
	addTotalRow(3, "Pending Payment Totals", finalTotal, response.pendingPaymentTotal);
	addTotalRow(3, "Grand Total", finalTotal + finalTotal1, response.grandTotal);
	
	// Sum of resolved suspense payments = 242
	
}

function renderReportBalancedScorecard(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).children("h1").text(response.reportName);
	$(titleArea).children("h2").text('Fiscal Year ' + response.fiscalYear + ' - Quarter ' + response.quarter);
	var startDate = $.format.date(new Date(response.startdate), "MM/dd/yyyy");
	var endDate = $.format.date(new Date(response.end), "MM/dd/yyyy");
	$(titleArea).children("h3").text('Processed between ' + startDate + ' and ' + endDate);
	
	
	// find all the rows template
	var theTbody = $("table.reportRealTable tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tSeperateRow = $("tr.sepRow", templateBody).clone();
	var tTotalRow = $("tr.tTotalRow", templateBody).clone();
	// clear the tbody
	theTbody.html('');
	
	// closedAccountItems
	var headRow = tHeadRow.clone();
	headRow.find("td:eq(0)").text('Closed Accounts');
	theTbody.append(headRow);
	var items = response.closedAccountItems;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(1)").text(item.accountType);
		dataRow.find("td:eq(2)").text(item.accountStatus);
		dataRow.find("td:eq(3)").text(item.number);
		theTbody.append(dataRow);
	}
	
	var totalRow = tTotalRow.clone();
	totalRow.find("td:eq(2)").text('Total Number of Closed Accounts');
	totalRow.find("td:eq(3)").text(response.closedAccountTotal);
	
	theTbody.append(totalRow);
	theTbody.append(tSeperateRow.clone());
	
	// initialBillingItems
	headRow = tHeadRow.clone();
	headRow.find("td:eq(0)").text('Initial Billings');
	theTbody.append(headRow);
	items = response.initialBillingItems;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(1)").text(item.accountType);
		dataRow.find("td:eq(2)").text(item.accountStatus);
		dataRow.find("td:eq(3)").text(item.number);
		theTbody.append(dataRow);
	}
	
	totalRow = tTotalRow.clone();
	totalRow.find("td:eq(2)").text('Total Number of Initial Billings');
	totalRow.find("td:eq(3)").text(response.initialBillingTotal);
	
	theTbody.append(totalRow);
	theTbody.append(tSeperateRow.clone());
	
	// newAccountItems
	headRow = tHeadRow.clone();
	headRow.find("td:eq(0)").text('New Accounts');
	theTbody.append(headRow);
	items = response.newAccountItems;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(1)").text(item.accountType);
		dataRow.find("td:eq(2)").text(item.accountStatus);
		dataRow.find("td:eq(3)").text(item.number);
		theTbody.append(dataRow);
	}
	
	totalRow = tTotalRow.clone();
	totalRow.find("td:eq(2)").text('Total Number of New Accounts');
	totalRow.find("td:eq(3)").text(response.newAccountTotal);
	
	theTbody.append(totalRow);;
}
function renderReportAccountsBalanceHistory(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).find(".reportDate").text(parseDateToString(response.reportGenerationDate));
	$(titleArea).children("h1").text(response.reportName + ' for CSD #' + response.csd);
	
	// find all the rows template
	var theTbody = $("table.accountsBalanceHistoryTbl tbody", panel);
	var templateBody = $("table.reportTemplateTable tbody", panel);
	var tTitleRow = $("tr.tTitleRow", templateBody).clone();
	var tHeadRow = $("tr.tHeadRow", templateBody).clone();
	var tDataRow = $("tr.tDataRow", templateBody).clone();
	var tSeperateRow = $("tr.tSeperateRow", templateBody).clone();
	var tGrandTotalRow = $("tr.tGrandTotalRow", templateBody).clone();
	var tSubTotalRow = $("tr.tSubTotalRow", templateBody).clone();
	// clear the tbody
	theTbody.html('');
	
	var tables = response.items;
	var grandTotal = 0;
	for (var key in tables) {
		
		var theTable = tables[key];
		var theTableTitle = key;
		
		var rowData = theTable;
		
		// render the table
		var theTitle = tTitleRow.clone();
		$(".titleCell", theTitle).text($.format.date(theTableTitle, "ddd, MMMM d, yyyy"));
		theTbody.append(theTitle);
		theTbody.append(tHeadRow.clone());
		var subTotal = 0;
		for (var i = 0; i < rowData.length; i++) {
			var theRow = tDataRow.clone();
			var rowObj = rowData[i];
			// audit time: 12:24 pm
			theRow.find("td:eq(0)").text($.format.date(new Date(rowObj.auditTime).toString(), "HH:mm p"));
			// Audit User
			theRow.find("td:eq(1)").text(rowObj.auditUser);
			// C
			theRow.find("td:eq(2)").text(rowObj.payCode);
			// Status
			theRow.find("td:eq(3)").text(rowObj.status);
			// Last Pay
			theRow.find("td:eq(4)").text($.format.date(new Date(rowObj.lastPay).toString(), "MM/dd/yyyy"));
			// Deposits
			theRow.find("td:eq(5)").text('$' + rowObj.deposit);
			// Add Interest
			theRow.find("td:eq(6)").text('$' + rowObj.additionalInterest);
			// Payment
			theRow.find("td:eq(7)").text('$' + rowObj.payment);
			// Balance
			theRow.find("td:eq(8)").text('$' + rowObj.balance);
			theTbody.append(theRow);
			subTotal += rowObj.subTotals;
			
		}
		var subTotalRow = tSubTotalRow.clone();
		subTotalRow.find("span").text(formatReportMoney(subTotal));
		grandTotal += subTotal;
		theTbody.append(subTotalRow);
		theTbody.append(tSeperateRow.clone());
	}
	var grandTotalRow = tGrandTotalRow.clone();
	grandTotalRow.find("span").text(formatReportMoney(grandTotal));
	theTbody.append(grandTotalRow);
}


function renderReportAccountNotesByUser(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).find(".reportDate").text(parseDateToString(response.reportGenerationDate));
	$(titleArea).children("h1").text(response.reportName);
	
	var groups = {};
	// assuming that all items are sort in auditDateTime
	var items = response.items;
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var key = $.format.date(new Date(item.auditDateTime).toString(), "MM/dd/yyyy") + " - " + item.userDescription;
		var theList = groups[key];
		if (theList == null) {
			theList = [];
			groups[key] = theList;
		}
		theList.push(item);
	}
	
	var theTable = $('table.AccountNotesByUserTbl', panel);
	var theTbody = $('tbody', theTable);
	var templateTbody = $('table.reportTemplateTable tbody', panel);
	var tTitleRow = $("tr.tTitleRow", templateTbody).clone();
	var tHeadRow = $("tr.tHeadRow", templateTbody).clone();
	var tDataRow = $("tr.tDataRow", templateTbody).clone();
	var tSeperateRow = $("tr.tSeperateRow", templateTbody).clone();
	
	// clear the body
	theTbody.html('');
	
	for (var key in groups) {
		var theList = groups[key];
		var titleRow = tTitleRow.clone();
		titleRow.find("td:eq(0)").text(key);
		theTbody.append(titleRow);
		theTbody.append(tHeadRow.clone());
		for (var i = 0; i < theList.length; i++) {
			var item = theList[i];
			var dataRow = tDataRow.clone();
			dataRow.find("td:eq(0)").text($.format.date(new Date(item.auditDateTime).toString(), "HH:mm p"));
			dataRow.find("td:eq(1)").text(item.databaseTable == null ? '' : item.databaseTable);

			dataRow.find("td:eq(2)").text(item.activity);
			dataRow.find("td:eq(3)").text(item.csd);
			dataRow.find("td:eq(4)").text(item.description);
			theTbody.append(dataRow);
		}
		theTbody.append(tSeperateRow.clone());
	}
	
	
}

function renderReportAccountTypeTotals(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).find(".printDate").text("Printed on " + $.format.date(new Date(response.reportGenerationDate).toString(), "ddd, MMMM d, yyyy"));
	$(titleArea).children("h1").text(response.reportName);
	
	var theTable = $('table.reportRealTable', panel);
	var theTbody = $('tbody', theTable);
	var templateTbody = $('table.reportTemplateTable tbody', panel);
	var tTotalRow = $("tr.tTotalRow", templateTbody).clone();
	var tTitleRow = $("tr.tTitle", templateTbody).clone();
	var tTotalAllRow = $("tr.tTotalAllRow", templateTbody).clone();
	var tHeadRow = $("tr.tHeadRow", templateTbody).clone();
	var tDataRow = $("tr.tDataRow", templateTbody).clone();
	var tSeperateRow = $("tr.tSeperateRow", templateTbody).clone();
	
	theTbody.html('');
	// print the title
	$('td:eq(0)', tTitleRow).text($.format.date(new Date(response.reportGenerationDate).toString(), "ddd, MMMM d, yyyy"));
	theTbody.append(tTitleRow);
	
	var csrsSum = 0;
	var fersSum = 0;
	
	// print the CSRS
	var headRow = tHeadRow.clone();
	$('td:eq(0)', headRow).text('CSRS');
	theTbody.append(headRow);
	for (var key in response.csrsAccounts) {
		var value = response.csrsAccounts[key];
		csrsSum += value;
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(1)").text(key);
		dataRow.find("td:eq(2)").text(value);
		theTbody.append(dataRow);
	}
	var totalRow = tTotalRow.clone();
	totalRow.find("td:eq(1)").text('Number of CSRS Accounts');
	totalRow.find("td:eq(2)").text(csrsSum);
	theTbody.append(totalRow);
	theTbody.append(tSeperateRow.clone());
	
	// print the FERS
	headRow = tHeadRow.clone();
	$('td:eq(0)', headRow).text('FERS');
	theTbody.append(headRow);
	for (var key in response.fersAccounts) {
		var value = response.fersAccounts[key];
		fersSum += value;
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(1)").text(key);
		dataRow.find("td:eq(2)").text(value);
		theTbody.append(dataRow);
	}
	totalRow = tTotalRow.clone();
	totalRow.find("td:eq(1)").text('Number of FERS Accounts');
	totalRow.find("td:eq(2)").text(fersSum);
	theTbody.append(totalRow);
	theTbody.append(tSeperateRow.clone());
	
	// print the UNKNOW
	headRow = tHeadRow.clone();
	$('td:eq(0)', headRow).text('UNKNOWN');
	theTbody.append(headRow);
	var dataRow = tDataRow.clone();
	dataRow.find("td:eq(1)").text("New Account");
	dataRow.find("td:eq(2)").text(response.unknownNewAccounts);
	theTbody.append(dataRow);
	dataRow = tDataRow.clone();
	dataRow.find("td:eq(1)").text("Active");
	dataRow.find("td:eq(2)").text(response.unknownActiveAccounts);
	theTbody.append(dataRow);
	theTbody.append(tSeperateRow.clone());
	
	// print the TOTAL
	totalAllRow = tTotalAllRow.clone();
	totalAllRow.find("td:eq(2)").text(response.totalAccounts);
	theTbody.append(totalAllRow);
	
	
}

function renderReportActiveCreditBalancesOver25Dollars(panel, response) {
	var titleArea = $(".reportTitleArea", panel);
	$(titleArea).find(".reportDate").text("Printed on " + $.format.date(new Date(response.reportGenerationDate).toString(), "MM/dd/yyyy"));
	$(titleArea).children("h1").text(response.reportName);
	

	var theTable = $('table.reportRealTable', panel);
	var theTbody = $('tbody', theTable);
	var templateTbody = $('table.reportTemplateTable tbody', panel);
	var tHeadRow = $("tr.tHeadRow", templateTbody).clone();
	var tDataRow = $("tr.tDataRow", templateTbody).clone();
	
	theTbody.html('');
	theTbody.append(tHeadRow.clone());
	for (var i = 0; i < response.items.length; i++) {
		var item = response.items[i];
		var dataRow = tDataRow.clone();
		dataRow.find("td:eq(0)").text(item.csd);
		dataRow.find("td:eq(1)").text('($' + item.creditBalance.toFixed(2) + ')');
		dataRow.find("td:eq(2)").text($.format.date(new Date(item.dateOfOverPayment).toString(), "MM/dd/yyyy"));
		theTbody.append(dataRow);
	}
	
}

function populateReportRequest(panel) {
	if (panel.length == 0) {
		// empty request
		return {};
	}
	var ok = true;
	var msg = "";
	var request = {};
	$('input[type="text"]', panel).each(function() {
		if (!$(this).attr('name')) {
			// ignore
			return;
		}
		if ($(this).val() == null || $(this).val().length == 0) {
			msg = $(this).attr('name') + ' cannot be empty'; 
			ok = false;
			return false;
		}
		if ($(this).hasClass("datePicker")) {
			// validate for a date
			var dateStr = $(this).val();
			
			if (!isDate(dateStr)) {
				msg = $(this).attr('name') + ' is not a valid date';
				ok = false;
				return false;
			}
		} else if ($(this).hasClass("monthPicker")) {
			// 
		} else {
			// validate for a number
			if (!$(this).val().match(/\d+/)) {
				msg = $(this).attr('name') + ' is not a valid number';
				ok = false;
				return false;
			}
		}
		if ($(this).hasClass("datePicker") || $(this).hasClass("monthPicker")) {
			request[$(this).attr('name')] = Date.parse($(this).val());
		} else {
			request[$(this).attr('name')] = $(this).val();	
		}
	}); 
	if (!ok) {
		alert(msg);
		return false;
	}
	return request;
}

function sendReportingRequest(reportName, request) {
	if (!request) {
		request = {};
	}
	var context = $('#context').val();
	$.ajax({
		url:context + '/report/get',
		type:"POST",
		data:{
			reportName:reportName,
			requestJSON:JSON.stringify(request)
		},
		success:function(response) {
			// display the page
			// get the current tab
			var tab = $('#' + currentReportTab);
			var panel = $('.report-' + reportName, tab);
			if (panel.length == 0) {
				// does nothing
				return;
			}
			panel.removeClass("isHidden");
			
			// render the page
			if (window["renderReport" + reportName]) {
				window["renderReport" + reportName] && window["renderReport" + reportName](panel, response);
			} else {
				// temporary
				var content = $("table.reportTemplateTable tbody", panel).clone();
				$("table.reportRealTable", panel).each(function() {
					$(this).find("tbody").remove("tbody");
					$(this).append(content);
				});
			}
		},
		error:function() {
			alert("Data does not exist.");
		}
	});
}

function createTreeView() {
	// remove all the display trees from the dom
	$('.displayTree').each(function() {
		$(this).remove();
	});
	
	var currentTab = $('#' + currentReportTab);
	var theTree = $('.sampleTree', currentTab).clone();
	theTree.removeClass("isHidden");
	theTree.removeClass("sampleTree");
	theTree.addClass("displayTree");
	// insert to the tab
	$('.sampleTree', currentTab).after(theTree);
	theTree.treeview();
	var firstLevelNode = theTree.children("li");
	if (firstLevelNode.length === 1){
		firstLevelNode.addClass("onlyNode");
	}
	theTree.on("click", "a", function(event){
		theTree.find("a").removeClass("selected");
		$(this).addClass("selected");
		
		var reportName = $(this).data('report-name');
		
		gPreviousReportNames[currentReportTab] = reportName;
		if (reportName != null && $(this).hasClass("pageContentLink")) {
			var tab = $(this).closest('.reportsTab');
			$('.rightReportWrapper > .viewReportWrapper', tab).each(function() {
				$(this).addClass("isHidden");
			});
			$('.rightReportWrapper .editReportWrapper').addClass('isHidden');
			
			var currentViewReport = $('.rightReportWrapper > .viewReportWrapper > .report-' + reportName).parent();
			currentViewReport.removeClass("isHidden");
			
			// hide the buttons
			$('.reportsActionsWrapper .priBtn', tab).each(function() {
				$(this).addClass("isHidden");
			});
			
			// only keep print
			$('.reportsActionsWrapper .jsPrintReport', tab).removeClass("isHidden");
			if (currentReportTab != "reportsPage") {
				$('.reportsActionsWrapper .jsCreateReport', tab).removeClass("isHidden");
			}
			event.preventDefault();
			return;
		}
		
		if (currentReportTab == "reportsPage") {
			
			
			var reportName = $(this).data('report-name');
			if (reportName == null) {
				// not supported yet
				alert("This report is not supported yet. (To confirm with copilot)");
				event.preventDefault();
				return;
			}
			var tab = $(this).closest('.reportsTab');
			$('.rightReportWrapper > .viewReportWrapper', tab).each(function() {
				$(this).addClass("isHidden");
			});
			
			var currentViewReport = $('.rightReportWrapper > .viewReportWrapper > .report-' + reportName).parent();
			currentViewReport.removeClass("isHidden");
			
			if ($('.reportParameterSetting', currentViewReport).length == 0) {
				// no parameters, send the ajax requests directly
				sendReportingRequest(reportName);
			} 
		} else if (currentReportTab == "correspondencePage") {
			var reportName = $(this).data('report-name');
			$('#correspondencePage .rightReportWrapper .viewReportWrapper:visible').each(function() {
				$(this).addClass("isHidden");
			});
			displayLetter(reportName);
		} else if (currentReportTab == "referencePage") {
			var reportName = $(this).data('report-name');
			$('#referencePage .rightReportWrapper .viewReportWrapper:visible').each(function() {
				$(this).addClass("isHidden");
			});
			displayLetter(reportName);
		} 
		
		event.preventDefault();
	});
	
	equalizeReportHeight();
}

function displayLetter(letterId) {
	// display the buttons
	var buttons = $('#' + currentReportTab + ' .reportsActionsWrapper');
	$(".jsDelReport", buttons).removeClass("isHidden");
	$(".jsEditReport", buttons).removeClass("isHidden");
	$(".jsCreateReport", buttons).removeClass("isHidden");
	$(".jsPrintReport", buttons).removeClass("isHidden");
	$(".jsCancelSaveReport", buttons).addClass("isHidden");
	$(".jsSaveReport", buttons).addClass("isHidden");
	
	var context = $('#context').val();
	var path = '/letter/';
	if (currentReportTab == "referencePage") {
		path = '/reference/';
	} 
	$.ajax({
		url:context + path + letterId,
		type:"GET",
		dataType:"json",
		async:true,
		success:function(letter) {
			var viewReport = $('#' + currentReportTab + ' .report-LetterReport').parent();
			if (viewReport.length == 0) {
				return;
			}
			viewReport.find(".reportTitle").text(letter.name);
			viewReport.find(".letterContent").html(letter.content);
			viewReport.removeClass("isHidden");
		},
		error:function() {
			alert('failed to get the letter');
		}
	});
}

//Report page equalize height
function equalizeReportHeight(){
	var currentTab = $('#' + currentReportTab);
	if ( $(".leftTree", currentTab).length > 0 ){
		var rightHeight = parseInt($(".rightReportWrapper", currentTab).height(), 10);
		$(".leftTreeWrapper", currentTab).css({
			"min-height": (rightHeight - 24)
		});
	}
}






