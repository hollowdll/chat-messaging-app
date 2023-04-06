package com.example.app.messageroom;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.example.app.user.AppUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MessageRoom {

	private int messageRoomId;
	
	@NotBlank(message = "Name cannot be blank")
	@Size(min=1, max=30, message = "Name must be between 1 and 30 characters")
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
	
	// Format to Finland time zone
	public String formatCreatedToFinland() {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(created, ZoneId.of("Europe/Helsinki"));
		return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(zonedDateTime);
	}

	@Override
	public String toString() {
		return "MessageRoom [messageRoomId=" + messageRoomId + ", name=" + name + ", owner=" + owner
				+ ", created=" + created + "]";
	}
	
}
