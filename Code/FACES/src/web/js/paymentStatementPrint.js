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


$(function() {
    var context = $('#context').val();
    $.ajax({
        url: context + '/faces/requestPaymentStatementPrint',
        async: false,
        success: function(data) {
            console.log(data);
            if (data == '') {
                alert('There is no payment statement data in the database.');
                return;
            }
            var date = parseDateToString(data.date);
            $('#paymentStatementDateContent').html('Execution Date : ' + date);
            $('#paymentStatementContent').text(data.message);
        },
        error: function(error) {
            alert('fail to get payment statement print data. : ' + error.responseText);
        }
    });

    $('.jsprintStatement').click(function() {
        window.print();
    });

    $('.jsrefresh').click(function() {
        $.ajax({
            url: context + '/faces/requestPaymentStatementPrint',
            async: false,
            success: function(data) {
                if (data == '') {
                    alert('There is no payment statement data in the database.');
                    return;
                }
                var date = parseDateToString(data.date);
                $('#paymentStatementDateContent').html('Execution Date : ' + date);
                $('#paymentStatementContent').text(data.message);
            },
            error: function(error) {
                alert('fail to get payment statement print data. : ' + error.responseText);
            }
        });
    });
});
