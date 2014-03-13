
<div class="viewReportWrapper isHidden" data-report-name="DailyReconciliationReport">
	<div class="reportParameterSetting roundedGrayPanel">
		<div class="paramRow group">
			<label class="fLeft">@BatchDate</label> <input type="text"
				class="text datePicker fLeft" id="batchDate" name="date"
				value="" /> 
			<label class="fLeft inputGap" for="nullFlag">Null Flag</label>
				<input type="checkbox" class="fLeft" name="nullFlag" id="nullFlag"/>
				<a class="priBtn fRight jsRunReport" href="javascript:;"><span><span>Run
						Report</span></span></a>
		</div>
		<div class="corner cornerTl"></div>
		<div class="corner cornerTr"></div>
		<div class="corner cornerBl"></div>
		<div class="corner cornerBr"></div>
	</div>
	<div class="reportPageReportWrapper report-DailyReconciliationReport isHidden">
		<div class="reportTitleArea">
			<h1>Service Credit Billing and Collection System</h1>
			<h1 class="reportTile">Daily Reconciliation Report</h1>
			<p class="reportDate">Date</p>
		</div>
		<div class="reportTblWrapper">
			<table width="100%" cellspacing="0" cellpadding="0" border="0"
				class="blackTbl dailyReconciliationTbl reportRealTable">
				<colgroup>
					<col class="col0" />
					<col class="col1" />
					<col class="col2" />
					<col class="col3" />
					<col class="col4" />
					<col class="col5" />
				</colgroup>
				<tbody></tbody>
			</table>
			<table class="reportTemplateTable isHidden">
				<tbody>
					<tr class="tHeadRow">
						<td class="blankTh">&nbsp;</td>
						<td># Items</td>
						<td>Receipts</td>
						<td>Processed</td>
						<td>Suspended</td>
						<td>Reversed</td>
					</tr>
					<tr class="tDataRow">
						<td class="boldTxt">Lockbox Total</td>
						<td>106</td>
						<td>$ 44,978.44</td>
						<td>$ 43,978.44</td>
						<td>$ 1,000.00</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="tTotalRow">
						<td class="boldTxt">Funds Control Total</td>
						<td>6</td>
						<td>$ 41,176.20</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>