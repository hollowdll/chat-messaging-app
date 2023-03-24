package com.example.app.messageroom;

import java.util.List;
import java.util.Optional;

// Database operations for message rooms
public interface MessageRoomDAO {

	public void save(MessageRoom messageRoom);
	public List<MessageRoom> findAll();
	public Optional<MessageRoom> findById(int id);
	public void deleteById(int id);
	public void updateById(int id, MessageRoom messageRoom);
	
}
