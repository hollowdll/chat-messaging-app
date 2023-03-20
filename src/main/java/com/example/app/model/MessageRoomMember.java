package com.example.app.model;

import java.time.LocalDateTime;

public class MessageRoomMember {

	private MessageRoom messageRoom;
	private AppUser member;
	private LocalDateTime joined;
	
	public MessageRoomMember() {
		
	}

	public MessageRoomMember(MessageRoom messageRoom, AppUser member, LocalDateTime joined) {
		this.messageRoom = messageRoom;
		this.member = member;
		this.joined = joined;
	}

	public MessageRoom getMessageRoom() {
		return messageRoom;
	}

	public void setMessageRoom(MessageRoom messageRoom) {
		this.messageRoom = messageRoom;
	}

	public AppUser getMember() {
		return member;
	}

	public void setMember(AppUser member) {
		this.member = member;
	}

	public LocalDateTime getJoined() {
		return joined;
	}

	public void setJoined(LocalDateTime joined) {
		this.joined = joined;
	}

	@Override
	public String toString() {
		return "MessageRoomMember [messageRoom=" + messageRoom + ", member=" + member + ", joined=" + joined + "]";
	}
	
}
