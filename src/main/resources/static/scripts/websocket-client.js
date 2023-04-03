let stompClient = null;

function connect() {
	let socket = new SockJS("/chat");
	stompClient = Stomp.over(socket);
	stompClient.connect({}, (frame) => {
		console.log("Connected: ", frame);
		stompClient.subscribe("/topic/messages", (messageOutput) => {
			showMessageOutput(JSON.parse(messageOutput.body));
		});
	});
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
}

function sendMessage() {
	let text = document.getElementById("text").value;
	let messageRoomId = document.getElementById("message-room-id").value;
	stompClient.send("/app/chat", {},
		JSON.stringify({'text': text, 'messageRoomId': messageRoomId }));
}

function showMessageOutput(messageOutput) {
	let chatContent = document.querySelector('.chat-content');
	let messageDiv = document.createElement("div");
	messageDiv.className = "chat-message"
	messageDiv.appendChild(document.createTextNode(
`${messageOutput.time}
${messageOutput.sender}: ${messageOutput.text}`
	));
	chatContent.appendChild(messageDiv);
}

window.addEventListener("DOMContentLoaded", _event => {
	connect();
})