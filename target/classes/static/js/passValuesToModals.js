$(document).on("click", ".open-AddLeague", function () {
    var leagueid = $(this).data('id');
    $(".modal-body #leagueid").val( leagueid );
});

