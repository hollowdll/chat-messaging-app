package com.example.app.message;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.example.app.messageroom.MessageRoom;
import com.example.app.user.AppUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Message {

	private int messageId;
	
	@Size(min=1, max=100, message = "Message must be between 1 and 100 characters")
	private String text;
	
	private AppUser sender;
	
	private MessageRoom messageRoom;
	
	private LocalDateTime created;
	
	public Message() {
		messageId = 0;
		text = "";
		sender = null;
		messageRoom = null;
		created = null;
	}
	
	public Message(String text, AppUser sender, MessageRoom messageRoom) {
		messageId = 0;
		this.text = text;
		this.sender = sender;
		this.messageRoom = messageRoom;
		created = LocalDateTime.now();
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
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
	
	// Format to UTC time
	public String formatCreatedToUTC() {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(created, ZoneId.of("UTC"));
		return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(zonedDateTime);
	}
	
	// Format to Finnish time zone
	public String formatCreatedToFinnish() {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(created, ZoneId.of("Europe/Helsinki"));
		return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(zonedDateTime);
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", text=" + text + ", sender=" + sender + ", messageRoom="
				+ messageRoom + ", created=" + created + "]";
	}
	
}
