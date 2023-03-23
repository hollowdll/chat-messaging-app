package com.example.app.dao;

import java.util.List;
import java.util.Optional;

import com.example.app.model.MessageRoomMember;

// Database operations for message room members
public interface MessageRoomMemberDAO {

	public void save(MessageRoomMember messageRoomMember);
	public List<MessageRoomMember> findAll();
	public Optional<MessageRoomMember> findById(long messageRoomId, long appUserId);
	public void deleteById(long messageRoomId, long appUserId);
	
}
