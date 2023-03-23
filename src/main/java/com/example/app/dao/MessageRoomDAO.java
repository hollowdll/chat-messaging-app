package com.example.app.dao;

import java.util.List;
import java.util.Optional;

import com.example.app.model.MessageRoom;

// Database operations for message rooms
public interface MessageRoomDAO {

	public void save(MessageRoom messageRoom);
	public List<MessageRoom> findAll();
	public Optional<MessageRoom> findById(long id);
	public void deleteById(long id);
	public void updateById(long id, MessageRoom messageRoom);
	
}
