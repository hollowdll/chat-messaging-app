package com.example.app.messageroommember;

import java.util.List;
import java.util.Optional;

// Database operations for message room members
public interface MessageRoomMemberDAO {

	public void save(MessageRoomMember messageRoomMember);
	public List<MessageRoomMember> findAll();
	public Optional<MessageRoomMember> findById(int messageRoomId, int appUserId);
	public List<MessageRoomMember> findAllByAppUserId(int appUserId);
	public void deleteById(int messageRoomId, int appUserId);
	
}
