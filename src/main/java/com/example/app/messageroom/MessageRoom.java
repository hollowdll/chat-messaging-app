package com.example.app.messageroom;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.example.app.user.AppUser;

public class MessageRoom {

	private int messageRoomId;
	private String name;
	private AppUser owner;
	private String hashedPassword;
	private LocalDateTime created;
	
	public MessageRoom() {}

	public MessageRoom(String name, AppUser owner, String hashedPassword) {
		messageRoomId = 0;
		this.name = name;
		this.owner = owner;
		this.hashedPassword = hashedPassword;
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

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
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
				+ ", hashedPassword=" + hashedPassword + ", created=" + created + "]";
	}
	
}
