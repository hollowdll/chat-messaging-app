package com.example.app.messageroommember;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.example.app.messageroom.MessageRoomDAO;
import com.example.app.user.AppUserDAO;

public class MessageRoomMemberRowMapper implements RowMapper<MessageRoomMember> {

	private final MessageRoomDAO messageRoomDAO;
	private final AppUserDAO appUserDAO;
	
	@Autowired
	public MessageRoomMemberRowMapper(MessageRoomDAO messageRoomDAO, AppUserDAO appUserDAO) {
		this.messageRoomDAO = messageRoomDAO;
		this.appUserDAO = appUserDAO;
	}

	public MessageRoomMember mapRow(ResultSet rs, int rowNum) throws SQLException, NoSuchElementException {
		MessageRoomMember messageRoomMember = new MessageRoomMember();
		messageRoomMember.setMessageRoom(messageRoomDAO.findById(rs.getInt("message_room_id")).get());
		messageRoomMember.setMember(appUserDAO.findById(rs.getInt("user_id")).get());
		messageRoomMember.setJoined(rs.getTimestamp("joined").toLocalDateTime());
		
		return messageRoomMember;
	}
	
}
