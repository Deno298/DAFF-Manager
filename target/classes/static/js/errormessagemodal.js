$(document).ready(function () {
    var error = document.getElementById('id').value;
    if(error === "error"){
        var errormessage = document.getElementById('errormsg').value;
        $('#addPostModal').modal('show');
        document.getElementById("errormessage").innerHTML = errormessage;
    }
});