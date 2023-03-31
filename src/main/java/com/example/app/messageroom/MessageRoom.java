package com.example.app.messageroom;

import java.time.LocalDateTime;

import com.example.app.user.AppUser;

public class MessageRoom {

	private int messageRoomId;
	private String name;
	private AppUser owner;
	private LocalDateTime created;
	
	public MessageRoom() {
		messageRoomId = 0;
		name = "";
		owner = null;
		created = null;
	}

	public MessageRoom(String name, AppUser owner) {
		messageRoomId = 0;
		this.name = name;
		this.owner = owner;
		created = LocalDateTime.now();
	}

	public int getMessageRoomId() {
		return messageRoomId;
	}

	public void setMessageRoomId(int messageRoomId) {
		this.messageRoomId = messageRoomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AppUser getOwner() {
		return owner;
	}

	public void setOwner(AppUser owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "MessageRoom [messageRoomId=" + messageRoomId + ", name=" + name + ", owner=" + owner
				+ ", created=" + created + "]";
	}
	
}
