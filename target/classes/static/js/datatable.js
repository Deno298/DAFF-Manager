$(document).ready( function () {
    var table = $('#employeesTable').DataTable({
        "sAjaxSource": "/employees",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "playerId"},
            { "mData": "firstName" },
            { "mData": "lastName" },
        ]
    });

    $('#employeesTable tbody').on( 'click', 'td', function (event) {
        console.log(table.row(this).data().playerId);
        var playerid = table.row(this).data().playerId;
        var lastname = 1;
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit(playerid);
    });

    setInterval( function () {
        table.ajax.reload();
    }, 3000 );

    function fire_ajax_submit(param1) {

        var array = [];
        array = $("#draftorder").val();
        console.log(array);

        var search = {}
        search["username"] = param1;
        search["leagueId"] = $("#leagueid").val();
        search["draftOrder"] = $("#draftorder").val();


        $("#btn-search").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/search",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        })
    }});