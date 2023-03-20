package com.example.app.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Message {

	private Long messageId;
	private String text;
	private AppUser sender;
	private MessageRoom messageRoom;
	private LocalDateTime created;
	
	public Message() {
		
	}
	
	public Message(String text, AppUser sender, MessageRoom messageRoom, LocalDateTime created) {
		this.text = text;
		this.sender = sender;
		this.messageRoom = messageRoom;
		this.created = created;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public AppUser getSender() {
		return sender;
	}

	public void setSender(AppUser sender) {
		this.sender = sender;
	}

	public MessageRoom getMessageRoom() {
		return messageRoom;
	}

	public void setMessageRoom(MessageRoom messageRoom) {
		this.messageRoom = messageRoom;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", text=" + text + ", sender=" + sender + ", messageRoom="
				+ messageRoom + ", created=" + created + "]";
	}
	
}
