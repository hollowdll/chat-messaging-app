package com.example.app.message;

public class OutputMessage {

	private String text;
	private String sender;
	private String time;
	private int messageRoomId;
	
	public OutputMessage() {}

	public OutputMessage(String text, String sender, String time, int messageRoomId) {
		this.text = text;
		this.sender = sender;
		this.time = time;
		this.messageRoomId = messageRoomId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getMessageRoomId() {
		return messageRoomId;
	}

	public void setMessageRoomId(int messageRoomId) {
		this.messageRoomId = messageRoomId;
	}

	@Override
	public String toString() {
		return "OutputMessage [text=" + text + ", sender=" + sender + ", time=" + time + ", messageRoomId="
				+ messageRoomId + "]";
	}
	
}
