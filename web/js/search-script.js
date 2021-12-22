/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $(".linkNeedUpdate").click(function () {
        var href = $(this).attr("href");
        href = href + "&selectedStatus=" + $("#selectedStatus").children("option:selected").val();
        $(".linkNeedUpdate").attr("href", href);
    });

    $('.searchForm').submit(function () {
        var selectedStatus = $("<input>")
                .attr("type", "hidden")
                .attr("name", "selectedStatus")
                .attr("value", $("#selectedStatus").children("option:selected").val());
        $('.searchForm').append(selectedStatus);
    });

});

