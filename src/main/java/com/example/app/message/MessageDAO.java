package com.example.app.message;

import java.util.List;
import java.util.Optional;

// Database operations for messages
public interface MessageDAO {

	// Saves a new message
	// Returns generated id (primary key)
	public int save(Message message);
	
	// Saves a new message with message sender's id
	// Returns generated id (primary key)
	public int saveWithSenderId(Message message, int appUserId);
	
	// Finds all messages
	public List<Message> findAll();
	
	// Finds all messages by message room id
	public List<Message> findAllByMessageRoomId(int id);
	
	// Finds a message by id
	public Optional<Message> findById(int id);
	
	// Deletes a message by id
	public void deleteById(int id);
	
	// Deletes all messages by message room id
	public void deleteAllByMessageRoomId(int id);
	
	// Updates a message by id
	public void updateById(int id, Message message);
	
}
