package com.example.app.dao;

import java.util.List;
import com.example.app.model.MessageRoom;

public interface MessageRoomDAO {

	public void save(MessageRoom messageRoom);
	public List<MessageRoom> findAll();
	
}
