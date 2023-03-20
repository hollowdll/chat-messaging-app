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
	
	public Message(String text, AppUser sender, MessageRoom messageRoom) {
		this.text = text;
		this.sender = sender;
		this.messageRoom = messageRoom;
		created = LocalDateTime.now(ZoneOffset.UTC);
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
	
	// Format to day/month/year hours:minutes:seconds
	public String formatCreated() {
		return created.format(DateTimeFormatter.ofPattern("d/MM/uuuu HH:mm:ss"));
	}
	
	// Format to day/month/year hours:minutes:seconds UTC+0
	public String formatCreatedUTC() {
		return created.format(DateTimeFormatter.ofPattern("d/MM/uuuu HH:mm:ss")) + " UTC+0";
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", text=" + text + ", sender=" + sender + ", messageRoom="
				+ messageRoom + ", created=" + created + "]";
	}
	
}
