package com.example.app.dao;

import java.util.List;
import java.util.Optional;

import com.example.app.model.Message;

// Database operations for messages
public interface MessageDAO {

	public void save(Message message);
	public List<Message> findAll();
	public Optional<Message> findById(int id);
	public void deleteById(int id);
	public void updateById(int id, Message message);
	
}
