package com.example.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.example.app.dao.AppUserDAO;
import com.example.app.model.MessageRoom;

public class MessageRoomRowMapper implements RowMapper<MessageRoom> {
	
	private final AppUserDAO appUserDAO;
	
	@Autowired
	public MessageRoomRowMapper(AppUserDAO appUserDAO) {
		this.appUserDAO = appUserDAO;
	}

	public MessageRoom mapRow(ResultSet rs, int rowNum) throws SQLException, NoSuchElementException {
		MessageRoom messageRoom = new MessageRoom();
		messageRoom.setMessageRoomId(rs.getInt("message_room_id"));
		messageRoom.setName(rs.getString("name"));
		messageRoom.setOwner(appUserDAO.findById(rs.getInt("user_id")).get());
		messageRoom.setHashedPassword(rs.getString("password"));
		messageRoom.setCreated(rs.getTimestamp("created").toLocalDateTime());
		
		return messageRoom;
	}
	
}
