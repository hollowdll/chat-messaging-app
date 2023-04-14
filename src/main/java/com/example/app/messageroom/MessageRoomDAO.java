package com.example.app.messageroom;

import java.util.List;
import java.util.Optional;

// Database operations for message rooms
public interface MessageRoomDAO {

	// Saves a new message room
	// Returns generated id (primary key)
	public int save(MessageRoom messageRoom);
	
	// Saves a new message room with message room owner id
	// Returns generated id (primary key)
	public int saveWithOwnerId(MessageRoom messageRoom, int appUserId);
	
	// Finds all message rooms
	public List<MessageRoom> findAll();
	
	// Finds a message room by id
	public Optional<MessageRoom> findById(int id);
	
	// Deletes a message room by id
	public void deleteById(int id);
	
	// Updates a message room by id
	public void updateById(int id, MessageRoom messageRoom);
	
}
