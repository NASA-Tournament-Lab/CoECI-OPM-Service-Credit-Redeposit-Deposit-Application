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
    setupPage('NOTIFICATION_LOG', 9);

    function SortPaginateTable(table, pageSizeSelect, paginationDiv, resultSizeDiv, searchUrl, columnNames, desc, renderRowsFunction) {

        // Make the table sort headers, refer to sortable.js
        ts_makeSortable(table[0]);

        // Get the default sort column
        var defaultSortCol = table.find('.defaultSortCol', 'th').eq(0);
        if (defaultSortCol.length < 1){
            // Use first column to sort
            defaultSortCol = table.find('th').eq(0);
        }

        var sortColumn, sortOrder, pageNumber, pageSize, that = this;

        // The function to reset sortColumn, sortOrder, pageNumber, pageSize
        this.reset = function() {
            sortColumn = columnNames[defaultSortCol.index()];
            sortOrder = defaultSortCol.hasClass('defaultSortDown') ? 'DESC' : 'ASC';
            pageNumber = 1;
            pageSize = 10;
            pageSizeSelect.val(pageSize);
        };

        // On column header click
        table.delegate('th', 'click', function() {
            var sortLink = $(".sortheader", $(this));
            if (sortLink.length > 0) {
                sortColumn = columnNames[$(this).index()];
                sortOrder = sortLink.find('.sortarrow').hasClass('sort-down') ? 'DESC' : 'ASC';
                that.search();
            }
        });

        // On page size change
        pageSizeSelect.change(function() {
            pageNumber = 1;
            pageSize = pageSizeSelect.val();
            if (pageSize == 'All') {
                pageSize = 0;
            }
            that.search();
        });

        // On page number click
        paginationDiv.delegate('.jsGoPage', 'click', function() {

            if ($(this).hasClass('currentPage')) {
                return;
            }
            pageNumber = parseInt($(this).text(), 10);
            that.search();
        });

        // On prev page click
        paginationDiv.delegate('.jsGoPrev', 'click', function() {
            if ($(this).hasClass('paginationBtnDisabled')) {
                return;
            }
            pageNumber--;
            that.search();
        });

        // On next page click
        paginationDiv.delegate('.jsGoNext', 'click', function() {
            if ($(this).hasClass('paginationBtnDisabled')) {
                return;
            }
            pageNumber++;
            that.search();
        });

        // The search function
        this.search = function() {

            var searchRequest = {};
            // PageSize = 0 means search All
            searchRequest.pageNumber = pageSize == 0 ? 0 : pageNumber;
            searchRequest.pageSize = pageSize;
            searchRequest.sortColumn = sortColumn;
            searchRequest.sortOrder = sortOrder;

            $.ajax({
                url : searchUrl,
                type : 'POST',
                cache : false,
                async : true,
                contentType : 'application/json',
                data : JSON.stringify(searchRequest),
                success : function(result) {
                    markAllAsRead(result.items);
                    // Render the rows in table
                    renderRowsFunction(result);

                    // Render sort column header
                    $('th', table).removeClass("current");
                    $('.sortarrow').addClass('sort-down');

                    var columnTh = $('th', table).eq($.inArray(searchRequest.sortColumn, columnNames));
                    columnTh.addClass("current");
                    if (sortOrder == 'DESC') {
                        columnTh.find('.sortarrow').removeClass('sort-down');
                    }
                    resultSizeDiv.show();
                    // Render size div
                    resultSizeDiv.find('.totalNum').text(result.total);
                    if (result.items && result.items.length > 0 ) {
                        var startNum = (searchRequest.pageNumber - 1) * searchRequest.pageSize + 1;
                        resultSizeDiv.find('.startNum').text(startNum);
                        resultSizeDiv.find('.endNum').text(startNum + result.items.length - 1);
                    } else {
                        resultSizeDiv.find('.startNum').text('0');
                        resultSizeDiv.find('.endNum').text('0');
                    }

                    if (searchRequest.pageNumber < 1) {
                        searchRequest.pageNumber = 1;
                        pageNumber = 1;
                    }
                    if (searchRequest.pageNumber > result.totalPageCount) {
                        searchRequest.pageNumber = result.totalPageCount + 1;
                        pageNumber = result.totalPageCount + 1;
                    }

                    // Render pagination numbers div
                    paginationDiv.text('');

                    paginationDiv.append(buildPageNumLink(searchRequest.pageNumber, true));

                    if (searchRequest.pageNumber > 1) {
                        paginationDiv.prepend(buildPageNumLink(searchRequest.pageNumber - 1, false));
                    }
                    if (searchRequest.pageNumber - 1 >= 5) {
                        paginationDiv.prepend($('<span class="fLeft">...</span>'));
                    }
                    for (var i = 3; i >= 1; i--) {
                        if (searchRequest.pageNumber - i >= 2) {
                            paginationDiv.prepend(buildPageNumLink(i, false));
                        }
                    }

                    if (searchRequest.pageNumber < result.totalPageCount) {
                        paginationDiv.append(buildPageNumLink(searchRequest.pageNumber + 1, false));
                    }
                    if (result.totalPageCount - searchRequest.pageNumber >= 5) {
                        paginationDiv.append($('<span class="fLeft">...</span>'));
                    }
                    for (var i = 3; i >=1; i--) {
                        if (searchRequest.pageNumber + 1 + i <= result.totalPageCount) {
                            paginationDiv.append(buildPageNumLink(result.totalPageCount - i + 1, false));
                        }
                    }

                    var goPrevLink = $('<a href="javascript:;" class="paginationBtn jsGoPrev fLeft"><span><span>&lt;&lt; Prev</span></span></a>');
                    if (searchRequest.pageNumber == 1) {
                        goPrevLink.addClass('paginationBtnDisabled');
                    }
                    paginationDiv.prepend(goPrevLink);

                    var goNextLink = $('<a href="javascript:;" class="paginationBtn jsGoNext fLeft"><span><span>Next &gt;&gt;</span></span></a>');
                    if (searchRequest.pageNumber >= result.totalPageCount) {
                        goNextLink.addClass('paginationBtnDisabled');
                    }
                    paginationDiv.append(goNextLink);

                },
                error : function(request, status, error) {
                    alert('Failed to search ' + desc + ': ' + request.responseText);
                }
            });
        }

        // The function to build page number link
        function buildPageNumLink(pageNum, isCurrentPage) {
            var link =  $('<a href="javascript:;" class="paginationBtn jsGoPage fLeft"><span><span>' + pageNum + '</span></span></a>');
            if (isCurrentPage) {
                link.addClass('currentPage');
            }
            return link;
        }
    }

    var notificationsTable;
    function showNotificationsTab() {
        if (!notificationsTable) {
            notificationsTable = new SortPaginateTable(
                $('.notificationsTab table'),
                $('.notificationsTab .paginationWrapper select'),
                $('.notificationsTab .paginationWrapper div.fRight'),
                $('.notificationsTab div.notiLogTblSummary'),
                'notificationLog/searchNotifications',
                ['date', 'sender', 'details'], 'notifications',
                function(result) {
                    $('.notificationsTab table tbody').text('');
                    if (result.items) {
                        for (var i in result.items) {
                            var item = result.items[i];

                            var row = ['<tr>'];
                            row = row.concat(['<td class="firstCol">', formatCaseDate(item.date), '</td>']);
                            row = row.concat(['<td>', item.sender, '</td>']);
                            row = row.concat(['<td class="lastCol">', item.details, '</td>']);
                            row.push('</tr>');
                            $('.notificationsTab table tbody').append($(row.join('')));
                        }
                    }
                });
        }

        notificationsTable.reset();
        notificationsTable.search();
    }

    // Show notifications tab on page load
    showNotificationsTab();


    var errorsTable;
    function showErrorsTab() {
        if (!errorsTable) {
            errorsTable = new SortPaginateTable(
                $('.errorsTab table'),
                $('.errorsTab .paginationWrapper select'),
                $('.errorsTab .paginationWrapper div.fRight'),
                $('.errorsTab div.notiLogTblSummary'),
                'notificationLog/searchErrors',
                ['date', 'details'], 'errors',
                function(result) {
                    $('.errorsTab table tbody').text('');
                    if (result.items) {
                        for (var i in result.items) {
                            var item = result.items[i];

                            var row = ['<tr>'];
                            row = row.concat(['<td class="firstCol">', formatCaseDate(item.date), '</td>']);
                            row = row.concat(['<td class="lastCol">', item.details, '</td>']);
                            row.push('</tr>');
                            $('.errorsTab table tbody').append($(row.join('')));
                        }
                    }
                });
        }

        errorsTable.reset();
        errorsTable.search();
    }

    var infoTable;
    function showInfoTab() {
        if (!infoTable) {
            infoTable = new SortPaginateTable(
                $('.infoTab table'),
                $('.infoTab .paginationWrapper select'),
                $('.infoTab .paginationWrapper div.fRight'),
                $('.infoTab div.notiLogTblSummary'),
                'notificationLog/searchInfos',
                ['date', 'details'], 'info',
                function(result) {
                    $('.infoTab table tbody').text('');
                    if (result.items) {
                        for (var i in result.items) {
                            var item = result.items[i];

                            var row = ['<tr>'];
                            row = row.concat(['<td class="firstCol">', formatCaseDate(item.date), '</td>']);
                            row = row.concat(['<td class="lastCol">', item.details, '</td>']);
                            row.push('</tr>');
                            $('.infoTab table tbody').append($(row.join('')));
                        }
                    }
                });
        }

        infoTable.reset();
        infoTable.search();
    }

    $(".jsPrintNoti").click(function(){
        showPopup(".reportPlaceHolderPopup");
    });

    // Tab switch
    switchTab([showNotificationsTab, showErrorsTab, showInfoTab]);

    function markAllAsRead(items) {
        var ids = [];
        for (var i = 0; i < items.length; i++) {
            ids.push(items[i].id);
        }
        $.ajax({
            url : 'notificationLog/markread',
            type : 'POST',
            cache : false,
            async : true,
            contentType : 'application/json',
            data : JSON.stringify(ids),
            success : function() {
                // set to 0
                $('.jsShowNotifications .notificationNum > span > span').text("0");
            },
            error: function() {
            }
        });
    }
});
