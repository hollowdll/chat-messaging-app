package com.example.app.message;

import jakarta.validation.constraints.Size;

public class InputMessage {

	@Size(min=1, max=100, message = "Message must be between 1 and 100 characters")
	private String text;
	
	private int messageRoomId;
	
	public InputMessage() {}
	
	public InputMessage(String text, int messageRoomId) {
		this.text = text;
		this.messageRoomId = messageRoomId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getMessageRoomId() {
		return messageRoomId;
	}

	public void setMessageRoomId(int messageRoomId) {
		this.messageRoomId = messageRoomId;
	}

	@Override
	public String toString() {
		return "InputMessage [text=" + text + ", messageRoomId=" + messageRoomId + "]";
	}
	
}
