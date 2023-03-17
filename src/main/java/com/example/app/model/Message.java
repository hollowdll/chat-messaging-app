package com.example.app.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Message {

	private Long id;
	private String text;
	private int userId, messageRoomId;
	private String created;
	
	public Message() {
		text = "";
		userId = 0;
		messageRoomId = 0;
		created = LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
	}
	
	public Message(String text, int userId, int messageRoomId, String created) {
		this.text = text;
		this.userId = userId;
		this.messageRoomId = messageRoomId;
		this.created = created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMessageRoomId() {
		return messageRoomId;
	}

	public void setMessageRoomId(int messageRoomId) {
		this.messageRoomId = messageRoomId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + ", userId=" + userId + ", messageRoomId=" + messageRoomId
				+ ", created=" + created + "]";
	}
	
}
