package com.example.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.app.model.MessageRoom;

public class MessageRoomRowMapper implements RowMapper<MessageRoom> {

	public MessageRoom mapRow(ResultSet rs, int rowNum) throws SQLException {
		MessageRoom messageRoom = new MessageRoom();
		messageRoom.setMessageRoomId(rs.getLong("message_room_id"));
		messageRoom.setName(rs.getString("name"));
		messageRoom.setOwner(null);
		messageRoom.setHashedPassword(rs.getString("password"));
		messageRoom.setCreated(rs.getTimestamp("created").toLocalDateTime());
		
		return messageRoom;
	}
	
}
