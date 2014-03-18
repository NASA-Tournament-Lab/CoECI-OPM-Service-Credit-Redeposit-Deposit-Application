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
    setupPage('HELP', 10);


    // On search button click
    $('.jsSearchHelp').click(function() {

        var searchTerm = $('.helpSearchInput').val();

        if ($('.helpSearchInput').hasClass('showPlaceholder')) {
            // Place holder
            searchTerm = '';
        }

        // search(searchTerm);
        window.location.hash = '#search=' + searchTerm;
    });

    // Search function
    function search(searchTerm) {
        $.ajax({
            url : 'help/search',
            type : 'GET',
            cache : true,
            async : true,
            data : {'term' : searchTerm},
            success : function(helpItems) {
                $('.helpItemList').text('');

                if (helpItems && helpItems.length > 0) {
                    for ( var i in helpItems) {
                        var helpItem = helpItems[i];
                        var helpItemRow = ['<li>'];
                        helpItemRow = helpItemRow.concat(['<a href="javascript:;" class="helpTitleLink">', helpItem.title, '<i class="helpArrowIcon"></i></a>']);
                        helpItemRow = helpItemRow.concat(['<p>', helpItem.summary, '</p>']);
                        helpItemRow.push('</li>');
                        helpItemRow = $(helpItemRow.join(''));
                        helpItemRow.find('.helpTitleLink').data('helpItemId', helpItem.id);
                        $('.helpItemList').append(helpItemRow);
                    }
                } else {
                    $('.helpItemList').append("<li><p>No result found.</p></li>");
                }

                if (!searchTerm) {
                    $('#searchBreadcurmb').text('');
                    $('.helpSearchSummary').text('');
                } else {
                    $('#searchBreadcurmb').text('Search Results');
                    $('.helpSearchSummary').html('Showing Search Results for <strong>'+ searchTerm + '</strong>');
                }

                renderAfterSearch();
            },
            error : function(request, status, error) {
                alert('Failed to search help content: ' + request.responseText);
            }
        });
    }

    // Initial search on page load
    //search('');
    onLocationHashChanged();

    // Function to render after searching
    function renderAfterSearch() {

        // Hide article details
        $('.articleTitle').hide();
        $('.helpArticleWrapper').hide();
        $('.relatedTopicsWrapper').hide();

        // Show search result
        $('.searchResult').show();
        $('.helpItemList').show();
    }

    // On breadcrumb link click
    $('#breadcurmbLink').click(function() {
        renderAfterSearch();
    });

    // On article title link clicked
    $('.panelBody').on('click', '.helpTitleLink', function(){

        var helpItemId = $(this).data('helpItemId');
        window.location.hash = '#' + helpItemId;
        onLocationHashChanged();
    });
    
    function goToHelpItem(helpItemId) {
        $.ajax({
            url : 'help/' + helpItemId,
            type : 'GET',
            cache : true,
            async : true,
            success : function(helpItem) {

                // Hide search result
                $('.helpItemList').hide();
                $('.searchResult').hide();

                // Show article details
                $('.articleTitle').text(helpItem.title).show();
                $('.helpArticleWrapper').html(helpItem.content).show();

                // Render related topics
                $('#relatedTopics').text('');
                if (helpItem.related && helpItem.related.length > 0) {
                    for (var i in helpItem.related) {
                        var related = helpItem.related[i];
                        var relatedLink = $('<a href="javascript:;" class="helpTitleLink">' + related.title + '</a>');
                        relatedLink = $(relatedLink);
                        relatedLink.data('helpItemId', related.id);
                        $('#relatedTopics').append(relatedLink);
                        if (i != helpItem.related.length - 1) {
                            $('#relatedTopics').append(', ');
                        }
                    }
                    $('.relatedTopicsWrapper').show();
                } else {
                    $('.relatedTopicsWrapper').hide();
                }
                
            },
            error : function(request, status, error) {
                alert('Failed to get help content: ' + request.responseText);
            }
        });
    }
    function onLocationHashChanged() {
        var hashValue = window.location.hash;
        if (hashValue == null || hashValue == "" || hashValue.indexOf('#') != 0) {
            search('');
            return;
        }
        hashValue = hashValue.substring(1);
        if (hashValue.indexOf('search=') == 0) {
            var searchItem = hashValue.substring(7);
            search(searchItem);
        } else if (!isNaN(hashValue)) {
            goToHelpItem(hashValue);
        }
    }
    
    if ("onhashchange" in window){ // cool browser
        $(window).on('hashchange',onLocationHashChanged);
    } else{ // lame browser
        var lastHash=''
        setInterval(function(){
            if (lastHash != window.location.hash) {
                onLocationHashChanged();
            }
            lastHash = window.location.hash
        }, 100);
    }
});
