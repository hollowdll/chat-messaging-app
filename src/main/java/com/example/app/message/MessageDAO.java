package com.example.app.message;

import java.util.List;
import java.util.Optional;

// Database operations for messages
public interface MessageDAO {

	public void save(Message message);
	public List<Message> findAll();
	public List<Message> findAllByMessageRoomId(int id);
	public Optional<Message> findById(int id);
	public void deleteById(int id);
	public void updateById(int id, Message message);
	
}
