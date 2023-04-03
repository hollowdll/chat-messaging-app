// Confirm deletion of a message room
const confirmMessageRoomDeletion = (id, name) => {
	const confirm = window.confirm(
`Do you really want to delete this room?

ID: ${id}
Name: ${name}`
	);
	
	if (confirm) {
		window.location = `/deletemessageroom/${id}`;
	}
}

// Confirm deletion of a message
const confirmMessageDeletion = (id, created, sender, message) => {
	const confirm = window.confirm(
`Do you really want to delete this message?

Sent: ${created}
Sender: ${sender}
Message: ${message}`
	);
	
	if (confirm) {
		window.location = `/deletemessage/${id}`;
	}
}