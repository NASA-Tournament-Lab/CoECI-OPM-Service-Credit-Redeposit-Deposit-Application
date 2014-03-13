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

/* You can change these values */

var europeandate = true;
var alternate_row_colors = false;

/* Don't change anything below this unless you know what you're doing */
//addEvent(window, "load", sortables_init);

var SORT_COLUMN_INDEX;
var thead = false;
$(document).ready(function () {
	//Sort table
	$('.sortable').delegate('th', 'click', function(e) {
		var tbl = $(this).parents("table").eq(0);
		var sortLink = $(".sortheader", $(this));
		e.stopPropagation;
		if (sortLink.length > 0){
			$("th", tbl).removeClass("current");
			$(this).addClass("current");
			var idx = sortLink.attr("alt");
			ts_resortTable(sortLink[0], idx);
		}
		var entriesTblRows = $("tbody tr", tbl);
		entriesTblRows.removeClass("valErrorRowBefore").removeClass("valErrorRowAfter");
		$(".valErrorRow").prev("tr").addClass("valErrorRowBefore");
		$(".valErrorRow").next("tr").addClass("valErrorRowAfter");
	});

	sortables_init();
	
});

/**
 * This function check whether or not a string represents
 * a valid number integer or decimal
 * @param {string} n a number
 * @return true if it is a valid number
 */
var isNumber = function (n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
};

function sortables_init() {
	// Find all tables with class sortable and make them sortable
	if (!document.getElementsByTagName) return;
	tbls = document.getElementsByTagName("table");
	for (ti=0;ti<tbls.length;ti++) {
		thisTbl = tbls[ti];
		if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
			ts_makeSortable(thisTbl);
		}
	}	
	$("table.sortable").each(function(index, element) {
        var defaultSortCol = $(this).find(".defaultSortCol .sortheader");
		if (defaultSortCol.length < 1){
			var defaultSortCol = $(this).find(".sortheader").eq(0);
		}
		defaultSortCol.eq(0).trigger("click");
		if ( defaultSortCol.parent().hasClass("defaultSortDown") ){
			defaultSortCol.eq(0).trigger("click");
			defaultSortCol.eq(0).trigger("click");
			defaultSortCol.eq(0).trigger("click");
		}
    });
}

function ts_makeSortable(t) {
	if (t.rows && t.rows.length > 0) {
		if (t.tHead && t.tHead.rows.length > 0) {
			var firstRow = t.tHead.rows[t.tHead.rows.length-1];
			thead = true;
		} else {
			var firstRow = t.rows[0];
		}
	}
	if (!firstRow) return;
	
	// We have a first row: assume it's the header, and make its contents clickable links
	for (var i=0;i<firstRow.cells.length;i++) {
		var cell = firstRow.cells[i];
		//var txt = ts_getInnerText(cell);
		var txt = $(cell).html();
		if (cell.className != "unsortable" && cell.className.indexOf("unsortable") == -1) {
			cell.innerHTML = '<a href="javascript:;" class="sortheader" alt="'+i+'"><span class="sortLinkTxt">'+txt+'</span><span class="sortarrow sort-down" sortdir="down"></span></a>';
		}
	}
	if (alternate_row_colors) {
		alternate(t);
	}
}

function ts_getInnerText(el) {
	if (typeof el == "string") return el;
	if (typeof el == "undefined") { return el };
	if (el.innerText) return el.innerText;	//Not needed but it is faster
	var str = "";
	
	var cs = el.childNodes;
	var l = cs.length;
	for (var i = 0; i < l; i++) {
		switch (cs[i].nodeType) {
			case 1: //ELEMENT_NODE
				str += ts_getInnerText(cs[i]);
				break;
			case 3:	//TEXT_NODE
				str += cs[i].nodeValue;
				break;
		}
	}
	return str;
}

function ts_resortTable(lnk, clid) {
	var span;
	for (var ci=0;ci<lnk.childNodes.length;ci++) {
		if (lnk.childNodes[ci].tagName && lnk.childNodes[ci].tagName.toLowerCase() == 'span') span = lnk.childNodes[ci];
	}
	var spantext = ts_getInnerText(span);
	var td = lnk.parentNode;
	var column = clid || td.cellIndex;
	var t = getParent(td,'TABLE');
	// Work out a type for the column
	if (t.rows.length <= 1) return;
	var itm = "";
	var i = 0;
	if(t.tBodies && t.tBodies.length < 1) return;
	while (itm == "" && i < t.tBodies[0].rows.length) {
		var itm = ts_getInnerText(t.tBodies[0].rows[i].cells[column]);
		itm = trim(itm);
		if (itm.substr(0,4) == "<!--" || itm.length == 0) {
			itm = "";
		}
		i++;
	}
	if (itm == "") return; 
	sortfn = ts_sort_caseinsensitive;
	if (itm.match(/^\d\d[\/\.-][a-zA-z][a-zA-Z][a-zA-Z][\/\.-]\d\d\d\d$/)) sortfn = ts_sort_date;
	if (itm.match(/^\d\d[\/\.-]\d\d[\/\.-]\d\d\d{2}?$/)) sortfn = ts_sort_date;
	if (itm.match(/^-?[?$�ۢ�]\d/)) sortfn = ts_sort_numeric;
	if (itm.match(/^-?(\d+[,\.]?)+(E[-+][\d]+)?%?$/)) sortfn = ts_sort_numeric;

	sortfn = ts_sort_caseinsensitive;

	if(isNumber(itm.replace(/\$/,""))){
		sortfn = ts_sort_numeric;
	}
	var datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;

	if(itm.match(datePat)){
		sortfn = ts_sort_date;
	}

	if(td.className.indexOf('asNumeric') != -1){
		sortfn = ts_sort_numeric;	
	} else if(td.className.indexOf('asDate') != -1){
		sortfn = ts_sort_date;
	} else if(td.className.indexOf('asString') != -1){
		sortfn = ts_sort_caseinsensitive;	
	}

	SORT_COLUMN_INDEX = column;
	var firstRow = new Array();
	var newRows = new Array();

	// Bug Fix 2
	var noMoveRows = new Array();

	for (k=0;k<t.tBodies.length;k++) {
		for (i=0;i<t.tBodies[k].rows[0].length;i++) { 
			firstRow[i] = t.tBodies[k].rows[0][i]; 
		}
	}
	var counti = 0; // Bug Fix 2
	var countNo = 0; // Bug Fix 2

	for (k=0;k<t.tBodies.length;k++) {
		if (!thead) {
			// Skip the first row
			for (j=1;j<t.tBodies[k].rows.length;j++) {

				if(t.tBodies[k].rows[j].className.indexOf('unsortable') == -1){

					newRows[counti] = t.tBodies[k].rows[j];
					counti++;
				} 
				else {
					noMoveRows[countNo] = t.tBodies[k].rows[j];
					countNo++;
				}
				//newRows[j-1] = t.tBodies[k].rows[j];
			}
		} else {
			// Do NOT skip the first row
			for (j=0;j<t.tBodies[k].rows.length;j++) { 

				if(t.tBodies[k].rows[j].className.indexOf('unsortable') == -1){

					newRows[counti] = t.tBodies[k].rows[j];
					counti++;
				} 
				else {
					noMoveRows[countNo] = t.tBodies[k].rows[j];
					countNo++;
				}
				//newRows[j] = t.tBodies[k].rows[j];
			}
		}
	}
	//newRows.sort(sortfn);
	if (span.getAttribute("sortdir") == 'down') {
			ARROW = '&nbsp;&nbsp;';
			//newRows.reverse();
			newRows = newRows.mergeSort(function(a,b){ return -sortfn(a,b)});
			span.setAttribute('sortdir','up');
			span.className = "sortarrow";
	} else {
			ARROW = '&nbsp;&nbsp;';
			span.setAttribute('sortdir','down');
			span.className = "sortarrow sort-down";
			newRows = newRows.mergeSort(sortfn);
	} 
    // We appendChild rows that already exist to the tbody, so it moves them rather than creating new ones
    // don't do sortbottom rows
    for (i=0; i<newRows.length; i++) { 
		if (!newRows[i].className || (newRows[i].className && (newRows[i].className.indexOf('sortbottom') == -1))) {
			t.tBodies[0].appendChild(newRows[i]);
		}
	}
    // do sortbottom rows only
    for (i=0; i<newRows.length; i++) {
		if (newRows[i].className && (newRows[i].className.indexOf('sortbottom') != -1)) 
			t.tBodies[0].appendChild(newRows[i]);
	}

	for(i=0; i<noMoveRows.length;i++){
		t.tBodies[0].appendChild(noMoveRows[i]);
	}
	// Delete any other arrows there may be showing
	var allspans = document.getElementsByTagName("span");
	for (var ci=0;ci<allspans.length;ci++) {
		if (allspans[ci].className == 'sortarrow') {
			if (getParent(allspans[ci],"table") == getParent(lnk,"table")) { // in the same table as us?
				allspans[ci].innerHTML = '';
			}
		}
	}		
	span.innerHTML = ARROW;
	alternate(t);
}

function getParent(el, pTagName) {
	if (el == null) {
		return null;
	} else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase()) {
		return el;
	} else {
		return getParent(el.parentNode, pTagName);
	}
}

function sort_date(date) {	

	dt = new Date(0);
	if(date.length == 10){

		dt = new Date(date.substr(6,4),date.substr(0,2)-"01",date.substr(3,2));
		if(dt.toString().toLowerCase() != 'invalid date'){
			return dt;
		}
	}

	dt = date.replace(/ /g, "");

	var hours = dt.substr(10,2);
	var minutes = dt.substr(13,2);
	var year = dt.substr(6,4);
	var month = dt.substr(0,2)-"01";
	var day = dt.substr(3,2);

	if(dt.length == 17){

		if(dt.substr(15,2) == 'PM'){
			hours += "12";
		}
		dt = new Date(year, month, day, hours, minutes);

		if(dt.toString().toLowerCase() != 'invalid date'){
			return dt;
		}
	}


	// y2k notes: two digit years less than 50 are treated as 20XX, greater than 50 are treated as 19XX
	dt = "00000000";
	if (date.length == 11) {
		mtstr = date.substr(3,3);
		mtstr = mtstr.toLowerCase();
		switch(mtstr) {
			case "jan": var mt = "01"; break;
			case "feb": var mt = "02"; break;
			case "mar": var mt = "03"; break;
			case "apr": var mt = "04"; break;
			case "may": var mt = "05"; break;
			case "jun": var mt = "06"; break;
			case "jul": var mt = "07"; break;
			case "aug": var mt = "08"; break;
			case "sep": var mt = "09"; break;
			case "oct": var mt = "10"; break;
			case "nov": var mt = "11"; break;
			case "dec": var mt = "12"; break;
			// default: var mt = "00";
		}
		dt = date.substr(7,4)+mt+date.substr(0,2);
		return dt;
	} else if (date.length == 10) {
		if (europeandate == false) {
			dt = date.substr(6,4)+date.substr(0,2)+date.substr(3,2);
			return dt;
		} else {
			dt = date.substr(6,4)+date.substr(3,2)+date.substr(0,2);
			return dt;
		}
	} else if (date.length == 8) {
		yr = date.substr(6,2);
		if (parseInt(yr) < 50) { 
			yr = '20'+yr; 
		} else { 
			yr = '19'+yr; 
		}
		if (europeandate == true) {
			dt = yr+date.substr(3,2)+date.substr(0,2);
			return dt;
		} else {
			dt = yr+date.substr(0,2)+date.substr(3,2);
			return dt;
		}
	}
	return dt;
}

function ts_sort_date(a,b) {
	dt1 = sort_date(ts_getInnerText(a.cells[SORT_COLUMN_INDEX]));
	dt2 = sort_date(ts_getInnerText(b.cells[SORT_COLUMN_INDEX]));

	if(dt1 > dt2 ){
		return 1;
	}

	if(dt1 < dt2){
		return -1;
	}

	
	return 0;
	
	if (dt1==dt2) {
		return 0;
	}
	if (dt1<dt2) { 
		return -1;
	}
	return 1;
}
function ts_sort_numeric(a,b) {
	var aa = ts_getInnerText(a.cells[SORT_COLUMN_INDEX]);
	aa = clean_num(aa);
	var bb = ts_getInnerText(b.cells[SORT_COLUMN_INDEX]);
	bb = clean_num(bb);
	return compare_numeric(aa,bb);
}
function compare_numeric(a,b) {
	var a = parseFloat(a);
	a = (isNaN(a) ? 0 : a);
	var b = parseFloat(b);
	b = (isNaN(b) ? 0 : b);
	return a - b;
}
function ts_sort_caseinsensitive(a,b) {
	aa = ts_getInnerText(a.cells[SORT_COLUMN_INDEX]).toLowerCase();
	bb = ts_getInnerText(b.cells[SORT_COLUMN_INDEX]).toLowerCase();
	return aa.localeCompare(bb);
	if (aa==bb) {
		return 0;
	}
	if (aa<bb) {
		return -1;
	}
	return 1;
}
function ts_sort_default(a,b) {
	aa = ts_getInnerText(a.cells[SORT_COLUMN_INDEX]);
	bb = ts_getInnerText(b.cells[SORT_COLUMN_INDEX]);
	if (aa==bb) {
		return 0;
	}
	if (aa<bb) {
		return -1;
	}
	return 1;
}
function addEvent(elm, evType, fn, useCapture)
// addEvent and removeEvent
// cross-browser event handling for IE5+,	NS6 and Mozilla
// By Scott Andrew
{
	if (elm.addEventListener){
		elm.addEventListener(evType, fn, useCapture);
		return true;
	} else if (elm.attachEvent){
		var r = elm.attachEvent("on"+evType, fn);
		return r;
	} else {
		alert("Handler could not be removed");
	}
}
function clean_num(str) {
	str = str.replace(new RegExp(/[^-?0-9.]/g),"");
	return str;
}
function trim(s) {
	return s.replace(/^\s+|\s+$/g, "");
}
function alternate(table) {
	// Take object table and get all it's tbodies.
	var tableBodies = table.getElementsByTagName("tbody");
	// Loop through these tbodies
	for (var i = 0; i < tableBodies.length; i++) {
		// Take the tbody, and get all it's rows
		var tableRows = tableBodies[i].getElementsByTagName("tr");
		// Loop through these rows
		// Start at 1 because we want to leave the heading row untouched
		for (var j = 0; j < tableRows.length; j++) {

			if(!(tableRows[j].className.indexOf('unsortable') == -1)){
				continue;
			}
			// Check if j is even, and apply classes for both possible results
			if ( (j % 2) == 0  ) {
				if ( !(tableRows[j].className.indexOf('odd') == -1) ) {
					tableRows[j].className = tableRows[j].className.replace('odd', 'even');
				} else {
					if ( tableRows[j].className.indexOf('even') == -1 ) {
						tableRows[j].className += " even";
					}
				}
			} else {
				if ( !(tableRows[j].className.indexOf('even') == -1) ) {
					tableRows[j].className = tableRows[j].className.replace('even', 'odd');
				} else {
					if ( tableRows[j].className.indexOf('odd') == -1 ) {
						tableRows[j].className += " odd";
					}
				}
			} 
		}
	}
}
