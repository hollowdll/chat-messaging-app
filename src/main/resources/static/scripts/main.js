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