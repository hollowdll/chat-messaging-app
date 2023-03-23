package com.example.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.example.app.dao.AppUserDAO;
import com.example.app.dao.MessageRoomDAO;
import com.example.app.model.Message;

public class MessageRowMapper implements RowMapper<Message> {

	private final AppUserDAO appUserDAO;
	private final MessageRoomDAO messageRoomDAO;
	
	@Autowired
	public MessageRowMapper(AppUserDAO appUserDAO, MessageRoomDAO messageRoomDAO) {
		this.appUserDAO = appUserDAO;
		this.messageRoomDAO = messageRoomDAO;
	}

	public Message mapRow(ResultSet rs, int rowNum) throws SQLException, NoSuchElementException {
		Message message = new Message();
		message.setMessageId(rs.getLong("message_id"));
		message.setText(rs.getString("text"));
		message.setSender(appUserDAO.findById(rs.getLong("user_id")).get());
		message.setMessageRoom(messageRoomDAO.findById(rs.getLong("message_room_id")).get());
		message.setCreated(rs.getTimestamp("created").toLocalDateTime());
		
		return message;
	}
	
}
