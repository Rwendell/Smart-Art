
var ws;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {

	ws = new WebSocket('ws://proj-309-sb-2.cs.iastate.edu:8080/board/mainBoard');//'ws://localhost:8080/board/mainBoard');

	console.log("ws 1:" + ws.url);

	ws.onmessage = function(data){
            showGreeting(data.data);
	}




	var ws2 = new WebSocket('ws://proj-309-sb-2.cs.iastate.edu:8080/board/2');//ws://localhost:8080/board/2');

    console.log("ws 2:" + ws2.url);

    console.log("The rest of the output will be taken from ws2")

    console.log("-----------------------------------------------------");

    ws2.onmessage = function(data){
        console.log("ws2:   " + data.data);
    }






	 setConnected(true);
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	var data = JSON.stringify({'drawElement': $("#name").val()})
    ws.send(data);
}

function showGreeting(message) {
    if (message.length > 1000){
        $("#greetings").append
        (
            "<tr><td> " +
            message.substring(0,50) + " ... " + message.substring(message.length-51,message.length)
            + "</td></tr>"

        );
    }
    else $("#greetings").append("<tr><td> " + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $("#checkbox").click(("#"));
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });

});

