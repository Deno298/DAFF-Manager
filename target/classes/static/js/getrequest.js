$( document ).ready(function() {

    // GET REQUEST
    $("#getBtn").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url : "/getallcustomer",
            success: function(result){
                if(result.status == "Done"){
                    $('#getResultDiv .list-group li').remove();
                    $.each(result.data, function(i, player){
                        var customer = "Customer " + i + ": FirstName=" + player.firstName + " ,LastName=" + player.lastName + "<br\>";
                        $('#getResultDiv .list-group').append('<li><h4 class="list-group-item">'+customer+'</h4></li>')
                    });
                    console.log("Success: ", result);
                }else{
                    $("#getResultDiv").html("<strong>HEEEYYY</strong>");
                    console.log("Fail: ", result);
                }
            },
            error : function(e) {
                $("#getResultDiv").html("<strong>HEEEEY</strong>");
                console.log("ERROR: ", e);
            }
        });
    }
})