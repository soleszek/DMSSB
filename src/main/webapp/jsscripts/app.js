var stompClient = null;

function connect() {
    var socket = new SockJS('/notifications');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/notify', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}


function showGreeting(message) {
    $("#notifications").html(message);
}
