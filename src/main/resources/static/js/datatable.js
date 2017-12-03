$(document).ready(function() {
    var table = $('table#sample').DataTable({
        'ajax' : '/data/users',
        'serverSide' : true,
        columns : [ {
            data : 'playerId'
        }, {
            data : 'firstName'
        }, {
            data : 'lastName'
        }]
    });
});