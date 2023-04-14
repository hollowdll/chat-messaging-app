// Connected client
let stompClient = null;

// Connects this client to websocket server
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

// Disconnects from websocket server
function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
}

// Sends a message to websocket server
function sendMessage() {
	let text = document.getElementById("text").value;
	let messageRoomId = document.getElementById("message-room-id").value;
	
	if (text.length < 1 || text.length > 100) {
		return alert("Message must be between 1 and 100 characters!");
	}
	
	stompClient.send("/app/chat", {},
		JSON.stringify({'text': text, 'messageRoomId': messageRoomId }));
}

// Shows received message from websocket server
function showMessageOutput(messageOutput) {
	let chatContent = document.querySelector('.chat-content');
	
	// Create message HTML structure
	let messageDiv = document.createElement("div");
	messageDiv.className = "chat-message";
	let timeText = document.createElement("span");
	timeText.innerText = messageOutput.time;
	let senderText = document.createElement("strong");
	senderText.innerText = messageOutput.sender + ": ";
	let messageText = document.createElement("span");
	messageText.innerText = messageOutput.text;
	messageText.className = "text-break";
	
	messageDiv.appendChild(timeText);
	messageDiv.appendChild(document.createElement("br"));
	messageDiv.appendChild(senderText);
	messageDiv.appendChild(messageText);
	chatContent.appendChild(messageDiv);
}

// Connect when user joins a chat room
window.addEventListener("DOMContentLoaded", _event => {
	connect();
})