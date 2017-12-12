$(document).ready( function () {
    var table = $('#employeesTable').DataTable({
        "sAjaxSource": "/employees",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns":[
            {"data":"playerId"}
            ,{"data":"firstName","title":"Name","class":"clickable"}
            ,{"data":"lastName","title":"Home","class":"clickable"},
        ],
    })

    $('#employeesTable tbody').on( 'click', 'td', function () {
        var sdata = table.cell('.selected', 0).data();
        alert(sdata);
    } );
});