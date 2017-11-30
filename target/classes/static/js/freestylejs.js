$( document ).ready(function() {

    // GET REQUEST
    $("#getPlayer").click(function (event) {
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet() {
        alert("Heeey")
    }
}