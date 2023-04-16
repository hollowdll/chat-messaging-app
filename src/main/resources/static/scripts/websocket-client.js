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
	let messageText = document.getElementById("message-text-field").value;
	let messageRoomId = document.getElementById("message-room-id").value;
	
	if (messageText.length < 1 || messageText.length > 100) {
		return alert("Message must be between 1 and 100 characters!");
	}
	
	stompClient.send("/app/chat", {},
		JSON.stringify({'text': messageText, 'messageRoomId': messageRoomId }));
		
	document.getElementById("message-text-field").value = "";
}

// Shows received message from websocket server
function showMessageOutput(messageOutput) {
	let messageRoomId = document.getElementById("message-room-id").value;
	if (messageOutput.messageRoomId != messageRoomId) {
		return;
	}
	
	let chatContent = document.querySelector('.chat-content');
	
	// Create message HTML structure
	let messageDiv = document.createElement("div");
	messageDiv.className = "chat-message";
	let timeText = document.createElement("span");
	timeText.innerText = messageOutput.time;
	let senderText = document.createElement("strong");
	senderText.innerText = messageOutput.sender + ": ";
	senderText.className="text-light";
	let messageText = document.createElement("span");
	messageText.innerText = messageOutput.text;
	messageText.className = "text-break";
	
	messageDiv.appendChild(timeText);
	messageDiv.appendChild(document.createElement("br"));
	messageDiv.appendChild(senderText);
	messageDiv.appendChild(messageText);
	chatContent.appendChild(messageDiv);
	
	// Scroll to the newest message
	chatContent.scrollTop = chatContent.scrollHeight;
}

// When client is ready
window.addEventListener("DOMContentLoaded", _event => {
	// Connect when user joins a chat room
	connect();
	
	// Scroll to the newest message
	let chatContent = document.querySelector('.chat-content');
	chatContent.scrollTop = chatContent.scrollHeight;
})