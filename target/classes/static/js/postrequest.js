$( document ).ready(function() {

	
	// SUBMIT FORM
    $("#customerForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    		firstname : $("#playerId").val(),
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/draftplayer",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "Done"){

				}else{

				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();

    }
    
    function resetData(){
    	$("#firstname").val("");
    	$("#lastname").val("");
    }
})