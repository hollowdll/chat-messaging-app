package com.example.app.messageroommember;

import java.time.LocalDateTime;

import com.example.app.messageroom.MessageRoom;
import com.example.app.user.AppUser;

public class MessageRoomMember {

	private MessageRoom messageRoom;
	private AppUser member;
	private LocalDateTime joined;
	
	public MessageRoomMember() {}

	public MessageRoomMember(MessageRoom messageRoom, AppUser member) {
		this.messageRoom = messageRoom;
		this.member = member;
		joined = LocalDateTime.now();
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
