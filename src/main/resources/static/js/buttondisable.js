// Set the date we're counting down to
var countDownDate = new Date("Nov 24, 2017 12:00:00").getTime();
var myBtn = document.getElementById('myBtn');

// Update the count down every 1 second
var x = setInterval(function() {

    // Get todays date and time
    var now = new Date().getTime();

    // Find the distance between now an the count down date
    var distance = countDownDate - now;

    // Time calculations for days, hours, minutes and seconds
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    // Output the result in an element with id="demo"
    document.getElementById("demo").innerHTML = days + "d " + hours + "h "
        + minutes + "m " + seconds + "s ";

    // If the count down is over, write some text
    if (distance < 0) {
        $("#myBtn").removeAttr("disabled");
        $("#myBtn").removeClass().addClass("btnEnable");
        $("#demo").fadeTo(2500, 0);
        myBtn.innerHTML = "Click Me!";
        clearInterval(x);
    }
}, 1000);


/*
var draftdate = new Date(JSON.parse(document.getElementById('draftdate')).getTime());
*/